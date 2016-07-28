/*
 * Last modified: 2009/01/18 15:40:02
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class TitleBlock
{
	int speed;
	int x;
	int y;
	int blockId;
	int skinId;
	
	public void set(int speed, int x, int y, int blockId, int skinId)
	{
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.blockId = blockId;
		this.skinId = skinId;
	}

	public boolean downPaint(MainCanvas g)
	{
		y += speed;
		if ( y > 240 )
		{
			return false;
		}
		else
		{
			g.drawImage(skinId, blockId * g.BLOCK_WIDTH, 0, x,y, g.BLOCK_WIDTH, g.BLOCK_HEIGHT);
			return true;
		}
	}
}