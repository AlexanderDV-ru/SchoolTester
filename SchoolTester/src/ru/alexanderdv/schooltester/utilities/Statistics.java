package ru.alexanderdv.schooltester.utilities;

import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class Statistics
{
	public static ArrayList<Config> getStatistics(String testFileName, String test, String classNumber, String classLetter, String surname, String name,
			String secondName)
	{
		File results = new File("Results");
		if (!results.isDirectory() && results.exists())
			results.delete();
		if (!results.exists())
			results.mkdir();
		File[] files = results.listFiles();
		ArrayList<Config> configs = new ArrayList<Config>();
		for (File file1 : files)
			if (file1.getName().equals(testFileName))
				for (File file : file1.listFiles())
					if (file.getName().startsWith("Result From") && file.getName().endsWith(".log"))
					{
						Config cfg = new Config(file);
						String language = cfg.getString("syntaxLanguage", null, false);
						if (test != null)
							if (!cfg.getString(MessageSystem.getMsg("testName", language), null, false).equals(test))
								continue;
						if (surname != null)
							if (!cfg.getString(MessageSystem.getMsg("studentSurname", language), null, false).equals(surname))
								continue;
						if (name != null)
							if (!cfg.getString(MessageSystem.getMsg("studentName", language), null, false).equals(name))
								continue;
						if (secondName != null)
							if (!cfg.getString(MessageSystem.getMsg("studentSecondName", language), null, false).equals(secondName))
								continue;
						if (classNumber != null)
							if (!cfg.getString(MessageSystem.getMsg("classNumber", language), null, false).equals(classNumber))
								continue;
						if (classLetter != null)
							if (!cfg.getString(MessageSystem.getMsg("classLetter", language), null, false).equals(classLetter))
								continue;
						configs.add(cfg);
					}
		return configs;
	}
}
