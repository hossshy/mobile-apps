/*
 * Last modified: 2008/07/05 21:56:58
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class Point
{
	public int x;
	public int y;
	
	public Point()
	{
		this(0, 0);
	}
	
	public Point(Point p)
	{
		this(p.x, p.y);
	}
	
	public Point(int x, int y)
	{
		setPosition(x, y);
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
