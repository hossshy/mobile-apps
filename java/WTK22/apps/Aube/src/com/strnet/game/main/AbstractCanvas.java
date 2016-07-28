/*
 * Last modified: 2008/07/05 21:57:44
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public abstract class AbstractCanvas extends ModelDependenceCanvas implements Runnable
{
	protected boolean play = true;
	protected int wait = 50;
	protected int scene;
	
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

		while ( play )
		{
			if ( g != null )
			{
			setKeyState();
			lock();
			resetScreen();

			doAction();
			
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
	
	abstract void doAction();
}
