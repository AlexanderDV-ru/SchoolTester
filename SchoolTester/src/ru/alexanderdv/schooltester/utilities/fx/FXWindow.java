package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.simpleutilities.ByteUtils;
import ru.alexanderdv.simpleutilities.MathUtils;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.6a
 */
public abstract class FXWindow
{
	protected static final MessageSystem msgSys = Main.msgSys;
	protected Pane panel, mainPanel;
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
	public Stage stage;
	public static final HashMap<String, FXWindow> windows = new HashMap<String, FXWindow>();
	private final ArrayList<EventHandler<WindowEvent>> showListeners = new ArrayList<EventHandler<WindowEvent>>();

	public FXWindow(String secondaryTitle, Pane panel, int type, boolean inDevelope, boolean resizable)
	{
		stage = new Stage();
		this.panel = panel;
		this.inDevelope = inDevelope;
		System.out.println(name() + " loading...");
		windows.put(name(), this);
		Dimension dimension;
		try
		{
			byte[] bytes = SystemUtils.readFile(new File(name() + ".windowinf"));
			dimension = bytes != null ? (Dimension) ByteUtils.byteArrayToObject(bytes) : null;
		}
		catch (Exception e)
		{
			dimension = null;
		}
		try
		{
			stage.setTitle(Main.program
					+ (secondaryTitle != null && !secondaryTitle.equals("") ? " - " + secondaryTitle : ""));
			mainPanel = new AnchorPane(createMenu(), panel, createAdPane());
			mainPanel.setPrefWidth(panel.getPrefWidth());
			panel.setLayoutY(Main.getMenuHeight());
			mainPanel.setPrefHeight(panel.getLayoutY() + panel.getPrefHeight() + adPane.getPrefHeight());
			int mw = (int) mainPanel.getPrefWidth(), mh = (int) mainPanel.getPrefHeight();
			if (dimension != null)
			{
				mainPanel.setPrefWidth(dimension.getWidth());
				mainPanel.setPrefHeight(dimension.getHeight());
			}
			stage.initStyle(StageStyle.UNDECORATED);
			fxScene = new FXScene(stage, 1, stage.getTitle(), false, type);
			fxScene.setContent(mainPanel, mainPanel.getPrefWidth(), mainPanel.getPrefHeight(), mw, mh);
			stage.setScene(new Scene(fxScene));
			stage.sizeToScene();
			stage.setResizable(resizable);
			stage.getIcons().clear();
			stage.getIcons().add(
					new Image(getClass().getResource(Main.resourcesPath + "/images" + "/Icon32x.png").openStream()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public String getDataPath()
	{
		return Main.getAccountDataPath() + "/" + name();
	}

	protected void centrizeByText(Labeled n, int width, double height)
	{
		double w = MathUtils.size(n.getText(), java.awt.Font.decode(n.getFont().getName()+"-"+n.getFont().getStyle()+"-"+n.getFont().getSize())).getWidth() + 20;
		n.setLayoutX(width / 2 - w / 2);
		n.setPrefSize(w, height);
	}

	protected void setAligmentToCenter(Control... controls)
	{
		setAligmentToCenter(Arrays.asList(controls));
	}

	protected void setAligmentToCenter(Collection<? extends Control> controls)
	{
		for (Control control : controls)
			if (control instanceof Labeled)
			{
				((Labeled) control).setTextAlignment(TextAlignment.CENTER);
				((Labeled) control).setAlignment(Pos.CENTER);
				((Labeled) control).setContentDisplay(ContentDisplay.CENTER);
			}
			else if (control instanceof TextField)
				((TextField) control).setAlignment(Pos.CENTER);
	}

	protected void createActionHandlers()
	{
		getMainPanel().prefWidthProperty().addListener((a, b, c) -> resize());
		getMainPanel().prefHeightProperty().addListener((a, b, c) -> resize());
		EventHandler<WindowEvent> showListener = (event) ->
		{
			resize();
			for (EventHandler<WindowEvent> h : showListeners)
				h.handle(event);
		};
		stage.setOnShown(showListener);
		stage.onShownProperty().addListener((a, b, c) ->
		{
			if (c != showListener)
				stage.setOnShown(showListener);
		});
	}

	public void addShowListener(EventHandler<WindowEvent> listener)
	{
		showListeners.add(listener);
	}

	public final void resize()
	{
		//throw new NullPointerException();
		Platform.runLater(() -> resize(getMainPanel().getPrefWidth(), getMainPanel().getPrefHeight()));
	}

	private final void resize(double panelWithMenuWidth, double panelWithMenuHeight)
	{
		panel.setPrefWidth(panelWithMenuWidth);
		panel.setPrefHeight(panelWithMenuHeight - panel.getLayoutY() - adPane.getPrefHeight());
		adPane.setPrefWidth(panelWithMenuWidth);
		adPane.setLayoutY(panelWithMenuHeight - adPane.getPrefHeight());
		_resize((int) panel.getPrefWidth(), (int) panel.getPrefHeight());
	}

	protected abstract void _resize(int w, int h);

	private Pane createAdPane()
	{
		this.adPane = new Pane();
		adPane.setPrefHeight(50);
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
		if (parent != null)
		{
			stage.setX(parent.getX() + parent.getWidth() / 2 - stage.getWidth() / 2);
			stage.setY(parent.getY() + parent.getHeight() / 2 - stage.getHeight() / 2);
		}
		stage.show();
		stage.requestFocus();
		return stage;
	}

	public Stage open(Stage parent)
	{
		return _open(
				parent != null
						? new Rectangle((int) parent.getX(), (int) parent.getY(), (int) parent.getWidth(),
								(int) parent.getHeight())
						: null);
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
		menubar.prefWidthProperty().bind(panel.prefWidthProperty());
		menubar.setPrefHeight(Main.getMenuHeight());
		menubar.getMenus().add(window = new Menu());
		menubar.getMenus().add(settings = new Menu());
		settings.getItems().add(setLanguage(new Menu()));
		menubar.getMenus().add(help = new Menu());
		help.getItems().add(privacyPolicy = new MenuItem());
		help.getItems().add(usersManual = new MenuItem());
		help.getItems().add(site = new MenuItem());
		onMenuCreated();
		return menubar;
	}

	public void updateLabels()
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
		SystemUtils.writeFile(new File(name() + ".windowinf"), ByteUtils
				.objectToByteArray(new Dimension((int) mainPanel.getPrefWidth(), (int) mainPanel.getPrefHeight())));
	}
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
		privacyPolicy.setOnAction(e ->
		{
		});
		usersManual.setOnAction(e ->
		{
		});
		site.setOnAction(event -> SystemUtils.openUrl(Main.msgSys.getMsg("site")));
		window.setMnemonicParsing(false);
		settings.setMnemonicParsing(false);
		language.setMnemonicParsing(false);
		help.setMnemonicParsing(false);
		privacyPolicy.setMnemonicParsing(false);
		usersManual.setMnemonicParsing(false);
		site.setMnemonicParsing(false);

		window.setOnMenuValidation(eh);
		settings.setOnMenuValidation(eh);
		language.setOnMenuValidation(eh);
		help.setOnMenuValidation(eh);
		privacyPolicy.setOnMenuValidation(eh);
		usersManual.setOnMenuValidation(eh);
		site.setOnMenuValidation(eh);
	}
}
