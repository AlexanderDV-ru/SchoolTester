package ru.alexanderdv.schooltester.utilities.network;

import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.types.Account;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class AccountPacket extends NetworkPacket
{
	private static final long serialVersionUID = -3635501077840946902L;
	private Account oldAccount;
	private Account newAccount;

	public AccountPacket(String request, Account oldAccount, Account newAccount)
	{
		super(request);
		this.oldAccount = oldAccount;
		this.newAccount = newAccount;
		if (oldAccount != null && newAccount != null)
			if (oldAccount.getAccountType() != newAccount.getAccountType() || !oldAccount.getLogin().equals(newAccount.getLogin()))
				throw new ExceptionInInitializerError(Main.msgSys.getMsg("accountsInfoNotMatch"));
	}

	/**
	 * @return the oldAccount
	 */
	public Account getOldAccount()
	{
		return oldAccount;
	}

	/**
	 * @return the newAccount
	 */
	public Account getNewAccount()
	{
		return newAccount;
	}
}
