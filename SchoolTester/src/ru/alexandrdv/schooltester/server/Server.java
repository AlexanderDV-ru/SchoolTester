package ru.alexandrdv.schooltester.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import ru.alexandrdv.schooltester.main.FXFrame;
import ru.alexandrdv.schooltester.util.Logger;
import ru.alexandrdv.schooltester.util.NetUtils;

/**
 * Server
 * 
 * @author AlexandrDV
 * @version 4.5.1a
 *
 */
@Deprecated
public class Server
{
	static Logger logger = new Logger("server");
	static ArrayList<String> passes = new ArrayList<String>();
	static
	{
		passes.add("1234");
	}
	static ArrayList<String> ipblacklist = new ArrayList<String>();
	static
	{
		// ipblacklist.add("94.181.44.135");
	}
	static ArrayList<String> macblacklist = new ArrayList<String>();
	static
	{
		// macblacklist.add("00-23-54-3D-9B-6C");
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
					s = ((NetUtils.Pack) NetUtils.readByteArray(data2)).str;
					logger.log(Level.INFO, s);
					FXFrame.writeFile(new File("ClientsInfo/" + Calendar.getInstance().getTimeInMillis() + ".log"), "network.host.ip" + '|' + pac.getAddress()
							.getHostAddress() + '\n' + s + '\n' + "key" + '|' + ((NetUtils.Pack) NetUtils.readByteArray(data2)).key);
					byte[] data3;
					String a = "";
					String mac = s.substring(s.indexOf("network.mac|") + "network.mac|".length()).substring(0, s.substring(s.indexOf("network.mac" + '|'))
							.indexOf("\n"));
					if ((!ipblacklist.contains(pac.getAddress().getHostAddress()) && !macblacklist.contains(mac) && passes.contains(((NetUtils.Pack) NetUtils.readByteArray(
							data2)).key)))
						a = "y";
					else a = "n";
					String s2;
					if(ipblacklist.contains(pac.getAddress().getHostAddress()) ||macblacklist.contains(mac))
						s2="blacklist";
					else s2=link;
					data3 = NetUtils.writeToByteArray(new NetUtils.Pack(version, s2, a));
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
}
