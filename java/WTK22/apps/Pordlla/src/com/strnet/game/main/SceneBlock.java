/*
 * Last modified: 2009/01/12 23:19:44
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class SceneBlock
{
	static final int[] DOWN_MOVE = new int[]{1,2,4,8,16,30,60};
	int keisuu = 0;
	int row;
	int y = 0;
	
	public SceneBlock(int row)
	{
		this.row = row;
		reset();
	}
	
	public void reset()
	{
		keisuu = 0;
		y = row * 30;
	}

	public void rakka()
	{
		if ( keisuu >= DOWN_MOVE.length )
		{
			keisuu = DOWN_MOVE.length - 1;
		}
		y += DOWN_MOVE[keisuu++];
	}
}