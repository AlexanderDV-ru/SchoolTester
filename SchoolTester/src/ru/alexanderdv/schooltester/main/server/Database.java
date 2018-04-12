package ru.alexanderdv.schooltester.main.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ru.alexanderdv.schooltester.utilities.Subject;
import ru.alexanderdv.schooltester.utilities.types.Account;
import ru.alexanderdv.schooltester.utilities.types.Account.AccountType;
import ru.alexanderdv.schooltester.utilities.types.Person.Rodstvennik;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class Database
{

	private static final String accountsTable = "ACCOUNTS", accountsFields = r0();
	private static final String accountsSELECT = "SELECT " + accountsFields + " FROM " + accountsTable + " ORDER BY _login";
	private static final String accountsSELECT_ONE = "SELECT " + accountsFields + " FROM " + accountsTable + " WHERE _login=?";
	private static final String accountsINSERT = "INSERT INTO " + accountsTable + " (" + accountsFields + ") VALUES (" + r() + ")";
	private static final String accountsUPDATE = "UPDATE " + accountsTable + " SET " + r2() + " WHERE _login=?";
	private static final String accountsDELETE = "DELETE FROM " + accountsTable + " WHERE _login=?";

	private static final String marketTable = "ACCOUNTS", marketFields = r0();
	private static final String marketSELECT = "SELECT " + marketFields + " FROM " + marketTable + " ORDER BY _login";
	private static final String marketSELECT_ONE = "SELECT " + marketFields + " FROM " + marketTable + " WHERE _login=?";
	private static final String marketINSERT = "INSERT INTO " + marketTable + " (" + marketFields + ") VALUES (" + r() + ")";
	private static final String marketUPDATE = "UPDATE " + marketTable + " SET " + r2() + " WHERE _login=?";
	private static final String marketDELETE = "DELETE FROM " + marketTable + " WHERE _login=?";
	private static Connection connection;

	private static String r0()
	{
		String s = "";
		s += "_active" + ", ";
		s += "_deleted" + ", ";
		s += "_surname" + ", ";
		s += "_name" + ", ";
		s += "_secondname" + ", ";
		s += "_phonenumbers" + ", ";
		s += "_emails" + ", ";
		s += "_gender" + ", ";
		s += "_age" + ", ";
		s += "_country" + ", ";
		s += "_region" + ", ";
		s += "_city" + ", ";
		s += "_school" + ", ";
		s += "_sites" + ", ";
		s += "_othersites" + ", ";
		s += "_othercontacts" + ", ";
		s += "_biografy" + ", ";
		s += "_mirovozrenie" + ", ";
		s += "_education" + ", ";
		s += "_carriere" + ", ";
		s += "_politica" + ", ";
		s += "_maininlife" + ", ";
		s += "_maininpeoples" + ", ";
		s += "_aboutsmoking" + ", ";
		s += "_aboutalhogol" + ", ";
		s += "_aboutnarcotics" + ", ";
		s += "_mainlanguages" + ", ";
		s += "_otherlanguages" + ", ";
		s += "_rcountry" + ", ";
		s += "_rregion" + ", ";
		s += "_rcity" + ", ";
		s += "_interests" + ", ";
		s += "_likemusic" + ", ";
		s += "_likefilms" + ", ";
		s += "_likeshows" + ", ";
		s += "_likeblogs" + ", ";
		s += "_likebooks" + ", ";
		s += "_likegames" + ", ";
		s += "_likecomputergames" + ", ";
		s += "_likepeoples" + ", ";
		s += "_vdohnovlenie" + ", ";
		s += "_quotes" + ", ";
		s += "_ideas" + ", ";
		s += "_othervsglyadi" + ", ";
		s += "_semeinoepolozhenie" + ", ";
		s += "_parents" + ", ";
		s += "_grandparents" + ", ";
		s += "_brothersandsisters" + ", ";
		s += "_childrens" + ", ";
		s += "_grandchildrens" + ", ";
		s += "_otherrodstvenniki" + ", ";
		s += "_suprugibivshie" + ", ";
		s += "_suprug" + ", ";
		s += "_subjects" + ", ";

		s += "_accounttype" + ", ";
		s += "_login" + ", ";
		s += "_passwordhash";
		return s;
	}

	private static String r()
	{
		String s = "";
		for (int i = 0; i < accountsFields.split(", ").length; i++)
			s += i != 0 ? ", ?" : "?";
		return s;
	}

	private static String r2()
	{
		String s = "";
		for (int i = 0; i < accountsFields.split(", ").length; i++)
			s += (i != 0 ? ", " : "") + accountsFields.split(", ")[i] + "=?";
		return s;
	}

	public static void addAccount(Connection con, Account account)
	{
		try (PreparedStatement pst = con.prepareStatement(accountsINSERT, new String[]
		{
				"_login"
		}))
		{
			setPst(pst, account, 1, false);
			pst.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void editAccount(Connection con, Account account)
	{
		try (PreparedStatement pst = con.prepareStatement(accountsUPDATE))
		{
			setPst(pst, account, 1, true);
			pst.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void setPst(PreparedStatement pst, Account account, int sl, boolean update) throws Exception
	{
		int i = sl;
		pst.setBoolean(i++, account.isActive());
		pst.setBoolean(i++, account.isDeleted());
		pst.setString(i++, account.getSurname());
		pst.setString(i++, account.getName());
		pst.setString(i++, account.getSecondName());
		pst.setString(i++, arrayToString(account.getPhoneNumbers()));
		pst.setString(i++, arrayToString(account.getEmails()));
		pst.setString(i++, account.getGender());
		pst.setString(i++, account.getAge());
		pst.setString(i++, account.getCountry());
		pst.setString(i++, account.getRegion());
		pst.setString(i++, account.getCity());
		pst.setString(i++, account.getSchool());
		pst.setString(i++, arrayToString(account.getPersonalSites()));
		pst.setString(i++, arrayToString(account.getOtherSites()));
		pst.setString(i++, arrayToString(account.getOtherContacts()));
		pst.setString(i++, account.getBiografy());
		pst.setString(i++, account.getWorldOutlook());
		pst.setString(i++, account.getEducation());
		pst.setString(i++, account.getCarriere());
		pst.setString(i++, account.getPoliticalViews());
		pst.setString(i++, account.getMainInLife());
		pst.setString(i++, account.getMainInPeople());
		pst.setString(i++, account.getAboutSmoking());
		pst.setString(i++, account.getAboutAlhogol());
		pst.setString(i++, account.getAboutNarcotics());
		pst.setString(i++, arrayToString(account.getMainLanguages()));
		pst.setString(i++, arrayToString(account.getOtherLanguages()));
		pst.setString(i++, account.getHomeCountry());
		pst.setString(i++, account.getHomeRegion());
		pst.setString(i++, account.getHomeCity());
		pst.setString(i++, account.getInterests());
		pst.setString(i++, account.getFavouriteMusic());
		pst.setString(i++, account.getFavouriteFilms());
		pst.setString(i++, account.getFavouriteShows());
		pst.setString(i++, account.getFavouriteBlogs());
		pst.setString(i++, account.getFavouriteBooks());
		pst.setString(i++, account.getFavouriteGames());
		pst.setString(i++, account.getFavouriteComputerGames());
		pst.setString(i++, account.getFavouritePeople());
		pst.setString(i++, account.getInspiration());
		pst.setString(i++, account.getFavouriteQuotes());
		pst.setString(i++, account.getIdeas());
		pst.setString(i++, account.getOtherViews());
		pst.setString(i++, account.getMaritalStatus());
		pst.setString(i++, arrayToString(account.getParents()));
		pst.setString(i++, arrayToString(account.getGrandParents()));
		pst.setString(i++, arrayToString(account.getSiblings()));
		pst.setString(i++, arrayToString(account.getChildren()));
		pst.setString(i++, arrayToString(account.getGrandChildren()));
		pst.setString(i++, arrayToString(account.getOtherRelatives()));
		pst.setString(i++, arrayToString(account.getExSpouses()));
		pst.setString(i++, account.getSpouse() != null ? account.getSpouse().toString() : null);
		pst.setString(i++, arrayToString(account.getSubjects()));

		pst.setString(i++, account.getAccountType().toString());
		pst.setString(i++, account.getLogin());
		pst.setInt(i++, account.getPassword().hashCode());
		if (update)
			pst.setString(i++, account.getLogin());
	}

	public static String arrayToString(ArrayList<?> list)
	{
		String s = "";
		if (list == null)
			return null;
		for (int i = 0; i < list.size(); i++)
			s += (i != 0 ? "\n" : "") + list.get(i).toString();
		return s;
	}

	public static ArrayList<String> stringToStringArray(String s)
	{
		ArrayList<String> list = new ArrayList<String>();
		if (s == null)
			return null;
		for (String s2 : s.split("\n"))
			list.add(s2);
		return list;
	}

	public static ArrayList<Rodstvennik> stringToRodstvennikArray(String s)
	{
		ArrayList<Rodstvennik> list = new ArrayList<Rodstvennik>();
		if (s == null)
			return null;
		for (String s2 : s.split("\n"))
			list.add(Rodstvennik.valueOf(s2));
		return list;
	}

	public static ArrayList<Subject> stringToSubjectArray(String s)
	{
		ArrayList<Subject> list = new ArrayList<Subject>();
		if (s == null)
			return null;
		for (String s2 : s.split("\n"))
			for (Subject sbj : Subject.values())
				if (sbj.name().equals(s2))
				{
					list.add(Subject.valueOf(s2));
					break;
				}
		return list;
	}

	private static Account fillAccount(Connection con, ResultSet rs) throws SQLException
	{
		Account account = new Account(AccountType.valueOf(rs.getString("_accounttype")), rs.getString("_login"));
		account._setPasswordHash(rs.getInt("_passwordhash"));
		account.setActive(rs.getBoolean("_Active"));
		account.setDeleted(rs.getBoolean("_Deleted"));
		account.setSurname(rs.getString("_Surname"));
		account.setName(rs.getString("_Name"));
		account.setSecondName(rs.getString("_Secondname"));
		account.getPhoneNumbers().addAll(stringToStringArray(rs.getString("_PhoneNumbers")));
		account.getEmails().addAll(stringToStringArray(rs.getString("_Emails")));
		account.setGender(rs.getString("_Gender"));
		account.setAge(rs.getString("_Age"));
		account.setCountry(rs.getString("_Country"));
		account.setRegion(rs.getString("_Region"));
		account.setCity(rs.getString("_City"));
		account.setSchool(rs.getString("_School"));
		account.getPersonalSites().addAll(stringToStringArray(rs.getString("_Sites")));
		account.getOtherSites().addAll(stringToStringArray(rs.getString("_OtherSites")));
		account.getOtherContacts().addAll(stringToStringArray(rs.getString("_OtherContacts")));
		account.setBiografy(rs.getString("_Biografy"));
		account.getMainLanguages().addAll(stringToStringArray(rs.getString("_MainLanguages")));
		account.getOtherLanguages().addAll(stringToStringArray(rs.getString("_OtherLanguages")));
		account.setHomeCountry(rs.getString("_rcountry"));
		account.setHomeRegion(rs.getString("_rregion"));
		account.setHomeCity(rs.getString("_rcity"));
		account.setEducation(rs.getString("_Education"));
		account.setCarriere(rs.getString("_Carriere"));
		account.setWorldOutlook(rs.getString("_Mirovozrenie"));
		account.setPoliticalViews(rs.getString("_Politica"));
		account.setMainInLife(rs.getString("_MainInLife"));
		account.setMainInPeople(rs.getString("_MainInPeoples"));
		account.setAboutSmoking(rs.getString("_AboutSmoking"));
		account.setAboutAlhogol(rs.getString("_AboutAlhogol"));
		account.setAboutNarcotics(rs.getString("_AboutNarcotics"));
		account.setInterests(rs.getString("_Interests"));
		account.setFavouriteMusic(rs.getString("_LikeMusic"));
		account.setFavouriteFilms(rs.getString("_LikeFilms"));
		account.setFavouriteShows(rs.getString("_LikeShows"));
		account.setFavouriteBlogs(rs.getString("_LikeBlogs"));
		account.setFavouriteBooks(rs.getString("_LikeBooks"));
		account.setFavouriteGames(rs.getString("_LikeGames"));
		account.setFavouriteComputerGames(rs.getString("_LikeComputerGames"));
		account.setFavouritePeople(rs.getString("_LikePeoples"));
		account.setInspiration(rs.getString("_Vdohnovlenie"));
		account.setFavouriteQuotes(rs.getString("_Quotes"));
		account.setIdeas(rs.getString("_Ideas"));
		account.setOtherViews(rs.getString("_OtherVsglyadi"));
		account.setMaritalStatus(rs.getString("_semeinoepolozhenie"));
		account.getParents().addAll(stringToRodstvennikArray(rs.getString("_Parents")));
		account.getGrandParents().addAll(stringToRodstvennikArray(rs.getString("_GrandParents")));
		account.getSiblings().addAll(stringToRodstvennikArray(rs.getString("_BrothersAndSisters")));
		account.getChildren().addAll(stringToRodstvennikArray(rs.getString("_Childrens")));
		account.getGrandChildren().addAll(stringToRodstvennikArray(rs.getString("_GrandChildrens")));
		account.getOtherRelatives().addAll(stringToRodstvennikArray(rs.getString("_OtherRodstvenniki")));
		account.getExSpouses().addAll(stringToRodstvennikArray(rs.getString("_SuprugiBivshie")));
		account.setSpouse(Rodstvennik.valueOf(rs.getString("_Suprug")));
		account.getSubjects().addAll(stringToSubjectArray(rs.getString("_Subjects")));

		return account;
	}

	public static void deleteAccount(Connection con, String login)
	{
		Account account = getAccount(con, login);
		account.setDeleted(true);
		editAccount(con, account);
	}

	public static void deleteAccountFromDatabase(Connection con, String login)
	{
		try (PreparedStatement pst = con.prepareStatement(accountsDELETE))
		{
			pst.setString(1, login);
			pst.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static Account getAccount(Connection con, String login)
	{
		Account account = null;
		try
		{
			PreparedStatement pst = con.prepareStatement(accountsSELECT_ONE);
			pst.setString(1, login);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				account = fillAccount(con, rs);
			rs.close();
			pst.close();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return account;
	}

	public static List<Account> findAccounts(Connection con)
	{
		List<Account> list = new LinkedList<>();
		try (PreparedStatement pst = con.prepareStatement(accountsSELECT); ResultSet rs = pst.executeQuery())
		{
			while (rs.next())
				list.add(fillAccount(con, rs));
			rs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

//****/*/*/********/
//
//	public static void addTest(Connection con, Test test)
//	{
//		try (PreparedStatement pst = con.prepareStatement(marketINSERT, new String[]
//		{
//				"_login"
//		}))
//		{
//			setPst(pst, test, 1, false);
//			pst.executeUpdate();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//	private static void setPstTest(PreparedStatement pst, Test test, int sl, boolean update) throws Exception
//	{
//		int i = sl;
//		pst.setBoolean(i++, account.isActive());
//		pst.setBoolean(i++, account.isDeleted());
//		pst.setString(i++, account.getSurname());
//		pst.setString(i++, account.getName());
//		pst.setString(i++, account.getSecondName());
//		pst.setString(i++, arrayToString(account.getPhoneNumbers()));
//		pst.setString(i++, arrayToString(account.getEmails()));
//		pst.setString(i++, account.getGender());
//		pst.setString(i++, account.getAge());
//		pst.setString(i++, account.getCountry());
//		pst.setString(i++, account.getRegion());
//		pst.setString(i++, account.getCity());
//		pst.setString(i++, account.getSchool());
//		pst.setString(i++, arrayToString(account.getPersonalSites()));
//		pst.setString(i++, arrayToString(account.getOtherSites()));
//		pst.setString(i++, arrayToString(account.getOtherContacts()));
//		pst.setString(i++, account.getBiografy());
//		pst.setString(i++, account.getWorldOutlook());
//		pst.setString(i++, account.getEducation());
//		pst.setString(i++, account.getCarriere());
//		pst.setString(i++, account.getPoliticalViews());
//		pst.setString(i++, account.getMainInLife());
//		pst.setString(i++, account.getMainInPeople());
//		pst.setString(i++, account.getAboutSmoking());
//		pst.setString(i++, account.getAboutAlhogol());
//		pst.setString(i++, account.getAboutNarcotics());
//		pst.setString(i++, arrayToString(account.getMainLanguages()));
//		pst.setString(i++, arrayToString(account.getOtherLanguages()));
//		pst.setString(i++, account.getHomeCountry());
//		pst.setString(i++, account.getHomeRegion());
//		pst.setString(i++, account.getHomeCity());
//		pst.setString(i++, account.getInterests());
//		pst.setString(i++, account.getFavouriteMusic());
//		pst.setString(i++, account.getFavouriteFilms());
//		pst.setString(i++, account.getFavouriteShows());
//		pst.setString(i++, account.getFavouriteBlogs());
//		pst.setString(i++, account.getFavouriteBooks());
//		pst.setString(i++, account.getFavouriteGames());
//		pst.setString(i++, account.getFavouriteComputerGames());
//		pst.setString(i++, account.getFavouritePeople());
//		pst.setString(i++, account.getInspiration());
//		pst.setString(i++, account.getFavouriteQuotes());
//		pst.setString(i++, account.getIdeas());
//		pst.setString(i++, account.getOtherViews());
//		pst.setString(i++, account.getMaritalStatus());
//		pst.setString(i++, arrayToString(account.getParents()));
//		pst.setString(i++, arrayToString(account.getGrandParents()));
//		pst.setString(i++, arrayToString(account.getSiblings()));
//		pst.setString(i++, arrayToString(account.getChildren()));
//		pst.setString(i++, arrayToString(account.getGrandChildren()));
//		pst.setString(i++, arrayToString(account.getOtherRelatives()));
//		pst.setString(i++, arrayToString(account.getExSpouses()));
//		pst.setString(i++, account.getSpouse() != null ? account.getSpouse().toString() : null);
//		pst.setString(i++, arrayToString(account.getSubjects()));
//
//		pst.setString(i++, account.getAccountType().toString());
//		pst.setString(i++, account.getLogin());
//		pst.setInt(i++, account.getPassword().hashCode());
//		if (update)
//			pst.setString(i++, account.getLogin());
//	}
//
//	private static Test fillTest(Connection con, ResultSet rs) throws SQLException
//	{
//		Test account = new Test(AccountType.valueOf(rs.getString("_accounttype")), rs.getString("_login"));
//		account._setPasswordHash(rs.getInt("_passwordhash"));
//		account.setActive(rs.getBoolean("_Active"));
//		account.setDeleted(rs.getBoolean("_Deleted"));
//		account.setSurname(rs.getString("_Surname"));
//		account.setName(rs.getString("_Name"));
//		account.setSecondName(rs.getString("_Secondname"));
//		account.getPhoneNumbers().addAll(stringToStringArray(rs.getString("_PhoneNumbers")));
//		account.getEmails().addAll(stringToStringArray(rs.getString("_Emails")));
//		account.setGender(rs.getString("_Gender"));
//		account.setAge(rs.getString("_Age"));
//		account.setCountry(rs.getString("_Country"));
//		account.setRegion(rs.getString("_Region"));
//		account.setCity(rs.getString("_City"));
//		account.setSchool(rs.getString("_School"));
//		account.getPersonalSites().addAll(stringToStringArray(rs.getString("_Sites")));
//		account.getOtherSites().addAll(stringToStringArray(rs.getString("_OtherSites")));
//		account.getOtherContacts().addAll(stringToStringArray(rs.getString("_OtherContacts")));
//		account.setBiografy(rs.getString("_Biografy"));
//		account.getMainLanguages().addAll(stringToStringArray(rs.getString("_MainLanguages")));
//		account.getOtherLanguages().addAll(stringToStringArray(rs.getString("_OtherLanguages")));
//		account.setHomeCountry(rs.getString("_rcountry"));
//		account.setHomeRegion(rs.getString("_rregion"));
//		account.setHomeCity(rs.getString("_rcity"));
//		account.setEducation(rs.getString("_Education"));
//		account.setCarriere(rs.getString("_Carriere"));
//		account.setWorldOutlook(rs.getString("_Mirovozrenie"));
//		account.setPoliticalViews(rs.getString("_Politica"));
//		account.setMainInLife(rs.getString("_MainInLife"));
//		account.setMainInPeople(rs.getString("_MainInPeoples"));
//		account.setAboutSmoking(rs.getString("_AboutSmoking"));
//		account.setAboutAlhogol(rs.getString("_AboutAlhogol"));
//		account.setAboutNarcotics(rs.getString("_AboutNarcotics"));
//		account.setInterests(rs.getString("_Interests"));
//		account.setFavouriteMusic(rs.getString("_LikeMusic"));
//		account.setFavouriteFilms(rs.getString("_LikeFilms"));
//		account.setFavouriteShows(rs.getString("_LikeShows"));
//		account.setFavouriteBlogs(rs.getString("_LikeBlogs"));
//		account.setFavouriteBooks(rs.getString("_LikeBooks"));
//		account.setFavouriteGames(rs.getString("_LikeGames"));
//		account.setFavouriteComputerGames(rs.getString("_LikeComputerGames"));
//		account.setFavouritePeople(rs.getString("_LikePeoples"));
//		account.setInspiration(rs.getString("_Vdohnovlenie"));
//		account.setFavouriteQuotes(rs.getString("_Quotes"));
//		account.setIdeas(rs.getString("_Ideas"));
//		account.setOtherViews(rs.getString("_OtherVsglyadi"));
//		account.setMaritalStatus(rs.getString("_semeinoepolozhenie"));
//		account.getParents().addAll(stringToRodstvennikArray(rs.getString("_Parents")));
//		account.getGrandParents().addAll(stringToRodstvennikArray(rs.getString("_GrandParents")));
//		account.getSiblings().addAll(stringToRodstvennikArray(rs.getString("_BrothersAndSisters")));
//		account.getChildren().addAll(stringToRodstvennikArray(rs.getString("_Childrens")));
//		account.getGrandChildren().addAll(stringToRodstvennikArray(rs.getString("_GrandChildrens")));
//		account.getOtherRelatives().addAll(stringToRodstvennikArray(rs.getString("_OtherRodstvenniki")));
//		account.getExSpouses().addAll(stringToRodstvennikArray(rs.getString("_SuprugiBivshie")));
//		account.setSpouse(Rodstvennik.valueOf(rs.getString("_Suprug")));
//		account.getSubjects().addAll(stringToSubjectArray(rs.getString("_Subjects")));
//
//		return account;
//	}
//
//	public static void deleteTest(Connection con, String login)
//	{
//		Test test = getTest(con, login);
//		test.setDeleted(true);
//		editAccount(con, test);
//	}
//
//	public static Test getTest(Connection con, String login)
//	{
//		Test test = null;
//		try
//		{
//			PreparedStatement pst = con.prepareStatement(marketSELECT_ONE);
//			pst.setString(1, login);
//			ResultSet rs = pst.executeQuery();
//			if (rs.next())
//				test = fillTest(con, rs);
//			rs.close();
//			pst.close();
//		}
//		catch (Exception e)
//		{
//
//			e.printStackTrace();
//		}
//		return test;
//	}
//
//	public static List<Test> findTests(Connection con)
//	{
//		List<Test> list = new LinkedList<>();
//		try (PreparedStatement pst = con.prepareStatement(marketSELECT); ResultSet rs = pst.executeQuery())
//		{
//			while (rs.next())
//				list.add(fillTest(con, rs));
//			rs.close();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return list;
//	}//****/*/*/*//

	public static Connection setConnection(String dbName, String login, String password)
	{
		if (connection == null)
			try
			{
				// Class.forName("org.postgresql.Driver");
				String url = "jdbc:postgresql://localhost:5432/" + dbName;
				connection = DriverManager.getConnection(url, login, password);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return connection;
	}
}
