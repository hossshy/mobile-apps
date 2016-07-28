/*
 * Last modified: 2010/03/23 22:51:49
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class Color
{
	public static final Color BLACK = new Color(0,0,0);
	public static final Color WHITE = new Color(255,255,255);
	public static final Color LIGHT_GRAY = new Color(189,189,189);
	public static final Color GRAY = new Color(127,127,127);
	public static final Color DARK_GRAY = new Color(63,63,63);
	public static final Color BLUE = new Color(0,0,255);
	public static final Color GREEN = new Color(0,255,0);
	public static final Color RED = new Color(255,0,0);
	
	public int r;
	public int g;
	public int b;
	
	public Color(int r, int g, int b)
	{
		set(r,g,b);
	}
	
	public void set(Color c)
	{
		set(c.r, c.g, c.b);
	}
	
	public void set(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		
		revise();
	}
	
	public void sub(Color other)
	{
		this.r -= other.r;
		this.g -= other.g;
		this.b -= other.b;
		revise();
	}
	
	public void add(Color other)
	{
		r += other.r;
		g += other.g;
		b += other.b;
		revise();
	}
	
	public void revise()
	{
		r = getRevise(r);
		g = getRevise(g);
		b = getRevise(b);
	}
	
	private int getRevise(int i)
	{
		if ( i < 0 )
		{
			return 0;
		}
		else if ( i > 255 )
		{
			return 255;
		}
		else
		{
			return i;
		}
	}
}
