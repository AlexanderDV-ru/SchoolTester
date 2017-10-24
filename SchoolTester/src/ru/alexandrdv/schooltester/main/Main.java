package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

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

	public Main()
	{
		JFrame window = new JFrame();
		window.setTitle("SchoolTester by AlexandrDV");
		window.getContentPane().setBackground(SystemColor.info);
		window.setDefaultCloseOperation(3);
		window.getContentPane().setLayout(null);
		window.setSize(448, 369);
		window.setVisible(true);
		// TODO Add theme with errors in words
		Color bg = new Color(200, 70, 0);
		Color fr = new Color(200, 0, 0);
		ButtonX lblNewLabel = new ButtonX(bg, bg, bg, bg, bg, bg, fr, fr, fr, fr, fr, fr, "Выверите дату путешествия Магеллана", new boolean[] { true, false, false, true });
		lblNewLabel.setFont(new Font("Comic Sans Ms", 0, 16));
		lblNewLabel.setTextColor(Color.black);
		lblNewLabel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("clicked");

			}
		});
		lblNewLabel.setBounds(10, 11, 414, 50);
		window.getContentPane().add(lblNewLabel);

		ButtonX btn1 = new ButtonX("1940-1946",true, new boolean[] { false, false, false, false });
		ButtonX btn2 = new ButtonX("2004-2008",true, new boolean[] { false, false, false, false });
		ButtonX btn3 = new ButtonX("1010-1020",true, new boolean[] { false, false, false, false });
		ButtonX btn4 = new ButtonX("1200-1221",true, new boolean[] { false, false, false, false });
		{
			ButtonX btnNewButton = btn1;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(20, 231, 393, 42);
			btnNewButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btn2.setClicked(false);
					btn3.setClicked(false);
					btn4.setClicked(false);
					var = 1;
				}
			});
		}
		window.getContentPane().add(btn1);

		{
			ButtonX btnNewButton = btn2;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(20, 178, 393, 42);
			window.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btn1.setClicked(false);
					btn3.setClicked(false);
					btn4.setClicked(false);
					var = 2;
				}
			});
		}

		{
			ButtonX btnNewButton = btn3;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(20, 125, 393, 42);
			window.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btn1.setClicked(false);
					btn2.setClicked(false);
					btn4.setClicked(false);
					var = 3;
				}
			});
		}

		{
			ButtonX btnNewButton = btn4;
			btnNewButton.setFont(new Font("Comic Sans Ms", 0, 16));
			btnNewButton.setTextColor(Color.black);
			btnNewButton.setBounds(20, 72, 393, 42);
			window.getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					btn1.setClicked(false);
					btn2.setClicked(false);
					btn3.setClicked(false);
					var = 4;
				}
			});
		}
	}
	static class ButtonX extends ru.alexandrdv.schooltester.util.ButtonX
	{

		public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor, Color frameClickedPressedColor, String text,boolean[] rect)
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
