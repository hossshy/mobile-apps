/*
 * Last modified: 2010/03/13 11:05:28
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.component.ImagePatternData;

public abstract class MyData extends CharacterData
{
	public static final int MAX_SCORE_COUNTER = 99999;
	public static final int MAX_HYPER_GAUGE = 300;
	public static final int MAX_BOMB = 3;
	
	private int myFixMaxX;
	private int myFixMaxY;
	
	protected int fastSpeed;
	protected int slowSpeed;

	protected int counter = 0;
	private int remainder = 0;
	private boolean shotFlag = true;
	private int invincibleCount = 0;
	protected boolean speedFlag = false;
	private int bombCounter = 0;
	private boolean lock = false;
	private int bomb;
	private boolean hyper = false;
	private int hyperCounter = 0;
	private int scoreCounter;
	private int hyperGauge = 0;
	private boolean noMiss;
	private boolean noTrance;
/*
	public void addHyperCounter(int count)
	{
		if ( bomb < MAX_BOMB )
		{
			hyperCounter += count;
			if ( hyperCounter >= MAX_HYPER_COUNTER )
			{
				bomb++;
				MainCanvas.getInstance().needPaintLeftField();
				hyperCounter = 0;
			}
		}
	}
	
	public void resetHyperCounter()
	{
		hyperCounter = 0;
		hyperGauge = 0;
	}
	public int getHyperCounter()
	{
		return hyperCounter;
	}
*/
	
	public int getSlowSpeed()
	{
		return slowSpeed;
	}

	public int getHyperGauge()
	{
		return hyperGauge;
	}

	public void setHyperGauge(int hyperGauge)
	{
		this.hyperGauge = hyperGauge;
	}
	
	public void resetScoreCounter()
	{
		scoreCounter = 0;
	}
	
	public void addHyperGauge(int mergeScore)
	{
		if ( !hyper && (hyperGauge < MAX_HYPER_GAUGE) )
		{
			hyperGauge += mergeScore;
			MainCanvas.getInstance().needPaintHyperGauge();
		}
	}

	public void addScoreCounter(int scoreCounter, int mergeScore)
	{
		if ( this.scoreCounter < MAX_SCORE_COUNTER )
		{
			this.scoreCounter += scoreCounter;
			if ( this.scoreCounter > MAX_SCORE_COUNTER )
			{
				this.scoreCounter = MAX_SCORE_COUNTER;
			}
			MainCanvas.getInstance().needPaintScoreCounter();
		}
		addHyperGauge(mergeScore);
	}

	public boolean isMaxScoreCounter()
	{
		return scoreCounter >= MAX_SCORE_COUNTER;
	}
	
	public int getScoreCounter()
	{
		return scoreCounter;
	}
	
	public void setBomb(int bomb)
	{
		this.bomb = bomb;
		MainCanvas.getInstance().needPaintLeftField();
	}
	
	public int getBomb()
	{
		return bomb;
	}
	
	public void addBomb()
	{
		if ( bomb < MAX_BOMB )
		{
			setBomb(bomb + 1);
			MainCanvas.getInstance().getBombCounter = 30;
		}
	}

	public void reset()
	{
		invincibleCount = 0;
		bombCounter = 0;
		hyper = false;
		//hyperGauge = 0;
		//hyperCounter = 0;
		lock = false;
		noMiss = true;
	}

	public boolean isLock()
	{
		return lock;
	}
	
	public void resetNoTrance()
	{
		noTrance = true;
	}
	
	public boolean isNoTrance()
	{
		return noTrance;
	}
	
	public void resetNoMiss()
	{
		noMiss = true;
	}
	
	public boolean isNoMiss()
	{
		return noMiss;
	}
	
	public void lock()
	{
		lock = true;
		imagePattern = 0;
	}
	
	public boolean canHyper()
	{
		return (hyperGauge >= 100);
	}
	
	public void onHyper()
	{
		if ( canHyper() )
		{
			hyper = true;
			noTrance = false;
			MainCanvas.getInstance().needPaintLeftField();
			MainCanvas.getInstance().needAllScoreItem(true);
			MainCanvas.getInstance().vibrate(700);
		}
	}

	public void offHyper()
	{
		hyper = false;
	}
	
	public boolean isHyper()
	{
		return hyper;
	}

	public void setImage(ImagePatternData image)
	{
		super.setImage(image);
		myFixMaxX = (screenFixMaxX - fixWidth);
		myFixMaxY = (screenFixMaxY - fixHeight);
	}
	
	public boolean isFastSpeed()
	{
		return speedFlag;
	}
	
	public void changeSpeed()
	{
		speedFlag = !speedFlag;
		if ( speedFlag )
		{
			setSpeed(fastSpeed);
		}
		else
		{
			setSpeed(slowSpeed);
		}
	}
	
	public void doBomb(int invincible)
	{
		if ( (bomb > 0) && (bombCounter == 0) )
		{
			bombCounter = 40;
			ObjectPool.destroyAllBullets(ObjectPool.bullets);
			invincibleCount = invincible;
			MainCanvas g = MainCanvas.getInstance();
			g.startSecondSE();
			g.needPaintLeftField();
			bomb--;
			g.vibrate(700);
		}
	}
	
	public void resetSpeed()
	{
		speedFlag = false;
		changeSpeed();
	}

	public int getRemainder()
	{
		return remainder;
	}
	
	public void onInvincible(int invincibleCount)
	{
		this.invincibleCount = invincibleCount;
	}
	
	public void onInvincible()
	{
		onInvincible(80);
	}

	public void setRemainder(int remainder)
	{
		this.remainder = remainder;
	}

	public void addRemainder()
	{
		setRemainder(remainder + 1);
		MainCanvas.getInstance().needPaintLeftField();
		MainCanvas.getInstance().get1UPCounter = 30;
	}
	
	public void hit(MainCanvas g)
	{
		if ( invincibleCount == 0 )
		{
			noMiss = false;
			addHyperGauge(50);
			//if ( g.autoBomb && bomb > 0 )
			if ( bomb > 0 )
			{
				scoreCounter = (scoreCounter / 3) * 2;
				hyper = false;
				doBomb(60);
			}
			else
			{
				destroy();
				g.vibrate(200);
				BurstData bd = Dictionary.getBurst(0);
				int sx = (fixWidth - bd.fixWidth) / 2;
				int sy = (fixHeight - bd.fixHeight) / 2;
				ObjectPool.setBurst(0, fixX + sx, fixY + sy);
				remainder--;
				scoreCounter = (scoreCounter / 5) * 4;
				hyper = false;
				if ( remainder < 0 )
				{
					StageManager stageManager = StageManager.getInstance();
					stageManager.gameover(g);
				}
				else
				{
					g.resetBomb();
					g.needPaintLeftField();
					onInvincible();
				}
			}
		}
	}

	public void appear()
	{
		bombCounter = 0;
		fixX = (screenFixMaxX - screenFixMinX) / 2 - halfFixWidth + screenFixMinX;
		fixY = screenFixMaxY;
	}
	
	public void up()
	{
		fixY -= 1024;
		imagePattern = (invincibleCount % 2 == 0) ? 0 : 3;
	}

	public void updateInvincible()
	{
		if ( hyper )
		{
			hyperGauge--;
			if ( hyperGauge <= 0 )
			{
				hyper = false;
				hyperGauge = 0;
				onInvincible(40);
			}
			MainCanvas.getInstance().needPaintHyperGauge();
		}
		
		if ( invincibleCount > 0 )
		{
			invincibleCount--;
			if ( invincibleCount == 0 )
			{
				ObjectPool.destroyAllBullets(ObjectPool.bullets, false);
			}
		}
		if ( bombCounter > 0 )
		{
			BurstData bd = Dictionary.getBurst(0);
			int sx = fixX + (fixWidth - bd.fixWidth) / 2;
			int sy = fixY + (fixHeight - bd.fixHeight) / 2;
			if ( bombCounter == 40 )
			{
				ObjectPool.setBurst(7, sx - bd.fixWidth, sy);
				ObjectPool.setBurst(7, sx + bd.fixWidth, sy);
				ObjectPool.setBurst(7, sx, sy - bd.fixHeight);
				ObjectPool.setBurst(7, sx, sy + bd.fixHeight);
			}
			if ( bombCounter == 39 ||
				 bombCounter == 37 ||
				 bombCounter == 35 )
			{
				ObjectPool.damageAll();
			}
			else if ( bombCounter == 38 )
			{
				ObjectPool.setBurst(7, sx - bd.fixWidth, sy - bd.fixHeight);
				ObjectPool.setBurst(7, sx - bd.fixWidth, sy + bd.fixHeight);
				ObjectPool.setBurst(7, sx + bd.fixWidth, sy - bd.fixHeight);
				ObjectPool.setBurst(7, sx + bd.fixWidth, sy + bd.fixHeight);
			}
			else if ( bombCounter == 36 )
			{
				ObjectPool.setBurst(7, sx - (bd.fixWidth * 2), sy);
				ObjectPool.setBurst(7, sx + (bd.fixWidth * 2), sy);
				ObjectPool.setBurst(7, sx, sy - (bd.fixHeight * 2));
				ObjectPool.setBurst(7, sx, sy + (bd.fixHeight* 2));
			}
			else if ( bombCounter == 34 )
			{
				int ax = bd.fixWidth * 2;
				int ay = bd.fixHeight * 2;
				ObjectPool.setBurst(7, sx - ax, sy - ay);
				ObjectPool.setBurst(7, sx - ax, sy + ay);
				ObjectPool.setBurst(7, sx + ax, sy - ay);
				ObjectPool.setBurst(7, sx + ax, sy + ay);
			}
			else if ( bombCounter == 32 )
			{
				ObjectPool.setBurst(7, sx - (bd.fixWidth * 3), sy);
				ObjectPool.setBurst(7, sx + (bd.fixWidth * 3), sy);
				ObjectPool.setBurst(7, sx, sy - (bd.fixHeight * 3));
				ObjectPool.setBurst(7, sx, sy + (bd.fixHeight* 3));
			}

			bombCounter--;
		}
	}
	
	public void move(MainCanvas g)
	{
		if ( lock ) 
		{
			return;
		}
		int vx = 0;
		int vy = 0;
		imagePattern = 0;
		if ( g.isUp() ) vy -= fixSpeed;
		if ( g.isDown() ) vy += fixSpeed;

		if ( g.isLeft() )
		{
			vx -= fixSpeed;
			imagePattern = 2;
		}
		else if ( g.isRight() )
		{
			vx += fixSpeed;
			imagePattern = 1;
		}

		if ( invincibleCount > 0 )
		{
			//imagePattern += (invincibleCount % 2 == 0) ? 0 : 3;
			imagePattern += (invincibleCount <= 0) ? 0 : 3;
		}
		
		if ( vx != 0 && vy != 0 )
		{
			vx = FixedPoint.multiply(vx, 181);
			vy = FixedPoint.multiply(vy, 181);
		}
		fixX += vx;
		fixY += vy;
		int hoseiX = 2000;
		int hoseiY = 3000;
		if ( fixX < screenFixMinX - hoseiX ) fixX = screenFixMinX - hoseiX;
		else if ( fixX > myFixMaxX + hoseiX ) fixX = myFixMaxX + hoseiX;
		if ( fixY < screenFixMinY - hoseiY ) fixY = screenFixMinY - hoseiY;
		else if ( fixY > myFixMaxY + hoseiY ) fixY = myFixMaxY + hoseiY;
		
		if ( g.getKeyEvent() == g.changeSpeedKey )
		{
			changeSpeed();
		}
		else if ( g.getKeyEvent() == g.bombKey )
		{
			if ( !isHyper() )
			{
				onHyper();
			}
		}
		else if ( g.getKeyEvent() == g.shotSwitchKey )
		{
			shotFlag = !shotFlag;
		}
	}
	
	public void shotCheck()
	{
		if ( lock ) 
		{
			return;
		}
		if ( shotFlag )
		{
			shot();
		}
	}

	public abstract void shot();
	public abstract String getName();
	public abstract String getFastWeaponName();
	public abstract String getSlowWeaponName();
	public abstract String getDescription();
}
