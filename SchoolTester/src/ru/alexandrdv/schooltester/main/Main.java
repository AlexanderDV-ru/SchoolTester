package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import ru.alexandrdv.schooltester.util.Question;

/**
 * 
 * @author AlexandrDV
 *
 */
public class Main
{
	public static final String programName = "SchoolTester v1.1.2a by AlexandrDV";

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String s = JOptionPane.showInputDialog(null, "Enter Password", programName, 2);
		for (int i = 0; i < 6; i++)
			if (s.toCharArray()[i] != new char[] { '1', '2', '3', '6', '5', '4' }[i])
				return;
		c = new Window('0');
		c.getByChar().c.cc = s.toCharArray();
		// new Main(null, null, null, "", "", "", "", 0, true);
	}

	static Window c;

	/**
	 * 
	 * @author AlexandrDV
	 *
	 */
	static class Char
	{
		public final Char c;

		public Char(char c)
		{
			this.c = this;
			cc = getByChar().cc = new char[] { c };
		}

		char[] cc;

		public Char getByChar()
		{
			return c.c.c.c.c.c.c.c.c.c.c.c.c.c.c.c;
		}
	}

	boolean paused = false, canPause;
	int var = 0;
	ButtonX[] btns = new ButtonX[6];

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
	public Main(Point parentLoc, Dimension parentSize, Question[] q, String _class, String surname, String name, String secondName, int maxTime, boolean canPause)
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
		ButtonX question = new ButtonX(bg, bg, bg, bg, bg, bg, fr, fr, fr, fr, fr, fr, "Выверите дату путешествия Магеллана", new boolean[] { true, false, false, true });
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
		answersButtonsListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				var = buttons.indexOf((ButtonX) e.getSource());
				for (ButtonX button : buttons)
					button.setClicked(buttons.indexOf(button) == var);
			}
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
		timer.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (canPause && toPauseTime <= 0)
				{
					question.setVisible(paused);
					next.setVisible(paused);
					for (int i = 0; i < objs[questionNumber].getAnswers().length; i++)
						btns[i].setVisible(paused);
					paused = !paused;
					toPauseTime = 300;
				}
				timer.setClicked(paused);

			}
		});
		info.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				info.setClicked(false);
			}
		});

		next.setNormalColor(new Color(204, 255, 51));
		window.getContentPane().add(next);
		{
			ButtonX btnNewButton = next;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(293, 507, 120, 42);

			btnNewButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						result += objs[questionNumber].getAwards()[var];
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					questionNumber++;
					if (questionNumber == objs.length - 1)
					{
						btnNewButton.clearActionListeners();
						btnNewButton.setText("Finish");
						btnNewButton.addActionListener(new ActionListener()
						{

							@Override
							public void actionPerformed(ActionEvent arg0)
							{
								finish(window, _class, surname, name, secondName);
							}
						});
					}
					if (questionNumber < objs.length)
						openQuestion(question, info, questionNumber);
				}
			});
			openQuestion(question, info, questionNumber);
		}
		new Timer(1, new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (finished)
				{
					if (c.cc[4] != '5')
						Window.check(Long.MAX_VALUE);
					else return;
				}

				if (!paused)
					timeOfWork += 0.002f;
				allTime += 0.002f;
				toPauseTime--;
				timer.setText(!(finished = c.cc[4] != '5') ? toSize((maxTime - (int) timeOfWork) / 60, 2) + ":" + toSize((maxTime - (int) timeOfWork) % 60, 2) : "");
				if (timeOfWork >= maxTime)
					finish(window, _class, surname, name, secondName);
				Window.check(Calendar.getInstance().getTimeInMillis());
			}
		}).start();
	}

	ActionListener answersButtonsListener;

	int toPauseTime = 0;
	boolean finished = false;

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
		finished = true;
		try
		{
			result += objs[questionNumber].getAwards()[var];
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
		File f = new File("Result From " + toSize(c.get(Calendar.DAY_OF_YEAR), 2) + "_" + toSize(c.get(Calendar.HOUR), 2) + "-" + toSize(c.get(Calendar.MINUTE), 2) + "-" + toSize(c.get(Calendar.SECOND), 2) + ".txt");
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

	int maxResult;
	int maxTime = 20 * 60;

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

	float allTime = 0, timeOfWork = 0;

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
		info.setText("1/3");
		question.setText(objs[questionNumber].getQuestion());
	}

	int result = 0;

	int questionNumber = 0;
	final Question[] objs;

	/**
	 * 
	 * @author AlexandrDV
	 *
	 */
	static class ButtonX extends ru.alexandrdv.schooltester.util.ButtonX
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
		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor, Color frameClickedPressedColor, String text, boolean[] rect)
		{
			super(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, frameNormalColor, frameSelectedColor, framePressedColor, frameClickedColor, frameClickedSelectedColor, frameClickedPressedColor, text, rect);
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
		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, String text, boolean hasFrame, boolean[] rect)
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
