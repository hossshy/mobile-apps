/*
 * Last modified: 2009/09/16 21:58:26
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;

public class HPGauge
{
	private EnemyData enemy;
	private int imageId;
	private int x;
	private int y;
	private Rectangle border;
	private Rectangle gauge;
	private int gaugeX;
	private int gaugeY;
	private int fixKeisuu;

	public HPGauge(int x, int y, Rectangle border, Rectangle gauge, int imageId)
	{
		this.x = x;
		this.y = y;
		this.border = border;
		this.gauge = gauge;
		this.imageId = imageId;
		gaugeX = (border.width - gauge.width) / 2 + x;
		gaugeY = (border.height - gauge.height) / 2 + y;
	}
	
	public void reset()
	{
		this.enemy = null;
		MainCanvas.getInstance().preLoadImage(imageId);
	}
	
	public boolean isDie()
	{
		return (enemy != null) && !enemy.isAlive();
	}

	public void setEnemy(EnemyData enemy)
	{
		this.enemy = enemy;
		fixKeisuu = FixedPoint.divide(FixedPoint.toFixedInt(enemy.getMaxHp()),
									  FixedPoint.toFixedInt(gauge.width));
	}

	public void paint(MainCanvas g)
	{
		if ( (enemy != null) && (enemy.isAlive()) )
		{
			g.drawImage(imageId,
						border.x, border.y,
						x, y, 
						border.width, border.height);

			int hp = enemy.getHp();
			if ( hp > 0 )
			{
				int w = FixedPoint.toInt(FixedPoint.divide(FixedPoint.toFixedInt(hp), fixKeisuu));
				g.drawImage(imageId,
							gauge.x, gauge.y,
							gaugeX, gaugeY, 
							w, gauge.height);
			}
		}
	}
}
