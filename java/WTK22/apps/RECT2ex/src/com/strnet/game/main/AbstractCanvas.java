/*
 * Last modified: 2010/03/31 14:14:56
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.Rectangle;
import com.strnet.game.common.GameUtil;

public abstract class AbstractCanvas extends ModelDependenceCanvas implements Runnable
{
	public static final int IMG_TITLE_ID = -1;
	private int softKeyNo = -1;
	protected boolean play = true;
	protected int wait = 50;
	protected int scene;
	protected int showFps = 0;
	private Rectangle enableDrawArea;
	private int screenMaxX;
	private int screenMaxY;
	int nowBGM = -1;
	int nowSE = -1;
	int backupVolume = -1;
	
	protected String msg = null;
	
	public AbstractCanvas(Main app)
	{
		super(app);
		setEnableDrawArea(new Rectangle(0,0,SCREEN_WIDTH,SCREEN_HEIGHT));
	}
	
	public void setEnableDrawArea(Rectangle enableDrawArea)
	{
		this.enableDrawArea = enableDrawArea;
		screenMaxX = enableDrawArea.x + enableDrawArea.width;
		screenMaxY = enableDrawArea.y + enableDrawArea.height;
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
				lock();
				//resetScreen();
				
				doAction();
				if ( msg != null )
				{
					setColor(Color.RED);
					drawString(msg, 30,30);
				}
				fps++;
				if ( System.currentTimeMillis() - fpsTime > 1000 )
				{
					fpsTime = System.currentTimeMillis();
					showFps = fps;
					fps = 0;
				}
				unlock();
			}

			long now = System.currentTimeMillis();
			if ( sleepTime > now )
			{
				GameUtil.sleep((int) (sleepTime - now));
			}
			sleepTime = System.currentTimeMillis() + wait;
		}
	}

	public void error(Exception e, String msg)
	{
		if ( e != null )
		{
			e.printStackTrace();
		}
		this.msg = msg;
	}
	
	public int getKeyEvent()
	{
		return keyEvent;
	}
	
	public boolean isUp()
	{
		return keyState[0];
	}
	
	public boolean isDown()
	{
		return keyState[1];
	}
	
	public boolean isLeft()
	{
		return keyState[2];
	}
	
	public boolean isRight()
	{
		return keyState[3];
	}
	
	public int getSoftKeyNo()
	{
		return softKeyNo;
	}
	
	public void keyReset()
	{
		keyEvent = -1;
		softKeyNo = -1;
	}

	void doCommandAction(int i)
	{
		softKeyNo = i;
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
	
	public void resetSoundCache()
	{
		nowBGM = -1;
		nowSE = -1;
		backupVolume = -1;
	}

	public void loadBGM(int id) throws Exception
	{
		if ( nowBGM != id )
		{
			super.loadBGM(id);
			this.nowBGM = id;
		}
	}
	
	public void stopSE() throws Exception
	{
		nowSE = -1;
		super.stopSE();
	}
	
	public void startBGM(int id) throws Exception
	{
		loadBGM(id);
		startBGM(nowBGM, 0);
	}
	
	public void loadSE(int id) throws Exception
	{
		if ( nowSE != id )
		{
			super.loadSE(id);
			this.nowSE = id;
		}
	}

	public void startSE(int id) throws Exception
	{
		loadSE(id);
		super.startSE();
	}
	
	public void stopBGM() throws Exception
	{
		nowBGM = -1;
		super.stopBGM();
	}
	
	public void setEqualSEVolume()
	{
		backupVolume = seVolume;
		seVolume = volume;
	}
	
	public void restoreSEVolume()
	{
		if ( backupVolume != -1 )
		{
			seVolume = backupVolume;
		}
	}
	
	void setSoftKey(int left, int right)
	{
		removeCommands();
		if ( left >= 0 )
		{
			addCommand(0, left);
		}
		if ( right >= 0 )
		{
			addCommand(1, right);
		}
	}
	
	public void setColor(Color c)
	{
		setColor(c.r, c.g, c.b);
	}
	
	public void drawTitle(int x, int y)
	{
		drawImage(IMG_TITLE_ID,0,0, x, y, 100,40);
	}

	public void drawImage(int imageNo, int sx, int sy, int dx, int dy, int w, int h)
	{
		if ( (dx > screenMaxX) || ((dx + w) < enableDrawArea.x) ||
			 (dy > screenMaxY) || ((dy + h) < enableDrawArea.y) )
		{
			return;
		}

		if ( (dx + w) > screenMaxX )
		{
			w = screenMaxX - dx;
		}
		else if ( dx < enableDrawArea.x )
		{
			w += (dx - enableDrawArea.x);
			sx -= (dx - enableDrawArea.x);
			dx = enableDrawArea.x;
		}
		
		if ( (dy + h) > screenMaxY )
		{
			h = screenMaxY - dy;
		}
		else if ( dy < enableDrawArea.y )
		{
			h += (dy - enableDrawArea.y);
			sy -= (dy - enableDrawArea.y);
			dy = enableDrawArea.y;
		}

		if ( imageNo == IMG_TITLE_ID )
		{
			super.drawTitle(sx, sy, dx, dy, w, h);
		}
		else
		{
			super.drawImage(imageNo, sx, sy, dx, dy, w, h);
		}
	}
	
	public boolean isHiSound()
	{
		return hiSound;
	}
	
	public void setHiSound(boolean hiSound)
	{
		this.hiSound = hiSound;
	}

	public void setVibrateEnable(boolean vibrateEnable)
	{
		this.vibrateEnable = vibrateEnable;
	}

	public boolean isVibrateEnable()
	{
		return vibrateEnable;
	}
	
	public void doGetBrowser(String url, String value) throws Exception
	{
		url = url + "?v=" + GameUtil.urlEncode(value) + "&GUID=ON";
		browser(url);
	}

	public String getPlatform()
	{
		String ret = System.getProperty("microedition.platform");
		if ( ret != null && ret.startsWith("au-") )
		{
			ret = ret.substring(3);
		}
		return ret;
	}

	public boolean validateText(String key, String val)
	{
		if ( !key.equals(val) )
		{
			setColor(Color.RED);
			drawString("ŒŸØ‚ÉŽ¸”s‚µ‚Ü‚µ‚½B", 30,50);
			drawString("DL‚µ’¼‚µ‚Ä‚­‚¾‚³‚¢B", 30,65);
			return false;
		}
		return true;
	}
	abstract void doAction();
}
