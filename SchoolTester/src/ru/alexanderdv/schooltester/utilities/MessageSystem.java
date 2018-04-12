package ru.alexanderdv.schooltester.utilities;

import java.util.HashMap;

/**
 * 
 * MessageSystem
 * 
 * @author AlexandrDV/AlexanderDV
 * @version 5.9.5a
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
			messages.get(language).put("siteURL", "http://schooltester.ucoz.org/");

			messages.get(language).put("keyIsBad", "Your key is invalid!");
			messages.get(language).put("updateMsg", "Your program is out of date, please, update your program to version ");
			messages.get(language).put("youAreInBlacklist", "You are in blacklist!");
			messages.get(language).put("keyIsRight", "Succesfully connected to the server!");
			messages.get(language).put("notConnectedToServer", "Could not connect to the server!");
			messages.get(language).put("verifyRequestSended",
					"Your computer not verified! To verify your computer communicate with us, you can do this in our site: " + messages.get(language).get(
							"siteURL"));
			messages.get(language).put("notVerified", "Your computer not verified! To verify your computer communicate with us, you can do this in our site: "
					+ messages.get(language).get("siteURL"));
			messages.get(language).put("testWithFiltersNotExist", "Tests' results with these filters aren't exist!");
			messages.get(language).put("testNotSelected", "Test don't selected!");
			messages.get(language).put("undefined", "Undefined");
			messages.get(language).put("openSetupFile", "Open file '%1' to start setup.");

			messages.get(language).put("classMustBeSelected", "Class must be selected!");
			messages.get(language).put("surnameMustBeSelected", "Surname must be selected!");
			messages.get(language).put("nameMustBeSelected", "Name must be selected!");
			messages.get(language).put("secondNameMustBeSelected", "Second name must be selected!");

			messages.get(language).put("signUpPerformed", "You have successfully signed up.");
			messages.get(language).put("signInPerformed", "You have successfully signed in.");
			messages.get(language).put("accountAlreadyExists", "Account with this login already exists!");
			messages.get(language).put("passwordIsInvalid", "The password is not valid!");
			messages.get(language).put("accountNotExists", "The account does not exist!");
			messages.get(language).put("signOutPerformed", "You have successfully signed out.");
			messages.get(language).put("accountInfoChanged", "Info of your profile changed, sign in again, please.");
			messages.get(language).put("accountInfoChangedByYou", "You have successfully change info of your profile.");
			messages.get(language).put("accountDeleted", "You have successfully delete account.");

			messages.get(language).put("pickOneQuestionInfo", "Pick the rightest answer by left click to button with answer text.");
			messages.get(language).put("selectSomeQuestionInfo", "Select some right answers by left click to button with answer text.");
			messages.get(language).put("enterTextQuestionInfo", "Enter right answer text to field.");
			messages.get(language).put("distributionQuestionInfo",
					"Distribute answers to groups, to do this do right click to group and select answer by left click.");
			messages.get(language).put("matchingQuestionInfo", "Match answers, to do this do right click to button and select answer by left click.");
			messages.get(language).put("arrangementQuestionInfo",
					"Arrange answers in right sequence, to select answer number do right click to answer and select answer number by left click. (Vertical arrange of answer not have a weight)");

			messages.get(language).put("youHaveSkipped", "Please, answer to skipped questions to finish test.");

			messages.get(language).put("passwordsNotMatch", "Password don't match!");

			messages.get(language).put("notInAccount", "Not in account");
			messages.get(language).put("teacherAccount", "In teacher's account");
			messages.get(language).put("studentAccount", "In student's account");
			messages.get(language).put("administratorAccount", "In administrator's account");

			messages.get(language).put("createDescription", "Create description");
			messages.get(language).put("enterFormula", "Enter formula");
			messages.get(language).put("namesInLatin", "Names in latin");
			messages.get(language).put("namesInSelectedLanguage", "Names in selected language");
			messages.get(language).put("enterName", "Enter name");
			messages.get(language).put("signsAfterComma", "Signs after comma");

			messages.get(language).put("errorInFormula", "Error, '%1' must be chemical formula!");
			messages.get(language).put("errorInFormulaAtomsLessThanOne", "Error, '%1' must be chemical formula(Atoms amount less than 1)!");
			messages.get(language).put("errorInFormulaInertGas", "Error, '%1' must be chemical formula(Inert)!");

			messages.get(language).put("specificSubstance", "Specific substance - %1.");
			messages.get(language).put("qualitativeComposition", "Qualitative composition - consists of %1 element(s): %2.");
			messages.get(language).put("substanceType", "Substance type - %1.");
			messages.get(language).put("quantitativeComposition", "Quantitative composition - %1.");
			messages.get(language).put("relativeMolecularMass", "Relative molecular mass - %1.");
			messages.get(language).put("elementsMassRatio", "Elements mass ratio - %1.");
			messages.get(language).put("elementsMassFractions", "Elements mass fractions - %1.");
			
			messages.get(language).put("atoms", "atom(s)");

			// Subjects translates
			messages.get(language).put("Math", "Math");
			messages.get(language).put("RussianAndWriting", "Russian and writing");
			messages.get(language).put("English", "English");
			messages.get(language).put("Archaeology", "Archaeology");
			messages.get(language).put("Art", "Art");
			messages.get(language).put("Biology", "Biology");
			messages.get(language).put("Chemistry", "Chemistry");
			messages.get(language).put("Informatics", "Informatics");
			messages.get(language).put("Drama", "Drama");
			messages.get(language).put("Economics", "Economics");
			messages.get(language).put("French", "French");
			messages.get(language).put("Geography", "Geography");
			messages.get(language).put("Geology", "Geology");
			messages.get(language).put("German", "German");
			messages.get(language).put("History", "History");
			messages.get(language).put("HomeEconomics", "Home economics");
			messages.get(language).put("LiteratureAndReading", "Literature and reading");
			messages.get(language).put("Music", "Music");
			messages.get(language).put("PhysicalEducation", "Physical education");
			messages.get(language).put("Physics", "Physics");
			messages.get(language).put("Psychology", "Psychology");
			messages.get(language).put("Science", "Science");
			messages.get(language).put("ForeignLanguage", "Foreign language");
			messages.get(language).put("OtherSubject", "Other subject");
			messages.get(language).put("SocialStudies", "Social studies");

			messages.get(language).put("testMustContainsSyntaxLanguage", "Syntax is wrong: .test file must contains property 'syntaxLanguage'!");
			messages.get(language).put("testMustContainsMainProperties",
					"Syntax is wrong: .test file must contains properties - 'syntaxLanguage', 'colorType' and 'programVersion'!");
			messages.get(language).put("syntaxLanguageIsNotSupported", "Syntax language '%1' is not supported!");
			messages.get(language).put("testVersionNotMatchWithProgramVersion",
					".test file version does not match with version of this program, this can create problems in work!");
			messages.get(language).put("awardsInGroupNotMatch", "Error: Awards of questions in group not match!");
			messages.get(language).put("questionsToTestAmountMoreThanQuestionsAmount", "Warning: questions to test amount more than questions amount!");
			messages.get(language).put("questionsToTestAmountLessThanOne", "Warning: questions to test amount less then one!");
			messages.get(language).put("testNotHaveQuestions", "Error: test don't have any questions!");
			messages.get(language).put("imageNotLoaded", "Image not loaded!");
			messages.get(language).put("questionTypeMustBe", "Type of question must be %1!");
			messages.get(language).put("protectionIsNotExist", "Protection is not exists!");
			messages.get(language).put("protectionIsWeak", "Protection is weak!");
			messages.get(language).put("charactersAreRepeating", "Characters repeats!");
			messages.get(language).put("characterNotExists", "Character not exists!");
			messages.get(language).put("valueNotExist", "Syntax is wrong: value in path '%1' in config in path '%2' not exists!");
			messages.get(language).put("valueMustBeTypeOf", "Syntax is wrong: value '%1' in path '%2' in config in path '%3' must be typeof %4");
			messages.get(language).put("quoteMustBeDouble", "Syntax is wrong: String '%1' is not properly closed by a double-quote");
			messages.get(language).put("colorTypeIsWrong", "Color type syntax is wrong, color type must contain r,g,b and if length equal 4 then a");
			messages.get(language).put("valueMustContainsComma", "Syntax is wrong: value '%1' in path '%2' must have 2 or 3 ','");
			messages.get(language).put("signInToWork", "To work sign in!");
			messages.get(language).put("printscreenWasClicked", "Key printscreen was clicked!");
			messages.get(language).put("questionResultMoreThanMaxResult", "Error! Result of question bigger then max result!");
			messages.get(language).put("accountsInfoNotMatch", "Basic accounts' information don't match: types or logins aren't match!");
			messages.get(language).put("fxWindowStateChanged", "FX window frame state changed! Restart the program to see the effect.");

			messages.get(language).put("splitByComma", "Split by comma.");
			messages.get(language).put("digits", "Digits");
			messages.get(language).put("letters", "Letters");
			messages.get(language).put("splitToH&V", "Split to H & V");
			messages.get(language).put("copyTable", "Copy table");
			messages.get(language).put("backgroundOfEmptyCell", "Background of empty cell");
			messages.get(language).put("borderOfEmptyCell", "Border of empty cell");
			messages.get(language).put("backgroundOfCrosswordCell", "Background of crossword cell");
			messages.get(language).put("borderOfCrosswordCell", "Border of crossword cell");

			messages.get(language).put("common", "Common");
			messages.get(language).put("special", "Special");
			messages.get(language).put("utils", "Utils");

			messages.get(language).put("fileTree", "File tree");

			messages.get(language).put("window", "Window");
			messages.get(language).put("settings", "Settings");
			messages.get(language).put("help", "Help");
			messages.get(language).put("accounts", "Accounts");

			messages.get(language).put("language", "Language");
			messages.get(language).put("privacyPolicy", "Privacy Policy");
			messages.get(language).put("usersManual", "Users manual");
			messages.get(language).put("siteLink", "Site: " + messages.get(language).get("siteURL"));
			messages.get(language).put("openAccount", "Open account");

			messages.get(language).put("statisticsTab", "Statistics");
			messages.get(language).put("testingTab", "Testing");

			messages.get(language).put("testName", "Test name");
			messages.get(language).put("classNumber", "Class number");
			messages.get(language).put("classLetter", "Class letter");
			messages.get(language).put("studentSurname", "Surname");
			messages.get(language).put("studentName", "Name");
			messages.get(language).put("studentSecondName", "Second name");
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

			messages.get(language).put("testingSettings", "Testing settings");
			{
				messages.get(language).put("none", "Default");
				messages.get(language).put("indicateAnswerQuality", "Indicate last answer quality");
				messages.get(language).put("indicateAnswersQuality", "Indicate all answers quality");
				messages.get(language).put("showRightAnswer", "Show right answer to last question");
				messages.get(language).put("goToAllQuestions", "Go to all answers");
				messages.get(language).put("skipBtn", "'Skip' button");
				messages.get(language).put("pause", "Pause button");
				messages.get(language).put("pauseOnUnfocus", "Pause on unfocus");
				messages.get(language).put("anticopy", "Anti-Copy");
				messages.get(language).put("antiscreenshot", "Anti-Screenshot");
				messages.get(language).put("saveTestingSettings", "Save testing settings");
			}
			messages.get(language).put("lookSettings", "Look settings");
			{
				messages.get(language).put("fixedQSelectBtnHeight", "Fixed question select button height");
				messages.get(language).put("fillAllHeightOfAnswersPanel", "Fill all height of answers panel");
				messages.get(language).put("maximazeAnswerButtonHeight", "Maximaze answer button height");
				messages.get(language).put("spaceBetweenAnswerButtons", "Space between answer buttons");
				messages.get(language).put("saveLookSettings", "Save look settings");
			}

			messages.get(language).put("next", "Next");
			messages.get(language).put("skip", "Skip");
			messages.get(language).put("finish", "Finish");
			messages.get(language).put("back", "Back");

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

			messages.get(language).put("questionToPickOne", "Question to pick one");
			messages.get(language).put("questionToSelectSome", "Question to select some");
			messages.get(language).put("questionToEnterText", "Question to enter text");
			messages.get(language).put("questionToDistribution", "Question to distribution");
			messages.get(language).put("questionToMatching", "Question to matching");
			messages.get(language).put("questionToArrangement", "Question to arrangement");

			messages.get(language).put("programVersion", "programVersion");
			messages.get(language).put("colorType", "colorType");
			messages.get(language).put("testVersion", "testVersion");
			messages.get(language).put("testCreationDate", "testCreationDate");
			messages.get(language).put("testLanguage", "testLanguage");
			messages.get(language).put("testSubject", "testSubject");
			messages.get(language).put("authors", "authors");
			messages.get(language).put("description", "description");
			messages.get(language).put("maxTestTime", "maxTestTime");

			messages.get(language).put("startPermissions", "startPermissions");
			messages.get(language).put("hintsPermissions", "hintsPermissions");

			messages.get(language).put("showLastAnswerQualityPermission", "showLastAnswerQualityPermission");
			messages.get(language).put("showAllAnswersQualityPermission", "showAllAnswersQualityPermission");
			messages.get(language).put("showRightAnswerPermission", "showRightAnswerPermission");
			messages.get(language).put("goToAllAnswersPermission", "goToAllAnswersPermission");
			messages.get(language).put("skipPermission", "skipPermission");
			messages.get(language).put("pausePermission", "pausePermission");

			messages.get(language).put("questions", "questions");
			messages.get(language).put("pickOne", "pickOne");
			messages.get(language).put("selectSome", "selectSome");
			messages.get(language).put("enterText", "enterText");
			messages.get(language).put("distribution", "distribution");
			messages.get(language).put("matching", "matching");
			messages.get(language).put("arrangement", "arrangement");
			messages.get(language).put("question", "question");
			messages.get(language).put("answers", "answers");
			messages.get(language).put("answer", "answer");
			messages.get(language).put("award", "award");
			messages.get(language).put("text", "text");
			messages.get(language).put("fontSize", "fontSize");
			messages.get(language).put("ignoreCase", "ignoreCase");
			messages.get(language).put("ignoredCharacters", "ignoredCharacters");
			messages.get(language).put("minimalResult", "minimalResult");
			messages.get(language).put("questionsToTestAmount", "questionsToTestAmount");
			messages.get(language).put("answerFontSize", "answerFontSize");
			messages.get(language).put("handleOnlyMaximal", "handleOnlyMaximal");
			messages.get(language).put("awardsForAnswers", "awardsForAnswers");
			messages.get(language).put("awardForAnswer", "awardForAnswer");
			messages.get(language).put("answersIndexes", "answersIndexes");
			messages.get(language).put("answerIndex", "answerIndex");
			messages.get(language).put("number", "number");
			messages.get(language).put("index", "index");
			messages.get(language).put("indexes", "indexes");
			messages.get(language).put("indexesForNames", "indexesForNames");
			messages.get(language).put("naming", "naming");
			messages.get(language).put("onlyThisIndexes", "onlyThisIndexes");
			messages.get(language).put("group", "group");

			messages.get(language).put("login", "Login");
			messages.get(language).put("password", "Password");
			messages.get(language).put("passwordRepeat", "Password repeat");

			messages.get(language).put("deleteAccount", "Delete account");
			messages.get(language).put("signOut", "Sign Out");
			messages.get(language).put("signIn", "Sign In");
			messages.get(language).put("signUp", "Sign Up");
			messages.get(language).put("profile", "Profile");
			{
				messages.get(language).put("showAllTabs", "Show all tabs");
				messages.get(language).put("security", "Security");
				{
					messages.get(language).put("newPassword", "New password");
					messages.get(language).put("newPasswordRepeat", "New password repeat");
					messages.get(language).put("changePassword", "Change password");
				}
				messages.get(language).put("main", "Main");
				{
					messages.get(language).put("surname", "Surname");
					messages.get(language).put("name", "Name");
					messages.get(language).put("secondName", "Second name");
					messages.get(language).put("country", "Country");
					messages.get(language).put("region", "Region");
					messages.get(language).put("city", "City");
					messages.get(language).put("school", "School");
					messages.get(language).put("subjets", "Subjects");
				}
				messages.get(language).put("family", "Family");
				{
					messages.get(language).put("maritalStatus", "Marital status");
					messages.get(language).put("parents", "Parents");
					messages.get(language).put("grandParents", "Grand parents");
					messages.get(language).put("children", "Children");
					messages.get(language).put("grandChildren", "Grand children");
					messages.get(language).put("siblings", "Siblings");
					messages.get(language).put("exSpouses", "Ex-Spouses");
					messages.get(language).put("spouse", "Spouse");
					messages.get(language).put("otherRelatives", "Other relatives");
				}
				messages.get(language).put("contacts", "Contacts");
				{
					messages.get(language).put("phoneNumbers", "Phone numbers");
					messages.get(language).put("emails", "Emails");
					messages.get(language).put("personalSites", "Personal sites");
					messages.get(language).put("otherSites", "Other sites");
					messages.get(language).put("otherContacts", "Other contacts");
				}
				messages.get(language).put("life", "Life");
				{
					messages.get(language).put("age", "Age");
					messages.get(language).put("gender", "Gender");
					messages.get(language).put("mainLanguages", "Main languages");
					messages.get(language).put("otherLanguages", "Other languages");
					messages.get(language).put("education", "Education");
					messages.get(language).put("career", "Career");
				}
				messages.get(language).put("ideas", "Ideas");
				{
					messages.get(language).put("aboutAlhogol", "About alhogol");
					messages.get(language).put("aboutNarcotics", "About narcotics");
					messages.get(language).put("aboutSmoking", "About smoking");
					messages.get(language).put("ideas", "Ideas");
					messages.get(language).put("interests", "Interests");
					messages.get(language).put("favouriteBlogs", "Favourite blogs");
					messages.get(language).put("favouriteBooks", "Favourite books");
					messages.get(language).put("favouriteComputerGames", "Favourite computer games");
					messages.get(language).put("favouriteFilms", "Favourite films");
					messages.get(language).put("favouriteGames", "Favourite games");
					messages.get(language).put("favouriteMusic", "Favourite music");
					messages.get(language).put("favouritePeople", "Favourite people");
					messages.get(language).put("favouriteShows", "Favourite shows");
					messages.get(language).put("favouriteQuotes", "Favourite quotes");
					messages.get(language).put("mainInLife", "Main in life");
					messages.get(language).put("mainInPeople", "Main in people");
					messages.get(language).put("worldOutlook", "World outlook");
					messages.get(language).put("politicalViews", "Political views");
					messages.get(language).put("otherViews", "Other views");
					messages.get(language).put("inspiration", "Inspiration");
				}
				messages.get(language).put("aboutYou", "About you");
				{
					messages.get(language).put("biografy", "Biografy");
					messages.get(language).put("homeCountry", "Home country");
					messages.get(language).put("homeRegion", "Home region");
					messages.get(language).put("homeCity", "Home city/village");
				}
				messages.get(language).put("save", "Save");
			}
			messages.get(language).put("crosswordGenerator", "Crossword generator");
			messages.get(language).put("testsControl", "Tests control");
			messages.get(language).put("testsDevelopment", "Test develope");
			messages.get(language).put("subjectUtilities", "%1 utilities");
			messages.get(language).put("chemicalCompoundsDescriptor", "Chemical compounds descriptor");
			messages.get(language).put("calculator", "Calculator");
			messages.get(language).put("unitConverter", "Unit converter");
			messages.get(language).put("functionsGraphicsGenerator", "Functions graphics generator");
			messages.get(language).put("console", "Console");
			messages.get(language).put("electronicBooks", "Electronic books");
			messages.get(language).put("market", "Market");
			messages.get(language).put("accountsSystem", "Accounts system");
			messages.get(language).put("tester", "Tester");
		}
	}
	static
	{
		String language = "en_uk";
		messages.get(language).put("AU", "Australia");
		messages.get(language).put("AT", "Austria");
		messages.get(language).put("AZ", "Azerbaijan");
		messages.get(language).put("AL", "Albania");
		messages.get(language).put("HW", "Algeria");
		messages.get(language).put("VI", "United States Virgin Islands");
		messages.get(language).put("AS", "American Samoa");
		messages.get(language).put("AI", "Anguilla");
		messages.get(language).put("AO", "Angola");
		messages.get(language).put("AD", "Andorra");
		messages.get(language).put("AQ", "Antarctica");
		messages.get(language).put("AG", "Antigua and Barbuda");
		messages.get(language).put("AR", "Argentina");
		messages.get(language).put("AM", "Armenia");
		messages.get(language).put("AW", "Aruba");
		messages.get(language).put("AF", "Afghanistan");
		messages.get(language).put("BS", "Bahamas");
		messages.get(language).put("BD", "Bangladesh");
		messages.get(language).put("BB", "Barbados");
		messages.get(language).put("BH", "Bahrain");
		messages.get(language).put("BZ", "Belize");
		messages.get(language).put("BY", "Belarus");
		messages.get(language).put("BE", "Belgium");
		messages.get(language).put("BJ", "Benin");
		messages.get(language).put("BM", "Bermuda");
		messages.get(language).put("BG", "Bulgaria");
		messages.get(language).put("BO", "Bolivia");
		messages.get(language).put("BQ", "Caribbean Netherlands");
		messages.get(language).put("BA", "Bosnia and Herzegovina");
		messages.get(language).put("BW", "Botswana");
		messages.get(language).put("BR", "Brazil");
		messages.get(language).put("IO", "British Indian Ocean Territory");
		messages.get(language).put("VG", "British Virgin Islands");
		messages.get(language).put("BN", "Brunei Darussalam");
		messages.get(language).put("BV", "Bouvet Island");
		messages.get(language).put("BF", "Burkina Faso");
		messages.get(language).put("BI", "Burundi");
		messages.get(language).put("BT", "Bhutan");
		messages.get(language).put("VU", "Vanuatu");
		messages.get(language).put("VA", "Vatican");
		messages.get(language).put("GB", "Great Britain");
		messages.get(language).put("HU", "Hungary");
		messages.get(language).put("VE", "Venezuela");
		messages.get(language).put("TL", "East Timor");
		messages.get(language).put("VN", "Vietnam");
		messages.get(language).put("GA", "Gabon");
		messages.get(language).put("HT", "Haiti");
		messages.get(language).put("GY", "Guyana");
		messages.get(language).put("GM", "Gambia");
		messages.get(language).put("GH", "Ghana");
		messages.get(language).put("GP", "Guadeloupe");
		messages.get(language).put("GT", "Guatemala");
		messages.get(language).put("GN", "Guinea");
		messages.get(language).put("GW", "Guinea-Bissau");
		messages.get(language).put("DE", "Germany");
		messages.get(language).put("GG", "Guernsey");
		messages.get(language).put("GI", "Gibraltar");
		messages.get(language).put("HN", "Honduras");
		messages.get(language).put("HK", "Hong Kong");
		messages.get(language).put("GD", "Grenada");
		messages.get(language).put("GL", "Greenland");
		messages.get(language).put("GR", "Greece");
		messages.get(language).put("GE", "Georgia");
		messages.get(language).put("GU", "Guam");
		messages.get(language).put("DK", "Denmark");
		messages.get(language).put("CD", "Democratic Republic of the Congo");
		messages.get(language).put("JE", "Jersey");
		messages.get(language).put("DJ", "Djibouti");
		messages.get(language).put("DM", "Dominica");
		messages.get(language).put("DO", "Dominican Republic");
		messages.get(language).put("EG", "Egypt");
		messages.get(language).put("ZM", "Zambia");
		messages.get(language).put("EH", "Western Sahara");
		messages.get(language).put("ZW", "Zimbabwe");
		messages.get(language).put("IL", "Israel");
		messages.get(language).put("IN", "India");
		messages.get(language).put("ID", "Indonesia");
		messages.get(language).put("JO", "Jordan");
		messages.get(language).put("IQ", "Iraq");
		messages.get(language).put("IR", "Iran");
		messages.get(language).put("IE", "Ireland");
		messages.get(language).put("IS", "Iceland");
		messages.get(language).put("ES", "Spain");
		messages.get(language).put("IT", "Italy");
		messages.get(language).put("YE", "Yemen");
		messages.get(language).put("CV", "Cape Verde");
		messages.get(language).put("KZ", "Kazakhstan");
		messages.get(language).put("KY", "Cayman Islands");
		messages.get(language).put("KH", "Cambodia");
		messages.get(language).put("CM", "Cameroon");
		messages.get(language).put("CA", "Canada");
		messages.get(language).put("QA", "Quatar");
		messages.get(language).put("KE", "Kenya");
		messages.get(language).put("CY", "Cyprus");
		messages.get(language).put("KG", "Kyrgyzstan");
		messages.get(language).put("KI", "Kiribati");
		messages.get(language).put("CN", "China");
		messages.get(language).put("KP", "North Korea");
		messages.get(language).put("CC", "Cocos (Keeling) Islands");
		messages.get(language).put("CO", "Colombia");
		messages.get(language).put("KM", "Comoros");
		messages.get(language).put("KR", "Korea");
		messages.get(language).put("CR", "Costa Rica");
		messages.get(language).put("CI", "Cote d'Ivoire (Ivory Coast)");
		messages.get(language).put("CU", "Cuba");
		messages.get(language).put("KW", "Kuwait");
		messages.get(language).put("CW", "Curacao");
		messages.get(language).put("LA", "Laos");
		messages.get(language).put("LV", "Latvia");
		messages.get(language).put("LS", "Lesotho");
		messages.get(language).put("LR", "Liberia");
		messages.get(language).put("LB", "Lebanon");
		messages.get(language).put("LY", "Libya");
		messages.get(language).put("LT", "Lithuania");
		messages.get(language).put("LI", "Liechtenstein");
		messages.get(language).put("LU", "Luxembourg");
		messages.get(language).put("MU", "Mauritius");
		messages.get(language).put("MR", "Mauritania");
		messages.get(language).put("MG", "Madagascar");
		messages.get(language).put("YT", "Mayotte");
		messages.get(language).put("MO", "Macau");
		messages.get(language).put("MK", "Macedonia");
		messages.get(language).put("MW", "Malawi");
		messages.get(language).put("MY", "Malaysia");
		messages.get(language).put("ML", "Mali");
		messages.get(language).put("MV", "Maldives");
		messages.get(language).put("MT", "Malta");
		messages.get(language).put("MA", "Morocco");
		messages.get(language).put("MQ", "Martinique");
		messages.get(language).put("MH", "Marshall Islands");
		messages.get(language).put("MX", "Mexico");
		messages.get(language).put("MZ", "Mozambique");
		messages.get(language).put("MD", "Moldova");
		messages.get(language).put("MC", "Monaco");
		messages.get(language).put("MN", "Mongolia");
		messages.get(language).put("MS", "Montserrat");
		messages.get(language).put("MM", "Myanmar (Burma)");
		messages.get(language).put("NA", "Namibia");
		messages.get(language).put("NR", "Nauru");
		messages.get(language).put("NP", "Nepal");
		messages.get(language).put("NE", "Niger");
		messages.get(language).put("NG", "Nigeria");
		messages.get(language).put("AN", "Netherlands Antilles");
		messages.get(language).put("NL", "Netherlands");
		messages.get(language).put("NI", "Nicaragua");
		messages.get(language).put("NU", "Niue");
		messages.get(language).put("NZ", "New Zealand");
		messages.get(language).put("NC", "New Caledonia");
		messages.get(language).put("NO", "Norway");
		messages.get(language).put("AE", "United Arab Emirates");
		messages.get(language).put("OM", "Oman");
		messages.get(language).put("NF", "Norfolk Island");
		messages.get(language).put("CX", "Christmas Island");
		messages.get(language).put("SH", "Saint Helena");
		messages.get(language).put("HM", "Heard Island and McDonald Islands");
		messages.get(language).put("CK", "Cook Islands");
		messages.get(language).put("PN", "Pitcairn Islands");
		messages.get(language).put("TC", "The Turks and Caicos Islands");
		messages.get(language).put("WF", "Wallis and Futuna");
		messages.get(language).put("PK", "Pakistan");
		messages.get(language).put("PW", "Palau");
		messages.get(language).put("PS", "Palestinian Territories");
		messages.get(language).put("PA", "Panama");
		messages.get(language).put("PG", "Papua New Guinea");
		messages.get(language).put("PY", "Paraguay");
		messages.get(language).put("PE", "Peru");
		messages.get(language).put("PL", "Poland");
		messages.get(language).put("PT", "Portugal");
		messages.get(language).put("PR", "Puerto Rico");
		messages.get(language).put("CG", "Republic of the Congo");
		messages.get(language).put("RE", "Reunion");
		messages.get(language).put("RU", "Russia");
		messages.get(language).put("RW", "Rwanda");
		messages.get(language).put("RO", "Romania");
		messages.get(language).put("SV", "El Salvador");
		messages.get(language).put("WS", "Samoa");
		messages.get(language).put("SM", "San Marino");
		messages.get(language).put("ST", "Sao Tome and Principe");
		messages.get(language).put("SA", "Saudi Arabia");
		messages.get(language).put("SZ", "Swaziland");
		messages.get(language).put("MP", "Northern Mariana Islands");
		messages.get(language).put("SC", "Seychelles");
		messages.get(language).put("PM", "Saint-Pierre and Miquelon");
		messages.get(language).put("SN", "Senegal");
		messages.get(language).put("VC", "Saint Vincent and the Grenadines");
		messages.get(language).put("KN", "Saint Kitts and Nevis");
		messages.get(language).put("LC", "Saint Lucia");
		messages.get(language).put("RS", "Serbia");
		messages.get(language).put("SG", "Singapore");
		messages.get(language).put("SY", "Syria");
		messages.get(language).put("SK", "Slovakia");
		messages.get(language).put("SI", "Slovenia");
		messages.get(language).put("SB", "Solomon Islands");
		messages.get(language).put("SO", "Somalia");
		messages.get(language).put("SD", "Sudan");
		messages.get(language).put("SR", "Suriname");
		messages.get(language).put("US", "USA");
		messages.get(language).put("SL", "Sierra Leone");
		messages.get(language).put("TJ", "Tajikistan");
		messages.get(language).put("TH", "Thailand");
		messages.get(language).put("TW", "Taiwan (Taipei)");
		messages.get(language).put("TZ", "Tanzania");
		messages.get(language).put("TG", "Togo");
		messages.get(language).put("TK", "Tokelau");
		messages.get(language).put("TO", "Tonga");
		messages.get(language).put("TT", "Trinidad and Tobago");
		messages.get(language).put("TV", "Tuvalu");
		messages.get(language).put("TN", "Tunisia");
		messages.get(language).put("TM", "Turkmenistan");
		messages.get(language).put("TR", "Turkey");
		messages.get(language).put("UG", "Uganda");
		messages.get(language).put("UZ", "Uzbekistan");
		messages.get(language).put("UA", "Ukraine");
		messages.get(language).put("UY", "Uruguay");
		messages.get(language).put("FO", "Faroe Islands");
		messages.get(language).put("FM", "Federated States of Micronesia");
		messages.get(language).put("FJ", "Fiji");
		messages.get(language).put("PH", "Philippines");
		messages.get(language).put("FI", "Finland");
		messages.get(language).put("FK", "Falkland Islands (Malvinas)");
		messages.get(language).put("FR", "France");
		messages.get(language).put("GF", "French Guiana");
		messages.get(language).put("PF", "French Polynesia");
		messages.get(language).put("TF", "French Southern Territories");
		messages.get(language).put("HR", "Croatia");
		messages.get(language).put("CF", "Central African Republic");
		messages.get(language).put("TD", "Chad");
		messages.get(language).put("ME", "Montenegro");
		messages.get(language).put("CZ", "Czechia");
		messages.get(language).put("CL", "Chile");
		messages.get(language).put("FM", "Chuuk");
		messages.get(language).put("CH", "Switzerland");
		messages.get(language).put("SE", "Sweden");
		messages.get(language).put("SJ", "Svalbard and Jan Mayen");
		messages.get(language).put("LK", "Sri Lanka");
		messages.get(language).put("EC", "Ecuador");
		messages.get(language).put("GQ", "Equatorial Guinea");
		messages.get(language).put("ER", "Eritrea");
		messages.get(language).put("EE", "Estonia");
		messages.get(language).put("ET", "Ethiopia");
		messages.get(language).put("ZA", "Republic of South Africa");
		messages.get(language).put("GS", "South Georgia and the South Sandwich Islands");
		messages.get(language).put("JM", "Jamaica");
		messages.get(language).put("FM", "Yap");
	}
	static
	{
		messages.put("ru_ru", new HashMap<String, String>());
		{
			String language = "ru_ru";

			messages.get(language).put("siteURL", "http://schooltester.ucoz.org/");

			messages.get(language).put("keyIsBad", "Ваш ключ недействителен!");
			messages.get(language).put("updateMsg", "Ваша программа устарела, пожалуйста, обновите вашу программу до версии ");
			messages.get(language).put("youAreInBlacklist", "Вы находитесь в черном списке!");
			messages.get(language).put("keyIsRight", "Удалось успешно подключиться серверу!");
			messages.get(language).put("notConnectedToServer", "Не удалось подключиться к серверу!");
			messages.get(language).put("verifyRequestSended",
					"Программа на вашем компьютере не верифицирована! Чтобы верифицировать вашу программу свяжитесь с нами, вы можете сделать это на нашем сайте: "
							+ messages.get(language).get("siteURL"));
			messages.get(language).put("notVerified",
					"Программа на вашем компьютере не верифицирована! Чтобы верифицировать вашу программу свяжитесь с нами, вы можете сделать это на нашем сайте: "
							+ messages.get(language).get("siteURL"));
			messages.get(language).put("testWithFiltersNotExist", "Результатов тестов подходящих под данные фильтры не существует!");
			messages.get(language).put("testNotSelected", "Тест не выбран!");
			messages.get(language).put("undefined", "Не определено");
			messages.get(language).put("openSetupFile", "Запустите файл '%1' для того, чтобы начать установку.");

			messages.get(language).put("classMustBeSelected", "Класс должен быть выбран!");
			messages.get(language).put("surnameMustBeSelected", "Фамилия должна быть выбрана!");
			messages.get(language).put("nameMustBeSelected", "Имя должно быть выбрано!");
			messages.get(language).put("secondNameMustBeSelected", "Отчество должно быть выбрано!");

			messages.get(language).put("createDescription", "Создать описание");
			messages.get(language).put("enterFormula", "Введите формулу");
			messages.get(language).put("namesInLatin", "Названия на латыни");
			messages.get(language).put("namesInSelectedLanguage", "Названия на выбранном языке");
			messages.get(language).put("enterName", "Введите название");
			messages.get(language).put("signsAfterComma", "Знаков после запятой");

			messages.get(language).put("errorInFormula", "Ошибка, '%1' должно быть формулой химического вещества!");
			messages.get(language).put("errorInFormulaAtomsLessThanOne", "Ошибка, '%1' должно быть формулой химического вещества(Количество атомов меньше 1)!");
			messages.get(language).put("errorInFormulaInertGas", "Ошибка, '%1' должно быть формулой химического вещества(Вещество инертное)!");

			messages.get(language).put("specificSubstance", "Конкретное вещество - %1.");
			messages.get(language).put("qualitativeComposition", "Качественный состав - состоит из %1 элементов: %2.");
			messages.get(language).put("substanceType", "Тип вещества - %1.");
			messages.get(language).put("quantitativeComposition", "Количественный состав вещества - %1.");
			messages.get(language).put("relativeMolecularMass", "Относительная молекулярная масса - %1.");
			messages.get(language).put("elementsMassRatio", "Соотношение масс элементов - %1.");
			messages.get(language).put("elementsMassFractions", "Массовые доли элементов - %1.");
			
			messages.get(language).put("atoms", "атом(а,ов)");

			// Subjects translates
			messages.get(language).put("Math", "Математика");
			messages.get(language).put("RussianAndWriting", "Русский и письмо");
			messages.get(language).put("English", "Английский");
			messages.get(language).put("Archaeology", "Археология");
			messages.get(language).put("Art", "ИЗО");
			messages.get(language).put("Biology", "Биология");
			messages.get(language).put("Chemistry", "Химия");
			messages.get(language).put("Informatics", "Информатика");
			messages.get(language).put("Drama", "Драма");
			messages.get(language).put("Economics", "Экономика");
			messages.get(language).put("French", "Французский");
			messages.get(language).put("Geography", "География");
			messages.get(language).put("Geology", "Геология");
			messages.get(language).put("German", "Немецкий");
			messages.get(language).put("History", "История");
			messages.get(language).put("LiteratureAndReading", "Литература и чтение");
			messages.get(language).put("Music", "Музыка");
			messages.get(language).put("PhysicalEducation", "Физкультура");
			messages.get(language).put("Physics", "Физика");
			messages.get(language).put("Psychology", "Психология");
			messages.get(language).put("Science", "Природоведенье");
			messages.get(language).put("ForeignLanguage", "Иностранный язык");
			messages.get(language).put("OtherSubject", "Другой предмет");
			messages.get(language).put("SocialStudies", "Обществознание");

			messages.get(language).put("notInAccount", "Не в аккаунте");
			messages.get(language).put("teacherAccount", "Аккаунт учителя");
			messages.get(language).put("studentAccount", "Аккаунт ученика");
			messages.get(language).put("administratorAccount", "Аккаунт администратора");

			messages.get(language).put("signUpPerformed", "Вы успешно зарегистрировались.");
			messages.get(language).put("signInPerformed", "Вы успешно вошли.");
			messages.get(language).put("accountAlreadyExists", "Аккаунт с таким логином уже существует!");
			messages.get(language).put("passwordIsInvalid", "Пароль неверный!");
			messages.get(language).put("accountNotExists", "Аккаунта не существует!");
			messages.get(language).put("signOutPerformed", "Вы успешно вышли.");
			messages.get(language).put("accountInfoChanged", "Информация в вашем профиле изменена, пожалуйста, зайдите заново.");
			messages.get(language).put("accountInfoChangedByYou", "Вы успешно изменили информацию в вашем профиле.");
			messages.get(language).put("accountDeleted", "Вы успешно удалили свой аккаунт.");

			messages.get(language).put("youHaveSkipped", "Пожалуйста, ответьте на пропущенные вопросы, чтобы закончить тест.");

			messages.get(language).put("pickOneQuestionInfo", "Выберите правильный ответ, для этого, сделайте левый клик по кнопке с ответом.");
			messages.get(language).put("selectSomeQuestionInfo", "Выберите несколько правильных ответов, для этого, сделайте левый клик по кнопке с ответом.");
			messages.get(language).put("enterTextQuestionInfo", "Введите правильный текст ответа в поле.");
			messages.get(language).put("distributionQuestionInfo",
					"Распределите ответы по группам, для этого, сделайте правый клик по группе и выберите ответ по левым кликом.");
			messages.get(language).put("matchingQuestionInfo", "Сопоставьте ответы, для этого, сделайте правый клик по кнопке и выберите ответ левым кликом.");
			messages.get(language).put("arrangementQuestionInfo",
					"Расположите ответы в правильной последовательности, чтобы выбрать номер ответа, сделайте правый клик по ответу и выберите номер левым кликом. (Порядок кнопок с ответами по вертикали значения не имеет)");

			messages.get(language).put("testMustContainsSyntaxLanguage", "Неверный синтаксис: .test файл должен содержать поле 'syntaxLanguage'!");
			messages.get(language).put("testMustContainsMainProperties",
					"Неверный синтаксис: .test файл должен содержать поля 'типЦвета' и 'версияПрограммы'!");
			messages.get(language).put("syntaxLanguageIsNotSupported", "Язык синтаксиса '%1' не поддерживается!");
			messages.get(language).put("testVersionNotMatchWithProgramVersion", "Версия теста отличается от версии программы, это может вызвать ошибки!");
			messages.get(language).put("awardsInGroupNotMatch", "Ошибка: Баллы за ответ в одной группе разные!");
			messages.get(language).put("questionsToTestAmountMoreThanQuestionsAmount",
					"Предупреждение: 'количествоВопросовДляТеста' больше, чем количество вопросов!");
			messages.get(language).put("questionsToTestAmountLessThanOne", "Предупреждение: 'количествоВопросовДляТеста' меньше, чем 1!");
			messages.get(language).put("testNotHaveQuestions", "Ошибка: в тесте нет ни одного вопроса!");
			messages.get(language).put("imageNotLoaded", "Не удалось загрузить изображение!");
			messages.get(language).put("questionTypeMustBe", "Тип вопроса должен быть %1!");
			messages.get(language).put("protectionIsNotExist", "Защита отсутствует!");
			messages.get(language).put("protectionIsWeak", "Защита слишком слабая!");
			messages.get(language).put("charactersAreRepeating", "Символы повторяются!");
			messages.get(language).put("characterNotExists", "Символа не существует!");
			messages.get(language).put("valueNotExist", "Неверный синтаксис: значения по пути '%1' по пути в конфиге '%2' не существует!");
			messages.get(language).put("valueMustBeTypeOf", "Неверный синтаксис: тип значения '%1' по пути '%2' по пути в конфиге '%3' должен быть %4");
			messages.get(language).put("quoteMustBeDouble", "Неверный синтаксис: Строка '%1' не закрыта двойными кавычками!");
			messages.get(language).put("colorTypeIsWrong", "Тип цвета должен быть 3(r,g,b) или 4(r,g,b,a) символа длинной!");
			messages.get(language).put("valueMustContainsComma", "Неверный синтаксис: значение '%1' по пути '%2' должно содержать 2 или 3 ','!");
			messages.get(language).put("signInToWork", "Чтобы начать работу войдите в аккаунт!");
			messages.get(language).put("printscreenWasClicked", "Принтскрин был нажат!");
			messages.get(language).put("questionResultMoreThanMaxResult", "Ошибка! Результат больше максимального результата!");
			messages.get(language).put("accountsInfoNotMatch", "Типы аккаунтов или логины не совпадают!");
			messages.get(language).put("fxWindowStateChanged", "Режим рамки окна изменен, чтобы увидеть эффект перезапустите программу!");
			messages.get(language).put("openSubjectUtilities", "Утилиты - '%1'");

			messages.get(language).put("passwordsNotMatch", "Пароли не совпадают!");

			messages.get(language).put("splitByComma", "Разделяйте запятыми.");
			messages.get(language).put("digits", "Цифры");
			messages.get(language).put("letters", "Буквы");
			messages.get(language).put("splitToH&V", "Делить на Г & В");
			messages.get(language).put("copyTable", "Копировать таблицу");
			messages.get(language).put("backgroundOfEmptyCell", "Фон пустой ячейки");
			messages.get(language).put("borderOfEmptyCell", "Бордер пустой ячейки");
			messages.get(language).put("backgroundOfCrosswordCell", "Фон ячейки кроссворда");
			messages.get(language).put("borderOfCrosswordCell", "Бордер ячейки кроссворда");

			messages.get(language).put("common", "Общее");
			messages.get(language).put("special", "Специальное");
			messages.get(language).put("utils", "Утилиты");

			messages.get(language).put("fileTree", "Древо файлов");

			messages.get(language).put("window", "Окно");
			messages.get(language).put("settings", "Настройки");
			messages.get(language).put("help", "Справка");
			messages.get(language).put("accounts", "Аккаунты");

			messages.get(language).put("language", "Язык");
			messages.get(language).put("privacyPolicy", "Политика конфиденциальности");
			messages.get(language).put("usersManual", "Инструкция по эксплуатации");
			messages.get(language).put("siteLink", "Сайт: " + messages.get(language).get("siteURL"));
			messages.get(language).put("authorEmail", "Копировать почту: AlexanderDV.ru@gmail.com");
			messages.get(language).put("openAccount", "Открыть аккаунт");

			messages.get(language).put("statisticsTab", "Статистика");
			messages.get(language).put("testingTab", "Тестирование");

			messages.get(language).put("testName", "Название теста");
			messages.get(language).put("file", "Файл");
			messages.get(language).put("classNumber", "Номер класса");
			messages.get(language).put("classLetter", "Буква класса");
			messages.get(language).put("studentSurname", "Фамилия");
			messages.get(language).put("studentName", "Имя");
			messages.get(language).put("studentSecondName", "Отчество");
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

			messages.get(language).put("testingSettings", "Настройки тестирования");
			{
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
				messages.get(language).put("saveTestingSettings", "Сохранить настройки тестирования");
			}
			messages.get(language).put("lookSettings", "Настройки вида");
			{
				messages.get(language).put("fixedQSelectBtnHeight", "Фиксированная высота кнопки выбора вопроса");
				messages.get(language).put("fillAllHeightOfAnswersPanel", "Заполнять всю высоту панели ответов");
				messages.get(language).put("maximazeAnswerButtonHeight", "Максимальная высота кнопок ответа");
				messages.get(language).put("spaceBetweenAnswerButtons", "Расстояние между кнопками ответов");
				messages.get(language).put("saveLookSettings", "Сохранить настройки вида");
			}

			messages.get(language).put("next", "Далее");
			messages.get(language).put("skip", "Пропустить");
			messages.get(language).put("finish", "Закончить");
			messages.get(language).put("back", "Назад");

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

			messages.get(language).put("programVersion", "версияПрограммы");
			messages.get(language).put("colorType", "типЦвета");
			messages.get(language).put("testVersion", "версияТеста");
			messages.get(language).put("testCreationDate", "датаСозданияТеста");
			messages.get(language).put("testLanguage", "языкТеста");
			messages.get(language).put("testSubject", "предметТеста");
			messages.get(language).put("authors", "авторы");
			messages.get(language).put("description", "описание");
			messages.get(language).put("maxTestTime", "максимальноеВремяТеста");

			messages.get(language).put("startPermissions", "разрешенияНаСтарт");
			messages.get(language).put("hintsPermissions", "разрешенияНаПодсказки");

			messages.get(language).put("showLastAnswerQualityPermission", "разрешениеНаРежимПоказаВерностиПоследнегоОтвета");
			messages.get(language).put("showAllAnswersQualityPermission", "разрешениеНаПоказВерностиВсехОтветов");
			messages.get(language).put("showRightAnswerPermission", "разрешениеНаПоказПравильногоОтвета");
			messages.get(language).put("goToAllAnswersPermission", "разрешениеНаРежимПереключенияНаЛюбойВопрос");
			messages.get(language).put("skipPermission", "разрешениеНаПропускВопроса");
			messages.get(language).put("pausePermission", "разрешениеНаПаузу");

			messages.get(language).put("questions", "вопросы");
			messages.get(language).put("pickOne", "выборОдного");
			messages.get(language).put("selectSome", "выборНескольких");
			messages.get(language).put("enterText", "вводТекста");
			messages.get(language).put("distribution", "распределение");
			messages.get(language).put("matching", "сопоставление");
			messages.get(language).put("arrangement", "упорядочивание");
			messages.get(language).put("question", "вопрос");
			messages.get(language).put("answers", "ответы");
			messages.get(language).put("answer", "ответ");
			messages.get(language).put("award", "баллы");
			messages.get(language).put("text", "текст");
			messages.get(language).put("fontSize", "размерШрифта");
			messages.get(language).put("ignoreCase", "игнорироватьРегистр");
			messages.get(language).put("ignoredCharacters", "игноруемыеСимволы");
			messages.get(language).put("minimalResult", "минимальныйРезультат");
			messages.get(language).put("questionsToTestAmount", "количествоВопросовДляТеста");
			messages.get(language).put("answerFontSize", "размерШрифтаОтвета");
			messages.get(language).put("handleOnlyMaximal", "обрабатыватьТолькоМаксимальный");
			messages.get(language).put("awardsForAnswers", "баллыЗаОтветы");
			messages.get(language).put("awardForAnswer", "баллыЗаОтвет");
			messages.get(language).put("answersIndexes", "индексыОтветов");
			messages.get(language).put("answerIndex", "индексОтвета");
			messages.get(language).put("number", "номер");
			messages.get(language).put("index", "индекс");
			messages.get(language).put("indexes", "индексы");
			messages.get(language).put("indexesForNames", "индексыНазваний");
			messages.get(language).put("naming", "название");
			messages.get(language).put("onlyThisIndexes", "толькоЭтиИндексы");
			messages.get(language).put("group", "группа");

			messages.get(language).put("questionToPickOne", "Вопрос на выбор одного");
			messages.get(language).put("questionToSelectSome", "Вопрос на выбор нескольких");
			messages.get(language).put("questionToEnterText", "Вопрос на ввод текста");
			messages.get(language).put("questionToDistribution", "Вопрос на распределение");
			messages.get(language).put("questionToMatching", "Вопрос на сопоставление");
			messages.get(language).put("questionToArrangement", "Вопрос на упорядочивание");

			messages.get(language).put("login", "Логин");
			messages.get(language).put("password", "Пароль");
			messages.get(language).put("passwordRepeat", "Повтор пароля");

			messages.get(language).put("deleteAccount", "Удалить аккаунт");
			messages.get(language).put("signOut", "Выйти из аккаунта");
			messages.get(language).put("signIn", "Войти в аккаунт");
			messages.get(language).put("signUp", "Создать аккаунт");
			messages.get(language).put("profile", "Профиль");
			{
				messages.get(language).put("showAllTabs", "Показывать все вкладки");
				messages.get(language).put("security", "Безопасность");
				{
					messages.get(language).put("newPassword", "Повтор пароля");
					messages.get(language).put("newPasswordRepeat", "Повтор нового пароля");
					messages.get(language).put("changePassword", "Сменить пароль");
				}
				messages.get(language).put("main", "Главное");
				{
					messages.get(language).put("surname", "Фамилия");
					messages.get(language).put("name", "Имя");
					messages.get(language).put("secondName", "Отчество");
					messages.get(language).put("country", "Страна");
					messages.get(language).put("region", "Область");
					messages.get(language).put("city", "Город/село");
					messages.get(language).put("school", "Школа");
					messages.get(language).put("subjets", "Предметы");
				}
				messages.get(language).put("family", "Семья");
				{
					messages.get(language).put("maritalStatus", "Семейное положение");
					messages.get(language).put("grandParents", "Бабушки/дедушки");
					messages.get(language).put("parents", "Родители");
					messages.get(language).put("children", "Дети");
					messages.get(language).put("grandChildren", "Внуки");
					messages.get(language).put("siblings", "Братья/сестры");
					messages.get(language).put("exSpouses", "Бывшие супруги");
					messages.get(language).put("spouse", "Супруг(а)");
					messages.get(language).put("otherRelatives", "Другие родственники");
				}
				messages.get(language).put("contacts", "Контакты");
				{
					messages.get(language).put("phoneNumbers", "Телефонные номера");
					messages.get(language).put("emails", "Адреса эл. почты");
					messages.get(language).put("personalSites", "Персональные сайты");
					messages.get(language).put("otherSites", "Другие сайты");
					messages.get(language).put("otherContacts", "Другие контакты");
				}
				messages.get(language).put("life", "Жизнь");
				{
					messages.get(language).put("age", "Возраст");
					messages.get(language).put("gender", "Пол");
					messages.get(language).put("mainLanguages", "Основные языки");
					messages.get(language).put("otherLanguages", "Другие языки");
					messages.get(language).put("education", "Образование");
					messages.get(language).put("career", "Карьера");
				}
				messages.get(language).put("ideas", "Идеи");
				{
					messages.get(language).put("aboutAlhogol", "Об алкоголе");
					messages.get(language).put("aboutNarcotics", "О наркотиках");
					messages.get(language).put("aboutSmoking", "О курении");
					messages.get(language).put("ideas", "Идеи");
					messages.get(language).put("interests", "Интересы");
					messages.get(language).put("favouriteBlogs", "Любимые блоги");
					messages.get(language).put("favouriteBooks", "Любимые книги");
					messages.get(language).put("favouriteComputerGames", "Любимые компьютерные игры");
					messages.get(language).put("favouriteFilms", "Любимые фильмы");
					messages.get(language).put("favouriteGames", "Любимые игры");
					messages.get(language).put("favouriteMusic", "Любимая музыка");
					messages.get(language).put("favouritePeople", "Приятные люди");
					messages.get(language).put("favouriteShows", "Любимые шоу");
					messages.get(language).put("favouriteQuotes", "Любимые цитаты");
					messages.get(language).put("mainInLife", "Главное в жизни");
					messages.get(language).put("mainInPeople", "Главное в людях");
					messages.get(language).put("worldOutlook", "Мировоззрение");
					messages.get(language).put("politicalViews", "Политические взгляды");
					messages.get(language).put("otherViews", "Другие взгляды");
					messages.get(language).put("inspiration", "Вдохновение");
				}
				messages.get(language).put("aboutYou", "О себе");
				{
					messages.get(language).put("biografy", "Биография");
					messages.get(language).put("homeCountry", "Родная страна");
					messages.get(language).put("homeRegion", "Родная область");
					messages.get(language).put("homeCity", "Родной город/село");
				}
				messages.get(language).put("save", "Сохранить");
			}
			messages.get(language).put("crosswordGenerator", "Генератор кроссвордов");
			messages.get(language).put("testsControl", "Управление тестированием");
			messages.get(language).put("testsDevelopment", "Разработка тестов");
			messages.get(language).put("subjectUtilities", "Утилиты - '%1'");
			messages.get(language).put("chemicalCompoundsDescriptor", "Описание химических соединений");
			messages.get(language).put("calculator", "Калькулятор");
			messages.get(language).put("unitConverter", "Конвертер величин");
			messages.get(language).put("functionsGraphicsGenerator", "Генератор графиков функций");
			messages.get(language).put("console", "Консоль");
			messages.get(language).put("electronicBooks", "Электронные учебники");
			messages.get(language).put("market", "Магазин");
			messages.get(language).put("accountsSystem", "Система аккаунтов");
			messages.get(language).put("tester", "Тестер");
		}
	}
	static
	{
		String language = "ru_ru";
		messages.get(language).put("AU", "Австралия");
		messages.get(language).put("AT", "Австрия");
		messages.get(language).put("AZ", "Азербайджан");
		messages.get(language).put("AL", "Албания");
		messages.get(language).put("HW", "Алжир");
		messages.get(language).put("VI", "Американские Виргинские острова");
		messages.get(language).put("AS", "Американское Самоа");
		messages.get(language).put("AI", "Ангилья");
		messages.get(language).put("AO", "Ангола");
		messages.get(language).put("AD", "Андорра");
		messages.get(language).put("AQ", "Антарктида");
		messages.get(language).put("AG", "Антигуа и Барбуда");
		messages.get(language).put("AR", "Аргентина");
		messages.get(language).put("AM", "Армения");
		messages.get(language).put("AW", "Аруба");
		messages.get(language).put("AF", "Афганистан");
		messages.get(language).put("BS", "Багамские острова");
		messages.get(language).put("BD", "Бангладеш");
		messages.get(language).put("BB", "Барбадос");
		messages.get(language).put("BH", "Бахрейн");
		messages.get(language).put("BZ", "Белиз");
		messages.get(language).put("BY", "Белоруссия");
		messages.get(language).put("BE", "Бельгия");
		messages.get(language).put("BJ", "Бенин");
		messages.get(language).put("BM", "Бермуды");
		messages.get(language).put("BG", "Болгария");
		messages.get(language).put("BO", "Боливия");
		messages.get(language).put("BQ", "Бонэйр, Синт-Эстатиус и Саба");
		messages.get(language).put("BA", "Босния и Герцеговина");
		messages.get(language).put("BW", "Ботсвана");
		messages.get(language).put("BR", "Бразилия");
		messages.get(language).put("IO", "Британская территория в Индийском океане");
		messages.get(language).put("VG", "Британские Виргинские острова");
		messages.get(language).put("BN", "Бруней");
		messages.get(language).put("BV", "Буве");
		messages.get(language).put("BF", "Буркина-Фасо");
		messages.get(language).put("BI", "Бурунди");
		messages.get(language).put("BT", "Бутан");
		messages.get(language).put("VU", "Вануату");
		messages.get(language).put("VA", "Ватикан");
		messages.get(language).put("GB", "Великобритания");
		messages.get(language).put("HU", "Венгрия");
		messages.get(language).put("VE", "Венесуэла");
		messages.get(language).put("TL", "Восточный Тимор (Тимор-Лешти)");
		messages.get(language).put("VN", "Вьетнам");
		messages.get(language).put("GA", "Габон");
		messages.get(language).put("HT", "Гаити");
		messages.get(language).put("GY", "Гайана");
		messages.get(language).put("GM", "Гамбия");
		messages.get(language).put("GH", "Гана");
		messages.get(language).put("GP", "Гваделупа");
		messages.get(language).put("GT", "Гватемала");
		messages.get(language).put("GN", "Гвинея");
		messages.get(language).put("GW", "Гвинея-Бисау");
		messages.get(language).put("DE", "Германия");
		messages.get(language).put("GG", "Гернси");
		messages.get(language).put("GI", "Гибралтар");
		messages.get(language).put("HN", "Гондурас");
		messages.get(language).put("HK", "Гонконг");
		messages.get(language).put("GD", "Гренада");
		messages.get(language).put("GL", "Гренландия");
		messages.get(language).put("GR", "Греция");
		messages.get(language).put("GE", "Грузия");
		messages.get(language).put("GU", "Гуам");
		messages.get(language).put("DK", "Дания");
		messages.get(language).put("CD", "Демократическая Республика Конго");
		messages.get(language).put("JE", "Джерси");
		messages.get(language).put("DJ", "Джибути");
		messages.get(language).put("DM", "Доминика");
		messages.get(language).put("DO", "Доминикана");
		messages.get(language).put("EG", "Египет");
		messages.get(language).put("ZM", "Замбия");
		messages.get(language).put("EH", "Западная Сахара");
		messages.get(language).put("ZW", "Зимбабве");
		messages.get(language).put("IL", "Израиль");
		messages.get(language).put("IN", "Индия, Гоа");
		messages.get(language).put("ID", "Индонезия, Бали");
		messages.get(language).put("JO", "Иордания");
		messages.get(language).put("IQ", "Ирак");
		messages.get(language).put("IR", "Иран");
		messages.get(language).put("IE", "Ирландия");
		messages.get(language).put("IS", "Исландия");
		messages.get(language).put("ES", "Испания, Канары");
		messages.get(language).put("IT", "Италия");
		messages.get(language).put("YE", "Йемен");
		messages.get(language).put("CV", "Кабо-Верде (Острова Зеленого Мыса)");
		messages.get(language).put("KZ", "Казахстан");
		messages.get(language).put("KY", "Каймановы острова");
		messages.get(language).put("KH", "Камбоджа");
		messages.get(language).put("CM", "Камерун");
		messages.get(language).put("CA", "Канада");
		messages.get(language).put("QA", "Катар");
		messages.get(language).put("KE", "Кения");
		messages.get(language).put("CY", "Кипр");
		messages.get(language).put("KG", "Киргизия");
		messages.get(language).put("KI", "Кирибати");
		messages.get(language).put("CN", "Китай");
		messages.get(language).put("KP", "КНДР");
		messages.get(language).put("CC", "Кокосовые острова (Килинг)");
		messages.get(language).put("CO", "Колумбия");
		messages.get(language).put("KM", "Коморские острова");
		messages.get(language).put("KR", "Корея");
		messages.get(language).put("CR", "Коста-Рика");
		messages.get(language).put("CI", "Кот-д’Ивуар (Берег Слоновой Кости)");
		messages.get(language).put("CU", "Куба");
		messages.get(language).put("KW", "Кувейт");
		messages.get(language).put("CW", "Кюрасао");
		messages.get(language).put("LA", "Лаос");
		messages.get(language).put("LV", "Латвия");
		messages.get(language).put("LS", "Лесото");
		messages.get(language).put("LR", "Либерия");
		messages.get(language).put("LB", "Ливан");
		messages.get(language).put("LY", "Ливия");
		messages.get(language).put("LT", "Литва");
		messages.get(language).put("LI", "Лихтенштейн");
		messages.get(language).put("LU", "Люксембург");
		messages.get(language).put("MU", "Маврикий");
		messages.get(language).put("MR", "Мавритания");
		messages.get(language).put("MG", "Мадагаскар");
		messages.get(language).put("YT", "Майотта");
		messages.get(language).put("MO", "Макао (Аомынь)");
		messages.get(language).put("MK", "Македония");
		messages.get(language).put("MW", "Малави");
		messages.get(language).put("MY", "Малайзия");
		messages.get(language).put("ML", "Мали");
		messages.get(language).put("MV", "Мальдивы");
		messages.get(language).put("MT", "Мальта");
		messages.get(language).put("MA", "Марокко");
		messages.get(language).put("MQ", "Мартиника");
		messages.get(language).put("MH", "Маршалловы острова");
		messages.get(language).put("MX", "Мексика");
		messages.get(language).put("MZ", "Мозамбик");
		messages.get(language).put("MD", "Молдавия");
		messages.get(language).put("MC", "Монако");
		messages.get(language).put("MN", "Монголия");
		messages.get(language).put("MS", "Монтсеррат");
		messages.get(language).put("MM", "Мьянма (Бирма)");
		messages.get(language).put("NA", "Намибия");
		messages.get(language).put("NR", "Науру");
		messages.get(language).put("NP", "Непал");
		messages.get(language).put("NE", "Нигер");
		messages.get(language).put("NG", "Нигерия");
		messages.get(language).put("AN", "Нидерландские Антильские острова");
		messages.get(language).put("NL", "Нидерланды (Голландия)");
		messages.get(language).put("NI", "Никарагуа");
		messages.get(language).put("NU", "Ниуэ");
		messages.get(language).put("NZ", "Новая Зеландия");
		messages.get(language).put("NC", "Новая Каледония");
		messages.get(language).put("NO", "Норвегия");
		messages.get(language).put("AE", "ОАЭ");
		messages.get(language).put("OM", "Оман");
		messages.get(language).put("NF", "Остров Норфолк");
		messages.get(language).put("CX", "Остров Рождества");
		messages.get(language).put("SH", "Остров Святой Елены");
		messages.get(language).put("HM", "Остров Херд и острова Макдональд");
		messages.get(language).put("CK", "Острова Кука");
		messages.get(language).put("PN", "Острова Питкэрн");
		messages.get(language).put("TC", "Острова Тёркс и Кайкос");
		messages.get(language).put("WF", "Острова Уоллис и Футуна");
		messages.get(language).put("PK", "Пакистан");
		messages.get(language).put("PW", "Палау");
		messages.get(language).put("PS", "Палестина");
		messages.get(language).put("PA", "Панама");
		messages.get(language).put("PG", "Папуа — Новая Гвинея");
		messages.get(language).put("PY", "Парагвай");
		messages.get(language).put("PE", "Перу");
		messages.get(language).put("PL", "Польша");
		messages.get(language).put("PT", "Португалия");
		messages.get(language).put("PR", "Пуэрто-Рико");
		messages.get(language).put("CG", "Республика Конго");
		messages.get(language).put("RE", "Реюньон");
		messages.get(language).put("RU", "Россия");
		messages.get(language).put("RW", "Руанда");
		messages.get(language).put("RO", "Румыния");
		messages.get(language).put("SV", "Сальвадор");
		messages.get(language).put("WS", "Самоа");
		messages.get(language).put("SM", "Сан-Марино");
		messages.get(language).put("ST", "Сан-Томе и Принсипи");
		messages.get(language).put("SA", "Саудовская Аравия");
		messages.get(language).put("SZ", "Свазиленд");
		messages.get(language).put("MP", "Северные Марианские острова");
		messages.get(language).put("SC", "Сейшелы");
		messages.get(language).put("PM", "Сен-Пьер и Микелон");
		messages.get(language).put("SN", "Сенегал");
		messages.get(language).put("VC", "Сент-Винсент и Гренадины");
		messages.get(language).put("KN", "Сент-Китс и Невис");
		messages.get(language).put("LC", "Сент-Люсия");
		messages.get(language).put("RS", "Сербия");
		messages.get(language).put("SG", "Сингапур, Бинтан");
		messages.get(language).put("SY", "Сирия");
		messages.get(language).put("SK", "Словакия");
		messages.get(language).put("SI", "Словения");
		messages.get(language).put("SB", "Соломоновы острова");
		messages.get(language).put("SO", "Сомали");
		messages.get(language).put("SD", "Судан");
		messages.get(language).put("SR", "Суринам");
		messages.get(language).put("US", "США");
		messages.get(language).put("SL", "Сьерра-Леоне");
		messages.get(language).put("TJ", "Таджикистан");
		messages.get(language).put("TH", "Таиланд");
		messages.get(language).put("TW", "Тайвань");
		messages.get(language).put("TZ", "Танзания");
		messages.get(language).put("TG", "Того");
		messages.get(language).put("TK", "Токелау");
		messages.get(language).put("TO", "Тонга");
		messages.get(language).put("TT", "Тринидад и Тобаго");
		messages.get(language).put("TV", "Тувалу");
		messages.get(language).put("TN", "Тунис");
		messages.get(language).put("TM", "Туркмения");
		messages.get(language).put("TR", "Турция");
		messages.get(language).put("UG", "Уганда");
		messages.get(language).put("UZ", "Узбекистан");
		messages.get(language).put("UA", "Украина");
		messages.get(language).put("UY", "Уругвай");
		messages.get(language).put("FO", "Фарерские острова");
		messages.get(language).put("FM", "Федеративные Штаты Микронезии");
		messages.get(language).put("FJ", "Фиджи");
		messages.get(language).put("PH", "Филиппины");
		messages.get(language).put("FI", "Финляндия");
		messages.get(language).put("FK", "Фолклендские (Мальвинские) острова");
		messages.get(language).put("FR", "Франция");
		messages.get(language).put("GF", "Французская Гвиана");
		messages.get(language).put("PF", "Французская Полинезия (Таити)");
		messages.get(language).put("TF", "Французские Южные и Антарктические Территории");
		messages.get(language).put("HR", "Хорватия");
		messages.get(language).put("CF", "Центральноафриканская Республика");
		messages.get(language).put("TD", "Чад");
		messages.get(language).put("ME", "Черногория");
		messages.get(language).put("CZ", "Чехия");
		messages.get(language).put("CL", "Чили, о. Пасхи");
		messages.get(language).put("FM", "Чуук (Трук)");
		messages.get(language).put("CH", "Швейцария");
		messages.get(language).put("SE", "Швеция");
		messages.get(language).put("SJ", "Шпицберген и Ян-Майен");
		messages.get(language).put("LK", "Шри-Ланка (Цейлон)");
		messages.get(language).put("EC", "Эквадор, Галапагосы");
		messages.get(language).put("GQ", "Экваториальная Гвинея");
		messages.get(language).put("ER", "Эритрея");
		messages.get(language).put("EE", "Эстония");
		messages.get(language).put("ET", "Эфиопия");
		messages.get(language).put("ZA", "ЮАР");
		messages.get(language).put("GS", "Южная Георгия и Южные Сандвичевы острова");
		messages.get(language).put("JM", "Ямайка");
		messages.get(language).put("FM", "Яп");
		messages.get(language).put("JP", "Япония");

	}
	static
	{
		for (String s : messages.keySet().toArray(new String[0]))
		{
			HashMap<String, String> l = new HashMap<String, String>();
			for (String s2 : messages.get(s).keySet())
				l.put(s2.toLowerCase(), messages.get(s).get(s2));
			messages.remove(s);
			messages.put(s.toLowerCase(), l);
		}
		{
			String language = "en_uk";
			messages.get(language).put("privacypolicytext", getMsg("privacyPolicy", language) + "\n" + "0.1 When using the program, you agree to these terms.\n"
					+ "0.2 If you read this Privacy Policy in a language other than Russian, you agree that, in the event of any discrepancies,\n"
					+ "the Russian version will prevail.\n"
					+ "1.1 Attention! Any data entered in the SchoolTester program is transferred to the server, the bytes are encrypted during"
					+ " the transfer. When requested by the FSB, in connection\n"
					+ " with the laws of the Russian Federation, access to all data will be open to the FSB. The authors of the program are not"
					+ " responsible if you have lost access to your\n"
					+ "account or someone else has access to your account. In addition to the data that you provide yourself, data is transferred"
					+ " to the server\n"
					+ "entered by other users, and also: local and global IP address, network name, poppy address, java version, java vendor, URL java\n"
					+ "vendor, java home directory, java class version, java class path, OS name, OS architecture, OS version, user name, home\n"
					+ "user directory, user directory, user country, user's time zone, user language, program name, version\n"
					+ "programs, authors of the program, letters of classes, class numbers, surnames, names and patronymics added to the program. If"
					+ " you enter into the program\n" + "any data, then agree to their processing.\n"
					+ "2.1 DO NOT study technology, emulate, create new versions, modify, decompile, disassemble, study code\n"
					+ "programs in other ways. Distribution and application of software products that modify (modify) the source code\n"
					+ "programs \\ SchoolTester \\ (except for official updates) entails responsibility.\n"
					+ "3.1 This product is provided on terms \\ as it is \\, with all possible malfunctions, but this agreement is not\n"
					+ "implies obligations or conditions of applicability for a particular purpose, accuracy or completeness of answers and whether the"
					+ " results of work, guarantees\n"
					+ "high skills, no viruses, no negligence in the manufacture of the product. In cases of using the program not for\n"
					+ "the authors do not bear responsibility for this, it is forbidden to use the School Tester program for the purpose of violating"
					+ " the laws of the Russian Federation or violating\n"
					+ "laws of the Russian Federation not having such goals, the authors of the program do not bear responsibility for violation"
					+ " of the laws of the Russian Federation by users of the program.\n" + "4.1 The program permits:\n"
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
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
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
			messages.get(language).put("notInAccountMsg", welcome + "Please, sign up or sign in, to start working with the program.");
			messages.get(language).put("inTeacherAccountMsg", welcome + "There are common functions in tab '" + messages.get(language).get("common") + "'.\n"
					+ "There are special for teacher functions in tab '" + messages.get(language).get("special") + "'.\n" + "And there are utilities in tab '"
					+ messages.get(language).get("utils") + "'.\n");
			messages.get(language).put("inStudentAccountMsg", welcome + "There are common functions in tab '" + messages.get(language).get("common") + "'.\n"
					+ "There are special for student functions in tab '" + messages.get(language).get("special") + "'.\n" + "And there are utilities in tab '"
					+ messages.get(language).get("utils") + "'.\n");
			messages.get(language).put("inAdministatorAccountMsg", welcome + "There are common functions in tab '" + messages.get(language).get("common")
					+ "'.\n" + "There are special for administrator functions in tab '" + messages.get(language).get("special") + "'.\n"
					+ "And there are utilities in tab '" + messages.get(language).get("utils") + "'.\n");
		}
		{
			String language = "ru_ru";
			messages.get(language).put("privacypolicytext", getMsg("privacyPolicy", language) + "\n"
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
					+ "назначению авторы не несут за это ответственности, запрещается использовать программу School Tester в целях нарушения законов РФ или"
					+ " нарушая законы РФ не имея таких целей, авторы программы не несут отвественности за нарушение пользователями программы законов РФ.\n"
					+ "4.1 В программе разрешается: "
					+ "4.1.1 Аккаунты: Cоздавать несколько аккаунтов, не указывать дополнительные данные в аккаунте, удалять свой аккаунт."
					+ "4.1.2 Тесты: Создавать тесты, проходить тесты, обмениваться созданными вами тестами, в том числе покупать тесты, продавать тесты,"
					+ " обменивать тесты на материальные и нематериальные вознаграждения, просматривать результаты тестов."
					+ "4.1.3 Общение: все, что не запрещено" + "4.1.4 Оценки: все, что не запрещено" + "4.1.5 ДЗ: все, что не запрещено"
					+ "4.1.6 Утилиты: все, что не запрещено" + "4.2 В программе запрещается: "
					+ "4.2.1 Аккаунты: Указывать ненастоящую информацию(Аккаунты с ненастоящей информацией будут удаляться), создавать фальшивые аккаунты"
					+ "(Фальшивые аккаунты будут удаляться)."
					+ "4.2.2 Тесты: Создавать тесты с фальшивой информацией(Тесты с фальшивой информацией будут блокироваться), создавать тесты пропагандирующие "
					+ "что-либо незаконное или оскорбительное(Тесты пропагандирующие что-либо незаконное или оскорбительное будут блокироваться), тесты содержащие "
					+ "нецензурные слова, картинки и тд(Тесты содержащие нецензурные слова, картинки и тд будут блокироваться), тесты содержащие оскорбления или "
					+ "что-либо неприличное(Тесты содержащие оскорбления или что-либо неприличное будут блокироваться)."
					+ "4.2.3 Общение: все, что не запрещено" + "4.2.4 Оценки: ставить необоснованные оценки"
					+ "4.2.5 ДЗ: ДЗ с фальшивой информацией(ДЗ с фальшивой информацией будут блокироваться), создавать ДЗ пропагандирующие "
					+ "что-либо незаконное или оскорбительное(ДЗ пропагандирующие что-либо незаконное или оскорбительное будут блокироваться), ДЗ содержащие "
					+ "нецензурные слова, картинки и тд(ДЗ содержащие нецензурные слова, картинки и тд будут блокироваться), ДЗ содержащие оскорбления или "
					+ "что-либо неприличное(ДЗ содержащие оскорбления или что-либо неприличное будут блокироваться)." + "4.2.6 Утилиты: запреты отсутствуют");
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "Программа предназначена для тестирования учащихся с помощью тестов созданных пользователем, автором программы, а также найденных в "
					+ "интернете. В программе есть несколько видов аккаунтов: Администратор, Студент и Учитель. В программе есть несколько разделов: \n"
					+ "Главный раздел - Стартовая часть, Аккаунтовая часть; \nРаздел разработки - Тестовая часть, Часть разработки; \nСтудентский раздел - "
					+ "Часть общения, Часть ДЗ, Часть оценок, Часть тестирования; \nУчительский раздел - Часть общения, Часть ДЗ, Часть оценок, Часть "
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
			messages.get(language).put("notInAccountMsg", welcome + "Пожалуйста, войдите в аккаунт, чтобы начать работать с программой.");
			messages.get(language).put("inTeacherAccountMsg", welcome + "Во вкладке '" + messages.get(language).get("common") + "' находятся общие функции.\n"
					+ "Во вкладке '" + messages.get(language).get("special") + "' находятся функции доступные только учителю.\n" + "Во вкладке '" + messages
							.get(language).get("utils") + "' находятся дополнительные функции.");
			messages.get(language).put("inStudentAccountMsg", welcome + "Во вкладке '" + messages.get(language).get("common") + "' находятся общие функции.\n"
					+ "Во вкладке '" + messages.get(language).get("special") + "' находятся функции доступные только студенту.\n" + "Во вкладке '" + messages
							.get(language).get("utils") + "' находятся дополнительные функции.");
			messages.get(language).put("inAdministratorAccountMsg", welcome + "Во вкладке '" + messages.get(language).get("common")
					+ "' находятся общие функции.\n" + "Во вкладке '" + messages.get(language).get("special") + "' находятся функции администратора.\n"
					+ "Во вкладке '" + messages.get(language).get("utils") + "' находятся дополнительные функции.");

		}
		for (String s : messages.keySet().toArray(new String[0]))
		{
			HashMap<String, String> l = new HashMap<String, String>();
			for (String s2 : messages.get(s).keySet())
				l.put(s2.toLowerCase(), messages.get(s).get(s2));
			messages.remove(s);
			messages.put(s.toLowerCase(), l);
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
		if (!messages.containsKey(language))
			throw new IllegalArgumentException("Language could not found!");
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

	public static HashMap<String, HashMap<String, String>> getMessages()
	{
		return messages;
	}

}
