/*
 * Last modified: 2009/06/27 23:13:48
 * author: Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.common.Point;
import com.strnet.game.component.ImageData;
import com.strnet.game.main.AbstractCanvas;

public class SelectBox
{
	private ImageData background;
	private Point dest;
	private int margin;
	private int rowSpace;
	private String[] msgs = null;
	private Color foreground = null;
	private MenuWindow menu;
	public String mark = "ÅÑ";
	public int leftSpace = 20;
	public int markSpace;

	public SelectBox(ImageData background, Point dest, Color foreground, int row)
	{
		this.background = background;
		this.dest = dest;
		this.foreground = foreground;
		margin = 5;
		rowSpace = 2;
		markSpace = rowSpace;
		menu = new MenuWindow(1, row, false, true);
	}

	public int getId()
	{
		return menu.getId();
	}

	public boolean move(AbstractCanvas g)
	{
		return menu.move(g);
	}

	public void resetId()
	{
		menu.resetId();
	}

	public void setVisible(boolean visible)
	{
		menu.setVisible(visible);
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
			if ( i == menu.getId() )
			{
				g.drawString(mark,
							 dest.x + margin + leftSpace,
							 dest.y + margin + (i * g.fontHeight) + (i * rowSpace));
				
			}
			g.drawString(msgs[i],
						 dest.x + margin + g.getStringWidth(mark) + markSpace + leftSpace,
						 dest.y + margin + (i * g.fontHeight) + (i * rowSpace));
		}
	}
}