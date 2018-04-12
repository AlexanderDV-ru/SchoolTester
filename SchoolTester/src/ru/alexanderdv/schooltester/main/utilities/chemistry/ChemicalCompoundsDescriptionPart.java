package ru.alexanderdv.schooltester.main.utilities.chemistry;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.simpleutilities.MathWithText;
import ru.alexanderdv.chemistryutilities.ChemicalElement;
import ru.alexanderdv.chemistryutilities.Localization;
/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 *@version 5.9.5a
 */
public class ChemicalCompoundsDescriptionPart extends ProtectedFXWindow
{
	public static ChemicalCompoundsDescriptionPart instance;

	public ChemicalCompoundsDescriptionPart(URL url,boolean inDevelope)
	{
		super(null, url, 1, 1,inDevelope);
		instance = this;
		createActionListeners();
	}

	private void createActionListeners()
	{
		InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.setOnKeyReleased(e ->
		{
			int caretPos = InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.getCaretPosition();
			InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.setText(InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.getText()
					.replace("0", "\u2080").replace("1", "\u2081").replace("2", "\u2082").replace("3", "\u2083").replace("4", "\u2084").replace("5", "\u2085")
					.replace("6", "\u2086").replace("7", "\u2087").replace("8", "\u2088").replace("9", "\u2089"));
			InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.positionCaret(caretPos);
		});
		InitChemicalCompoundsDescriptionPart.instance.createDescriptionButton.setOnAction(e ->
		{
			if (InitChemicalCompoundsDescriptionPart.instance.nameTextfield.getText().replace(" ", "").equals(""))
				InitChemicalCompoundsDescriptionPart.instance.nameTextfield.setText(InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.getText());
			boolean namesInSelectedLanguage = InitChemicalCompoundsDescriptionPart.instance.namesInSelectedLanguageRadio.isSelected();
			String formula = InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.getText().replace("\u2080", "0").replace("\u2081", "1").replace(
					"\u2082", "2").replace("\u2083", "3").replace("\u2084", "4").replace("\u2085", "5").replace("\u2086", "6").replace("\u2087", "7").replace(
							"\u2088", "8").replace("\u2089", "9");
			boolean b = false;
			for (char c : formula.toCharArray())
				if (!Character.isAlphabetic(c) && !Character.isDigit(c))
				{
					InitChemicalCompoundsDescriptionPart.instance.descriptionTextarea.setText(msgSys.getMsg("errorInFormula").replace("%1", formula));
					return;
				}
				else if (Character.isUpperCase(c))
					b = true;
			if (!b)
			{
				InitChemicalCompoundsDescriptionPart.instance.descriptionTextarea.setText(msgSys.getMsg("errorInFormula").replace("%1", formula));
				return;
			}
			boolean containsInert = false;
			HashMap<ChemicalElement, Integer> elements = new HashMap<ChemicalElement, Integer>();
			for (ChemicalElement el : ChemicalElement.values())
				if (formula.indexOf(el.getSymbol()) != -1 && !Character.isLowerCase((formula + "\n").charAt(formula.indexOf(el.getSymbol()) + el.getSymbol()
						.length())))
				{
					String cur = MathWithText.getAfter(formula, formula.indexOf(el.getSymbol()));
					if (cur.length() == 0)
						cur = "1";
					int curAmount = Integer.parseInt(cur);
					if (curAmount < 1)
					{
						InitChemicalCompoundsDescriptionPart.instance.descriptionTextarea.setText(msgSys.getMsg("errorInFormulaAtomsLessThanOne").replace("%1",
								formula));
						return;
					}
					int amount = (elements.containsKey(el) ? elements.get(el) : 0) + curAmount;
					if (el.isInert() && (containsInert || amount > 1))
					{
						InitChemicalCompoundsDescriptionPart.instance.descriptionTextarea.setText(msgSys.getMsg("errorInFormulaInertGas").replace("%1",
								formula));
						return;
					}
					if (el.isInert())
						containsInert = true;
					elements.put(el, amount);
				}
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
				elementsStr += (namesInSelectedLanguage ? Localization.getMsg(el.getNaming(), msgSys.getLanguage()) : el.getNaming()) + "(" + el.getSymbol()
						+ ")" + (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? ", " : "");
				amountStr += (namesInSelectedLanguage ? Localization.getMsg(el.getNaming(), msgSys.getLanguage()) : el.getNaming()) + "(" + el.getSymbol() + ")"
						+ " - " + elements.get(el) +" "+ msgSys.getMsg("atoms") + (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? ", " : "");
				molecularMassStr += toStr(el.getRelativeMass() * elements.get(el)) + "(" + el.getSymbol() + ", " + toStr(el.getRelativeMass()) + " * "
						+ elements.get(el) + ")" + (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] != el ? " + "
								: " = " + toStr(molecularMass));
				for (ChemicalElement el2 : elements.keySet())
					if (el != el2)
						if (used.containsKey(el2) ? !used.get(el2).contains(el) : true)
						{
							propStr += el.getSymbol() + ":" + el2.getSymbol() + "=" + toStr(el.getRelativeMass() * elements.get(el)) + ":" + toStr(el2
									.getRelativeMass() * elements.get(el2)) + (elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 1] == el
											&& elements.keySet().toArray(new ChemicalElement[0])[elements.size() - 2] == el2 ? "" : ", ");
							if (!used.containsKey(el))
								used.put(el, new ArrayList<ChemicalElement>());
							used.get(el).add(el2);
						}
				massDolesStr += el.getSymbol() + "~" + toStr(el.getRelativeMass() * elements.get(el) / molecularMass * 100) + "%" + (elements.keySet().toArray(
						new ChemicalElement[0])[elements.size() - 1] != el ? "," : "");
			}
			String description = "\t" + InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.getText() + "\n";
			description += "1. " + msgSys.getMsg("specificSubstance").replace("%1", InitChemicalCompoundsDescriptionPart.instance.nameTextfield.getText())
					+ "\n";
			description += "2. " + msgSys.getMsg("qualitativeComposition").replace("%1", elements.size() + "").replace("%2", elementsStr) + "\n";
			ChemicalElement key = elements.keySet().toArray(new ChemicalElement[0])[0];
			String type = (elements.size() > 1 ? "сложное" : (elements.get(key) > 1 || key.isInert() ? "простое" : "одиночный атом"));
			description += "3. " + msgSys.getMsg("substanceType").replace("%1", type) + "\n";
			description += "4. " + msgSys.getMsg("quantitativeComposition").replace("%1", amountStr) + "\n";
			description += "5. " + msgSys.getMsg("relativeMolecularMass").replace("%1", molecularMassStr) + "\n";
			description += "6. " + msgSys.getMsg("elementsMassRatio").replace("%1", propStr) + "\n";
			description += "7. " + msgSys.getMsg("elementsMassFractions").replace("%1", massDolesStr);
			InitChemicalCompoundsDescriptionPart.instance.descriptionTextarea.setText(description);
		});
	}

	private String toStr(double d)
	{
		switch (InitChemicalCompoundsDescriptionPart.instance.signsAfterCommaCombobox.getSelectionModel().getSelectedItem())
		{
			case "0":
				return "" + (d + "").split("\\.")[0] + "." + (d + "000").split("\\.")[1].substring(0, 0);
			case "1":
				return "" + (d + "").split("\\.")[0] + "." + (d + "000").split("\\.")[1].substring(0, 1);
			case "2":
				return "" + (d + "").split("\\.")[0] + "." + (d + "000").split("\\.")[1].substring(0, 2);
			case "3":
				return "" + (d + "").split("\\.")[0] + "." + (d + "000").split("\\.")[1].substring(0, 3);
			case "4":
				return "" + (d + "").split("\\.")[0] + "." + (d + "000").split("\\.")[1].substring(0, 4);
			default:
				return "" + d;
		}
	}

	@Override
	public void updateLabelsInPart()
	{
		super.updateLabelsInPart();
		InitChemicalCompoundsDescriptionPart.instance.formulaTextfield.setPromptText(msgSys.getMsg("enterFormula"));
		InitChemicalCompoundsDescriptionPart.instance.nameTextfield.setPromptText(msgSys.getMsg("enterName"));
		InitChemicalCompoundsDescriptionPart.instance.namesInLatinRadio.setText(msgSys.getMsg("namesInLatin"));
		InitChemicalCompoundsDescriptionPart.instance.namesInSelectedLanguageRadio.setText(msgSys.getMsg("namesInSelectedLanguage"));
		InitChemicalCompoundsDescriptionPart.instance.signsAfterCommaLabel.setText(msgSys.getMsg("signsAfterComma"));
		InitChemicalCompoundsDescriptionPart.instance.createDescriptionButton.setText(msgSys.getMsg("createDescription"));
	}

	@Override
	public String name()
	{
		return "chemicalCompoundsDescriptor";
	}

}
