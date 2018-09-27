package ru.alexanderdv.schooltester.main.utilities;

import java.io.File;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.alexanderdv.schooltester.main.AccountsPart;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.enums.SearchType;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.MarketUserDoPacket;
import ru.alexanderdv.schooltester.utilities.network.SearchInMarketPacket;
import ru.alexanderdv.schooltester.utilities.types.SearchResult;
import ru.alexanderdv.schooltester.utilities.types.TestSearchResult;
import ru.alexanderdv.schooltester.utilities.types.TestToMarket;
import ru.alexanderdv.simpleutilities.SystemUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class MarketPart extends ProtectedFXWindow
{
	public static MarketPart instance;

	public ComboBox<SearchType> typeCombobox;
	public ComboBox<Integer> pageCombobox;
	public TextField searchTextfield;
	public Button searchButton;
	public TabPane tabPane;
	public Tab marketTab, myTab, sharedTab;
	public VBox marketBox, myBox, sharedBox;

	public MarketPart(boolean inDevelope)
	{
		super(null, Main.createPane(1000, 700), 1, 2, inDevelope, true);
		instance = this;
		initialize();
		createActionHandlers();
	}

	protected void createActionHandlers()
	{
		super.createActionHandlers();
		searchButton.setOnAction(e -> search());
	}

	@Override
	protected void _resize(int w, int h)
	{
		typeCombobox.setLayoutX(Main.spaceBetweenComponents);
		pageCombobox.setLayoutX(typeCombobox.getLayoutX() + typeCombobox.getPrefWidth() + Main.spaceBetweenComponents);
		searchTextfield.setLayoutX(pageCombobox.getLayoutX() + pageCombobox.getPrefWidth() + Main.spaceBetweenComponents);
		searchButton.setLayoutX(w - searchButton.getPrefWidth() - Main.spaceBetweenComponents);
		searchTextfield.setPrefWidth(searchButton.getLayoutX() - Main.spaceBetweenComponents - searchTextfield.getLayoutX());
		tabPane.setPrefWidth(w);

		typeCombobox.setPrefHeight(25);
		pageCombobox.setPrefHeight(25);
		searchTextfield.setPrefHeight(25);
		searchButton.setPrefHeight(25);
		typeCombobox.setLayoutY(Main.spaceBetweenComponents);
		pageCombobox.setLayoutY(Main.spaceBetweenComponents);
		searchTextfield.setLayoutY(Main.spaceBetweenComponents);
		searchButton.setLayoutY(Main.spaceBetweenComponents);
		tabPane.setLayoutY(typeCombobox.getLayoutY() + typeCombobox.getPrefHeight() + Main.spaceBetweenComponents);
		tabPane.setPrefHeight(h - tabPane.getLayoutY());
	}

	private void initialize()
	{
		panel.getChildren().add(typeCombobox = new ComboBox<SearchType>());
		panel.getChildren().add(pageCombobox = new ComboBox<Integer>());
		panel.getChildren().add(searchTextfield = new TextField());
		panel.getChildren().add(searchButton = new Button("Search"));
		panel.getChildren().add(tabPane = new TabPane(marketTab = new Tab("Market", marketBox = new VBox(5)), myTab = new Tab("My", myBox = new VBox(5)),
				sharedTab = new Tab("Shared", sharedBox = new VBox(5))));

		typeCombobox.setPrefWidth(80);
		pageCombobox.setPrefWidth(50);
		searchButton.setPrefWidth(80);

		typeCombobox.getItems().addAll(SearchType.values());
		typeCombobox.getSelectionModel().select(0);
		pageCombobox.getItems().add(1);
		pageCombobox.getSelectionModel().select(0);

		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		resize();
	}

	private void search()
	{
		if (marketTab.isSelected())
			Main.instance.addSearch(instance.searchTextfield.getText(), pageCombobox.getSelectionModel().getSelectedItem(), typeCombobox.getSelectionModel()
					.getSelectedItem());
		else if (myTab.isSelected())
			setSearchResult(searchMy(instance.searchTextfield.getText(), pageCombobox.getSelectionModel().getSelectedItem(), typeCombobox.getSelectionModel()
					.getSelectedItem()), "my");
	}

	private SearchInMarketPacket searchMy(String search, int page, SearchType type)
	{
		TreeMap<Double, SearchResult> results = new TreeMap<Double, SearchResult>((n1, n2) -> n2.compareTo(n1));
		for (File f : new File("Tests").listFiles())
			for (File f2 : f.listFiles())
				if (f2.getName().equals(f.getName() + ".test"))
				{
					// results.put(0, new TestSearchResult(author, test, version, testToMarket, -1, -1, -1, -1));
				}

		SearchResult[] resultsArray = new SearchResult[20];
		int i = 0;
		for (SearchResult result : results.values())
		{
			if (i - (page - 1) * 20 >= 0 && i - (page - 1) * 20 < 20)
				resultsArray[i - (page - 1) * 20] = result;
			i++;
		}
		return new SearchInMarketPacket(search, type, page, resultsArray);
	}

	public void setSearchResult(SearchInMarketPacket packet, String type)
	{
		Platform.runLater(() ->
		{
			VBox resultsBox = type.equals("market") ? marketBox : type.equals("my") ? myBox : type.equals("shared") ? sharedBox : null;
			if (resultsBox == null)
				return;
			resultsBox.getChildren().clear();
			for (SearchResult result : packet.getResults())
				if (result != null)
					if (result instanceof TestSearchResult)
					{
						TestSearchResult testSearchResult = (TestSearchResult) result;
						TestToMarket testToMarket = testSearchResult.getTestToMarket();
						HBox box1 = new HBox(5, new Label(testToMarket.getName()), new Label(testToMarket.getTestLanguage()), new Label(testToMarket
								.getDescription()), new Label(testToMarket.getTestVersion()), new Label(testToMarket.getProgramVersion()), new Label(
										testToMarket.getTestSubject()), new Label(testToMarket.getTestCreationDate()));
						Button load = new Button("Load");
						load.setOnAction(e ->
						{
							Button load2 = new Button("Load");
							load2.setPrefSize(100, 25);
							load2.setOnAction(ev2 ->
							{
								FXDialogsGenerator.closeLast();
								for (String s : testToMarket.getFiles().keySet())
									SystemUtils.writeFile(new File("Tests/" + testToMarket.getDirName() + "/" + s), testToMarket.getFiles().get(s));
								Main.sendToServer(new MarketUserDoPacket("load", result.getType(), result.getAuthor(), result.getTest(), result
										.getVersion(), AccountsPart.account.get().getLogin()));
							});
							FXDialogsGenerator.showFXDialog(stage, new File("Tests/" + testToMarket.getDirName()).exists()
									? "Test already exists! Overrided test can be lost! Override the test file?"
									: "Load test?", load2, true);
						});
						Button like = new Button("Like");
						like.setOnAction(e ->
						{
							Main.sendToServer(new MarketUserDoPacket("like", result.getType(), result.getAuthor(), result.getTest(), result
									.getVersion(), AccountsPart.account.get().getLogin()));
						});
						HBox box2 = new HBox(5, new Label(testSearchResult.getViews() + ""), new Label(testSearchResult.getLikes() + ""), new Label(
								testSearchResult.getLoads() + ""), new Label(testSearchResult.getRate() + ""), like, load);
						VBox vbox = new VBox(5, box1, box2);
						resultsBox.getChildren().add(vbox);
						Main.sendToServer(new MarketUserDoPacket("view", result.getType(), result.getAuthor(), result.getTest(), result.getVersion(),
								AccountsPart.account.get().getLogin()));
					}
					else break;
		});
	}

	@Override
	public String name()
	{
		return "market";
	}
}
