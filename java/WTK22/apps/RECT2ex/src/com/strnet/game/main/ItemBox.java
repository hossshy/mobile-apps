/*
 * Last modified: 2009/05/21 01:00:14
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.common.SaveDataCreator;
import java.util.Vector;

public class ItemBox
{
	static int MAX_VIEW = 5;
	
	Vector items = new Vector();
	int viewStart = 0;
	
	public void add(ItemData d)
	{
		if ( !items.contains(d) )
		{
			items.addElement(d);
		}
	}
	
	public void remove(ItemData d)
	{
		items.removeElement(d);
		if ( items.size() == 0 )
		{
			viewStart = 0;
		}
		else if ( viewStart >= items.size() )
		{
			viewStart = GameUtil.loopDecf(viewStart, 0, items.size() - 1);
		}
	}
	
	public boolean isOverItem()
	{
		return items.size() > MAX_VIEW;
	}
	
	public void left()
	{
		if ( isOverItem() )
		{
			viewStart = GameUtil.loopDecf(viewStart, 0, items.size() - 1);
			updateViewItems();
		}
	}
	
	public void right()
	{
		if ( isOverItem() )
		{
			viewStart = GameUtil.loopIncf(viewStart, 0, items.size() - 1);
			updateViewItems();
		}
	}
	
	public void updateViewItems()
	{
		for ( int i = 0; i < items.size(); i++ )
		{
			ItemData d = (ItemData) items.elementAt(i);
			d.state = ItemData.STATE_HIDE;
		}
		
		int st = viewStart;
		for ( int i = 0; i < Math.min(items.size(), MAX_VIEW); i++ )
		{
			ItemData d = (ItemData) items.elementAt(st);
			d.state = ItemData.STATE_SHOW;
			d.x = 63 + (24 * i);
			d.y = 28;
			st++;
			if ( st >= items.size() )
			{
				st = 0;
			}
		}
	}
	
	public String toString()
	{
		SaveDataCreator sdc = new SaveDataCreator();

		sdc.add(viewStart);

		for ( int i = 0; i < items.size(); i++ )
		{
			ItemData d = (ItemData) items.elementAt(i);
			sdc.add(d.id);
		}
		return sdc.toString();
	}
}