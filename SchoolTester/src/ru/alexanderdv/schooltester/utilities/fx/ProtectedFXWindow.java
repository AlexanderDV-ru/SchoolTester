package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Rectangle;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.net.URL;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.NetworkUtils;
import ru.alexanderdv.schooltester.utilities.network.AccountPacket;
import ru.alexanderdv.schooltester.utilities.network.NetworkPacket;
import ru.alexanderdv.schooltester.utilities.types.Account;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
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
		throw new IllegalArgumentException(msgSys.getMsg("protectionIsNotExist"));
	}

	@Override
	public Stage open(Stage parent)
	{
		throw new IllegalArgumentException(msgSys.getMsg("protectionIsNotExist"));
	}

	public Stage open(Rectangle parent, Account account, DatagramSocket socket) throws Exception
	{
		if (account == null && level > 0)
			throw new Exception(msgSys.getMsg("protectionIsWeak"));
		if (level > 1)
		{
			try
			{
				NetworkUtils.sendData(socket, new AccountPacket("checkAccount", Main.macAddress, "", null, account), Main.server, 21577, 14);
				socket.setSoTimeout(500);
				NetworkPacket packet = NetworkUtils.recieveData(socket, NetworkPacket.class, 13);
				if (packet instanceof AccountPacket)
					if (((AccountPacket) packet).getRequest().equals("accountConfirmed") && ((AccountPacket) packet).getNewAccount().getLogin().equals(account
							.getLogin()) && ((AccountPacket) packet).getNewAccount().getPassword().equals(account.getPassword()))
					{
					}
					else throw new Exception(msgSys.getMsg("protectionIsWeak"));
			}
			catch (SocketTimeoutException e)
			{
				throw new Exception(msgSys.getMsg("protectionIsWeak"));
			}
		}
		return super.open(parent);
	}

	public Stage open(Stage parent, Account account, DatagramSocket socket) throws Exception
	{
		if (account == null && level > 0)
			throw new Exception(msgSys.getMsg("protectionIsWeak"));
		if (level > 1)
		{
			try
			{
				NetworkUtils.sendData(socket, new AccountPacket("checkAccount", Main.macAddress, "", null, account), Main.server, 21577, 14);
				socket.setSoTimeout(1000);
				NetworkPacket packet = NetworkUtils.recieveData(socket, NetworkPacket.class, 13);
				if (packet instanceof AccountPacket)
					if (((AccountPacket) packet).getRequest().equals("accountConfirmed") && ((AccountPacket) packet).getNewAccount().getLogin().equals(account
							.getLogin()) && ((AccountPacket) packet).getNewAccount().getPassword().equals(account.getPassword()))
					{
					}
					else throw new Exception(msgSys.getMsg("protectionIsWeak"));
			}
			catch (SocketTimeoutException e)
			{
				throw new Exception(msgSys.getMsg("protectionIsWeak"));
			}
		}
		return super.open(parent);
	}

}
