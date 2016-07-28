/*
 * Last modified: 2008/07/06 23:11:57
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Counter;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class Magic extends CharacterData
{
	int counter = 0;
	int usePower = 25;
	int backupChipId = 0;

	public Magic()
	{
	}

	public Magic(Magic m)
	{
		super(m);
		this.usePower = m.usePower;
	}
	
	public int getImagePattern()
	{
		int ret = imageFrame;
		if ( imageFrame >= 3 )
		{
			if ( (imageSpeed <= 0) || Counter.count % imageSpeed == 0 )
			{
				imageFrame++;
				if ( imageFrame >= maxImageFrame )
				{
					imageFrame = 3;
				}
			}
		}
		else
		{
			imageFrame++;
		}
		
		return 10 + ret;
	}


	public boolean action(CharacterData cd, int[][] map, MapChip[] chips, Point[] enemys, Point aube, Point hall)
	{
		if ( cd.power < usePower )
		{
			return false;
		}

		x = cd.x;
		y = cd.y;
		switch ( cd.movingDir )
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

		if ( GameUtil.isMoveMap(map, chips, x, y) &&
			 !GameUtil.isHitChip(this, enemys) &&
			 !GameUtil.equalPoint(this, aube) &&
			 !GameUtil.equalPoint(this, hall) )
		{
			cd.power -= usePower;
			backupChipId = map[y][x];
			map[y][x] = 5;
			return true;
		}
		return false;
	}
	
	public boolean doAction(int[][] map)
	{
		counter++;
		if ( counter > 30 )
		{
			//map[y][x] = backupChipId;
			map[y][x] = 0;
			return false;
		}
		return true;
	}
}
