package ru.alexanderdv.schooltester.utilities;

import java.util.Random;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SecureContainer<ValueType>
{
	private long[] cryptedValue;
	private byte plusCrypt;
	private byte multCrypt;

	public SecureContainer(ValueType value)
	{
		setValue(value);
	}

	public void setValue(ValueType value)
	{
		if (value == null)
			cryptedValue = null;
		changeCrypt(false);
		byte[] dcrv = ByteUtils.objectToByteArray(value);
		cryptedValue = new long[dcrv.length];
		for (int i = 0; i < dcrv.length; i++)
			cryptedValue[i] = ((long) dcrv[i] + (long) plusCrypt) * (long) multCrypt;
	}

	@SuppressWarnings("unchecked")
	public ValueType getValue()
	{
		if (cryptedValue == null)
			return null;
		byte[] dcrv = new byte[cryptedValue.length];
		for (int i = 0; i < cryptedValue.length; i++)
			dcrv[i] = (byte) (cryptedValue[i] / (long) multCrypt - (long) plusCrypt);
		try
		{
			Object value = ByteUtils.byteArrayToObject(dcrv);
			return (ValueType) value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			changeCrypt(true);
		}
	}

	private void changeCrypt(boolean recrypt)
	{
		byte oldM = multCrypt;
		byte oldP = plusCrypt;
		for (multCrypt = 0; multCrypt == 0; multCrypt = (byte) (new Random().nextInt(256) - 128));
		for (plusCrypt = 0; plusCrypt == 0; plusCrypt = (byte) (new Random().nextInt(256) - 128));
		if (recrypt)
			if (cryptedValue != null)
				for (int i = 0; i < cryptedValue.length; i++)
					cryptedValue[i] = ((cryptedValue[i] / (long) oldM - (long) oldP) + (long) plusCrypt) * (long) multCrypt;
	}
}
