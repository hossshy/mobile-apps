/*
 * Last modified: 2010/03/25 12:30:10
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.jblend.media.smaf.phrase.Phrase;
import com.jblend.media.smaf.phrase.PhraseEventType;
import com.jblend.media.smaf.phrase.PhrasePlayer;
import com.jblend.media.smaf.phrase.PhraseTrack;
import com.jblend.media.smaf.phrase.PhraseTrackListener;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.media.Manager;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.rms.RecordStore;
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
	public static final String URL = "http://m.strnet.com/sb/";

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
	public static final int MAX_VOLUME = 128;
	
	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	Image title = null;
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
	PhrasePlayer phrasePlayer = null;
	Phrase[] phrase = new Phrase[4];
	PhraseTrack[] phraseTracks = new PhraseTrack[4];
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
			
			if ( phrasePlayer == null )
			{
				//init
				phrasePlayer = PhrasePlayer.getPlayer();
				for ( int i = 0; i < phraseTracks.length; i++ )
				{
					phraseTracks[i] = phrasePlayer.getTrack(i);
				}
			}
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
		Font font;
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
		wideFontWidth = font.stringWidth("‚ ");
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
		try
		{
			stopBGM();
			int split = getBGMSplit(id);
			String prefix = (hiSound) ? "ma5_" : "";
			for ( int i = 0; i < split; i++ )
			{
				String file = prefix + id + ".spf";
				if ( i > 0 )
				{
					file = prefix + id + "_"+ (i+1) + ".spf";
				}
				Phrase phrase = new Phrase("resource:" + file);
				phraseTracks[i].setPhrase(phrase);
			}
		
			if ( split >= 2 && phraseTracks[1] != null )
			{
				phraseTracks[1].setSubjectTo(phraseTracks[0]);
			}
			if ( split >= 3 && phraseTracks[2] != null )
			{
				phraseTracks[2].setSubjectTo(phraseTracks[0]);
			}
		}
		catch ( Exception ignore ){}

	}
	
	public void startBGM(int id, int mediaTime) throws Exception
	{
		try
		{
			if ( bgmEnable )
			{
				stopBGM();
				loadBGM(id);
				setBGMVolume(volume);
				phraseTracks[0].play(0);
			}
		}
		catch ( Exception ignore ){}

	}
	
	public void stopBGM() throws Exception
	{
		try
		{
			if ( (phraseTracks[0] != null) && (phraseTracks[0].getState() != PhraseTrack.NO_DATA) )
			{
				phraseTracks[0].stop();
				//phrasePlayer.kill();
				for ( int i = 0; i < 3; i++ )
				{
					phraseTracks[i].removePhrase();
				}
			}
		}
		catch ( Exception ignore ){}

	}

	public void loadSE(int id) throws Exception
	{
		try
		{
			stopSE();
			String prefix = (hiSound) ? "ma5_" : "";
			String file = prefix + id + ".spf";
			Phrase phrase = new Phrase("resource:" + file);
			phraseTracks[3].setPhrase(phrase);
		}
		catch ( Exception ignore ){}
	}
	
	public void startSE() throws Exception
	{
		try
		{	
			if ( seEnable && (phraseTracks[3].getState() != PhraseTrack.PLAYING) )
			{
				setSEVolume(seVolume);
				phraseTracks[3].play(1);
			}
		}
		catch ( Exception ignore ){}
	}
	
	public void pauseBGM() throws Exception
	{
		if ( bgmEnable && phraseTracks[0] != null )
		{
			phraseTracks[0].pause();
		}
	}
	
	public void restartBGM() throws Exception
	{
		if ( bgmEnable && phraseTracks[0] != null )
		{
			phraseTracks[0].resume();
		}
	}
	
	public void stopSE() throws Exception
	{
		try
		{
			if ( (phraseTracks[3] != null) && (phraseTracks[3].getState() != PhraseTrack.NO_DATA) )
			{
				phraseTracks[3].stop();
				phraseTracks[3].removePhrase();
			}
		}
		catch ( Exception ignore ){}

	}
	
	public void setBGMVolume(int volume)
	{
		this.volume = volume;
		setVolume(volume, phraseTracks[0]);
		setVolume(volume, phraseTracks[1]);
		setVolume(volume, phraseTracks[2]);
	}

	public void setSEVolume(int seVolume)
	{
		this.seVolume = seVolume;
		setVolume(seVolume, phraseTracks[3]);
	}

	public void setVolume(int volume, PhraseTrack p)
	{
		if ( p != null )
		{
			p.setVolume(volume);
		}
	}

	/*
	  public void eventOccurred(int event)
	  {
	  if (event == PhraseEventType.EV_END && phraseTracks[0] != null && phraseTracks[0].getState() == PhraseTrack.READY )
	  {
	  phraseTracks[0].play();
	  }
	  }
	*/
	void vibrate(int ms)
	{
		if ( vibrateEnable )
		{
			Display.getDisplay(app).vibrate(ms);
		}
	}

	public String getCarrier()
	{
		return "s";
	}

	public String readTextFile(String fileName) throws Exception
	{
		System.out.println("file:"+fileName);
		
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try
		{
			in = getClass().getResourceAsStream("/" + fileName);
			out = new ByteArrayOutputStream();

			byte[] buf = new byte[1];
			int len;
			int counter = 0;
			//while ( (len = in.read(buf, counter++, 1)) != -1 )
			while ( (len = in.read(buf,0,1)) != -1 )
			{
				out.write(buf, 0, len);
			}
			out.flush();
			in.close();
			System.out.println("file:"+fileName);
			return new String(out.toByteArray(), "SJIS");
			//return out.toString();
		}
		finally
		{
			IOUtil.close(in);
			IOUtil.close(out);
		}
	}
		
	public abstract int getBGMSplit(int id);
}
