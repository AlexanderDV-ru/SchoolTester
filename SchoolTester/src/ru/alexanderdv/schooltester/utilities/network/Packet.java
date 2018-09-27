package ru.alexanderdv.schooltester.utilities.network;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class Packet extends NetworkPacket
{
	private static final long serialVersionUID = -2411012860338304596L;
	private String data;
	private String fileTree;
	private String version;
	private String url;
	private String key;

	/**
	 * Constructor of the "Packet" class
	 * 
	 * @param request
	 *            - the request to the server or to the client
	 * @param version
	 *            - the version of the server or of the client
	 * @param url
	 *            - the URL to downloading current version of program
	 * @param key
	 *            - the key specified by user
	 */
	public Packet(String request, String data, String fileTree, String version, String url, String key)
	{
		super(request);
		this.data = data;
		this.fileTree = fileTree;
		this.version = version;
		this.url = url;
		this.key = key;
	}

	/**
	 * Returns the version of the server or of the client
	 * 
	 * @return the version of the server or of the client
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Returns the version of the server or of the client
	 * 
	 * @return the version of the server or of the client
	 */
	public String getFileTree()
	{
		return fileTree;
	}

	/**
	 * Returns the version of the server or of the client
	 * 
	 * @return the version of the server or of the client
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * Returns the URL to downloading current version of program
	 * 
	 * @return the URL to downloading current version of program
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Returns the key specified by user
	 * 
	 * @return the key specified by user
	 */
	public String getKey()
	{
		return key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Packet [data=" + data + ", fileTree=" + fileTree + ", version=" + version + ", url=" + url + ", key=" + key + "]";
	}
}
