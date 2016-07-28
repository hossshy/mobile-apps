/*
 * Last modified: 2010/02/17 16:20:20
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;

public class ObjectPool
{
	public static final int MAX_ENEMYS = 10;
	public static final int MAX_BULLETS = 98;
	public static final int MAX_MY_BULLETS = 9;

	public static BulletData[] bullets = new BulletData[MAX_BULLETS];
	public static MyBulletData[] myBullets = new MyBulletData[MAX_MY_BULLETS];
	public static EnemyData[] enemies = new EnemyData[MAX_ENEMYS];
	public static BurstData[] bursts = new BurstData[MAX_ENEMYS + MAX_BULLETS + 1];
	public static BombItemData[] bombItemData = new BombItemData[1];
	
	public static void init()
	{
		for ( int i = 0; i < bullets.length; i++ )
		{
			bullets[i] = new BulletData();
		}
		for ( int i = 0; i < enemies.length; i++ )
		{
			enemies[i] = new EnemyDataImpl();
		}
		for ( int i = 0; i < myBullets.length; i++ )
		{
			myBullets[i] = new MyBulletData();
		}
		for ( int i = 0; i < bursts.length; i++ )
		{
			bursts[i] = new BurstData();
		}
		bombItemData[0] = new BombItemData();
	}
	
	
	public static EnemyData getEnemy(int id)
	{
		for ( int i = 0; i < enemies.length; i++ )
		{
			EnemyData e = enemies[i];
			if ( !e.isAlive() )
			{
				e.copy(Dictionary.getEnemy(id));
				e.onAlive();
				
				return e;
			}
		}
		
		return null;
	}
	
	public static BurstData getBurst(int id)
	{
		for ( int i = 0; i < bursts.length; i++ )
		{
			BurstData b = bursts[i];
			if ( !b.isAlive() )
			{
				b.copy(Dictionary.getBurst(id));
				b.onAlive();
				return b;
			}
		}
		return null;
	}
	
	public static void setBurst(int id, int fixX, int fixY)
	{
		BurstData bd = getBurst(id);
		if ( bd != null )
		{
			bd.setFixPosition(fixX, fixY);
		}
	}	
	
	public static BulletData getBullet(int id)
	{
		for ( int i = 0; i < bullets.length; i++ )
		{
			BulletData b = bullets[i];
			if ( !b.isAlive() )
			{
				b.copy(Dictionary.getBullet(id));
				b.onAlive();
				return b;
			}
		}
		return null;
	}
	
	public static BombItemData getBombItem(EnemyData ed)
	{
		if ( !bombItemData[0].isAlive() )
		{
			bombItemData[0].copy(Dictionary.getBombItem());
			bombItemData[0].onAlive();
			bombItemData[0].setFixPosition(ed.getCenterFixX(), ed.getCenterFixY());
			bombItemData[0].setFixSpeed(ed.getFixSpeed());
			bombItemData[0].setAngle(269);
			return bombItemData[0];
		}
		return null;
	}
	
	public static MyBulletData getMyBullet(int id)
	{
		for ( int i = (myBullets.length - 1); i >= 0; i-- )
		{
			MyBulletData b = myBullets[i];
			if ( !b.isAlive() )
			{
				b.copy(Dictionary.getBullet(id));
				b.onAlive();
				return b;
			}
		}
		return null;
	}

	public static int getEmptyObjects(CharacterData[] cds)
	{
		int count = 0;
		for ( int i = 0; i < cds.length; i++ )
		{
			if ( !cds[i].isAlive() )
			{
				count++;
			}
		}
		return count;
	}
	
	public static void checkHitMyBullets(EnemyData ed, MainCanvas g)
	{
		for ( int i = 0; i < myBullets.length; i++ )
		{
			if ( !ed.isAlive() )
			{
				break;
			}
			MyBulletData bd = myBullets[i];
			if ( bd.isAlive() && ed.isHitImage(bd) )
			{
				/*
				if ( g.my.isHyper() || g.my.isMaxScoreCounter() )
				{
					BulletData sbd = ObjectPool.getBullet(0);
					if ( sbd == null )
					{
						g.addScore(g.my.getScoreCounter());
					}
					else
					{
						sbd.changeScoreItem(g, BulletData.RANK_SMALL, false);
						sbd.setSpeed(8);
						sbd.setFixPosition(bd.getFixX(), bd.getFixY());
					}
				}
				*/
				ed.hitMyBullet(g, bd);
				bd.destroy();
			}
		}
	}
	
	public static void destroyAll(CharacterData[] cds)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			CharacterData cd = cds[i];
			if ( cd.isAlive() )
			{
				cd.destroy();
			}
		}		
	}
	
	public static void destroyAllBullets(BulletData[] cds)
	{
		destroyAllBullets(cds, true);
	}	
	public static void destroyAllBullets(BulletData[] cds, boolean targetScore)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			BulletData cd = cds[i];
			if ( cd.isAlive() )
			{
				if ( !targetScore && cd.isScoreItem() )
				{
				}
				else
				{
					cd.destroy();
					BurstData bd = ObjectPool.getBurst(2);
					if ( bd != null )
					{
						bd.setFixPosition(cd.getFixX(), cd.getFixY());
					}
				}
			}
		}
	}
	
	public static int scoreItemAll(BulletData[] cds, MainCanvas g, boolean force)
	{
		int rank;
		//if ( force || g.my.isMaxScoreCounter() )
		if ( force )
		{
			rank = BulletData.RANK_LARGE;
		}
		else if ( g.my.isFastSpeed()  )
		{
			rank = BulletData.RANK_MIDDLE;
		}
		else
		{
			rank = BulletData.RANK_SMALL;
		}
		
		/*
		//if ( g.my.isHyper() || g.my.isMaxHyperCounter() )
		if ( g.my.isHyper() || g.my.isMaxScoreCounter() )
		{
			rank = BulletData.RANK_LARGE;
		}
		*/
		
		int ret = 0;
		for ( int i = 0; i < cds.length; i++ )
		{
			BulletData cd = cds[i];
			if ( cd.isAlive() )
			{
				cd.changeScoreItem(g, rank, force);
				ret++;
			}
		}
		return ret;
	}

	private static int mergeCheckCounter = 0;
	public static int moveBulletAll(BulletData[] cds, MainCanvas g)
	{
		int ret = 0;
		mergeCheckCounter = GameUtil.loopIncf(mergeCheckCounter, 0, cds.length);
		boolean nocheck = true;
		for ( int i = 0; i < cds.length; i++ )
		{
			BulletData cd = cds[i];
			if ( cd.isAlive() )
			{
				if ( nocheck && cd.isScoreItem() && (cd.getScoreLevel() < BulletData.RANK_LARGE) )
				{
					mergeCheck(cds, cd);
					mergeCheckCounter = i;
					nocheck = false;
				}
				cd.move(g);
				ret++;
			}
		}
		return ret;
	}
	
	public static void mergeCheck(BulletData[] cds, BulletData target)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			BulletData cd = cds[i];
			if ( target.isUpdateAngle() && (target.getScoreLevel() == cd.getScoreLevel()) && target.duplicatePosition(cd) && (target != cd) )
			{
				target.mergeScoreItem(cd);
			}
		}		
	}
	
	public static void moveAll(CharacterData[] cds, MainCanvas g)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			CharacterData cd = cds[i];
			if ( cd.isAlive() )
			{
				cd.move(g);
			}
		}		
	}

	public static void damageAll()
	{
		for ( int i = 0; i < enemies.length; i++ )
		{
			EnemyData cd = enemies[i];
			if ( cd.isAlive() )
			{
				cd.minusHp(10);
				cd.checkHp(MainCanvas.getInstance());
			}
		}
	}
	
	public static void paintEnemyAll(EnemyData[] cds, MainCanvas g)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			EnemyData cd = cds[i];
			if ( cd.isAlive() && cd.isGround() )
			{
				cd.paint(g);
			}
		}

		for ( int i = 0; i < cds.length; i++ )
		{
			EnemyData cd = cds[i];
			if ( cd.isAlive() && !cd.isGround() )
			{
				cd.paint(g);
			}
		}
	}
	
	public static void paintBulletAll(BulletData[] cds, MainCanvas g)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			BulletData cd = cds[i];
			if ( cd.isAlive() && cd.isScoreItem() )
			{
				cd.paint(g);
			}
		}		
		for ( int i = 0; i < cds.length; i++ )
		{
			BulletData cd = cds[i];
			if ( cd.isAlive() && !cd.isScoreItem() )
			{
				cd.paint(g);
			}
		}		
	}
	
	public static void paintAll(CharacterData[] cds, MainCanvas g)
	{
		for ( int i = 0; i < cds.length; i++ )
		{
			CharacterData cd = cds[i];
			if ( cd.isAlive() )
			{
				cd.paint(g);
			}
		}		
	}
}
