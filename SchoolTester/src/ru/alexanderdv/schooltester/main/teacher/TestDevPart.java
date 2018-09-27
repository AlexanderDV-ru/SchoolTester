package ru.alexanderdv.schooltester.main.teacher;

import java.awt.MouseInfo;
import java.io.File;
import java.nio.charset.Charset;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import ru.alexanderdv.fxutilities.components.TextFieldWithHints;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.simpleutilities.MathUtils;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class TestDevPart extends ProtectedFXWindow
{
	private static final MessageSystem msgSys = Main.msgSys;
	public static TestDevPart instance;
	private TabPane tabPane;
	private Button addNewTestButton;

	public TestDevPart()
	{
		super(null, Main.createPane(1000, 700), 1, 1, true, true);
		instance = this;
		initialize();
		createActionHandlers();
	}

	private void initialize()
	{
		panel.getChildren().add(tabPane = new TabPane());
		panel.getChildren().add(addNewTestButton = new Button("+"));
		addNewTestButton.setOnAction(e ->
		{
			String name = "New test ";
			for (int i = 1; !name.endsWith(i + ""); i++)
			{
				for (Tab t : tabPane.getTabs())
					if (t.getText().equals(name + i))
						continue;
				name += i;
			}
			TestDevTab tab = new TestDevTab(name);
			tabPane.getTabs().add(tab);
			tabPane.getSelectionModel().select(tab);
			tab.initialize();
		});
		if (new File("Tests").isDirectory())
			for (File f : new File("Tests").listFiles())
				if (f.isDirectory())
					for (File f2 : f.listFiles())
						if (f2.isFile())
							if (f2.getName().equals(f.getName() + ".test"))
							{
								TestDevTab tab = new TestDevTab(f.getName());
								tabPane.getTabs().add(tab);
								tabPane.getSelectionModel().select(tab);
								tab.initialize();
								tab.setCode(SystemUtils.readFile(f2, "cp1251"));
							}
		if (tabPane.getTabs().size() == 0)
			addNewTestButton.fire();
	}

	@Override
	public void updateLabels()
	{
		super.updateLabels();
		for (Tab t : tabPane.getTabs())
			if (t instanceof TestDevTab)
				((TestDevTab) t).updateLabels();
	};

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

	@Override
	protected void _resize(int w, int h)
	{
		tabPane.setLayoutX(0);
		tabPane.setLayoutY(0);
		tabPane.setPrefSize(w, h);

		addNewTestButton.setPrefSize(25, 25);
		addNewTestButton.setLayoutX(panel.getPrefWidth() - addNewTestButton.getPrefWidth() - 2);
		addNewTestButton.setLayoutY(2);

		for (Tab tab : tabPane.getTabs())
			if (tab instanceof TestDevTab)
				((TestDevTab) tab).resize(w, h - Main.tabsSwitchHeight);
	}

}

enum FieldType
{
	Audio,
	Bool,
	File,
	Html,
	Image,
	Number,
	Object,
	Script,
	String,
	Video
}

class Container<T>
{
	T t;
}

abstract class AccordionElement
{
	final Container<String> name;

	public AccordionElement(Container<String> name)
	{
		super();
		this.name = name;
	}
}

abstract class AccordionField<ValueControl extends Control> extends AccordionElement
{
	final Pane pane;
	final Label label;
	final ValueControl control;

	public AccordionField(Container<String> name, Pane pane, Label label, ValueControl control)
	{
		super(name);
		this.pane = pane;
		this.label = label;
		this.control = control;
	}

	public abstract String getValue();
}

class AccordionTextfield extends AccordionField<TextField>
{

	public AccordionTextfield(Container<String> name, Pane pane, Label label, TextField control)
	{
		super(name, pane, label, control);
	}

	@Override
	public String getValue()
	{
		return control.getText();
	}
}

class AccordionCheckbox extends AccordionField<CheckBox>
{

	public AccordionCheckbox(Container<String> name, Pane pane, Label label, CheckBox control)
	{
		super(name, pane, label, control);
	}

	@Override
	public String getValue()
	{
		return control.isSelected() + "";
	}
}

class AccordionObject extends AccordionElement
{
	final TitledPane titledPane;
	final Accordion accordion;

	public AccordionObject(Container<String> name, TitledPane titledPane, Accordion accordion)
	{
		super(name);
		this.titledPane = titledPane;
		this.accordion = accordion;
	}
}

class Accordion extends Pane
{
	HashMap<Integer, AccordionElement> map2 = new HashMap<Integer, AccordionElement>();
	Accordion parent;
	TitledPane titledPane;

	public Accordion(Accordion parent, TitledPane titledPane)
	{
		this.parent = parent;
		this.titledPane = titledPane;
	}

	public void add(AccordionObject accordionObject)
	{
		accordionObject.titledPane.heightProperty().addListener(e -> update());
		map2.put(map2.size(), accordionObject);
		getChildren().add(accordionObject.titledPane);
		update();
	}

	public void update()
	{
		int y = 0;
		@SuppressWarnings("unchecked")
		HashMap<Integer, AccordionElement> map2 = (HashMap<Integer, AccordionElement>) this.map2.clone();
		for (Integer i : map2.keySet())
			if (map2.get(i) instanceof AccordionField)
			{
				((AccordionField) map2.get(i)).pane.setLayoutY(y);
				y += ((AccordionField) map2.get(i)).pane.getHeight();
			}
			else if (map2.get(i) instanceof AccordionObject)
			{
				((AccordionObject) map2.get(i)).titledPane.setLayoutY(y);
				y += (((AccordionObject) map2.get(i)).titledPane.isExpanded()
						? ((AccordionObject) map2.get(i)).accordion.getPrefHeight()
						: 0) + 25;
			}
		this.map2.clear();
		int i = 0;
		for (Integer i2 : map2.keySet())
			this.map2.put(i++, map2.get(i2));
		setMinHeight(y);
		setPrefHeight(y);
		setMaxHeight(y);
		if (parent != null)
			parent.update();
	}

	public void add(AccordionField accordionField)
	{
		accordionField.pane.heightProperty().addListener(e -> update());
		map2.put(map2.size(), accordionField);
		getChildren().add(accordionField.pane);
		update();
	}

	public void remove(AccordionElement i)
	{
		int i2 = -1;
		for (int i3 : map2.keySet())
			if (map2.get(i3).equals(i))
			{
				i2 = i3;
				break;
			}
		if (i2 == -1)
			return;
		if (i instanceof AccordionField)
			getChildren().remove(((AccordionField) i).pane);
		else if (i instanceof AccordionObject)
			getChildren().remove(((AccordionObject) i).titledPane);
		map2.remove(i2);
		update();
	}
}

class Field
{
	String name;
	FieldType type;
	boolean numberized;
	String[] restrictors;
	ArrayList<String> parents;
	ArrayList<String> children;

	public Field(String name, FieldType type, boolean numberized, String[] restrictors, ArrayList<String> parents)
	{
		this.name = name;
		this.type = type;
		this.numberized = numberized;
		this.restrictors = restrictors;
		this.parents = parents != null ? parents : new ArrayList<String>();
	}

	public Field(String name, FieldType type, boolean numberized, ArrayList<String> parents)
	{
		this(name, type, numberized, null, parents);
	}

	public Field(String name, FieldType type, boolean numberized, String[] restrictors)
	{
		this(name, type, numberized, restrictors, null);
	}

	public Field(String name, FieldType type, boolean numberized)
	{
		this(name, type, numberized, null, null);
	}

	public void setChildren(ArrayList<String> children)
	{
		this.children = children != null ? children : new ArrayList<String>();
	}
}

class HtmlAndVisualEditor extends TabPane
{
	TextArea html;
	HTMLEditor visual;
	Tab htmlTab;
	Tab visualTab;

	public HtmlAndVisualEditor()
	{
		html = new TextArea();
		visual = new HTMLEditor();
		setMinSize(600, 200);
		htmlTab = new Tab("HTML", html);
		visualTab = new Tab("Visual", visual);
		htmlTab.setClosable(false);
		visualTab.setClosable(false);
		htmlTab.setOnSelectionChanged(e ->
		{
			if (htmlTab.isSelected())
				html.setText(visual.getHtmlText().replace("><", "> <"));
		});
		visualTab.setOnSelectionChanged(e ->
		{
			if (visualTab.isSelected())
				visual.setHtmlText(html.getText());
		});
		html.setWrapText(true);
		getTabs().add(htmlTab);
		getTabs().add(visualTab);
	}
}



class ScriptEditor extends CodeArea
{
	private static final String[] types = "Int,Float,Bool,String".split(",");
	private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", types) + ")\\b";
	private static final String ERROR_PATTERN = "(\"(([^\"]+(\n|\\Z))|(\n|\\Z)))|((\\A|[\n])(" + String.join("|", types)
			+ ")(([^ ])|([ ][a-zA-Z][0-9a-zA-Z]+[^=:])|([ ][^a-zA-Z]))[^\n](\\Z|[\n]))";
	// private static final String ERROR_PATTERN = "\\b(" + String.join("|", types)
	// + ")\\b";
	private static final String COMMENT_PATTERN = "(\\A|\n)//[^\n]+";
	private static final String SEMICOLON_PATTERN = "\\:";
	private static final String FLOAT_PATTERN = "\\b[0-9]+[.][0-9]+\\b";
	private static final String INT_PATTERN = "\\b[0-9]+\\b";
	private static final String STRING_PATTERN = "\"(([^\"^\n]+\")|\")";
	private static final ArrayList<String> vars = new ArrayList<String>();

	public ScriptEditor()
	{
		String stylesheet = TestDevPart.class.getResource("/"+Main.resourcesPackage.replace(".", "/")+"/schooltesterscript-keywords.css").toString();
		IntFunction<String> format = (digits -> " %" + digits + "d ");
		setParagraphGraphicFactory(LineNumberFactory.get(this, format));
		getStylesheets().add(stylesheet);
		textProperty().addListener((obs, oldText, newText) -> setStyleSpans(0, computeHighlighting(newText)));
	}

	private StyleSpans<Collection<String>> computeHighlighting(String text)
	{
		vars.clear();
		for (String line : text.replace("\r\n", "\\n").replace("\r", "\\n").replace("\n", "\\n").split("[\\\\][n]"))
			for (String type : types)
				if (line.startsWith(type))
				{
					if (line.contains("="))
						vars.add(line.substring(type.length() + 1, line.indexOf("=")));
					else if (line.contains(":"))
						vars.add(line.substring(type.length() + 1, line.indexOf(":")));
				}
		Matcher matcher = Pattern.compile("(?<ERROR>" + ERROR_PATTERN + ")" + "|(?<VARS>" + "\\b("
				+ String.join("|", vars) + ")\\b" + ")" + "|(?<KEYWORD>" + KEYWORD_PATTERN + ")" + "|(?<SEMICOLON>"
				+ SEMICOLON_PATTERN + ")" + "|(?<STRING>" + STRING_PATTERN + ")" + "|(?<COMMENT>" + COMMENT_PATTERN
				+ ")" + "|(?<FLOAT>" + FLOAT_PATTERN + ")" + "|(?<INT>" + INT_PATTERN + ")").matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find())
		{
			String styleClass = matcher.group("VARS") != null ? "var"
					: matcher.group("KEYWORD") != null ? "keyword"
							: matcher.group("SEMICOLON") != null ? "semicolon"
									: matcher.group("COMMENT") != null ? "comment"
											: matcher.group("STRING") != null ? "string"
													: matcher.group("FLOAT") != null ? "float"
															: matcher.group("INT") != null ? "int"
																	: matcher.group("ERROR") != null ? "error" : null;
			assert styleClass != null;
			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}
}

class DirFilesView extends VBox
{
	private static Background selectedBG = new Background(
			new BackgroundFill(Color.BLUE, new CornerRadii(0), new Insets(0))),
			notSelectedBG = new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), new Insets(0)));
	ArrayList<String> typeFilter;
	boolean blacklist;
	File dir;
	String selected;

	public DirFilesView()
	{
	}

	public void update()
	{
		Button first = null;
		getChildren().clear();
		if (dir != null)
			for (File f : dir.listFiles())
				if (f.isFile())
				{
					Button btn = new Button(f.getName());
					boolean isOk = blacklist;
					for (String s : typeFilter)
						if (blacklist)
							isOk = !f.getName().endsWith("." + s) && isOk;
						else isOk = f.getName().endsWith("." + s) || isOk;
					btn.setTextFill(isOk ? Color.GREEN : Color.RED);
					if (isOk)
						btn.setOnAction(e ->
						{
							for (Node n : getChildren())
								if (n instanceof Button)
									((Button) n).setBackground(n != btn ? notSelectedBG : selectedBG);
							selected = btn.getText();
						});
					if (first == null && isOk)
						first = btn;
					getChildren().add(btn);
				}
		if (first != null)
			first.fire();
		autosize();
	}
}

class TestDevTab extends Tab
{
	private static final MessageSystem msgSys = Main.msgSys;
	private boolean initialized;
	private Tab codeTab, constructorTab;
	private Button save;
	private String testName;
	private Button delete;
	private HBox pane1h;
	private TextField name1;
	private CodeArea area;

	private static HashMap<String, HashMap<String, Field>> fields = createFields();
	private TitledPane testTitledPane;

	public void resize(int w, int h1)
	{
		double height = 20, dst = 5, offset = height + dst;
		double height2 = 30, dst2 = 5, offset2 = height2 + dst2;
		double height3 = 25, dst3 = 5, offset3 = height3 + dst3;

		pane1h.setPrefSize(w, height2);
		pane1h.setLayoutX(0);
		pane1h.setLayoutY(h1 - pane1h.getPrefHeight());

		codeTab.getTabPane().setLayoutX(0);
		codeTab.getTabPane().setLayoutY(0);
		codeTab.getTabPane().setPrefSize(w, pane1h.getLayoutY());

		double h = h1 - Main.tabsSwitchHeight - pane1h.getPrefHeight();

		pane2.setPrefSize(500, h);
		pane2.setLayoutX(w - pane2.getPrefWidth());
		pane2.setLayoutY(0);

		pane1p.setLayoutX(0);
		pane1p.setLayoutY(0);
		pane1p.setPrefSize(pane2.getLayoutX(), h);

		if (testTitledPane != null)
		{
			sp.setLayoutX(0);
			sp.setLayoutY(0);
			sp.setPrefSize(pane1p.getPrefWidth(), pane1p.getPrefHeight());

			pp1.titledPane.setLayoutX(0);
			pp1.titledPane.setLayoutY(0);
			pp1.titledPane.setPrefSize(sp.getPrefWidth() - 15, sp.getPrefHeight());

			pp1.setLayoutX(0);
			pp1.setLayoutY(0);
			pp1.setPrefSize(sp.getPrefWidth() - 16, sp.getPrefHeight());

			resize(pp1);
		}
	}

	private void resize(Accordion acc)
	{
		for (Integer i : acc.map2.keySet())
		{
			if (acc.map2.get(i) instanceof AccordionObject)
			{
				((AccordionObject) acc.map2.get(i)).titledPane
						.setPrefWidth(acc.getPrefWidth() - ((AccordionObject) acc.map2.get(i)).titledPane.getLayoutX());
				((AccordionObject) acc.map2.get(i)).accordion.setPrefWidth(
						acc.getPrefWidth() - ((AccordionObject) acc.map2.get(i)).accordion.getLayoutX() - 1);
				resize(((AccordionObject) acc.map2.get(i)).accordion);
			}
			else if (acc.map2.get(i) instanceof AccordionField)
				((AccordionField) acc.map2.get(i)).pane
						.setPrefWidth(acc.getPrefWidth() - ((AccordionField) acc.map2.get(i)).pane.getLayoutX());
		}
	}

	private static HashMap<String, HashMap<String, Field>> createFields()
	{
		fields = new HashMap<String, HashMap<String, Field>>();
		for (String language : msgSys.getMessages().keySet())
		{
			ArrayList<Field> languageFieldsList = new ArrayList<Field>();

			ArrayList<String> parent_Test = toGetMessagedList(language, "test");

			languageFieldsList.add(
					new Field(msgSys.getMsg("test", language), FieldType.Object, false, toGetMessagedList(language)));

			languageFieldsList.add(new Field("syntaxLanguage", FieldType.String, false,
					new String[] { "ru_ru", "en_uk" }, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("programVersion", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testVersion", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("colorType", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testCreationDate", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testLanguage", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testSubject", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testAuthors", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testNaming", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("testDescription", language), FieldType.String, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("timeLimit", language), FieldType.Number, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("timeEndWarning", language), FieldType.Number, false, parent_Test));
			languageFieldsList.add(new Field(msgSys.getMsg("smartMode", language), FieldType.Bool, false, parent_Test));

			languageFieldsList
					.add(new Field(msgSys.getMsg("startPermissions", language), FieldType.Object, false, parent_Test));
			languageFieldsList
					.add(new Field(msgSys.getMsg("hintsPermissions", language), FieldType.Object, false, parent_Test));

			languageFieldsList.add(new Field(msgSys.getMsg("scripts", language), FieldType.Object, false, parent_Test));
			languageFieldsList.add(new Field(msgSys.getMsg("script", language), FieldType.Object, true,
					toGetMessagedList(language, "scripts")));
			languageFieldsList.add(new Field(msgSys.getMsg("scriptName", language), FieldType.String, false,
					toGetMessagedList(language, "script")));
			languageFieldsList.add(new Field(msgSys.getMsg("scriptText", language), FieldType.Script, false,
					toGetMessagedList(language, "script")));

			ArrayList<String> parent_Permissions = toGetMessagedList(language, "startPermissions", "hintsPermissions");

			languageFieldsList.add(new Field(msgSys.getMsg("showLastAnswerQualityPermission", language), FieldType.Bool,
					false, parent_Permissions));
			languageFieldsList.add(new Field(msgSys.getMsg("showAllAnswersQualityPermission", language), FieldType.Bool,
					false, parent_Permissions));
			languageFieldsList.add(new Field(msgSys.getMsg("showRightAnswerPermission", language), FieldType.Bool,
					false, parent_Permissions));
			languageFieldsList.add(new Field(msgSys.getMsg("goToAllAnswersPermission", language), FieldType.Bool, false,
					parent_Permissions));
			languageFieldsList.add(
					new Field(msgSys.getMsg("skipPermission", language), FieldType.Bool, false, parent_Permissions));
			languageFieldsList.add(
					new Field(msgSys.getMsg("pausePermission", language), FieldType.Bool, false, parent_Permissions));

			ArrayList<String> parent_AnswerQuestion = toGetMessagedList(language, "question", "answer");

			languageFieldsList.add(new Field(msgSys.getMsg("clippedImages", language), FieldType.Object, false,
					parent_AnswerQuestion));
			languageFieldsList.add(new Field(msgSys.getMsg("clippedVideos", language), FieldType.Object, false,
					parent_AnswerQuestion));
			languageFieldsList.add(new Field(msgSys.getMsg("clippedAudios", language), FieldType.Object, false,
					parent_AnswerQuestion));
			languageFieldsList.add(new Field(msgSys.getMsg("image", language), FieldType.Image, true,
					toGetMessagedList(language, "clippedImages")));
			languageFieldsList.add(new Field(msgSys.getMsg("video", language), FieldType.Video, true,
					toGetMessagedList(language, "clippedVideos")));
			languageFieldsList.add(new Field(msgSys.getMsg("audio", language), FieldType.Audio, true,
					toGetMessagedList(language, "clippedAudio")));

			languageFieldsList
					.add(new Field(msgSys.getMsg("questions", language), FieldType.Object, false, parent_Test));

			ArrayList<String> parent_Questions = toGetMessagedList(language, "questions");
			ArrayList<String> parent_Question = toGetMessagedList(language, "question");

			languageFieldsList
					.add(new Field(msgSys.getMsg("chooseOne", language), FieldType.Object, false, parent_Questions));
			languageFieldsList.add(
					new Field(msgSys.getMsg("selectMultiple", language), FieldType.Object, false, parent_Questions));
			languageFieldsList
					.add(new Field(msgSys.getMsg("enterText", language), FieldType.Object, false, parent_Questions));
			languageFieldsList
					.add(new Field(msgSys.getMsg("distribution", language), FieldType.Object, false, parent_Questions));
			languageFieldsList
					.add(new Field(msgSys.getMsg("matching", language), FieldType.Object, false, parent_Questions));
			languageFieldsList
					.add(new Field(msgSys.getMsg("arrangement", language), FieldType.Object, false, parent_Questions));

			languageFieldsList.add(new Field(msgSys.getMsg("question", language), FieldType.Object, true,
					toGetMessagedList(language, "group")));
			languageFieldsList.add(new Field(msgSys.getMsg("answers", language), FieldType.Object, false,
					toGetMessagedList(language, "question")));
			languageFieldsList.add(new Field(msgSys.getMsg("answer", language), FieldType.Object, true,
					toGetMessagedList(language, "answers", "answersCombination")));
			languageFieldsList
					.add(new Field(msgSys.getMsg("usedScript", language), FieldType.String, false, parent_Question));
			languageFieldsList
					.add(new Field(msgSys.getMsg("questionIndex", language), FieldType.Number, false, parent_Question));
			languageFieldsList.add(new Field(msgSys.getMsg("award", language), FieldType.Number, false,
					toGetMessagedList(language, "answer", "question", "awardForAnswer")));
			languageFieldsList.add(new Field("html", FieldType.Html, false, parent_AnswerQuestion));
			languageFieldsList
					.add(new Field(msgSys.getMsg("ignoreCase", language), FieldType.Bool, false, parent_Question));
			languageFieldsList.add(
					new Field(msgSys.getMsg("ignoredCharacters", language), FieldType.String, false, parent_Question));
			languageFieldsList.add(new Field(msgSys.getMsg("minimalResult", language), FieldType.Number, false,
					toGetMessagedList(language, "question", "questions")));// TODO �������� �������
			languageFieldsList.add(new Field(msgSys.getMsg("questionsToTestAmount", language), FieldType.Number, false,
					toGetMessagedList(language, "group")));
			languageFieldsList.add(new Field(msgSys.getMsg("randomize", language), FieldType.Bool, false,
					toGetMessagedList(language, "group", "questions", "pickOne", "selectSome", "enterText",
							"distribution", "matching", "arrangement")));
			languageFieldsList.add(new Field(msgSys.getMsg("randomizeBlocks", language), FieldType.Bool, false,
					toGetMessagedList(language, "questions")));
			languageFieldsList.add(new Field(msgSys.getMsg("randomizeGroups", language), FieldType.Bool, false,
					toGetMessagedList(language, "pickOne", "selectSome", "enterText", "distribution", "matching",
							"arrangement")));
			languageFieldsList.add(
					new Field(msgSys.getMsg("answerFontSize", language), FieldType.Number, false, parent_Questions));
			languageFieldsList.add(
					new Field(msgSys.getMsg("handleOnlyMaximal", language), FieldType.Bool, false, parent_Question));

			languageFieldsList.add(new Field(msgSys.getMsg("awardsForAnswersCombinations", language), FieldType.Object,
					false, parent_Question));
			languageFieldsList.add(new Field(msgSys.getMsg("awardForAnswersCombination", language), FieldType.Object,
					true, toGetMessagedList(language, "awardsForAnswersCombinations")));
			languageFieldsList.add(new Field(msgSys.getMsg("answersCombination", language), FieldType.Object, false,
					toGetMessagedList(language, "awardForAnswersCombination")));
			languageFieldsList.add(new Field(msgSys.getMsg("answerNumber", language), FieldType.Number, false,
					toGetMessagedList(language, "answer")));
			languageFieldsList.add(new Field(msgSys.getMsg("answerIndexes", language), FieldType.Object, false,
					toGetMessagedList(language, "answer")));
			languageFieldsList.add(new Field(msgSys.getMsg("index", language), FieldType.Number, true,
					toGetMessagedList(language, "answerIndexes")));
			languageFieldsList.add(new Field(msgSys.getMsg("answerIndex", language), FieldType.Number, false,
					toGetMessagedList(language, "answer")));
			languageFieldsList.add(new Field(msgSys.getMsg("onlyThisIndexes", language), FieldType.Bool, false,
					toGetMessagedList(language, "answer")));

			languageFieldsList.add(
					new Field(msgSys.getMsg("indexesForNames", language), FieldType.Object, false, parent_Question));
			languageFieldsList.add(new Field(msgSys.getMsg("naming", language), FieldType.String, true,
					toGetMessagedList(language, "indexesForNames")));
			languageFieldsList.add(new Field(msgSys.getMsg("onlyThisIndexes", language), FieldType.Bool, false,
					toGetMessagedList(language, "answerIndex")));
			languageFieldsList
					.add(new Field(msgSys.getMsg("group", language), FieldType.Object, true, toGetMessagedList(language,
							"chooseOne", "selectMultiple", "enterText", "distribution", "matching", "arrangement")));
			HashMap<String, Field> languageFields = new HashMap<String, Field>();

			HashMap<String, ArrayList<String>> fieldsWithChildren = new HashMap<String, ArrayList<String>>();
			for (Field field : languageFieldsList)
				for (String parent : field.parents)
				{
					if (!fieldsWithChildren.containsKey(parent))
						fieldsWithChildren.put(parent, new ArrayList<String>());
					fieldsWithChildren.get(parent).add(field.name);
				}

			for (Field field : languageFieldsList)
			{
				field.setChildren(fieldsWithChildren.get(field.name));
				languageFields.put(field.name, field);
			}
			fields.put(language, languageFields);
		}
		return fields;
	}

	private static ArrayList<String> toGetMessagedList(String language, String... array)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (String key : array)
			list.add(msgSys.getMsg(key, language));
		return list;
	}

	public void setCode(String code)
	{
		area.replaceText(code);
	}

	public TestDevTab(String name)
	{
		super(name);
		testName = name;
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
		for (String language : msgSys.getMessages().keySet())
		{
			ArrayList<String> keyWordsToLanguage = new ArrayList<String>();
			for (String name : fields.get(language).keySet())
				if (fields.get(language).get(name).numberized)
					keyWordsToLanguage.add(name + "[0-9]+");
			keyWords.put(language, keyWordsToLanguage);
		}
		return keyWords;
	}

	private static HashMap<String, ArrayList<String>> createKeyWordsWithoutDigit()
	{
		HashMap<String, ArrayList<String>> keyWords = new HashMap<String, ArrayList<String>>();
		for (String language : msgSys.getMessages().keySet())
		{
			ArrayList<String> keyWordsToLanguage = new ArrayList<String>();
			for (String name : fields.get(language).keySet())
				if (!fields.get(language).get(name).numberized)
					keyWordsToLanguage.add(name);
			keyWords.put(language, keyWordsToLanguage);
		}
		return keyWords;
	}

	private static HashMap<String, String> createKeywordParents()
	{
		HashMap<String, String> keywordParents = new HashMap<String, String>();
		for (String language : msgSys.getMessages().keySet())
			keywordParents.put(language, "\\b(" + String.join("|", keyWordsWithoutDigit.get(language)) + "|"
					+ replaceBetween("%d<", ">", "[0-9]+", String.join("|", keyWordsWithDigit.get(language))) + ")\\b");
		return keywordParents;

	}

	private static String replaceBetween(String first, String second, String replacement, String text)
	{
		for (; text.indexOf(first) < text.indexOf(second) && text.indexOf(first) != -1;)
			text = text.substring(0, text.indexOf(first)) + replacement
					+ text.substring(text.indexOf(second) + second.length());
		return text;
	}

	private static HashMap<String, Pattern> createPatterns()
	{
		HashMap<String, Pattern> patterns = new HashMap<String, Pattern>();
		for (String language : msgSys.getMessages().keySet())
			patterns.put(language,
					Pattern.compile(
							"(?<KEYWORD>" + KEYWORD_PATTERNS.get(language) + ")" + "|(?<SEMICOLON>" + SEMICOLON_PATTERN
									+ ")" + "|(?<STRING>" + STRING_PATTERN + ")" + "|(?<COMMENT>" + COMMENT_PATTERN
									+ ")" + "|(?<ERROR>" + ERROR_PATTERN + ")" + "|(?<NUMBER>" + NUMBER_PATTERN + ")"));
		return patterns;
	}

	private static final String ERROR_PATTERN = "(\"(([^\"]+(\n|\\Z))|(\n|\\Z)))";
	private static final String COMMENT_PATTERN = "(\\A|\n)//[^\n]+";
	private static final String SEMICOLON_PATTERN = "\\:";
	private static final String NUMBER_PATTERN = "\\b[0-9]+\\b";
	private static final String STRING_PATTERN = "\"(([^\"^\n]+\")|\")";

	private StyleSpans<Collection<String>> computeHighlighting(String text)
	{
		if (!PATTERNS.containsKey(syntaxLanguage))
			return new StyleSpansBuilder<Collection<String>>().add(Collections.emptyList(), text.length()).create();
		Matcher matcher = PATTERNS.get(syntaxLanguage).matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find())
		{
			String styleClass = matcher.group("KEYWORD") != null ? "keyword"
					: matcher.group("SEMICOLON") != null ? "semicolon"
							: matcher.group("COMMENT") != null ? "comment"
									: matcher.group("ERROR") != null ? "error"
											: matcher.group("STRING") != null ? "string"
													: matcher.group("NUMBER") != null ? "number" : null;
			assert styleClass != null;
			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}

	private static final HashMap<String, Pattern> PATTERNS = createPatterns();

	public void initialize()
	{
		if (initialized)
			throw new NullPointerException("Already initialized!");
		save = new Button("save");
		delete = new Button("delete");
		Label errF = new Label();
		name1 = new TextField();
		pane1h = new HBox();
		pane1h.getChildren().add(name1);
		pane1h.getChildren().add(save);
		pane1h.getChildren().add(delete);
		pane1h.getChildren().add(errF);
		Pane splitPane = new Pane();
		splitPane.getChildren().add(
				new TabPane(codeTab = new Tab("Code", area = new CodeArea()), constructorTab = new Tab("Constructor")));
		area.setPrefHeight(getTabPane().getPrefHeight() - 30);
		codeTab.getTabPane().setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		splitPane.getChildren().add(pane1h);
		setContent(splitPane);
		name1.setText(testName);
		new Timer(100,
				e -> Platform.runLater(() -> setText(testName + (((area.getText().equals(SystemUtils.readFile(
						new File("Tests/" + testName + "/" + testName + ".test"), Charset.forName("cp1251"))))) ? ""
								: "*")))).start();
		save.setOnAction(e ->
		{
			if (constructorTab.isSelected())
				setCodeToConstructor();
			area.replaceText(area.getText().replace("\t\n", " \n").replace(" \n", "\n"));
			testName = name1.getText();
			setText(testName);
			SystemUtils.writeFile(new File("Tests/" + testName + "/" + testName + ".test"), area.getText(),
					Charset.forName("cp1251"));
		});
		delete.setOnAction(e ->
		{
			getTabPane().getTabs().remove(TestDevTab.this);
			SystemUtils.deleteFile(new File("Tests/" + testName));
		});
		String stylesheet = TestDevPart.class.getResource(Main.resourcesPath+"/schooltester-keywords.css").toString();
		IntFunction<String> format = (digits -> " %" + digits + "d ");
		area.setParagraphGraphicFactory(LineNumberFactory.get(area, format));
		area.getStylesheets().add(stylesheet);
		errF.setTextFill(Color.RED);
		class C
		{
			int q = 1, a = 1;
		}
		C c = new C();
		area.textProperty().addListener((obs, oldText, newText) ->
		{
			Config conf = new Config(newText);
			syntaxLanguage = conf.getString("syntaxLanguage", "", true);
			if (!conf.hasValue("syntaxLanguage"))
				errF.setText("Add syntax language!");
			else if (!msgSys.getMessages().containsKey(syntaxLanguage))
				errF.setText("Supported syntax languages are '" + String.join("', '", msgSys.getMessages().keySet())
						+ "'! Current syntax - '" + syntaxLanguage + "' is not supported!");
			else errF.setText("");
			area.setStyleSpans(0, computeHighlighting(newText));
		});
		area.setOnKeyPressed(e ->
		{
			if (e.isControlDown())
				if (e.getCode() == KeyCode.SPACE)
					for (int i = area.getCaretPosition() - 1; i >= 0; i--)
						if (i < area.getText().length() ? !Character.isLetter(area.getText().charAt(i))
								&& !Character.isDigit(area.getText().charAt(i)) : true)
						{
							i++;
							String s = String.join("\n", getKeyWordsFrom(area.getText(i, area.getCaretPosition())));
							if (area.getText().lastIndexOf(msgSys.getMsg("question", syntaxLanguage)) != -1)
								c.q = MathUtils.parseI(area.getText().substring(
										area.getText().lastIndexOf(msgSys.getMsg("question", syntaxLanguage)),
										area.getText().lastIndexOf(msgSys.getMsg("question", syntaxLanguage))
												+ area.getText()
														.substring(area.getText()
																.lastIndexOf(msgSys.getMsg("question", syntaxLanguage)))
														.indexOf(":")));
							else c.q = 1;
							if (c.q < 1)
								c.q = 1;
							if (area.getText().lastIndexOf(msgSys.getMsg("answer", syntaxLanguage)) != -1)
								c.a = MathUtils.parseI(area.getText().substring(
										area.getText().lastIndexOf(msgSys.getMsg("answer", syntaxLanguage)),
										area.getText().lastIndexOf(msgSys.getMsg("answer", syntaxLanguage))
												+ area.getText()
														.substring(area.getText()
																.lastIndexOf(msgSys.getMsg("answer", syntaxLanguage)))
														.indexOf(":")));
							else c.a = 1;
							if (c.a < 1)
								c.a = 1;
							String[] keyWords = s.replace("%d<question>", "" + c.q).replace("%d<answer>", "" + c.a)
									.replace("%d<awardForAnswer>", "" + 1).replace("%d<answerIndex>", "" + 1)
									.split("\n");
							MenuItem[] items = new MenuItem[keyWords.length];
							for (int r = 0; r < items.length; r++)
							{
								items[r] = new MenuItem(keyWords[r]);
								int ii = i, rr = r;
								items[r].setOnAction(ev ->
								{
									area.replaceText(ii, area.getCaretPosition(), keyWords[rr] + ": ");
								});
							}
							area.setContextMenu(new ContextMenu(items));
							area.getContextMenu().show(area, MouseInfo.getPointerInfo().getLocation().x,
									MouseInfo.getPointerInfo().getLocation().y);
							area.getContextMenu().requestFocus();
							break;
						}
		});
		area.setOnKeyReleased(e ->
		{
			String textToCaret = area.getText(0,
					Math.max(0, Math.min(area.getCaretPosition() - 1, area.getText().length() - 1)));
			if (e.getCode() == KeyCode.ENTER
					&& area.getText().charAt(Math.min(area.getCaretPosition(), area.getText().length() - 1)) == '\n')
			{
				String tabs = getStartTabs(
						textToCaret.substring(Math.max(0, textToCaret.lastIndexOf("\n") + 1), textToCaret.length()))
						+ (textToCaret.replace("\t", "").replace(" ", "").endsWith(":") ? "\t" : "");
				area.replaceText(Math.max(0, Math.min(area.getCaretPosition(), area.getText().length() - 1)) - 1,
						Math.max(0, Math.min(area.getCaretPosition(), area.getText().length() - 1)), "\n" + tabs);
			}
		});
		constructorTab.setContent(new Pane(pane1p, pane2));
		codeTab.setOnSelectionChanged(e -> setCodeToConstructor());
		pane2.setMinWidth(400);
		constructorTab.setOnSelectionChanged(e ->
		{
			if (constructorTab.isSelected())
			{
				HashMap<String, SimpleEntry<Accordion, Container<String>>> tabsAndPanes = new HashMap<String, SimpleEntry<Accordion, Container<String>>>();
				offsets = new HashMap<Accordion, IntegerE>();

				pane1p.getChildren().clear();

				pane1p.setMinWidth(getTabPane().getPrefWidth() - editor.getMinWidth() - 5);
				pane1p.setPrefWidth(pane1p.getMinWidth());

				Pane pane = new Pane();

				pane.setMinWidth(pane1p.getPrefWidth() - 10);
				pane.setPrefWidth(pane.getMinWidth());

				sp = new ScrollPane(pane);
				sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
				sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
				pane1p.getChildren().add(sp);
				sp.setPrefHeight(getTabPane().getPrefHeight() - 60);
				sp.setMaxHeight(getTabPane().getPrefHeight() - 60);
				sp.setPrefWidth(getTabPane().getPrefWidth());

				pp1 = addObject(null, msgSys.getMsg("test", syntaxLanguage), null, tabsAndPanes, pane2, true,
						new Container<String>(), pane);
				testTitledPane = pp1.titledPane;

				for (String line : area.getText().replace("\r\n", "\n").replace("\r", "\n").split("\n"))
				{
					if (!line.contains(":"))
						continue;
					String tabs = "";
					for (char ch : line.toCharArray())
						if (ch == '\t')
							tabs += ch;
						else break;
					if (!tabsAndPanes.containsKey(tabs))
					{
						e.consume();
						codeTab.getTabPane().getSelectionModel().select(codeTab);
						FXDialogsGenerator.showFXDialog(TestDevPart.instance.stage, "Syntax error!", null, true);
						return;
					}
					Accordion p = tabsAndPanes.get(tabs).getKey();
					if (line.contains(":"))
						if (line.replace("\t", "").replace(" ", "").endsWith(":"))
							addObject(p, line.substring(0, line.indexOf(":")).replace("\t", "").replace(" ", ""), tabs,
									tabsAndPanes, pane2, false, tabsAndPanes.get(tabs).getValue(), null);
						else addField(p, line.substring(0, line.indexOf(":")).replace("\t", "").replace(" ", ""),
								line.substring(line.indexOf(":") + 1), pane2, tabsAndPanes.get(tabs).getValue());
					else
					{
						e.consume();
						codeTab.getTabPane().getSelectionModel().select(codeTab);
						FXDialogsGenerator.showFXDialog(TestDevPart.instance.stage, "Syntax error!", null, true);
						return;
					}
					offsets.get(tabsAndPanes.get(tabs).getKey()).i++;
				}
				pp1.update();
				pp1.setPrefWidth(pane1p.getWidth() - 5);
				resize((int) getTabPane().getPrefWidth(),
						(int) getTabPane().getPrefHeight() - (int) Main.tabsSwitchHeight);
			}
		});
		initialized = true;
	}

	ScrollPane sp;

	private void setCodeToConstructor()
	{
		if (codeTab.isSelected())
		{
			String res = "";
			if (offsets != null)
				res = handleText(pp1, "");
			area.replaceText(res);
		}
	}

	Pane pane1p = new Pane();
	TabPane pane2 = new TabPane();

	HtmlAndVisualEditor editor = new HtmlAndVisualEditor();
	Tab htmlT = new Tab("HTML", editor);
	TextField string = new TextField();
	Tab stringT = new Tab("String", string);
	TextField number = new TextField();
	Tab numberT = new Tab("Number", number);
	CheckBox bool = new CheckBox();
	Tab boolT = new Tab("Bool", bool);
	ComboBox<String> restricted = new ComboBox<String>();
	Tab restrictedT = new Tab("Restricted", restricted);
	TextFieldWithHints field = new TextFieldWithHints();
	Tab fieldT = new Tab("Field", field);
	DirFilesView file = new DirFilesView();
	Tab fileT = new Tab("File", file);
	ScriptEditor script = new ScriptEditor();
	Tab scriptT = new Tab("Script", script);

	Container<Runnable> onGoToOther = new Container<Runnable>();
	{
		onGoToOther.t = () ->
		{
		};
	}

	private Color checkWrongSyntax(Container<String> ss, Container<String> parents)
	{
		String s = removeDigits(ss.t), parent = removeDigits(parents.t);
		// System.out.println(s + "\t" + (fields.get(syntaxLanguage).get(s) == null) +
		// "\t" + (fields.get(syntaxLanguage).get(s) != null ? fields.get(
		// syntaxLanguage).get(s).parents.toString() : null) + "\t" +
		// removeDigits(parent) + "\t" + parent);
		return fields.get(syntaxLanguage).get(s) == null ? Color.RED
				: (!fields.get(syntaxLanguage).get(s).parents.contains(removeDigits(parent)) ? Color.RED : Color.BLACK);
	}

	private String removeDigits(String t)
	{
		String s = "";
		for (char c : t.toCharArray())
			if (!Character.isDigit(c))
				s += c;
		return s;
	}

	private void addField(Accordion p, String name, String text, TabPane pane2, Container<String> parent)
	{
		Container<String> s = new Container<String>();
		s.t = name;
		Pane pan1 = new Pane();
		Label l = new Label(toLabel(name));
		l.setPrefWidth(p.getPrefWidth() / 2);
		FieldType fieldType1 = fields.get(syntaxLanguage).get(removeDigits(s.t)) != null
				? fields.get(syntaxLanguage).get(removeDigits(s.t)).type
				: FieldType.String;

		MenuItem change = new MenuItem("Change");
		Runnable checkWrongSyntax = () -> l.setTextFill(checkWrongSyntax(s, parent));
		checkWrongSyntax.run();
		change.setOnAction(e ->
		{
			onGoToOther.t.run();
			pane2.getTabs().clear();
			pane2.getTabs().add(fieldT);
			field.setText(s.t);
			field.getItems().clear();
			field.getItems().addAll(toFieldNameItems(getChildrenOf(parent.t, syntaxLanguage)));
			onGoToOther.t = () ->
			{
				l.setText(toLabel(s.t = field.getText()));
				checkWrongSyntax.run();
			};
		});
		Menu delete = new Menu("Delete");
		Menu cantCancel = new Menu("You can't cancel this action!");
		MenuItem yes = new MenuItem("Yes");
		cantCancel.getItems().add(yes);
		cantCancel.getItems().add(new MenuItem("No"));
		delete.getItems().add(cantCancel);
		delete.getItems().add(new MenuItem("No"));

		l.setContextMenu(new ContextMenu(change, delete));

		Pane span;

		String val = text.replace(" ", "").replace("\t", "");
		if (fieldType1 == FieldType.Bool && (val.equals("true") || val.equals("false")))
		{
			CheckBox f = new CheckBox();
			f.setSelected(val.equals("true"));
			AccordionField field = new AccordionCheckbox(s, pan1, l, f);
			p.add(field);
			span = new Pane(l, f);
			pan1.prefWidthProperty().addListener((a, b, c) ->
			{
				span.setPrefSize(pan1.getPrefWidth(), pan1.getPrefHeight());
				f.setPrefWidth(15);
				f.setLayoutX(span.getPrefWidth() - f.getPrefWidth());
				l.setPrefWidth(span.getPrefWidth() - f.getPrefWidth());
			});
		}
		else if (fieldType1 == FieldType.Number && val.lastIndexOf("-") <= 0
				&& val.replaceAll("[0123456789-]", "").length() == 0)
		{
			TextField f = new TextField(val);
			f.setTextFormatter(new TextFormatter<>(c -> c.getControlNewText().lastIndexOf("-") <= 0
					&& c.getControlNewText().replaceAll("[0123456789-]", "").length() == 0 ? c : null));
			AccordionField field = new AccordionTextfield(s, pan1, l, f);
			p.add(field);
			span = new Pane(l, f);
			pan1.prefWidthProperty().addListener((a, b, c) ->
			{
				span.setPrefSize(pan1.getPrefWidth(), pan1.getPrefHeight());
				f.setPrefWidth(50);
				f.setLayoutX(span.getPrefWidth() - f.getPrefWidth());
				l.setPrefWidth(span.getPrefWidth() - f.getPrefWidth());
			});
		}
		else
		{
			TextField f = new TextField(text);
			f.setPrefWidth(p.getPrefWidth() / 2);
			f.setEditable(false);
			f.setOnMouseClicked(eve ->
			{
				onGoToOther.t.run();

				pane2.getTabs().clear();
				FieldType fieldType = fields.get(syntaxLanguage).get(removeDigits(s.t)) != null
						? fields.get(syntaxLanguage).get(removeDigits(s.t)).type
						: FieldType.String;
				boolean b = false;
				if (fields.get(syntaxLanguage).get(removeDigits(s.t)) != null
						? fields.get(syntaxLanguage).get(removeDigits(s.t)).restrictors == null
						: true)
					switch (fieldType)
					{
						case Html:
						{
							pane2.requestFocus();
							pane2.getTabs().add(htmlT);
							String str = f.getText();
							editor.visualTab.getTabPane().requestFocus();
							editor.visualTab.getTabPane().getSelectionModel().select(editor.visualTab);
							if (str.contains("\"") && str.indexOf("\"") != str.lastIndexOf("\""))
								editor.visual.setHtmlText(str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\"")));
							else editor.visual.setHtmlText(str);
							onGoToOther.t = () -> f.setText("\"" + (editor.htmlTab.isSelected() ? editor.html.getText()
									: editor.visual.getHtmlText()) + "\"");
						}
							break;
						case String:
						{
							pane2.requestFocus();
							pane2.getTabs().add(stringT);
							String str = f.getText();
							if (str.contains("\"") && str.indexOf("\"") != str.lastIndexOf("\""))
								string.setText(str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\"")));
							else string.setText(str);
							onGoToOther.t = () -> f.setText("\"" + string.getText() + "\"");
						}
							break;
						case Script:
						{
							pane2.requestFocus();
							pane2.getTabs().add(scriptT);
							String str = f.getText().replace("\\n", "\r\n");
							if (str.contains("\"") && str.indexOf("\"") != str.lastIndexOf("\""))
								script.replaceText(str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\"")));
							else script.replaceText(str);
							onGoToOther.t = () -> f.setText("\""
									+ script.getText().replace("\r\n", "\\n").replace("\r", "\\n").replace("\n", "\\n")
									+ "\"");
						}
							break;
						default:
						{
							FXDialogsGenerator.showFXDialog(TestDevPart.instance.stage,
									"Error! This value is " + fieldType + "!", null, true);
							pane2.requestFocus();
							pane2.getTabs().add(stringT);
							string.setText(f.getText());
							onGoToOther.t = () -> f.setText(string.getText());
						}
							break;
						case Audio:
							if (!b)
							{
								file.typeFilter = new ArrayList<String>();
								file.typeFilter.add("aif");
								file.typeFilter.add("aiff");
								file.typeFilter.add("fxm");
								file.typeFilter.add("flv");
								file.typeFilter.add("wav");
								file.typeFilter.add("mp3");
								b = true;
							}
						case Video:
							if (!b)
							{
								file.typeFilter = new ArrayList<String>();
								file.typeFilter.add("fxm");
								file.typeFilter.add("flv");
								file.typeFilter.add("mp4");
								b = true;
							}
						case Image:
							if (!b)
							{
								file.typeFilter = new ArrayList<String>();
								file.typeFilter.add("bmp");
								file.typeFilter.add("gif");
								file.typeFilter.add("jpg");
								file.typeFilter.add("png");
								b = true;
							}
						case File:
						{
							pane2.requestFocus();
							pane2.getTabs().add(fileT);
							file.blacklist = !b;
							if (!b)
							{
								file.typeFilter = new ArrayList<String>();
								b = true;
							}
							file.dir = new File("Tests/" + testName);
							file.update();
							String ss = "";
							if (f.getText().contains("\"")
									&& f.getText().indexOf("\"") != f.getText().lastIndexOf("\""))
								ss = f.getText().substring(f.getText().indexOf("\"") + 1,
										f.getText().lastIndexOf("\""));
							for (Node n : file.getChildren())
								if (n instanceof Button)
									if (((Button) n).getText().equals(ss))
										((Button) n).fire();
							onGoToOther.t = () -> f.setText("\"" + file.selected + "\"");
						}
							break;
					}
				else
				{
					pane2.getTabs().add(restrictedT);
					restricted.getItems().clear();
					restricted.getItems().addAll(fields.get(syntaxLanguage).get(removeDigits(s.t)).restrictors);
					restricted.getSelectionModel().select(0);
					switch (fieldType)
					{
						case Html:
						case String:
						case Image:
						case Video:
						case Audio:
						case File:
						case Script:
							onGoToOther.t = () -> f
									.setText("\"" + restricted.getSelectionModel().getSelectedItem() + "\"");
							break;
						case Number:
						case Bool:
						case Object:
							onGoToOther.t = () -> f.setText(restricted.getSelectionModel().getSelectedItem());
							break;
					}
				}
			});
			Button btn = new Button();
			btn.setMaxWidth(5);
			btn.setLayoutX(p.getPrefWidth() / 2);
			span = new Pane(l, btn, f);
			class B
			{
				boolean b;
			}
			B b = new B();
			btn.setOnMousePressed(eve -> b.b = true);
			btn.setOnMouseReleased(eve -> b.b = false);
			Runnable r = () ->
			{
				btn.setLayoutX(
						Math.min(Math.max(btn.getLayoutX(), l.getLayoutBounds().getWidth()),
								p.getPrefWidth() - 60));
				l.setPrefWidth(btn.getLayoutX());
				f.setLayoutX(btn.getLayoutX() + 3);
				f.setPrefWidth(p.getPrefWidth() - btn.getLayoutX());
			};
			Runnable r2 = () ->
			{
				if (b.b)
				{
					btn.setLayoutX(span.screenToLocal(MouseInfo.getPointerInfo().getLocation().x, 0).getX());
					r.run();
				}
			};
			span.setOnMouseMoved(eve -> r2.run());
			span.setOnMouseDragged(eve -> r2.run());
			span.setOnDragDone(eve -> r2.run());
			l.setOnMouseMoved(eve -> r2.run());
			l.setOnMouseDragged(eve -> r2.run());
			l.setOnDragDone(eve -> r2.run());
			btn.setOnMouseMoved(eve -> r2.run());
			btn.setOnMouseDragged(eve -> r2.run());
			btn.setOnDragDone(eve -> r2.run());
			f.setOnMouseMoved(eve -> r2.run());
			f.setOnMouseDragged(eve -> r2.run());
			f.setOnDragDone(eve -> r2.run());

			if (p != null)
			{
				AccordionField field = new AccordionTextfield(s, pan1, l, f);
				p.add(field);
				yes.setOnAction(e -> p.remove(field));
			}
			r.run();
			pan1.prefWidthProperty().addListener((a, b2, c) ->
			{
				span.setPrefSize(pan1.getPrefWidth(), pan1.getPrefHeight());
				// f.setPrefWidth(12);
				// f.setLayoutX(span.getPrefWidth() - f.getPrefWidth());
				// l.setPrefWidth(span.getPrefWidth() - f.getPrefWidth());
				r.run();
			});
		}
		pan1.getChildren().add(span);
		// span.setMinWidth(p.getPrefWidth());
		// span.setPrefWidth(p.getPrefWidth());
		// span.setMaxWidth(p.getPrefWidth());
		pan1.setMinWidth(p.getPrefWidth());
		pan1.setPrefWidth(p.getPrefWidth());
		pan1.setMaxWidth(p.getPrefWidth());
		p.update();
	}

	private ArrayList<String> toFieldNameItems(ArrayList<String> fieldsNames)
	{
		ArrayList<String> items = new ArrayList<String>();
		if (fieldsNames != null)
			for (String field : fieldsNames)
				if (fields.get(syntaxLanguage).get(field) != null)
					if (fields.get(syntaxLanguage).get(field).type != FieldType.Object)
						items.add(field);
		return items;
	}

	private ArrayList<String> getChildrenOf(String field, String syntaxLanguage)
	{
		String restrictedField = field;
		for (; restrictedField.length() > 0 ? Character.isDigit(restrictedField.charAt(restrictedField.length() - 1))
				: false;)
			restrictedField = restrictedField.substring(0, restrictedField.length() - 1);
		Field f = fields.get(syntaxLanguage).get(restrictedField);
		if (f != null)
			if (restrictedField.equals(field))
				return f.children;
			else if (f.numberized)
				return f.children;
		return null;
	}

	private String toLabel(String name)
	{
		if (name == null || name.equals(""))
			return "";
		String res = (name.charAt(0) + "").toUpperCase();
		char last = name.charAt(0);
		for (int i = 1; i < name.length(); last = name.charAt(i), i++)
			if (Character.isUpperCase(name.charAt(i)) || Character.isDigit(name.charAt(i)))
				if (!(Character.isUpperCase(last) || Character.isDigit(last)))
					res += (" " + name.charAt(i)).toLowerCase();
				else res = res.substring(0, res.length() - 1) + (res.charAt(res.length() - 1) + "").toUpperCase()
						+ name.charAt(i);
			else res += ((Character.isUpperCase(last) || Character.isDigit(last)) && (res.length() > 1
					? (Character.isUpperCase(name.charAt(i - 2)) || Character.isDigit(name.charAt(i - 2)))
					: false) ? " " : "") + name.charAt(i);
		return res;
	}

	private void setOnDoubleClick(Node node, int time, EventHandler<? super MouseEvent> event)
	{
		if (event == null || node == null)
			return;
		Container<Long> n = new Container<Long>();
		n.t = 0l;
		EventHandler<? super MouseEvent> clickEvent = node.getOnMouseClicked();
		node.setOnMouseClicked(ev ->
		{
			if (clickEvent != null)
				clickEvent.handle(ev);
			long t1 = Calendar.getInstance().getTimeInMillis();
			if (t1 - n.t < time)
				event.handle(ev);
			n.t = t1;
		});
	}

	private Accordion addObject(Accordion p, String name, String tabs,
			HashMap<String, SimpleEntry<Accordion, Container<String>>> tabsAndPanes, TabPane pane2, boolean first,
			Container<String> parent, Pane pppppp)
	{
		String thisTabs = tabs != null ? tabs + "\t" : "";
		Pane PP1 = new Pane();
		Container<String> s = new Container<String>();
		s.t = name;
		TitledPane t = new TitledPane(toLabel(s.t), PP1);
		t.setAnimated(false);
		if (!first)
			t.setLayoutY(offsets.get(tabsAndPanes.get(tabs).getKey()).i * 30);
		Accordion pp = new Accordion(p, t);
		t.expandedProperty().addListener(e -> pp.update());
		PP1.getChildren().add(pp);
		pp.prefHeightProperty().addListener(eve -> t.setPrefHeight(pp.getPrefHeight() + 26));
		pp.setLayoutX(10);
		if (p != null)
			pp.setPrefWidth(p.getPrefWidth() - pp.getLayoutX());
		else if (pppppp != null)
			pp.setPrefWidth(pppppp.getPrefWidth() - pp.getLayoutX());
		if (tabsAndPanes.containsKey(thisTabs))
			tabsAndPanes.remove(thisTabs);
		tabsAndPanes.put(thisTabs, new SimpleEntry<Accordion, Container<String>>(pp, s));
		offsets.put(pp, new IntegerE(0));
		AccordionObject object = new AccordionObject(s, t, pp);
		if (p != null)
			p.add(object);
		if (pppppp != null)
			pppppp.getChildren().add(t);

		MenuItem itemAdd = new MenuItem("Add field");
		itemAdd.setOnAction(ev -> addField(pp, "newField", "", pane2, s));
		MenuItem objectAdd = new MenuItem("Add object");// TODO ������� ��������� ��������� ���������� � ��������,
														// ������ �������� � �����1, � �� �����
		objectAdd.setOnAction(ev -> addObject(pp, "newObject", thisTabs, tabsAndPanes, pane2, false, s, null));
		if (!first)
		{
			Runnable checkWrongSyntax = () -> t.setTextFill(checkWrongSyntax(s, parent));
			checkWrongSyntax.run();
			MenuItem change = new MenuItem("Change");
			change.setOnAction(e ->
			{
				onGoToOther.t.run();
				pane2.getTabs().clear();
				pane2.getTabs().add(fieldT);
				field.setText(s.t);
				field.setPrefSize(200, 30);
				field.getItems().clear();
				field.getItems().addAll(toObjectNameItems(getChildrenOf(parent.t, syntaxLanguage)));
				onGoToOther.t = () ->
				{
					t.setText(toLabel(s.t = field.getText()));
					checkWrongSyntax.run();
				};
			});

			Menu delete = new Menu("Delete");
			Menu cantCancel = new Menu("You can't cancel this action!");
			MenuItem yes = new MenuItem("Yes");

			if (p != null)
				yes.setOnAction(e -> p.remove(object));

			cantCancel.getItems().add(yes);
			cantCancel.getItems().add(new MenuItem("No"));
			delete.getItems().add(cantCancel);
			delete.getItems().add(new MenuItem("No"));

			t.setContextMenu(new ContextMenu(itemAdd, objectAdd, change, delete));
		}
		else t.setContextMenu(new ContextMenu(itemAdd, objectAdd));
		pp.update();
		t.setExpanded(false);
		return pp;
	}

	private ArrayList<String> toObjectNameItems(ArrayList<String> fieldsNames)
	{
		ArrayList<String> items = new ArrayList<String>();
		if (fieldsNames != null)
			for (String field : fieldsNames)
				if (fields.get(syntaxLanguage).get(field) != null)
					if (fields.get(syntaxLanguage).get(field).type == FieldType.Object)
						items.add(field);
		return items;
	}

	Accordion pp1;

	private String handleText(Accordion acc, String tabs)
	{
		String res = "";
		for (Integer i : acc.map2.keySet())
			if (acc.map2.get(i) instanceof AccordionObject)
			{
				res += tabs + ((AccordionObject) acc.map2.get(i)).name.t + ":\n";
				res += handleText(((AccordionObject) acc.map2.get(i)).accordion, tabs + "\t");
			}
			else if (acc.map2.get(i) instanceof AccordionField)
				res += tabs + ((AccordionField) acc.map2.get(i)).name.t + ":"
						+ ((AccordionField) acc.map2.get(i)).getValue() + "\n";

		return res;
	}

	private static class IntegerE
	{
		int i;

		public IntegerE(int i)
		{
			this.i = i;
		}
	}

	HashMap<Accordion, IntegerE> offsets;

	private String getStartTabs(String text)
	{
		String s = "";
		for (char c : text.toCharArray())
			if (c == '\t')
				s += c;
			else break;
		return s;
	}

	String syntaxLanguage;

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
	}
}