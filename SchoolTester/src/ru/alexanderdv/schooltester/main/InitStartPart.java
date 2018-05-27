package ru.alexanderdv.schooltester.main;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.student.StudentsTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.teacher.TestDevPart;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class InitStartPart
{
	private static final MessageSystem msgSys = Main.msgSys;
	public static InitStartPart instance;
	@FXML
	public TitledPane mainPane;
	@FXML
	public TitledPane special, utils, common;
	@FXML
	public VBox specialPane, utilsPane, commonPane;
	@FXML
	public Label message;
	@FXML
	public ProgressBar serverConnectionSpeedProgressbar;
	@FXML
	public Label serverConnectionSpeedLabel;

	@FXML
	public Button openTeachersTestsControlPart, openStundentsTestsControlPart, openTestDevPart, openMarketPart;
	public HashMap<Button, SubjectUtilitiesPart> subjectUtilitiesButtons;

	@FXML
	public void initialize()
	{
		instance = this;
		subjectUtilitiesButtons = new HashMap<Button, SubjectUtilitiesPart>();
		int o = 10;

		openTeachersTestsControlPart = new Button();
		openTeachersTestsControlPart.setTextFill(TeachersTestsControlPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openTeachersTestsControlPart.setOnAction(e ->
		{
			try
			{
				System.out.println("mark1");
				Main.getAccountsPart().close();
				System.out.println("mark2");
				Main.getTeachersTestsControlPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.client);
				System.out.println("mark3");
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true);
			}
		});
		specialPane.getChildren().add(openTeachersTestsControlPart);
		openTeachersTestsControlPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTeachersTestsControlPart.setLayoutX(o);

		openStundentsTestsControlPart = new Button();
		openStundentsTestsControlPart.setTextFill(StudentsTestsControlPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openStundentsTestsControlPart.setOnAction(e ->
		{
			try
			{
				System.out.println("mark1");
				Main.getAccountsPart().close();
				System.out.println("mark2");
				Main.getStudentsTestsControlPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.client);
				System.out.println("mark3");
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true);
			}
		});
		specialPane.getChildren().add(openStundentsTestsControlPart);
		openStundentsTestsControlPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openStundentsTestsControlPart.setLayoutX(o);

		openTestDevPart = new Button();
		openTestDevPart.setTextFill(TestDevPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openTestDevPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getTestDevPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.client);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true);
			}
		});
		openTestDevPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTestDevPart.setLayoutX(o);
		specialPane.getChildren().add(openTestDevPart);

		openMarketPart = new Button();
		openMarketPart.setTextFill(MarketPart.instance.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		openMarketPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getMarketPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.client);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true);
			}
		});
		openMarketPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openMarketPart.setLayoutX(o);
		specialPane.getChildren().add(openMarketPart);

		for (SubjectUtilitiesPart subjectUtilities : Main.getSubjectUtilitiesParts())
		{
			Button subjectUtilitiesButton = new Button(msgSys.getMsg("openSubjectUtilities").replace("%1", msgSys.getMsg(subjectUtilities.getSubject()
					.name())));
			subjectUtilitiesButton.setTextFill(subjectUtilities.inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
			subjectUtilitiesButtons.put(subjectUtilitiesButton, subjectUtilities);
			subjectUtilitiesButton.setOnAction(e ->
			{
				Main.getAccountsPart().close();
				subjectUtilities.open(StartPart.instance.getStage());
			});
			commonPane.getChildren().add(subjectUtilitiesButton);
			subjectUtilitiesButton.setPrefWidth(common.getPrefWidth() - o * 2);
			subjectUtilitiesButton.setLayoutX(o);
		}
	}

	public void resize(int w, int h)
	{
		mainPane.setPrefWidth(w);
		message.setLayoutX(w / 2 - message.getWidth() / 2);
		serverConnectionSpeedLabel.setLayoutX(w / 2 - serverConnectionSpeedLabel.getWidth() - Main.spaceBetweenComponents / 2);
		serverConnectionSpeedProgressbar.setLayoutX(w / 2 + Main.spaceBetweenComponents / 2);

		int boxW = (w - Main.spaceBetweenComponents * 4) / 3;

		common.setLayoutX(boxW * 0 + Main.spaceBetweenComponents * 1);
		special.setLayoutX(boxW * 1 + Main.spaceBetweenComponents * 2);
		utils.setLayoutX(boxW * 2 + Main.spaceBetweenComponents * 3);

		common.setPrefWidth(boxW);
		special.setPrefWidth(boxW);
		utils.setPrefWidth(boxW);

		mainPane.setPrefHeight(h);
		common.setPrefHeight(h - Main.titleHeight - common.getLayoutY() - Main.spaceBetweenComponents);
		special.setPrefHeight(h - Main.titleHeight - special.getLayoutY() - Main.spaceBetweenComponents);
		utils.setPrefHeight(h - Main.titleHeight - utils.getLayoutY() - Main.spaceBetweenComponents);

		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.addAll(commonPane.getChildren());
		nodes.addAll(specialPane.getChildren());
		nodes.addAll(utilsPane.getChildren());
		for (Node node : nodes)
			if (node instanceof Region)
			{
				Region region = (Region) node;
				region.setLayoutX(Main.spaceBetweenComponents);
				region.setPrefWidth(boxW - Main.spaceBetweenComponents * 2);
			}
	}
}