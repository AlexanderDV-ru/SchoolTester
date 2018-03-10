package ru.alexanderdv.schooltester.utilities;

import java.awt.Rectangle;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.net.URL;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.Main;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public abstract class ProtectedFXWindow extends FXWindow
{

	private final int level;

	public ProtectedFXWindow(String secondaryTitle, AnchorPane panel, int type, int level)
	{
		super(secondaryTitle, panel, type);
		this.level = level;
	}

	public ProtectedFXWindow(String secondaryTitle, URL url, int type, int level)
	{
		super(secondaryTitle, url, type);
		this.level = level;
	}

	@Override
	public Stage open(Rectangle parent)
	{
		throw new IllegalArgumentException("Protection is not exists!");
	}

	@Override
	public Stage open(Stage parent)
	{
		throw new IllegalArgumentException("Protection is not exists!");
	}

	public Stage open(Rectangle parent, Account account, DatagramSocket socket) throws Exception
	{
		if (account == null && level > 0)
			throw new Exception("Protection is weak!");
		if (level > 1)
		{
			try
			{
				NetworkUtils.sendData(socket, new AccountPacket("checkAccount", Main.macAddress, "", null, account), Main.server, 21577, 14);
				socket.setSoTimeout(500);
				NetworkPacket packet = NetworkUtils.recieveData(socket, NetworkPacket.class, 13);
				if (packet instanceof AccountPacket)
					if (((AccountPacket) packet).getRequest().equals("accountConfirmed") && ((AccountPacket) packet).getNewAccount().getLogin().equals(account
							.getLogin()) && ((AccountPacket) packet).getNewAccount().getPasswordHash() == account.getPasswordHash())
					{
					}
					else throw new Exception("Protection is weak!");
			}
			catch (SocketTimeoutException e)
			{
				throw new Exception("Protection is weak!");
			}
		}
		return super.open(parent);
	}

	public Stage open(Stage parent, Account account, DatagramSocket socket) throws Exception
	{
		if (account == null && level > 0)
			throw new Exception("Protection is weak!");
		if (level > 1)
		{
			try
			{
				NetworkUtils.sendData(socket, new AccountPacket("checkAccount", Main.macAddress, "", null, account), Main.server, 21577, 14);
				socket.setSoTimeout(500);
				NetworkPacket packet = NetworkUtils.recieveData(socket, NetworkPacket.class, 13);
				if (packet instanceof AccountPacket)
					if (((AccountPacket) packet).getRequest().equals("accountConfirmed") && ((AccountPacket) packet).getNewAccount().getLogin().equals(account
							.getLogin()) && ((AccountPacket) packet).getNewAccount().getPasswordHash() == account.getPasswordHash())
					{
					}
					else throw new Exception("Protection is weak!");
			}
			catch (SocketTimeoutException e)
			{
				throw new Exception("Protection is weak!");
			}
		}
		return super.open(parent);
	}

}
