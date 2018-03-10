package ru.alexandrdv.schooltester.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Deprecated
public class NetUtils
{

	/**
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] writeToByteArray(Object object) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(baos);
		out.writeObject(object);
		byte[] bytes = baos.toByteArray();
		out.close();
		baos.close();
		return bytes;
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readByteArray(byte[] bytes) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream in = new ObjectInputStream(bais);
		Object packet = in.readObject();
		in.close();
		bais.close();
		return packet;
	}

	public static class Pack implements Serializable
	{
		private static final long serialVersionUID = 8084221224402314395L;
		public String str;
		public String str2;
		public String key;

		public Pack(String s, String s2, String key)
		{
			str = s;
			str2 = s2;
			this.key = key;
		}
	}
}
