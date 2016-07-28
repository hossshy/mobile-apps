/*
 * Last modified: 2010/03/25 12:29:44
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
//import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Player;
import javax.microedition.media.Manager;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.rms.RecordStore;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.IOUtil;

public class Main extends MIDlet
{
	MainCanvas c = null;
	public Main()
	{
		c = new MainCanvas(this);
		Display.getDisplay(this).setCurrent(c);
		c.loadTitle();
		(new Thread(c)).start();
		c.init();
	}
		
	public static boolean isDxMode()
	{
		return false;
	}

	protected void startApp() {}
	protected void pauseApp() {}
	
	protected void destroyApp(boolean flag)
	{
		if ( c != null )
		{
			c.terminate();
		}
	}
}


abstract class ModelDependenceCanvas extends Canvas implements Runnable, CommandListener
{
	public static final String URL = "http://m.strnet.com/au/";

	public static final int SCREEN_WIDTH = 240;
	public static final int SCREEN_HEIGHT = 240;
	
	private static final int G_POS = Graphics.LEFT|Graphics.TOP;
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
	
	public static final boolean DX = false;
	public static final int MAX_VOLUME = 100;
	
	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	Image title = null;
	Player bgm = null;
	Player se = null;
	String[] labels;
	Command[] command;
	Graphics g = null;
	//int keyState;
	boolean[] keyState = new boolean[] {false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	int keyEvent = -1;
	Main app;
	public int fontWidth;
	public int wideFontWidth;
	public int fontHeight;
	public int wordSpace;
	private Image offscreen;
	boolean initFlag = false;
	int loadedCounter = 0;
	int volume = MAX_VOLUME;
	int seVolume = MAX_VOLUME;
	boolean bgmEnable = true;
	boolean seEnable = true;
	boolean vibrateEnable = true;
	boolean hiSound = true;
	int ascent = 0;
	private Font font;
	
	public ModelDependenceCanvas(Main app)
	{
		//super(false);
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
			
			command = new Command[labels.length];
			for ( int i = 0; i < command.length; i++ )
			{
				command[i] = new Command(labels[i], Command.SCREEN, i);
			}
			offscreen = Image.createImage(getWidth(), getHeight());
			g = offscreen.getGraphics();
			setCommandListener(this);
			se = null;
			
			initFlag = true;
		}
	}

	void initImage(int max)
	{
		image = new Image[max+1];
	}
	
	public void loadImage(int i) throws Exception
	{
		if ( image[i] == null )
		{
			image[i] = Image.createImage("/" + i + ".png");
		}
	}
	
	public void loadTitle()
	{
		try
		{
			title = Image.createImage("/str.png");
		} catch ( Exception e ) {}
	}
	
	public void releaseImage(int i) throws Exception
	{
		image[i] = null;
	}

	public boolean isLoadedImage(int i)
	{
		return image[i] != null;
	}
	
	void removeCommands()
	{
		for ( int i = 0; i < command.length; i++ )
		{
			removeCommand(command[i]);
		}
	}
	
	void addCommand(int key, int i)
	{
		addCommand(command[i]);
	}
	
	public void commandAction(Command c, Displayable disp)
	{
		for ( int i = 0; i < command.length; i++ )
		{
			if ( c == command[i] )
			{
				doCommandAction(i);
				keyEvent = S_SOFT_KEY;
				fireKeyPressed();
				break;
			}
		}
	}
	
	abstract void doCommandAction(int i);

	public void keyPressed(int keyCode)
	{
		switch ( keyCode )
		{
		case KEY_NUM0:
			keyEvent = S_KEY_0;
			keyState[4] = true;
			break;
		case KEY_NUM1:
			keyEvent = S_KEY_1;
			keyState[5] = true;
			break;
		case KEY_NUM2:
			keyEvent = S_KEY_2;
			keyState[6] = true;
			break;
		case KEY_NUM3:
			keyEvent = S_KEY_3;
			keyState[7] = true;
			break;
		case KEY_NUM4:
			keyEvent = S_KEY_4;
			keyState[8] = true;
			break;
		case KEY_NUM5:
			keyEvent = S_KEY_5;
			keyState[9] = true;
			break;
		case KEY_NUM6:
			keyEvent = S_KEY_6;
			keyState[10] = true;
			break;
		case KEY_NUM7:
			keyEvent = S_KEY_7;
			keyState[11] = true;
			break;
		case KEY_NUM8:
			keyEvent = S_KEY_8;
			keyState[12] = true;
			break;
		case KEY_NUM9:
			keyEvent = S_KEY_9;
			keyState[13] = true;
			break;
		case KEY_POUND:
			keyEvent = S_KEY_POUND;
			break;
		case KEY_STAR:
			keyEvent = S_KEY_STAR;
			break;
		default:
			switch ( getGameAction(keyCode) )
			{
			case FIRE:
				keyEvent = S_KEY_FIRE;
				break;
			case UP:
				keyEvent = S_KEY_UP;
				keyState[0] = true;
				break;
			case DOWN:
				keyEvent = S_KEY_DOWN;
				keyState[1] = true;
				break;
			case LEFT:
				keyEvent = S_KEY_LEFT;
				keyState[2] = true;
				break;
			case RIGHT:
				keyEvent = S_KEY_RIGHT;
				keyState[3] = true;
				break;
			}
		}
		fireKeyPressed();
	}
	
	public void keyReleased(int keyCode)
	{
		switch ( keyCode )
		{
		case KEY_NUM0:
			keyState[4] = false;
			break;
		case KEY_NUM1:
			keyState[5] = false;
			break;
		case KEY_NUM2:
			keyState[6] = false;
			break;
		case KEY_NUM3:
			keyState[7] = false;
			break;
		case KEY_NUM4:
			keyState[8] = false;
			break;
		case KEY_NUM5:
			keyState[9] = false;
			break;
		case KEY_NUM6:
			keyState[10] = false;
			break;
		case KEY_NUM7:
			keyState[11] = false;
			break;
		case KEY_NUM8:
			keyState[12] = false;
			break;
		case KEY_NUM9:
			keyState[13] = false;
			break;
		default:
			switch ( getGameAction(keyCode) )
			{
			case UP:
				keyState[0] = false;
				break;
			case DOWN:
				keyState[1] = false;
				break;
			case LEFT:
				keyState[2] = false;
				break;
			case RIGHT:
				keyState[3] = false;
				break;
			}
		}
		fireKeyReleased();
	}	

	public void fireKeyPressed() {}
	public void fireKeyReleased() {}
	
	public void browser(String url)
	{
		try
		{
			app.platformRequest(url);
		}
		catch ( Exception e )
		{
		}
	}
	
	public void lock()
	{
	}
	
	public void unlock()
	{
		//flushGraphics(screenX, screenY, SCREEN_WIDTH, SCREEN_HEIGHT);
		//flushGraphics();
		repaint();
		serviceRepaints();
	}

	public void paint(Graphics g2)
	{
		g2.drawImage(offscreen, 0, 0, G_POS);
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
		g.drawImage(image[imageNo], screenX + x, screenY + y, G_POS);
	}

	public void drawTitle(int x, int y)
	{
		if ( title != null )
		{
			g.drawImage(title, screenX + x, screenY + y, G_POS);
		}
	}
	
	public void drawTitle(int sx, int sy, int dx, int dy, int w, int h)
	{
		g.drawRegion(title, sx, sy, w, h, Sprite.TRANS_NONE, screenX + dx, screenY + dy, G_POS);
	}

	public void drawImage(int imageNo, int sx, int sy, int dx, int dy, int w, int h)
	{
		if ( image[imageNo] == null )
		{
			try{
				loadImage(imageNo);
			}catch(Exception e){}
		}
		g.drawRegion(image[imageNo], sx, sy, w, h, Sprite.TRANS_NONE, screenX + dx, screenY + dy, G_POS);
	}

	public void drawString(String msg, int x, int y)
	{
		g.drawString(msg, screenX + x, screenY + y, G_POS);
	}
	
	public void drawRect(int x, int y, int w, int h)
	{
		g.drawRect(screenX + x, screenY + y, w, h);
	}
	
	public void setColor(int r, int gg, int b)
	{
		g.setColor(r,gg,b);
	}
	
	public void setFont(int size)
	{
		if ( S_FONT_SMALL == size )
		{
			font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		}
		else if ( S_FONT_LARGE == size )
		{
			font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
		}
		else
		{
			font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
		}
		fontWidth = font.stringWidth("A");
		wideFontWidth = font.stringWidth("あ");
		fontHeight = font.getHeight();
		wordSpace = font.stringWidth("AA") - (fontWidth * 2);
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
			try
			{
				stopBGM();
			} catch ( Exception ignore ) {}
			app.notifyDestroyed();
		} catch ( Exception ignore ) {}
	}
	
	public static boolean isRecord(String key)
	{
		RecordStore rs = null;
		try
		{
			rs = RecordStore.openRecordStore(key, false);
			return rs != null;
		}
		catch ( Exception e )
		{
		}
		finally
		{
			close(rs);
		}
		return false;
	}

	public static String load(String key) throws Exception
	{
		RecordStore rs = null;
		try
		{
			rs = RecordStore.openRecordStore(key, false);
			if ( rs == null )
			{
				return null;
			}
			else
			{
				byte [] w = rs.getRecord(1);
				return new String(w);
			}
		}
		catch ( Exception e )
		{
			throw e;
		}
		finally
		{
			close(rs);
		}
	}
	
	public static void delete(String key) throws Exception
	{
		RecordStore.deleteRecordStore(key);
	}
	
	public static void save(String key, String value) throws Exception
	{
		RecordStore rs = null;
		try
		{
			byte[] w = value.getBytes();
			rs = RecordStore.openRecordStore(key, true);
			if ( rs.getNumRecords() == 0 )
			{
				rs.addRecord(w, 0, w.length);
			}
			else
			{
				rs.setRecord(1, w, 0, w.length);
			}
		}
		catch ( Exception e )
		{
			throw e;
		}
		finally
		{
			close(rs);
		}
	}

	public static void close(RecordStore rs)
	{
		try
		{
			if ( rs != null )
			{
				rs.closeRecordStore();
			}
		}
		catch ( Exception ignore) {}
	}
	
	public void loadBGM(int id) throws Exception
	{
		stopBGM();
		InputStream in = getClass().getResourceAsStream("/" + id + ".mid");
		bgm = Manager.createPlayer(in, "audio/midi");
		bgm.setLoopCount(-1);
		bgm.prefetch();
	}
	
	public void startBGM(int id, int mediaTime) throws Exception
	{
		if ( bgmEnable )
		{
			setBGMVolume(volume);
			bgm.setMediaTime(mediaTime);
			bgm.start();
		}
	}
	
	public void stopBGM() throws Exception
	{
		if ( bgm != null )
		{
			bgm.stop();
			bgm.close();
			bgm = null;
		}
	}

	public void loadSE(int id) throws Exception
	{
		if ( se != null )
		{
			stopSE();
		}
		InputStream in = getClass().getResourceAsStream("/" + id + ".mid");
		se = Manager.createPlayer(in, "audio/midi");
		se.setLoopCount(1);
		se.prefetch();
	}
	
	public void startSE() throws Exception
	{
		if ( seEnable && (se.getState() == Player.PREFETCHED) )
		{
			se.setMediaTime(0);
			setSEVolume(seVolume);
			se.start();
		}
	}
	
	public void pauseBGM() throws Exception
	{
		if ( bgmEnable && bgm != null )
		{
			bgm.stop();
		}
	}
	
	public void restartBGM() throws Exception
	{
		if ( bgmEnable && bgm != null )
		{
			bgm.start();
		}
	}

	public void stopSE() throws Exception
	{
		if ( se != null )
		{
			se.stop();
			se.close();
			se = null;
		}
	}
	
	public void setBGMVolume(int volume)
	{
		this.volume = volume;
		if ( bgm != null )
		{
			VolumeControl vc = (VolumeControl) bgm.getControl("VolumeControl");
			vc.setLevel(volume);
		}
	}
	
	public void setSEVolume(int seVolume)
	{
		this.seVolume = seVolume;
		if ( se != null )
		{
			VolumeControl vc = (VolumeControl) se.getControl("VolumeControl");
			vc.setLevel(seVolume);
		}
	}
	
	void vibrate(int ms)
	{
		if ( vibrateEnable )
		{
			Display.getDisplay(app).vibrate(ms);
		}
	}

	public String getCarrier()
	{
		return "au";
	}

	public String readTextFile(String fileName) throws Exception
	{
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try
		{
			in = getClass().getResourceAsStream("/" + fileName);
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
	
	public void updateTileImage(int id, int width, int height, int sw, int sh)
	{
		Image im = Image.createImage(getWidth(), getHeight());
		Graphics tmpG = im.getGraphics();
		int x = 0;
		int y = 0;
		while ( x < width || y < height )
		{
			tmpG.drawRegion(image[id], 0, 0, sw, sh, Sprite.TRANS_NONE, x, y, G_POS);
			if ( x >= width )
			{
				x = 0;
				y += sh;
			}
			else
			{
				x += sw;
			}
		}
		image[id] = im;
	}
	
	public String doGet(String url, String value) throws Exception
	{
		byte[] w = new byte[10240];
		int rc;
		int size;
		HttpConnection c = null;
		InputStream in = null;
		OutputStream out = null;
		ByteArrayOutputStream bout = null;
			
		try {
			url = url + "?v=" + GameUtil.urlEncode(value) + "&GUID=ON";
			//ネットと接続する
			c = (HttpConnection)Connector.open(url, Connector.READ_WRITE, true);
			c.setRequestMethod(HttpConnection.GET);
			c.setRequestProperty("Content-type", "text/plain");
			/*
			  c.setRequestMethod(HttpConnection.POST);
			  c.setRequestProperty("Content-type", "text/plain");
			  out = c.openOutputStream();
			  out.write(value.getBytes());
			  out.close();
			*/
				
			in = c.openInputStream();
			bout = new ByteArrayOutputStream();

			//レスポンスコードのチェック
			rc = c.getResponseCode();
			if ( rc!=HttpConnection.HTTP_OK )
			{
				throw new Exception("invalid http request");
			}

			//データを読み込む
			while ( true )
			{
				size = in.read(w);
				if ( size <= 0 ) break;
				bout.write(w,0,size);
			}

			c.close();
			return new String(bout.toByteArray());
		}
		finally
		{
			IOUtil.close(in);
			IOUtil.close(out);
			if ( c != null )
			{
				try
				{
					c.close();
				} catch ( Exception ignore ) {}
			}
			IOUtil.close(bout);
		}
	}

	public abstract int getBGMSplit(int id);
}
