package ru.alexanderdv.schooltester.main.utilities.othersubjects;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import ru.alexanderdv.schooltester.utilities.Config;
import ru.alexanderdv.schooltester.utilities.fx.ProtectedFXWindow;
import ru.alexanderdv.simpleutilities.MathUtils;

/**
 * 
 * @author AlexanderDV
 * @version 6.1.5a
 */
public final class ElectronicBooksPart extends ProtectedFXWindow
{
	private TabPane tabPane = InitElectronicBooksPart.instance.tabPane;

	public ElectronicBooksPart(URL url,boolean inDevelope)
	{
		super(null, url, 1, 1,inDevelope,true);
		updateBooks();
	}
	@Override
	protected void _resize(int w, int h)
	{
		
	}

	public void updateBooks()
	{
		tabPane.getTabs().clear();
		for (String s : new String[]
		{
				// "Dictionary_of_Ozhegov"
		})
		{
			String info = getByName(s, "BookInfo.txt");
			String metrics = getByName(s, "BookMetrics.txt");
			Config cfg = new Config(info);
			Config cfg2 = new Config(metrics);
			Tab tab = new Tab();
			tab.setText(cfg.getString("name", null, false));
			tab.setTooltip(new Tooltip(cfg.getString("description", null, false) + "\n" + cfg.getString("authors", null, false)));
			tabPane.getTabs().add(tab);
			AnchorPane pane = new AnchorPane();
			pane.setPrefSize(tabPane.getPrefWidth(), tabPane.getPrefHeight() - 30);
			tab.setContent(pane);

			TextArea textArea = new TextArea();
			textArea.setWrapText(true);
			textArea.setLayoutX(0);
			textArea.setLayoutY(30);
			textArea.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight() - 30);
			pane.getChildren().add(textArea);

			ComboBox<String> cb1 = new ComboBox<String>();
			HashMap<String, String> map = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();
			Font font = new Font(textArea.getFont().getName(), 0, (int) textArea.getFont().getSize());
			for (String s2 : metrics.split("\n"))
			{
				cb1.getItems().add(s2.split(":\t")[0]);
				String text = getByName(s, cfg2.getString(s2.split(":\t")[0], null, false));
				map.put(s2.split(":\t")[0], text);
				map2.put(s2.split(":\t")[0], MathUtils.changeTextToWidth(text, (int) (textArea.getPrefWidth() / (test2(text.replace("\n", " "), font)
						.getWidth() / text.length())), true, false));
			}
			cb1.setOnAction(e -> textArea.setText(map.get(cb1.getSelectionModel().getSelectedItem())));
			cb1.setLayoutX(0);
			cb1.setLayoutY(0);
			cb1.setPrefSize(60, 30);
			pane.getChildren().add(cb1);
			ComboBox<String> cb2 = new ComboBox<String>();
			cb2.setLayoutX(60);
			cb2.setLayoutY(0);
			cb2.setPrefSize(60, 30);
			pane.getChildren().add(cb2);

			ComboBox<String> cb3 = new ComboBox<String>();
			cb3.setLayoutX(120);
			cb3.setLayoutY(0);
			cb3.setPrefSize(60, 30);
			pane.getChildren().add(cb3);

			TextField search = new TextField();
			search.setLayoutX(pane.getPrefWidth() - 150);
			search.setLayoutY(0);
			search.setPrefSize(150, 30);
			search.setOnAction(e ->
			{
				String tt = map2.get(cb1.getSelectionModel().getSelectedItem());
				java.awt.TextArea ta = new java.awt.TextArea();
				ta.setSize((int) textArea.getWidth(), (int) textArea.getHeight());
				int h = 0;
				for (String s9 : tt.substring(0, tt.indexOf(search.getText().toUpperCase())).split("\n"))
					h += test1(s9, font).getHeight();
				textArea.setScrollTop(h - h + tt.substring(0, tt.indexOf(search.getText().toUpperCase())).split("\n").length * 37);// textArea.getText().split("\n").length);
			});
			pane.getChildren().add(search);
		}
	}

	private static Rectangle2D test1(String text, Font font)
	{
		TextLayout tl = new TextLayout(text, font, new FontRenderContext(null, true, true));
		return tl.getBounds();
	}

	private static Rectangle2D test2(String text, Font font)
	{
		return font.getStringBounds(text, new FontRenderContext(null, true, true));
	}

	public String getByName(String r, String s)
	{
		try
		{
			File file = new File(getClass().getResource("/" + r + "/" + s).getPath());
			if (!file.exists())
				file.createNewFile();
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader pw = new InputStreamReader(fis, "UTF-8");
			char[] bs = new char[fis.available()];
			pw.read(bs);
			pw.close();
			fis.close();
			return new String(bs);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String name()
	{
		return "electronicBooks";
	}
}
