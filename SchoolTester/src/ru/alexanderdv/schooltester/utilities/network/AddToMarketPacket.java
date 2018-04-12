package ru.alexanderdv.schooltester.utilities.network;

import ru.alexanderdv.schooltester.utilities.types.Test;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class AddToMarketPacket extends NetworkPacket
{
	private static final long serialVersionUID = -3949901956019257816L;
	private final Test test;

	public AddToMarketPacket(String request, String mac, String ip, Test test)
	{
		super(request, mac, ip);
		this.test = test;
	}

	/**
	 * @return the test
	 */
	public Test getTest()
	{
		return test;
	}

}
