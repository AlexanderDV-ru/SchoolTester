package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import ru.alexandrdv.schooltester.server.Server;
import ru.alexandrdv.schooltester.util.Question;

/**
 * Main v1.7.0a
 * 
 * @author AlexandrDV
 *
 */
public class Main
{
	public static final String version = "1.7.0a";
	public static final String autors = "AlexandrDV";
	public static final String programName = "SchoolTester v" + version + " by " + autors;
	public static StartBlank c;
	private boolean paused = false, canPause;
	private int var = -1;
	private ButtonX[] btns = new ButtonX[6];
	private ActionListener answersButtonsListener;
	private int toPauseTime = 0;
	private int maxResult;
	private int maxTime = 20 * 60;
	private float allTime = 0, timeOfWork = 0;
	private int result = 0;
	private int questionNumber = 0;
	private long lastTime = 0;
	private final Question[] objs;

	/**
	 * 
	 * @param uriStr
	 */
	private static void launchBrowser(String uriStr)
	{
		Desktop desktop;
		if (Desktop.isDesktopSupported())
		{
			desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE))
			{
				URI uri;
				try
				{
					uri = new URI(uriStr);
					desktop.browse(uri);
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
				catch (URISyntaxException use)
				{
					use.printStackTrace();
				}
			}
		}
	}

	/**
	 * The main method of class Main
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Check for updates
		{
			try
			{
				DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) + 10000);
				socket.setSoTimeout(1000);
				{
					byte[] data = Server.writeToByteArray(new Server.Pack("checkUpdates", null));
					socket.send(new DatagramPacket(data, 0, data.length, InetAddress.getByName("94.181.44.135"), 21577));// отправление пакета
				}
				{
					byte[] data2 = new byte[256];
					DatagramPacket pac = new DatagramPacket(data2, data2.length);
					socket.receive(pac);
					String s = ((Server.Pack) Server.readByteArray(data2)).str;
					String s2 = ((Server.Pack) Server.readByteArray(data2)).str2;
					if (!s.equals(version))
					{
						JOptionPane.showMessageDialog(null, "Please, update your program to version " + s);
						launchBrowser(s2);
					}
				}
				socket.close();
			}
			catch (UnknownHostException e)
			{
				e.printStackTrace();// неверный адрес получателя
			}
			catch (SocketException e)
			{
				e.printStackTrace();// возникли ошибки при передаче данных
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		String s = JOptionPane.showInputDialog(null, "Enter Password", programName, 2);
		for (int i = 0; i < 6; i++)
			if (s.toCharArray()[i] != new char[] { '1', '2', '3', '6', '5', '4' }[i])
				return;
		c = new StartBlank('0', args);
		c.getByChar().c.cc = s.toCharArray();
		// new Main(null, null, null, "", "", "", "", 0, true);
	}

	/**
	 * 
	 * @author AlexandrDV
	 *
	 */
	static class Char
	{
		public final Char c;
		char[] cc;

		public Char(char c)
		{
			this.c = this;
			cc = getByChar().cc = new char[] { c };
		}

		public Char getByChar()
		{
			return c.c.c.c.c.c.c.c.c.c.c.c.c.c.c.c;
		}
	}

	/**
	 * 
	 * @param parentLoc
	 * @param parentSize
	 * @param q
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 * @param maxTime
	 * @param canPause
	 */
	public Main(Point parentLoc, Dimension parentSize, Question[] q, String _class, String surname, String name, String secondName, int maxTime,
			boolean canPause)
	{
		this.canPause = canPause;
		this.maxTime = maxTime;
		objs = q;
		for (int j = 0, max = 0; j < objs.length; j++, maxResult += max, max = 0)
			for (int i = 0; i < objs[j].getAwards().length; i++)
				max = Math.max(max, objs[j].getAwards()[i]);
		JFrame window = new JFrame();
		window.setSize(451, 595);
		window.setLocation(parentLoc.x + parentSize.width / 2 - window.getWidth() / 2, parentLoc.y + parentSize.height / 2 - window.getHeight() / 2);
		window.setTitle(programName);
		window.getContentPane().setBackground(SystemColor.info);
		window.setDefaultCloseOperation(3);
		window.getContentPane().setLayout(null);
		window.setVisible(true);
		// TODO Add theme with errors in words
		Color bg = new Color(200, 70, 0);
		Color fr = new Color(200, 0, 0);
		ButtonX question = new ButtonX(bg, bg, bg, bg, bg, bg, fr, fr, fr, fr, fr, fr, "Выверите дату путешествия Магеллана", new boolean[] {
				true,
				false,
				false,
				true });
		question.setFont(new Font("Comic Sans Ms", 0, 16));
		question.setTextColor(Color.black);
		question.setBounds(10, 11, 414, 50);
		window.getContentPane().add(question);

		ButtonX btn0 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[0] = btn0;
		window.getContentPane().add(btn0);

		ButtonX btn1 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[1] = btn1;
		window.getContentPane().add(btn1);

		ButtonX btn2 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[2] = btn2;
		window.getContentPane().add(btn2);

		ButtonX btn3 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[3] = btn3;
		window.getContentPane().add(btn3);

		ButtonX btn4 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[4] = btn4;
		window.getContentPane().add(btn4);

		ButtonX btn5 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[5] = btn5;
		window.getContentPane().add(btn5);

		btn0.setFont(new Font("Comic Sans Ms", 0, 16));
		btn0.setTextColor(Color.black);
		btn0.setBounds(20, 72, 393, 60);

		btn1.setFont(new Font("Comic Sans Ms", 0, 16));
		btn1.setTextColor(Color.black);
		btn1.setBounds(20, 143, 393, 60);

		btn2.setFont(new Font("Comic Sans Ms", 0, 16));
		btn2.setTextColor(Color.black);
		btn2.setBounds(20, 214, 393, 60);

		btn3.setFont(new Font("Comic Sans Ms", 0, 16));
		btn3.setTextColor(Color.black);
		btn3.setBounds(20, 285, 393, 60);

		btn4.setFont(new Font("Comic Sans Ms", 0, 16));
		btn4.setTextColor(Color.black);
		btn4.setBounds(20, 360, 393, 60);

		btn5.setFont(new Font("Comic Sans Ms", 0, 16));
		btn5.setTextColor(Color.black);
		btn5.setBounds(20, 431, 393, 60);

		ArrayList<ButtonX> buttons = new ArrayList<ButtonX>();
		answersButtonsListener = e ->
		{
			var = buttons.indexOf((ButtonX) e.getSource());
			for (ButtonX button : buttons)
				button.setClicked(buttons.indexOf(button) == var);
		};
		for (ButtonX btn : btns)
		{
			buttons.add(btn);
			btn.addActionListener(answersButtonsListener);
		}
		ButtonX timer = new ButtonX(toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2), true, new boolean[] { false, false, false, false });
		ButtonX info = new ButtonX("", true, new boolean[] { false, false, false, false });
		info.setNormalColor(new Color(173, 255, 47));
		info.setBackground(Color.WHITE);
		timer.setNormalColor(new Color(173, 255, 47));
		ButtonX next = new ButtonX("Next", true, new boolean[] { false, false, false, false });
		next.setRounding(10);
		window.getContentPane().add(timer);
		window.getContentPane().add(info);

		info.setFont(new Font("Arial Bold", 1, 16));
		info.setTextColor(Color.black);
		info.setBounds(157, 507, 120, 42);
		info.setRounding(10);

		timer.setFont(new Font("Arial Bold", 1, 16));
		timer.setTextColor(Color.black);
		timer.setBounds(20, 507, 120, 42);
		timer.setRounding(10);
		timer.addActionListener(e ->
		{
			if (Main.this.canPause && toPauseTime <= 0)
			{
				question.setVisible(paused);
				next.setVisible(paused);
				for (int i = 0; i < objs[questionNumber].getAnswers().length; i++)
					btns[i].setVisible(paused);
				paused = !paused;
				toPauseTime = 300;
			}
			timer.setClicked(paused);

		});
		info.addActionListener(e -> info.setClicked(false));

		next.setNormalColor(new Color(204, 255, 51));
		window.getContentPane().add(next);
		{
			ButtonX btnNewButton = next;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(293, 507, 120, 42);

			btnNewButton.addActionListener(e ->
			{
				try
				{
					result += objs[questionNumber].getAwards()[var];
				}
				catch (Exception exception)
				{
					exception.printStackTrace();
				}
				questionNumber++;
				if (questionNumber == objs.length - 1)
				{
					btnNewButton.clearActionListeners();
					btnNewButton.setText("Finish");
					btnNewButton.addActionListener(ev -> finish(window, _class, surname, name, secondName));
				}
				if (questionNumber < objs.length)
					openQuestion(question, info, questionNumber);
			});
			openQuestion(question, info, questionNumber);
		}
		lastTime = Calendar.getInstance().getTimeInMillis();
		new Timer(1, e ->
		{
			/*if (finished)
			{
				if (c.cc[4] != '5')
					StartBlank.check(Long.MAX_VALUE);
				else return;
			}*/
			long time=Calendar.getInstance().getTimeInMillis();

			if (!paused)
				timeOfWork += ((float) (time - lastTime)) / 1000f;
			allTime += ((float) (time - lastTime)) / 1000f;
			toPauseTime--;
			timer.setText(toSize((maxTime - (int) timeOfWork) / 60, 2) + ":" + toSize((maxTime - (int) timeOfWork) % 60, 2));
			if (timeOfWork >= maxTime)
				finish(window, _class, surname, name, secondName);
			StartBlank.check(Calendar.getInstance().getTimeInMillis());
			lastTime = Calendar.getInstance().getTimeInMillis();
		}).start();
	}
	

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void finish(JFrame window, String _class, String surname, String name, String secondName)
	{
		if (var >=0)
			result += objs[questionNumber].getAward() + objs[questionNumber].getAwards()[var];
		showResult(window, _class, surname, name, secondName);
		window.setVisible(false);
		System.exit(0);
	}

	/**
	 * 
	 * @param window
	 * @param _class
	 * @param surname
	 * @param name
	 * @param secondName
	 */
	public void showResult(JFrame window, String _class, String surname, String name, String secondName)
	{
		String text = "";
		text += "Class: " + _class;
		text += "\nSurname: " + surname;
		text += "\nName: " + name;
		text += "\nSecond Name: " + secondName;
		text += "\nResult: " + result;
		text += "\nMax result: " + maxResult;
		text += "\nTime: " + toSize((int) timeOfWork / 60, 2) + ":" + toSize((int) timeOfWork % 60, 2);
		text += "\nMax time: " + toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2);
		text += "\nAll time: " + toSize((int) allTime / 60, 2) + ":" + toSize((int) allTime % 60, 2);
		Calendar c = Calendar.getInstance();
		File f = new File("Result From " + toSize(c.get(Calendar.DAY_OF_YEAR), 2) + "_" + toSize(c.get(Calendar.HOUR), 2) + "-" + toSize(c.get(Calendar.MINUTE),
				2) + "-" + toSize(c.get(Calendar.SECOND), 2) + ".txt");
		try
		{
			f.createNewFile();
			BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "Cp1251"));
			pw.write(text);
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		JOptionPane j = new JOptionPane(text);
		JDialog d = j.createDialog(null, programName);
		d.setLocationRelativeTo(window);
		d.setVisible(true);
	}

	/**
	 * 
	 * @param i
	 * @param size
	 * @return
	 */
	public String toSize(int i, int size)
	{
		String s = i + "";
		for (; s.length() < size;)
			s = "0" + s;
		return s;
	}

	/**
	 * 
	 * @param question
	 * @param info
	 * @param number
	 */
	public void openQuestion(ButtonX question, ButtonX info, int number)
	{
		btns[2].setVisible(objs[questionNumber].getAnswers().length > 2);
		btns[3].setVisible(objs[questionNumber].getAnswers().length > 4);
		btns[4].setVisible(objs[questionNumber].getAnswers().length > 5);
		btns[5].setVisible(objs[questionNumber].getAnswers().length > 6);
		for (int i = 0; i < objs[questionNumber].getAnswers().length; i++)
		{
			btns[i].setText(objs[questionNumber].getAnswers()[i]);
			btns[i].setFont(new Font(btns[i].getFont().getFontName(), btns[i].getFont().getStyle(), objs[questionNumber].getFontSizes()[i]));
			btns[i].setClicked(false);
		}
		var = -1;
		info.setText((questionNumber + 1) + "/" + objs.length);
		question.setText(objs[questionNumber].getQuestion());
	}

	/**
	 * 
	 * @author AlexandrDV
	 *
	 */
	static class ButtonX extends ru.alexandrdv.components.ButtonX
	{
		private static final long serialVersionUID = -7097051178021598223L;

		/**
		 * 
		 * @param normalColor
		 * @param selectedColor
		 * @param pressedColor
		 * @param clickedColor
		 * @param clickedSelectedColor
		 * @param clickedPressedColor
		 * @param frameNormalColor
		 * @param frameSelectedColor
		 * @param framePressedColor
		 * @param frameClickedColor
		 * @param frameClickedSelectedColor
		 * @param frameClickedPressedColor
		 * @param text
		 * @param rect
		 */
		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor,
				Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor,
				Color frameClickedPressedColor, String text, boolean[] rect)
		{
			super(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, frameNormalColor, frameSelectedColor,
					framePressedColor, frameClickedColor, frameClickedSelectedColor, frameClickedPressedColor, text, rect);
		}

		/**
		 * 
		 * @param normalColor
		 * @param selectedColor
		 * @param pressedColor
		 * @param clickedColor
		 * @param clickedSelectedColor
		 * @param clickedPressedColor
		 * @param text
		 * @param hasFrame
		 * @param rect
		 */
		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor,
				String text, boolean hasFrame, boolean[] rect)
		{
			super(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, text, hasFrame, rect);
		}

		/**
		 * 
		 * @param text
		 * @param hasFrame
		 * @param rect
		 */
		public ButtonX(String text, boolean hasFrame, boolean[] rect)
		{
			super(text, hasFrame, rect);
		}

	}

}
