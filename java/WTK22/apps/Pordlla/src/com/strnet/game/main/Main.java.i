/*
 * Last modified: 2009/02/26 00:28:10
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.nttdocomo.ui.IApplication;
import com.nttdocomo.ui.Display;
import com.nttdocomo.ui.Canvas;
import com.nttdocomo.ui.Display;
import com.nttdocomo.ui.Font;
import com.nttdocomo.ui.Graphics;
import com.nttdocomo.ui.MediaManager;
import com.nttdocomo.ui.MediaImage;
import com.nttdocomo.ui.PhoneSystem;
import com.nttdocomo.ui.Image;
import com.nttdocomo.ui.AudioPresenter;
import com.nttdocomo.ui.MediaListener;
import com.nttdocomo.ui.MediaManager;
import com.nttdocomo.ui.MediaPresenter;
import com.nttdocomo.ui.MediaSound;
import javax.microedition.io.*;
import java.io.*;

public class Main extends IApplication
{
	public static String DX_MODE = "@@DX_MODE@@";

	MainCanvas c = null;
	
	public static boolean isDxMode()
	{
		return ( ".dx".equals(DX_MODE) );
	}
	
	public void start()
	{
		if ( isDxMode() )
		{
			AppgetDx.check(0);
		}
		c = new MainCanvas(this);
		
		Display.setCurrent(c);
		c.loadTitle();
		(new Thread(c)).start();
		//c.run();
		c.init();
	}
	
	protected void startApp() {}
	protected void pauseApp()
	{
	}
	
	public void resume()
	{
		if ( c != null )
		{
			c.resume();
		}
	}
	
	protected void destroyApp(boolean flag)
	{
		if ( c != null )
		{
			c.terminate();
		}
	}
}

abstract class ModelDependenceCanvas extends Canvas implements Runnable, MediaListener
{
	public static final String URL = "http://m.strnet.com/i/";
	
	public static final int S_KEY_FIRE = 1;
	public static final int S_KEY_UP = 2;
	public static final int S_KEY_DOWN = 3;
	public static final int S_KEY_LEFT = 4;
	public static final int S_KEY_RIGHT = 5;
	public static final int S_KEY_0 = 6;
	public static final int S_KEY_1 = 7;
	public static final int S_KEY_2 = 8;
	public static final int S_KEY_3 = 9;
	public static final int S_KEY_4 = 10;
	public static final int S_KEY_5 = 11;
	public static final int S_KEY_6 = 12;
	public static final int S_KEY_7 = 13;
	public static final int S_KEY_8 = 14;
	public static final int S_KEY_9 = 15;
	public static final int S_KEY_POUND = 16;
	public static final int S_KEY_STAR = 17;
	public static final int S_SOFT_KEY = 18;

	public static final int S_FONT_SMALL = 0;
	public static final int S_FONT_MEDIUM = 1;
	public static final int S_FONT_LARGE = 2;
	public static String dx = ";pos=8";
	public static final int MAX_VOLUME = 100;
	
	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	Image title = null;
	String[] labels;
	Graphics g = null;
	int keyState;
	int keyEvent = -1;
	Main app;
	public int fontWidth;
	public int wideFontWidth;
	public int fontHeight;
	public int wordSpace;
	int[] softkey = new int[]{0, 0};
	boolean initFlag = false;
	int loadedCounter = 0;

	AudioPresenter bgm = null;
	AudioPresenter se = null;
	int volume = MAX_VOLUME;
	int seVolume = MAX_VOLUME;
	boolean bgmEnable = true;
	boolean seEnable = true;
	boolean vibrateEnable = true;
	AudioPresenter[] audio = null;
	boolean hiSound = true;
	
	public ModelDependenceCanvas(Main app)
	{
		this.app = app;
		if ( getWidth() > 240 )
			screenX = (getWidth() - 240) / 2;
		if ( getHeight() > 240 )
			screenY = (getHeight() - 240) / 2;
	}

	void init(String[] labels)
	{
		if ( !initFlag )
		{
			this.labels = labels;
			if ( g == null )
			{			
				g = getGraphics();
			}
			bgm = AudioPresenter.getAudioPresenter();
			se = AudioPresenter.getAudioPresenter();
			MediaListener repeat = new MediaListener()
			{
				public void mediaAction(MediaPresenter audio, int type, int param)
				{
					if ( type == AudioPresenter.AUDIO_COMPLETE )
					{
						((AudioPresenter)audio).play();
					}
				}
			};
			bgm.setMediaListener(repeat);
			bgm.setAttribute(AudioPresenter.SYNC_MODE, AudioPresenter.ATTR_SYNC_ON);
			se.setAttribute(AudioPresenter.SYNC_MODE, AudioPresenter.ATTR_SYNC_ON);
			initFlag = true;
		}

	}

	void initImage(int max)
	{
		image = new Image[max+1];
	}
	
	public void loadTitle()
	{
		try
		{
			MediaImage mi = MediaManager.getImage("resource:///str.gif");
			mi.use();
			title = mi.getImage();
		} catch ( Exception e ) {}
	}

	public void loadImage(int i) throws Exception
	{
		if ( image[i] == null )
		{
			MediaImage mi = MediaManager.getImage("resource:///" + i + ".gif");
			mi.use();
			image[i] = mi.getImage();
		}
	}
	
	public void releaseImage(int i) throws Exception
	{
		if ( image[i] != null )
		{
			image[i].dispose();
			image[i] = null;
		}
	}
	
	public void paint(Graphics g)
	{
	}

	void setKeyState()
	{
		keyState = getKeypadState();
	}

	boolean isUp()
	{
		return ((keyState >> Display.KEY_UP) & 0x1) == 1;
	}
	
	boolean isDown()
	{
		return ((keyState >> Display.KEY_DOWN) & 0x1) == 1;
	}
	
	boolean isLeft()
	{
		return ((keyState >> Display.KEY_LEFT) & 0x1) == 1;
	}
	
	boolean isRight()
	{
		return ((keyState >> Display.KEY_RIGHT) & 0x1) == 1;
	}
	
	void removeCommands()
	{
		setSoftLabel(0, null);
		setSoftLabel(1, null);
		softkey[0] = -1;
		softkey[1] = -1;
	}
	
	void addCommand(int key, int i)
	{
		softkey[key] = i;
		setSoftLabel(key, labels[i]);
	}
	
	abstract void doCommandAction(int i);

	public void processEvent(int type, int param)
	{
		if ( type == Display.KEY_PRESSED_EVENT )
		{
			switch ( param )
			{
			case Display.KEY_SELECT:
				keyEvent = S_KEY_FIRE;
				break;
			case Display.KEY_UP:
				keyEvent = S_KEY_UP;
				break;
			case Display.KEY_DOWN:
				keyEvent = S_KEY_DOWN;
				break;
			case Display.KEY_LEFT:
				keyEvent = S_KEY_LEFT;
				break;
			case Display.KEY_RIGHT:
				keyEvent = S_KEY_RIGHT;
				break;
			case Display.KEY_0:
				keyEvent = S_KEY_0;
				break;
			case Display.KEY_1:
				keyEvent = S_KEY_1;
				break;
			case Display.KEY_2:
				keyEvent = S_KEY_2;
				break;
			case Display.KEY_3:
				keyEvent = S_KEY_3;
				break;
			case Display.KEY_4:
				keyEvent = S_KEY_4;
				break;
			case Display.KEY_5:
				keyEvent = S_KEY_5;
				break;
			case Display.KEY_6:
				keyEvent = S_KEY_6;
				break;
			case Display.KEY_7:
				keyEvent = S_KEY_7;
				break;
			case Display.KEY_8:
				keyEvent = S_KEY_8;
				break;
			case Display.KEY_9:
				keyEvent = S_KEY_9;
				break;
			case Display.KEY_ASTERISK:
				keyEvent = S_KEY_STAR;
				break;
			case Display.KEY_POUND:
				keyEvent = S_KEY_POUND;
				break;
			case Display.KEY_SOFT1:
				doCommandAction(softkey[0]);
				keyEvent = S_SOFT_KEY;
				break;
				
			case Display.KEY_SOFT2:
				doCommandAction(softkey[1]);
				keyEvent = S_SOFT_KEY;
				break;
			}
			fireKeyPressed();
		}
		else if ( type == Display.KEY_RELEASED_EVENT )
		{
			fireKeyReleased();
		}
	}

	public void fireKeyPressed() {}
	public void fireKeyReleased() {}
	
	public void browser(String url)
	{
		try
		{
			app.launch(app.LAUNCH_BROWSER, new String[]{url});
		}
		catch ( Exception e )
		{
		}
	}

	public void lock()
	{
		g.lock();
	}
	
	public void unlock()
	{
		g.unlock(true);
	}
	
	public void fillRect(int x, int y, int w, int h)
	{
		g.fillRect(screenX + x, screenY + y, w, h);
	}

	public void fillAll()
	{
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public void drawImage(int imageNo, int x, int y)
	{
		if ( image[imageNo] == null )
		{
			try{
			loadImage(imageNo);
			}catch(Exception e){}
		}
		g.drawImage(image[imageNo], screenX + x, screenY + y);
	}

	public void drawTitle(int x, int y)
	{
		if ( title != null )
		{
			g.drawImage(title, screenX + x, screenY + y);
		}
	}
	
	public void drawImage(int imageNo, int sx, int sy, int dx, int dy, int w, int h)
	{
		if ( image[imageNo] == null )
		{
			try{
			loadImage(imageNo);
			}catch(Exception e){}
		}
		g.drawImage(image[imageNo], screenX + dx, screenY + dy, sx, sy, w, h);
	}

	public void drawString(String msg, int x, int y)
	{
		g.drawString(msg, screenX + x, screenY + y + fontHeight);
	}
	
	public void drawRect(int x, int y, int w, int h)
	{
		g.drawRect(screenX + x, screenY + y, w, h);
	}
	
	public void setColor(int r, int gg, int b)
	{
		g.setColor(g.getColorOfRGB(r,gg,b));
	}
	
	public void setFont(int size)
	{
		Font f;
		if ( S_FONT_SMALL == size )
		{
			f = Font.getFont(Font.FACE_MONOSPACE | Font.SIZE_SMALL);
		}
		else if ( S_FONT_LARGE == size )
		{
			f = Font.getFont(Font.TYPE_DEFAULT);
		}
		else
		{
			f = Font.getFont(Font.FACE_MONOSPACE | Font.SIZE_TINY);
		}
		fontWidth = f.stringWidth("A");
		wideFontWidth = f.stringWidth("‚ ");
		fontHeight = f.getHeight();
		wordSpace = f.stringWidth("AA") - fontWidth;
		g.setFont(f);
	}
	
	public void terminate()
	{
		try
		{
			stopBGM();
		} catch ( Exception ignore ) {}
		app.terminate();
	}
	
	public static boolean isRecord(String key)
	{
		DataInputStream in = null;
		try
		{
			in = Connector.openDataInputStream("scratchpad:///0" + dx);
			String ret = in.readUTF();
			return (ret != null) && (ret.length() > 100);
		}
		catch ( Exception e )
		{
			return false;
		}
		finally
		{
			if ( in != null )
			{
				try 
				{
					in.close();
				}
				catch (Exception ignore) {}
			}
		}
	}

	public static String load(String key) throws Exception
	{
		String ret = null;
		DataInputStream in = null;
		try
		{
			in = Connector.openDataInputStream("scratchpad:///0" + dx);
			ret = in.readUTF();
		}
		finally
		{
			if ( in != null )
			{
				try 
				{
					in.close();
				}
				catch (Exception ignore) {}
			}
		}
		return ret;
	}
	
	public static void delete(String key) throws Exception
	{
		save(null, "");
	}
	
	public static void save(String key, String value) throws Exception
	{
		DataOutputStream out = null;
		try
		{
			out = Connector.openDataOutputStream("scratchpad:///0" + dx);
			out.writeUTF(value);
		}
		finally
		{
			if ( out != null )
			{
				try 
				{
					out.close();
				}
				catch (Exception ignore) {}
			}
		}
	}
	
	void loadBGM(int id) throws Exception
	{
		stopBGM();
		MediaSound tmp = MediaManager.getSound("resource:///" + id + ".mld");
		tmp.use();
		bgm.setSound(tmp);
		bgm.setMediaListener(this);
	}

	void startBGM(int id, int mediaTime) throws Exception
	{
		if ( bgmEnable )
		{
			stopBGM();
			loadBGM(id);
			setBGMVolume(volume);
			bgm.play();
		}
	}
	
	void resume()
	{
		if ( bgmEnable && bgm != null )
		{
			try
			{
				bgm.play(bgm.getCurrentTime());
			}
			catch ( Exception ignore ) {}
		}
	}

	void stopBGM() throws Exception
	{
		if ( bgm != null )
		{
			if ( bgm.getMediaResource() != null )
			{
				bgm.getMediaResource().dispose();
			}
		}
	}

	void loadSE(int id) throws Exception
	{
		stopSE();
		MediaSound tmp = MediaManager.getSound("resource:///" + id + ".mld");
		tmp.use();
		se.setSound(tmp);
	}
	

	void startSE() throws Exception
	{
		if ( seEnable )
		{
			setSEVolume(seVolume);
			se.play();
		}
	}
	
	void stopSE() throws Exception
	{
		if ( se != null )
		{
			if ( se.getMediaResource() != null )
			{
				se.getMediaResource().dispose();
			}
		}
	}

	void setBGMVolume(int volume)
	{
		this.volume = volume;
		if ( bgm != null )
		{
			bgm.setAttribute(AudioPresenter.SET_VOLUME, volume);
		}
	}

	void setSEVolume(int seVolume)
	{
		this.seVolume = seVolume;
		if ( se != null )
		{
			se.setAttribute(AudioPresenter.SET_VOLUME, seVolume);
		}
	}

	public void mediaAction(MediaPresenter source, int type, int param)
	{
		if ( type == AudioPresenter.AUDIO_COMPLETE )
		{
			((AudioPresenter)source).play();
		}
	}

	private ShortTimer sTimer = null;
	void vibrate(int ms)
	{
		if ( vibrateEnable )
		{
			PhoneSystem.setAttribute(PhoneSystem.DEV_VIBRATOR , PhoneSystem.ATTR_VIBRATOR_ON);
			if ( sTimer != null )
			{
				sTimer.stop = true;
			}
			sTimer = new ShortTimer(ms);
			sTimer.start();
		}
	}

	public String getCarrier()
	{
		return "i";
	}

	public abstract int getBGMSplit(int id);
}
	
class ShortTimer extends Thread
{
	int sleepTime;
	boolean stop = false;
	public ShortTimer(int sleepTime)
	{
		this.sleepTime = sleepTime;
	}
	
	public void run()
	{
		try
		{
			Thread.sleep(sleepTime);
		}
		catch ( Exception e ) {}
		if ( !stop )
		{
			PhoneSystem.setAttribute(PhoneSystem.DEV_VIBRATOR , PhoneSystem.ATTR_VIBRATOR_OFF);
		}
	}
}
