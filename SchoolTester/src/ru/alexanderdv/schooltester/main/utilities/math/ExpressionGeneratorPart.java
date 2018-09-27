package ru.alexanderdv.schooltester.main.utilities.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.alexanderdv.fxutilities.ComponentUtils;
import ru.alexanderdv.fxutilities.components.EditableComboBox;
import ru.alexanderdv.fxutilities.components.Resizable;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.fx.FXDialogsGenerator;
import ru.alexanderdv.schooltester.utilities.fx.SchoolTesterFXWindow;
import ru.alexanderdv.simpleutilities.MathUtils;

public class ExpressionGeneratorPart extends SchoolTesterFXWindow
{
	private static final Random rand = new Random();
	public static ExpressionGeneratorPart instance;

	static class ExpressionElement extends Pane implements Resizable
	{
		TextField id = new TextField();
		ComboBox<String> box = new ComboBox<String>();
		HBox content = new HBox();
		EditableComboBox cb = new EditableComboBox();
		TextField from, to, coef, spaceW, cellW, cellH, cellThickness;
		HBox number, space, cell, text;

		public ExpressionElement()
		{
			box.getItems().addAll("Number", "Space", "Cell", "Text");

			number = new HBox();
			{
				number.getChildren().add(from = new TextField("0"));
				number.getChildren().add(to = new TextField("10"));
				number.getChildren().add(coef = new TextField("1"));
			}

			space = new HBox();
			{
				space.getChildren().add(spaceW = new TextField("15"));
			}

			cell = new HBox();
			{
				cell.getChildren().add(cellW = new TextField("15"));
				cell.getChildren().add(cellH = new TextField("15"));
				cell.getChildren().add(cellThickness = new TextField("1"));
			}

			text = new HBox();
			{
				text.getChildren().add(cb);
			}

			box.setOnAction(e ->
			{
				content.getChildren().clear();
				switch (box.getSelectionModel().getSelectedItem())
				{
					case "Number":
						content.getChildren().add(number);
						break;

					case "Cell":
						content.getChildren().add(cell);
						break;

					case "Text":
						content.getChildren().add(text);
						break;

					default:
						content.getChildren().add(space);
						break;
				}
			});

			getChildren().add(new HBox(id, box, content));
			createListeners();
		}

		@Override
		public void _resize(double w, double h)
		{
			id.setPrefSize(80, h);
			box.setPrefSize(100, h);
			content.setPrefSize(w - box.getPrefWidth() - id.getPrefWidth(), h);
			{
				number.setPrefSize(content.getPrefWidth(), content.getPrefHeight());
				{
					from.setLayoutX(number.getPrefWidth() / 3 * 0);
					from.setPrefSize(number.getPrefWidth() / 3, number.getPrefHeight());
					to.setLayoutX(number.getPrefWidth() / 3 * 1);
					to.setPrefSize(number.getPrefWidth() / 3, number.getPrefHeight());
					coef.setLayoutX(number.getPrefWidth() / 3 * 2);
					coef.setPrefSize(number.getPrefWidth() / 3, number.getPrefHeight());
				}
				space.setPrefSize(content.getPrefWidth(), content.getPrefHeight());
				{
					spaceW.setPrefSize(space.getPrefWidth(), space.getPrefHeight());
				}
				cell.setPrefSize(content.getPrefWidth(), content.getPrefHeight());
				{
					cellW.setLayoutX(cell.getPrefWidth() / 3 * 0);
					cellW.setPrefSize(cell.getPrefWidth() / 3, cell.getPrefHeight());
					cellH.setLayoutX(cell.getPrefWidth() / 3 * 1);
					cellH.setPrefSize(cell.getPrefWidth() / 3, cell.getPrefHeight());
					cellThickness.setLayoutX(cell.getPrefWidth() / 3 * 2);
					cellThickness.setPrefSize(cell.getPrefWidth() / 3, cell.getPrefHeight());
				}
				text.setPrefSize(content.getPrefWidth(), content.getPrefHeight());
				{
					cb.setPrefSize(text.getPrefWidth(), text.getPrefHeight());
				}
			}
			System.out.println("m3");
		}

		@Override
		public void createListeners()
		{
			prefWidthProperty().addListener((a, b, c) -> resize());
			prefHeightProperty().addListener((a, b, c) -> resize());
		}

		@Override
		public void resize()
		{
			Platform.runLater(() -> _resize(getPrefWidth(), getPrefHeight()));
		}
	}

	static class Expression extends Tab implements Resizable
	{
		private ArrayList<ExpressionElement> expressionElements = new ArrayList<ExpressionElement>();

		public Expression()
		{
			super();

			VBox box = new VBox(), cont;
			TextField renameField = new TextField();
			textProperty().bindBidirectional(renameField.textProperty());
			renameField.setPrefHeight(25);
			Button add = new Button("Add");
			add.setOnAction(e ->
			{
				ExpressionElement ex = new ExpressionElement();
				expressionElements.add(ex);
				box.getChildren().add(ex);
				ExpressionGeneratorPart.instance.resize();
			});
			setContent(new VBox(renameField, cont = new VBox(box, add)));

			createListeners();
		}

		@Override
		public void _resize(double w, double h)
		{
			for (ExpressionElement ex : expressionElements)
				ex.setPrefWidth(w);
			System.out.println("m2");
		}

		@Override
		public void createListeners()
		{
			tabPaneProperty().addListener((a, b, c) ->
			{
				if (b != null)
				{
					b.prefWidthProperty().removeListener(n);
					b.prefHeightProperty().removeListener(n);
				}
				if (c != null)
				{
					c.prefWidthProperty().addListener(n);
					c.prefHeightProperty().addListener(n);
				}
			});
		}

		ChangeListener<Number> n = (a2, b2, c2) -> resize();

		@Override
		public void resize()
		{
			Platform.runLater(() -> _resize(getTabPane().getPrefWidth(), getTabPane().getPrefHeight()));
		}
	}

	private ArrayList<Expression> expressions = new ArrayList<Expression>();
	private TabPane box;
	Button add;

	private VBox content;

	public ExpressionGeneratorPart()
	{
		super(new Stage(), Main.program, Main.icon, Main.createPane(600, 800), 1, true, true, true);
		instance = this;
		TextArea ta;
		getPanel().getChildren()
				.add(content = new VBox(new Pane(box = new TabPane(), add = ComponentUtils.createButton("+", e ->
				{
					Expression ex = new Expression();
					expressions.add(ex);
					box.getTabs().add(ex);
					resize();
				})), ta = new TextArea(), ComponentUtils.createButton("Generate image and copy to clipboard", e ->
				{
					double expressionHeight = 20;
					Canvas cnv = new Canvas();
					cnv.getGraphicsContext2D().setTextBaseline(VPos.TOP);
					cnv.setWidth(600);
					cnv.setHeight(600);
					HashMap<String, Expression> exps = new HashMap<String, Expression>();
					for (Expression exp : expressions)
						exps.put(exp.getText().replaceAll("[\n\r\t \f]", ""), exp);

					HashMap<String, Double> vars = new HashMap<String, Double>();
					for (char c : "abcdefghixy".toCharArray())
						vars.put(c + "", 0d);

//					String txt = "";
//					String txt2 = null;
//					String txt3 = null;
//					for (char ch : ta.getText().toCharArray())
//						if (txt3 != null)
//							if (ch == ';')
//							{
//								txt+=MathUtils.multiply(txt2, MathUtils.parseI(txt3));
//								txt2=null;
//								txt3=null;
//							}
//							else txt3 += ch;
//						else if (txt3 == null && txt2 != null)
//							if (ch == '}')
//								txt3 = "";
//							else txt2 += ch;
//						else if (txt2 == null)
//							if (ch == '{')
//								txt2 = "";
//							else txt += ch;
//						else txt += ch;
					
					String txt=ta.getText();
					
					
					//FXDialogsGenerator.showFXDialog(stage, txt, null, true);

					// if (txt3 == null)
					// if (ch == '}')
					// txt3 = "";
					// else txt2 += ch;
					// else if (txt2 != null)
					// if (ch == '{')
					// txt2 = "";
					// else txt += ch;
					// else if(ch==';')
					// txt+=MathUtils.multiply(txt2+ch, MathUtils.parseI(txt3));

					String prop = null;
					String action = null;
					String value = "";
					for (char ch : txt.toCharArray())
					{
						if (prop == null)
							if (ch == 'r')
								prop = ch + "";
							else if (vars.containsKey(ch + ""))
								prop = ch + "";
							else;
						else if (action == null)
							switch (ch)
							{
								case '=':
								case '+':
								case '#':
									action = ch + "";
									break;
							}
						else if (ch == ';' || ch == '\n' || ch == '\r')
						{
							if (prop.equals("r"))
							{
								Expression exp = exps.get(value.replaceAll("[\n\r\t \f]", ""));
								if (exp == null)
								{
									cnv.getGraphicsContext2D().setFill(Color.RED);
									cnv.getGraphicsContext2D().fillText("ERROR! VALUE IS NULL", vars.get("x"),
											vars.get("y"));
									vars.put("x", vars.get("x") + 100);
								}
								else
								{
									String text;
									double h;

									HashMap<String, Double> numbers = new HashMap<String, Double>();
									for (ExpressionElement ex : exp.expressionElements)
									{
										double w;
										cnv.getGraphicsContext2D().setFill(Color.BLACK);
										switch (ex.box.getSelectionModel().getSelectedItem() != null
												? ex.box.getSelectionModel().getSelectedItem()
												: "")
										{
											case "Number":
												String fromS = ex.from.getText();
												String toS = ex.to.getText();
												String coefS = ex.coef.getText();

												for (String s : numbers.keySet())
												{
													fromS = fromS.replace(s, numbers.get(s) + "");
													toS = toS.replace(s, numbers.get(s) + "");
													coefS = coefS.replace(s, numbers.get(s) + "");
												}

												int from = (int) MathUtils.calculate(fromS);
												int to = (int) MathUtils.calculate(toS);
												double coef = MathUtils.calculate(coefS);

												double result = (from + rand.nextInt(to - from)) * coef;

												numbers.put(ex.id.getText(), result);

												text = MathUtils.toString(result);
												w = MathUtils.size(text, java.awt.Font.decode(cnv.getGraphicsContext2D()
														.getFont().getName() + "-"
														+ cnv.getGraphicsContext2D().getFont().getStyle() + "-"
														+ cnv.getGraphicsContext2D().getFont().getSize())).width;
												h = MathUtils.size(text, java.awt.Font.decode(cnv.getGraphicsContext2D()
														.getFont().getName() + "-"
														+ cnv.getGraphicsContext2D().getFont().getStyle() + "-"
														+ cnv.getGraphicsContext2D().getFont().getSize())).height;

												cnv.getGraphicsContext2D().fillText(text, vars.get("x"), vars.get("y"));
												break;

											case "Cell":
												w = MathUtils.parseI(ex.cellW.getText());
												h = MathUtils.parseI(ex.cellH.getText());
												cnv.getGraphicsContext2D()
														.setLineWidth(MathUtils.parseI(ex.cellThickness.getText()));
												cnv.getGraphicsContext2D().strokeRect(vars.get("x"),
														vars.get("y") + expressionHeight / 2 - h / 2, w, h);
												break;

											case "Text":
												text = ex.cb.getItems().get(rand.nextInt(ex.cb.getItems().size()));
												w = MathUtils.size(text, java.awt.Font.decode(cnv.getGraphicsContext2D()
														.getFont().getName() + "-"
														+ cnv.getGraphicsContext2D().getFont().getStyle() + "-"
														+ cnv.getGraphicsContext2D().getFont().getSize())).width;
												h = MathUtils.size(text, java.awt.Font.decode(cnv.getGraphicsContext2D()
														.getFont().getName() + "-"
														+ cnv.getGraphicsContext2D().getFont().getStyle() + "-"
														+ cnv.getGraphicsContext2D().getFont().getSize())).height;

												cnv.getGraphicsContext2D().fillText(text, vars.get("x"), vars.get("y"));
												break;

											default:
												w = MathUtils.parseI(ex.spaceW.getText());
												h = 0;
												break;
										}
										// cnv.getGraphicsContext2D().setStroke(Color.RED);
										// cnv.getGraphicsContext2D().strokeRect(x, y, w, h);

										vars.put("x", vars.get("x") + (int) w);
									}
								}
							}
							if (vars.containsKey(prop))
							{
								for (String var : vars.keySet())
									value = value.replace(var, vars.get(var) + "");
								switch (action)
								{
									case "=":
										vars.put(prop, MathUtils.calculate(value));
										break;
									case "+":
										vars.put(prop, vars.get(prop) + MathUtils.calculate(value));
										break;
									case "-":
										vars.put(prop, vars.get(prop) - MathUtils.calculate(value));
										break;
									case "*":
										vars.put(prop, vars.get(prop) * MathUtils.calculate(value));
										break;
									case "/":
										vars.put(prop, vars.get(prop) / MathUtils.calculate(value));
										break;
									case "%":
										vars.put(prop, vars.get(prop) % MathUtils.calculate(value));
										break;
									case "#":
										vars.put(prop, vars.get(prop) - vars.get(prop) % MathUtils.calculate(value));
										break;
								}
							}
							prop = null;
							value = "";
							action = null;
						}
						else value += ch;
						if (ch == '\n' || ch == '\r')
						{
							vars.put("x", 0d);
							vars.put("y", vars.get("y") + (int) expressionHeight);
						}

					}

					WritableImage img = new WritableImage((int) cnv.getWidth(), (int) cnv.getHeight());
					cnv.snapshot(new SnapshotParameters(), img);

					ClipboardContent content = new ClipboardContent();
					for (DataFormat f : Clipboard.getSystemClipboard().getContentTypes())
						content.put(f, Clipboard.getSystemClipboard().getContent(f));
					content.putImage(img);
					Clipboard.getSystemClipboard().setContent(content);
				})));
		createListeners();
	}

	@Override
	public void _resize(double w, double h)
	{
		content.setPrefSize(w, h);
		box.setPrefSize(w, h / 2);
		for (Expression e : expressions)
			e.resize();
		add.setLayoutY(2);
		add.setPrefSize(25, 25);
		add.setLayoutX(w - add.getPrefWidth() - 1);
		System.out.println("m1");
	}

	@Override
	public void updateLabels()
	{
		super.updateLabels();
	}

	@Override
	public String name()
	{
		return "expressionGenerator";
	}

}
