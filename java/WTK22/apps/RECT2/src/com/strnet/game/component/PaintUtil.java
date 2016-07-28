/*
 * Last modified: 2010/05/17 01:06:09
 * author: Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.main.AbstractCanvas;

public class PaintUtil
{
	public static void drawBoldString(AbstractCanvas g, String str, int x, int y)
	{
		g.setColor(255,180,180);
		g.drawString(str, x+1, y);
		g.setColor(Color.WHITE);
		g.drawString(str, x, y);
	}
}