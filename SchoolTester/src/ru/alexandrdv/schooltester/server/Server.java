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
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Server
 * 
 * @author AlexandrDV
 * @version 2.0.0a
 *
 */
public class Server
{
	static ArrayList<Long> passes = new ArrayList<Long>();
	static
	{
		passes.add(123654l);
	}

	public static void main(String[] args)
	{
		Long iii=(long)(new Random().nextInt(1000)+1);
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
			for(String line;(line = pw.readLine()) != null;)
				passes.add(Long.parseLong(line));
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
			boolean enabled = true;
			while (enabled)
			{
				String s = "";

				byte[] data2 = new byte[256];
				DatagramPacket pac = new DatagramPacket(data2, data2.length);
				socket.receive(pac);
				try
				{
					s = ((Pack) readByteArray(data2)).str;
					if (((Pack) readByteArray(data2)).str2.equals("1"))
					{
						byte[] data = writeToByteArray(new Pack("", "2", ((Pack) readByteArray(data2)).key*iii));
						socket.send(new DatagramPacket(data, 0, data.length, pac.getAddress(), pac.getPort()));// отправление пакета
						continue;
					}
				}
				catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (s.equals("checkUpdates"))
				{

					byte[] data;
					try
					{
						data = writeToByteArray(new Pack(version, link, (passes.contains(((Pack) readByteArray(data2)).key/iii) ? -57l : -58l)));
						System.out.println(((Pack) readByteArray(data2)).key/iii);
						socket.send(new DatagramPacket(data, 0, data.length, pac.getAddress(), pac.getPort()));// отправление пакета
					}
					catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		public Long key;

		/* public int strLength; */
		public Pack(String s, String s2, Long key/* ,int l */)
		{
			str = s;
			str2 = s2;
			this.key = key;
			/* strLength=l; */
		}
	}
}
