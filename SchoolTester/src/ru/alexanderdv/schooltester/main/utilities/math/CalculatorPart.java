package ru.alexanderdv.schooltester.main.utilities.math;

import java.net.URL;

import javafx.scene.AccessibleAction;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.simpleutilities.MathUtils;
import ru.alexanderdv.simpleutilities.MathUtils.MathAction;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class CalculatorPart extends ProtectedFXWindow
{
	public static CalculatorPart instance;

	public CalculatorPart(URL url, boolean inDevelope)
	{
		super(null, url, 1, 1, inDevelope, false);
		instance = this;
		createActionListeners();
	}

	@Override
	protected void _resize(int w, int h)
	{

	}

	boolean piSym;
	boolean rootUp;
	boolean powerUp;
	MathAction[][][] mathActions = new MathAction[][][]
	{
			{
					{
							new MathAction("!", (l, r) ->
							{
								int b = 1;
								for (int i = 1; i <= l; b *= i, i++);
								return b;
							}),
							new MathAction("sin>", (l, r) -> Math.sin(r)),
							new MathAction("cos>", (l, r) -> Math.cos(r)),
							new MathAction("tan>", (l, r) -> Math.tan(r)),
							new MathAction("round>", (l, r) -> Math.round(r)),
							new MathAction("abs>", (l, r) -> Math.abs(r)),
							new MathAction("√", (l, r) -> Math.pow(r, 1d / 2d)),
							// new MathAction("Int>", (l, r) -> Math.int(r)),
							// new MathAction("dms>", (l, r) -> Math.dms(r)),
							// new MathAction("Inv>", (l, r) -> Math.inv(r)),
							new MathAction("sinh>", (l, r) -> Math.sinh(r)),
							new MathAction("cosh>", (l, r) -> Math.cosh(r)),
							new MathAction("tanh>", (l, r) -> Math.tanh(r)),
							new MathAction("sin>", (l, r) -> Math.sin(r)),
							new MathAction("cos>", (l, r) -> Math.cos(r)),
							new MathAction("tan>", (l, r) -> Math.tan(r)),
							new MathAction("log>", (l, r) -> Math.log(r)),
					// new MathAction("In>", (l, r) -> Math.)
					}
			},
			{
					{
							new MathAction("<min>", (l, r) -> Math.min(l, r)),
							new MathAction("<max>", (l, r) -> Math.max(l, r))
					},
					{
							new MathAction("^", (l, r) -> Math.pow(l, r)),
							new MathAction("√", (l, r) -> Math.pow(r, 1d / l))
					},
					{
							new MathAction("*", (l, r) -> l * r),
							new MathAction("/", (l, r) -> l / r),
							new MathAction("%", (l, r) -> l % r)
					},
					{
							new MathAction("+", (l, r) -> l + r),
							new MathAction("-", (l, r) -> l - r)
					}
			}
	};

	private void createActionListeners()
	{

		InitCalculatorPart.instance.btn0.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "0"));
		InitCalculatorPart.instance.btn1.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "1"));
		InitCalculatorPart.instance.btn2.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "2"));
		InitCalculatorPart.instance.btn3.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "3"));
		InitCalculatorPart.instance.btn4.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "4"));
		InitCalculatorPart.instance.btn5.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "5"));
		InitCalculatorPart.instance.btn6.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "6"));
		InitCalculatorPart.instance.btn7.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "7"));
		InitCalculatorPart.instance.btn8.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "8"));
		InitCalculatorPart.instance.btn9.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "9"));
		InitCalculatorPart.instance.btnBackspace.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText().substring(
				0, Math.max(0, InitCalculatorPart.instance.area.getText().length() - 1))));
		InitCalculatorPart.instance.btnLeftBracket.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "("));
		InitCalculatorPart.instance.btnRightBracket.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText()
				+ ")"));
		InitCalculatorPart.instance.btnClear.setOnAction(e -> InitCalculatorPart.instance.area.setText(""));
		InitCalculatorPart.instance.btnMinus.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "-"));
		InitCalculatorPart.instance.btnPlus.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "+"));
		InitCalculatorPart.instance.btnMultiply.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "*"));
		InitCalculatorPart.instance.btnDivide.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "/"));
		InitCalculatorPart.instance.btnRemainder.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "%"));
		InitCalculatorPart.instance.btnFactorial.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "!"));
		InitCalculatorPart.instance.btnPower.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "^"));
		InitCalculatorPart.instance.btnRoot.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "√"));
		InitCalculatorPart.instance.btnDot.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + "."));
		InitCalculatorPart.instance.btnEquals.setOnAction(e -> InitCalculatorPart.instance.area.setText("" + MathUtils.calculate(
				InitCalculatorPart.instance.area.getText(), mathActions)));
		InitCalculatorPart.instance.btnPi.setOnAction(e -> InitCalculatorPart.instance.area.setText(InitCalculatorPart.instance.area.getText() + (piSym ? "π"
				: Math.PI)));
		InitCalculatorPart.instance.area.setOnKeyReleased(e ->
		{
			switch (e.getCharacter().toLowerCase())
			{
				case "0":
				case "⁰":
					InitCalculatorPart.instance.btn0.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "1":
				case "¹":
					InitCalculatorPart.instance.btn1.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "2":
				case "²":
					InitCalculatorPart.instance.btn2.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "3":
				case "³":
					InitCalculatorPart.instance.btn3.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "4":
				case "⁴":
					InitCalculatorPart.instance.btn4.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "5":
				case "⁵":
					InitCalculatorPart.instance.btn5.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "6":
				case "⁶":
					InitCalculatorPart.instance.btn6.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "7":
				case "⁷":
					InitCalculatorPart.instance.btn7.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "8":
				case "⁸":
					InitCalculatorPart.instance.btn8.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "9":
				case "⁹":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case ".":
				case ",":
				case "˙":
				case "'":
					InitCalculatorPart.instance.btnDot.executeAccessibleAction(AccessibleAction.FIRE);
					break;

				case "=":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "-":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "+":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "*":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "/":
				case ":":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "%":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "!":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "^":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "v":
				case "√":
					InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);// "⁰¹²³⁴⁵⁶⁷⁸⁹"
					break;
				case "(":
				case "{":
				case "[":
					InitCalculatorPart.instance.btnLeftBracket.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case ")":
				case "]":
				case "}":
					InitCalculatorPart.instance.btnRightBracket.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				case "π":
					InitCalculatorPart.instance.btnPi.executeAccessibleAction(AccessibleAction.FIRE);
					break;
				default:

					switch (e.getCode())
					{
						case NUMPAD0:
						case DIGIT0:
							InitCalculatorPart.instance.btn0.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD1:
						case DIGIT1:
							InitCalculatorPart.instance.btn1.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD2:
						case DIGIT2:
							InitCalculatorPart.instance.btn2.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD3:
						case DIGIT3:
							InitCalculatorPart.instance.btn3.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD4:
						case DIGIT4:
							InitCalculatorPart.instance.btn4.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD5:
						case DIGIT5:
							InitCalculatorPart.instance.btn5.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD6:
						case DIGIT6:
							InitCalculatorPart.instance.btn6.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD7:
						case DIGIT7:
							InitCalculatorPart.instance.btn7.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD8:
						case DIGIT8:
							InitCalculatorPart.instance.btn8.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case NUMPAD9:
						case DIGIT9:
							InitCalculatorPart.instance.btn9.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case COMMA:
						case DECIMAL:
							InitCalculatorPart.instance.btnDot.executeAccessibleAction(AccessibleAction.FIRE);
							break;

						case ENTER:
							InitCalculatorPart.instance.btnEquals.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case MINUS:
							InitCalculatorPart.instance.btnMinus.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case PLUS:
							InitCalculatorPart.instance.btnPlus.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case MULTIPLY:
							InitCalculatorPart.instance.btnMultiply.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case DIVIDE:
							InitCalculatorPart.instance.btnDivide.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case DEAD_TILDE:
						case R:
							InitCalculatorPart.instance.btnRemainder.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case F:
							InitCalculatorPart.instance.btnFactorial.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case P:
						case Y:
							InitCalculatorPart.instance.btnPower.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case V:
						case Q:
							InitCalculatorPart.instance.btnRoot.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case OPEN_BRACKET:
							InitCalculatorPart.instance.btnLeftBracket.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case CLOSE_BRACKET:
							InitCalculatorPart.instance.btnRightBracket.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case C:
							InitCalculatorPart.instance.btnClear.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case BACK_SPACE:
							InitCalculatorPart.instance.btnBackspace.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						case I:
							InitCalculatorPart.instance.btnPi.executeAccessibleAction(AccessibleAction.FIRE);
							break;
						default:
							break;
					}
					break;
			}
		});
	}

	@Override
	public String name()
	{
		return "calculator";
	}
}
