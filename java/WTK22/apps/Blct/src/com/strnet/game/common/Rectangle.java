/*
 * Last modified: 2008/09/05 11:30:59
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class Rectangle extends Point
{
	public int width;
	public int height;
	
	public Rectangle(Rectangle r)
	{
		this(r.x, r.y, r.width, r.height);
	}
	
	public Rectangle(int x, int y, int width, int height)
	{
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public boolean isHit(Rectangle item)
	{
		return (x + width >= item.x) &&
			(x <= (item.x + item.width)) &&
			(y + height >= item.y) &&
			(y <= (item.y + item.height));
	}
	
	public boolean isHitLeftTop(Rectangle item)
	{
		return (x >= item.x) &&
			(x <= (item.x + item.width)) &&
			(y >= item.y) &&
			(y <= (item.y + item.height));
	}
	
	public String toString()
	{
		return x + "," +
				y + "," +
				width + "," +
				height;
	}
}
