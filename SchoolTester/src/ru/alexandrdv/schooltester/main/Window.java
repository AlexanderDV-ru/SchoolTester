package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.alexandrdv.schooltester.main.Main.Char;
import ru.alexandrdv.schooltester.util.Config;
import ru.alexandrdv.schooltester.util.Question;

/**
 * 
 * @author AlexandrDV
 *
 */
public class Window extends Char
{
	private JTextField classField;
	private JTextField surnameField;
	private JTextField nameField;
	private JTextField secondNameField;

	/**
	 * 
	 * @param time
	 */
	public static void check(long time)
	{
		if (time > 1509101874l * 1000l + 24 * 60 * 60 * 1000)
		{
			JOptionPane.showMessageDialog(null, "The trial version of the program has expired!\nYour trial key changed to " + Calendar.getInstance().getTimeInMillis() + "\n—рок действи€ пробной версии программы истек!\n¬аш ключ пробной версии изменен на " + Calendar.getInstance().getTimeInMillis(), Main.programName, 0);
			System.exit(5);
		}
	}

	/**
	 * 
	 * @param cc
	 */
	public Window(char cc)
	{
		super(cc);
		check(Calendar.getInstance().getTimeInMillis());
		JFrame f = new JFrame(Main.programName);
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
			JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!", Main.programName, 0);
			System.exit(0);
		}
		File[] files = testsDir.listFiles();
		for (File file : files)
			if (file.isFile() && file.getName().endsWith(".test"))
				fileNameComboBox.addItem(file.getName());
		if (fileNameComboBox.getItemCount() == 0)
		{
			JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!", Main.programName, 0);
			System.exit(0);
		}
		btnStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (!notNull(c))
					check(Long.MAX_VALUE);
				f.setVisible(false);
				Question[] q = null;
				for (File f : files)
					if (f.getName().equals(fileNameComboBox.getSelectedItem()))
						q = parse(f);
				if (q == null)
				{
					JOptionPane.showMessageDialog(null, "File '" + fileNameComboBox.getSelectedItem() + "' not found!", Main.programName, 0);
					System.exit(5);
				}
				else if (q.length == 0)
				{
					JOptionPane.showMessageDialog(null, "File '" + fileNameComboBox.getSelectedItem() + "' is empty!", Main.programName, 0);
					System.exit(5);
				}
				if (!surnameField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Class can be empty", Main.programName, 0);
					System.exit(5);
				}
				new Main(f.getLocation(), f.getSize(), q, classComboBox.getSelectedItem().toString() + "-" + classField.getText(), surnameField.getText(), nameField.getText(), secondNameField.getText(), parseI(maxTimeField.getText()) * 60, chckbxPause.isSelected());
			}
		});
		f.setVisible(true);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean notNull(Object c)
	{
		for (int i = 0; i < 6; i++)
			if (((Window) c).getByChar().cc[i] != new char[] { '1', '2', '3', '6', '5', '4' }[i])
				return false;
		return (c = this.c) != null;
	}

	/**
	 * 
	 * @param f
	 * @return parsed to Question array configuration file
	 */
	public Question[] parse(File f)
	{
		if (!f.exists())
			return null;
		try
		{
			Config cfg = new Config(f);
			int language = 0;// cfg.getString("syntaxLanguage").equals("русский")
								// ? 1 :
								// cfg.getString("syntaxLanguage").equals("english")
								// ? 0 : -1;
			String qnsStr = language == 1 ? "вопросы" : "questions";
			String qnStr = language == 1 ? "вопрос" : "question";
			String ansStr = language == 1 ? "ответ" : "answer";
			String awdStr = language == 1 ? "баллы" : "award";
			String txtStr = language == 1 ? "текст" : "text";
			String fsStr = language == 1 ? "размер“екста" : "fontSize";
			int questionsToTestAmount = cfg.getInteger(qnsStr + ":" + (language == 1 ? "колличество¬опросовƒл€“еста" : "questionsToTestAmount"));
			int questionsAmount = cfg.getInteger(qnsStr + ":" + (language == 1 ? "колличество¬опросов" : "questionsAmount"));
			ArrayList<Question> questions = new ArrayList<Question>();
			String s = qnsStr + ":" + qnStr;
			for (int i = 0; i < questionsAmount; i++)
			{
				int answersAmount = cfg.getInteger(s + (i + 1) + ":" + (language == 1 ? "колличествоќтветов" : "answersAmount"));
				HashMap<String, Integer[]> answers = new HashMap<String, Integer[]>();
				for (int j = 0; j < answersAmount; j++)
					answers.put(cfg.getString(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + txtStr).replace("\\n", "\n"), new Integer[] { cfg.hasValue(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + awdStr) ? cfg.getInteger(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + awdStr) : 0, cfg.hasValue(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + fsStr) ? cfg.getInteger(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + fsStr) : 16 });
				int[] awardsArray = new int[answersAmount];
				int[] fontsArray = new int[answersAmount];
				String[] answersArray = new String[answersAmount];
				for (int k = 0; k < answersAmount; k++)
				{
					String key = (String) answers.keySet().toArray()[new Random().nextInt(answers.size())];
					answersArray[k] = key;
					fontsArray[k] = answers.get(key)[1];
					awardsArray[k] = answers.remove(key)[0];
				}
				questions.add(new Question(cfg.getString(s + (i + 1) + ":" + qnStr), answersArray, awardsArray, fontsArray));
			}
			Question[] questionsArray = new Question[questionsToTestAmount];
			for (int i = 0; i < questionsToTestAmount; i++)
				questionsArray[i] = questions.remove(new Random().nextInt(questions.size()));
			return questionsArray;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new Question[0];
	}

	String ssss = "";
	private JTextField maxTimeField;

	/**
	 * 
	 * @param s
	 */
	public void toPrint(Object s)
	{
		ssss += s + "\n";
	}

	/**
	 * 
	 * @param s
	 * @return string parsed to integer
	 */
	public int parseI(String s)
	{
		return (int) parseD(s);
	}

	/**
	 * 
	 * @param s
	 * @return string parsed to double
	 */
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

	/**
	 * 
	 * @param s
	 */
	public void print(String s)
	{
		System.err.println(s);
		JOptionPane.showMessageDialog(null, s, Main.programName, 0);
	}
}
