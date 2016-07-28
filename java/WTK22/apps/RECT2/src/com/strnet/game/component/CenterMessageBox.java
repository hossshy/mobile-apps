/*
 * Last modified: 2009/06/26 03:24:10
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.common.Point;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImageData;
import com.strnet.game.main.AbstractCanvas;

public class CenterMessageBox
{
	private ImageData background;
	private Point dest;
	private String msg = null;
	private Color foreground = null;
	private int dx;
	private int dy;

	public CenterMessageBox(ImageData background, Point dest, Color foreground)
	{
		this.background = background;
		this.dest = dest;
		this.foreground = foreground;
	}

	public void setMessage(String msg, AbstractCanvas g)
	{
		this.msg = "Åy" + msg + "Åz";
		Rectangle rect = background.getRectangle();
		dx = dest.x + ((rect.width - g.getStringWidth(this.msg)) / 2);
		dy = dest.y + ((rect.height - g.fontHeight) / 2);
	}

	public boolean hasMessage()
	{
		return msg != null;
	}

	public void clear()
	{
		if ( hasMessage() )
		{
			msg = null;
		}
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
		g.drawString(msg, dx, dy);
	}
}