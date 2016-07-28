/*
 * Last modified: 2009/12/27 15:30:53
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class BombItemData extends BulletData
{
	public void move(MainCanvas g)
	{
		if ( alive )
		{
			fixX += vx;
			fixY += vy;
			checkScreenRange();
			counter++;
			
			if ( alive && g.my.isAlive() && g.my.isHitImage(this) )
			{
				g.my.addBomb();
				destroy();
			}
			else if ( counter % 2 == 0 )
			{
				roleImagePattern(image.size());
			}
		}
	}

	public void copy(BombItemData parent)
	{
		super.copy(parent);
	}
	
	public boolean duplicatePosition(BulletData bd)
	{
		return false;
	}

	public void mergeScoreItem(BulletData bd)
	{
	}
	
	private void updateScore()
	{
	}

	public void changeScoreItem(MainCanvas g, int rank, boolean force)
	{
	}

	protected int updateShotAngle(int myX, int myY)
	{
		return 269;
	}
}
