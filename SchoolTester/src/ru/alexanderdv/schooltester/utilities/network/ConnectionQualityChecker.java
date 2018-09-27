package ru.alexanderdv.schooltester.utilities.network;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ConnectionQualityChecker extends NetworkPacket
{
	private static final long serialVersionUID = 2690334887038126568L;
	private long time;

	public ConnectionQualityChecker(String request, long time)
	{
		super(request);
		this.time = time;
	}

	public long getTime()
	{
		return time;
	}

}
