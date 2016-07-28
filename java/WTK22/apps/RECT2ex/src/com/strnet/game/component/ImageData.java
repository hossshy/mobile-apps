/*
 * Last modified: 2009/08/11 16:46:29
 * author: Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Rectangle;
import com.strnet.game.main.AbstractCanvas;

public class ImageData
{
	private int imageId;
	private Rectangle rect;
	
	public ImageData(int imageId, Rectangle rect)
	{
		this.imageId = imageId;
		this.rect = rect;
	}

	public int getImageId()
	{
		return imageId;
	}

	public Rectangle getRectangle()
	{
		return rect;
	}

	public void paint(AbstractCanvas g, int x, int y)
	{
		g.drawImage(imageId, rect.x, rect.y, x, y, rect.width, rect.height);
	}
}