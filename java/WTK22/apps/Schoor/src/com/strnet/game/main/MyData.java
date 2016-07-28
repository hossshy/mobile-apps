/*
 * Last modified: 2008/08/31 00:37:35
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.MapChip;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.SpriteData;
import com.strnet.game.common.Point;

public class MyData extends SpriteData
{
	public static final int STATE_STAND = 0;
	public static final int STATE_WALK = 1;
	public static final int STATE_RUN = 2;
	public static final boolean RIGHT = true;
	public static final boolean LEFT = false;
	
	public boolean run = false;
	public boolean direction = LEFT;
	
	private int state;
	private int frame = 0;
	
	public MyData()	{}

	public MyData(int x, int y)
	{
		super(x, y);
	}

	public MyData(MyData ed)
	{
		super(ed);
	}
	
	public void setState(int state)
	{
		this.state = state;
	}
	
	public int getImagePattern()
	{
		switch ( state )
		{
		case STATE_STAND:
			imagePattern = 0;
			break;
		case STATE_WALK:
			frame++;
			if ( frame >= 3 )
			{
				imagePattern = GameUtil.loopIncf(imagePattern, 9, 16);
				frame = 0;
			}
			break;
		case STATE_RUN:
			frame++;
			if ( frame >= 2 )
			{
				imagePattern = GameUtil.loopIncf(imagePattern, 1, 8);
				frame = 0;
			}
			break;
		}
		
		return imagePattern;
	}
	
	public void move(int[][] map, MapChip[] chips, AbstractCanvas g)
	{
		if ( g.isUp() )
		{
		}
		else if ( g.isDown() )
		{
		}
		else if ( g.isLeft() )
		{
			direction = LEFT;
			state = (run) ? STATE_RUN : STATE_WALK;
		}
		else if ( g.isRight() )
		{
			direction = RIGHT;
			state = (run) ? STATE_RUN : STATE_WALK;
		}
		else
		{
			state = STATE_STAND;
		}
	}
}
