package ru.alexanderdv.schooltester.utilities.fx;

import java.io.File;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.alexanderdv.fxutilities.components.FXWindow;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.simpleutilities.SystemUtils;

public abstract class SchoolTesterFXWindow extends FXWindow
{

	public SchoolTesterFXWindow(Stage stage, String title, Image icon, Pane panel, int type, boolean inDevelope,
			boolean resizable, boolean fxScened)
	{
		super(stage, title, icon, panel, type, inDevelope, resizable, fxScened);
		setSize(Main.getSavedSize(new File("Data/Common/WindowSizes/" + name() + ".size")));
	}

	@Override
	public void onMenuCreated()
	{
		EventHandler<ActionEvent> actionHandler = (event) ->
		{
			Main.msgSys.setLanguage(((RadioMenuItem) event.getSource()).getText().replace("(", ":").split(":")[1]
					.replace(")", ":").split(":")[0]);
			Main.instance.updateAllLabels();
		};
		languageBtns = new HashMap<String, RadioMenuItem>();

		ToggleGroup group = new ToggleGroup();

		EventHandler<Event> eh = e -> e.consume();
		for (String language : Main.msgSys.getMessages().keySet())
		{
			RadioMenuItem btn = new RadioMenuItem(
					Main.msgSys.getMsg("currentLanguageName", language) + " (" + language + ")");
			btn.setSelected(Main.msgSys.getLanguage().equals(language));
			getLanguage().getItems().add(btn);
			languageBtns.put(language, btn);
			btn.setMnemonicParsing(false);
			btn.setOnAction(actionHandler);
			btn.setOnMenuValidation(eh);
			btn.setToggleGroup(group);
		}
		privacyPolicy.setOnAction(
				e -> FXDialogsGenerator.showFXDialog(stage, Main.msgSys.getMsg("privacyPolicyText"), null, true));
		usersManual.setOnAction(
				e -> FXDialogsGenerator.showFXDialog(stage, Main.msgSys.getMsg("usersManualText"), null, true));
		site.setOnAction(event -> SystemUtils.openUrl(Main.msgSys.getMsg("site")));
		file.setMnemonicParsing(false);
		window.setMnemonicParsing(false);
		settings.setMnemonicParsing(false);
		language.setMnemonicParsing(false);
		help.setMnemonicParsing(false);
		privacyPolicy.setMnemonicParsing(false);
		usersManual.setMnemonicParsing(false);
		site.setMnemonicParsing(false);

		file.setOnMenuValidation(eh);
		window.setOnMenuValidation(eh);
		settings.setOnMenuValidation(eh);
		language.setOnMenuValidation(eh);
		help.setOnMenuValidation(eh);
		privacyPolicy.setOnMenuValidation(eh);
		usersManual.setOnMenuValidation(eh);
		site.setOnMenuValidation(eh);
	}

	public void updateLabels()
	{
		file.setText(Main.msgSys.getMsg("file"));
		window.setText(Main.msgSys.getMsg("window"));
		settings.setText(Main.msgSys.getMsg("settings"));
		{
			getLanguage().setText(Main.msgSys.getMsg("language"));
		}
		help.setText(Main.msgSys.getMsg("help"));
		{
			privacyPolicy.setText(Main.msgSys.getMsg("privacyPolicy"));
			usersManual.setText(Main.msgSys.getMsg("usersManual"));
			site.setText(Main.msgSys.getMsg("siteLink"));
		}
	}
}
