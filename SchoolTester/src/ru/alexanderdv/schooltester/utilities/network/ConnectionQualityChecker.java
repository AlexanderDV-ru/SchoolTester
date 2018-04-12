package ru.alexanderdv.schooltester.utilities.network;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class ConnectionQualityChecker extends NetworkPacket
{
	private static final long serialVersionUID = 2690334887038126568L;
	private long time;

	public ConnectionQualityChecker(String request, String mac, String ip, long time)
	{
		super(request, mac, ip);
		this.time = time;
	}

	public long getTime()
	{
		return time;
	}

}
