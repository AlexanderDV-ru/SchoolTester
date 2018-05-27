package ru.alexanderdv.schooltester.utilities.network;

import java.math.BigInteger;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class VerifyPacket extends NetworkPacket
{
	private static final long serialVersionUID = 3781561479172063290L;
	private final BigInteger code;

	public VerifyPacket(String request, BigInteger code)
	{
		super(request);
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public BigInteger getCode()
	{
		return code;
	}

}
