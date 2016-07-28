/*
 * Last modified: 2009/02/02 22:08:35
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class Menu
{
	private int maxRow;
	private int maxCol;
	private boolean endless;
	private int row;
	private int col;
	
	public Menu(int maxCol, int maxRow, boolean endless)
	{
		this.maxCol = maxCol;
		this.maxRow = maxRow;
		this.endless = endless;
		resetId();
	}
	
	public void resetId()
	{
		setId(0, 0);
	}
	
	public void setId(int row, int col)
	{
		this.col = col;
		this.row = row;
	}
	
	public int getId()
	{
		return (maxCol * row) + col;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void up()
	{
		if ( row <= 0 )
		{
			if ( endless )
			{
				row = maxRow - 1;
			}
		}
		else
		{
			row--;
		}
	}
	
	public void left()
	{
		if ( col <= 0 )
		{
			if ( endless )
			{
				col = maxCol - 1;
			}
		}
		else
		{
			col--;
		}
	}
	
	public void down()
	{
		if ( row >= (maxRow - 1) )
		{
			if ( endless )
			{
				row = 0;
			}
		}
		else
		{
			row++;
		}
	}
	
	public void right()
	{
		if ( col >= (maxCol - 1) )
		{
			if ( endless )
			{
				col = 0;
			}
		}
		else
		{
			col++;
		}
	}
}
