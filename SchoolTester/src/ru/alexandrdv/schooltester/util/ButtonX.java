package ru.alexandrdv.schooltester.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ButtonX extends JPanel

{
	String text;
	int size = 15, framesize = 4;
	Color backgroundColor, frameColor = new Color(255, 150, 0), textColor = Color.WHITE;
	boolean[] rect;

	@Override
	public void setBackground(Color bg)
	{
		backgroundColor = bg;
		repaint();
	}

	public Color getTextColor()
	{
		return textColor;
	}

	public void setTextColor(Color textColor)
	{
		this.textColor = textColor;
		update();
	}

	@Override
	public void paint(Graphics g)
	{
		// Drawing rounded rectangle for message
		g.setColor(frameColor);
		fillRoundedRect(g, 1, 1, getWidth() - 1, getHeight() - 1 - 3, size);
		g.setColor(backgroundColor);
		fillRoundedRect(g, framesize, framesize, getWidth() - framesize, getHeight() - 3 - framesize, size - 2);

		g.setColor(textColor);
		g.setFont(getFont());
		g.drawString(text, (getWidth() - width(text, getFont())) / 2, 0 + (getHeight()) / 2 + height(text, getFont()) / 4);

	}

	private int width(String text, Font font)
	{
		return (int) font.getStringBounds(text, new FontRenderContext(null, true, true)).getWidth();
	}

	private int height(String text, Font font)
	{
		return (int) font.getStringBounds(text, new FontRenderContext(null, true, true)).getHeight();
	}

	void fillRoundedRect(Graphics g, int x1, int y1, int x2, int y2, int round)
	{
		if (rect[0])
			g.fillRect(x1, y1, (x2 - x1) / 2, (y2 - y1) / 2);
		else g.fillOval(x1, y1, round * 2, round * 2);
		if (rect[1])
			g.fillRect(x1, (y2 - y1) / 2 + y1, (x2 - x1) / 2, (y2 - y1) / 2);
		else g.fillOval(x1, y2 - round * 2, round * 2, round * 2);
		if (rect[2])
			g.fillRect((x2 - x1) / 2 + x1, (y2 - y1) / 2 + y1, (x2 - x1) / 2, (y2 - y1) / 2);
		else g.fillOval(x2 - round * 2, y2 - round * 2, round * 2, round * 2);
		if (rect[3])
			g.fillRect((x2 - x1) / 2 + x1 + 1, y1, (x2 - x1) / 2, (y2 - y1) / 2);
		else g.fillOval(x2 - round * 2, y1, round * 2, round * 2);
		g.fillRect(x1 + round, y1, x2 - round * 2 - x1, y2 - y1 + 1);
		g.fillRect(x1, y1 + round, x2 - x1 + 1, y2 - round * 2 - y1);
	}

	private static final long serialVersionUID = 5629188079550741270L;
	private boolean selected = false, clicked = false, pressed = false;
	private Color selectedColor, pressedColor, normalColor, clickedColor, clickedSelectedColor, clickedPressedColor;
	private Color frameClickedColor, framePressedColor, frameNormalColor, frameSelectedColor, frameClickedSelectedColor, frameClickedPressedColor;
	private ArrayList<ActionListener> actionListeners;

	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor, Color frameClickedPressedColor, String text, boolean[] rect)
	{
		actionListeners = new ArrayList<ActionListener>();
		this.text = text;
		this.rect = rect;
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				selected = true;
				update();
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				pressed = true;
				update();
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				clicked = true;
				pressed = false;
				update();
				ActionEvent ev = new ActionEvent(this, 45, "click");
				if (actionListeners != null)
					for (ActionListener listener : actionListeners)
						listener.actionPerformed(ev);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				selected = false;
				update();
			}
		});
		this.clickedColor = clickedColor;
		this.pressedColor = pressedColor;
		this.normalColor = normalColor;
		this.selectedColor = selectedColor;
		this.clickedSelectedColor = clickedSelectedColor;
		this.clickedPressedColor = clickedPressedColor;
		this.frameClickedColor = frameClickedColor;
		this.framePressedColor = framePressedColor;
		this.frameNormalColor = frameNormalColor;
		this.frameSelectedColor = frameSelectedColor;
		this.frameClickedSelectedColor = frameClickedSelectedColor;
		this.frameClickedPressedColor = frameClickedPressedColor;
		update();
	}

	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, String text, boolean hasFrame, boolean[] rect)
	{
		this(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, text, hasFrame ? 20 : 0, rect);
	}

	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, String text, int framePower, boolean[] rect)
	{
		this(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, night(normalColor, framePower), night(selectedColor, framePower), night(pressedColor, framePower), night(clickedColor, framePower), night(clickedSelectedColor, framePower), night(clickedPressedColor, framePower), text, rect);
	}

	public static Color night(Color c, int power)
	{
		return new Color(clamp(0, c.getRed() - power, 255), clamp(0, c.getGreen() - power, 255), clamp(0, c.getBlue() - power, 255));
	}

	public ButtonX(String text, boolean hasFrame, boolean[] rect)
	{
		this(Color.white, Color.lightGray, Color.gray, new Color(0, 150, 255), new Color(0, 120, 220), new Color(0, 70, 170), text, hasFrame, rect);
	}

	public void update()
	{
		setBackground(isPressed() ? (isClicked() ? clickedPressedColor : pressedColor) : (isClicked() ? (isSelected() ? clickedSelectedColor : clickedColor) : (isSelected() ? selectedColor : normalColor)));
		setFrame(isPressed() ? (isClicked() ? frameClickedPressedColor : framePressedColor) : (isClicked() ? (isSelected() ? frameClickedSelectedColor : frameClickedColor) : (isSelected() ? frameSelectedColor : frameNormalColor)));
	}

	public void setFrame(Color frame)
	{
		frameColor = frame;
		repaint();
	}

	public void addActionListener(ActionListener listener)
	{
		actionListeners.add(listener);
	}

	public static int clamp(int min, int val, int max)
	{
		return Math.max(min, Math.min(val, max));
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
		update();
	}

	public boolean isClicked()
	{
		return clicked;
	}

	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
		update();
	}

	public boolean isPressed()
	{
		return pressed;
	}

	public void setPressed(boolean pressed)
	{
		this.pressed = pressed;
		update();
	}

	public Color getSelectedColor()
	{
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor)
	{
		this.selectedColor = selectedColor;
		update();
	}

	public Color getPressedColor()
	{
		return pressedColor;
	}

	public void setPressedColor(Color pressedColor)
	{
		this.pressedColor = pressedColor;
		update();
	}

	public Color getNormalColor()
	{
		return normalColor;
	}

	public void setNormalColor(Color normalColor)
	{
		this.normalColor = normalColor;
		update();
	}

	public Color getClickedColor()
	{
		return clickedColor;
	}

	public void setClickedColor(Color clickedColor)
	{
		this.clickedColor = clickedColor;
		update();
	}

	public Color getClickedSelectedColor()
	{
		return clickedSelectedColor;
	}

	public void setClickedSelectedColor(Color clickedSelectedColor)
	{
		this.clickedSelectedColor = clickedSelectedColor;
		update();
	}

	public Color getClickedPressedColor()
	{
		return clickedPressedColor;
	}

	public void setClickedPressedColor(Color clickedPressedColor)
	{
		this.clickedPressedColor = clickedPressedColor;
		update();
	}
}
