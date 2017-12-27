package ru.alexandrdv.schooltester.util;

import java.io.File;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import ru.alexandrdv.schooltester.main.FXFrame;

/**
 * Logger
 * 
 * @author AlexandrDV
 * @version 4.2.1a
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

}
