package ru.alexanderdv.schooltester.utilities;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class ComboboxWithAdd extends Pane
{
	private ComboBox<String> combobox;
	private TextField textfield;
	private Button button;
	private EventHandler<ActionEvent> eh;

	public ComboboxWithAdd()
	{
		super();
		combobox = new ComboBox<String>();
		getChildren().add(combobox);
		textfield = new TextField();
		textfield.setVisible(false);
		getChildren().add(textfield);
		button = new Button("+");
		textfield.setOnKeyReleased(e -> button.setText(combobox.getItems().contains(textfield.getText()) ? "-" : (textfield.getText().equals("") ? "<" : "+")));
		button.setOnAction((e) ->
		{
			if (!textfield.isVisible())
			{
				combobox.setVisible(false);
				textfield.setVisible(true);
				textfield.requestFocus();
				textfield.setText(combobox.getSelectionModel().getSelectedItem() != null ? combobox.getSelectionModel().getSelectedItem() : "");
			}
			else
			{
				textfield.setVisible(false);
				combobox.setVisible(true);
				combobox.requestFocus();
				if (combobox.getItems().contains(textfield.getText()))
				{
					combobox.getItems().remove(textfield.getText());
					if (eh != null)
						eh.handle(new ActionEvent("\n" + textfield.getText(), null));
				}
				else if (!textfield.getText().equals(""))
				{
					combobox.getItems().add(textfield.getText());
					combobox.getSelectionModel().select(textfield.getText());
					if (eh != null)
						eh.handle(new ActionEvent(textfield.getText(), null));
				}
			}
			button.setText("+");
		});
		getChildren().add(button);
	}

	/**
	 * @param eh
	 *            the eh to set
	 */
	public void setOnAddOrRemoveElement(EventHandler<ActionEvent> eh)
	{
		this.eh = eh;
	}

	/**
	 * @param eh
	 *            the eh to set
	 */
	public void setOnAction(EventHandler<ActionEvent> eh)
	{
		this.combobox.setOnAction(eh);
	}

	/**
	 * @param eh
	 *            the eh to set
	 */
	public EventHandler<ActionEvent> getOnAction()
	{
		return this.combobox.getOnAction();
	}

	public void resize()
	{
		double width = getPrefWidth(), height = getPrefHeight();
		combobox.setLayoutX(0);
		combobox.setLayoutY(0);
		combobox.setMinSize(width - height, height);
		combobox.setPrefSize(width - height, height);
		combobox.setMaxSize(width - height, height);
		textfield.setLayoutX(0);
		textfield.setLayoutY(0);
		textfield.setMinSize(width - height, height);
		textfield.setPrefSize(width - height, height);
		textfield.setMaxSize(width - height, height);
		button.setLayoutX(width - height);
		button.setLayoutY(0);
		button.setMinSize(height, height);
		button.setPrefSize(height, height);
		button.setMaxSize(height, height);
	}

	@Override
	public void setMinSize(double minWidth, double minHeight)
	{
		super.setMinSize(minWidth, minHeight);
		resize();
	}

	@Override
	public void setPrefSize(double prefWidth, double prefHeight)
	{
		super.setPrefSize(prefWidth, prefHeight);
		resize();
	}

	@Override
	public void setMaxSize(double maxWidth, double maxHeight)
	{
		super.setMaxSize(maxWidth, maxHeight);
		resize();
	}

	public SelectionModel<String> getSelectionModel()
	{
		return combobox.getSelectionModel();
	}

	public ObservableList<String> getItems()
	{
		return combobox.getItems();
	}

}
