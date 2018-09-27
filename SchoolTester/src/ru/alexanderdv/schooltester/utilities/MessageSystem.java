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
					"Программа на вашем компьютере не верифицирована! Чтобы верифицировать вашу программу свяжитесь с нами, вы можете сделать это на нашем сайте: "
							+ siteUrl);
			messages.get(language).put("notVerified",
					"Программа на вашем компьютере не верифицирована! Чтобы верифицировать вашу программу свяжитесь с нами, вы можете сделать это на нашем сайте: "
							+ siteUrl);
		}
	}
	void init4()
	
	{
		String language = "ru_ru";
		HashMap<String, String> map = messages.get(language);
		map.put("AU", "Австралия");
		map.put("AT", "Австрия");
		map.put("AZ", "Азербайджан");
		map.put("AL", "Албания");
		map.put("HW", "Алжир");
		map.put("VI", "Американские Виргинские острова");
		map.put("AS", "Американское Самоа");
		map.put("AI", "Ангилья");
		map.put("AO", "Ангола");
		map.put("AD", "Андорра");
		map.put("AQ", "Антарктида");
		map.put("AG", "Антигуа и Барбуда");
		map.put("AR", "Аргентина");
		map.put("AM", "Армения");
		map.put("AW", "Аруба");
		map.put("AF", "Афганистан");
		map.put("BS", "Багамские острова");
		map.put("BD", "Бангладеш");
		map.put("BB", "Барбадос");
		map.put("BH", "Бахрейн");
		map.put("BZ", "Белиз");
		map.put("BY", "Белоруссия");
		map.put("BE", "Бельгия");
		map.put("BJ", "Бенин");
		map.put("BM", "Бермуды");
		map.put("BG", "Болгария");
		map.put("BO", "Боливия");
		map.put("BQ", "Бонэйр, Синт-Эстатиус и Саба");
		map.put("BA", "Босния и Герцеговина");
		map.put("BW", "Ботсвана");
		map.put("BR", "Бразилия");
		map.put("IO", "Британская территория в Индийском океане");
		map.put("VG", "Британские Виргинские острова");
		map.put("BN", "Бруней");
		map.put("BV", "Буве");
		map.put("BF", "Буркина-Фасо");
		map.put("BI", "Бурунди");
		map.put("BT", "Бутан");
		map.put("VU", "Вануату");
		map.put("VA", "Ватикан");
		map.put("GB", "Великобритания");
		map.put("HU", "Венгрия");
		map.put("VE", "Венесуэла");
		map.put("TL", "Восточный Тимор (Тимор-Лешти)");
		map.put("VN", "Вьетнам");
		map.put("GA", "Габон");
		map.put("HT", "Гаити");
		map.put("GY", "Гайана");
		map.put("GM", "Гамбия");
		map.put("GH", "Гана");
		map.put("GP", "Гваделупа");
		map.put("GT", "Гватемала");
		map.put("GN", "Гвинея");
		map.put("GW", "Гвинея-Бисау");
		map.put("DE", "Германия");
		map.put("GG", "Гернси");
		map.put("GI", "Гибралтар");
		map.put("HN", "Гондурас");
		map.put("HK", "Гонконг");
		map.put("GD", "Гренада");
		map.put("GL", "Гренландия");
		map.put("GR", "Греция");
		map.put("GE", "Грузия");
		map.put("GU", "Гуам");
		map.put("DK", "Дания");
		map.put("CD", "Демократическая Республика Конго");
		map.put("JE", "Джерси");
		map.put("DJ", "Джибути");
		map.put("DM", "Доминика");
		map.put("DO", "Доминикана");
		map.put("EG", "Египет");
		map.put("ZM", "Замбия");
		map.put("EH", "Западная Сахара");
		map.put("ZW", "Зимбабве");
		map.put("IL", "Израиль");
		map.put("IN", "Индия, Гоа");
		map.put("ID", "Индонезия, Бали");
		map.put("JO", "Иордания");
		map.put("IQ", "Ирак");
		map.put("IR", "Иран");
		map.put("IE", "Ирландия");
		map.put("IS", "Исландия");
		map.put("ES", "Испания, Канары");
		map.put("IT", "Италия");
		map.put("YE", "Йемен");
		map.put("CV", "Кабо-Верде (Острова Зеленого Мыса)");
		map.put("KZ", "Казахстан");
		map.put("KY", "Каймановы острова");
		map.put("KH", "Камбоджа");
		map.put("CM", "Камерун");
		map.put("CA", "Канада");
		map.put("QA", "Катар");
		map.put("KE", "Кения");
		map.put("CY", "Кипр");
		map.put("KG", "Киргизия");
		map.put("KI", "Кирибати");
		map.put("CN", "Китай");
		map.put("KP", "КНДР");
		map.put("CC", "Кокосовые острова (Килинг)");
		map.put("CO", "Колумбия");
		map.put("KM", "Коморские острова");
		map.put("KR", "Корея");
		map.put("CR", "Коста-Рика");
		map.put("CI", "Кот-д’Ивуар (Берег Слоновой Кости)");
		map.put("CU", "Куба");
		map.put("KW", "Кувейт");
		map.put("CW", "Кюрасао");
		map.put("LA", "Лаос");
		map.put("LV", "Латвия");
		map.put("LS", "Лесото");
		map.put("LR", "Либерия");
		map.put("LB", "Ливан");
		map.put("LY", "Ливия");
		map.put("LT", "Литва");
		map.put("LI", "Лихтенштейн");
		map.put("LU", "Люксембург");
		map.put("MU", "Маврикий");
		map.put("MR", "Мавритания");
		map.put("MG", "Мадагаскар");
		map.put("YT", "Майотта");
		map.put("MO", "Макао (Аомынь)");
		map.put("MK", "Македония");
		map.put("MW", "Малави");
		map.put("MY", "Малайзия");
		map.put("ML", "Мали");
		map.put("MV", "Мальдивы");
		map.put("MT", "Мальта");
		map.put("MA", "Марокко");
		map.put("MQ", "Мартиника");
		map.put("MH", "Маршалловы острова");
		map.put("MX", "Мексика");
		map.put("MZ", "Мозамбик");
		map.put("MD", "Молдавия");
		map.put("MC", "Монако");
		map.put("MN", "Монголия");
		map.put("MS", "Монтсеррат");
		map.put("MM", "Мьянма (Бирма)");
		map.put("NA", "Намибия");
		map.put("NR", "Науру");
		map.put("NP", "Непал");
		map.put("NE", "Нигер");
		map.put("NG", "Нигерия");
		map.put("AN", "Нидерландские Антильские острова");
		map.put("NL", "Нидерланды (Голландия)");
		map.put("NI", "Никарагуа");
		map.put("NU", "Ниуэ");
		map.put("NZ", "Новая Зеландия");
		map.put("NC", "Новая Каледония");
		map.put("NO", "Норвегия");
		map.put("AE", "ОАЭ");
		map.put("OM", "Оман");
		map.put("NF", "Остров Норфолк");
		map.put("CX", "Остров Рождества");
		map.put("SH", "Остров Святой Елены");
		map.put("HM", "Остров Херд и острова Макдональд");
		map.put("CK", "Острова Кука");
		map.put("PN", "Острова Питкэрн");
		map.put("TC", "Острова Тёркс и Кайкос");
		map.put("WF", "Острова Уоллис и Футуна");
		map.put("PK", "Пакистан");
		map.put("PW", "Палау");
		map.put("PS", "Палестина");
		map.put("PA", "Панама");
		map.put("PG", "Папуа — Новая Гвинея");
		map.put("PY", "Парагвай");
		map.put("PE", "Перу");
		map.put("PL", "Польша");
		map.put("PT", "Португалия");
		map.put("PR", "Пуэрто-Рико");
		map.put("CG", "Республика Конго");
		map.put("RE", "Реюньон");
		map.put("RU", "Россия");
		map.put("RW", "Руанда");
		map.put("RO", "Румыния");
		map.put("SV", "Сальвадор");
		map.put("WS", "Самоа");
		map.put("SM", "Сан-Марино");
		map.put("ST", "Сан-Томе и Принсипи");
		map.put("SA", "Саудовская Аравия");
		map.put("SZ", "Свазиленд");
		map.put("MP", "Северные Марианские острова");
		map.put("SC", "Сейшелы");
		map.put("PM", "Сен-Пьер и Микелон");
		map.put("SN", "Сенегал");
		map.put("VC", "Сент-Винсент и Гренадины");
		map.put("KN", "Сент-Китс и Невис");
		map.put("LC", "Сент-Люсия");
		map.put("RS", "Сербия");
		map.put("SG", "Сингапур, Бинтан");
		map.put("SY", "Сирия");
		map.put("SK", "Словакия");
		map.put("SI", "Словения");
		map.put("SB", "Соломоновы острова");
		map.put("SO", "Сомали");
		map.put("SD", "Судан");
		map.put("SR", "Суринам");
		map.put("US", "США");
		map.put("SL", "Сьерра-Леоне");
		map.put("TJ", "Таджикистан");
		map.put("TH", "Таиланд");
		map.put("TW", "Тайвань");
		map.put("TZ", "Танзания");
		map.put("TG", "Того");
		map.put("TK", "Токелау");
		map.put("TO", "Тонга");
		map.put("TT", "Тринидад и Тобаго");
		map.put("TV", "Тувалу");
		map.put("TN", "Тунис");
		map.put("TM", "Туркмения");
		map.put("TR", "Турция");
		map.put("UG", "Уганда");
		map.put("UZ", "Узбекистан");
		map.put("UA", "Украина");
		map.put("UY", "Уругвай");
		map.put("FO", "Фарерские острова");
		map.put("FM", "Федеративные Штаты Микронезии");
		map.put("FJ", "Фиджи");
		map.put("PH", "Филиппины");
		map.put("FI", "Финляндия");
		map.put("FK", "Фолклендские (Мальвинские) острова");
		map.put("FR", "Франция");
		map.put("GF", "Французская Гвиана");
		map.put("PF", "Французская Полинезия (Таити)");
		map.put("TF", "Французские Южные и Антарктические Территории");
		map.put("HR", "Хорватия");
		map.put("CF", "Центральноафриканская Республика");
		map.put("TD", "Чад");
		map.put("ME", "Черногория");
		map.put("CZ", "Чехия");
		map.put("CL", "Чили, о. Пасхи");
		map.put("FM", "Чуук (Трук)");
		map.put("CH", "Швейцария");
		map.put("SE", "Швеция");
		map.put("SJ", "Шпицберген и Ян-Майен");
		map.put("LK", "Шри-Ланка (Цейлон)");
		map.put("EC", "Эквадор, Галапагосы");
		map.put("GQ", "Экваториальная Гвинея");
		map.put("ER", "Эритрея");
		map.put("EE", "Эстония");
		map.put("ET", "Эфиопия");
		map.put("ZA", "ЮАР");
		map.put("GS", "Южная Георгия и Южные Сандвичевы острова");
		map.put("JM", "Ямайка");
		map.put("FM", "Яп");
		map.put("JP", "Япония");

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
					+ "tests for tangible and intangible rewards, view test results.\n"
					+ " 4.1.3 Communication: everything that is not forbidden\n"
					+ "4.1.4 Evaluation: anything that is not prohibited\n"
					+ " 4.1.5 HW: everything that is not forbidden\n"
					+ " 4.1.6 Utilities: everything that is not forbidden\n" + "4.2 The program prohibits:\n"
					+ "4.2.1 Accounts: Incorrect information (Accounts with unauthorized information will be deleted), create fake accounts (Fake\n"
					+ "accounts will be deleted).\n"
					+ "4.2.2 Tests: Create tests with fake information (Tests with false information will be blocked), create tests that promote\n"
					+ "anything illegal or offensive (Tests that propagate anything illegal or offensive will be blocked), tests containing\n"
					+ "obscene words, pictures, etc. (Tests containing obscene words, pictures, etc. will be blocked), tests containing insults or\n"
					+ "something indecent (Tests containing insults or anything indecent will be blocked).\n"
					+ "4.2.3 Communication: everything that is not forbidden\n"
					+ " 4.2.4 Evaluation: to lay down unreasonable estimates\n"
					+ "4.2.5 HW: HW with false information (HW with false information will be blocked), create HW propaganda\n"
					+ "anything illegal or offensive (HW propagandizing anything illegal or offensive will be blocked), HW containing\n"
					+ "obscene words, pictures, etc. (HW containing obscene words, pictures, etc. will be blocked), HW containing insults or\n"
					+ "something indecent (HW containing insults or anything indecent will be blocked).\n"
					+ " 4.2.6 Utilities: No bans");
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "The program is designed to test students. The program consists of two parts - main and testing. Main part."
					+ "The main part is intended for tuning the testing part and viewing statistics by tests. Сверху есть панель настроек со вкладками \""
					+ getMsg("window", language) + "\", \"" + getMsg("settings", language) + "\", \""
					+ getMsg("help", language) + "\". Во вкладке \"" + getMsg("help", language) + "\" есть \""
					+ getMsg("privacyPolicy", language) + "\" и \"" + getMsg("usersManual", language)
					+ "\". Во вкладке \"" + getMsg("settings", language) + "\" есть вкладка \""
					+ getMsg("language", language)
					+ "\". Для переключения языка выберите нужный в данной вкладке. Во вкладке \""
					+ getMsg("window", language) + "\" можно переключить режим работы программы: выберите \""
					+ getMsg("testMode", language) + "\" или \"" + getMsg("statsMode", language)
					+ "\". В любом режиме работы программы под панелью настроек есть поле выбора \""
					+ getMsg("testFileName", language) + "\", поле выбора с полем ввода \"" + getMsg("class", language)
					+ "\", поле ввода \"" + getMsg("surname", language) + "\", поле ввода \"" + getMsg("name", language)
					+ "\" и поле ввода \"" + getMsg("secondName", language)
					+ "\". В режиме тестирования появляется также поле ввода \"" + getMsg("timeToTest", language)
					+ "\", кнопка \"" + getMsg("start", language) + "\", переключатель \"" + getMsg("none", language)
					+ "\", переключатель \"" + getMsg("indicateAnswerQuality", language)
					+ "\", зависящие от него флажок \"" + getMsg("indicateAnswersQuality", language) + "\" и флажок \""
					+ getMsg("showRightAnswer", language) + "\", переключатель \""
					+ getMsg("goToAllQuestions", language) + "\", флажок \"" + getMsg("skipBtn", language)
					+ "\", флажок \"" + getMsg("pause", language) + "\", зависящий от него флажок \""
					+ getMsg("pauseOnUnfocus", language) + "\", флажок \"" + getMsg("anticopy", language)
					+ "\", флажок \"" + getMsg("antiscreenshot", language) + "\", кнопка \""
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
					+ "4.1.3 Общение: все, что не запрещено" + "4.1.4 Оценки: все, что не запрещено"
					+ "4.1.5 ДЗ: все, что не запрещено" + "4.1.6 Утилиты: все, что не запрещено"
					+ "4.2 В программе запрещается: "
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
					+ "что-либо неприличное(ДЗ содержащие оскорбления или что-либо неприличное будут блокироваться)."
					+ "4.2.6 Утилиты: запреты отсутствуют");
			messages.get(language).put("usersmanualtext", getMsg("usersManual", language) + "\n"
					+ "Программа предназначена для тестирования учащихся с помощью тестов созданных пользователем, автором программы, а также найденных в "
					+ "интернете. В программе есть несколько видов аккаунтов: Администратор, Студент и Учитель. В программе есть несколько разделов: \n"
					+ "Главный раздел - Стартовая часть, Аккаунтовая часть; \nРаздел разработки - Тестовая часть, Часть разработки; \nСтудентский раздел - "
					+ "Часть общения, Часть ДЗ, Часть оценок, Часть тестирования; \nУчительский раздел - Часть общения, Часть ДЗ, Часть оценок, Часть "
					+ "управления тестированием; \nРаздел утилит - часть доп. материалов(Словари, электронные учебники и тд), часть вспомогательных программ("
					+ "Калькулятор, построитель графиков и тд).\n"
					+ "Часть управления тестированием предназначена для настройки тестирующей части и просмотра статистики по тестам. Сверху есть панель настроек "
					+ "со вкладками \"" + getMsg("window", language) + "\", \"" + getMsg("settings", language)
					+ "\", \"" + getMsg("help", language) + "\". Во вкладке \"" + getMsg("help", language)
					+ "\" есть \"" + getMsg("privacyPolicy", language) + "\" и \"" + getMsg("usersManual", language)
					+ "\". Во вкладке \"" + getMsg("settings", language) + "\" есть вкладка \""
					+ getMsg("language", language)
					+ "\". Для переключения языка выберите нужный в данной вкладке. Во вкладке \""
					+ getMsg("window", language) + "\" можно переключить режим работы программы: выберите \""
					+ getMsg("testMode", language) + "\" или \"" + getMsg("statsMode", language)
					+ "\". В любом режиме работы программы под панелью настроек есть поле выбора \""
					+ getMsg("testFileName", language) + "\", поле выбора с полем ввода \"" + getMsg("class", language)
					+ "\", поле ввода \"" + getMsg("surname", language) + "\", поле ввода \"" + getMsg("name", language)
					+ "\" и поле ввода \"" + getMsg("secondName", language)
					+ "\". В режиме тестирования появляется также поле ввода \"" + getMsg("timeToTest", language)
					+ "\", кнопка \"" + getMsg("start", language) + "\", переключатель \"" + getMsg("none", language)
					+ "\", переключатель \"" + getMsg("indicateAnswerQuality", language)
					+ "\", зависящие от него флажок \"" + getMsg("indicateAnswersQuality", language) + "\" и флажок \""
					+ getMsg("showRightAnswer", language) + "\", переключатель \""
					+ getMsg("goToAllQuestions", language) + "\", флажок \"" + getMsg("skipBtn", language)
					+ "\", флажок \"" + getMsg("pause", language) + "\", зависящий от него флажок \""
					+ getMsg("pauseOnUnfocus", language) + "\", флажок \"" + getMsg("anticopy", language)
					+ "\", флажок \"" + getMsg("antiscreenshot", language) + "\", кнопка \""
					+ getMsg("savePropsToDefault", language) + "\".");
			String welcome = "Добро пожаловать в программу SchoolTester!\n"
					+ "Если вы начинаете работать с программой, то вы принимаете политику конфенденциальности.\n";
			messages.get(language).put("notInAccountMsg",
					welcome + "Пожалуйста, войдите в аккаунт, чтобы начать работать с программой.");
			messages.get(language).put("inTeacherAccountMsg",
					welcome + "Во вкладке '" + messages.get(language).get("common") + "' находятся общие функции.\n"
							+ "Во вкладке '" + messages.get(language).get("special")
							+ "' находятся функции доступные только учителю.\n" + "Во вкладке '"
							+ messages.get(language).get("utils") + "' находятся дополнительные функции.");
			messages.get(language).put("inStudentAccountMsg",
					welcome + "Во вкладке '" + messages.get(language).get("common") + "' находятся общие функции.\n"
							+ "Во вкладке '" + messages.get(language).get("special")
							+ "' находятся функции доступные только студенту.\n" + "Во вкладке '"
							+ messages.get(language).get("utils") + "' находятся дополнительные функции.");
			messages.get(language).put("inAdministratorAccountMsg",
					welcome + "Во вкладке '" + messages.get(language).get("common") + "' находятся общие функции.\n"
							+ "Во вкладке '" + messages.get(language).get("special")
							+ "' находятся функции администратора.\n" + "Во вкладке '"
							+ messages.get(language).get("utils") + "' находятся дополнительные функции.");

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
