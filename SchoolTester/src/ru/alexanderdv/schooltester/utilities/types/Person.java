package ru.alexanderdv.schooltester.utilities.types;

import java.io.Serializable;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.8a
 */
public abstract class Person implements Serializable
{
	private static final long serialVersionUID = -5187388895344395517L;
	private final String login;
	private String surname;
	private String name;
	private String secondName;

	public Person(String login)
	{
		this.login = login;
	}

	/**
	 * @return the surname
	 */
	public final String getSurname()
	{
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the secondName
	 */
	public final String getSecondName()
	{
		return secondName;
	}

	/**
	 * @param secondName
	 *            the secondName to set
	 */
	public final void setSecondName(String secondName)
	{
		this.secondName = secondName;
	}

	/**
	 * @return the login
	 */
	public final String getLogin()
	{
		return login;
	}

	public static class Rodstvennik extends Person
	{
		private static final long serialVersionUID = 2086015659204599269L;
		private boolean updateByLogin;

		public Rodstvennik(String login)
		{
			super(login);
		}

		public Rodstvennik(String login, String surname, String name, String secondName)
		{
			super(login);
			setSurname(surname);
			setName(name);
			setSecondName(secondName);
		}

		/**
		 * @return the updateByLogin
		 */
		public final boolean isUpdateByLogin()
		{
			return updateByLogin;
		}

		/**
		 * @param updateByLogin
		 *            the updateByLogin to set
		 */
		public final void setUpdateByLogin(boolean updateByLogin)
		{
			this.updateByLogin = updateByLogin;
		}

		/*
		 * (non-Javadoc)
		 */
		public final static Rodstvennik valueOf(String s)
		{
			if (s == null)
				return null;
			Rodstvennik r = new Rodstvennik((s + "\n \n \n \n ").split("\n")[0]);
			r.setSurname((s + "\n \n \n \n ").split("\n")[0]);
			r.setName((s + "\n \n \n \n ").split("\n")[0]);
			r.setSecondName((s + "\n \n \n \n ").split("\n")[0]);
			r.setUpdateByLogin(Boolean.parseBoolean((s + "\n \n \n \ntrue").split("\n")[0]));
			return r;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public final String toString()
		{
			return getLogin() + "\n" + getSurname() + "\n" + getName() + "\n" + getSecondName() + "\n" + updateByLogin;
		}

	}

}