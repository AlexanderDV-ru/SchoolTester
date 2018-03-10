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

			getMessages().get(language).put("keyIsBad", "��� ���� ��������������!");
			getMessages().get(language).put("updateMsg", "���� ��������� ��������, ����������, �������� ���� ��������� �� ������ ");
			getMessages().get(language).put("youAreInBlacklist", "�� ���������� � ������ ������!");
			getMessages().get(language).put("keyIsRight", "������� ������� ������������ �������!");
			getMessages().get(language).put("notConnectedToServer", "�� ������� ������������ � �������!");
			getMessages().get(language).put("verifyRequestSended",
					"��������� �� ����� ���������� �� ��������������! ����� �������������� ���� ��������� ��������� � ����, �� ������ ������� ��� �� ����� �����: "
							+ getMessages().get(language).get("siteURL"));
			getMessages().get(language).put("testWithFiltersNotExist", "����������� ������ ���������� ��� ������ ������� �� ����������!");
			getMessages().get(language).put("testNotSelected", "���� �� ������!");
			getMessages().get(language).put("undefined", "�� ����������");
			getMessages().get(language).put("notInAccount", "�� � ��������");
			getMessages().get(language).put("teacherAccount", "������� �������");
			getMessages().get(language).put("studentAccount", "������� �������");
			getMessages().get(language).put("administratorAccount", "������� ��������������");

			getMessages().get(language).put("signUpPerformed", "�� ������� ������������������.");
			getMessages().get(language).put("signInPerformed", "�� ������� �����.");
			getMessages().get(language).put("accountAlreadyExists", "������� � ����� ������� ��� ����������!");
			getMessages().get(language).put("passwordIsInvalid", "������ ��������!");
			getMessages().get(language).put("accountNotExists", "�������� �� ����������!");
			getMessages().get(language).put("signOutPerformed", "�� ������� �����.");
			getMessages().get(language).put("accountInfoChanged", "���������� � ����� ������� ��������, ����������, ������� ������.");
			getMessages().get(language).put("accountInfoChangedByYou", "�� ������� �������� ���������� � ����� �������.");
			getMessages().get(language).put("accountDeleted", "�� ������� ������� ���� �������.");

			getMessages().get(language).put("passwordsNotMatch", "������ �� ���������!");

			getMessages().get(language).put("common", "�����");
			getMessages().get(language).put("special", "�����������");
			getMessages().get(language).put("utils", "�������");

			getMessages().get(language).put("fileTree", "����� ������");

			getMessages().get(language).put("window", "����");
			getMessages().get(language).put("settings", "���������");
			getMessages().get(language).put("help", "�������");
			getMessages().get(language).put("accounts", "��������");

			getMessages().get(language).put("language", "����");
			getMessages().get(language).put("privacyPolicy", "�������� ������������������");
			getMessages().get(language).put("usersManual", "���������� �� ������������");
			getMessages().get(language).put("siteLink", "����: " + getMessages().get(language).get("siteURL"));
			getMessages().get(language).put("authorEmail", "���������� �����: AlexanderDV.ru@gmail.com");
			getMessages().get(language).put("openAccount", "������� �������");

			getMessages().get(language).put("statisticsTab", "����������");
			getMessages().get(language).put("testingTab", "������������");

			getMessages().get(language).put("testName", "�������� �����");
			getMessages().get(language).put("file", "����");
			getMessages().get(language).put("classNumber", "����� ������");
			getMessages().get(language).put("classLetter", "����� ������");
			getMessages().get(language).put("studentSurname", "�������");
			getMessages().get(language).put("studentName", "���");
			getMessages().get(language).put("studentSecondName", "��������");
			getMessages().get(language).put("start", "������ ������������");
			getMessages().get(language).put("result", "���������");
			getMessages().get(language).put("maxResult", "������������ ���������");
			getMessages().get(language).put("time", "�����");
			getMessages().get(language).put("timeToTest", "����� ��� �����");
			getMessages().get(language).put("rightAnswersAmount", "���������� ������� �������");
			getMessages().get(language).put("perfectAnswersAmount", "���������� �������� �������");
			getMessages().get(language).put("questionsAmount", "���������� ��������");
			getMessages().get(language).put("fullTime", "������ �����");
			getMessages().get(language).put("getStats", "�������� ����������");
			getMessages().get(language).put("inPercents", "� ���������");
			getMessages().get(language).put("inFractions", "� ������");

			getMessages().get(language).put("testingSettings", "��������� ������������");
			{
				getMessages().get(language).put("none", "�����������");
				getMessages().get(language).put("indicateAnswerQuality", "���������� �������� ���������� ������");
				getMessages().get(language).put("indicateAnswersQuality", "���������� �������� ���� �������");
				getMessages().get(language).put("showRightAnswer", "���������� ������ ����� �� ��������� ������");
				getMessages().get(language).put("goToAllQuestions", "������������� �� ����� ������");
				getMessages().get(language).put("skipBtn", "������ '����������'");
				getMessages().get(language).put("pause", "������ �����");
				getMessages().get(language).put("pauseOnUnfocus", "����� ��� ��������������");
				getMessages().get(language).put("anticopy", "����-�����������");
				getMessages().get(language).put("antiscreenshot", "����-��������");
				getMessages().get(language).put("saveTestingSettings", "��������� ��������� ������������");
			}
			getMessages().get(language).put("lookSettings", "��������� ����");
			{
				getMessages().get(language).put("fixedQSelectBtnHeight", "������������� ������ ������ ������ �������");
				getMessages().get(language).put("fillAllHeightOfAnswersPanel", "��������� ��� ������ ������ �������");
				getMessages().get(language).put("maximazeAnswerButtonHeight", "������������ ������ ������ ������");
				getMessages().get(language).put("spaceBetweenAnswerButtons", "���������� ����� �������� �������");
				getMessages().get(language).put("saveLookSettings", "��������� ��������� ����");
			}

			getMessages().get(language).put("next", "�����");
			getMessages().get(language).put("skip", "����������");
			getMessages().get(language).put("finish", "���������");

			getMessages().get(language).put("smallest", "�������");
			getMessages().get(language).put("average", "�������");
			getMessages().get(language).put("biggest", "�������");
			getMessages().get(language).put("max", "��������");
			getMessages().get(language).put("all", "�����");
			getMessages().get(language).put("score", "�����");
			getMessages().get(language).put("rightAnswers", "�������\n�������");
			getMessages().get(language).put("perfectAnswers", "��������\n�������");
			getMessages().get(language).put("time", "�����");

			getMessages().get(language).put("Config.boolean.true0", "������");
			getMessages().get(language).put("Config.boolean.true1", "�");
			getMessages().get(language).put("Config.boolean.true2", "��");
			getMessages().get(language).put("Config.boolean.true3", "�");
			getMessages().get(language).put("Config.boolean.false0", "����");
			getMessages().get(language).put("Config.boolean.false1", "�");
			getMessages().get(language).put("Config.boolean.false2", "���");
			getMessages().get(language).put("Config.boolean.false3", "�");

			getMessages().get(language).put("programVersion", "���������������");
			getMessages().get(language).put("colorType", "��������");
			getMessages().get(language).put("testVersion", "�����������");
			getMessages().get(language).put("testCreationDate", "�����������������");
			getMessages().get(language).put("testLanguage", "���������");
			getMessages().get(language).put("testSubject", "������������");
			getMessages().get(language).put("authors", "������");
			getMessages().get(language).put("description", "��������");
			getMessages().get(language).put("maxTestTime", "����������������������");

			getMessages().get(language).put("questionToPickOne", "������ �� ����� ������");
			getMessages().get(language).put("questionToSelectSome", "������ �� ����� ����������");
			getMessages().get(language).put("questionToEnterText", "������ �� ���� ������");
			getMessages().get(language).put("questionToDistribution", "������ �� �������������");
			getMessages().get(language).put("questionToMatching", "������ �� �������������");
			getMessages().get(language).put("questionToArrangement", "������ �� ��������������");

			getMessages().get(language).put("questions", "�������");
			getMessages().get(language).put("pickOne", "�����������");
			getMessages().get(language).put("selectSome", "���������������");
			getMessages().get(language).put("enterText", "����������");
			getMessages().get(language).put("distribution", "�������������");
			getMessages().get(language).put("matching", "�������������");
			getMessages().get(language).put("arrangement", "��������������");
			getMessages().get(language).put("question", "������");
			getMessages().get(language).put("answers", "������");
			getMessages().get(language).put("answer", "�����");
			getMessages().get(language).put("award", "�����");
			getMessages().get(language).put("text", "�����");
			getMessages().get(language).put("fontSize", "������������");
			getMessages().get(language).put("ignoreCase", "�������������������");
			getMessages().get(language).put("ignoredCharacters", "�����������������");
			getMessages().get(language).put("minimalResult", "��������������������");
			getMessages().get(language).put("questionsToTestAmount", "��������������������������");
			getMessages().get(language).put("answerFontSize", "������������������");
			getMessages().get(language).put("handleOnlyMaximal", "������������������������������");
			getMessages().get(language).put("awardsForAnswers", "�������������");
			getMessages().get(language).put("awardForAnswer", "������������");
			getMessages().get(language).put("answersIndexes", "��������������");
			getMessages().get(language).put("answerIndex", "������������");
			getMessages().get(language).put("number", "�����");
			getMessages().get(language).put("index", "������");
			getMessages().get(language).put("indexesForNames", "���������������");
			getMessages().get(language).put("naming", "��������");

			getMessages().get(language).put("login", "�����");
			getMessages().get(language).put("password", "������");
			getMessages().get(language).put("passwordRepeat", "������ ������");

			getMessages().get(language).put("deleteAccount", "������� �������");
			getMessages().get(language).put("signOut", "����� �� ��������");
			getMessages().get(language).put("signIn", "����� � �������");
			getMessages().get(language).put("signUp", "������� �������");
			getMessages().get(language).put("profile", "�������");
			{
				getMessages().get(language).put("showAllTabs", "���������� ��� �������");
				getMessages().get(language).put("security", "������������");
				{
					getMessages().get(language).put("newPassword", "������ ������");
					getMessages().get(language).put("newPasswordRepeat", "������ ������ ������");
					getMessages().get(language).put("changePassword", "������� ������");
				}
				getMessages().get(language).put("main", "�������");
				{
					getMessages().get(language).put("surname", "�������");
					getMessages().get(language).put("name", "���");
					getMessages().get(language).put("secondName", "��������");
					getMessages().get(language).put("country", "������");
					getMessages().get(language).put("region", "�������");
					getMessages().get(language).put("city", "�����/����");
					getMessages().get(language).put("school", "�����");
					getMessages().get(language).put("subjets", "��������");
				}
				getMessages().get(language).put("family", "�����");
				{
					getMessages().get(language).put("maritalStatus", "�������� ���������");
					getMessages().get(language).put("grandParents", "�������/�������");
					getMessages().get(language).put("parents", "��������");
					getMessages().get(language).put("children", "����");
					getMessages().get(language).put("grandChildren", "�����");
					getMessages().get(language).put("siblings", "������/������");
					getMessages().get(language).put("exSpouses", "������ �������");
					getMessages().get(language).put("spouse", "������(�)");
					getMessages().get(language).put("otherRelatives", "������ ������������");
				}
				getMessages().get(language).put("contacts", "��������");
				{
					getMessages().get(language).put("phoneNumbers", "���������� ������");
					getMessages().get(language).put("emails", "������ ��. �����");
					getMessages().get(language).put("personalSites", "������������ �����");
					getMessages().get(language).put("otherSites", "������ �����");
					getMessages().get(language).put("otherContacts", "������ ��������");
				}
				getMessages().get(language).put("life", "�����");
				{
					getMessages().get(language).put("age", "�������");
					getMessages().get(language).put("gender", "���");
					getMessages().get(language).put("mainLanguages", "�������� �����");
					getMessages().get(language).put("otherLanguages", "������ �����");
					getMessages().get(language).put("education", "�����������");
					getMessages().get(language).put("career", "�������");
				}
				getMessages().get(language).put("ideas", "����");
				{
					getMessages().get(language).put("aboutAlhogol", "�� ��������");
					getMessages().get(language).put("aboutNarcotics", "� ����������");
					getMessages().get(language).put("aboutSmoking", "� �������");
					getMessages().get(language).put("ideas", "����");
					getMessages().get(language).put("interests", "��������");
					getMessages().get(language).put("favouriteBlogs", "������� �����");
					getMessages().get(language).put("favouriteBooks", "������� �����");
					getMessages().get(language).put("favouriteComputerGames", "������� ������������ ����");
					getMessages().get(language).put("favouriteFilms", "������� ������");
					getMessages().get(language).put("favouriteGames", "������� ����");
					getMessages().get(language).put("favouriteMusic", "������� ������");
					getMessages().get(language).put("favouritePeople", "�������� ����");
					getMessages().get(language).put("favouriteShows", "������� ���");
					getMessages().get(language).put("favouriteQuotes", "������� ������");
					getMessages().get(language).put("mainInLife", "������� � �����");
					getMessages().get(language).put("mainInPeople", "������� � �����");
					getMessages().get(language).put("worldOutlook", "�������������");
					getMessages().get(language).put("politicalViews", "������������ �������");
					getMessages().get(language).put("otherViews", "������ �������");
					getMessages().get(language).put("inspiration", "�����������");
				}
				getMessages().get(language).put("aboutYou", "� ����");
				{
					getMessages().get(language).put("biografy", "���������");
					getMessages().get(language).put("homeCountry", "������ ������");
					getMessages().get(language).put("homeRegion", "������ �������");
					getMessages().get(language).put("homeCity", "������ �����/����");
				}
				getMessages().get(language).put("save", "���������");
			}
			getMessages().get(language).put("openCrossWordGeneratorPart", "������� ��������� �����������");
			getMessages().get(language).put("openTeachersTestsControlPart", "������� �������� ������");
			getMessages().get(language).put("openTestDevPart", "������� ���������� ������");
		}
	}
	static
	{
		String language = "ru_ru";
		getMessages().get(language).put("AU", "���������");
		getMessages().get(language).put("AT", "�������");
		getMessages().get(language).put("AZ", "�����������");
		getMessages().get(language).put("AL", "�������");
		getMessages().get(language).put("HW", "�����");
		getMessages().get(language).put("VI", "������������ ���������� �������");
		getMessages().get(language).put("AS", "������������ �����");
		getMessages().get(language).put("AI", "�������");
		getMessages().get(language).put("AO", "������");
		getMessages().get(language).put("AD", "�������");
		getMessages().get(language).put("AQ", "����������");
		getMessages().get(language).put("AG", "������� � �������");
		getMessages().get(language).put("AR", "���������");
		getMessages().get(language).put("AM", "�������");
		getMessages().get(language).put("AW", "�����");
		getMessages().get(language).put("AF", "����������");
		getMessages().get(language).put("BS", "��������� �������");
		getMessages().get(language).put("BD", "���������");
		getMessages().get(language).put("BB", "��������");
		getMessages().get(language).put("BH", "�������");
		getMessages().get(language).put("BZ", "�����");
		getMessages().get(language).put("BY", "����������");
		getMessages().get(language).put("BE", "�������");
		getMessages().get(language).put("BJ", "�����");
		getMessages().get(language).put("BM", "�������");
		getMessages().get(language).put("BG", "��������");
		getMessages().get(language).put("BO", "�������");
		getMessages().get(language).put("BQ", "������, ����-�������� � ����");
		getMessages().get(language).put("BA", "������ � �����������");
		getMessages().get(language).put("BW", "��������");
		getMessages().get(language).put("BR", "��������");
		getMessages().get(language).put("IO", "���������� ���������� � ��������� ������");
		getMessages().get(language).put("VG", "���������� ���������� �������");
		getMessages().get(language).put("BN", "������");
		getMessages().get(language).put("BV", "����");
		getMessages().get(language).put("BF", "�������-����");
		getMessages().get(language).put("BI", "�������");
		getMessages().get(language).put("BT", "�����");
		getMessages().get(language).put("VU", "�������");
		getMessages().get(language).put("VA", "�������");
		getMessages().get(language).put("GB", "��������������");
		getMessages().get(language).put("HU", "�������");
		getMessages().get(language).put("VE", "���������");
		getMessages().get(language).put("TL", "��������� ����� (�����-�����)");
		getMessages().get(language).put("VN", "�������");
		getMessages().get(language).put("GA", "�����");
		getMessages().get(language).put("HT", "�����");
		getMessages().get(language).put("GY", "������");
		getMessages().get(language).put("GM", "������");
		getMessages().get(language).put("GH", "����");
		getMessages().get(language).put("GP", "���������");
		getMessages().get(language).put("GT", "���������");
		getMessages().get(language).put("GN", "������");
		getMessages().get(language).put("GW", "������-�����");
		getMessages().get(language).put("DE", "��������");
		getMessages().get(language).put("GG", "������");
		getMessages().get(language).put("GI", "���������");
		getMessages().get(language).put("HN", "��������");
		getMessages().get(language).put("HK", "�������");
		getMessages().get(language).put("GD", "�������");
		getMessages().get(language).put("GL", "����������");
		getMessages().get(language).put("GR", "������");
		getMessages().get(language).put("GE", "������");
		getMessages().get(language).put("GU", "����");
		getMessages().get(language).put("DK", "�����");
		getMessages().get(language).put("CD", "��������������� ���������� �����");
		getMessages().get(language).put("JE", "������");
		getMessages().get(language).put("DJ", "�������");
		getMessages().get(language).put("DM", "��������");
		getMessages().get(language).put("DO", "����������");
		getMessages().get(language).put("EG", "������");
		getMessages().get(language).put("ZM", "������");
		getMessages().get(language).put("EH", "�������� ������");
		getMessages().get(language).put("ZW", "��������");
		getMessages().get(language).put("IL", "�������");
		getMessages().get(language).put("IN", "�����, ���");
		getMessages().get(language).put("ID", "���������, ����");
		getMessages().get(language).put("JO", "��������");
		getMessages().get(language).put("IQ", "����");
		getMessages().get(language).put("IR", "����");
		getMessages().get(language).put("IE", "��������");
		getMessages().get(language).put("IS", "��������");
		getMessages().get(language).put("ES", "�������, ������");
		getMessages().get(language).put("IT", "������");
		getMessages().get(language).put("YE", "�����");
		getMessages().get(language).put("CV", "����-����� (������� �������� ����)");
		getMessages().get(language).put("KZ", "���������");
		getMessages().get(language).put("KY", "��������� �������");
		getMessages().get(language).put("KH", "��������");
		getMessages().get(language).put("CM", "�������");
		getMessages().get(language).put("CA", "������");
		getMessages().get(language).put("QA", "�����");
		getMessages().get(language).put("KE", "�����");
		getMessages().get(language).put("CY", "����");
		getMessages().get(language).put("KG", "��������");
		getMessages().get(language).put("KI", "��������");
		getMessages().get(language).put("CN", "�����");
		getMessages().get(language).put("KP", "����");
		getMessages().get(language).put("CC", "��������� ������� (������)");
		getMessages().get(language).put("CO", "��������");
		getMessages().get(language).put("KM", "��������� �������");
		getMessages().get(language).put("KR", "�����");
		getMessages().get(language).put("CR", "�����-����");
		getMessages().get(language).put("CI", "���-������ (����� �������� �����)");
		getMessages().get(language).put("CU", "����");
		getMessages().get(language).put("KW", "������");
		getMessages().get(language).put("CW", "�������");
		getMessages().get(language).put("LA", "����");
		getMessages().get(language).put("LV", "������");
		getMessages().get(language).put("LS", "������");
		getMessages().get(language).put("LR", "�������");
		getMessages().get(language).put("LB", "�����");
		getMessages().get(language).put("LY", "�����");
		getMessages().get(language).put("LT", "�����");
		getMessages().get(language).put("LI", "�����������");
		getMessages().get(language).put("LU", "����������");
		getMessages().get(language).put("MU", "��������");
		getMessages().get(language).put("MR", "����������");
		getMessages().get(language).put("MG", "����������");
		getMessages().get(language).put("YT", "�������");
		getMessages().get(language).put("MO", "����� (������)");
		getMessages().get(language).put("MK", "���������");
		getMessages().get(language).put("MW", "������");
		getMessages().get(language).put("MY", "��������");
		getMessages().get(language).put("ML", "����");
		getMessages().get(language).put("MV", "��������");
		getMessages().get(language).put("MT", "������");
		getMessages().get(language).put("MA", "�������");
		getMessages().get(language).put("MQ", "���������");
		getMessages().get(language).put("MH", "���������� �������");
		getMessages().get(language).put("MX", "�������");
		getMessages().get(language).put("MZ", "��������");
		getMessages().get(language).put("MD", "��������");
		getMessages().get(language).put("MC", "������");
		getMessages().get(language).put("MN", "��������");
		getMessages().get(language).put("MS", "����������");
		getMessages().get(language).put("MM", "������ (�����)");
		getMessages().get(language).put("NA", "�������");
		getMessages().get(language).put("NR", "�����");
		getMessages().get(language).put("NP", "�����");
		getMessages().get(language).put("NE", "�����");
		getMessages().get(language).put("NG", "�������");
		getMessages().get(language).put("AN", "������������� ���������� �������");
		getMessages().get(language).put("NL", "���������� (���������)");
		getMessages().get(language).put("NI", "���������");
		getMessages().get(language).put("NU", "����");
		getMessages().get(language).put("NZ", "����� ��������");
		getMessages().get(language).put("NC", "����� ���������");
		getMessages().get(language).put("NO", "��������");
		getMessages().get(language).put("AE", "���");
		getMessages().get(language).put("OM", "����");
		getMessages().get(language).put("NF", "������ �������");
		getMessages().get(language).put("CX", "������ ���������");
		getMessages().get(language).put("SH", "������ ������ �����");
		getMessages().get(language).put("HM", "������ ���� � ������� ����������");
		getMessages().get(language).put("CK", "������� ����");
		getMessages().get(language).put("PN", "������� �������");
		getMessages().get(language).put("TC", "������� Ҹ��� � ������");
		getMessages().get(language).put("WF", "������� ������ � ������");
		getMessages().get(language).put("PK", "��������");
		getMessages().get(language).put("PW", "�����");
		getMessages().get(language).put("PS", "���������");
		getMessages().get(language).put("PA", "������");
		getMessages().get(language).put("PG", "����� � ����� ������");
		getMessages().get(language).put("PY", "��������");
		getMessages().get(language).put("PE", "����");
		getMessages().get(language).put("PL", "������");
		getMessages().get(language).put("PT", "����������");
		getMessages().get(language).put("PR", "������-����");
		getMessages().get(language).put("CG", "���������� �����");
		getMessages().get(language).put("RE", "�������");
		getMessages().get(language).put("RU", "������");
		getMessages().get(language).put("RW", "������");
		getMessages().get(language).put("RO", "�������");
		getMessages().get(language).put("SV", "���������");
		getMessages().get(language).put("WS", "�����");
		getMessages().get(language).put("SM", "���-������");
		getMessages().get(language).put("ST", "���-���� � ��������");
		getMessages().get(language).put("SA", "���������� ������");
		getMessages().get(language).put("SZ", "���������");
		getMessages().get(language).put("MP", "�������� ���������� �������");
		getMessages().get(language).put("SC", "�������");
		getMessages().get(language).put("PM", "���-���� � �������");
		getMessages().get(language).put("SN", "�������");
		getMessages().get(language).put("VC", "����-������� � ���������");
		getMessages().get(language).put("KN", "����-���� � �����");
		getMessages().get(language).put("LC", "����-�����");
		getMessages().get(language).put("RS", "������");
		getMessages().get(language).put("SG", "��������, ������");
		getMessages().get(language).put("SY", "�����");
		getMessages().get(language).put("SK", "��������");
		getMessages().get(language).put("SI", "��������");
		getMessages().get(language).put("SB", "���������� �������");
		getMessages().get(language).put("SO", "������");
		getMessages().get(language).put("SD", "�����");
		getMessages().get(language).put("SR", "�������");
		getMessages().get(language).put("US", "���");
		getMessages().get(language).put("SL", "������-�����");
		getMessages().get(language).put("TJ", "�����������");
		getMessages().get(language).put("TH", "�������");
		getMessages().get(language).put("TW", "�������");
		getMessages().get(language).put("TZ", "��������");
		getMessages().get(language).put("TG", "����");
		getMessages().get(language).put("TK", "�������");
		getMessages().get(language).put("TO", "�����");
		getMessages().get(language).put("TT", "�������� � ������");
		getMessages().get(language).put("TV", "������");
		getMessages().get(language).put("TN", "�����");
		getMessages().get(language).put("TM", "���������");
		getMessages().get(language).put("TR", "������");
		getMessages().get(language).put("UG", "������");
		getMessages().get(language).put("UZ", "����������");
		getMessages().get(language).put("UA", "�������");
		getMessages().get(language).put("UY", "�������");
		getMessages().get(language).put("FO", "��������� �������");
		getMessages().get(language).put("FM", "������������ ����� ����������");
		getMessages().get(language).put("FJ", "�����");
		getMessages().get(language).put("PH", "���������");
		getMessages().get(language).put("FI", "���������");
		getMessages().get(language).put("FK", "������������ (�����������) �������");
		getMessages().get(language).put("FR", "�������");
		getMessages().get(language).put("GF", "����������� ������");
		getMessages().get(language).put("PF", "����������� ��������� (�����)");
		getMessages().get(language).put("TF", "����������� ����� � �������������� ����������");
		getMessages().get(language).put("HR", "��������");
		getMessages().get(language).put("CF", "��������������������� ����������");
		getMessages().get(language).put("TD", "���");
		getMessages().get(language).put("ME", "����������");
		getMessages().get(language).put("CZ", "�����");
		getMessages().get(language).put("CL", "����, �. �����");
		getMessages().get(language).put("FM", "���� (����)");
		getMessages().get(language).put("CH", "���������");
		getMessages().get(language).put("SE", "������");
		getMessages().get(language).put("SJ", "���������� � ��-�����");
		getMessages().get(language).put("LK", "���-����� (������)");
		getMessages().get(language).put("EC", "�������, ����������");
		getMessages().get(language).put("GQ", "�������������� ������");
		getMessages().get(language).put("ER", "�������");
		getMessages().get(language).put("EE", "�������");
		getMessages().get(language).put("ET", "�������");
		getMessages().get(language).put("ZA", "���");
		getMessages().get(language).put("GS", "����� ������� � ����� ���������� �������");
		getMessages().get(language).put("JM", "������");
		getMessages().get(language).put("FM", "��");
		getMessages().get(language).put("JP", "������");

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
					+ "�with the laws of the Russian Federation, access to all data will be open to the FSB. The authors of the program are not responsible if you have lost access to your\n"
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
					+ "tests for tangible and intangible rewards, view test results.\n" + "�4.1.3 Communication: everything that is not forbidden\n"
					+ "4.1.4 Evaluation: anything that is not prohibited\n" + "�4.1.5 HW: everything that is not forbidden\n"
					+ "�4.1.6 Utilities: everything that is not forbidden\n" + "4.2 The program prohibits:\n"
					+ "4.2.1 Accounts: Incorrect information (Accounts with unauthorized information will be deleted), create fake accounts (Fake\n"
					+ "accounts will be deleted).\n"
					+ "4.2.2 Tests: Create tests with fake information (Tests with false information will be blocked), create tests that promote\n"
					+ "anything illegal or offensive (Tests that propagate anything illegal or offensive will be blocked), tests containing\n"
					+ "obscene words, pictures, etc. (Tests containing obscene words, pictures, etc. will be blocked), tests containing insults or\n"
					+ "something indecent (Tests containing insults or anything indecent will be blocked).\n"
					+ "4.2.3 Communication: everything that is not forbidden\n" + "�4.2.4 Evaluation: to lay down unreasonable estimates\n"
					+ "4.2.5 HW: HW with false information (HW with false information will be blocked), create HW propaganda\n"
					+ "anything illegal or offensive (HW propagandizing anything illegal or offensive will be blocked), HW containing\n"
					+ "obscene words, pictures, etc. (HW containing obscene words, pictures, etc. will be blocked), HW containing insults or\n"
					+ "something indecent (HW containing insults or anything indecent will be blocked).\n" + "�4.2.6 Utilities: No bans");
			getMessages().get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
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
					+ "0.1 ��� ������������� ��������� �� ������������ � ������� ���������.\n"
					+ "0.2 ���� �� ������� ��������� �������� ������������������ �� �� ������� �����, �� ������������ � ���, ���, � ������ ����� �����������, "
					+ "���������������� ���� ����� ����� ������� ������.\n"
					+ "1.1 ��������! ����� ������ ��������� � ��������� SchoolTester ���������� �� ������, ����� ��� �������� ���������. ��� ������� ���, � �����"
					+ " � �������� ��, ��� ��� ����� ������ ������ �� ���� ������. ������ ��������� �� ����� ���������������, ���� �� �������� ������ � ������ "
					+ "�������� ��� ������ � ������ �������� ������� ���-���� ������. ����� ������, ������� �������������� �� ����, �� ������ ���������� ������ "
					+ "��������� ������� ��������������, � �����: ��������� � ���������� IP �����, ������� ���, ��� �����, ������ java, java ��������, URL java "
					+ "��������, �������� ������� java, ������ ������ java, ���� ������ java, �������� ��, ����������� ��, ������ ��, ��� ������������, �������� "
					+ "������� ������������, ������� ������������, ������ ������������, ������� ���� ������������, ���� ������������, �������� ���������, ������ "
					+ "���������, ������ ���������, ����� �������, ����� �������, �������, ����� � �������� ����������� � ���������. ���� �� ������� � ��������� "
					+ "�����-���� ������, �� ������������ �� �� ���������."
					+ "2.1 ����������� ������� ����������, �����������, ��������� ����� ������, ��������, ���������������, �����������������, ������� ��� "
					+ "��������� ������� ���������. ��������������� � ���������� ����������� ���������, �������������� (����������) �������� ����������� ��� "
					+ "��������� \"SchoolTester\" (�� ����������� ����������� ����������) ������ �� ����� ���������������.\n"
					+ "3.1 ��������� ������� ��������������� �� �������� \"��� ����\", �� ����� ���������� ���������������, ��� ���� ��������� ���������� �� "
					+ "������������� ������������ ��� ������� ������������ ��� ������������ ����, �������� ��� ������� ������� � �� ����������� ������, �������� "
					+ "������� ������������, ���������� �������, ���������� ����������� ��� ������������ ��������. � ������ ������������� ��������� �� �� "
					+ "���������� ������ �� ����� �� ��� ���������������, ����������� ������������ ��������� School Tester � ����� ��������� ������� �� ��� ������� "
					+ "������ �� �� ���� ����� �����, ������ ��������� �� ����� �������������� �� ��������� �������������� ��������� ������� ��.\n"
					+ "4.1 � ��������� �����������: "
					+ "4.1.1 ��������: C�������� ��������� ���������, �� ��������� �������������� ������ � ��������, ������� ���� �������."
					+ "4.1.2 �����: ��������� �����, ��������� �����, ������������ ���������� ���� �������, � ��� ����� �������� �����, ��������� �����, ���������� "
					+ "����� �� ������������ � �������������� ��������������, ������������� ���������� ������." + "4.1.3 �������: ���, ��� �� ���������"
					+ "4.1.4 ������: ���, ��� �� ���������" + "4.1.5 ��: ���, ��� �� ���������" + "4.1.6 �������: ���, ��� �� ���������"
					+ "4.2 � ��������� �����������: "
					+ "4.2.1 ��������: ��������� ����������� ����������(�������� � ����������� ����������� ����� ���������), ��������� ��������� ��������(��������� "
					+ "�������� ����� ���������)."
					+ "4.2.2 �����: ��������� ����� � ��������� �����������(����� � ��������� ����������� ����� �������������), ��������� ����� ���������������� "
					+ "���-���� ���������� ��� ��������������(����� ���������������� ���-���� ���������� ��� �������������� ����� �������������), ����� ���������� "
					+ "����������� �����, �������� � ��(����� ���������� ����������� �����, �������� � �� ����� �������������), ����� ���������� ����������� ��� "
					+ "���-���� �����������(����� ���������� ����������� ��� ���-���� ����������� ����� �������������)."
					+ "4.2.3 �������: ���, ��� �� ���������" + "4.2.4 ������: ������� �������������� ������"
					+ "4.2.5 ��: �� � ��������� �����������(�� � ��������� ����������� ����� �������������), ��������� �� ���������������� "
					+ "���-���� ���������� ��� ��������������(�� ���������������� ���-���� ���������� ��� �������������� ����� �������������), �� ���������� "
					+ "����������� �����, �������� � ��(�� ���������� ����������� �����, �������� � �� ����� �������������), �� ���������� ����������� ��� "
					+ "���-���� �����������(�� ���������� ����������� ��� ���-���� ����������� ����� �������������)." + "4.2.6 �������: ������� �����������");
			getMessages().get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "��������� ������������� ��� ������������ �������� � ������� ������ ��������� �������������, ������� ���������, � ����� ��������� � "
					+ "���������. � ��������� ���� ��������� ����� ���������: �����������, �������������, ������� � �������. � ��������� ���� ��������� ��������: \n"
					+ "������� ������ - ��������� �����, ����������� �����; \n������ ���������� - �������� �����, ����� ����������; \n��������� ������(������ ��� "
					+ "���������������) - ������� �����, ����������� �����, ����� �������, ����� ��, ����� ������, ����� ��������� ������������; \n����������� "
					+ "������ - ����� �������, ����� ��, ����� ������, ����� ������������; \n����������� ������ - ����� �������, ����� ��, ����� ������, ����� "
					+ "���������� �������������; \n������ ������ - ����� ���. ����������(�������, ����������� �������� � ��), ����� ��������������� ��������("
					+ "�����������, ����������� �������� � ��).\n"
					+ "����� ���������� ������������� ������������� ��� ��������� ����������� ����� � ��������� ���������� �� ������. ������ ���� ������ �������� "
					+ "�� ��������� \"" + getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \"" + getMsg("help", language)
					+ "\". �� ������� \"" + getMsg("help", language) + "\" ���� \"" + getMsg("privacyPolicy", language) + "\" � \"" + getMsg("usersManual",
							language) + "\". �� ������� \"" + getMsg("settings", language) + "\" ���� ������� \"" + getMsg("language", language)
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
			String welcome = "����� ���������� � ��������� SchoolTester!\n"
					+ "���� �� ��������� �������� � ����������, �� �� ���������� �������� �������������������.\n";
			getMessages().get(language).put("notInAccountMsg", welcome + "����������, ������� � �������, ����� ������ �������� � ����������.");
			getMessages().get(language).put("inTeacherAccountMsg", welcome + "�� ������� '" + getMessages().get(language).get("common")
					+ "' ��������� ����� �������.\n" + "�� ������� '" + getMessages().get(language).get("special")
					+ "' ��������� ������� ��������� ������ �������.\n" + "�� ������� '" + getMessages().get(language).get("utils")
					+ "' ��������� �������������� �������.");
			getMessages().get(language).put("inStudentAccountMsg", welcome + "�� ������� '" + getMessages().get(language).get("common")
					+ "' ��������� ����� �������.\n" + "�� ������� '" + getMessages().get(language).get("special")
					+ "' ��������� ������� ��������� ������ ��������.\n" + "�� ������� '" + getMessages().get(language).get("utils")
					+ "' ��������� �������������� �������.");
			getMessages().get(language).put("inAdministratorAccountMsg", welcome + "�� ������� '" + getMessages().get(language).get("common")
					+ "' ��������� ����� �������.\n" + "�� ������� '" + getMessages().get(language).get("special") + "' ��������� ������� ��������������.\n"
					+ "�� ������� '" + getMessages().get(language).get("utils") + "' ��������� �������������� �������.");

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
