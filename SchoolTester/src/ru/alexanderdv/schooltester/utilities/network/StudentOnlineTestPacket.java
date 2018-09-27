package ru.alexanderdv.schooltester.utilities.network;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.6a
 */
public class StudentOnlineTestPacket extends NetworkPacket
{
	private final String login;

	public StudentOnlineTestPacket(String request, String login)
	{
		super(request);
		this.login = login;
	}

	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}

}
