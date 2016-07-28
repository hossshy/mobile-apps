/*
 * Last modified: 2010/03/24 12:51:08
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;

public class EnemyDataImpl extends EnemyData
{
	int tmpShotAngle = 0;
	int tmpX = 0;
	int tmpY = 0;
	boolean tmpB = false;
	boolean yuragiFlag = false;
	int yuragi = 0;
	
	public void copy(EnemyDataImpl parent)
	{
		super.copy(parent);
		tmpX = 0;
		tmpY = 0;
		tmpShotAngle = 0;
		tmpB = false;
		yuragiFlag = false;
		yuragi = 0;
	}
	
	public void move(int myX, int myY)
	{
		int t = 0;
		int t2;
		switch ( moveId )
		{
		case 0:
			break;
		case 1:
			if ( counter > 100 )
			{
				// Ç‹Ç¡Ç∑ÇÆç~ÇËÇÈ
				fixY += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter > 15 )
			{
				shotCheck(myX, myY);
			}			
			else
			{
				fixY += fixSpeed;
			}
			break;
		case 2:
			// Ç‹Ç¡Ç∑ÇÆç~ÇËÇÈ
			fixY += fixSpeed;

			// àÍíËéûä‘ÇÃÇ›î≠éÀ
			if ( counter < 40 )
			{
				shotCheck(myX, myY);
			}
			break;
		case 3:
			fixY += fixSpeed;
			
			shotCheck(myX, myY);
			break;
		case 4:
			// 1boss
				
			if ( counter < 44 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 44 )
			{
				setBulletData(16, 0, 4, 2, 50, 34, -1);
				onHalfTargetAngle();
				setShotAngleRange3(50);
				offRandom();
				setInterval(3);
				setShotTime(13);
				setShotWaitTime(13);
			}
			else if ( counter < 55 )
			{
			}
			else if ( counter >= 55 && counter < 240 )
			{
				shotCheck(myX, myY);
				if ( counter % 50 == 49 )
				{
					onRandom();
					tmpB = targetAngle;
					onTargetAngle();
					setBulletData(1, 7, 5, 12, -1, 0, -1);
					setShotAngleRange3(0);
					shot(myX, myY);
					setBulletData(16, 0, 4, 2, 50, 34, -1);
					setShotAngleRange3(50);
					if ( !tmpB )
					{
						offTargetAngle();
					}
					offRandom();
				}
			}
			else if ( counter < 260 )
			{
			}
			else if ( counter == 260 )
			{
				offHalfTargetAngle();
				setShotAngleRange2(62);
				tmpB = false;
				setInterval(10);
				setShotTime(-1);
				setShotWaitTime(0);
				tmpX = 270;
				tmpY = 0;
			}
			else if ( counter > 260 && counter < 600 )
			{
				// óºë§íeñãÅïë_Ç¢way
				setBulletData(4, 7, 7, 6, 12, -1, tmpX);
				onRandom();
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						tmpX += 2;
						if ( tmpX > 280 )
						{
							tmpB = false;
						}
					}
					else
					{
						tmpX -= 2;
						if ( tmpX < 260 )
						{
							tmpB = true;
						}
					}
				}
				
				if ( counter % (interval * 5) == 0 )
				{
					t = shotAngleRange2;
					setBulletData(10, 2, 3, 1, 0, 30, -1);
					setShotAngleRange3(45);
					onTargetAngle();
					shot(myX, myY);
					setShotAngleRange3(0);

					setBulletData(0, 2, 4, 1, 0, 0, -1);
					shot(myX, myY);


					offTargetAngle();
					shotAngleRange2 = t;
				}
				offRandom();
			}
			else if ( counter == 600 )
			{
				setBulletData(10, 2, 4, 5, 50, 30, -1);
				setShotAngleRange3(45);
				onTargetAngle();
				setInterval(5);
			}
			else if ( counter > 620 && counter < 680 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter == 680 )
			{
				setShotAngleRange3(0);
				offTargetAngle();
			}
			else if ( counter > 730 && counter < 760 )
			{
				fixY -= 256;
			}
			else if ( counter == 800 )
			{
				// É_ÉuÉãÇÆÇÈÇÆÇÈíe
				setBulletData(8, 2, 2, 3, 30, -1, -1);
				tmpY = FixedPoint.toFixedInt(50);
				setInterval(3);
			}
			else if ( counter > 800 )
			{
				shotCheck(myX, myY);
			}
			break;
		case 5:


			if ( counter > 200 )
			{
				// Ç‹Ç¡Ç∑ÇÆè„Ç™ÇÈ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter % 40 == 0 )
			{
				setShotId(10);
				setShotImageId(7);
				t = shotAngleRange2;
				setShotAngleRange2(30);
				shot(myX, myY);

				setShotId(4);
				setShotImageId(0);
				setShotAngleRange2(t);
			}
			else if ( counter > 15 )
			{
				shotCheck(myX, myY);
			}			
			else
			{
				fixY += fixSpeed;
			}
			break;
		case 6:
			if ( counter < 40 )
			{
				// Ç‹Ç¡Ç∑ÇÆç~ÇËÇÈ
				fixY += fixSpeed;
			}
			
			// í«Ç¢âzÇµíe
			if ( counter == 40 )
			{
				updateShotAngle(myX, myY);
				setBulletData(0, -1, 1, 6, 20, -1, -1);
			}
			if ( counter >= 40 && counter <= 60)
			{
				if ( shotAngle >= 0 && counter % 4 == 0 )
				{
					shot(myX, myY);
					addShotSpeed(1, 0);
				}
			}
			break;
		case 7:
			if ( counter < 20 || counter > 40 )
			{
				fixY += fixSpeed;
			}
			
			// àÍíËéûä‘ÇÃÇ›î≠éÀ
			if ( counter < 40 )
			{
				shotCheck(myX, myY);
			}
			break;
		case 8:
			// â°à⁄ìÆ
			fixX += (reverse) ? -fixSpeed : fixSpeed;
			
			t = counter % 24;
			// Ç‰ÇÁÇ¨
			if ( t < 6 )
			{
				fixY -= (t * 100);
			}
			else if ( t < 12 )
			{
				fixY += ((t-6) * 100);
			}

			shotCheck(myX, myY);
			break;
		case 9:
			if ( counter >= 70 )
			{
				fixX += (reverse) ? -tmpX : tmpX;
				fixY += tmpY;
			}
			else if ( counter > 5 && counter < 70 )
			{
				t = 270 - (counter - 10);
				tmpX = FixedPoint.multiply(fixSpeed, FixedPoint.cos(t));
				tmpY = FixedPoint.multiply(fixSpeed, FixedPoint.sin(t));
				fixX += (reverse) ? -tmpX : tmpX;
				fixY += tmpY;
				shotCheck(myX, myY);
			}
			else
			{
				// Ç‹Ç¡Ç∑ÇÆç~ÇËÇÈ
				fixY += fixSpeed;
				shotCheck(myX, myY);
			}
			
			break;
		case 10:
			// 1boss hard
			if ( counter < 44 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 44 )
			{
				setBulletData(16, 0, 4, 3, 50, 34, -1);
				onHalfTargetAngle();
				setShotAngleRange3(50);
				offRandom();
				setInterval(3);
				setShotTime(13);
				setShotWaitTime(13);
			}
			else if ( counter < 55 )
			{
			}
			else if ( counter >= 55 && counter < 240 )
			{
				shotCheck(myX, myY);
				if ( counter % 40 == 39 )
				{
					onRandom();
					tmpB = targetAngle;
					onTargetAngle();
					setBulletData(1, 7, 5, 15, -1, 0, -1);
					setShotAngleRange3(0);
					shot(myX, myY);
					setBulletData(16, 0, 4, 3, 50, 34, -1);
					setShotAngleRange3(50);
					if ( !tmpB )
					{
						offTargetAngle();
					}
					offRandom();
				}
			}
			else if ( counter < 260 )
			{
			}
			else if ( counter == 260 )
			{
				offHalfTargetAngle();
				setShotAngleRange2(57);
				tmpB = false;
				setInterval(10);
				setShotTime(-1);
				setShotWaitTime(0);
				tmpX = 270;
				tmpY = 0;
			}
			else if ( counter > 260 && counter < 600 )
			{
				// óºë§íeñãÅïë_Ç¢way
				setBulletData(4, 7, 4, 5, 12, -1, tmpX);
				onRandom();
				
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						tmpX += 4;
						if ( tmpX > 300 )
						{
							tmpB = false;
						}
					}
					else
					{
						tmpX -= 4;
						if ( tmpX < 230 )
						{
							tmpB = true;
						}
					}
				}

				if ( counter % (interval * 2) == 0 )
				{
					t = shotAngleRange2;
					setBulletData(10, 2, 4, 1, 30, 30, -1);
					setShotAngleRange3(45);
					onTargetAngle();
					shot(myX, myY);
					setShotAngleRange3(0);

					setBulletData(0, 2, 5, 1, 30, 0, -1);
					shot(myX, myY);

					offTargetAngle();
					shotAngleRange2 = t;
				}
				offRandom();
			}
			else if ( counter == 600 )
			{
				setBulletData(10, 2, 6, 5, 50, 30, -1);
				setShotAngleRange3(45);
				onTargetAngle();
				setInterval(5);
			}
			else if ( counter > 620 && counter < 700 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter == 700 )
			{
				setShotAngleRange3(0);
				offTargetAngle();
			}
			else if ( counter > 730 && counter < 760 )
			{
				fixY -= 256;
			}
			else if ( counter == 800 )
			{
				// É_ÉuÉãÇÆÇÈÇÆÇÈíe
				setBulletData(8, 2, 3, 3, 40, -1, -1);
				tmpY = FixedPoint.toFixedInt(50);
				setInterval(3);
			}
			else if ( counter > 800 )
			{
				shotCheck(myX, myY);
				
				if ( counter % 40 == 39 )
				{
					t = shotAngle;
					updateShotAngle(myX, myY);
					setBulletData(0, 7, 2, 5, 4, -1, -1);
					shot(myX, myY);
					setBulletData(8, 2, 3, 3, 30, -1, -1);
					setShotAngle(t);
				}
			}
			break;
		case 11:
			if ( counter > 200 )
			{
				// Ç‹Ç¡Ç∑ÇÆè„Ç™ÇÈ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter > 15 )
			{
				shotCheck(myX, myY);
			}			
			else
			{
				fixY += fixSpeed;
			}
			break;
			
		case 12:
			// 3 tyuu boss
			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 40 )
			{
				tmpShotAngle = 270;
				tmpB = false;
				setBulletData(0, 4, 2, 7, 5, -1, -1);
				interval = 20;
			}
			else if ( counter > 40 && counter < 300 && (hp > 700) )
			{
				//í¥ÉèÉCÉìÉ_Å[
				shotCheck(myX, myY);
				if ( counter % 40 == 0 )
				{
					// ë_Ç¢íe
					onRandom();
					updateShotAngle(myX, myY);
					shotNWay(getCenterFixX(), getCenterFixY(), shotAngle, shotSpeed + FixedPoint.toFixedInt(2), 5, 72, 2);
					offRandom();
				}
			}
			else if ( counter > 40 && counter < 300 && counter < 300 )
			{
				//Ç‹Ç´éUÇÁÇµ
				setBulletData(12, 4, 2, 4, 50, -1, -1);
				interval = 5;
				shotCheck(myX, myY);
			}
			else
			{
				// Ç‹Ç¡Ç∑ÇÆè„Ç™ÇÈ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			break;
		case 13:
			// 3boss
			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 45 )
			{
				setBulletData(1, 7, -1, 12, -1, -1, -1);
				updateShotAngle(myX, myY);
				shot(myX, myY);
				tmpB = false;
				tmpX = 0;
			}
			else if ( counter < 50 )
			{
			}
			else if ( counter < 90 )
			{
				fixY -= (fixSpeed / 2);
			}
			else if ( counter < 620 )
			{
				yuragi();
				
				if ( counter == 140 || counter == 170 || counter == 200 || counter == 230 )
				{
					setBulletData(0, 7, 5, 3, 1, -1, -1);
					onRandom();
					shot(myX, myY);
					offRandom();
				}
				else if ( counter == 145 || counter == 175 || counter == 205 || counter == 235 ||
						  counter == 147 || counter == 177 || counter == 207 || counter == 237 ||
						  counter == 149 || counter == 179 || counter == 209 || counter == 239 )
				{
					setBulletData(0, 11, 8, 12, 30, -1, -1);
					shot(myX, myY);
				}
				else if ( counter == 269 )
				{
					setBulletData(10, 2, 5, 4, 2, 38, -1);
				}
				else if ( counter >= 270 && counter <= 410 )
				{
					if ( shotCheck(myX, myY) )
					{
						addShotSpeed(1, 0);
					}
				}
				else if ( counter == 420 )
				{
					setBulletData(10, 7, 4, 5, 26, 38, -1);
					onRandom();
					updateShotAngle(myX, myY);
				}
				else if ( counter > 420 && counter < 620 )
				{
					shotCheck(myX, myY);
					if ( tmpB )
					{
						shotAngle += 5;
					}
					else
					{
						shotAngle -= 5;
					}
				}
			}
			else if ( counter == 650 )
			{
				setBulletData(1, 2, 5, 8, 60, 38, -1);
				interval = 7;
				offRandom();
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter > 650 && counter < 800 )
			{
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						shotAngleRange2 *= -1;
						tmpB = false;
					}
					else
					{
						shotAngleRange2 *= -1;
						tmpB = true;
					}
					shotAngle += 4;
				}
			}
			else if ( counter >= 800 && counter < 820 )
			{
			}
			else if ( counter >= 820 && counter < 855 )
			{
				fixY += 512;
			}
			else if ( counter == 870 )
			{
				setBulletData(1, 7, 7, 4, 0, 0, 45);
				shotAngleRange3 = 0;
				interval = 3;
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if (counter > 870 )
			{
				shotCheck(myX, myY);
				setShotAngle(shotAngle - 1);
				if ( counter % 20 == 0 )
				{
					t = shotAngle;
					setBulletData(0, 9, 2, 9, 20, 0, 0);
					if ( tmpB )
					{
						shotAngleRange3 = 38;
						shotAngle = 180;
						tmpB = false;
					}
					else
					{
						shotAngleRange3 = -38;
						tmpB = true;
					}
					shot(myX, myY);
					
					setBulletData(1, 7, 7, 4, 0, 0, 0);
					shotAngleRange3 = 0;
					shotAngle = t;
				}
			}
			
			break;
		case 14:
			// 3boss hard
			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 45 )
			{
				setBulletData(1, 7, -1, 12, -1, -1, -1);
				updateShotAngle(myX, myY);
				shot(myX, myY);
				tmpB = true;
				tmpX = 0;
			}
			else if ( counter == 47 )
			{
				shotAngle += 15;
				setShotImageId(2);
				offTargetAngle();
				shot(myX, myY);
				onTargetAngle();
			}
			else if ( counter < 50 )
			{
			}
			else if ( counter < 96 )
			{
				fixY -= (fixSpeed / 2);
			}
			else if ( counter < 120 )
			{
			}
			else if ( counter < 269 )
			{
				yuragi();

				t = counter % 30;
				if ( t < 14 && (t % 3 == 0) )
				{
					if ( t == 0 )
					{
						setBulletData(0, 7, 4, 5, 7, -1, -1);
						onRandom();
						updateShotAngle(myX, myY);
						offRandom();
						offTargetAngle();
					}
					else
					{
						shotCount--;
					}
					shot(myX, myY);
				}
				else if ( t == 18 )
				{
					updateShotAngle(myX, myY);
					setBulletData(1, 4, 3, 13, 10, -1, -1);
					shot(myX, myY);
				}
			}
			else if ( counter == 269 )
			{
				yuragi();
				setBulletData(10, 2, 7, 4, 2, 38, -1);
				onTargetAngle();
				onRandom();
			}
			else if ( counter >= 290 && counter <= 400 )
			{
				yuragi();
				shotCheck(myX, myY);
				if ( counter % 30 == 29 )
				{
					setBulletData(1, 0, 3, 13, 10, 0, 270);
					shot(myX, myY);
					setBulletData(10, 2, 7, 4, 2, 38, -1);
				}
			}
			else if ( counter == 420 )
			{
				yuragi();
				setBulletData(10, 7, 4, 5, 21, 38, -1);
				onRandom();
				updateShotAngle(myX, myY);
			}
			else if ( counter > 420 && counter < 620 )
			{
				yuragi();
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						shotAngle += 5;
						addShotSpeed(0, 3);
					}
					else
					{
						shotAngle -= 5;
						addShotSpeed(0, 4);
					}
				}
			}
			else if ( counter == 650 )
			{
				setBulletData(1, 2, 6, 8, 60, 38, -1);
				interval = 5;
				offRandom();
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter > 650 && counter < 800 )
			{
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						shotAngleRange2 *= -1;
						tmpB = false;
					}
					else
					{
						shotAngleRange2 *= -1;
						tmpB = true;
					}
					shotAngle += 5;
				}
			}
			else if ( counter >= 800 && counter < 820 )
			{
			}
			else if ( counter >= 820 && counter < 855 )
			{
				fixY += 512;
			}
			else if ( counter == 870 )
			{
				setBulletData(1, 7, 7, 6, 0, 0, 45);
				shotAngleRange3 = 0;
				interval = 3;
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if (counter > 870 )
			{
				shotCheck(myX, myY);
				setShotAngle(shotAngle + 1);
				if ( counter % 20 == 0 )
				{
					t = shotAngle;
					setBulletData(0, 9, 2, 12, 15, 0, 0);
					if ( tmpB )
					{
						shotAngleRange3 = 38;
						shotAngle = 180;
						tmpB = false;
					}
					else
					{
						shotAngleRange3 = -38;
						tmpB = true;
					}
					shot(myX, myY);
					
					setBulletData(1, 7, 7, 6, 0, 0, 0);
					shotAngleRange3 = 0;
					shotAngle = t;
				}
			}
			
			break;
		case 15:
			// 2boss
			if ( counter < 47 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 47 )
			{
				setShotId(1);
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter < 160 )
			{
				if ( counter % 16 <= 4 )
				{
					shot(myX, myY);
				}

				if ( counter < 100 )
				{
					setShotAngle(shotAngle + shotAngleRange);
				}
				else
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}
			else if ( counter == 160 )
			{
				setBulletData(10, 10, 7, 2, 50, 55, -1);
				interval = 2;
				tmpY = FixedPoint.toFixedInt(25);
				onTargetAngle();
			}
			else if ( counter > 165 && counter < 500 )
			{
				shotCheck(myX, myY);

				int tt = counter % 40;
				if ( tt <= 1 )
				{
					t = shotAngleRange;
					setBulletData(0, 2, 4, 4, 3, 0, -1);
					t2 = shotAngleRange3;
					shotAngleRange3 = 0;
					if ( tt == 0 )
					{
						onRandom();
						updateShotAngle(myX, myY);
						offRandom();
					}

					offTargetAngle();
					shot(myX, myY);
					onTargetAngle();
					
					setBulletData(10, 4, 7, 2, t, 55, -1);
					shotAngleRange3 = t2;
				}
			}
			else if ( counter == 500 )
			{
				setBulletData(10, 7, 4, 3, 50, 55, -1);
				interval = 2;
				tmpY = FixedPoint.toFixedInt(25);
				onRandom();
				updateShotAngle(myX, myY);
				offRandom();
			}
			else if ( counter <= 506 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter <= 528 )
			{
				fixY -= (fixSpeed / 2);
			}
			else if ( counter < 540 )
			{
			}
			else if ( counter == 540 )
			{
				setBulletData(11, 7, 3, 5, 72, 55, -1);
				interval = 8;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter <=  750 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 11);
				}
			}
			else if ( counter == 810 )
			{
				setBulletData(11, 2, 3, 7, 72, 55, -1);
				interval = 6;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter > 810 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle - 13);
				}
			}
			
			break;
		case 16:
			// 2boss hard
			if ( counter < 47 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 47 )
			{
				setShotId(1);
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter < 143 )
			{
				if ( counter % 12 <= 2 )
				{
					shot(myX, myY);
				}

				if ( counter < 100 )
				{
					setShotAngle(shotAngle + shotAngleRange);
				}
				else
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}
			else if ( counter < 160 )
			{
			}
			else if ( counter == 160 )
			{
				setBulletData(10, 4, 5, 3, 50, 55, -1);
				interval = 3;
				tmpY = FixedPoint.toFixedInt(25);
				onTargetAngle();
				tmpB = false;
				tmpShotAngle = 0;
			}
			else if ( counter >= 180 && counter < 500 )
			{
				if ( tmpB )
				{
					shotCheck(myX, myY);
				}
				
				int tt = counter % 20;
				if ( tt <= 2 )
				{
					t = shotAngleRange;
					setBulletData(0, 2, 6, 7, 20, 0, -1);
					t2 = shotAngleRange3;
					shotAngleRange3 = 0;
					if ( tt == 0 )
					{
						onRandom();
						tmpShotAngle = updateShotAngle(myX, myY);
						offRandom();
					}
					else
					{
						setShotAngle(tmpShotAngle);
					}

					offTargetAngle();
					shot(myX, myY);
					onTargetAngle();
					setBulletData(10, 4, 5, 3, t, 55, -1);
					shotAngleRange3 = t2;
				}
				else if ( counter % 30 == 3 )
				{
					tmpB = !tmpB;
				}
			}
			else if ( counter == 500 )
			{
				setBulletData(10, 4, 6, 6, 15, 55, -1);
				interval = 1;
				tmpY = FixedPoint.toFixedInt(25);
				onRandom();
				updateShotAngle(myX, myY);
				offRandom();
			}
			else if ( counter <= 506 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter <= 528 )
			{
				fixY -= (fixSpeed / 2);
			}
			else if ( counter < 540 )
			{
			}
			else if ( counter == 540 )
			{
				setBulletData(11, 7, 3, 6, 60, 55, -1);
				interval = 7;
				tmpY = FixedPoint.toFixedInt(25);
				tmpX = 0;
			}
			else if ( counter <=  750 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 11);

					tmpX++;
					if ( tmpX > 6 )
					{
						setShotSpeed(FixedPoint.toFixedInt(2));
						tmpX = 0;
					}
					else
					{
						addShotSpeed(0, tmpX);
					}
				}
			}
			else if ( counter == 810 )
			{
				setBulletData(11, 2, 5, 8, 15, 55, -1);
				interval = 6;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter > 810 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle - 23);
				}
			}
			
			break;
		case 17:
			fixY += fixSpeed;
			
			// 3òAéÀ
			if ( tmpX > 2 )
			{
				setShotId(0);
				tmpX = 0;
			}
			else if ( tmpX >= 1 )
			{
				shot(myX, myY);
				tmpX++;
			}
			else if ( shotCheck(myX, myY) )
			{
				setShotId(0);
				tmpX++;
			}
			
			break;
		case 18:
			// 3 tyuu boss hard
			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 40 )
			{
				tmpShotAngle = 270;
				tmpB = false;
				setBulletData(0, 4, 3, 7, 5, -1, -1);
				interval = 15;
			}
			else if ( counter > 40 && counter < 300 && (hp > 700) )
			{
				//í¥ÉèÉCÉìÉ_Å[
				shotCheck(myX, myY);
				if ( counter % 30 == 0 )
				{
					// ë_Ç¢íe
					onRandom();
					updateShotAngle(myX, myY);
					shotNWay(getCenterFixX(), getCenterFixY(), shotAngle, shotSpeed + FixedPoint.toFixedInt(2), 5, 54, 2);
					offRandom();
				}
			}
			else if ( counter > 40 && counter < 300 )
			{
				//Ç‹Ç´éUÇÁÇµ
				setBulletData(12, 4, 2, 5, 50, -1, -1);
				interval = 4;
				shotCheck(myX, myY);
			}
			else
			{
				// Ç‹Ç¡Ç∑ÇÆè„Ç™ÇÈ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			break;
		case 19:
			// Ç‹Ç¡Ç∑ÇÆâEorç∂Ç÷
			if ( reverse )
			{
				fixX -= fixSpeed;
			}
			else
			{
				fixX += fixSpeed;
			}
			fixY += FixedPoint.toFixedInt(2);
			shotCheck(myX, myY);
			break;
		case 20:
			if ( counter > 200 )
			{
				// Ç‹Ç¡Ç∑ÇÆÇ≥Ç™ÇÈ
				fixY += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter > 15 )
			{
				shotCheck(myX, myY);
			}			
			else
			{
				fixY += fixSpeed;
			}
			break;
		case 21: // 4boss

			if ( counter > 40 && counter < 215 )
			{
				yuragi();
			}

			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter < 47 )
			{
			}
			else if ( counter < 120 )
			{
				// ÇŒÇÁÇ‹Ç´
				shotCheck(myX, myY);
			}
			else if ( counter < 140 )
			{
				fixY += (fixSpeed / 2);
			}
			else if ( counter == 140 )
			{
				// óºéËÇ©ÇÁ
				setBulletData(1,2,2,12,10,-1,-1);
				offTargetAngle();
				setShotAngle(FixRandom.next() * 3);

				shotAngleRange2 = -55;
				shotAngleRange3 = 50;
				shot(myX, myY);
				shotAngleRange2 = 55;
				shot(myX, myY);
			}
			else if ( counter < 150 )
			{
				setShotImageId(4);
				setShotSpeed(FixedPoint.toFixedInt(1));
			}
			else if ( counter < 156 )
			{
				// óºéËÇ©ÇÁë¨Ç¢ÇÃ
				if ( counter % 2 == 0 )
				{
					shotAngleRange2 = -55;
					shotAngleRange3 = 50;
					shot(myX, myY);
					shotAngleRange2 = 55;
					shot(myX, myY);
					addShotSpeed(1, 0);
				}
			}
			else if ( counter == 165 )
			{
				// íÜêSÇ©ÇÁÉèÉCÉìÉ_Å[àÍî≠
				setBulletData(0,11,2,8,13,0,-1);
				shotAngleRange3 = 0;
				onTargetAngle();
				shot(myX, myY);
			}
			else if ( counter < 230 )
			{
			}
			else if ( counter < 275 )
			{
				// ç∂â∫à⁄ìÆ
				fixX -= fixSpeed;
				fixY += (fixSpeed / 2);
			}
			else if ( counter < 293 )
			{
				fixY += fixSpeed;
			}
			else if ( counter < 300 )
			{
			}
			else if ( counter == 300 )
			{
				setBulletData(1,7,1,8,-1,-1,270);
				offTargetAngle();
				interval = 10;
			}
			else if ( counter < 375 )
			{
				// âEà⁄ìÆ
				fixX += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 375 )
			{
				setShotSpeed(FixedPoint.toFixedInt(2));
			}
			else if ( counter < 430 )
			{
				// è„à⁄ìÆ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 460 )
			{
				// ç∂à⁄ìÆ
				fixX -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 460 )
			{
				setBulletData(0,2,5,5,3,-1,-1);
				onTargetAngle();
				interval = 4;
				shot(myX, myY);
			}
			else if ( counter < 482 )
			{
				// Ç«ÇÌÇ¡Ç∆
				shotCheck(myX, myY);
			}
			else if ( counter < 530 )
			{
			}
			else if ( counter == 530 )
			{
				interval = 7;
				updateShotAngle(myX, myY);
				offTargetAngle();
				tmpB = true;
				tmpX = 0;
			}
			else if ( counter < 540 )
			{
			}
			else if ( counter < 900 )
			{
				setBulletData(0,11,3,3,120,-1,-1);
				setShotAngle(shotAngle + 2);
				shotAngleRange3 = -60;
				shotAngleRange2 = 50;
				shotCheck(myX, myY);
				shotAngleRange3 = 60;
				t = shotAngle;
				setBulletData(0,0,3,4,90,-1,-1);
				addShotSpeed(0,5);
				setShotAngle(120 - t);
				shotCheck(myX, myY);
				shotAngle = t;
				
				if ( counter % 35 == 0 )
				{
					t = shotAngle;
					setBulletData(1,7,4,10,10,0,-1);
					shotAngleRange3 = 0;
					onRandom();
					updateShotAngle(myX, myY);
					shot(myX, myY);

					offRandom();
					shotAngle = t;
					setBulletData(0,11,3,3,120,-1,-1);
				}
			}
			else if ( counter < 910 )
			{
				fixY -= fixSpeed;
			}
			else if ( counter < 950 )
			{
			}
			else if ( counter == 950 )
			{
				setBulletData(0,11,2,8,15,-1,260);
				offTargetAngle();
				offRandom();
				tmpX = updateShotAngle(myX, myY);
			}
			else if ( counter > 950 )
			{
				if ( counter % 26 == 0 )
				{
					shotAngleRange3 = -60;
					shotAngleRange2 = 50;
					shot(myX, myY);
					shotAngleRange3 = 60;
					shot(myX, myY);
				}

				t = counter % 45;
				if ( t > 40 )
				{
					setBulletData(0,2,2 + (t-41),1,3,0,tmpX);
					shotAngleRange3 = 0;
					if ( t == 41 )
					{
						onTargetAngle();
						onRandom();
						tmpX = updateShotAngle(myX, myY);
						offRandom();
						offTargetAngle();
					}
					shot(myX, myY);
					setBulletData(0,11,2,8,15,-1,tmpX);
				}
			}
			break;
		case 22: // 4boss hard

			if ( counter > 40 && counter < 215 )
			{
				// Ç‰ÇÁÇ¨
				yuragi();
			}

			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter < 47 )
			{
			}
			else if ( counter < 120 )
			{
				// ÇŒÇÁÇ‹Ç´
				shotCheck(myX, myY);
			}
			else if ( counter < 140 )
			{
				fixY += (fixSpeed / 2);
			}
			else if ( counter == 140 )
			{
				// óºéËÇ©ÇÁ
				setBulletData(1,2,2,12,10,-1,-1);
				offTargetAngle();
				setShotAngle(FixRandom.next() * 3);

				shotAngleRange2 = -55;
				shotAngleRange3 = 50;
				shot(myX, myY);
				shotAngleRange2 = 55;
				shot(myX, myY);
			}
			else if ( counter < 150 )
			{
				setShotImageId(4);
				setShotSpeed(FixedPoint.toFixedInt(2));
			}
			else if ( counter < 156 )
			{
				// óºéËÇ©ÇÁë¨Ç¢ÇÃ
				if ( counter % 2 == 0 )
				{
					shotAngleRange2 = -55;
					shotAngleRange3 = 50;
					shot(myX, myY);
					shotAngleRange2 = 55;
					shot(myX, myY);
					addShotSpeed(1, 5);
				}
			}
			else if ( counter == 165 )
			{
				// íÜêSÇ©ÇÁÉèÉCÉìÉ_Å[àÍî≠
				setBulletData(0,11,3,13,8,0,-1);
				shotAngleRange3 = 0;
				onTargetAngle();
				shot(myX, myY);
			}
			else if ( counter < 230 )
			{
			}
			else if ( counter < 275 )
			{
				fixX -= fixSpeed;
				fixY += (fixSpeed / 2);
			}
			else if ( counter < 293 )
			{
				fixY += fixSpeed;
			}
			else if ( counter < 300 )
			{
			}
			else if ( counter == 300 )
			{
				setBulletData(1,7,3,16,-1,-1,270);
				offTargetAngle();
				interval = 9;
			}
			else if ( counter < 375 )
			{
				fixX += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 375 )
			{
				setShotSpeed(FixedPoint.toFixedInt(4));
			}
			else if ( counter < 430 )
			{
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 460 )
			{
				fixX -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 460 )
			{
				setBulletData(0,2,6,7,3,-1,-1);
				onTargetAngle();
				interval = 3;
				shot(myX, myY);
			}
			else if ( counter < 502 )
			{
				// Ç«ÇÌÇ¡Ç∆
				shotCheck(myX, myY);
			}
			else if ( counter < 530 )
			{
			}
			else if ( counter == 530 )
			{
				interval = 5;
				updateShotAngle(myX, myY);
				offTargetAngle();
				tmpB = true;
				tmpX = 3;
			}
			else if ( counter < 540 )
			{
			}
			else if ( counter < 900 )
			{
				setBulletData(0,11,3,4,90,-1,-1);
				setShotAngle(shotAngle + tmpX);
				shotAngleRange3 = -60;
				shotAngleRange2 = 50;
				shotCheck(myX, myY);
				shotAngleRange3 = 60;
				t = shotAngle;
				setBulletData(0,0,4,5,72,-1,-1);
				setShotAngle(120 - t);
				shotCheck(myX, myY);
				shotAngle = t;
				
				if ( counter % 45 == 0 )
				{
					t = shotAngle;
					setBulletData(0,7,2,5,5,0,-1);
					shotAngleRange3 = 0;
					onRandom();
					updateShotAngle(myX, myY);
					shot(myX, myY);

					offRandom();
					shotAngle = t;
					setBulletData(0,11,3,3,120,-1,-1);
					tmpX *= -1;
				}
			}
			else if ( counter < 910 )
			{
				fixY -= fixSpeed;
			}
			else if ( counter < 950 )
			{
			}
			else if ( counter == 950 )
			{
				setBulletData(0,11,2,12,12,-1,260);
				offTargetAngle();
				tmpX = updateShotAngle(myX, myY);
			}
			else if ( counter > 950 )
			{
				if ( counter % 26 == 0 )
				{
					shotAngleRange3 = -60;
					shotAngleRange2 = 50;
					shot(myX, myY);
					shotAngleRange3 = 60;
					shot(myX, myY);
				}

				t = counter % 45;
				if ( t > 40 )
				{
					setBulletData(0,2,3 + (t-41),2,2,0,tmpX);
					shotAngleRange3 = 0;
					if ( t == 41 )
					{
						onTargetAngle();
						onRandom();
						tmpX = updateShotAngle(myX, myY);
						offRandom();
						offTargetAngle();
					}
					shot(myX, myY);
					setBulletData(0,11,2,12,12,-1,tmpX);
				}
			}
			break;
		case 23:
			if ( counter > 200 )
			{
				// Ç‹Ç¡Ç∑ÇÆè„Ç™ÇÈ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter % 30 == 0 )
			{
				setShotId(10);
				setShotImageId(7);
				t = shotAngleRange2;
				setShotAngleRange2(30);
				shot(myX, myY);

				setShotId(4);
				setShotImageId(0);
				setShotAngleRange2(t);
			}
			else if ( counter > 15 )
			{
				shotCheck(myX, myY);
			}			
			else
			{
				fixY += fixSpeed;
			}
			break;
		case 24:
			if ( counter % 24 == 0 )
			{
				setBulletData(10,12,2,10,36,36,-1);
				setShotAngleRange3(36);
				shot(myX, myY);
				
				setBulletData(10,7,4,8,18,-36,-1);
				setShotAngleRange2(-36);
				setShotAngleRange3(-36);
				shot(myX, myY);
				
				setBulletData(0,2,5,4,6,0,-1);
				setShotAngleRange2(0);
				setShotAngleRange3(0);
				shot(myX, myY);
			}
			else if ( counter > 50 )
			{
				shotCheck(myX, myY);
			}
			else if (counter == 50 )
			{
				fixSpeed = FixedPoint.toFixedInt(1);
			}
			fixY += fixSpeed;
			break;
		case 25:
			fixY += fixSpeed;
			setBulletData(10,16,5,1,40,35,235);
			setShotAngleRange3(35);
			if ( shotCheck(myX, myY) )
			{
				setBulletData(10,15,5,1,40,35,305);
				setShotAngleRange3(35);
				shot(myX, myY);
				setBulletData(10,14,5,1,40,35,270);
				setShotAngleRange3(35);
				shot(myX, myY);

				if ( tmpB && (tmpX != 0) && (counter < tmpX) && (counter % interval == 0) )
				{
					setBulletData(0,0,6,1,0,25,tmpShotAngle);
					setShotAngleRange3(22);
					shot(myX, myY);
					setBulletData(0,0,6,1,0,25,yuragi);
					setShotAngleRange3(-22);
					shot(myX, myY);
					tmpY++;
					if ( tmpY == 3 )
					{
					}
				}
				else
				{
					setShotAngleRange3(22);
					t = FixedPoint.toFixedInt(shotAngleRange3);
					tmpShotAngle = updateShotAngle(myX, myY, getCenterFixX() + t, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange2));
					setShotAngleRange3(-22);
					yuragi = updateShotAngle(myX, myY, getCenterFixX() - t, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange2));
					tmpX = shotTimeCounter;
					tmpB = true;
					tmpY = 0;
					offTargetAngle();
				}
			}
			break;
		case 26:
			// 5boss
			if ( counter < 45 )
			{
				fixY += fixSpeed;
			}
			else if (  counter < 159 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter == 159 )
			{
				setBulletData(13, 2, 4, 1, 0, 40, -1);
			}
			else if ( counter < 280 )
			{
				t = counter % 40;
				switch ( t )
				{
				case 0:
					onRandom();
					onTargetAngle();
					updateShotAngle(myX, myY);
					offTargetAngle();
					offRandom();
					setShotSpeed(FixedPoint.toFixedInt(2));
				case 3:
				case 6:
				case 9:
				case 12:
					shot(myX, myY);
					setShotAngle(shotAngle + 10);
					addShotSpeed(0, 3);
					break;
				}
			}
			else if ( counter == 280 )
			{
				setBulletData(0, 11, 4, 11, 15, -10, -1);
				updateShotAngle(myX, myY);
				shot(myX, myY);
			}
			else if ( counter == 282 || counter == 284 )
			{
				shot(myX, myY);
			}
			else if ( counter < 330 )
			{
			}
			else if ( counter == 330 )
			{
				tmpX = 0;
			}
			else if ( counter < 730 )
			{
				if ( counter % 8 == 0 )
				{
					t = shotAngleRange3;
					t2 = FixedPoint.toInt(shotSpeed);
					int t3 = shotCount;
					int t4 = shotAngleRange2;
					setBulletData(1, 9, 3, 3, 120, 0, -1);
					shotAngleRange3 = -10;
					tmpShotAngle += 7;
					setShotAngle(tmpShotAngle);
					shot(myX, myY);
					tmpShotAngle += 14;
					setShotAngle(tmpShotAngle);
					shot(myX, myY);
					tmpShotAngle -= 7;
					setShotImageId(7);
					setShotAngle(tmpShotAngle);
					shot(myX, myY);

					tmpShotAngle = shotAngle + 14;
					
					setBulletData(15, 0, t2, t3, 20, -1, 270);
					setShotAngleRange2(t4);
					setShotAngleRange3(t);
				}
				t = counter % 40;
				switch ( t )
				{
				case 0:
					setBulletData(15, 0, 2, 4, 20, -1, 270);
					setShotAngleRange2(-28);
				case 3:
				case 6:
				case 9:
					shot(myX, myY);
					setShotCount(shotCount + 2);
					addShotSpeed(1, 0);
					shotAngleRange2 -= 16;
					break;
				case 14:
					tmpY++;
					break;
				case 28:
					switch ( tmpX )
					{
					case 0:
						shotAngleRange3 = 25 + FixRandom.next();
						tmpX++;
						break;
					case 1:
						shotAngleRange3 = -25 - FixRandom.next();
						tmpX++;
						break;
					case 2:
						shotAngleRange3 = 0;
						tmpX = 0;
						break;
					}
					break;
				}
			}
			else if ( counter < 779 )
			{
			}
			else if ( counter == 779 )
			{
				setBulletData(13, 2, 4, 1, 13, -1, 270);
				setShotAngleRange2(-40);
				interval = 2;
			}
			else if ( counter < 849 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}
			else if ( counter == 849 )
			{
				setBulletData(14, 14, 5, 1, 8, -1, 269);
				setShotAngleRange2(-30);
				setShotAngleRange3(0);
				interval = 4;
			}
			else if ( counter < 890 )
			{
			}
			else
			{
				if ( shotCheck(myX, myY) )
				{
					setShotSpeed(FixedPoint.toFixedInt(2 + (FixRandom.next() % 5)));
				}
				
				if ( counter % 22 == 0 )
				{
					setShotId(0);
					setShotAngleRange(20);
					setShotImageId(2);
					setShotCount(18);
					shot(myX, myY);
					setShotImageId(14);
					setShotCount(1);
					setShotId(14);
				}
			}

			break;
		case 27:
			fixY += fixSpeed;
			
			offTargetAngle();
			offRandom();
			setBulletData(10,16,6,1,40,35,235);
			setShotAngleRange3(35);
			if ( shotCheck(myX, myY) )
			{
				setBulletData(10,15,6,1,40,35,305);
				setShotAngleRange3(35);
				shot(myX, myY);
				setBulletData(10,14,6,1,40,35,270);
				setShotAngleRange3(35);
				shot(myX, myY);

				onTargetAngle();
				setBulletData(10,0,7,1,3,22,-1);
				setShotAngleRange3(25);
				shot(myX, myY);
			}
			break;
		case 28:
			if ( counter % 21 == 0 )
			{
				setBulletData(10,12,3,10,36,36,-1);
				setShotAngleRange3(36);
				shot(myX, myY);
				
				setBulletData(10,7,5,8,18,-36,-1);
				setShotAngleRange2(-36);
				setShotAngleRange3(-36);
				shot(myX, myY);
				
				setBulletData(0,2,6,4,3,0,-1);
				setShotAngleRange2(0);
				setShotAngleRange3(0);
				shot(myX, myY);
			}
			else if ( counter > 50 )
			{
				shotCheck(myX, myY);
			}
			else if (counter == 50 )
			{
				fixSpeed = FixedPoint.toFixedInt(1);
			}
			fixY += fixSpeed;
			break;
		case 29:
			// 1boss hell
			if ( counter < 44 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 44 )
			{
				setBulletData(16, 0, 4, 3, 25, 34, -1);
				onHalfTargetAngle();
				setShotAngleRange3(50);
				offRandom();
				setInterval(3);
				setShotTime(13);
				setShotWaitTime(13);
			}
			else if ( counter < 55 )
			{
			}
			else if ( counter >= 55 && counter < 240 )
			{
				shotCheck(myX, myY);
				if ( counter % 50 == 49 )
				{
					onRandom();
					tmpB = targetAngle;
					onTargetAngle();
					setBulletData(0, 7, 5, 27, 8, 0, -1);
					setShotAngleRange3(0);
					shot(myX, myY);
					setBulletData(16, 0, 4, 3, 25, 34, -1);
					setShotAngleRange3(50);
					if ( !tmpB )
					{
						offTargetAngle();
					}
					offRandom();
				}
			}
			else if ( counter < 260 )
			{
			}
			else if ( counter == 260 )
			{
				offHalfTargetAngle();
				setShotAngleRange2(57);
				tmpB = false;
				setInterval(10);
				setShotTime(-1);
				setShotWaitTime(0);
				tmpX = 270;
				tmpY = 0;
			}
			else if ( counter > 260 && counter < 600 )
			{
				// óºë§íeñãÅïë_Ç¢way
				setBulletData(4, 7, 4, 9, 9, -1, tmpX);
				onRandom();
				
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						tmpX += 12;
						if ( tmpX > 300 )
						{
							tmpB = false;
						}
					}
					else
					{
						tmpX -= 12;
						if ( tmpX < 230 )
						{
							tmpB = true;
						}
					}
				}

				if ( counter % (interval * 2) == 0 )
				{
					t = shotAngleRange2;
					setBulletData(10, 2, 4, 1, 30, 30, -1);
					setShotAngleRange3(45);
					onTargetAngle();
					shot(myX, myY);
					setShotAngleRange3(0);

					setBulletData(0, 2, 5, 3, 30, 0, -1);
					shot(myX, myY);

					offTargetAngle();
					shotAngleRange2 = t;
				}
				offRandom();
			}
			else if ( counter == 600 )
			{
				setBulletData(10, 2, 6, 5, 25, 30, -1);
				setShotAngleRange3(45);
				onTargetAngle();
				setInterval(3);
			}
			else if ( counter > 615 && counter < 700 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter == 700 )
			{
				setShotAngleRange3(0);
				offTargetAngle();
			}
			else if ( counter > 730 && counter < 760 )
			{
				fixY -= 256;
			}
			else if ( counter == 800 )
			{
				// É_ÉuÉãÇÆÇÈÇÆÇÈíe
				setBulletData(8, 2, 3, 3, 30, -1, -1);
				tmpY = FixedPoint.toFixedInt(50);
				setInterval(3);
			}
			else if ( counter > 800 )
			{
				shotCheck(myX, myY);
				
				if ( counter % 40 == 39 )
				{
					t = shotAngle;
					updateShotAngle(myX, myY);
					setBulletData(0, 7, 2, 11, 5, -1, -1);
					shot(myX, myY);
					setBulletData(8, 2, 3, 3, 30, -1, -1);
					setShotAngle(t);
				}
			}
			break;
		case 30:
			// 2boss hell
			if ( counter < 47 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 47 )
			{
				setShotId(1);
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter < 143 )
			{
				if ( counter % 10 <= 2 )
				{
					shot(myX, myY);
				}

				if ( counter < 100 )
				{
					setShotAngle(shotAngle + shotAngleRange);
				}
				else
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}
			else if ( counter < 160 )
			{
			}
			else if ( counter == 160 )
			{
				setBulletData(10, 4, 5, 3, 30, 55, -1);
				interval = 3;
				tmpY = FixedPoint.toFixedInt(25);
				onTargetAngle();
				tmpB = false;
				tmpShotAngle = 0;
			}
			else if ( counter >= 180 && counter < 500 )
			{
				if ( tmpB )
				{
					shotCheck(myX, myY);
				}
				
				int tt = counter % 20;
				if ( tt <= 2 )
				{
					t = shotAngleRange;
					setBulletData(0, 2, 5, 7, 11, 0, -1);
					t2 = shotAngleRange3;
					shotAngleRange3 = 0;
					if ( tt == 0 )
					{
						onRandom();
						tmpShotAngle = updateShotAngle(myX, myY);
						offRandom();
					}
					else
					{
						setShotAngle(tmpShotAngle);
					}

					offTargetAngle();
					shot(myX, myY);
					onTargetAngle();
					setBulletData(10, 4, 6, 3, t, 55, -1);
					shotAngleRange3 = t2;
				}
				else if ( counter % 25 == 3 )
				{
					tmpB = !tmpB;
				}
			}
			else if ( counter == 500 )
			{
				setBulletData(10, 4, 6, 6, 15, 55, -1);
				interval = 1;
				tmpY = FixedPoint.toFixedInt(25);
				onRandom();
				updateShotAngle(myX, myY);
				offRandom();
			}
			else if ( counter <= 506 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter <= 528 )
			{
				fixY -= (fixSpeed / 2);
			}
			else if ( counter < 540 )
			{
			}
			else if ( counter == 540 )
			{
				setBulletData(11, 7, 4, 6, 60, 55, -1);
				interval = 7;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter <=  750 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 11);

					setBulletData(11, 0, 3, 6, 60, 55, -1);
					t = shotAngle;
					setShotAngle(360 - t);
					shot(myX, myY);
					setBulletData(11, 7, 4, 6, 60, 55, -1);
					setShotAngle(t);
				}
			}
			else if ( counter == 810 )
			{
				setBulletData(11, 2, 5, 8, 45, 55, -1);
				interval = 5;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter > 810 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle - 13);
				}
			}
			break;
		case 31:
			// 3boss hell
			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 45 )
			{
				setBulletData(1, 7, -1, 30, -1, -1, -1);
				updateShotAngle(myX, myY);
				shot(myX, myY);
				tmpB = true;
				tmpX = 0;
			}
			else if ( counter == 47 )
			{
				shotCount = 30;
				shotAngle += 6;
				setShotImageId(2);
				offTargetAngle();
				shot(myX, myY);
				onTargetAngle();
			}
			else if ( counter < 50 )
			{
			}
			else if ( counter < 96 )
			{
				fixY -= (fixSpeed / 2);
			}
			else if ( counter < 120 )
			{
			}
			else if ( counter < 269 )
			{
				yuragi();

				t = counter % 30;
				if ( t < 14 && (t % 3 == 0) )
				{
					if ( t == 0 )
					{
						setBulletData(0, 7, 4, 9, 11, -1, -1);
						onRandom();
						updateShotAngle(myX, myY);
						offRandom();
						offTargetAngle();
					}
					else
					{
						shotCount--;
					}
					shot(myX, myY);
				}
				else if ( t == 18 )
				{
					updateShotAngle(myX, myY);
					setBulletData(1, 4, 3, 19, 10, -1, -1);
					shot(myX, myY);
				}
			}
			else if ( counter == 269 )
			{
				yuragi();
				setBulletData(10, 2, 1, 4, 11, 38, -1);
				onTargetAngle();
				onRandom();
			}
			else if ( counter >= 290 && counter <= 400 )
			{
				yuragi();
				if ( shotCheck(myX, myY) )
				{
					addShotSpeed(1, 0);
				}
				
				if ( counter % 30 == 29 )
				{
					t = shotSpeed;
					setBulletData(1, 0, 3, 13, 10, 0, 270);
					shot(myX, myY);
					setBulletData(10, 2, -1, 4, 11, 38, -1);
					setShotSpeed(t);
				}
			}
			else if ( counter == 420 )
			{
				yuragi();
				setBulletData(10, 7, 3, 5, 21, 38, -1);
				onRandom();
				updateShotAngle(myX, myY);
			}
			else if ( counter > 420 && counter < 620 )
			{
				yuragi();
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						shotAngle += 5;
						t = shotSpeed;
						setBulletData(0, 4, 4, 9, 11, 0, -1);
						shot(myX, myY);

						setBulletData(10, 7, -1, 5, 21, 38, -1);
						setShotSpeed(t);
						addShotSpeed(0, 3);
					}
					else
					{
						shotAngle -= 5;
						addShotSpeed(1, 0);
					}
				}
			}
			else if ( counter == 650 )
			{
				setBulletData(1, 2, 6, 13, 60, 38, -1);
				interval = 5;
				offRandom();
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter > 650 && counter < 800 )
			{
				if ( shotCheck(myX, myY) )
				{
					if ( tmpB )
					{
						shotAngleRange2 *= -1;
						tmpB = false;
					}
					else
					{
						shotAngleRange2 *= -1;
						tmpB = true;
					}
					shotAngle += 5;
				}
			}
			else if ( counter >= 800 && counter < 820 )
			{
			}
			else if ( counter >= 820 && counter < 850 )
			{
				fixY += 512;
			}
			else if ( counter == 870 )
			{
				setBulletData(1, 7, 7, 7, 0, 0, 45);
				shotAngleRange3 = 0;
				interval = 3;
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if (counter > 870 )
			{
				shotCheck(myX, myY);
				setShotAngle(shotAngle - 1);
				if ( counter % 20 == 0 )
				{
					t = shotAngle;
					setBulletData(0, 9, 2, 12, 9, 0, 0);
					if ( tmpB )
					{
						shotAngleRange3 = 38;
						shotAngle = 180;
						tmpB = false;
					}
					else
					{
						shotAngleRange3 = -38;
						tmpB = true;
					}
					shot(myX, myY);
					
					setBulletData(1, 7, 7, 7, 0, 0, 45);
					shotAngleRange3 = 0;
					shotAngle = t;
				}
			}
			break;
		case 32: // 4boss hell

			if ( counter > 40 && counter < 215 )
			{
				// Ç‰ÇÁÇ¨
				yuragi();
			}

			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter < 47 )
			{
			}
			else if ( counter < 120 )
			{
				// ÇŒÇÁÇ‹Ç´
				if ( shotCheck(myX, myY) )
				{
					setShotSpeed(FixedPoint.toFixedInt(4) + (FixRandom.next() * 25));
					setShotImageId((FixRandom.next() % 2 == 0) ? 10 : 7);
				}
			}
			else if ( counter < 140 )
			{
				fixY += (fixSpeed / 2);
			}
			else if ( counter == 140 )
			{
				setBulletData(1,2,2,12,10,-1,-1);
				offTargetAngle();
				setShotAngle(FixRandom.next() * 3);

				shotAngleRange2 = -55;
				shotAngleRange3 = 50;
				shot(myX, myY);
				shotAngleRange2 = 55;
				shot(myX, myY);
			}
			else if ( counter < 150 )
			{
				setShotImageId(4);
				setShotSpeed(FixedPoint.toFixedInt(3));
			}
			else if ( counter < 156 )
			{
				if ( counter % 2 == 0 )
				{
					shotAngleRange2 = -55;
					shotAngleRange3 = 50;
					shot(myX, myY);
					shotAngleRange2 = 55;
					shot(myX, myY);
					addShotSpeed(2, 0);
				}
			}
			else if ( counter == 165 )
			{
				setBulletData(0,11,2,15,6,0,-1);
				shotAngleRange3 = 0;
				onTargetAngle();
				shot(myX, myY);

				setBulletData(1,7,1,16,-1,-1,270);
				offTargetAngle();
				interval = 8;
			}
			else if ( counter < 230 )
			{
			}
			else if ( counter < 275 )
			{
				fixX -= fixSpeed;
				fixY += (fixSpeed / 2);
				shotCheck(myX, myY);
			}
			else if ( counter < 293 )
			{
				fixY += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 293 )
			{
				setShotSpeed(FixedPoint.toFixedInt(4));
			}
			else if ( counter < 300 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter < 375 )
			{
				fixX += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 375 )
			{
				setShotSpeed(FixedPoint.toFixedInt(5));
			}
			else if ( counter < 430 )
			{
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 460 )
			{
				fixX -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter == 460 )
			{
				setBulletData(0,2,6,5,9,-1,-1);
				onTargetAngle();
				interval = 3;
				shot(myX, myY);
			}
			else if ( counter < 520 )
			{
				// Ç«ÇÌÇ¡Ç∆
				shotCheck(myX, myY);
			}
			else if ( counter < 539 )
			{
			}
			else if ( counter == 539 )
			{
				interval = 5;
				setBulletData(0,11,4,4,90,-1,-1);
				updateShotAngle(myX, myY);
				offTargetAngle();
				tmpB = true;
				tmpX = 3;
			}
			else if ( counter < 900 )
			{
				setBulletData(0,11,4,3,120,-1,-1);
				setShotAngle(shotAngle + tmpX + 1);
				shotAngleRange3 = -60;
				shotAngleRange2 = 50;
				shotCheck(myX, myY);

				shotAngleRange3 = 60;
				t = shotAngle - 1;
				setBulletData(0,0,3,3,120,-1,-1);
				setShotAngle(120 - t);
				shotCheck(myX, myY);

				shotAngle = t;
				if (counter % 70 == 0 )
				{
					tmpX *= -1;
				} 
				else if ( counter % 2 == 0 )
				{
					t = shotAngle;
					setBulletData(0,7,3,3,120,-1,-1);
					shotAngleRange3 = 0;
					shot(myX, myY);
					shotAngle = t;
				}
			}
			else if ( counter < 910 )
			{
				fixY -= fixSpeed;
			}
			else if ( counter < 950 )
			{
			}
			else if ( counter == 950 )
			{
				setBulletData(0,11,2,12,9,-1,260);
				offTargetAngle();
				tmpX = updateShotAngle(myX, myY);
			}
			else if ( counter > 950 )
			{
				if ( counter % 26 == 0 )
				{
					shotAngleRange3 = -60;
					shotAngleRange2 = 50;
					shot(myX, myY);
					shotAngleRange3 = 60;
					shot(myX, myY);
				}

				t = counter % 45;
				if ( t > 40 )
				{
					setBulletData(0,2,3 + (t-41),3,3,0,tmpX);
					shotAngleRange3 = 0;
					if ( t == 41 )
					{
						onTargetAngle();
						tmpX = updateShotAngle(myX, myY);
						offTargetAngle();
					}
					shot(myX, myY);
					setBulletData(0,11,2,12,9,-1,tmpX);
				}
			}
			break;
		case 33:
			// 5boss hell
			if ( counter < 45 )
			{
				fixY += fixSpeed;
			}
			else if (  counter < 159 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter == 159 )
			{
				setBulletData(13, 2, 4, 1, 0, 45, -1);
			}
			else if ( counter < 280 )
			{
				t = counter % 18;
				switch ( t )
				{
				case 0:
					onRandom();
					onTargetAngle();
					updateShotAngle(myX, myY);
					offTargetAngle();
					offRandom();
					setShotSpeed(FixedPoint.toFixedInt(2));
				case 3:
				case 6:
				case 9:
				case 12:
					shot(myX, myY);
					setShotAngle(shotAngle + 10);
					addShotSpeed(0, 9);
					break;
				}
			}
			else if ( counter == 280 )
			{
				setBulletData(0, 11, 4, 17, 8, -10, -1);
				updateShotAngle(myX, myY);
				shot(myX, myY);
			}
			else if ( counter == 282 || counter == 284 )
			{
				shot(myX, myY);
			}
			else if ( counter < 330 )
			{
			}
			else if ( counter == 330 )
			{
				tmpX = 0;
			}
			else if ( counter < 730 )
			{
				t = counter % 35;
				switch ( t )
				{
				case 0:
					setBulletData(15, 0, 2, 5, 18, -1, 270);
					setShotAngleRange2(-28);
				case 3:
				case 6:
				case 9:
					shot(myX, myY);
					setShotCount(shotCount + 1);
					addShotSpeed(1, 0);
					shotAngleRange2 -= 16;
					break;
				case 14:
					tmpY++;
					break;
				case 25:
					if ( tmpY == 3 )
					{
						setBulletData(0,2,2,30,6, 30, -1);
						shotAngleRange3 = 0;
						onTargetAngle();
						shot(myX, myY);
						offTargetAngle();
					}
					if ( tmpY > 6 )
					{
						setBulletData(0,2,3,30,2, 30, -1);
						shotAngleRange3 = 0;
						onTargetAngle();
						shot(myX, myY);
						offTargetAngle();
						tmpY = 0;
					}
					break;
				case 28:
					switch ( tmpX )
					{
					case 0:
						shotAngleRange3 = 25 + FixRandom.next();
						tmpX++;
						break;
					case 1:
						shotAngleRange3 = -25 - FixRandom.next();
						tmpX++;
						break;
					case 2:
						shotAngleRange3 = 0;
						tmpX = 0;
						break;
					}
					break;
				}
			}
			else if ( counter < 779 )
			{
			}
			else if ( counter == 779 )
			{
				setBulletData(13, 2, 7, 1, 13, -1, 270);
				setShotAngleRange2(-40);
				interval = 1;
			}
			else if ( counter < 849 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}
			else if ( counter == 849 )
			{
				setBulletData(14, 14, 5, 1, 8, -1, 269);
				setShotAngleRange2(-30);
				interval = 3;
				tmpY = 269;
			}
			else if ( counter < 890 )
			{
			}
			else
			{
				if ( shotCheck(myX, myY) )
				{
					setShotSpeed(FixedPoint.toFixedInt(3 + (FixRandom.next() % 4)));
				}
				
				if ( counter % 20 == 0 )
				{
					setShotId(0);
					setShotAngleRange(15);
					setShotImageId(7);
					setShotCount(24);
					shot(myX, myY);
					setShotImageId(14);
					setShotCount(1);
					setShotId(14);
				}
				else if ( counter % 2 == 0 )
				{
					t = shotSpeed;
					setBulletData(1,2,5,7,20,0,tmpY);
					shot(myX, myY);
					tmpY += 21;
					setBulletData(14, 14, -1, 1, 8, -1, 269);
					setShotSpeed(t);
					setShotAngleRange2(-30);
				}
			}
			break;
		case 34:
			// 5boss hard
			if ( counter < 45 )
			{
				fixY += fixSpeed;
			}
			else if (  counter < 159 )
			{
				shotCheck(myX, myY);
			}
			else if ( counter == 159 )
			{
				setBulletData(13, 2, 4, 1, 0, 40, -1);
			}
			else if ( counter < 280 )
			{
				t = counter % 20;
				switch ( t )
				{
				case 0:
					onRandom();
					onTargetAngle();
					updateShotAngle(myX, myY);
					offTargetAngle();
					offRandom();
					setShotSpeed(FixedPoint.toFixedInt(2));
				case 3:
				case 6:
				case 9:
				case 12:
					shot(myX, myY);
					setShotAngle(shotAngle + 10);
					addShotSpeed(0, 6);
					break;
				}
			}
			else if ( counter == 280 )
			{
				setBulletData(0, 11, 4, 17, 8, -10, -1);
				updateShotAngle(myX, myY);
				shot(myX, myY);
			}
			else if ( counter == 282 || counter == 284 )
			{
				shot(myX, myY);
			}
			else if ( counter < 330 )
			{
			}
			else if ( counter == 330 )
			{
				tmpX = 0;
			}
			else if ( counter < 730 )
			{
				t = counter % 35;
				switch ( t )
				{
				case 0:
					setBulletData(15, 0, 2, 3, 28, -1, 270);
					setShotAngleRange2(-28);
				case 3:
				case 6:
				case 9:
					shot(myX, myY);
					setShotCount(shotCount + 1);
					addShotSpeed(1, 0);
					shotAngleRange2 -= 16;
					break;
				case 14:
					tmpY++;
					break;
				case 25:
					if ( tmpY == 3 )
					{
						setBulletData(0,2,2,30,12, 30, -1);
						shotAngleRange3 = 0;
						onTargetAngle();
						shot(myX, myY);
						offTargetAngle();
					}
					if ( tmpY > 6 )
					{
						setBulletData(0,2,2,30,2, 30, -1);
						shotAngleRange3 = 0;
						onTargetAngle();
						shot(myX, myY);
						offTargetAngle();
						tmpY = 0;
					}
					break;
				case 28:
					switch ( tmpX )
					{
					case 0:
						shotAngleRange3 = 25 + FixRandom.next();
						tmpX++;
						break;
					case 1:
						shotAngleRange3 = -25 - FixRandom.next();
						tmpX++;
						break;
					case 2:
						shotAngleRange3 = 0;
						tmpX = 0;
						break;
					}
					break;
				}
			}
			else if ( counter < 779 )
			{
			}
			else if ( counter == 779 )
			{
				setBulletData(13, 2, 5, 1, 13, -1, 270);
				setShotAngleRange2(-40);
				interval = 1;
			}
			else if ( counter < 849 )
			{
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}
			else if ( counter == 849 )
			{
				setBulletData(14, 14, 5, 1, 8, -1, 269);
				setShotAngleRange2(-30);
				setShotAngleRange3(0);
				interval = 3;
			}
			else if ( counter < 890 )
			{
			}
			else
			{
				if ( shotCheck(myX, myY) )
				{
					setShotSpeed(FixedPoint.toFixedInt(3 + (FixRandom.next() % 4)));
				}
				
				if ( counter % 20 == 0 )
				{
					setShotId(0);
					setShotAngleRange(15);
					setShotImageId(7);
					setShotCount(24);
					shot(myX, myY);
					setShotImageId(14);
					setShotCount(1);
					setShotId(14);
				}
				else if ( counter % 3 == 0 )
				{
					t = shotSpeed;
					setBulletData(1,2,6,1,10,0,269);
					shot(myX, myY);

					setBulletData(14, 14, -1, 1, 8, -1, 269);
					setShotSpeed(t);
					setShotAngleRange2(-30);
				}
			}

			break;
		case 35:
			// 3 tyuu boss hell
			if ( counter < 40 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 40 )
			{
				tmpShotAngle = 270;
				tmpB = false;
				setBulletData(0, 4, 3, 11, 5, -1, -1);
				interval = 15;
			}
			else if ( counter > 40 && counter < 300 && (hp > 700) )
			{
				//í¥ÉèÉCÉìÉ_Å[
				shotCheck(myX, myY);
				if ( counter % 25 == 0 )
				{
					// ë_Ç¢íe
					onRandom();
					updateShotAngle(myX, myY);
					shotNWay(getCenterFixX(), getCenterFixY(), shotAngle, shotSpeed + FixedPoint.toFixedInt(2), 5, 30, 2);
					offRandom();
				}
			}
			else if ( counter > 40 && counter < 300 )
			{
				//Ç‹Ç´éUÇÁÇµ
				setBulletData(12, 4, 2, 7, 50, -1, -1);
				interval = 5;
				shotCheck(myX, myY);
			}
			else
			{
				// Ç‹Ç¡Ç∑ÇÆè„Ç™ÇÈ
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			break;
		case 36:
			if ( counter > 100 )
			{
				// Ç‹Ç¡Ç∑ÇÆç~ÇËÇÈ
				fixY += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter > 15 )
			{
				shotCheck(myX, myY);
			}			
			else
			{
				fixY += fixSpeed;
				shotCheck(myX, myY);
			}
			break;
		case 37:
			// Ç‹Ç¡Ç∑ÇÆâEorç∂Ç÷
			if ( reverse )
			{
				fixX -= fixSpeed;
			}
			else
			{
				fixX += fixSpeed;
			}
			fixY += FixedPoint.toFixedInt(2);
			if ( counter < 40 )
			{
				shotCheck(myX, myY);
			}
			break;
		case 38: //true boss
			if ( counter < 45 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 45 )
			{
				setBulletData(16, 2, 4, 5, 29, 40, -1);
				onRandom();
				updateShotAngle(myX, myY);
				onHalfTargetAngle();
				setInterval(4);
				setShotTime(13);
				setShotWaitTime(13);
			}
			else if (  counter < 200 )
			{
				// 1
				shotCheck(myX, myY);
				if ( counter % 50 >= 48 )
				{
					tmpB = targetAngle;
					tmpShotAngle = shotAngle;
					setBulletData(0, 7, 3, 9, -1, 0, -1);
					setShotAngleRange3(0);
					updateShotAngle(myX, myY);
					shot(myX, myY);

					setBulletData(16, 2, 4, 5, 23, 40, -1);
					setShotAngle(tmpShotAngle);
					targetAngle = tmpB;
				}
			}
			else if ( counter == 200 )
			{
				setShotTime(-1);
				setShotWaitTime(0);
				setBulletData(13, 2, 4, 1, 0, 40, -1);
			}
			else if ( counter < 230 )
			{
			}
			else if ( counter == 230 )
			{
				setBulletData(11, 7, 4, 5, 90, 40, -1);
				interval = 11;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter < 435 )
			{
				// 2
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 7);
					setBulletData(11, 2, 3, 3, 120, 40, -1);
					t = shotAngle;
					setShotAngle(360 - t);
					shot(myX, myY);
					setBulletData(11, 7, 4, 5, 90, 40, -1);
					setShotAngle(t);
				}
			}
			else if ( counter < 465 )
			{
			}
			else if ( counter < 483 )
			{
				fixY += 512;
			}
			else if ( counter < 513 )
			{
			}
			else if ( counter == 513 )
			{
				setBulletData(1, 2, 7, 4, 0, 0, 45);
				shotAngleRange3 = 0;
				interval = 3;
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter < 770 )
			{
				// 3 ok
				shotCheck(myX, myY);
				setShotAngle(shotAngle - 1);
				if ( counter % 20 == 0 )
				{
					t = shotAngle;
					setBulletData(0, 7, 2, 3, 23, 0, 0);
					if ( tmpB )
					{
						shotAngleRange3 = 38;
						shotAngle = 180;
						tmpB = false;
					}
					else
					{
						shotAngleRange3 = -38;
						tmpB = true;
					}
					onTargetAngle();
					onRandom();
					shot(myX, myY);
					offTargetAngle();
					offRandom();
					
					setBulletData(1, 2, 7, 4, 0, 0, 45);
					shotAngleRange3 = 0;
					shotAngle = t;
				}
			}
			else if ( counter == 770 )
			{
				// 4
				setBulletData(1,2,3,10,-1,-1,-1);
				shotAngleRange3 = 0;
				onTargetAngle();
				onRandom();
				interval = 9;
			}
			else if ( counter < 800 )
			{
			}
			else if ( counter < 860 )
			{
				fixY += 512;
				shotCheck(myX, myY);
			}
			else if ( counter < 881 )
			{
				fixX += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 901 )
			{
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 921 )
			{
				fixX -= fixSpeed;
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 981 )
			{
			}
			else if ( counter == 981 )
			{
				setInterval(4);
				setShotTime(-1);
				setShotWaitTime(0);
			}
			else if ( counter < 1181 )
			{
				// 5 ok
				t = counter % 60;
				if ( t > 10 )
				{	
				if ( t % 2 == 0 )
					{
						onRandom();
						onTargetAngle();
						setBulletData(0, 7, 4, 3, 33, 0, -1);
						shotAngleRange3 = 0;
						shot(myX, myY);
					}
				}
				else
				{
					t2 = 3;
					switch ( t )
					{
					case 0:
						tmpX = FixedPoint.toInt(myX - getCenterFixY());
						t2--;
					case 2:
						t2--;
					case 4:
						t2--;
					case 8:
						offRandom();
						offTargetAngle();
						setBulletData(15, 2, 4, 1 + t2, 33, 0, 270);
						shotAngleRange3 = tmpX;
						addShotSpeed(0, t);
						shot(myX, myY);
						break;
					}
				}
			}
			else if ( counter < 1231 )
			{
			}
			else if (counter == 1231 )
			{
				setBulletData(1,7,4,5,17,-1,230);
				offTargetAngle();
				interval = 5;
				shotAngleRange3 = 0;
				tmpX = 360;
			}
			else if ( counter == 1400 )
			{
				tmpX = 180;
			}
			else
			{
				// Ç«ÇÌÇ¡Ç∆
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 11);
					t = shotAngle;
					setShotAngle(tmpX - t);
					shot(myX, myY);
					shotAngle = t;
				}
				
				if ( counter % 30 == 0 )
				{
					setBulletData(0,2,2,3,2,-1,-1);
					t = shotAngle;
					onRandom();
					updateShotAngle(myX, myY);
					offRandom();
					shot(myX, myY);
					setBulletData(1,7,3,5,17,-1,-1);
					shotAngle = t;
				}
			}
			break;
		case 39: //true boss hard
			if ( counter < 45 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 45 )
			{
				setBulletData(16, 2, 4, 5, 18, 40, -1);
				onRandom();
				updateShotAngle(myX, myY);
				onHalfTargetAngle();
				setInterval(4);
				setShotTime(13);
				setShotWaitTime(13);
			}
			else if (  counter < 200 )
			{
				// 1
				shotCheck(myX, myY);
				if ( counter % 50 >= 48 )
				{
					tmpB = targetAngle;
					tmpShotAngle = shotAngle;
					setBulletData(0, 7, 3, 12, -1, 0, -1);
					setShotAngleRange3(0);
					updateShotAngle(myX, myY);
					shot(myX, myY);

					setBulletData(16, 2, 4, 5, 18, 40, -1);
					setShotAngle(tmpShotAngle);
					targetAngle = tmpB;
				}
			}
			else if ( counter == 200 )
			{
				setShotTime(-1);
				setShotWaitTime(0);
				setBulletData(13, 2, 4, 1, 0, 40, -1);
			}
			else if ( counter < 230 )
			{
			}
			else if ( counter == 230 )
			{
				setBulletData(11, 7, 5, 5, 60, 40, -1);
				interval = 9;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter < 435 )
			{
				// 2
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 7);
					setBulletData(11, 2, 4, 3, 120, 40, -1);
					t = shotAngle;
					setShotAngle(360 - t);
					shot(myX, myY);
					setBulletData(11, 7, 5, 5, 60, 40, -1);
					setShotAngle(t);
				}
			}
			else if ( counter < 465 )
			{
			}
			else if ( counter < 483 )
			{
				fixY += 512;
			}
			else if ( counter < 513 )
			{
			}
			else if ( counter == 513 )
			{
				setBulletData(1, 2, 7, 5, 0, 0, 45);
				shotAngleRange3 = 0;
				interval = 3;
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter < 770 )
			{
				// 3 ok
				shotCheck(myX, myY);
				setShotAngle(shotAngle + 1);
				if ( counter % 20 == 0 )
				{
					t = shotAngle;
					setBulletData(0, 7, 2, 5, 19, 0, 0);
					if ( tmpB )
					{
						shotAngleRange3 = 38;
						shotAngle = 180;
						tmpB = false;
					}
					else
					{
						shotAngleRange3 = -38;
						tmpB = true;
					}
					onTargetAngle();
					onRandom();
					shot(myX, myY);
					offTargetAngle();
					offRandom();
					
					setBulletData(1, 2, 7, 5, 0, 0, 45);
					shotAngleRange3 = 0;
					shotAngle = t;
				}
			}
			else if ( counter == 770 )
			{
				// 4
				setBulletData(1,2,3,12,-1,-1,-1);
				shotAngleRange3 = 0;
				onTargetAngle();
				onRandom();
				interval = 8;
			}
			else if ( counter < 800 )
			{
			}
			else if ( counter < 860 )
			{
				fixY += 512;
				shotCheck(myX, myY);
			}
			else if ( counter < 881 )
			{
				fixX += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 901 )
			{
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 921 )
			{
				fixX -= fixSpeed;
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 981 )
			{
			}
			else if ( counter == 981 )
			{
				setInterval(3);
				setShotTime(-1);
				setShotWaitTime(0);
			}
			else if ( counter < 1181 )
			{
				// 5 ok
				t = counter % 60;
				if ( t > 10 )
				{
					if ( t % 2 == 0 )
					{
						onRandom();
						onTargetAngle();
						setBulletData(0, 7, 4, 5, 26, 0, -1);
						shotAngleRange3 = 0;
						shot(myX, myY);
					}
				}
				else
				{
					t2 = 3;
					switch ( t )
					{
					case 0:
						tmpX = FixedPoint.toInt(myX - getCenterFixY());
						t2--;
					case 2:
						t2--;
					case 4:
						t2--;
					case 8:
						offRandom();
						offTargetAngle();
						setBulletData(15, 2, 4, 2 + t2, 33, 0, 270);
						shotAngleRange3 = tmpX;
						addShotSpeed(0, t);
						shot(myX, myY);
						break;
					}
				}
			}
			else if ( counter < 1231 )
			{
			}
			else if (counter == 1231 )
			{
				setBulletData(1,7,4,5,13,-1,230);
				offTargetAngle();
				interval = 3;
				shotAngleRange3 = 0;
				tmpX = 360;
			}
			else if ( counter == 85 )
			{
				//tmpX = 180;
			}
			else
			{
				// Ç«ÇÌÇ¡Ç∆
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 9);
					t = shotAngle;
					setShotAngle(tmpX - t);
					shot(myX, myY);
					shotAngle = t;
				}
				
				if ( counter % 25 == 0 )
				{
					setBulletData(0,2,4,3,7,-1,-1);
					t = shotAngle;
					onRandom();
					updateShotAngle(myX, myY);
					offRandom();
					shot(myX, myY);
					setBulletData(1,7,4,5,13,-1,230);
					shotAngle = t;
				}
			}
			break;
		case 40: //true boss chaos
			if ( counter < 45 )
			{
				fixY += fixSpeed;
			}
			else if ( counter == 45 )
			{
				setBulletData(16, 2, 4, 7, 11, 40, -1);
				onRandom();
				updateShotAngle(myX, myY);
				onHalfTargetAngle();
				setInterval(4);
				setShotTime(13);
				setShotWaitTime(13);
			}
			else if (  counter < 200 )
			{
				// 1
				shotCheck(myX, myY);
				if ( counter % 50 >= 48 )
				{
					tmpB = targetAngle;
					tmpShotAngle = shotAngle;
					setBulletData(0, 7, 5, 12, -1, 0, -1);
					setShotAngleRange3(0);
					updateShotAngle(myX, myY);
					shot(myX, myY);

					setBulletData(16, 2, 4, 7, 11, 40, -1);
					setShotAngle(tmpShotAngle);
					targetAngle = tmpB;
				}
			}
			else if ( counter == 200 )
			{
				setShotTime(-1);
				setShotWaitTime(0);
				setBulletData(13, 2, 4, 1, 0, 40, -1);
			}
			else if ( counter < 230 )
			{
			}
			else if ( counter == 230 )
			{
				setBulletData(11, 7, 5, 6, 60, 40, -1);
				interval = 7;
				tmpY = FixedPoint.toFixedInt(25);
			}
			else if ( counter < 435 )
			{
				// 2 ok
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 7);
					setBulletData(11, 2, 6, 6, 60, 40, -1);
					t = shotAngle;
					setShotAngle(360 - t);
					shot(myX, myY);
					setBulletData(11, 7, 4, 5, 60, 40, -1);
					setShotAngle(t);
				}
			}
			else if ( counter < 465 )
			{
			}
			else if ( counter < 483 )
			{
				fixY += 512;
			}
			else if ( counter < 513 )
			{
			}
			else if ( counter == 513 )
			{
				setBulletData(1, 2, 7, 5, 0, 0, 45);
				shotAngleRange3 = 0;
				interval = 3;
				updateShotAngle(myX, myY);
				offTargetAngle();
			}
			else if ( counter < 770 )
			{
				// 3 ok
				shotCheck(myX, myY);
				setShotAngle(shotAngle - 2);
				if ( counter % 20 == 0 )
				{
					t = shotAngle;
					setBulletData(0, 7, 2, 7, 15, 0, 0);
					if ( tmpB )
					{
						shotAngleRange3 = 38;
						shotAngle = 180;
						tmpB = false;
					}
					else
					{
						shotAngleRange3 = -38;
						tmpB = true;
					}
					onTargetAngle();
					onRandom();
					shot(myX, myY);
					offTargetAngle();
					offRandom();
					
					setBulletData(1, 2, 7, 5, 0, 0, 45);
					shotAngleRange3 = 0;
					shotAngle = t;
				}
			}
			else if ( counter == 770 )
			{
				// 4
				setBulletData(1,2,3,16,-1,-1,-1);
				shotAngleRange3 = 0;
				onTargetAngle();
				onRandom();
				interval = 7;
			}
			else if ( counter < 800 )
			{
			}
			else if ( counter < 860 )
			{
				fixY += 512;
				shotCheck(myX, myY);
			}
			else if ( counter < 881 )
			{
				fixX += fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 901 )
			{
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 921 )
			{
				fixX -= fixSpeed;
				fixY -= fixSpeed;
				shotCheck(myX, myY);
			}
			else if ( counter < 981 )
			{
			}
			else if ( counter == 981 )
			{
				setInterval(2);
				setShotTime(-1);
				setShotWaitTime(0);
			}
			else if ( counter < 1181 )
			{
				// 5 ok
				t = counter % 60;
				if ( t > 10 )
				{
					if ( t % 2 == 0 )
					{
						onRandom();
						onTargetAngle();
						setBulletData(0, 7, 4, 7, 20, 0, -1);
						shotAngleRange3 = 0;
						shot(myX, myY);
					}
				}
				else
				{
					t2 = 3;
					switch ( t )
					{
					case 0:
						tmpX = FixedPoint.toInt(myX - getCenterFixY());
						t2--;
					case 2:
						t2--;
					case 4:
						t2--;
					case 8:
						offRandom();
						offTargetAngle();
						setBulletData(15, 2, 4, 3 + t2, 29, 0, 270);
						shotAngleRange3 = tmpX;
						addShotSpeed(0, t);
						shot(myX, myY);
						break;
					}
				}
			}
			else if ( counter < 1231 )
			{
			}
			else if (counter == 1231 )
			{
				setBulletData(1,7,6,5,11,-1,230);
				offTargetAngle();
				interval = 3;
				shotAngleRange3 = 0;
				tmpX = 360;
			}
			else if ( counter == 1400 )
			{
				tmpX = 180;
			}
			else
			{
				// Ç«ÇÌÇ¡Ç∆
				if ( shotCheck(myX, myY) )
				{
					setShotAngle(shotAngle + 9);
					t = shotAngle;
					setShotAngle(tmpX - t);
					shot(myX, myY);
					shotAngle = t;
				}
				
				if ( counter % 20 == 0 )
				{
					setBulletData(0,2,4,4,2,-1,-1);
					t = shotAngle;
					onRandom();
					updateShotAngle(myX, myY);
					offRandom();
					shot(myX, myY);
					setBulletData(1,7,6,5,11,-1,-1);
					shotAngle = t;
				}
			}
			break;
		case 41:
			// Ç‹Ç¡Ç∑ÇÆâEorç∂Ç÷
			if ( reverse )
			{
				fixX -= fixSpeed;
			}
			else
			{
				fixX += fixSpeed;
			}
			
			if ( shotCheck(myX, myY) )
			{
				if ( reverse )
				{
					setShotAngle(shotAngle + shotAngleRange);
				}
				else
				{
					setShotAngle(shotAngle - shotAngleRange);
				}
			}

			break;
		}
	}

	public void shot(int myX, int myY)
	{
		int tmp;
		int r;
		switch ( shotId )
		{
		case 0: //ë_Ç¢way
			if ( targetAngle )
			{
				updateShotAngle(myX, myY);
			}
			
			if ( shotAngle >= 0 )
			{
				shotNWay(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange3), getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange2), shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			}
			break;
		case 1:
			// ëSï˚å¸
			if ( targetAngle )
			{
				updateShotAngle(myX, myY);
			}
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3), shotAngle, shotSpeed, shotCount, shotImageId);
			break;
			
		case 2:
			// ëSï˚å¸
			if ( targetAngle )
			{
				updateShotAngle(myX, myY);
			}
			else
			{
				shotAngle += 10;
			}
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + shotAngleRange3, shotAngle, shotSpeed, shotCount, shotImageId);
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + shotAngleRange3, shotAngle + shotAngleRange, shotSpeed, shotCount, shotImageId);
			break;
		case 3:
			// ëSï˚å¸
			if ( targetAngle )
			{
				updateShotAngle(myX, myY);
			}
			else
			{
				shotAngle += 10;
			}

			shotAngle += 4;
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + shotAngleRange3, shotAngle, shotSpeed, shotCount, shotImageId);
			shotAngle += 4;
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + shotAngleRange3, shotAngle, shotSpeed, shotCount, shotImageId);
			shotAngle += 4;
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + shotAngleRange3, shotAngle, shotSpeed, shotCount, shotImageId);
			shotAngle += 4;
			shotAllDirection(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange2), getCenterFixY() + shotAngleRange3, shotAngle, shotSpeed, shotCount, shotImageId);
			break;

		case 4:
			// óºë§Ç÷
			shotNWay(getCenterFixX(), getCenterFixY(), shotAngle - shotAngleRange2, shotSpeed, shotCount, shotAngleRange, shotImageId);
			shotNWay(getCenterFixX(), getCenterFixY(), shotAngle + shotAngleRange2, shotSpeed, shotCount, shotAngleRange, shotImageId);
			break;
		case 6:
			// ÇÆÇÈÇÆÇÈíe
			shotNWay(getCenterFixX(), getCenterFixY(), shotAngle, shotSpeed, 1, 1, shotImageId);
			shotAngle += shotAngleRange;
			break;
		case 7:
			// ägéUíe
			updateShotAngle(myX, myY);
			if ( shotAngle >= 0 )
			{
				for ( int i = 2; i < 6; i++ )
				{
					shotNWay(getCenterFixX(), getCenterFixY(), shotAngle, i, 7, 10, shotImageId);
				}
			}
			break;
		case 8:
			// É_ÉuÉãÇÆÇÈÇÆÇÈíe
			shotNWay(getCenterFixX() - FixedPoint.toFixedInt(shotAngleRange), getCenterFixY()+tmpY, shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			shotNWay(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange), getCenterFixY()+tmpY, 360 - shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			shotAngle += 35;
			break;
		case 9:
			if ( targetAngle )
			{
				updateShotAngle(myX, myY, getCenterFixX(), getCenterFixY());
			}
			// 3men maruino
			shotAllDirection(getCenterFixX(), getCenterFixY(), shotAngle, shotSpeed, shotCount, shotImageId);
			shotAllDirection(getCenterFixX(), getCenterFixY(), shotAngle + shotAngleRange, shotSpeed + FixedPoint.toFixedInt(shotAngleRange2), shotCount, shotImageId);

			break;
		case 10:
			// óºë§Ç©ÇÁé©ã@ë_Ç¢
			r = FixedPoint.toFixedInt(shotAngleRange2);

			if ( targetAngle )
			{
				updateShotAngle(myX, myY, getCenterFixX() - r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3));
			}
			
			shotNWay(getCenterFixX() - r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3), shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);

			if ( targetAngle )
			{
				updateShotAngle(myX, myY, getCenterFixX() + r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3));
			}
			shotNWay(getCenterFixX() + r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3), shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			break;
		case 11:
			// óºë§Ç©ÇÁ
			r = FixedPoint.toFixedInt(shotAngleRange2);

			shotNWay(getCenterFixX() - r, getCenterFixY() + tmpY, shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			shotNWay(getCenterFixX() + r, getCenterFixY() + tmpY, 360 - shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			break;
		case 12: //ë_Ç¢ÉâÉìÉ_ÉÄway
			if ( targetAngle )
			{
				updateShotAngle(myX, myY);
				setShotAngle(shotAngle + ((FixRandom.next() - 5) * 3));
			}
			
			if ( shotAngle >= 0 )
			{
				shotNWay(getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange3), getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange2), shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			}
			break;
		case 13:
			// óºë§Ç©ÇÁ
			r = FixedPoint.toFixedInt(shotAngleRange2);

			shotNWay(getCenterFixX() - r, getCenterFixY() + tmpY, shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			shotNWay(getCenterFixX() + r, getCenterFixY() + tmpY, 180 - shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			break;
		case 14:
			// é©ã@ê^â∫
			shotNWay(myX, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange2), shotAngle, shotSpeed, shotCount, shotAngleRange, shotImageId);
			break;
		case 15:
			// ê^â°
			tmp = shotCount * (FixedPoint.toFixedInt(shotAngleRange));
			r = (getCenterFixX() + FixedPoint.toFixedInt(shotAngleRange3)) - (tmp / 2);
			tmp = (FixedPoint.toFixedInt(shotAngleRange));
			r += (tmp / 2);
			for ( int i = 0; i < shotCount; i++ )
			{
				if ( r >= 0 )
				{
					shotNWay(r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange2), shotAngle, shotSpeed, 1, shotAngleRange, shotImageId);
					r += tmp;
				}
			}
			break;
		case 16:
			// óºë§Ç©ÇÁé©ã@ë_Ç¢
			r = FixedPoint.toFixedInt(shotAngleRange2);

			if ( targetAngle )
			{
				tmpX = updateShotAngle(myX, myY, getCenterFixX() - r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3));
				tmpY = updateShotAngle(myX, myY, getCenterFixX() + r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3));
			}
			
			shotNWay(getCenterFixX() - r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3), tmpX, shotSpeed, shotCount, shotAngleRange, shotImageId);
			shotNWay(getCenterFixX() + r, getCenterFixY() + FixedPoint.toFixedInt(shotAngleRange3), tmpY, shotSpeed, shotCount, shotAngleRange, shotImageId);
			break;
		}
	}

	private void yuragi()
	{
		// Ç‰ÇÁÇ¨
		if ( yuragiFlag )
		{
			fixY -= 64;
			yuragi++;
			if ( yuragi > 10 )
			{
				yuragiFlag = false;
				yuragi = 0;
			}
		}
		else
		{
			fixY += 64;
			yuragi++;
			if ( yuragi > 10 )
			{
				yuragiFlag = true;
				yuragi = 0;
			}
		}
	}
}
