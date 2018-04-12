package ru.alexanderdv.schooltester.main.utilities;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.5a
 */
public class InitMarketPart
{
	public static InitMarketPart instance;

	public ComboBox<SearchType> typeOfSearchCombobox;
	public ComboBox<Integer> pageCombobox;
	public TextField searchTextfield;
	public Button searchButton;
	public GridPane resultsGrid;

	@FXML
	private void initialize()
	{
		instance = this;
		typeOfSearchCombobox.getItems().addAll(SearchType.values());
		typeOfSearchCombobox.getSelectionModel().select(0);
		pageCombobox.getItems().add(1);
		pageCombobox.getSelectionModel().select(0);
		resultsGrid.getColumnConstraints().clear();
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(48, 48, 48, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(80, 80, 80, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(48, 48, 48, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(120, 120, 120, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(48, 48, 48, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(48, 48, 48, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(48, 48, 48, Priority.NEVER, HPos.CENTER, false));
		resultsGrid.getColumnConstraints().add(new ColumnConstraints(48, 48, 48, Priority.NEVER, HPos.CENTER, false));
	}

	public static enum SearchType
	{
		Test
	}

}
