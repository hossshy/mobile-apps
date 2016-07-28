/*
 * Last modified: 2010/03/23 22:49:35
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.main.GameCommonCanvas;

public abstract class StateObject
{
	protected String msg;
	protected int x;
	protected int y;
	protected Color color;
	
	public StateObject()
	{
		color = new Color(0,0,0);
	}
	
	public abstract void set(String msg, int x, int y, Color color);
	public abstract int paint(GameCommonCanvas g);

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Color getColor()
	{
		return color;
	}
}