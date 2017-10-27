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

	private static final long serialVersionUID = 5629188079550741270L;
	private boolean selected = false, clicked = false, pressed = false;
	private Color selectedColor, pressedColor, normalColor, clickedColor, clickedSelectedColor, clickedPressedColor;
	private Color frameClickedColor, framePressedColor, frameNormalColor, frameSelectedColor, frameClickedSelectedColor, frameClickedPressedColor;
	private ArrayList<ActionListener> actionListeners;
	private String text;
	private int rounding, framesize = 4;
	private Color backgroundColor, frameColor = new Color(255, 150, 0), textColor = Color.WHITE;
	private boolean[] rect;

	/**
	 * 
	 * @param text
	 * @param font
	 * @return
	 */
	private int width(String text, Font font)
	{
		return (int) font.getStringBounds(text, new FontRenderContext(null, true, true)).getWidth();
	}

	/**
	 * 
	 * @param text
	 * @param font
	 * @return
	 */
	private int height(String text, Font font)
	{
		return (int) font.getStringBounds(text, new FontRenderContext(null, true, true)).getHeight();
	}

	/**
	 * 
	 * @param g
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param round
	 */
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

	/**
	 * 
	 * @param normalColor
	 * @param selectedColor
	 * @param pressedColor
	 * @param clickedColor
	 * @param clickedSelectedColor
	 * @param clickedPressedColor
	 * @param frameNormalColor
	 * @param frameSelectedColor
	 * @param framePressedColor
	 * @param frameClickedColor
	 * @param frameClickedSelectedColor
	 * @param frameClickedPressedColor
	 * @param text
	 * @param rect
	 */
	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor, Color frameClickedPressedColor, String text, boolean[] rect)
	{
		rounding = 15;
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
				ActionEvent ev = new ActionEvent(ButtonX.this, 45, "click");
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

	/**
	 * 
	 * @param normalColor
	 * @param selectedColor
	 * @param pressedColor
	 * @param clickedColor
	 * @param clickedSelectedColor
	 * @param clickedPressedColor
	 * @param text
	 * @param hasFrame
	 * @param rect
	 */
	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, String text, boolean hasFrame, boolean[] rect)
	{
		this(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, text, hasFrame ? 20 : 0, rect);
	}

	/**
	 * 
	 * @param normalColor
	 * @param selectedColor
	 * @param pressedColor
	 * @param clickedColor
	 * @param clickedSelectedColor
	 * @param clickedPressedColor
	 * @param text
	 * @param framePower
	 * @param rect
	 */
	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor, String text, int framePower, boolean[] rect)
	{
		this(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, night(normalColor, framePower), night(selectedColor, framePower), night(pressedColor, framePower), night(clickedColor, framePower), night(clickedSelectedColor, framePower), night(clickedPressedColor, framePower), text, rect);
	}

	/**
	 * 
	 * @param text
	 * @param hasFrame
	 * @param rect
	 */
	public ButtonX(String text, boolean hasFrame, boolean[] rect)
	{
		this(Color.white, Color.lightGray, Color.gray, new Color(0, 150, 255), new Color(0, 120, 220), new Color(0, 70, 170), text, hasFrame, rect);
	}

	/**
	 * 
	 * @param c
	 * @param power
	 * @return
	 */
	public static Color night(Color c, int power)
	{
		return new Color(clamp(0, c.getRed() - power, 255), clamp(0, c.getGreen() - power, 255), clamp(0, c.getBlue() - power, 255));
	}

	/**
	 * @param g
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g)
	{
		// Drawing rounded rectangle for message
		g.setColor(frameColor);
		fillRoundedRect(g, 1, 1, getWidth() - 1, getHeight() - 1 - 3, rounding);
		g.setColor(backgroundColor);
		fillRoundedRect(g, framesize, framesize, getWidth() - framesize, getHeight() - 3 - framesize, rounding - 2);

		g.setColor(textColor);
		g.setFont(getFont());
		int strings = text.split("\n").length;
		int i = 1;
		for (String text : text.split("\n"))
			g.drawString(text, (getWidth() - width(text, getFont())) / 2, 0 + (getHeight()) / (strings + 1) * i++ + height(text, getFont()) / 4);
	}

	/**
	 * 
	 */
	public void update()
	{
		setBackground(isPressed() ? (isClicked() ? clickedPressedColor : pressedColor) : (isClicked() ? (isSelected() ? clickedSelectedColor : clickedColor) : (isSelected() ? selectedColor : normalColor)));
		setFrame(isPressed() ? (isClicked() ? frameClickedPressedColor : framePressedColor) : (isClicked() ? (isSelected() ? frameClickedSelectedColor : frameClickedColor) : (isSelected() ? frameSelectedColor : frameNormalColor)));
	}

	/**
	 * 
	 * @param listener
	 */
	public void addActionListener(ActionListener listener)
	{
		actionListeners.add(listener);
	}

	/**
	 * 
	 * @param min
	 * @param val
	 * @param max
	 * @return
	 */
	public static int clamp(int min, int val, int max)
	{
		return Math.max(min, Math.min(val, max));
	}

	/**
	 * 
	 */
	public void clearActionListeners()
	{
		actionListeners.clear();
	}

	/**
	 * @param frameColor
	 *            the frameColor to set
	 */
	public void setFrame(Color frameColor)
	{
		this.frameColor = frameColor;
		repaint();
	}

	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		update();
	}

	/**
	 * @return the frameColor
	 */
	public Color getFrameColor()
	{
		return frameColor;
	}

	/**
	 * @param frameColor
	 *            the frameColor to set
	 */
	public void setFrameColor(Color frameColor)
	{
		this.frameColor = frameColor;
		update();
	}

	/**
	 * @return the frameClickedColor
	 */
	public Color getFrameClickedColor()
	{
		return frameClickedColor;
	}

	/**
	 * @param frameClickedColor
	 *            the frameClickedColor to set
	 */
	public void setFrameClickedColor(Color frameClickedColor)
	{
		this.frameClickedColor = frameClickedColor;
		update();
	}

	/**
	 * @return the framePressedColor
	 */
	public Color getFramePressedColor()
	{
		return framePressedColor;
	}

	/**
	 * @param framePressedColor
	 *            the framePressedColor to set
	 */
	public void setFramePressedColor(Color framePressedColor)
	{
		this.framePressedColor = framePressedColor;
		update();
	}

	/**
	 * @return the frameNormalColor
	 */
	public Color getFrameNormalColor()
	{
		return frameNormalColor;
	}

	/**
	 * @param frameNormalColor
	 *            the frameNormalColor to set
	 */
	public void setFrameNormalColor(Color frameNormalColor)
	{
		this.frameNormalColor = frameNormalColor;
		update();
	}

	/**
	 * @return the frameSelectedColor
	 */
	public Color getFrameSelectedColor()
	{
		return frameSelectedColor;
	}

	/**
	 * @param frameSelectedColor
	 *            the frameSelectedColor to set
	 */
	public void setFrameSelectedColor(Color frameSelectedColor)
	{
		this.frameSelectedColor = frameSelectedColor;
		update();
	}

	/**
	 * @return the frameClickedSelectedColor
	 */
	public Color getFrameClickedSelectedColor()
	{
		return frameClickedSelectedColor;
	}

	/**
	 * @param frameClickedSelectedColor
	 *            the frameClickedSelectedColor to set
	 */
	public void setFrameClickedSelectedColor(Color frameClickedSelectedColor)
	{
		this.frameClickedSelectedColor = frameClickedSelectedColor;
		update();
	}

	/**
	 * @return the frameClickedPressedColor
	 */
	public Color getFrameClickedPressedColor()
	{
		return frameClickedPressedColor;
	}

	/**
	 * @param frameClickedPressedColor
	 *            the frameClickedPressedColor to set
	 */
	public void setFrameClickedPressedColor(Color frameClickedPressedColor)
	{
		this.frameClickedPressedColor = frameClickedPressedColor;
		update();
	}

	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
		update();
	}

	/**
	 * @return the rounding
	 */
	public int getRounding()
	{
		return rounding;
	}

	/**
	 * @param rounding
	 *            the rounding to set
	 */
	public void setRounding(int rounding)
	{
		this.rounding = rounding;
		update();
	}

	/**
	 * @return the framesize
	 */
	public int getFramesize()
	{
		return framesize;
	}

	/**
	 * @param framesize
	 *            the framesize to set
	 */
	public void setFramesize(int framesize)
	{
		this.framesize = framesize;
		update();
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected)
	{
		this.selected = selected;
		update();
	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked()
	{
		return clicked;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
		update();
	}

	/**
	 * @return the pressed
	 */
	public boolean isPressed()
	{
		return pressed;
	}

	/**
	 * @param pressed
	 *            the pressed to set
	 */
	public void setPressed(boolean pressed)
	{
		this.pressed = pressed;
		update();
	}

	/**
	 * @return the selectedColor
	 */
	public Color getSelectedColor()
	{
		return selectedColor;
	}

	/**
	 * @param selectedColor
	 *            the selectedColor to set
	 */
	public void setSelectedColor(Color selectedColor)
	{
		this.selectedColor = selectedColor;
		update();
	}

	/**
	 * @return the pressedColor
	 */
	public Color getPressedColor()
	{
		return pressedColor;
	}

	/**
	 * @param pressedColor
	 *            the pressedColor to set
	 */
	public void setPressedColor(Color pressedColor)
	{
		this.pressedColor = pressedColor;
		update();
	}

	/**
	 * @return the normalColor
	 */
	public Color getNormalColor()
	{
		return normalColor;
	}

	/**
	 * @param normalColor
	 *            the normalColor to set
	 */
	public void setNormalColor(Color normalColor)
	{
		this.normalColor = normalColor;
		update();
	}

	/**
	 * @return the clickedColor
	 */
	public Color getClickedColor()
	{
		return clickedColor;
	}

	/**
	 * @param clickedColor
	 *            the clickedColor to set
	 */
	public void setClickedColor(Color clickedColor)
	{
		this.clickedColor = clickedColor;
		update();
	}

	/**
	 * @return the clickedSelectedColor
	 */
	public Color getClickedSelectedColor()
	{
		return clickedSelectedColor;
	}

	/**
	 * @param clickedSelectedColor
	 *            the clickedSelectedColor to set
	 */
	public void setClickedSelectedColor(Color clickedSelectedColor)
	{
		this.clickedSelectedColor = clickedSelectedColor;
		update();
	}

	/**
	 * @return the clickedPressedColor
	 */
	public Color getClickedPressedColor()
	{
		return clickedPressedColor;
	}

	/**
	 * @param clickedPressedColor
	 *            the clickedPressedColor to set
	 */
	public void setClickedPressedColor(Color clickedPressedColor)
	{
		this.clickedPressedColor = clickedPressedColor;
		update();
	}

	/**
	 * @return the textColor
	 */
	public Color getTextColor()
	{
		return textColor;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(Color textColor)
	{
		this.textColor = textColor;
		update();
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	@Override
	public void setBackground(Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		repaint();
	}
}
