/*
 * Last modified: 2009/06/15 15:32:28
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
import com.nttdocomo.ui.Image;
import com.nttdocomo.ui.AudioPresenter;
import com.nttdocomo.ui.MediaListener;
import com.nttdocomo.ui.MediaManager;
import com.nttdocomo.ui.MediaPresenter;
import com.nttdocomo.ui.MediaSound;
import com.nttdocomo.util.JarInflater;

import com.nttdocomo.io.HttpConnection;

//import com.nttdocomo.ui.*;
import javax.microedition.io.*;
import java.io.*;

public class Main extends IApplication
{
	MainCanvas c = null;
	public void start()
	{
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
	
	protected void destroyApp(boolean flag)
	{
		if ( c != null )
		{
			c.terminate();
		}
	}
}

abstract class ModelDependenceCanvas extends Canvas implements Runnable
{
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

	public static final int S_FONT_SMALL = 0;
	public static final int S_FONT_MEDIUM = 1;
	public static final int S_FONT_LARGE = 2;
	public static String dx = ";pos=8";
	public static String RESOURCE_SIZE_URL = "scratchpad:///0;pos=8092";
	public static String RESOURCE_URL = "scratchpad:///0;pos=8102";

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
	MediaSound[] se = null;
	int volume = 50;
	int nowBGM = 0;
	boolean bgmEnable = true;
	boolean seEnable = true;
	AudioPresenter[] audio = null;
	
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
			setVolume(50, bgm);
			initFlag = true;
		}

	}

	void loadSE(int max) throws Exception
	{
		if ( se == null )
		{
			se = new MediaSound[max + 1];
			for ( int i = 0; i <= max; i++ )
			{
				try
				{
					se[i] = MediaManager.getSound(getFileResource(i + ".wav"));
					se[i].use();
				} catch ( Exception ignore ) {}
			}
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
		image[i].dispose();
		image[i] = null;
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
				break;
				
			case Display.KEY_SOFT2:
				doCommandAction(softkey[1]);
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
			close(in);
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
			close(in);
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
			close(out);
		}
	}
	
	void startBGM(int id, int mediaTime) throws Exception
	{
		if ( bgmEnable )
		{
			MediaSound tmp = MediaManager.getSound(getFileResource(id + ".mid"));
			tmp.use();
			try
			{
				stopBGM();
			} catch ( Exception ignore ) {}
			bgm.setSound(tmp);
		}
	}
	
	void stopBGM() throws Exception
	{
		if ( bgm != null )
		{
			bgm.getMediaResource().dispose();
		}
	}

	void startSE(int id) throws Exception
	{
		if ( seEnable )
		{
			//TODO
		}
	}
	
	void stopSE(int id) throws Exception
	{
		//TODO
	}

	void setVolume(int volume, AudioPresenter audio)
	{
		this.volume = volume;
		if ( audio != null )
		{
			audio.setAttribute(AudioPresenter.SET_VOLUME, volume);
		}
	}
	
	public void addRank(String uid, int score)
	{	   
	}
	
	public void viewRank()
	{
	}

	public String getCarrer()
	{
		return "i";
	}



	JarInflater archive = null;
	
	public boolean setArchive()
	{
		g = getGraphics();
		setColor(255,0,0);
		int len = getArchiveSize();
		drawString("Check " + len,0,12);

		try
		{
			if ( len <= 0 )
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
			close(buf);
			close(in);
		}
	}

	public InputStream getFileResource(String fileName) throws Exception
	{
		return archive.getInputStream(fileName);
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
			close(in);
		}
	}

	private static void close(InputStream in)
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

	private static void close(OutputStream out)
	{
		if ( out != null )
		{
			try
			{
				out.close();
			}
			catch ( Exception ignore ) {}
		}
	}
	
	private void saveArchive(byte[] data) throws Exception
	{
		DataOutputStream out = null;
		try
		{
			out = Connector.openDataOutputStream(RESOURCE_URL);
			out.write(data);
			out.close();
			out = Connector.openDataOutputStream(RESOURCE_SIZE_URL);
			out.writeInt(data.length);
		}
		finally 
		{
			if ( out != null )
			{
				try
				{
					out.close();
				}
				catch ( Exception ignore ) {}
			}
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
			close(buf);
			close(in);
			if ( con != null )
			{
				try
				{
					con.close();
				}
				catch ( Exception ignore ) {}
			}
		}
	}
}
