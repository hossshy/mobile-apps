/*
 * Last modified: 2009/12/27 15:16:20
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;

public class ScheduleData
{
	public static final int APPEAR_Y = -1;
	public static final int APPEAR_LEFT = -2;
	public static final int APPEAR_RIGHT = -3;
	public static final int APPEAR_CENTER = -4;
	public static final int APPEAR_MY = -5;

	public static final int TYPE_BOSS = 1;
	public static final int TYPE_WARNING = 2;
	public static final int TYPE_BG_SPEED = 3;
	public static final int TYPE_BG_IMAGE = 4;
	public static final int TYPE_BOMB = 5;
	
	private int scheduleCount;
	private int id;
	private int x;
	private int y;
	private int type = 0;
	
	public ScheduleData(String code)
	{
		String[] tmp = GameUtil.split(code, ',');

		scheduleCount = Integer.parseInt(tmp[0]);
		id = Integer.parseInt(tmp[1]);
		x = convAppear(tmp[2]);
		y = convAppear(tmp[3]);
		if ( tmp.length > 4 )
		{
			if ( "boss".equals(tmp[4]) )
			{
				type = TYPE_BOSS;
			}
			else if ( "warning".equals(tmp[4]) )
			{
				type = TYPE_WARNING;
			}
			else if ( "bgSpeed".equals(tmp[4]) )
			{
				type = TYPE_BG_SPEED;
			}
			else if ( "bgImage".equals(tmp[4]) )
			{
				type = TYPE_BG_IMAGE;
			}
			else if ( "bomb".equals(tmp[4]) )
			{
				type = TYPE_BOMB;
			}
		}
	}
	
	public int getScheduleCount()
	{
		return scheduleCount;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getX(EnemyData ed)
	{
		return getAppear(ed, x);
	}
	
	public int getY(EnemyData ed)
	{
		return getAppear(ed, y);
	}
	
	public boolean isBgSpeed()
	{
		return type == TYPE_BG_SPEED;
	}
	
	public boolean isBoss()
	{
		return type == TYPE_BOSS;
	}
	
	public boolean isWarning()
	{
		return type == TYPE_WARNING;
	}
	
	public boolean isBgImage()
	{
		return type == TYPE_BG_IMAGE;
	}
	
	public boolean isBomb()
	{
		return type == TYPE_BOMB;
	}
	
	public static int getAppear(EnemyData ed, int a)
	{
		if ( a == APPEAR_Y )
		{
			return ed.getAppearY();
		}
		else if ( a == APPEAR_LEFT )
		{
			return ed.getAppearLeft();
		}
		else if ( a == APPEAR_RIGHT )
		{
			return ed.getAppearRight();
		}
		else if ( a == APPEAR_CENTER )
		{
			return ed.getAppearCenter();
		}
		else if ( a == APPEAR_MY )
		{
			return ed.getAppearMy();
		}
		else
		{
			return a;
		}
	}
	
	private int convAppear(String a)
	{
		if ( "up".equals(a) )
		{
			return APPEAR_Y;
		}
		else if ( "left".equals(a) )
		{
			return APPEAR_LEFT;
		}
		else if ( "right".equals(a) )
		{
			return APPEAR_RIGHT;
		}
		else if ( "center".equals(a) )
		{
			return APPEAR_CENTER;
		}
		else if ( "my".equals(a) )
		{
			return APPEAR_MY;
		}
		else
		{
			return Integer.parseInt(a);
		}
	}
}
