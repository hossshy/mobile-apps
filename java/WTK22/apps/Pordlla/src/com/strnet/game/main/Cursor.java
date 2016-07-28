/*
 * Last modified: 2009/02/03 01:00:20
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;
import com.strnet.game.common.GameUtil;

public class Cursor
{
	private static final int[] MOVE = new int[]{0, 10, 17};
	private static final int MOVE_NO = 0;
	private static final int MOVE_UP = 1;
	private static final int MOVE_DOWN = 2;

	private static final int[] ANIMATION_PATTERN = new int[]{1,2,3,4};
	//private static final int[] ANIMATION_PATTERN = new int[]{1,2,3,4,3,2};
	private static final int MAX_FRAME = 2;
	private int animation = 0;
	private int animationFrame = 0;

	private int x;
	private int y;
	private Rectangle imgRect;
	
	private int imageId = 1;
	private int row = 0;
	private int nextRow = -1;
	private int moveCount = 0;
	private int moveMode = MOVE_NO;
	
	public Cursor(int x, int y, Rectangle imgRect)
	{
		this.x = x;
		this.y = y;
		this.imgRect = imgRect;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public boolean up(Cell[][] map)
	{
		nextRow = Map.getUpRow(map, row);
		if ( nextRow >= 0 )
		{
			moveMode = MOVE_UP;
			execAnimation();
			return true;
		}
		return false;
	}
	
	public boolean down(Cell[][] map)
	{
		nextRow = Map.getDownRow(map, row);
		if ( nextRow >= 0 )
		{
			moveMode = MOVE_DOWN;
			execAnimation();
			return true;
		}
		return false;
	}
	
	public int getAnimation()
	{
		return ANIMATION_PATTERN[animation];
	}
	
	public boolean execAnimation()
	{
		// 移動中か
		if ( moveMode == MOVE_NO )
		{
			// アニメーション処理
			animationFrame++;
			if ( animationFrame > MAX_FRAME )
			{
				animationFrame = 0;
				animation = GameUtil.loopIncf(animation, 0, ANIMATION_PATTERN.length);
			}
			return false;
		}
		
		// 次のカーソル行が離れている場合
		if ( nextRow < (row - 1) )
		{
			row--;
		}
		else if ( nextRow > (row + 1) )
		{
			row++;
		}
		else
		{
			moveCount++;
			
			if ( moveCount >= MOVE.length )
			{
				if ( moveMode == MOVE_UP )
				{
					row--;
				}
				else if ( moveMode == MOVE_DOWN )
				{
					row++;
				}
				moveMode = 0;
				moveCount = 0;
			}
		}
		return true;
	}
	
	public void reset(Cell[][] map)
	{
		for ( int i = 0; i < map.length; i++ )
		{
			if ( Map.hasBlock(map, i) )
			{
				row = i;
				break;
			}
		}
		moveMode = MOVE_NO;
		moveCount = 0;
	}
	
	public void setSkin(int skin)
	{
		this.imageId = skin;
	}
	
	public void paint(MainCanvas g)
	{
		int paintY = y + (row * 22);
		if ( moveMode == MOVE_UP )
		{
			paintY -= MOVE[moveCount];
		}
		else if ( moveMode == MOVE_DOWN )
		{
			paintY += MOVE[moveCount];
		}
		g.drawImage(imageId, imgRect.x, imgRect.y * getAnimation(), x, paintY, imgRect.width, imgRect.height);
	}
}