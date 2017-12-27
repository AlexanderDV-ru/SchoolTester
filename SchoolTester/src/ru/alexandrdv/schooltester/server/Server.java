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
import java.util.Calendar;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import ru.alexandrdv.schooltester.main.FXFrame;
import ru.alexandrdv.schooltester.util.Logger;

/**
 * Server
 * 
 * @author AlexandrDV
 * @version 4.2.1a
 *
 */
public class Server
{
	static Logger logger = new Logger("server");
	static ArrayList<String> passes = new ArrayList<String>();
	static
	{
		passes.add("1234");
	}

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
			for (String line; (line = pw.readLine()) != null;)
				passes.add(line);
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

				byte[] data2 = new byte[1024];
				DatagramPacket pac = new DatagramPacket(data2, data2.length);
				socket.receive(pac);
				try
				{
					s = ((Pack) readByteArray(data2)).str;
					logger.log(Level.INFO, s);
					FXFrame.writeFile(new File("ClientsInfo/" + Calendar.getInstance().getTimeInMillis() + ".log"), "network.host.ip" + '|' + pac.getAddress()
							.getHostAddress() + '\n' + s + '\n' + "key" + '|' + ((Pack) readByteArray(data2)).key);
					byte[] data3;
					data3 = writeToByteArray(new Pack(version,link, (passes.contains(((Pack) readByteArray(data2)).key) ? "y" : "n")));
					socket.send(new DatagramPacket(data3, 0, data3.length, pac.getAddress(), pac.getPort()));// отправление пакета
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}

			}
			socket.close();
			Logger.exit(0);
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
