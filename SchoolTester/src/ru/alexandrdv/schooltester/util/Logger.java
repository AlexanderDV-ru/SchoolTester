package ru.alexandrdv.schooltester.util;

import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import javafx.stage.Stage;
import ru.alexandrdv.schooltester.main.FXFrame;
import ru.alexandrdv.schooltester.main.Main;

/**
 * Logger
 * 
 * @author AlexandrDV
 * @version 4.4.5a
 */
public class Logger extends java.util.logging.Logger
{
	public static String log = "";

	public Logger(String name)
	{
		super(name, null);
		setParent(Logger.getGlobal());
		setLevel(Level.ALL);
	}

	@Override
	public void log(LogRecord logRecord)
	{
		logRecord.setMessage("[" + getName() + "] " + logRecord.getMessage());
		log += logRecord.getLevel() + " " + logRecord.getMillis() + " " + logRecord.getMessage() + "\n";
		getParent().log(logRecord);
	}

	public static void exit(int code)
	{
		if (!log.equals(""))
			FXFrame.writeFile(new File("Logs/" + Calendar.getInstance().getTimeInMillis() + ".log"), log);
		System.exit(code);
	}

	public static void showMsgDialog(Component r, Object msg, int messageType, int optionType)
	{
		JOptionPane pane = new JOptionPane(msg, messageType, optionType);
		JDialog dialog = pane.createDialog(Main.programName);
		ArrayList<Image> icons = new ArrayList<>();
		icons.add(new ImageIcon(Logger.class.getResource("/Icon16x.png")).getImage());
		icons.add(new ImageIcon(Logger.class.getResource("/Icon32x.png")).getImage());
		icons.add(new ImageIcon(Logger.class.getResource("/Icon48x.png")).getImage());
		dialog.setIconImages(icons);
		dialog.setLocationRelativeTo(r);
		dialog.setVisible(true);
	}

	public static void showMsgDialog(Stage stage, Object msg, int messageType, int optionType)
	{
		Component c = new Panel();
		c.setBounds((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight());
		showMsgDialog(c, msg, messageType, optionType);
	}

}
