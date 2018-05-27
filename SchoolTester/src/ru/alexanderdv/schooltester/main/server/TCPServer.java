package ru.alexanderdv.schooltester.main.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TCPServer
{
	static class ByteFile implements Serializable
	{
		private static final long serialVersionUID = -6029020224509299191L;
		byte[] bytes;

		public ByteFile(File file)
		{
			bytes = SystemUtils.readFile(file);
		}
	}

	static class Pack implements Serializable
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2974739417846695549L;
		InetSocketAddress address;
		Object obj;

		public Pack(InetSocketAddress address, Object obj)
		{
			this.address = address;
			this.obj = obj;
		}

		public Pack(InetAddress address, int port, Object obj)
		{
			this(new InetSocketAddress(address, port), obj);
		}

	}

	ArrayList<EventHandler<ActionEvent>> recieveListeners;
	ArrayList<EventHandler<ActionEvent>> disconnectionListeners;

	public void addDisconnectionListener(EventHandler<ActionEvent> listener)
	{
		disconnectionListeners.add(listener);
	}
	public void addRecieveListener(EventHandler<ActionEvent> listener)
	{
		recieveListeners.add(listener);
	}

	ServerSocket server;

	public static class Recieve
	{
		private final InetSocketAddress address;
		private final Object object;

		public Recieve(InetSocketAddress address, Object object)
		{
			super();
			this.address = address;
			this.object = object;
		}

		/**
		 * @return the address
		 */
		public InetSocketAddress getAddress()
		{
			return address;
		}

		/**
		 * @return the object
		 */
		public Object getObject()
		{
			return object;
		}
	}

	HashMap<InetSocketAddress, Socket> sockets;

	public TCPServer(int port)
	{
		recieveListeners = new ArrayList<EventHandler<ActionEvent>>();
		disconnectionListeners = new ArrayList<EventHandler<ActionEvent>>();
		new Thread(() ->
		{
			try
			{
				server = new ServerSocket(port);
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try
			{
				sockets = new HashMap<InetSocketAddress, Socket>();
				for (Socket sockk = null; (sockk = server.accept()) != null;)
				{
					Socket sock = sockk;
					try
					{
						InetSocketAddress socketAddress = new InetSocketAddress(sock.getInetAddress(), sock.getPort());
						new Thread(() ->
						{
							sockets.put(socketAddress, sock);
							for (Object obj = null;;)
								try
								{
									if ("/break".equals((obj = new ObjectInputStream(sock.getInputStream()).readObject())))
										break;
									for (EventHandler<ActionEvent> recieveListener : recieveListeners)
										recieveListener.handle(new ActionEvent(new Recieve(socketAddress, obj), null));
								}
								catch (IOException e)
								{
									e.printStackTrace();
									for (EventHandler<ActionEvent> disconnectionListener : disconnectionListeners)
										disconnectionListener.handle(new ActionEvent(socketAddress, null));
									sockets.remove(socketAddress);
									break;
								}
								catch (ClassNotFoundException e)
								{
									e.printStackTrace();
								}
							try
							{
								sock.close();
							}
							catch (IOException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}).start();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					try
					{
						// sock.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				server.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}).start();
	}

	class ObjectSocket
	{
		Socket socket;
		// ObjectOutputStream oos;
		// ObjectInputStream ois;

		public ObjectSocket(Socket socket) throws IOException, NullPointerException
		{
			if (socket == null)
				throw new NullPointerException("Socket must be not null!");
			this.socket = socket;
			// this.oos = new ObjectOutputStream(socket.getOutputStream());
			// this.ois = new ObjectInputStream(socket.getInputStream());
		}
	}

	public void send(InetSocketAddress address, Object obj) throws SocketException
	{
		if (obj == null || address == null)
			return;
		try
		{
			if (sockets.containsKey(address))
				new ObjectOutputStream(sockets.get(address).getOutputStream()).writeObject(obj);
		}
		catch (SocketException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
