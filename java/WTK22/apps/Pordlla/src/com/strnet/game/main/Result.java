/*
 * Last modified: 2009/02/21 21:29:09
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class Result
{
	private int clearScore;
	private int specialScore;
	private int comboScore;
	private int combo;
	
	public Result()
	{
		reset();
		resetCombo();
	}
	
	public void reset()
	{
		clearScore = 0;
		specialScore = 0;
		comboScore = 0;
	}
	
	public void resetCombo()
	{
		combo = 0;
		comboScore = 0;
	}
	
	public int getSpecialScore()
	{
		specialScore = 100;
		return specialScore;
	}
	
	public int gotSpecialScore()
	{
		return specialScore;
	}
	
	public int incfCombo()
	{
		combo++;
		return comboScore = combo* 100;
	}
	
	public int getCombo()
	{
		return combo;
	}
	
	public int getComboScore()
	{
		return comboScore;
	}
	
	public int getClearScore()
	{
		return clearScore;
	}
	
	public int getClearScore(int stage)
	{
		if ( 0 <= stage && stage < 20 )
		{
			clearScore = 100;//2000
		}
		if ( 20 <= stage && stage < 30 )
		{
			clearScore = 200;//2000
		}//4000
		else if ( 30 <= stage && stage < 50 )
		{
			clearScore = 500;//10000
		}//14000
		else if ( 50 <= stage && stage < 70 )
		{
			clearScore = 1000;//20000
		}//34000
		else if ( 70 <= stage && stage < 90 )
		{
			clearScore = 2000;//40000
		}//74000
		else if ( 90 <= stage && stage < 100 )
		{
			clearScore = 3000;//30000
		}//104000
		return clearScore;
	}
	
}