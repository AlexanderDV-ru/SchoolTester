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
import ru.alexanderdv.schooltester.main.student.StudentsTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TestDevPart;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart.ButtonWithWindow;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.fx.FXWindow;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class StartPart extends FXWindow
{
	public static StartPart instance;
	private MenuItem openAccountsMenu;
	private Menu accounts;

	public StartPart(String secondaryTitle, URL url, boolean inDevelope)
	{
		super(secondaryTitle, url, 3, inDevelope, true);
		instance = this;
		stage.setOnCloseRequest(e -> Main.exit(ExitCodes.UserCloseProgram));
		stage.setOnShown(e -> resize());
		createActionHandlers();
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

	@Override
	protected void _resize(int w, int h)
	{
		InitStartPart.instance.resize(w, h);
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
		InitStartPart.instance.serverConnectionSpeedLabel.setText(msgSys.getMsg("serverConnectionSpeed"));
		InitStartPart.instance.common.setText(msgSys.getMsg("common"));
		InitStartPart.instance.special.setText(msgSys.getMsg("special"));
		InitStartPart.instance.utils.setText(msgSys.getMsg("utils"));

		InitStartPart.instance.openTeachersTestsControlPart.setText(msgSys.getMsg(TeachersTestsControlPart.instance.name()));
		InitStartPart.instance.openStundentsTestsControlPart.setText(msgSys.getMsg(StudentsTestsControlPart.instance.name()));
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