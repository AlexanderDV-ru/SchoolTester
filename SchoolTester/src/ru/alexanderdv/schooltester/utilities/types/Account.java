package ru.alexanderdv.schooltester.utilities.types;

import java.util.ArrayList;

import ru.alexanderdv.schooltester.utilities.enums.Subject;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public final class Account extends Person
{
	private static final long serialVersionUID = 8499083051889167350L;

	private final AccountType accountType;
	private String password;
	private int passwordHash;

	private boolean active;
	private boolean deleted;

	private final ArrayList<String> personalSites = new ArrayList<String>();
	private final ArrayList<String> otherSites = new ArrayList<String>();
	private final ArrayList<String> phoneNumbers = new ArrayList<String>();
	private final ArrayList<String> emails = new ArrayList<String>();
	private final ArrayList<String> otherContacts = new ArrayList<String>();

	private String gender;
	private String age;

	private String country;
	private String region;
	private String city;
	private String school;

	private String biografy;

	private String education, carriere;

	private final ArrayList<String> mainLanguages = new ArrayList<String>(), otherLanguages = new ArrayList<String>();
	private String homeCountry, homeRegion, homeCity;

	private String aboutAlhogol;
	private String aboutNarcotics;
	private String aboutSmoking;
	private String ideas;
	private String interests;
	private String favouriteBlogs;
	private String favouriteBooks;
	private String favouriteComputerGames;
	private String favouriteFilms;
	private String favouriteGames;
	private String favouriteMusic;
	private String favouritePeople;
	private String favouriteShows;
	private String favouriteQuotes;
	private String mainInLife;
	private String mainInPeople;
	private String worldOutlook;
	private String otherViews;
	private String politicalViews;
	private String inspiration;

	private String maritalStatus;
	private final ArrayList<Rodstvennik> parents = new ArrayList<Rodstvennik>();
	private final ArrayList<Rodstvennik> grandParents = new ArrayList<Rodstvennik>();
	private final ArrayList<Rodstvennik> siblings = new ArrayList<Rodstvennik>();
	private final ArrayList<Rodstvennik> children = new ArrayList<Rodstvennik>();
	private final ArrayList<Rodstvennik> grandChildren = new ArrayList<Rodstvennik>();
	private final ArrayList<Rodstvennik> otherRelatives = new ArrayList<Rodstvennik>();
	private final ArrayList<Rodstvennik> exSpouses = new ArrayList<Rodstvennik>();
	private Rodstvennik spouse;

	private final ArrayList<Subject> subjects = new ArrayList<Subject>();

	public static enum AccountType
	{
		Teacher,
		Student,
		Administrator
	}

	public Account(AccountType accountType, String login, String password)
	{
		super(login);
		this.accountType = accountType;
		this.password = password;
	}

	public Account(AccountType accountType, String login)
	{
		super(login);
		this.accountType = accountType;
	}

	/**
	 * @return the passwordHash
	 */
	public final String getPassword()
	{
		return password;
	}

	/**
	 * @param passwordHash
	 *            the passwordHash to set
	 */
	public final void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the active
	 */
	public final boolean isActive()
	{
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public final void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return the deleted
	 */
	public final boolean isDeleted()
	{
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public final void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * @return the gender
	 */
	public final String getGender()
	{
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public final void setGender(String gender)
	{
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public final String getAge()
	{
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public final void setAge(String age)
	{
		this.age = age;
	}

	/**
	 * @return the country
	 */
	public final String getCountry()
	{
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public final void setCountry(String country)
	{
		this.country = country;
	}

	/**
	 * @return the region
	 */
	public final String getRegion()
	{
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public final void setRegion(String region)
	{
		this.region = region;
	}

	/**
	 * @return the city
	 */
	public final String getCity()
	{
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public final void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the school
	 */
	public final String getSchool()
	{
		return school;
	}

	/**
	 * @param school
	 *            the school to set
	 */
	public final void setSchool(String school)
	{
		this.school = school;
	}

	/**
	 * @return the biografy
	 */
	public final String getBiografy()
	{
		return biografy;
	}

	/**
	 * @param biografy
	 *            the biografy to set
	 */
	public final void setBiografy(String biografy)
	{
		this.biografy = biografy;
	}

	/**
	 * @return the education
	 */
	public final String getEducation()
	{
		return education;
	}

	/**
	 * @param education
	 *            the education to set
	 */
	public final void setEducation(String education)
	{
		this.education = education;
	}

	/**
	 * @return the carriere
	 */
	public final String getCarriere()
	{
		return carriere;
	}

	/**
	 * @return the homeCountry
	 */
	public final String getHomeCountry()
	{
		return homeCountry;
	}

	/**
	 * @param homeCountry
	 *            the homeCountry to set
	 */
	public final void setHomeCountry(String homeCountry)
	{
		this.homeCountry = homeCountry;
	}

	/**
	 * @return the homeRegion
	 */
	public final String getHomeRegion()
	{
		return homeRegion;
	}

	/**
	 * @param homeRegion
	 *            the homeRegion to set
	 */
	public final void setHomeRegion(String homeRegion)
	{
		this.homeRegion = homeRegion;
	}

	/**
	 * @return the homeCity
	 */
	public final String getHomeCity()
	{
		return homeCity;
	}

	/**
	 * @param homeCity
	 *            the homeCity to set
	 */
	public final void setHomeCity(String homeCity)
	{
		this.homeCity = homeCity;
	}

	/**
	 * @return the aboutAlhogol
	 */
	public final String getAboutAlhogol()
	{
		return aboutAlhogol;
	}

	/**
	 * @param aboutAlhogol
	 *            the aboutAlhogol to set
	 */
	public final void setAboutAlhogol(String aboutAlhogol)
	{
		this.aboutAlhogol = aboutAlhogol;
	}

	/**
	 * @return the aboutNarcotics
	 */
	public final String getAboutNarcotics()
	{
		return aboutNarcotics;
	}

	/**
	 * @param aboutNarcotics
	 *            the aboutNarcotics to set
	 */
	public final void setAboutNarcotics(String aboutNarcotics)
	{
		this.aboutNarcotics = aboutNarcotics;
	}

	/**
	 * @return the aboutSmoking
	 */
	public final String getAboutSmoking()
	{
		return aboutSmoking;
	}

	/**
	 * @param aboutSmoking
	 *            the aboutSmoking to set
	 */
	public final void setAboutSmoking(String aboutSmoking)
	{
		this.aboutSmoking = aboutSmoking;
	}

	/**
	 * @return the ideas
	 */
	public final String getIdeas()
	{
		return ideas;
	}

	/**
	 * @param ideas
	 *            the ideas to set
	 */
	public final void setIdeas(String ideas)
	{
		this.ideas = ideas;
	}

	/**
	 * @return the interests
	 */
	public final String getInterests()
	{
		return interests;
	}

	/**
	 * @param interests
	 *            the interests to set
	 */
	public final void setInterests(String interests)
	{
		this.interests = interests;
	}

	/**
	 * @return the favouriteBlogs
	 */
	public final String getFavouriteBlogs()
	{
		return favouriteBlogs;
	}

	/**
	 * @param favouriteBlogs
	 *            the favouriteBlogs to set
	 */
	public final void setFavouriteBlogs(String favouriteBlogs)
	{
		this.favouriteBlogs = favouriteBlogs;
	}

	/**
	 * @return the favouriteBooks
	 */
	public final String getFavouriteBooks()
	{
		return favouriteBooks;
	}

	/**
	 * @param favouriteBooks
	 *            the favouriteBooks to set
	 */
	public final void setFavouriteBooks(String favouriteBooks)
	{
		this.favouriteBooks = favouriteBooks;
	}

	/**
	 * @return the favouriteComputerGames
	 */
	public final String getFavouriteComputerGames()
	{
		return favouriteComputerGames;
	}

	/**
	 * @param favouriteComputerGames
	 *            the favouriteComputerGames to set
	 */
	public final void setFavouriteComputerGames(String favouriteComputerGames)
	{
		this.favouriteComputerGames = favouriteComputerGames;
	}

	/**
	 * @return the favouriteFilms
	 */
	public final String getFavouriteFilms()
	{
		return favouriteFilms;
	}

	/**
	 * @param favouriteFilms
	 *            the favouriteFilms to set
	 */
	public final void setFavouriteFilms(String favouriteFilms)
	{
		this.favouriteFilms = favouriteFilms;
	}

	/**
	 * @return the favouriteGames
	 */
	public final String getFavouriteGames()
	{
		return favouriteGames;
	}

	/**
	 * @param favouriteGames
	 *            the favouriteGames to set
	 */
	public final void setFavouriteGames(String favouriteGames)
	{
		this.favouriteGames = favouriteGames;
	}

	/**
	 * @return the favouriteMusic
	 */
	public final String getFavouriteMusic()
	{
		return favouriteMusic;
	}

	/**
	 * @param favouriteMusic
	 *            the favouriteMusic to set
	 */
	public final void setFavouriteMusic(String favouriteMusic)
	{
		this.favouriteMusic = favouriteMusic;
	}

	/**
	 * @return the favouritePeople
	 */
	public final String getFavouritePeople()
	{
		return favouritePeople;
	}

	/**
	 * @param favouritePeople
	 *            the favouritePeople to set
	 */
	public final void setFavouritePeople(String favouritePeople)
	{
		this.favouritePeople = favouritePeople;
	}

	/**
	 * @return the favouriteShows
	 */
	public final String getFavouriteShows()
	{
		return favouriteShows;
	}

	/**
	 * @param favouriteShows
	 *            the favouriteShows to set
	 */
	public final void setFavouriteShows(String favouriteShows)
	{
		this.favouriteShows = favouriteShows;
	}

	/**
	 * @return the favouriteQuotes
	 */
	public final String getFavouriteQuotes()
	{
		return favouriteQuotes;
	}

	/**
	 * @param favouriteQuotes
	 *            the favouriteQuotes to set
	 */
	public final void setFavouriteQuotes(String favouriteQuotes)
	{
		this.favouriteQuotes = favouriteQuotes;
	}

	/**
	 * @return the mainInLife
	 */
	public final String getMainInLife()
	{
		return mainInLife;
	}

	/**
	 * @param mainInLife
	 *            the mainInLife to set
	 */
	public final void setMainInLife(String mainInLife)
	{
		this.mainInLife = mainInLife;
	}

	/**
	 * @return the mainInPeople
	 */
	public final String getMainInPeople()
	{
		return mainInPeople;
	}

	/**
	 * @param mainInPeople
	 *            the mainInPeople to set
	 */
	public final void setMainInPeople(String mainInPeople)
	{
		this.mainInPeople = mainInPeople;
	}

	/**
	 * @return the worldOutlook
	 */
	public final String getWorldOutlook()
	{
		return worldOutlook;
	}

	/**
	 * @param worldOutlook
	 *            the worldOutlook to set
	 */
	public final void setWorldOutlook(String worldOutlook)
	{
		this.worldOutlook = worldOutlook;
	}

	/**
	 * @return the otherViews
	 */
	public final String getOtherViews()
	{
		return otherViews;
	}

	/**
	 * @param otherViews
	 *            the otherViews to set
	 */
	public final void setOtherViews(String otherViews)
	{
		this.otherViews = otherViews;
	}

	/**
	 * @return the politicalViews
	 */
	public final String getPoliticalViews()
	{
		return politicalViews;
	}

	/**
	 * @param politicalViews
	 *            the politicalViews to set
	 */
	public final void setPoliticalViews(String politicalViews)
	{
		this.politicalViews = politicalViews;
	}

	/**
	 * @return the inspiration
	 */
	public final String getInspiration()
	{
		return inspiration;
	}

	/**
	 * @param inspiration
	 *            the inspiration to set
	 */
	public final void setInspiration(String inspiration)
	{
		this.inspiration = inspiration;
	}

	/**
	 * @return the maritalStatus
	 */
	public final String getMaritalStatus()
	{
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public final void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the spouse
	 */
	public final Rodstvennik getSpouse()
	{
		return spouse;
	}

	/**
	 * @param spouse
	 *            the spouse to set
	 */
	public final void setSpouse(Rodstvennik spouse)
	{
		this.spouse = spouse;
	}

	/**
	 * @return the accountType
	 */
	public final AccountType getAccountType()
	{
		return accountType;
	}

	/**
	 * @return the personalSites
	 */
	public final ArrayList<String> getPersonalSites()
	{
		return personalSites;
	}

	/**
	 * @return the otherSites
	 */
	public final ArrayList<String> getOtherSites()
	{
		return otherSites;
	}

	/**
	 * @return the phoneNumbers
	 */
	public final ArrayList<String> getPhoneNumbers()
	{
		return phoneNumbers;
	}

	/**
	 * @return the emails
	 */
	public final ArrayList<String> getEmails()
	{
		return emails;
	}

	/**
	 * @return the otherContacts
	 */
	public final ArrayList<String> getOtherContacts()
	{
		return otherContacts;
	}

	/**
	 * @return the mainLanguages
	 */
	public final ArrayList<String> getMainLanguages()
	{
		return mainLanguages;
	}

	/**
	 * @return the otherLanguages
	 */
	public final ArrayList<String> getOtherLanguages()
	{
		return otherLanguages;
	}

	/**
	 * @return the parents
	 */
	public final ArrayList<Rodstvennik> getParents()
	{
		return parents;
	}

	/**
	 * @return the grandParents
	 */
	public final ArrayList<Rodstvennik> getGrandParents()
	{
		return grandParents;
	}

	/**
	 * @return the siblings
	 */
	public final ArrayList<Rodstvennik> getSiblings()
	{
		return siblings;
	}

	/**
	 * @return the children
	 */
	public final ArrayList<Rodstvennik> getChildren()
	{
		return children;
	}

	/**
	 * @return the grandChildren
	 */
	public final ArrayList<Rodstvennik> getGrandChildren()
	{
		return grandChildren;
	}

	/**
	 * @return the otherRelatives
	 */
	public final ArrayList<Rodstvennik> getOtherRelatives()
	{
		return otherRelatives;
	}

	/**
	 * @return the exSpouses
	 */
	public final ArrayList<Rodstvennik> getExSpouses()
	{
		return exSpouses;
	}

	/**
	 * @return the subjects
	 */
	public final ArrayList<Subject> getSubjects()
	{
		return subjects;
	}

	/**
	 * @param carriere
	 *            the carriere to set
	 */
	public final void setCarriere(String carriere)
	{
		this.carriere = carriere;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString()
	{
		return "Account [accountType=" + accountType + ", password=" + password + ", active=" + active + ", deleted=" + deleted + ", personalSites="
				+ personalSites + ", otherSites=" + otherSites + ", phoneNumbers=" + phoneNumbers + ", emails=" + emails + ", otherContacts=" + otherContacts
				+ ", gender=" + gender + ", age=" + age + ", country=" + country + ", region=" + region + ", city=" + city + ", school=" + school
				+ ", biografy=" + biografy + ", education=" + education + ", carriere=" + carriere + ", mainLanguages=" + mainLanguages + ", otherLanguages="
				+ otherLanguages + ", homeCountry=" + homeCountry + ", homeRegion=" + homeRegion + ", homeCity=" + homeCity + ", aboutAlhogol=" + aboutAlhogol
				+ ", aboutNarcotics=" + aboutNarcotics + ", aboutSmoking=" + aboutSmoking + ", ideas=" + ideas + ", interests=" + interests
				+ ", favouriteBlogs=" + favouriteBlogs + ", favouriteBooks=" + favouriteBooks + ", favouriteComputerGames=" + favouriteComputerGames
				+ ", favouriteFilms=" + favouriteFilms + ", favouriteGames=" + favouriteGames + ", favouriteMusic=" + favouriteMusic + ", favouritePeople="
				+ favouritePeople + ", favouriteShows=" + favouriteShows + ", favouriteQuotes=" + favouriteQuotes + ", mainInLife=" + mainInLife
				+ ", mainInPeople=" + mainInPeople + ", worldOutlook=" + worldOutlook + ", otherViews=" + otherViews + ", politicalViews=" + politicalViews
				+ ", inspiration=" + inspiration + ", maritalStatus=" + maritalStatus + ", parents=" + parents + ", grandParents=" + grandParents
				+ ", siblings=" + siblings + ", children=" + children + ", grandChildren=" + grandChildren + ", otherRelatives=" + otherRelatives
				+ ", exSpouses=" + exSpouses + ", spouse=" + spouse + ", subjects=" + subjects + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aboutAlhogol == null) ? 0 : aboutAlhogol.hashCode());
		result = prime * result + ((aboutNarcotics == null) ? 0 : aboutNarcotics.hashCode());
		result = prime * result + ((aboutSmoking == null) ? 0 : aboutSmoking.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((biografy == null) ? 0 : biografy.hashCode());
		result = prime * result + ((carriere == null) ? 0 : carriere.hashCode());
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (deleted ? 1231 : 1237);
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result + ((exSpouses == null) ? 0 : exSpouses.hashCode());
		result = prime * result + ((favouriteBlogs == null) ? 0 : favouriteBlogs.hashCode());
		result = prime * result + ((favouriteBooks == null) ? 0 : favouriteBooks.hashCode());
		result = prime * result + ((favouriteComputerGames == null) ? 0 : favouriteComputerGames.hashCode());
		result = prime * result + ((favouriteFilms == null) ? 0 : favouriteFilms.hashCode());
		result = prime * result + ((favouriteGames == null) ? 0 : favouriteGames.hashCode());
		result = prime * result + ((favouriteMusic == null) ? 0 : favouriteMusic.hashCode());
		result = prime * result + ((favouritePeople == null) ? 0 : favouritePeople.hashCode());
		result = prime * result + ((favouriteQuotes == null) ? 0 : favouriteQuotes.hashCode());
		result = prime * result + ((favouriteShows == null) ? 0 : favouriteShows.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((grandChildren == null) ? 0 : grandChildren.hashCode());
		result = prime * result + ((grandParents == null) ? 0 : grandParents.hashCode());
		result = prime * result + ((homeCity == null) ? 0 : homeCity.hashCode());
		result = prime * result + ((homeCountry == null) ? 0 : homeCountry.hashCode());
		result = prime * result + ((homeRegion == null) ? 0 : homeRegion.hashCode());
		result = prime * result + ((ideas == null) ? 0 : ideas.hashCode());
		result = prime * result + ((inspiration == null) ? 0 : inspiration.hashCode());
		result = prime * result + ((interests == null) ? 0 : interests.hashCode());
		result = prime * result + ((mainInLife == null) ? 0 : mainInLife.hashCode());
		result = prime * result + ((mainInPeople == null) ? 0 : mainInPeople.hashCode());
		result = prime * result + ((mainLanguages == null) ? 0 : mainLanguages.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((otherContacts == null) ? 0 : otherContacts.hashCode());
		result = prime * result + ((otherLanguages == null) ? 0 : otherLanguages.hashCode());
		result = prime * result + ((otherRelatives == null) ? 0 : otherRelatives.hashCode());
		result = prime * result + ((otherSites == null) ? 0 : otherSites.hashCode());
		result = prime * result + ((otherViews == null) ? 0 : otherViews.hashCode());
		result = prime * result + ((parents == null) ? 0 : parents.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((personalSites == null) ? 0 : personalSites.hashCode());
		result = prime * result + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
		result = prime * result + ((politicalViews == null) ? 0 : politicalViews.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((siblings == null) ? 0 : siblings.hashCode());
		result = prime * result + ((spouse == null) ? 0 : spouse.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		result = prime * result + ((worldOutlook == null) ? 0 : worldOutlook.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (aboutAlhogol == null)
		{
			if (other.aboutAlhogol != null)
				return false;
		}
		else if (!aboutAlhogol.equals(other.aboutAlhogol))
			return false;
		if (aboutNarcotics == null)
		{
			if (other.aboutNarcotics != null)
				return false;
		}
		else if (!aboutNarcotics.equals(other.aboutNarcotics))
			return false;
		if (aboutSmoking == null)
		{
			if (other.aboutSmoking != null)
				return false;
		}
		else if (!aboutSmoking.equals(other.aboutSmoking))
			return false;
		if (accountType != other.accountType)
			return false;
		if (active != other.active)
			return false;
		if (age == null)
		{
			if (other.age != null)
				return false;
		}
		else if (!age.equals(other.age))
			return false;
		if (biografy == null)
		{
			if (other.biografy != null)
				return false;
		}
		else if (!biografy.equals(other.biografy))
			return false;
		if (carriere == null)
		{
			if (other.carriere != null)
				return false;
		}
		else if (!carriere.equals(other.carriere))
			return false;
		if (children == null)
		{
			if (other.children != null)
				return false;
		}
		else if (!children.equals(other.children))
			return false;
		if (city == null)
		{
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
			return false;
		if (country == null)
		{
			if (other.country != null)
				return false;
		}
		else if (!country.equals(other.country))
			return false;
		if (deleted != other.deleted)
			return false;
		if (education == null)
		{
			if (other.education != null)
				return false;
		}
		else if (!education.equals(other.education))
			return false;
		if (emails == null)
		{
			if (other.emails != null)
				return false;
		}
		else if (!emails.equals(other.emails))
			return false;
		if (exSpouses == null)
		{
			if (other.exSpouses != null)
				return false;
		}
		else if (!exSpouses.equals(other.exSpouses))
			return false;
		if (favouriteBlogs == null)
		{
			if (other.favouriteBlogs != null)
				return false;
		}
		else if (!favouriteBlogs.equals(other.favouriteBlogs))
			return false;
		if (favouriteBooks == null)
		{
			if (other.favouriteBooks != null)
				return false;
		}
		else if (!favouriteBooks.equals(other.favouriteBooks))
			return false;
		if (favouriteComputerGames == null)
		{
			if (other.favouriteComputerGames != null)
				return false;
		}
		else if (!favouriteComputerGames.equals(other.favouriteComputerGames))
			return false;
		if (favouriteFilms == null)
		{
			if (other.favouriteFilms != null)
				return false;
		}
		else if (!favouriteFilms.equals(other.favouriteFilms))
			return false;
		if (favouriteGames == null)
		{
			if (other.favouriteGames != null)
				return false;
		}
		else if (!favouriteGames.equals(other.favouriteGames))
			return false;
		if (favouriteMusic == null)
		{
			if (other.favouriteMusic != null)
				return false;
		}
		else if (!favouriteMusic.equals(other.favouriteMusic))
			return false;
		if (favouritePeople == null)
		{
			if (other.favouritePeople != null)
				return false;
		}
		else if (!favouritePeople.equals(other.favouritePeople))
			return false;
		if (favouriteQuotes == null)
		{
			if (other.favouriteQuotes != null)
				return false;
		}
		else if (!favouriteQuotes.equals(other.favouriteQuotes))
			return false;
		if (favouriteShows == null)
		{
			if (other.favouriteShows != null)
				return false;
		}
		else if (!favouriteShows.equals(other.favouriteShows))
			return false;
		if (gender == null)
		{
			if (other.gender != null)
				return false;
		}
		else if (!gender.equals(other.gender))
			return false;
		if (grandChildren == null)
		{
			if (other.grandChildren != null)
				return false;
		}
		else if (!grandChildren.equals(other.grandChildren))
			return false;
		if (grandParents == null)
		{
			if (other.grandParents != null)
				return false;
		}
		else if (!grandParents.equals(other.grandParents))
			return false;
		if (homeCity == null)
		{
			if (other.homeCity != null)
				return false;
		}
		else if (!homeCity.equals(other.homeCity))
			return false;
		if (homeCountry == null)
		{
			if (other.homeCountry != null)
				return false;
		}
		else if (!homeCountry.equals(other.homeCountry))
			return false;
		if (homeRegion == null)
		{
			if (other.homeRegion != null)
				return false;
		}
		else if (!homeRegion.equals(other.homeRegion))
			return false;
		if (ideas == null)
		{
			if (other.ideas != null)
				return false;
		}
		else if (!ideas.equals(other.ideas))
			return false;
		if (inspiration == null)
		{
			if (other.inspiration != null)
				return false;
		}
		else if (!inspiration.equals(other.inspiration))
			return false;
		if (interests == null)
		{
			if (other.interests != null)
				return false;
		}
		else if (!interests.equals(other.interests))
			return false;
		if (mainInLife == null)
		{
			if (other.mainInLife != null)
				return false;
		}
		else if (!mainInLife.equals(other.mainInLife))
			return false;
		if (mainInPeople == null)
		{
			if (other.mainInPeople != null)
				return false;
		}
		else if (!mainInPeople.equals(other.mainInPeople))
			return false;
		if (mainLanguages == null)
		{
			if (other.mainLanguages != null)
				return false;
		}
		else if (!mainLanguages.equals(other.mainLanguages))
			return false;
		if (maritalStatus == null)
		{
			if (other.maritalStatus != null)
				return false;
		}
		else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (otherContacts == null)
		{
			if (other.otherContacts != null)
				return false;
		}
		else if (!otherContacts.equals(other.otherContacts))
			return false;
		if (otherLanguages == null)
		{
			if (other.otherLanguages != null)
				return false;
		}
		else if (!otherLanguages.equals(other.otherLanguages))
			return false;
		if (otherRelatives == null)
		{
			if (other.otherRelatives != null)
				return false;
		}
		else if (!otherRelatives.equals(other.otherRelatives))
			return false;
		if (otherSites == null)
		{
			if (other.otherSites != null)
				return false;
		}
		else if (!otherSites.equals(other.otherSites))
			return false;
		if (otherViews == null)
		{
			if (other.otherViews != null)
				return false;
		}
		else if (!otherViews.equals(other.otherViews))
			return false;
		if (parents == null)
		{
			if (other.parents != null)
				return false;
		}
		else if (!parents.equals(other.parents))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (personalSites == null)
		{
			if (other.personalSites != null)
				return false;
		}
		else if (!personalSites.equals(other.personalSites))
			return false;
		if (phoneNumbers == null)
		{
			if (other.phoneNumbers != null)
				return false;
		}
		else if (!phoneNumbers.equals(other.phoneNumbers))
			return false;
		if (politicalViews == null)
		{
			if (other.politicalViews != null)
				return false;
		}
		else if (!politicalViews.equals(other.politicalViews))
			return false;
		if (region == null)
		{
			if (other.region != null)
				return false;
		}
		else if (!region.equals(other.region))
			return false;
		if (school == null)
		{
			if (other.school != null)
				return false;
		}
		else if (!school.equals(other.school))
			return false;
		if (siblings == null)
		{
			if (other.siblings != null)
				return false;
		}
		else if (!siblings.equals(other.siblings))
			return false;
		if (spouse == null)
		{
			if (other.spouse != null)
				return false;
		}
		else if (!spouse.equals(other.spouse))
			return false;
		if (subjects == null)
		{
			if (other.subjects != null)
				return false;
		}
		else if (!subjects.equals(other.subjects))
			return false;
		if (worldOutlook == null)
		{
			if (other.worldOutlook != null)
				return false;
		}
		else if (!worldOutlook.equals(other.worldOutlook))
			return false;
		return true;
	}

	public void _setPasswordHash(int passwordHash)
	{
		this.passwordHash=passwordHash;
	}
	public int _getPasswordHash()
	{
		return passwordHash;
	}
}
