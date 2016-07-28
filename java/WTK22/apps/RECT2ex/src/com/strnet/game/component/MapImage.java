/*
 * Last modified: 2008/07/05 21:57:09
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Point;
import com.strnet.game.main.AbstractCanvas;

public class MapImage
{
	private int imageNo;
	private int maxCol;
	private int chipWidth;
	private int chipHeight;
	
	public MapImage(int imageNo, int maxCol, int chipWidth, int chipHeight)
	{
		this.imageNo = imageNo;
		this.maxCol = maxCol;
		this.chipWidth = chipWidth;
		this.chipHeight = chipHeight;
	}
	
	public void drawCenter(AbstractCanvas g, int id)
	{
		draw(g, id, new Point(120 - (chipWidth / 2), 120 - (chipHeight / 2)));
	}	
	
	public void draw(AbstractCanvas g, int id, Point p)
	{
		int row = 0;
		int amari = 0;
		
		if ( id > 0 )
		{
			row = (id / maxCol) * chipHeight;
			amari = (id % maxCol) * chipWidth;
		}
		g.drawImage(imageNo, amari, row, p.x, p.y, chipWidth, chipHeight);
	}
}
