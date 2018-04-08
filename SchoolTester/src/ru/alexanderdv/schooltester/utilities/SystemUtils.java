package ru.alexanderdv.schooltester.utilities;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * SystemUtils - the utils class used to interaction with system
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.9.0a
 */
public class SystemUtils
{
	/**
	 * Opens page with URL - 'url' in browser, if browser is closed, browser will be started
	 * 
	 * @param url
	 *            - URL of page to opening in browser
	 */
	public static void openUrl(String url)
	{
		if (Desktop.isDesktopSupported())
		{
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE))
				try
				{
					URI uri = new URI(url);
					desktop.browse(uri);
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
				catch (URISyntaxException use)
				{
					use.printStackTrace();
				}
		}
	}

	/**
	 * Writes text - 'text' to the file - 'file'
	 * 
	 * @param file
	 *            - the file to text writing
	 * @param text
	 *            - the text to writing to file
	 */
	public static void writeFile(File file, String text, Charset charset)
	{
		try
		{
			String path = file.getAbsolutePath().replace("\\", "/").split("/")[0];
			for (int i = 1; i < file.getAbsolutePath().replace("\\", "/").split("/").length - 1; path += "\\" + file.getAbsolutePath().replace("\\", "/").split(
					"/")[i], i++)
				if (!new File(path + "\\" + file.getAbsolutePath().replace("\\", "/").split("/")[i]).exists() || !new File(path + "\\" + file.getAbsolutePath()
						.replace("\\", "/").split("/")[i]).isDirectory())
					new File(path + "\\" + file.getAbsolutePath().replace("\\", "/").split("/")[i]).mkdir();
			if (!file.exists())
				file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			writer.write(text);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void writeFile(File file, String text, String charset)
	{
		writeFile(file, text, Charset.forName(charset));
	}

	/**
	 * Writes text - 'text' to the file - 'file'
	 * 
	 * @param file
	 *            - the file to text writing
	 * @param text
	 *            - the text to writing to file
	 */
	public static void writeFile(File file, byte[] data)
	{
		try
		{
			String path = file.getAbsolutePath().replace("\\", "/").split("/")[0];
			for (int i = 1; i < file.getAbsolutePath().replace("\\", "/").split("/").length - 1; path += "\\" + file.getAbsolutePath().replace("\\", "/").split(
					"/")[i], i++)
				if (!new File(path + "\\" + file.getAbsolutePath().replace("\\", "/").split("/")[i]).exists() || !new File(path + "\\" + file.getAbsolutePath()
						.replace("\\", "/").split("/")[i]).isDirectory())
					new File(path + "\\" + file.getAbsolutePath().replace("\\", "/").split("/")[i]).mkdir();
			if (!file.exists())
				file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Removes file - 'file', if that file exists
	 * 
	 * @param file
	 *            - the file to removing
	 */
	public static void removeFile(File file)
	{
		if (file.exists())
		{
			if (file.isDirectory())
				for (File f : file.listFiles())
					removeFile(f);
			file.delete();
		}
	}

	/**
	 * Creates file - 'file'
	 * 
	 * @param file
	 *            - the file to creating
	 */
	public static boolean createFile(File file, boolean dir, boolean createDirs, boolean prinudi)
	{
		try
		{
			String path = file.getAbsolutePath().replace("\\", "/").split("/")[0];
			for (int i = 1; i < file.getAbsolutePath().replace("\\", "/").split("/").length - 1; path += "\\" + file.getAbsolutePath().replace("\\", "/").split(
					"/")[i], i++)
				if (createDirs)
					if (!createFile(new File(path), true, false, prinudi))
						return false;
			if (file.exists())
			{
				if (!prinudi)
					return false;
				if (file.isDirectory() && !dir || file.isFile() && dir)
					file.delete();
			}
			if (dir)
				file.mkdir();
			if (!dir)
				file.createNewFile();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public static byte[] readFile(File file)
	{
		if (file.exists())
			try
			{
				FileInputStream fis = new FileInputStream(file);
				byte[] bs = new byte[fis.available()];
				fis.read(bs);
				fis.close();
				return bs;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return null;
	}

	public static String readFile(File file, Charset charset)
	{
		if (file!=null&&file.exists()&&file.isFile())
			try
			{
				return String.join("\n", Files.readAllLines(Paths.get(file.toURI()), charset));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return null;
	}

	public static String readFile(File file, String charset)
	{
		return readFile(file, Charset.forName(charset));
	}
}
