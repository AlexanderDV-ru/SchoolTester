package ru.alexanderdv.schooltester.main;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.alexanderdv.schooltester.utilities.Account;
import ru.alexanderdv.schooltester.utilities.Account.AccountType;
import ru.alexanderdv.schooltester.utilities.AccountPacket;
import ru.alexanderdv.schooltester.utilities.ComboboxWithAdd;
import ru.alexanderdv.schooltester.utilities.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.Person.Rodstvennik;
import ru.alexanderdv.schooltester.utilities.Subject;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class InitAccountsPart
{
	public static InitAccountsPart instance;

	@FXML
	public TextField loginField, passwordField, passwordRepeatField;
	@FXML
	public ComboBox<AccountType> accountTypeCombobox;

	@FXML
	public TabPane tabPane;

	@FXML
	public Tab signUpTab;
	@FXML
	public Button signUpButton;

	@FXML
	public Tab signInTab;
	@FXML
	public Button signInButton;

	@FXML
	public Tab deleteAccountTab;
	@FXML
	public Button deleteAccountButton;

	@FXML
	public Tab signOutTab;
	@FXML
	public Button signOutButton;

	@FXML
	public Tab profileTab;

	@FXML
	public Tab securityTab;
	@FXML
	public TextField newPasswordField, newPasswordRepeatField;
	@FXML
	public Button changePasswordButton;

	@FXML
	public Tab mainTab;
	@FXML
	public TextField surnameField, nameField, secondNameField, countryField, regionField, cityField, schoolField;
	@FXML
	public MenuButton subjectsCombobox;
	public ArrayList<Subject> selectedsubjectsCombobox = new ArrayList<Subject>();

	@FXML
	public Tab familyTab;
	@FXML
	public Label maritalStatusLabel, grandParentsLabel, parentsLabel, childrenLabel, grandChildrenLabel, siblingsLabel, exSpousesLabel, spouseLabel,
			otherRelativesLabel;
	@FXML
	public TextField maritalStatusField, spouseField;
	@FXML
	public ComboboxWithAdd grandParentsCombobox, parentsCombobox, childrenCombobox, grandChildrenCombobox, siblingsCombobox, exSpousesCombobox,
			otherRelativesCombobox;

	@FXML
	public Tab contactsTab;
	@FXML
	public Label phoneNumbersLabel, emailsLabel, personalSitesLabel, otherSitesLabel, otherContactsLabel;
	@FXML
	public ComboboxWithAdd phoneNumbersCombobox, emailsCombobox, personalSitesCombobox, otherSitesCombobox, otherContactsCombobox;

	@FXML
	public Tab lifeTab;
	@FXML
	public Label ageLabel, genderLabel, mainLanguagesLabel, otherLanguagesLabel, educationLabel, careerLabel;
	@FXML
	public TextField ageField;
	@FXML
	public ComboBox<String> genderCombobox;
	@FXML
	public ComboboxWithAdd mainLanguagesCombobox, otherLanguagesCombobox;
	@FXML
	public TextArea educationArea, careerArea;

	@FXML
	public Tab ideasTab;
	@FXML
	public Label aboutAlhogolLabel, aboutNarcoticsLabel, aboutSmokingLabel, ideasLabel, interestsLabel, favouriteBlogsLabel, favouriteBooksLabel,
			favouriteComputerGamesLabel, favouriteFilmsLabel, favouriteGamesLabel, favouriteMusicLabel, favouritePeopleLabel, favouriteShowsLabel,
			favouriteQuotesLabel, mainInLifeLabel, mainInPeopleLabel, worldOutlookLabel, otherViewsLabel, politicalViewsLabel, inspirationLabel;
	@FXML
	public TextField aboutAlhogolField, aboutNarcoticsField, aboutSmokingField, ideasField, interestsField, favouriteBlogsField, favouriteBooksField,
			favouriteComputerGamesField, favouriteFilmsField, favouriteGamesField, favouriteMusicField, favouritePeopleField, favouriteShowsField,
			favouriteQuotesField, mainInLifeField, mainInPeopleField, worldOutlookField, otherViewsField, politicalViewsField, inspirationField;

	@FXML
	public Tab aboutYouTab;
	@FXML
	public Label biografyLabel, homeCountryLabel, homeRegionLabel, homeCityLabel;
	@FXML
	public TextArea biografyArea;
	@FXML
	public TextField homeCountryField, homeRegionField, homeCityField;
	@FXML
	public Button saveButton;

	@FXML
	public GridPane familyFields, contactsFields, lifeFields, ideasFields;
	//
	// @FXML
	// public MenuBar menubar;
	// @FXML
	// public RadioMenuItem languageRU, languageEN;
	// @FXML
	// public Menu window, settings, language, help;
	// @FXML
	// public MenuItem privacyPolicy, usersManual, site;
	@FXML
	public TabPane profileTabs;

	public void createActionHandlers()
	{
		AccountsPart.account.addActionListener(e -> accountTypeCombobox.getSelectionModel().select(AccountsPart.account.get() == null ? AccountType.Student
				: AccountsPart.account.get().getAccountType()));
		for (MenuItem item : subjectsCombobox.getItems())
			item.setOnAction(e ->
			{
				if (selectedsubjectsCombobox.contains(Subject.valueOf(item.getText().substring(offset))))
					selectedsubjectsCombobox.remove(Subject.valueOf(item.getText().substring(offset)));
				else selectedsubjectsCombobox.add(Subject.valueOf(item.getText().substring(offset)));
				InitAccountsPart.instance.updateSelectedsubjectsCombobox();
			});
		InitAccountsPart.instance.updateSelectedsubjectsCombobox();
		InitAccountsPart.instance.signUpButton.setOnAction(e ->
		{
			if (passwordField.getText().equals(InitAccountsPart.instance.passwordRepeatField.getText()))
			{
				Main.instance.addRequest(new AccountPacket("signUp", Main.macAddress, null, AccountsPart.account.get(), new Account(
						InitAccountsPart.instance.accountTypeCombobox.getSelectionModel().getSelectedItem(), InitAccountsPart.instance.loginField.getText(),
						InitAccountsPart.instance.passwordField.getText())));
			}
			else FXDialogsGenerator.showFXDialog(null, null, Main.msgSys.getMsg("passwordsNotMatch"), 1, 1, Main.isFxWindowFrame(), true);
		});
		InitAccountsPart.instance.signInButton.setOnAction(e ->
		{
			Main.instance.addRequest(new AccountPacket("signIn", Main.macAddress, null, AccountsPart.account.get(), new Account(accountTypeCombobox
					.getSelectionModel().getSelectedItem(), loginField.getText(), passwordField.getText())));
		});
		InitAccountsPart.instance.deleteAccountButton.setOnAction(e ->
		{
			if (passwordField.getText().equals(passwordRepeatField.getText()))
				Main.instance.addRequest(new AccountPacket("deleteAccount", Main.macAddress, null, AccountsPart.account.get(), new Account(accountTypeCombobox
						.getSelectionModel().getSelectedItem(), loginField.getText(), passwordField.getText())));
			else FXDialogsGenerator.showFXDialog(null, null, Main.msgSys.getMsg("passwordsNotMatch"), 1, 1, Main.isFxWindowFrame(), true);
		});
		signOutButton.setOnAction(e ->
		{
			Main.instance.addRequest(new AccountPacket("signOut", Main.macAddress, null, AccountsPart.account.get(), null));
		});
		saveButton.setOnAction(e ->
		{
			if (passwordField.getText().equals(passwordRepeatField.getText()))
			{
				Account account = new Account(accountTypeCombobox.getSelectionModel().getSelectedItem(), loginField.getText(), passwordField.getText());
				account.setSurname(surnameField.getText());
				account.setName(nameField.getText());
				account.setSecondName(secondNameField.getText());
				account.setCountry(countryField.getText());
				account.setRegion(regionField.getText());
				account.setCity(cityField.getText());
				account.setSchool(schoolField.getText());
				account.getSubjects().clear();
				for (Subject subject : selectedsubjectsCombobox)
					account.getSubjects().add(subject);

				account.setMaritalStatus(maritalStatusField.getText());
				account.getGrandParents().addAll(sToR(grandParentsCombobox.getItems()));
				account.getParents().addAll(sToR(parentsCombobox.getItems()));
				account.getChildren().addAll(sToR(childrenCombobox.getItems()));
				account.getGrandChildren().addAll(sToR(grandChildrenCombobox.getItems()));
				account.getSiblings().addAll(sToR(siblingsCombobox.getItems()));
				account.getExSpouses().addAll(sToR(exSpousesCombobox.getItems()));
				account.setSpouse(sToR(spouseField.getText()));
				account.getOtherRelatives().addAll(sToR(otherRelativesCombobox.getItems()));

				account.getPhoneNumbers().addAll(phoneNumbersCombobox.getItems());
				account.getEmails().addAll(emailsCombobox.getItems());
				account.getPersonalSites().addAll(personalSitesCombobox.getItems());
				account.getOtherSites().addAll(otherSitesCombobox.getItems());
				account.getOtherContacts().addAll(otherContactsCombobox.getItems());

				account.setAge(ageField.getText());
				account.setGender(genderCombobox.getSelectionModel().getSelectedItem());
				account.getMainLanguages().addAll(mainLanguagesCombobox.getItems());
				account.getOtherLanguages().addAll(otherLanguagesCombobox.getItems());
				account.setEducation(educationArea.getText());
				account.setCarriere(careerArea.getText());

				account.setAboutAlhogol(aboutAlhogolField.getText());
				account.setAboutNarcotics(aboutNarcoticsField.getText());
				account.setAboutSmoking(aboutSmokingField.getText());
				account.setIdeas(ideasField.getText());
				account.setInterests(interestsField.getText());
				account.setFavouriteBlogs(favouriteBlogsField.getText());
				account.setFavouriteBooks(favouriteBooksField.getText());
				account.setFavouriteComputerGames(favouriteComputerGamesField.getText());
				account.setFavouriteFilms(favouriteFilmsField.getText());
				account.setFavouriteGames(favouriteGamesField.getText());
				account.setFavouriteMusic(favouriteMusicField.getText());
				account.setFavouritePeople(favouritePeopleField.getText());
				account.setFavouriteShows(favouriteShowsField.getText());
				account.setFavouriteQuotes(favouriteQuotesField.getText());
				account.setMainInLife(mainInLifeField.getText());
				account.setMainInPeople(mainInPeopleField.getText());
				account.setWorldOutlook(worldOutlookField.getText());
				account.setOtherViews(otherViewsField.getText());
				account.setPoliticalViews(politicalViewsField.getText());
				account.setInspiration(inspirationField.getText());

				account.setBiografy(biografyArea.getText());
				account.setHomeCountry(homeCountryField.getText());
				account.setHomeRegion(homeRegionField.getText());
				account.setHomeCity(homeCityField.getText());
				Main.instance.addRequest(new AccountPacket("changeProfileInfo", Main.macAddress, null, account, account));
			}
			else FXDialogsGenerator.showFXDialog(null, null, Main.msgSys.getMsg("passwordsNotMatch"), 1, 1, Main.isFxWindowFrame(), true);
		});
		changePasswordButton.setOnAction(e ->
		{
			if (newPasswordField.getText().equals(newPasswordRepeatField.getText()) && passwordField.getText().equals(passwordRepeatField.getText()))
				Main.instance.addRequest(new AccountPacket("changeSecurityInfo", Main.macAddress, null, AccountsPart.account.get(), new Account(
						accountTypeCombobox.getSelectionModel().getSelectedItem(), loginField.getText(), newPasswordField.getText())));
			else FXDialogsGenerator.showFXDialog(null, null, Main.msgSys.getMsg("passwordsNotMatch"), 1, 1, Main.isFxWindowFrame(), true);
		});

		signUpTab.setOnSelectionChanged(e -> handleTabSelect());
		signInTab.setOnSelectionChanged(e -> handleTabSelect());
		deleteAccountTab.setOnSelectionChanged(e -> handleTabSelect());
		signOutTab.setOnSelectionChanged(e -> handleTabSelect());
		profileTab.setOnSelectionChanged(e -> handleTabSelect());
		changeVisibleTabs(AccountsPart.account.get());
	}

	CheckMenuItem showAllTabsItem;

	@FXML
	public void initialize()
	{
		instance = this;
		profileTabs.setContextMenu(new ContextMenu(showAllTabsItem = new CheckMenuItem()));
		showAllTabsItem.setOnAction(e ->
		{
			if (showAllTabsItem.isSelected())
			{
				if (!profileTabs.getTabs().contains(familyTab))
					profileTabs.getTabs().add(familyTab);
				if (!profileTabs.getTabs().contains(contactsTab))
					profileTabs.getTabs().add(contactsTab);
				if (!profileTabs.getTabs().contains(lifeTab))
					profileTabs.getTabs().add(lifeTab);
				if (!profileTabs.getTabs().contains(ideasTab))
					profileTabs.getTabs().add(ideasTab);
				if (!profileTabs.getTabs().contains(aboutYouTab))
					profileTabs.getTabs().add(aboutYouTab);
			}
			else
			{
				if (profileTabs.getTabs().contains(familyTab))
					profileTabs.getTabs().remove(familyTab);
				if (profileTabs.getTabs().contains(contactsTab))
					profileTabs.getTabs().remove(contactsTab);
				if (profileTabs.getTabs().contains(lifeTab))
					profileTabs.getTabs().remove(lifeTab);
				if (profileTabs.getTabs().contains(ideasTab))
					profileTabs.getTabs().remove(ideasTab);
				if (profileTabs.getTabs().contains(aboutYouTab))
					profileTabs.getTabs().remove(aboutYouTab);
			}
		});
		showAllTabsItem.setSelected(false);
		showAllTabsItem.fire();
		subjectsCombobox.getItems().clear();
		for (Subject subject : Subject.values())
			subjectsCombobox.getItems().add(new MenuItem("   \t" + subject.name()));
		genderCombobox.getItems().add("Man");
		genderCombobox.getItems().add("Woman");
		accountTypeCombobox.getItems().clear();
		for (AccountType accountType : AccountType.values())
			accountTypeCombobox.getItems().add(accountType);
		accountTypeCombobox.getSelectionModel().select(AccountType.Student);

		int w1 = 190, h1 = 30;
		parentsCombobox = new ComboboxWithAdd();
		familyFields.add(parentsCombobox, 1, 1);
		parentsCombobox.setPrefSize(w1, h1);
		grandParentsCombobox = new ComboboxWithAdd();
		familyFields.add(grandParentsCombobox, 1, 2);
		grandParentsCombobox.setPrefSize(w1, h1);
		childrenCombobox = new ComboboxWithAdd();
		familyFields.add(childrenCombobox, 1, 3);
		childrenCombobox.setPrefSize(w1, h1);
		grandChildrenCombobox = new ComboboxWithAdd();
		familyFields.add(grandChildrenCombobox, 1, 4);
		grandChildrenCombobox.setPrefSize(w1, h1);
		siblingsCombobox = new ComboboxWithAdd();
		familyFields.add(siblingsCombobox, 1, 5);
		siblingsCombobox.setPrefSize(w1, h1);
		exSpousesCombobox = new ComboboxWithAdd();
		familyFields.add(exSpousesCombobox, 1, 6);
		exSpousesCombobox.setPrefSize(w1, h1);
		otherRelativesCombobox = new ComboboxWithAdd();
		familyFields.add(otherRelativesCombobox, 1, 8);
		otherRelativesCombobox.setPrefSize(w1, h1);

		int w2 = 125, h2 = 30;
		mainLanguagesCombobox = new ComboboxWithAdd();
		lifeFields.add(mainLanguagesCombobox, 1, 2);
		mainLanguagesCombobox.setPrefSize(w2, h2);
		otherLanguagesCombobox = new ComboboxWithAdd();
		lifeFields.add(otherLanguagesCombobox, 1, 3);
		otherLanguagesCombobox.setPrefSize(w2, h2);

		int w3 = 130, h3 = 30;
		emailsCombobox = new ComboboxWithAdd();
		contactsFields.add(emailsCombobox, 1, 0);
		emailsCombobox.setPrefSize(w3, h3);
		phoneNumbersCombobox = new ComboboxWithAdd();
		contactsFields.add(phoneNumbersCombobox, 1, 1);
		phoneNumbersCombobox.setPrefSize(w3, h3);
		personalSitesCombobox = new ComboboxWithAdd();
		contactsFields.add(personalSitesCombobox, 1, 2);
		personalSitesCombobox.setPrefSize(w3, h3);
		otherSitesCombobox = new ComboboxWithAdd();
		contactsFields.add(otherSitesCombobox, 1, 3);
		otherSitesCombobox.setPrefSize(w3, h3);
		otherContactsCombobox = new ComboboxWithAdd();
		contactsFields.add(otherContactsCombobox, 1, 4);
		otherContactsCombobox.setPrefSize(w3, h3);
	}

	public void handleTabSelect()
	{
		if (signUpTab.isSelected())
		{
			loginField.setDisable(false);
			accountTypeCombobox.setDisable(false);
			accountTypeCombobox.setVisible(true);
			passwordRepeatField.setVisible(true);
		}
		if (signInTab.isSelected())
		{
			loginField.setDisable(false);
			accountTypeCombobox.setDisable(true);
			accountTypeCombobox.setVisible(false);
			passwordRepeatField.setVisible(false);
		}
		if (deleteAccountTab.isSelected())
		{
			loginField.setDisable(false);
			accountTypeCombobox.setDisable(true);
			accountTypeCombobox.setVisible(true);
			passwordRepeatField.setVisible(true);
		}
		if (signOutTab.isSelected())
		{
			loginField.setDisable(true);
			accountTypeCombobox.setDisable(true);
			accountTypeCombobox.setVisible(true);
			passwordRepeatField.setVisible(false);
		}
		if (profileTab.isSelected())
		{
			loginField.setDisable(true);
			accountTypeCombobox.setDisable(true);
			accountTypeCombobox.setVisible(true);
			passwordRepeatField.setVisible(true);
		}
		passwordRepeatField.setText("");
	}

	public void changeVisibleTabs(Account account)
	{
		ObservableList<Tab> tabs = tabPane.getTabs();
		changeTabVisibility(tabs, account == null, signUpTab);
		changeTabVisibility(tabs, account == null, signInTab);
		changeTabVisibility(tabs, account != null, deleteAccountTab);
		changeTabVisibility(tabs, account != null, signOutTab);
		changeTabVisibility(tabs, account != null, profileTab);
	}

	public void changeTabVisibility(ObservableList<Tab> tabs, boolean visible, Tab tab)
	{
		if (visible && !tabs.contains(tab))
			tabs.add(tab);
		if (!visible && tabs.contains(tab))
			tabs.remove(tab);

	}

	public void fillFieldsOfAccount(Account account)
	{
		surnameField.setText(account.getSurname());
		nameField.setText(account.getName());
		secondNameField.setText(account.getSecondName());
		countryField.setText(account.getCountry());
		regionField.setText(account.getRegion());
		cityField.setText(account.getCity());
		schoolField.setText(account.getSchool());
		selectedsubjectsCombobox.clear();
		for (Subject subject : account.getSubjects())
			selectedsubjectsCombobox.add(subject);
		updateSelectedsubjectsCombobox();

		maritalStatusField.setText(account.getMaritalStatus());
		grandParentsCombobox.getItems().addAll(rToS(account.getGrandParents()));
		parentsCombobox.getItems().addAll(rToS(account.getParents()));
		childrenCombobox.getItems().addAll(rToS(account.getChildren()));
		grandChildrenCombobox.getItems().addAll(rToS(account.getGrandChildren()));
		siblingsCombobox.getItems().addAll(rToS(account.getSiblings()));
		exSpousesCombobox.getItems().addAll(rToS(account.getExSpouses()));
		spouseField.setText(rToS(account.getSpouse()));
		otherRelativesCombobox.getItems().addAll(rToS(account.getOtherRelatives()));

		phoneNumbersCombobox.getItems().addAll(account.getPhoneNumbers());
		emailsCombobox.getItems().addAll(account.getEmails());
		personalSitesCombobox.getItems().addAll(account.getPersonalSites());
		otherSitesCombobox.getItems().addAll(account.getOtherSites());
		otherContactsCombobox.getItems().addAll(account.getOtherContacts());

		ageField.setText(account.getAge());
		genderCombobox.getSelectionModel().select(account.getGender());
		mainLanguagesCombobox.getItems().addAll(account.getMainLanguages());
		otherLanguagesCombobox.getItems().addAll(account.getOtherLanguages());
		educationArea.setText(account.getEducation());
		careerArea.setText(account.getCarriere());

		worldOutlookField.setText(account.getWorldOutlook());
		politicalViewsField.setText(account.getPoliticalViews());
		mainInLifeField.setText(account.getMainInLife());
		mainInPeopleField.setText(account.getMainInPeople());
		aboutSmokingField.setText(account.getAboutSmoking());
		aboutAlhogolField.setText(account.getAboutAlhogol());
		aboutNarcoticsField.setText(account.getAboutNarcotics());
		interestsField.setText(account.getInterests());
		favouriteMusicField.setText(account.getFavouriteMusic());
		favouriteFilmsField.setText(account.getFavouriteFilms());
		favouriteShowsField.setText(account.getFavouriteShows());
		favouriteBlogsField.setText(account.getFavouriteBlogs());
		favouriteBooksField.setText(account.getFavouriteBooks());
		favouriteGamesField.setText(account.getFavouriteGames());
		favouriteComputerGamesField.setText(account.getFavouriteComputerGames());
		favouritePeopleField.setText(account.getFavouritePeople());
		inspirationField.setText(account.getInspiration());
		favouriteQuotesField.setText(account.getFavouriteQuotes());
		ideasField.setText(account.getIdeas());
		otherViewsField.setText(account.getOtherViews());

		biografyArea.setText(account.getBiografy());
		homeCountryField.setText(account.getHomeCountry());
		homeRegionField.setText(account.getHomeRegion());
		homeCityField.setText(account.getHomeCity());
	}

	private String rToS(Rodstvennik r)
	{
		return r != null ? r.getLogin() + "," + r.getSurname() + "," + r.getName() + "," + r.getSecondName() : "";
	}

	private ArrayList<String> rToS(Collection<Rodstvennik> rs)
	{
		ArrayList<String> ss = new ArrayList<String>();
		for (Rodstvennik r : rs)
			ss.add(rToS(r));
		return ss;
	}

	private ArrayList<Rodstvennik> sToR(Collection<String> ss)
	{
		ArrayList<Rodstvennik> rs = new ArrayList<Rodstvennik>();
		for (String s : ss)
			rs.add(sToR(s));
		return rs;
	}

	private Rodstvennik sToR(String s)
	{
		return new Rodstvennik((s + ", , , ").split(",")[0], (s + ", , , ").split(",")[1], (s + ", , , ").split(",")[2], (s + ", , , ").split(",")[3]);
	}

	int offset = 4;

	void updateSelectedsubjectsCombobox()
	{
		MenuButton subjectsCombobox = InitAccountsPart.instance.subjectsCombobox;
		ArrayList<Subject> selectedsubjectsCombobox = InitAccountsPart.instance.selectedsubjectsCombobox;
		String s = "";
		for (int i = 0; i < subjectsCombobox.getItems().size(); i++)
			for (int j = 0; j < selectedsubjectsCombobox.size(); j++)
				if (subjectsCombobox.getItems().get(i).getText().substring(offset).equals(selectedsubjectsCombobox.get(j).name()))
					s += (s != "" ? "; " : "") + selectedsubjectsCombobox.get(j);
		for (MenuItem item2 : subjectsCombobox.getItems())
			item2.setText((selectedsubjectsCombobox.contains(Subject.valueOf(item2.getText().substring(offset))) ? "  ✔\t" : "   \t") + item2.getText()
					.substring(offset));
		subjectsCombobox.setText(s);
	}
}
