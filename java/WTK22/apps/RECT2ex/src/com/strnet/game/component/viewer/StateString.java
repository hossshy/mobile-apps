/*
 * Last modified: 2010/03/23 22:52:08
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.main.GameCommonCanvas;

class StateString extends StateObject
{
	public void set(String msg, int x, int y, Color c)
	{
		this.msg = msg;
		this.x = x;
		this.y = y;
		color.set(c);
	}

	public int paint(GameCommonCanvas g)
	{
		if ( (color.r > 0) && 40 <= y && y <= 220 )
		{
			g.setColor(color);
			g.drawString(msg, x, y);
		}
		return y;
	}
}