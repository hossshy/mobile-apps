/*
 * Last modified: 2009/12/16 15:47:12
 * author: Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.main.AbstractCanvas;

public class StrongString
{
	private Color centerColor;
	private Color aroundColor;

	public StrongString(Color centerColor, Color aroundColor)
	{
		this.centerColor = centerColor;
		this.aroundColor = aroundColor;
	}

	public void paint(AbstractCanvas g, String str, int x, int y)
	{
		g.setColor(aroundColor);
		g.drawString(str, x - 1, y);
		g.drawString(str, x + 1, y);
		g.drawString(str, x, y - 1);
		g.drawString(str, x, y + 1);
		g.setColor(centerColor);
		g.drawString(str, x, y);
	}
}