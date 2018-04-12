package ru.alexanderdv.schooltester.main;

import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.developer.TestDevPart;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.main.utilities.MarketPart;
import ru.alexanderdv.schooltester.main.utilities.SubjectUtilitiesPart;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class InitStartPart
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
	public ProgressBar serverConnectionQualityProgressbar;
	@FXML
	public Label serverConnectionQualityLabel;

	@FXML
	public Button openTeachersTestsControlPart, openTestDevPart, openMarketPart;
	public HashMap<Button, SubjectUtilitiesPart> subjectUtilitiesButtons;

	@FXML
	public void initialize()
	{
		instance = this;
		subjectUtilitiesButtons = new HashMap<Button, SubjectUtilitiesPart>();
		int o = 10;
		openTeachersTestsControlPart = new Button();
		openTeachersTestsControlPart.setTextFill(TeachersTestsControlPart.instance.inDevelope()?new Color(0.8,0,0,1):Color.BLACK);
		openTeachersTestsControlPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getTeachersTestsControlPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.instance.socket);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, 0, Main.isFxWindowFrame(), true);
			}
		});
		specialPane.getChildren().add(openTeachersTestsControlPart);
		openTeachersTestsControlPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTeachersTestsControlPart.setLayoutX(o);

		openTestDevPart = new Button();
		openTestDevPart.setTextFill(TestDevPart.instance.inDevelope()?new Color(0.8,0,0,1):Color.BLACK);
		openTestDevPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getTestDevPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.instance.socket);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, 0, Main.isFxWindowFrame(), true);
			}
		});
		openTestDevPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTestDevPart.setLayoutX(o);
		specialPane.getChildren().add(openTestDevPart);

		openMarketPart = new Button();
		openMarketPart.setTextFill(MarketPart.instance.inDevelope()?new Color(0.8,0,0,1):Color.BLACK);
		openMarketPart.setOnAction(e ->
		{
			try
			{
				Main.getAccountsPart().close();
				Main.getMarketPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.instance.socket);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, 0, Main.isFxWindowFrame(), true);
			}
		});
		openMarketPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openMarketPart.setLayoutX(o);
		specialPane.getChildren().add(openMarketPart);
		
		for (SubjectUtilitiesPart subjectUtilities : Main.getSubjectUtilitiesParts())
		{
			Button subjectUtilitiesButton = new Button(msgSys.getMsg("openSubjectUtilities").replace("%1", msgSys.getMsg(subjectUtilities.getSubject()
					.name())));
			subjectUtilitiesButton.setTextFill(subjectUtilities.inDevelope()?new Color(0.8,0,0,1):Color.BLACK);
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
}
