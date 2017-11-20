package ru.alexandrdv.schooltester.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

public class Server
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket socket = new DatagramSocket(21577);
			while (true)
			{
				String s = "";

				byte[] data2 = new byte[256];
				DatagramPacket pac = new DatagramPacket(data2, data2.length);
				socket.receive(pac);
				try
				{
					s = ((Pack)readByteArray(data2)).str;
				}
				catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (s.equals("checkUpdates"))
				{
					byte[] data = writeToByteArray(new Pack("1.4.0a","https://yadi.sk/d/UEzlp33f3PqKYs"));
					socket.send(new DatagramPacket(data, 0, data.length, pac.getAddress(), pac.getPort()));// отправление пакета
				}
			}
		}
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
		public String str;
		public String str2;
		/*public int strLength;*/
		public Pack(String s,String s2/*,int l*/)
		{
			str=s;
			str2=s2;
			/*strLength=l;*/
		}
	}
}
