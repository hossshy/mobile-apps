/*
 * Last modified: 2008/10/03 00:23:07
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.component;

import com.strnet.game.common.Color;
import com.strnet.game.common.Rectangle;
import com.strnet.game.main.AbstractCanvas;

public class MessageWindow
{
	protected int fontWidth;
	protected int fontHeight;
	protected int wordSpace;
	protected int rowSpace = 0;
	protected Rectangle rect;
	protected String[] message = null;
	protected int maxRowWords;
	protected Painter background = null;
	protected Color foreground = new Color(255,255,255);
	
	public MessageWindow(Rectangle rect, int fontWidth, int fontHeight, int wordSpace)
	{
		this.rect = rect;
		this.fontWidth = fontWidth;
		this.fontHeight = fontHeight;
		this.wordSpace = wordSpace;
		
		int w = rect.width;
		maxRowWords = w / fontWidth;
	}
	
	public void setBackground(Painter background)
	{
		this.background = background;
	}

	public boolean clear()
	{
		boolean ret = message != null;
		message = null;
		return ret;
	}
	
	public boolean hasMessage()
	{
		return message != null;
	}
	
	public void setMessage(String msg)
	{
		if ( msg == null )
		{
			throw new NullPointerException("MessageWindow setMessage null.");
		}
		
		int row = msg.length() / maxRowWords;
		if ( (msg.length() % maxRowWords) > 0 )
		{
			row++;
		}
		
		message = new String[row];
		for ( int i = 0; i < message.length; i++ )
		{
			int start = i * maxRowWords;
			int end = start + maxRowWords;

			if ( end > msg.length() )
			{
				end = msg.length();
			}
			message[i] = msg.substring(start, end);
		}
	}
	
	public void paint(AbstractCanvas g)
	{
		if ( (message != null) && (message.length > 0) )
		{
			if ( background != null )
			{
				background.paint(g, rect.x - background.getRevise(), rect.y - background.getRevise(), rect.width + (background.getRevise()*2), (fontHeight + rowSpace) * message.length + (background.getRevise()*2));
			}
			
			g.setColor(foreground.r, foreground.g, foreground.b);
			for ( int i = 0; i < message.length; i++ )
			{
				g.drawString(message[i], rect.x, rect.y + ((fontHeight + rowSpace) * i));
			}
		}
	}
}