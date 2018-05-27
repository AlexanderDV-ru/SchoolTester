package ru.alexanderdv.schooltester.main.utilities;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.AccountsPart;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.StartPart;
import ru.alexanderdv.schooltester.utilities.enums.Subject;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.FXWindow;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class SubjectUtilitiesPart extends FXWindow
{
	private final VBox content;
	private final Subject subject;
	private final ArrayList<ButtonWithWindow> buttonsAndWindows;

	@Override
	protected void _resize(int w, int h)
	{

	}

	public SubjectUtilitiesPart(Subject subject, boolean inDevelope, ButtonWithWindow... buttonsAndWindows)
	{
		super(null, createAnchorPane(300, 400), 1, inDevelope, false);
		this.subject = subject;
		TitledPane title = new TitledPane(msgSys.getMsg(subject.name()), content = new VBox());
		title.setCollapsible(false);
		title.setAlignment(Pos.CENTER);
		title.setTextAlignment(TextAlignment.CENTER);
		panel.getChildren().add(title);
		title.setPrefSize(panel.getPrefWidth(), panel.getPrefHeight());
		content.setPrefSize(title.getPrefWidth(), title.getPrefHeight());
		int o = 10;
		this.buttonsAndWindows = new ArrayList<ButtonWithWindow>();
		for (ButtonWithWindow buttonWithWindow : buttonsAndWindows)
		{
			this.buttonsAndWindows.add(buttonWithWindow);
			buttonWithWindow.getButton().setOnAction(e ->
			{
				Main.getAccountsPart().close();
				if (buttonWithWindow.getWindow() instanceof ProtectedFXWindow)
					try
					{
						((ProtectedFXWindow) buttonWithWindow.getWindow()).open(StartPart.instance.getStage(), AccountsPart.account.get(),
								Main.client);
					}
					catch (Exception e1)
					{
						FXDialogsGenerator.showFXDialog(StartPart.instance, (Stage) null, msgSys.getMsg("signInToWork"), 0, null, true);
					}
				else buttonWithWindow.getWindow().open(StartPart.instance.getStage());
			});
			content.getChildren().add(buttonWithWindow.getButton());
			buttonWithWindow.getButton().setPrefWidth(content.getPrefWidth() - o * 2);
			buttonWithWindow.getButton().setLayoutX(o);
			buttonWithWindow.getButton().setTextFill(buttonWithWindow.getWindow().inDevelope() ? new Color(0.8, 0, 0, 1) : Color.BLACK);
		}
	}

	/**
	 * @return the buttonsAndWindows
	 */
	public ArrayList<ButtonWithWindow> getButtonsAndWindows()
	{
		return buttonsAndWindows;
	}

	public static class ButtonWithWindow
	{
		private Button button;
		private FXWindow window;

		public ButtonWithWindow(Button button, FXWindow window)
		{
			super();
			this.button = button;
			this.window = window;
		}

		/**
		 * @return the button
		 */
		public Button getButton()
		{
			return button;
		}

		/**
		 * @param button
		 *            the button to set
		 */
		public void setButton(Button button)
		{
			this.button = button;
		}

		/**
		 * @return the window
		 */
		public FXWindow getWindow()
		{
			return window;
		}

		/**
		 * @param window
		 *            the window to set
		 */
		public void setWindow(FXWindow window)
		{
			this.window = window;
		}

	}

	public Subject getSubject()
	{
		return subject;
	}

	private static AnchorPane createAnchorPane(int width, int height)
	{
		AnchorPane pane = new AnchorPane();
		pane.setPrefSize(width, height);
		return pane;
	}

	@Override
	public String name()
	{
		return "subjectUtilities";
	}

	@Override
	public boolean inDevelope()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
