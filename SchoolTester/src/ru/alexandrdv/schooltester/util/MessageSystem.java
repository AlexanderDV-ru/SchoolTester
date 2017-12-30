package ru.alexandrdv.schooltester.util;

import java.util.HashMap;

/**
 * 
 * MessageSystem
 * 
 * @author AlexandrDV
 * @version 4.3.5a
 *
 */
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
			messages.get(language).put("indicateAnswerQuality", "Indicate answer quality");
			messages.get(language).put("indicateAnswersQuality", "Indicate answers quality");
			messages.get(language).put("skipBtn", "Button 'Skip'");
			messages.get(language).put("none", "None");
			messages.get(language).put("goToAllQuestions", "Go to all questions");
			messages.get(language).put("pause", "Button 'Pause'");
			messages.get(language).put("pauseOnUnfocus", "Pause on unfocus");
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
			messages.get(language).put("window", "window");
			messages.get(language).put("background", "background");
			messages.get(language).put("foreground", "foreground");
			messages.get(language).put("frame", "frame");
			messages.get(language).put("red", "red");
			messages.get(language).put("green", "green");
			messages.get(language).put("blue", "blue");
			messages.get(language).put("question", "question");
			messages.get(language).put("answers", "answers");
			messages.get(language).put("pickOne", "pickOne");
			messages.get(language).put("selectSome", "selectSome");
			messages.get(language).put("enterText", "enterText");
			messages.get(language).put("questionSelect", "questionSelect");
			messages.get(language).put("specialButtons", "specialButtons");
			messages.get(language).put("normal", "normal");
			messages.get(language).put("skipped", "skipped");
		}

		messages.put("ru_ru", new HashMap<String, String>());
		{
			String language = "ru_ru";
			messages.get(language).put("schoolTester", "School Tester");
			messages.get(language).put("badKey", "��� ���� ��������������!");
			messages.get(language).put("updateMsg", "���� ��������� ��������, ����������, �������� ���� ��������� �� ������ ");

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
			messages.get(language).put("rightAnswersAmount", "����������� ������� �������");
			messages.get(language).put("perfectAnswersAmount", "����������� �������� �������");
			messages.get(language).put("questionsAmount", "����������� ��������");
			messages.get(language).put("fullTime", "������ �����");
			messages.get(language).put("getStats", "�������� ����������");
			messages.get(language).put("inPercents", "� ���������");
			messages.get(language).put("inFractions", "� ������");
			messages.get(language).put("indicateAnswerQuality", "���������� �������� ����������� ������");
			messages.get(language).put("indicateAnswersQuality", "���������� �������� ���� �������");
			messages.get(language).put("none", "������");
			messages.get(language).put("skipBtn", "������ '����������'");
			messages.get(language).put("goToAllQuestions", "����������� �������");
			messages.get(language).put("pause", "������ '�����'");
			messages.get(language).put("pauseOnUnfocus", "����� ��� ��������������");
			messages.get(language).put("savePropsToDefault", "������� �������� ������������");

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
			messages.get(language).put("ignoreCase", "������������������");
			messages.get(language).put("ignoredCharacters", "�����������������");
			messages.get(language).put("minimalResult", "��������������������");
			messages.get(language).put("questionsToTestAmount", "���������������������������");
			messages.get(language).put("answerFontSize", "������������������");
			
			messages.get(language).put("theme", "����");
			messages.get(language).put("window", "����");
			messages.get(language).put("background", "��������");
			messages.get(language).put("foreground", "����������");
			messages.get(language).put("frame", "���������");
			messages.get(language).put("red", "�������");
			messages.get(language).put("green", "�������");
			messages.get(language).put("blue", "�����");
			messages.get(language).put("question", "������");
			messages.get(language).put("answers", "������");
			messages.get(language).put("pickOne", "�����������");
			messages.get(language).put("selectSome", "���������������");
			messages.get(language).put("enterText", "����������");
			messages.get(language).put("questionSelect", "������������");
			messages.get(language).put("specialButtons", "�����������������");
			messages.get(language).put("normal", "����������");
			messages.get(language).put("skipped", "�����������");
		}
		messages.get("ru_ru").put("privacyPolicyText", getMsg("privacyPolicy", "ru_ru")+"\n"
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
		messages.get("ru_ru").put("usersManualText", getMsg("usersManual", "ru_ru")+"\n"
				+ "��������� ������������� ��� ������������ ��������. ��������� ������� �� ���� ������ ����������� � ��������. �������� �����. �������� ����� ������������� ��� ��������� ����������� ����� � ��������� ���������� �� ������. ������ ���� ������ �������� �� ��������� \""
				+ getMsg("window", "ru_ru") + "\", \"" + getMsg("settings", "ru_ru") + "\", \"" + getMsg("help", "ru_ru") + "\". �� ������� \"" + getMsg("help",
						"ru_ru") + "\" ���� \"" + getMsg("privacyPolicy", "ru_ru") + "\" � \"" + getMsg("usersManual", "ru_ru") + "\". �� ������� \"" + getMsg(
								"settings", "ru_ru") + "\" ���� ������� \"" + getMsg("language", "ru_ru")
				+ "\". ��� ������������ ����� �������� ������ � ������ �������. �� ������� \"" + getMsg("window", "ru_ru")
				+ "\" ����� ����������� ����� ������ ���������: �������� \"" + getMsg("testMode", "ru_ru") + "\" ��� \"" + getMsg("statsMode", "ru_ru")
				+ "\". � ����� ������ ������ ��������� ��� ������� �������� ���� ���� ������ \"" + getMsg("testFileName", "ru_ru")
				+ "\", ���� ������ � ����� ����� \"" + getMsg("class", "ru_ru") + "\", ���� ����� \"" + getMsg("surname", "ru_ru") + "\", ���� ����� \""
				+ getMsg("name", "ru_ru") + "\" � ���� ����� \"" + getMsg("secondName", "ru_ru") + "\". � ������ ������������ ���������� ����� ���� ����� \""
				+ getMsg("timeToTest", "ru_ru") + "\", ������ \"" + getMsg("start", "ru_ru") + "\", ������������� \"" + getMsg("none", "ru_ru")
				+ "\", ������������� \"" + getMsg("indicateAnswerQuality", "ru_ru") + "\", ��������� �� ���� ������ \"" + getMsg("indicateAnswersQuality",
						"ru_ru") + "\", ������������� \"" + getMsg("goToAllQuestions", "ru_ru") + "\", ������ \"" + getMsg("skipBtn", "ru_ru") + "\", ������ \""
				+ getMsg("pause", "ru_ru") + "\", ��������� �� ���� ������ \"" + getMsg("pauseOnUnfocus", "ru_ru") + "\", ������ \"" + getMsg(
						"savePropsToDefault", "ru_ru") + "\".");
		messages.get("en_uk").put("privacyPolicyText", getMsg("privacyPolicy", "en_uk")+"\n"
				+ "In the event you read this Privacy Policy in any language other than Russian, you agree that in the event of any discrepancies, the Russian version shall prevail.");
		messages.get("en_uk").put("usersManualText", getMsg("usersManual", "en_uk")+"\n"
				+ "��������� ������������� ��� ������������ ��������. ��������� ������� �� ���� ������ ����������� � ��������. �������� �����. �������� ����� ������������� ��� ��������� ����������� ����� � ��������� ���������� �� ������. ������ ���� ������ �������� �� ��������� \""
				+ getMsg("window", "en_uk") + "\", \"" + getMsg("settings", "en_uk") + "\", \"" + getMsg("help", "en_uk") + "\". �� ������� \"" + getMsg("help",
						"en_uk") + "\" ���� \"" + getMsg("privacyPolicy", "en_uk") + "\" � \"" + getMsg("usersManual", "en_uk") + "\". �� ������� \"" + getMsg(
								"settings", "en_uk") + "\" ���� ������� \"" + getMsg("language", "en_uk")
				+ "\". ��� ������������ ����� �������� ������ � ������ �������. �� ������� \"" + getMsg("window", "en_uk")
				+ "\" ����� ����������� ����� ������ ���������: �������� \"" + getMsg("testMode", "en_uk") + "\" ��� \"" + getMsg("statsMode", "en_uk")
				+ "\". � ����� ������ ������ ��������� ��� ������� �������� ���� ���� ������ \"" + getMsg("testFileName", "en_uk")
				+ "\", ���� ������ � ����� ����� \"" + getMsg("class", "en_uk") + "\", ���� ����� \"" + getMsg("surname", "en_uk") + "\", ���� ����� \""
				+ getMsg("name", "en_uk") + "\" � ���� ����� \"" + getMsg("secondName", "en_uk") + "\". � ������ ������������ ���������� ����� ���� ����� \""
				+ getMsg("timeToTest", "en_uk") + "\", ������ \"" + getMsg("start", "en_uk") + "\", ������������� \"" + getMsg("none", "en_uk")
				+ "\", ������������� \"" + getMsg("indicateAnswerQuality", "en_uk") + "\", ��������� �� ���� ������ \"" + getMsg("indicateAnswersQuality",
						"en_uk") + "\", ������������� \"" + getMsg("goToAllQuestions", "en_uk") + "\", ������ \"" + getMsg("skipBtn", "en_uk") + "\", ������ \""
				+ getMsg("pause", "en_uk") + "\", ��������� �� ���� ������ \"" + getMsg("pauseOnUnfocus", "en_uk") + "\", ������ \"" + getMsg(
						"savePropsToDefault", "en_uk") + "\".");
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
