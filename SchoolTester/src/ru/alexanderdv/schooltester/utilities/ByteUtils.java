package ru.alexanderdv.schooltester.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class ByteUtils
{
	/**
	 * Returns 'byte' array translated from 'Object' - obj
	 * 
	 * @param obj
	 *            - 'Object' to translate to byte array
	 * @return 'byte' array translated from 'Object' - obj
	 */
	public static byte[] objectToByteArray(Object obj)
	{
		byte[] byteArray = new byte[0];
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byteArray = baos.toByteArray();
			oos.close();
			baos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return byteArray;
	}

	/**
	 * Returns 'Object' translated from 'byte' array - byteArray
	 * 
	 * @param byteArray
	 *            - 'byte' array to translate to 'Object'
	 * @return 'Object' translated from 'byte' array - byteArray
	 */
	public static Object byteArrayToObject(byte[] byteArray)
	{
		Object obj = null;
		try
		{
			ByteArrayInputStream baos = new ByteArrayInputStream(byteArray);
			ObjectInputStream oos = new ObjectInputStream(baos);
			obj = oos.readObject();
			oos.close();
			baos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return obj;
	}
}
