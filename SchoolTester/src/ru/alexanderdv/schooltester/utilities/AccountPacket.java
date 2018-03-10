package ru.alexanderdv.schooltester.utilities;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class AccountPacket extends NetworkPacket
{
	private static final long serialVersionUID = -3635501077840946902L;
	private Account oldAccount;
	private Account newAccount;

	public AccountPacket(String request, String mac, String ip, Account oldAccount, Account newAccount)
	{
		super(request, mac, ip);
		this.oldAccount = oldAccount;
		this.newAccount = newAccount;
		if (oldAccount != null && newAccount != null)
			if (oldAccount.getAccountType() != newAccount.getAccountType() || !oldAccount.getLogin().equals(newAccount.getLogin()))
				throw new ExceptionInInitializerError("Basic accounts' information don't match: types or logins aren't match!");
	}

	public AccountPacket(String request, Account oldAccount, Account newAccount) throws Exception
	{
		this(request, null, null, oldAccount, newAccount);
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
