package ru.alexanderdv.schooltester.utilities.fx;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.Timer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import ru.alexanderdv.schooltester.main.Main;
import ru.alexanderdv.schooltester.utilities.Logger;
import ru.alexanderdv.schooltester.utilities.Logger.ExitCodes;
import ru.alexanderdv.schooltester.utilities.types.StageContainer;
import ru.alexanderdv.simpleutilities.MathWithText;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class FXDialogsGenerator
{
	private static Stage lastDialogStage;

	/**
	 * Creates FXDialog with owner - 'primaryStage', with message - 'msg', with type of message - 'messageType', with type of option - 'optionType' in the
	 * middle of 'Component'
	 *
	 * @param comp
	 *            - the component in the middle of them FXDialog will be created
	 * @param primaryStage
	 *            - the owner "Stage"
	 * @param msg
	 *            - the message of created FXDialog
	 * @param messageType
	 *            - the messageType of created FXDialog
	 * @param optionType
	 *            - the optionType of created FXDialog
	 * @param frameIsFX
	 *            - the type of window frame
	 */
	public static Stage showFXDialog(StageContainer _comp, Rectangle parPos, Object msgm, int messageType, Region region, boolean wait)
	{
		Object msg = msgm;
		Stage comp = _comp != null ? _comp.getStage(new StageContainer.Wrapper()
		{

			@Override
			public char[] check(int algo)
			{
				return (algo / 10000 % 10 + "" + algo / 1000 % 10 + "" + algo / 100 % 10 + "" + algo / 10 % 10 + "" + algo % 10).toCharArray();
			}
		}) : null;
		Stage stage = lastDialogStage = new Stage();
		try
		{
			class cccc
			{
				Runnable r;
			}
			cccc ccccv = new cccc();
			stage.setTitle(Main.program);
			stage.initStyle(StageStyle.UNDECORATED);
			FXScene fxScene = new FXScene(stage, 1, Main.program, false, 1);
			Pane panel = new Pane();
			ImageView imageView;
			MediaView mediaView = new MediaView();
			Dimension size = msg instanceof String ? MathWithText.size((String) msg, new Font("System", 0, 12)) : new Dimension();

			if (msg instanceof String)
			{
				int w = ((int) (MathWithText.size(((String) msg).split("\n")[0], new Font("System", 0, 12)).getWidth() / ((String) msg).split("\n")[0]
						.length()));
				if (size.getWidth() > 800)
				{
					msg = MathWithText.changeTextToWidth((String) msg, 800 / w, true, true);
					size = MathWithText.size((String) msg, new Font("System", 0, 12));
				}
			}
			Label label = new Label(msg.toString());
			if (msg instanceof Image)
			{
				imageView = new ImageView();
				imageView.setImage((Image) msg);
				Dimension screenedSize = toScreen(((Image) msg).getWidth(), ((Image) msg).getHeight());
				int width = Math.max(screenedSize.width, 40 + MathWithText.size(stage.getTitle(), fxScene.getTitleFont()).width + 30);
				int height = screenedSize.height;
				imageView.setFitWidth(width);
				imageView.setFitHeight(height);
				panel.getChildren().add(imageView);
				size = new Dimension(width, height);
			}
			else if (msg instanceof String)
				panel.getChildren().add(label);
			else if (msg instanceof Media)
			{
				Media media = (Media) msg;
				MediaPlayer mediaPlayer = new MediaPlayer(media);
				mediaView.setMediaPlayer(mediaPlayer);
				mediaPlayer.play();
				panel.getChildren().add(mediaView);
				Dimension screenedSize = toScreen(media.getWidth(), media.getHeight());
				int width = Math.max(screenedSize.width, 40 + MathWithText.size(stage.getTitle(), fxScene.getTitleFont()).width + 30);
				int height = screenedSize.height;
				mediaView.setFitWidth(Math.min(media.getWidth(), width));
				mediaView.setFitHeight(Math.min(media.getHeight(), height));
				Button btn = new Button("||");
				btn.setOnAction(e ->
				{
					if (btn.getText().equals("||"))
					{
						mediaPlayer.pause();
						btn.setText(">");
					}
					else
					{
						mediaPlayer.play();
						btn.setText("||");
					}
				});
				btn.setPrefSize(30, 30);
				btn.setLayoutY(height);
				class B
				{
					boolean b;
				}
				B moving = new B();
				Timer timer = new Timer(100, e ->
				{
					if (!moving.b)
						mediaPlayer.stop();
					if (moving.b)
						if (btn.getText().equals("||"))
							mediaPlayer.play();
					moving.b = false;
				});
				ccccv.r = () -> timer.stop();
				panel.getChildren().add(btn);
				{
					Slider slider = new Slider(0, media.getDuration().toSeconds(), 0);
					B bbb = new B();
					Runnable rr = () ->
					{
						if (bbb.b)
						{
							mediaPlayer.stop();
							mediaPlayer.setStartTime(new Duration(slider.getValue() * 1000));
							if (btn.getText().equals("||"))
								mediaPlayer.play();
						}
					};
					Runnable on = () ->
					{
						bbb.b = true;
						rr.run();
					};
					Runnable off = () ->
					{
						bbb.b = false;
					};
					slider.setOnMousePressed(e -> on.run());
					slider.setOnMouseReleased(e -> off.run());
					slider.setOnMouseClicked(e -> rr.run());
					slider.setOnMouseMoved(e -> moving.b = true);
					slider.valueProperty().addListener((a, b, c) -> rr.run());
					slider.setPrefSize(width - 30, 30);
					slider.setLayoutX(30);
					slider.setLayoutY(height);
					panel.getChildren().add(slider);
					mediaPlayer.currentTimeProperty().addListener((a, b, c) ->
					{
						if (!bbb.b)
							slider.setValue(mediaPlayer.getCurrentTime().toSeconds());
					});
				}
				{
					Slider slider = new Slider(-2, 2, 0);
					slider.valueProperty().addListener((a, b, c) ->
					{
						double x = slider.getValue();
						double d = 1d / Math.abs(((Math.abs(x) - x) / 2d + 1d)) + (Math.abs(x) + x) / 2d / 4d * 3d;
						mediaPlayer.setRate(d);
					});
					slider.setPrefSize(width / 2, 30);
					slider.setLayoutX(0);
					slider.setLayoutY(height + 30);
					panel.getChildren().add(slider);
				}
				{
					Slider slider = new Slider(0, 1, 0);
					slider.setValue(mediaPlayer.getVolume());
					slider.valueProperty().addListener((a, b, c) -> mediaPlayer.setVolume(slider.getValue()));
					slider.setPrefSize(width / 2, 30);
					slider.setLayoutX(width / 2);
					slider.setLayoutY(height + 30);
					panel.getChildren().add(slider);
				}
				size = new Dimension(width, media.getHeight() + 60);
			}

			if (region != null)
			{
				panel.getChildren().add(region);
				System.out.println(region.getPrefHeight());
				region.setLayoutY(size.height);
				size.height += (int) region.getPrefHeight();
				size.width = Math.max(size.width, (int) region.getPrefHeight());
			}
			int pw = (int) Math.max(size.getWidth(), 40 + MathWithText.size(stage.getTitle(), fxScene.getTitleFont()).width + fxScene.getButtonsWidth()),
					ph = (int) size.getHeight(), mw = pw, mh = ph;
			fxScene.setContent(panel, pw, ph, mw, mh);
			stage.setScene(new Scene(fxScene));
			stage.sizeToScene();
			stage.setResizable(false);
			stage.getIcons().clear();
			stage.getIcons().add(new Image(Logger.class.getResource("/Icon32x.png").openStream()));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(comp);
			if (parPos != null)
			{
				stage.setX((int) (parPos.getX() + parPos.getHeight() / 2 - stage.getWidth() / 2));
				stage.setY((int) (parPos.getY() + parPos.getHeight() / 2 - stage.getHeight() / 2));
			}
			stage.setOnCloseRequest(e ->
			{
				if (ccccv.r != null)
					ccccv.r.run();
				if (lastDialogStage == stage)
					lastDialogStage = null;
				stage.close();
				if (mediaView.getMediaPlayer() != null)
					mediaView.getMediaPlayer().stop();
				if (comp != null)
					comp.requestFocus();
			});
			return stage;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Main.exit(ExitCodes.UnknownError);
			return null;
		}
		finally
		{
			if (wait)
				stage.showAndWait();
			else stage.show();
		}
	}

	private static Dimension toScreen(double d1, double d2)
	{
		if (d1 < 1 || d2 < 1)
			return new Dimension(0, 0);
		double r1 = Math.max(0, d1 - (FXScene.maxX - FXScene.minX));
		double r2 = Math.max(0, d2 - (FXScene.maxY - FXScene.minY));
		if (r1 > r2)
			return new Dimension((int) (d1 / d1 * (d1 - r1 - 100)), (int) (d2 / d1 * (d1 - r1 - 100)));
		else return new Dimension((int) (d1 / d2 * (d2 - r2 - 100)), (int) (d2 / d2 * (d2 - r2 - 100)));
	}

	public static Stage showFXDialog(StageContainer comp, Stage parPos, Object msg, int messageType, Region region, boolean wait)
	{
		return showFXDialog(comp, parPos != null ? new Rectangle((int) parPos.getX(), (int) parPos.getY(), (int) parPos.getWidth(), (int) parPos.getHeight())
				: null, msg, messageType, region, wait);
	}

	public static Stage showFXDialog(Stage comp, Stage parPos, Object msg, int messageType, Region region, boolean wait)
	{
		return showFXDialog(new StageContainer(comp), parPos, msg, messageType, region, wait);
	}

	public static Stage showFXDialog(Stage comp, Rectangle parPos, Object msg, int messageType, Region region, boolean wait)
	{
		return showFXDialog(new StageContainer(comp), parPos, msg, messageType, region, wait);
	}

	public static boolean hasShowedDialog()
	{
		return lastDialogStage != null;
	}

	public static void focus()
	{
		if (hasShowedDialog())
			lastDialogStage.requestFocus();
	}

	public static void closeLast()
	{
		if (lastDialogStage != null)
			lastDialogStage.close();
	}

}
