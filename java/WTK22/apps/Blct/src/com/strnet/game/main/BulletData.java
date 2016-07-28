/*
 * Last modified: 2010/03/02 12:50:41
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class BulletData extends MyBulletData
{
	public static final int ITEM_SPEED = 3;
	public static final int ITEM_MAX_SPEED = 9;
	public static final int RANK_SMALL = 0;
	public static final int RANK_MIDDLE = 1;
	public static final int RANK_LARGE = 2;
	private static final int[] SCORE_COUNTER = new int[]{1,20,50};
	private static final int[] HYPER_COUNTER = new int[]{1,1,1};

	private int scoreLevel;
	private int mergeScore;
	private int score;
	private boolean updateAngle = true;
	
	public void move(MainCanvas g)
	{
		if ( alive )
		{
			if ( isScoreItem() )
			{
				fixX += vx;
				fixY += vy;
				if ( updateAngle )
				{
					if ( counter % 6 == 0 )
					{
						setAngle(updateShotAngle(g.my.getCenterFixX(), g.my.getCenterFixY()));
					}
				}
				else
				{
					checkScreenRange();
				}
				counter++;

				if ( alive && g.my.isAlive() && g.my.isHitImage(this) )
				{
					int addScore = 0;
					switch ( scoreLevel )
					{
					case RANK_SMALL:
						addScore = ((g.my.getScoreCounter() / 5) * mergeScore);
						break;
					case RANK_MIDDLE:
						addScore = ((g.my.getScoreCounter() / 2) * mergeScore);
						break;
					case RANK_LARGE:
						addScore = (g.my.getScoreCounter() * mergeScore);
						break;
					}
					g.my.addScoreCounter(SCORE_COUNTER[scoreLevel] * mergeScore, mergeScore);
					g.addScore(addScore);
					g.startScoreItemSE(mergeScore);
					destroy();
				}
			}
			else
			{
				super.move(g);
				if ( alive && g.my.isAlive() && isHit(g.my) )
				{
					g.my.hit(g);
				}
			}
		}
	}

	public void copy(BulletData parent)
	{
		super.copy(parent);
		scoreLevel = -1;
		mergeScore = 1;
		score = 10;
		updateAngle = true;
	}
	
	public void offUpdateAngle()
	{
		updateAngle = false;
	}
	
	public boolean isUpdateAngle()
	{
		return updateAngle;
	}
	
	public boolean isScoreItem()
	{
		return scoreLevel >= 0;
	}
	
	public int getScoreLevel()
	{
		return scoreLevel;
	}

	static final int range = FixedPoint.toFixedInt(4);
	public boolean duplicatePosition(BulletData bd)
	{
		return bd.isAlive() && bd.isScoreItem() &&
			((bd.getFixX() - range) < fixX && fixX < (bd.getFixX() + range)) &&
			((bd.getFixY() - range) < fixY && fixY < (bd.getFixY() + range));
/*
		int range = 4;
		int x = FixedPoint.toInt(fixX);
		int y = FixedPoint.toInt(fixY);
		int ox = FixedPoint.toInt(bd.getFixX());
		int oy = FixedPoint.toInt(bd.getFixY());
		return bd.isAlive() && bd.isScoreItem() &&
			((ox - range) < x && x < (ox + range)) &&
			((oy - range) < y && y < (oy + range));
*/
	}

	public void mergeScoreItem(BulletData bd)
	{
		//this.scoreLevel = Math.max(scoreLevel, bd.scoreLevel);
		mergeScore+= bd.mergeScore;
		bd.destroy();
	}
	
	private void updateScore()
	{
		//score *= 2;
		//mergeScore *= 2;
		//score = 500 * scoreLevel * mergeScore;
		//score += 500 * mergeScore;
	}

	public void changeScoreItem(MainCanvas g, int rank, boolean force)
	{
		if ( isScoreItem() )
		{
			if ( force )
			{
				scoreLevel = rank;
			}
			else if ( scoreLevel < RANK_LARGE )
			{
				scoreLevel++;
			}
			
			updateAngle = true;
		}
		else
		{
			image = Dictionary.getScoreItem();
			if ( force )
			{
				scoreLevel = RANK_LARGE;
			}
			else
			{
				scoreLevel = rank;
			}
			//setAngle(270);
		}
		
		if ( scoreLevel == RANK_LARGE )
		{
			setCollision(33,34);
			setSpeed(ITEM_MAX_SPEED);
		}
		else
		{
			setCollision(7,7);
			setSpeed(ITEM_SPEED);
		}
		imagePattern = scoreLevel;
	}

	protected int updateShotAngle(int myX, int myY)
	{
		int shotAngle;
		int x = myX - getCenterFixX();
		int y = myY - getCenterFixY();
		if ( x != 0 || y != 0 )
		{
			shotAngle = FixedPoint.getAngle(x,y);
		}
		else
		{
			shotAngle = -1;
		}
		return shotAngle;
	}
}
