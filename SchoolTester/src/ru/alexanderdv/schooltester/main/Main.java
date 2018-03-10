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
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.FXDialogsGenerator;
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
 * @version 5.5.0a
 */
public class Main extends Application
{
	public static final MessageSystem msgSys = new MessageSystem(new Config(new File("language.cfg")).getString("language", "en_uk", true));
	private static final boolean developmentMode = false;
	private static boolean fxWindowFrame;
	public static final String programName = "SchoolTester";
	public static final String programVersion = "5.5.0a";
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
		if (connectToServer())
		{
			getStartPart().open(primaryStage, AccountsPart.account.get(), Main.instance.socket);
			getStartPart().getStage().centerOnScreen();
			startHandling();
		}
		else exit(ExitCodes.UnknownError);

	}

	private void openNeedfulParts()
	{
		// accountsPart.open(stage);
		// teachersTestsControlPart.open(stage);
		// testDevPart.stage.show();
		// electronicBooksPart.open(stage);
		// functionsWorkPart.open(stage);
		// crossWordGeneratorPart.open(stage);
	}

	public static final InetAddress server = pickReservOrStandardByOnline("18.219.238.19", "94.181.44.135");

	public boolean connectToServer()
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
						versionStr = responsePacket.getVersion();
						urlStr = responsePacket.getUrl();
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
		switch (requestStr)
		{
			case "youAreInBlacklist":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg(requestStr), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,
						isFxWindowFrame(), true);
				exit(ExitCodes.YouAreInBlacklist);
				return false;
			case "keyIsBad":
				SystemUtils.writeFile(new File("lock.cfg"), "locked: true", "Cp1251");
				FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg(requestStr), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,
						isFxWindowFrame(), true);
				exit(ExitCodes.KeyIsBad);
				return false;
			case "verifyRequestSended":
				FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg(requestStr), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,
						isFxWindowFrame(), true);
				SystemUtils.openUrlInBrowser(msgSys.getMsg("siteURL"));
				exit(ExitCodes.NotVerified);
				return false;
			case "keyIsRight":
				FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg(requestStr), JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,
						isFxWindowFrame(), true);
				if (!programVersion.equals(versionStr))
				{
					FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg("updateMsg") + versionStr, JOptionPane.INFORMATION_MESSAGE,
							JOptionPane.DEFAULT_OPTION, isFxWindowFrame(), true);
					SystemUtils.openUrlInBrowser(urlStr);
				}
				return true;
			default:
				FXDialogsGenerator.showFXDialog((Stage) null, null, msgSys.getMsg(requestStr), JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION,
						isFxWindowFrame(), true);
				return true;
		}
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
	}

	private static ArrayList<NetworkPacket> requests = new ArrayList<NetworkPacket>();

	int port = new Random().nextInt(50000);
	Thread socketThread;

	public synchronized void addRequest(NetworkPacket packet)
	{
		requests.add(packet);
	}

	public DatagramSocket socket;

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
							addRequest(new NetworkPacket("check", macAddress, ""));
							for (NetworkPacket packet : requests)
								NetworkUtils.sendData(socket, packet, server, 21577, 14);
							requests.clear();
							socket.setSoTimeout(500);
							NetworkPacket packet = NetworkUtils.recieveData(socket, NetworkPacket.class, 13);
							if (packet instanceof AccountPacket)
								Platform.runLater(() -> AccountsPart.instance.handleAccountRequest((AccountPacket) packet));
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
		FXDialogsGenerator.showFXDialog(null, null, "FX window frame state changed! Restart the program to see the effect.", 0, 0, b, true);
	}

	static boolean h1, h2, h3, h4, h5, h6;
	public static Main instance;

	@SuppressWarnings("unused")
	private void prepareToExit()
	{
		getAccountsPart().actionPerformed(new ActionEvent(instance, 0, "prepareToExit"));
	}

	public void hideAll()
	{
		h1 = getAccountsPart().getStage().isShowing();
		h2 = getCrossWordGeneratorPart().getStage().isShowing();
		h3 = electronicBooksPart.getStage().isShowing();
		h4 = getTeachersTestsControlPart().getStage().isShowing();
		h5 = getStartPart().getStage().isShowing();
		h6 = getTestDevPart().getStage().isShowing();
		getAccountsPart().getStage().hide();
		getCrossWordGeneratorPart().getStage().hide();
		electronicBooksPart.getStage().hide();
		getTeachersTestsControlPart().getStage().hide();
		getStartPart().getStage().hide();
		getTestDevPart().getStage().hide();
	}

	public void showAllHided()
	{
		if (h1)
			getAccountsPart().getStage().show();
		if (h2)
			getCrossWordGeneratorPart().getStage().show();
		if (h3)
			electronicBooksPart.getStage().show();
		if (h4)
			getTeachersTestsControlPart().getStage().show();
		if (h5)
			getStartPart().getStage().show();
		if (h6)
			getTestDevPart().getStage().show();
		h1 = h2 = h3 = h4 = h5 = h6 = false;
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
