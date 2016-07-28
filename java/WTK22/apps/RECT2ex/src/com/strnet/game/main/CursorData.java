/*
 * Last modified: 2009/05/21 15:22:29
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;

public class CursorData extends CharacterData
{
	static final int[] DEF_CUR_SPEED = new int[]{2, 2, 3, 4, 5, 7, 9, 12};

	public CursorData(int x, int y, int width, int height, int imageId, Rectangle imgSrc)
	{
		super(x, y, width, height, imageId, imgSrc);
	}


	public boolean move(MainCanvas g)
	{
		boolean ret = false;
		if ( g.isUp() || g.isDown() || g.isLeft() || g.isRight() )
		{
			if ( speed < DEF_CUR_SPEED.length - 1 )
			{
				speed++;
			}
			ret = true;
		}
		else
		{
			speed = 0;
		}
		if ( g.isUp() ) y -= DEF_CUR_SPEED[speed];
		if ( g.isDown() ) y += DEF_CUR_SPEED[speed];
		if ( g.isLeft() ) x -= DEF_CUR_SPEED[speed];
		if ( g.isRight() ) x += DEF_CUR_SPEED[speed];

		if ( x < 0 ) x=0;
		else if ( x > 240 - width ) x= 240 - width;
		if ( y < 0 ) y=0;
		else if ( y > 240 - height ) y=240 - height;
		return ret;
	}
}