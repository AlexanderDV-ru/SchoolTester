package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Rectangle;
import java.net.SocketTimeoutException;
import java.net.URL;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.TCPClient;
import ru.alexanderdv.schooltester.utilities.network.AccountPacket;
import ru.alexanderdv.schooltester.utilities.types.Account;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class ProtectedFXWindow extends FXWindow
{

	private final int level;

	public ProtectedFXWindow(String secondaryTitle, Pane panel, int type, int level, boolean inDevelope, boolean resizable)
	{
		super(secondaryTitle, panel, type, inDevelope, resizable);
		this.level = level;
	}

	public ProtectedFXWindow(String secondaryTitle, URL url, int type, int level, boolean inDevelope, boolean resizable)
	{
		super(secondaryTitle, url, type, inDevelope, resizable);
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

	public Stage open(Rectangle parent, Account account, TCPClient client) throws Exception
	{
		System.out.println("mark4");
		if (account == null && level > 0)
			throw new Exception(msgSys.getMsg("protectionIsWeak"));
		System.out.println("mark5");
		if (level > 1)
		{
			try
			{
				System.out.println("mark6");
				client.send(new AccountPacket("checkAccount", null, account));
				System.out.println("mark7");
				AccountPacket packet = client.waitToRecieve(AccountPacket.class);
				System.out.println("mark8");
				if (packet.getRequest().equals("accountConfirmed") && packet.getNewAccount().getLogin().equals(account.getLogin()))
				{
				}
				else throw new Exception(msgSys.getMsg("protectionIsWeak"));
				System.out.println("mark9");
			}
			catch (SocketTimeoutException e)
			{
				throw new Exception(msgSys.getMsg("protectionIsWeak"));
			}
		}
		return super.open(parent);
	}

	public Stage open(Stage parent, Account account, TCPClient client) throws Exception
	{
		return this.open(new Rectangle((int) parent.getX(), (int) parent.getY(), (int) parent.getWidth(), (int) parent.getHeight()), account, client);
	}

}
