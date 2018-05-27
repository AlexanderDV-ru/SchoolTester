package ru.alexanderdv.schooltester.utilities.network;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class FilePacket extends NetworkPacket
{
	private static final long serialVersionUID = -8081466345562995103L;
	private final byte[] bytes;

	public FilePacket(String request,  byte[] bytes)
	{
		super(request);
		this.bytes = bytes;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes()
	{
		return bytes;
	}

}
