package ru.alexanderdv.schooltester.main;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Timer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.alexanderdv.schooltester.main.student.StudentsTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TestDevPart;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart.ButtonWithWindow;
import ru.alexanderdv.schooltester.main.utilities.chemistry.ChemicalCompoundsDescriptionPart;
import ru.alexanderdv.schooltester.main.utilities.math.CalculatorPart;
import ru.alexanderdv.schooltester.main.utilities.math.ExpressionGeneratorPart;
import ru.alexanderdv.schooltester.main.utilities.math.FunctionsWorkPart;
import ru.alexanderdv.schooltester.main.utilities.math.UnitConverterPart;
import ru.alexanderdv.schooltester.main.utilities.othersubjects.CrossWordGeneratorPart;
import ru.alexanderdv.schooltester.main.utilities.othersubjects.ElectronicBooksPart;
import ru.alexanderdv.schooltester.utilities.Logger;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.enums.SearchType;
import ru.alexanderdv.schooltester.utilities.enums.Subject;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.FXWindow;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.fx.SchoolTesterFXWindow;
import ru.alexanderdv.schooltester.utilities.network.AccountPacket;
import ru.alexanderdv.schooltester.utilities.network.ConnectionQualityChecker;
import ru.alexanderdv.schooltester.utilities.network.FilePacket;
import ru.alexanderdv.schooltester.utilities.network.NetworkPacket;
import ru.alexanderdv.schooltester.utilities.network.ResultSendPacket;
import ru.alexanderdv.schooltester.utilities.network.SearchInMarketPacket;
import ru.alexanderdv.schooltester.utilities.network.StudentOnlineTestPacket;
import ru.alexanderdv.schooltester.utilities.network.TCPClient;
import ru.alexanderdv.schooltester.utilities.network.TestingTaskPacket;
import ru.alexanderdv.schooltester.utilities.network.VerifyPacket;
import ru.alexanderdv.schooltester.utilities.types.Account.AccountType;
import ru.alexanderdv.simpleutilities.ByteUtils;
import ru.alexanderdv.simpleutilities.Container;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.2.0a
 */
public final class Main extends Application
{
	public static final String resourcesPackage = Main.class.getPackage().getName().split("\\.")[0] + "."
			+ Main.class.getPackage().getName().split("\\.")[1] + "."
			+ Main.class.getPackage().getName().split("\\.")[2] + "." + "resources";
	public static final String resourcesPath = "/" + resourcesPackage.replace(".", "/");
	public static final MessageSystem msgSys = new MessageSystem(
			SystemUtils.readFile(new File("language.cfg"), "UTF-8"), resourcesPackage + ".languages");
	static
	{
		if (!msgSys.getMessages().keySet().contains(msgSys.getLanguage()))
		{
			msgSys.setLanguage("en_uk");
			SystemUtils.writeFile(new File("language.cfg"), "en_uk", "UTF-8");
		}
	}
	private static final boolean debug = true;
	public static final String programName = "SchoolTester";
	public static final String programVersion = "6.2.0a";
	public static final String programAuthors = "AlexanderDV";
	public static final String program = programName + " v" + programVersion + " by " + programAuthors;
	public static final Image icon = loadImage(Main.class.getResource(Main.resourcesPath + "/images" + "/Icon16x.png"));
	public static final int spaceBetweenComponents = 8;
	public static final int tabsSwitchHeight = 30;
	public static final int paneTitleHeight = 25;
	private static MarketPart marketPart;
	private static StartPart startPart;
	private static AccountsPart accountsPart;
	private static ElectronicBooksPart electronicBooksPart;
	//
	private static FunctionsWorkPart functionsWorkPart;
	private static CalculatorPart calculatorPart;
	private static UnitConverterPart unitConverterPart;
	private static ExpressionGeneratorPart expressionGeneratorPart;

	private static TeachersTestsControlPart teachersTestsControlPart;
	private static TestDevPart testDevPart;
	private static TestingPart testingPart;

	private static CrossWordGeneratorPart crossWordGeneratorPart;
	private static ChemicalCompoundsDescriptionPart chemicalCompoundsDescriptionPart;

	private static FXWindow[] parts;
	private static SchoolTesterFXWindow[] parts2;

	private static StudentsTestsControlPart studentsTestsControlPart;
	int uuuValue;
	private static int menuHeight = 24;
	private static ArrayList<SubjectUtilitiesPart> subjectUtilitiesParts = new ArrayList<SubjectUtilitiesPart>();

	public static final TCPClient client = pickReservOrStandardByOnline("schooltester.ru", "94.181.44.135");

	/**
	 * The main method of program
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		debug("Loading...");
		launch(args);
	}

	private static Image loadImage(URL resource)
	{
		try
		{
			return new Image(resource.openStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new WritableImage(16, 16);
		}
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		debug("FX loading...");
		instance = this;
		debug("Parts initialization...");
		testingPart = new TestingPart();
		teachersTestsControlPart = new TeachersTestsControlPart();
		testDevPart = new TestDevPart();

		studentsTestsControlPart = new StudentsTestsControlPart();

		marketPart = new MarketPart(true);

		electronicBooksPart = new ElectronicBooksPart(
				getClass().getResource(Main.resourcesPath + "/fxml" + "/ElectronicBooksPart.fxml"), true);
		crossWordGeneratorPart = new CrossWordGeneratorPart(
				getClass().getResource(Main.resourcesPath + "/fxml" + "/CrossWordGenerator.fxml"), false);

		functionsWorkPart = new FunctionsWorkPart(
				getClass().getResource(Main.resourcesPath + "/fxml" + "/FunctionsWorkPart.fxml"), true);
		calculatorPart = new CalculatorPart(getClass().getResource(Main.resourcesPath + "/fxml" + "/Calculator.fxml"),
				true);
		unitConverterPart = new UnitConverterPart(
				getClass().getResource(Main.resourcesPath + "/fxml" + "/UnitConverter.fxml"), true);
		expressionGeneratorPart = new ExpressionGeneratorPart();

		chemicalCompoundsDescriptionPart = new ChemicalCompoundsDescriptionPart();
		subjectUtilitiesParts.add(
				new SubjectUtilitiesPart(Subject.Math, false, new ButtonWithWindow(new Button("Calc"), calculatorPart),
						new ButtonWithWindow(new Button("Functions"), functionsWorkPart),
						new ButtonWithWindow(new Button("Converter"), unitConverterPart),
						new ButtonWithWindow(new Button("expressionGenerator"), expressionGeneratorPart)));
		subjectUtilitiesParts.add(new SubjectUtilitiesPart(Subject.Chemistry, false,
				new ButtonWithWindow(new Button("Chemical compounds descriptor"), chemicalCompoundsDescriptionPart)));
		subjectUtilitiesParts.add(new SubjectUtilitiesPart(Subject.OtherSubject, false,
				new ButtonWithWindow(new Button("Electronic book"), electronicBooksPart),
				new ButtonWithWindow(new Button("Crossword generator"), crossWordGeneratorPart)));

		accountsPart = (new AccountsPart(null,
				getClass().getResource(Main.resourcesPath + "/fxml" + "/AccountPart.fxml"), true));
		startPart = new StartPart(stage);

		parts = new FXWindow[] { marketPart, accountsPart, electronicBooksPart, functionsWorkPart,
				teachersTestsControlPart, testDevPart, crossWordGeneratorPart, chemicalCompoundsDescriptionPart,
				calculatorPart };

		parts2 = new SchoolTesterFXWindow[] { startPart, expressionGeneratorPart };

		startPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		accountsPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		teachersTestsControlPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		crossWordGeneratorPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		electronicBooksPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		functionsWorkPart.focusedProperty().addListener((ev, ev2, ev3) -> FXDialogsGenerator.focus());
		debug("Parts initializated!");
		debug("Listeners initialization...");
		getStartPart().addOnHiding(e ->
		{
			getTestDevPart().min();
			getTeachersTestsControlPart().min();
			electronicBooksPart.min();
			functionsWorkPart.min();
			getAccountsPart().min();
		});
		debug("Labels updating...");
		updateAllLabels();
		debug("Labels updated!");
		openNeedfulParts();
		debug("FX loaded!");
		startNetworking();
	}

	private void startNetworking()
	{
		debug("Server connect trying...");
		String request = connectToServer();
		debug("Server connect tryied!");
		debug("Dialog info showing...");
		switch (request)
		{
			case "youAreInBlacklist":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog((Stage) null, msgSys.getMsg(request), null, true);
				exit(ExitCodes.YouAreInBlacklist);
				break;
			case "keyIsBad":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog((Stage) null, msgSys.getMsg(request), null, true);
				exit(ExitCodes.KeyIsBad);
				break;
			case "notVerified":
			case "verifyRequestSended":
				FXDialogsGenerator.showFXDialog((Stage) null, msgSys.getMsg(request), null, true);
				SystemUtils.openUrl(msgSys.getMsg("siteURL"));
				exit(ExitCodes.NotVerified);
				break;
			case "keyIsRight":
				getAccountsPart().open((Stage) null);
				getAccountsPart().stage.centerOnScreen();
				getAccountsPart().addOnCloseRequest(e ->
				{
					if (!getStartPart().stage.isShowing())
					{
						getStartPart().open((Stage) null);
						getStartPart().stage.centerOnScreen();
					}
				});
				startHandling();
				FXDialogsGenerator.showFXDialog(getAccountsPart().stage, msgSys.getMsg(request), null, true);
				break;
		}
	}

	private void openNeedfulParts()
	{
		// AccountsPart.account.set(new Account(AccountType.Administrator, "dasfsd",
		// "sdfsdf"));
		// accountsPart.getStage().show();
		// teachersTestsControlPart.getStage().show();
		// testDevPart.getStage().show();
		// electronicBooksPart.getStage().show();
		// functionsWorkPart.getStage().show();
		// crossWordGeneratorPart.getStage().show();
	}

	public String connectToServer()
	{
		// /** Changable part ***/
		//
		// String requestStr = "notConnectedToServer", versionStr = null, urlStr = null;
		// if (!developmentMode)
		// {
		// String key;
		// boolean ver = false;
		// File file = new File("program.data");
		// double d = 0;
		// if (!file.exists())
		// {
		// key = null;
		// d = (double) Calendar.getInstance().getTimeInMillis() / (double) 1;
		// for (; d < Calendar.getInstance().getTimeInMillis();)
		// d *= 10;
		// }
		// else
		// {
		// byte[] b1 = null;
		// try
		// {
		// FileInputStream fis = new FileInputStream(file);
		// b1 = new byte[fis.available()];
		// fis.read(b1);
		// fis.close();
		// G g = (G) ByteUtils.byteArrayToObject(SecurityUtils.crypt(((F)
		// ByteUtils.byteArrayToObject(b1)).b, -23));
		// key = (String) ByteUtils.byteArrayToObject(SecurityUtils.crypt(g.b, -g.key));
		// d = (double) Calendar.getInstance().getTimeInMillis() / (double) g.key;
		// ver = g.verified;
		// for (; d < Calendar.getInstance().getTimeInMillis();)
		// d *= 10;
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// SystemUtils.removeFile(file);
		// exit(ExitCodes.UnknownError);
		// return null;
		// }
		// }
		// try
		// {
		// client = new TCPClient(server, 21577, e ->
		// {
		// });
		// client.send("checkUpdate");
		// String strRes = client.waitToRecieve(String.class);
		// if (strRes != null)
		// if (strRes.contains("\n"))
		// {
		// versionStr = strRes.split("\n")[0];
		// urlStr = strRes.split("\n")[1];
		// }
		// String dataStr = "";
		// dataStr += "network.ip.local" + '|' +
		// InetAddress.getLocalHost().getHostAddress() + '\n';
		// dataStr += "network.name" + '|' + InetAddress.getLocalHost().getHostName() +
		// '\n';
		// dataStr += "network.mac" + '|' + macAddress + '\n';
		// dataStr += "java.version" + '|' + System.getProperty("java.version") + '\n';
		// dataStr += "java.vendor" + '|' + System.getProperty("java.vendor") + '\n';
		// dataStr += "java.vendor.url" + '|' + System.getProperty("java.vendor.url") +
		// '\n';
		// dataStr += "java.home" + '|' + System.getProperty("java.home") + '\n';
		// dataStr += "java.class.version" + '|' +
		// System.getProperty("java.class.version") + '\n';
		// dataStr += "java.class.path" + '|' + System.getProperty("java.class.path") +
		// '\n';
		// dataStr += "os.name" + '|' + System.getProperty("os.name") + '\n';
		// dataStr += "os.arch" + '|' + System.getProperty("os.arch") + '\n';
		// dataStr += "os.version" + '|' + System.getProperty("os.version") + '\n';
		// dataStr += "user.name" + '|' + System.getProperty("user.name") + '\n';
		// dataStr += "user.home" + '|' + System.getProperty("user.home") + '\n';
		// dataStr += "user.dir" + '|' + System.getProperty("user.dir") + '\n';
		// dataStr += "user.country" + '|' + System.getProperty("user.country") + '\n';
		// dataStr += "user.timezone" + '|' + System.getProperty("user.timezone") +
		// '\n';
		// dataStr += "user.language" + '|' + System.getProperty("user.language") +
		// '\n';
		// dataStr += "program.name" + '|' + programName + '\n';
		// dataStr += "program.version" + '|' + programVersion + '\n';
		// dataStr += "program.authors" + '|' + programAuthors + '\n';
		// if (key == null)
		// {
		// int[] hashes = new int[]
		// {
		// System.getProperty("user.name").hashCode(),
		// System.getProperty("user.country").hashCode(),
		// System.getProperty("os.arch").hashCode(),
		// System.getProperty("os.name").hashCode(),
		// System.getProperty("os.version").hashCode(),
		// macAddress.hashCode(),
		// };
		// final int prime = 31;
		// int result = 1;
		// for (int i = 0; i < hashes.length; i++)
		// result = prime * result + hashes[i];
		// key = Integer.toHexString(result);
		// client.send(new Packet("registerComputer", dataStr, null, macAddress, null,
		// programVersion, null, key));
		// }
		// else
		// {
		// dataStr += "key" + '|' + key;
		//
		// client.send(new Packet("verifyComputer", dataStr,
		// TeachersTestsControlPart.getFileTree(), macAddress, null, programVersion,
		// null, key));
		//
		// NetworkPacket netPack = client.waitToRecieve(NetworkPacket.class);
		// if (netPack instanceof Packet)
		// {
		// Packet responsePacket = (Packet) netPack;
		// if (responsePacket.getRequest().equals("keyIsRight"))
		// ver = true;
		// requestStr = responsePacket.getRequest();
		// }
		// }
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// try
		// {
		// G g2 = new G();
		// g2.b = SecurityUtils.crypt(ByteUtils.objectToByteArray(key), (int) d);
		// g2.key = (int) d;
		// g2.verified = ver;
		// FileOutputStream fos = new FileOutputStream(file);
		// F f2 = new F();
		// f2.b = SecurityUtils.crypt(ByteUtils.objectToByteArray(g2), 23);
		// fos.write(ByteUtils.objectToByteArray(f2));
		// fos.close();
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// }
		// if (!programVersion.equals(versionStr))
		// {
		// {
		// if (Desktop.isDesktopSupported())
		// {
		// try
		// {
		// client.send(new NetworkPacket("loadUpdate", macAddress, ""));
		// SystemUtils.writeFile(new File("setup.exe"),
		// client.waitToRecieve(FilePacket.class).getBytes());
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		//
		// }
		// }
		// FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null,
		// msgSys.getMsg("updateMsg") + versionStr, JOptionPane.INFORMATION_MESSAGE,
		// null,
		// true);
		// SystemUtils.openUrl(urlStr);
		// }
		// return requestStr;

		String serverResponse;

		try
		{
			client.send("checkServerVersion");
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		String checkServerVersionResponse = client.waitToRecieve(String.class);
		boolean updateAvailible = !programVersion.equals(checkServerVersionResponse);
		if (updateAvailible)
		{
			try
			{
				client.send("getUpdateLink");
			}
			catch (SocketException e)
			{
				e.printStackTrace();
			}
			String getUpdateLinkResponse = client.waitToRecieve(String.class);
			try
			{
				client.send("getUpdateFile");
			}
			catch (SocketException e)
			{
				e.printStackTrace();
			}

			ProgressBar bar = new ProgressBar();
			bar.setPrefSize(100, 25);
			bar.setProgress(-1);
			FXDialogsGenerator.showFXDialog((Stage) null,
					msgSys.getMsg("updateLoadingMsg").replace("%1", checkServerVersionResponse), bar, false);

			new Thread(() ->
			{
				FilePacket updateFile = client.waitToRecieve(FilePacket.class);
				File setup = new File("Updates/SchoolTesterSetup_v" + checkServerVersionResponse + ".exe")
						.getAbsoluteFile();
				if (updateFile != null)
					SystemUtils.writeFile(setup, updateFile.getBytes());

				Platform.runLater(() ->
				{
					FXDialogsGenerator.closeLast();
					FXDialogsGenerator.showFXDialog((Stage) null,
							msgSys.getMsg("installUpdateMsg").replace("%1", checkServerVersionResponse), null, true);

					Desktop desktop = Desktop.getDesktop();
					boolean canOpen = desktop.isSupported(Desktop.Action.OPEN);
					if (canOpen)
					{
						try
						{
							Runtime.getRuntime().exec("cmd /c " + setup.getAbsolutePath() + "").waitFor();
						}
						catch (IOException e1)
						{
							e1.printStackTrace();
						}
						catch (InterruptedException e1)
						{
							e1.printStackTrace();
						}
						Button goToSite = new Button("Go to site");
						goToSite.setOnAction(e -> SystemUtils.openUrl(getUpdateLinkResponse));
						goToSite.setPrefSize(100, 25);
						FXDialogsGenerator.showFXDialog(startPart.getStage(), msgSys.getMsg("updatedMsg")
								.replace("%1", programVersion).replace("%2", checkServerVersionResponse), goToSite,
								true);
					}
					else
					{
						FXDialogsGenerator.showFXDialog(startPart.getStage(), msgSys.getMsg("openSetupFile")
								.replace("%1", canOpen ? setup.getName() : setup.getAbsolutePath()), null, true);
						if (canOpen)
							try
							{
								desktop.open(setup.getParentFile());
							}
							catch (IOException ioe)
							{
								ioe.printStackTrace();
							}
					}

					exit(ExitCodes.Update);
				});
			}).start();
			return serverResponse = "update";
		}
		else
		{
			BigInteger programID = loadProgramID();
			if (programID == null)
			{
				programID = createProgramID();
				saveProgramID(programID);
			}

			try
			{
				client.send(new VerifyPacket("verify", programID));
			}
			catch (SocketException e)
			{
				e.printStackTrace();
			}
			VerifyPacket verifyResponse = client.waitToRecieve(VerifyPacket.class);
			serverResponse = verifyResponse.getRequest();

			try
			{
				Process process = Runtime.getRuntime().exec(new String[] { "wmic", "bios", "get", "serialnumber" });
				process.getOutputStream().close();
				Scanner sc = new Scanner(process.getInputStream());
				for (; sc.hasNext();)
				{
					String property = sc.next();
					String serial = sc.next();
				}
				sc.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return serverResponse;
		}
	}

	private void saveProgramID(BigInteger programID)
	{
		// TODO Auto-generated method stub

	}

	private BigInteger loadProgramID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private BigInteger createProgramID()
	{
		BigInteger b = new BigInteger("0");
		long[] longs = new long[] { Calendar.getInstance().getTimeInMillis(), new Random().nextInt(100000000),
				System.getProperty("user.name").hashCode(), System.getProperty("os.name").hashCode(),
				System.getProperty("os.version").hashCode(), System.getProperty("os.arch").hashCode(), };
		for (int i = 0; i < longs.length; i++)
			if (longs[i] != 0)
				b = b.add(BigInteger.valueOf(i)
						.multiply(BigInteger.valueOf(Integer.MAX_VALUE).multiply(BigInteger.valueOf(longs[i]))));
		return b;
	}

	// public String connectToServer()
	// {
	// String requestStr = "notConnectedToServer", versionStr = null, urlStr = null;
	// if (!developmentMode)
	// {
	// String key;
	// boolean ver = false;
	// File file = new File("program.data");
	// double d = 0;
	// if (!file.exists())
	// {
	// key = null;
	// d = (double) Calendar.getInstance().getTimeInMillis() / (double) 1;
	// for (; d < Calendar.getInstance().getTimeInMillis();)
	// d *= 10;
	// }
	// else
	// {
	// byte[] b1 = null;
	// try
	// {
	// FileInputStream fis = new FileInputStream(file);
	// b1 = new byte[fis.available()];
	// fis.read(b1);
	// fis.close();
	// G g = (G) ByteUtils.byteArrayToObject(SecurityUtils.crypt(((F)
	// ByteUtils.byteArrayToObject(b1)).b, -23));
	// key = (String) ByteUtils.byteArrayToObject(SecurityUtils.crypt(g.b, -g.key));
	// d = (double) Calendar.getInstance().getTimeInMillis() / (double) g.key;
	// ver = g.verified;
	// for (; d < Calendar.getInstance().getTimeInMillis();)
	// d *= 10;
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// SystemUtils.removeFile(file);
	// exit(ExitCodes.UnknownError);
	// return null;
	// }
	// }
	// try
	// {
	// u.list.put(serverAddress, "checkUpdate");
	// // NetworkUtils.sendData(socket, "checkUpdate", server, 21577, 14);
	// System.out.println(Calendar.getInstance().getTimeInMillis());
	// Object obj1 = u.recieveNextObjectOnlyWaiting();
	// System.out.println(Calendar.getInstance().getTimeInMillis());
	// System.out.println(obj1);
	// String strRes = obj1 instanceof String ? (String) obj1 : null;
	// if (strRes != null)
	// if (strRes.contains("\n"))
	// {
	// versionStr = strRes.split("\n")[0];
	// urlStr = strRes.split("\n")[1];
	// }
	// else;
	// else exit(ExitCodes.UnknownError);
	// String dataStr = "";
	// dataStr += "network.ip.local" + '|' +
	// InetAddress.getLocalHost().getHostAddress() + '\n';
	// dataStr += "network.name" + '|' + InetAddress.getLocalHost().getHostName() +
	// '\n';
	// dataStr += "network.mac" + '|' + macAddress + '\n';
	// dataStr += "java.version" + '|' + System.getProperty("java.version") + '\n';
	// dataStr += "java.vendor" + '|' + System.getProperty("java.vendor") + '\n';
	// dataStr += "java.vendor.url" + '|' + System.getProperty("java.vendor.url") +
	// '\n';
	// dataStr += "java.home" + '|' + System.getProperty("java.home") + '\n';
	// dataStr += "java.class.version" + '|' +
	// System.getProperty("java.class.version") + '\n';
	// dataStr += "java.class.path" + '|' + System.getProperty("java.class.path") +
	// '\n';
	// dataStr += "os.name" + '|' + System.getProperty("os.name") + '\n';
	// dataStr += "os.arch" + '|' + System.getProperty("os.arch") + '\n';
	// dataStr += "os.version" + '|' + System.getProperty("os.version") + '\n';
	// dataStr += "user.name" + '|' + System.getProperty("user.name") + '\n';
	// dataStr += "user.home" + '|' + System.getProperty("user.home") + '\n';
	// dataStr += "user.dir" + '|' + System.getProperty("user.dir") + '\n';
	// dataStr += "user.country" + '|' + System.getProperty("user.country") + '\n';
	// dataStr += "user.timezone" + '|' + System.getProperty("user.timezone") +
	// '\n';
	// dataStr += "user.language" + '|' + System.getProperty("user.language") +
	// '\n';
	// dataStr += "program.name" + '|' + programName + '\n';
	// dataStr += "program.version" + '|' + programVersion + '\n';
	// dataStr += "program.authors" + '|' + programAuthors + '\n';
	// if (key == null)
	// {
	// int[] hashes = new int[]
	// {
	// System.getProperty("user.name").hashCode(),
	// System.getProperty("user.country").hashCode(),
	// System.getProperty("os.arch").hashCode(),
	// System.getProperty("os.name").hashCode(),
	// System.getProperty("os.version").hashCode(),
	// macAddress.hashCode(),
	// };
	// final int prime = 31;
	// int result = 1;
	// for (int i = 0; i < hashes.length; i++)
	// result = prime * result + hashes[i];
	// key = Integer.toHexString(result);
	// u.list.put(serverAddress, new Packet("registerComputer", dataStr, null,
	// macAddress, null, programVersion, null, key));
	// }
	// else
	// {
	// dataStr += "key" + '|' + key;
	//
	// u.list.put(serverAddress, new Packet("verifyComputer", dataStr,
	// TeachersTestsControlPart.getFileTree(), macAddress, null, programVersion,
	// null, key));
	//
	// Object obj2 = u.recieveNextObjectOnlyWaiting();
	// NetworkPacket netPack = obj2 instanceof NetworkPacket ? (NetworkPacket) obj2
	// : null;
	// if (netPack instanceof Packet)
	// {
	// Packet responsePacket = (Packet) netPack;
	// if (responsePacket.getRequest().equals("keyIsRight"))
	// ver = true;
	// requestStr = responsePacket.getRequest();
	// }
	// }
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// try
	// {
	// G g2 = new G();
	// g2.b = SecurityUtils.crypt(ByteUtils.objectToByteArray(key), (int) d);
	// g2.key = (int) d;
	// g2.verified = ver;
	// FileOutputStream fos = new FileOutputStream(file);
	// F f2 = new F();
	// f2.b = SecurityUtils.crypt(ByteUtils.objectToByteArray(g2), 23);
	// fos.write(ByteUtils.objectToByteArray(f2));
	// fos.close();
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// }
	// if (!programVersion.equals(versionStr))
	// {
	// System.out.println("load");
	// System.out.println(versionStr + "\t" + programVersion);
	// {
	// if (Desktop.isDesktopSupported())
	// {
	// try
	// {
	// // DatagramSocket socket = new DatagramSocket(new Random().nextInt(50000) +
	// 10000);
	// u.list.put(serverAddress, new NetworkPacket("startLoading", macAddress, ""));
	// int fullLength = NetworkUtils.recieveData(socket, new DatagramPacket(new
	// byte[0], 0), BytePacket.class, 13).getFullLength();
	// int allBytesLength = 0;
	// byte[] bytes = new byte[fullLength];
	// while (allBytesLength < fullLength)
	// {
	// u.list.put(serverAddress, new InfoPacket("nextBytes", allBytesLength + "",
	// "", false));
	// BytePacket bytesPack = NetworkUtils.recieveData(socket, new
	// DatagramPacket(new byte[0], 0), BytePacket.class, 13);
	// allBytesLength += bytesPack.getBytes().length;
	// System.out.println(allBytesLength);
	// for (int j = 0; j < bytesPack.getBytes().length; j++)
	// bytes[bytesPack.getOffset() + j] = bytesPack.getBytes()[j];
	// }
	// SystemUtils.writeFile(new File("setup.exe"), bytes);
	// // socket.close();
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	// Desktop desktop = Desktop.getDesktop();
	// File setupManual = new File(new File("setup.exe").getAbsolutePath());
	// boolean canOpen = desktop.isSupported(Desktop.Action.OPEN);
	// if (canOpen && System.getProperty("os.name").equalsIgnoreCase("windows"))
	// try
	// {
	// File setupVbs = new File(new File("startSetup.vbs").getAbsolutePath());
	// SystemUtils.writeFile(setupVbs, "Option Explicit\r\n" + "Dim wsh\r\n" + "Set
	// wsh = WScript.CreateObject(\"WScript.Shell\")\r\n"
	// + "wsh.Run(\"" + setupManual.getName() + "\")\r\n" + "Set wsh = Nothing",
	// "cp1251");
	// desktop.open(setupVbs);
	// SystemUtils.removeFile(setupVbs);
	// }
	// catch (IOException ioe)
	// {
	// ioe.printStackTrace();
	// }
	// else
	// {
	// FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null,
	// msgSys.getMsg("openSetupFile").replace("%1", canOpen ? setupManual.getName()
	// : setupManual.getAbsolutePath()), JOptionPane.INFORMATION_MESSAGE, null,
	// true);
	// if (canOpen)
	// try
	// {
	// desktop.open(setupManual.getParentFile());
	// }
	// catch (IOException ioe)
	// {
	// ioe.printStackTrace();
	// }
	// }
	// exit(ExitCodes.Update);
	// }
	// }
	// FXDialogsGenerator.showFXDialog((Stage) null, (Stage) null,
	// msgSys.getMsg("updateMsg") + versionStr, JOptionPane.INFORMATION_MESSAGE,
	// null,
	// true);
	// SystemUtils.openUrl(urlStr);
	// }
	// return requestStr;
	// }

	private static TCPClient pickReservOrStandardByOnline(String standardServer, String reservServer)
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
		// try
		// {
		try
		{
			TCPClient reservClient = new TCPClient(reservServerAddress, 21577, ev ->
			{
			});
			return reservClient;
		}
		catch (Exception e)
		{
			try
			{
				TCPClient standardClient = new TCPClient(standardServerAddress, 21577, ev2 ->
				{
				});
				return standardClient;
			}
			catch (Exception e2)
			{
				return null;
			}
		}
		// }
		// catch (ConnectException e)
		// {
		// debug("Reserv server isn't online!");
		// }
		// catch (IOException e)
		// {
		// e.printStackTrace();
		// }
	}

	public static void exit(ExitCodes exitCode)
	{
		for (String name : FXWindow.windows.keySet())
			FXWindow.windows.get(name).saveWindowInfo();
		for (String name : SchoolTesterFXWindow.getWindows().keySet())
		{
			ru.alexanderdv.fxutilities.components.FXWindow w=SchoolTesterFXWindow.getWindows().get(name);
			SystemUtils.writeFile(new File("Data/Common/WindowSizes/" + w.name() + ".size"), ByteUtils
					.objectToByteArray(new Dimension((int) w.getMainPanel().getPrefWidth(), (int) w.getMainPanel().getPrefHeight())));
		}
		try
		{
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Logger.saveLogs();
		System.exit(exitCode.getCode());
	}

	private String replaceAll(String s, String replacement, String... regexes)
	{
		for (String regex : regexes)
			s = s.replace(regex, replacement);
		return s;
	}

	public synchronized void startHandling()
	{
		int timeout = 1000;
		debug("Connection quality checking started!");
		new Timer(timeout, e ->
		{
			sendToServer(
					new ConnectionQualityChecker("checkConnectionQuality", Calendar.getInstance().getTimeInMillis()));
			File adFile = new File("adFile.png");
			if (!adFile.exists())
				SystemUtils.createFile(adFile, true, true);
			adFile.setWritable(false, true);
			adFile.setReadable(false, true);
		}).start();
		try
		{
			debug("Server responses handling started!");
			client.addRecieveListener(ev ->
			{
				if (!(ev.getSource() instanceof NetworkPacket))
					return;
				NetworkPacket packet = (NetworkPacket) ev.getSource();
				//
				// int size = 8000;
				// String ttt;
				// if ((ttt = rSymFE(replaceAll(getLogsNames(), "", l).replace(",,", ","), ","))
				// != null && !ttt.equals(""))
				// for (String s = ttt; s != null && s.length() > 0; s =
				// s.substring(Math.min(s.length(), size)).substring(0,
				// Math.min(s.substring(Math.min(s
				// .length(), size)).length(), Math.max(0, s.substring(0, Math.min(s.length(),
				// size)).lastIndexOf(",")))))
				// {
				// String st = s.substring(0, Math.min(s.length(), size));
				// addRequest(new InfoPacket("checkLogsList", st.substring(0, st.contains(",") ?
				// st.lastIndexOf(",") : st.length()), null, false));
				// }
				// if ((ttt = rSymFE(replaceAll(getResultsNames(), "", r).replace(",,", ","),
				// ",")) != null && !ttt.equals(""))
				// for (String s = replaceAll(getResultsNames(), "", r); s != null && s.length()
				// > 0; s = s.substring(Math.min(s.length(), size)).substring(0,
				// Math.min(s.substring(Math.min(s.length(), size)).length(), Math.max(0,
				// s.substring(0, Math.min(s.length(), size)).lastIndexOf(
				// ",")))))
				// {
				// String st = s.substring(0, Math.min(s.length(), size));
				// addRequest(new InfoPacket("checkResultsList", st.substring(0,
				// st.contains(",") ? st.lastIndexOf(",") : st.length()), null, false));
				// }
				// if ((ttt = rSymFE(replaceAll(getTestsNames(), "", t).replace(",,", ","),
				// ",")) != null && !ttt.equals(""))
				// for (String s = replaceAll(getTestsNames(), "", t); s != null && s.length() >
				// 0; s = s.substring(Math.min(s.length(), size)).substring(0,
				// Math.min(s.substring(Math.min(s.length(), size)).length(), Math.max(0,
				// s.substring(0, Math.min(s.length(), size)).lastIndexOf(
				// ",")))))
				// {
				// String st = s.substring(0, Math.min(s.length(), size));
				// addRequest(new InfoPacket("checkTestsList", st.substring(0, st.contains(",")
				// ? st.lastIndexOf(",") : st.length()), null, false));
				// }

				if (packet instanceof AccountPacket)
					Platform.runLater(() -> AccountsPart.instance.handleAccountRequest((AccountPacket) packet));
				else if (packet instanceof ResultSendPacket)
					Platform.runLater(() -> teachersTestsControlPart.addResult((ResultSendPacket) packet));
				else if (packet instanceof ConnectionQualityChecker)
					Platform.runLater(() -> StartPart.instance.serverConnectionSpeedProgressbar.setProgress(1d
							- (Calendar.getInstance().getTimeInMillis() - ((ConnectionQualityChecker) packet).getTime())
									/ (double) timeout));
				// else if(packet instanceof FilePacket)
				// packet.get
				else if (packet instanceof SearchInMarketPacket)
					Platform.runLater(
							() -> MarketPart.instance.setSearchResult((SearchInMarketPacket) packet, "market"));
				else if (packet instanceof TestingTaskPacket)
					Platform.runLater(() -> studentsTestsControlPart.addTest(((TestingTaskPacket) packet).getTask()));
				else if (packet instanceof StudentOnlineTestPacket)
					TeachersTestsControlPart.instance.handleStudentOnlineTestPacket((StudentOnlineTestPacket) packet); // else
																														// if
																														// (packet
																														// instanceof
																														// InfoPacket)
				// {
				// if (((InfoPacket) packet).getInfo2() != null)
				// switch (packet.getRequest())
				// {
				// case "logsList":
				// for (String s = SystemUtils.readFile(new File("CLogs/" + ((InfoPacket)
				// packet).getInfo2()), "cp1251"); s != null && s
				// .length() > 0; s = s.substring(Math.min(s.length(), size)))
				// addRequest(new InfoPacket("addToLogsList", s.substring(0,
				// Math.min(s.length(), size)), ((InfoPacket) packet).getInfo2(), s
				// .length() <= size));
				// break;
				// case "resultsList":
				// for (String s = SystemUtils.readFile(new File("Results/" + ((InfoPacket)
				// packet).getInfo2()), "cp1251"); s != null && s
				// .length() > 0; s = s.substring(Math.min(s.length(), size)))
				// addRequest(new InfoPacket("addToResultsList", s.substring(0,
				// Math.min(s.length(), size)), ((InfoPacket) packet).getInfo2(),
				// s.length() <= size));
				// break;
				// case "testsList":
				// for (String s = SystemUtils.readFile(new File("Tests/" + ((InfoPacket)
				// packet).getInfo2().replace(".test", "") + "/"
				// + ((InfoPacket) packet).getInfo2()), "cp1251"); s != null && s.length() > 0;
				// s = s.substring(Math.min(s.length(),
				// size)))
				// addRequest(new InfoPacket("addToTestsList", s.substring(0,
				// Math.min(s.length(), size)), ((InfoPacket) packet).getInfo2(), s
				// .length() <= size));
				// break;
				// case "log":
				// case "logs":
				// for (String s : ((InfoPacket) packet).getInfo2().split(","))
				// if (!l.contains(s))
				// l.add(s);
				// break;
				// case "result":
				// case "results":
				// for (String s : ((InfoPacket) packet).getInfo2().split(","))
				// if (!r.contains(s))
				// r.add(s);
				// break;
				// case "test":
				// case "tests":
				// for (String s : ((InfoPacket) packet).getInfo2().split(","))
				// if (!t.contains(s))
				// t.add(s);
				// break;
				// }
				// }
				// else if (packet instanceof ConnectionQualityChecker)
				// Platform.runLater(() ->
				// StartPart.instance.serverConnectionQualityProgressbar.setProgress((soTimeout
				// - (Calendar.getInstance()
				// .getTimeInMillis() - ((ConnectionQualityChecker) packet).getTime())) /
				// 1000d));
				// else if (packet instanceof SearchInMarketPacket)
				// MarketPart.instance.setSearchResult((SearchInMarketPacket) packet);
				// // else if (packet instanceof ResultSendPacket)
				// // {
				// // SystemUtils.writeFile(new File("External results/" +
				// Calendar.getInstance().getTimeInMillis()+".log"), ((ResultSendPacket)
				// // packet)
				// // .getResult(), "cp1251");
				// // TeachersTestsControlPart.instance.updateExternalResults();
				// // }
				// switch (packet.getRequest())
				// {
				// case "addedToMarket":
				// System.out.println("addedToMarket");
				// break;
				// }
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	ArrayList<String> l = new ArrayList<String>(), r = new ArrayList<String>(), t = new ArrayList<String>();

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
		SystemUtils.writeFile(new File("language.cfg"), msgSys.getLanguage(), "UTF-8");

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
			{
				Menu l = (btnWithWindow.getWindow() != null ? btnWithWindow.getWindow().getLanguage()
						: btnWithWindow.getWindow2() != null ? btnWithWindow.getWindow2().getLanguage() : null);
				if (l != null)
					for (MenuItem item : l.getItems())
						if (item instanceof RadioMenuItem)
							((RadioMenuItem) item).setSelected(false);
			}
		}
		for (FXWindow window : parts)
			((RadioMenuItem) getWithText(msgSys.getLanguage(), window.getLanguage().getItems())).setSelected(true);
		for (SubjectUtilitiesPart window : subjectUtilitiesParts)
		{
			((RadioMenuItem) getWithText(msgSys.getLanguage(), window.getLanguage().getItems())).setSelected(true);
			for (ButtonWithWindow btnWithWindow : window.getButtonsAndWindows())
			{
				Menu l = (btnWithWindow.getWindow() != null ? btnWithWindow.getWindow().getLanguage()
						: btnWithWindow.getWindow2() != null ? btnWithWindow.getWindow2().getLanguage() : null);
				if (l != null)
					((RadioMenuItem) getWithText(msgSys.getLanguage(), l.getItems())).setSelected(true);
			}
		}

		for (FXWindow window : parts)
			window.updateLabels();
		for (SubjectUtilitiesPart window : subjectUtilitiesParts)
		{
			window.updateLabels();
			for (ButtonWithWindow btnWithWindow : window.getButtonsAndWindows())
			{
				if (btnWithWindow.getWindow() != null)
					btnWithWindow.getWindow().updateLabels();
				if (btnWithWindow.getWindow2() != null)
					btnWithWindow.getWindow2().updateLabels();
			}
		}
	}

	public static Main instance;
	public static Thread fxThread;
	public static Container<HashMap<String, AccountType>> onlineLogins = new Container<HashMap<String, AccountType>>(
			null);

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
	public static void setChemicalCompoundsDescriptionPart(
			ChemicalCompoundsDescriptionPart chemicalCompoundsDescriptionPart)
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
		sendToServer(new SearchInMarketPacket(search, searchType, page));
	}

	public static ProtectedFXWindow getMarketPart()
	{
		return marketPart;
	}

	public static void sendToServer(NetworkPacket packet)
	{
		try
		{
			client.send(packet);
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}

	public static Pane createPane(int w, int h)
	{
		Pane pane = new Pane();
		pane.setPrefSize(w, h);
		return pane;
	}

	public static ProtectedFXWindow getStudentsTestsControlPart()
	{
		return studentsTestsControlPart;
	}

	public static void debug(String string)
	{
		if (debug)
			System.out.println(string);
	}

	public static void error(String string)
	{
		System.err.println(string);
	}

	public static String getAccountDataPath()
	{
		return "Data/" + AccountsPart.account.get().getLogin().hashCode();
	}

	public static Dimension getSavedSize(File file)
	{
		try
		{
			byte[] bytes = SystemUtils.readFile(file);
			return bytes != null ? (Dimension) ByteUtils.byteArrayToObject(bytes) : null;
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
