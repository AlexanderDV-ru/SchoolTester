package ru.alexanderdv.schooltester.utilities;

import java.util.HashMap;

/**
 * 
 * MessageSystem
 * 
 * @author AlexandrDV
 * @version 5.0.0a
 *
 */
public class MessageSystem
{
	private String language;
	private static final HashMap<String, HashMap<String, String>> messages = new HashMap<String, HashMap<String, String>>();
	static
	{
		getMessages().put("en_uk", new HashMap<String, String>());
		{
			String language = "en_uk";
			getMessages().get(language).put("siteURL", "http://schooltester.ucoz.org/");

			getMessages().get(language).put("keyIsBad", "Your key is invalid!");
			getMessages().get(language).put("updateMsg", "Your program is out of date, please, update your program to version ");
			getMessages().get(language).put("youAreInBlacklist", "You are in blacklist!");
			getMessages().get(language).put("keyIsRight", "Succesfully connected to the server!");
			getMessages().get(language).put("notConnectedToServer", "Could not connect to the server!");
			getMessages().get(language).put("verifyRequestSended",
					"Your computer not verified! To verify your computer communicate with us, you can do this in our site: " + getMessages().get(language).get(
							"siteURL"));
			getMessages().get(language).put("testWithFiltersNotExist", "Tests' results with these filters aren't exist!");
			getMessages().get(language).put("testNotSelected", "Test don't selected!");
			getMessages().get(language).put("undefined", "Undefined");
			getMessages().get(language).put("notInAccount", "Not in account");
			getMessages().get(language).put("teacherAccount", "Teacher account");
			getMessages().get(language).put("studentAccount", "Student account");
			getMessages().get(language).put("administratorAccount", "Administrator account");

			getMessages().get(language).put("signUpPerformed", "You have successfully signed up.");
			getMessages().get(language).put("signInPerformed", "You have successfully signed in.");
			getMessages().get(language).put("accountAlreadyExists", "Account with this login already exists!");
			getMessages().get(language).put("passwordIsInvalid", "The password is not valid!");
			getMessages().get(language).put("accountNotExists", "The account does not exist!");
			getMessages().get(language).put("signOutPerformed", "You have successfully signed out.");
			getMessages().get(language).put("accountInfoChanged", "Info of your profile changed, sign in again, please.");
			getMessages().get(language).put("accountInfoChangedByYou", "You have successfully change info of your profile.");
			getMessages().get(language).put("accountDeleted", "You have successfully delete account.");

			getMessages().get(language).put("passwordsNotMatch", "Password don't match!");

			getMessages().get(language).put("common", "Common");
			getMessages().get(language).put("special", "Special");
			getMessages().get(language).put("utils", "Utils");

			getMessages().get(language).put("fileTree", "File tree");

			getMessages().get(language).put("window", "Window");
			getMessages().get(language).put("settings", "Settings");
			getMessages().get(language).put("help", "Help");
			getMessages().get(language).put("accounts", "Accounts");

			getMessages().get(language).put("language", "Language");
			getMessages().get(language).put("privacyPolicy", "Privacy Policy");
			getMessages().get(language).put("usersManual", "Users manual");
			getMessages().get(language).put("siteLink", "Site: " + getMessages().get(language).get("siteURL"));
			getMessages().get(language).put("openAccount", "Open account");

			getMessages().get(language).put("statisticsTab", "Statistics");
			getMessages().get(language).put("testingTab", "Testing");

			getMessages().get(language).put("testName", "Test name");
			getMessages().get(language).put("classNumber", "Class number");
			getMessages().get(language).put("classLetter", "Class letter");
			getMessages().get(language).put("studentSurname", "Surname");
			getMessages().get(language).put("studentName", "Name");
			getMessages().get(language).put("studentSecondName", "Second name");
			getMessages().get(language).put("timeToTest", "Time to test");
			getMessages().get(language).put("start", "Start");
			getMessages().get(language).put("result", "Result");
			getMessages().get(language).put("maxResult", "Max result");
			getMessages().get(language).put("time", "Time");
			getMessages().get(language).put("rightAnswersAmount", "Right answers amount");
			getMessages().get(language).put("perfectAnswersAmount", "Perfect answers amount");
			getMessages().get(language).put("questionsAmount", "Questions amount");
			getMessages().get(language).put("fullTime", "Full time");
			getMessages().get(language).put("file", "File");
			getMessages().get(language).put("getStats", "Get statistics");
			getMessages().get(language).put("inPercents", "In percents");
			getMessages().get(language).put("inFractions", "In fraction");

			getMessages().get(language).put("testingSettings", "Testing settings");
			{
				getMessages().get(language).put("none", "Default");
				getMessages().get(language).put("indicateAnswerQuality", "Indicate last answer quality");
				getMessages().get(language).put("indicateAnswersQuality", "Indicate all answers quality");
				getMessages().get(language).put("showRightAnswer", "Show right answer to last question");
				getMessages().get(language).put("goToAllQuestions", "Go to all answers");
				getMessages().get(language).put("skipBtn", "'Skip' button");
				getMessages().get(language).put("pause", "Pause button");
				getMessages().get(language).put("pauseOnUnfocus", "Pause on unfocus");
				getMessages().get(language).put("anticopy", "Anti-Copy");
				getMessages().get(language).put("antiscreenshot", "Anti-Screenshot");
				getMessages().get(language).put("saveTestingSettings", "Save testing settings");
			}
			getMessages().get(language).put("lookSettings", "Look settings");
			{
				getMessages().get(language).put("fixedQSelectBtnHeight", "Fixed question select button height");
				getMessages().get(language).put("fillAllHeightOfAnswersPanel", "Fill all height of answers panel");
				getMessages().get(language).put("maximazeAnswerButtonHeight", "Maximaze answer button height");
				getMessages().get(language).put("spaceBetweenAnswerButtons", "Space between answer buttons");
				getMessages().get(language).put("saveLookSettings", "Save look settings");
			}

			getMessages().get(language).put("next", "Next");
			getMessages().get(language).put("skip", "Skip");
			getMessages().get(language).put("finish", "Finish");

			getMessages().get(language).put("smallest", "Smallest");
			getMessages().get(language).put("average", "Average");
			getMessages().get(language).put("biggest", "Biggest");
			getMessages().get(language).put("max", "Max");
			getMessages().get(language).put("all", "All");
			getMessages().get(language).put("score", "Score");
			getMessages().get(language).put("rightAnswers", "Right\nanswers");
			getMessages().get(language).put("perfectAnswers", "Perfect\nanswers");
			getMessages().get(language).put("time", "Time");

			getMessages().get(language).put("Config.boolean.true0", "true");
			getMessages().get(language).put("Config.boolean.true1", "t");
			getMessages().get(language).put("Config.boolean.true2", "yes");
			getMessages().get(language).put("Config.boolean.true3", "y");
			getMessages().get(language).put("Config.boolean.false0", "false");
			getMessages().get(language).put("Config.boolean.false1", "f");
			getMessages().get(language).put("Config.boolean.false2", "no");
			getMessages().get(language).put("Config.boolean.false3", "n");

			getMessages().get(language).put("programVersion", "programVersion");
			getMessages().get(language).put("colorType", "colorType");
			getMessages().get(language).put("testVersion", "testVersion");
			getMessages().get(language).put("testCreationDate", "testCreationDate");
			getMessages().get(language).put("testLanguage", "testLanguage");
			getMessages().get(language).put("testSubject", "testSubject");
			getMessages().get(language).put("authors", "authors");
			getMessages().get(language).put("description", "description");
			getMessages().get(language).put("maxTestTime", "maxTestTime");

			getMessages().get(language).put("questionToPickOne", "Question to pick one");
			getMessages().get(language).put("questionToSelectSome", "Question to select some");
			getMessages().get(language).put("questionToEnterText", "Question to enter text");
			getMessages().get(language).put("questionToDistribution", "Question to distribution");
			getMessages().get(language).put("questionToMatching", "Question to matching");
			getMessages().get(language).put("questionToArrangement", "Question to arrangement");

			getMessages().get(language).put("questions", "questions");
			getMessages().get(language).put("pickOne", "pickOne");
			getMessages().get(language).put("selectSome", "selectSome");
			getMessages().get(language).put("enterText", "enterText");
			getMessages().get(language).put("distribution", "distribution");
			getMessages().get(language).put("matching", "matching");
			getMessages().get(language).put("arrangement", "arrangement");
			getMessages().get(language).put("question", "question");
			getMessages().get(language).put("answers", "answers");
			getMessages().get(language).put("answer", "answer");
			getMessages().get(language).put("award", "award");
			getMessages().get(language).put("text", "text");
			getMessages().get(language).put("fontSize", "fontSize");
			getMessages().get(language).put("ignoreCase", "ignoreCase");
			getMessages().get(language).put("ignoredCharacters", "ignoredCharacters");
			getMessages().get(language).put("minimalResult", "minimalResult");
			getMessages().get(language).put("questionsToTestAmount", "questionsToTestAmount");
			getMessages().get(language).put("answerFontSize", "answerFontSize");
			getMessages().get(language).put("handleOnlyMaximal", "handleOnlyMaximal");
			getMessages().get(language).put("awardsForAnswers", "awardsForAnswers");
			getMessages().get(language).put("awardForAnswer", "awardForAnswer");
			getMessages().get(language).put("answersIndexes", "answersIndexes");
			getMessages().get(language).put("answerIndex", "answerIndex");
			getMessages().get(language).put("number", "number");
			getMessages().get(language).put("index", "index");
			getMessages().get(language).put("indexesForNames", "indexesForNames");
			getMessages().get(language).put("naming", "naming");

			getMessages().get(language).put("login", "Login");
			getMessages().get(language).put("password", "Password");
			getMessages().get(language).put("passwordRepeat", "Password repeat");

			getMessages().get(language).put("deleteAccount", "Delete account");
			getMessages().get(language).put("signOut", "Sign Out");
			getMessages().get(language).put("signIn", "Sign In");
			getMessages().get(language).put("signUp", "Sign Up");
			getMessages().get(language).put("profile", "Profile");
			{
				getMessages().get(language).put("showAllTabs", "Show all tabs");
				getMessages().get(language).put("security", "Security");
				{
					getMessages().get(language).put("newPassword", "New password");
					getMessages().get(language).put("newPasswordRepeat", "New password repeat");
					getMessages().get(language).put("changePassword", "Change password");
				}
				getMessages().get(language).put("main", "Main");
				{
					getMessages().get(language).put("surname", "Surname");
					getMessages().get(language).put("name", "Name");
					getMessages().get(language).put("secondName", "Second name");
					getMessages().get(language).put("country", "Country");
					getMessages().get(language).put("region", "Region");
					getMessages().get(language).put("city", "City");
					getMessages().get(language).put("school", "School");
					getMessages().get(language).put("subjets", "Subjects");
				}
				getMessages().get(language).put("family", "Family");
				{
					getMessages().get(language).put("maritalStatus", "Marital status");
					getMessages().get(language).put("parents", "Parents");
					getMessages().get(language).put("grandParents", "Grand parents");
					getMessages().get(language).put("children", "Children");
					getMessages().get(language).put("grandChildren", "Grand children");
					getMessages().get(language).put("siblings", "Siblings");
					getMessages().get(language).put("exSpouses", "Ex-Spouses");
					getMessages().get(language).put("spouse", "Spouse");
					getMessages().get(language).put("otherRelatives", "Other relatives");
				}
				getMessages().get(language).put("contacts", "Contacts");
				{
					getMessages().get(language).put("phoneNumbers", "Phone numbers");
					getMessages().get(language).put("emails", "Emails");
					getMessages().get(language).put("personalSites", "Personal sites");
					getMessages().get(language).put("otherSites", "Other sites");
					getMessages().get(language).put("otherContacts", "Other contacts");
				}
				getMessages().get(language).put("life", "Life");
				{
					getMessages().get(language).put("age", "Age");
					getMessages().get(language).put("gender", "Gender");
					getMessages().get(language).put("mainLanguages", "Main languages");
					getMessages().get(language).put("otherLanguages", "Other languages");
					getMessages().get(language).put("education", "Education");
					getMessages().get(language).put("career", "Career");
				}
				getMessages().get(language).put("ideas", "Ideas");
				{
					getMessages().get(language).put("aboutAlhogol", "About alhogol");
					getMessages().get(language).put("aboutNarcotics", "About narcotics");
					getMessages().get(language).put("aboutSmoking", "About smoking");
					getMessages().get(language).put("ideas", "Ideas");
					getMessages().get(language).put("interests", "Interests");
					getMessages().get(language).put("favouriteBlogs", "Favourite blogs");
					getMessages().get(language).put("favouriteBooks", "Favourite books");
					getMessages().get(language).put("favouriteComputerGames", "Favourite computer games");
					getMessages().get(language).put("favouriteFilms", "Favourite films");
					getMessages().get(language).put("favouriteGames", "Favourite games");
					getMessages().get(language).put("favouriteMusic", "Favourite music");
					getMessages().get(language).put("favouritePeople", "Favourite people");
					getMessages().get(language).put("favouriteShows", "Favourite shows");
					getMessages().get(language).put("favouriteQuotes", "Favourite quotes");
					getMessages().get(language).put("mainInLife", "Main in life");
					getMessages().get(language).put("mainInPeople", "Main in people");
					getMessages().get(language).put("worldOutlook", "World outlook");
					getMessages().get(language).put("politicalViews", "Political views");
					getMessages().get(language).put("otherViews", "Other views");
					getMessages().get(language).put("inspiration", "Inspiration");
				}
				getMessages().get(language).put("aboutYou", "About you");
				{
					getMessages().get(language).put("biografy", "Biografy");
					getMessages().get(language).put("homeCountry", "Home country");
					getMessages().get(language).put("homeRegion", "Home region");
					getMessages().get(language).put("homeCity", "Home city/village");
				}
				getMessages().get(language).put("save", "Save");
			}
			getMessages().get(language).put("openCrossWordGeneratorPart", "Open crossword generator");
			getMessages().get(language).put("openTeachersTestsControlPart", "Open tests contol");
			getMessages().get(language).put("openTestDevPart", "Open test develop");
		}
	}
	static
	{
		String language = "en_uk";
		getMessages().get(language).put("AU", "Australia");
		getMessages().get(language).put("AT", "Austria");
		getMessages().get(language).put("AZ", "Azerbaijan");
		getMessages().get(language).put("AL", "Albania");
		getMessages().get(language).put("HW", "Algeria");
		getMessages().get(language).put("VI", "United States Virgin Islands");
		getMessages().get(language).put("AS", "American Samoa");
		getMessages().get(language).put("AI", "Anguilla");
		getMessages().get(language).put("AO", "Angola");
		getMessages().get(language).put("AD", "Andorra");
		getMessages().get(language).put("AQ", "Antarctica");
		getMessages().get(language).put("AG", "Antigua and Barbuda");
		getMessages().get(language).put("AR", "Argentina");
		getMessages().get(language).put("AM", "Armenia");
		getMessages().get(language).put("AW", "Aruba");
		getMessages().get(language).put("AF", "Afghanistan");
		getMessages().get(language).put("BS", "Bahamas");
		getMessages().get(language).put("BD", "Bangladesh");
		getMessages().get(language).put("BB", "Barbados");
		getMessages().get(language).put("BH", "Bahrain");
		getMessages().get(language).put("BZ", "Belize");
		getMessages().get(language).put("BY", "Belarus");
		getMessages().get(language).put("BE", "Belgium");
		getMessages().get(language).put("BJ", "Benin");
		getMessages().get(language).put("BM", "Bermuda");
		getMessages().get(language).put("BG", "Bulgaria");
		getMessages().get(language).put("BO", "Bolivia");
		getMessages().get(language).put("BQ", "Caribbean Netherlands");
		getMessages().get(language).put("BA", "Bosnia and Herzegovina");
		getMessages().get(language).put("BW", "Botswana");
		getMessages().get(language).put("BR", "Brazil");
		getMessages().get(language).put("IO", "British Indian Ocean Territory");
		getMessages().get(language).put("VG", "British Virgin Islands");
		getMessages().get(language).put("BN", "Brunei Darussalam");
		getMessages().get(language).put("BV", "Bouvet Island");
		getMessages().get(language).put("BF", "Burkina Faso");
		getMessages().get(language).put("BI", "Burundi");
		getMessages().get(language).put("BT", "Bhutan");
		getMessages().get(language).put("VU", "Vanuatu");
		getMessages().get(language).put("VA", "Vatican");
		getMessages().get(language).put("GB", "Great Britain");
		getMessages().get(language).put("HU", "Hungary");
		getMessages().get(language).put("VE", "Venezuela");
		getMessages().get(language).put("TL", "East Timor");
		getMessages().get(language).put("VN", "Vietnam");
		getMessages().get(language).put("GA", "Gabon");
		getMessages().get(language).put("HT", "Haiti");
		getMessages().get(language).put("GY", "Guyana");
		getMessages().get(language).put("GM", "Gambia");
		getMessages().get(language).put("GH", "Ghana");
		getMessages().get(language).put("GP", "Guadeloupe");
		getMessages().get(language).put("GT", "Guatemala");
		getMessages().get(language).put("GN", "Guinea");
		getMessages().get(language).put("GW", "Guinea-Bissau");
		getMessages().get(language).put("DE", "Germany");
		getMessages().get(language).put("GG", "Guernsey");
		getMessages().get(language).put("GI", "Gibraltar");
		getMessages().get(language).put("HN", "Honduras");
		getMessages().get(language).put("HK", "Hong Kong");
		getMessages().get(language).put("GD", "Grenada");
		getMessages().get(language).put("GL", "Greenland");
		getMessages().get(language).put("GR", "Greece");
		getMessages().get(language).put("GE", "Georgia");
		getMessages().get(language).put("GU", "Guam");
		getMessages().get(language).put("DK", "Denmark");
		getMessages().get(language).put("CD", "Democratic Republic of the Congo");
		getMessages().get(language).put("JE", "Jersey");
		getMessages().get(language).put("DJ", "Djibouti");
		getMessages().get(language).put("DM", "Dominica");
		getMessages().get(language).put("DO", "Dominican Republic");
		getMessages().get(language).put("EG", "Egypt");
		getMessages().get(language).put("ZM", "Zambia");
		getMessages().get(language).put("EH", "Western Sahara");
		getMessages().get(language).put("ZW", "Zimbabwe");
		getMessages().get(language).put("IL", "Israel");
		getMessages().get(language).put("IN", "India");
		getMessages().get(language).put("ID", "Indonesia");
		getMessages().get(language).put("JO", "Jordan");
		getMessages().get(language).put("IQ", "Iraq");
		getMessages().get(language).put("IR", "Iran");
		getMessages().get(language).put("IE", "Ireland");
		getMessages().get(language).put("IS", "Iceland");
		getMessages().get(language).put("ES", "Spain");
		getMessages().get(language).put("IT", "Italy");
		getMessages().get(language).put("YE", "Yemen");
		getMessages().get(language).put("CV", "Cape Verde");
		getMessages().get(language).put("KZ", "Kazakhstan");
		getMessages().get(language).put("KY", "Cayman Islands");
		getMessages().get(language).put("KH", "Cambodia");
		getMessages().get(language).put("CM", "Cameroon");
		getMessages().get(language).put("CA", "Canada");
		getMessages().get(language).put("QA", "Quatar");
		getMessages().get(language).put("KE", "Kenya");
		getMessages().get(language).put("CY", "Cyprus");
		getMessages().get(language).put("KG", "Kyrgyzstan");
		getMessages().get(language).put("KI", "Kiribati");
		getMessages().get(language).put("CN", "China");
		getMessages().get(language).put("KP", "North Korea");
		getMessages().get(language).put("CC", "Cocos (Keeling) Islands");
		getMessages().get(language).put("CO", "Colombia");
		getMessages().get(language).put("KM", "Comoros");
		getMessages().get(language).put("KR", "Korea");
		getMessages().get(language).put("CR", "Costa Rica");
		getMessages().get(language).put("CI", "Cote d'Ivoire (Ivory Coast)");
		getMessages().get(language).put("CU", "Cuba");
		getMessages().get(language).put("KW", "Kuwait");
		getMessages().get(language).put("CW", "Curacao");
		getMessages().get(language).put("LA", "Laos");
		getMessages().get(language).put("LV", "Latvia");
		getMessages().get(language).put("LS", "Lesotho");
		getMessages().get(language).put("LR", "Liberia");
		getMessages().get(language).put("LB", "Lebanon");
		getMessages().get(language).put("LY", "Libya");
		getMessages().get(language).put("LT", "Lithuania");
		getMessages().get(language).put("LI", "Liechtenstein");
		getMessages().get(language).put("LU", "Luxembourg");
		getMessages().get(language).put("MU", "Mauritius");
		getMessages().get(language).put("MR", "Mauritania");
		getMessages().get(language).put("MG", "Madagascar");
		getMessages().get(language).put("YT", "Mayotte");
		getMessages().get(language).put("MO", "Macau");
		getMessages().get(language).put("MK", "Macedonia");
		getMessages().get(language).put("MW", "Malawi");
		getMessages().get(language).put("MY", "Malaysia");
		getMessages().get(language).put("ML", "Mali");
		getMessages().get(language).put("MV", "Maldives");
		getMessages().get(language).put("MT", "Malta");
		getMessages().get(language).put("MA", "Morocco");
		getMessages().get(language).put("MQ", "Martinique");
		getMessages().get(language).put("MH", "Marshall Islands");
		getMessages().get(language).put("MX", "Mexico");
		getMessages().get(language).put("MZ", "Mozambique");
		getMessages().get(language).put("MD", "Moldova");
		getMessages().get(language).put("MC", "Monaco");
		getMessages().get(language).put("MN", "Mongolia");
		getMessages().get(language).put("MS", "Montserrat");
		getMessages().get(language).put("MM", "Myanmar (Burma)");
		getMessages().get(language).put("NA", "Namibia");
		getMessages().get(language).put("NR", "Nauru");
		getMessages().get(language).put("NP", "Nepal");
		getMessages().get(language).put("NE", "Niger");
		getMessages().get(language).put("NG", "Nigeria");
		getMessages().get(language).put("AN", "Netherlands Antilles");
		getMessages().get(language).put("NL", "Netherlands");
		getMessages().get(language).put("NI", "Nicaragua");
		getMessages().get(language).put("NU", "Niue");
		getMessages().get(language).put("NZ", "New Zealand");
		getMessages().get(language).put("NC", "New Caledonia");
		getMessages().get(language).put("NO", "Norway");
		getMessages().get(language).put("AE", "United Arab Emirates");
		getMessages().get(language).put("OM", "Oman");
		getMessages().get(language).put("NF", "Norfolk Island");
		getMessages().get(language).put("CX", "Christmas Island");
		getMessages().get(language).put("SH", "Saint Helena");
		getMessages().get(language).put("HM", "Heard Island and McDonald Islands");
		getMessages().get(language).put("CK", "Cook Islands");
		getMessages().get(language).put("PN", "Pitcairn Islands");
		getMessages().get(language).put("TC", "The Turks and Caicos Islands");
		getMessages().get(language).put("WF", "Wallis and Futuna");
		getMessages().get(language).put("PK", "Pakistan");
		getMessages().get(language).put("PW", "Palau");
		getMessages().get(language).put("PS", "Palestinian Territories");
		getMessages().get(language).put("PA", "Panama");
		getMessages().get(language).put("PG", "Papua New Guinea");
		getMessages().get(language).put("PY", "Paraguay");
		getMessages().get(language).put("PE", "Peru");
		getMessages().get(language).put("PL", "Poland");
		getMessages().get(language).put("PT", "Portugal");
		getMessages().get(language).put("PR", "Puerto Rico");
		getMessages().get(language).put("CG", "Republic of the Congo");
		getMessages().get(language).put("RE", "Reunion");
		getMessages().get(language).put("RU", "Russia");
		getMessages().get(language).put("RW", "Rwanda");
		getMessages().get(language).put("RO", "Romania");
		getMessages().get(language).put("SV", "El Salvador");
		getMessages().get(language).put("WS", "Samoa");
		getMessages().get(language).put("SM", "San Marino");
		getMessages().get(language).put("ST", "Sao Tome and Principe");
		getMessages().get(language).put("SA", "Saudi Arabia");
		getMessages().get(language).put("SZ", "Swaziland");
		getMessages().get(language).put("MP", "Northern Mariana Islands");
		getMessages().get(language).put("SC", "Seychelles");
		getMessages().get(language).put("PM", "Saint-Pierre and Miquelon");
		getMessages().get(language).put("SN", "Senegal");
		getMessages().get(language).put("VC", "Saint Vincent and the Grenadines");
		getMessages().get(language).put("KN", "Saint Kitts and Nevis");
		getMessages().get(language).put("LC", "Saint Lucia");
		getMessages().get(language).put("RS", "Serbia");
		getMessages().get(language).put("SG", "Singapore");
		getMessages().get(language).put("SY", "Syria");
		getMessages().get(language).put("SK", "Slovakia");
		getMessages().get(language).put("SI", "Slovenia");
		getMessages().get(language).put("SB", "Solomon Islands");
		getMessages().get(language).put("SO", "Somalia");
		getMessages().get(language).put("SD", "Sudan");
		getMessages().get(language).put("SR", "Suriname");
		getMessages().get(language).put("US", "USA");
		getMessages().get(language).put("SL", "Sierra Leone");
		getMessages().get(language).put("TJ", "Tajikistan");
		getMessages().get(language).put("TH", "Thailand");
		getMessages().get(language).put("TW", "Taiwan (Taipei)");
		getMessages().get(language).put("TZ", "Tanzania");
		getMessages().get(language).put("TG", "Togo");
		getMessages().get(language).put("TK", "Tokelau");
		getMessages().get(language).put("TO", "Tonga");
		getMessages().get(language).put("TT", "Trinidad and Tobago");
		getMessages().get(language).put("TV", "Tuvalu");
		getMessages().get(language).put("TN", "Tunisia");
		getMessages().get(language).put("TM", "Turkmenistan");
		getMessages().get(language).put("TR", "Turkey");
		getMessages().get(language).put("UG", "Uganda");
		getMessages().get(language).put("UZ", "Uzbekistan");
		getMessages().get(language).put("UA", "Ukraine");
		getMessages().get(language).put("UY", "Uruguay");
		getMessages().get(language).put("FO", "Faroe Islands");
		getMessages().get(language).put("FM", "Federated States of Micronesia");
		getMessages().get(language).put("FJ", "Fiji");
		getMessages().get(language).put("PH", "Philippines");
		getMessages().get(language).put("FI", "Finland");
		getMessages().get(language).put("FK", "Falkland Islands (Malvinas)");
		getMessages().get(language).put("FR", "France");
		getMessages().get(language).put("GF", "French Guiana");
		getMessages().get(language).put("PF", "French Polynesia");
		getMessages().get(language).put("TF", "French Southern Territories");
		getMessages().get(language).put("HR", "Croatia");
		getMessages().get(language).put("CF", "Central African Republic");
		getMessages().get(language).put("TD", "Chad");
		getMessages().get(language).put("ME", "Montenegro");
		getMessages().get(language).put("CZ", "Czechia");
		getMessages().get(language).put("CL", "Chile");
		getMessages().get(language).put("FM", "Chuuk");
		getMessages().get(language).put("CH", "Switzerland");
		getMessages().get(language).put("SE", "Sweden");
		getMessages().get(language).put("SJ", "Svalbard and Jan Mayen");
		getMessages().get(language).put("LK", "Sri Lanka");
		getMessages().get(language).put("EC", "Ecuador");
		getMessages().get(language).put("GQ", "Equatorial Guinea");
		getMessages().get(language).put("ER", "Eritrea");
		getMessages().get(language).put("EE", "Estonia");
		getMessages().get(language).put("ET", "Ethiopia");
		getMessages().get(language).put("ZA", "Republic of South Africa");
		getMessages().get(language).put("GS", "South Georgia and the South Sandwich Islands");
		getMessages().get(language).put("JM", "Jamaica");
		getMessages().get(language).put("FM", "Yap");
	}
	static
	{
		getMessages().put("ru_ru", new HashMap<String, String>());
		{
			String language = "ru_ru";

			getMessages().get(language).put("siteURL", "http://schooltester.ucoz.org/");

			getMessages().get(language).put("keyIsBad", "Ваш ключ недействителен!");
			getMessages().get(language).put("updateMsg", "Ваша программа устарела, пожалуйста, обновите вашу программу до версии ");
			getMessages().get(language).put("youAreInBlacklist", "Вы находитесь в черном списке!");
			getMessages().get(language).put("keyIsRight", "Удалось успешно подключиться серверу!");
			getMessages().get(language).put("notConnectedToServer", "Не удалось подключиться к серверу!");
			getMessages().get(language).put("verifyRequestSended",
					"Программа на вашем компьютере не верифицирована! Чтобы верифицировать вашу программу свяжитесь с нами, вы можете сделать это на нашем сайте: "
							+ getMessages().get(language).get("siteURL"));
			getMessages().get(language).put("testWithFiltersNotExist", "Результатов тестов подходящих под данные фильтры не существует!");
			getMessages().get(language).put("testNotSelected", "Тест не выбран!");
			getMessages().get(language).put("undefined", "Не определено");
			getMessages().get(language).put("notInAccount", "Не в аккаунте");
			getMessages().get(language).put("teacherAccount", "Аккаунт учителя");
			getMessages().get(language).put("studentAccount", "Аккаунт ученика");
			getMessages().get(language).put("administratorAccount", "Аккаунт администратора");

			getMessages().get(language).put("signUpPerformed", "Вы успешно зарегистрировались.");
			getMessages().get(language).put("signInPerformed", "Вы успешно вошли.");
			getMessages().get(language).put("accountAlreadyExists", "Аккаунт с таким логином уже существует!");
			getMessages().get(language).put("passwordIsInvalid", "Пароль неверный!");
			getMessages().get(language).put("accountNotExists", "Аккаунта не существует!");
			getMessages().get(language).put("signOutPerformed", "Вы успешно вышли.");
			getMessages().get(language).put("accountInfoChanged", "Информация в вашем профиле изменена, пожалуйста, зайдите заново.");
			getMessages().get(language).put("accountInfoChangedByYou", "Вы успешно изменили информацию в вашем профиле.");
			getMessages().get(language).put("accountDeleted", "Вы успешно удалили свой аккаунт.");

			getMessages().get(language).put("passwordsNotMatch", "Пароли не совпадают!");

			getMessages().get(language).put("common", "Общее");
			getMessages().get(language).put("special", "Специальное");
			getMessages().get(language).put("utils", "Утилиты");

			getMessages().get(language).put("fileTree", "Древо файлов");

			getMessages().get(language).put("window", "Окно");
			getMessages().get(language).put("settings", "Настройки");
			getMessages().get(language).put("help", "Справка");
			getMessages().get(language).put("accounts", "Аккаунты");

			getMessages().get(language).put("language", "Язык");
			getMessages().get(language).put("privacyPolicy", "Политика конфиденциальности");
			getMessages().get(language).put("usersManual", "Инструкция по эксплуатации");
			getMessages().get(language).put("siteLink", "Сайт: " + getMessages().get(language).get("siteURL"));
			getMessages().get(language).put("authorEmail", "Копировать почту: AlexanderDV.ru@gmail.com");
			getMessages().get(language).put("openAccount", "Открыть аккаунт");

			getMessages().get(language).put("statisticsTab", "Статистика");
			getMessages().get(language).put("testingTab", "Тестирование");

			getMessages().get(language).put("testName", "Название теста");
			getMessages().get(language).put("file", "Файл");
			getMessages().get(language).put("classNumber", "Номер класса");
			getMessages().get(language).put("classLetter", "Буква класса");
			getMessages().get(language).put("studentSurname", "Фамилия");
			getMessages().get(language).put("studentName", "Имя");
			getMessages().get(language).put("studentSecondName", "Отчество");
			getMessages().get(language).put("start", "Начать тестирование");
			getMessages().get(language).put("result", "Результат");
			getMessages().get(language).put("maxResult", "Максимальный результат");
			getMessages().get(language).put("time", "Время");
			getMessages().get(language).put("timeToTest", "Время для теста");
			getMessages().get(language).put("rightAnswersAmount", "Количество хороших ответов");
			getMessages().get(language).put("perfectAnswersAmount", "Количество отличных ответов");
			getMessages().get(language).put("questionsAmount", "Количество вопросов");
			getMessages().get(language).put("fullTime", "Полное время");
			getMessages().get(language).put("getStats", "Получить статистику");
			getMessages().get(language).put("inPercents", "В процентах");
			getMessages().get(language).put("inFractions", "В дробях");

			getMessages().get(language).put("testingSettings", "Настройки тестирования");
			{
				getMessages().get(language).put("none", "Стандартный");
				getMessages().get(language).put("indicateAnswerQuality", "Показывать верность последнего ответа");
				getMessages().get(language).put("indicateAnswersQuality", "Показывать верность всех ответов");
				getMessages().get(language).put("showRightAnswer", "Показывать верный ответ на последний вопрос");
				getMessages().get(language).put("goToAllQuestions", "Переключаться на любой вопрос");
				getMessages().get(language).put("skipBtn", "Кнопка 'Пропустить'");
				getMessages().get(language).put("pause", "Кнопка паузы");
				getMessages().get(language).put("pauseOnUnfocus", "Пауза при расфокусировке");
				getMessages().get(language).put("anticopy", "Анти-Копирование");
				getMessages().get(language).put("antiscreenshot", "Анти-Скриншот");
				getMessages().get(language).put("saveTestingSettings", "Сохранить настройки тестирования");
			}
			getMessages().get(language).put("lookSettings", "Настройки вида");
			{
				getMessages().get(language).put("fixedQSelectBtnHeight", "Фиксированная высота кнопки выбора вопроса");
				getMessages().get(language).put("fillAllHeightOfAnswersPanel", "Заполнять всю высоту панели ответов");
				getMessages().get(language).put("maximazeAnswerButtonHeight", "Максимальная высота кнопок ответа");
				getMessages().get(language).put("spaceBetweenAnswerButtons", "Расстояние между кнопками ответов");
				getMessages().get(language).put("saveLookSettings", "Сохранить настройки вида");
			}

			getMessages().get(language).put("next", "Далее");
			getMessages().get(language).put("skip", "Пропустить");
			getMessages().get(language).put("finish", "Закончить");

			getMessages().get(language).put("smallest", "Меньшее");
			getMessages().get(language).put("average", "Среднее");
			getMessages().get(language).put("biggest", "Большее");
			getMessages().get(language).put("max", "Максимум");
			getMessages().get(language).put("all", "Всего");
			getMessages().get(language).put("score", "Баллы");
			getMessages().get(language).put("rightAnswers", "Хороших\nответов");
			getMessages().get(language).put("perfectAnswers", "Отличных\nответов");
			getMessages().get(language).put("time", "Время");

			getMessages().get(language).put("Config.boolean.true0", "правда");
			getMessages().get(language).put("Config.boolean.true1", "п");
			getMessages().get(language).put("Config.boolean.true2", "да");
			getMessages().get(language).put("Config.boolean.true3", "д");
			getMessages().get(language).put("Config.boolean.false0", "ложь");
			getMessages().get(language).put("Config.boolean.false1", "л");
			getMessages().get(language).put("Config.boolean.false2", "нет");
			getMessages().get(language).put("Config.boolean.false3", "н");

			getMessages().get(language).put("programVersion", "версияПрограммы");
			getMessages().get(language).put("colorType", "типЦвета");
			getMessages().get(language).put("testVersion", "версияТеста");
			getMessages().get(language).put("testCreationDate", "датаСозданияТеста");
			getMessages().get(language).put("testLanguage", "языкТеста");
			getMessages().get(language).put("testSubject", "предметТеста");
			getMessages().get(language).put("authors", "авторы");
			getMessages().get(language).put("description", "описание");
			getMessages().get(language).put("maxTestTime", "максимальноеВремяТеста");

			getMessages().get(language).put("questionToPickOne", "Вопрос на выбор одного");
			getMessages().get(language).put("questionToSelectSome", "Вопрос на выбор нескольких");
			getMessages().get(language).put("questionToEnterText", "Вопрос на ввод текста");
			getMessages().get(language).put("questionToDistribution", "Вопрос на распределение");
			getMessages().get(language).put("questionToMatching", "Вопрос на сопоставление");
			getMessages().get(language).put("questionToArrangement", "Вопрос на упорядочивание");

			getMessages().get(language).put("questions", "вопросы");
			getMessages().get(language).put("pickOne", "выборОдного");
			getMessages().get(language).put("selectSome", "выборНескольких");
			getMessages().get(language).put("enterText", "вводТекста");
			getMessages().get(language).put("distribution", "распределение");
			getMessages().get(language).put("matching", "сопоставление");
			getMessages().get(language).put("arrangement", "упорядочивание");
			getMessages().get(language).put("question", "вопрос");
			getMessages().get(language).put("answers", "ответы");
			getMessages().get(language).put("answer", "ответ");
			getMessages().get(language).put("award", "баллы");
			getMessages().get(language).put("text", "текст");
			getMessages().get(language).put("fontSize", "размерШрифта");
			getMessages().get(language).put("ignoreCase", "игнорироватьРегистр");
			getMessages().get(language).put("ignoredCharacters", "игноруемыеСимволы");
			getMessages().get(language).put("minimalResult", "минимальныйРезультат");
			getMessages().get(language).put("questionsToTestAmount", "количествоВопросовДляТеста");
			getMessages().get(language).put("answerFontSize", "размерШрифтаОтвета");
			getMessages().get(language).put("handleOnlyMaximal", "обрабатыватьТолькоМаксимальный");
			getMessages().get(language).put("awardsForAnswers", "баллыЗаОтветы");
			getMessages().get(language).put("awardForAnswer", "баллыЗаОтвет");
			getMessages().get(language).put("answersIndexes", "индексыОтветов");
			getMessages().get(language).put("answerIndex", "индексОтвета");
			getMessages().get(language).put("number", "номер");
			getMessages().get(language).put("index", "индекс");
			getMessages().get(language).put("indexesForNames", "индексыНазваний");
			getMessages().get(language).put("naming", "название");

			getMessages().get(language).put("login", "Логин");
			getMessages().get(language).put("password", "Пароль");
			getMessages().get(language).put("passwordRepeat", "Повтор пароля");

			getMessages().get(language).put("deleteAccount", "Удалить аккаунт");
			getMessages().get(language).put("signOut", "Выйти из аккаунта");
			getMessages().get(language).put("signIn", "Войти в аккаунт");
			getMessages().get(language).put("signUp", "Создать аккаунт");
			getMessages().get(language).put("profile", "Профиль");
			{
				getMessages().get(language).put("showAllTabs", "Показывать все вкладки");
				getMessages().get(language).put("security", "Безопасность");
				{
					getMessages().get(language).put("newPassword", "Повтор пароля");
					getMessages().get(language).put("newPasswordRepeat", "Повтор нового пароля");
					getMessages().get(language).put("changePassword", "Сменить пароль");
				}
				getMessages().get(language).put("main", "Главное");
				{
					getMessages().get(language).put("surname", "Фамилия");
					getMessages().get(language).put("name", "Имя");
					getMessages().get(language).put("secondName", "Отчество");
					getMessages().get(language).put("country", "Страна");
					getMessages().get(language).put("region", "Область");
					getMessages().get(language).put("city", "Город/село");
					getMessages().get(language).put("school", "Школа");
					getMessages().get(language).put("subjets", "Предметы");
				}
				getMessages().get(language).put("family", "Семья");
				{
					getMessages().get(language).put("maritalStatus", "Семейное положение");
					getMessages().get(language).put("grandParents", "Бабушки/дедушки");
					getMessages().get(language).put("parents", "Родители");
					getMessages().get(language).put("children", "Дети");
					getMessages().get(language).put("grandChildren", "Внуки");
					getMessages().get(language).put("siblings", "Братья/сестры");
					getMessages().get(language).put("exSpouses", "Бывшие супруги");
					getMessages().get(language).put("spouse", "Супруг(а)");
					getMessages().get(language).put("otherRelatives", "Другие родственники");
				}
				getMessages().get(language).put("contacts", "Контакты");
				{
					getMessages().get(language).put("phoneNumbers", "Телефонные номера");
					getMessages().get(language).put("emails", "Адреса эл. почты");
					getMessages().get(language).put("personalSites", "Персональные сайты");
					getMessages().get(language).put("otherSites", "Другие сайты");
					getMessages().get(language).put("otherContacts", "Другие контакты");
				}
				getMessages().get(language).put("life", "Жизнь");
				{
					getMessages().get(language).put("age", "Возраст");
					getMessages().get(language).put("gender", "Пол");
					getMessages().get(language).put("mainLanguages", "Основные языки");
					getMessages().get(language).put("otherLanguages", "Другие языки");
					getMessages().get(language).put("education", "Образование");
					getMessages().get(language).put("career", "Карьера");
				}
				getMessages().get(language).put("ideas", "Идеи");
				{
					getMessages().get(language).put("aboutAlhogol", "Об алкоголе");
					getMessages().get(language).put("aboutNarcotics", "О наркотиках");
					getMessages().get(language).put("aboutSmoking", "О курении");
					getMessages().get(language).put("ideas", "Идеи");
					getMessages().get(language).put("interests", "Интересы");
					getMessages().get(language).put("favouriteBlogs", "Любимые блоги");
					getMessages().get(language).put("favouriteBooks", "Любимые книги");
					getMessages().get(language).put("favouriteComputerGames", "Любимые компьютерные игры");
					getMessages().get(language).put("favouriteFilms", "Любимые фильмы");
					getMessages().get(language).put("favouriteGames", "Любимые игры");
					getMessages().get(language).put("favouriteMusic", "Любимая музыка");
					getMessages().get(language).put("favouritePeople", "Приятные люди");
					getMessages().get(language).put("favouriteShows", "Любимые шоу");
					getMessages().get(language).put("favouriteQuotes", "Любимые цитаты");
					getMessages().get(language).put("mainInLife", "Главное в жизни");
					getMessages().get(language).put("mainInPeople", "Главное в людях");
					getMessages().get(language).put("worldOutlook", "Мировоззрение");
					getMessages().get(language).put("politicalViews", "Политические взгляды");
					getMessages().get(language).put("otherViews", "Другие взгляды");
					getMessages().get(language).put("inspiration", "Вдохновение");
				}
				getMessages().get(language).put("aboutYou", "О себе");
				{
					getMessages().get(language).put("biografy", "Биография");
					getMessages().get(language).put("homeCountry", "Родная страна");
					getMessages().get(language).put("homeRegion", "Родная область");
					getMessages().get(language).put("homeCity", "Родной город/село");
				}
				getMessages().get(language).put("save", "Сохранить");
			}
			getMessages().get(language).put("openCrossWordGeneratorPart", "Открыть генератор кроссвордов");
			getMessages().get(language).put("openTeachersTestsControlPart", "Открыть контроль тестов");
			getMessages().get(language).put("openTestDevPart", "Открыть разработку тестов");
		}
	}
	static
	{
		String language = "ru_ru";
		getMessages().get(language).put("AU", "Австралия");
		getMessages().get(language).put("AT", "Австрия");
		getMessages().get(language).put("AZ", "Азербайджан");
		getMessages().get(language).put("AL", "Албания");
		getMessages().get(language).put("HW", "Алжир");
		getMessages().get(language).put("VI", "Американские Виргинские острова");
		getMessages().get(language).put("AS", "Американское Самоа");
		getMessages().get(language).put("AI", "Ангилья");
		getMessages().get(language).put("AO", "Ангола");
		getMessages().get(language).put("AD", "Андорра");
		getMessages().get(language).put("AQ", "Антарктида");
		getMessages().get(language).put("AG", "Антигуа и Барбуда");
		getMessages().get(language).put("AR", "Аргентина");
		getMessages().get(language).put("AM", "Армения");
		getMessages().get(language).put("AW", "Аруба");
		getMessages().get(language).put("AF", "Афганистан");
		getMessages().get(language).put("BS", "Багамские острова");
		getMessages().get(language).put("BD", "Бангладеш");
		getMessages().get(language).put("BB", "Барбадос");
		getMessages().get(language).put("BH", "Бахрейн");
		getMessages().get(language).put("BZ", "Белиз");
		getMessages().get(language).put("BY", "Белоруссия");
		getMessages().get(language).put("BE", "Бельгия");
		getMessages().get(language).put("BJ", "Бенин");
		getMessages().get(language).put("BM", "Бермуды");
		getMessages().get(language).put("BG", "Болгария");
		getMessages().get(language).put("BO", "Боливия");
		getMessages().get(language).put("BQ", "Бонэйр, Синт-Эстатиус и Саба");
		getMessages().get(language).put("BA", "Босния и Герцеговина");
		getMessages().get(language).put("BW", "Ботсвана");
		getMessages().get(language).put("BR", "Бразилия");
		getMessages().get(language).put("IO", "Британская территория в Индийском океане");
		getMessages().get(language).put("VG", "Британские Виргинские острова");
		getMessages().get(language).put("BN", "Бруней");
		getMessages().get(language).put("BV", "Буве");
		getMessages().get(language).put("BF", "Буркина-Фасо");
		getMessages().get(language).put("BI", "Бурунди");
		getMessages().get(language).put("BT", "Бутан");
		getMessages().get(language).put("VU", "Вануату");
		getMessages().get(language).put("VA", "Ватикан");
		getMessages().get(language).put("GB", "Великобритания");
		getMessages().get(language).put("HU", "Венгрия");
		getMessages().get(language).put("VE", "Венесуэла");
		getMessages().get(language).put("TL", "Восточный Тимор (Тимор-Лешти)");
		getMessages().get(language).put("VN", "Вьетнам");
		getMessages().get(language).put("GA", "Габон");
		getMessages().get(language).put("HT", "Гаити");
		getMessages().get(language).put("GY", "Гайана");
		getMessages().get(language).put("GM", "Гамбия");
		getMessages().get(language).put("GH", "Гана");
		getMessages().get(language).put("GP", "Гваделупа");
		getMessages().get(language).put("GT", "Гватемала");
		getMessages().get(language).put("GN", "Гвинея");
		getMessages().get(language).put("GW", "Гвинея-Бисау");
		getMessages().get(language).put("DE", "Германия");
		getMessages().get(language).put("GG", "Гернси");
		getMessages().get(language).put("GI", "Гибралтар");
		getMessages().get(language).put("HN", "Гондурас");
		getMessages().get(language).put("HK", "Гонконг");
		getMessages().get(language).put("GD", "Гренада");
		getMessages().get(language).put("GL", "Гренландия");
		getMessages().get(language).put("GR", "Греция");
		getMessages().get(language).put("GE", "Грузия");
		getMessages().get(language).put("GU", "Гуам");
		getMessages().get(language).put("DK", "Дания");
		getMessages().get(language).put("CD", "Демократическая Республика Конго");
		getMessages().get(language).put("JE", "Джерси");
		getMessages().get(language).put("DJ", "Джибути");
		getMessages().get(language).put("DM", "Доминика");
		getMessages().get(language).put("DO", "Доминикана");
		getMessages().get(language).put("EG", "Египет");
		getMessages().get(language).put("ZM", "Замбия");
		getMessages().get(language).put("EH", "Западная Сахара");
		getMessages().get(language).put("ZW", "Зимбабве");
		getMessages().get(language).put("IL", "Израиль");
		getMessages().get(language).put("IN", "Индия, Гоа");
		getMessages().get(language).put("ID", "Индонезия, Бали");
		getMessages().get(language).put("JO", "Иордания");
		getMessages().get(language).put("IQ", "Ирак");
		getMessages().get(language).put("IR", "Иран");
		getMessages().get(language).put("IE", "Ирландия");
		getMessages().get(language).put("IS", "Исландия");
		getMessages().get(language).put("ES", "Испания, Канары");
		getMessages().get(language).put("IT", "Италия");
		getMessages().get(language).put("YE", "Йемен");
		getMessages().get(language).put("CV", "Кабо-Верде (Острова Зеленого Мыса)");
		getMessages().get(language).put("KZ", "Казахстан");
		getMessages().get(language).put("KY", "Каймановы острова");
		getMessages().get(language).put("KH", "Камбоджа");
		getMessages().get(language).put("CM", "Камерун");
		getMessages().get(language).put("CA", "Канада");
		getMessages().get(language).put("QA", "Катар");
		getMessages().get(language).put("KE", "Кения");
		getMessages().get(language).put("CY", "Кипр");
		getMessages().get(language).put("KG", "Киргизия");
		getMessages().get(language).put("KI", "Кирибати");
		getMessages().get(language).put("CN", "Китай");
		getMessages().get(language).put("KP", "КНДР");
		getMessages().get(language).put("CC", "Кокосовые острова (Килинг)");
		getMessages().get(language).put("CO", "Колумбия");
		getMessages().get(language).put("KM", "Коморские острова");
		getMessages().get(language).put("KR", "Корея");
		getMessages().get(language).put("CR", "Коста-Рика");
		getMessages().get(language).put("CI", "Кот-д’Ивуар (Берег Слоновой Кости)");
		getMessages().get(language).put("CU", "Куба");
		getMessages().get(language).put("KW", "Кувейт");
		getMessages().get(language).put("CW", "Кюрасао");
		getMessages().get(language).put("LA", "Лаос");
		getMessages().get(language).put("LV", "Латвия");
		getMessages().get(language).put("LS", "Лесото");
		getMessages().get(language).put("LR", "Либерия");
		getMessages().get(language).put("LB", "Ливан");
		getMessages().get(language).put("LY", "Ливия");
		getMessages().get(language).put("LT", "Литва");
		getMessages().get(language).put("LI", "Лихтенштейн");
		getMessages().get(language).put("LU", "Люксембург");
		getMessages().get(language).put("MU", "Маврикий");
		getMessages().get(language).put("MR", "Мавритания");
		getMessages().get(language).put("MG", "Мадагаскар");
		getMessages().get(language).put("YT", "Майотта");
		getMessages().get(language).put("MO", "Макао (Аомынь)");
		getMessages().get(language).put("MK", "Македония");
		getMessages().get(language).put("MW", "Малави");
		getMessages().get(language).put("MY", "Малайзия");
		getMessages().get(language).put("ML", "Мали");
		getMessages().get(language).put("MV", "Мальдивы");
		getMessages().get(language).put("MT", "Мальта");
		getMessages().get(language).put("MA", "Марокко");
		getMessages().get(language).put("MQ", "Мартиника");
		getMessages().get(language).put("MH", "Маршалловы острова");
		getMessages().get(language).put("MX", "Мексика");
		getMessages().get(language).put("MZ", "Мозамбик");
		getMessages().get(language).put("MD", "Молдавия");
		getMessages().get(language).put("MC", "Монако");
		getMessages().get(language).put("MN", "Монголия");
		getMessages().get(language).put("MS", "Монтсеррат");
		getMessages().get(language).put("MM", "Мьянма (Бирма)");
		getMessages().get(language).put("NA", "Намибия");
		getMessages().get(language).put("NR", "Науру");
		getMessages().get(language).put("NP", "Непал");
		getMessages().get(language).put("NE", "Нигер");
		getMessages().get(language).put("NG", "Нигерия");
		getMessages().get(language).put("AN", "Нидерландские Антильские острова");
		getMessages().get(language).put("NL", "Нидерланды (Голландия)");
		getMessages().get(language).put("NI", "Никарагуа");
		getMessages().get(language).put("NU", "Ниуэ");
		getMessages().get(language).put("NZ", "Новая Зеландия");
		getMessages().get(language).put("NC", "Новая Каледония");
		getMessages().get(language).put("NO", "Норвегия");
		getMessages().get(language).put("AE", "ОАЭ");
		getMessages().get(language).put("OM", "Оман");
		getMessages().get(language).put("NF", "Остров Норфолк");
		getMessages().get(language).put("CX", "Остров Рождества");
		getMessages().get(language).put("SH", "Остров Святой Елены");
		getMessages().get(language).put("HM", "Остров Херд и острова Макдональд");
		getMessages().get(language).put("CK", "Острова Кука");
		getMessages().get(language).put("PN", "Острова Питкэрн");
		getMessages().get(language).put("TC", "Острова Тёркс и Кайкос");
		getMessages().get(language).put("WF", "Острова Уоллис и Футуна");
		getMessages().get(language).put("PK", "Пакистан");
		getMessages().get(language).put("PW", "Палау");
		getMessages().get(language).put("PS", "Палестина");
		getMessages().get(language).put("PA", "Панама");
		getMessages().get(language).put("PG", "Папуа — Новая Гвинея");
		getMessages().get(language).put("PY", "Парагвай");
		getMessages().get(language).put("PE", "Перу");
		getMessages().get(language).put("PL", "Польша");
		getMessages().get(language).put("PT", "Португалия");
		getMessages().get(language).put("PR", "Пуэрто-Рико");
		getMessages().get(language).put("CG", "Республика Конго");
		getMessages().get(language).put("RE", "Реюньон");
		getMessages().get(language).put("RU", "Россия");
		getMessages().get(language).put("RW", "Руанда");
		getMessages().get(language).put("RO", "Румыния");
		getMessages().get(language).put("SV", "Сальвадор");
		getMessages().get(language).put("WS", "Самоа");
		getMessages().get(language).put("SM", "Сан-Марино");
		getMessages().get(language).put("ST", "Сан-Томе и Принсипи");
		getMessages().get(language).put("SA", "Саудовская Аравия");
		getMessages().get(language).put("SZ", "Свазиленд");
		getMessages().get(language).put("MP", "Северные Марианские острова");
		getMessages().get(language).put("SC", "Сейшелы");
		getMessages().get(language).put("PM", "Сен-Пьер и Микелон");
		getMessages().get(language).put("SN", "Сенегал");
		getMessages().get(language).put("VC", "Сент-Винсент и Гренадины");
		getMessages().get(language).put("KN", "Сент-Китс и Невис");
		getMessages().get(language).put("LC", "Сент-Люсия");
		getMessages().get(language).put("RS", "Сербия");
		getMessages().get(language).put("SG", "Сингапур, Бинтан");
		getMessages().get(language).put("SY", "Сирия");
		getMessages().get(language).put("SK", "Словакия");
		getMessages().get(language).put("SI", "Словения");
		getMessages().get(language).put("SB", "Соломоновы острова");
		getMessages().get(language).put("SO", "Сомали");
		getMessages().get(language).put("SD", "Судан");
		getMessages().get(language).put("SR", "Суринам");
		getMessages().get(language).put("US", "США");
		getMessages().get(language).put("SL", "Сьерра-Леоне");
		getMessages().get(language).put("TJ", "Таджикистан");
		getMessages().get(language).put("TH", "Таиланд");
		getMessages().get(language).put("TW", "Тайвань");
		getMessages().get(language).put("TZ", "Танзания");
		getMessages().get(language).put("TG", "Того");
		getMessages().get(language).put("TK", "Токелау");
		getMessages().get(language).put("TO", "Тонга");
		getMessages().get(language).put("TT", "Тринидад и Тобаго");
		getMessages().get(language).put("TV", "Тувалу");
		getMessages().get(language).put("TN", "Тунис");
		getMessages().get(language).put("TM", "Туркмения");
		getMessages().get(language).put("TR", "Турция");
		getMessages().get(language).put("UG", "Уганда");
		getMessages().get(language).put("UZ", "Узбекистан");
		getMessages().get(language).put("UA", "Украина");
		getMessages().get(language).put("UY", "Уругвай");
		getMessages().get(language).put("FO", "Фарерские острова");
		getMessages().get(language).put("FM", "Федеративные Штаты Микронезии");
		getMessages().get(language).put("FJ", "Фиджи");
		getMessages().get(language).put("PH", "Филиппины");
		getMessages().get(language).put("FI", "Финляндия");
		getMessages().get(language).put("FK", "Фолклендские (Мальвинские) острова");
		getMessages().get(language).put("FR", "Франция");
		getMessages().get(language).put("GF", "Французская Гвиана");
		getMessages().get(language).put("PF", "Французская Полинезия (Таити)");
		getMessages().get(language).put("TF", "Французские Южные и Антарктические Территории");
		getMessages().get(language).put("HR", "Хорватия");
		getMessages().get(language).put("CF", "Центральноафриканская Республика");
		getMessages().get(language).put("TD", "Чад");
		getMessages().get(language).put("ME", "Черногория");
		getMessages().get(language).put("CZ", "Чехия");
		getMessages().get(language).put("CL", "Чили, о. Пасхи");
		getMessages().get(language).put("FM", "Чуук (Трук)");
		getMessages().get(language).put("CH", "Швейцария");
		getMessages().get(language).put("SE", "Швеция");
		getMessages().get(language).put("SJ", "Шпицберген и Ян-Майен");
		getMessages().get(language).put("LK", "Шри-Ланка (Цейлон)");
		getMessages().get(language).put("EC", "Эквадор, Галапагосы");
		getMessages().get(language).put("GQ", "Экваториальная Гвинея");
		getMessages().get(language).put("ER", "Эритрея");
		getMessages().get(language).put("EE", "Эстония");
		getMessages().get(language).put("ET", "Эфиопия");
		getMessages().get(language).put("ZA", "ЮАР");
		getMessages().get(language).put("GS", "Южная Георгия и Южные Сандвичевы острова");
		getMessages().get(language).put("JM", "Ямайка");
		getMessages().get(language).put("FM", "Яп");
		getMessages().get(language).put("JP", "Япония");

	}
	static
	{
		for (String s : getMessages().keySet().toArray(new String[0]))
		{
			HashMap<String, String> l = new HashMap<String, String>();
			for (String s2 : getMessages().get(s).keySet())
				l.put(s2.toLowerCase(), getMessages().get(s).get(s2));
			getMessages().remove(s);
			getMessages().put(s.toLowerCase(), l);
		}
		{
			String language = "en_uk";
			getMessages().get(language).put("privacypolicytext", getMsg("privacyPolicy", language) + "\n"
					+ "0.1 When using the program, you agree to these terms.\n"
					+ "0.2 If you read this Privacy Policy in a language other than Russian, you agree that, in the event of any discrepancies,\n"
					+ "the Russian version will prevail.\n"
					+ "1.1 Attention! Any data entered in the SchoolTester program is transferred to the server, the bytes are encrypted during the transfer. When requested by the FSB, in connection\n"
					+ " with the laws of the Russian Federation, access to all data will be open to the FSB. The authors of the program are not responsible if you have lost access to your\n"
					+ "account or someone else has access to your account. In addition to the data that you provide yourself, data is transferred to the server\n"
					+ "entered by other users, and also: local and global IP address, network name, poppy address, java version, java vendor, URL java\n"
					+ "vendor, java home directory, java class version, java class path, OS name, OS architecture, OS version, user name, home\n"
					+ "user directory, user directory, user country, user's time zone, user language, program name, version\n"
					+ "programs, authors of the program, letters of classes, class numbers, surnames, names and patronymics added to the program. If you enter into the program\n"
					+ "any data, then agree to their processing.\n"
					+ "2.1 DO NOT study technology, emulate, create new versions, modify, decompile, disassemble, study code\n"
					+ "programs in other ways. Distribution and application of software products that modify (modify) the source code\n"
					+ "programs \\ SchoolTester \\ (except for official updates) entails responsibility.\n"
					+ "3.1 This product is provided on terms \\ as it is \\, with all possible malfunctions, but this agreement is not\n"
					+ "implies obligations or conditions of applicability for a particular purpose, accuracy or completeness of answers and whether the results of work, guarantees\n"
					+ "high skills, no viruses, no negligence in the manufacture of the product. In cases of using the program not for\n"
					+ "the authors do not bear responsibility for this, it is forbidden to use the School Tester program for the purpose of violating the laws of the Russian Federation or violating\n"
					+ "laws of the Russian Federation not having such goals, the authors of the program do not bear responsibility for violation of the laws of the Russian Federation by users of the program.\n"
					+ "4.1 The program permits:\n"
					+ "4.1.1 Accounts: Create multiple accounts, do not specify additional data in the account, delete your account.\n"
					+ "4.1.2 Tests: Create tests, pass tests, exchange your tests, including buying tests, selling tests, exchanging\n"
					+ "tests for tangible and intangible rewards, view test results.\n" + " 4.1.3 Communication: everything that is not forbidden\n"
					+ "4.1.4 Evaluation: anything that is not prohibited\n" + " 4.1.5 HW: everything that is not forbidden\n"
					+ " 4.1.6 Utilities: everything that is not forbidden\n" + "4.2 The program prohibits:\n"
					+ "4.2.1 Accounts: Incorrect information (Accounts with unauthorized information will be deleted), create fake accounts (Fake\n"
					+ "accounts will be deleted).\n"
					+ "4.2.2 Tests: Create tests with fake information (Tests with false information will be blocked), create tests that promote\n"
					+ "anything illegal or offensive (Tests that propagate anything illegal or offensive will be blocked), tests containing\n"
					+ "obscene words, pictures, etc. (Tests containing obscene words, pictures, etc. will be blocked), tests containing insults or\n"
					+ "something indecent (Tests containing insults or anything indecent will be blocked).\n"
					+ "4.2.3 Communication: everything that is not forbidden\n" + " 4.2.4 Evaluation: to lay down unreasonable estimates\n"
					+ "4.2.5 HW: HW with false information (HW with false information will be blocked), create HW propaganda\n"
					+ "anything illegal or offensive (HW propagandizing anything illegal or offensive will be blocked), HW containing\n"
					+ "obscene words, pictures, etc. (HW containing obscene words, pictures, etc. will be blocked), HW containing insults or\n"
					+ "something indecent (HW containing insults or anything indecent will be blocked).\n" + " 4.2.6 Utilities: No bans");
			getMessages().get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
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
			String welcome = "Welcome to SchoolTester!\n" + "If you start working with the program, you submit the privacy policy.\n";
			getMessages().get(language).put("notInAccountMsg", welcome + "Please, sign up or sign in, to start working with the program.");
			getMessages().get(language).put("inTeacherAccountMsg", welcome + "There are common functions in tab '" + getMessages().get(language).get("common")
					+ "'.\n" + "There are special for teacher functions in tab '" + getMessages().get(language).get("special") + "'.\n"
					+ "And there are utilities in tab '" + getMessages().get(language).get("utils") + "'.\n");
			getMessages().get(language).put("inStudentAccountMsg", welcome + "There are common functions in tab '" + getMessages().get(language).get("common")
					+ "'.\n" + "There are special for student functions in tab '" + getMessages().get(language).get("special") + "'.\n"
					+ "And there are utilities in tab '" + getMessages().get(language).get("utils") + "'.\n");
			getMessages().get(language).put("inAdministatorAccountMsg", welcome + "There are common functions in tab '" + getMessages().get(language).get(
					"common") + "'.\n" + "There are special for administrator functions in tab '" + getMessages().get(language).get("special") + "'.\n"
					+ "And there are utilities in tab '" + getMessages().get(language).get("utils") + "'.\n");
		}
		{
			String language = "ru_ru";
			getMessages().get(language).put("privacypolicytext", getMsg("privacyPolicy", language) + "\n"
					+ "0.1 При использовании программы вы соглашаетесь с данными условиями.\n"
					+ "0.2 Если вы читаете настоящую Политику конфиденциальности не на русском языке, вы соглашаетесь с тем, что, в случае любых разночтений, "
					+ "преимущественную силу будет иметь русская версия.\n"
					+ "1.1 Внимание! Любые данные введенные в программу SchoolTester передаются на сервер, байты при передаче шифруются. При запросе ФСБ, в связи"
					+ " с законами РФ, для ФСБ будет открыт доступ ко всем данным. Авторы программы не несут ответственности, если вы потеряли доступ к своему "
					+ "аккаунту или доступ к вашему аккаунту получил кто-либо другой. Кроме данных, которые предоставляете вы сами, на сервер передаются данные "
					+ "введенные другими пользователями, а также: локальный и глобальный IP адрес, сетевое имя, мак адрес, версия java, java продавец, URL java "
					+ "продавца, домашний каталог java, версия класса java, путь класса java, название ОС, архитектура ОС, версия ОС, имя пользователя, домашний "
					+ "каталог пользователя, каталог пользователя, страна пользователя, часовой пояс пользователя, язык пользователя, название программы, версия "
					+ "программы, авторы программы, буквы классов, цифры классов, фамилии, имена и отчества добавленные в программу. Если вы вводите в программу "
					+ "какие-либо данные, то соглашаетесь на их обработку."
					+ "2.1 ЗАПРЕЩАЕТСЯ изучать технологию, эмулировать, создавать новые версии, изменять, декомпилировать, дизассемблировать, изучать код "
					+ "программы другими способами. Распространение и применение программных продуктов, модифицирующих (изменяющих) исходный программный код "
					+ "программы \"SchoolTester\" (за исключением официальных обновлений) влечет за собой ответственность.\n"
					+ "3.1 Настоящий продукт предоставляется на условиях \"как есть\", со всеми возможными неисправностями, при этом настоящее соглашение не "
					+ "подразумевает обязательств или условий применимости для определенной цели, точности или полноты ответов и ли результатов работы, гарантии "
					+ "высокой квалификации, отсутствия вирусов, отсутствия небрежности при изготовлении продукта. В случаи использования программы не по "
					+ "назначению авторы не несут за это ответственности, запрещается использовать программу School Tester в целях нарушения законов РФ или нарушая "
					+ "законы РФ не имея таких целей, авторы программы не несут отвественности за нарушение пользователями программы законов РФ.\n"
					+ "4.1 В программе разрешается: "
					+ "4.1.1 Аккаунты: Cоздавать несколько аккаунтов, не указывать дополнительные данные в аккаунте, удалять свой аккаунт."
					+ "4.1.2 Тесты: Создавать тесты, проходить тесты, обмениваться созданными вами тестами, в том числе покупать тесты, продавать тесты, обменивать "
					+ "тесты на материальные и нематериальные вознаграждения, просматривать результаты тестов." + "4.1.3 Общение: все, что не запрещено"
					+ "4.1.4 Оценки: все, что не запрещено" + "4.1.5 ДЗ: все, что не запрещено" + "4.1.6 Утилиты: все, что не запрещено"
					+ "4.2 В программе запрещается: "
					+ "4.2.1 Аккаунты: Указывать ненастоящую информацию(Аккаунты с ненастоящей информацией будут удаляться), создавать фальшивые аккаунты(Фальшивые "
					+ "аккаунты будут удаляться)."
					+ "4.2.2 Тесты: Создавать тесты с фальшивой информацией(Тесты с фальшивой информацией будут блокироваться), создавать тесты пропагандирующие "
					+ "что-либо незаконное или оскорбительное(Тесты пропагандирующие что-либо незаконное или оскорбительное будут блокироваться), тесты содержащие "
					+ "нецензурные слова, картинки и тд(Тесты содержащие нецензурные слова, картинки и тд будут блокироваться), тесты содержащие оскорбления или "
					+ "что-либо неприличное(Тесты содержащие оскорбления или что-либо неприличное будут блокироваться)."
					+ "4.2.3 Общение: все, что не запрещено" + "4.2.4 Оценки: ставить необоснованные оценки"
					+ "4.2.5 ДЗ: ДЗ с фальшивой информацией(ДЗ с фальшивой информацией будут блокироваться), создавать ДЗ пропагандирующие "
					+ "что-либо незаконное или оскорбительное(ДЗ пропагандирующие что-либо незаконное или оскорбительное будут блокироваться), ДЗ содержащие "
					+ "нецензурные слова, картинки и тд(ДЗ содержащие нецензурные слова, картинки и тд будут блокироваться), ДЗ содержащие оскорбления или "
					+ "что-либо неприличное(ДЗ содержащие оскорбления или что-либо неприличное будут блокироваться)." + "4.2.6 Утилиты: запреты отсутствуют");
			getMessages().get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "Программа предназначена для тестирования учащихся с помощью тестов созданных пользователем, автором программы, а также найденных в "
					+ "интернете. В программе есть несколько видов аккаунтов: Разработчик, Администратор, Студент и Учитель. В программе есть несколько разделов: \n"
					+ "Главный раздел - Стартовая часть, Аккаунтовая часть; \nРаздел разработки - Тестовая часть, Часть разработки; \nСерверный раздел(Только для "
					+ "администраторов) - Главная часть, Аккаунтовая часть, Часть общения, Часть ДЗ, Часть оценок, Часть обработки тестирований; \nСтудентский "
					+ "раздел - Часть общения, Часть ДЗ, Часть оценок, Часть тестирования; \nУчительский раздел - Часть общения, Часть ДЗ, Часть оценок, Часть "
					+ "управления тестированием; \nРаздел утилит - часть доп. материалов(Словари, электронные учебники и тд), часть вспомогательных программ("
					+ "Калькулятор, построитель графиков и тд).\n"
					+ "Часть управления тестированием предназначена для настройки тестирующей части и просмотра статистики по тестам. Сверху есть панель настроек "
					+ "со вкладками \"" + getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \"" + getMsg("help", language)
					+ "\". Во вкладке \"" + getMsg("help", language) + "\" есть \"" + getMsg("privacyPolicy", language) + "\" и \"" + getMsg("usersManual",
							language) + "\". Во вкладке \"" + getMsg("settings", language) + "\" есть вкладка \"" + getMsg("language", language)
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
			String welcome = "Добро пожаловать в программу SchoolTester!\n"
					+ "Если вы начинаете работать с программой, то вы принимаете политику конфенденциальности.\n";
			getMessages().get(language).put("notInAccountMsg", welcome + "Пожалуйста, войдите в аккаунт, чтобы начать работать с программой.");
			getMessages().get(language).put("inTeacherAccountMsg", welcome + "Во вкладке '" + getMessages().get(language).get("common")
					+ "' находятся общие функции.\n" + "Во вкладке '" + getMessages().get(language).get("special")
					+ "' находятся функции доступные только учителю.\n" + "Во вкладке '" + getMessages().get(language).get("utils")
					+ "' находятся дополнительные функции.");
			getMessages().get(language).put("inStudentAccountMsg", welcome + "Во вкладке '" + getMessages().get(language).get("common")
					+ "' находятся общие функции.\n" + "Во вкладке '" + getMessages().get(language).get("special")
					+ "' находятся функции доступные только студенту.\n" + "Во вкладке '" + getMessages().get(language).get("utils")
					+ "' находятся дополнительные функции.");
			getMessages().get(language).put("inAdministratorAccountMsg", welcome + "Во вкладке '" + getMessages().get(language).get("common")
					+ "' находятся общие функции.\n" + "Во вкладке '" + getMessages().get(language).get("special") + "' находятся функции администратора.\n"
					+ "Во вкладке '" + getMessages().get(language).get("utils") + "' находятся дополнительные функции.");

		}
		for (String s : getMessages().keySet().toArray(new String[0]))
		{
			HashMap<String, String> l = new HashMap<String, String>();
			for (String s2 : getMessages().get(s).keySet())
				l.put(s2.toLowerCase(), getMessages().get(s).get(s2));
			getMessages().remove(s);
			getMessages().put(s.toLowerCase(), l);
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
		key = key.toLowerCase();
		language = language.toLowerCase();
		if (!getMessages().containsKey(language))
			throw new IllegalArgumentException("Language could not found!");
		if (!getMessages().get(language).containsKey(key))
			return "null";
		return getMessages().get(language).get(key);
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

	public static HashMap<String, HashMap<String, String>> getMessages()
	{
		return messages;
	}

}
