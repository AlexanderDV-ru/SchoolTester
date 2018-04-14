package ru.alexanderdv.schooltester.utilities.types;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.MessageSystem;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.types.Question.Answer;
import ru.alexanderdv.schooltester.utilities.types.Question.QuestionType;
import ru.alexanderdv.schooltester.utilities.types.Test.TableQuestionSelector.Cell;
import ru.alexanderdv.simpleutilities.Entry;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.8a
 */
public class Test
{
	private static final MessageSystem msgSys = Main.msgSys;
	private static final Image defaultIcon = loadDefaultIcon();
	private static final Random random = new Random();
	private final String syntaxLanguage;
	private final String programVersion;
	private final String colorType;
	private final String testVersion;
	private final String testCreationDate;
	private final String testLanguage;
	private final String testSubject;
	private final String authors;
	private final String name;
	private final String description;
	private final int maxTestTime;
	private final boolean unlimitedTime;
	private final Permissions permissionsToStart;
	private final Permissions permissionsToUseHints;
	private final Question[] questions;
	private final HashMap<Integer, Question> questionsByIndexes;
	private final Background wrongAnswerBackground;
	private final Background rightAnswerBackground;
	private final Background perfectAnswerBackground;
	private final TableQuestionSelector tableQuestionSelector;
	private final Image icon;

	public Test(String syntaxLanguage, String programVersion, String colorType, String testVersion, String testCreationDate, String testLanguage,
			String testSubject, String authors, String name, String description, int maxTestTime, boolean unlimitedTime, Permissions permissionsToStart,
			Permissions permissionsToUseHints, Question[] questions, Image wrongAnswer, Image rightAnswer, Image perfectAnswer,
			TableQuestionSelector tableQuestionSelector, Image icon)
	{
		this.syntaxLanguage = syntaxLanguage;
		this.programVersion = programVersion;
		this.colorType = colorType;
		this.testVersion = testVersion;
		this.testCreationDate = testCreationDate;
		this.testLanguage = testLanguage;
		this.testSubject = testSubject;
		this.authors = authors;
		this.name = name;
		this.description = description;
		this.icon = icon != null ? icon : defaultIcon;
		if (unlimitedTime || maxTestTime < 1)
		{
			this.unlimitedTime = true;
			this.maxTestTime = -1;
		}
		else
		{
			this.unlimitedTime = false;
			this.maxTestTime = maxTestTime;
		}
		this.permissionsToStart = permissionsToStart;
		this.permissionsToUseHints = permissionsToUseHints;
		this.questions = questions;
		if (tableQuestionSelector != null)
		{
			questionsByIndexes = new HashMap<Integer, Question>();
			for (Question q : questions)
				questionsByIndexes.put(q.getIndex(), q);
		}
		else questionsByIndexes = null;
		this.wrongAnswerBackground = wrongAnswer != null ? new Background(new BackgroundImage(wrongAnswer, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))
				: new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0)));
		this.rightAnswerBackground = wrongAnswer != null ? new Background(new BackgroundImage(rightAnswer, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))
				: new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), new Insets(0)));
		this.perfectAnswerBackground = wrongAnswer != null ? new Background(new BackgroundImage(perfectAnswer, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))
				: new Background(new BackgroundFill(Color.LIME, new CornerRadii(0), new Insets(0)));
		this.tableQuestionSelector = tableQuestionSelector;
	}

	private static Image loadDefaultIcon()
	{
		try
		{
			return new Image(Test.class.getResource("/defaultTestIcon.png").openStream());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new RuntimeException("defaultTestIcon.png was not loaded!");
	}

	public static class TableQuestionSelector
	{
		private final String[] cols;
		private final String[] rows;
		private final Cell[][] questionsTable;

		public TableQuestionSelector(String[] cols, String[] rows, Cell[][] questionsTable)
		{
			if (cols.length < 1 || questionsTable.length < 1)
				throw new IndexOutOfBoundsException("Columns count must be more than 0!");
			if (questionsTable[0].length < 1 || rows.length < 1)
				throw new IndexOutOfBoundsException("Rows count must be more than 0!");
			if (cols.length != questionsTable.length)
				throw new IndexOutOfBoundsException("Columns names length (" + cols.length + ") not equal questions table columns length ("
						+ questionsTable.length + ")!");
			if (rows.length != questionsTable[0].length)
				throw new IndexOutOfBoundsException("Rows names length (" + cols.length + ") not equal questions table rows length (" + questionsTable.length
						+ ")!");
			this.cols = cols;
			this.rows = rows;
			this.questionsTable = questionsTable;
		}

		/**
		 * @return the cols
		 */
		public String[] getCols()
		{
			return cols;
		}

		/**
		 * @return the rows
		 */
		public String[] getRows()
		{
			return rows;
		}

		/**
		 * @return the questions
		 */
		public Cell[][] getQuestionsTable()
		{
			return questionsTable;
		}

		public static class Cell
		{
			private final String text;
			private final int questionNumber;

			public Cell(String text, int questionNumber)
			{
				super();
				this.text = text;
				this.questionNumber = questionNumber;
			}

			/**
			 * @return the text
			 */
			public String getText()
			{
				return text;
			}

			/**
			 * @return the questionNumber
			 */
			public int getQuestionNumber()
			{
				return questionNumber;
			}
		}
	}

	public static class Permissions
	{
		private final boolean showLastAnswerQualityPermission;
		private final boolean showAllAnswersQualityPermission;
		private final boolean showRightAnswerPermission;
		private final boolean goToAllAnswersPermission;
		private final boolean skipPermission;
		private final boolean pausePermission;

		public Permissions(boolean showLastAnswerQualityPermission, boolean showAllAnswersQualityPermission, boolean showRightAnswerPermission,
				boolean goToAllAnswersPermission, boolean skipPermission, boolean pausePermission)
		{
			this.showLastAnswerQualityPermission = showLastAnswerQualityPermission;
			this.showAllAnswersQualityPermission = showAllAnswersQualityPermission;
			this.showRightAnswerPermission = showRightAnswerPermission;
			this.goToAllAnswersPermission = goToAllAnswersPermission;
			this.skipPermission = skipPermission;
			this.pausePermission = pausePermission;
		}

		/**
		 * @return the showLastAnswerQualityPermission
		 */
		public boolean isShowLastAnswerQualityPermission()
		{
			return showLastAnswerQualityPermission;
		}

		/**
		 * @return the showAllAnswersQualityPermission
		 */
		public boolean isShowAllAnswersQualityPermission()
		{
			return showAllAnswersQualityPermission;
		}

		/**
		 * @return the showRightAnswerPermission
		 */
		public boolean isShowRightAnswerPermission()
		{
			return showRightAnswerPermission;
		}

		/**
		 * @return the goToAllAnswersPermission
		 */
		public boolean isGoToAllAnswersPermission()
		{
			return goToAllAnswersPermission;
		}

		/**
		 * @return the skipPermission
		 */
		public boolean isSkipPermission()
		{
			return skipPermission;
		}

		/**
		 * @return the pausePermission
		 */
		public boolean isPausePermission()
		{
			return pausePermission;
		}

	}

	/**
	 * @return the syntaxLanguage
	 */
	public String getSyntaxLanguage()
	{
		return syntaxLanguage;
	}

	/**
	 * @return the programVersion
	 */
	public String getProgramVersion()
	{
		return programVersion;
	}

	/**
	 * @return the colorType
	 */
	public String getColorType()
	{
		return colorType;
	}

	/**
	 * @return the testVersion
	 */
	public String getTestVersion()
	{
		return testVersion;
	}

	/**
	 * @return the testCreationDate
	 */
	public String getTestCreationDate()
	{
		return testCreationDate;
	}

	/**
	 * @return the testLanguage
	 */
	public String getTestLanguage()
	{
		return testLanguage;
	}

	/**
	 * @return the testSubject
	 */
	public String getTestSubject()
	{
		return testSubject;
	}

	/**
	 * @return the authors
	 */
	public String getAuthors()
	{
		return authors;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the questions
	 */
	public Question[] getQuestions()
	{
		return questions;
	}

	/**
	 * @return the permissionToStart
	 */
	public Permissions getPermissionsToStart()
	{
		return permissionsToStart;
	}

	/**
	 * @return the permissionToUseHints
	 */
	public Permissions getPermissionsToUseHints()
	{
		return permissionsToUseHints;
	}

	/**
	 * @return the wrongAnswer
	 */
	public Background getWrongAnswerBackground()
	{
		return wrongAnswerBackground;
	}

	/**
	 * @return the rightAnswer
	 */
	public Background getRightAnswerBackground()
	{
		return rightAnswerBackground;
	}

	/**
	 * @return the perfectAnswer
	 */
	public Background getPerfectAnswerBackground()
	{
		return perfectAnswerBackground;
	}

	/**
	 * @return the tableQuestionSelector
	 */
	public TableQuestionSelector getTableQuestionSelector()
	{
		return tableQuestionSelector;
	}

	/**
	 * Parses text to "Test"
	 * 
	 * @param text
	 *            - text to parsing
	 * @return "Test" parsed from text
	 */
	public static Test valueOf(Config cfg)
	{
		try
		{
			if (!cfg.hasValue("syntaxLanguage"))
			{
				Config.print(msgSys.getMsg("testMustContainsSyntaxLanguage"));
				Main.exit(ExitCodes.TestNotHaveSyntaxLanguage);
			}
			String syntaxLanguage = cfg.getString("syntaxLanguage", null, false).toLowerCase();
			if (!cfg.hasValue(MessageSystem.getMsg("programVersion", syntaxLanguage)) || !cfg.hasValue(MessageSystem.getMsg("colorType", syntaxLanguage)))
			{
				Config.print(msgSys.getMsg("testMustContainsMainProperties"));
				Main.exit(ExitCodes.TestNotHaveMainValues);
			}
			switch (syntaxLanguage)
			{
				case "ru_ru":
				case "en_uk":
					break;
				default:
					Config.print("Syntax language '" + syntaxLanguage + "' is not supported!");
					Main.exit(ExitCodes.TestSyntaxLanguageIsNotSupported);
					break;
			}
			if (!cfg.getString(MessageSystem.getMsg("programVersion", syntaxLanguage), null, false).equals(Main.programVersion))
				FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance, (Stage) null, msgSys.getMsg("testVersionNotMatchWithProgramVersion"),
						JOptionPane.WARNING_MESSAGE, null, Main.isFxWindowFrame(), true);

			String colorType = cfg.getString(MessageSystem.getMsg("colorType", syntaxLanguage), null, false);

			String qnsStr = MessageSystem.getMsg("questions", syntaxLanguage);
			String qnStr = MessageSystem.getMsg("question", syntaxLanguage);
			String anrsStr = MessageSystem.getMsg("answers", syntaxLanguage);
			String ansStr = MessageSystem.getMsg("answer", syntaxLanguage);
			String awdStr = MessageSystem.getMsg("award", syntaxLanguage);
			String txtStr = MessageSystem.getMsg("text", syntaxLanguage);
			String htmlStr = "html";
			String fsStr = MessageSystem.getMsg("fontSize", syntaxLanguage);
			String minResStr = MessageSystem.getMsg("minimalResult", syntaxLanguage);
			String igreCaseStr = MessageSystem.getMsg("ignoreCase", syntaxLanguage);
			String handleOnlyMaximalStr = MessageSystem.getMsg("handleOnlyMaximal", syntaxLanguage);
			String igrdChrsStr = MessageSystem.getMsg("ignoredCharacters", syntaxLanguage);
			String awardsForAnswersStr = MessageSystem.getMsg("awardsForAnswers", syntaxLanguage);
			String awardForAnswerStr = MessageSystem.getMsg("awardForAnswer", syntaxLanguage);
			String answersIndexesStr = MessageSystem.getMsg("answersIndexes", syntaxLanguage);
			String answerIndexStr = MessageSystem.getMsg("answerIndex", syntaxLanguage);
			String numberStr = MessageSystem.getMsg("number", syntaxLanguage);
			String indexStr = MessageSystem.getMsg("index", syntaxLanguage);
			String indexesStr = MessageSystem.getMsg("indexes", syntaxLanguage);
			String indexesForNamesStr = MessageSystem.getMsg("indexesForNames", syntaxLanguage);
			String nameStr = MessageSystem.getMsg("naming", syntaxLanguage);
			String onlyThisIndexesStr = MessageSystem.getMsg("onlyThisIndexes", syntaxLanguage);
			String groupStr = MessageSystem.getMsg("group", syntaxLanguage);
			String randomizeStr = MessageSystem.getMsg("randomize", syntaxLanguage);
			String randomizeBlocksStr = MessageSystem.getMsg("randomizeBlocks", syntaxLanguage);
			String randomizeGroupsStr = MessageSystem.getMsg("randomizeGroups", syntaxLanguage);
			String imagesStr = MessageSystem.getMsg("images", syntaxLanguage);
			String imageStr = MessageSystem.getMsg("image", syntaxLanguage);
			String videosStr = MessageSystem.getMsg("videos", syntaxLanguage);
			String videoStr = MessageSystem.getMsg("video", syntaxLanguage);
			String audiosStr = MessageSystem.getMsg("audios", syntaxLanguage);
			String audioStr = MessageSystem.getMsg("audio", syntaxLanguage);
			String path = cfg.getFile().getAbsoluteFile().getParentFile().getAbsolutePath() + "\\";

			// Table how in game "Svoya igra"
			TableQuestionSelector tableQuestionSelector;
			if (cfg.hasValue("tableQuestionSelector"))
			{
				ArrayList<String> colsList = new ArrayList<String>(), rowsList = new ArrayList<String>();
				for (int i = 0; cfg.hasValue("tableQuestionSelector:colsNames:colName" + (i + 1)); colsList.add(cfg.getString(
						"tableQuestionSelector:colsNames:colName" + (i + 1), null, false)), i++);
				for (int i = 0; cfg.hasValue("tableQuestionSelector:rowsNames:rowName" + (i + 1)); rowsList.add(cfg.getString(
						"tableQuestionSelector:rowsNames:rowName" + (i + 1), null, false)), i++);
				String[] cols = colsList.toArray(new String[0]), rows = rowsList.toArray(new String[0]);
				Cell[][] questionsTable = new Cell[cols.length][rows.length];
				for (int i = 0; i < cols.length; i++)
					for (int j = 0; j < rows.length; questionsTable[i][j] = cfg.hasValue("tableQuestionSelector:questionsTable:cell" + (i + 1) + "_" + (j + 1))
							? new Cell(cfg.getString("tableQuestionSelector:questionsTable:cell" + (i + 1) + "_" + (j + 1) + ":" + txtStr, null, true), cfg
									.getInteger("tableQuestionSelector:questionsTable:cell" + (i + 1) + "_" + (j + 1) + ":questionNumber", -1, true))
							: null, j++);
				tableQuestionSelector = new TableQuestionSelector(cols, rows, questionsTable);
			}
			else tableQuestionSelector = null;
			boolean tabledView = tableQuestionSelector != null;

			// Questions
			int dqFont = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), 14, true);
			int daFont = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("answerFontSize", syntaxLanguage), 14, true);
			int stMinRes = cfg.getInteger(qnsStr + ":" + minResStr, Integer.MIN_VALUE, true);
			boolean randomizeAll = cfg.getBoolean(qnsStr + ":" + randomizeStr, !tabledView, true);
			boolean randomizeBlocks = cfg.getBoolean(qnsStr + ":" + randomizeBlocksStr, !tabledView, true);
			if (tabledView)
				if (randomizeAll || randomizeBlocks)
				{
					randomizeAll = randomizeBlocks = false;
					System.out.println("with tabled view randomizing is not compatible");
				}
			ArrayList<Question> questions = new ArrayList<Question>();
			// ArrayList<ArrayList<Question>> list1 = new ArrayList<ArrayList<Question>>();
			ArrayList<Question[]> mList1 = new ArrayList<Question[]>();
			for (QuestionType questionType : QuestionType.values())
				if (cfg.hasValue(qnsStr + ":" + MessageSystem.getMsg(questionType.name().toLowerCase(), syntaxLanguage) + ":" + groupStr + 1))
				{
					String gr = qnsStr + ":" + MessageSystem.getMsg(questionType.name().toLowerCase(), syntaxLanguage);
					String gs = gr + ":" + groupStr;
					Config group = cfg.getConfig(gs + 1, true);
					// ArrayList<Question> typedQuestions = new ArrayList<Question>();
					// ArrayList<Question> list2 = new ArrayList<Question>();
					// ArrayList<ArrayList<Question>> list = new ArrayList<ArrayList<Question>>();
					ArrayList<Question[]> mList = new ArrayList<Question[]>();
					for (int ji = 0; cfg.hasValue(gs + (ji + 1)) ? (group = cfg.getConfig(gs + (ji + 1), true)) != null || true : false; ji++)
					{
						ArrayList<Question> typedGroupQuestions = new ArrayList<Question>();
						Config question;// = group.getConfig(qnStr + 1, true);
						int maxAward = Integer.MIN_VALUE;
						boolean b = false;
						for (int i = 0; group.hasValue(qnStr + (i + 1)) ? (question = group.getConfig(qnStr + (i + 1), true)) != null || true : false; i++)
						{
							Question questionQ = loadQuestion(question, path, anrsStr, syntaxLanguage, daFont, imagesStr, imageStr, videosStr, videoStr,
									audiosStr, audioStr, ansStr, htmlStr, txtStr, awdStr, fsStr, questionType, minResStr, stMinRes, handleOnlyMaximalStr,
									dqFont, igrdChrsStr, igreCaseStr, awardsForAnswersStr, awardForAnswerStr, answersIndexesStr, answerIndexStr, numberStr,
									indexStr, indexesStr, indexesForNamesStr, nameStr, onlyThisIndexesStr, tabledView);
							if (!b)
								maxAward = questionQ.getMaxAward();
							b = true;
							if (maxAward != questionQ.getMaxAward())
							{
								FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance, (Stage) null, msgSys.getMsg("awardsInGroupNotMatch")
										+ "\nFile: '" + cfg.getFile().getName() + "' Path: '" + group.getFullPathInParents() + "'" + " a1: " + maxAward
										+ " a2: " + questionQ.getMaxAward(), JOptionPane.WARNING_MESSAGE, null, Main.isFxWindowFrame(), true);
								Main.exit(ExitCodes.TestAwardsOfQuestionsInGroupNotMatch);
							}
							typedGroupQuestions.add(questionQ);
						}
						if (typedGroupQuestions.size() > 0)
						{
							int questionsToTestAmount = group.getInteger(MessageSystem.getMsg("questionsToTestAmount", syntaxLanguage), typedGroupQuestions
									.size(), true);
							boolean randomize = group.getBoolean(randomizeStr, !tabledView, true);
							if (tabledView)
								if (randomize)
								{
									randomize = false;
									System.out.println("with tabled view randomizing is not compatible");
								}
							if (questionsToTestAmount > typedGroupQuestions.size())
							{
								questionsToTestAmount = typedGroupQuestions.size();
								FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance, (Stage) null, msgSys.getMsg(
										"questionsToTestAmountMoreThanQuestionsAmount") + "\nFile: '" + cfg.getFile().getName() + "' Path: '" + group
												.getFullPathInParents() + "'", JOptionPane.WARNING_MESSAGE, null, Main.isFxWindowFrame(), true);
							}
							if (questionsToTestAmount <= 0)
							{
								questionsToTestAmount = typedGroupQuestions.size();
								FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance, (Stage) null, msgSys.getMsg(
										"questionsToTestAmountLessThanOne") + "\nFile: '" + cfg.getFile().getName() + "' Path: '" + group.getFullPathInParents()
										+ "'", JOptionPane.WARNING_MESSAGE, null, Main.isFxWindowFrame(), true);
							}
							Question[] array = new Question[questionsToTestAmount];
							if (randomize)
								mList.add(randomizeToArray(typedGroupQuestions, array));
							else mList.add(nonrandomizeToArray(typedGroupQuestions, array));
						}
					}
					boolean randomize1 = cfg.getBoolean(gr + ":" + randomizeStr, !tabledView, true);
					boolean randomizeGroups = cfg.getBoolean(gr + ":" + randomizeGroupsStr, !tabledView, true);
					if (tabledView)
						if (randomize1 || randomizeGroups)
						{
							randomize1 = randomizeGroups = false;
							System.out.println("with tabled view randomizing is not compatible");
						}
					Question[][] arr = randomizeGroups ? randomizeToArray(mList, new Question[mList.size()][]) : mList.toArray(new Question[mList.size()][]);
					ArrayList<Question> ll = new ArrayList<Question>();
					for (Question[] qs : arr)
						if (qs != null)
							for (Question q : qs)
								ll.add(q);
					Question[] array = new Question[ll.size()];
					if (randomize1)
						mList1.add(randomizeToArray(ll, array));
					else mList1.add(nonrandomizeToArray(ll, array));
				}
			Question[][] mArray = new Question[mList1.size()][];
			if (randomizeBlocks)
				mArray = randomizeToArray(mList1, mArray);
			else mArray = mList1.toArray(mArray);
			for (Question[] qs : mArray)
				if (qs != null)
					for (Question q : qs)
						questions.add(q);
			if (questions.size() <= 0)
			{
				FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance, (Stage) null, msgSys.getMsg("testNotHaveQuestions") + "\nFile: '" + cfg
						.getFile().getName() + "'", JOptionPane.ERROR_MESSAGE, null, Main.isFxWindowFrame(), true);
				Main.exit(ExitCodes.TestNotHaveQuestions);
			}
			Question[] array = new Question[questions.size()];
			if (randomizeAll)
				array = randomizeToArray(questions, array);
			else array = questions.toArray(array);
			String startPermsStr = MessageSystem.getMsg("startPermissions", syntaxLanguage);
			Permissions startPerms = new Permissions(cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("showLastAnswerQualityPermission",
					syntaxLanguage), false, false), cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("showAllAnswersQualityPermission",
							syntaxLanguage), false, false), cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("showRightAnswerPermission",
									syntaxLanguage), false, false), cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("goToAllAnswersPermission",
											syntaxLanguage), false, false), cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("skipPermission",
													syntaxLanguage), false, false), cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("pausePermission",
															syntaxLanguage), false, false));
			String hintsPermsStr = MessageSystem.getMsg("hintsPermissions", syntaxLanguage);
			Permissions hintsPerms = new Permissions(cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("showLastAnswerQualityPermission",
					syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("showAllAnswersQualityPermission",
							syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("showRightAnswerPermission",
									syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("goToAllAnswersPermission",
											syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("skipPermission",
													syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("pausePermission",
															syntaxLanguage), false, false));
			boolean unlimitedTime = cfg.getValue(MessageSystem.getMsg("maxTestTime", syntaxLanguage)).replace(" ", "").replace("\t", "").equalsIgnoreCase(
					"unlimited");
			return new Test(syntaxLanguage, cfg.getString(MessageSystem.getMsg("programVersion", syntaxLanguage), null, false), colorType, cfg.getString(
					MessageSystem.getMsg("testVersion", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("testCreationDate", syntaxLanguage),
							null, false), cfg.getString(MessageSystem.getMsg("testLanguage", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg(
									"testSubject", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("authors", syntaxLanguage), null, false),
					cfg.getString(MessageSystem.getMsg("naming", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("description",
							syntaxLanguage), null, false), unlimitedTime ? -1
									: cfg.getInteger(MessageSystem.getMsg("maxTestTime", syntaxLanguage), null, false), unlimitedTime, startPerms, hintsPerms,
					array, cfg.hasValue("wrongAnswerImage") ? getImage(new File("Tests/" + cfg.getFile().getName().replace(".test", "") + "/" + cfg.getString(
							"wrongAnswerImage", null, false)), true) : getImage(new File("wrongAnswerImage.png"), false), cfg.hasValue("rightAnswerImage")
									? getImage(new File("Tests/" + cfg.getFile().getName().replace(".test", "") + "/" + cfg.getString("rightAnswerImage", null,
											false)), true)
									: getImage(new File("rightAnswerImage.png"), false), cfg.hasValue("perfectAnswerImage") ? getImage(new File("Tests/" + cfg
											.getFile().getName().replace(".test", "") + "/" + cfg.getString("perfectAnswerImage", null, false)), true)
											: getImage(new File("perfectAnswerImage.png"), false), tableQuestionSelector, getImage(new File("testIcon.png"),
													false));
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			Main.exit(ExitCodes.UnknownError);
		}
		return null;
	}

	private static Image getImage(File file, boolean b)
	{
		try
		{
			return new Image(file.getAbsolutePath());
		}
		catch (Exception e)
		{
			if (b)
				FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance, (Stage) null, msgSys.getMsg("imageNotLoaded"), JOptionPane.ERROR_MESSAGE,
						null, Main.isFxWindowFrame(), true);
			return null;
		}
	}

	public int getMaxTestTime()
	{
		if (unlimitedTime)
			throw new NullPointerException("Time is unlimited!");
		return maxTestTime;
	}

	private static Question loadQuestion(Config question, String path, String anrsStr, String syntaxLanguage, int daFont, String imagesStr, String imageStr,
			String videosStr, String videoStr, String audiosStr, String audioStr, String ansStr, String htmlStr, String txtStr, String awdStr, String fsStr,
			QuestionType type, String minResStr, int stMinRes, String handleOnlyMaximalStr, int dqFont, String igrdChrsStr, String igreCaseStr,
			String awardsForAnswersStr, String awardForAnswerStr, String answersIndexesStr, String answerIndexStr, String numberStr, String indexStr,
			String indexesStr, String indexesForNamesStr, String nameStr, String onlyThisIndexesStr, boolean tabledView)
	{
		boolean multiline = true;
		int dqaFont = question.getInteger(anrsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), daFont, true);

		ArrayList<Answer> answers = new ArrayList<Answer>();
		Config ans = question.getConfig(anrsStr + ":" + ansStr + 1, true);
		for (int j = 0; question.hasValue(anrsStr + ":" + ansStr + (j + 1)) ? (ans = question.getConfig(anrsStr + ":" + ansStr + (j + 1), true)) != null || true
				: false; j++)
		{
			answers.add(new Answer(ans.getString(htmlStr, null, true), ans.getString(txtStr, null, false).replace("\\n", multiline ? "\n" : "\\n"), new Font(
					"Ms Comic Sans", ans.getInteger(fsStr, dqaFont, true)), ans.getInteger(awdStr, 0, true), j));
		}
		ArrayList<String> isfmsList = new ArrayList<String>();
		// String ifmO = question.getString(indexesForNamesStr + ":" + nameStr + (1), null, true);
		for (int j = 0; question.hasValue(indexesForNamesStr + ":" + nameStr + (j + 1)); isfmsList.add(question.getString(indexesForNamesStr + ":" + nameStr
				+ (j + 1), null, true)), j++);
		String[] isfms = randomizeToArray(isfmsList, new String[isfmsList.size()]);
		int[] isfms2 = new int[isfms.length];
		for (int i = 0; i < isfms2.length; i++)
			isfms2[i] = isfmsList.indexOf(isfms[i]);
		ArrayList<Image> imagesList = new ArrayList<Image>();
		for (int j = 0; question.hasValue(imagesStr + ":" + imageStr + (j + 1)); imagesList.add(new Image(new File(path + question.getString(imagesStr + ":"
				+ imageStr + (j + 1), null, true)).toURI().toString())), j++);
		ArrayList<Media> videosList = new ArrayList<Media>();
		for (int j = 0; question.hasValue(videosStr + ":" + videoStr + (j + 1)); videosList.add(new Media(new File(path + question.getString(videosStr + ":"
				+ videoStr + (j + 1), null, true)).toURI().toString())), j++);
		ArrayList<Media> audiosList = new ArrayList<Media>();
		for (int j = 0; question.hasValue(audiosStr + ":" + audioStr + (j + 1)); audiosList.add(new Media(new File(path + question.getString(audiosStr + ":"
				+ audioStr + (j + 1), null, true)).toURI().toString())), j++);
		Question q;
		switch (type)
		{
			case Arrangement:
			case Matching:
			{
				HashMap<HashMap<Integer, Integer>, Integer> answerss = new HashMap<HashMap<Integer, Integer>, Integer>();
				Config answerssn = question.getConfig(awardsForAnswersStr + ":" + awardForAnswerStr + (1), true);
				for (int j = 0; question.hasValue(awardsForAnswersStr + ":" + awardForAnswerStr + (j + 1)) ? (answerssn = question.getConfig(awardsForAnswersStr
						+ ":" + awardForAnswerStr + (j + 1), true)) != null || true : false; j++)
				{
					HashMap<Integer, Integer> answersss = new HashMap<Integer, Integer>();
					Config answerssnn = answerssn.getConfig(answersIndexesStr + ":" + answerIndexStr + (1), true);
					for (int k = 0; answerssn.hasValue(answersIndexesStr + ":" + answerIndexStr + (k + 1)) ? (answerssnn = answerssn.getConfig(answersIndexesStr
							+ ":" + answerIndexStr + (k + 1), true)) != null || true : false; k++)
						answersss.put(answerssnn.getInteger(numberStr, null, false) - 1, answerssnn.getInteger(indexStr, null, false) - 1);
					answerss.put(answersss, answerssn.getInteger(awdStr, 0, true));
				}

				q = new Question(nonrandomizeToArray(imagesList, new Image[imagesList.size()]), nonrandomizeToArray(videosList, new Media[videosList.size()]),
						nonrandomizeToArray(audiosList, new Media[audiosList.size()]), question.getString(htmlStr, null, true), question.getString(txtStr, null,
								false), new Font("Ms Comic Sans", question.getInteger(fsStr, dqFont, true)), question.getInteger(awdStr, 0, true), question
										.getInteger(minResStr, stMinRes, true), type, randomizeToArray(answers, new Answer[answers.size()]), question
												.getBoolean(handleOnlyMaximalStr, null, false), answerss, isfms, isfms2, question.getString(igrdChrsStr, "",
														true), question.getBoolean(igreCaseStr, true, true), false, tabledView ? question.getInteger(indexStr,
																-1, true) : -1);
				if (tabledView && q.getIndex() < 0)
				{
					System.out.println("error: with tabled view must be index");
					Main.exit(ExitCodes.WrongSyntax);
				}
			}
				break;
			case Distribution:
			{
				HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer> answerss2 = new HashMap<HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>, Integer>();
				Config answerssn = question.getConfig(awardsForAnswersStr + ":" + awardForAnswerStr + (1), true);
				for (int j = 0; question.hasValue(awardsForAnswersStr + ":" + awardForAnswerStr + (j + 1)) ? (answerssn = question.getConfig(awardsForAnswersStr
						+ ":" + awardForAnswerStr + (j + 1), true)) != null || true : false; j++)
				{
					HashMap<Entry<Integer, Boolean>, ArrayList<Integer>> answersss = new HashMap<Entry<Integer, Boolean>, ArrayList<Integer>>();
					Config answerssnn = answerssn.getConfig(answersIndexesStr + ":" + answerIndexStr + (1), true);
					for (int k = 0; answerssn.hasValue(answersIndexesStr + ":" + answerIndexStr + (k + 1)) ? (answerssnn = answerssn.getConfig(answersIndexesStr
							+ ":" + answerIndexStr + (k + 1), true)) != null || true : false; k++)
					{
						ArrayList<Integer> answersssi = new ArrayList<Integer>();
						int index = answerssnn.getInteger(indexesStr + ":" + indexStr + (1), -1, true);
						for (int ki = 0; answerssnn.hasValue(indexesStr + ":" + indexStr + (ki + 1)); ki++, index = answerssnn.getInteger(indexesStr + ":"
								+ indexStr + (ki + 1), -1, true))
							answersssi.add(index - 1);
						answersss.put(new Entry<Integer, Boolean>(answerssnn.getInteger(numberStr, null, false) - 1, answerssnn.getBoolean(onlyThisIndexesStr,
								true, true)), answersssi);
					}
					answerss2.put(answersss, answerssn.getInteger(awdStr, 0, true));
				}

				q = new Question(nonrandomizeToArray(imagesList, new Image[imagesList.size()]), nonrandomizeToArray(videosList, new Media[videosList.size()]),
						nonrandomizeToArray(audiosList, new Media[audiosList.size()]), question.getString(htmlStr, null, true), question.getString(txtStr, null,
								false), new Font("Ms Comic Sans", question.getInteger(fsStr, dqFont, true)), question.getInteger(awdStr, 0, true), question
										.getInteger(minResStr, stMinRes, true), type, randomizeToArray(answers, new Answer[answers.size()]), question
												.getBoolean(handleOnlyMaximalStr, null, false), answerss2, isfms, isfms2, question.getString(igrdChrsStr, "",
														true), question.getBoolean(igreCaseStr, true, true), tabledView ? question.getInteger(indexStr, -1,
																true) : -1);
				if (tabledView && q.getIndex() < 0)
				{
					System.out.println("error: with tabled view must be index");
					Main.exit(ExitCodes.WrongSyntax);
				}
			}
				break;
			case EnterText:
			case PickOne:
			case SelectSome:
			default:
			{
				String ignoredCharacters = question.getString(igrdChrsStr, "", true);
				boolean ignoreCase = question.getBoolean(igreCaseStr, true, true);
				if (question.hasValue(igrdChrsStr) && type != QuestionType.EnterText)
					System.out.println("To use ignored characters type must be EnterText");
				if (question.hasValue(igreCaseStr) && type != QuestionType.EnterText)
					System.out.println("To use ignore case type must be EnterText");
				q = new Question(nonrandomizeToArray(imagesList, new Image[imagesList.size()]), nonrandomizeToArray(videosList, new Media[videosList.size()]),
						nonrandomizeToArray(audiosList, new Media[audiosList.size()]), question.getString(htmlStr, null, true), question.getString(txtStr, null,
								false).replace("\\n", multiline ? "\n" : "\\n"), new Font("Ms Comic Sans", question.getInteger(fsStr, dqFont, true)), question
										.getInteger(awdStr, 0, true), question.getInteger(minResStr, stMinRes, true), type, randomizeToArray(answers,
												new Answer[answers.size()]), ignoredCharacters, ignoreCase, tabledView ? question.getInteger(indexStr, -1, true)
														: -1);
				if (tabledView && q.getIndex() < 0)
				{
					System.out.println("error: with tabled view must be index");
					Main.exit(ExitCodes.WrongSyntax);
				}
			}
				break;
		}
		return q;
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] randomizeToArray(ArrayList<T> list1, T[] array)
	{
		ArrayList<T> list = (ArrayList<T>) list1.clone();
		for (int i = 0; i < Math.min(array.length, list1.size()); i++)
			array[i] = list.remove(random.nextInt(list.size()));
		return array;
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] nonrandomizeToArray(ArrayList<T> list1, T[] array)
	{
		ArrayList<T> list = (ArrayList<T>) list1.clone();
		for (int i = 0; i < Math.min(array.length, list1.size()); i++)
			array[i] = list.remove(0);
		return array;
	}

	public boolean timeIsUnlimited()
	{
		return unlimitedTime;
	}

	public int getQuestionNumberAtQuestionsByQuestionIndex(int questionNumber)
	{
		if (questionsByIndexes == null)
			throw new NullPointerException("Table question selector must be not null!");
		ArrayList<Question> qs = new ArrayList<Question>();
		for (Question q : questions)
			qs.add(q);
		return qs.indexOf(questionsByIndexes.get(questionNumber));
	}

	public Image getIcon()
	{
		return icon;
	}
}
