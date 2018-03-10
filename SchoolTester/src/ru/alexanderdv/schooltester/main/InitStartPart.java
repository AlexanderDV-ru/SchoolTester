package ru.alexanderdv.schooltester.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import ru.alexanderdv.schooltester.main.utils.CrossWordGeneratorPart;
import ru.alexanderdv.schooltester.utilities.FXDialogsGenerator;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class InitStartPart
{
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
	public Button openTeachersTestsControlPart, openTestDevPart, openCrossWordGeneratorPart;

	@FXML
	public void initialize()
	{
		instance = this;
		int o = 10;
		openTeachersTestsControlPart = new Button(Main.msgSys.getMsg("openTeachersTestsControlPart"));
		openTeachersTestsControlPart.setOnAction(e ->
		{
			try
			{
				Main.getTeachersTestsControlPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.instance.socket);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance.getStage(), null, "To work sign in!", 0, 0, Main.isFxWindowFrame(), true);
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
				Main.getTestDevPart().open(StartPart.instance.getStage(), AccountsPart.account.get(), Main.instance.socket);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance.getStage(), null, "To work sign in!", 0, 0, Main.isFxWindowFrame(), true);
			}
		});
		// specialPane.getChildren().add(openTestDevPart);
		openTestDevPart.setPrefWidth(special.getPrefWidth() - o * 2);
		openTestDevPart.setLayoutX(o);

		openCrossWordGeneratorPart = new Button(Main.msgSys.getMsg("openCrossWordGeneratorPart"));
		openCrossWordGeneratorPart.setOnAction(e ->
		{
			try
			{
				Main.getCrossWordGeneratorPart().open(CrossWordGeneratorPart.instance.getStage(), AccountsPart.account.get(), Main.instance.socket);
			}
			catch (Exception e1)
			{
				FXDialogsGenerator.showFXDialog(StartPart.instance.getStage(), null, "To work sign in!", 0, 0, Main.isFxWindowFrame(), true);
			}
		});
		commonPane.getChildren().add(openCrossWordGeneratorPart);
		openCrossWordGeneratorPart.setPrefWidth(common.getPrefWidth() - o * 2);
		openCrossWordGeneratorPart.setLayoutX(o);
	}
}
