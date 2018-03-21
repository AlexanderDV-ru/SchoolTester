package ru.alexanderdv.schooltester.utilities;

import java.io.Serializable;

/**
 * NetworkPacket - the class for communicating with the server
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.8.0a
 */
public class NetworkPacket implements Serializable
{
	private static final long serialVersionUID = 7833614208511060237L;
	private String request;
	private String mac;
	private String ip;

	public NetworkPacket(String request, String mac, String ip)
	{
		super();
		this.request = request;
		this.mac = mac;
		this.ip = ip;
	}

	/**
	 * Returns the request to the server or to the client
	 * 
	 * @return the request to the server or to the client
	 */
	public String getRequest()
	{
		return request;
	}

	/**
	 * @return the mac
	 */
	public String getMac()
	{
		return mac;
	}

	/**
	 * @return the ip
	 */
	public String getIp()
	{
		return ip;
	}
}
