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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.alexandrdv.schooltester.main.Main.Question;

public class Window
{
	private JTextField classField;
	private JTextField surnameField;
	private JTextField nameField;
	private JTextField secondNameField;

	public Window()
	{
		parse(new File("Tests/7hr1.test"));
		System.exit(0);
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

		JComboBox<String> fileNameComboBox = new JComboBox<String>();
		fileNameComboBox.setBounds(180, 11, 180, 20);
		f.getContentPane().add(fileNameComboBox);

		JComboBox<String> classComboBox = new JComboBox<String>();
		classComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" }));
		classComboBox.setBackground(Color.WHITE);
		classComboBox.setBounds(180, 42, 40, 20);
		f.getContentPane().add(classComboBox);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(180, 166, 89, 23);
		f.getContentPane().add(btnStart);

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
				for (File f : files)
					if (f.getName().equals(fileNameComboBox.getSelectedItem()))
						parse(f);
				new Main(classComboBox.getSelectedItem().toString() + "-" + classField.getText(), surnameField.getText(), nameField.getText(), secondNameField.getText());
			}
		});
		f.setVisible(true);
	}

	public Question[] parse(File f)
	{
		String text = "";
		try
		{
			if (!f.exists())
				f.createNewFile();
			BufferedReader pw = new BufferedReader(new InputStreamReader(new FileInputStream(f), "Cp1251"));
			for (String line = null; (line = pw.readLine()) != null;)
				text += line + "\n";
			pw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		checkString(text,"\nquestions:");
		String questions = text.substring(text.indexOf("\nquestions:")+"\nquestions:".length(), text.length());
		for(int i=1;i<questions.split("\n\t,").length+1;i++)
		{
			String questionString="";
			ArrayList<String> answers=new ArrayList<String>();
			String question=questions.split("\n\t,")[i-1];
			String questionStart="\n\tquestion"+i+":";
			checkString(question,questionStart);
			question = question.substring(question.indexOf(questionStart)+questionStart.length(), question.length());
			checkString(question,"\n\t\tquestion:");
			questionString=question.substring(question.indexOf("\n\t\tquestion:")+"\n\t\tquestion:".length());
			questionString=questionString.substring(0,questionString.indexOf("\n"));
			System.out.println(questionString);
			checkString(text,"\n\t\tanswers:");
			for(int j=1;j<question.split("\n\t\t\t,").length+1;j++)
			{
				String answerString="";
				String answer=question.split("\n\t\t\t,")[j-1];
				String answerStart="\n\t\t\tanswer"+j+":";
				checkString(answer,answerStart);
				answer = answer.substring(answer.indexOf(answerStart)+answerStart.length(), answer.length());
				System.out.println(answer);
				//answers.add(arg0);
			}
			
		}
		return null;
	}
	public void print(String s)
	{
		System.out.println(s);
	}
	public void checkString(String text,String toCheck)
	{
		if (!text.contains(toCheck))
		{
			print("Syntax is wrong: file not have line \""+toCheck+"\"");
			System.exit(2);
		}
	}
}
