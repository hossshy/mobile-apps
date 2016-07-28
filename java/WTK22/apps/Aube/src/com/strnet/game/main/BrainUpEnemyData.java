/*
 * Last modified: 2008/07/05 21:57:48
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class BrainUpEnemyData extends EnemyData
{
	boolean nextBrain = false;
	
	public BrainUpEnemyData()
	{
	}

	public BrainUpEnemyData(int x, int y)
	{
		super(x, y);
	}

	public BrainUpEnemyData(BrainUpEnemyData ed)
	{
		super(ed);
	}
	
	public EnemyData deepCopy()
	{
		return new BrainUpEnemyData(this);
	}

	public void actionMagicd()
	{
		if ( state != STATE_DIE )
		{
			nextBrain = true;
		}
	}

	
	public void move(int[][] map, MapChip[] chips)
	{
		if ( !moved )
		{
			if ( nextBrain )
			{
				nextBrain = false;
				state = STATE_BRAIN;
			}
			else
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
						state = STATE_WAIT;
						allows = null;
					}
				}
				else if ( state == STATE_ALIVE )
				{
					state = STATE_BRAIN;
				}
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
}
