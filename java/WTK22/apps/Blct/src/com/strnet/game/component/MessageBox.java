/*
 * Last modified: 2009/06/27 22:24:27
 * author: Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.common.Point;
import com.strnet.game.component.ImageData;
import com.strnet.game.main.AbstractCanvas;

public class MessageBox
{
	private ImageData background;
	private Point dest;
	private int margin;
	private int rowSpace;
	private String[] msgs = null;
	private Color foreground = null;

	public MessageBox(ImageData background, Point dest, Color foreground)
	{
		this.background = background;
		this.dest = dest;
		this.foreground = foreground;
		margin = 5;
		rowSpace = 2;
	}

	public void setMessage(String[] msgs)
	{
		this.msgs = msgs;
	}

	public boolean hasMessage()
	{
		return msgs != null;
	}

	public void clear()
	{
		if ( hasMessage() )
		{
			msgs = null;
		}
	}

	public void setMargin(int margin)
	{
		this.margin = margin;
	}

	public void setRowSpace(int rowSpace)
	{
		this.rowSpace = rowSpace;
	}

	public void paint(AbstractCanvas g)
	{
		if ( !hasMessage() )
		{
			return;
		}
		
		if ( background != null )
		{
			background.paint(g, dest.x, dest.y);
		}

		g.setColor(foreground);
		for ( int i = 0; i < msgs.length; i++ )
		{
			g.drawString(msgs[i],
						 dest.x + margin,
						 dest.y + margin + (i * g.fontHeight) + (i * rowSpace));
		}
	}
}