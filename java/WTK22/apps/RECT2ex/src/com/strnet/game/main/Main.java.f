/*
 * Last modified: 2010/03/25 12:31:07
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
import com.nttdocomo.util.JarInflater;

import com.nttdocomo.io.HttpConnection;

import com.strnet.game.common.IOUtil;
//import com.nttdocomo.ui.*;
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
		/*
		if ( isDxMode() )
		{
			AppgetDx.check(0);
		}
		*/
		c = new MainCanvas(this);
		
		Display.setCurrent(c);
		if ( c.setArchive() )
		{
			c.loadTitle();
			(new Thread(c)).start();
			//c.run();
			c.init();
		}
	}
	
	protected void startApp() {}
	protected void pauseApp() {}
	
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

	public static final int SCREEN_WIDTH = 240;
	public static final int SCREEN_HEIGHT = 240;
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
	public static final String dx = ";pos=8";
	public static final int MAX_VOLUME = 100;
	public static final String RESOURCE_SIZE_URL = "scratchpad:///0;pos=8092";
	public static final String RESOURCE_VERSION_URL = "scratchpad:///0;pos=8102";
	public static final String RESOURCE_URL = "scratchpad:///0;pos=8112";
	private static final String ARCHIVE_VERSION = "@@ARCHIVE_VERSION@@";

	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	Image title = null;
	String[] labels;
	Graphics g = null;
	boolean[] keyState = new boolean[] {false,false,false,false,false,false,false,false,false,false,false,false,false,false};
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
	boolean seStop = true;
	boolean vibrateEnable = true;
	AudioPresenter[] audio = null;
	boolean hiSound = true;
	int ascent = 0;
	private Font font;
	
	public ModelDependenceCanvas(Main app)
	{
		this.app = app;
		if ( getWidth() > SCREEN_WIDTH )
			screenX = (getWidth() - SCREEN_WIDTH) / 2;
		if ( getHeight() > SCREEN_HEIGHT )
			screenY = (getHeight() - SCREEN_HEIGHT) / 2;
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
			se.setMediaListener(this);
			se.setAttribute(AudioPresenter.SYNC_MODE, AudioPresenter.ATTR_SYNC_ON);
			seStop = true;
			PhoneSystem.setAttribute(PhoneSystem.DEV_BACKLIGHT,PhoneSystem.ATTR_BACKLIGHT_ON);
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
			MediaImage mi = MediaManager.getImage(getFileResource("str.gif"));
			mi.use();
			title = mi.getImage();
		} catch ( Exception e ) {}
	}

	public void loadImage(int i) throws Exception
	{
		if ( image[i] == null )
		{
			MediaImage mi = MediaManager.getImage(getFileResource(i + ".gif"));
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

	public boolean isLoadedImage(int i)
	{
		return image[i] != null;
	}
	
	public void paint(Graphics g)
	{
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
				keyState[0] = true;
				break;
			case Display.KEY_DOWN:
				keyEvent = S_KEY_DOWN;
				keyState[1] = true;
				break;
			case Display.KEY_LEFT:
				keyEvent = S_KEY_LEFT;
				keyState[2] = true;
				break;
			case Display.KEY_RIGHT:
				keyEvent = S_KEY_RIGHT;
				keyState[3] = true;
				break;
			case Display.KEY_0:
				keyEvent = S_KEY_0;
				keyState[4] = true;
				break;
			case Display.KEY_1:
				keyEvent = S_KEY_1;
				keyState[5] = true;
				break;
			case Display.KEY_2:
				keyEvent = S_KEY_2;
				keyState[6] = true;
				break;
			case Display.KEY_3:
				keyEvent = S_KEY_3;
				keyState[7] = true;
				break;
			case Display.KEY_4:
				keyEvent = S_KEY_4;
				keyState[8] = true;
				break;
			case Display.KEY_5:
				keyEvent = S_KEY_5;
				keyState[9] = true;
				break;
			case Display.KEY_6:
				keyEvent = S_KEY_6;
				keyState[10] = true;
				break;
			case Display.KEY_7:
				keyEvent = S_KEY_7;
				keyState[11] = true;
				break;
			case Display.KEY_8:
				keyEvent = S_KEY_8;
				keyState[12] = true;
				break;
			case Display.KEY_9:
				keyEvent = S_KEY_9;
				keyState[13] = true;
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
			switch ( param )
			{
			case Display.KEY_UP:
				keyState[0] = false;
				break;
			case Display.KEY_DOWN:
				keyState[1] = false;
				break;
			case Display.KEY_LEFT:
				keyState[2] = false;
				break;
			case Display.KEY_RIGHT:
				keyState[3] = false;
				break;
			case Display.KEY_0:
				keyState[4] = false;
				break;
			case Display.KEY_1:
				keyState[5] = false;
				break;
			case Display.KEY_2:
				keyState[6] = false;
				break;
			case Display.KEY_3:
				keyState[7] = false;
				break;
			case Display.KEY_4:
				keyState[8] = false;
				break;
			case Display.KEY_5:
				keyState[9] = false;
				break;
			case Display.KEY_6:
				keyState[10] = false;
				break;
			case Display.KEY_7:
				keyState[11] = false;
				break;
			case Display.KEY_8:
				keyState[12] = false;
				break;
			case Display.KEY_9:
				keyState[13] = false;
				break;
			}
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
	
	public void drawTitle(int sx, int sy, int dx, int dy, int w, int h)
	{
		g.drawImage(title, screenX + dx, screenY + dy, sx, sy, w, h);
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
		g.drawString(msg, screenX + x, screenY + y + ascent);
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
		if ( S_FONT_SMALL == size )
		{
			font = Font.getFont(Font.FACE_MONOSPACE | Font.STYLE_PLAIN | Font.SIZE_TINY);
		}
		else if ( S_FONT_LARGE == size )
		{
			font = Font.getFont(Font.TYPE_DEFAULT);
		}
		else
		{
			font = Font.getFont(Font.FACE_MONOSPACE | Font.SIZE_SMALL);
		}
		fontWidth = font.stringWidth("A");
		wideFontWidth = font.stringWidth("‚ ");
		fontHeight = font.getHeight();
		wordSpace = font.stringWidth("AA") - fontWidth;
		ascent = font.getAscent();
		g.setFont(font);
	}

	public int getStringWidth(String s)
	{
		return font.stringWidth(s);
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
			return (ret != null) && (ret.length() > 1);
		}
		catch ( Exception e )
		{
			return false;
		}
		finally
		{
			IOUtil.close(in);
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
			IOUtil.close(in);
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
			IOUtil.close(out);
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
	
	public void loadBGM(int id) throws Exception
	{
		stopBGM();
		MediaSound tmp = MediaManager.getSound(getFileResource(id + ".mld"));
		tmp.use();
		bgm.setSound(tmp);
	}

	public void startBGM(int id, int mediaTime) throws Exception
	{
		if ( bgmEnable )
		{
			stopBGM();
			loadBGM(id);
			setBGMVolume(volume);
			bgm.play();
		}
	}

	public void pauseBGM() throws Exception
	{
		if ( bgmEnable && bgm != null )
		{
			bgm.pause();
		}
	}
	
	public void restartBGM() throws Exception
	{
		if ( bgmEnable && bgm != null )
		{
			bgm.restart();
		}
	}

	public void stopBGM() throws Exception
	{
		if ( bgm != null )
		{
			if ( bgm.getMediaResource() != null )
			{
				bgm.getMediaResource().dispose();
			}
		}
	}

	public void loadSE(int id) throws Exception
	{
		stopSE();
		MediaSound tmp = MediaManager.getSound(getFileResource(id + ".mld"));
		tmp.use();
		se.setSound(tmp);
	}
	

	public void startSE() throws Exception
	{
		if ( seEnable && seStop )
		{
			setSEVolume(seVolume);
			seStop = false;
			se.play();
		}
	}
	
	public void stopSE() throws Exception
	{
		if ( se != null )
		{
			if ( se.getMediaResource() != null )
			{
				se.getMediaResource().dispose();
				seStop = true;
			}
		}
	}

	public void setBGMVolume(int volume)
	{
		this.volume = volume;
		if ( bgm != null )
		{
			bgm.setAttribute(AudioPresenter.SET_VOLUME, volume);
		}
	}

	public void setSEVolume(int seVolume)
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
			seStop = true;
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

	public String getCarrer()
	{
		return "i";
	}



	JarInflater archive = null;
	
	public boolean setArchive()
	{
		g = getGraphics();
		int len = getArchiveSize();
		String av = getArchiveVersion();
		if ( av == null )
		{
			av = "-----";
		}
		setColor(0,0,0);
		fillAll();
		setColor(255,255,255);
		drawString("Check " + len + " Ver [" + av + "]",0,12);
		try
		{
			if ( (len <= 0) || !ARCHIVE_VERSION.equals(av) )
			{
				drawString("Download Resource...",0,24);
				len = download();
			}

			drawString("Load Resource...",0,36);
			loadArchilve(len);
			return true;
		}
		catch ( Exception e )
		{
			setColor(255,0,0);
			drawString("ƒŠƒ\[ƒX‚ÌŽæ“¾‚ÉŽ¸”s", 0, 50);
			drawString("Å‰‚©‚ç‚â‚è’¼‚µ‚Ä‰º‚³‚¢", 0, 80);
			e.printStackTrace();
		}
		return false;
	}


	private String getPath()
	{
		return IApplication.getCurrentApp().getSourceURL();
	}

	public int download() throws Exception
	{
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		
		for ( int i = 0; i < 20; i++ )
		{
			byte[] tmp = downloadFile(i + ".spl");
			if ( tmp == null )
			{
				break;
			}
			buf.write(tmp, 0, tmp.length);
			drawString(".",i*16,200);
		}
		buf.flush();
		byte[] data = buf.toByteArray();
		JarInflater jar = null;
		try
		{
			jar = new JarInflater(data);
		}
		finally
		{
			if ( jar != null )
			{
				try
				{
					jar.close();
				}
				catch ( Exception ignore ){}
			}
		}

		saveArchive(data);
		return data.length;
	}

	public void loadArchilve(int size) throws Exception
	{
		DataInputStream in = null;
		ByteArrayOutputStream buf = null;
		try
		{
			in = Connector.openDataInputStream(RESOURCE_URL + ",length=" + size);
			buf = new ByteArrayOutputStream();
			byte[] tmp = new byte[64];
			int len = 0;
			while ( (len = in.read(tmp)) > 0 )
			{
				buf.write(tmp,0,len);
			}
			buf.flush();
			archive = new JarInflater(buf.toByteArray());
		}
		finally
		{
			IOUtil.close(buf);
			IOUtil.close(in);
		}
	}

	public InputStream getFileResource(String fileName) throws Exception
	{
		return archive.getInputStream(fileName);
	}
	
	public String getArchiveVersion()
	{
		DataInputStream in = null;
		try
		{
			in = Connector.openDataInputStream(RESOURCE_VERSION_URL);
			return in.readUTF();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "=====";
		}
		finally
		{
			IOUtil.close(in);
		}
	}
	
	public int getArchiveSize()
	{
		DataInputStream in = null;
		try
		{
			in = Connector.openDataInputStream(RESOURCE_SIZE_URL);
			return in.readInt();
		}
		catch ( Exception e )
		{
			return -1;
		}
		finally
		{
			IOUtil.close(in);
		}
	}

	private void saveArchive(byte[] data) throws Exception
	{
		DataOutputStream out = null;
		try
		{
			out = Connector.openDataOutputStream(RESOURCE_URL);
			/*
			for ( int i = 0; i < data.length; i++ )
			{
				out.write(data, i, 1);
			}
			*/
			out.write(data);
			out.close();

			out = Connector.openDataOutputStream(RESOURCE_SIZE_URL);
			out.writeInt(data.length);
			out.close();

			out = Connector.openDataOutputStream(RESOURCE_VERSION_URL);
			out.writeUTF(ARCHIVE_VERSION);
			out.close();
		}
		finally 
		{
			IOUtil.close(out);
		}
	}

	private byte[] downloadFile(String fileName)
	{
		HttpConnection con = null;
		InputStream in = null;
		ByteArrayOutputStream buf = null;

		try
		{
			String url = getPath() + fileName;
			con = (HttpConnection) Connector.open(url, Connector.READ, true);
			con.setRequestMethod(HttpConnection.GET);
			con.connect();
			if ( con.getResponseCode() != HttpConnection.HTTP_OK )
			{
				return null;
			}

			in = con.openInputStream();
			byte[] tmp = new byte[64];
			buf = new ByteArrayOutputStream();
			int len = 0;
			while ( (len = in.read(tmp)) > 0 )
			{
				buf.write(tmp, 0, len);
			}
			buf.flush();
			return buf.toByteArray();
		}
		catch ( Exception e )
		{
			return null;
		}
		finally
		{
			IOUtil.close(buf);
			IOUtil.close(in);
			close(con);
		}
	}
	  
	public static void close(Connection in)
	{
		if ( in != null )
		{
			try
			{
				in.close();
			}
			catch ( Exception ignore ) {}
		}
	}
	public String readTextFile(String fileName) throws Exception
	{
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try
		{
			in = getFileResource(fileName);
			out = new ByteArrayOutputStream();

			byte[] buf = new byte[64];
			int len;
			while ( (len = in.read(buf)) != -1 )
			{
				out.write(buf, 0, len);
			}
			out.flush();
			in.close();
			return new String(out.toByteArray(), "SJIS");
			//return out.toString();
		}
		finally
		{
			IOUtil.close(in);
			IOUtil.close(out);
		}
	}
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
