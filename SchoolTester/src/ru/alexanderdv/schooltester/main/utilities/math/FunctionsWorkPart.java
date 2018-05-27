package ru.alexanderdv.schooltester.main.utilities.math;

import java.net.URL;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.simpleutilities.MathWithText;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class FunctionsWorkPart extends ProtectedFXWindow
{
	private double[] defXP;

	@Override
	protected void _resize(int w, int h)
	{
		
	}
	private int pointsToBuildCount;
	private double masshtab = 5, step;
	private Line[] lines;
	private Circle[] points;
	private boolean drawOnlyPoints;
	private TextField pointsToBuildCountTextfield = InitFunctionsWorkPart.instance.pointsToBuildCountTextfield;

	private TextField changeDetalisationTextfield = InitFunctionsWorkPart.instance.changeDetalisationTextfield;

	private TextField masshtabTextfield = InitFunctionsWorkPart.instance.masshtabTextfield;

	private TextField stepTextfield = InitFunctionsWorkPart.instance.stepTextfield;

	private CheckBox onlyPoints = InitFunctionsWorkPart.instance.onlyPoints;

	private Pane graphic = InitFunctionsWorkPart.instance.graphic;

	private TextField formula = InitFunctionsWorkPart.instance.formula;

	public FunctionsWorkPart(URL url,boolean inDevelope)
	{
		super(null, url, 1, 1,inDevelope,true);
		createActionHandlers();
	}

	public void createActionHandlers()
	{
		changepointsToBuildCount(50, 1);
		changeDetalisationTextfield.setOnAction(e ->
		{
			pointsToBuildCountTextfield.setText(MathWithText.parseI(changeDetalisationTextfield.getText()) + "");
			stepTextfield.setText(50d / MathWithText.parseI(changeDetalisationTextfield.getText()) * MathWithText.parseD(stepTextfield.getText()) + "");
			updateGraphic(masshtab, changepointsToBuildCount(pointsToBuildCount = MathWithText.parseI(pointsToBuildCountTextfield.getText()),
					step = MathWithText.parseD(stepTextfield.getText())), drawOnlyPoints);
		});
		stepTextfield.setOnAction(e -> updateGraphic(masshtab, changepointsToBuildCount(pointsToBuildCount, step = MathWithText.parseD(stepTextfield
				.getText())), drawOnlyPoints));
		masshtabTextfield.setOnAction(e -> updateGraphic(masshtab = MathWithText.parseD(masshtabTextfield.getText()), pointsToBuildCount, drawOnlyPoints));
		pointsToBuildCountTextfield.setOnAction(e -> updateGraphic(masshtab, changepointsToBuildCount(MathWithText.parseI(pointsToBuildCountTextfield
				.getText()), step), drawOnlyPoints));
		onlyPoints.setOnAction(e -> updateGraphic(masshtab, pointsToBuildCount, drawOnlyPoints = onlyPoints.isSelected()));
		formula.setOnAction(e -> updateGraphic(masshtab, pointsToBuildCount, drawOnlyPoints));
	}

	public int changepointsToBuildCount(int count, double step)
	{
		pointsToBuildCount = count / 2 * 2;
		graphic.getChildren().clear();
		lines = new Line[pointsToBuildCount];
		points = new Circle[pointsToBuildCount];
		defXP = new double[count];
		for (int i = 0; i < pointsToBuildCount; i++)
		{
			graphic.getChildren().add(lines[i] = new Line());
			graphic.getChildren().add(points[i] = new Circle());
		}
		for (int i = 0; i < pointsToBuildCount / 2 - 1; i++)
			defXP[i] = i - pointsToBuildCount / 2 + 1;
		defXP[(pointsToBuildCount / 2 - 1) + 0] = -0.001;
		defXP[(pointsToBuildCount / 2 - 1) + 1] = +0.001;
		for (int i = 0; i < pointsToBuildCount / 2 - 1; i++)
			defXP[pointsToBuildCount / 2 + 1 + i] = i + 1;
		for (int i = 0; i < pointsToBuildCount; i++)
			defXP[i] *= step;
		return count;
	}

	public void updateGraphic(double step, int pointsToBuildCount, boolean drawOnlyPoints)
	{
		double[] xPositions = new double[pointsToBuildCount], yPositions = new double[pointsToBuildCount];
		xPositions = defXP;
		for (int i = 0; i < pointsToBuildCount; i++)
		{
			yPositions[i] = MathWithText.calculate(formula.getText().replace("x", "" + xPositions[i]));
			lines[i].setVisible(false);
			points[i].setVisible(false);
			if (!drawOnlyPoints)
				if (i > 0)
					if (!(yPositions[i] > graphic.getHeight() / 2 / step && yPositions[i - 1] < -graphic.getHeight() / 2 / step || yPositions[i] < -graphic
							.getHeight() / 2 / step && yPositions[i - 1] > graphic.getHeight() / 2 / step))
						if (!(yPositions[i] > graphic.getHeight() / 2 / step && yPositions[i - 1] > graphic.getHeight() / 2 / step || yPositions[i
								- 1] < -graphic.getHeight() / 2 / step && yPositions[i] < -graphic.getHeight() / 2 / step))
							if (!(xPositions[i] > graphic.getWidth() / 2 / step && xPositions[i - 1] > graphic.getWidth() / 2 / step || xPositions[i
									- 1] < -graphic.getWidth() / 2 / step && xPositions[i] < -graphic.getWidth() / 2 / step))
							{
								lines[i].setStartX(graphic.getWidth() / 2 + xPositions[i - 1] * step);
								lines[i].setStartY(graphic.getHeight() / 2 + yPositions[i - 1] * step);
								lines[i].setEndX(graphic.getWidth() / 2 + xPositions[i] * step);
								lines[i].setEndY(graphic.getHeight() / 2 + yPositions[i] * step);
								lines[i].setVisible(true);
							}
			if (!(yPositions[i] > graphic.getHeight() / 2 / step || yPositions[i] < -graphic.getHeight() / 2 / step))
				if (!(yPositions[i] > graphic.getHeight() / 2 / step || yPositions[i] < -graphic.getHeight() / 2 / step))
					if (!(xPositions[i] > graphic.getWidth() / 2 / step || xPositions[i] < -graphic.getWidth() / 2 / step))
					{
						points[i].setRadius(1);
						points[i].setCenterX(graphic.getWidth() / 2 + xPositions[i] * step);
						points[i].setCenterY(graphic.getHeight() / 2 + yPositions[i] * step);
						points[i].setVisible(true);
					}
		}
	}

	@Override
	public String name()
	{
		return "functionsGraphicsGenerator";
	}
}