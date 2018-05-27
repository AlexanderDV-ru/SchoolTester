package ru.alexanderdv.schooltester.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TCPClient
{
	Socket socket;
	ArrayList<EventHandler<ActionEvent>> recieveListeners;

	public TCPClient(InetAddress address, int port, EventHandler<ActionEvent> _recieveHandler) throws Exception
	{
		recieveListeners = new ArrayList<EventHandler<ActionEvent>>();
		addRecieveListener(_recieveHandler);
		socket = new Socket(address, port);
		start();
	}

	private void start()
	{
		new Thread(() ->
		{
			while (!socket.isClosed())
			{
				try
				{
					Object obj = new ObjectInputStream(socket.getInputStream()).readObject();
					for (EventHandler<ActionEvent> recieveListener : recieveListeners)
						recieveListener.handle(new ActionEvent(obj, null));
				}
				catch (IOException e)
				{
					e.printStackTrace();
					System.exit(-1);
					return;
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void addRecieveListener(EventHandler<ActionEvent> listener)
	{
		recieveListeners.add(listener);
	}

	public void send(Object obj) throws SocketException
	{
		if (obj == null)
			return;
		try
		{
			new ObjectOutputStream(socket.getOutputStream()).writeObject(obj);
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

	@SuppressWarnings("unchecked")
	public <T> T waitToRecieve(Class<T> cl)
	{
		Object[] recieved = new Object[1];
		EventHandler<ActionEvent> listener = e -> recieved[0] = e.getSource();
		addRecieveListener(listener);
		for (; recieved[0] == null ? true : !recieved[0].getClass().equals(cl);)
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
		recieveListeners.remove(listener);
		return (T) recieved[0];
	}

	public synchronized void close() throws IOException
	{
		send("/break");
		socket.close();
	}
}
