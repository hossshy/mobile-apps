/*
 * Last modified: 2010/03/25 12:57:27
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImageData;
import com.strnet.game.main.GameCommonCanvas;

public class StateTitleImage extends StateObject
{
	public void set(String msg, int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
	}

	public int paint(GameCommonCanvas g)
	{
		int tmpY = y;
		if ( 40 <= y && y < 240 )
		{
			if ( y < 100 )
			{
				tmpY = 100;
			}
			g.drawTitle(x, tmpY);
		}
		return y;
	}
}