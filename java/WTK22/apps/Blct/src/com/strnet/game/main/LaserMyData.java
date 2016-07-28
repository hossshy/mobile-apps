/*
 * Last modified: 2010/03/23 14:20:01
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImagePatternData;

public class LaserMyData extends MyData
{
	private boolean laserImage = false;

	public LaserMyData()
	{
		fastSpeed = 6;
		slowSpeed = 3;
		setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(0,32,27,29),
														 new Rectangle(27,32,27,29),
														 new Rectangle(54,32,27,29),
														 new Rectangle(81,32,27,29),
														 new Rectangle(108,32,27,29),
														 new Rectangle(135,32,27,29)}));
		setCollision(1, 1);
	}

	public String getName()
	{
		return "GT-H";
	}

	public String getFastWeaponName()
	{
		return "拡散3Way";
	}

	public String getSlowWeaponName()
	{
		return "レーザー";
	}
	
	public String getDescription()
	{
		return "レイリック・ラチカ";
	}
	public void shot()
	{
		counter--;
		if ( counter <= 0 )
		{
			if ( speedFlag )
			{
				int addX = 3584;
				counter = 4;
				
				if ( ObjectPool.getEmptyObjects(ObjectPool.myBullets) >= 3 )
				{
					MyBulletData bd = ObjectPool.getMyBullet((isHyper()) ? 21 : 19);
					if ( bd != null )
					{
						bd.setSpeed(16);
						bd.setFixPosition(getCenterFixX() + (-1 * addX), getCenterFixY() - 2048);
						bd.setAngle(100);
					}
					bd = ObjectPool.getMyBullet((isHyper()) ? 24 : 23);
					if ( bd != null )
					{
						bd.setSpeed(16);
						bd.setFixPosition(getCenterFixX(), getCenterFixY() - 4096);
						bd.setAngle(90);
					}
					bd = ObjectPool.getMyBullet((isHyper()) ? 22 : 20);
					if ( bd != null )
					{
						bd.setSpeed(16);
						bd.setFixPosition(getCenterFixX() + addX, getCenterFixY() - 2048);
						bd.setAngle(80);
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
