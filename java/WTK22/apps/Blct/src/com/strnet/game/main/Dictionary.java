/*
 * Last modified: 2010/03/25 01:06:37
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.common.list.StringLightList;
import com.strnet.game.component.ImagePatternData;

public class Dictionary
{
	private static BulletData[] bullets;
	private static EnemyData[] enemies;
	private static BurstData[] bursts;
	private static ImagePatternData scoreItem;
	private static BombItemData bombItem;

	public static void init()
	{
		bullets = new BulletData[50];
		int sy = 106;
		int imgId = 1;
		int collisionHosei = 2;
		// 紫11px
		bullets[0] = new BulletData();
		bullets[0].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(0,0+sy,11,11), new Rectangle(12,0+sy,11,11)}));
		bullets[0].setCollision(5 - collisionHosei, 5 - collisionHosei);
		// 水色MyBullet
		bullets[1] = new BulletData();
		bullets[1].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(0,64,10,37)}));
		bullets[1].setCollision(10, 37);
		bullets[1].setDamage(10);
		
		// 赤15x15
		bullets[2] = new BulletData();
		bullets[2].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(0,12+sy,15,15), new Rectangle(16,12+sy,15,15)}));
		bullets[2].setCollision(7 - collisionHosei, 7 - collisionHosei);
		// 6px
		bullets[3] = new BulletData();
		bullets[3].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(24,0+sy,6,6), new Rectangle(30,0+sy,6,6)}));
		bullets[3].setCollision(2, 2);
		// 10px
		bullets[4] = new BulletData();
		bullets[4].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(40,0+sy,10,10), new Rectangle(50,0+sy,10,10)}));
		bullets[4].setCollision(4 - collisionHosei, 4 - collisionHosei);
		// 縦長
		bullets[5] = new BulletData();
		bullets[5].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(36,12+sy,6,12), new Rectangle(42,12+sy,6,12)}));
		bullets[5].setCollision(2, 6);
		// ミサイル
		bullets[6] = new BulletData();
		bullets[6].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(0,30+sy,12,30), new Rectangle(12,30+sy,12,30)}));
		bullets[6].setCollision(6 - collisionHosei, 16 - collisionHosei);
		// 13px
		bullets[7] = new BulletData();
		bullets[7].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(32,12+sy,13,13), new Rectangle(46,12+sy,13,13)}));
		bullets[7].setCollision(5 - collisionHosei, 5 - collisionHosei);

		// 自機みどり
		bullets[8] = new BulletData();
		bullets[8].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(53,64,11,37)}));
		bullets[8].setCollision(11, 37);
		bullets[8].setDamage(11);
		
		// 9x9
		bullets[9] = new BulletData();
		bullets[9].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(62,120,9,9),new Rectangle(71,120,9,9)}));
		bullets[9].setCollision(3, 3);
		// 8
		bullets[10] = new BulletData();
		bullets[10].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(62,120,8,8),new Rectangle(71,120,8,8)}));
		bullets[10].setCollision(2, 2);
		// 11x11
		bullets[11] = new BulletData();
		bullets[11].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(33,133,11,11),new Rectangle(44,133,11,11)}));
		bullets[11].setCollision(5 - collisionHosei, 5 - collisionHosei);
		// 10x10
		bullets[12] = new BulletData();
		bullets[12].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(61,134,10,10),new Rectangle(72,134,10,10)}));
		bullets[12].setCollision(4 - collisionHosei, 4 - collisionHosei);
		// 9x9 hisi
		bullets[13] = new BulletData();
		bullets[13].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(91,133,9,9),new Rectangle(82,133,9,9)}));
		bullets[13].setCollision(4 - collisionHosei, 4 - collisionHosei);
		// した
		bullets[14] = new BulletData();
		bullets[14].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(104,123,7,13), new Rectangle(112,123,7,13)}));
		bullets[14].setCollision(4 - collisionHosei, 4 - collisionHosei);
		// みぎななめした
		bullets[15] = new BulletData();
		bullets[15].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(122,124,13,11),new Rectangle(136,124,13,11)}));
		bullets[15].setCollision(4 - collisionHosei, 4 - collisionHosei);
		// ひだりななめした
		bullets[16] = new BulletData();
		bullets[16].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(121,138,13,11),new Rectangle(137,138,13,11)}));
		bullets[16].setCollision(4 - collisionHosei, 4 - collisionHosei);

		// 自機レーザー
		bullets[17] = new BulletData();
		bullets[17].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(23,62,15,42),new Rectangle(38,62,15,42)}));
		bullets[17].setCollision(15, 42);
		bullets[17].onLaser();
		bullets[17].setDamage(11);
		// 自機ハイパーレーザー
		bullets[18] = new BulletData();
		bullets[18].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(143,61,17,42), new Rectangle(161,61,17,42)}));
		bullets[18].setCollision(15, 42);
		bullets[18].onLaser();
		bullets[18].setDamage(13);
		// 自機ひだり斜めうえ
		bullets[19] = new BulletData();
		bullets[19].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(68,64,16,38)}));
		bullets[19].setCollision(17, 37);
		bullets[19].setDamage(10);
		// 自機みぎ斜めうえ
		bullets[20] = new BulletData();
		bullets[20].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(89,64,16,38)}));
		bullets[20].setCollision(16, 38);
		bullets[20].setDamage(10);
		// 自機ひだり斜めうえハイパー
		bullets[21] = new BulletData();
		bullets[21].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(106,64,16,38)}));
		bullets[21].setCollision(16, 38);
		bullets[21].setDamage(11);
		// 自機みぎ斜めうえハイパー
		bullets[22] = new BulletData();
		bullets[22].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(125,64,16,38)}));
		bullets[22].setCollision(16, 38);
		bullets[22].setDamage(11);
		// 自機まえ
		bullets[23] = new BulletData();
		bullets[23].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(181,70,12,37)}));
		bullets[23].setCollision(12, 37);
		bullets[23].setDamage(10);
		// 自機まえハイパー
		bullets[24] = new BulletData();
		bullets[24].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(11,64,12,37)}));
		bullets[24].setCollision(12, 37);
		bullets[24].setDamage(11);

		


		bursts = new BurstData[8];
		// normal 48x48
		bursts[0] = new BurstData();
		bursts[0].onWind();
		bursts[0].setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																	new Rectangle(192,192,48,48)}));

		// my
		bursts[1] = new BurstData();
		bursts[1].setImage(new ImagePatternData(1, new Rectangle[]{
																	 new Rectangle(24,32,24,32), 
																	 new Rectangle(0,32,24,32), 
																	 new Rectangle(24,32,24,32), 
																	 new Rectangle(48,32,24,32)}));
		// 弾消え
		bursts[2] = new BurstData();
		bursts[2].setImage(new ImagePatternData(imgId, new Rectangle[]{new Rectangle(60,0+sy,12,12), 
																   new Rectangle(72,0+sy,12,12),
																   new Rectangle(84,0+sy,12,12)}));
		
		// warning
		bursts[3] = new BurstData();
		bursts[3].setImage(new ImagePatternData(3,new Rectangle[] {
					new Rectangle(0,0,112,24),
					new Rectangle(0,24,112,24),
					new Rectangle(0,48,112,24),
					new Rectangle(0,72,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,96,112,24),
					new Rectangle(0,120,112,24),
					new Rectangle(0,144,112,24),
					new Rectangle(0,168,112,24),
					new Rectangle(0,192,112,24),
					new Rectangle(0,216,112,24)}));


		// boss 48x48
		bursts[4] = new BurstData();
		bursts[4].onWind();
		bursts[4].setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																   new Rectangle(192,192,48,48),
																   new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																	new Rectangle(192,192,48,48)}));

		// boss 48x48
		bursts[5] = new BurstData();
		bursts[5].onWind();
		bursts[5].setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																   new Rectangle(192,192,48,48),
																   new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																	new Rectangle(192,192,48,48)}));

		// boss 48x48
		bursts[6] = new BurstData();
		bursts[6].onWind();
		bursts[6].setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,0,0,0), 
																   new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																   new Rectangle(192,192,48,48),
																   new Rectangle(0,192,48,48), 
																	new Rectangle(48,192,48,48),
																	new Rectangle(96,192,48,48),
																	new Rectangle(144,192,48,48),
																	new Rectangle(192,192,48,48)}));
		// bomb 48x48
		bursts[7] = new BurstData();
		bursts[7].setImage(new ImagePatternData(3, new Rectangle[]{new Rectangle(192,0,48,48), 
																   new Rectangle(192,48,48,48),
																	new Rectangle(192,96,48,48),
//																   new Rectangle(192,144,48,48)}));
																	new Rectangle(192,144,48,48),
																	new Rectangle(192,192,48,48)}));

		/*
		scoreItem = new ImagePatternData(1, new Rectangle[]{new Rectangle(144,0,9,15),
															new Rectangle(153,0,10,16),
															new Rectangle(163,0,11,17),
															new Rectangle(174,0,11,18),
															new Rectangle(185,0,12,19),
															new Rectangle(197,0,12,20),
															new Rectangle(209,0,14,23),
															new Rectangle(223,0,17,26)});
		scoreItem = new ImagePatternData(1, new Rectangle[]{new Rectangle(144,0,9,15),
															new Rectangle(185,0,12,19),
															new Rectangle(223,0,17,26)});
		*/
		scoreItem = new ImagePatternData(1, new Rectangle[]{new Rectangle(170,33,7,7),
															new Rectangle(186,33,17,17),
															new Rectangle(207,33,33,34)});
		bombItem = new BombItemData();
		bombItem.setImage(new ImagePatternData(1, new Rectangle[]{new Rectangle(176,0,32,22),
																  new Rectangle(208,0,32,22)}));
		bombItem.setCollision(32, 22);
	}
	
	public static ImagePatternData getScoreItem()
	{
		return scoreItem;
	}
	
	public static BombItemData getBombItem()
	{
		return bombItem;
	}
	
	public static void preLoadImages()
	{
		preLoadImage(bullets);
		preLoadImage(enemies);
		preLoadImage(bursts);
		MainCanvas.getInstance().preLoadImage(scoreItem.getImageId());
	}
	
	private static void preLoadImage(CharacterData[] cd)
	{
		for ( int i = 0; i < cd.length; i++ )
		{
			if ( cd[i] != null )
			{
				cd[i].preLoad();
			}
		}
	}
	
	public static void setEnemies(StringLightList list)
	{
		enemies = new EnemyDataImpl[list.size()];
		for ( int i = 0; i < enemies.length; i++ )
		{
			setEnemy(i, GameUtil.split(list.get(i), ','));
		}
	}
	
	private static void setEnemy(int index, String[] str)
	{
		int counter = 0;
		enemies[index] = new EnemyDataImpl();
		int imageId = Integer.parseInt(str[counter++]);
		int sx = Integer.parseInt(str[counter++]);
		int sy = Integer.parseInt(str[counter++]);
		int width = Integer.parseInt(str[counter++]);
		int height = Integer.parseInt(str[counter++]);
		int sx2 = Integer.parseInt(str[counter++]);
		int sy2 = Integer.parseInt(str[counter++]);
		enemies[index].setImage(new ImagePatternData(imageId,
													 new Rectangle[]{
														 new Rectangle(sx,sy,width,height),
														 new Rectangle(sx2,sy2,width,height)}));
		enemies[index].setCollision(Integer.parseInt(str[counter++]), Integer.parseInt(str[counter++]));
		enemies[index].setHp(Integer.parseInt(str[counter++]));
		enemies[index].setDestroyPattern(("normal".equals(str[counter++])) ? EnemyData.DESTROY_NORMAL : EnemyData.DESTROY_SCORE_ITEM);
		enemies[index].setDestroyScore(Integer.parseInt(str[counter++]));
		enemies[index].setInterval(Integer.parseInt(str[counter++]));
		if ( "ground".equals(str[counter]) )
		{
			enemies[index].onGround();
		}
		counter++;

		int t = Integer.parseInt(str[counter++]);
		if ( t < 0 )
		{
			enemies[index].reverse = true;
		}
		enemies[index].setSpeed(Math.abs(t));
		enemies[index].setMoveId(Integer.parseInt(str[counter++]));
		enemies[index].setShotId(Integer.parseInt(str[counter++]));
		enemies[index].setShotImageId(Integer.parseInt(str[counter++]));
		enemies[index].setShotSpeed(FixedPoint.toFixedInt(Integer.parseInt(str[counter++])));
		
		enemies[index].setShotCount(Integer.parseInt(str[counter++]));
		enemies[index].setShotAngleRange(Integer.parseInt(str[counter++]));
		enemies[index].setShotAngleRange2(Integer.parseInt(str[counter++]));
		String size = str[counter++];
		if ( "large".equals(size) )
		{
			enemies[index].setSize(EnemyData.SIZE_LARGE);
		}
		else if ( "medium".equals(size) )
		{
			enemies[index].setSize(EnemyData.SIZE_MEDIUM);
		}
		if ( "random".equals(str[counter++]) )
		{
			enemies[index].onRandom();
		}
		String an = str[counter++];
		if ( "target".equals(an) )
		{
			enemies[index].onTargetAngle();
		}
		else if ( "halfTarget".equals(an) )
		{
			enemies[index].onHalfTargetAngle();
		}
		else
		{
			enemies[index].setShotAngle(Integer.parseInt(an));
		}
		enemies[index].setShotTime(Integer.parseInt(str[counter++]));
		enemies[index].setShotWaitTime(Integer.parseInt(str[counter++]));
		enemies[index].setShotAngleRange3(Integer.parseInt(str[counter++]));
	}

	public static BulletData getBullet(int id)
	{
		return bullets[id];
	}

	public static BurstData getBurst(int id)
	{
		return bursts[id];
	}

	public static EnemyData getEnemy(int id)
	{
		return enemies[id];
	}
}