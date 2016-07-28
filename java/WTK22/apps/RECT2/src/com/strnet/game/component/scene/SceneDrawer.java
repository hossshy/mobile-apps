/*
 * Last modified: 2009/06/17 01:38:18
 * @author Kazamai, Kou
 */
package com.strnet.game.component.scene;

import com.strnet.game.main.AbstractCanvas;

public abstract class SceneDrawer
{
	private int speed;
	private int loadCount;
	private int endCount;

	public SceneDrawer(int pattern)
	{
		this.speed = (pattern/2)*2+1;
		this.loadCount = speed + 1;
		this.endCount = speed * 2 + 1;
	}
	
	protected int getSpeed()
	{
		return speed;
	}

	public int getLoadCount()
	{
		return loadCount;
	}

	public int getEndCount()
	{
		return endCount;
	}
	
	public abstract void paint(AbstractCanvas g, int count);
}
