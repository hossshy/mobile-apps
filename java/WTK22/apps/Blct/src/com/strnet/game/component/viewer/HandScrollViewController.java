/*
 * Last modified: 2010/03/23 22:41:38
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.main.GameCommonCanvas;
import com.strnet.game.common.list.StringLightList;

public class HandScrollViewController extends CommonViewController
{
	protected static final int SCROLL_HEIGHT = 8;

	public boolean canUp()
	{
		return sv[0].y < 40;
	}
	public boolean canDown()
	{
		return sv[sv.length - 1].y > 220;
	}

	public void move(GameCommonCanvas g)
	{
		if ( g.isUp() && canUp() )
		{
			for ( int i = 0; i < sv.length; i++ )
			{
				sv[i].y += SCROLL_HEIGHT;
			}
		}
		else if ( g.isDown() && canDown() )
		{
			for ( int i = 0; i < sv.length; i++ )
			{
				sv[i].y -= SCROLL_HEIGHT;
			}
		}
	}
	
	public boolean paintAll(GameCommonCanvas g)
	{
		for ( int i = 0; i < sv.length; i++ )
		{
			if ( sv[i] != null )
			{
				sv[i].color.set(255,255,255);
				sv[i].paint(g);
			}
		}
		
		if ( canUp() )
		{
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("£", 10, 40);
		}
		if ( canDown() )
		{
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("¥", 10, 220);
		}
		return true;
	}
}