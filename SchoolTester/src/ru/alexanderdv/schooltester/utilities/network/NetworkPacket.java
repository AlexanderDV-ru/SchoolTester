package ru.alexanderdv.schooltester.utilities.network;

import java.io.Serializable;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public class NetworkPacket implements Serializable
{
	private static final long serialVersionUID = 7833614208511060237L;
	private String request;

	public NetworkPacket(String request)
	{
		this.request = request;
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
}
