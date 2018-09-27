package ru.alexanderdv.schooltester.main;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.alexanderdv.schooltester.main.student.StudentsTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TestDevPart;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart.ButtonWithWindow;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.SchoolTesterFXWindow;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class StartPart extends SchoolTesterFXWindow
{
	public static StartPart instance;
	private final MenuItem openAccountsMenu;
	private final Menu accounts;

	public final TitledPane mainPane;
	public final TitledPane special, utils, common;
	public final VBox specialPane, utilsPane, commonPane;
	public final Label message;
	public final ProgressBar serverConnectionSpeedProgressbar;
	public final Label serverConnectionSpeedLabel;
	public final Button openTeachersTestsControlPart, openStundentsTestsControlPart, openTestDevPart, openMarketPart;
	public final HashMap<Button, SubjectUtilitiesPart> subjectUtilitiesButtons;

	public StartPart(Stage stage)
	{
		super(stage, Main.program, Main.icon, Main.createPane(600, 500), 3, false, true, true);
		instance = this;
		// Init
		// Menu
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
		// Navigation
		getPanel().getChildren()
				.add(mainPane = new TitledPane("",
						new Pane(message = new Label(), serverConnectionSpeedLabel = new Label(),
								serverConnectionSpeedProgressbar = new ProgressBar(),
								common = new TitledPane("", commonPane = new VBox(5)),
								special = new TitledPane("", specialPane = new VBox(5)),
								utils = new TitledPane("", utilsPane = new VBox(5)))));
		subjectUtilitiesButtons = new HashMap<Button, SubjectUtilitiesPart>();
		openTeachersTestsControlPart = new Button();
		openStundentsTestsControlPart = new Button();
		openTestDevPart = new Button();
		openMarketPart = new Button();
		// Init end
		// Setting
		message.setWrapText(true);
		mainPane.setCollapsible(false);
		common.setCollapsible(false);
		special.setCollapsible(false);
		utils.setCollapsible(false);
		setAligmentToCenter(mainPane, common, special, utils, serverConnectionSpeedLabel, message);

		int o = 10;
		openTeachersTestsControlPart
				.setTextFill(TeachersTestsControlPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openTeachersTestsControlPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getTeachersTestsControlPart().open(getStage(), AccountsPart.account.get(), Main.client);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(stage, Main.msgSys.getMsg("signInToWork"), null, true);
			}
		});
		openTeachersTestsControlPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTeachersTestsControlPart.setLayoutX(o);
		openStundentsTestsControlPart
				.setTextFill(StudentsTestsControlPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openStundentsTestsControlPart.setOnAction(e ->
		{
			try
			{
				System.out.println("mark1");
				Main.getAccountsPart().close();
				System.out.println("mark2");
				Main.getStudentsTestsControlPart().open(getStage(), AccountsPart.account.get(), Main.client);
				System.out.println("mark3");
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(stage, Main.msgSys.getMsg("signInToWork"), null, true);
			}
		});
		openStundentsTestsControlPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openStundentsTestsControlPart.setLayoutX(o);

		openTestDevPart.setTextFill(TestDevPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openTestDevPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getTestDevPart().open(getStage(), AccountsPart.account.get(), Main.client);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(stage, Main.msgSys.getMsg("signInToWork"), null, true);
			}
		});
		openTestDevPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTestDevPart.setLayoutX(o);

		openMarketPart.setTextFill(MarketPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openMarketPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getMarketPart().open(getStage(), AccountsPart.account.get(), Main.client);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
				FXDialogsGenerator.showFXDialog(stage, Main.msgSys.getMsg("signInToWork"), null, true);
			}
		});
		openMarketPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openMarketPart.setLayoutX(o);

		for (SubjectUtilitiesPart subjectUtilities : Main.getSubjectUtilitiesParts())
		{
			Button subjectUtilitiesButton = new Button(Main.msgSys.getMsg("openSubjectUtilities").replace("%1",
					Main.msgSys.getMsg(subjectUtilities.getSubject().name())));
			subjectUtilitiesButton.setTextFill(subjectUtilities.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
			subjectUtilitiesButtons.put(subjectUtilitiesButton, subjectUtilities);
			subjectUtilitiesButton.setOnAction(e ->
			{
				Main.getAccountsPart().close();
				subjectUtilities.open(getStage());
			});
			commonPane.getChildren().add(subjectUtilitiesButton);
			subjectUtilitiesButton.setPrefWidth(common.getPrefWidth() - o * 2);
			subjectUtilitiesButton.setLayoutX(o);
		}

		// Setting end
		createListeners();
	}

	@Override
	public void createListeners()
	{
		super.createListeners();
		stage.setOnCloseRequest(e -> Main.exit(ExitCodes.UserCloseProgram));
		AccountsPart.account.addChangeListener(e ->
		{
			updateLabels();
			specialPane.getChildren().clear();
			commonPane.getChildren().clear();
			utilsPane.getChildren().clear();
			String t = AccountsPart.account.get() != null
					? AccountsPart.account.get().getAccountType().name().toLowerCase()
					: "none";
			if (t.equals("teacher"))
			{
				specialPane.getChildren().add(openTeachersTestsControlPart);
				specialPane.getChildren().add(openTestDevPart);
			}
			if (t.equals("student"))
			{
				specialPane.getChildren().add(openStundentsTestsControlPart);
			}
			if (!t.equals("none"))
			{
				for (Button subjectUtilitiesButton : subjectUtilitiesButtons.keySet())
					utilsPane.getChildren().add(subjectUtilitiesButton);
				commonPane.getChildren().add(openMarketPart);
			}
		});
		AccountsPart.account.set(null);
	}

	private void setAccountChangeListener()
	{
		AccountsPart.account.set(null);
	}

	public Stage getStage()
	{
		return stage;
	}

	@Override
	public void _resize(double w, double h1)
	{
		double dst = 5;
		double height = 20, offset = height + dst;
		double height2 = 30, offset2 = height2 + dst;
		double height3 = 25, offset3 = height3 + dst;

		double y = 0;

		mainPane.setLayoutX(0);
		mainPane.setLayoutY(0);
		mainPane.setPrefSize(w, h1);

		double h = h1 - Main.paneTitleHeight;

		serverConnectionSpeedLabel.setLayoutX(dst);
		serverConnectionSpeedLabel.setLayoutY(y += dst);
		serverConnectionSpeedLabel.setPrefSize(w / 2 - dst * 1.5, height);

		serverConnectionSpeedProgressbar.setLayoutX(w / 2 + dst * 0.5);
		serverConnectionSpeedProgressbar.setLayoutY(dst);
		serverConnectionSpeedProgressbar.setPrefSize(w / 2 - dst * 1.5, height);

		message.setLayoutY(y += offset);
		centrizeByText(message, (int)w, 120);

		double boxW = (w - dst * 2) / 3;

		common.setLayoutX(0);
		common.setLayoutY(y += dst + 120);
		common.setPrefSize(boxW, h - y);

		special.setLayoutX(boxW * 1 + dst * 1);
		special.setLayoutY(y);
		special.setPrefSize(boxW, h - y);

		utils.setLayoutX(boxW * 2 + dst * 2);
		utils.setLayoutY(y);
		utils.setPrefSize(boxW, h - y);

		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.addAll(commonPane.getChildren());
		nodes.addAll(specialPane.getChildren());
		nodes.addAll(utilsPane.getChildren());
		for (Node node : nodes)
			if (node instanceof Region)
			{
				Region region = (Region) node;
				region.setLayoutX(dst);
				region.setPrefWidth(boxW - dst * 2);
			}

	}

	public void updateLabels()
	{
		super.updateLabels();

		accounts.setText(Main.msgSys.getMsg("accounts"));
		{
			openAccountsMenu.setText(Main.msgSys.getMsg("openAccount"));
		}

		if (AccountsPart.account.get() == null)
		{
			message.setText(Main.msgSys.getMsg("notInAccountMsg"));
			mainPane.setText(Main.msgSys.getMsg("notInAccount"));
		}
		else
		{
			message.setText(
					Main.msgSys.getMsg("in" + AccountsPart.account.get().getAccountType().name() + "AccountMsg"));
			mainPane.setText(Main.msgSys.getMsg(AccountsPart.account.get().getAccountType().name() + "Account"));
		}
		serverConnectionSpeedLabel.setText(Main.msgSys.getMsg("serverConnectionSpeed"));
		common.setText(Main.msgSys.getMsg("common"));
		special.setText(Main.msgSys.getMsg("special"));
		utils.setText(Main.msgSys.getMsg("utils"));

		openTeachersTestsControlPart.setText(Main.msgSys.getMsg(TeachersTestsControlPart.instance.name()));
		openStundentsTestsControlPart.setText(Main.msgSys.getMsg(StudentsTestsControlPart.instance.name()));
		openTestDevPart.setText(Main.msgSys.getMsg(TestDevPart.instance.name()));
		openMarketPart.setText(Main.msgSys.getMsg(MarketPart.instance.name()));

		for (Button btn : subjectUtilitiesButtons.keySet())
		{
			btn.setText(Main.msgSys.getMsg(subjectUtilitiesButtons.get(btn).name()).replace("%1",
					Main.msgSys.getMsg(subjectUtilitiesButtons.get(btn).getSubject().name())));
			for (ButtonWithWindow btnWithWindow : subjectUtilitiesButtons.get(btn).getButtonsAndWindows())
				btnWithWindow.getButton().setText(Main.msgSys.getMsg(btnWithWindow.getWindow()!=null?btnWithWindow.getWindow().name():btnWithWindow.getWindow2().name()));
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