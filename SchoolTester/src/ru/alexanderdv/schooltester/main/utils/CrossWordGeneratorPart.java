package ru.alexanderdv.schooltester.main.utils;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.TextAlignment;
import ru.alexanderdv.schooltester.utilities.ProtectedFXWindow;
import ru.alexanderdv.schooltester.utilities.TableSelection;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class CrossWordGeneratorPart extends ProtectedFXWindow
{
	public static CrossWordGeneratorPart instance;
	private InitCrossWordGeneratorPart elements = InitCrossWordGeneratorPart.instance;
	private Background bg = new Background(new BackgroundFill(new javafx.scene.paint.Color(1, 1, 1, 1), new CornerRadii(0), new Insets(0)));
	private Border bd = new Border(new BorderStroke(new javafx.scene.paint.Color(0, 0, 0, 1), new BorderStrokeStyle(StrokeType.CENTERED, StrokeLineJoin.ROUND,
			StrokeLineCap.SQUARE, 0, 0, null), new CornerRadii(0), new BorderWidths(1)));

	private ArrayList<C> s = new ArrayList<C>();

	public CrossWordGeneratorPart(String secondaryTitle, URL url)
	{
		super(secondaryTitle, url, 1, 1);
		instance = this;
		elements.crossword.setBackground(new Background(new BackgroundFill(new javafx.scene.paint.Color(0.9, 0.9, 0.9, 1), new CornerRadii(0), new Insets(0))));
		createActionHandlers();
	}

	private void createActionHandlers()
	{
		elements.words.setOnKeyPressed(e ->
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				e.consume();
				elements.crossword.getChildren().clear();
				s.clear();
				ArrayList<String> array = new ArrayList<String>();
				for (String s : elements.words.getText().replace(" ", "").replace("\t", "").toLowerCase().split(","))
					array.add(s);
				int i = 0, x = 0, y = 0;
				for (; i < array.size(); i++)
				{
					if (i == 0)
						s.add(new C(array.get(i).toCharArray()[0], x, y, -1, new Label(), true, false, true, null));
					while (q(x, y, i, s, array))
					{
					}
					// if(i>0)
					// break;
				}
				double w = elements.crossword.getPrefWidth() / (maxX(s) + 1), h = elements.crossword.getPrefHeight() / (maxY(s) + 1);
				for (C c : s)
					if (c.l != null)
					{
						c.l.setLayoutX(c.x * w);
						c.l.setLayoutY(c.y * h);
						c.l.setPrefSize(w, h);
						c.l.setTextAlignment(TextAlignment.CENTER);
						c.l.setAlignment(Pos.CENTER);
						c.l.setBorder(bd);
						c.l.setBackground(bg);
					}
				// elements.crossword.getRowConstraints().clear();
				// double h = elements.crossword.getPrefHeight() / s(s);
				// elements.crossword.getRowConstraints().add(new RowConstraints(h, h, h, Priority.SOMETIMES, VPos.BASELINE, false));
				// elements.crossword.setGridLinesVisible(true);
			}
		});
		elements.copyInExcelFormat.setOnAction(e ->
		{
			String str = "";
			int v = 0, h = 0, l = 0;
			for (int y = 0; y <= maxY(s); y++)
			{
				for (int x = 0; x <= maxX(s); x++)
				{
					boolean letterExists = false;
					ArrayList<C> ss = new ArrayList<C>();
					for (C c : s)
						if (c.x == x && c.y == y)
							ss.add(c);

					C c1 = null;
					for (C c : ss)
						if (c.main)
						{
							c1 = c;
							String s = "";
							if (elements.digits.isSelected() && c.main)
								if (elements.splitToHorizontalAndVertical.isSelected())
									s += (c.vert ? v : h);
								else s += l;
							if (elements.letters.isSelected())
								s += !s.equals("") ? " " + c.c : c.c;
							if (s.equals(""))
								s += " ";
							str += s + "\t";
							letterExists = true;
							if (c.main)
							{
								if (c.vert)
									v++;
								else h++;
								l++;
							}
							break;
						}
					if (c1 == null && ss.size() > 0)
					{
						C c = ss.get(0);
						String s = "";
						if (elements.digits.isSelected() && c.main)
							if (elements.splitToHorizontalAndVertical.isSelected())
								s += (c.vert ? v : h);
							else s += l;
						if (elements.letters.isSelected())
							s += !s.equals("") ? " " + c.c : c.c;
						if (s.equals(""))
							s += " ";
						str += s + "\t";
						letterExists = true;
						if (c.main)
						{
							if (c.vert)
								v++;
							else h++;
							l++;
						}
					}
					if (!letterExists)
						str += "\t";
				}
				str += "\n";
			}
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new TableSelection(str, elements.backgroundOfEmptyCell.getValue().toString(),
					elements.backgroundOfCrosswordCell.getValue().toString(), elements.borderOfEmptyCell.getValue().toString(),
					elements.borderOfCrosswordCell.getValue().toString()), null);
		});
	}

	private double maxX(ArrayList<C> s)
	{
		int i = 0;
		for (C c : s)
			i = Math.max(c.x, i);
		return i;
	}

	private double maxY(ArrayList<C> s)
	{
		int i = 0;
		for (C c : s)
			i = Math.max(c.y, i);
		return i;
	}
	@Override
	public void updateLabelsInPart()
	{
		super.updateLabelsInPart();
		InitCrossWordGeneratorPart.instance.words.setPromptText(msgSys.getMsg("splitByComma"));
		InitCrossWordGeneratorPart.instance.digits.setText(msgSys.getMsg("digits"));
		InitCrossWordGeneratorPart.instance.letters.setText(msgSys.getMsg("letters"));
		InitCrossWordGeneratorPart.instance.splitToHorizontalAndVertical.setText(msgSys.getMsg("splitToH&V"));
		InitCrossWordGeneratorPart.instance.copyInExcelFormat.setText(msgSys.getMsg("copyTable"));
		InitCrossWordGeneratorPart.instance.backgroundOfEmptyCellLabel.setText(msgSys.getMsg("backgroundOfEmptyCell"));
		InitCrossWordGeneratorPart.instance.borderOfEmptyCellLabel.setText(msgSys.getMsg("borderOfEmptyCell"));
		InitCrossWordGeneratorPart.instance.backgroundOfCrosswordCellLabel.setText(msgSys.getMsg("backgroundOfCrosswordCell"));
		InitCrossWordGeneratorPart.instance.borderOfCrosswordCellLabel.setText(msgSys.getMsg("borderOfCrosswordCell"));
	}

	private boolean q(int x, int y, int i, ArrayList<C> s, ArrayList<String> array)
	{
		boolean created = false;
		for (int k = 0; k < s.size(); k++)
			if (s.get(k).c == array.get(i).toCharArray()[0] && !s.get(k).added)
			{
				x = s.get(k).x;
				y = s.get(k).y;
				boolean vert = !s.get(k).vert;
				s.get(k).added = true;
				for (int l = 0; l < s.size(); l++)
					if (s.get(l).x == x - 1 && s.get(l).y == y - 1 || s.get(l).x == x - 1 && s.get(l).y == y + 1 || s.get(l).x == x + 1 && s.get(l).y == y + 1
							|| s.get(l).x == x + 1 && s.get(l).y == y - 1)
						s.get(l).added = true;

				for (int j = 0; j < array.get(i).toCharArray().length; j++)
				{
					boolean b = false, last = j == array.get(i).toCharArray().length - 1, cancel = false;
					if (last && getInXY(x + (!vert ? 1 : 0), y + (vert ? 1 : 0)) != null)
						cancel = true;
					if (vert && (getInXY(x - 1, y) != null ? getInXY(x - 1, y).vert : false))
						cancel = true;
					if (vert && (getInXY(x + 1, y) != null ? getInXY(x + 1, y).vert : false))
						cancel = true;
					if (!vert && (getInXY(x, y - 1) != null ? !getInXY(x, y - 1).vert : false))
						cancel = true;
					if (!vert && (getInXY(x, y + 1) != null ? !getInXY(x, y + 1).vert : false))
						cancel = true;
					for (int l = 0; l < s.size(); l++)
						if (s.get(l).x == x && s.get(l).y == y && s.get(l).i != -1)
							if (s.get(l).c != array.get(i).toCharArray()[j])
								cancel = true;
							else b = true;
					if (cancel)
					{
						for (int r = 0; r < s.size(); r++)
							if (s.get(r).i == i)
							{
								elements.crossword.getChildren().remove(s.get(r).l);
								s.remove(r);
								r--;
							}
						return true;
					}
					Label label = null;
					boolean added = b && i != 0;
					if (!b)
						elements.crossword.getChildren().add(label = new Label(array.get(i).toCharArray()[j] + ""));
					C c;
					s.add(c = new C(array.get(i).toCharArray()[j], x, y, i, label, vert, added, j == 0, s.get(k)));
					created = true;
					if (label != null)
						label.setTooltip(new Tooltip(c.toString()));
					if (!vert)
						x++;
					if (vert)
						y++;
				}
				break;
			}
		if (!created && !(array.indexOf(array.get(i)) != -1 && array.lastIndexOf(array.get(i)) > array.indexOf(array.get(i))))
			array.add(array.get(i));
		return false;
	}

	C getInXY(int x, int y)
	{
		for (C c : s)
			if (c.x == x && c.y == y)
				return c;
		return null;
	}

	class C
	{
		C parent;
		Character c;
		int x;
		int y;
		int i;
		Label l;
		boolean vert, added, main;

		public C(Character c, int x, int y, int i, Label l, boolean vert, boolean added, boolean main, C parent)
		{
			super();
			this.c = c;
			this.x = x;
			this.y = y;
			this.i = i;
			this.l = l;
			this.vert = vert;
			this.added = added;
			this.parent = parent;
			this.main = main;
		}

		@Override
		public String toString()
		{
			return "C[x: " + x + ", y: " + y + ", i: " + i + ", vert: " + vert + ", added: " + added + ", main: " + main + ", parent: " + parent + "]";
		}
	}

}