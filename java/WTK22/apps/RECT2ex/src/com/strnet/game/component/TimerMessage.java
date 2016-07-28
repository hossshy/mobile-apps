/*
 * Last modified: 2008/07/05 21:57:33
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.main.AbstractCanvas;

public class TimerMessage extends Message
{
	protected String msg;
	private int showTimeMillis;
	private long startTime;
	
	public TimerMessage(int showTimeMillis, int x, int y)
	{
		super("", x, y);
		this.showTimeMillis = showTimeMillis;
		startTime = 0L;
		msg = null;
	}
	
	public void setMsg(String msg)
	{
		super.setMsg(msg);
		startTime = System.currentTimeMillis();
	}
	
	protected void reset()
	{
		if ( (msg != null) && (System.currentTimeMillis() - startTime) > showTimeMillis )
		{
			msg = null;
		}
	}
	
	public void paint(AbstractCanvas g)
	{
		reset();
		super.paint(g);
	}
}
