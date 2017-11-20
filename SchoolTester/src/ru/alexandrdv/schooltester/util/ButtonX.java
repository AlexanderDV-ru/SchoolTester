package ru.alexandrdv.schooltester.util;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.font.FontRenderContext;
//import java.util.ArrayList;
//
//import javax.swing.JPanel;
//
///**
// * ButtonX
// * 
// * @author AlexandrDV
// *
// */

//Deprecated: replaced with ru.alexandrdv.components.ButtonX from Library

public class ButtonX// extends JPanel
{
//
//	private static final long serialVersionUID = 5629188079550741270L;
//	private boolean selected = false, clicked = false, pressed = false;
//	private Color selectedColor, pressedColor, normalColor, clickedColor, clickedSelectedColor, clickedPressedColor;
//	private Color frameClickedColor, framePressedColor, frameNormalColor, frameSelectedColor, frameClickedSelectedColor, frameClickedPressedColor;
//	private ArrayList<ActionListener> actionListeners;
//	private String text;
//	private int rounding, framesize = 4;
//	private Color backgroundColor, frameColor = new Color(255, 150, 0), textColor = Color.WHITE;
//	private boolean[] rect;
//
//	/**
//	 * 
//	 * @param normalColor
//	 *            - background color of ButtonX in standard state
//	 * @param selectedColor
//	 *            - background color of ButtonX in selected state
//	 * @param pressedColor
//	 *            - background color of ButtonX in pressed state
//	 * @param clickedColor
//	 *            - background color of ButtonX in standard after click
//	 * @param clickedSelectedColor
//	 *            - background color of ButtonX in selected state after click
//	 * @param clickedPressedColor
//	 *            - background color of ButtonX in pressed state after click
//	 * @param frameNormalColor
//	 *            - frame color of ButtonX in standard state
//	 * @param frameSelectedColor
//	 *            - frame color of ButtonX in selected state
//	 * @param framePressedColor
//	 *            - frame color of ButtonX in pressed state
//	 * @param frameClickedColor
//	 *            - frame color of ButtonX in standard state after click
//	 * @param frameClickedSelectedColor
//	 *            - frame color of ButtonX in selected state after click
//	 * @param frameClickedPressedColor
//	 *            - frame color of ButtonX in pressed state after click
//	 * @param text
//	 *            - text printed in ButtonX
//	 * @param rect
//	 *            - angles' types of ButtonX rectangle
//	 */
//	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor,
//			Color frameNormalColor, Color frameSelectedColor, Color framePressedColor, Color frameClickedColor, Color frameClickedSelectedColor,
//			Color frameClickedPressedColor, String text, boolean[] rect)
//	{
//		rounding = 15;
//		actionListeners = new ArrayList<ActionListener>();
//		this.text = text;
//		this.rect = rect;
//		addMouseListener(new MouseAdapter()
//		{
//			@Override
//			public void mouseEntered(MouseEvent e)
//			{
//				selected = true;
//				update();
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e)
//			{
//				pressed = true;
//				update();
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e)
//			{
//				clicked = true;
//				pressed = false;
//				update();
//				ActionEvent ev = new ActionEvent(ButtonX.this, 45, "click");
//				if (actionListeners != null)
//					for (ActionListener listener : actionListeners)
//						listener.actionPerformed(ev);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e)
//			{
//				selected = false;
//				update();
//			}
//		});
//		this.clickedColor = clickedColor;
//		this.pressedColor = pressedColor;
//		this.normalColor = normalColor;
//		this.selectedColor = selectedColor;
//		this.clickedSelectedColor = clickedSelectedColor;
//		this.clickedPressedColor = clickedPressedColor;
//		this.frameClickedColor = frameClickedColor;
//		this.framePressedColor = framePressedColor;
//		this.frameNormalColor = frameNormalColor;
//		this.frameSelectedColor = frameSelectedColor;
//		this.frameClickedSelectedColor = frameClickedSelectedColor;
//		this.frameClickedPressedColor = frameClickedPressedColor;
//		update();
//	}
//
//	/**
//	 * 
//	 * @param normalColor
//	 *            - background color of ButtonX in standard state
//	 * @param selectedColor
//	 *            - background color of ButtonX in selected state
//	 * @param pressedColor
//	 *            - background color of ButtonX in pressed state
//	 * @param clickedColor
//	 *            - background color of ButtonX in standard after click
//	 * @param clickedSelectedColor
//	 *            - background color of ButtonX in selected state after click
//	 * @param clickedPressedColor
//	 *            - background color of ButtonX in pressed state after click
//	 * @param text
//	 *            - text printed in ButtonX
//	 * @param frameSize
//	 *            - size of ButtonX frame
//	 * @param rect
//	 *            - angles' types of ButtonX rectangle
//	 */
//	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor,
//			String text, int frameSize, boolean[] rect)
//	{
//		this(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, reduceBrightness(normalColor, frameSize),
//				reduceBrightness(selectedColor, frameSize), reduceBrightness(pressedColor, frameSize), reduceBrightness(clickedColor, frameSize),
//				reduceBrightness(clickedSelectedColor, frameSize), reduceBrightness(clickedPressedColor, frameSize), text, rect);
//	}
//
//	/**
//	 * 
//	 * @param normalColor
//	 *            - background color of ButtonX in standard state
//	 * @param selectedColor
//	 *            - background color of ButtonX in selected state
//	 * @param pressedColor
//	 *            - background color of ButtonX in pressed state
//	 * @param clickedColor
//	 *            - background color of ButtonX in standard after click
//	 * @param clickedSelectedColor
//	 *            - background color of ButtonX in selected state after click
//	 * @param clickedPressedColor
//	 *            - background color of ButtonX in pressed state after click
//	 * @param text
//	 *            - text printed in ButtonX
//	 * @param hasFrame
//	 *            - indicates frame presence
//	 * @param rect
//	 *            - angles' types of ButtonX rectangle
//	 */
//	public ButtonX(Color normalColor, Color selectedColor, Color pressedColor, Color clickedColor, Color clickedSelectedColor, Color clickedPressedColor,
//			String text, boolean hasFrame, boolean[] rect)
//	{
//		this(normalColor, selectedColor, pressedColor, clickedColor, clickedSelectedColor, clickedPressedColor, text, hasFrame ? 20 : 0, rect);
//	}
//
//	/**
//	 * 
//	 * @param text
//	 *            - text printed in ButtonX
//	 * @param hasFrame
//	 *            - indicates frame presence
//	 * @param rect
//	 *            - angles' types of ButtonX rectangle
//	 */
//	public ButtonX(String text, boolean hasFrame, boolean[] rect)
//	{
//		this(Color.white, Color.lightGray, Color.gray, new Color(0, 150, 255), new Color(0, 120, 220), new Color(0, 70, 170), text, hasFrame, rect);
//	}
//
//	/**
//	 * 
//	 * @param text
//	 *            - text
//	 * @param font
//	 *            - font
//	 * @return
//	 */
//	private int width(String text, Font font)
//	{
//		return (int) font.getStringBounds(text, new FontRenderContext(null, true, true)).getWidth();
//	}
//
//	/**
//	 * 
//	 * @param text
//	 *            - text
//	 * @param font
//	 *            - font
//	 * @return
//	 */
//	private int height(String text, Font font)
//	{
//		return (int) font.getStringBounds(text, new FontRenderContext(null, true, true)).getHeight();
//	}
//
//	/**
//	 * 
//	 * @param g
//	 *            - Graphics
//	 * @param x1
//	 *            - first x
//	 * @param y1
//	 *            - first y
//	 * @param x2
//	 *            - second x
//	 * @param y2
//	 *            - second y
//	 * @param rounding
//	 *            - power of rounding angles of rectangle
//	 */
//	void fillRoundedRect(Graphics g, int x1, int y1, int x2, int y2, int rounding)
//	{
//		if (rect[0])
//			g.fillRect(x1, y1, (x2 - x1) / 2, (y2 - y1) / 2);
//		else g.fillOval(x1, y1, rounding * 2, rounding * 2);
//		if (rect[1])
//			g.fillRect(x1, (y2 - y1) / 2 + y1, (x2 - x1) / 2, (y2 - y1) / 2);
//		else g.fillOval(x1, y2 - rounding * 2, rounding * 2, rounding * 2);
//		if (rect[2])
//			g.fillRect((x2 - x1) / 2 + x1, (y2 - y1) / 2 + y1, (x2 - x1) / 2, (y2 - y1) / 2);
//		else g.fillOval(x2 - rounding * 2, y2 - rounding * 2, rounding * 2, rounding * 2);
//		if (rect[3])
//			g.fillRect((x2 - x1) / 2 + x1 + 1, y1, (x2 - x1) / 2, (y2 - y1) / 2);
//		else g.fillOval(x2 - rounding * 2, y1, rounding * 2, rounding * 2);
//		g.fillRect(x1 + rounding, y1, x2 - rounding * 2 - x1, y2 - y1 + 1);
//		g.fillRect(x1, y1 + rounding, x2 - x1 + 1, y2 - rounding * 2 - y1);
//	}
//
//	/**
//	 * Returns 'color' with reduced using power brightness
//	 * 
//	 * @param color
//	 *            - color to reducing brightness
//	 * @param power
//	 *            - power of brightness reducing
//	 * @return Color 'color' with brightness reduced using power
//	 */
//	public static Color reduceBrightness(Color color, int power)
//	{
//		return new Color(clamp(0, color.getRed() - power, 255), clamp(0, color.getGreen() - power, 255), clamp(0, color.getBlue() - power, 255));
//	}
//
//	/**
//	 * @param g
//	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
//	 */
//	@Override
//	public void paint(Graphics g)
//	{
//		g.setColor(frameColor);
//		fillRoundedRect(g, 1, 1, getWidth() - 1, getHeight() - 1 - 3, rounding);
//		g.setColor(backgroundColor);
//		fillRoundedRect(g, framesize, framesize, getWidth() - framesize, getHeight() - 3 - framesize, rounding - 2);
//
//		g.setColor(textColor);
//		g.setFont(getFont());
//		int strings = text.split("\n").length;
//		int i = 1;
//		for (String text : text.split("\n"))
//			g.drawString(text, (getWidth() - width(text, getFont())) / 2, 0 + (getHeight()) / (strings + 1) * i++ + height(text, getFont()) / 4);
//	}
//
//	/**
//	 * Updates color of ButtonX background and color of ButtonX frame
//	 */
//	public void update()
//	{
//		setBackground(isPressed() ? (isClicked() ? clickedPressedColor : pressedColor)
//				: (isClicked() ? (isSelected() ? clickedSelectedColor : clickedColor) : (isSelected() ? selectedColor : normalColor)));
//		setFrame(isPressed() ? (isClicked() ? frameClickedPressedColor : framePressedColor)
//				: (isClicked() ? (isSelected() ? frameClickedSelectedColor : frameClickedColor) : (isSelected() ? frameSelectedColor : frameNormalColor)));
//	}
//
//	/**
//	 * Adds new ActionListener 'listener' to list of ActionListener's
//	 * @param listener - ActionListener to add
//	 */
//	public void addActionListener(ActionListener listener)
//	{
//		actionListeners.add(listener);
//	}
//
//	/**
//	 * 
//	 * @param min
//	 * @param val
//	 * @param max
//	 * @return
//	 */
//	public static int clamp(int min, int val, int max)
//	{
//		return Math.max(min, Math.min(val, max));
//	}
//
//	/**
//	 * Clears action listeners list
//	 */
//	public void clearActionListeners()
//	{
//		actionListeners.clear();
//	}
//
//	/**
//	 * Sets color of ButtonX frame to 'frameColor'
//	 * 
//	 * @param frameColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrame(Color frameColor)
//	{
//		this.frameColor = frameColor;
//		repaint();
//	}
//
//	/**
//	 * @return the backgroundColor
//	 */
//	public Color getBackgroundColor()
//	{
//		return backgroundColor;
//	}
//
//	/**
//	 * Sets color of ButtonX background to 'backgroundColor'
//	 * 
//	 * @param backgroundColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setBackgroundColor(Color backgroundColor)
//	{
//		this.backgroundColor = backgroundColor;
//		update();
//	}
//
//	/**
//	 * @return the frameColor
//	 */
//	public Color getFrameColor()
//	{
//		return frameColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param frameColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrameColor(Color frameColor)
//	{
//		this.frameColor = frameColor;
//		update();
//	}
//
//	/**
//	 * @return the frameClickedColor
//	 */
//	public Color getFrameClickedColor()
//	{
//		return frameClickedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param frameClickedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrameClickedColor(Color frameClickedColor)
//	{
//		this.frameClickedColor = frameClickedColor;
//		update();
//	}
//
//	/**
//	 * @return the framePressedColor
//	 */
//	public Color getFramePressedColor()
//	{
//		return framePressedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param framePressedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFramePressedColor(Color framePressedColor)
//	{
//		this.framePressedColor = framePressedColor;
//		update();
//	}
//
//	/**
//	 * @return the frameNormalColor
//	 */
//	public Color getFrameNormalColor()
//	{
//		return frameNormalColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param frameNormalColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrameNormalColor(Color frameNormalColor)
//	{
//		this.frameNormalColor = frameNormalColor;
//		update();
//	}
//
//	/**
//	 * @return the frameSelectedColor
//	 */
//	public Color getFrameSelectedColor()
//	{
//		return frameSelectedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param frameSelectedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrameSelectedColor(Color frameSelectedColor)
//	{
//		this.frameSelectedColor = frameSelectedColor;
//		update();
//	}
//
//	/**
//	 * @return the frameClickedSelectedColor
//	 */
//	public Color getFrameClickedSelectedColor()
//	{
//		return frameClickedSelectedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param frameClickedSelectedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrameClickedSelectedColor(Color frameClickedSelectedColor)
//	{
//		this.frameClickedSelectedColor = frameClickedSelectedColor;
//		update();
//	}
//
//	/**
//	 * @return the frameClickedPressedColor
//	 */
//	public Color getFrameClickedPressedColor()
//	{
//		return frameClickedPressedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param frameClickedPressedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFrameClickedPressedColor(Color frameClickedPressedColor)
//	{
//		this.frameClickedPressedColor = frameClickedPressedColor;
//		update();
//	}
//
//	/**
//	 * @return the text
//	 */
//	public String getText()
//	{
//		return text;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param text
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setText(String text)
//	{
//		this.text = text;
//		update();
//	}
//
//	/**
//	 * @return the rounding
//	 */
//	public int getRounding()
//	{
//		return rounding;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param rounding
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setRounding(int rounding)
//	{
//		this.rounding = rounding;
//		update();
//	}
//
//	/**
//	 * @return the frameSize
//	 */
//	public int getFramesize()
//	{
//		return framesize;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param framesize
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setFramesize(int frameSize)
//	{
//		this.framesize = frameSize;
//		update();
//	}
//
//	/**
//	 * @return the selected
//	 */
//	public boolean isSelected()
//	{
//		return selected;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param selected
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setSelected(boolean selected)
//	{
//		this.selected = selected;
//		update();
//	}
//
//	/**
//	 * @return the clicked
//	 */
//	public boolean isClicked()
//	{
//		return clicked;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param clicked
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setClicked(boolean clicked)
//	{
//		this.clicked = clicked;
//		update();
//	}
//
//	/**
//	 * @return the pressed
//	 */
//	public boolean isPressed()
//	{
//		return pressed;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param pressed
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setPressed(boolean pressed)
//	{
//		this.pressed = pressed;
//		update();
//	}
//
//	/**
//	 * @return the selectedColor
//	 */
//	public Color getSelectedColor()
//	{
//		return selectedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param selectedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setSelectedColor(Color selectedColor)
//	{
//		this.selectedColor = selectedColor;
//		update();
//	}
//
//	/**
//	 * @return the pressedColor
//	 */
//	public Color getPressedColor()
//	{
//		return pressedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param pressedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setPressedColor(Color pressedColor)
//	{
//		this.pressedColor = pressedColor;
//		update();
//	}
//
//	/**
//	 * @return the normalColor
//	 */
//	public Color getNormalColor()
//	{
//		return normalColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param normalColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setNormalColor(Color normalColor)
//	{
//		this.normalColor = normalColor;
//		update();
//	}
//
//	/**
//	 * @return the clickedColor
//	 */
//	public Color getClickedColor()
//	{
//		return clickedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedColor'
//	 * 
//	 * @param clickedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setClickedColor(Color clickedColor)
//	{
//		this.clickedColor = clickedColor;
//		update();
//	}
//
//	/**
//	 * @return the clickedSelectedColor
//	 */
//	public Color getClickedSelectedColor()
//	{
//		return clickedSelectedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX selected state after click to 'clickedSelectedColor'
//	 * 
//	 * @param clickedSelectedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setClickedSelectedColor(Color clickedSelectedColor)
//	{
//		this.clickedSelectedColor = clickedSelectedColor;
//		update();
//	}
//
//	/**
//	 * @return the clickedPressedColor
//	 */
//	public Color getClickedPressedColor()
//	{
//		return clickedPressedColor;
//	}
//
//	/**
//	 * Sets color of ButtonX pressed state after click to 'clickedPressedColor'
//	 * 
//	 * @param clickedPressedColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setClickedPressedColor(Color clickedPressedColor)
//	{
//		this.clickedPressedColor = clickedPressedColor;
//		update();
//	}
//
//	/**
//	 * @return the textColor
//	 */
//	public Color getTextColor()
//	{
//		return textColor;
//	}
//
//	/**
//	 * Sets color of ButtonX text to 'textColor'
//	 * 
//	 * @param textColor
//	 *            - color using to set color of ButtonX text
//	 */
//	public void setTextColor(Color textColor)
//	{
//		this.textColor = textColor;
//		update();
//	}
//
//	/**
//	 * Sets ButtonX background to 'backgroundColor'
//	 * 
//	 * @param backgroundColor
//	 *            - the backgroundColor to set
//	 */
//	@Override
//	public void setBackground(Color backgroundColor)
//	{
//		this.backgroundColor = backgroundColor;
//		repaint();
//	}
}
