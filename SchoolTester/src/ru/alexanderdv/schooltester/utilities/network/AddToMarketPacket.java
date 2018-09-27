package ru.alexanderdv.schooltester.utilities.network;

import ru.alexanderdv.schooltester.utilities.types.Test;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class AddToMarketPacket extends NetworkPacket
{
	private static final long serialVersionUID = -3949901956019257816L;
	private final Test test;

	public AddToMarketPacket(String request, Test test)
	{
		super(request);
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
