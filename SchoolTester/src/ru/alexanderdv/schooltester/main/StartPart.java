package ru.alexanderdv.schooltester.main;

import java.io.Serializable;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.alexanderdv.schooltester.main.developer.TestDevPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart.ButtonWithWindow;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.fx.FXWindow;

/**
 * StartPart - the GUI part for working after program launch
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.8a
 */
public class StartPart extends FXWindow
{
	public static StartPart instance;
	private MenuItem openAccountsMenu;
	private Menu accounts;

	public StartPart(String secondaryTitle, URL url, boolean inDevelope)
	{
		super(secondaryTitle, url, 3, inDevelope);
		instance = this;
		AccountsPart.account.addActionListener(e -> updateLabelsInPart());
		AccountsPart.account.addActionListener(e ->
		{
			InitStartPart.instance.openTeachersTestsControlPart.setVisible(false);
			InitStartPart.instance.openTestDevPart.setVisible(false);
			switch (AccountsPart.account.get() != null ? AccountsPart.account.get().getAccountType().name().toLowerCase() : "none")
			{
				case "administrator":
				case "teacher":
					InitStartPart.instance.openTeachersTestsControlPart.setVisible(true);
					InitStartPart.instance.openTestDevPart.setVisible(true);
				case "student":
					for (Button subjectUtilitiesButton : InitStartPart.instance.subjectUtilitiesButtons.keySet())
						subjectUtilitiesButton.setVisible(true);
				case "none":
					break;
			}
		});
		AccountsPart.account.set(null);
		stage.setOnCloseRequest(e -> Main.exit(ExitCodes.UserCloseProgram));
	}

	public Stage getStage()
	{
		return stage;
	}

	@Override
	public MenuBar createMenu()
	{
		super.createMenu();
		menubar.getMenus().add(accounts = new Menu());
		accounts.getItems().add(openAccountsMenu = new MenuItem());
		accounts.setOnShowing(e -> openAccountsMenu.setDisable(Main.getAccountsPart().isVisible()));
		openAccountsMenu.setOnAction(e ->
		{
			openAccountsMenu.setDisable(true);
			try
			{
				Main.getAccountsPart().open(stage);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		});
		return menubar;
	}

	static class G implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6913229322384679569L;
		int key;
		byte[] b;
		boolean verified;
	}

	static class F implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6913229322384675369L;
		byte[] b;
	}

	@FXML
	public void initialize()
	{
		instance = this;
	}

	public void updateLabelsInPart()
	{
		super.updateLabelsInPart();
		accounts.setText(msgSys.getMsg("accounts"));
		{
			openAccountsMenu.setText(msgSys.getMsg("openAccount"));
		}

		if (AccountsPart.account.get() == null)
		{
			InitStartPart.instance.message.setText(msgSys.getMsg("notInAccountMsg"));
			InitStartPart.instance.mainPane.setText(msgSys.getMsg("notInAccount"));
		}
		else
		{
			InitStartPart.instance.message.setText(msgSys.getMsg("in" + AccountsPart.account.get().getAccountType().name() + "AccountMsg"));
			InitStartPart.instance.mainPane.setText(msgSys.getMsg(AccountsPart.account.get().getAccountType().name() + "Account"));
		}
		InitStartPart.instance.common.setText(msgSys.getMsg("common"));
		InitStartPart.instance.special.setText(msgSys.getMsg("special"));
		InitStartPart.instance.utils.setText(msgSys.getMsg("utils"));

		InitStartPart.instance.openTeachersTestsControlPart.setText(msgSys.getMsg(TeachersTestsControlPart.instance.name()));
		InitStartPart.instance.openTestDevPart.setText(msgSys.getMsg(TestDevPart.instance.name()));
		InitStartPart.instance.openMarketPart.setText(msgSys.getMsg(MarketPart.instance.name()));

		for (Button btn : InitStartPart.instance.subjectUtilitiesButtons.keySet())
		{
			btn.setText(msgSys.getMsg(InitStartPart.instance.subjectUtilitiesButtons.get(btn).name()).replace("%1", msgSys.getMsg(
					InitStartPart.instance.subjectUtilitiesButtons.get(btn).getSubject().name())));
			for (ButtonWithWindow btnWithWindow : InitStartPart.instance.subjectUtilitiesButtons.get(btn).getButtonsAndWindows())
				btnWithWindow.getButton().setText(msgSys.getMsg(btnWithWindow.getWindow().name()));
		}
	}

	public void addOnHiding(EventHandler<WindowEvent> event)
	{
		if (fxScene != null)
			fxScene.setOnHiding(event);
		else stage.setOnHiding(event);
	}

	@Override
	public String name()
	{
		return "mainPart";
	}
}