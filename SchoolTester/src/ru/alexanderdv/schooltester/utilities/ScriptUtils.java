package ru.alexanderdv.schooltester.utilities;

import java.text.DecimalFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ScriptUtils
{

	static enum VarType
	{
		Int,
		Float,
		String,
		Bool,
		Func
	}

	static enum PropType
	{
		Int,
		Float,
		String,
		Bool
	}

	public static abstract class Var extends Prop
	{
		public Var()
		{
		}

		public static Var parse(VarType type, String val) throws SyntaxException
		{
			return type == VarType.Bool ? BoolVar.parse(val)
					: type == VarType.Float ? FloatVar.parse(val)
							: type == VarType.String ? StringVar.parse(val) : type == VarType.Int ? IntVar.parse(val) : null;
		}

		public abstract String getValue();

		public abstract String getScriptValue();

		public String getStringValue()
		{
			return "\"" + getValue() + "\"";
		}

		@Override
		public String toString()
		{
			return getValue();
		}

	}

	static class IntVar extends Var
	{
		int value;

		public IntVar(int i)
		{
			super();
			value = i;
		}

		public IntVar()
		{
			super();
		}

		public static IntVar parse(String val) throws SyntaxException
		{
			IntVar intProp = new IntVar();
			intProp.value = (int) calc(val.replace("--", "+").replace("+-", "-"));
			return intProp;
		}

		@Override
		public String getValue()
		{
			return value + "";
		}

		@Override
		public String getScriptValue()
		{
			return getValue();
		}

		public static IntVar cast(Var var) throws CastingException
		{
			try
			{
				int f = Integer.parseInt(var.getValue());
				return new IntVar(f);
			}
			catch (NumberFormatException e)
			{
				throw new CastingException("This is not integer!");
			}
		}
	}

	public static class CastingException extends Exception
	{
		private static final long serialVersionUID = 7620048197733124490L;

		public CastingException(String s)
		{
			super(s);
		}
	}

	static class FloatVar extends Var
	{
		float value;

		public FloatVar(float f)
		{
			super();
			value = f;
		}

		public FloatVar()
		{
			super();
		}

		public static FloatVar parse(String val) throws SyntaxException
		{
			FloatVar floatProp = new FloatVar();
			floatProp.value = (float) calc(val.replace("--", "+").replace("+-", "-"));
			return floatProp;
		}

		@Override
		public String getValue()
		{
			return value + "";
		}

		@Override
		public String getScriptValue()
		{
			return getValue();
		}

		public static FloatVar cast(Var var) throws CastingException
		{
			try
			{
				float f = Float.parseFloat(var.getValue());
				return new FloatVar(f);
			}
			catch (NumberFormatException e)
			{
				throw new CastingException("This is not float number!");
			}
		}
	}

	private static double calc(String s)
	{
		// ArrayList<String> strs=new ArrayList<String>();
		// for(;s.indexOf("*");)
		if (s.indexOf("/") != -1)
			return Double.parseDouble(s.substring(0, s.indexOf("/"))) / Double.parseDouble(s.substring(s.indexOf("/") + 1));
		if (s.indexOf("*") == -1)
			return Double.parseDouble(s);
		double res = Double.parseDouble(s.substring(0, s.indexOf("*"))) * Double.parseDouble(s.substring(s.indexOf("*") + 1, s.indexOf("-"))) - Double
				.parseDouble(s.substring(s.indexOf("-") + 1, s.indexOf("+"))) + Double.parseDouble(s.substring(s.indexOf("+") + 1));
		return res;
	}

	static class StringVar extends Var
	{
		String value;

		public StringVar(String s)
		{
			super();
			value = s;
		}

		public StringVar()
		{
			super();
		}

		public static StringVar parse(String val) throws SyntaxException
		{
			String b1 = "";
			boolean inS = false;
			ArrayList<String> strs = new ArrayList<String>();
			String cur = "";
			for (char c : val.toCharArray())
				if (c == '\"')
				{
					inS = !inS;
					cur += c;
				}
				else if (c == '+')
					if (!inS)
					{
						strs.add(cur);
						cur = "";
					}
					else cur += c;
				else cur += c;
			strs.add(cur);
			for (String s : strs)
			{
				int start, end;
				if (s.endsWith("]"))
				{
					String inBrackets = s.substring(s.lastIndexOf("[") + 1, s.length() - 1);
					String first = inBrackets.substring(0, inBrackets.indexOf(";"));
					String second = inBrackets.substring(inBrackets.indexOf(";") + 1);
					start = first.equals("") ? 0 : Integer.parseInt(first);
					end = second.equals("") ? s.lastIndexOf("[") - 2 : Integer.parseInt(second);
					s = s.substring(1, s.lastIndexOf("[") - 1);
				}
				else
				{
					s = s.substring(1, s.length() - 1);
					start = 0;
					end = s.length();
				}
				start = (s.length() + 1 + start % (s.length() + 1)) % (s.length() + 1);
				end = (s.length() + 1 + end % (s.length() + 1)) % (s.length() + 1);
				s = s.replace("\\\"", "\"").replace("\\\\", "\\\\");
				if (start > end)
				{
					String s2 = "";
					for (int i = s.length() - 1; i >= 0; s2 += s.charAt(i), i--);
					s = s2;
					int start2 = start;
					start = end;
					end = start2;
				}
				b1 += s.substring(start, end);
			}
			StringVar stringProp = new StringVar();
			stringProp.value = b1;
			return stringProp;
		}

		@Override
		public String getValue()
		{
			return value + "";
		}

		@Override
		public String getScriptValue()
		{
			return "\"" + getValue().replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
		}

		public static StringVar cast(Var var)
		{
			return new StringVar(var.getValue());
		}
	}

	static class BoolVar extends Var
	{
		boolean value;

		public BoolVar(boolean b)
		{
			super();
			value = b;
		}

		public BoolVar()
		{
			super();
		}

		public static BoolVar parse(String val) throws SyntaxException
		{
			boolean b1 = false;
			for (String s : val.split("[|][|]"))
			{
				boolean b2 = true;
				for (String s2 : s.split("[&][&]"))
					if (s2.equals("!true") || s2.equals("false"))
						b2 = false;
					else if (s2.equals("true") || s2.equals("!false"))
						b2 = b2 && true;
					else throw new SyntaxException("Not bool value '" + s2 + "' at '" + val + "'!");
				b1 = b1 || b2;
			}
			BoolVar boolProp = new BoolVar();
			boolProp.value = b1;
			return boolProp;
		}

		@Override
		public String getValue()
		{
			return value + "";
		}

		@Override
		public String getScriptValue()
		{
			return getValue();
		}
	}

	public static class SyntaxException extends Exception
	{
		private static final long serialVersionUID = 2227147308001937925L;

		public SyntaxException(String s)
		{
			super(s);
		}
	}

	// public static abstract class Any<Type>
	// {
	// }
	//
	// public static abstract class Do
	// {
	// public abstract void DoThisDo();
	// }
	//
	// public static abstract class Operator<_Prop extends Prop<?>, Value> extends Do
	// {
	// _Prop prop;
	// }
	//
	// public static class Set<_Prop extends Prop<Value>, Value> extends Operator<_Prop, Value>
	// {
	//
	// @Override
	// public void DoThisDo()
	// {
	// // TODO Auto-generated method stub
	//
	// }
	// }
	//
	// public static abstract class Action<_Func extends Func<NoneType>, Value> extends Do
	// {
	// _Func func;
	// Value val;
	// }
	//
	// public static class NoneType
	// {
	// }
	//
	// public static class While<_Func extends Func<NoneType>> extends Action<_Func, String>
	// {
	//
	// @Override
	// public void DoThisDo()
	// {
	// while(parseBool(val))
	//
	// }
	// }
	//
	// public static class If<_Func extends Func<NoneType>> extends Action<_Func, String>
	// {
	//
	// @Override
	// public void DoThisDo()
	// {
	// // TODO Auto-generated method stub
	//
	// }
	// }
	//
	// public static class NoneIf<_Func extends Func<NoneType>> extends Action<_Func, NoneType>
	// {
	//
	// @Override
	// public void DoThisDo()
	// {
	// // TODO Auto-generated method stub
	//
	// }
	// }
	//
	// public static abstract class Func<Type>
	// {
	// Do[] actions;
	//
	// public Func(Do[] actions)
	// {
	// super();
	// this.actions = actions;
	// }
	//
	// private Type doFunc(Prop... props)
	// {
	// return null;
	// }
	// }
	static abstract class Function extends Prop
	{
		public abstract Var doFunc(/* VarType[] types, */String[] split) throws SyntaxException;
	}

	public static class Script
	{
		private final HashMap<String, Prop> properties;

		public Script()
		{
			this.properties = new HashMap<String, Prop>();
			this.properties.putAll(ScriptUtils.getFunctions());
		}

		public HashMap<String, Prop> getProps()
		{
			return properties;
		}

		public HashMap<String, Var> getVars()
		{
			HashMap<String, Var> vars = new HashMap<String, Var>();
			for (String name : properties.keySet())
				if (properties.get(name) instanceof Var)
					vars.put(name, (Var) properties.get(name));
			return vars;
		}

		public HashMap<String, String> getVarsToString()
		{
			HashMap<String, String> varsToString = new HashMap<String, String>();
			for (String name : properties.keySet())
				if (properties.get(name) instanceof Prop)
					varsToString.put(name, properties.get(name).toString());
			return varsToString;
		}

		public HashMap<String, Function> getFunctions()
		{
			HashMap<String, Function> functions = new HashMap<String, Function>();
			for (String name : properties.keySet())
				if (properties.get(name) instanceof Function)
					functions.put(name, (Function) properties.get(name));
			return functions;
		}
	}

	static abstract class Prop
	{
	}

	public static Script parseScript(String string) throws SyntaxException, CastingException
	{
		Script script = new Script();
		// HashMap<String, Var> variables = script.getVars();
		// HashMap<String, Function> functions = script.getFunctions();
		int i = 0;
		for (String line : string.replace("\\n", "\n").split("\n"))
		{
			for (VarType type : VarType.values())
				if (line.toLowerCase().startsWith(type.name().toLowerCase() + " "))
				{
					Var var;
					String key = line.substring(type.name().length() + 1);
					if (key.contains("="))
					{
						key = key.substring(0, key.indexOf("="));
						var = Var.parse(type, replaceVars(type, script.getVars(), line.substring(type.name().length() + 1 + key.length() + 1)));
					}
					else if (key.contains(":"))
					{
						key = key.substring(0, key.indexOf(":"));
						String func = line.substring(type.name().length() + 1 + key.length() + 1, line.indexOf("("));
						var = script.getFunctions().get(func).doFunc(replaceVars(type, script.getVars(), line.substring(line.indexOf("(") + 1, line.indexOf(
								")"))).split(","));
					}
					else throw new SyntaxException("At line " + i + ": There must be casting or setting!");
					if (!script.getProps().containsKey(key))
						script.getProps().put(key, var);
					else throw new SyntaxException("At line " + i + ": Property '" + key + "' already exists!");
				}
			i++;
		}
		return script;
	}

	private static String replaceVars(VarType type, HashMap<String, Var> map, String value)
	{
		String res = "";
		TreeMap<Integer, SimpleEntry<String, Integer>> ss = new TreeMap<Integer, SimpleEntry<String, Integer>>();
		for (String s : map.keySet())
		{
			String val = value;
			for (int vv, lastV = 0; (vv = val.indexOf(s)) != -1; val = (vv != -1 ? val.substring(vv + s.length()) : val), lastV += vv + s.length())
			{
				int v = vv + lastV;

				if (vv - 1 == -1 ? true : !Character.isLetterOrDigit(val.charAt(vv - 1)))
					if (vv + s.length() == val.length() ? true : !Character.isLetterOrDigit(val.charAt(vv + s.length())))
						if (type == VarType.String)
						{
							ss.put(v, new SimpleEntry<String, Integer>(map.get(s).getStringValue(), s.length()));
							continue;
						}
						else
						{
							ss.put(v, new SimpleEntry<String, Integer>(map.get(s).getScriptValue(), s.length()));
						}
				if (type == VarType.String)
					if ((vv - 1 == -1 ? false : val.charAt(vv - 1) == '[') && (vv + s.length() == val.length() ? false : val.charAt(vv + s.length()) == ';')
							|| (vv - 1 == -1 ? false : val.charAt(vv - 1) == ';') && (vv + s.length() == val.length() ? false
									: val.charAt(vv + s.length()) == ']'))
					{
						ss.put(v, new SimpleEntry<String, Integer>(map.get(s).getScriptValue(), s.length()));
					}
			}
		}
		int l = 0;
		for (Integer ii : ss.keySet())
		{
			res += value.substring(l, ii) + ss.get(ii).getKey();
			l = ii + ss.get(ii).getValue();
		}
		res += value.substring(l);
		return res;
	}

	static HashMap<String, Function> mainFuncs = new HashMap<String, Function>();
	static
	{
		mainFuncs.put("randf", new Function()
		{

			@Override
			public FloatVar doFunc(String[] args) throws SyntaxException
			{
				if (args.length == 3)
					try
					{
						float start = Float.parseFloat(args[0]);
						float end = Float.parseFloat(args[1]);
						int digitsAfterComma = Integer.parseInt(args[2]);
						float rand = (float) (start + Math.random() * (end - start));
						DecimalFormat f = new DecimalFormat();
						f.setMaximumFractionDigits(digitsAfterComma);
						float formated = Float.parseFloat(f.format(rand).replace(",", "."));
						return new FloatVar(formated);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						throw new SyntaxException("Args 1, 2 must type of numbers and arg 3 must be type of integer!");
					}
				else if (args.length == 2)
					try
					{
						float start = Float.parseFloat(args[0]);
						float end = Float.parseFloat(args[1]);
						float rand = (float) (start + Math.random() * (end - start));
						return new FloatVar(rand);
					}
					catch (Exception e)
					{
						throw new SyntaxException("Args must type of numbers!");
					}
				else throw new SyntaxException("Args length must be 3 or 2!");
			}
		});
		mainFuncs.put("randi", new Function()
		{

			@Override
			public IntVar doFunc(/* VarType[] types, */String[] args) throws SyntaxException
			{
				if (args.length != 2)
					throw new SyntaxException("Args length must be 2!");
				try
				{
					int start = Integer.parseInt(args[0]);
					int end = Integer.parseInt(args[1]);
					return new IntVar((int) (start + Math.random() * (end - start)));
				}
				catch (Exception e)
				{
					throw new SyntaxException("Args must type of integers!");
				}
			}
		});
		mainFuncs.put("int", new Function()
		{

			@Override
			public IntVar doFunc(/* VarType[] types, */String[] args) throws SyntaxException
			{
				if (args.length == 1)
					try
					{
						String s = args[0].startsWith("\"") && args[0].endsWith("\"") ? StringVar.parse(args[0]).getValue() : args[0];
						return new IntVar(Integer.parseInt(s));
					}
					catch (Exception e)
					{
						throw new SyntaxException("Unknown error in syntax!");
					}
				else throw new SyntaxException("Args length must be 1!");
			}
		});
		mainFuncs.put("float", new Function()
		{

			@Override
			public FloatVar doFunc(/* VarType[] types, */String[] args) throws SyntaxException
			{
				if (args.length == 1)
					try
					{
						String s = args[0].startsWith("\"") && args[0].endsWith("\"") ? StringVar.parse(args[0]).getValue() : args[0];
						return new FloatVar(Float.parseFloat(s));
					}
					catch (Exception e)
					{
						throw new SyntaxException("Unknown error in syntax!");
					}
				else throw new SyntaxException("Args length must be 1!");
			}
		});
		mainFuncs.put("bool", new Function()
		{

			@Override
			public BoolVar doFunc(/* VarType[] types, */String[] args) throws SyntaxException
			{
				if (args.length == 1)
					try
					{
						String s = args[0].startsWith("\"") && args[0].endsWith("\"") ? StringVar.parse(args[0]).getValue() : args[0];
						return new BoolVar(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("1") || s.equalsIgnoreCase("1.0"));
					}
					catch (Exception e)
					{
						throw new SyntaxException("Unknown error in syntax!");
					}
				else throw new SyntaxException("Args length must be 1!");
			}
		});
		mainFuncs.put("if", new Function()
		{

			@Override
			public Var doFunc(/* VarType[] types, */String[] args) throws SyntaxException
			{
				if (args.length == 3)
					try
					{
						return new BoolVar(Boolean.parseBoolean(StringVar.parse(args[0]).getValue()));
					}
					catch (Exception e)
					{
						throw new SyntaxException("Args must type of strings!");
					}
				throw new SyntaxException("Args length must be 1!");
			}
		});
	}

	private static HashMap<String, Function> getFunctions()
	{
		return mainFuncs;
	}

	public static void main(String[] args)
	{
		// String script = "Int sqxir:randi(1,999)\\nInt sqyir:randi(1,999)\\nFloat sqxfc#sqxir\\nFloat sqyfc#sqyir\\nFloat sqx=sqxfc/10\\nFloat
		// sqy=sqyfc/10\\n\\nInt ki1:randi(1,100)\\nInt ki2:randi(1,100)\\nFloat kf1#ki1\\nFloat kf2#ki2\\nFloat k1=kf1/10\\nFloat k2=kf2/10\\n\\nInt
		// bi1:randi(1,100)\\nInt bi2:randi(1,100)\\nFloat bf1#bi1\\nFloat bf2#bi2\\nFloat b1=bf1/10\\nFloat b2=bf2/10\\n\\nString k1s#k1\\nString
		// k2s#k2\\nString b1s#b1\\nString b2s#b2\\n\\nFloat res1=k1*sqx-sqy+b1\\nFloat res2=k2*sqy-sqx+b2\\nString res1s#res1\\nString res2s#res2\\n\\nString
		// expression1=k1s+\"x\"+\"+y\"+\"+(\"+b1s+\")\"+\"=\"+res1s\\nString expression2=k2s+\"y\"+\"+x\"+\"+(\"+b2s+\")\"+\"=\"+res2s\\n\\nInt
		// rxi1:randi(1,999)\\nInt ryi1:randi(1,999)\\nFloat rxf1#rxi1\\nFloat ryf1#ryi1\\nFloat rx1=rxf1/10\\nFloat ry1=ryf1/10\\n\\nInt
		// rxi2:randi(1,999)\\nInt ryi2:randi(1,999)\\nFloat rxf2#rxi2\\nFloat ryf2#ryi2\\nFloat rx2=rxf2/10\\nFloat ry2=ryf2/10\\n\\nInt
		// rxi3:randi(1,999)\\nInt ryi3:randi(1,999)\\nFloat rxf3#rxi3\\nFloat ryf3#ryi3\\nFloat rx3=rxf3/10\\nFloat ry3=ryf3/10\\n\\nInt
		// rxi4:randi(1,999)\\nInt ryi4:randi(1,999)\\nFloat rxf4#rxi4\\nFloat ryf4#ryi4\\nFloat rx4=rxf4/10\\nFloat ry4=ryf4/10\\n\\nInt
		// rxi5:randi(1,999)\\nInt ryi5:randi(1,999)\\nFloat rxf5#rxi5\\nFloat ryf5#ryi5\\nFloat rx5=rxf5/10\\nFloat ry5=ryf5/10";
		// String script = "Float sqx:randf(0.1,10,2)\\nString expression1=sqx\\nFloat sqy:randf(0.1,10,2)\\nString expression2=sqy";
		String script = "Float minX=0.1\\nFloat minY=0.1\\nFloat maxX=100\\nFloat maxY=100\\n\\nFloat minK=0.1\\nFloat minB=0.1\\nFloat maxK=10\\nFloat maxB=10\\n\\nInt digitsAfterComma=1\\n\\nFloat sqx:randf(minX,maxX,digitsAfterComma)\\nFloat sqy:randf(minY,maxY,digitsAfterComma)\\n\\nFloat k1:randf(minK,maxK,digitsAfterComma)\\nFloat k2:randf(minK,maxK,digitsAfterComma)\\n\\nFloat b1:randf(minB,maxB,digitsAfterComma)\\nFloat b2:randf(minB,maxB,digitsAfterComma)\\n\\nFloat res1=k1*sqx-sqy+b1\\nFloat res2=k2*sqy-sqx+b2\\n\\nString expression1=k1+\"x\"+\"-y\"+\"+(\"+b1+\")\"+\"=\"+res1\\nString expression2=k2+\"y\"+\"-x\"+\"+(\"+b2+\")\"+\"=\"+res2\\n\\nFloat rx1:randf(minX,maxX,digitsAfterComma)\\nFloat ry1:randf(minY,maxY,digitsAfterComma)\\n\\nFloat rx2:randf(minX,maxX,digitsAfterComma)\\nFloat ry2:randf(minY,maxY,digitsAfterComma)\\n\\nFloat rx3:randf(minX,maxX,digitsAfterComma)\\nFloat ry3:randf(minY,maxY,digitsAfterComma)\\n\\nFloat rx4:randf(minX,maxX,digitsAfterComma)\\nFloat ry4:randf(minY,maxY,digitsAfterComma)\\n\\nFloat rx5:randf(minX,maxX,digitsAfterComma)\\nFloat ry5:randf(minY,maxY,digitsAfterComma)";
		try
		{
			HashMap<String, String> args2 = parseScript(script).getVarsToString();
			System.out.println(args2.get("expression1"));
			System.out.println(args2.get("expression2"));
			System.out.println(args2.get("sqx"));
			System.out.println(args2.get("sqy"));
		}
		catch (SyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CastingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

	}
}
