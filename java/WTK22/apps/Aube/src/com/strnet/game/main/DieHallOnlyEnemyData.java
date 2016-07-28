/*
 * Last modified: 2008/07/05 21:58:01
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class DieHallOnlyEnemyData extends EnemyData
{
	public DieHallOnlyEnemyData()
	{
	}

	public DieHallOnlyEnemyData(int x, int y)
	{
		super(x, y);
	}

	public DieHallOnlyEnemyData(DieHallOnlyEnemyData ed)
	{
		super(ed);
	}
	
	public EnemyData deepCopy()
	{
		return new DieHallOnlyEnemyData(this);
	}

	/*
	public void actionMagicd()
	{
		if ( state != STATE_DIE )
		{
			state = STATE_BRAIN;
		}
		}*/
	
	public void die(boolean isHall)
	{
		if ( isHall )
		{
			super.die(isHall);
		}
		else
		{
			state = STATE_WAIT;
		}
	}
}
