/*
 * Last modified: 2009/06/13 02:05:30
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

public class EndingData
{
	int endingCounter = 0;
	int fontHeight=0;
	int x = 30;
	int y = 278;
	boolean exitFlag = false;
	String[] msg1 = new String[]{"企画/プログラム", "グラフィック", "デバッグ", "Presented by"};
	String[] msg2 = new String[]{"風舞 光", "ゆのき", "すぐは", ""};

	public EndingData(int fontHeight)
	{
		this.fontHeight = fontHeight;
	}

	public String toString()
	{
		return endingCounter +":"+fontHeight+":"+x+":"+y;
	}

	public boolean next()
	{
		if ( endingCounter == msg1.length )
		{
			if ( y > 100 )
			{
				y-=4;
			}
			else if ( !exitFlag )
			{
				exitFlag = true;
			}
			else
			{
				return false;
			}
		}
		else if ( (y + (fontHeight * 2)) < (-20 - fontHeight) )
		{
			if ( endingCounter < msg1.length )
			{
				endingCounter++;
				y = 278;
			}
		}
		else
		{
			y-=4;
		}
		return true;
	}
	
	public void paint(AbstractCanvas g)
	{
		if ( endingCounter < msg1.length )
		{
			g.setColor(255,255,255);
			g.drawString(msg1[endingCounter], x, y);
			g.drawString(msg2[endingCounter], x+fontHeight, y + (fontHeight * 2));
		}
		else if ( endingCounter == msg1.length )
		{
			g.drawTitle(70, y);
		}
	}
}