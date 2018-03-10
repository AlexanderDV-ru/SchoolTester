package ru.alexanderdv.schooltester.utilities;

import java.util.HashMap;

@Deprecated
public class CountryGetter
{
	@SuppressWarnings("unused")
	private static String[] ruAndEnNamesArray = new String[]
	{
			"Австралия-Australia",
			"Австрия-Austria",
			"Азербайджан-Azerbaijan",
			"Албания-Albania",
			"Алжир-Algeria",
			"Американские Виргинские острова-United States Virgin Islands",
			"Американское Самоа-American Samoa",
			"Ангилья-Anguilla",
			"Ангола-Angola",
			"Андорра-Andorra",
			"Антарктида-Antarctica",
			"Антигуа и Барбуда-Antigua and Barbuda",
			"Аргентина-Argentina",
			"Армения-Armenia",
			"Аруба-Aruba",
			"Афганистан-Afghanistan",
			"Багамские острова-Bahamas",
			"Бангладеш-Bangladesh",
			"Барбадос-Barbados",
			"Бахрейн-Bahrain",
			"Белиз-Belize",
			"Белоруссия-Belarus",
			"Бельгия-Belgium",
			"Бенин-Benin",
			"Бермуды-Bermuda",
			"Болгария-Bulgaria",
			"Боливия-Bolivia",
			"Бонэйр, Синт-Эстатиус и Саба-Caribbean Netherlands",
			"Босния и Герцеговина-Bosnia and Herzegovina",
			"Ботсвана-Botswana",
			"Бразилия-Brazil",
			"Британская территория в Индийском океане /British Indian Ocean Territory",
			"Британские Виргинские острова-British Virgin Islands",
			"Бруней-Brunei Darussalam",
			"Буве-Bouvet Island",
			"Буркина-Фасо-Burkina Faso",
			"Бурунди-Burundi",
			"Бутан-Bhutan",
			"Вануату-Vanuatu",
			"Ватикан-Vatican",
			"Великобритания-Great Britain",
			"Венгрия-Hungary",
			"Венесуэла-Venezuela",
			"Восточный Тимор (Тимор-Лешти)-East Timor",
			"Вьетнам-Vietnam",
			"Габон-Gabon",
			"Гаити-Haiti",
			"Гайана-Guyana",
			"Гамбия-Gambia",
			"Гана-Ghana",
			"Гваделупа-Guadeloupe",
			"Гватемала-Guatemala",
			"Гвинея-Guinea",
			"Гвинея-Бисау-Guinea-Bissau",
			"Германия-Germany",
			"Гернси-Guernsey",
			"Гибралтар-Gibraltar",
			"Гондурас-Honduras",
			"Гонконг-Hong Kong",
			"Гренада-Grenada",
			"Гренландия-Greenland",
			"Греция-Greece",
			"Грузия-Georgia",
			"Гуам-Guam",
			"Дания-Denmark",
			"Демократическая Республика Конго-Democratic Republic of the Congo",
			"Джерси-Jersey",
			"Джибути-Djibouti",
			"Доминика-Dominica",
			"Доминикана-Dominican Republic",
			"Египет-Egypt",
			"Замбия-Zambia",
			"Западная Сахара-Western Sahara",
			"Зимбабве-Zimbabwe",
			"Израиль-Israel",
			"Индия, Гоа-India",
			"Индонезия, Бали-Indonesia",
			"Иордания-Jordan",
			"Ирак-Iraq",
			"Иран-Iran",
			"Ирландия-Ireland",
			"Исландия-Iceland",
			"Испания, Канары-Spain",
			"Италия-Italy",
			"Йемен-Yemen",
			"Кабо-Верде (Острова Зеленого Мыса)-Cape Verde",
			"Казахстан-Kazakhstan",
			"Каймановы острова-Cayman Islands",
			"Камбоджа-Cambodia",
			"Камерун-Cameroon",
			"Канада-Canada",
			"Катар-Quatar",
			"Кения-Kenya",
			"Кипр-Cyprus",
			"Киргизия-Kyrgyzstan",
			"Кирибати-Kiribati",
			"Китай-China",
			"КНДР-North Korea",
			"Кокосовые острова (Килинг)-Cocos (Keeling) Islands",
			"Колумбия-Colombia",
			"Коморские острова-Comoros",
			"Корея-Korea",
			"Коста-Рика-Costa Rica",
			"Кот-д’Ивуар (Берег Слоновой Кости)-Cote d'Ivoire (Ivory Coast)",
			"Куба-Cuba",
			"Кувейт-Kuwait",
			"Кюрасао-Curacao",
			"Лаос-Laos",
			"Латвия-Latvia",
			"Лесото-Lesotho",
			"Либерия-Liberia",
			"Ливан-Lebanon",
			"Ливия-Libya",
			"Литва-Lithuania",
			"Лихтенштейн-Liechtenstein",
			"Люксембург-Luxembourg",
			"Маврикий-Mauritius",
			"Мавритания-Mauritania",
			"Мадагаскар-Madagascar",
			"Майотта-Mayotte",
			"Макао (Аомынь)-Macau",
			"Македония-Macedonia",
			"Малави-Malawi",
			"Малайзия-Malaysia",
			"Мали-Mali",
			"Мальдивы-Maldives",
			"Мальта-Malta",
			"Марокко-Morocco",
			"Мартиника-Martinique",
			"Маршалловы острова-Marshall Islands",
			"Мексика-Mexico",
			"Мозамбик-Mozambique",
			"Молдавия-Moldova",
			"Монако-Monaco",
			"Монголия-Mongolia",
			"Монтсеррат-Montserrat",
			"Мьянма (Бирма)-Myanmar (Burma)",
			"Намибия-Namibia",
			"Науру-Nauru",
			"Непал-Nepal",
			"Нигер-Niger",
			"Нигерия-Nigeria",
			"Нидерландские Антильские острова-Netherlands Antilles",
			"Нидерланды (Голландия)-Netherlands",
			"Никарагуа-Nicaragua",
			"Ниуэ-Niue",
			"Новая Зеландия-New Zealand",
			"Новая Каледония-New Caledonia",
			"Норвегия-Norway",
			"ОАЭ-United Arab Emirates",
			"Оман-Oman",
			"Остров Норфолк-Norfolk Island",
			"Остров Рождества-Christmas Island",
			"Остров Святой Елены-Saint Helena",
			"Остров Херд и острова Макдональд-Heard Island and McDonald Islands",
			"Острова Кука-Cook Islands",
			"Острова Питкэрн-Pitcairn Islands",
			"Острова Тёркс и Кайкос-The Turks and Caicos Islands",
			"Острова Уоллис и Футуна-Wallis and Futuna",
			"Пакистан-Pakistan",
			"Палау-Palau",
			"Палестина-Palestinian Territories",
			"Панама-Panama",
			"Папуа — Новая Гвинея-Papua New Guinea",
			"Парагвай-Paraguay",
			"Перу-Peru",
			"Польша-Poland",
			"Португалия-Portugal",
			"Пуэрто-Рико-Puerto Rico",
			"Республика Конго-Republic of the Congo",
			"Реюньон-Reunion",
			"Россия-Russia",
			"Руанда-Rwanda",
			"Румыния-Romania",
			"Сальвадор-El Salvador",
			"Самоа-Samoa",
			"Сан-Марино-San Marino",
			"Сан-Томе и Принсипи-Sao Tome and Principe",
			"Саудовская Аравия-Saudi Arabia",
			"Свазиленд-Swaziland",
			"Северные Марианские острова-Northern Mariana Islands",
			"Сейшелы-Seychelles",
			"Сен-Пьер и Микелон-Saint-Pierre and Miquelon",
			"Сенегал-Senegal",
			"Сент-Винсент и Гренадины-Saint Vincent and the Grenadines",
			"Сент-Китс и Невис-Saint Kitts and Nevis",
			"Сент-Люсия-Saint Lucia",
			"Сербия-Serbia",
			"Сингапур, Бинтан-Singapore",
			"Сирия-Syria",
			"Словакия-Slovakia",
			"Словения-Slovenia",
			"Соломоновы острова-Solomon Islands",
			"Сомали-Somalia",
			"Судан-Sudan",
			"Суринам-Suriname",
			"США-USA",
			"Сьерра-Леоне-Sierra Leone",
			"Таджикистан-Tajikistan",
			"Таиланд-Thailand",
			"Тайвань-Taiwan (Taipei)",
			"Танзания-Tanzania",
			"Того-Togo",
			"Токелау-Tokelau",
			"Тонга-Tonga",
			"Тринидад и Тобаго-Trinidad and Tobago",
			"Тувалу-Tuvalu",
			"Тунис-Tunisia",
			"Туркмения-Turkmenistan",
			"Турция-Turkey",
			"Уганда-Uganda",
			"Узбекистан-Uzbekistan",
			"Украина-Ukraine",
			"Уругвай-Uruguay",
			"Фарерские острова-Faroe Islands",
			"Федеративные Штаты Микронезии-Federated States of Micronesia",
			"Фиджи-Fiji",
			"Филиппины-Philippines",
			"Финляндия-Finland",
			"Фолклендские (Мальвинские) острова-Falkland Islands (Malvinas)",
			"Франция-France",
			"Французская Гвиана-French Guiana",
			"Французская Полинезия (Таити)-French Polynesia",
			"Французские Южные и Антарктические Территории-French Southern Territories",
			"Хорватия-Croatia",
			"Центральноафриканская Республика-Central African Republic",
			"Чад-Chad",
			"Черногория-Montenegro",
			"Чехия-Czechia",
			"Чили, о. Пасхи-Chile",
			"Чуук (Трук)-Chuuk",
			"Швейцария-Switzerland",
			"Швеция-Sweden",
			"Шпицберген и Ян-Майен-Svalbard and Jan Mayen",
			"Шри-Ланка (Цейлон)-Sri Lanka",
			"Эквадор, Галапагосы-Ecuador",
			"Экваториальная Гвинея-Equatorial Guinea",
			"Эритрея-Eritrea",
			"Эстония-Estonia",
			"Эфиопия-Ethiopia",
			"ЮАР-Republic of South Africa",
			"Южная Георгия и Южные Сандвичевы острова-South Georgia and the South Sandwich Islands",
			"Ямайка-Jamaica",
			"Яп-Yap",
			"Япония-Japan"
	};
	@SuppressWarnings("unused")
	private static String[] letter2codesArray = new String[]
	{
			"AU",
			"AT",
			"AZ",
			"AL",
			"DZ",
			"VI",
			"AS",
			"AI",
			"AO",
			"AD",
			"AQ",
			"AG",
			"AR",
			"AM",
			"AW",
			"AF",
			"BS",
			"BD",
			"BB",
			"BH",
			"BZ",
			"BY",
			"BE",
			"BJ",
			"BM",
			"BG",
			"BO",
			"BQ",
			"BA",
			"BW",
			"BR",
			"IO",
			"VG",
			"BN",
			"BV",
			"BF",
			"BI",
			"BT",
			"VU",
			"VA",
			"GB",
			"HU",
			"VE",
			"TL",
			"VN",
			"GA",
			"HT",
			"GY",
			"GM",
			"GH",
			"GP",
			"GT",
			"GN",
			"GW",
			"DE",
			"GG",
			"GI",
			"HN",
			"HK",
			"GD",
			"GL",
			"GR",
			"GE",
			"GU",
			"DK",
			"CD",
			"JE",
			"DJ",
			"DM",
			"DO",
			"EG",
			"ZM",
			"EH",
			"ZW",
			"IL",
			"IN",
			"ID",
			"JO",
			"IQ",
			"IR",
			"IE",
			"IS",
			"ES",
			"IT",
			"YE",
			"CV",
			"KZ",
			"KY",
			"KH",
			"CM",
			"CA",
			"QA",
			"KE",
			"CY",
			"KG",
			"KI",
			"CN",
			"KP",
			"CC",
			"CO",
			"KM",
			"KR",
			"CR",
			"CI",
			"CU",
			"KW",
			"CW",
			"LA",
			"LV",
			"LS",
			"LR",
			"LB",
			"LY",
			"LT",
			"LI",
			"LU",
			"MU",
			"MR",
			"MG",
			"YT",
			"MO",
			"MK",
			"MW",
			"MY",
			"ML",
			"MV",
			"MT",
			"MA",
			"MQ",
			"MH",
			"MX",
			"MZ",
			"MD",
			"MC",
			"MN",
			"MS",
			"MM",
			"NA",
			"NR",
			"NP",
			"NE",
			"NG",
			"AN",
			"NL",
			"NI",
			"NU",
			"NZ",
			"NC",
			"NO",
			"AE",
			"OM",
			"NF",
			"CX",
			"SH",
			"HM",
			"CK",
			"PN",
			"TC",
			"WF",
			"PK",
			"PW",
			"PS",
			"PA",
			"PG",
			"PY",
			"PE",
			"PL",
			"PT",
			"PR",
			"CG",
			"RE",
			"RU",
			"RW",
			"RO",
			"SV",
			"WS",
			"SM",
			"ST",
			"SA",
			"SZ",
			"MP",
			"SC",
			"PM",
			"SN",
			"VC",
			"KN",
			"LC",
			"RS",
			"SG",
			"SY",
			"SK",
			"SI",
			"SB",
			"SO",
			"SD",
			"SR",
			"US",
			"SL",
			"TJ",
			"TH",
			"TW",
			"TZ",
			"TG",
			"TK",
			"TO",
			"TT",
			"TV",
			"TN",
			"TM",
			"TR",
			"UG",
			"UZ",
			"UA",
			"UY",
			"FO",
			"FM",
			"FJ",
			"PH",
			"FI",
			"FK",
			"FR",
			"GF",
			"PF",
			"TF",
			"HR",
			"CF",
			"TD",
			"ME",
			"CZ",
			"CL",
			"FM",
			"CH",
			"SE",
			"SJ",
			"LK",
			"EC",
			"GQ",
			"ER",
			"EE",
			"ET",
			"ZA",
			"GS",
			"JM",
			"FM",
			"JP"
	};
	private static HashMap<String, String> map = new HashMap<String, String>();
	static
	{
		map.put("AU", "Австралия\tAustralia");
		map.put("AT", "Австрия\tAustria");
		map.put("AZ", "Азербайджан\tAzerbaijan");
		map.put("AL", "Албания\tAlbania");
		map.put("DZ", "Алжир\tAlgeria");
		map.put("VI", "Американские Виргинские острова\tUnited States Virgin Islands");
		map.put("AS", "Американское Самоа\tAmerican Samoa");
		map.put("AI", "Ангилья\tAnguilla");
		map.put("AO", "Ангола\tAngola");
		map.put("AD", "Андорра\tAndorra");
		map.put("AQ", "Антарктида\tAntarctica");
		map.put("AG", "Антигуа и Барбуда\tAntigua and Barbuda");
		map.put("AR", "Аргентина\tArgentina");
		map.put("AM", "Армения\tArmenia");
		map.put("AW", "Аруба\tAruba");
		map.put("AF", "Афганистан\tAfghanistan");
		map.put("BS", "Багамские острова\tBahamas");
		map.put("BD", "Бангладеш\tBangladesh");
		map.put("BB", "Барбадос\tBarbados");
		map.put("BH", "Бахрейн\tBahrain");
		map.put("BZ", "Белиз\tBelize");
		map.put("BY", "Белоруссия\tBelarus");
		map.put("BE", "Бельгия\tBelgium");
		map.put("BJ", "Бенин\tBenin");
		map.put("BM", "Бермуды\tBermuda");
		map.put("BG", "Болгария\tBulgaria");
		map.put("BO", "Боливия\tBolivia");
		map.put("BQ", "Бонэйр, Синт-Эстатиус и Саба\tCaribbean Netherlands");
		map.put("BA", "Босния и Герцеговина\tBosnia and Herzegovina");
		map.put("BW", "Ботсвана\tBotswana");
		map.put("BR", "Бразилия\tBrazil");
		map.put("IO", "Британская территория в Индийском океане /British Indian Ocean Territory");
		map.put("VG", "Британские Виргинские острова\tBritish Virgin Islands");
		map.put("BN", "Бруней\tBrunei Darussalam");
		map.put("BV", "Буве\tBouvet Island");
		map.put("BF", "Буркина-Фасо\tBurkina Faso");
		map.put("BI", "Бурунди\tBurundi");
		map.put("BT", "Бутан\tBhutan");
		map.put("VU", "Вануату\tVanuatu");
		map.put("VA", "Ватикан\tVatican");
		map.put("GB", "Великобритания\tGreat Britain");
		map.put("HU", "Венгрия\tHungary");
		map.put("VE", "Венесуэла\tVenezuela");
		map.put("TL", "Восточный Тимор (Тимор-Лешти)\tEast Timor");
		map.put("VN", "Вьетнам\tVietnam");
		map.put("GA", "Габон\tGabon");
		map.put("HT", "Гаити\tHaiti");
		map.put("GY", "Гайана\tGuyana");
		map.put("GM", "Гамбия\tGambia");
		map.put("GH", "Гана\tGhana");
		map.put("GP", "Гваделупа\tGuadeloupe");
		map.put("GT", "Гватемала\tGuatemala");
		map.put("GN", "Гвинея\tGuinea");
		map.put("GW", "Гвинея-Бисау\tGuinea-Bissau");
		map.put("DE", "Германия\tGermany");
		map.put("GG", "Гернси\tGuernsey");
		map.put("GI", "Гибралтар\tGibraltar");
		map.put("HN", "Гондурас\tHonduras");
		map.put("HK", "Гонконг\tHong Kong");
		map.put("GD", "Гренада\tGrenada");
		map.put("GL", "Гренландия\tGreenland");
		map.put("GR", "Греция\tGreece");
		map.put("GE", "Грузия\tGeorgia");
		map.put("GU", "Гуам\tGuam");
		map.put("DK", "Дания\tDenmark");
		map.put("CD", "Демократическая Республика Конго\tDemocratic Republic of the Congo");
		map.put("JE", "Джерси\tJersey");
		map.put("DJ", "Джибути\tDjibouti");
		map.put("DM", "Доминика\tDominica");
		map.put("DO", "Доминикана\tDominican Republic");
		map.put("EG", "Египет\tEgypt");
		map.put("ZM", "Замбия\tZambia");
		map.put("EH", "Западная Сахара\tWestern Sahara");
		map.put("ZW", "Зимбабве\tZimbabwe");
		map.put("IL", "Израиль\tIsrael");
		map.put("IN", "Индия, Гоа\tIndia");
		map.put("ID", "Индонезия, Бали\tIndonesia");
		map.put("JO", "Иордания\tJordan");
		map.put("IQ", "Ирак\tIraq");
		map.put("IR", "Иран\tIran");
		map.put("IE", "Ирландия\tIreland");
		map.put("IS", "Исландия\tIceland");
		map.put("ES", "Испания, Канары\tSpain");
		map.put("IT", "Италия\tItaly");
		map.put("YE", "Йемен\tYemen");
		map.put("CV", "Кабо-Верде (Острова Зеленого Мыса)\tCape Verde");
		map.put("KZ", "Казахстан\tKazakhstan");
		map.put("KY", "Каймановы острова\tCayman Islands");
		map.put("KH", "Камбоджа\tCambodia");
		map.put("CM", "Камерун\tCameroon");
		map.put("CA", "Канада\tCanada");
		map.put("QA", "Катар\tQuatar");
		map.put("KE", "Кения\tKenya");
		map.put("CY", "Кипр\tCyprus");
		map.put("KG", "Киргизия\tKyrgyzstan");
		map.put("KI", "Кирибати\tKiribati");
		map.put("CN", "Китай\tChina");
		map.put("KP", "КНДР\tNorth Korea");
		map.put("CC", "Кокосовые острова (Килинг)\tCocos (Keeling) Islands");
		map.put("CO", "Колумбия\tColombia");
		map.put("KM", "Коморские острова\tComoros");
		map.put("KR", "Корея\tKorea");
		map.put("CR", "Коста-Рика\tCosta Rica");
		map.put("CI", "Кот-д’Ивуар (Берег Слоновой Кости)\tCote d'Ivoire (Ivory Coast)");
		map.put("CU", "Куба\tCuba");
		map.put("KW", "Кувейт\tKuwait");
		map.put("CW", "Кюрасао\tCuracao");
		map.put("LA", "Лаос\tLaos");
		map.put("LV", "Латвия\tLatvia");
		map.put("LS", "Лесото\tLesotho");
		map.put("LR", "Либерия\tLiberia");
		map.put("LB", "Ливан\tLebanon");
		map.put("LY", "Ливия\tLibya");
		map.put("LT", "Литва\tLithuania");
		map.put("LI", "Лихтенштейн\tLiechtenstein");
		map.put("LU", "Люксембург\tLuxembourg");
		map.put("MU", "Маврикий\tMauritius");
		map.put("MR", "Мавритания\tMauritania");
		map.put("MG", "Мадагаскар\tMadagascar");
		map.put("YT", "Майотта\tMayotte");
		map.put("MO", "Макао (Аомынь)\tMacau");
		map.put("MK", "Македония\tMacedonia");
		map.put("MW", "Малави\tMalawi");
		map.put("MY", "Малайзия\tMalaysia");
		map.put("ML", "Мали\tMali");
		map.put("MV", "Мальдивы\tMaldives");
		map.put("MT", "Мальта\tMalta");
		map.put("MA", "Марокко\tMorocco");
		map.put("MQ", "Мартиника\tMartinique");
		map.put("MH", "Маршалловы острова\tMarshall Islands");
		map.put("MX", "Мексика\tMexico");
		map.put("MZ", "Мозамбик\tMozambique");
		map.put("MD", "Молдавия\tMoldova");
		map.put("MC", "Монако\tMonaco");
		map.put("MN", "Монголия\tMongolia");
		map.put("MS", "Монтсеррат\tMontserrat");
		map.put("MM", "Мьянма (Бирма)\tMyanmar (Burma)");
		map.put("NA", "Намибия\tNamibia");
		map.put("NR", "Науру\tNauru");
		map.put("NP", "Непал\tNepal");
		map.put("NE", "Нигер\tNiger");
		map.put("NG", "Нигерия\tNigeria");
		map.put("AN", "Нидерландские Антильские острова\tNetherlands Antilles");
		map.put("NL", "Нидерланды (Голландия)\tNetherlands");
		map.put("NI", "Никарагуа\tNicaragua");
		map.put("NU", "Ниуэ\tNiue");
		map.put("NZ", "Новая Зеландия\tNew Zealand");
		map.put("NC", "Новая Каледония\tNew Caledonia");
		map.put("NO", "Норвегия\tNorway");
		map.put("AE", "ОАЭ\tUnited Arab Emirates");
		map.put("OM", "Оман\tOman");
		map.put("NF", "Остров Норфолк\tNorfolk Island");
		map.put("CX", "Остров Рождества\tChristmas Island");
		map.put("SH", "Остров Святой Елены\tSaint Helena");
		map.put("HM", "Остров Херд и острова Макдональд\tHeard Island and McDonald Islands");
		map.put("CK", "Острова Кука\tCook Islands");
		map.put("PN", "Острова Питкэрн\tPitcairn Islands");
		map.put("TC", "Острова Тёркс и Кайкос\tThe Turks and Caicos Islands");
		map.put("WF", "Острова Уоллис и Футуна\tWallis and Futuna");
		map.put("PK", "Пакистан\tPakistan");
		map.put("PW", "Палау\tPalau");
		map.put("PS", "Палестина\tPalestinian Territories");
		map.put("PA", "Панама\tPanama");
		map.put("PG", "Папуа — Новая Гвинея\tPapua New Guinea");
		map.put("PY", "Парагвай\tParaguay");
		map.put("PE", "Перу\tPeru");
		map.put("PL", "Польша\tPoland");
		map.put("PT", "Португалия\tPortugal");
		map.put("PR", "Пуэрто-Рико\tPuerto Rico");
		map.put("CG", "Республика Конго\tRepublic of the Congo");
		map.put("RE", "Реюньон\tReunion");
		map.put("RU", "Россия\tRussia");
		map.put("RW", "Руанда\tRwanda");
		map.put("RO", "Румыния\tRomania");
		map.put("SV", "Сальвадор\tEl Salvador");
		map.put("WS", "Самоа\tSamoa");
		map.put("SM", "Сан-Марино\tSan Marino");
		map.put("ST", "Сан-Томе и Принсипи\tSao Tome and Principe");
		map.put("SA", "Саудовская Аравия\tSaudi Arabia");
		map.put("SZ", "Свазиленд\tSwaziland");
		map.put("MP", "Северные Марианские острова\tNorthern Mariana Islands");
		map.put("SC", "Сейшелы\tSeychelles");
		map.put("PM", "Сен-Пьер и Микелон\tSaint-Pierre and Miquelon");
		map.put("SN", "Сенегал\tSenegal");
		map.put("VC", "Сент-Винсент и Гренадины\tSaint Vincent and the Grenadines");
		map.put("KN", "Сент-Китс и Невис\tSaint Kitts and Nevis");
		map.put("LC", "Сент-Люсия\tSaint Lucia");
		map.put("RS", "Сербия\tSerbia");
		map.put("SG", "Сингапур, Бинтан\tSingapore");
		map.put("SY", "Сирия\tSyria");
		map.put("SK", "Словакия\tSlovakia");
		map.put("SI", "Словения\tSlovenia");
		map.put("SB", "Соломоновы острова\tSolomon Islands");
		map.put("SO", "Сомали\tSomalia");
		map.put("SD", "Судан\tSudan");
		map.put("SR", "Суринам\tSuriname");
		map.put("US", "США\tUSA");
		map.put("SL", "Сьерра-Леоне\tSierra Leone");
		map.put("TJ", "Таджикистан\tTajikistan");
		map.put("TH", "Таиланд\tThailand");
		map.put("TW", "Тайвань\tTaiwan (Taipei)");
		map.put("TZ", "Танзания\tTanzania");
		map.put("TG", "Того\tTogo");
		map.put("TK", "Токелау\tTokelau");
		map.put("TO", "Тонга\tTonga");
		map.put("TT", "Тринидад и Тобаго\tTrinidad and Tobago");
		map.put("TV", "Тувалу\tTuvalu");
		map.put("TN", "Тунис\tTunisia");
		map.put("TM", "Туркмения\tTurkmenistan");
		map.put("TR", "Турция\tTurkey");
		map.put("UG", "Уганда\tUganda");
		map.put("UZ", "Узбекистан\tUzbekistan");
		map.put("UA", "Украина\tUkraine");
		map.put("UY", "Уругвай\tUruguay");
		map.put("FO", "Фарерские острова\tFaroe Islands");
		map.put("FM", "Федеративные Штаты Микронезии\tFederated States of Micronesia");
		map.put("FJ", "Фиджи\tFiji");
		map.put("PH", "Филиппины\tPhilippines");
		map.put("FI", "Финляндия\tFinland");
		map.put("FK", "Фолклендские (Мальвинские) острова\tFalkland Islands (Malvinas)");
		map.put("FR", "Франция\tFrance");
		map.put("GF", "Французская Гвиана\tFrench Guiana");
		map.put("PF", "Французская Полинезия (Таити)\tFrench Polynesia");
		map.put("TF", "Французские Южные и Антарктические Территории\tFrench Southern Territories");
		map.put("HR", "Хорватия\tCroatia");
		map.put("CF", "Центральноафриканская Республика\tCentral African Republic");
		map.put("TD", "Чад\tChad");
		map.put("ME", "Черногория\tMontenegro");
		map.put("CZ", "Чехия\tCzechia");
		map.put("CL", "Чили, о. Пасхи\tChile");
		map.put("FM", "Чуук (Трук)\tChuuk");
		map.put("CH", "Швейцария\tSwitzerland");
		map.put("SE", "Швеция\tSweden");
		map.put("SJ", "Шпицберген и Ян-Майен\tSvalbard and Jan Mayen");
		map.put("LK", "Шри-Ланка (Цейлон)\tSri Lanka");
		map.put("EC", "Эквадор, Галапагосы\tEcuador");
		map.put("GQ", "Экваториальная Гвинея\tEquatorial Guinea");
		map.put("ER", "Эритрея\tEritrea");
		map.put("EE", "Эстония\tEstonia");
		map.put("ET", "Эфиопия\tEthiopia");
		map.put("ZA", "ЮАР\tRepublic of South Africa");
		map.put("GS", "Южная Георгия и Южные Сандвичевы острова\tSouth Georgia and the South Sandwich Islands");
		map.put("JM", "Ямайка\tJamaica");
		map.put("FM", "Яп\tYap");
		map.put("JP", "Япония\tJapan");

	}
	static
	{
		map.put("AU", "Австралия");
		map.put("AT", "Австрия");
		map.put("AZ", "Азербайджан");
		map.put("AL", "Албания");
		map.put("DZ", "Алжир");
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
		map.put("IO", "Британская территория в Индийском океане /British Indian Ocean Territory");
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
	static
	{
		map.put("AU", "Australia");
		map.put("AT", "Austria");
		map.put("AZ", "Azerbaijan");
		map.put("AL", "Albania");
		map.put("DZ", "Algeria");
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
		map.put("IO", "Британская территория в Индийском океане /British Indian Ocean Territory");
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

	public static String getCountryByCode(String code, String language)
	{
		// ArrayList<String> ruAndEnNames = new ArrayList<String>();
		// ArrayList<String> letter2codes = new ArrayList<String>();
		// for (int i = 0; i < letter2codesArray.length; i++)
		// {
		// ruAndEnNames.add(ruAndEnNamesArray[i]);
		// letter2codes.add(letter2codesArray[i]);
		// }
		if (code.length() == 2)
		{
			// int pos = letter2codes.indexOf(code);
			// for (int i = 0; i < letter2codesArray.length; i++)
			// if (letter2codesArray[i].equals(code))
			// pos = i;
			// if (pos == -1)
			// new IndexOutOfBoundsException("\"" + code + "\" must be country code!");
			switch (language)
			{
				case "ru":
					return map.get(code).split("\t")[0];
				case "en":
					return map.get(code).split("\t")[1];
				default:
					return map.get(code);
			}
		}
		else throw new StringIndexOutOfBoundsException("\"" + code + "\" must be 2 symbols length!");
	}

	public static String safetyGetCountryByCode(String code, String language)
	{
		try
		{
			return getCountryByCode(code, language);
		}
		catch (IndexOutOfBoundsException e)
		{
			return null;
		}

	}
}