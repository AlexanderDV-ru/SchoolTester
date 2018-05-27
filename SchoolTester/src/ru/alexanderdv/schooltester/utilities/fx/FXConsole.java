package ru.alexanderdv.schooltester.utilities.fx;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class FXConsole extends FXWindow
{
	private String baked;
	@FXML
	private TextArea text;

	public FXConsole(String secondaryTitle, URL url, boolean inDevelope)
	{
		super(secondaryTitle, url, 1, inDevelope, true);
		baked = "";
	}

	@Override
	protected void _resize(int w, int h)
	{

	}

	@FXML
	private void initialize()
	{
		text.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 1), new CornerRadii(0), new Insets(0))));

		text.setOnKeyPressed(e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				text.setEditable(false);
				baked = text.getText();
			}
			if (text.getText().length() < baked.length())
				text.setText(baked);
		});
		text.setOnKeyReleased(e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				text.setEditable(true);
			}
			if (text.getText().length() < baked.length())
				text.setText(baked);
		});
	}

	@Override
	public String name()
	{
		return "console";
	}
}
