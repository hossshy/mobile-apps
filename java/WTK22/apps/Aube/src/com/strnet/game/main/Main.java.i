/*
 * Last modified: 2008/09/09 12:19:37
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
import com.nttdocomo.ui.*;
import javax.microedition.io.*;
import java.io.*;

public class Main extends IApplication
{
	MainCanvas c = null;
	public void start()
	{
		//AppgetDx.check(0);

		c = new MainCanvas(this);
		
		Display.setCurrent(c);
		(new Thread(c)).start();
		//c.run();
		c.init();
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

	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	String[] labels;
	Graphics g = null;
	int keyState;
	int keyEvent = -1;
	Main app;
	public int fontWidth;
	public int fontHeight;
	int[] softkey = new int[]{0, 0};
	boolean initFlag = false;
	int loadedCounter = 0;

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
			initFlag = true;
		}

	}
	
	void loadImage(int maxImage) throws Exception
	{
		if ( image == null )
		{
			image = new Image[maxImage + 1];
			for ( int i = 0; i <= maxImage; i++ )
			{
				try
				{
					MediaImage mi = MediaManager.getImage("resource:///" + i + ".gif");
					mi.use();
					image[i] = mi.getImage();
					loadedCounter++;
				} catch ( Exception ignore ) {}
			}
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
		else
		{
			f = Font.getFont(Font.FACE_MONOSPACE | Font.SIZE_MEDIUM);
		}
		fontWidth = f.stringWidth("A");
		fontHeight = f.getHeight();
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
			in = Connector.openDataInputStream("scratchpad:///0;pos=8");
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
			in = Connector.openDataInputStream("scratchpad:///0;pos=8");
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
			out = Connector.openDataOutputStream("scratchpad:///0;pos=8");
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
}
