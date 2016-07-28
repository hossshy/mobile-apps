/*
 * Last modified: 2010/03/28 20:13:11
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImageData;
import com.strnet.game.main.GameCommonCanvas;

public class StateImage extends StateObject
{
	private static Rectangle baseRect = new Rectangle(0,0,GameCommonCanvas.SCREEN_WIDTH,GameCommonCanvas.SCREEN_HEIGHT);
	private static Rectangle myRect = new Rectangle(0,40,GameCommonCanvas.SCREEN_WIDTH,200);

	private ImageData image;
	
	public void set(String msg, int x, int y, Color color)
	{
		this.msg = msg;
		String[] d = GameUtil.split(msg.substring(1), ':');
		String[] xy = GameUtil.split(d[0], ',');
		this.x = Integer.parseInt(xy[0]);
		this.y = Integer.parseInt(xy[1]);
		xy = GameUtil.split(d[1], ',');
		Rectangle r = new Rectangle(Integer.parseInt(xy[1]),
									Integer.parseInt(xy[2]),
									Integer.parseInt(xy[3]),
									Integer.parseInt(xy[4]));
		image = new ImageData(Integer.parseInt(xy[0]), r);
	}

	public int paint(GameCommonCanvas g)
	{
		if ( 0 <= y && y <= 220 )
		{
			g.setEnableDrawArea(myRect);
			g.drawImage(image, x, y);
			g.setEnableDrawArea(baseRect);
		}
		return y;
	}
}