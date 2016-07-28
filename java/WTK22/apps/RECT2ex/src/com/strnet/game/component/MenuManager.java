/*
 * Last modified: 2010/03/12 18:42:22
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.list.IntLightList;
import com.strnet.game.main.AbstractCanvas;

public class MenuManager extends MenuWindow
{
	private String[] labels;
	private IntLightList hideList;
	
	public MenuManager(String[] labels, boolean endless)
	{
		super(1, labels.length, endless);
		this.labels = labels;
		hideList = new IntLightList();
	}

	public void setShow(int id, boolean b)
	{
		if ( b )
		{
			offHide(id);
		}
		else
		{
			onHide(id);
		}
	}
	
	public void onHide(int id)
	{
		if ( !isHide(id) )
		{
			hideList.add(id);
			if ( id == getId() )
			{
				down();
			}
		}
	}
	
	public void offHide(int id)
	{
		hideList.remove(id);
	}
	
	public boolean isHide(int id)
	{
		return hideList.contains(id);
	}
	
	public String getLabel(int id)
	{
		return labels[id];
	}
	
	public int getLabelLength()
	{
		return labels.length;
	}
	
	public boolean move(AbstractCanvas g)
	{
		boolean ret = super.move(g);
		if ( ret )
		{
			if ( hideList.contains(getId()) )
			{
				move(g);
			}
		}
		return ret;
	}
}