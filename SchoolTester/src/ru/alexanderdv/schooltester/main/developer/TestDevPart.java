package ru.alexanderdv.schooltester.main.developer;

import java.awt.MouseInfo;
import java.net.URL;
import java.util.ArrayList;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.Question.QuestionType;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.5.0a
 */
public class TestDevPart extends ProtectedFXWindow
{
	private TabPane tabPane;
	private Button addNewTestButton;

	public TestDevPart(String secondaryTitle, URL url)
	{
		super(secondaryTitle, url, 1, 1);
		initialize();
	}

	public TestDevPart(String secondaryTitle, AnchorPane panel)
	{
		super(secondaryTitle, panel, 1, 1);
		initialize();
	}

	private void initialize()
	{
		tabPane = new TabPane();
		panel.getChildren().add(tabPane);
		tabPane.setPrefSize(panel.getPrefWidth(), panel.getPrefHeight());
		addNewTestButton = new Button("+");
		panel.getChildren().add(addNewTestButton);
		addNewTestButton.setPrefSize(25, 25);
		addNewTestButton.setLayoutX(panel.getPrefWidth() - addNewTestButton.getPrefWidth() - 2);
		addNewTestButton.setLayoutY(2);
		addNewTestButton.setOnAction(e ->
		{
			String name = "New test ";
			for (int i = 1;; i++)
			{
				boolean exist = false;
				for (Tab t : tabPane.getTabs())
					if (t.getText().equals(name + i))
						exist = true;
				if (!exist)
				{
					name += i;
					break;
				}
			}
			TestDevTab tab = new TestDevTab(name);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().select(tab);
			tab.initialize();
		});
		addNewTestButton.fire();
	}

	@Override
	public void updateLabelsInPart()
	{
		super.updateLabelsInPart();
		for (Tab t : tabPane.getTabs())
			if (t instanceof TestDevTab)
				((TestDevTab) t).updateLabels();
	};

	private static class TestDevTab extends Tab
	{
		private boolean initialized;
		private Tab codeTab, constructorTab;
		private TitledPane[] questionTypes;

		public TestDevTab(String name)
		{
			super(name);
		}

		public void initialize()
		{
			if (initialized)
				throw new NullPointerException("Already initialized!");
			setContent(new TabPane(codeTab = new Tab("Code"), constructorTab = new Tab("Constructor")));
			TextArea area;
			codeTab.setContent(area = new TextArea());
			area.setOnKeyPressed(e ->
			{
				if (e.isControlDown())
					if (e.getCode() == KeyCode.SPACE)
						for (int i = area.getCaretPosition(); i >= 0; i--)
							if (i < area.getText().length() ? !Character.isLetter(area.getText().charAt(i)) && !Character.isDigit(area.getText().charAt(i))
									: true)
							{
								ArrayList<String> keyWords = getKeyWordsFrom(area.getText(i, area.getCaretPosition()));
								MenuItem[] items = new MenuItem[keyWords.size()];
								for (int r = 0; r < items.length; r++)
								{
									items[r] = new MenuItem(keyWords.get(r));
									int ii = i, rr = r;
									items[r].setOnAction(ev ->
									{
										String textToCaret = area.getText(0, ii) + keyWords.get(rr) + ": ";
										area.setText(textToCaret + area.getText(area.getCaretPosition(), area.getText().length()));
										area.positionCaret(textToCaret.length());
									});
								}
								area.setContextMenu(new ContextMenu(items));
								area.getContextMenu().show(area, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
								area.getContextMenu().requestFocus();
								break;
							}
			});
			area.setOnKeyReleased(e ->
			{
				String textToCaret = area.getText(0, Math.max(0, Math.min(area.getCaretPosition(), area.getText().length() - 1))), text;
				if (e.getCode() == KeyCode.ENTER && area.getText().charAt(Math.min(area.getCaretPosition(), area.getText().length() - 1)) == '\n')
				{
					String tabs = getStartTabs(textToCaret.substring(Math.max(0, textToCaret.lastIndexOf("\n") + 1), textToCaret.length())) + (textToCaret
							.replace("\t", "").replace(" ", "").endsWith(":") ? "\t" : "");
					area.setText(text = (textToCaret + "\n" + tabs) + area.getText(area.getCaretPosition(), area.getText().length()));
					area.positionCaret(text.length());
				}
			});
			Accordion pane = new Accordion();
			questionTypes = new TitledPane[QuestionType.values().length];
			for (int i = 0; i < QuestionType.values().length; i++)
			{
				pane.getPanes().add(questionTypes[i] = new TitledPane(QuestionType.values()[i].name(), new AnchorPane()));
				MenuItem add;
				@SuppressWarnings("unused")
				CheckMenuItem randomize;
				Menu questionsToTest;
				questionTypes[i].setContextMenu(new ContextMenu(randomize = new CheckMenuItem("Randomize"), questionsToTest = new Menu("Questions to test"),
						add = new MenuItem("Add new question")));
				ToggleGroup group = new ToggleGroup();
				RadioMenuItem r;
				questionsToTest.getItems().add(r = new RadioMenuItem(questionsToTest.getItems().size() + ""));
				r.setToggleGroup(group);
				GridPane questions;
				questionTypes[i].setContent(questions = new GridPane());
				add.setOnAction(e ->
				{
					Button btn;
					questions.add(btn = new Button("Question " + (questions.getChildren().size() + 1)), 0, questions.getChildren().size());
					MenuItem remove;
					btn.setContextMenu(new ContextMenu(remove = new MenuItem("Remove")));
					remove.setOnAction(e2 ->
					{
						questions.getChildren().remove(btn);
						questionsToTest.getItems().remove(questionsToTest.getItems().size() - 1);
					});
					RadioMenuItem rb;
					questionsToTest.getItems().add(rb = new RadioMenuItem(questionsToTest.getItems().size() + ""));
					rb.setToggleGroup(group);
				});
			}
			Pane pane2 = new Pane();
			constructorTab.setContent(new SplitPane(pane, pane2));
			initialized = true;
		}

		private String getStartTabs(String text)
		{
			String s = "";
			for (char c : text.toCharArray())
				if (c == '\t')
					s += c;
				else break;
			return s;
		}

		private static final ArrayList<String> keyWords = new ArrayList<String>();
		static
		{
			keyWords.add(MessageSystem.getMsg("question", "en_uk"));
			keyWords.add(MessageSystem.getMsg("questions", "en_uk"));
			keyWords.add(MessageSystem.getMsg("answer", "en_uk"));
			keyWords.add(MessageSystem.getMsg("answers", "en_uk"));
		}

		private ArrayList<String> getKeyWordsFrom(String text)
		{
			ArrayList<String> list = new ArrayList<String>();
			for (String s : keyWords)
				if (s.startsWith(text))
					list.add(s);
			return list;
		}

		public void updateLabels()
		{
			if (!initialized)
				throw new NullPointerException("Must be initialized!");
			for (int i = 0; i < QuestionType.values().length; i++)
				questionTypes[i].setText(msgSys.getMsg("questionTo" + QuestionType.values()[i].name()));
		}
	}

}
