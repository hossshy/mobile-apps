/*
 * Last modified: 2009/01/01 21:09:42
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class Cell
{
	int id = 0;
	boolean checked = false; // 判定済みフラグ
	boolean clear = false; // 消去フラグ

	static final int[] DOWN_MOVE = new int[]{0,21,19,15,8,0};
	//static final int[] DOWN_MOVE = new int[]{0,20,16,11,0};
	static final int[] DOWN_MOVE2 = new int[]{11,10,11,0};
	private static final int MAX_CLEAR = 5;
	private static final int MOVE_NO = 0;
	private static final int MOVE_DOWN = 2;
	private int[] moveArr = DOWN_MOVE;
	private int moveCount = 0;
	private int moveMode = MOVE_NO;
	private int clearCount = 0;

	public void reset()
	{
		resetState();
		resetMove();
	}
	
	public void resetState()
	{
		checked = false;
		clear = false;
		clearCount = 0;
	}
	
	public void resetMove()
	{
		moveCount = 0;
		moveMode = MOVE_NO;
		moveArr = DOWN_MOVE;
	}
	
	public void clear()
	{
		clear = true;
	}
	
	public boolean isClear()
	{
		return clear;
	}
	
	public int getClearCount()
	{
		return clearCount;
	}
	
	public boolean clearAnimation()
	{
		if ( !clear )
		{
			return false;
		}
		if ( clearCount > MAX_CLEAR )
		{
			this.id = 0;
			return false;
		}
		clearCount++;
		return true;
	}

	public void down(int mc)
	{
		moveMode = MOVE_DOWN;
		if ( mc > 0 )
		{
			moveArr = DOWN_MOVE2;
		}
		this.moveCount = 0;
		execAnimation();
	}
	
	int getMoveCount()
	{
		return moveCount;
	}
	
	public int getMoveY()
	{
		return moveArr[moveCount];
	}
	
	public boolean execAnimation()
	{
		// 移動中か
		if ( moveMode == MOVE_NO )
		{
			return false;
		}

		moveCount++;
		if ( moveCount >= moveArr.length )
		{
			moveCount = moveArr.length - 1;
			moveMode = MOVE_NO;
			return false;
		}
		return true;
	}
}