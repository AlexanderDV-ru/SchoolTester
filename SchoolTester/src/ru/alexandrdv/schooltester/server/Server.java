package ru.alexandrdv.schooltester.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JOptionPane;


/**
 * Server v1.7.0a
 * 
 * @author AlexandrDV
 *
 */
public class Server
{
	public static void main(String[] args)
	{
		String version;
		String link;
		File file = new File("SchoolTesterServerConfig.cfg");
		try
		{
			if (!file.exists())
				file.createNewFile();
			BufferedReader pw = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1251"));
			if ((version = pw.readLine()) == null)
			{
				JOptionPane.showMessageDialog(null, "Config is invalid!");
				pw.close();
				System.exit(1);
				return;
			}
			if ((link = pw.readLine()) == null)
			{
				JOptionPane.showMessageDialog(null, "Config is invalid!");
				pw.close();
				System.exit(1);
				return;
			}
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}

		try
		{
			DatagramSocket socket = new DatagramSocket(21577);
			boolean enabled=true;
			while (enabled)
			{
				String s = "";

				byte[] data2 = new byte[256];
				DatagramPacket pac = new DatagramPacket(data2, data2.length);
				socket.receive(pac);
				try
				{
					s = ((Pack) readByteArray(data2)).str;
				}
				catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (s.equals("checkUpdates"))
				{
					byte[] data = writeToByteArray(new Pack(version, link));
					socket.send(new DatagramPacket(data, 0, data.length, pac.getAddress(), pac.getPort()));// отправление пакета
				}
			}
			socket.close();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
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
		private static final long serialVersionUID = 8084221224402314394L;
		public String str;
		public String str2;

		/* public int strLength; */
		public Pack(String s, String s2/* ,int l */)
		{
			str = s;
			str2 = s2;
			/* strLength=l; */
		}
	}
}
