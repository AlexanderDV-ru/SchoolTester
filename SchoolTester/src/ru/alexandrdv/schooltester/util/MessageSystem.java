package ru.alexandrdv.schooltester.util;

import java.util.HashMap;

/**
 * 
 * MessageSystem
 * 
 * @author AlexandrDV
 * @version 4.4.5a
 *
 */
@Deprecated
public class MessageSystem
{
	private String language;
	private static final HashMap<String, HashMap<String, String>> messages = new HashMap<String, HashMap<String, String>>();
	static
	{
		messages.put("en_uk", new HashMap<String, String>());
		{
			String language = "en_uk";
			messages.get(language).put("schoolTester", "School Tester");
			messages.get(language).put("badKey", "Your key is invalid!");
			messages.get(language).put("updateMsg", "Your program is out of date, please, update your program to version ");
			messages.get(language).put("youinblacklist", "You are in blacklist!");

			messages.get(language).put("window", "Window");
			messages.get(language).put("settings", "Settings");
			messages.get(language).put("help", "Help");

			messages.get(language).put("statsMode", "Statistics mode");
			messages.get(language).put("testMode", "Test mode");
			messages.get(language).put("language", "Language");
			messages.get(language).put("privacyPolicy", "Privacy Policy");
			messages.get(language).put("usersManual", "Users manual");

			messages.get(language).put("testFileName", "Test file name");
			messages.get(language).put("class", "Class");
			messages.get(language).put("surname", "Surname");
			messages.get(language).put("name", "Name");
			messages.get(language).put("secondName", "Second name");
			messages.get(language).put("timeToTest", "Time to test");
			messages.get(language).put("start", "Start");
			messages.get(language).put("result", "Result");
			messages.get(language).put("maxResult", "Max result");
			messages.get(language).put("time", "Time");
			messages.get(language).put("rightAnswersAmount", "Right answers amount");
			messages.get(language).put("perfectAnswersAmount", "Perfect answers amount");
			messages.get(language).put("questionsAmount", "Questions amount");
			messages.get(language).put("fullTime", "Full time");
			messages.get(language).put("file", "File");
			messages.get(language).put("getStats", "Get statistics");
			messages.get(language).put("inPercents", "In percents");
			messages.get(language).put("inFractions", "In fraction");

			messages.get(language).put("none", "Default");
			messages.get(language).put("indicateAnswerQuality", "Indicate quality of last answer");
			messages.get(language).put("indicateAnswersQuality", "Indicate quality of all answers");
			messages.get(language).put("showRightAnswer", "Show right answer");
			messages.get(language).put("goToAllQuestions", "Go to all questions");
			messages.get(language).put("skipBtn", "Button 'Skip'");
			messages.get(language).put("pause", "Pause button");
			messages.get(language).put("pauseOnUnfocus", "Pause on unfocus");
			messages.get(language).put("anticopy", "Anti-Copy");
			messages.get(language).put("antiscreenshot", "Anti-Screenshot");
			messages.get(language).put("savePropsToDefault", "Save properties how default");

			messages.get(language).put("next", "Next");
			messages.get(language).put("skip", "Skip");
			messages.get(language).put("finish", "Finish");

			messages.get(language).put("smallest", "Smallest");
			messages.get(language).put("average", "Average");
			messages.get(language).put("biggest", "Biggest");
			messages.get(language).put("max", "Max");
			messages.get(language).put("all", "All");
			messages.get(language).put("score", "Score");
			messages.get(language).put("rightAnswers", "Right\nanswers");
			messages.get(language).put("perfectAnswers", "Perfect\nanswers");
			messages.get(language).put("time", "Time");

			messages.get(language).put("Config.boolean.true0", "true");
			messages.get(language).put("Config.boolean.true1", "t");
			messages.get(language).put("Config.boolean.true2", "yes");
			messages.get(language).put("Config.boolean.true3", "y");
			messages.get(language).put("Config.boolean.false0", "false");
			messages.get(language).put("Config.boolean.false1", "f");
			messages.get(language).put("Config.boolean.false2", "no");
			messages.get(language).put("Config.boolean.false3", "n");

			messages.get(language).put("questions", "questions");
			messages.get(language).put("question", "question");
			messages.get(language).put("answers", "answers");
			messages.get(language).put("answer", "answer");
			messages.get(language).put("award", "award");
			messages.get(language).put("questionType", "questionType");
			messages.get(language).put("text", "text");
			messages.get(language).put("fontSize", "fontSize");
			messages.get(language).put("ignoreCase", "ignoreCase");
			messages.get(language).put("ignoredCharacters", "ignoredCharacters");
			messages.get(language).put("minimalResult", "minimalResult");
			messages.get(language).put("questionsToTestAmount", "questionsToTestAmount");
			messages.get(language).put("answerFontSize", "answerFontSize");

			messages.get(language).put("theme", "theme");
			messages.get(language).put("windowbackground", "windowBackground");
			messages.get(language).put("background", "background");
			messages.get(language).put("foreground", "foreground");
			messages.get(language).put("frame", "frame");
			messages.get(language).put("question", "question");
			messages.get(language).put("answers", "answers");
			messages.get(language).put("pickOne", "pickOne");
			messages.get(language).put("selectSome", "selectSome");
			messages.get(language).put("enterText", "enterText");
			messages.get(language).put("questionSelect", "questionSelect");
			messages.get(language).put("specialButtons", "specialButtons");
			messages.get(language).put("normal", "normal");
			messages.get(language).put("skipped", "skipped");

			messages.get(language).put("privacyPolicyText", getMsg("privacyPolicy", language) + "\n"
					+ "0.1 When using the program, you agree to these terms and conditions.\n"
					+ "0.2 If you read this Privacy Policy in a language other than Russian, you agree that, in the event of any discrepancies, the Russian version "
					+ "will prevail.\n"
					+ "1.1 The program transmits data on your IP address, computer network name, Mac address, java version, java location, OC version, OC name, "
					+ "OC architecture, user name, version of the program and the location of the program to the server.\n"
					+ "2.1 DON'T study technology, emulate, create new versions, modify, decompile, disassemble, study the program code in other ways. "
					+ "Distribution and application of software products that modify (modify) the source code of the program \"SchoolTester\" (with the "
					+ "exception of official updates) entails responsibility.\n"
					+ "3.1 This product is provided on an \"as is\" basis, with all possible malfunctions, and this agreement does not imply obligations or "
					+ "conditions of applicability for a particular purpose, accuracy or completeness of the answers and whether the results of the work, the "
					+ "guarantee of high qualification, the absence of viruses, product manufacturing.");
			messages.get(language).put("usersManualText", getMsg("usersManual", language) + "\n"
					+ "The program is designed to test students. The program consists of two parts - main and testing. Main part."
					+ "The main part is intended for tuning the testing part and viewing statistics by tests. ������ ���� ������ �������� �� ��������� \""
					+ getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \"" + getMsg("help", language) + "\". �� ������� \"" + getMsg(
							"help", language) + "\" ���� \"" + getMsg("privacyPolicy", language) + "\" � \"" + getMsg("usersManual", language)
					+ "\". �� ������� \"" + getMsg("settings", language) + "\" ���� ������� \"" + getMsg("language", language)
					+ "\". ��� ������������ ����� �������� ������ � ������ �������. �� ������� \"" + getMsg("window", language)
					+ "\" ����� ����������� ����� ������ ���������: �������� \"" + getMsg("testMode", language) + "\" ��� \"" + getMsg("statsMode", language)
					+ "\". � ����� ������ ������ ��������� ��� ������� �������� ���� ���� ������ \"" + getMsg("testFileName", language)
					+ "\", ���� ������ � ����� ����� \"" + getMsg("class", language) + "\", ���� ����� \"" + getMsg("surname", language) + "\", ���� ����� \""
					+ getMsg("name", language) + "\" � ���� ����� \"" + getMsg("secondName", language)
					+ "\". � ������ ������������ ���������� ����� ���� ����� \"" + getMsg("timeToTest", language) + "\", ������ \"" + getMsg("start", language)
					+ "\", ������������� \"" + getMsg("none", language) + "\", ������������� \"" + getMsg("indicateAnswerQuality", language)
					+ "\", ��������� �� ���� ������ \"" + getMsg("indicateAnswersQuality", language) + "\" � ������ \"" + getMsg("showRightAnswer", language)
					+ "\", ������������� \"" + getMsg("goToAllQuestions", language) + "\", ������ \"" + getMsg("skipBtn", language) + "\", ������ \"" + getMsg(
							"pause", language) + "\", ��������� �� ���� ������ \"" + getMsg("pauseOnUnfocus", language) + "\", ������ \"" + getMsg("anticopy",
									language) + "\", ������ \"" + getMsg("antiscreenshot", language) + "\", ������ \"" + getMsg("savePropsToDefault", language)
					+ "\".");
		}

		messages.put("ru_ru", new HashMap<String, String>());
		{
			String language = "ru_ru";
			messages.get(language).put("schoolTester", "School Tester");
			messages.get(language).put("badKey", "��� ���� ��������������!");
			messages.get(language).put("updateMsg", "���� ��������� ��������, ����������, �������� ���� ��������� �� ������ ");
			messages.get(language).put("youinblacklist", "�� ���������� � ������ ������!");

			messages.get(language).put("window", "����");
			messages.get(language).put("settings", "���������");
			messages.get(language).put("help", "�������");

			messages.get(language).put("statsMode", "����� ����������");
			messages.get(language).put("testMode", "����� �����");
			messages.get(language).put("language", "����");
			messages.get(language).put("privacyPolicy", "�������� ������������������");
			messages.get(language).put("usersManual", "���������� �� ������������");

			messages.get(language).put("testFileName", "��� .test �����");
			messages.get(language).put("file", "����");
			messages.get(language).put("class", "�����");
			messages.get(language).put("surname", "�������");
			messages.get(language).put("name", "���");
			messages.get(language).put("secondName", "��������");
			messages.get(language).put("start", "������ ������������");
			messages.get(language).put("result", "���������");
			messages.get(language).put("maxResult", "������������ ���������");
			messages.get(language).put("time", "�����");
			messages.get(language).put("timeToTest", "����� ��� �����");
			messages.get(language).put("rightAnswersAmount", "���������� ������� �������");
			messages.get(language).put("perfectAnswersAmount", "���������� �������� �������");
			messages.get(language).put("questionsAmount", "���������� ��������");
			messages.get(language).put("fullTime", "������ �����");
			messages.get(language).put("getStats", "�������� ����������");
			messages.get(language).put("inPercents", "� ���������");
			messages.get(language).put("inFractions", "� ������");

			messages.get(language).put("none", "�����������");
			messages.get(language).put("indicateAnswerQuality", "���������� �������� ���������� ������");
			messages.get(language).put("indicateAnswersQuality", "���������� �������� ���� �������");
			messages.get(language).put("showRightAnswer", "���������� ������ ����� �� ��������� ������");
			messages.get(language).put("goToAllQuestions", "������������� �� ����� ������");
			messages.get(language).put("skipBtn", "������ '����������'");
			messages.get(language).put("pause", "������ �����");
			messages.get(language).put("pauseOnUnfocus", "����� ��� ��������������");
			messages.get(language).put("anticopy", "����-�����������");
			messages.get(language).put("antiscreenshot", "����-��������");
			messages.get(language).put("savePropsToDefault", "���������� �� ���������");

			messages.get(language).put("next", "�����");
			messages.get(language).put("skip", "����������");
			messages.get(language).put("finish", "���������");

			messages.get(language).put("smallest", "�������");
			messages.get(language).put("average", "�������");
			messages.get(language).put("biggest", "�������");
			messages.get(language).put("max", "��������");
			messages.get(language).put("all", "�����");
			messages.get(language).put("score", "�����");
			messages.get(language).put("rightAnswers", "�������\n�������");
			messages.get(language).put("perfectAnswers", "��������\n�������");
			messages.get(language).put("time", "�����");

			messages.get(language).put("Config.boolean.true0", "������");
			messages.get(language).put("Config.boolean.true1", "�");
			messages.get(language).put("Config.boolean.true2", "��");
			messages.get(language).put("Config.boolean.true3", "�");
			messages.get(language).put("Config.boolean.false0", "����");
			messages.get(language).put("Config.boolean.false1", "�");
			messages.get(language).put("Config.boolean.false2", "���");
			messages.get(language).put("Config.boolean.false3", "�");

			messages.get(language).put("questions", "�������");
			messages.get(language).put("question", "������");
			messages.get(language).put("answers", "������");
			messages.get(language).put("answer", "�����");
			messages.get(language).put("award", "�����");
			messages.get(language).put("questionType", "����������");
			messages.get(language).put("text", "�����");
			messages.get(language).put("fontSize", "������������");
			messages.get(language).put("ignoreCase", "�������������������");
			messages.get(language).put("ignoredCharacters", "�����������������");
			messages.get(language).put("minimalResult", "��������������������");
			messages.get(language).put("questionsToTestAmount", "��������������������������");
			messages.get(language).put("answerFontSize", "������������������");

			messages.get(language).put("theme", "����");
			messages.get(language).put("windowbackground", "������������");
			messages.get(language).put("background", "��������");
			messages.get(language).put("foreground", "����������");
			messages.get(language).put("frame", "���������");
			messages.get(language).put("question", "������");
			messages.get(language).put("answers", "������");
			messages.get(language).put("pickOne", "�����������");
			messages.get(language).put("selectSome", "���������������");
			messages.get(language).put("enterText", "����������");
			messages.get(language).put("questionSelect", "������������");
			messages.get(language).put("specialButtons", "�����������������");
			messages.get(language).put("normal", "����������");
			messages.get(language).put("skipped", "�����������");

			messages.get(language).put("privacyPolicyText", getMsg("privacyPolicy", language) + "\n"
					+ "0.1 ��� ������������� ��������� �� ������������ � ������� ���������.\n"
					+ "0.2 ���� �� ������� ��������� �������� ������������������ �� �� ������� �����, �� ������������ � ���, ���, � ������ ����� �����������, "
					+ "���������������� ���� ����� ����� ������� ������.\n"
					+ "1.1 ��������� �������� �� ������ ������ � ����� IP ������, ������� ����� ����������, Mac ������, ������ java, ��������������� java, "
					+ "������ OC, �������� OC, ����������� OC, ����� ������������, ������ ��������� � ��������������� ���������\n"
					+ "2.1 ����������� ������� ����������, �����������, ��������� ����� ������, ��������, ���������������, �����������������, ������� ��� ��������� "
					+ "������� ���������. ��������������� � ���������� ����������� ���������, �������������� (����������) �������� ����������� ��� ��������� "
					+ "\"SchoolTester\" (�� ����������� ����������� ����������) ������ �� ����� ���������������.\n"
					+ "3.1 ��������� ������� ��������������� �� �������� \"��� ����\", �� ����� ���������� ���������������, ��� ���� ��������� ���������� �� "
					+ "������������� ������������ ��� ������� ������������ ��� ������������ ����, �������� ��� ������� ������� � �� ����������� ������, �������� "
					+ "������� ������������, ���������� �������, ���������� ����������� ��� ������������ ��������. ");
			messages.get(language).put("usersManualText", getMsg("usersManual", language) + "\n"
					+ "��������� ������������� ��� ������������ ��������. ��������� ������� �� ���� ������ - ����������� � ��������. �������� �����. "
					+ "�������� ����� ������������� ��� ��������� ����������� ����� � ��������� ���������� �� ������. ������ ���� ������ �������� �� ��������� \""
					+ getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \"" + getMsg("help", language) + "\". �� ������� \"" + getMsg(
							"help", language) + "\" ���� \"" + getMsg("privacyPolicy", language) + "\" � \"" + getMsg("usersManual", language)
					+ "\". �� ������� \"" + getMsg("settings", language) + "\" ���� ������� \"" + getMsg("language", language)
					+ "\". ��� ������������ ����� �������� ������ � ������ �������. �� ������� \"" + getMsg("window", language)
					+ "\" ����� ����������� ����� ������ ���������: �������� \"" + getMsg("testMode", language) + "\" ��� \"" + getMsg("statsMode", language)
					+ "\". � ����� ������ ������ ��������� ��� ������� �������� ���� ���� ������ \"" + getMsg("testFileName", language)
					+ "\", ���� ������ � ����� ����� \"" + getMsg("class", language) + "\", ���� ����� \"" + getMsg("surname", language) + "\", ���� ����� \""
					+ getMsg("name", language) + "\" � ���� ����� \"" + getMsg("secondName", language)
					+ "\". � ������ ������������ ���������� ����� ���� ����� \"" + getMsg("timeToTest", language) + "\", ������ \"" + getMsg("start", language)
					+ "\", ������������� \"" + getMsg("none", language) + "\", ������������� \"" + getMsg("indicateAnswerQuality", language)
					+ "\", ��������� �� ���� ������ \"" + getMsg("indicateAnswersQuality", language) + "\" � ������ \"" + getMsg("showRightAnswer", language)
					+ "\", ������������� \"" + getMsg("goToAllQuestions", language) + "\", ������ \"" + getMsg("skipBtn", language) + "\", ������ \"" + getMsg(
							"pause", language) + "\", ��������� �� ���� ������ \"" + getMsg("pauseOnUnfocus", language) + "\", ������ \"" + getMsg("anticopy",
									language) + "\", ������ \"" + getMsg("antiscreenshot", language) + "\", ������ \"" + getMsg("savePropsToDefault", language)
					+ "\".");
		}
	}

	public MessageSystem(String language)
	{
		this.language = language;
	}

	public String getMsg(String key)
	{
		return getMsg(key, language);
	}

	public static String getMsg(String key, String language)
	{
		if (!messages.get(language).containsKey(key))
			return "null";
		return messages.get(language).get(key);
	}

	/**
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

}
