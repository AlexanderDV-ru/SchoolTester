package ru.alexanderdv.schooltester.utilities;

import java.io.Serializable;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
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
			Rodstvennik r = new Rodstvennik(getString(s, "login"));
			r.setSurname(getString(s, "surname"));
			r.setName(getString(s, "name"));
			r.setSecondName(getString(s, "secondname"));
			r.setUpdateByLogin(Boolean.parseBoolean(getString(s, "updateByLogin")));
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
			return "Rodstvennik[updateByLogin=" + prepareString(updateByLogin) + ", login=" + prepareString(getLogin()) + ", surname=" + prepareString(
					getSurname()) + ", name=" + prepareString(getName()) + ", secondname=" + prepareString(getSecondName()) + "]";
		}

		private static String prepareString(Object o)
		{
			if (o == null)
				return null;
			return "[" + o.getClass() + "]'" + o.toString().replace("\\", "\\s").replace("'", "\\q").replace("\n", "\\n").replace("\t", "\\t").replace("=",
					"\\e").replace("[", "\\l").replace("]", "\\r") + "'";
		}

		private static String getString(String s, String name)
		{
			if (s == null || name == null)
				return null;
			try
			{
				String s2 = s.substring(s.indexOf(name + "=[") + name.length() + 2);
				s2 = s2.substring(0, s2.indexOf("]"));
				s = s.substring(s.indexOf(name + "=[" + s2 + "]'"));
				s = s.substring(0, s.indexOf("'"));
				return s.replace("\\e", "=").replace("\\q", "'").replace("\\n", "\n").replace("\\t", "\t").replace("\\s", "\\").replace("\\l", "[").replace(
						"\\r", "]");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}

	}

}