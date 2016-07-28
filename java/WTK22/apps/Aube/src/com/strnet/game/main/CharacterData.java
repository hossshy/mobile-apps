/*
 * Last modified: 2008/07/05 21:57:53
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Counter;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class CharacterData extends Point
{
	boolean moved = false;
	char movingDir = 'D';
	int hosuu = 0;
	int chipWidth = MainCanvas.CHIP_WIDTH;
	int speed = 13;
	int imageId;
	int power;
	int frame = 0;
	int imageFrame = 0;
	int maxImageFrame = 0;
	int imagePattern = 0;
	int imageSpeed = 0;
	

	public CharacterData()
	{
	}

	public CharacterData(int x, int y)
	{
		super(x, y);
	}
	
	public CharacterData(CharacterData cd)
	{
		super(cd);
		this.speed = cd.speed;
		this.imageId = cd.imageId;
		this.maxImageFrame = cd.maxImageFrame;
		this.imageSpeed = cd.imageSpeed;
	}

	public int getImagePattern()
	{
		int row = 0;
		switch ( movingDir )
		{
		case 'D':
			row = 0;
			break;
		case 'U':
			row = 10;
			break;
		case 'L':
			row = 20;
			break;
		case 'R':
			row = 30;
			break;
		}
		
		if ( moved )
		{
			updateImageFrame();
		}
	
		return row + imageFrame;
	}

	protected void updateImageFrame()
	{
		if ( (imageSpeed <= 0) || Counter.count % imageSpeed == 0 )
		{
			imageFrame++;
			if ( imageFrame >= maxImageFrame )
			{
				imageFrame = 0;
			}
		}
	}

	public Point getPosition()
	{
		Point p = new Point(x * chipWidth, y * chipWidth);
		if ( hosuu > 0 )
		{
			switch ( movingDir )
			{
			case 'L':
				p.x -= hosuu;
				break;
			case 'R':
				p.x += hosuu;
				break;
			case 'U':
				p.y -= hosuu;
				break;
			case 'D':
				p.y += hosuu;
				break;
			}
		}
		return p;
	}
}
