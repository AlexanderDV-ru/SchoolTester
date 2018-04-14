package ru.alexanderdv.schooltester.main;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.StartPart.F;
import ru.alexanderdv.schooltester.main.StartPart.G;
import ru.alexanderdv.schooltester.main.developer.TestDevPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.utilities.InitMarketPart.SearchType;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart.ButtonWithWindow;
import ru.alexanderdv.schooltester.main.utilities.chemistry.ChemicalCompoundsDescriptionPart;
import ru.alexanderdv.schooltester.main.utilities.math.CalculatorPart;
import ru.alexanderdv.schooltester.main.utilities.math.FunctionsWorkPart;
import ru.alexanderdv.schooltester.main.utilities.othersubjects.CrossWordGeneratorPart;
import ru.alexanderdv.schooltester.main.utilities.othersubjects.ElectronicBooksPart;
import ru.alexanderdv.schooltester.utilities.ByteUtils;
import ru.alexanderdv.schooltester.utilities.Logger;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.NetworkUtils;
import ru.alexanderdv.schooltester.utilities.SecurityUtils;
import ru.alexanderdv.schooltester.utilities.Subject;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.FXWindow;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.AccountPacket;
import ru.alexanderdv.schooltester.utilities.network.BytePacket;
import ru.alexanderdv.schooltester.utilities.network.ConnectionQualityChecker;
import ru.alexanderdv.schooltester.utilities.network.InfoPacket;
import ru.alexanderdv.schooltester.utilities.network.NetworkPacket;
import ru.alexanderdv.schooltester.utilities.network.Packet;
import ru.alexanderdv.schooltester.utilities.network.SearchInMarketPacket;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.8a
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
	public static final String programVersion = "5.9.8a";
	public static final String programAuthors = "AlexanderDV";
	public static final String program = programName + " v" + programVersion + " by " + programAuthors;
	private static MarketPart marketPart;
	private static StartPart startPart;
	private static AccountsPart accountsPart;
	private static ElectronicBooksPart electronicBooksPart;
	private static FunctionsWorkPart functionsWorkPart;
	private static TeachersTestsControlPart teachersTestsControlPart;
	private static TestDevPart testDevPart;
	private static CrossWordGeneratorPart crossWordGeneratorPart;
	private static ChemicalCompoundsDescriptionPart chemicalCompoundsDescriptionPart;
	private static CalculatorPart calculatorPart;
	private static FXWindow[] parts;

	private static int menuHeight = 24;
	private static ArrayList<SubjectUtilitiesPart> subjectUtilitiesParts = new ArrayList<SubjectUtilitiesPart>();

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
			getStartPart().open(primaryStage);
			getStartPart().getStage().centerOnScreen();
			getAccountsPart().open(getStartPart().getStage());
			//getStartPart().hide();
			getAccountsPart().addOnCloseRequest(e -> getStartPart().open((Stage) null));
			startHandling();
		}
		boolean b=true;
		if(b)
		return;
		switch (request)
		{
			case "youAreInBlacklist":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null, msgSys.getMsg(request), JOptionPane.ERROR_MESSAGE, null,
						isFxWindowFrame(), true);
				exit(ExitCodes.YouAreInBlacklist);
				break;
			case "keyIsBad":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null, msgSys.getMsg(request), JOptionPane.ERROR_MESSAGE, null,
						isFxWindowFrame(), true);
				exit(ExitCodes.KeyIsBad);
				break;
			case "notVerified":
			case "verifyRequestSended":
				FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null, msgSys.getMsg(request), JOptionPane.ERROR_MESSAGE, null,
						isFxWindowFrame(), true);
				SystemUtils.openUrl(msgSys.getMsg("siteURL"));
				exit(ExitCodes.NotVerified);
				break;
			case "keyIsRight":
				FXDialogsGenerator.showFXDialog(getStartPart().getStage(), (Stage) null, msgSys.getMsg(request), JOptionPane.INFORMATION_MESSAGE,
						null, isFxWindowFrame(), true);
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
					G g = (G) ByteUtils.byteArrayToObject(SecurityUtils.crypt(((F) ByteUtils.byteArrayToObject(b1)).b, -23));
					key = (String) ByteUtils.byteArrayToObject(SecurityUtils.crypt(g.b, -g.key));
					d = (double) Calendar.getInstance().getTimeInMillis() / (double) g.key;
					ver = g.verified;
					for (; d < Calendar.getInstance().getTimeInMillis();)
						d *= 10;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					SystemUtils.removeFile(file);
					exit(ExitCodes.UnknownError);
					return null;
				}
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
			{
				if (Desktop.isDesktopSupported())
				{
					try
					{
						DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) + 10000);
						NetworkUtils.sendData(socket, new NetworkPacket("startLoading", macAddress, ""), server, 21577, 14);
						int fullLength = NetworkUtils.recieveData(socket, new DatagramPacket(new byte[0], 0), BytePacket.class, 13).getFullLength();
						int allBytesLength = 0;
						byte[] bytes = new byte[fullLength];
						while (allBytesLength < fullLength)
						{
							NetworkUtils.sendData(socket, new InfoPacket("nextBytes", macAddress, "", allBytesLength + "", "", false), server, 21577, 14);
							BytePacket bytesPack = NetworkUtils.recieveData(socket, new DatagramPacket(new byte[0], 0), BytePacket.class, 13);
							allBytesLength += bytesPack.getBytes().length;
							System.out.println(allBytesLength);
							for (int j = 0; j < bytesPack.getBytes().length; j++)
								bytes[bytesPack.getOffset() + j] = bytesPack.getBytes()[j];
						}
						SystemUtils.writeFile(new File("setup.exe"), bytes);
						socket.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					Desktop desktop = Desktop.getDesktop();
					File setupManual = new File(new File("setup.exe").getAbsolutePath());
					boolean canOpen = desktop.isSupported(Desktop.Action.OPEN);
					if (canOpen && System.getProperty("os.name").equalsIgnoreCase("windows"))
						try
						{
							File setupVbs = new File(new File("startSetup.vbs").getAbsolutePath());
							SystemUtils.writeFile(setupVbs, "Option Explicit\r\n" + "Dim wsh\r\n" + "Set wsh = WScript.CreateObject(\"WScript.Shell\")\r\n"
									+ "wsh.Run(\"" + setupManual.getName() + "\")\r\n" + "Set wsh = Nothing", "cp1251");
							desktop.open(setupVbs);
							SystemUtils.removeFile(setupVbs);
						}
						catch (IOException ioe)
						{
							ioe.printStackTrace();
						}
					else
					{
						FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null, msgSys.getMsg("openSetupFile").replace("%1", canOpen ? setupManual.getName()
								: setupManual.getAbsolutePath()), JOptionPane.INFORMATION_MESSAGE, null, isFxWindowFrame(), true);
						if (canOpen)
							try
							{
								desktop.open(setupManual.getParentFile());
							}
							catch (IOException ioe)
							{
								ioe.printStackTrace();
							}
					}
					exit(ExitCodes.Update);
				}
			}
			FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null, msgSys.getMsg("updateMsg") + versionStr, JOptionPane.INFORMATION_MESSAGE,
					null, isFxWindowFrame(), true);
			SystemUtils.openUrl(urlStr);
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
		marketPart = new MarketPart(getClass().getResource("/MarketPart.fxml"), true);
		Pane p = new Pane();
		p.setPrefSize(1000, 700);
		testDevPart = new TestDevPart(p, true);
		electronicBooksPart = new ElectronicBooksPart(getClass().getResource("/ElectronicBooksPart.fxml"), true);
		functionsWorkPart = new FunctionsWorkPart(getClass().getResource("/FunctionsWorkPart.fxml"), true);
		crossWordGeneratorPart = new CrossWordGeneratorPart(getClass().getResource("/CrossWordGenerator.fxml"), false);
		calculatorPart = new CalculatorPart(getClass().getResource("/Calculator.fxml"), true);
		chemicalCompoundsDescriptionPart = new ChemicalCompoundsDescriptionPart(getClass().getResource("/ChemicalCompoundsDescriptor.fxml"), false);
		subjectUtilitiesParts.add(new SubjectUtilitiesPart(Subject.Math, false, new ButtonWithWindow(new Button("Calc"), calculatorPart), new ButtonWithWindow(
				new Button("Functions"), functionsWorkPart)));
		subjectUtilitiesParts.add(new SubjectUtilitiesPart(Subject.Chemistry, false, new ButtonWithWindow(new Button("Chemical compounds descriptor"),
				chemicalCompoundsDescriptionPart)));
		subjectUtilitiesParts.add(new SubjectUtilitiesPart(Subject.OtherSubject, false, new ButtonWithWindow(new Button("Electronic book"),
				electronicBooksPart), new ButtonWithWindow(new Button("Crossword generator"), crossWordGeneratorPart)));

		accountsPart = (new AccountsPart(null, getClass().getResource("/AccountPart.fxml"), true));
		teachersTestsControlPart = (new TeachersTestsControlPart(getClass().getResource("/TestsControlPart.fxml"), false));
		{
			AnchorPane pane = new AnchorPane();
			pane.setPrefSize(500, 800);
			setTestDevPart(new TestDevPart(pane, false));
		}
		startPart = (new StartPart(null, getClass().getResource("/StartPartLook.fxml"), false));
		parts = new FXWindow[]
		{
				marketPart,
				startPart,
				accountsPart,
				electronicBooksPart,
				functionsWorkPart,
				teachersTestsControlPart,
				testDevPart,
				crossWordGeneratorPart,
				chemicalCompoundsDescriptionPart,
				calculatorPart
		};

		startPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		accountsPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		teachersTestsControlPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
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
		int soTimeout = 1000;
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
							addRequest(new ConnectionQualityChecker("checkTestsList", macAddress, "", Calendar.getInstance().getTimeInMillis()));
							for (NetworkPacket packet : requests.toArray(new NetworkPacket[0]))
								NetworkUtils.sendData(socket, packet, server, 21577, 14);
							requests.clear();
							socket.setSoTimeout(soTimeout);
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
							else if (packet instanceof ConnectionQualityChecker)
								Platform.runLater(() -> InitStartPart.instance.serverConnectionQualityProgressbar.setProgress((soTimeout - (Calendar
										.getInstance().getTimeInMillis() - ((ConnectionQualityChecker) packet).getTime())) / 1000d));
							else if (packet instanceof SearchInMarketPacket)
								MarketPart.instance.setSearchResult((SearchInMarketPacket) packet);
							switch (packet.getRequest())
							{
								case "addedToMarket":
									System.out.println("addedToMarket");
									break;
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

	private MenuItem getWithText(String text, ObservableList<MenuItem> items)
	{
		for (MenuItem item : items)
			if (item.getText().contains(text))
				return item;
		return null;
	}

	public void updateAllLabels()
	{
		SystemUtils.writeFile(new File("language.cfg"), msgSys.getLanguage(), "cp1251");
		for (FXWindow window : parts)
			for (MenuItem item : window.getLanguage().getItems())
				if (item instanceof RadioMenuItem)
					((RadioMenuItem) item).setSelected(false);
		for (SubjectUtilitiesPart window : subjectUtilitiesParts)
		{
			for (MenuItem item : window.getLanguage().getItems())
				if (item instanceof RadioMenuItem)
					((RadioMenuItem) item).setSelected(false);
			for (ButtonWithWindow btnWithWindow : window.getButtonsAndWindows())
				for (MenuItem item : btnWithWindow.getWindow().getLanguage().getItems())
					if (item instanceof RadioMenuItem)
						((RadioMenuItem) item).setSelected(false);
		}
		for (FXWindow window : parts)
			((RadioMenuItem) getWithText(msgSys.getLanguage(), window.getLanguage().getItems())).setSelected(true);
		for (SubjectUtilitiesPart window : subjectUtilitiesParts)
		{
			((RadioMenuItem) getWithText(msgSys.getLanguage(), window.getLanguage().getItems())).setSelected(true);
			for (ButtonWithWindow btnWithWindow : window.getButtonsAndWindows())
				((RadioMenuItem) getWithText(msgSys.getLanguage(), btnWithWindow.getWindow().getLanguage().getItems())).setSelected(true);
		}

		for (FXWindow window : parts)
			window.updateLabelsInPart();
		for (SubjectUtilitiesPart window : subjectUtilitiesParts)
		{
			window.updateLabelsInPart();
			for (ButtonWithWindow btnWithWindow : window.getButtonsAndWindows())
				btnWithWindow.getWindow().updateLabelsInPart();
		}
	}

	public void changeFXWindowFrameState(boolean b)
	{
		setFxWindowFrame(b);
		SystemUtils.writeFile(new File("LookInfo.data"), ByteUtils.objectToByteArray(isFxWindowFrame()));
		FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null, msgSys.getMsg("fxWindowStateChanged"), 0, null, b, true);
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

	/**
	 * @return the electronicBooksPart
	 */
	public static ElectronicBooksPart getElectronicBooksPart()
	{
		return electronicBooksPart;
	}

	/**
	 * @param electronicBooksPart
	 *            the electronicBooksPart to set
	 */
	public static void setElectronicBooksPart(ElectronicBooksPart electronicBooksPart)
	{
		Main.electronicBooksPart = electronicBooksPart;
	}

	/**
	 * @return the functionsWorkPart
	 */
	public static FunctionsWorkPart getFunctionsWorkPart()
	{
		return functionsWorkPart;
	}

	/**
	 * @param functionsWorkPart
	 *            the functionsWorkPart to set
	 */
	public static void setFunctionsWorkPart(FunctionsWorkPart functionsWorkPart)
	{
		Main.functionsWorkPart = functionsWorkPart;
	}

	/**
	 * @return the chemicalCompoundsDescriptionPart
	 */
	public static ChemicalCompoundsDescriptionPart getChemicalCompoundsDescriptionPart()
	{
		return chemicalCompoundsDescriptionPart;
	}

	/**
	 * @param chemicalCompoundsDescriptionPart
	 *            the chemicalCompoundsDescriptionPart to set
	 */
	public static void setChemicalCompoundsDescriptionPart(ChemicalCompoundsDescriptionPart chemicalCompoundsDescriptionPart)
	{
		Main.chemicalCompoundsDescriptionPart = chemicalCompoundsDescriptionPart;
	}

	/**
	 * @return the calculatorPart
	 */
	public static CalculatorPart getCalculatorPart()
	{
		return calculatorPart;
	}

	/**
	 * @param calculatorPart
	 *            the calculatorPart to set
	 */
	public static void setCalculatorPart(CalculatorPart calculatorPart)
	{
		Main.calculatorPart = calculatorPart;
	}

	/**
	 * @return the subjectUtilitiesParts
	 */
	public static ArrayList<SubjectUtilitiesPart> getSubjectUtilitiesParts()
	{
		return subjectUtilitiesParts;
	}

	/**
	 * @param subjectUtilitiesParts
	 *            the subjectUtilitiesParts to set
	 */
	public static void setSubjectUtilitiesParts(ArrayList<SubjectUtilitiesPart> subjectUtilitiesParts)
	{
		Main.subjectUtilitiesParts = subjectUtilitiesParts;
	}

	public void addSearch(String search, int page, SearchType searchType)
	{
		addRequest(new SearchInMarketPacket(search, macAddress, "", searchType, page));
	}

	public static ProtectedFXWindow getMarketPart()
	{
		return marketPart;
	}
}
