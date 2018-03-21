package ru.alexanderdv.schooltester.utilities;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class InfoPacket extends NetworkPacket
{
	private static final long serialVersionUID = 1188081698428885008L;
	private final String info;
	private final String info2;
	private final boolean end;

	public InfoPacket(String request, String mac, String ip, String info, String info2, boolean end)
	{
		super(request, mac, ip);
		this.info = info;
		this.info2 = info2;
		this.end = end;
	}

	public InfoPacket(String request, String info, String info2, boolean end)
	{
		super(request, null, null);
		this.info = info;
		this.info2 = info2;
		this.end = end;
	}

	public String getInfo()
	{
		return info;
	}

	public String getInfo2()
	{
		return info2;
	}

	public boolean isEnd()
	{
		return end;
	}

}
