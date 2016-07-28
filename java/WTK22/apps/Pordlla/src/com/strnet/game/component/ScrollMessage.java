/*
 * Last modified: 2008/07/05 21:57:29
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.main.AbstractCanvas;

public class ScrollMessage extends Message
{
	private int low;
	private int count;
	
	public ScrollMessage(String m, int x, int y)
	{
		super(m, x, y);
		count = 0;
		low = 0;
	}
	
	public void nextLow()
	{
		low++;
		count = 0;
	}

	public void setMsg(String m)
	{
		super.setMsg(m);
		count = 0;
		low = 0;
	}

	public boolean isNextLow()
	{
		return (low < msg.length - 1);
	}
	
	public boolean isNextCount()
	{
		return count < msg[low].length();
	}
	
	public boolean nextCount()
	{
		if ( isNextCount() )
		{
			count++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void paint(AbstractCanvas g)
	{
		if ( msg != null )
		{
			nextCount();
			/*
			if ( background != null )
			{
				background.paint(g, x, y);
			}
			*/

			for ( int i = 0; i < low; i++ )
			{
				g.drawString(msg[i], x, y + ((g.fontHeight + rowSpace) * i));
			}
		
			int max = Math.min(msg[low].length(), count);
			String tmp = "";
			for ( int i = 0; i <= max; i++ )
			{
				tmp = msg[low].substring(0, i);
				g.drawString(tmp, x, y + ((g.fontHeight + rowSpace) *low));
			}
		}
	}
}
