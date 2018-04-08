package ru.alexanderdv.schooltester.main;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.utils.SubjectUtilitiesPart;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
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
	public Button openTeachersTestsControlPart, openTestDevPart;
	public ArrayList<Button> subjectUtilitiesButtons;

	@FXML
	public void initialize()
	{
		instance = this;
		subjectUtilitiesButtons = new ArrayList<Button>();
		int o = 10;
		openTeachersTestsControlPart = new Button(Main.msgSys.getMsg("openTeachersTestsControlPart"));
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

		openTestDevPart = new Button(Main.msgSys.getMsg("openTestDevPart"));
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
		// specialPane.getChildren().add(openTestDevPart);
		openTestDevPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTestDevPart.setLayoutX(o);
		int y = 0;
		for (SubjectUtilitiesPart subjectUtilities : Main.getSubjectUtilitiesParts())
		{
			Button subjectUtilitiesButton = new Button(msgSys.getMsg("openSubjectUtilities").replace("%1", msgSys.getMsg(subjectUtilities.getSubject()
					.name())));
			subjectUtilitiesButtons.add(subjectUtilitiesButton);
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
