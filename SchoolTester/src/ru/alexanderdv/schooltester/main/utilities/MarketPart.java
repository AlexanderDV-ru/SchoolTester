package ru.alexanderdv.schooltester.main.utilities;

import java.net.URL;

import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.network.SearchInMarketPacket;
import ru.alexanderdv.schooltester.utilities.network.SearchInMarketPacket.SearchResult;
import ru.alexanderdv.schooltester.utilities.network.SearchInMarketPacket.TestSearchResult;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class MarketPart extends ProtectedFXWindow
{

	public static MarketPart instance;

	public MarketPart(URL url, boolean inDevelope)
	{
		super(null, url, 1, 2, inDevelope);
		instance = this;
		createActionListeners();
	}

	private void createActionListeners()
	{
		InitMarketPart.instance.searchButton.setOnAction(e -> Main.instance.addSearch(InitMarketPart.instance.searchTextfield.getText(),
				InitMarketPart.instance.pageCombobox.getSelectionModel().getSelectedItem(), InitMarketPart.instance.typeOfSearchCombobox.getSelectionModel()
						.getSelectedItem()));
	}

	public void setSearchResult(SearchInMarketPacket packet)
	{
		InitMarketPart.instance.resultsGrid.getChildren().clear();
		InitMarketPart.instance.resultsGrid.getRowConstraints().clear();
		int ri = 0;
		for (SearchResult result : packet.getResults())
		{
			InitMarketPart.instance.resultsGrid.getRowConstraints().add(new RowConstraints(80, 80, 80, Priority.NEVER, VPos.CENTER, false));
			InitMarketPart.instance.resultsGrid.add(new ImageView(((TestSearchResult) result).getTest().getIcon()), 0, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getName()), 1, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getTestLanguage()), 2, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getDescription()), 3, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getTestVersion()), 4, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getProgramVersion()), 5, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getTestSubject()), 6, ri);
			InitMarketPart.instance.resultsGrid.add(new Label(((TestSearchResult) result).getTest().getTestCreationDate()), 7, ri);
			InitMarketPart.instance.resultsGrid.add(new Button("Load"), 8, ri);
			ri++;
		}
	}

	@Override
	public String name()
	{
		return "market";
	}
}
