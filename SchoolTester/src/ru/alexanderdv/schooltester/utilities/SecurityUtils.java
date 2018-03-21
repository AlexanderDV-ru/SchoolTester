package ru.alexanderdv.schooltester.utilities;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class SecurityUtils
{
	public static byte[] crypt(byte[] bytes, int cip)
	{
		for (int i = 0; i < bytes.length; i++)
			bytes[i] = (byte) ((bytes[i] + cip * (i % 3 + 1) + i * (cip / Math.abs(cip)) + 256) % 256);
		return bytes;
	}
}
