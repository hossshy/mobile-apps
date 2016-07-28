/*
 * Last modified: 2009/07/08 15:59:21
 * @author Kazamai, Kou
 */
package com.strnet.game.component.scene;

import com.strnet.game.main.AbstractCanvas;

public class PordllaSceneDrawer extends SceneDrawer
{
	public PordllaSceneDrawer(int pattern)
	{
		super(pattern);
	}
	
	public void paint(AbstractCanvas g, int count)
	{
		g.setColor(0,0,0);
		if ( count == getSpeed() )
		{
			g.resetScreen();
		}
		else
		{
			if ( count > (getLoadCount()) )
			{
				count = getEndCount() - count;
			}

			if ( count > 0 )
			{
				int height = 240 / (getSpeed()*2);
				g.fillRect(0,0, 240,height*count);
				g.fillRect(0,240 - (height*count), 240,height*count);
				g.setColor(255,0,0);
			}
		}
	}
}
