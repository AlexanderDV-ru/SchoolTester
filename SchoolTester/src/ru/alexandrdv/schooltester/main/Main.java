package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ru.alexandrdv.schooltester.util.ButtonX;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Main
{

	public static void main(String[] args)
	{
		new Main();
	}

	int var = 0;
	ButtonX[] btns = new ButtonX[6];

	public Main()
	{
		JFrame window = new JFrame();
		window.setTitle("SchoolTester by AlexandrDV");
		window.getContentPane().setBackground(SystemColor.info);
		window.setDefaultCloseOperation(3);
		window.getContentPane().setLayout(null);
		window.setSize(448, 508);
		window.setVisible(true);
		// TODO Add theme with errors in words
		Color bg = new Color(200, 70, 0);
		Color fr = new Color(200, 0, 0);
		ButtonX question = new ButtonX(bg, bg, bg, bg, bg, bg, fr, fr, fr, fr, fr, fr, "Выверите дату путешествия Магеллана", new boolean[] { true, false, false, true });
		question.setFont(new Font("Comic Sans Ms", 0, 16));
		question.setTextColor(Color.black);
		question.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("clicked");

			}
		});
		question.setBounds(10, 11, 414, 50);
		window.getContentPane().add(question);

		ButtonX btn0 = new ButtonX("1994-1995", true, new boolean[] { false, false, false, false });
		btns[0] = btn0;
		window.getContentPane().add(btn0);

		ButtonX btn1 = new ButtonX("1994-1995", true, new boolean[] { false, false, false, false });
		btns[1] = btn1;
		window.getContentPane().add(btn1);

		ButtonX btn2 = new ButtonX("1994-1995", true, new boolean[] { false, false, false, false });
		btns[2] = btn2;
		window.getContentPane().add(btn2);

		ButtonX btn3 = new ButtonX("1994-1995", true, new boolean[] { false, false, false, false });
		btns[3] = btn3;
		window.getContentPane().add(btn3);

		ButtonX btn4 = new ButtonX("1994-1995", true, new boolean[] { false, false, false, false });
		btns[4] = btn4;
		window.getContentPane().add(btn4);

		ButtonX btn5 = new ButtonX("1994-1995", true, new boolean[] { false, false, false, false });
		btns[5] = btn5;
		window.getContentPane().add(btn5);
		{
			btn0.setFont(new Font("Comic Sans Ms", 0, 16));
			btn0.setTextColor(Color.black);
			btn0.setBounds(20, 72, 393, 42);
			btn0.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btns[1].setClicked(false);
					btns[2].setClicked(false);
					btns[3].setClicked(false);
					btns[4].setClicked(false);
					btns[5].setClicked(false);
					var = 0;
				}
			});
		}

		{
			btn1.setFont(new Font("Comic Sans Ms", 0, 16));
			btn1.setTextColor(Color.black);
			btn1.setBounds(20, 125, 393, 42);
			btn1.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btns[0].setClicked(false);
					btns[2].setClicked(false);
					btns[3].setClicked(false);
					btns[4].setClicked(false);
					btns[5].setClicked(false);
					var = 1;
				}
			});
		}

		{
			btn2.setFont(new Font("Comic Sans Ms", 0, 16));
			btn2.setTextColor(Color.black);
			btn2.setBounds(20, 179, 393, 42);
			btn2.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btns[0].setClicked(false);
					btns[1].setClicked(false);
					btns[3].setClicked(false);
					btns[4].setClicked(false);
					btns[5].setClicked(false);
					var = 2;
				}
			});
		}

		{
			btn3.setFont(new Font("Comic Sans Ms", 0, 16));
			btn3.setTextColor(Color.black);
			btn3.setBounds(20, 232, 393, 42);
			btn3.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btns[0].setClicked(false);
					btns[1].setClicked(false);
					btns[2].setClicked(false);
					btns[4].setClicked(false);
					btns[5].setClicked(false);
					var = 3;
				}
			});
		}

		{
			btn4.setFont(new Font("Comic Sans Ms", 0, 16));
			btn4.setTextColor(Color.black);
			btn4.setBounds(20, 285, 393, 42);
			btn4.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btns[0].setClicked(false);
					btns[1].setClicked(false);
					btns[2].setClicked(false);
					btns[3].setClicked(false);
					btns[5].setClicked(false);
					var = 4;
				}
			});
		}

		{
			btn5.setFont(new Font("Comic Sans Ms", 0, 16));
			btn5.setTextColor(Color.black);
			btn5.setBounds(20, 338, 393, 42);
			btn5.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btns[0].setClicked(false);
					btns[1].setClicked(false);
					btns[2].setClicked(false);
					btns[3].setClicked(false);
					btns[4].setClicked(false);
					var = 5;
				}
			});
		}
		ButtonX next = new ButtonX("Next", true, new boolean[] { false, false, false, false });
		next.setNormalColor(new Color(204, 255, 51));
		window.getContentPane().add(next);
		{
			ButtonX btnNewButton = next;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(280, 391, 133, 42);
			btnNewButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						result += objs[questionNumber].awards[var];
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
								try
								{
									result += objs[questionNumber].awards[var];
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
								JOptionPane j = new JOptionPane("Your result: " + result);
								JDialog d = j.createDialog(null, "SchoolTester by AlexandrDV");
								d.setLocationRelativeTo(window);
								d.setVisible(true);
								window.setVisible(false);
								System.exit(0);
							}
						});
					}
					if (questionNumber < objs.length)
						openQuestion(question, questionNumber);
				}
			});
			openQuestion(question, questionNumber);
		}
	}

	public void openQuestion(ButtonX question, int number)
	{
		btns[2].setVisible(objs[questionNumber].answers.length > 2);
		btns[3].setVisible(objs[questionNumber].answers.length > 4);
		btns[4].setVisible(objs[questionNumber].answers.length > 5);
		btns[5].setVisible(objs[questionNumber].answers.length > 6);
		for (int i = 0; i < objs[questionNumber].answers.length; i++)
		{
			btns[i].setText(objs[questionNumber].answers[i]);
			btns[i].setClicked(false);
		}
		question.setText("q1");
	}

	int result = 0;

	static class Question
	{
		String question;
		String[] answers;
		int[] awards;

		public Question(String question, String[] answers, int[] awards)
		{
			super();
			this.question = question;
			this.answers = answers;
			this.awards = awards;
		}
	}

	int questionNumber = 0;
	static final Question[] objs = new Question[] { new Question("q1", new String[] { "aa1", "aa2" }, new int[] { 2, 0 }), new Question("q1", new String[] { "aa1", "aa2" }, new int[] { 2, 0 }), new Question("q2", new String[] { "ab1", "ab2", "ab3" }, new int[] { 2, 0, 1 }) };

	static class ButtonX extends ru.alexandrdv.schooltester.util.ButtonX
	{

		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor, Color frameClickedPressedColor, String text, boolean[] rect)
		{
			super(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, frameNormalColor, frameSelectedColor, framePressedColor, frameClickedColor, frameClickedSelectedColor, frameClickedPressedColor, text, rect);
		}

		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, String text, boolean hasFrame, boolean[] rect)
		{
			super(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, text, hasFrame, rect);
		}

		public ButtonX(String text, boolean hasFrame, boolean[] rect)
		{
			super(text, hasFrame, rect);
		}

	}

}
