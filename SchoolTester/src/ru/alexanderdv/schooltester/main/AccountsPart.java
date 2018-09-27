package ru.alexanderdv.schooltester.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.alexanderdv.fxutilities.components.ComboboxWithAdd;
import ru.alexanderdv.schooltester.utilities.enums.Subject;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.FXWindow;
import ru.alexanderdv.schooltester.utilities.network.AccountPacket;
import ru.alexanderdv.schooltester.utilities.types.Account;
import ru.alexanderdv.schooltester.utilities.types.Account.AccountType;
import ru.alexanderdv.simpleutilities.SecureContainer;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class AccountsPart extends FXWindow implements ActionListener
{
	public static final SecureContainer<Account> account = new SecureContainer<Account>(null);
	public static AccountsPart instance;

	public TextField loginField, passwordField, passwordRepeatField;

	public ComboBox<AccountType> accountTypeCombobox;

	public TabPane tabPane;

	public Tab signUpTab;

	public Button signUpButton;

	public Tab signInTab;

	public Button signInButton;

	public Tab deleteAccountTab;

	public Button deleteAccountButton;

	public Tab signOutTab;

	public Button signOutButton;

	public Tab profileTab;

	public Tab securityTab;

	public TextField newPasswordField, newPasswordRepeatField;

	public Button changePasswordButton;

	public Tab mainTab;

	public TextField surnameField, nameField, secondNameField, countryField, regionField, cityField, schoolField;

	public MenuButton subjectsCombobox;
	public ArrayList<Subject> selectedsubjectsCombobox = new ArrayList<Subject>();

	public Tab familyTab;

	public Label maritalStatusLabel, grandParentsLabel, parentsLabel, childrenLabel, grandChildrenLabel, siblingsLabel,
			exSpousesLabel, spouseLabel, otherRelativesLabel;

	public TextField maritalStatusField, spouseField;

	public ComboboxWithAdd grandParentsCombobox, parentsCombobox, childrenCombobox, grandChildrenCombobox,
			siblingsCombobox, exSpousesCombobox, otherRelativesCombobox;

	public Tab contactsTab;

	public Label phoneNumbersLabel, emailsLabel, personalSitesLabel, otherSitesLabel, otherContactsLabel;

	public ComboboxWithAdd phoneNumbersCombobox, emailsCombobox, personalSitesCombobox, otherSitesCombobox,
			otherContactsCombobox;

	public Tab lifeTab;

	public Label ageLabel, genderLabel, mainLanguagesLabel, otherLanguagesLabel, educationLabel, careerLabel;

	public TextField ageField;

	public ComboBox<String> genderCombobox;

	public ComboboxWithAdd mainLanguagesCombobox, otherLanguagesCombobox;

	public TextArea educationArea, careerArea;

	public Tab ideasTab;

	public Label aboutAlhogolLabel, aboutNarcoticsLabel, aboutSmokingLabel, ideasLabel, interestsLabel,
			favouriteBlogsLabel, favouriteBooksLabel, favouriteComputerGamesLabel, favouriteFilmsLabel,
			favouriteGamesLabel, favouriteMusicLabel, favouritePeopleLabel, favouriteShowsLabel, favouriteQuotesLabel,
			mainInLifeLabel, mainInPeopleLabel, worldOutlookLabel, otherViewsLabel, politicalViewsLabel,
			inspirationLabel;

	public TextField aboutAlhogolField, aboutNarcoticsField, aboutSmokingField, ideasField, interestsField,
			favouriteBlogsField, favouriteBooksField, favouriteComputerGamesField, favouriteFilmsField,
			favouriteGamesField, favouriteMusicField, favouritePeopleField, favouriteShowsField, favouriteQuotesField,
			mainInLifeField, mainInPeopleField, worldOutlookField, otherViewsField, politicalViewsField,
			inspirationField;

	public Tab aboutYouTab;

	public Label biografyLabel, homeCountryLabel, homeRegionLabel, homeCityLabel;

	public TextArea biografyArea;

	public TextField homeCountryField, homeRegionField, homeCityField;

	public Button saveButton;

	public GridPane profileMainFields, familyFields, contactsFields, lifeFields, lifeFields2, ideasFields,
			aboutYouFields;

	public ScrollPane ideasScrollpane;

	public TabPane profileTabs;

	public AccountsPart(String secondaryTitle, URL url, boolean inDevelope)
	{
		super(secondaryTitle, url, 1, inDevelope, true);
		instance = this;
		createActionHandlers();
	}

	@Override
	protected void createActionHandlers()
	{
		super.createActionHandlers();
		InitAccountsPart.instance.createActionHandlers();
		stage.setOnShown(e -> resize());
	}

	public void handleAccountRequest(AccountPacket packet)
	{
		boolean showDialog = true;
		switch (packet.getRequest())
		{
			case "signUpPerformed":
				account.set(packet.getNewAccount());
				break;

			case "accountAlreadyExists":
				break;

			case "accountInfoChangedByYou":
				if (packet.getOldAccount()._getPasswordHash() == packet.getNewAccount()._getPasswordHash())
					InitAccountsPart.instance.passwordField
							.setText(InitAccountsPart.instance.newPasswordField.getText());
			case "passwordIsInvalid":
				InitAccountsPart.instance.newPasswordField.setText("");
				InitAccountsPart.instance.newPasswordRepeatField.setText("");
				InitAccountsPart.instance.passwordRepeatField.setText("");
				break;

			case "accountInfoChanged":
				account.set(null);
				break;

			case "accountNotExists":
				break;

			case "accountDeleted":
				account.set(null);
				break;

			case "signInPerformed":
				account.set(packet.getNewAccount());
				InitAccountsPart.instance.fillFieldsOfAccount(account.get());
				break;

			case "signOutPerformed":
				account.set(null);
				break;
			default:
				showDialog = false;
				break;
		}
		if (showDialog)
			FXDialogsGenerator.showFXDialog(AccountsPart.instance.stage, msgSys.getMsg(packet.getRequest()), null,
					true);
		InitAccountsPart.instance.changeVisibleTabs(account.get());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("prepareToExit"))
			try
			{
				if (account != null)
					Main.sendToServer(new AccountPacket("signOut", account.get(), null));
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
	}

	public void updateLabels()
	{
		super.updateLabels();
		InitAccountsPart.instance.loginField.setPromptText(msgSys.getMsg("login"));
		InitAccountsPart.instance.passwordField.setPromptText(msgSys.getMsg("password"));
		InitAccountsPart.instance.passwordRepeatField.setPromptText(msgSys.getMsg("passwordRepeat"));

		InitAccountsPart.instance.deleteAccountTab.setText(msgSys.getMsg("deleteAccount"));
		{
			InitAccountsPart.instance.deleteAccountButton.setText(msgSys.getMsg("deleteAccount"));
		}
		InitAccountsPart.instance.signOutTab.setText(msgSys.getMsg("signOut"));
		{
			InitAccountsPart.instance.signOutButton.setText(msgSys.getMsg("signOut"));
		}
		InitAccountsPart.instance.signInTab.setText(msgSys.getMsg("signIn"));
		{
			InitAccountsPart.instance.signInButton.setText(msgSys.getMsg("signIn"));
		}
		InitAccountsPart.instance.signUpTab.setText(msgSys.getMsg("signUp"));
		{
			InitAccountsPart.instance.signUpButton.setText(msgSys.getMsg("signUp"));
		}
		InitAccountsPart.instance.profileTab.setText(msgSys.getMsg("profile"));
		{
			InitAccountsPart.instance.showAllTabsItem.setText(Main.msgSys.getMsg("showAllTabs"));
			InitAccountsPart.instance.securityTab.setText(msgSys.getMsg("security"));
			{
				InitAccountsPart.instance.newPasswordField.setPromptText(msgSys.getMsg("newPassword"));
				InitAccountsPart.instance.newPasswordRepeatField.setPromptText(msgSys.getMsg("newPasswordRepeat"));
				InitAccountsPart.instance.changePasswordButton.setText(msgSys.getMsg("changePassword"));
			}
			InitAccountsPart.instance.mainTab.setText(msgSys.getMsg("main"));
			{
				InitAccountsPart.instance.surnameField.setPromptText(msgSys.getMsg("surname"));
				InitAccountsPart.instance.nameField.setPromptText(msgSys.getMsg("name"));
				InitAccountsPart.instance.secondNameField.setPromptText(msgSys.getMsg("secondName"));
				InitAccountsPart.instance.countryField.setPromptText(msgSys.getMsg("country"));
				InitAccountsPart.instance.regionField.setPromptText(msgSys.getMsg("region"));
				InitAccountsPart.instance.cityField.setPromptText(msgSys.getMsg("city"));
				InitAccountsPart.instance.schoolField.setPromptText(msgSys.getMsg("school"));
				// InitAccountsPart.instance.subjectsCombobox.setText(msgSys.getMsg("subjets"));
			}
			InitAccountsPart.instance.familyTab.setText(msgSys.getMsg("family"));
			{
				InitAccountsPart.instance.maritalStatusLabel.setText(msgSys.getMsg("maritalStatus"));
				InitAccountsPart.instance.grandParentsLabel.setText(msgSys.getMsg("grandParents"));
				InitAccountsPart.instance.parentsLabel.setText(msgSys.getMsg("parents"));
				InitAccountsPart.instance.childrenLabel.setText(msgSys.getMsg("children"));
				InitAccountsPart.instance.grandChildrenLabel.setText(msgSys.getMsg("grandChildren"));
				InitAccountsPart.instance.siblingsLabel.setText(msgSys.getMsg("siblings"));
				InitAccountsPart.instance.exSpousesLabel.setText(msgSys.getMsg("exSpouses"));
				InitAccountsPart.instance.spouseLabel.setText(msgSys.getMsg("spouse"));
				InitAccountsPart.instance.otherRelativesLabel.setText(msgSys.getMsg("otherRelatives"));

				InitAccountsPart.instance.maritalStatusField.setPromptText(msgSys.getMsg("maritalStatus"));
				// InitAccountsPart.instance.grandParentsField.setPromptText(msgSys.getMsg("grandParents"));
				// InitAccountsPart.instance.parentsField.setPromptText(msgSys.getMsg("parents"));
				// InitAccountsPart.instance.childrenField.setPromptText(msgSys.getMsg("children"));
				// InitAccountsPart.instance.grandChildrenField.setPromptText(msgSys.getMsg("grandChildren"));
				// InitAccountsPart.instance.siblingsField.setPromptText(msgSys.getMsg("siblings"));
				// InitAccountsPart.instance.exSpousesField.setPromptText(msgSys.getMsg("exSpouses"));
				InitAccountsPart.instance.spouseField.setPromptText(msgSys.getMsg("spouse"));
				// InitAccountsPart.instance.otherRelativesField.setPromptText(msgSys.getMsg("otherRelatives"));
			}
			InitAccountsPart.instance.contactsTab.setText(msgSys.getMsg("contacts"));
			{
				InitAccountsPart.instance.phoneNumbersLabel.setText(msgSys.getMsg("phoneNumbers"));
				InitAccountsPart.instance.emailsLabel.setText(msgSys.getMsg("emails"));
				InitAccountsPart.instance.personalSitesLabel.setText(msgSys.getMsg("personalSites"));
				InitAccountsPart.instance.otherSitesLabel.setText(msgSys.getMsg("otherSites"));
				InitAccountsPart.instance.otherContactsLabel.setText(msgSys.getMsg("otherContacts"));

				// InitAccountsPart.instance.phoneNumbersField.setPromptText(msgSys.getMsg("phoneNumbers"));
				// InitAccountsPart.instance.emailsField.setPromptText(msgSys.getMsg("emails"));
				// InitAccountsPart.instance.personalSitesField.setPromptText(msgSys.getMsg("personalSites"));
				// InitAccountsPart.instance.otherSitesField.setPromptText(msgSys.getMsg("otherSites"));
				// InitAccountsPart.instance.otherContactsField.setPromptText(msgSys.getMsg("otherContacts"));
			}
			InitAccountsPart.instance.lifeTab.setText(msgSys.getMsg("life"));
			{
				InitAccountsPart.instance.ageLabel.setText(msgSys.getMsg("age"));
				InitAccountsPart.instance.genderLabel.setText(msgSys.getMsg("gender"));
				InitAccountsPart.instance.mainLanguagesLabel.setText(msgSys.getMsg("mainLanguages"));
				InitAccountsPart.instance.otherLanguagesLabel.setText(msgSys.getMsg("otherLanguages"));
				InitAccountsPart.instance.educationLabel.setText(msgSys.getMsg("education"));
				InitAccountsPart.instance.careerLabel.setText(msgSys.getMsg("career"));

				InitAccountsPart.instance.ageField.setPromptText(msgSys.getMsg("age"));
				// InitAccountsPart.instance.genderCombobox.setPromptText(msgSys.getMsg("gender"));
				// InitAccountsPart.instance.mainLanguagesField.setPromptText(msgSys.getMsg("mainLanguages"));
				// InitAccountsPart.instance.otherLanguagesField.setPromptText(msgSys.getMsg("otherLanguages"));
				InitAccountsPart.instance.educationArea.setPromptText(msgSys.getMsg("education"));
				InitAccountsPart.instance.careerArea.setPromptText(msgSys.getMsg("career"));
			}
			InitAccountsPart.instance.ideasTab.setText(msgSys.getMsg("ideas"));
			{
				InitAccountsPart.instance.aboutAlhogolLabel.setText(msgSys.getMsg("aboutAlhogol"));
				InitAccountsPart.instance.aboutNarcoticsLabel.setText(msgSys.getMsg("aboutNarcotics"));
				InitAccountsPart.instance.aboutSmokingLabel.setText(msgSys.getMsg("aboutSmoking"));
				InitAccountsPart.instance.ideasLabel.setText(msgSys.getMsg("ideas"));
				InitAccountsPart.instance.interestsLabel.setText(msgSys.getMsg("interests"));
				InitAccountsPart.instance.favouriteBlogsLabel.setText(msgSys.getMsg("favouriteBlogs"));
				InitAccountsPart.instance.favouriteBooksLabel.setText(msgSys.getMsg("favouriteBooks"));
				InitAccountsPart.instance.favouriteComputerGamesLabel.setText(msgSys.getMsg("favouriteComputerGames"));
				InitAccountsPart.instance.favouriteFilmsLabel.setText(msgSys.getMsg("favouriteFilms"));
				InitAccountsPart.instance.favouriteGamesLabel.setText(msgSys.getMsg("favouriteGames"));
				InitAccountsPart.instance.favouriteMusicLabel.setText(msgSys.getMsg("favouriteMusic"));
				InitAccountsPart.instance.favouritePeopleLabel.setText(msgSys.getMsg("favouritePeople"));
				InitAccountsPart.instance.favouriteShowsLabel.setText(msgSys.getMsg("favouriteShows"));
				InitAccountsPart.instance.favouriteQuotesLabel.setText(msgSys.getMsg("favouriteQuotes"));
				InitAccountsPart.instance.mainInLifeLabel.setText(msgSys.getMsg("mainInLife"));
				InitAccountsPart.instance.mainInPeopleLabel.setText(msgSys.getMsg("mainInPeople"));
				InitAccountsPart.instance.worldOutlookLabel.setText(msgSys.getMsg("worldOutlook"));
				InitAccountsPart.instance.otherViewsLabel.setText(msgSys.getMsg("otherViews"));
				InitAccountsPart.instance.politicalViewsLabel.setText(msgSys.getMsg("politicalViews"));
				InitAccountsPart.instance.inspirationLabel.setText(msgSys.getMsg("inspiration"));

				InitAccountsPart.instance.aboutAlhogolField.setPromptText(msgSys.getMsg("aboutAlhogol"));
				InitAccountsPart.instance.aboutNarcoticsField.setPromptText(msgSys.getMsg("aboutNarcotics"));
				InitAccountsPart.instance.aboutSmokingField.setPromptText(msgSys.getMsg("aboutSmoking"));
				InitAccountsPart.instance.ideasField.setPromptText(msgSys.getMsg("ideas"));
				InitAccountsPart.instance.interestsField.setPromptText(msgSys.getMsg("interests"));
				InitAccountsPart.instance.favouriteBlogsField.setPromptText(msgSys.getMsg("favouriteBlogs"));
				InitAccountsPart.instance.favouriteBooksField.setPromptText(msgSys.getMsg("favouriteBooks"));
				InitAccountsPart.instance.favouriteComputerGamesField
						.setPromptText(msgSys.getMsg("favouriteComputerGames"));
				InitAccountsPart.instance.favouriteFilmsField.setPromptText(msgSys.getMsg("favouriteFilms"));
				InitAccountsPart.instance.favouriteGamesField.setPromptText(msgSys.getMsg("favouriteGames"));
				InitAccountsPart.instance.favouriteMusicField.setPromptText(msgSys.getMsg("favouriteMusic"));
				InitAccountsPart.instance.favouritePeopleField.setPromptText(msgSys.getMsg("favouritePeople"));
				InitAccountsPart.instance.favouriteShowsField.setPromptText(msgSys.getMsg("favouriteShows"));
				InitAccountsPart.instance.favouriteQuotesField.setPromptText(msgSys.getMsg("favouriteQuotes"));
				InitAccountsPart.instance.mainInLifeField.setPromptText(msgSys.getMsg("mainInLife"));
				InitAccountsPart.instance.mainInPeopleField.setPromptText(msgSys.getMsg("mainInPeople"));
				InitAccountsPart.instance.worldOutlookField.setPromptText(msgSys.getMsg("worldOutlook"));
				InitAccountsPart.instance.otherViewsField.setPromptText(msgSys.getMsg("otherViews"));
				InitAccountsPart.instance.politicalViewsField.setPromptText(msgSys.getMsg("politicalViews"));
				InitAccountsPart.instance.inspirationField.setPromptText(msgSys.getMsg("inspiration"));
			}
			InitAccountsPart.instance.aboutYouTab.setText(msgSys.getMsg("aboutYou"));
			{
				InitAccountsPart.instance.biografyLabel.setText(msgSys.getMsg("biografy"));
				InitAccountsPart.instance.homeCountryLabel.setText(msgSys.getMsg("homeCountry"));
				InitAccountsPart.instance.homeRegionLabel.setText(msgSys.getMsg("homeRegion"));
				InitAccountsPart.instance.homeCityLabel.setText(msgSys.getMsg("homeCity"));

				InitAccountsPart.instance.biografyArea.setPromptText(msgSys.getMsg("biografy"));
				InitAccountsPart.instance.homeCountryField.setPromptText(msgSys.getMsg("homeCountry"));
				InitAccountsPart.instance.homeRegionField.setPromptText(msgSys.getMsg("homeRegion"));
				InitAccountsPart.instance.homeCityField.setPromptText(msgSys.getMsg("homeCity"));
			}
			InitAccountsPart.instance.saveButton.setText(msgSys.getMsg("save"));
		}
	}

	@Override
	protected void _resize(int w, int h)
	{
		InitAccountsPart.instance.resize(w, h);
	}

	@Override
	public String name()
	{
		return "accountsSystem";
	}
}
