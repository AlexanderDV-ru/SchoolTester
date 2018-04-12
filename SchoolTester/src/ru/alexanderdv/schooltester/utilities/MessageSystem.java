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

			messages.get(language).put("keyIsBad", "��� ���� ��������������!");
			messages.get(language).put("updateMsg", "���� ��������� ��������, ����������, �������� ���� ��������� �� ������ ");
			messages.get(language).put("youAreInBlacklist", "�� ���������� � ������ ������!");
			messages.get(language).put("keyIsRight", "������� ������� ������������ �������!");
			messages.get(language).put("notConnectedToServer", "�� ������� ������������ � �������!");
			messages.get(language).put("verifyRequestSended",
					"��������� �� ����� ���������� �� ��������������! ����� �������������� ���� ��������� ��������� � ����, �� ������ ������� ��� �� ����� �����: "
							+ messages.get(language).get("siteURL"));
			messages.get(language).put("notVerified",
					"��������� �� ����� ���������� �� ��������������! ����� �������������� ���� ��������� ��������� � ����, �� ������ ������� ��� �� ����� �����: "
							+ messages.get(language).get("siteURL"));
			messages.get(language).put("testWithFiltersNotExist", "����������� ������ ���������� ��� ������ ������� �� ����������!");
			messages.get(language).put("testNotSelected", "���� �� ������!");
			messages.get(language).put("undefined", "�� ����������");
			messages.get(language).put("openSetupFile", "��������� ���� '%1' ��� ����, ����� ������ ���������.");

			messages.get(language).put("classMustBeSelected", "����� ������ ���� ������!");
			messages.get(language).put("surnameMustBeSelected", "������� ������ ���� �������!");
			messages.get(language).put("nameMustBeSelected", "��� ������ ���� �������!");
			messages.get(language).put("secondNameMustBeSelected", "�������� ������ ���� �������!");

			messages.get(language).put("createDescription", "������� ��������");
			messages.get(language).put("enterFormula", "������� �������");
			messages.get(language).put("namesInLatin", "�������� �� ������");
			messages.get(language).put("namesInSelectedLanguage", "�������� �� ��������� �����");
			messages.get(language).put("enterName", "������� ��������");
			messages.get(language).put("signsAfterComma", "������ ����� �������");

			messages.get(language).put("errorInFormula", "������, '%1' ������ ���� �������� ����������� ��������!");
			messages.get(language).put("errorInFormulaAtomsLessThanOne", "������, '%1' ������ ���� �������� ����������� ��������(���������� ������ ������ 1)!");
			messages.get(language).put("errorInFormulaInertGas", "������, '%1' ������ ���� �������� ����������� ��������(�������� ��������)!");

			messages.get(language).put("specificSubstance", "���������� �������� - %1.");
			messages.get(language).put("qualitativeComposition", "������������ ������ - ������� �� %1 ���������: %2.");
			messages.get(language).put("substanceType", "��� �������� - %1.");
			messages.get(language).put("quantitativeComposition", "�������������� ������ �������� - %1.");
			messages.get(language).put("relativeMolecularMass", "������������� ������������ ����� - %1.");
			messages.get(language).put("elementsMassRatio", "����������� ���� ��������� - %1.");
			messages.get(language).put("elementsMassFractions", "�������� ���� ��������� - %1.");
			
			messages.get(language).put("atoms", "����(�,��)");

			// Subjects translates
			messages.get(language).put("Math", "����������");
			messages.get(language).put("RussianAndWriting", "������� � ������");
			messages.get(language).put("English", "����������");
			messages.get(language).put("Archaeology", "����������");
			messages.get(language).put("Art", "���");
			messages.get(language).put("Biology", "��������");
			messages.get(language).put("Chemistry", "�����");
			messages.get(language).put("Informatics", "�����������");
			messages.get(language).put("Drama", "�����");
			messages.get(language).put("Economics", "���������");
			messages.get(language).put("French", "�����������");
			messages.get(language).put("Geography", "���������");
			messages.get(language).put("Geology", "��������");
			messages.get(language).put("German", "��������");
			messages.get(language).put("History", "�������");
			messages.get(language).put("LiteratureAndReading", "���������� � ������");
			messages.get(language).put("Music", "������");
			messages.get(language).put("PhysicalEducation", "�����������");
			messages.get(language).put("Physics", "������");
			messages.get(language).put("Psychology", "����������");
			messages.get(language).put("Science", "��������������");
			messages.get(language).put("ForeignLanguage", "����������� ����");
			messages.get(language).put("OtherSubject", "������ �������");
			messages.get(language).put("SocialStudies", "��������������");

			messages.get(language).put("notInAccount", "�� � ��������");
			messages.get(language).put("teacherAccount", "������� �������");
			messages.get(language).put("studentAccount", "������� �������");
			messages.get(language).put("administratorAccount", "������� ��������������");

			messages.get(language).put("signUpPerformed", "�� ������� ������������������.");
			messages.get(language).put("signInPerformed", "�� ������� �����.");
			messages.get(language).put("accountAlreadyExists", "������� � ����� ������� ��� ����������!");
			messages.get(language).put("passwordIsInvalid", "������ ��������!");
			messages.get(language).put("accountNotExists", "�������� �� ����������!");
			messages.get(language).put("signOutPerformed", "�� ������� �����.");
			messages.get(language).put("accountInfoChanged", "���������� � ����� ������� ��������, ����������, ������� ������.");
			messages.get(language).put("accountInfoChangedByYou", "�� ������� �������� ���������� � ����� �������.");
			messages.get(language).put("accountDeleted", "�� ������� ������� ���� �������.");

			messages.get(language).put("youHaveSkipped", "����������, �������� �� ����������� �������, ����� ��������� ����.");

			messages.get(language).put("pickOneQuestionInfo", "�������� ���������� �����, ��� �����, �������� ����� ���� �� ������ � �������.");
			messages.get(language).put("selectSomeQuestionInfo", "�������� ��������� ���������� �������, ��� �����, �������� ����� ���� �� ������ � �������.");
			messages.get(language).put("enterTextQuestionInfo", "������� ���������� ����� ������ � ����.");
			messages.get(language).put("distributionQuestionInfo",
					"������������ ������ �� �������, ��� �����, �������� ������ ���� �� ������ � �������� ����� �� ����� ������.");
			messages.get(language).put("matchingQuestionInfo", "����������� ������, ��� �����, �������� ������ ���� �� ������ � �������� ����� ����� ������.");
			messages.get(language).put("arrangementQuestionInfo",
					"����������� ������ � ���������� ������������������, ����� ������� ����� ������, �������� ������ ���� �� ������ � �������� ����� ����� ������. (������� ������ � �������� �� ��������� �������� �� �����)");

			messages.get(language).put("testMustContainsSyntaxLanguage", "�������� ���������: .test ���� ������ ��������� ���� 'syntaxLanguage'!");
			messages.get(language).put("testMustContainsMainProperties",
					"�������� ���������: .test ���� ������ ��������� ���� '��������' � '���������������'!");
			messages.get(language).put("syntaxLanguageIsNotSupported", "���� ���������� '%1' �� ��������������!");
			messages.get(language).put("testVersionNotMatchWithProgramVersion", "������ ����� ���������� �� ������ ���������, ��� ����� ������� ������!");
			messages.get(language).put("awardsInGroupNotMatch", "������: ����� �� ����� � ����� ������ ������!");
			messages.get(language).put("questionsToTestAmountMoreThanQuestionsAmount",
					"��������������: '��������������������������' ������, ��� ���������� ��������!");
			messages.get(language).put("questionsToTestAmountLessThanOne", "��������������: '��������������������������' ������, ��� 1!");
			messages.get(language).put("testNotHaveQuestions", "������: � ����� ��� �� ������ �������!");
			messages.get(language).put("imageNotLoaded", "�� ������� ��������� �����������!");
			messages.get(language).put("questionTypeMustBe", "��� ������� ������ ���� %1!");
			messages.get(language).put("protectionIsNotExist", "������ �����������!");
			messages.get(language).put("protectionIsWeak", "������ ������� ������!");
			messages.get(language).put("charactersAreRepeating", "������� �����������!");
			messages.get(language).put("characterNotExists", "������� �� ����������!");
			messages.get(language).put("valueNotExist", "�������� ���������: �������� �� ���� '%1' �� ���� � ������� '%2' �� ����������!");
			messages.get(language).put("valueMustBeTypeOf", "�������� ���������: ��� �������� '%1' �� ���� '%2' �� ���� � ������� '%3' ������ ���� %4");
			messages.get(language).put("quoteMustBeDouble", "�������� ���������: ������ '%1' �� ������� �������� ���������!");
			messages.get(language).put("colorTypeIsWrong", "��� ����� ������ ���� 3(r,g,b) ��� 4(r,g,b,a) ������� �������!");
			messages.get(language).put("valueMustContainsComma", "�������� ���������: �������� '%1' �� ���� '%2' ������ ��������� 2 ��� 3 ','!");
			messages.get(language).put("signInToWork", "����� ������ ������ ������� � �������!");
			messages.get(language).put("printscreenWasClicked", "���������� ��� �����!");
			messages.get(language).put("questionResultMoreThanMaxResult", "������! ��������� ������ ������������� ����������!");
			messages.get(language).put("accountsInfoNotMatch", "���� ��������� ��� ������ �� ���������!");
			messages.get(language).put("fxWindowStateChanged", "����� ����� ���� �������, ����� ������� ������ ������������� ���������!");
			messages.get(language).put("openSubjectUtilities", "������� - '%1'");

			messages.get(language).put("passwordsNotMatch", "������ �� ���������!");

			messages.get(language).put("splitByComma", "���������� ��������.");
			messages.get(language).put("digits", "�����");
			messages.get(language).put("letters", "�����");
			messages.get(language).put("splitToH&V", "������ �� � & �");
			messages.get(language).put("copyTable", "���������� �������");
			messages.get(language).put("backgroundOfEmptyCell", "��� ������ ������");
			messages.get(language).put("borderOfEmptyCell", "������ ������ ������");
			messages.get(language).put("backgroundOfCrosswordCell", "��� ������ ����������");
			messages.get(language).put("borderOfCrosswordCell", "������ ������ ����������");

			messages.get(language).put("common", "�����");
			messages.get(language).put("special", "�����������");
			messages.get(language).put("utils", "�������");

			messages.get(language).put("fileTree", "����� ������");

			messages.get(language).put("window", "����");
			messages.get(language).put("settings", "���������");
			messages.get(language).put("help", "�������");
			messages.get(language).put("accounts", "��������");

			messages.get(language).put("language", "����");
			messages.get(language).put("privacyPolicy", "�������� ������������������");
			messages.get(language).put("usersManual", "���������� �� ������������");
			messages.get(language).put("siteLink", "����: " + messages.get(language).get("siteURL"));
			messages.get(language).put("authorEmail", "���������� �����: AlexanderDV.ru@gmail.com");
			messages.get(language).put("openAccount", "������� �������");

			messages.get(language).put("statisticsTab", "����������");
			messages.get(language).put("testingTab", "������������");

			messages.get(language).put("testName", "�������� �����");
			messages.get(language).put("file", "����");
			messages.get(language).put("classNumber", "����� ������");
			messages.get(language).put("classLetter", "����� ������");
			messages.get(language).put("studentSurname", "�������");
			messages.get(language).put("studentName", "���");
			messages.get(language).put("studentSecondName", "��������");
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

			messages.get(language).put("testingSettings", "��������� ������������");
			{
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
				messages.get(language).put("saveTestingSettings", "��������� ��������� ������������");
			}
			messages.get(language).put("lookSettings", "��������� ����");
			{
				messages.get(language).put("fixedQSelectBtnHeight", "������������� ������ ������ ������ �������");
				messages.get(language).put("fillAllHeightOfAnswersPanel", "��������� ��� ������ ������ �������");
				messages.get(language).put("maximazeAnswerButtonHeight", "������������ ������ ������ ������");
				messages.get(language).put("spaceBetweenAnswerButtons", "���������� ����� �������� �������");
				messages.get(language).put("saveLookSettings", "��������� ��������� ����");
			}

			messages.get(language).put("next", "�����");
			messages.get(language).put("skip", "����������");
			messages.get(language).put("finish", "���������");
			messages.get(language).put("back", "�����");

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

			messages.get(language).put("programVersion", "���������������");
			messages.get(language).put("colorType", "��������");
			messages.get(language).put("testVersion", "�����������");
			messages.get(language).put("testCreationDate", "�����������������");
			messages.get(language).put("testLanguage", "���������");
			messages.get(language).put("testSubject", "������������");
			messages.get(language).put("authors", "������");
			messages.get(language).put("description", "��������");
			messages.get(language).put("maxTestTime", "����������������������");

			messages.get(language).put("startPermissions", "�����������������");
			messages.get(language).put("hintsPermissions", "���������������������");

			messages.get(language).put("showLastAnswerQualityPermission", "�����������������������������������������������");
			messages.get(language).put("showAllAnswersQualityPermission", "������������������������������������");
			messages.get(language).put("showRightAnswerPermission", "����������������������������������");
			messages.get(language).put("goToAllAnswersPermission", "������������������������������������������");
			messages.get(language).put("skipPermission", "��������������������������");
			messages.get(language).put("pausePermission", "�����������������");

			messages.get(language).put("questions", "�������");
			messages.get(language).put("pickOne", "�����������");
			messages.get(language).put("selectSome", "���������������");
			messages.get(language).put("enterText", "����������");
			messages.get(language).put("distribution", "�������������");
			messages.get(language).put("matching", "�������������");
			messages.get(language).put("arrangement", "��������������");
			messages.get(language).put("question", "������");
			messages.get(language).put("answers", "������");
			messages.get(language).put("answer", "�����");
			messages.get(language).put("award", "�����");
			messages.get(language).put("text", "�����");
			messages.get(language).put("fontSize", "������������");
			messages.get(language).put("ignoreCase", "�������������������");
			messages.get(language).put("ignoredCharacters", "�����������������");
			messages.get(language).put("minimalResult", "��������������������");
			messages.get(language).put("questionsToTestAmount", "��������������������������");
			messages.get(language).put("answerFontSize", "������������������");
			messages.get(language).put("handleOnlyMaximal", "������������������������������");
			messages.get(language).put("awardsForAnswers", "�������������");
			messages.get(language).put("awardForAnswer", "������������");
			messages.get(language).put("answersIndexes", "��������������");
			messages.get(language).put("answerIndex", "������������");
			messages.get(language).put("number", "�����");
			messages.get(language).put("index", "������");
			messages.get(language).put("indexes", "�������");
			messages.get(language).put("indexesForNames", "���������������");
			messages.get(language).put("naming", "��������");
			messages.get(language).put("onlyThisIndexes", "����������������");
			messages.get(language).put("group", "������");

			messages.get(language).put("questionToPickOne", "������ �� ����� ������");
			messages.get(language).put("questionToSelectSome", "������ �� ����� ����������");
			messages.get(language).put("questionToEnterText", "������ �� ���� ������");
			messages.get(language).put("questionToDistribution", "������ �� �������������");
			messages.get(language).put("questionToMatching", "������ �� �������������");
			messages.get(language).put("questionToArrangement", "������ �� ��������������");

			messages.get(language).put("login", "�����");
			messages.get(language).put("password", "������");
			messages.get(language).put("passwordRepeat", "������ ������");

			messages.get(language).put("deleteAccount", "������� �������");
			messages.get(language).put("signOut", "����� �� ��������");
			messages.get(language).put("signIn", "����� � �������");
			messages.get(language).put("signUp", "������� �������");
			messages.get(language).put("profile", "�������");
			{
				messages.get(language).put("showAllTabs", "���������� ��� �������");
				messages.get(language).put("security", "������������");
				{
					messages.get(language).put("newPassword", "������ ������");
					messages.get(language).put("newPasswordRepeat", "������ ������ ������");
					messages.get(language).put("changePassword", "������� ������");
				}
				messages.get(language).put("main", "�������");
				{
					messages.get(language).put("surname", "�������");
					messages.get(language).put("name", "���");
					messages.get(language).put("secondName", "��������");
					messages.get(language).put("country", "������");
					messages.get(language).put("region", "�������");
					messages.get(language).put("city", "�����/����");
					messages.get(language).put("school", "�����");
					messages.get(language).put("subjets", "��������");
				}
				messages.get(language).put("family", "�����");
				{
					messages.get(language).put("maritalStatus", "�������� ���������");
					messages.get(language).put("grandParents", "�������/�������");
					messages.get(language).put("parents", "��������");
					messages.get(language).put("children", "����");
					messages.get(language).put("grandChildren", "�����");
					messages.get(language).put("siblings", "������/������");
					messages.get(language).put("exSpouses", "������ �������");
					messages.get(language).put("spouse", "������(�)");
					messages.get(language).put("otherRelatives", "������ ������������");
				}
				messages.get(language).put("contacts", "��������");
				{
					messages.get(language).put("phoneNumbers", "���������� ������");
					messages.get(language).put("emails", "������ ��. �����");
					messages.get(language).put("personalSites", "������������ �����");
					messages.get(language).put("otherSites", "������ �����");
					messages.get(language).put("otherContacts", "������ ��������");
				}
				messages.get(language).put("life", "�����");
				{
					messages.get(language).put("age", "�������");
					messages.get(language).put("gender", "���");
					messages.get(language).put("mainLanguages", "�������� �����");
					messages.get(language).put("otherLanguages", "������ �����");
					messages.get(language).put("education", "�����������");
					messages.get(language).put("career", "�������");
				}
				messages.get(language).put("ideas", "����");
				{
					messages.get(language).put("aboutAlhogol", "�� ��������");
					messages.get(language).put("aboutNarcotics", "� ����������");
					messages.get(language).put("aboutSmoking", "� �������");
					messages.get(language).put("ideas", "����");
					messages.get(language).put("interests", "��������");
					messages.get(language).put("favouriteBlogs", "������� �����");
					messages.get(language).put("favouriteBooks", "������� �����");
					messages.get(language).put("favouriteComputerGames", "������� ������������ ����");
					messages.get(language).put("favouriteFilms", "������� ������");
					messages.get(language).put("favouriteGames", "������� ����");
					messages.get(language).put("favouriteMusic", "������� ������");
					messages.get(language).put("favouritePeople", "�������� ����");
					messages.get(language).put("favouriteShows", "������� ���");
					messages.get(language).put("favouriteQuotes", "������� ������");
					messages.get(language).put("mainInLife", "������� � �����");
					messages.get(language).put("mainInPeople", "������� � �����");
					messages.get(language).put("worldOutlook", "�������������");
					messages.get(language).put("politicalViews", "������������ �������");
					messages.get(language).put("otherViews", "������ �������");
					messages.get(language).put("inspiration", "�����������");
				}
				messages.get(language).put("aboutYou", "� ����");
				{
					messages.get(language).put("biografy", "���������");
					messages.get(language).put("homeCountry", "������ ������");
					messages.get(language).put("homeRegion", "������ �������");
					messages.get(language).put("homeCity", "������ �����/����");
				}
				messages.get(language).put("save", "���������");
			}
			messages.get(language).put("crosswordGenerator", "��������� �����������");
			messages.get(language).put("testsControl", "���������� �������������");
			messages.get(language).put("testsDevelopment", "���������� ������");
			messages.get(language).put("subjectUtilities", "������� - '%1'");
			messages.get(language).put("chemicalCompoundsDescriptor", "�������� ���������� ����������");
			messages.get(language).put("calculator", "�����������");
			messages.get(language).put("unitConverter", "��������� �������");
			messages.get(language).put("functionsGraphicsGenerator", "��������� �������� �������");
			messages.get(language).put("console", "�������");
			messages.get(language).put("electronicBooks", "����������� ��������");
			messages.get(language).put("market", "�������");
			messages.get(language).put("accountsSystem", "������� ���������");
			messages.get(language).put("tester", "������");
		}
	}
	static
	{
		String language = "ru_ru";
		messages.get(language).put("AU", "���������");
		messages.get(language).put("AT", "�������");
		messages.get(language).put("AZ", "�����������");
		messages.get(language).put("AL", "�������");
		messages.get(language).put("HW", "�����");
		messages.get(language).put("VI", "������������ ���������� �������");
		messages.get(language).put("AS", "������������ �����");
		messages.get(language).put("AI", "�������");
		messages.get(language).put("AO", "������");
		messages.get(language).put("AD", "�������");
		messages.get(language).put("AQ", "����������");
		messages.get(language).put("AG", "������� � �������");
		messages.get(language).put("AR", "���������");
		messages.get(language).put("AM", "�������");
		messages.get(language).put("AW", "�����");
		messages.get(language).put("AF", "����������");
		messages.get(language).put("BS", "��������� �������");
		messages.get(language).put("BD", "���������");
		messages.get(language).put("BB", "��������");
		messages.get(language).put("BH", "�������");
		messages.get(language).put("BZ", "�����");
		messages.get(language).put("BY", "����������");
		messages.get(language).put("BE", "�������");
		messages.get(language).put("BJ", "�����");
		messages.get(language).put("BM", "�������");
		messages.get(language).put("BG", "��������");
		messages.get(language).put("BO", "�������");
		messages.get(language).put("BQ", "������, ����-�������� � ����");
		messages.get(language).put("BA", "������ � �����������");
		messages.get(language).put("BW", "��������");
		messages.get(language).put("BR", "��������");
		messages.get(language).put("IO", "���������� ���������� � ��������� ������");
		messages.get(language).put("VG", "���������� ���������� �������");
		messages.get(language).put("BN", "������");
		messages.get(language).put("BV", "����");
		messages.get(language).put("BF", "�������-����");
		messages.get(language).put("BI", "�������");
		messages.get(language).put("BT", "�����");
		messages.get(language).put("VU", "�������");
		messages.get(language).put("VA", "�������");
		messages.get(language).put("GB", "��������������");
		messages.get(language).put("HU", "�������");
		messages.get(language).put("VE", "���������");
		messages.get(language).put("TL", "��������� ����� (�����-�����)");
		messages.get(language).put("VN", "�������");
		messages.get(language).put("GA", "�����");
		messages.get(language).put("HT", "�����");
		messages.get(language).put("GY", "������");
		messages.get(language).put("GM", "������");
		messages.get(language).put("GH", "����");
		messages.get(language).put("GP", "���������");
		messages.get(language).put("GT", "���������");
		messages.get(language).put("GN", "������");
		messages.get(language).put("GW", "������-�����");
		messages.get(language).put("DE", "��������");
		messages.get(language).put("GG", "������");
		messages.get(language).put("GI", "���������");
		messages.get(language).put("HN", "��������");
		messages.get(language).put("HK", "�������");
		messages.get(language).put("GD", "�������");
		messages.get(language).put("GL", "����������");
		messages.get(language).put("GR", "������");
		messages.get(language).put("GE", "������");
		messages.get(language).put("GU", "����");
		messages.get(language).put("DK", "�����");
		messages.get(language).put("CD", "��������������� ���������� �����");
		messages.get(language).put("JE", "������");
		messages.get(language).put("DJ", "�������");
		messages.get(language).put("DM", "��������");
		messages.get(language).put("DO", "����������");
		messages.get(language).put("EG", "������");
		messages.get(language).put("ZM", "������");
		messages.get(language).put("EH", "�������� ������");
		messages.get(language).put("ZW", "��������");
		messages.get(language).put("IL", "�������");
		messages.get(language).put("IN", "�����, ���");
		messages.get(language).put("ID", "���������, ����");
		messages.get(language).put("JO", "��������");
		messages.get(language).put("IQ", "����");
		messages.get(language).put("IR", "����");
		messages.get(language).put("IE", "��������");
		messages.get(language).put("IS", "��������");
		messages.get(language).put("ES", "�������, ������");
		messages.get(language).put("IT", "������");
		messages.get(language).put("YE", "�����");
		messages.get(language).put("CV", "����-����� (������� �������� ����)");
		messages.get(language).put("KZ", "���������");
		messages.get(language).put("KY", "��������� �������");
		messages.get(language).put("KH", "��������");
		messages.get(language).put("CM", "�������");
		messages.get(language).put("CA", "������");
		messages.get(language).put("QA", "�����");
		messages.get(language).put("KE", "�����");
		messages.get(language).put("CY", "����");
		messages.get(language).put("KG", "��������");
		messages.get(language).put("KI", "��������");
		messages.get(language).put("CN", "�����");
		messages.get(language).put("KP", "����");
		messages.get(language).put("CC", "��������� ������� (������)");
		messages.get(language).put("CO", "��������");
		messages.get(language).put("KM", "��������� �������");
		messages.get(language).put("KR", "�����");
		messages.get(language).put("CR", "�����-����");
		messages.get(language).put("CI", "���-������ (����� �������� �����)");
		messages.get(language).put("CU", "����");
		messages.get(language).put("KW", "������");
		messages.get(language).put("CW", "�������");
		messages.get(language).put("LA", "����");
		messages.get(language).put("LV", "������");
		messages.get(language).put("LS", "������");
		messages.get(language).put("LR", "�������");
		messages.get(language).put("LB", "�����");
		messages.get(language).put("LY", "�����");
		messages.get(language).put("LT", "�����");
		messages.get(language).put("LI", "�����������");
		messages.get(language).put("LU", "����������");
		messages.get(language).put("MU", "��������");
		messages.get(language).put("MR", "����������");
		messages.get(language).put("MG", "����������");
		messages.get(language).put("YT", "�������");
		messages.get(language).put("MO", "����� (������)");
		messages.get(language).put("MK", "���������");
		messages.get(language).put("MW", "������");
		messages.get(language).put("MY", "��������");
		messages.get(language).put("ML", "����");
		messages.get(language).put("MV", "��������");
		messages.get(language).put("MT", "������");
		messages.get(language).put("MA", "�������");
		messages.get(language).put("MQ", "���������");
		messages.get(language).put("MH", "���������� �������");
		messages.get(language).put("MX", "�������");
		messages.get(language).put("MZ", "��������");
		messages.get(language).put("MD", "��������");
		messages.get(language).put("MC", "������");
		messages.get(language).put("MN", "��������");
		messages.get(language).put("MS", "����������");
		messages.get(language).put("MM", "������ (�����)");
		messages.get(language).put("NA", "�������");
		messages.get(language).put("NR", "�����");
		messages.get(language).put("NP", "�����");
		messages.get(language).put("NE", "�����");
		messages.get(language).put("NG", "�������");
		messages.get(language).put("AN", "������������� ���������� �������");
		messages.get(language).put("NL", "���������� (���������)");
		messages.get(language).put("NI", "���������");
		messages.get(language).put("NU", "����");
		messages.get(language).put("NZ", "����� ��������");
		messages.get(language).put("NC", "����� ���������");
		messages.get(language).put("NO", "��������");
		messages.get(language).put("AE", "���");
		messages.get(language).put("OM", "����");
		messages.get(language).put("NF", "������ �������");
		messages.get(language).put("CX", "������ ���������");
		messages.get(language).put("SH", "������ ������ �����");
		messages.get(language).put("HM", "������ ���� � ������� ����������");
		messages.get(language).put("CK", "������� ����");
		messages.get(language).put("PN", "������� �������");
		messages.get(language).put("TC", "������� Ҹ��� � ������");
		messages.get(language).put("WF", "������� ������ � ������");
		messages.get(language).put("PK", "��������");
		messages.get(language).put("PW", "�����");
		messages.get(language).put("PS", "���������");
		messages.get(language).put("PA", "������");
		messages.get(language).put("PG", "����� � ����� ������");
		messages.get(language).put("PY", "��������");
		messages.get(language).put("PE", "����");
		messages.get(language).put("PL", "������");
		messages.get(language).put("PT", "����������");
		messages.get(language).put("PR", "������-����");
		messages.get(language).put("CG", "���������� �����");
		messages.get(language).put("RE", "�������");
		messages.get(language).put("RU", "������");
		messages.get(language).put("RW", "������");
		messages.get(language).put("RO", "�������");
		messages.get(language).put("SV", "���������");
		messages.get(language).put("WS", "�����");
		messages.get(language).put("SM", "���-������");
		messages.get(language).put("ST", "���-���� � ��������");
		messages.get(language).put("SA", "���������� ������");
		messages.get(language).put("SZ", "���������");
		messages.get(language).put("MP", "�������� ���������� �������");
		messages.get(language).put("SC", "�������");
		messages.get(language).put("PM", "���-���� � �������");
		messages.get(language).put("SN", "�������");
		messages.get(language).put("VC", "����-������� � ���������");
		messages.get(language).put("KN", "����-���� � �����");
		messages.get(language).put("LC", "����-�����");
		messages.get(language).put("RS", "������");
		messages.get(language).put("SG", "��������, ������");
		messages.get(language).put("SY", "�����");
		messages.get(language).put("SK", "��������");
		messages.get(language).put("SI", "��������");
		messages.get(language).put("SB", "���������� �������");
		messages.get(language).put("SO", "������");
		messages.get(language).put("SD", "�����");
		messages.get(language).put("SR", "�������");
		messages.get(language).put("US", "���");
		messages.get(language).put("SL", "������-�����");
		messages.get(language).put("TJ", "�����������");
		messages.get(language).put("TH", "�������");
		messages.get(language).put("TW", "�������");
		messages.get(language).put("TZ", "��������");
		messages.get(language).put("TG", "����");
		messages.get(language).put("TK", "�������");
		messages.get(language).put("TO", "�����");
		messages.get(language).put("TT", "�������� � ������");
		messages.get(language).put("TV", "������");
		messages.get(language).put("TN", "�����");
		messages.get(language).put("TM", "���������");
		messages.get(language).put("TR", "������");
		messages.get(language).put("UG", "������");
		messages.get(language).put("UZ", "����������");
		messages.get(language).put("UA", "�������");
		messages.get(language).put("UY", "�������");
		messages.get(language).put("FO", "��������� �������");
		messages.get(language).put("FM", "������������ ����� ����������");
		messages.get(language).put("FJ", "�����");
		messages.get(language).put("PH", "���������");
		messages.get(language).put("FI", "���������");
		messages.get(language).put("FK", "������������ (�����������) �������");
		messages.get(language).put("FR", "�������");
		messages.get(language).put("GF", "����������� ������");
		messages.get(language).put("PF", "����������� ��������� (�����)");
		messages.get(language).put("TF", "����������� ����� � �������������� ����������");
		messages.get(language).put("HR", "��������");
		messages.get(language).put("CF", "��������������������� ����������");
		messages.get(language).put("TD", "���");
		messages.get(language).put("ME", "����������");
		messages.get(language).put("CZ", "�����");
		messages.get(language).put("CL", "����, �. �����");
		messages.get(language).put("FM", "���� (����)");
		messages.get(language).put("CH", "���������");
		messages.get(language).put("SE", "������");
		messages.get(language).put("SJ", "���������� � ��-�����");
		messages.get(language).put("LK", "���-����� (������)");
		messages.get(language).put("EC", "�������, ����������");
		messages.get(language).put("GQ", "�������������� ������");
		messages.get(language).put("ER", "�������");
		messages.get(language).put("EE", "�������");
		messages.get(language).put("ET", "�������");
		messages.get(language).put("ZA", "���");
		messages.get(language).put("GS", "����� ������� � ����� ���������� �������");
		messages.get(language).put("JM", "������");
		messages.get(language).put("FM", "��");
		messages.get(language).put("JP", "������");

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
					+ "�with the laws of the Russian Federation, access to all data will be open to the FSB. The authors of the program are not"
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
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
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
					+ "���������� ������ �� ����� �� ��� ���������������, ����������� ������������ ��������� School Tester � ����� ��������� ������� �� ���"
					+ " ������� ������ �� �� ���� ����� �����, ������ ��������� �� ����� �������������� �� ��������� �������������� ��������� ������� ��.\n"
					+ "4.1 � ��������� �����������: "
					+ "4.1.1 ��������: C�������� ��������� ���������, �� ��������� �������������� ������ � ��������, ������� ���� �������."
					+ "4.1.2 �����: ��������� �����, ��������� �����, ������������ ���������� ���� �������, � ��� ����� �������� �����, ��������� �����,"
					+ " ���������� ����� �� ������������ � �������������� ��������������, ������������� ���������� ������."
					+ "4.1.3 �������: ���, ��� �� ���������" + "4.1.4 ������: ���, ��� �� ���������" + "4.1.5 ��: ���, ��� �� ���������"
					+ "4.1.6 �������: ���, ��� �� ���������" + "4.2 � ��������� �����������: "
					+ "4.2.1 ��������: ��������� ����������� ����������(�������� � ����������� ����������� ����� ���������), ��������� ��������� ��������"
					+ "(��������� �������� ����� ���������)."
					+ "4.2.2 �����: ��������� ����� � ��������� �����������(����� � ��������� ����������� ����� �������������), ��������� ����� ���������������� "
					+ "���-���� ���������� ��� ��������������(����� ���������������� ���-���� ���������� ��� �������������� ����� �������������), ����� ���������� "
					+ "����������� �����, �������� � ��(����� ���������� ����������� �����, �������� � �� ����� �������������), ����� ���������� ����������� ��� "
					+ "���-���� �����������(����� ���������� ����������� ��� ���-���� ����������� ����� �������������)."
					+ "4.2.3 �������: ���, ��� �� ���������" + "4.2.4 ������: ������� �������������� ������"
					+ "4.2.5 ��: �� � ��������� �����������(�� � ��������� ����������� ����� �������������), ��������� �� ���������������� "
					+ "���-���� ���������� ��� ��������������(�� ���������������� ���-���� ���������� ��� �������������� ����� �������������), �� ���������� "
					+ "����������� �����, �������� � ��(�� ���������� ����������� �����, �������� � �� ����� �������������), �� ���������� ����������� ��� "
					+ "���-���� �����������(�� ���������� ����������� ��� ���-���� ����������� ����� �������������)." + "4.2.6 �������: ������� �����������");
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "��������� ������������� ��� ������������ �������� � ������� ������ ��������� �������������, ������� ���������, � ����� ��������� � "
					+ "���������. � ��������� ���� ��������� ����� ���������: �������������, ������� � �������. � ��������� ���� ��������� ��������: \n"
					+ "������� ������ - ��������� �����, ����������� �����; \n������ ���������� - �������� �����, ����� ����������; \n����������� ������ - "
					+ "����� �������, ����� ��, ����� ������, ����� ������������; \n����������� ������ - ����� �������, ����� ��, ����� ������, ����� "
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
			messages.get(language).put("notInAccountMsg", welcome + "����������, ������� � �������, ����� ������ �������� � ����������.");
			messages.get(language).put("inTeacherAccountMsg", welcome + "�� ������� '" + messages.get(language).get("common") + "' ��������� ����� �������.\n"
					+ "�� ������� '" + messages.get(language).get("special") + "' ��������� ������� ��������� ������ �������.\n" + "�� ������� '" + messages
							.get(language).get("utils") + "' ��������� �������������� �������.");
			messages.get(language).put("inStudentAccountMsg", welcome + "�� ������� '" + messages.get(language).get("common") + "' ��������� ����� �������.\n"
					+ "�� ������� '" + messages.get(language).get("special") + "' ��������� ������� ��������� ������ ��������.\n" + "�� ������� '" + messages
							.get(language).get("utils") + "' ��������� �������������� �������.");
			messages.get(language).put("inAdministratorAccountMsg", welcome + "�� ������� '" + messages.get(language).get("common")
					+ "' ��������� ����� �������.\n" + "�� ������� '" + messages.get(language).get("special") + "' ��������� ������� ��������������.\n"
					+ "�� ������� '" + messages.get(language).get("utils") + "' ��������� �������������� �������.");

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
