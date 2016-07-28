/*
 * Last modified: 2008/09/05 19:13:48
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class Color
{
	public int r;
	public int g;
	public int b;
	
	public Color(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		
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
