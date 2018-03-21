package ru.alexanderdv.schooltester.utilities;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * 
 * @author AlexanderDV/AlexandrDV
 * @version 5.8.0a
 */
public class FXScene extends AnchorPane
{
	private Stage stage;
	private Pane titlePane;
	private Label title;
	private Button hide, max, exit;
	private Pane content;
	private Point lastPoint;
	private int borderSize;
	private int type;
	private boolean canResize;
	private ImageView icon;
	private int titlePaneHeight = 30;
	private double addingWidth, addingHeight;
	public static final double minX, minY, maxX, maxY;
	private EventHandler<WindowEvent> ev, ev3;
	public static final Rectangle[] bounds;
	static
	{
		GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		bounds = new Rectangle[devices.length];
		double _minX = Double.MAX_VALUE, _minY = Double.MAX_VALUE, _maxX = Double.MIN_VALUE, _maxY = Double.MIN_VALUE;
		for (int i = 0; i < devices.length; i++)
		{
			bounds[i] = devices[i].getDefaultConfiguration().getBounds();
			_minX = Math.min(_minX, bounds[i].getMinX());
			_minY = Math.min(_minY, bounds[i].getMinY());
			_maxX = Math.max(_maxX, bounds[i].getMaxX());
			_maxY = Math.max(_maxY, bounds[i].getMaxY());
		}
		minX = _minX;
		minY = _minY;
		maxX = _maxX;
		maxY = _maxY;
	}

	/**
	 * @return the addingWidth
	 */
	public double getAddingWidth()
	{
		return addingWidth;
	}

	/**
	 * @param addingWidth
	 *            the addingWidth to set
	 */
	public void setAddingWidth(double addingWidth)
	{
		this.addingWidth = addingWidth;
	}

	/**
	 * @return the addingHeight
	 */
	public double getAddingHeight()
	{
		return addingHeight;
	}

	/**
	 * @param addingHeight
	 *            the addingHeight to set
	 */
	public void setAddingHeight(double addingHeight)
	{
		this.addingHeight = addingHeight;
	}

	public FXScene(Stage stage, int borderSize, String titleText, boolean canResize, int type)
	{
		this.canResize = canResize;
		this.type = type;
		this.borderSize = borderSize;
		this.stage = stage;
		this.setBackground(new Background(new BackgroundFill(new Color(0.4, 0.4, 0.4, 1), new CornerRadii(0), new Insets(0, 0, 0, 0))));
		titlePane = new Pane();
		this.getChildren().add(titlePane);
		title = new Label("         " + titleText);
		title.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 1), new CornerRadii(0), new Insets(0, 0, 0, 0))));
		title.setFont(new Font("System Bold", 10));
		titlePane.getChildren().add(title);
		try
		{
			icon = new ImageView(new Image(Logger.class.getResource("/Icon16x.png").openStream()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		titlePane.getChildren().add(icon);
		hide = new Button("–");
		hide.setFont(new Font("Verdana", 12));
		titlePane.getChildren().add(hide);
		max = new Button("⃞");
		max.setFont(new Font("Verdana", 12));
		titlePane.getChildren().add(max);
		exit = new Button("×");
		exit.setFont(new Font("Verdana", 12));
		titlePane.getChildren().add(exit);
		content = new Pane();
		content.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 1), new CornerRadii(0), new Insets(0, 0, 0, 0))));
		this.getChildren().add(content);
		addingWidth = borderSize * 2;
		addingHeight = borderSize * 3 + titlePaneHeight;
		resize();
	}

	public void resize()
	{
		double width = getPrefWidth(), height = getPrefHeight();
		double[] buttonsSizes = new double[]
		{
				type > 1 ? 40 : 0,
				type > 2 ? 40 : 0,
				type > 0 ? 40 : 0
		};
		titlePane.setLayoutX(borderSize);
		titlePane.setLayoutY(borderSize);
		titlePane.setMinWidth(width - borderSize * 2);
		titlePane.setPrefWidth(width - borderSize * 2);
		titlePane.setMaxWidth(width - borderSize * 2);
		titlePane.setMinHeight(titlePaneHeight);
		titlePane.setPrefHeight(titlePaneHeight);
		titlePane.setMaxHeight(titlePaneHeight);

		icon.setLayoutX(4);
		icon.setLayoutY(titlePane.getPrefHeight() / 2 - 16 / 2);

		title.setLayoutX(0);
		title.setLayoutY(0);
		title.setMinWidth(titlePane.getPrefWidth() - (buttonsSizes[0] > 0 ? buttonsSizes[0] + borderSize : 0) - (buttonsSizes[1] > 0 ? buttonsSizes[1]
				+ borderSize : 0) - (buttonsSizes[2] > 0 ? buttonsSizes[2] + borderSize : 0));
		title.setPrefWidth(titlePane.getPrefWidth() - (buttonsSizes[0] > 0 ? buttonsSizes[0] + borderSize : 0) - (buttonsSizes[1] > 0 ? buttonsSizes[1]
				+ borderSize : 0) - (buttonsSizes[2] > 0 ? buttonsSizes[2] + borderSize : 0));
		title.setMaxWidth(titlePane.getPrefWidth() - (buttonsSizes[0] > 0 ? buttonsSizes[0] + borderSize : 0) - (buttonsSizes[1] > 0 ? buttonsSizes[1]
				+ borderSize : 0) - (buttonsSizes[2] > 0 ? buttonsSizes[2] + borderSize : 0));
		title.setMinHeight(titlePane.getPrefHeight());
		title.setPrefHeight(titlePane.getPrefHeight());
		title.setMaxHeight(titlePane.getPrefHeight());

		hide.setLayoutX(title.getLayoutX() + title.getPrefWidth() + borderSize);
		hide.setLayoutY(0);
		hide.setMinWidth(buttonsSizes[0]);
		hide.setPrefWidth(buttonsSizes[0]);
		hide.setMaxWidth(buttonsSizes[0]);
		hide.setMinHeight(titlePane.getPrefHeight() - 1);
		hide.setPrefHeight(titlePane.getPrefHeight() - 1);
		hide.setMaxHeight(titlePane.getPrefHeight() - 1);
		hide.setVisible(buttonsSizes[0] > 0);

		max.setLayoutX(title.getLayoutX() + title.getPrefWidth() + borderSize + (buttonsSizes[0] > 0 ? buttonsSizes[0] + borderSize : 0));
		max.setLayoutY(0);
		max.setMinWidth(buttonsSizes[1]);
		max.setPrefWidth(buttonsSizes[1]);
		max.setMaxWidth(buttonsSizes[1]);
		max.setMinHeight(titlePane.getPrefHeight() - 1);
		max.setPrefHeight(titlePane.getPrefHeight() - 1);
		max.setMaxHeight(titlePane.getPrefHeight() - 1);
		max.setVisible(buttonsSizes[1] > 0);

		exit.setLayoutX(title.getLayoutX() + title.getPrefWidth() + borderSize + (buttonsSizes[0] > 0 ? buttonsSizes[0] + borderSize : 0) + (buttonsSizes[1] > 0
				? buttonsSizes[1] + borderSize
				: 0));
		exit.setLayoutY(0);
		exit.setMinWidth(buttonsSizes[2]);
		exit.setPrefWidth(buttonsSizes[2]);
		exit.setMaxWidth(buttonsSizes[2]);
		exit.setMinHeight(titlePane.getPrefHeight() - 1);
		exit.setPrefHeight(titlePane.getPrefHeight() - 1);
		exit.setMaxHeight(titlePane.getPrefHeight() - 1);
		exit.setVisible(buttonsSizes[2] > 0);

		content.setLayoutX(borderSize);
		content.setLayoutY(titlePane.getPrefHeight() + borderSize * 2);
		content.setMinWidth(width - borderSize * 2);
		content.setPrefWidth(width - borderSize * 2);
		content.setMaxWidth(width - borderSize * 2);
		content.setMinHeight(height - borderSize * 3 - titlePane.getPrefHeight());
		content.setPrefHeight(height - borderSize * 3 - titlePane.getPrefHeight());
		content.setMaxHeight(height - borderSize * 3 - titlePane.getPrefHeight());

		hide.setOnAction(e ->
		{
			stage.setIconified(!stage.isIconified());
			if (ev != null)
				ev.handle(new WindowEvent(stage, EventType.ROOT));
		});
		max.setOnAction(e ->
		{
			if (canResize)
				stage.setMaximized(!stage.isMaximized());
			if (ev3 != null)
				ev3.handle(new WindowEvent(stage, EventType.ROOT));
		});
		exit.setOnAction(e ->
		{
			if (stage.getOnCloseRequest() != null)
				stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
			stage.close();
		});
		EventHandler<MouseEvent> ev = (e) -> updatePosition(new Point((int) (e.getScreenX()), (int) (e.getScreenY())),true);
		EventHandler<MouseEvent> ev2 = (e) -> lastPoint = new Point((int) (e.getScreenX()), (int) (e.getScreenY()));
		icon.setOnMouseDragged(ev);
		icon.setOnMousePressed(ev2);
		title.setOnMouseDragged(ev);
		title.setOnMousePressed(ev2);
	}

	public void updatePosition(Point nowPoint, boolean byMouse)
	{
		if (lastPoint != null && nowPoint != null && byMouse)
		{
			stage.setX(stage.getX() + nowPoint.getX() - lastPoint.getX());
			stage.setY(stage.getY() + nowPoint.getY() - lastPoint.getY());
		}
		stage.setX(Math.max(minX + 1, Math.min(stage.getX(), maxX - 1)));
		stage.setY(Math.max(minY + 1, Math.min(stage.getY(), maxY - 1)));
		if (nowPoint != null)
			lastPoint = nowPoint;
	}

	public void setContent(Node node, double width, double height)
	{
		content.getChildren().clear();
		content.getChildren().add(node);

		content.setMinWidth(width);
		content.setPrefWidth(width);
		content.setMaxWidth(width);
		content.setMinHeight(height);
		content.setPrefHeight(height);
		content.setMaxHeight(height);

		setMinWidth(width + borderSize * 2);
		setPrefWidth(width + borderSize * 2);
		setMaxWidth(width + borderSize * 2);
		setMinHeight(height + borderSize * 3 + titlePaneHeight);
		setPrefHeight(height + borderSize * 3 + titlePaneHeight);
		setMaxHeight(height + borderSize * 3 + titlePaneHeight);
		resize();
	}

	public void setTitle(String title)
	{
		stage.setTitle(title);
		this.title.setText(title);
	}

	public void setOnHiding(EventHandler<WindowEvent> ev)
	{
		this.ev = ev;
	}

	public void setOnMaximalize(EventHandler<WindowEvent> ev)
	{
		this.ev3 = ev;
	}

	public String getTitle()
	{
		return title.getText();
	}

	public Font getTitleFont()
	{
		return title.getFont();
	}

	public int getButtonsWidth()
	{
		return (int) (hide.getPrefWidth() + max.getPrefWidth() + exit.getPrefWidth());
	}
}
