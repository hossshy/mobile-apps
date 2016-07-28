/*
 * Last modified: 2010/03/02 15:17:05
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class FighterInformation
{
	private int fighterId;
	private int mode;
	private int availableStage;
	private int highScore;
	private boolean cleared;
	private boolean noTrance;
	private boolean trueBoss;
	private int rank;
	
	public FighterInformation(int fighterId, int mode)
	{
		this.fighterId = fighterId;
		this.mode = mode;
		setHighScore(0);
		setCleared(false);
		setNoTrance(false);
		availableStage = 1;
		setTrueBoss(false);
		setRank(0);
	}

	public void setRank(int rank)
	{
		if ( this.rank < rank )
		{
			this.rank = rank;
		}
	}

	public int getRank()
	{
		return rank;
	}

	public void setTrueBoss(boolean trueBoss)
	{
		this.trueBoss |= trueBoss;
	}
	
	public boolean hasTrueBoss()
	{
		return trueBoss;
	}

	public int getAvailableStage()
	{
		return availableStage;
	}

	public void setAvailableStage(int availableStage)
	{
		if ( this.availableStage < availableStage )
		{
			this.availableStage = availableStage;
		}
	}

	public int getFighterId()
	{
		return fighterId;
	}

	public int getMode()
	{
		return mode;
	}

	public int getHighScore()
	{
		return highScore;
	}

	public void setHighScore(int highScore)
	{
		if ( this.highScore < highScore )
		{
			this.highScore = highScore;
		}
	}

	public boolean isCleared()
	{
		return cleared;
	}

	public void setCleared(boolean cleared)
	{
		this.cleared |= cleared;
	}
	
	public boolean isNoTrance()
	{
		return noTrance;
	}
	
	public void setNoTrance(boolean noTrance)
	{
		this.noTrance |= noTrance;
	}
}
