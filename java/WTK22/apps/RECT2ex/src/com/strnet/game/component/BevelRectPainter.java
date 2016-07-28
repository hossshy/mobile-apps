/*
 * Last modified: 2008/09/07 23:00:16
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.main.AbstractCanvas;

public class BevelRectPainter implements Painter
{
	Color leftBorder = null;
	Color rightBorder = null;
	Color upBorder = null;
	Color downBorder = null;
	Color background = null;
	int borderWidth = 1;
	
	public void setNormalColor(Color background)
	{
		this.background = background;
		int revise;
		
		revise = -100;
		leftBorder = new Color(background.r + revise, background.g + revise, background.b + revise);
		revise = 70;
		rightBorder = new Color(background.r + revise, background.g + revise, background.b + revise);
		revise = -70;
		upBorder = new Color(background.r + revise, background.g + revise, background.b + revise);
		revise = 100;
		downBorder = new Color(background.r + revise, background.g + revise, background.b + revise);
	}
	
	public void setBorderWidth(int borderWidth)
	{
		this.borderWidth = borderWidth;
	}

	public int getRevise()
	{
		return borderWidth + 2;
	}
	
	public void paint(AbstractCanvas g, int x, int y, int width, int height)
	{
		g.setColor(background);
		g.fillRect(x, y, width, height);
		g.setColor(leftBorder);
		g.fillRect(x, y, borderWidth, height);
		g.setColor(upBorder);
		g.fillRect(x, y, width, borderWidth);
		g.setColor(rightBorder);
		g.fillRect((x + width) - borderWidth, y, borderWidth, height);
		g.setColor(downBorder);
		g.fillRect(x, (y + height) - borderWidth, width, borderWidth);
	}
}
