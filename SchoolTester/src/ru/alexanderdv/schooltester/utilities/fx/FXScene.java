package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.Timer;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.alexanderdv.schooltester.utilities.Logger;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class FXScene extends AnchorPane
{
	private Stage stage;
	private Pane titlePane;
	private Label title;
	private Button hide, max, exit;
	private ScrollPane content;
	private Point lastPoint, lastSizePoint;
	private int borderSize;
	private int type;
	private boolean canResize;
	private ImageView icon;
	private int titlePaneHeight = 30;
	private double addingWidth, addingHeight;
	public static final double minX, minY, maxX, maxY;
	private EventHandler<WindowEvent> ev, ev3;
	public static final Rectangle[] bounds;

	private boolean xb1, xb2, yb1, yb2;
	private int minWidth;
	private int minHeight;
	private int minContentWidth;
	private int minContentHeight;
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
		content = new ScrollPane();
		content.setBackground(new Background(new BackgroundFill(new Color(1, 1, 1, 1), new CornerRadii(0), new Insets(0, 0, 0, 0))));
		this.getChildren().add(content);
		addingWidth = borderSize * 2;
		addingHeight = borderSize * 3 + titlePaneHeight;
		resize();
	}

	public void resize()
	{
		double width = getPrefWidth(), height = getPrefHeight();

		stage.setWidth(width);
		stage.setHeight(height);

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

		if (content.getContent() != null)
			if (content.getContent() instanceof Region)
				((Region) content.getContent()).setPrefSize(Math.max(minContentWidth, content.getPrefWidth() - 2), Math.max(minContentHeight, content
						.getPrefHeight() - 2));

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
		EventHandler<MouseEvent> ev = (e) -> updatePosition(new Point((int) e.getScreenX(), (int) e.getScreenY()), true);
		EventHandler<MouseEvent> ev2 = (e) -> lastPoint = new Point((int) e.getScreenX(), (int) e.getScreenY());
		icon.setOnMouseDragged(ev);
		icon.setOnMousePressed(ev2);
		title.setOnMouseDragged(ev);
		title.setOnMousePressed(ev2);

		this.setOnMouseDragged(e ->
		{
			clicked = e.isPrimaryButtonDown() || e.isSecondaryButtonDown();
			if (stage.isResizable())
				if (lastSizePoint != null)
				{

					if (xb2)
						setPrefWidth(Math.max(minWidth, e.getScreenX() - stage.getX()));
					else if (xb1)
						if (minWidth < stage.getX() - e.getScreenX() + getPrefWidth())
						{
							setPrefWidth(Math.max(minWidth, stage.getX() - e.getScreenX() + getPrefWidth()));
							stage.setX(e.getScreenX());
						}

					if (yb2)
						setPrefHeight(Math.max(minHeight, e.getScreenY() - stage.getY()));
					else if (yb1)
						if (minHeight < stage.getY() - e.getScreenY() + getPrefHeight())
						{
							setPrefHeight(Math.max(minHeight, stage.getY() - e.getScreenY() + getPrefHeight()));
							stage.setY(e.getScreenY());
						}

					resize();
				}
		});
		this.setOnMousePressed(e ->
		{
			clicked = e.isPrimaryButtonDown() || e.isSecondaryButtonDown();
			changeCursor();

			lastSizePoint = new Point((int) (e.getScreenX()), (int) (e.getScreenY()));
		});
		// this.setOnMousePressed(e -> lastSizePoint = new Point((int) stage.getWidth(), (int) stage.getHeight()));

		setOnMouseDragReleased(e -> clicked = e.isPrimaryButtonDown() || e.isSecondaryButtonDown());
		setOnMouseDragOver(e -> clicked = e.isPrimaryButtonDown() || e.isSecondaryButtonDown());
		setOnMouseReleased(e -> clicked = e.isPrimaryButtonDown() || e.isSecondaryButtonDown());
		setOnMouseMoved(e -> clicked = e.isPrimaryButtonDown() || e.isSecondaryButtonDown());
		new Timer(200, ae -> changeCursor()).start();
	}

	private boolean clicked;

	private void changeCursor()
	{
		try
		{
			double x = screenToLocal(MouseInfo.getPointerInfo().getLocation().x, 0).getX();
			double y = screenToLocal(0, MouseInfo.getPointerInfo().getLocation().y).getY();
			if (stage.isResizable())
				if (x <= borderSize)
				{
					if (y <= borderSize)
						setCursor(Cursor.NW_RESIZE);
					else if (y >= getScene().getHeight() - borderSize - 1)
						setCursor(Cursor.SW_RESIZE);
					else setCursor(Cursor.W_RESIZE);
				}
				else if (x >= getScene().getWidth() - borderSize - 1)
				{
					if (y <= borderSize)
						setCursor(Cursor.NE_RESIZE);
					else if (y >= getScene().getHeight() - borderSize - 1)
						setCursor(Cursor.SE_RESIZE);
					else setCursor(Cursor.E_RESIZE);
				}
				else
				{
					if (y <= borderSize)
						setCursor(Cursor.N_RESIZE);
					else if (y >= getScene().getHeight() - borderSize - 1)
						setCursor(Cursor.S_RESIZE);
					else if (!clicked)
						setCursor(Cursor.DEFAULT);
				}
			else setCursor(Cursor.DEFAULT);

			if (x <= borderSize || x >= getScene().getWidth() - borderSize - 1 || y <= borderSize || y >= getScene().getHeight() - borderSize - 1 || !clicked)
			{
				xb1 = x <= borderSize;
				xb2 = x >= getScene().getWidth() - borderSize - 1;

				yb1 = y <= borderSize;
				yb2 = y >= getScene().getHeight() - borderSize - 1;
			}
		}
		catch (NullPointerException e)
		{
		}
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

	public void setContent(Node node, double width, double height, double minWidth, double minHeight)
	{
		content.setContent(node);

		content.setMinWidth(width + 2);
		content.setPrefWidth(width + 2);
		content.setMaxWidth(width + 2);
		content.setMinHeight(height + 2);
		content.setPrefHeight(height + 2);
		content.setMaxHeight(height + 2);

		setMinWidth(content.getPrefWidth() + borderSize * 2);
		setPrefWidth(content.getPrefWidth() + borderSize * 2);
		setMaxWidth(content.getPrefWidth() + borderSize * 2);
		setMinHeight(content.getPrefHeight() + borderSize * 3 + titlePaneHeight);
		setPrefHeight(content.getPrefHeight() + borderSize * 3 + titlePaneHeight);
		setMaxHeight(content.getPrefHeight() + borderSize * 3 + titlePaneHeight);

		this.minWidth = (int) (minWidth + 2 + borderSize * 2);
		this.minHeight = (int) (minHeight + 2 + borderSize * 3 + titlePaneHeight);
		this.minContentWidth = (int) minWidth;
		this.minContentHeight = (int) minHeight;
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
