/*
 * Last modified: 2009/01/23 10:52:41
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class ManualManager
{
	private int[] imageNos;
	private int page = 0;
	
	public ManualManager(int[] imageNos)
	{
		this.imageNos = imageNos;
	}

	public void reset()
	{
		page = 0;
	}
	
	public void load(MainCanvas g) throws Exception
	{
		for ( int i = 0; i < g.getMaxImage(); i++ )
		{
			g.releaseImage(i);
		}
		
		for ( int i = 0; i < imageNos.length; i++ )
		{
			g.loadImage(imageNos[i]);
		}
	}
	
	public void execManual(GameCommonCanvas g) throws Exception
	{
		if ( (g.getKeyEvent() == g.S_KEY_RIGHT) ||
			 (g.getKeyEvent() == g.S_KEY_FIRE) )
		{
			page++;
			if ( page >= imageNos.length )
			{
				page = imageNos.length - 1;
			}
			
			g.keyReset();
		}
		if ( g.getKeyEvent() == g.S_KEY_LEFT )
		{
			page--;
			if ( page < 0 )
			{
				page = 0;
			}
			
			g.keyReset();
		}
		else if ( g.getKeyEvent() == g.S_SOFT_KEY )
		{
			g.execCommandAction(g.getSoftKeyNo());
			g.keyReset();
		}
		
		doPaintManual(g);
	}
	
	public void doPaintManual(GameCommonCanvas g)
	{
		g.drawImage(imageNos[page], 0,0, 0,0, 240,240);
	}
}