/*
 * Last modified: 2008/07/05 21:58:15
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Counter;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;

public class ItemData extends CharacterData
{
	public ItemData()
	{
	}

	public ItemData(int x, int y)
	{
		super(x, y);
	}

	public ItemData(ItemData ed)
	{
		super(ed);
	}
	
	public ItemData deepCopy()
	{
		return new ItemData(this);
	}
	
	public int getImagePattern()
	{
		if ( (imageSpeed <= 0) || Counter.count % imageSpeed == 0 )
		{
			imageFrame++;
			if ( imageFrame >= maxImageFrame )
			{
				imageFrame = 0;
			}
		}
		
		return imageFrame;
	}
}
