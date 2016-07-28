/*
 * Last modified: 2008/07/15 00:17:53
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public abstract class EnemyData extends CharacterData
{
	StringBuffer allows = null;
	static final int STATE_ALIVE = 0;
	static final int STATE_BRAIN = 1;
	static final int STATE_DIE = 2;
	static final int STATE_WAIT = 3;
	
	int state = STATE_ALIVE;
	int tmpx, tmpy;
	int minusPower = 0;
	int counter = 0;
	boolean isHall = true;

	public int getImagePattern()
	{
		if ( state == STATE_DIE )
		{
			if ( isHall )
			{
				switch ( counter )
				{
				case 0:
					counter++;
					return 50;
				case 1:
					counter++;
					return 51;
				case 2:
					counter++;
					return 52;
				default:
					return -1;
				}
			}
			else
			{
				switch ( counter )
				{
				case 0:
					counter++;
					return 40;
				case 1:
					counter++;
					return 41;
				case 2:
					counter++;
					return 42;
				default:
					return 43;
				}
			}
		}
		else
		{
			return super.getImagePattern();
		}
	}

	public EnemyData()
	{
	}

	public EnemyData(int x, int y)
	{
		super(x, y);
	}

	public EnemyData(EnemyData ed)
	{
		super(ed);
		this.minusPower = ed.minusPower;
	}
	
	public void die(boolean isHall)
	{
		state = STATE_DIE;
		moved = false;
		hosuu = 0;
		allows = null;
		this.isHall = isHall;
	}
	
	public void move(int[][] map, MapChip[] chips)
	{
		if ( !moved )
		{
			if ( (allows != null) && (allows.length() > 0 ) )
			{
				movingDir = allows.charAt(0);
				allows.deleteCharAt(0);
				tmpx = x;
				tmpy = y;
				switch ( movingDir )
				{
				case 'L':
					tmpx--;
					break;
				case 'R':
					tmpx++;
					break;
				case 'U':
					tmpy--;
					break;
				case 'D':
					tmpy++;
					break;
				}

				if ( GameUtil.isMoveMap(map, chips, tmpx, tmpy) )
				{
					moved = true;
				}
				else
				{
					state = STATE_BRAIN;
					allows = null;
				}
			}
			else if ( state == STATE_ALIVE )
			{
				state = STATE_BRAIN;
			}
		}

		if ( moved )
		{
			hosuu += speed;
			if ( hosuu >= chipWidth )
			{
				x = tmpx;
				y = tmpy;
				moved = false;
				hosuu = 0;
			}
			else if ( map[tmpy][tmpx] == 5 )
			{
				moved = false;
				hosuu = 0;
				allows = null;
			}
		}
	}
	
	public void searchRoad(int[][] map, MapChip[] mapChips, Point end)
	{
		ASter aster = ASter.getInstance();
		
		Node node = aster.search(map, mapChips, this, end);
		if ( node == null )
		{
			die(false);
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			aster.buildAllows(sb, node);
			allows = sb;
			state = EnemyData.STATE_ALIVE;
		}
	}

	public void actionMagicd()
	{
	}
	
	public abstract EnemyData deepCopy();
}
