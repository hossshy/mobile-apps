/*
 * Last modified: 2008/10/16 12:07:46
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;

public abstract class AbstractCanvas extends ModelDependenceCanvas implements Runnable
{
	protected boolean play = true;
	protected int wait = 50;
	protected int scene;
	protected int showFps = 0;
	
	public AbstractCanvas(Main app)
	{
		super(app);
	}
	
	void init(String[] labels)
	{
		super.init(labels);
	}
	
	public void setWaitTime(int wait)
	{
		this.wait = wait;
	}
	
	public void run()
	{
		long sleepTime = 0L;
		
		int fps = 0;
		long fpsTime = 0L;

		while ( play )
		{
			if ( g != null )
			{
				setKeyState();
				lock();
				resetScreen();
				
				doAction();
				
				fps++;
				if ( System.currentTimeMillis() - fpsTime > 1000 )
				{
					fpsTime = System.currentTimeMillis();
					showFps = fps;
					fps = 0;
				}
				
				unlock();
			}
			
			while ( System.currentTimeMillis() < sleepTime + wait && System.currentTimeMillis() - sleepTime < wait);
			sleepTime = System.currentTimeMillis();
		}
	}
	
	public int getKeyEvent()
	{
		return keyEvent;
	}
	
	public void keyReset()
	{
		keyEvent = -1;
	}
	
	public void terminate()
	{
		play = false;
		super.terminate();
	}
	
	public void resetScreen()
	{
		setColor(0,0,0);
		fillAll();
	}

	void setBGMEnable(boolean bgmEnable)
	{
		this.bgmEnable = bgmEnable;
		if ( !bgmEnable )
		{
			try {
				stopBGM();
			} catch ( Exception ignore ) {}
		}
	}
	
	void setSEEnable(boolean seEnable)
	{
		this.seEnable = seEnable;
	}
	
	void setVolume(int volume)
	{
		setVolume(volume, null);
	}
	
	void startBGM(int id) throws Exception
	{
		startBGM(id, 0);
	}
	
	void setSoftKey(int left, int right)
	{
		removeCommands();
		addCommand(0, left);
		if ( right >= 0 )
		{
			addCommand(1, right);
		}
	}
	
	public void setColor(Color c)
	{
		setColor(c.r, c.g, c.b);
	}
	
	public void drawImage(int imageNo, int sx, int sy, int dx, int dy, int w, int h)
	{
		if ( dx > 240 )
		{
			return;
		}
		else if ( (dx + w) > 240 )
		{
			w = 240 - dx;
		}
		else if ( (dx + w) < 0 )
		{
			return;
		}
		else if ( dx < 0 )
		{
			w += dx;
			sx -= dx;
			dx = 0;
		}
		
		if ( dy > 240 )
		{
			return;
		}
		else if ( (dy + h) > 240 )
		{
			h = 240 - dy;
		}
		else if ( (dy + h) < 0 )
		{
			return;
		}
		else if ( dy < 0 )
		{
			h += dy;
			sy -= dy;
			dy = 0;
		}
		
		super.drawImage(imageNo, sx, sy, dx, dy, w, h);
	}

	abstract void doAction();
}
