/*
 * Last modified: 2008/07/05 21:58:47
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Counter;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class MyData extends CharacterData
{
	static final int STATE_NORMAL = 0;
	static final int STATE_CLEAR = 1;
	static final int STATE_GAMEOVER = 2;
	private int state;
	int canMove = 0;
	
	public MyData()
	{
	}

	public MyData(int x, int y)
	{
		super(x, y);
	}

	public MyData(ItemData ed)
	{
		super(ed);
	}
	
	public void setState(int state)
	{
		this.state = state;
	}
	
	public int getImagePattern()
	{
		if ( state == STATE_CLEAR )
		{
			return 41;
		}
		else if ( state == STATE_GAMEOVER )
		{
			return 40;
		}
		else
		{
			return super.getImagePattern();
		}
	}
	
	public void move(int[][] map, MapChip[] chips, AbstractCanvas g)
	{
		if ( !moved )
		{
			char tmpDir = 'N';
			if ( g.isUp() )
			{
				tmpDir = 'U';
			}
			else if ( g.isDown() )
			{
				tmpDir = 'D';
			}
			else if ( g.isLeft() )
			{
				tmpDir = 'L';
			}
			else if ( g.isRight() )
			{
				tmpDir = 'R';
			}

			if ( (tmpDir != 'N') && (movingDir != tmpDir) )
			{
				movingDir = tmpDir;
				canMove = 0;
			}
			else if ( canMove < 1 )
			{
				canMove++;
			}
			else
			{
				if ( g.isUp() && GameUtil.isMoveMap(map, chips, x, y - 1) )
				{
					movingDir = 'U';
					moved = true;
				}
				else if ( g.isDown() && GameUtil.isMoveMap(map, chips, x, y + 1) )
				{
					movingDir = 'D';
					moved = true;
				}
				else if ( g.isLeft() && GameUtil.isMoveMap(map, chips, x - 1, y) )
				{
					movingDir = 'L';
					moved = true;
				}
				else if ( g.isRight() && GameUtil.isMoveMap(map, chips, x + 1, y) )
				{
					movingDir = 'R';
					moved = true;
				}
			}
		}

		if ( moved )
		{
			hosuu += speed;
			if ( hosuu >= chipWidth )
			{
				switch ( movingDir )
				{
				case 'L':
					x--;
					break;
				case 'R':
					x++;
					break;
				case 'U':
					y--;
					break;
				case 'D':
					y++;
					break;
				}

				updateImageFrame();
				moved = false;
				hosuu = 0;
			}
		}
	}
}
