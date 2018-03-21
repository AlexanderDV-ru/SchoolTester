package ru.alexandrdv.schooltester.util;

import java.io.File;
import java.util.ArrayList;

/**
 * Statistics
 * 
 * @author AlexandrDV
 * @version 4.3.0a
 */
@Deprecated
public class Statistics
{
	public static ArrayList<Config> getStats(String test, String _class, String surname, String name, String secondName)
	{
		File results = new File("Results");
		if (!results.isDirectory() && results.exists())
			results.delete();
		if (!results.exists())
			results.mkdir();
		File[] files = results.listFiles();
		ArrayList<Config> configs = new ArrayList<Config>();
		for (File file : files)
			if (file.getName().startsWith("Result From") && file.getName().endsWith(".log"))
			{
				Config cfg = Config.getConfig(file);
				String language = cfg.getString("syntaxLanguage");
				if (test != null)
					if (!cfg.getString(MessageSystem.getMsg("file", language)).equals(test))
						continue;
				if (surname != null)
					if (!cfg.getString(MessageSystem.getMsg("studentSurname", language)).equals(surname))
						continue;
				if (name != null)
					if (!cfg.getString(MessageSystem.getMsg("studentName", language)).equals(name))
						continue;
				if (secondName != null)
					if (!cfg.getString(MessageSystem.getMsg("studentSecondName", language)).equals(secondName))
						continue;
				if (_class != null)
					if (!cfg.getString(MessageSystem.getMsg("class", language)).equals(_class))
						continue;
				configs.add(cfg);
			}
		return configs;
	}
}