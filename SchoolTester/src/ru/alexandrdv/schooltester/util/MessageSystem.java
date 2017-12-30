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
			messages.get(language).put("badKey", "Ваш ключ недействителен!");
			messages.get(language).put("updateMsg", "Ваша программа устарела, пожалуйста, обновите вашу программу до версии ");

			messages.get(language).put("window", "Окно");
			messages.get(language).put("settings", "Настройки");
			messages.get(language).put("help", "Справка");

			messages.get(language).put("statsMode", "Режим статистики");
			messages.get(language).put("testMode", "Режим теста");
			messages.get(language).put("language", "Язык");
			messages.get(language).put("privacyPolicy", "Политика конфиденциальности");
			messages.get(language).put("usersManual", "Инструкция по эксплуатации");

			messages.get(language).put("testFileName", "Имя .test файла");
			messages.get(language).put("file", "Файл");
			messages.get(language).put("class", "Класс");
			messages.get(language).put("surname", "Фамилия");
			messages.get(language).put("name", "Имя");
			messages.get(language).put("secondName", "Отчество");
			messages.get(language).put("start", "Начать тестирование");
			messages.get(language).put("result", "Результат");
			messages.get(language).put("maxResult", "Максимальный результат");
			messages.get(language).put("time", "Время");
			messages.get(language).put("timeToTest", "Время для теста");
			messages.get(language).put("rightAnswersAmount", "Колличество хороших ответов");
			messages.get(language).put("perfectAnswersAmount", "Колличество отличных ответов");
			messages.get(language).put("questionsAmount", "Колличество вопросов");
			messages.get(language).put("fullTime", "Полное время");
			messages.get(language).put("getStats", "Получить статистику");
			messages.get(language).put("inPercents", "В процентах");
			messages.get(language).put("inFractions", "В дробях");
			messages.get(language).put("indicateAnswerQuality", "Показывать качество предыдущего ответа");
			messages.get(language).put("indicateAnswersQuality", "Показывать качество всех ответов");
			messages.get(language).put("none", "Ничего");
			messages.get(language).put("skipBtn", "Кнопка 'Пропустить'");
			messages.get(language).put("goToAllQuestions", "Переключать вопросы");
			messages.get(language).put("pause", "Кнопка 'Пауза'");
			messages.get(language).put("pauseOnUnfocus", "Пауза при расфокусировке");
			messages.get(language).put("savePropsToDefault", "Сделать значения стандартными");

			messages.get(language).put("next", "Далее");
			messages.get(language).put("skip", "Пропустить");
			messages.get(language).put("finish", "Закончить");

			messages.get(language).put("smallest", "Меньшее");
			messages.get(language).put("average", "Среднее");
			messages.get(language).put("biggest", "Большее");
			messages.get(language).put("max", "Максимум");
			messages.get(language).put("all", "Всего");
			messages.get(language).put("score", "Баллы");
			messages.get(language).put("rightAnswers", "Хороших\nответов");
			messages.get(language).put("perfectAnswers", "Отличных\nответов");
			messages.get(language).put("time", "Время");

			messages.get(language).put("Config.boolean.true0", "правда");
			messages.get(language).put("Config.boolean.true1", "п");
			messages.get(language).put("Config.boolean.true2", "да");
			messages.get(language).put("Config.boolean.true3", "д");
			messages.get(language).put("Config.boolean.false0", "ложь");
			messages.get(language).put("Config.boolean.false1", "л");
			messages.get(language).put("Config.boolean.false2", "нет");
			messages.get(language).put("Config.boolean.false3", "н");

			messages.get(language).put("questions", "вопросы");
			messages.get(language).put("question", "вопрос");
			messages.get(language).put("answers", "ответы");
			messages.get(language).put("answer", "ответ");
			messages.get(language).put("award", "баллы");
			messages.get(language).put("questionType", "типВопроса");
			messages.get(language).put("text", "текст");
			messages.get(language).put("fontSize", "размерШрифта");
			messages.get(language).put("ignoreCase", "инорироватьРегистр");
			messages.get(language).put("ignoredCharacters", "игноруемыеСимволы");
			messages.get(language).put("minimalResult", "минимальныйРезультат");
			messages.get(language).put("questionsToTestAmount", "колличествоВопросовДляТеста");
			messages.get(language).put("answerFontSize", "размерШрифтаОтвета");
			
			messages.get(language).put("theme", "тема");
			messages.get(language).put("window", "окно");
			messages.get(language).put("background", "цветФона");
			messages.get(language).put("foreground", "цветТекста");
			messages.get(language).put("frame", "цветРамки");
			messages.get(language).put("red", "красный");
			messages.get(language).put("green", "зеленый");
			messages.get(language).put("blue", "синий");
			messages.get(language).put("question", "вопрос");
			messages.get(language).put("answers", "ответы");
			messages.get(language).put("pickOne", "выборОдного");
			messages.get(language).put("selectSome", "выборНескольких");
			messages.get(language).put("enterText", "вводТекста");
			messages.get(language).put("questionSelect", "выборВопроса");
			messages.get(language).put("specialButtons", "специальныеКнопки");
			messages.get(language).put("normal", "нормальный");
			messages.get(language).put("skipped", "пропущенный");
		}
		messages.get("ru_ru").put("privacyPolicyText", getMsg("privacyPolicy", "ru_ru")+"\n"
				+ "0.1 При использовании программы вы соглашаетесь с данными условиями.\n"
				+ "0.2 Если вы читаете настоящую Политику конфиденциальности не на русском языке, вы соглашаетесь с тем, что, в случае любых разночтений, "
				+ "преимущественную силу будет иметь русская версия.\n"
				+ "1.1 Программа передает на сервер данные о вашем IP адресе, сетевом имени компьютера, Mac адресе, версии java, местонахождении java, "
				+ "версии OC, названии OC, архитектуре OC, имени пользователя, версии программы и местонахождении программы\n"
				+ "2.1 ЗАПРЕЩАЕТСЯ изучать технологию, эмулировать, создавать новые версии, изменять, декомпилировать, дизассемблировать, изучать код программы "
				+ "другими способами. Распространение и применение программных продуктов, модифицирующих (изменяющих) исходный программный код программы "
				+ "\"SchoolTester\" (за исключением официальных обновлений) влечет за собой ответственность.\n"
				+ "3.1 Настоящий продукт предоставляется на условиях \"как есть\", со всеми возможными неисправностями, при этом настоящее соглашение не "
				+ "подразумевает обязательств или условий применимости для определенной цели, точности или полноты ответов и ли результатов работы, гарантии "
				+ "высокой квалификации, отсутствия вирусов, отсутствия небрежности при изготовлении продукта. ");
		messages.get("ru_ru").put("usersManualText", getMsg("usersManual", "ru_ru")+"\n"
				+ "Программа предназначена для тестирования учащихся. Программа состоит из двух частей тестирующей и основной. Основная часть. Основная часть предназначена для настройки тестирующей части и просмотра статистики по тестам. Сверху есть панель настроек со вкладками \""
				+ getMsg("window", "ru_ru") + "\", \"" + getMsg("settings", "ru_ru") + "\", \"" + getMsg("help", "ru_ru") + "\". Во вкладке \"" + getMsg("help",
						"ru_ru") + "\" есть \"" + getMsg("privacyPolicy", "ru_ru") + "\" и \"" + getMsg("usersManual", "ru_ru") + "\". Во вкладке \"" + getMsg(
								"settings", "ru_ru") + "\" есть вкладка \"" + getMsg("language", "ru_ru")
				+ "\". Для переключения языка выберите нужный в данной вкладке. Во вкладке \"" + getMsg("window", "ru_ru")
				+ "\" можно переключить режим работы программы: выберите \"" + getMsg("testMode", "ru_ru") + "\" или \"" + getMsg("statsMode", "ru_ru")
				+ "\". В любом режиме работы программы под панелью настроек есть поле выбора \"" + getMsg("testFileName", "ru_ru")
				+ "\", поле выбора с полем ввода \"" + getMsg("class", "ru_ru") + "\", поле ввода \"" + getMsg("surname", "ru_ru") + "\", поле ввода \""
				+ getMsg("name", "ru_ru") + "\" и поле ввода \"" + getMsg("secondName", "ru_ru") + "\". В режиме тестирования появляется также поле ввода \""
				+ getMsg("timeToTest", "ru_ru") + "\", кнопка \"" + getMsg("start", "ru_ru") + "\", переключатель \"" + getMsg("none", "ru_ru")
				+ "\", переключатель \"" + getMsg("indicateAnswerQuality", "ru_ru") + "\", зависящий от него флажок \"" + getMsg("indicateAnswersQuality",
						"ru_ru") + "\", переключатель \"" + getMsg("goToAllQuestions", "ru_ru") + "\", флажок \"" + getMsg("skipBtn", "ru_ru") + "\", флажок \""
				+ getMsg("pause", "ru_ru") + "\", зависящий от него флажок \"" + getMsg("pauseOnUnfocus", "ru_ru") + "\", кнопка \"" + getMsg(
						"savePropsToDefault", "ru_ru") + "\".");
		messages.get("en_uk").put("privacyPolicyText", getMsg("privacyPolicy", "en_uk")+"\n"
				+ "In the event you read this Privacy Policy in any language other than Russian, you agree that in the event of any discrepancies, the Russian version shall prevail.");
		messages.get("en_uk").put("usersManualText", getMsg("usersManual", "en_uk")+"\n"
				+ "Программа предназначена для тестирования учащихся. Программа состоит из двух частей тестирующей и основной. Основная часть. Основная часть предназначена для настройки тестирующей части и просмотра статистики по тестам. Сверху есть панель настроек со вкладками \""
				+ getMsg("window", "en_uk") + "\", \"" + getMsg("settings", "en_uk") + "\", \"" + getMsg("help", "en_uk") + "\". Во вкладке \"" + getMsg("help",
						"en_uk") + "\" есть \"" + getMsg("privacyPolicy", "en_uk") + "\" и \"" + getMsg("usersManual", "en_uk") + "\". Во вкладке \"" + getMsg(
								"settings", "en_uk") + "\" есть вкладка \"" + getMsg("language", "en_uk")
				+ "\". Для переключения языка выберите нужный в данной вкладке. Во вкладке \"" + getMsg("window", "en_uk")
				+ "\" можно переключить режим работы программы: выберите \"" + getMsg("testMode", "en_uk") + "\" или \"" + getMsg("statsMode", "en_uk")
				+ "\". В любом режиме работы программы под панелью настроек есть поле выбора \"" + getMsg("testFileName", "en_uk")
				+ "\", поле выбора с полем ввода \"" + getMsg("class", "en_uk") + "\", поле ввода \"" + getMsg("surname", "en_uk") + "\", поле ввода \""
				+ getMsg("name", "en_uk") + "\" и поле ввода \"" + getMsg("secondName", "en_uk") + "\". В режиме тестирования появляется также поле ввода \""
				+ getMsg("timeToTest", "en_uk") + "\", кнопка \"" + getMsg("start", "en_uk") + "\", переключатель \"" + getMsg("none", "en_uk")
				+ "\", переключатель \"" + getMsg("indicateAnswerQuality", "en_uk") + "\", зависящий от него флажок \"" + getMsg("indicateAnswersQuality",
						"en_uk") + "\", переключатель \"" + getMsg("goToAllQuestions", "en_uk") + "\", флажок \"" + getMsg("skipBtn", "en_uk") + "\", флажок \""
				+ getMsg("pause", "en_uk") + "\", зависящий от него флажок \"" + getMsg("pauseOnUnfocus", "en_uk") + "\", кнопка \"" + getMsg(
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
