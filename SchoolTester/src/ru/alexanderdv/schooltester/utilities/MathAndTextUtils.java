package ru.alexanderdv.schooltester.utilities;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

import ru.alexanderdv.schooltester.main.Main;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public class MathAndTextUtils
{
	private static final MessageSystem msgSys=Main.msgSys;
	/**
	 * 
	 * @param text
	 * @param font
	 * @return
	 */
	public static Dimension size(String text, Font font)
	{
		int maxWidth = 0, allHeight = 0;
		for (String s : text.split("\n"))
		{
			Rectangle r = font.getStringBounds(s, new FontRenderContext(null, true, true)).getBounds();
			maxWidth = (int) Math.max(maxWidth, r.getWidth());
			allHeight += r.getHeight();
		}
		return new Dimension(maxWidth, allHeight);
	}

	/**
	 * 
	 * @param text
	 * @param font
	 * @return
	 */
	public static Dimension size(String text, javafx.scene.text.Font font)
	{
		return size(text, new Font(font.getName(), font.getStyle().equalsIgnoreCase("bold") ? Font.BOLD
				: font.getStyle().equalsIgnoreCase("italic") ? Font.ITALIC : Font.PLAIN, (int) font.getSize()));
	}

	public static String changeTextToWidth(String s, int stringWidth, boolean removeSpace, boolean removeAllSpaces)
	{
		StringBuilder asd = new StringBuilder();
		for (String s8 : s.split("\n"))
		{
			StringBuilder s6 = new StringBuilder();
			for (int i = 0; i < Math.ceil((double) s8.length() / (double) stringWidth); i++)
			{
				StringBuilder s3 = new StringBuilder(s8.substring(Math.min(i * stringWidth, s8.length()), Math.min(i * stringWidth + stringWidth, s8.length()))
						+ "\n");
				while (removeAllSpaces && s3.charAt(0) == ' ')
					s3.delete(0, 1);
				if (removeSpace && s3.charAt(0) == ' ')
					s3.delete(0, 1);
				s6.append(s3);
			}
			asd.append(s6);
		}
		return asd.toString();
	}

	public static int parseI(String text)
	{
		String res = text.startsWith("-") ? "-" : "";
		if (res.equals("-"))
			text = text.substring(1);
		for (char c : text.toCharArray())
			if (Character.isDigit(c))
				res += c;
			else break;
		return (res.equals("-") || res.equals("")) ? 0 : Integer.parseInt(res);
	}

	public static String fromTenDigitsSystemToAnotherSystem(int i, char[] n)
	{
		for (int j = 0; j < n.length; j++)
			for (int k = 0; k < n.length; k++)
				if (n[j] == n[k] && j != k)
					throw new NullPointerException(msgSys.getMsg("charactersAreRepeating"));
		String s = "";
		for (; i != 0; i = (i - i % n.length) / n.length)
			s = n[i % n.length] + s;
		return s;
	}

	public static int fromAnotherSystemToTenDigits(String i, char[] n)
	{
		for (int j = 0; j < n.length; j++)
			for (int k = 0; k < n.length; k++)
				if (n[j] == n[k] && j != k)
					throw new NullPointerException(msgSys.getMsg("charactersAreRepeating"));
		ArrayList<Character> cc = new ArrayList<Character>();
		for (char c : n)
			cc.add(c);
		int s = 0;
		for (int j = i.length() - 1; j >= 0; j--)
		{
			s += cc.indexOf(i.charAt(j)) * Math.pow(n.length, i.length() - 1 - j);
			if (cc.indexOf(i.charAt(j)) == -1)
				throw new NullPointerException(msgSys.getMsg("characterNotExists"));
		}
		return s;
	}
}
