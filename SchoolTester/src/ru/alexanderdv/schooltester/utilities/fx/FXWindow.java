package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.types.StageContainer;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public abstract class FXWindow extends StageContainer
{
	protected static final MessageSystem msgSys = Main.msgSys;
	protected Pane panel, mainPanel;
	protected ScrollPane scroller;
	protected FXScene fxScene;

	protected MenuBar menubar;
	protected Menu window, settings;
	protected Menu language;
	protected Menu help;
	protected CheckMenuItem fxWindowFrameState;
	protected RadioMenuItem languageRU, languageEN;
	protected MenuItem privacyPolicy, usersManual, site;

	public FXWindow(String secondaryTitle, Pane panel, int type)
	{
		super(new Stage());
		try
		{
			stage.setTitle(Main.program + (secondaryTitle != null && !secondaryTitle.equals("") ? " - " + secondaryTitle : ""));
			mainPanel = new AnchorPane();
			scroller = new ScrollPane(this.panel = panel);
			mainPanel.getChildren().add(scroller);
			scroller.setLayoutY(Main.getMenuHeight());
			if (Main.isFxWindowFrame())
			{
				stage.initStyle(StageStyle.UNDECORATED);
				fxScene = new FXScene(stage, 1, stage.getTitle(), false, type);
				scroller.setPrefSize(Math.min(panel.getPrefWidth(), Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 10 - fxScene.getAddingWidth()),
						Math.min(panel.getPrefHeight(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Main.getMenuHeight() - 10 - fxScene
								.getAddingHeight()));
				mainPanel.setPrefSize(scroller.getPrefWidth(), Main.getMenuHeight() + scroller.getPrefHeight());
				fxScene.setContent(mainPanel, mainPanel.getPrefWidth(), mainPanel.getPrefHeight());
				stage.setScene(new Scene(fxScene));
				stage.sizeToScene();
			}
			else
			{
				stage.initStyle(StageStyle.DECORATED);
				scroller.setPrefSize(Math.min(panel.getPrefWidth(), Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 10 - (20)), Math.min(panel
						.getPrefHeight(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Main.getMenuHeight() - 10 - (50)));
				mainPanel.setPrefSize(scroller.getPrefWidth(), Main.getMenuHeight() + scroller.getPrefHeight());
				stage.setScene(new Scene(mainPanel));
				stage.sizeToScene();
			}
			scroller.setHbarPolicy(scroller.getPrefWidth() == panel.getPrefWidth() ? ScrollBarPolicy.NEVER : ScrollBarPolicy.ALWAYS);
			scroller.setVbarPolicy(scroller.getPrefHeight() == panel.getPrefHeight() ? ScrollBarPolicy.NEVER : ScrollBarPolicy.ALWAYS);
			stage.setResizable(false);
			stage.getIcons().clear();
			stage.getIcons().add(new Image(getClass().getResource("/Icon32x.png").openStream()));

			mainPanel.getChildren().add(createMenu());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public FXWindow(String secondaryTitle, URL url, int type)
	{
		this(secondaryTitle, load(url), type);
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
		menubar.setPrefSize(mainPanel.getPrefWidth(), Main.getMenuHeight());
		menubar.getMenus().add(window = new Menu());
		// window.getItems().add(fxWindowFrameState = new CheckMenuItem("Window frame is FX"));
		// fxWindowFrameState.setSelected(Main.fxWindowFrame);
		// fxWindowFrameState.setOnAction(e -> Main.instance.changeFXWindowFrameState(fxWindowFrameState.isSelected()));
		menubar.getMenus().add(settings = new Menu());
		settings.getItems().add(setLanguage(new Menu()));
		getLanguage().getItems().add(languageEN = new RadioMenuItem("English (en_uk)"));
		getLanguage().getItems().add(languageRU = new RadioMenuItem("Русский (ru_ru)"));
		menubar.getMenus().add(help = new Menu());
		help.getItems().add(privacyPolicy = new MenuItem());
		help.getItems().add(usersManual = new MenuItem());
		help.getItems().add(site = new MenuItem());
		EventHandler<ActionEvent> actionHandler = (event) ->
		{
			msgSys.setLanguage(((RadioMenuItem) event.getSource()).getText().replace("(", ":").split(":")[1].replace(")", ":").split(":")[0]);
			Main.instance.updateAllLabels();
		};
		languageEN.setOnAction(actionHandler);
		languageRU.setOnAction(actionHandler);
		privacyPolicy.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("privacyPolicyText"), 0, 0, Main.isFxWindowFrame(),
				true));
		usersManual.setOnAction(e -> FXDialogsGenerator.showFXDialog(stage, (Stage) null, msgSys.getMsg("usersManualText"), 0, 0, Main.isFxWindowFrame(),
				true));
		site.setOnAction(event -> SystemUtils.openUrl(msgSys.getMsg("site")));

		EventHandler<Event> eh = e -> e.consume();
		window.setMnemonicParsing(false);
		settings.setMnemonicParsing(false);
		language.setMnemonicParsing(false);
		help.setMnemonicParsing(false);
		// fxWindowFrameState.setMnemonicParsing(false);
		languageEN.setMnemonicParsing(false);
		languageRU.setMnemonicParsing(false);
		privacyPolicy.setMnemonicParsing(false);
		usersManual.setMnemonicParsing(false);
		site.setMnemonicParsing(false);

		window.setOnMenuValidation(eh);
		settings.setOnMenuValidation(eh);
		language.setOnMenuValidation(eh);
		help.setOnMenuValidation(eh);
		// fxWindowFrameState.setOnMenuValidation(eh);
		languageEN.setOnMenuValidation(eh);
		languageRU.setOnMenuValidation(eh);
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

	/**
	 * @return the scroller
	 */
	public ScrollPane getScroller()
	{
		return scroller;
	}

	/**
	 * @param scroller
	 *            the scroller to set
	 */
	public void setScroller(ScrollPane scroller)
	{
		this.scroller = scroller;
	}

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
	 * @return the languageRU
	 */
	public RadioMenuItem getLanguageRU()
	{
		return languageRU;
	}

	/**
	 * @param languageRU
	 *            the languageRU to set
	 */
	public void setLanguageRU(RadioMenuItem languageRU)
	{
		this.languageRU = languageRU;
	}

	/**
	 * @return the languageEN
	 */
	public RadioMenuItem getLanguageEN()
	{
		return languageEN;
	}

	/**
	 * @param languageEN
	 *            the languageEN to set
	 */
	public void setLanguageEN(RadioMenuItem languageEN)
	{
		this.languageEN = languageEN;
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

	// /**
	// * @param stage
	// * the stage to set
	// */
	// public void setStage(Stage stage)
	// {
	// this.stage = stage;
	// }
}
