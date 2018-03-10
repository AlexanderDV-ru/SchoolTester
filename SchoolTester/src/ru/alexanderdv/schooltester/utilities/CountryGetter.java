package ru.alexanderdv.schooltester.utilities;

import java.util.HashMap;

@Deprecated
public class CountryGetter
{
	@SuppressWarnings("unused")
	private static String[] ruAndEnNamesArray = new String[]
	{
			"���������-Australia",
			"�������-Austria",
			"�����������-Azerbaijan",
			"�������-Albania",
			"�����-Algeria",
			"������������ ���������� �������-United States Virgin Islands",
			"������������ �����-American Samoa",
			"�������-Anguilla",
			"������-Angola",
			"�������-Andorra",
			"����������-Antarctica",
			"������� � �������-Antigua and Barbuda",
			"���������-Argentina",
			"�������-Armenia",
			"�����-Aruba",
			"����������-Afghanistan",
			"��������� �������-Bahamas",
			"���������-Bangladesh",
			"��������-Barbados",
			"�������-Bahrain",
			"�����-Belize",
			"����������-Belarus",
			"�������-Belgium",
			"�����-Benin",
			"�������-Bermuda",
			"��������-Bulgaria",
			"�������-Bolivia",
			"������, ����-�������� � ����-Caribbean Netherlands",
			"������ � �����������-Bosnia and Herzegovina",
			"��������-Botswana",
			"��������-Brazil",
			"���������� ���������� � ��������� ������ /British Indian Ocean Territory",
			"���������� ���������� �������-British Virgin Islands",
			"������-Brunei Darussalam",
			"����-Bouvet Island",
			"�������-����-Burkina Faso",
			"�������-Burundi",
			"�����-Bhutan",
			"�������-Vanuatu",
			"�������-Vatican",
			"��������������-Great Britain",
			"�������-Hungary",
			"���������-Venezuela",
			"��������� ����� (�����-�����)-East Timor",
			"�������-Vietnam",
			"�����-Gabon",
			"�����-Haiti",
			"������-Guyana",
			"������-Gambia",
			"����-Ghana",
			"���������-Guadeloupe",
			"���������-Guatemala",
			"������-Guinea",
			"������-�����-Guinea-Bissau",
			"��������-Germany",
			"������-Guernsey",
			"���������-Gibraltar",
			"��������-Honduras",
			"�������-Hong Kong",
			"�������-Grenada",
			"����������-Greenland",
			"������-Greece",
			"������-Georgia",
			"����-Guam",
			"�����-Denmark",
			"��������������� ���������� �����-Democratic Republic of the Congo",
			"������-Jersey",
			"�������-Djibouti",
			"��������-Dominica",
			"����������-Dominican Republic",
			"������-Egypt",
			"������-Zambia",
			"�������� ������-Western Sahara",
			"��������-Zimbabwe",
			"�������-Israel",
			"�����, ���-India",
			"���������, ����-Indonesia",
			"��������-Jordan",
			"����-Iraq",
			"����-Iran",
			"��������-Ireland",
			"��������-Iceland",
			"�������, ������-Spain",
			"������-Italy",
			"�����-Yemen",
			"����-����� (������� �������� ����)-Cape Verde",
			"���������-Kazakhstan",
			"��������� �������-Cayman Islands",
			"��������-Cambodia",
			"�������-Cameroon",
			"������-Canada",
			"�����-Quatar",
			"�����-Kenya",
			"����-Cyprus",
			"��������-Kyrgyzstan",
			"��������-Kiribati",
			"�����-China",
			"����-North Korea",
			"��������� ������� (������)-Cocos (Keeling) Islands",
			"��������-Colombia",
			"��������� �������-Comoros",
			"�����-Korea",
			"�����-����-Costa Rica",
			"���-������ (����� �������� �����)-Cote d'Ivoire (Ivory Coast)",
			"����-Cuba",
			"������-Kuwait",
			"�������-Curacao",
			"����-Laos",
			"������-Latvia",
			"������-Lesotho",
			"�������-Liberia",
			"�����-Lebanon",
			"�����-Libya",
			"�����-Lithuania",
			"�����������-Liechtenstein",
			"����������-Luxembourg",
			"��������-Mauritius",
			"����������-Mauritania",
			"����������-Madagascar",
			"�������-Mayotte",
			"����� (������)-Macau",
			"���������-Macedonia",
			"������-Malawi",
			"��������-Malaysia",
			"����-Mali",
			"��������-Maldives",
			"������-Malta",
			"�������-Morocco",
			"���������-Martinique",
			"���������� �������-Marshall Islands",
			"�������-Mexico",
			"��������-Mozambique",
			"��������-Moldova",
			"������-Monaco",
			"��������-Mongolia",
			"����������-Montserrat",
			"������ (�����)-Myanmar (Burma)",
			"�������-Namibia",
			"�����-Nauru",
			"�����-Nepal",
			"�����-Niger",
			"�������-Nigeria",
			"������������� ���������� �������-Netherlands Antilles",
			"���������� (���������)-Netherlands",
			"���������-Nicaragua",
			"����-Niue",
			"����� ��������-New Zealand",
			"����� ���������-New Caledonia",
			"��������-Norway",
			"���-United Arab Emirates",
			"����-Oman",
			"������ �������-Norfolk Island",
			"������ ���������-Christmas Island",
			"������ ������ �����-Saint Helena",
			"������ ���� � ������� ����������-Heard Island and McDonald Islands",
			"������� ����-Cook Islands",
			"������� �������-Pitcairn Islands",
			"������� Ҹ��� � ������-The Turks and Caicos Islands",
			"������� ������ � ������-Wallis and Futuna",
			"��������-Pakistan",
			"�����-Palau",
			"���������-Palestinian Territories",
			"������-Panama",
			"����� � ����� ������-Papua New Guinea",
			"��������-Paraguay",
			"����-Peru",
			"������-Poland",
			"����������-Portugal",
			"������-����-Puerto Rico",
			"���������� �����-Republic of the Congo",
			"�������-Reunion",
			"������-Russia",
			"������-Rwanda",
			"�������-Romania",
			"���������-El Salvador",
			"�����-Samoa",
			"���-������-San Marino",
			"���-���� � ��������-Sao Tome and Principe",
			"���������� ������-Saudi Arabia",
			"���������-Swaziland",
			"�������� ���������� �������-Northern Mariana Islands",
			"�������-Seychelles",
			"���-���� � �������-Saint-Pierre and Miquelon",
			"�������-Senegal",
			"����-������� � ���������-Saint Vincent and the Grenadines",
			"����-���� � �����-Saint Kitts and Nevis",
			"����-�����-Saint Lucia",
			"������-Serbia",
			"��������, ������-Singapore",
			"�����-Syria",
			"��������-Slovakia",
			"��������-Slovenia",
			"���������� �������-Solomon Islands",
			"������-Somalia",
			"�����-Sudan",
			"�������-Suriname",
			"���-USA",
			"������-�����-Sierra Leone",
			"�����������-Tajikistan",
			"�������-Thailand",
			"�������-Taiwan (Taipei)",
			"��������-Tanzania",
			"����-Togo",
			"�������-Tokelau",
			"�����-Tonga",
			"�������� � ������-Trinidad and Tobago",
			"������-Tuvalu",
			"�����-Tunisia",
			"���������-Turkmenistan",
			"������-Turkey",
			"������-Uganda",
			"����������-Uzbekistan",
			"�������-Ukraine",
			"�������-Uruguay",
			"��������� �������-Faroe Islands",
			"������������ ����� ����������-Federated States of Micronesia",
			"�����-Fiji",
			"���������-Philippines",
			"���������-Finland",
			"������������ (�����������) �������-Falkland Islands (Malvinas)",
			"�������-France",
			"����������� ������-French Guiana",
			"����������� ��������� (�����)-French Polynesia",
			"����������� ����� � �������������� ����������-French Southern Territories",
			"��������-Croatia",
			"��������������������� ����������-Central African Republic",
			"���-Chad",
			"����������-Montenegro",
			"�����-Czechia",
			"����, �. �����-Chile",
			"���� (����)-Chuuk",
			"���������-Switzerland",
			"������-Sweden",
			"���������� � ��-�����-Svalbard and Jan Mayen",
			"���-����� (������)-Sri Lanka",
			"�������, ����������-Ecuador",
			"�������������� ������-Equatorial Guinea",
			"�������-Eritrea",
			"�������-Estonia",
			"�������-Ethiopia",
			"���-Republic of South Africa",
			"����� ������� � ����� ���������� �������-South Georgia and the South Sandwich Islands",
			"������-Jamaica",
			"��-Yap",
			"������-Japan"
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
		map.put("AU", "���������\tAustralia");
		map.put("AT", "�������\tAustria");
		map.put("AZ", "�����������\tAzerbaijan");
		map.put("AL", "�������\tAlbania");
		map.put("DZ", "�����\tAlgeria");
		map.put("VI", "������������ ���������� �������\tUnited States Virgin Islands");
		map.put("AS", "������������ �����\tAmerican Samoa");
		map.put("AI", "�������\tAnguilla");
		map.put("AO", "������\tAngola");
		map.put("AD", "�������\tAndorra");
		map.put("AQ", "����������\tAntarctica");
		map.put("AG", "������� � �������\tAntigua and Barbuda");
		map.put("AR", "���������\tArgentina");
		map.put("AM", "�������\tArmenia");
		map.put("AW", "�����\tAruba");
		map.put("AF", "����������\tAfghanistan");
		map.put("BS", "��������� �������\tBahamas");
		map.put("BD", "���������\tBangladesh");
		map.put("BB", "��������\tBarbados");
		map.put("BH", "�������\tBahrain");
		map.put("BZ", "�����\tBelize");
		map.put("BY", "����������\tBelarus");
		map.put("BE", "�������\tBelgium");
		map.put("BJ", "�����\tBenin");
		map.put("BM", "�������\tBermuda");
		map.put("BG", "��������\tBulgaria");
		map.put("BO", "�������\tBolivia");
		map.put("BQ", "������, ����-�������� � ����\tCaribbean Netherlands");
		map.put("BA", "������ � �����������\tBosnia and Herzegovina");
		map.put("BW", "��������\tBotswana");
		map.put("BR", "��������\tBrazil");
		map.put("IO", "���������� ���������� � ��������� ������ /British Indian Ocean Territory");
		map.put("VG", "���������� ���������� �������\tBritish Virgin Islands");
		map.put("BN", "������\tBrunei Darussalam");
		map.put("BV", "����\tBouvet Island");
		map.put("BF", "�������-����\tBurkina Faso");
		map.put("BI", "�������\tBurundi");
		map.put("BT", "�����\tBhutan");
		map.put("VU", "�������\tVanuatu");
		map.put("VA", "�������\tVatican");
		map.put("GB", "��������������\tGreat Britain");
		map.put("HU", "�������\tHungary");
		map.put("VE", "���������\tVenezuela");
		map.put("TL", "��������� ����� (�����-�����)\tEast Timor");
		map.put("VN", "�������\tVietnam");
		map.put("GA", "�����\tGabon");
		map.put("HT", "�����\tHaiti");
		map.put("GY", "������\tGuyana");
		map.put("GM", "������\tGambia");
		map.put("GH", "����\tGhana");
		map.put("GP", "���������\tGuadeloupe");
		map.put("GT", "���������\tGuatemala");
		map.put("GN", "������\tGuinea");
		map.put("GW", "������-�����\tGuinea-Bissau");
		map.put("DE", "��������\tGermany");
		map.put("GG", "������\tGuernsey");
		map.put("GI", "���������\tGibraltar");
		map.put("HN", "��������\tHonduras");
		map.put("HK", "�������\tHong Kong");
		map.put("GD", "�������\tGrenada");
		map.put("GL", "����������\tGreenland");
		map.put("GR", "������\tGreece");
		map.put("GE", "������\tGeorgia");
		map.put("GU", "����\tGuam");
		map.put("DK", "�����\tDenmark");
		map.put("CD", "��������������� ���������� �����\tDemocratic Republic of the Congo");
		map.put("JE", "������\tJersey");
		map.put("DJ", "�������\tDjibouti");
		map.put("DM", "��������\tDominica");
		map.put("DO", "����������\tDominican Republic");
		map.put("EG", "������\tEgypt");
		map.put("ZM", "������\tZambia");
		map.put("EH", "�������� ������\tWestern Sahara");
		map.put("ZW", "��������\tZimbabwe");
		map.put("IL", "�������\tIsrael");
		map.put("IN", "�����, ���\tIndia");
		map.put("ID", "���������, ����\tIndonesia");
		map.put("JO", "��������\tJordan");
		map.put("IQ", "����\tIraq");
		map.put("IR", "����\tIran");
		map.put("IE", "��������\tIreland");
		map.put("IS", "��������\tIceland");
		map.put("ES", "�������, ������\tSpain");
		map.put("IT", "������\tItaly");
		map.put("YE", "�����\tYemen");
		map.put("CV", "����-����� (������� �������� ����)\tCape Verde");
		map.put("KZ", "���������\tKazakhstan");
		map.put("KY", "��������� �������\tCayman Islands");
		map.put("KH", "��������\tCambodia");
		map.put("CM", "�������\tCameroon");
		map.put("CA", "������\tCanada");
		map.put("QA", "�����\tQuatar");
		map.put("KE", "�����\tKenya");
		map.put("CY", "����\tCyprus");
		map.put("KG", "��������\tKyrgyzstan");
		map.put("KI", "��������\tKiribati");
		map.put("CN", "�����\tChina");
		map.put("KP", "����\tNorth Korea");
		map.put("CC", "��������� ������� (������)\tCocos (Keeling) Islands");
		map.put("CO", "��������\tColombia");
		map.put("KM", "��������� �������\tComoros");
		map.put("KR", "�����\tKorea");
		map.put("CR", "�����-����\tCosta Rica");
		map.put("CI", "���-������ (����� �������� �����)\tCote d'Ivoire (Ivory Coast)");
		map.put("CU", "����\tCuba");
		map.put("KW", "������\tKuwait");
		map.put("CW", "�������\tCuracao");
		map.put("LA", "����\tLaos");
		map.put("LV", "������\tLatvia");
		map.put("LS", "������\tLesotho");
		map.put("LR", "�������\tLiberia");
		map.put("LB", "�����\tLebanon");
		map.put("LY", "�����\tLibya");
		map.put("LT", "�����\tLithuania");
		map.put("LI", "�����������\tLiechtenstein");
		map.put("LU", "����������\tLuxembourg");
		map.put("MU", "��������\tMauritius");
		map.put("MR", "����������\tMauritania");
		map.put("MG", "����������\tMadagascar");
		map.put("YT", "�������\tMayotte");
		map.put("MO", "����� (������)\tMacau");
		map.put("MK", "���������\tMacedonia");
		map.put("MW", "������\tMalawi");
		map.put("MY", "��������\tMalaysia");
		map.put("ML", "����\tMali");
		map.put("MV", "��������\tMaldives");
		map.put("MT", "������\tMalta");
		map.put("MA", "�������\tMorocco");
		map.put("MQ", "���������\tMartinique");
		map.put("MH", "���������� �������\tMarshall Islands");
		map.put("MX", "�������\tMexico");
		map.put("MZ", "��������\tMozambique");
		map.put("MD", "��������\tMoldova");
		map.put("MC", "������\tMonaco");
		map.put("MN", "��������\tMongolia");
		map.put("MS", "����������\tMontserrat");
		map.put("MM", "������ (�����)\tMyanmar (Burma)");
		map.put("NA", "�������\tNamibia");
		map.put("NR", "�����\tNauru");
		map.put("NP", "�����\tNepal");
		map.put("NE", "�����\tNiger");
		map.put("NG", "�������\tNigeria");
		map.put("AN", "������������� ���������� �������\tNetherlands Antilles");
		map.put("NL", "���������� (���������)\tNetherlands");
		map.put("NI", "���������\tNicaragua");
		map.put("NU", "����\tNiue");
		map.put("NZ", "����� ��������\tNew Zealand");
		map.put("NC", "����� ���������\tNew Caledonia");
		map.put("NO", "��������\tNorway");
		map.put("AE", "���\tUnited Arab Emirates");
		map.put("OM", "����\tOman");
		map.put("NF", "������ �������\tNorfolk Island");
		map.put("CX", "������ ���������\tChristmas Island");
		map.put("SH", "������ ������ �����\tSaint Helena");
		map.put("HM", "������ ���� � ������� ����������\tHeard Island and McDonald Islands");
		map.put("CK", "������� ����\tCook Islands");
		map.put("PN", "������� �������\tPitcairn Islands");
		map.put("TC", "������� Ҹ��� � ������\tThe Turks and Caicos Islands");
		map.put("WF", "������� ������ � ������\tWallis and Futuna");
		map.put("PK", "��������\tPakistan");
		map.put("PW", "�����\tPalau");
		map.put("PS", "���������\tPalestinian Territories");
		map.put("PA", "������\tPanama");
		map.put("PG", "����� � ����� ������\tPapua New Guinea");
		map.put("PY", "��������\tParaguay");
		map.put("PE", "����\tPeru");
		map.put("PL", "������\tPoland");
		map.put("PT", "����������\tPortugal");
		map.put("PR", "������-����\tPuerto Rico");
		map.put("CG", "���������� �����\tRepublic of the Congo");
		map.put("RE", "�������\tReunion");
		map.put("RU", "������\tRussia");
		map.put("RW", "������\tRwanda");
		map.put("RO", "�������\tRomania");
		map.put("SV", "���������\tEl Salvador");
		map.put("WS", "�����\tSamoa");
		map.put("SM", "���-������\tSan Marino");
		map.put("ST", "���-���� � ��������\tSao Tome and Principe");
		map.put("SA", "���������� ������\tSaudi Arabia");
		map.put("SZ", "���������\tSwaziland");
		map.put("MP", "�������� ���������� �������\tNorthern Mariana Islands");
		map.put("SC", "�������\tSeychelles");
		map.put("PM", "���-���� � �������\tSaint-Pierre and Miquelon");
		map.put("SN", "�������\tSenegal");
		map.put("VC", "����-������� � ���������\tSaint Vincent and the Grenadines");
		map.put("KN", "����-���� � �����\tSaint Kitts and Nevis");
		map.put("LC", "����-�����\tSaint Lucia");
		map.put("RS", "������\tSerbia");
		map.put("SG", "��������, ������\tSingapore");
		map.put("SY", "�����\tSyria");
		map.put("SK", "��������\tSlovakia");
		map.put("SI", "��������\tSlovenia");
		map.put("SB", "���������� �������\tSolomon Islands");
		map.put("SO", "������\tSomalia");
		map.put("SD", "�����\tSudan");
		map.put("SR", "�������\tSuriname");
		map.put("US", "���\tUSA");
		map.put("SL", "������-�����\tSierra Leone");
		map.put("TJ", "�����������\tTajikistan");
		map.put("TH", "�������\tThailand");
		map.put("TW", "�������\tTaiwan (Taipei)");
		map.put("TZ", "��������\tTanzania");
		map.put("TG", "����\tTogo");
		map.put("TK", "�������\tTokelau");
		map.put("TO", "�����\tTonga");
		map.put("TT", "�������� � ������\tTrinidad and Tobago");
		map.put("TV", "������\tTuvalu");
		map.put("TN", "�����\tTunisia");
		map.put("TM", "���������\tTurkmenistan");
		map.put("TR", "������\tTurkey");
		map.put("UG", "������\tUganda");
		map.put("UZ", "����������\tUzbekistan");
		map.put("UA", "�������\tUkraine");
		map.put("UY", "�������\tUruguay");
		map.put("FO", "��������� �������\tFaroe Islands");
		map.put("FM", "������������ ����� ����������\tFederated States of Micronesia");
		map.put("FJ", "�����\tFiji");
		map.put("PH", "���������\tPhilippines");
		map.put("FI", "���������\tFinland");
		map.put("FK", "������������ (�����������) �������\tFalkland Islands (Malvinas)");
		map.put("FR", "�������\tFrance");
		map.put("GF", "����������� ������\tFrench Guiana");
		map.put("PF", "����������� ��������� (�����)\tFrench Polynesia");
		map.put("TF", "����������� ����� � �������������� ����������\tFrench Southern Territories");
		map.put("HR", "��������\tCroatia");
		map.put("CF", "��������������������� ����������\tCentral African Republic");
		map.put("TD", "���\tChad");
		map.put("ME", "����������\tMontenegro");
		map.put("CZ", "�����\tCzechia");
		map.put("CL", "����, �. �����\tChile");
		map.put("FM", "���� (����)\tChuuk");
		map.put("CH", "���������\tSwitzerland");
		map.put("SE", "������\tSweden");
		map.put("SJ", "���������� � ��-�����\tSvalbard and Jan Mayen");
		map.put("LK", "���-����� (������)\tSri Lanka");
		map.put("EC", "�������, ����������\tEcuador");
		map.put("GQ", "�������������� ������\tEquatorial Guinea");
		map.put("ER", "�������\tEritrea");
		map.put("EE", "�������\tEstonia");
		map.put("ET", "�������\tEthiopia");
		map.put("ZA", "���\tRepublic of South Africa");
		map.put("GS", "����� ������� � ����� ���������� �������\tSouth Georgia and the South Sandwich Islands");
		map.put("JM", "������\tJamaica");
		map.put("FM", "��\tYap");
		map.put("JP", "������\tJapan");

	}
	static
	{
		map.put("AU", "���������");
		map.put("AT", "�������");
		map.put("AZ", "�����������");
		map.put("AL", "�������");
		map.put("DZ", "�����");
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
		map.put("IO", "���������� ���������� � ��������� ������ /British Indian Ocean Territory");
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
		map.put("IO", "���������� ���������� � ��������� ������ /British Indian Ocean Territory");
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