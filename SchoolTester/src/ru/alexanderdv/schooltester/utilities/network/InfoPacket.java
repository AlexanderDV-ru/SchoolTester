package ru.alexanderdv.schooltester.utilities.network;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class InfoPacket extends NetworkPacket
{
	private static final long serialVersionUID = 1188081698428885008L;
	private final String info;
	private final String info2;
	private final boolean end;

	public InfoPacket(String request, String info, String info2, boolean end)
	{
		super(request);
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
