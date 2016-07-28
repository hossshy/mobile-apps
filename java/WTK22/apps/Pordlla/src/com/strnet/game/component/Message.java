/*
 * Last modified: 2008/07/05 21:57:18
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.GameUtil;
import com.strnet.game.main.AbstractCanvas;

public class Message
{
	protected String[] msg;
	protected int x, y;
	protected int rowSpace = 2;
	protected int r, gg, b;
	
	public Message(String m, int x, int y)
	{
		setMsg(m);
		this.x = x;
		this.y =y;
		r = 0;
		gg = 0;
		b = 0;
	}
	
	public void setColor(int r, int gg, int b)
	{
		this.r = r;
		this.gg = gg;
		this.b = b;
	}
	
	public boolean isNull()
	{
		return msg == null;
	}
	
	public void setMsg(String m)
	{
		if ( m == null )
		{
			msg = null;
		}
		else
		{
			this.msg = GameUtil.split(m, '\n');
		}
	}
	
	public void setRowSpace(int rowSpace)
	{
		this.rowSpace = rowSpace;
	}
	
	public void paint(AbstractCanvas g)
	{
		if ( msg != null )
		{
			g.setColor(r, gg, b);
			for ( int i = 0; i < msg.length; i++ )
			{
				g.drawString(msg[i], x, y + ((g.fontHeight + rowSpace) * i));
			}
		}
	}
}
