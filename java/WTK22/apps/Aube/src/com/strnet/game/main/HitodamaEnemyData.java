/*
 * Last modified: 2008/07/05 21:58:11
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class HitodamaEnemyData extends EnemyData
{
	public HitodamaEnemyData()
	{
	}

	public HitodamaEnemyData(int x, int y)
	{
		super(x, y);
	}

	public HitodamaEnemyData(HitodamaEnemyData ed)
	{
		super(ed);
	}
	
	public EnemyData deepCopy()
	{
		return new HitodamaEnemyData(this);
	}
}
