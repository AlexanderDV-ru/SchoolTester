package ru.alexandrdv.schooltester.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.alexandrdv.schooltester.util.Config;
import ru.alexandrdv.schooltester.util.MessageSystem;
import ru.alexandrdv.schooltester.util.Question;
import ru.alexandrdv.schooltester.util.Question.Answer;

/**
 * StartBlank
 * 
 * @author AlexandrDV
 * @version 2.0.0a
 *
 */
public class StartBlank
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
		if (time / 60 / 60 / 1000 >= 419756l + 24 * 7)
		{
			JOptionPane.showMessageDialog(null, "The trial version of the program has expired!\nYour trial key changed to " + Calendar.getInstance()
					.getTimeInMillis() + "\n—рок действи€ пробной версии программы истек!\n¬аш ключ пробной версии изменен на " + Calendar.getInstance()
							.getTimeInMillis(), Main.programName, 0);
			System.exit(5);
		}
	}
	public void changeLanguage()
	{
		mnHelp.setText(MessageSystem.getStringByKey("help"));
		{
			mntmPrivacyPolicy.setText(MessageSystem.getStringByKey("privacyPolicy"));
		}
		mnSettings.setText(MessageSystem.getStringByKey("settings"));
		{
			mnLanguage.setText(MessageSystem.getStringByKey("language"));
		}
		
		lblTestFileName.setText(MessageSystem.getStringByKey("testFileName"));
		lblClass.setText(MessageSystem.getStringByKey("class"));
		lblStudentsSurname.setText(MessageSystem.getStringByKey("studentSurname"));
		lblStudentsName.setText(MessageSystem.getStringByKey("studentName"));
		lblStudentsSecondName.setText(MessageSystem.getStringByKey("studentSecondName"));
		lblMaxTime.setText(MessageSystem.getStringByKey("maxTime"));
		btnStart.setText(MessageSystem.getStringByKey("start"));
		chckbxPause.setText(MessageSystem.getStringByKey("pause"));
	}

	/**
	 * 
	 * @param cc
	 * @param args
	 */
	public StartBlank(String[] args)
	{
		check(Calendar.getInstance().getTimeInMillis());
		
		JFrame window = new JFrame(Main.programName);
		window.setSize(452, 470);
		window.setDefaultCloseOperation(3);
		window.getContentPane().setLayout(null);
		window.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 436, 21);
		window.setJMenuBar(menuBar);
		
		mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		mnLanguage = new JMenu("Language");
		mnSettings.add(mnLanguage);
		
		ActionListener languagePickListener = (e) ->
		{
			MessageSystem.setLanguage(((JMenuItem) e.getSource()).getText().split("\n")[1]);
			changeLanguage();
		};

		JMenuItem mntmEnglish = new JMenuItem("English (\nen_uk\n)");
		mntmEnglish.addActionListener(languagePickListener);
		mnLanguage.add(mntmEnglish);

		JMenuItem menuRussian = new JMenuItem("–усский (\nru_ru\n)");
		menuRussian.addActionListener(languagePickListener);
		mnLanguage.add(menuRussian);
		
		mnHelp = new JMenu();
		menuBar.add(mnHelp);
		
		mntmPrivacyPolicy = new JMenuItem();
		mnHelp.add(mntmPrivacyPolicy);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 436, 411);
		window.getContentPane().add(panel);

		classField = new JTextField();
		classField.setBounds(220, 42, 140, 20);
		panel.add(classField);
		classField.setColumns(10);

		surnameField = new JTextField();
		surnameField.setBounds(180, 73, 180, 20);
		panel.add(surnameField);
		surnameField.setColumns(10);

		nameField = new JTextField();
		nameField.setBounds(180, 104, 180, 20);
		panel.add(nameField);
		nameField.setColumns(10);

		secondNameField = new JTextField();
		secondNameField.setBounds(180, 135, 180, 20);
		panel.add(secondNameField);
		secondNameField.setColumns(10);

		maxTimeField = new JTextField();
		maxTimeField.setColumns(10);
		maxTimeField.setBounds(180, 166, 180, 20);
		panel.add(maxTimeField);

		lblTestFileName = new JLabel("Test file name");
		lblTestFileName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTestFileName.setBounds(10, 14, 160, 14);
		panel.add(lblTestFileName);

		lblClass = new JLabel("Class");
		lblClass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClass.setBounds(10, 45, 160, 14);
		panel.add(lblClass);

		lblStudentsName = new JLabel("Student's name");
		lblStudentsName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentsName.setBounds(10, 107, 160, 14);
		panel.add(lblStudentsName);

		lblStudentsSurname = new JLabel("Student's surname");
		lblStudentsSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentsSurname.setBounds(10, 76, 160, 14);
		panel.add(lblStudentsSurname);

		lblStudentsSecondName = new JLabel("Student's second name");
		lblStudentsSecondName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentsSecondName.setBounds(10, 138, 160, 14);
		panel.add(lblStudentsSecondName);

		lblMaxTime = new JLabel("Max Time");
		lblMaxTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaxTime.setBounds(10, 169, 160, 14);
		panel.add(lblMaxTime);

		JComboBox<String> fileNameComboBox = new JComboBox<String>();
		fileNameComboBox.setBounds(180, 11, 180, 20);
		panel.add(fileNameComboBox);

		JComboBox<String> classComboBox = new JComboBox<String>();
		classComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "<", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", ">" }));
		classComboBox.setBackground(Color.WHITE);
		classComboBox.setBounds(180, 42, 40, 20);
		panel.add(classComboBox);

		btnStart = new JButton("Start");
		btnStart.setBounds(179, 197, 89, 23);
		panel.add(btnStart);

		chckbxPause = new JCheckBox("Pause");
		chckbxPause.setBounds(274, 197, 86, 23);
		panel.add(chckbxPause);
		
		changeLanguage();

		if (args.length == 0)
			args = new String[] { "" };

		if (args[0].endsWith(".test"))
		{
			started = new File(args[0]);
			fileNameComboBox.addItem(started.getName());
			fileNameComboBox.setSelectedIndex(0);
			fileNameComboBox.setEnabled(false);
		}
		else
		{
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
			files = testsDir.listFiles();
			for (File file : files)
				if (file.isFile() && file.getName().endsWith(".test"))
					fileNameComboBox.addItem(file.getName());
		}
		if (fileNameComboBox.getItemCount() == 0)
		{
			JOptionPane.showMessageDialog(null, "In directory Tests not exists any files!", Main.programName, 0);
			System.exit(0);
		}
		btnStart.addActionListener((event) ->

		{
			if (classField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Class field can't be empty!", Main.programName, 0);
				return;
			}
			if (surnameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Surname field can't be empty!", Main.programName, 0);
				return;
			}
			if (nameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Name field can't be empty!", Main.programName, 0);
				return;
			}
			if (secondNameField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Second name field can't be empty!", Main.programName, 0);
				return;
			}
			Question[] q = null;
			if (fileNameComboBox.isEnabled())
			{
				for (File f : files)
					if (f.getName().equals(fileNameComboBox.getSelectedItem()))
						q = parse(f);
			}
			else q = parse(started);

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
			new Main(window.getLocation(), window.getSize(), q, classComboBox.getSelectedItem().toString() + "-" + classField.getText(), surnameField.getText(),
					nameField.getText(), secondNameField.getText(), Math.max(parseI(maxTimeField.getText()), 1) * 60, chckbxPause.isSelected());
			window.setVisible(false);
		});
		window.setVisible(true);
	}

	File[] files = null;
	File started = null;

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
			cfg.getText(true);
			int language;
			switch (cfg.getString("syntaxLanguage").toLowerCase())
			{
				case "русский":
					language = 1;
					break;
				default:
				case "english":
					language = 0;
					break;
			}
			String qnsStr = language == 1 ? "вопросы" : "questions";
			String qnStr = language == 1 ? "вопрос" : "question";
			String ansStr = language == 1 ? "ответ" : "answer";
			String awdStr = language == 1 ? "баллы" : "award";
			String potStr = language == 1 ? "тип" : "type";
			String txtStr = language == 1 ? "текст" : "text";
			String fsStr = language == 1 ? "размер“екста" : "fontSize";
			int questionsToTestAmount = cfg.getInteger(qnsStr + ":" + (language == 1 ? "колличество¬опросовƒл€“еста" : "questionsToTestAmount"));
			int questionsAmount = cfg.getInteger(qnsStr + ":" + (language == 1 ? "колличество¬опросов" : "questionsAmount"));
			int dqFont = cfg.hasValue(qnsStr + ":" + (language == 1 ? "колличество¬опросов" : "defaultQuestionFont"))?cfg.getInteger(qnsStr + ":" + (language == 1 ? "колличество¬опросов" : "defaultQuestionFont")):16;
			int daFont = cfg.hasValue(qnsStr + ":" + (language == 1 ? "колличество¬опросов" : "defaultAnswerFont"))?cfg.getInteger(qnsStr + ":" + (language == 1 ? "колличество¬опросов" : "defaultAnswerFont")):16;
			ArrayList<Question> questions = new ArrayList<Question>();
			String s = qnsStr + ":" + qnStr;
			for (int i = 0; i < questionsAmount; i++)
			{
				int dqaFont = cfg.hasValue(s + (i + 1) + ":" + (language == 1 ? "колличествоќтветов" : "defaultAnswerFont"))?cfg.getInteger(s + (i + 1) + ":" + (language == 1 ? "колличествоќтветов" : "defaultAnswerFont")):daFont;
				int answersAmount = cfg.getInteger(s + (i + 1) + ":" + (language == 1 ? "колличествоќтветов" : "answersAmount"));
				int award = cfg.getInteger(s + (i + 1) + ":" + awdStr);
				boolean pickOneType = cfg.getBoolean(s + (i + 1) + ":" + potStr);
				ArrayList<Answer> answers = new ArrayList<Answer>();
				for (int j = 0; j < answersAmount; j++)
					answers.add(new Answer(cfg.getString(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + txtStr).replace("\\n", "\n"), new Font("Ms Comic Sans", 0,
							cfg.hasValue(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + fsStr) ? cfg.getInteger(s + (i + 1) + ":" + ansStr + (j + 1) + ":"
									+ fsStr) : dqaFont), cfg.hasValue(s + (i + 1) + ":" + ansStr + (j + 1) + ":" + awdStr) ? cfg.getInteger(s + (i + 1) + ":"
											+ ansStr + (j + 1) + ":" + awdStr) : 0));
				Answer[] answersArray = new Answer[answersAmount];
				for (int k = 0; k < answersAmount; k++)
					answersArray[k] = (Answer) answers.remove(new Random().nextInt(answers.size()));

				questions.add(new Question(cfg.getString(s + (i + 1) + ":" + qnStr), award, pickOneType, answersArray));
			}
			Question[] questionsArray = new Question[questionsToTestAmount];
			for (int i = 0; i < questionsToTestAmount; i++)
				questionsArray[i] = questions.remove(new Random().nextInt(questions.size()));
			return questionsArray;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return new Question[0];
	}

	String ssss = "";
	private JTextField maxTimeField;
	private JMenu mnHelp;
	private JMenuItem mntmPrivacyPolicy;
	private JMenu mnSettings;
	private JMenu mnLanguage;
	private JLabel lblMaxTime;
	private JLabel lblStudentsSecondName;
	private JLabel lblStudentsSurname;
	private JLabel lblStudentsName;
	private JLabel lblClass;
	private JLabel lblTestFileName;
	private JButton btnStart;
	private JCheckBox chckbxPause;

	/**
	 * 
	 * @param s
	 * @return
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
					num += '.';
					hasDot = true;
				}
				else break;
			else break;
		return num.equals("-") || num.equals("") || num.equals(".") ? 0 : Double.parseDouble(num);
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
