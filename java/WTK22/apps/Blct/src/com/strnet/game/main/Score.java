/*
 * Last modified: 2009/12/12 14:32:49
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.list.IntLightList;

public class Score
{
	private int score;
	private IntLightList addScores;

	public Score()
	{
		reset();
	}
	
	public void reset()
	{
		score = 0;
		addScores = new IntLightList(100);
	}

	public void directAdd(int score)
	{
		score += score;
	}

	public void add(int score)
	{
		addScores.add(score);
	}
	
	public boolean isNoAddScore()
	{
		return addScores.size() == 0;
	}

	public int getScore()
	{
		return score;
	}
	
	public boolean updateAll()
	{
		if ( addScores.isEmpty() )
		{
			return false;
		}
		else
		{
			for ( int i = 0; i < addScores.size(); i++ )
			{
				score += addScores.get(i);
			}
			addScores.clear();
			return true;
		}
	}

	public boolean update()
	{
		if ( addScores.isEmpty() )
		{
			return false;
		}
		else
		{
			score += addScores.get(0);
			addScores.removeByIndex(0);
			return true;
		}
	}
}
