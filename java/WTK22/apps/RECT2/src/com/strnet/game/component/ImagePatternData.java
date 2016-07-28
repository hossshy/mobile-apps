/*
 * Last modified: 2009/08/11 17:28:12
 * author: Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Rectangle;
import com.strnet.game.main.AbstractCanvas;

public class ImagePatternData
{
	private int imageId;
	private Rectangle[] rects;
	
	public ImagePatternData(int imageId, Rectangle[] rects)
	{
		this.imageId = imageId;
		this.rects = rects;
	}

	public int getImageId()
	{
		return imageId;
	}

	public Rectangle getRectangle(int pattern)
	{
		return rects[pattern];
	}

	public int size()
	{
		return rects.length;
	}
}