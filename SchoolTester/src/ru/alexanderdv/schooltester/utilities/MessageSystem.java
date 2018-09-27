package ru.alexanderdv.schooltester.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import ru.alexanderdv.schooltester.utilities.questionvars.ArrangementAnswer;
import ru.alexanderdv.simpleutilities.MessageUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.6a
 */
public final class MessageSystem extends MessageUtils
{
	public MessageSystem(String language, String languageResourcesPackage)
	{
		super(language, languageResourcesPackage);
		init1();
		init2();
		init3();
		init4();
		init5();
	}

	public static final String siteUrl = "http://schooltester.ucoz.org/";
	// private String language;
	// private static final HashMap<String, HashMap<String, String>> messages = new
	// HashMap<String, HashMap<String, String>>();

	void init1()
	{

		System.out.println("Message system loading...");
		//messages.put("en_uk", new HashMap<String, String>());
		{
			String language = "en_uk";
			messages.get(language).put("verifyRequestSended",
					"Your computer not verified! To verify your computer communicate with us, you can do this in our site: "
							+ siteUrl);
			messages.get(language).put("notVerified",
					"Your computer not verified! To verify your computer communicate with us, you can do this in our site: "
							+ siteUrl);
		}
	}

	void init2()
	{
		String language = "en_uk";
		HashMap<String, String> map = messages.get(language);
		map.put("AU", "Australia");
		map.put("AT", "Austria");
		map.put("AZ", "Azerbaijan");
		map.put("AL", "Albania");
		map.put("HW", "Algeria");
		map.put("VI", "United States Virgin Islands");
		map.put("AS", "American Samoa");
		map.put("AI", "Anguilla");
		map.put("AO", "Angola");
		map.put("AD", "Andorra");
		map.put("AQ", "Antarctica");
		map.put("AG", "Antigua and Barbuda");
		map.put("AR", "Argentina");
		map.put("AM", "Armenia");
		map.put("AW", "Aruba");
		map.put("AF", "Afghanistan");
		map.put("BS", "Bahamas");
		map.put("BD", "Bangladesh");
		map.put("BB", "Barbados");
		map.put("BH", "Bahrain");
		map.put("BZ", "Belize");
		map.put("BY", "Belarus");
		map.put("BE", "Belgium");
		map.put("BJ", "Benin");
		map.put("BM", "Bermuda");
		map.put("BG", "Bulgaria");
		map.put("BO", "Bolivia");
		map.put("BQ", "Caribbean Netherlands");
		map.put("BA", "Bosnia and Herzegovina");
		map.put("BW", "Botswana");
		map.put("BR", "Brazil");
		map.put("IO", "British Indian Ocean Territory");
		map.put("VG", "British Virgin Islands");
		map.put("BN", "Brunei Darussalam");
		map.put("BV", "Bouvet Island");
		map.put("BF", "Burkina Faso");
		map.put("BI", "Burundi");
		map.put("BT", "Bhutan");
		map.put("VU", "Vanuatu");
		map.put("VA", "Vatican");
		map.put("GB", "Great Britain");
		map.put("HU", "Hungary");
		map.put("VE", "Venezuela");
		map.put("TL", "East Timor");
		map.put("VN", "Vietnam");
		map.put("GA", "Gabon");
		map.put("HT", "Haiti");
		map.put("GY", "Guyana");
		map.put("GM", "Gambia");
		map.put("GH", "Ghana");
		map.put("GP", "Guadeloupe");
		map.put("GT", "Guatemala");
		map.put("GN", "Guinea");
		map.put("GW", "Guinea-Bissau");
		map.put("DE", "Germany");
		map.put("GG", "Guernsey");
		map.put("GI", "Gibraltar");
		map.put("HN", "Honduras");
		map.put("HK", "Hong Kong");
		map.put("GD", "Grenada");
		map.put("GL", "Greenland");
		map.put("GR", "Greece");
		map.put("GE", "Georgia");
		map.put("GU", "Guam");
		map.put("DK", "Denmark");
		map.put("CD", "Democratic Republic of the Congo");
		map.put("JE", "Jersey");
		map.put("DJ", "Djibouti");
		map.put("DM", "Dominica");
		map.put("DO", "Dominican Republic");
		map.put("EG", "Egypt");
		map.put("ZM", "Zambia");
		map.put("EH", "Western Sahara");
		map.put("ZW", "Zimbabwe");
		map.put("IL", "Israel");
		map.put("IN", "India");
		map.put("ID", "Indonesia");
		map.put("JO", "Jordan");
		map.put("IQ", "Iraq");
		map.put("IR", "Iran");
		map.put("IE", "Ireland");
		map.put("IS", "Iceland");
		map.put("ES", "Spain");
		map.put("IT", "Italy");
		map.put("YE", "Yemen");
		map.put("CV", "Cape Verde");
		map.put("KZ", "Kazakhstan");
		map.put("KY", "Cayman Islands");
		map.put("KH", "Cambodia");
		map.put("CM", "Cameroon");
		map.put("CA", "Canada");
		map.put("QA", "Quatar");
		map.put("KE", "Kenya");
		map.put("CY", "Cyprus");
		map.put("KG", "Kyrgyzstan");
		map.put("KI", "Kiribati");
		map.put("CN", "China");
		map.put("KP", "North Korea");
		map.put("CC", "Cocos (Keeling) Islands");
		map.put("CO", "Colombia");
		map.put("KM", "Comoros");
		map.put("KR", "Korea");
		map.put("CR", "Costa Rica");
		map.put("CI", "Cote d'Ivoire (Ivory Coast)");
		map.put("CU", "Cuba");
		map.put("KW", "Kuwait");
		map.put("CW", "Curacao");
		map.put("LA", "Laos");
		map.put("LV", "Latvia");
		map.put("LS", "Lesotho");
		map.put("LR", "Liberia");
		map.put("LB", "Lebanon");
		map.put("LY", "Libya");
		map.put("LT", "Lithuania");
		map.put("LI", "Liechtenstein");
		map.put("LU", "Luxembourg");
		map.put("MU", "Mauritius");
		map.put("MR", "Mauritania");
		map.put("MG", "Madagascar");
		map.put("YT", "Mayotte");
		map.put("MO", "Macau");
		map.put("MK", "Macedonia");
		map.put("MW", "Malawi");
		map.put("MY", "Malaysia");
		map.put("ML", "Mali");
		map.put("MV", "Maldives");
		map.put("MT", "Malta");
		map.put("MA", "Morocco");
		map.put("MQ", "Martinique");
		map.put("MH", "Marshall Islands");
		map.put("MX", "Mexico");
		map.put("MZ", "Mozambique");
		map.put("MD", "Moldova");
		map.put("MC", "Monaco");
		map.put("MN", "Mongolia");
		map.put("MS", "Montserrat");
		map.put("MM", "Myanmar (Burma)");
		map.put("NA", "Namibia");
		map.put("NR", "Nauru");
		map.put("NP", "Nepal");
		map.put("NE", "Niger");
		map.put("NG", "Nigeria");
		map.put("AN", "Netherlands Antilles");
		map.put("NL", "Netherlands");
		map.put("NI", "Nicaragua");
		map.put("NU", "Niue");
		map.put("NZ", "New Zealand");
		map.put("NC", "New Caledonia");
		map.put("NO", "Norway");
		map.put("AE", "United Arab Emirates");
		map.put("OM", "Oman");
		map.put("NF", "Norfolk Island");
		map.put("CX", "Christmas Island");
		map.put("SH", "Saint Helena");
		map.put("HM", "Heard Island and McDonald Islands");
		map.put("CK", "Cook Islands");
		map.put("PN", "Pitcairn Islands");
		map.put("TC", "The Turks and Caicos Islands");
		map.put("WF", "Wallis and Futuna");
		map.put("PK", "Pakistan");
		map.put("PW", "Palau");
		map.put("PS", "Palestinian Territories");
		map.put("PA", "Panama");
		map.put("PG", "Papua New Guinea");
		map.put("PY", "Paraguay");
		map.put("PE", "Peru");
		map.put("PL", "Poland");
		map.put("PT", "Portugal");
		map.put("PR", "Puerto Rico");
		map.put("CG", "Republic of the Congo");
		map.put("RE", "Reunion");
		map.put("RU", "Russia");
		map.put("RW", "Rwanda");
		map.put("RO", "Romania");
		map.put("SV", "El Salvador");
		map.put("WS", "Samoa");
		map.put("SM", "San Marino");
		map.put("ST", "Sao Tome and Principe");
		map.put("SA", "Saudi Arabia");
		map.put("SZ", "Swaziland");
		map.put("MP", "Northern Mariana Islands");
		map.put("SC", "Seychelles");
		map.put("PM", "Saint-Pierre and Miquelon");
		map.put("SN", "Senegal");
		map.put("VC", "Saint Vincent and the Grenadines");
		map.put("KN", "Saint Kitts and Nevis");
		map.put("LC", "Saint Lucia");
		map.put("RS", "Serbia");
		map.put("SG", "Singapore");
		map.put("SY", "Syria");
		map.put("SK", "Slovakia");
		map.put("SI", "Slovenia");
		map.put("SB", "Solomon Islands");
		map.put("SO", "Somalia");
		map.put("SD", "Sudan");
		map.put("SR", "Suriname");
		map.put("US", "USA");
		map.put("SL", "Sierra Leone");
		map.put("TJ", "Tajikistan");
		map.put("TH", "Thailand");
		map.put("TW", "Taiwan (Taipei)");
		map.put("TZ", "Tanzania");
		map.put("TG", "Togo");
		map.put("TK", "Tokelau");
		map.put("TO", "Tonga");
		map.put("TT", "Trinidad and Tobago");
		map.put("TV", "Tuvalu");
		map.put("TN", "Tunisia");
		map.put("TM", "Turkmenistan");
		map.put("TR", "Turkey");
		map.put("UG", "Uganda");
		map.put("UZ", "Uzbekistan");
		map.put("UA", "Ukraine");
		map.put("UY", "Uruguay");
		map.put("FO", "Faroe Islands");
		map.put("FM", "Federated States of Micronesia");
		map.put("FJ", "Fiji");
		map.put("PH", "Philippines");
		map.put("FI", "Finland");
		map.put("FK", "Falkland Islands (Malvinas)");
		map.put("FR", "France");
		map.put("GF", "French Guiana");
		map.put("PF", "French Polynesia");
		map.put("TF", "French Southern Territories");
		map.put("HR", "Croatia");
		map.put("CF", "Central African Republic");
		map.put("TD", "Chad");
		map.put("ME", "Montenegro");
		map.put("CZ", "Czechia");
		map.put("CL", "Chile");
		map.put("FM", "Chuuk");
		map.put("CH", "Switzerland");
		map.put("SE", "Sweden");
		map.put("SJ", "Svalbard and Jan Mayen");
		map.put("LK", "Sri Lanka");
		map.put("EC", "Ecuador");
		map.put("GQ", "Equatorial Guinea");
		map.put("ER", "Eritrea");
		map.put("EE", "Estonia");
		map.put("ET", "Ethiopia");
		map.put("ZA", "Republic of South Africa");
		map.put("GS", "South Georgia and the South Sandwich Islands");
		map.put("JM", "Jamaica");
		map.put("FM", "Yap");
	}

	void init3()
	{
		//messages.put("ru_ru", new HashMap<String, String>());
		{
			String language = "ru_ru";
			messages.get(language).put("verifyRequestSended",
					"��������� �� ����� ���������� �� ��������������! ����� �������������� ���� ��������� ��������� � ����, �� ������ ������� ��� �� ����� �����: "
							+ siteUrl);
			messages.get(language).put("notVerified",
					"��������� �� ����� ���������� �� ��������������! ����� �������������� ���� ��������� ��������� � ����, �� ������ ������� ��� �� ����� �����: "
							+ siteUrl);
		}
	}
	void init4()
	
	{
		String language = "ru_ru";
		HashMap<String, String> map = messages.get(language);
		map.put("AU", "���������");
		map.put("AT", "�������");
		map.put("AZ", "�����������");
		map.put("AL", "�������");
		map.put("HW", "�����");
		map.put("VI", "������������ ���������� �������");
		map.put("AS", "������������ �����");
		map.put("AI", "�������");
		map.put("AO", "������");
		map.put("AD", "�������");
		map.put("AQ", "����������");
		map.put("AG", "������� � �������");
		map.put("AR", "���������");
		map.put("AM", "�������");
		map.put("AW", "�����");
		map.put("AF", "����������");
		map.put("BS", "��������� �������");
		map.put("BD", "���������");
		map.put("BB", "��������");
		map.put("BH", "�������");
		map.put("BZ", "�����");
		map.put("BY", "����������");
		map.put("BE", "�������");
		map.put("BJ", "�����");
		map.put("BM", "�������");
		map.put("BG", "��������");
		map.put("BO", "�������");
		map.put("BQ", "������, ����-�������� � ����");
		map.put("BA", "������ � �����������");
		map.put("BW", "��������");
		map.put("BR", "��������");
		map.put("IO", "���������� ���������� � ��������� ������");
		map.put("VG", "���������� ���������� �������");
		map.put("BN", "������");
		map.put("BV", "����");
		map.put("BF", "�������-����");
		map.put("BI", "�������");
		map.put("BT", "�����");
		map.put("VU", "�������");
		map.put("VA", "�������");
		map.put("GB", "��������������");
		map.put("HU", "�������");
		map.put("VE", "���������");
		map.put("TL", "��������� ����� (�����-�����)");
		map.put("VN", "�������");
		map.put("GA", "�����");
		map.put("HT", "�����");
		map.put("GY", "������");
		map.put("GM", "������");
		map.put("GH", "����");
		map.put("GP", "���������");
		map.put("GT", "���������");
		map.put("GN", "������");
		map.put("GW", "������-�����");
		map.put("DE", "��������");
		map.put("GG", "������");
		map.put("GI", "���������");
		map.put("HN", "��������");
		map.put("HK", "�������");
		map.put("GD", "�������");
		map.put("GL", "����������");
		map.put("GR", "������");
		map.put("GE", "������");
		map.put("GU", "����");
		map.put("DK", "�����");
		map.put("CD", "��������������� ���������� �����");
		map.put("JE", "������");
		map.put("DJ", "�������");
		map.put("DM", "��������");
		map.put("DO", "����������");
		map.put("EG", "������");
		map.put("ZM", "������");
		map.put("EH", "�������� ������");
		map.put("ZW", "��������");
		map.put("IL", "�������");
		map.put("IN", "�����, ���");
		map.put("ID", "���������, ����");
		map.put("JO", "��������");
		map.put("IQ", "����");
		map.put("IR", "����");
		map.put("IE", "��������");
		map.put("IS", "��������");
		map.put("ES", "�������, ������");
		map.put("IT", "������");
		map.put("YE", "�����");
		map.put("CV", "����-����� (������� �������� ����)");
		map.put("KZ", "���������");
		map.put("KY", "��������� �������");
		map.put("KH", "��������");
		map.put("CM", "�������");
		map.put("CA", "������");
		map.put("QA", "�����");
		map.put("KE", "�����");
		map.put("CY", "����");
		map.put("KG", "��������");
		map.put("KI", "��������");
		map.put("CN", "�����");
		map.put("KP", "����");
		map.put("CC", "��������� ������� (������)");
		map.put("CO", "��������");
		map.put("KM", "��������� �������");
		map.put("KR", "�����");
		map.put("CR", "�����-����");
		map.put("CI", "���-������ (����� �������� �����)");
		map.put("CU", "����");
		map.put("KW", "������");
		map.put("CW", "�������");
		map.put("LA", "����");
		map.put("LV", "������");
		map.put("LS", "������");
		map.put("LR", "�������");
		map.put("LB", "�����");
		map.put("LY", "�����");
		map.put("LT", "�����");
		map.put("LI", "�����������");
		map.put("LU", "����������");
		map.put("MU", "��������");
		map.put("MR", "����������");
		map.put("MG", "����������");
		map.put("YT", "�������");
		map.put("MO", "����� (������)");
		map.put("MK", "���������");
		map.put("MW", "������");
		map.put("MY", "��������");
		map.put("ML", "����");
		map.put("MV", "��������");
		map.put("MT", "������");
		map.put("MA", "�������");
		map.put("MQ", "���������");
		map.put("MH", "���������� �������");
		map.put("MX", "�������");
		map.put("MZ", "��������");
		map.put("MD", "��������");
		map.put("MC", "������");
		map.put("MN", "��������");
		map.put("MS", "����������");
		map.put("MM", "������ (�����)");
		map.put("NA", "�������");
		map.put("NR", "�����");
		map.put("NP", "�����");
		map.put("NE", "�����");
		map.put("NG", "�������");
		map.put("AN", "������������� ���������� �������");
		map.put("NL", "���������� (���������)");
		map.put("NI", "���������");
		map.put("NU", "����");
		map.put("NZ", "����� ��������");
		map.put("NC", "����� ���������");
		map.put("NO", "��������");
		map.put("AE", "���");
		map.put("OM", "����");
		map.put("NF", "������ �������");
		map.put("CX", "������ ���������");
		map.put("SH", "������ ������ �����");
		map.put("HM", "������ ���� � ������� ����������");
		map.put("CK", "������� ����");
		map.put("PN", "������� �������");
		map.put("TC", "������� Ҹ��� � ������");
		map.put("WF", "������� ������ � ������");
		map.put("PK", "��������");
		map.put("PW", "�����");
		map.put("PS", "���������");
		map.put("PA", "������");
		map.put("PG", "����� � ����� ������");
		map.put("PY", "��������");
		map.put("PE", "����");
		map.put("PL", "������");
		map.put("PT", "����������");
		map.put("PR", "������-����");
		map.put("CG", "���������� �����");
		map.put("RE", "�������");
		map.put("RU", "������");
		map.put("RW", "������");
		map.put("RO", "�������");
		map.put("SV", "���������");
		map.put("WS", "�����");
		map.put("SM", "���-������");
		map.put("ST", "���-���� � ��������");
		map.put("SA", "���������� ������");
		map.put("SZ", "���������");
		map.put("MP", "�������� ���������� �������");
		map.put("SC", "�������");
		map.put("PM", "���-���� � �������");
		map.put("SN", "�������");
		map.put("VC", "����-������� � ���������");
		map.put("KN", "����-���� � �����");
		map.put("LC", "����-�����");
		map.put("RS", "������");
		map.put("SG", "��������, ������");
		map.put("SY", "�����");
		map.put("SK", "��������");
		map.put("SI", "��������");
		map.put("SB", "���������� �������");
		map.put("SO", "������");
		map.put("SD", "�����");
		map.put("SR", "�������");
		map.put("US", "���");
		map.put("SL", "������-�����");
		map.put("TJ", "�����������");
		map.put("TH", "�������");
		map.put("TW", "�������");
		map.put("TZ", "��������");
		map.put("TG", "����");
		map.put("TK", "�������");
		map.put("TO", "�����");
		map.put("TT", "�������� � ������");
		map.put("TV", "������");
		map.put("TN", "�����");
		map.put("TM", "���������");
		map.put("TR", "������");
		map.put("UG", "������");
		map.put("UZ", "����������");
		map.put("UA", "�������");
		map.put("UY", "�������");
		map.put("FO", "��������� �������");
		map.put("FM", "������������ ����� ����������");
		map.put("FJ", "�����");
		map.put("PH", "���������");
		map.put("FI", "���������");
		map.put("FK", "������������ (�����������) �������");
		map.put("FR", "�������");
		map.put("GF", "����������� ������");
		map.put("PF", "����������� ��������� (�����)");
		map.put("TF", "����������� ����� � �������������� ����������");
		map.put("HR", "��������");
		map.put("CF", "��������������������� ����������");
		map.put("TD", "���");
		map.put("ME", "����������");
		map.put("CZ", "�����");
		map.put("CL", "����, �. �����");
		map.put("FM", "���� (����)");
		map.put("CH", "���������");
		map.put("SE", "������");
		map.put("SJ", "���������� � ��-�����");
		map.put("LK", "���-����� (������)");
		map.put("EC", "�������, ����������");
		map.put("GQ", "�������������� ������");
		map.put("ER", "�������");
		map.put("EE", "�������");
		map.put("ET", "�������");
		map.put("ZA", "���");
		map.put("GS", "����� ������� � ����� ���������� �������");
		map.put("JM", "������");
		map.put("FM", "��");
		map.put("JP", "������");

	}

	void init5()
	{
		// for (File lang : new
		// File(MessageSystem.class.getResource("/").getFile()).listFiles())
		// System.out.println(lang.getAbsolutePath());
		// for (File lang : new
		// File(MessageSystem.class.getResource("/").getFile()).listFiles(file ->
		// file.getName().endsWith(".lang") && file.isFile() && file
		// .getName().length() == 10 && Character.isLetter(file.getName().charAt(0)) &&
		// Character.isLetter(file.getName().charAt(1)) && file.getName()
		// .charAt(2) == '_' && Character.isLetter(file.getName().charAt(3)) &&
		// Character.isLetter(file.getName().charAt(4))))
		// {

		// char[] englishLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		// for (char c1 : englishLetters)
		// for (char c2 : englishLetters)
		// for (char c3 : englishLetters)
		// for (char c4 : englishLetters)
		// {
		// String language = c1 + "" + c2 + "_" + c3 + "" + c4;
		//
		// System.out.println("Message system .lang files loading...");
		//
		// ArrayList<String> languagesL = new ArrayList<String>();
		// String[] languages;
		// if (!new File("langs.list").exists())
		// {
		// char[] englishLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		// for (char c1 : englishLetters)
		// for (char c2 : englishLetters)
		// for (char c3 : englishLetters)
		// for (char c4 : englishLetters)
		// {
		// String language = c1 + "" + c2 + "_" + c3 + "" + c4;
		// if (MessageSystem.class.getResourceAsStream("/" + language + ".lang") !=
		// null)
		// languagesL.add(language);
		// }
		// SystemUtils.writeFile(new File("langs.list"),
		// String.join(System.lineSeparator(), languagesL), "UTF-8");
		// languages=languagesL.toArray(new String[0]);
		// }
		// else languages = SystemUtils.readFile(new File("langs.list"),
		// "UTF-8").replace("\r\n", "\n").replace("\r", "\n").split("\n");
		// for (String language : languages)
		// {
		// InputStream stream = MessageSystem.class.getResourceAsStream("/" + language +
		// ".lang");
		// if (stream == null)
		// continue;
		// try
		// {
		// BufferedReader br = new BufferedReader(new InputStreamReader(stream,
		// "UTF-8"));
		// if (!messages.containsKey(language))
		// messages.put(language, new HashMap<String, String>());
		// for (String l; (l = br.readLine()) != null;)
		// {
		// String[] splited = l.split("': '");
		// if (splited.length == 2 && splited[0].startsWith("'") &&
		// splited[1].endsWith("'") && !splited[0].endsWith("\\") &&
		// !splited[1].endsWith(
		// "\\'"))
		// {
		// String key = splited[0].substring(1), value = splited[1].substring(0,
		// splited[1].length() - 1);
		// if (!messages.get(language).containsKey(key))
		// messages.get(language).put(key, value);
		// }
		// }
		// }
		// catch (UnsupportedEncodingException e)
		// {
		// e.printStackTrace();
		// continue;
		// }
		// catch (IOException e)
		// {
		// e.printStackTrace();
		// continue;
		// }
		// }
//
//		for (String s : messages.keySet().toArray(new String[0]))
//		{
//			HashMap<String, String> l = new HashMap<String, String>();
//			for (String s2 : messages.get(s).keySet())
//				l.put(s2.toLowerCase(), messages.get(s).get(s2));
//			messages.remove(s);
//			messages.put(s.toLowerCase(), l);
//		}
		{
			String language = "en_uk";
			messages.get(language).put("privacypolicytext", getMsg("privacyPolicy", language) + "\n"
					+ "0.1 When using the program, you agree to these terms.\n"
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
					+ "tests for tangible and intangible rewards, view test results.\n"
					+ "�4.1.3 Communication: everything that is not forbidden\n"
					+ "4.1.4 Evaluation: anything that is not prohibited\n"
					+ "�4.1.5 HW: everything that is not forbidden\n"
					+ "�4.1.6 Utilities: everything that is not forbidden\n" + "4.2 The program prohibits:\n"
					+ "4.2.1 Accounts: Incorrect information (Accounts with unauthorized information will be deleted), create fake accounts (Fake\n"
					+ "accounts will be deleted).\n"
					+ "4.2.2 Tests: Create tests with fake information (Tests with false information will be blocked), create tests that promote\n"
					+ "anything illegal or offensive (Tests that propagate anything illegal or offensive will be blocked), tests containing\n"
					+ "obscene words, pictures, etc. (Tests containing obscene words, pictures, etc. will be blocked), tests containing insults or\n"
					+ "something indecent (Tests containing insults or anything indecent will be blocked).\n"
					+ "4.2.3 Communication: everything that is not forbidden\n"
					+ "�4.2.4 Evaluation: to lay down unreasonable estimates\n"
					+ "4.2.5 HW: HW with false information (HW with false information will be blocked), create HW propaganda\n"
					+ "anything illegal or offensive (HW propagandizing anything illegal or offensive will be blocked), HW containing\n"
					+ "obscene words, pictures, etc. (HW containing obscene words, pictures, etc. will be blocked), HW containing insults or\n"
					+ "something indecent (HW containing insults or anything indecent will be blocked).\n"
					+ "�4.2.6 Utilities: No bans");
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "The program is designed to test students. The program consists of two parts - main and testing. Main part."
					+ "The main part is intended for tuning the testing part and viewing statistics by tests. ������ ���� ������ �������� �� ��������� \""
					+ getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \""
					+ getMsg("help", language) + "\". �� ������� \"" + getMsg("help", language) + "\" ���� \""
					+ getMsg("privacyPolicy", language) + "\" � \"" + getMsg("usersManual", language)
					+ "\". �� ������� \"" + getMsg("settings", language) + "\" ���� ������� \""
					+ getMsg("language", language)
					+ "\". ��� ������������ ����� �������� ������ � ������ �������. �� ������� \""
					+ getMsg("window", language) + "\" ����� ����������� ����� ������ ���������: �������� \""
					+ getMsg("testMode", language) + "\" ��� \"" + getMsg("statsMode", language)
					+ "\". � ����� ������ ������ ��������� ��� ������� �������� ���� ���� ������ \""
					+ getMsg("testFileName", language) + "\", ���� ������ � ����� ����� \"" + getMsg("class", language)
					+ "\", ���� ����� \"" + getMsg("surname", language) + "\", ���� ����� \"" + getMsg("name", language)
					+ "\" � ���� ����� \"" + getMsg("secondName", language)
					+ "\". � ������ ������������ ���������� ����� ���� ����� \"" + getMsg("timeToTest", language)
					+ "\", ������ \"" + getMsg("start", language) + "\", ������������� \"" + getMsg("none", language)
					+ "\", ������������� \"" + getMsg("indicateAnswerQuality", language)
					+ "\", ��������� �� ���� ������ \"" + getMsg("indicateAnswersQuality", language) + "\" � ������ \""
					+ getMsg("showRightAnswer", language) + "\", ������������� \""
					+ getMsg("goToAllQuestions", language) + "\", ������ \"" + getMsg("skipBtn", language)
					+ "\", ������ \"" + getMsg("pause", language) + "\", ��������� �� ���� ������ \""
					+ getMsg("pauseOnUnfocus", language) + "\", ������ \"" + getMsg("anticopy", language)
					+ "\", ������ \"" + getMsg("antiscreenshot", language) + "\", ������ \""
					+ getMsg("savePropsToDefault", language) + "\".");
			String welcome = "Welcome to SchoolTester!\n"
					+ "If you start working with the program, you submit the privacy policy.\n";
			messages.get(language).put("notInAccountMsg",
					welcome + "Please, sign up or sign in, to start working with the program.");
			messages.get(language).put("inTeacherAccountMsg",
					welcome + "There are common functions in tab '" + messages.get(language).get("common") + "'.\n"
							+ "There are special for teacher functions in tab '" + messages.get(language).get("special")
							+ "'.\n" + "And there are utilities in tab '" + messages.get(language).get("utils")
							+ "'.\n");
			messages.get(language).put("inStudentAccountMsg",
					welcome + "There are common functions in tab '" + messages.get(language).get("common") + "'.\n"
							+ "There are special for student functions in tab '" + messages.get(language).get("special")
							+ "'.\n" + "And there are utilities in tab '" + messages.get(language).get("utils")
							+ "'.\n");
			messages.get(language).put("inAdministatorAccountMsg",
					welcome + "There are common functions in tab '" + messages.get(language).get("common") + "'.\n"
							+ "There are special for administrator functions in tab '"
							+ messages.get(language).get("special") + "'.\n" + "And there are utilities in tab '"
							+ messages.get(language).get("utils") + "'.\n");
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
					+ "4.1.3 �������: ���, ��� �� ���������" + "4.1.4 ������: ���, ��� �� ���������"
					+ "4.1.5 ��: ���, ��� �� ���������" + "4.1.6 �������: ���, ��� �� ���������"
					+ "4.2 � ��������� �����������: "
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
					+ "���-���� �����������(�� ���������� ����������� ��� ���-���� ����������� ����� �������������)."
					+ "4.2.6 �������: ������� �����������");
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "��������� ������������� ��� ������������ �������� � ������� ������ ��������� �������������, ������� ���������, � ����� ��������� � "
					+ "���������. � ��������� ���� ��������� ����� ���������: �������������, ������� � �������. � ��������� ���� ��������� ��������: \n"
					+ "������� ������ - ��������� �����, ����������� �����; \n������ ���������� - �������� �����, ����� ����������; \n����������� ������ - "
					+ "����� �������, ����� ��, ����� ������, ����� ������������; \n����������� ������ - ����� �������, ����� ��, ����� ������, ����� "
					+ "���������� �������������; \n������ ������ - ����� ���. ����������(�������, ����������� �������� � ��), ����� ��������������� ��������("
					+ "�����������, ����������� �������� � ��).\n"
					+ "����� ���������� ������������� ������������� ��� ��������� ����������� ����� � ��������� ���������� �� ������. ������ ���� ������ �������� "
					+ "�� ��������� \"" + getMsg("window", language) + "\", \"" + getMsg("settings", language)
					+ "\", \"" + getMsg("help", language) + "\". �� ������� \"" + getMsg("help", language)
					+ "\" ���� \"" + getMsg("privacyPolicy", language) + "\" � \"" + getMsg("usersManual", language)
					+ "\". �� ������� \"" + getMsg("settings", language) + "\" ���� ������� \""
					+ getMsg("language", language)
					+ "\". ��� ������������ ����� �������� ������ � ������ �������. �� ������� \""
					+ getMsg("window", language) + "\" ����� ����������� ����� ������ ���������: �������� \""
					+ getMsg("testMode", language) + "\" ��� \"" + getMsg("statsMode", language)
					+ "\". � ����� ������ ������ ��������� ��� ������� �������� ���� ���� ������ \""
					+ getMsg("testFileName", language) + "\", ���� ������ � ����� ����� \"" + getMsg("class", language)
					+ "\", ���� ����� \"" + getMsg("surname", language) + "\", ���� ����� \"" + getMsg("name", language)
					+ "\" � ���� ����� \"" + getMsg("secondName", language)
					+ "\". � ������ ������������ ���������� ����� ���� ����� \"" + getMsg("timeToTest", language)
					+ "\", ������ \"" + getMsg("start", language) + "\", ������������� \"" + getMsg("none", language)
					+ "\", ������������� \"" + getMsg("indicateAnswerQuality", language)
					+ "\", ��������� �� ���� ������ \"" + getMsg("indicateAnswersQuality", language) + "\" � ������ \""
					+ getMsg("showRightAnswer", language) + "\", ������������� \""
					+ getMsg("goToAllQuestions", language) + "\", ������ \"" + getMsg("skipBtn", language)
					+ "\", ������ \"" + getMsg("pause", language) + "\", ��������� �� ���� ������ \""
					+ getMsg("pauseOnUnfocus", language) + "\", ������ \"" + getMsg("anticopy", language)
					+ "\", ������ \"" + getMsg("antiscreenshot", language) + "\", ������ \""
					+ getMsg("savePropsToDefault", language) + "\".");
			String welcome = "����� ���������� � ��������� SchoolTester!\n"
					+ "���� �� ��������� �������� � ����������, �� �� ���������� �������� �������������������.\n";
			messages.get(language).put("notInAccountMsg",
					welcome + "����������, ������� � �������, ����� ������ �������� � ����������.");
			messages.get(language).put("inTeacherAccountMsg",
					welcome + "�� ������� '" + messages.get(language).get("common") + "' ��������� ����� �������.\n"
							+ "�� ������� '" + messages.get(language).get("special")
							+ "' ��������� ������� ��������� ������ �������.\n" + "�� ������� '"
							+ messages.get(language).get("utils") + "' ��������� �������������� �������.");
			messages.get(language).put("inStudentAccountMsg",
					welcome + "�� ������� '" + messages.get(language).get("common") + "' ��������� ����� �������.\n"
							+ "�� ������� '" + messages.get(language).get("special")
							+ "' ��������� ������� ��������� ������ ��������.\n" + "�� ������� '"
							+ messages.get(language).get("utils") + "' ��������� �������������� �������.");
			messages.get(language).put("inAdministratorAccountMsg",
					welcome + "�� ������� '" + messages.get(language).get("common") + "' ��������� ����� �������.\n"
							+ "�� ������� '" + messages.get(language).get("special")
							+ "' ��������� ������� ��������������.\n" + "�� ������� '"
							+ messages.get(language).get("utils") + "' ��������� �������������� �������.");

		}

		for (String s : messages.keySet().toArray(new String[0]))
		{
			HashMap<String, String> l = new HashMap<String, String>();
			for (String s2 : messages.get(s).keySet())
				l.put(s2.toLowerCase(), messages.get(s).get(s2));
			messages.remove(s);
			messages.put(s.toLowerCase(), l);
		}
		System.out.println("Message system loaded!");
	}
	//
	// public MessageSystem(String language)
	// {
	// this.language = language;
	// }
	//
	// public String getMsg(String key)
	// {
	// return getMsg(key, language);
	// }
	//
	// public static String getMsg(String key, String language)
	// {
	// key = key.toLowerCase();
	// language = language.toLowerCase();
	// if (!messages.containsKey(language))
	// throw new IllegalArgumentException("Language '" + language + "' could not
	// found!");
	// if (!messages.get(language).containsKey(key))
	// return key + "(null)";
	// return messages.get(language).get(key);
	// }
	//
	// /**
	// * @return the language
	// */
	// public String getLanguage()
	// {
	// return language;
	// }
	//
	// /**
	// * @param language
	// * the language to set
	// */
	// public void setLanguage(String language)
	// {
	// this.language = language;
	// }
	//
	// public static HashMap<String, HashMap<String, String>> getMessages()
	// {
	// return messages;
	// }

}
