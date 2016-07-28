/*
 * Last modified: 2008/10/29 22:46:06
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.main.AbstractCanvas;

public class CheapPainter implements Painter
{
	Color background = new Color(0,0,0);
	public int getRevise()
	{
		return 0;
	}
	
	public void paint(AbstractCanvas g, int x, int y, int width, int height)
	{
		g.setColor(background);
		g.fillRect(x, y-2, width, height+4);
	}
}
