package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.alexandrdv.schooltester.main.Main.Question;
import javax.swing.JCheckBox;

public class Window
{
	private JTextField classField;
	private JTextField surnameField;
	private JTextField nameField;
	private JTextField secondNameField;

	public Window()
	{

		JFrame f = new JFrame("SchoolTester by AlexandrDV");
		f.setSize(452, 470);
		f.setDefaultCloseOperation(3);
		f.getContentPane().setLayout(null);

		classField = new JTextField();
		classField.setBounds(220, 42, 140, 20);
		f.getContentPane().add(classField);
		classField.setColumns(10);

		surnameField = new JTextField();
		surnameField.setBounds(180, 73, 180, 20);
		f.getContentPane().add(surnameField);
		surnameField.setColumns(10);

		nameField = new JTextField();
		nameField.setBounds(180, 104, 180, 20);
		f.getContentPane().add(nameField);
		nameField.setColumns(10);

		secondNameField = new JTextField();
		secondNameField.setBounds(180, 135, 180, 20);
		f.getContentPane().add(secondNameField);
		secondNameField.setColumns(10);
		
		maxTimeField = new JTextField();
		maxTimeField.setColumns(10);
		maxTimeField.setBounds(180, 166, 180, 20);
		f.getContentPane().add(maxTimeField);

		JLabel lblTestFileName = new JLabel("Test file name");
		lblTestFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTestFileName.setBounds(10, 14, 160, 14);
		f.getContentPane().add(lblTestFileName);

		JLabel lblClass = new JLabel("Class");
		lblClass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClass.setBounds(10, 45, 160, 14);
		f.getContentPane().add(lblClass);

		JLabel lblStudentsName = new JLabel("Student's name");
		lblStudentsName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentsName.setBounds(10, 107, 160, 14);
		f.getContentPane().add(lblStudentsName);

		JLabel lblStudentsSurname = new JLabel("Student's surname");
		lblStudentsSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentsSurname.setBounds(10, 76, 160, 14);
		f.getContentPane().add(lblStudentsSurname);

		JLabel lblStudentsSecondName = new JLabel("Student's second name");
		lblStudentsSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentsSecondName.setBounds(10, 138, 160, 14);
		f.getContentPane().add(lblStudentsSecondName);
		
		JLabel lblMaxTime = new JLabel("Max Time");
		lblMaxTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxTime.setBounds(10, 169, 160, 14);
		f.getContentPane().add(lblMaxTime);

		JComboBox<String> fileNameComboBox = new JComboBox<String>();
		fileNameComboBox.setBounds(180, 11, 180, 20);
		f.getContentPane().add(fileNameComboBox);

		JComboBox<String> classComboBox = new JComboBox<String>();
		classComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" }));
		classComboBox.setBackground(Color.WHITE);
		classComboBox.setBounds(180, 42, 40, 20);
		f.getContentPane().add(classComboBox);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(179, 197, 89, 23);
		f.getContentPane().add(btnStart);
		
		JCheckBox chckbxPause = new JCheckBox("Pause");
		chckbxPause.setBounds(274, 197, 86, 23);
		f.getContentPane().add(chckbxPause);

		File testsDir = new File("Tests");
		if (!testsDir.isDirectory())
		{
			if (!testsDir.exists())
				testsDir.mkdir();
			else
			{
				testsDir.delete();
				testsDir.mkdir();
			}
			JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!");
			System.exit(0);
		}
		File[] files = testsDir.listFiles();
		for (File file : files)
			if (file.isFile() && file.getName().endsWith(".test"))
				fileNameComboBox.addItem(file.getName());
		if (fileNameComboBox.getItemCount() == 0)
		{
			JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!");
			System.exit(0);
		}
		btnStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				f.setVisible(false);
				Question[] q = null;
				for (File f : files)
					if (f.getName().equals(fileNameComboBox.getSelectedItem()))
						q = parse(f);
				if (q == null)
				{
					JOptionPane.showMessageDialog(null, "File not found!");
					System.exit(5);
				}
				new Main(f.getLocation(), f.getSize(), q, classComboBox.getSelectedItem().toString() + "-" + classField.getText(), surnameField.getText(), nameField.getText(), secondNameField.getText(),parseI(maxTimeField.getText()),chckbxPause.isSelected());
			}
		});
		f.setVisible(true);
	}
	

	public Question[] parse(File f)
	{
		Config cfg = new Config(f);
		int questionsToTestAmount = cfg.getInteger("questions:questionsToTestAmount");
		int questionsAmount = cfg.getInteger("questions:questionsAmount");
		ArrayList<Question> questions = new ArrayList<Question>();
		String s = "questions:question";
		for (int i = 0; i < questionsAmount; i++)
		{
			int answersAmount = cfg.getInteger(s + (i + 1) + ":answersAmount");
			HashMap<String, Integer> answers = new HashMap<String, Integer>();
			for (int j = 0; j < answersAmount; j++)
				answers.put(cfg.getString(s + (i + 1) + ":answer" + (j + 1) + ":text"), cfg.getInteger(s + (i + 1) + ":answer" + (j + 1) + ":award"));
			int[] awardsArray = new int[answersAmount];
			String[] answersArray = new String[answersAmount];
			for (int k = 0; k < answersAmount; k++)
				awardsArray[k] = answers.remove(answersArray[k] = (String) answers.keySet().toArray()[new Random().nextInt(answers.size())]).intValue();
			questions.add(new Question(cfg.getString(s + (i + 1) + ":question"), answersArray, awardsArray));
		}
		Question[] questionsArray = new Question[questionsToTestAmount];
		for (int i = 0; i < questionsToTestAmount; i++)
			questionsArray[i] = questions.remove(new Random().nextInt(questions.size()));
		return questionsArray;
	}

	class Config
	{
		private File file;

		public Config(File file)
		{
			this.file = file;
		}

		public String getValue(String path)
		{
			String text = read();
			String[] dirs = path.split(":");
			for (int i = 0; i < dirs.length; i++)
			{
				for (int j = 0; j < i; j++)
					dirs[i] = "\t" + dirs[i];
				dirs[i] = "\n" + dirs[i] + ":";
			}
			for (int i = 0; i < dirs.length; i++)
				text = text.substring(text.indexOf(dirs[i]) + dirs[i].length());
			if (text.indexOf("\n") != -1)
				text = text.substring(0, text.indexOf("\n"));

			return text;
		}

		public String getString(String path)
		{
			String text = getValue(path);
			if (text.indexOf("\"") == -1 || text.indexOf("\"") == text.lastIndexOf("\""))
			{
				print("Syntax is wrong: value '" + text + "' must be typeof String");
				System.exit(4);
			}
			return text.substring(text.indexOf("\"") + 1, text.lastIndexOf("\""));
		}

		public double getDouble(String path)
		{

			String text = getValue(path);
			String text2 = text;
			for (char c : "-+0123456789.,\t ".toCharArray())
				text2 = text2.replace(c + "", "");
			if (!text2.equals(""))
			{
				print("Syntax is wrong: value '" + text + "' must be typeof Double");
				System.exit(3);
			}
			return parseD(text);
		}

		public int getInteger(String path)
		{

			String text = getValue(path);
			String text2 = text;
			for (char c : "-+0123456789\t ".toCharArray())
				text2 = text2.replace(c + "", "");
			if (!text2.equals(""))
			{
				print("Syntax is wrong: value '" + text + "' must be typeof Integer");
				System.exit(3);
			}
			return parseI(text);
		}

		public String read()
		{
			String text = "";
			try
			{
				if (!file.exists())
					file.createNewFile();
				BufferedReader pw = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1251"));
				for (String line = null; (line = pw.readLine()) != null;)
					text += line + "\n";
				pw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return text;
		}

	}

	String ssss = "";
	private JTextField maxTimeField;

	public void toPrint(Object s)
	{
		ssss += s + "\n";
	}

	public int parseI(String s)
	{
		return (int) parseD(s);
	}

	public double parseD(String s)
	{
		s = s.replace(" ", "").replace("\t", "");
		String num = "";
		if (s.startsWith("-"))
		{
			s = s.substring(1);
			num = "-";
		}
		if (s.startsWith("+"))
			s = s.substring(1);
		boolean hasDot = false;
		for (char c : s.toCharArray())
			if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
				num += c;
			else if (c == '.' || c == ',')
				if (!hasDot)
				{
					num += c;
					hasDot = true;
				}
				else return Double.parseDouble(num);
			else return Double.parseDouble(num);
		return Double.parseDouble(num);
	}

	public void print(String s)
	{
		System.err.println(s);
	}

	public void checkString(String text, String toCheck)
	{
		if (!text.contains(toCheck))
		{
			print("Syntax is wrong: file not have line \"" + toCheck + "\"");
			System.exit(2);
		}
	}

	public void checkStringType(String text)
	{
		if (text.indexOf("\"") == -1 || text.indexOf("\"") == text.lastIndexOf("\""))
		{
			print("Syntax is wrong: value '" + text + "' must be typeof String");
			System.exit(4);
		}
	}

	public void checkNumberType(String text)
	{
		String text2 = text;
		for (char c : "-+0123456789., ".toCharArray())
			text2 = text2.replace(c + "", "");
		if (!text2.equals(""))
		{
			print("Syntax is wrong: value '" + text + "' must be typeof Number");
			System.exit(3);
		}
	}
}
