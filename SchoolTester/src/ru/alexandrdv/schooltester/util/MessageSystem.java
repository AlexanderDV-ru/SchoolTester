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
					+ "The main part is intended for tuning the testing part and viewing statistics by tests. Сверху есть панель настроек со вкладками \""
					+ getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \"" + getMsg("help", language) + "\". Во вкладке \"" + getMsg(
							"help", language) + "\" есть \"" + getMsg("privacyPolicy", language) + "\" и \"" + getMsg("usersManual", language)
					+ "\". Во вкладке \"" + getMsg("settings", language) + "\" есть вкладка \"" + getMsg("language", language)
					+ "\". Для переключения языка выберите нужный в данной вкладке. Во вкладке \"" + getMsg("window", language)
					+ "\" можно переключить режим работы программы: выберите \"" + getMsg("testMode", language) + "\" или \"" + getMsg("statsMode", language)
					+ "\". В любом режиме работы программы под панелью настроек есть поле выбора \"" + getMsg("testFileName", language)
					+ "\", поле выбора с полем ввода \"" + getMsg("class", language) + "\", поле ввода \"" + getMsg("surname", language) + "\", поле ввода \""
					+ getMsg("name", language) + "\" и поле ввода \"" + getMsg("secondName", language)
					+ "\". В режиме тестирования появляется также поле ввода \"" + getMsg("timeToTest", language) + "\", кнопка \"" + getMsg("start", language)
					+ "\", переключатель \"" + getMsg("none", language) + "\", переключатель \"" + getMsg("indicateAnswerQuality", language)
					+ "\", зависящие от него флажок \"" + getMsg("indicateAnswersQuality", language) + "\" и флажок \"" + getMsg("showRightAnswer", language)
					+ "\", переключатель \"" + getMsg("goToAllQuestions", language) + "\", флажок \"" + getMsg("skipBtn", language) + "\", флажок \"" + getMsg(
							"pause", language) + "\", зависящий от него флажок \"" + getMsg("pauseOnUnfocus", language) + "\", флажок \"" + getMsg("anticopy",
									language) + "\", флажок \"" + getMsg("antiscreenshot", language) + "\", кнопка \"" + getMsg("savePropsToDefault", language)
					+ "\".");
		}

		messages.put("ru_ru", new HashMap<String, String>());
		{
			String language = "ru_ru";
			messages.get(language).put("schoolTester", "School Tester");
			messages.get(language).put("badKey", "Ваш ключ недействителен!");
			messages.get(language).put("updateMsg", "Ваша программа устарела, пожалуйста, обновите вашу программу до версии ");
			messages.get(language).put("youinblacklist", "Вы находитесь в черном списке!");

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
			messages.get(language).put("rightAnswersAmount", "Количество хороших ответов");
			messages.get(language).put("perfectAnswersAmount", "Количество отличных ответов");
			messages.get(language).put("questionsAmount", "Количество вопросов");
			messages.get(language).put("fullTime", "Полное время");
			messages.get(language).put("getStats", "Получить статистику");
			messages.get(language).put("inPercents", "В процентах");
			messages.get(language).put("inFractions", "В дробях");

			messages.get(language).put("none", "Стандартный");
			messages.get(language).put("indicateAnswerQuality", "Показывать верность последнего ответа");
			messages.get(language).put("indicateAnswersQuality", "Показывать верность всех ответов");
			messages.get(language).put("showRightAnswer", "Показывать верный ответ на последний вопрос");
			messages.get(language).put("goToAllQuestions", "Переключаться на любой вопрос");
			messages.get(language).put("skipBtn", "Кнопка 'Пропустить'");
			messages.get(language).put("pause", "Кнопка паузы");
			messages.get(language).put("pauseOnUnfocus", "Пауза при расфокусировке");
			messages.get(language).put("anticopy", "Анти-Копирование");
			messages.get(language).put("antiscreenshot", "Анти-Скриншот");
			messages.get(language).put("savePropsToDefault", "Установить по умолчанию");

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
			messages.get(language).put("ignoreCase", "игнорироватьРегистр");
			messages.get(language).put("ignoredCharacters", "игноруемыеСимволы");
			messages.get(language).put("minimalResult", "минимальныйРезультат");
			messages.get(language).put("questionsToTestAmount", "количествоВопросовДляТеста");
			messages.get(language).put("answerFontSize", "размерШрифтаОтвета");

			messages.get(language).put("theme", "тема");
			messages.get(language).put("windowbackground", "цветФонаОкна");
			messages.get(language).put("background", "цветФона");
			messages.get(language).put("foreground", "цветТекста");
			messages.get(language).put("frame", "цветРамки");
			messages.get(language).put("question", "вопрос");
			messages.get(language).put("answers", "ответы");
			messages.get(language).put("pickOne", "выборОдного");
			messages.get(language).put("selectSome", "выборНескольких");
			messages.get(language).put("enterText", "вводТекста");
			messages.get(language).put("questionSelect", "выборВопроса");
			messages.get(language).put("specialButtons", "специальныеКнопки");
			messages.get(language).put("normal", "нормальный");
			messages.get(language).put("skipped", "пропущенный");

			messages.get(language).put("privacyPolicyText", getMsg("privacyPolicy", language) + "\n"
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
			messages.get(language).put("usersManualText", getMsg("usersManual", language) + "\n"
					+ "Программа предназначена для тестирования учащихся. Программа состоит из двух частей - тестирующей и основной. Основная часть. "
					+ "Основная часть предназначена для настройки тестирующей части и просмотра статистики по тестам. Сверху есть панель настроек со вкладками \""
					+ getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \"" + getMsg("help", language) + "\". Во вкладке \"" + getMsg(
							"help", language) + "\" есть \"" + getMsg("privacyPolicy", language) + "\" и \"" + getMsg("usersManual", language)
					+ "\". Во вкладке \"" + getMsg("settings", language) + "\" есть вкладка \"" + getMsg("language", language)
					+ "\". Для переключения языка выберите нужный в данной вкладке. Во вкладке \"" + getMsg("window", language)
					+ "\" можно переключить режим работы программы: выберите \"" + getMsg("testMode", language) + "\" или \"" + getMsg("statsMode", language)
					+ "\". В любом режиме работы программы под панелью настроек есть поле выбора \"" + getMsg("testFileName", language)
					+ "\", поле выбора с полем ввода \"" + getMsg("class", language) + "\", поле ввода \"" + getMsg("surname", language) + "\", поле ввода \""
					+ getMsg("name", language) + "\" и поле ввода \"" + getMsg("secondName", language)
					+ "\". В режиме тестирования появляется также поле ввода \"" + getMsg("timeToTest", language) + "\", кнопка \"" + getMsg("start", language)
					+ "\", переключатель \"" + getMsg("none", language) + "\", переключатель \"" + getMsg("indicateAnswerQuality", language)
					+ "\", зависящие от него флажок \"" + getMsg("indicateAnswersQuality", language) + "\" и флажок \"" + getMsg("showRightAnswer", language)
					+ "\", переключатель \"" + getMsg("goToAllQuestions", language) + "\", флажок \"" + getMsg("skipBtn", language) + "\", флажок \"" + getMsg(
							"pause", language) + "\", зависящий от него флажок \"" + getMsg("pauseOnUnfocus", language) + "\", флажок \"" + getMsg("anticopy",
									language) + "\", флажок \"" + getMsg("antiscreenshot", language) + "\", кнопка \"" + getMsg("savePropsToDefault", language)
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
