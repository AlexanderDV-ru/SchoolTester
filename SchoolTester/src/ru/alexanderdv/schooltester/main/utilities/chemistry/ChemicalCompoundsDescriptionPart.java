package ru.alexanderdv.schooltester.main.utilities.chemistry;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.simpleutilities.MathUtils;
import ru.alexanderdv.chemistryutilities.ChemicalElement;
import ru.alexanderdv.chemistryutilities.ChemistryUtils;
import ru.alexanderdv.chemistryutilities.Localization;
import ru.alexanderdv.chemistryutilities.exceptions.IndexLessThanOneErrorInFormulaException;
import ru.alexanderdv.chemistryutilities.exceptions.InertGasCompoundErrorInFormulaException;
import ru.alexanderdv.chemistryutilities.exceptions.NotClosedBracketErrorInFormulaException;
import ru.alexanderdv.chemistryutilities.exceptions.WrongBracketOrderErrorInFormulaException;
import ru.alexanderdv.fxutilities.components.SignsAfterCommaCombobox;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ChemicalCompoundsDescriptionPart extends ProtectedFXWindow
{
	public static ChemicalCompoundsDescriptionPart instance;
	private final TextField formulaTextfield;
	private final TextField nameTextfield;
	private final Button createDescriptionButton;
	private final TextArea descriptionTextarea;
	private final RadioButton namesInLatinRadio;
	private final RadioButton namesInSelectedLanguageRadio;
	private final SignsAfterCommaCombobox signsAfterCommaCombobox;

	public ChemicalCompoundsDescriptionPart()
	{
		super(null, Main.createPane(400, 300), 1, 1, false, true);
		instance = this;
		// Init
		getPanel().getChildren().add(namesInLatinRadio = new RadioButton());
		getPanel().getChildren().add(namesInSelectedLanguageRadio = new RadioButton());
		getPanel().getChildren().add(formulaTextfield = new TextField());
		getPanel().getChildren().add(nameTextfield = new TextField());
		getPanel().getChildren().add(signsAfterCommaCombobox = new SignsAfterCommaCombobox());
		getPanel().getChildren().add(createDescriptionButton = new Button());
		getPanel().getChildren().add(descriptionTextarea = new TextArea());
		// Init end
		// Setting
		descriptionTextarea.setEditable(false);
		// Setting end
		createActionHandlers();
		stage.setOnShowing(e -> resize());

		ToggleGroup group = new ToggleGroup();
		namesInLatinRadio.setToggleGroup(group);
		namesInSelectedLanguageRadio.setToggleGroup(group);
	}

	@Override
	protected void _resize(int w, int h)
	{
		int i = 0;
		double height = 20, dst = 5, offset = height + dst;
		double height2 = 30, dst2 = 5, offset2 = height2 + dst2;
		double height3 = 25, dst3 = 5, offset3 = height3 + dst3;
		namesInLatinRadio.setLayoutX(0);
		namesInLatinRadio.setLayoutY(i);
		namesInLatinRadio.setPrefSize(w, height);

		namesInSelectedLanguageRadio.setLayoutX(0);
		namesInSelectedLanguageRadio.setLayoutY(i += offset);
		namesInSelectedLanguageRadio.setPrefSize(w, height);

		formulaTextfield.setLayoutX(0);
		formulaTextfield.setLayoutY(i += offset2);
		formulaTextfield.setPrefSize(w / 2, height2);

		nameTextfield.setLayoutX(w / 2);
		nameTextfield.setLayoutY(i);
		nameTextfield.setPrefSize(w / 2, height2);

		createDescriptionButton.setLayoutY(i += offset2);
		centrizeByText(createDescriptionButton, w, height3);

		descriptionTextarea.setLayoutX(0);
		descriptionTextarea.setLayoutY(i += offset3);
		descriptionTextarea.setPrefSize(w, h - descriptionTextarea.getLayoutY());
	}

	@Override
	protected void createActionHandlers()
	{
		super.createActionHandlers();
		formulaTextfield.setOnKeyReleased(e ->
		{
			int caretPos = formulaTextfield.getCaretPosition();
			formulaTextfield.setText(formulaTextfield.getText().replace("0", "\u2080").replace("1", "\u2081")
					.replace("2", "\u2082").replace("3", "\u2083").replace("4", "\u2084").replace("5", "\u2085")
					.replace("6", "\u2086").replace("7", "\u2087").replace("8", "\u2088").replace("9", "\u2089"));
			formulaTextfield.positionCaret(caretPos);
		});
		createDescriptionButton.setOnAction(e ->
		{
			if (nameTextfield.getText().replace(" ", "").equals(""))
				nameTextfield.setText(formulaTextfield.getText());
			boolean namesInSelectedLanguage = namesInSelectedLanguageRadio.isSelected();
			String formula = formulaTextfield.getText().replace("\u2080", "0").replace("\u2081", "1")
					.replace("\u2082", "2").replace("\u2083", "3").replace("\u2084", "4").replace("\u2085", "5")
					.replace("\u2086", "6").replace("\u2087", "7").replace("\u2088", "8").replace("\u2089", "9");
			boolean b = false;
			for (char c : formula.toCharArray())
				if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != '(' && c != ')')
				{
					descriptionTextarea.setText(msgSys.getMsg("mustBeFormula").replace("%1", formula));
					return;
				}
				else if (Character.isUpperCase(c))
					b = true;
			if (!b)
			{
				descriptionTextarea.setText(msgSys.getMsg("mustBeFormula").replace("%1", formula));
				return;
			}
			boolean containsInert = false;
			try
			{
				HashMap<ChemicalElement, Integer> elements = ChemistryUtils.parseChemicalFormulaToElementsList(formula);
				double molecularMass = 0;
				for (ChemicalElement el : elements.keySet())
					molecularMass += el.getRelativeMass() * elements.get(el);
				String elementsStr = "";
				String amountStr = "";
				String molecularMassStr = "";
				String propStr = "";
				String massDolesStr = "";
				HashMap<ChemicalElement, ArrayList<ChemicalElement>> used = new HashMap<ChemicalElement, ArrayList<ChemicalElement>>();
				for (ChemicalElement el : elements.keySet())
				{
					elementsStr += (namesInSelectedLanguage ? Localization.getMsg(el.getNaming(), msgSys.getLanguage())
							: el.getNaming()) + "(" + el.getSymbol() + ")"
							+ (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? ", "
									: "");
					amountStr += (namesInSelectedLanguage ? Localization.getMsg(el.getNaming(), msgSys.getLanguage())
							: el.getNaming()) + "(" + el.getSymbol() + ")" + " - " + elements.get(el) + " "
							+ msgSys.getMsg("atoms")
							+ (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? ", "
									: "");
					molecularMassStr += signsAfterCommaCombobox.doubleToString(el.getRelativeMass() * elements.get(el))
							+ "(" + el.getSymbol() + ", " + signsAfterCommaCombobox.doubleToString(el.getRelativeMass())
							+ " * " + elements.get(el) + ")"
							+ (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? " + "
									: " = " + signsAfterCommaCombobox.doubleToString(molecularMass));
					for (ChemicalElement el2 : elements.keySet())
						if (el != el2)
							if (used.containsKey(el2) ? !used.get(el2).contains(el) : true)
							{
								propStr += el.getSymbol() + ":" + el2.getSymbol() + "="
										+ signsAfterCommaCombobox
												.doubleToString(el.getRelativeMass() * elements.get(el))
										+ ":"
										+ signsAfterCommaCombobox
												.doubleToString(el2.getRelativeMass() * elements.get(el2))
										+ (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] == el
												&& elements.keySet().toArray(new ChemicalElement[0])[elements.size()
														- 2] == el2 ? "" : ", ");
								if (!used.containsKey(el))
									used.put(el, new ArrayList<ChemicalElement>());
								used.get(el).add(el2);
							}
					massDolesStr += el.getSymbol() + "~"
							+ signsAfterCommaCombobox
									.doubleToString(el.getRelativeMass() * elements.get(el) / molecularMass * 100)
							+ "%"
							+ (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? "," : "");
				}
				String description = "\t" + formulaTextfield.getText() + "\n";
				description += "1. " + msgSys.getMsg("specificSubstance").replace("%1", nameTextfield.getText()) + "\n";
				description += "2. " + msgSys.getMsg("qualitativeComposition").replace("%1", elements.size() + "")
						.replace("%2", elementsStr) + "\n";
				ChemicalElement key = elements.keySet().toArray(new ChemicalElement[0])[0];
				String type = (elements.size() > 1 ? "�������"
						: (elements.get(key) > 1 || key.isInert() ? "�������" : "��������� ����"));
				description += "3. " + msgSys.getMsg("substanceType").replace("%1", type) + "\n";
				description += "4. " + msgSys.getMsg("quantitativeComposition").replace("%1", amountStr) + "\n";
				description += "5. " + msgSys.getMsg("relativeMolecularMass").replace("%1", molecularMassStr) + "\n";
				description += "6. " + msgSys.getMsg("elementsMassRatio").replace("%1", propStr) + "\n";
				description += "7. " + msgSys.getMsg("elementsMassFractions").replace("%1", massDolesStr);
				descriptionTextarea.setText(description);
			}
			catch (IndexLessThanOneErrorInFormulaException e1)
			{
				descriptionTextarea.setText(msgSys.getMsg("indexLessThanOneErrorInFormula")
						.replace("%1",
								e1.getMessage().substring(e1.getMessage().indexOf(" (") + 2,
										e1.getMessage().indexOf(") ")))
						.replace("%2", e1.getMessage().substring("Index of ".length(), e1.getMessage().indexOf(" ("))));
			}
			catch (InertGasCompoundErrorInFormulaException e1)
			{
				descriptionTextarea.setText(msgSys.getMsg("inertGasCompoundErrorInFormula"));
			}
			catch (NotClosedBracketErrorInFormulaException e1)
			{
				descriptionTextarea.setText(msgSys.getMsg("inertGasCompoundErrorInFormula"));
			}
			catch (WrongBracketOrderErrorInFormulaException e1)
			{
				descriptionTextarea.setText(msgSys.getMsg("wrongBracketOrderErrorInFormula"));
			}
		});
	}

	@Override
	public void updateLabels()
	{
		super.updateLabels();
		formulaTextfield.setPromptText(msgSys.getMsg("enterFormula"));
		nameTextfield.setPromptText(msgSys.getMsg("enterName"));
		namesInLatinRadio.setText(msgSys.getMsg("namesInLatin"));
		namesInSelectedLanguageRadio.setText(msgSys.getMsg("namesInSelectedLanguage"));
		signsAfterCommaCombobox.setText(msgSys.getMsg("signsAfterComma"));
		createDescriptionButton.setText(msgSys.getMsg("createDescription"));
	}

	@Override
	public String name()
	{
		return "chemicalCompoundsDescriptor";
	}

}
