package ru.alexanderdv.schooltester.main;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.StartPart.F;
import ru.alexanderdv.schooltester.main.StartPart.G;
import ru.alexanderdv.schooltester.main.developer.TestDevPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.utils.CrossWordGeneratorPart;
import ru.alexanderdv.schooltester.main.utils.ElectronicBooksPart;
import ru.alexanderdv.schooltester.main.utils.FunctionsWorkPart;
import ru.alexanderdv.schooltester.utilities.AccountPacket;
import ru.alexanderdv.schooltester.utilities.ByteUtils;
import ru.alexanderdv.schooltester.utilities.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.InfoPacket;
import ru.alexanderdv.schooltester.utilities.Logger;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.NetworkPacket;
import ru.alexanderdv.schooltester.utilities.NetworkUtils;
import ru.alexanderdv.schooltester.utilities.Packet;
import ru.alexanderdv.schooltester.utilities.SecurityUtils;
import ru.alexanderdv.schooltester.utilities.SystemUtils;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class Main extends Application
{
	public static final MessageSystem msgSys = new MessageSystem(SystemUtils.readFile(new File("language.cfg"), "cp1251"));
	static
	{
		if (!MessageSystem.getMessages().keySet().contains(msgSys.getLanguage()))
		{
			msgSys.setLanguage("en_uk");
			SystemUtils.writeFile(new File("language.cfg"), msgSys.getLanguage(), "cp1251");
		}
	}
	private static final boolean developmentMode = false;
	private static boolean fxWindowFrame;
	public static final String programName = "SchoolTester";
	public static final String programVersion = "5.8.0a";
	public static final String programAuthors = "AlexanderDV";
	public static final String program = programName + " v" + programVersion + " by " + programAuthors;
	private static StartPart startPart;
	private static AccountsPart accountsPart;
	private static ElectronicBooksPart electronicBooksPart;
	private static FunctionsWorkPart functionsWorkPart;
	private static TeachersTestsControlPart teachersTestsControlPart;
	private static TestDevPart testDevPart;
	private static CrossWordGeneratorPart crossWordGeneratorPart;
	private static int menuHeight = 24;

	/**
	 * The main method of program
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		File file = new File("LookInfo.data");
		if (file.exists())
			setFxWindowFrame((boolean) ByteUtils.byteArrayToObject(SystemUtils.readFile(file)));
		else setFxWindowFrame(true);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		instance = this;
		primaryStage.setOpacity(0);
		primaryStage.show();
		primaryStage.close();
		initParts();
		getStartPart().addOnHiding(e ->
		{
			getTestDevPart().min();
			getTeachersTestsControlPart().min();
			electronicBooksPart.min();
			functionsWorkPart.min();
			getAccountsPart().min();
		});
		updateAllLabels();
		openNeedfulParts();
		String request = connectToServer();
		if (request.equals("keyIsRight"))
		{
			getStartPart().open(primaryStage, AccountsPart.account.get(), Main.instance.socket);
			getStartPart().getStage().centerOnScreen();
			startHandling();
		}
		switch (request)
		{
			case "youAreInBlacklist":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog(null, null, msgSys.getMsg(request), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, isFxWindowFrame(),
						true);
				exit(ExitCodes.YouAreInBlacklist);
				break;
			case "keyIsBad":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog(null, null, msgSys.getMsg(request), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, isFxWindowFrame(),
						true);
				exit(ExitCodes.KeyIsBad);
				break;
			case "notVerified":
			case "verifyRequestSended":
				FXDialogsGenerator.showFXDialog(null, null, msgSys.getMsg(request), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, isFxWindowFrame(),
						true);
				SystemUtils.openUrlInBrowser(msgSys.getMsg("siteURL"));
				exit(ExitCodes.NotVerified);
				break;
			case "keyIsRight":
				FXDialogsGenerator.showFXDialog(getStartPart().getStage(), null, msgSys.getMsg(request), JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, isFxWindowFrame(), true);
				break;
		}

	}

	private void openNeedfulParts()
	{
		// AccountsPart.account.set(new Account(AccountType.Administrator, "dasfsd", "sdfsdf"));
		// accountsPart.getStage().show();
		// teachersTestsControlPart.getStage().show();
		// testDevPart.getStage().show();
		// electronicBooksPart.getStage().show();
		// functionsWorkPart.getStage().show();
		// crossWordGeneratorPart.getStage().show();
	}

	public static final InetAddress server = pickReservOrStandardByOnline("18.219.238.19", "94.181.44.135");

	public String connectToServer()
	{
		String requestStr = "notConnectedToServer", versionStr = null, urlStr = null;
		if (!developmentMode)
		{
			String key;
			boolean ver = false;
			File file = new File("program.data");
			double d = 0;
			if (!file.exists())
			{
				key = null;
				d = (double) Calendar.getInstance().getTimeInMillis() / (double) 1;
				for (; d < Calendar.getInstance().getTimeInMillis();)
					d *= 10;
			}
			else
			{
				byte[] b1 = null;
				try
				{
					FileInputStream fis = new FileInputStream(file);
					b1 = new byte[fis.available()];
					fis.read(b1);
					fis.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				G g = (G) ByteUtils.byteArrayToObject(SecurityUtils.crypt(((F) ByteUtils.byteArrayToObject(b1)).b, -23));
				key = (String) ByteUtils.byteArrayToObject(SecurityUtils.crypt(g.b, -g.key));
				d = (double) Calendar.getInstance().getTimeInMillis() / (double) g.key;
				ver = g.verified;
				for (; d < Calendar.getInstance().getTimeInMillis();)
					d *= 10;
			}
			try
			{
				DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) + 10000);
				socket.setSoTimeout(1000);

				NetworkUtils.sendData(socket, "checkUpdate", server, 21577, 14);

				String strRes = NetworkUtils.recieveData(socket, new DatagramPacket(new byte[0], 0), String.class, 13);
				if (strRes != null)
					if (strRes.contains("\n"))
					{
						versionStr = strRes.split("\n")[0];
						urlStr = strRes.split("\n")[1];
					}
				String dataStr = "";
				dataStr += "network.ip.local" + '|' + InetAddress.getLocalHost().getHostAddress() + '\n';
				dataStr += "network.name" + '|' + InetAddress.getLocalHost().getHostName() + '\n';
				dataStr += "network.mac" + '|' + macAddress + '\n';
				dataStr += "java.version" + '|' + System.getProperty("java.version") + '\n';
				dataStr += "java.vendor" + '|' + System.getProperty("java.vendor") + '\n';
				dataStr += "java.vendor.url" + '|' + System.getProperty("java.vendor.url") + '\n';
				dataStr += "java.home" + '|' + System.getProperty("java.home") + '\n';
				dataStr += "java.class.version" + '|' + System.getProperty("java.class.version") + '\n';
				dataStr += "java.class.path" + '|' + System.getProperty("java.class.path") + '\n';
				dataStr += "os.name" + '|' + System.getProperty("os.name") + '\n';
				dataStr += "os.arch" + '|' + System.getProperty("os.arch") + '\n';
				dataStr += "os.version" + '|' + System.getProperty("os.version") + '\n';
				dataStr += "user.name" + '|' + System.getProperty("user.name") + '\n';
				dataStr += "user.home" + '|' + System.getProperty("user.home") + '\n';
				dataStr += "user.dir" + '|' + System.getProperty("user.dir") + '\n';
				dataStr += "user.country" + '|' + System.getProperty("user.country") + '\n';
				dataStr += "user.timezone" + '|' + System.getProperty("user.timezone") + '\n';
				dataStr += "user.language" + '|' + System.getProperty("user.language") + '\n';
				dataStr += "program.name" + '|' + programName + '\n';
				dataStr += "program.version" + '|' + programVersion + '\n';
				dataStr += "program.authors" + '|' + programAuthors + '\n';
				if (key == null)
				{
					int[] hashes = new int[]
					{
							System.getProperty("user.name").hashCode(),
							System.getProperty("user.country").hashCode(),
							System.getProperty("os.arch").hashCode(),
							System.getProperty("os.name").hashCode(),
							System.getProperty("os.version").hashCode(),
							macAddress.hashCode(),
					};
					final int prime = 31;
					int result = 1;
					for (int i = 0; i < hashes.length; i++)
						result = prime * result + hashes[i];
					key = Integer.toHexString(result);
					NetworkUtils.sendData(socket, new Packet("registerComputer", dataStr, null, macAddress, null, programVersion, null, key), server, 21577,
							14);
				}
				else
				{
					dataStr += "key" + '|' + key;

					NetworkUtils.sendData(socket, new Packet("verifyComputer", dataStr, TeachersTestsControlPart.getFileTree(), macAddress, null,
							programVersion, null, key), server, 21577, 14);

					NetworkPacket netPack = NetworkUtils.recieveData(socket, new DatagramPacket(new byte[0], 0), NetworkPacket.class, 13);
					if (netPack instanceof Packet)
					{
						Packet responsePacket = (Packet) netPack;
						if (responsePacket.getRequest().equals("keyIsRight"))
							ver = true;
						requestStr = responsePacket.getRequest();
					}
				}
				socket.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				G g2 = new G();
				g2.b = SecurityUtils.crypt(ByteUtils.objectToByteArray(key), (int) d);
				g2.key = (int) d;
				g2.verified = ver;
				FileOutputStream fos = new FileOutputStream(file);
				F f2 = new F();
				f2.b = SecurityUtils.crypt(ByteUtils.objectToByteArray(g2), 23);
				fos.write(ByteUtils.objectToByteArray(f2));
				fos.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if (!programVersion.equals(versionStr))
		{
			FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg("updateMsg") + versionStr, JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION, isFxWindowFrame(), true);
			SystemUtils.openUrlInBrowser(urlStr);
		}
		return requestStr;
	}

	private static InetAddress pickReservOrStandardByOnline(String standardServer, String reservServer)
	{
		InetAddress standardServerAddress, reservServerAddress;
		try
		{
			standardServerAddress = InetAddress.getByName(standardServer);
			reservServerAddress = InetAddress.getByName(reservServer);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Main.exit(ExitCodes.ServerNotExists);
			return null;
		}
		try
		{

			DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) + 10000);
			socket.setSoTimeout(500);
			NetworkUtils.sendData(socket, new NetworkPacket("checkOnline", macAddress, null), reservServerAddress, 21577, 14);
			NetworkPacket netPack = NetworkUtils.recieveData(socket, new DatagramPacket(new byte[0], 0), NetworkPacket.class, 13);
			if (netPack.getClass().equals(NetworkPacket.class))
				return reservServerAddress;
			socket.close();
		}
		catch (SocketTimeoutException e)
		{
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return standardServerAddress;
	}

	public static void exit(ExitCodes exitCode)
	{
		Logger.saveLogs();
		System.exit(exitCode.getCode());
	}

	private void initParts()
	{
		setStartPart(new StartPart(null, getClass().getResource("/StartPartLook.fxml")));
		setAccountsPart(new AccountsPart(null, getClass().getResource("/AccountPart.fxml")));
		setTeachersTestsControlPart(new TeachersTestsControlPart(null, getClass().getResource("/TestsControlPart.fxml")));
		{
			AnchorPane pane = new AnchorPane();
			pane.setPrefSize(500, 800);
			setTestDevPart(new TestDevPart(null, pane));
		}
		electronicBooksPart = new ElectronicBooksPart(null, getClass().getResource("/ElectronicBooksPart.fxml"));
		functionsWorkPart = new FunctionsWorkPart(null, getClass().getResource("/FunctionsWorkPart.fxml"));
		setCrossWordGeneratorPart(new CrossWordGeneratorPart(null, getClass().getResource("/CrossWordGenerator.fxml")));

		getStartPart().focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		getAccountsPart().focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		getTeachersTestsControlPart().focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		crossWordGeneratorPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		electronicBooksPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		functionsWorkPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
	}

	private static ArrayList<NetworkPacket> requests = new ArrayList<NetworkPacket>();

	int port = new Random().nextInt(50000);
	Thread socketThread;

	public synchronized void addRequest(NetworkPacket packet)
	{
		requests.add(packet);
	}

	public DatagramSocket socket;

	@SuppressWarnings("unused")
	private String replaceAll(String s, String replacement, String... regexes)
	{
		for (String regex : regexes)
			s = s.replace(regex, replacement);
		return s;
	}

	private String replaceAll(String s, String replacement, Iterable<String> regexes)
	{
		for (String regex : regexes)
			s = s.replace(regex, replacement);
		return s;
	}

	public synchronized void startHandling()
	{
		try
		{
			socketThread = new Thread(() ->
			{
				try
				{
					socket = new DatagramSocket(port);
					while (true)
						try
						{
							int size = 8000;
							String ttt;
							if ((ttt = rSymFE(replaceAll(getLogsNames(), "", l).replace(",,", ","), ",")) != null && !ttt.equals(""))
								for (String s = ttt; s != null && s.length() > 0; s = s.substring(Math.min(s.length(), size)).substring(0, Math.min(s.substring(
										Math.min(s.length(), size)).length(), Math.max(0, s.substring(0, Math.min(s.length(), size)).lastIndexOf(",")))))
								{
									String st = s.substring(0, Math.min(s.length(), size));
									addRequest(new InfoPacket("checkLogsList", macAddress, "", st.substring(0, st.contains(",") ? st.lastIndexOf(",")
											: st.length()), null, false));
								}
							if ((ttt = rSymFE(replaceAll(getResultsNames(), "", r).replace(",,", ","), ",")) != null && !ttt.equals(""))
								for (String s = replaceAll(getResultsNames(), "", r); s != null && s.length() > 0; s = s.substring(Math.min(s.length(), size))
										.substring(0, Math.min(s.substring(Math.min(s.length(), size)).length(), Math.max(0, s.substring(0, Math.min(s.length(),
												size)).lastIndexOf(",")))))
								{
									String st = s.substring(0, Math.min(s.length(), size));
									addRequest(new InfoPacket("checkResultsList", macAddress, "", st.substring(0, st.contains(",") ? st.lastIndexOf(",")
											: st.length()), null, false));
								}
							if ((ttt = rSymFE(replaceAll(getTestsNames(), "", t).replace(",,", ","), ",")) != null && !ttt.equals(""))
								for (String s = replaceAll(getTestsNames(), "", t); s != null && s.length() > 0; s = s.substring(Math.min(s.length(), size))
										.substring(0, Math.min(s.substring(Math.min(s.length(), size)).length(), Math.max(0, s.substring(0, Math.min(s.length(),
												size)).lastIndexOf(",")))))
								{
									String st = s.substring(0, Math.min(s.length(), size));
									addRequest(new InfoPacket("checkTestsList", macAddress, "", st.substring(0, st.contains(",") ? st.lastIndexOf(",")
											: st.length()), null, false));
								}
							for (NetworkPacket packet : requests.toArray(new NetworkPacket[0]))
								NetworkUtils.sendData(socket, packet, server, 21577, 14);
							requests.clear();
							socket.setSoTimeout(500);
							NetworkPacket packet = NetworkUtils.recieveData(socket, NetworkPacket.class, 13);
							if (packet instanceof AccountPacket)
								Platform.runLater(() -> AccountsPart.instance.handleAccountRequest((AccountPacket) packet));
							else if (packet instanceof InfoPacket)
							{
								if (((InfoPacket) packet).getInfo2() != null)
									switch (packet.getRequest())
									{
										case "logsList":
											for (String s = SystemUtils.readFile(new File("CLogs/" + ((InfoPacket) packet).getInfo2()), "cp1251"); s != null
													&& s.length() > 0; s = s.substring(Math.min(s.length(), size)))
												addRequest(new InfoPacket("addToLogsList", macAddress, "", s.substring(0, Math.min(s.length(), size)),
														((InfoPacket) packet).getInfo2(), s.length() <= size));
											break;
										case "resultsList":
											for (String s = SystemUtils.readFile(new File("Results/" + ((InfoPacket) packet).getInfo2()), "cp1251"); s != null
													&& s.length() > 0; s = s.substring(Math.min(s.length(), size)))
												addRequest(new InfoPacket("addToResultsList", macAddress, "", s.substring(0, Math.min(s.length(), size)),
														((InfoPacket) packet).getInfo2(), s.length() <= size));
											break;
										case "testsList":
											for (String s = SystemUtils.readFile(new File("Tests/" + ((InfoPacket) packet).getInfo2().replace(".test", "") + "/"
													+ ((InfoPacket) packet).getInfo2()), "cp1251"); s != null && s.length() > 0; s = s.substring(Math.min(s
															.length(), size)))
												addRequest(new InfoPacket("addToTestsList", macAddress, "", s.substring(0, Math.min(s.length(), size)),
														((InfoPacket) packet).getInfo2(), s.length() <= size));
											break;
										case "log":
										case "logs":
											for (String s : ((InfoPacket) packet).getInfo2().split(","))
												if (!l.contains(s))
													l.add(s);
											break;
										case "result":
										case "results":
											for (String s : ((InfoPacket) packet).getInfo2().split(","))
												if (!r.contains(s))
													r.add(s);
											break;
										case "test":
										case "tests":
											for (String s : ((InfoPacket) packet).getInfo2().split(","))
												if (!t.contains(s))
													t.add(s);
											break;
									}
							}
						}
						catch (SocketTimeoutException e)
						{
						}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			});
			socketThread.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private String rSymFE(String string, String toReplace)
	{
		if (string != null)
			if (string.endsWith(toReplace))
				return string.substring(0, string.length() - toReplace.length());
		return string;
	}

	ArrayList<String> l = new ArrayList<String>(), r = new ArrayList<String>(), t = new ArrayList<String>();

	private String getTestsNames()
	{
		String s = "";
		for (File f : new File("Tests").listFiles())
			for (String f2 : f.list())
				if (f2.startsWith(f.getName()))
					s += f2 + ",";
		return s;
	}

	private String getResultsNames()
	{
		return new File("Results").exists() ? String.join(",", new File("Results").list()) : "";
	}

	private String getLogsNames()
	{
		return new File("CLogs").exists() ? String.join(",", new File("CLogs").list()) : "";
	}

	public static final String macAddress = getMacAddress();

	private static String getMacAddress()
	{
		try
		{
			NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++)
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void updateAllLabels()
	{
		SystemUtils.writeFile(new File("language.cfg"), msgSys.getLanguage(), "cp1251");
		for (MenuItem item : getStartPart().getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);
		for (MenuItem item : getAccountsPart().getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);
		for (MenuItem item : getTeachersTestsControlPart().getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);
		for (MenuItem item : getTestDevPart().getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);
		for (MenuItem item : electronicBooksPart.getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);
		for (MenuItem item : functionsWorkPart.getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);
		for (MenuItem item : getCrossWordGeneratorPart().getLanguage().getItems())
			if (item instanceof RadioMenuItem)
				((RadioMenuItem) item).setSelected(false);

		switch (msgSys.getLanguage())
		{
			case "ru_ru":
				getStartPart().getLanguageRU().setSelected(true);
				getAccountsPart().getLanguageRU().setSelected(true);
				getTestDevPart().getLanguageRU().setSelected(true);
				getTeachersTestsControlPart().getLanguageRU().setSelected(true);
				electronicBooksPart.getLanguageRU().setSelected(true);
				functionsWorkPart.getLanguageRU().setSelected(true);
				getCrossWordGeneratorPart().getLanguageRU().setSelected(true);
				break;

			case "en_uk":
				getStartPart().getLanguageEN().setSelected(true);
				getAccountsPart().getLanguageEN().setSelected(true);
				getTestDevPart().getLanguageEN().setSelected(true);
				getTeachersTestsControlPart().getLanguageEN().setSelected(true);
				electronicBooksPart.getLanguageEN().setSelected(true);
				functionsWorkPart.getLanguageEN().setSelected(true);
				getCrossWordGeneratorPart().getLanguageEN().setSelected(true);
				break;
		}
		getStartPart().updateLabelsInPart();
		getAccountsPart().updateLabelsInPart();
		getTestDevPart().updateLabelsInPart();
		getTeachersTestsControlPart().updateLabelsInPart();
		electronicBooksPart.updateLabelsInPart();
		functionsWorkPart.updateLabelsInPart();
		getCrossWordGeneratorPart().updateLabelsInPart();
	}

	public void changeFXWindowFrameState(boolean b)
	{
		setFxWindowFrame(b);
		SystemUtils.writeFile(new File("LookInfo.data"), ByteUtils.objectToByteArray(isFxWindowFrame()));
		FXDialogsGenerator.showFXDialog(null, null, msgSys.getMsg("fxWindowStateChanged"), 0, 0, b, true);
	}

	public static Main instance;

	@SuppressWarnings("unused")
	private void prepareToExit()
	{
		getAccountsPart().actionPerformed(new ActionEvent(instance, 0, "prepareToExit"));
	}

	public void hideAll()
	{
		getAccountsPart().hideToTime();
		getCrossWordGeneratorPart().hideToTime();
		electronicBooksPart.hideToTime();
		getTeachersTestsControlPart().hideToTime();
		getStartPart().hideToTime();
		getTestDevPart().hideToTime();
	}

	public void showAllHided()
	{
		getAccountsPart().showIfHided();
		getCrossWordGeneratorPart().showIfHided();
		electronicBooksPart.showIfHided();
		getTeachersTestsControlPart().showIfHided();
		getStartPart().showIfHided();
		getTestDevPart().showIfHided();
	}

	public static boolean isFxWindowFrame()
	{
		return fxWindowFrame;
	}

	public static void setFxWindowFrame(boolean fxWindowFrame)
	{
		Main.fxWindowFrame = fxWindowFrame;
	}

	public static int getMenuHeight()
	{
		return menuHeight;
	}

	public static void setMenuHeight(int menuHeight)
	{
		Main.menuHeight = menuHeight;
	}

	public static TeachersTestsControlPart getTeachersTestsControlPart()
	{
		return teachersTestsControlPart;
	}

	public static void setTeachersTestsControlPart(TeachersTestsControlPart teachersTestsControlPart)
	{
		Main.teachersTestsControlPart = teachersTestsControlPart;
	}

	public static TestDevPart getTestDevPart()
	{
		return testDevPart;
	}

	public static void setTestDevPart(TestDevPart testDevPart)
	{
		Main.testDevPart = testDevPart;
	}

	public static CrossWordGeneratorPart getCrossWordGeneratorPart()
	{
		return crossWordGeneratorPart;
	}

	public static void setCrossWordGeneratorPart(CrossWordGeneratorPart crossWordGeneratorPart)
	{
		Main.crossWordGeneratorPart = crossWordGeneratorPart;
	}

	public static StartPart getStartPart()
	{
		return startPart;
	}

	public static void setStartPart(StartPart startPart)
	{
		Main.startPart = startPart;
	}

	public static AccountsPart getAccountsPart()
	{
		return accountsPart;
	}

	public static void setAccountsPart(AccountsPart accountsPart)
	{
		Main.accountsPart = accountsPart;
	}
}
