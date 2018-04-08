package ru.alexanderdv.schooltester.utilities.network;

import java.io.Serializable;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public class BytePacket implements Serializable
{
	private static final long serialVersionUID = 964005607974413397L;
	private final byte[] bytes;
	private final int offset;
	private final int fullLength;

	public BytePacket(byte[] bytes, int offset, int fullLength)
	{
		super();
		this.bytes = bytes;
		this.offset = offset;
		this.fullLength = fullLength;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes()
	{
		return bytes;
	}

	/**
	 * @return the offset
	 */
	public int getOffset()
	{
		return offset;
	}

	/**
	 * @return the fullLength
	 */
	public int getFullLength()
	{
		return fullLength;
	}

}
