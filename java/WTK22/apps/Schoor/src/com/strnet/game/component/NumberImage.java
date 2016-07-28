/*
 * Last modified: 2008/07/05 21:57:22
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.main.AbstractCanvas;

public class NumberImage
{
	private int imageNo;
	private int chipWidth;
	private int chipHeight;
	private int maxLength;
	
	public NumberImage(int imageNo, int chipWidth, int chipHeight)
	{
		this.imageNo = imageNo;
		this.chipWidth = chipWidth;
		this.chipHeight = chipHeight;
		maxLength = -1;
	}
	
	public void setMaxLength(int maxLength)
	{
		this.maxLength = maxLength;
	}
	
	public void draw(AbstractCanvas g, int number, int x, int y)
	{
		String tmp = String.valueOf(number);
		int addX = 0;
		if ( (maxLength != -1) && (maxLength > tmp.length() ))
		{
			addX = maxLength - tmp.length();
		}
		for ( int i = 0; i < tmp.length(); i++ )
		{
			int n = Integer.parseInt(tmp.substring(i, i+1));
			g.drawImage(imageNo, n * chipWidth, 0, x + (chipWidth * addX++), y, chipWidth, chipHeight);
		}
	}
}
