package ru.alexanderdv.schooltester.main.developer;

import java.awt.MouseInfo;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Timer;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.SystemUtils;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.types.Question.QuestionType;
import ru.alexanderdv.simpleutilities.MathWithText;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class TestDevPart extends ProtectedFXWindow
{
	public static TestDevPart instance;
	private TabPane tabPane;
	private Button addNewTestButton;

	public TestDevPart(Pane pane,boolean inDevelope)
	{
		super(null, pane, 1, 1,inDevelope);
		instance=this;
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
		private Button save;

		public TestDevTab(String name)
		{
			super(name);
		}

		private static final HashMap<String, ArrayList<String>> keyWordsWithDigit = createKeyWordsWithDigit();
		private static final HashMap<String, ArrayList<String>> keyWordsWithoutDigit = createKeyWordsWithoutDigit();
		private static final HashMap<String, ArrayList<String>> keyWords = glue();

		private static HashMap<String, ArrayList<String>> glue()
		{
			HashMap<String, ArrayList<String>> d = new HashMap<String, ArrayList<String>>();
			for (String s : keyWordsWithDigit.keySet())
				d.put(s, keyWordsWithDigit.get(s));
			for (String s : keyWordsWithoutDigit.keySet())
				d.get(s).addAll(keyWordsWithoutDigit.get(s));
			return d;
		}

		private static final HashMap<String, String> KEYWORD_PATTERNS = createKeywordParents();

		private static HashMap<String, ArrayList<String>> createKeyWordsWithDigit()
		{
			HashMap<String, ArrayList<String>> keyWords = new HashMap<String, ArrayList<String>>();
			for (String language : MessageSystem.getMessages().keySet())
			{
				ArrayList<String> keyWordsToLanguage = new ArrayList<String>();
				keyWordsToLanguage.add(MessageSystem.getMsg("question", language) + "%d<question>");
				keyWordsToLanguage.add(MessageSystem.getMsg("answer", language) + "%d<answer>");
				keyWordsToLanguage.add(MessageSystem.getMsg("awardForAnswer", language) + "%d<awardForAnswer>");
				keyWordsToLanguage.add(MessageSystem.getMsg("answerIndex", language) + "%d<answerIndex>");
				keyWords.put(language, keyWordsToLanguage);
			}
			return keyWords;
		}

		private static HashMap<String, ArrayList<String>> createKeyWordsWithoutDigit()
		{
			HashMap<String, ArrayList<String>> keyWords = new HashMap<String, ArrayList<String>>();
			for (String language : MessageSystem.getMessages().keySet())
			{
				ArrayList<String> keyWordsToLanguage = new ArrayList<String>();
				keyWordsToLanguage.add("syntaxLanguage");
				keyWordsToLanguage.add(MessageSystem.getMsg("programVersion", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("colorType", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("testVersion", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("testCreationDate", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("testLanguage", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("testSubject", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("authors", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("description", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("maxTestTime", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("questions", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("pickOne", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("selectSome", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("enterText", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("distribution", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("matching", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("arrangement", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("answers", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("award", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("text", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("fontSize", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("ignoreCase", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("ignoredCharacters", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("minimalResult", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("questionsToTestAmount", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("answerFontSize", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("handleOnlyMaximal", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("awardsForAnswers", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("answersIndexes", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("number", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("index", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("indexesForNames", language));
				keyWordsToLanguage.add(MessageSystem.getMsg("naming", language));
				keyWords.put(language, keyWordsToLanguage);
			}
			return keyWords;
		}

		private static HashMap<String, String> createKeywordParents()
		{
			HashMap<String, String> keywordParents = new HashMap<String, String>();
			for (String language : MessageSystem.getMessages().keySet())
				keywordParents.put(language, "\\b(" + String.join("|", keyWordsWithoutDigit.get(language)) + "|" + replaceBetween("%d<", ">", "[0-9]+", String
						.join("|", keyWordsWithDigit.get(language))) + ")\\b");
			return keywordParents;

		}

		private static String replaceBetween(String first, String second, String replacement, String text)
		{
			for (; text.indexOf(first) < text.indexOf(second) && text.indexOf(first) != -1;)
				text = text.substring(0, text.indexOf(first)) + replacement + text.substring(text.indexOf(second) + second.length());
			return text;
		}

		private static HashMap<String, Pattern> createPatterns()
		{
			HashMap<String, Pattern> patterns = new HashMap<String, Pattern>();
			for (String language : MessageSystem.getMessages().keySet())
				patterns.put(language, Pattern.compile("(?<KEYWORD>" + KEYWORD_PATTERNS.get(language) + ")" + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
						+ "|(?<STRING>" + STRING_PATTERN + ")" + "|(?<COMMENT>" + COMMENT_PATTERN + ")" + "|(?<ERROR>" + ERROR_PATTERN + ")" + "|(?<NUMBER>"
						+ NUMBER_PATTERN + ")"));
			return patterns;
		}

		private static final String ERROR_PATTERN = "(\"(([^\"]+(\n|\\Z))|(\n|\\Z)))";
		private static final String COMMENT_PATTERN = "(\\A|\n)//[^\n]+";
		private static final String SEMICOLON_PATTERN = "\\:";
		private static final String NUMBER_PATTERN = "\\b[0-9]+\\b";
		private static final String STRING_PATTERN = "\"(([^\"^\n]+\")|\")";

		private StyleSpans<Collection<String>> computeHighlighting(String text)
		{
			Matcher matcher = PATTERNS.get(syntaxLanguage).matcher(text);
			int lastKwEnd = 0;
			StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
			while (matcher.find())
			{
				String styleClass = matcher.group("KEYWORD") != null ? "keyword"
						: matcher.group("SEMICOLON") != null ? "semicolon"
								: matcher.group("COMMENT") != null ? "comment"
										: matcher.group("ERROR") != null ? "error"
												: matcher.group("STRING") != null ? "string" : matcher.group("NUMBER") != null ? "number" : null;
				assert styleClass != null;
				spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
				spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
				lastKwEnd = matcher.end();
			}
			spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
			return spansBuilder.create();
		}

		private static final HashMap<String, Pattern> PATTERNS = createPatterns();
		private String testName;
		private Button delete;
		private HBox pane1;
		private TextField name1;

		public void initialize()
		{
			if (initialized)
				throw new NullPointerException("Already initialized!");
			testName = getText();

			CodeArea area = new CodeArea();
			save = new Button("save");
			delete = new Button("delete");
			name1 = new TextField();
			pane1 = new HBox();
			pane1.getChildren().add(name1);
			pane1.getChildren().add(save);
			pane1.getChildren().add(delete);
			area.setPrefHeight(getTabPane().getPrefHeight() - 30);
			VBox splitPane = new VBox();
			splitPane.getChildren().add(new TabPane(codeTab = new Tab("Code"), constructorTab = new Tab("Constructor")));
			splitPane.getChildren().add(pane1);
			setContent(splitPane);
			name1.setText(testName);
			codeTab.setContent(area);
			new Timer(100, e -> Platform.runLater(() -> setText(testName + (( (area.getText().equals(SystemUtils.readFile(new File("Tests/" + testName
					+ "/" + testName + ".test"), Charset.forName("cp1251"))))) ? "" : "*")))).start();
			save.setOnAction(e ->
			{
				area.replaceText(area.getText().replace("\t\n", " \n").replace(" \n", "\n"));
				testName = name1.getText();
				setText(testName);
				SystemUtils.writeFile(new File("Tests/" + testName + "/" + testName + ".test"), area.getText(), Charset.forName("cp1251"));
			});
			delete.setOnAction(e ->
			{
				getTabPane().getTabs().remove(TestDevTab.this);
				SystemUtils.removeFile(new File("Tests/" + testName));
			});
			String stylesheet = TestDevPart.class.getResource("/schooltester-keywords.css").toString();
			IntFunction<String> format = (digits -> " %" + digits + "d ");
			area.setParagraphGraphicFactory(LineNumberFactory.get(area, format));
			area.textProperty().addListener((obs, oldText, newText) ->
			{
				area.setStyleSpans(0, computeHighlighting(newText));
			});
			area.getStylesheets().add(stylesheet);

			class C
			{
				int q = 1, a = 1;
			}
			C c = new C();
			area.setOnKeyPressed(e ->
			{
				if (e.isControlDown())
					if (e.getCode() == KeyCode.SPACE)
						for (int i = area.getCaretPosition(); i >= 0; i--)
							if (i < area.getText().length() ? !Character.isLetter(area.getText().charAt(i)) && !Character.isDigit(area.getText().charAt(i))
									: true)
							{
								String s = String.join("\n", getKeyWordsFrom(area.getText(i, area.getCaretPosition())));
								if (area.getText().lastIndexOf(MessageSystem.getMsg("question", syntaxLanguage)) != -1)
									c.q = MathWithText.parseI(area.getText().substring(area.getText().lastIndexOf(MessageSystem.getMsg("question",
											syntaxLanguage)), area.getText().lastIndexOf(MessageSystem.getMsg("question", syntaxLanguage)) + area.getText()
													.substring(area.getText().lastIndexOf(MessageSystem.getMsg("question", syntaxLanguage))).indexOf(":")));
								else c.q = 1;
								if (c.q < 1)
									c.q = 1;
								if (area.getText().lastIndexOf(MessageSystem.getMsg("answer", syntaxLanguage)) != -1)
									c.a = MathWithText.parseI(area.getText().substring(area.getText().lastIndexOf(MessageSystem.getMsg("answer",
											syntaxLanguage)), area.getText().lastIndexOf(MessageSystem.getMsg("answer", syntaxLanguage)) + area.getText()
													.substring(area.getText().lastIndexOf(MessageSystem.getMsg("answer", syntaxLanguage))).indexOf(":")));
								else c.a = 1;
								if (c.a < 1)
									c.a = 1;
								String[] keyWords = s.replace("%d<question>", "" + c.q).replace("%d<answer>", "" + c.a).replace("%d<awardForAnswer>", "" + 1)
										.replace("%d<answerIndex>", "" + 1).split("\n");
								MenuItem[] items = new MenuItem[keyWords.length];
								for (int r = 0; r < items.length; r++)
								{
									items[r] = new MenuItem(keyWords[r]);
									int ii = i, rr = r;
									items[r].setOnAction(ev ->
									{
										area.replaceText(ii, ii, keyWords[rr] + ": " + area.getText(area.getCaretPosition(), area.getText().length()));
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
				String textToCaret = area.getText(0, Math.max(0, Math.min(area.getCaretPosition(), area.getText().length() - 1)));
				if (e.getCode() == KeyCode.ENTER && area.getText().charAt(Math.min(area.getCaretPosition(), area.getText().length() - 1)) == '\n')
				{
					String tabs = getStartTabs(textToCaret.substring(Math.max(0, textToCaret.lastIndexOf("\n") + 1), textToCaret.length())) + (textToCaret
							.replace("\t", "").replace(" ", "").endsWith(":") ? "\t" : "");
					area.replaceText(Math.max(0, Math.min(area.getCaretPosition(), area.getText().length() - 1)) + 1, Math.max(0, Math.min(area
							.getCaretPosition(), area.getText().length() - 1)) + 1, (tabs) + area.getText(area.getCaretPosition(), area.getText().length()));
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

		String syntaxLanguage = "ru_ru";

		private ArrayList<String> getKeyWordsFrom(String text)
		{
			ArrayList<String> list = new ArrayList<String>();
			for (String s : keyWords.get(syntaxLanguage))
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

	@Override
	public String name()
	{
		return "testsDevelopment";
	}

	@Override
	public boolean inDevelope()
	{
		return true;
	}

}
