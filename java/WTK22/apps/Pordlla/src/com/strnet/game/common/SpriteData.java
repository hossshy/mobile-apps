/*
 * Last modified: 2008/08/31 00:15:07
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public abstract class SpriteData extends Point
{
	public int imageId = 0;
	protected int imagePattern = 0;

	public SpriteData()	{}

	public SpriteData(int x, int y)
	{
		super(x, y);
	}
	
	public SpriteData(SpriteData cd)
	{
		super(cd);
		this.imageId = cd.imageId;
		this.imagePattern = cd.imagePattern;
	}

	public abstract int getImagePattern();
}
