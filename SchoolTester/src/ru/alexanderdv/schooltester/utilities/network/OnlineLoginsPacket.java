package ru.alexanderdv.schooltester.utilities.network;

import java.util.ArrayList;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public class OnlineLoginsPacket extends NetworkPacket
{
	private static final long serialVersionUID = 1278915369036005356L;
	private final ArrayList<String> online;

	/**
	 * @param request
	 * @param online
	 */
	public OnlineLoginsPacket(String request, ArrayList<String> online)
	{
		super(request);
		this.online = online;
	}

	/**
	 * @return the online
	 */
	public ArrayList<String> getOnline()
	{
		return online;
	}

}
