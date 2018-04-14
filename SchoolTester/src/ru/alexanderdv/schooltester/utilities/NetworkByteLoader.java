package ru.alexanderdv.schooltester.utilities;

import java.util.HashMap;
import java.util.UUID;
/**
 * 
 * @author AlexanderDV/AlexandrDV
 *@version 5.9.8a
 */
public class NetworkByteLoader
{
	static class BytePack
	{
		UUID uuid;
		byte[] bytes;
		int offset;
		int fullLength;
	}

	static HashMap<UUID, byte[]> bytesInfos = new HashMap<UUID, byte[]>();

	public static void continueLoading(BytePack info)
	{
		if(!bytesInfos.containsKey(info.uuid))
			bytesInfos.put(info.uuid, new byte[info.fullLength]);
		for(int i=0;i<Math.min(info.offset+info.bytes.length,bytesInfos.get(info.uuid).length);i++)
			bytesInfos.get(info.uuid)[info.offset+i]=info.bytes[i];
	}
	public static byte[] endLoading(UUID uuid)
	{
		return bytesInfos.remove(uuid);
	}

}
