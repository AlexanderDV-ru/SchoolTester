package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Rectangle;
import java.net.SocketTimeoutException;
import java.net.URL;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.utilities.network.AccountPacket;
import ru.alexanderdv.schooltester.utilities.network.TCPClient;
import ru.alexanderdv.schooltester.utilities.types.Account;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class ProtectedFXWindow extends FXWindow
{

	private final int level;

	public ProtectedFXWindow(String secondaryTitle, Pane panel, int type, int level, boolean inDevelope,
			boolean resizable)
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
		if (account == null && level > 0)
			throw new Exception(msgSys.getMsg("protectionIsWeak"));
		if (level > 1)
		{
			try
			{
				client.send(new AccountPacket("checkAccount", null, account));
				AccountPacket packet = client.waitToRecieve(AccountPacket.class);
				if (!packet.getRequest().equals("accountConfirmed")
						|| !packet.getNewAccount().getLogin().equals(account.getLogin()))
					throw new Exception(msgSys.getMsg("protectionIsWeak"));
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
		return this.open(parent!=null?new Rectangle((int) parent.getX(), (int) parent.getY(), (int) parent.getWidth(),
				(int) parent.getHeight()):null, account, client);
	}

}
