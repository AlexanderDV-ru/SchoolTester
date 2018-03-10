package ru.alexanderdv.schooltester.utilities;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class NetworkUtils
{

	public static <P> void sendData(DatagramSocket socket, P packet, InetAddress ia, int port, int cipcode) throws Exception
	{
		byte[] request = SecurityUtils.crypt(ByteUtils.objectToByteArray(packet), cipcode);
		socket.send(new DatagramPacket(request, 0, request.length, ia, port));
	}

	@SuppressWarnings("unchecked")
	public static <P> P recieveData(DatagramSocket socket, DatagramPacket outPac, Class<P> _class, int cipcode) throws Exception
	{
		DatagramPacket pac;
		byte[] response;
		do
		{
			response = new byte[9192];
			pac = new DatagramPacket(response, 0, response.length);
			socket.receive(pac);
			response = SecurityUtils.crypt(response, -cipcode);
		} while (ByteUtils.byteArrayToObject(response).getClass() != _class && ByteUtils.byteArrayToObject(response).getClass().getSuperclass() != _class);
		if (outPac != null)
		{
			outPac.setSocketAddress(pac.getSocketAddress());
			outPac.setPort(pac.getPort());
		}
		return (P) ByteUtils.byteArrayToObject(response);
	}

	public static <P> P recieveData(DatagramSocket socket, Class<P> _class, int cipcode) throws Exception
	{
		return recieveData(socket, null, _class, cipcode);
	}
}
