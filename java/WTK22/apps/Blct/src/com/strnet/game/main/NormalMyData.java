/*
 * Last modified: 2010/03/22 12:15:27
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImagePatternData;

public class NormalMyData extends MyData
{
	public NormalMyData()
	{
		fastSpeed = 7;
		slowSpeed = 4;
		setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(0,0,23,31),
														 new Rectangle(24,0,23,31),
														 new Rectangle(48,0,23,31),
														 new Rectangle(72,0,23,31),
														 new Rectangle(96,0,23,31),
														 new Rectangle(120,0,23,31)}));
		setCollision(1, 1);
	}

	public String getName()
	{
		return "TC-R";
	}

	public String getFastWeaponName()
	{
		return "前方3Way";
	}

	public String getSlowWeaponName()
	{
		return "前方密集3Way";
	}
	
	public String getDescription()
	{
		return "ゲイリー・ラウル";
	}

	public void shot()
	{
		counter--;
		if ( counter <= 0 )
		{
			int addX = (speedFlag) ? 3584 : 2000;
			counter = 3;
			if ( ObjectPool.getEmptyObjects(ObjectPool.myBullets) >= 3 )
			{
				for ( int i = -1; i < 2; i++ )
				{
					MyBulletData bd = ObjectPool.getMyBullet((isHyper()) ? 8 : 1);
					if ( bd != null )
					{
						bd.setSpeed(16);
						bd.setFixPosition(getCenterFixX() + (i * addX), getCenterFixY() - ((i == 0) ? 2048:0));
						bd.setAngle(90);
					}
				}
			}
		}
	}
}
