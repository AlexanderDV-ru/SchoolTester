package ru.alexanderdv.schooltester.utilities;

import java.io.File;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.main.teacher.TeachersTestsControlPart;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.Question.Answer;
import ru.alexanderdv.schooltester.utilities.Question.QuestionType;
import ru.alexanderdv.simpleutilities.Entry;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class Test
{
	private static final MessageSystem msgSys = Main.msgSys;
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
	private final Permission permissionToStart;
	private final Permission permissionToUseHints;
	private final Question[] questions;
	private final Background wrongAnswerBackground;
	private final Background rightAnswerBackground;
	private final Background perfectAnswerBackground;

	public Test(String syntaxLanguage, String programVersion, String colorType, String testVersion, String testCreationDate, String testLanguage,
			String testSubject, String authors, String name, String description, int maxTestTime, Permission permissionToStart, Permission permissionToUseHints,
			Question[] questions, Image wrongAnswer, Image rightAnswer, Image perfectAnswer)
	{
		super();
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
		this.maxTestTime = maxTestTime;
		this.permissionToStart = permissionToStart;
		this.permissionToUseHints = permissionToUseHints;
		this.questions = questions;
		this.wrongAnswerBackground = wrongAnswer != null ? new Background(new BackgroundImage(wrongAnswer, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))
				: new Background(new BackgroundFill(Color.RED, new CornerRadii(5), new Insets(0)));
		this.rightAnswerBackground = wrongAnswer != null ? new Background(new BackgroundImage(rightAnswer, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))
				: new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(5), new Insets(0)));
		this.perfectAnswerBackground = wrongAnswer != null ? new Background(new BackgroundImage(perfectAnswer, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT))
				: new Background(new BackgroundFill(Color.LIME, new CornerRadii(5), new Insets(0)));
	}

	public static class Permission
	{
		private final boolean showLastAnswerQualityPermission;
		private final boolean showAllAnswersQualityPermission;
		private final boolean showRightAnswerPermission;
		private final boolean goToAllAnswersPermission;
		private final boolean skipPermission;
		private final boolean pausePermission;

		public Permission(boolean showLastAnswerQualityPermission, boolean showAllAnswersQualityPermission, boolean showRightAnswerPermission,
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
	public Permission getPermissionToStart()
	{
		return permissionToStart;
	}

	/**
	 * @return the permissionToUseHints
	 */
	public Permission getPermissionToUseHints()
	{
		return permissionToUseHints;
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
				FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance.stage, null, msgSys.getMsg("testVersionNotMatchWithProgramVersion"),
						JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);

			String colorType = cfg.getString(MessageSystem.getMsg("colorType", syntaxLanguage), null, false);

			String qnsStr = MessageSystem.getMsg("questions", syntaxLanguage);
			String qnStr = MessageSystem.getMsg("question", syntaxLanguage);
			String anrsStr = MessageSystem.getMsg("answers", syntaxLanguage);
			String ansStr = MessageSystem.getMsg("answer", syntaxLanguage);
			String awdStr = MessageSystem.getMsg("award", syntaxLanguage);
			String txtStr = MessageSystem.getMsg("text", syntaxLanguage);
			String fsStr = MessageSystem.getMsg("fontSize", syntaxLanguage);
			String minResStr = MessageSystem.getMsg("minimalResult", syntaxLanguage);
			String igreCaseStr = MessageSystem.getMsg("ignoreCase", syntaxLanguage);
			String handleOnlyMaximalStr = MessageSystem.getMsg("handleOnlyMaximal", syntaxLanguage);
			String igrdChrsStr = MessageSystem.getMsg("ignoredCharacters", syntaxLanguage);
			int dqFont = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), 14, true);
			int daFont = cfg.getInteger(qnsStr + ":" + MessageSystem.getMsg("answerFontSize", syntaxLanguage), 14, true);
			int stMinRes = cfg.getInteger(qnsStr + ":" + minResStr, Integer.MIN_VALUE, true);
			boolean randomizeAll = cfg.getBoolean(qnsStr + ":" + "randomize", true, true);
			boolean randomizeBlocks = cfg.getBoolean(qnsStr + ":" + "randomizeBlocks", true, true);
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
							Question questionQ = loadQuestion(question, anrsStr, syntaxLanguage, daFont, ansStr, txtStr, awdStr, fsStr, questionType, minResStr,
									stMinRes, handleOnlyMaximalStr, dqFont, igrdChrsStr, igreCaseStr, awardsForAnswersStr, awardForAnswerStr, answersIndexesStr,
									answerIndexStr, numberStr, indexStr, indexesStr, indexesForNamesStr, nameStr, onlyThisIndexesStr);
							if (!b)
								maxAward = questionQ.getMaxAward();
							b = true;
							if (maxAward != questionQ.getMaxAward())
							{
								FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance.stage, null, msgSys.getMsg("awardsInGroupNotMatch")
										+ "\nFile: '" + cfg.getFile().getName() + "' Path: '" + group.getFullPathInParents() + "'" + " a1: " + maxAward
										+ " a2: " + questionQ.getMaxAward(), JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(),
										true);
								Main.exit(ExitCodes.TestAwardsOfQuestionsInGroupNotMatch);
							}
							typedGroupQuestions.add(questionQ);
						}
						if (typedGroupQuestions.size() > 0)
						{
							int questionsToTestAmount = group.getInteger(MessageSystem.getMsg("questionsToTestAmount", syntaxLanguage), typedGroupQuestions
									.size(), true);
							boolean randomize = group.getBoolean(MessageSystem.getMsg("randomize", syntaxLanguage), true, true);
							if (questionsToTestAmount > typedGroupQuestions.size())
							{
								questionsToTestAmount = typedGroupQuestions.size();
								FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance.stage, null, msgSys.getMsg(
										"questionsToTestAmountMoreThanQuestionsAmount") + "\nFile: '" + cfg.getFile().getName() + "' Path: '" + group
												.getFullPathInParents() + "'", JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(),
										true);
							}
							if (questionsToTestAmount <= 0)
							{
								questionsToTestAmount = typedGroupQuestions.size();
								FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance.stage, null, msgSys.getMsg("questionsToTestAmountLessThanOne")
										+ "\nFile: '" + cfg.getFile().getName() + "' Path: '" + group.getFullPathInParents() + "'", JOptionPane.WARNING_MESSAGE,
										JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
							}
							Question[] array = new Question[questionsToTestAmount];
							if (randomize)
								mList.add(array = randomizeToArray(typedGroupQuestions, array));
							// else mList.add(array = typedGroupQuestions.toArray(array));
							else mList.add(array = mList.get(mList.size() - 1));
						}
					}
					boolean randomize1 = cfg.getBoolean(gr + ":" + MessageSystem.getMsg("randomize", syntaxLanguage), true, true);
					boolean randomizeGroups = cfg.getBoolean(gr + ":" + MessageSystem.getMsg("randomizeGroups", syntaxLanguage), true, true);
					Question[][] arr = randomizeGroups ? randomizeToArray(mList, new Question[mList.size()][]) : mList.toArray(new Question[mList.size()][]);
					ArrayList<Question> ll = new ArrayList<Question>();
					for (Question[] qs : arr)
						if (qs != null)
							for (Question q : qs)
								ll.add(q);
					Question[] array = new Question[ll.size()];
					if (randomize1)
						mList1.add(array = randomizeToArray(ll, array));
					// else mList.add(array = typedGroupQuestions.toArray(array));
					else mList1.add(array = mList1.get(mList1.size() - 1));
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
				FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance.stage, null, msgSys.getMsg("testNotHaveQuestions") + "\nFile: '" + cfg
						.getFile().getName() + "'", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
				Main.exit(ExitCodes.TestNotHaveQuestions);
			}
			Question[] array = new Question[questions.size()];
			if (randomizeAll)
				array = randomizeToArray(questions, array);
			else array = questions.toArray(array);
			String startPermsStr = MessageSystem.getMsg("startPermissions", syntaxLanguage);
			Permission startPerms = new Permission(cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("showLastAnswerQualityPermission", syntaxLanguage),
					false, false), cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("showAllAnswersQualityPermission", syntaxLanguage), false, false),
					cfg.getBoolean(startPermsStr + ":" + MessageSystem.getMsg("showRightAnswerPermission", syntaxLanguage), false, false), cfg.getBoolean(
							startPermsStr + ":" + MessageSystem.getMsg("goToAllAnswersPermission", syntaxLanguage), false, false), cfg.getBoolean(startPermsStr
									+ ":" + MessageSystem.getMsg("skipPermission", syntaxLanguage), false, false), cfg.getBoolean(startPermsStr + ":"
											+ MessageSystem.getMsg("pausePermission", syntaxLanguage), false, false));
			String hintsPermsStr = MessageSystem.getMsg("hintsPermissions", syntaxLanguage);
			Permission hintsPerms = new Permission(cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("showLastAnswerQualityPermission", syntaxLanguage),
					false, false), cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("showAllAnswersQualityPermission", syntaxLanguage), false, false),
					cfg.getBoolean(hintsPermsStr + ":" + MessageSystem.getMsg("showRightAnswerPermission", syntaxLanguage), false, false), cfg.getBoolean(
							hintsPermsStr + ":" + MessageSystem.getMsg("goToAllAnswersPermission", syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr
									+ ":" + MessageSystem.getMsg("skipPermission", syntaxLanguage), false, false), cfg.getBoolean(hintsPermsStr + ":"
											+ MessageSystem.getMsg("pausePermission", syntaxLanguage), false, false));
			return new Test(syntaxLanguage, cfg.getString(MessageSystem.getMsg("programVersion", syntaxLanguage), null, false), colorType, cfg.getString(
					MessageSystem.getMsg("testVersion", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("testCreationDate", syntaxLanguage),
							null, false), cfg.getString(MessageSystem.getMsg("testLanguage", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg(
									"testSubject", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("authors", syntaxLanguage), null, false),
					cfg.getString(MessageSystem.getMsg("naming", syntaxLanguage), null, false), cfg.getString(MessageSystem.getMsg("description",
							syntaxLanguage), null, false), cfg.getInteger(MessageSystem.getMsg("maxTestTime", syntaxLanguage), null, false), startPerms,
					hintsPerms, array, cfg.hasValue("wrongAnswerImage") ? getImage(new File("Tests/" + cfg.getFile().getName().replace(".test", "") + "/" + cfg
							.getString("wrongAnswerImage", null, false)), true) : getImage(new File("wrongAnswerImage.png"), false), cfg.hasValue(
									"rightAnswerImage") ? getImage(new File("Tests/" + cfg.getFile().getName().replace(".test", "") + "/" + cfg.getString(
											"rightAnswerImage", null, false)), true) : getImage(new File("rightAnswerImage.png"), false), cfg.hasValue(
													"perfectAnswerImage") ? getImage(new File("Tests/" + cfg.getFile().getName().replace(".test", "") + "/"
															+ cfg.getString("perfectAnswerImage", null, false)), true)
															: getImage(new File("perfectAnswerImage.png"), false));
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
				FXDialogsGenerator.showFXDialog(TeachersTestsControlPart.instance.stage, null, msgSys.getMsg("imageNotLoaded"), JOptionPane.ERROR_MESSAGE,
						JOptionPane.DEFAULT_OPTION, Main.isFxWindowFrame(), true);
			return null;
		}
	}

	public int getMaxTestTime()
	{
		return maxTestTime;
	}

	private static Question loadQuestion(Config question, String anrsStr, String syntaxLanguage, int daFont, String ansStr, String txtStr, String awdStr,
			String fsStr, QuestionType type, String minResStr, int stMinRes, String handleOnlyMaximalStr, int dqFont, String igrdChrsStr, String igreCaseStr,
			String awardsForAnswersStr, String awardForAnswerStr, String answersIndexesStr, String answerIndexStr, String numberStr, String indexStr,
			String indexesStr, String indexesForNamesStr, String nameStr, String onlyThisIndexesStr)
	{
		boolean multiline = true;
		int dqaFont = question.getInteger(anrsStr + ":" + MessageSystem.getMsg("fontSize", syntaxLanguage), daFont, true);

		ArrayList<Answer> answers = new ArrayList<Answer>();
		Config ans = question.getConfig(anrsStr + ":" + ansStr + 1, true);
		for (int j = 0; question.hasValue(anrsStr + ":" + ansStr + (j + 1)) ? (ans = question.getConfig(anrsStr + ":" + ansStr + (j + 1), true)) != null || true
				: false; j++)
		{
			answers.add(new Answer(ans.getString(txtStr, null, false).replace("\\n", multiline ? "\n" : "\\n"), new Font("Ms Comic Sans", ans.getInteger(fsStr,
					dqaFont, true)), ans.getInteger(awdStr, 0, true), j));
		}
		ArrayList<String> isfmsList = new ArrayList<String>();
		String ifmO = question.getString(indexesForNamesStr + ":" + nameStr + (1), null, true);
		for (int j = 0; question.hasValue(indexesForNamesStr + ":" + nameStr + (j + 1)); j++, ifmO = question.getString(indexesForNamesStr + ":" + nameStr + (j
				+ 1), null, true))
			isfmsList.add(ifmO);
		String[] isfms = randomizeToArray(isfmsList, new String[isfmsList.size()]);
		int[] isfms2 = new int[isfms.length];
		for (int i = 0; i < isfms2.length; i++)
			isfms2[i] = isfmsList.indexOf(isfms[i]);
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

				q = new Question(question.getString(txtStr, null, false), new Font("Ms Comic Sans", question.getInteger(fsStr, dqFont, true)), question
						.getInteger(awdStr, 0, true), question.getInteger(minResStr, stMinRes, true), type, randomizeToArray(answers, new Answer[answers
								.size()]), question.getBoolean(handleOnlyMaximalStr, null, false), answerss, isfms, isfms2, question.getString(igrdChrsStr, "",
										true), question.getBoolean(igreCaseStr, true, true), false);
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

				q = new Question(question.getString(txtStr, null, false), new Font("Ms Comic Sans", question.getInteger(fsStr, dqFont, true)), question
						.getInteger(awdStr, 0, true), question.getInteger(minResStr, stMinRes, true), type, randomizeToArray(answers, new Answer[answers
								.size()]), question.getBoolean(handleOnlyMaximalStr, null, false), answerss2, isfms, isfms2, question.getString(igrdChrsStr, "",
										true), question.getBoolean(igreCaseStr, true, true));
			}
				break;
			case EnterText:
			case PickOne:
			case SelectSome:
			default:
			{
				q = new Question(question.getString(txtStr, null, false).replace("\\n", multiline ? "\n" : "\\n"), new Font("Ms Comic Sans", question
						.getInteger(fsStr, dqFont, true)), question.getInteger(awdStr, 0, true), question.getInteger(minResStr, stMinRes, true), type,
						randomizeToArray(answers, new Answer[answers.size()]), question.getString(igrdChrsStr, "", true), question.getBoolean(igreCaseStr, true,
								true));
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
}
