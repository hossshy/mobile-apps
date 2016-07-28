/*
 * Last modified: 2008/07/05 21:56:46
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class MapChip
{
	private int id;
	private boolean move;
	
	public MapChip(int id, boolean move)
	{
		this.id = id;
		this.move = move;
	}
	
	public int getId()
	{
		return id;
	}
	
	public boolean isMove()
	{
		return move;
	}
}
