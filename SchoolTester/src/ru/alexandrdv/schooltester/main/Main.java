package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.print.attribute.standard.JobPrioritySupported;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import ru.alexandrdv.schooltester.server.Server;
import ru.alexandrdv.schooltester.server.Server.Pack;
import ru.alexandrdv.schooltester.util.Config;
import ru.alexandrdv.schooltester.util.MessageSystem;
import ru.alexandrdv.schooltester.util.Question;
import ru.alexandrdv.schooltester.util.Question.Answer;

/**
 * Main
 * 
 * @author AlexandrDV
 * @version 2.0.0a
 *
 */
public class Main
{
	public static final String version = "2.0.0a";
	public static final String authors = "AlexandrDV";
	public static final String programName = "SchoolTester v" + version + " by " + authors;
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
	private ButtonX question_1;
	private ButtonX info_1;
	private ButtonX timer;
	private ButtonX next;
	private ButtonX finish;

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

	private static final boolean testMode = false;
	/**
	 * The main method of class Main
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Config cfg=new Config(new File("language.cfg"));
		MessageSystem.setLanguage(cfg.getString("language"));
		if (!testMode)
			try
			{
				long iii=new Random().nextInt(1000)+1;
				DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) + 10000);
				socket.setSoTimeout(5000);
				byte[] data = Server.writeToByteArray(new Server.Pack("checkUpdates", "1", iii*Long.parseLong(JOptionPane.showInputDialog("Enter password:"))));
				socket.send(new DatagramPacket(data, 0, data.length, InetAddress.getByName("94.181.44.135"), 21577));// отправление пакета
				for (int i = 0; i < 2; i++)
				{
					byte[] data2 = new byte[256];
					DatagramPacket pac = new DatagramPacket(data2, data2.length);
					socket.receive(pac);
					

					if(((Server.Pack) Server.readByteArray(data2)).str2.equals("2"))
					{
						byte[] d2data = Server.writeToByteArray(new Pack("checkUpdates", "3",((Pack) Server.readByteArray(data2)).key/iii));
						System.out.println(((Pack) Server.readByteArray(data2)).key/iii);
						socket.send(new DatagramPacket(d2data, 0, d2data.length, pac.getAddress(), pac.getPort()));// отправление пакета
						continue;
					}
					
					String s = ((Server.Pack) Server.readByteArray(data2)).str;
					String s2 = ((Server.Pack) Server.readByteArray(data2)).str2;
					if(((Server.Pack) Server.readByteArray(data2)).key==-58)
					{
						JOptionPane.showMessageDialog(null, MessageSystem.getStringByKey("badKey"));
						System.exit(50);
					}
					if (!s.equals(version))
					{
						JOptionPane.showMessageDialog(null, MessageSystem.getStringByKey("updateMsg") + s);
						launchBrowser(s2);
					}
				}
				socket.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		new StartBlank(args);
	}

	public void changeLanguage()
	{
		next.setText(MessageSystem.getStringByKey("next"));
		finish.setText(MessageSystem.getStringByKey("finish"));
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
	 * @wbp.parser.entryPoint
	 */
	public Main(Point parentLoc, Dimension parentSize, Question[] q, String _class, String surname, String name, String secondName, int maxTime,
			boolean canPause)
	{
		this.canPause = canPause;
		this.maxTime = maxTime;
		objs = q;
		for (int j = 0, max = objs[j].getAward(); j < objs.length; max = objs[j].getAward(), j++, maxResult += max)
			for (int i = 0; i < objs[j].getAnswers().length; i++)
				if (objs[j].isPickOneType())
					max = Math.max(max, objs[j].getAnswers()[i].getAward());
				else for (Answer answer : objs[j].getAnswers())
					max = Math.max(0, answer.getAward());
		JFrame window = new JFrame();
		window.setSize(451, 576);
		window.setLocation(parentLoc.x + parentSize.width / 2 - window.getWidth() / 2, parentLoc.y + parentSize.height / 2 - window.getHeight() / 2);
		window.setTitle(programName);
		window.setDefaultCloseOperation(3);
		window.getContentPane().setLayout(null);
		window.setVisible(true);

		Color bg = new Color(200, 70, 0);
		Color fr = new Color(200, 0, 0);

		ArrayList<ButtonX> buttons = new ArrayList<ButtonX>();
		answersButtonsListener = (e) ->
		{
			var = buttons.indexOf((ButtonX) e.getSource());
			for (ButtonX button : buttons)
				button.setClicked(buttons.indexOf(button) == var);
		};
		timer = new ButtonX(toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2), true, new boolean[] { false, false, false, false });
		timer.setNormalColor(new Color(173, 255, 47));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 435, 21);
		window.getContentPane().add(menuBar);

		JPanel content = new JPanel();
		content.setBackground(SystemColor.info);
		content.setBounds(0, 22, 435, 516);
		window.getContentPane().add(content);
		content.setLayout(null);
		question_1 = new ButtonX(bg, bg, bg, bg, bg, bg, fr, fr, fr, fr, fr, fr, "", new boolean[] { true, false, false, true });
		question_1.setFont(new Font("Comic Sans Ms", 0, 16));
		question_1.setTextColor(Color.black);
		question_1.setBounds(10, 10, 414, 60);
		content.add(question_1);

		ButtonX btn0 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[0] = btn0;
		content.add(btn0);

		btn0.setFont(new Font("Comic Sans Ms", 0, 16));
		btn0.setTextColor(Color.black);
		btn0.setBounds(20, 75, 393, 60);

		ButtonX btn1 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[1] = btn1;
		content.add(btn1);

		btn1.setFont(new Font("Comic Sans Ms", 0, 16));
		btn1.setTextColor(Color.black);
		btn1.setBounds(20, 140, 393, 60);

		ButtonX btn2 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[2] = btn2;
		content.add(btn2);

		btn2.setFont(new Font("Comic Sans Ms", 0, 16));
		btn2.setTextColor(Color.black);
		btn2.setBounds(20, 205, 393, 60);

		ButtonX btn3 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[3] = btn3;
		content.add(btn3);

		btn3.setFont(new Font("Comic Sans Ms", 0, 16));
		btn3.setTextColor(Color.black);
		btn3.setBounds(20, 270, 393, 60);

		ButtonX btn4 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[4] = btn4;
		content.add(btn4);

		btn4.setFont(new Font("Comic Sans Ms", 0, 16));
		btn4.setTextColor(Color.black);
		btn4.setBounds(20, 335, 393, 60);

		ButtonX btn5 = new ButtonX("", true, new boolean[] { false, false, false, false });
		btns[5] = btn5;
		content.add(btn5);

		btn5.setFont(new Font("Comic Sans Ms", 0, 16));
		btn5.setTextColor(Color.black);
		btn5.setBounds(20, 400, 393, 60);

		info_1 = new ButtonX("", true, new boolean[] { false, false, false, false });
		info_1.setNormalColor(Color.WHITE);
		info_1.setBackground(Color.WHITE);
		openQuestion(info_1, questionNumber);
		content.add(info_1);

		info_1.setFont(new Font("Arial Bold", 1, 16));
		info_1.setTextColor(Color.black);
		info_1.setBounds(157, 465, 120, 42);
		info_1.setRounding(10);
		info_1.addActionListener(e -> info_1.setClicked(false));

		next = new ButtonX("", true, new boolean[] { false, false, false, false });
		next.setRounding(10);
		next.setNormalColor(new Color(204, 255, 51));
		next.setFont(new Font("Comic Sans Ms", 0, 16));
		next.setTextColor(Color.black);
		next.setBounds(293, 465, 120, 42);
		next.addActionListener(e ->
		{
			result += objs[questionNumber].getAward();
			if (var >= 0)
			{
				result += objs[questionNumber].getAnswers()[var].getAward();
				if (objs[questionNumber].getAnswers()[var].getAward() > 0)
					info_1.setBackground(Color.green);
				else info_1.setBackground(Color.red);
			}
			else info_1.setBackground(Color.red);
			questionNumber++;
			if (questionNumber == objs.length - 1)
			{
				next.setVisible(false);
				finish.setVisible(true);
			}
			if (questionNumber < objs.length)
				openQuestion(info_1, questionNumber);
		});
		content.add(next);

		finish = new ButtonX("", true, new boolean[] { false, false, false, false });
		finish.setRounding(10);
		finish.setNormalColor(new Color(204, 255, 51));
		finish.setFont(new Font("Comic Sans Ms", 0, 16));
		finish.setTextColor(Color.black);
		finish.setBounds(293, 465, 120, 42);
		finish.addActionListener(ev -> finish(window, _class, surname, name, secondName));
		content.add(finish);
		finish.setVisible(false);

		content.add(timer);

		timer.setFont(new Font("Arial Bold", 1, 16));
		timer.setTextColor(Color.black);
		timer.setBounds(20, 465, 120, 42);
		timer.setRounding(10);
		timer.addActionListener(e ->
		{
			if (Main.this.canPause && toPauseTime <= 0)
			{
				question_1.setVisible(paused);
				next.setVisible(paused);
				for (int i = 0; i < objs[questionNumber].getAnswers().length; i++)
					btns[i].setVisible(paused);
				paused = !paused;
				toPauseTime = 300;
			}
			timer.setClicked(paused);

		});
		for (ButtonX btn : btns)
		{
			buttons.add(btn);
			btn.addActionListener(answersButtonsListener);
		}
		lastTime = Calendar.getInstance().getTimeInMillis();
		new Timer(1, e ->
		{
			/*
			 * if (finished) { if (c.cc[4] != '5') StartBlank.check(Long.MAX_VALUE); else return; }
			 */
			long time = Calendar.getInstance().getTimeInMillis();

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
		changeLanguage();
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
		paused = true;
		result += objs[questionNumber].getAward();
		if (var >= 0)
			result += objs[questionNumber].getAnswers()[var].getAward();
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
		text += MessageSystem.getStringByKey("class") + ": " + _class + "\n";
		text += MessageSystem.getStringByKey("studentSurname") + ": " + surname + "\n";
		text += MessageSystem.getStringByKey("studentName") + ": " + name + "\n";
		text += MessageSystem.getStringByKey("studentSecondName") + ": " + secondName + "\n";
		text += MessageSystem.getStringByKey("result") + ": " + result + "\n";
		text += MessageSystem.getStringByKey("maxResult") + ": " + maxResult + "\n";
		text += MessageSystem.getStringByKey("time") + ": " + toSize((int) timeOfWork / 60, 2) + ":" + toSize((int) timeOfWork % 60, 2) + "\n";
		text += MessageSystem.getStringByKey("maxTime") + ": " + toSize(maxTime / 60, 2) + ":" + toSize(maxTime % 60, 2) + "\n";
		text += MessageSystem.getStringByKey("fullTime") + ": " + toSize((int) allTime / 60, 2) + ":" + toSize((int) allTime % 60, 2);
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
	public void openQuestion(ButtonX info, int number)
	{
		btns[2].setVisible(objs[questionNumber].getAnswers().length > 2);
		btns[3].setVisible(objs[questionNumber].getAnswers().length > 4);
		btns[4].setVisible(objs[questionNumber].getAnswers().length > 5);
		btns[5].setVisible(objs[questionNumber].getAnswers().length > 6);
		for (int i = 0; i < objs[questionNumber].getAnswers().length; i++)
		{
			btns[i].setText(objs[questionNumber].getAnswers()[i].getText());
			btns[i].setFont(new Font(btns[i].getFont().getFontName(), btns[i].getFont().getStyle(), objs[questionNumber].getAnswers()[i].getFont().getSize()));
			btns[i].setClicked(false);
		}
		var = -1;
		info.setText((questionNumber + 1) + "/" + objs.length);
		question_1.setText(objs[questionNumber].getQuestion());
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
