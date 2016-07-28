/*
 * Last modified: 2010/03/03 17:44:57
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;

public abstract class EnemyData extends CharacterData
{
	public static final int DESTROY_NORMAL = 1;
	public static final int DESTROY_SCORE_ITEM = 0;
	
	public static final int SIZE_NORMAL = 0;
	public static final int SIZE_MEDIUM = 1;
	public static final int SIZE_LARGE = 2;

	protected int destroyScore;
	protected int maxHp;
	protected int hp;
	protected int moveId;
	protected int shotId;
	protected boolean collisionFlag = true;
	protected int destroyPattern = DESTROY_NORMAL;
	protected int interval;
	protected int shotAngle = 270;
	protected int shotAngleRange = 0;
	protected int shotAngleRange2 = 0;
	protected int shotCount = 1;
	protected int shotSpeed = 1;
	protected int shotImageId = 0;
	protected int size = SIZE_NORMAL;
	protected boolean reverse = false;
	protected boolean random = false;
	protected boolean targetAngle = false;
	protected boolean halfTargetAngle = false;
	protected int shotTime = 0;
	protected int shotWaitTime = 0;
	protected int shotTimeCounter = 0;
	protected int shotAngleRange3 = 0;
	protected boolean bomb = false;
	protected int largeCount = 0;
	private int lastImageCounter = 0;
	public void haveBomb()
	{
		bomb = true;
	}
	
	public void setDestroyScore(int destroyScore)
	{
		this.destroyScore = destroyScore;
	}

	public void setInterval(int interval)
	{
		this.interval = interval;
	}
	
	public void setShotTime(int shotTime)
	{
		this.shotTime = shotTime;
		updateShotTimeCounter();
	}

	private void updateShotTimeCounter()
	{
		shotTimeCounter = shotTime + counter;
	}

	public void setShotWaitTime(int shotWaitTime)
	{
		this.shotWaitTime = shotWaitTime;
	}

	public int getInterval()
	{
		return interval;
	}

	public void setShotAngle(int angle)
	{
		while ( (angle < 0) || (angle >= 360) )
		{
			if ( angle >= 360 )
			{
				angle -= 360;
			}
			else if ( angle < 0 )
			{
				angle += 360;
			}
		}
		this.shotAngle = angle;
	}
	
	public void setShotAngleRange(int shotAngleRange)
	{
		this.shotAngleRange = shotAngleRange;
	}
	
	public void setShotAngleRange3(int shotAngleRange3)
	{
		this.shotAngleRange3 = shotAngleRange3;
	}

	public void setShotAngleRange2(int shotAngleRange2)
	{
		this.shotAngleRange2 = shotAngleRange2;
	}
	
	public void setShotCount(int shotCount)
	{
		this.shotCount = shotCount;
	}
	
	public void setShotImageId(int shotImageId)
	{
		this.shotImageId = shotImageId;
	}
	
	public void setShotSpeed(int shotSpeed)
	{
		this.shotSpeed = shotSpeed;
	}
	
	public void addShotSpeed(int i, int f)
	{
		this.shotSpeed += FixedPoint.toFixedInt(i) + (f * 25);
	}
	
	public int getShotAngleRange()
	{
		return shotAngleRange;
	}

	public void setHp(int hp)
	{
		this.hp = hp;
		maxHp = hp;
	}

	public void minusHp(int minus)
	{
		this.hp -= minus;
	}

	public int getHp()
	{
		return hp;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getMaxHp()
	{
		return maxHp;
	}

	public void setMoveId(int moveId)
	{
		this.moveId = moveId;
	}

	public void setShotId(int shotId)
	{
		this.shotId = shotId;
	}
	
	public boolean isGround()
	{
		return !collisionFlag;
	}
	
	public void onRandom()
	{
		this.random = true;
	}

	public void offRandom()
	{
		this.random = false;
	}

	public void onGround()
	{
		collisionFlag = false;
	}

	public void copy(EnemyData parent)
	{
		super.copy(parent);
		setHp(parent.hp);
		this.size = parent.size;
		this.moveId = parent.moveId;
		this.shotId = parent.shotId;
		this.destroyScore = parent.destroyScore;
		this.collisionFlag = parent.collisionFlag;
		this.destroyPattern = parent.destroyPattern;
		this.interval = parent.interval;
		this.shotAngle = parent.shotAngle;
		this.shotAngleRange = parent.shotAngleRange;
		this.shotAngleRange2 = parent.shotAngleRange2;
		this.shotCount = parent.shotCount;
		this.shotSpeed = parent.shotSpeed;
		this.shotImageId = parent.shotImageId;
		this.reverse = parent.reverse;
		this.random = parent.random;
		this.targetAngle = parent.targetAngle;
		this.halfTargetAngle = parent.halfTargetAngle;
		this.shotWaitTime = parent.shotWaitTime;
		shotTime = parent.shotTime;
		this.shotAngleRange3 = parent.shotAngleRange3;
		updateShotTimeCounter();
		bomb = false;
		largeCount = 0;
		lastImageCounter = 0;
	}
	
	public void onHalfTargetAngle()
	{
		onTargetAngle();
		halfTargetAngle = true;
	}
	
	public void offHalfTargetAngle()
	{
		halfTargetAngle = false;
	}
	
	public void onTargetAngle()
	{
		targetAngle = true;
	}
	public void offTargetAngle()
	{
		targetAngle = false;
	}
	
	public void setDestroyPattern(int destroyPattern)
	{
		this.destroyPattern = destroyPattern;
	}
	
	public int getDestroyPattern()
	{
		return destroyPattern;
	}

	protected void shotAllDirection(int fixX, int fixY, int angle, int speed, int count, int id)
	{
		int range = 360 / count;
		shotNWay(fixX, fixY, angle, speed, count, range, id, false);
	}

	protected void shotNWay(int fixX, int fixY, int angle, int speed, int count, int range, int id)
	{
		shotNWay(fixX, fixY, angle, speed, count, range, id, true);
	}

	protected void shotNWay(int fixX, int fixY, int angle, int speed, int count, int range, int id, boolean hosei)
	{
		while ( (angle < 0) || (angle >= 360) )
		{
			if ( angle >= 360 )
			{
				angle -= 360;
			}
			else if ( angle < 0 )
			{
				angle += 360;
			}
		}

		if ( count == 1 )
		{
			BulletData bd = ObjectPool.getBullet(id);
			setBullet(bd, speed, angle, fixX, fixY);
		}
		else
		{
			int start;
			if ( hosei && count % 2 == 0 )
			{
				// ‹ô”’e‘Î‰ž
				start = angle - count / 2 * range + (range / 2);
			}
			else
			{
				start = angle - (count - 1) / 2 * range;
			}
			
			for ( int i = 0; i < count; i++ )
			{
				BulletData bd = ObjectPool.getBullet(id);
				setBullet(bd, speed, start + (i * range), fixX, fixY);
			}
		}
	}
	
	private void setBullet(BulletData bd, int speed, int angle, int fixX, int fixY)
	{
		if ( bd != null )
		{
			if ( MainCanvas.getInstance().my.isHyper() )
			{
				bd.changeScoreItem(MainCanvas.getInstance(), (MainCanvas.getInstance().my.isMaxScoreCounter()) ? BulletData.RANK_LARGE : BulletData.RANK_MIDDLE, false);
				bd.offUpdateAngle();
			}
			bd.setFixSpeed(speed);
			bd.setFixPosition(fixX, fixY);
			bd.setAngle(angle);
		}
	}

	public int getAppearY()
	{
		return MainCanvas.PLAY_SCREEN_Y - image.getRectangle(0).height + 1;
	}
	
	public int getAppearLeft()
	{
		return MainCanvas.PLAY_SCREEN_X - image.getRectangle(0).width + 1;
	}
	
	public int getAppearRight()
	{
		return MainCanvas.PLAY_SCREEN_X + MainCanvas.PLAY_SCREEN_WIDTH - 1;
	}

	public int getAppearCenter()
	{
		return MainCanvas.PLAY_SCREEN_X + ((MainCanvas.PLAY_SCREEN_WIDTH - image.getRectangle(0).width) / 2);
	}

	public int getAppearMy()
	{
		return FixedPoint.toInt(MainCanvas.getInstance().my.getCenterFixX()) - ((image.getRectangle(0).width) / 2);
	}
	
	public void checkHp(MainCanvas g)
	{
		if ( hp <= 0 )
		{
			g.addScore(destroyScore);

			BurstData bd = Dictionary.getBurst(0);
			int sx = (fixWidth - bd.fixWidth) / 2;
			int sy = (fixHeight - bd.fixHeight) / 2;
			ObjectPool.setBurst(0, fixX + sx, fixY + sy);
			if ( size == SIZE_LARGE )
			{
				sx = (fixWidth - bd.fixWidth);
				sy = (fixHeight - bd.fixHeight);
				ObjectPool.setBurst(6, fixX + sx, fixY + sy);

				sx = 0;
				sy = 0;
				ObjectPool.setBurst(4, fixX + sx, fixY + sy);
				sx = 14240;
				sy = 12480;
				ObjectPool.setBurst(4, fixX + sx, fixY + sy);
				sx = 20480;
				sy = 3960;
				ObjectPool.setBurst(4, fixX + sx, fixY + sy);
				sx = 2048;
				sy = 15240;
				ObjectPool.setBurst(5, fixX + sx, fixY + sy);


				sx = 18960;
				sy = 15480;
				ObjectPool.setBurst(5, fixX + sx, fixY + sy);

				sx = 7480;
				sy = 21240;
				ObjectPool.setBurst(4, fixX + sx, fixY + sy);
				
				g.vibrate(1500);
			}
			else if ( size == SIZE_MEDIUM )
			{
				sx = (fixWidth - bd.fixWidth);
				sy = 0;
				ObjectPool.setBurst(6, fixX + sx, fixY + sy);
				sx = -2048;
				sy = -3480;
				ObjectPool.setBurst(4, fixX + sx, fixY + sy);
				g.vibrate(500);
			}
			else
			{
				g.vibrate(100);
			}
			
			if ( bomb )
			{
				ObjectPool.getBombItem(this);
			}
			destroy();
			
			if ( destroyPattern == DESTROY_SCORE_ITEM )
			{
				g.needAllScoreItem((size == SIZE_LARGE) || g.my.isMaxScoreCounter());
			}
		}
	}
	
	public void hitMyBullet(MainCanvas g, MyBulletData bd)
	{
		lastImageCounter++;
		if ( lastImageCounter <= 2 )
		{
			setLastImagePattern();
		}
		else if ( lastImageCounter > 2 )
		{
			lastImageCounter = 0;
		}
		
		if ( size == SIZE_LARGE )
		{
			largeCount++;
			if ( largeCount > 5 )
			{
				g.my.addHyperGauge(1);
				largeCount = 0;
			}
		}

		/*
		if ( g.my.isFastSpeed() )
		{
			BulletData b = ObjectPool.getBullet(0);
			if ( b != null )
			{
				b.changeScoreItem(MainCanvas.getInstance(), (g.my.isMaxScoreCounter()) ? BulletData.RANK_LARGE : BulletData.RANK_SMALL, false);
				b.setSpeed(BulletData.ITEM_MAX_SPEED);
				b.setFixPosition(bd.getFixX(), bd.getFixY());
			}			
		}
		*/
		hp -= bd.getDamage();
		checkHp(g);
	}

	public void move(MainCanvas g)
	{
		// rect
		int myX = g.my.getCenterFixX();
		int myY = g.my.getCenterFixY();
		if ( counter % 2 == 0 )
		{
			roleImagePattern(image.size() - 1);
		}

		checkScreenRange();
		
		ObjectPool.checkHitMyBullets(this, g);
		
		if ( alive && collisionFlag && g.my.isAlive() && isHit(g.my) )
		{
			g.my.hit(g);
		}
		
		move(myX, myY);

		counter++;
		if ( counter == Integer.MAX_VALUE )
		{
			counter = 0;
		}
	}


	public boolean shotCheck(int myX, int myY)
	{
		if ( (shotTime < 0) || (counter < shotTimeCounter) )
		{
			if ( counter % interval == 0 )
			{
				shot(myX, myY);
				if ( halfTargetAngle && targetAngle )
				{
					offTargetAngle();
				}
				return true;
			}
		}
		else if ( counter == (shotTimeCounter + shotWaitTime) )
		{
			updateShotTimeCounter();
			if ( halfTargetAngle )
			{
				onTargetAngle();
			}
		}
		
		return false;
	}

	protected int updateShotAngle(int myX, int myY)
	{
		return updateShotAngle(myX, myY, getCenterFixX(), getCenterFixY());
	}
	
	protected int updateShotAngle(int myX, int myY, int fX, int fY)
	{
		int x = myX - fX;
		int y = myY - fY;
		if ( x != 0 || y != 0 )
		{
			shotAngle = FixedPoint.getAngle(x,y);
			if ( random )
			{
				shotAngle += (FixRandom.next() - 5);
			}
		}
		else
		{
			shotAngle = -1;
		}
		return shotAngle;
	}

	
	protected void setBulletData(int shotId,
								 int shotImageId,
								 int shotSpeed,
								 int shotCount,
								 int shotAngleRange,
								 int shotAngleRange2,
								 int shotAngle)
	{
		if ( shotId >= 0 )
		{
			setShotId(shotId);
		}
		if ( shotImageId >= 0 )
		{
			setShotImageId(shotImageId);
		}
		if ( shotSpeed >= 0 )
		{
			setShotSpeed(FixedPoint.toFixedInt(shotSpeed));
		}
		if ( shotCount >= 0 )
		{
			setShotCount(shotCount);
		}
		if ( shotAngleRange >= 0 )
		{
			setShotAngleRange(shotAngleRange);
		}
		if ( shotAngleRange2 >= 0 )
		{
			setShotAngleRange2(shotAngleRange2);
		}
		if ( shotAngle >= 0 )
		{
			setShotAngle(shotAngle);
		}
	}
	
	public abstract void move(int myX, int myY);
	public abstract void shot(int myX, int myY);
}
