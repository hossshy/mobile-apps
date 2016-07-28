/*
 * Last modified: 2009/02/04 01:57:00
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;

public class StageSelectList
{
	private int min;
	private int max;
	private int view;
	private int start;
	private int cursor;
	
	public StageSelectList(int min, int max, int view)
	{
		this.min = min;
		this.max = max;
		this.view = view;
		start = 0;
		cursor = 0;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public void setStart(int start)
	{
		this.start = start;
		cursor = 0;
	}
	
	public void down()
	{
		if ( cursor >= (view - 1) )
		{
			start = GameUtil.loopIncf(start, min, max);
		}
		else
		{
			cursor++;
		}
	}

	public int getCursor()
	{
		return cursor;
	}

	public int getId()
	{
		int id = start;
		for ( int i = 0; i < cursor; i++ )
		{
			id = GameUtil.loopIncf(id, min, max);
		}
		return id;
	}
	
	public boolean move(AbstractCanvas g)
	{
		int tmpId = getId();
		switch ( g.getKeyEvent() )
		{
		case AbstractCanvas.S_KEY_UP:
			if ( cursor <= 0 )
			{
				start = GameUtil.loopDecf(start, min, max);
			}
			else
			{
				cursor--;
			}
			break;
		case AbstractCanvas.S_KEY_DOWN:
			down();
			break;
		case AbstractCanvas.S_KEY_LEFT:
			for ( int i = 0; i < view; i++ )
			{
				start = GameUtil.loopDecf(start, min, max);
			}
			break;
		case AbstractCanvas.S_KEY_RIGHT:
			for ( int i = 0; i < view; i++ )
			{
				start = GameUtil.loopIncf(start, min, max);
			}
			break;
		}
		
		return getId() != tmpId;
	}
}