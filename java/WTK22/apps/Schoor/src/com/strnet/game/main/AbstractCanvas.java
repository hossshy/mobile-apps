/*
 * Last modified: 2008/11/07 22:20:10
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
	
	abstract void doAction();
}
