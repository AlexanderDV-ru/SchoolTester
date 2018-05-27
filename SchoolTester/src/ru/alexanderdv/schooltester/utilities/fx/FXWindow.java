package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.ByteUtils;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.types.StageContainer;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public abstract class FXWindow extends StageContainer
{
	protected static final MessageSystem msgSys = Main.msgSys;
	protected Pane panel, mainPanel;
	// protected ScrollPane scroller;
	protected FXScene fxScene;

	protected MenuBar menubar;
	protected Menu window, settings;
	protected Menu language;
	protected Menu help;
	protected CheckMenuItem fxWindowFrameState;
	protected HashMap<String, RadioMenuItem> languageBtns;
	protected MenuItem privacyPolicy, usersManual, site;
	protected boolean inDevelope;
	protected Pane adPane;
	public static final HashMap<String, FXWindow> windows = new HashMap<String, FXWindow>();

	public FXWindow(String secondaryTitle, Pane panel, int type, boolean inDevelope, boolean resizable)
	{
		super(new Stage());
		windows.put(name(), this);
		Dimension dimension;
		try
		{
			byte[] bytes = SystemUtils.readFile(new File(name() + ".windowinf"));
			if (bytes != null)
				dimension = (Dimension) ByteUtils.byteArrayToObject(bytes);
			else dimension = null;
		}
		catch (Exception e)
		{
			dimension = null;
		}
		this.inDevelope = inDevelope;
		try
		{
			stage.setTitle(Main.program + (secondaryTitle != null && !secondaryTitle.equals("") ? " - " + secondaryTitle : ""));
			mainPanel = new AnchorPane();
			mainPanel.setPrefWidth(panel.getPrefWidth());
			mainPanel.getChildren().add(createMenu());
			mainPanel.getChildren().add(this.panel = panel);
			mainPanel.getChildren().add(createAdPane());
			panel.setLayoutY(Main.getMenuHeight());
			mainPanel.setPrefHeight(panel.getLayoutY() + panel.getPrefHeight());
			int mw=(int) mainPanel.getPrefWidth(),mh=(int) mainPanel.getPrefHeight();
			if (dimension != null)
			{
				mainPanel.setPrefWidth(dimension.getWidth());
				mainPanel.setPrefHeight(dimension.getHeight());
			}
			stage.initStyle(StageStyle.UNDECORATED);
			fxScene = new FXScene(stage, 1, stage.getTitle(), false, type);
			fxScene.setContent(mainPanel, mainPanel.getPrefWidth(), mainPanel.getPrefHeight(),mw,mh);
			stage.setScene(new Scene(fxScene));
			stage.sizeToScene();
			stage.setResizable(resizable);
			stage.getIcons().clear();
			stage.getIcons().add(new Image(getClass().getResource("/Icon32x.png").openStream()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	protected void createActionHandlers()
	{
		getMainPanel().prefWidthProperty().addListener((a, b, c) -> resize(c.intValue(), getMainPanel().getPrefHeight()));
		getMainPanel().prefHeightProperty().addListener((a, b, c) -> resize(getMainPanel().getPrefWidth(), c.intValue()));
	}

	public final void resize()
	{
		resize(getMainPanel().getPrefWidth(), getMainPanel().getPrefHeight());
	}

	private final void resize(double panelWithMenuWidth, double panelWithMenuHeight)
	{
		_resize((int) panelWithMenuWidth, (int) panelWithMenuHeight - Main.getMenuHeight());
	}

	protected abstract void _resize(int w, int h);

	private Pane createAdPane()
	{
		this.adPane = new Pane();
		panel.prefHeightProperty().addListener((e, oldV, newV) -> adPane.setLayoutY(Main.getMenuHeight() + newV.intValue()));
		mainPanel.prefWidthProperty().addListener((e, oldV, newV) -> adPane.setPrefWidth(newV.intValue()));
		return adPane;
	}

	public FXWindow(String secondaryTitle, URL url, int type, boolean inDevelope, boolean resizable)
	{
		this(secondaryTitle, load(url), type, inDevelope, resizable);
	}

	private static AnchorPane load(URL url)
	{
		try
		{
			return (AnchorPane) FXMLLoader.load(url);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new AnchorPane();
		}
	}

	private final Stage _open(Rectangle parent)
	{
		stage.show();
		stage.requestFocus();
		if (parent != null)
		{
			stage.setX(Math.max(FXScene.minX + 1, Math.min(Math.min(parent.getX() + parent.getWidth() / 2 - stage.getWidth() / 2, FXScene.maxX - stage
					.getWidth()), FXScene.maxX - 1)));
			stage.setY(Math.max(FXScene.minY + 1, Math.min(Math.min(parent.getY() + parent.getHeight() / 2 - stage.getHeight() / 2, FXScene.maxY - stage
					.getHeight()), FXScene.maxY - 1)));
		}
		return stage;
	}

	public Stage open(Stage parent)
	{
		return _open(parent != null ? new Rectangle((int) parent.getX(), (int) parent.getY(), (int) parent.getWidth(), (int) parent.getHeight()) : null);
	}

	public Stage open(Rectangle parent)
	{
		return _open(parent);
	}

	public void addOnCloseRequest(EventHandler<WindowEvent> event)
	{
		EventHandler<WindowEvent> eh = stage.getOnCloseRequest();
		stage.setOnCloseRequest(e ->
		{
			if (eh != null)
				eh.handle(e);
			if (event != null)
				event.handle(e);
		});
	}

	public void hide()
	{
		stage.hide();
	}

	public void min()
	{
		stage.setIconified(true);
	}

	public MenuBar createMenu()
	{
		menubar = new MenuBar();
		menubar.prefWidthProperty().bind(mainPanel.prefWidthProperty());
		menubar.setPrefHeight(Main.getMenuHeight());
		menubar.getMenus().add(window = new Menu());
		menubar.getMenus().add(settings = new Menu());
		settings.getItems().add(setLanguage(new Menu()));
		menubar.getMenus().add(help = new Menu());
		help.getItems().add(privacyPolicy = new MenuItem());
		help.getItems().add(usersManual = new MenuItem());
		help.getItems().add(site = new MenuItem());
		EventHandler<ActionEvent> actionHandler = (event) ->
		{
			msgSys.setLanguage(((RadioMenuItem) event.getSource()).getText().replace("(", ":").split(":")[1].replace(")", ":").split(":")[0]);
			Main.instance.updateAllLabels();
		};
		languageBtns = new HashMap<String, RadioMenuItem>();

		EventHandler<Event> eh = e -> e.consume();
		for (String language : MessageSystem.getMessages().keySet())
		{
			RadioMenuItem btn = new RadioMenuItem(MessageSystem.getMsg("currentLanguageName", language) + " (" + language + ")");
			getLanguage().getItems().add(btn);
			languageBtns.put(language, btn);
			btn.setMnemonicParsing(false);
			btn.setOnAction(actionHandler);
			btn.setOnMenuValidation(eh);
		}
		privacyPolicy.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("privacyPolicyText"), 0, null, true));
		usersManual.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("usersManualText"), 0, null, true));
		site.setOnAction(event -> SystemUtils.openUrl(msgSys.getMsg("site")));
		window.setMnemonicParsing(false);
		settings.setMnemonicParsing(false);
		language.setMnemonicParsing(false);
		help.setMnemonicParsing(false);
		// fxWindowFrameState.setMnemonicParsing(false);
		privacyPolicy.setMnemonicParsing(false);
		usersManual.setMnemonicParsing(false);
		site.setMnemonicParsing(false);

		window.setOnMenuValidation(eh);
		settings.setOnMenuValidation(eh);
		language.setOnMenuValidation(eh);
		help.setOnMenuValidation(eh);
		// fxWindowFrameState.setOnMenuValidation(eh);
		privacyPolicy.setOnMenuValidation(eh);
		usersManual.setOnMenuValidation(eh);
		site.setOnMenuValidation(eh);
		return menubar;
	}

	public void updateLabelsInPart()
	{
		window.setText(msgSys.getMsg("window"));
		settings.setText(msgSys.getMsg("settings"));
		{
			getLanguage().setText(msgSys.getMsg("language"));
		}
		help.setText(msgSys.getMsg("help"));
		{
			privacyPolicy.setText(msgSys.getMsg("privacyPolicy"));
			usersManual.setText(msgSys.getMsg("usersManual"));
			site.setText(msgSys.getMsg("siteLink"));
		}
	}

	private boolean hidedToTime = false;

	public void hideToTime()
	{
		if (stage.isShowing())
		{
			stage.hide();
			hidedToTime = true;
		}
	}

	public ReadOnlyBooleanProperty focusedProperty()
	{
		return stage.focusedProperty();
	}

	public void showIfHided()
	{
		if (hidedToTime)
		{
			stage.show();
			hidedToTime = false;
		}
	}

	public boolean isVisible()
	{
		return stage.isShowing();
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) stage.getX(), (int) stage.getY(), (int) stage.getWidth(), (int) stage.getHeight());
	}

	public void close()
	{
		stage.close();
	}

	public Menu getLanguage()
	{
		return language;
	}

	public Menu setLanguage(Menu language)
	{
		this.language = language;
		return language;
	}

	/**
	 * @return the panel
	 */
	public Pane getPanel()
	{
		return panel;
	}

	/**
	 * @param panel
	 *            the panel to set
	 */
	public void setPanel(Pane panel)
	{
		this.panel = panel;
	}

	/**
	 * @return the mainPanel
	 */
	public Pane getMainPanel()
	{
		return mainPanel;
	}

	/**
	 * @param mainPanel
	 *            the mainPanel to set
	 */
	public void setMainPanel(Pane mainPanel)
	{
		this.mainPanel = mainPanel;
	}

	// /**
	// * @return the scroller
	// */
	// public ScrollPane getScroller()
	// {
	// return scroller;
	// }
	//
	// /**
	// * @param scroller
	// * the scroller to set
	// */
	// public void setScroller(ScrollPane scroller)
	// {
	// this.scroller = scroller;
	// }

	/**
	 * @return the fxScene
	 */
	public FXScene getFxScene()
	{
		return fxScene;
	}

	/**
	 * @param fxScene
	 *            the fxScene to set
	 */
	public void setFxScene(FXScene fxScene)
	{
		this.fxScene = fxScene;
	}

	/**
	 * @return the menubar
	 */
	public MenuBar getMenubar()
	{
		return menubar;
	}

	/**
	 * @param menubar
	 *            the menubar to set
	 */
	public void setMenubar(MenuBar menubar)
	{
		this.menubar = menubar;
	}

	/**
	 * @return the window
	 */
	public Menu getWindow()
	{
		return window;
	}

	/**
	 * @param window
	 *            the window to set
	 */
	public void setWindow(Menu window)
	{
		this.window = window;
	}

	/**
	 * @return the settings
	 */
	public Menu getSettings()
	{
		return settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(Menu settings)
	{
		this.settings = settings;
	}

	/**
	 * @return the help
	 */
	public Menu getHelp()
	{
		return help;
	}

	/**
	 * @param help
	 *            the help to set
	 */
	public void setHelp(Menu help)
	{
		this.help = help;
	}

	/**
	 * @return the fxWindowFrameState
	 */
	public CheckMenuItem getFxWindowFrameState()
	{
		return fxWindowFrameState;
	}

	/**
	 * @param fxWindowFrameState
	 *            the fxWindowFrameState to set
	 */
	public void setFxWindowFrameState(CheckMenuItem fxWindowFrameState)
	{
		this.fxWindowFrameState = fxWindowFrameState;
	}

	/**
	 * @return the privacyPolicy
	 */
	public MenuItem getPrivacyPolicy()
	{
		return privacyPolicy;
	}

	/**
	 * @param privacyPolicy
	 *            the privacyPolicy to set
	 */
	public void setPrivacyPolicy(MenuItem privacyPolicy)
	{
		this.privacyPolicy = privacyPolicy;
	}

	/**
	 * @return the usersManual
	 */
	public MenuItem getUsersManual()
	{
		return usersManual;
	}

	/**
	 * @param usersManual
	 *            the usersManual to set
	 */
	public void setUsersManual(MenuItem usersManual)
	{
		this.usersManual = usersManual;
	}

	/**
	 * @return the site
	 */
	public MenuItem getSite()
	{
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(MenuItem site)
	{
		this.site = site;
	}

	public abstract String name();

	public boolean inDevelope()
	{
		return inDevelope;
	}

	public void saveWindowInfo()
	{
		SystemUtils.writeFile(new File(name() + ".windowinf"), ByteUtils.objectToByteArray(new Dimension((int) mainPanel.getPrefWidth(), (int) mainPanel
				.getPrefHeight())));
	}
}
