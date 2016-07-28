/*
 * Last modified: 2010/03/23 14:19:24
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImagePatternData;

public class Laser2MyData extends MyData
{
	private boolean laserImage = false;

	public Laser2MyData()
	{
		fastSpeed = 7;
		slowSpeed = 4;
		setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(171,129,23,31),
														 new Rectangle(194,129,23,31),
														 new Rectangle(217,129,23,31),
														 new Rectangle(171,160,23,31),
														 new Rectangle(194,160,23,31),
														 new Rectangle(217,160,23,31)}));
		setCollision(1, 1);
	}

	public String getName()
	{
		return "L2D-RP";
	}

	public String getFastWeaponName()
	{
		return "前方範囲3Way";
	}

	public String getSlowWeaponName()
	{
		return "レーザー";
	}

	public String getDescription()
	{
		return "ビレーゼ・ノマック";
	}


	public void shot()
	{
		counter--;
		if ( counter <= 0 )
		{
			if ( speedFlag )
			{
				int addX = 5584;
				//int addX = 4584;
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
			else
			{
				MyBulletData bd = ObjectPool.getMyBullet((isHyper()) ? 18 : 17);
				counter = 1;
				if ( bd != null )
				{
					bd.setSpeed(38);
					bd.setFixPosition(getCenterFixX(), getCenterFixY() - 5096);
					bd.setAngle(90);
					laserImage = !laserImage;
					if ( laserImage )
					{
						bd.roleImagePattern();
					}
				}
			}
		}
	}
}
