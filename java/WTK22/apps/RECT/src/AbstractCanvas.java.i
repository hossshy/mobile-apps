import com.nttdocomo.ui.Canvas;
import com.nttdocomo.ui.Display;
import com.nttdocomo.ui.Font;
import com.nttdocomo.ui.Graphics;
import com.nttdocomo.ui.MediaManager;
import com.nttdocomo.ui.MediaImage;
import com.nttdocomo.ui.Image;
import com.nttdocomo.ui.SoftKeyListener;

public abstract class AbstractCanvas extends Canvas implements SoftKeyListener
{
	static final int S_KEY_FIRE = 1;
	static final int S_KEY_UP = 2;
	static final int S_KEY_DOWN = 3;
	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	String[] command;
	Graphics g = null;
	int keyState;
	int keyEvent = -1;
	State state;
	Main app;
	int scene;
	int[] softkey = new int[]{0, 0};

	public AbstractCanvas()
	{
		this.app = app;
	}

	void init()
	{
		command = new String[4];
		command[0] = "±²ÃÑ";
		command[1] = "I—¹";
		command[2] = "•Â";
		command[3] = "";
		if ( g == null )
		{			
			g = getGraphics();
		}
		if ( Display.getWidth() > 240 )
			screenX = (Display.getWidth() - 240) / 2;
		
		if ( Display.getHeight() > 268 )
			screenY = (Display.getHeight() - 268) / 2;

	}
	
	void loadImage(int maxImage) throws Exception
	{
		if ( image == null )
		{
			image = new Image[maxImage + 1];
			for ( int i = 0; i <= maxImage; i++ )
			{
				MediaImage mi = MediaManager.getImage("resource:///" + i + ".gif");
				mi.use();
				image[i] = mi.getImage();
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
		return (Display.KEY_UP & keyState) != 0;
	}
	
	boolean isDown()
	{
		return (Display.KEY_DOWN & keyState) != 0;
	}
	
	boolean isLeft()
	{
		return (Display.KEY_LEFT & keyState) != 0;
	}
	
	boolean isRight()
	{
		return (Display.KEY_RIGHT & keyState) != 0;
	}
	
	boolean isKeyPress()
	{
		return (Display.KEY_UP & keyState) != 0 ||
			(Display.KEY_DOWN & keyState) != 0 ||
			(Display.KEY_LEFT & keyState) != 0 ||
			(Display.KEY_RIGHT & keyState) != 0;	
	}
	
	void removeCommands()
	{
		for ( int i = 0; i < command.length; i++ )
		{
			setSoftLabel(null);
		}
	}
	
	void addCommand(int key, int i)
	{
		softkey[key] = i;
		setSoftLabel(key, command[i]);
	}
	
	public void commandAction()
	{
		for ( int i = 0; i < command.length; i++ )
		{
			if ( c == command[i] )
			{
				doCommandAction(i);
			}
		}
	}

	public void softKeyPressed(int s)
	{
		doCommandAction(softkey[s]);
	}

	public void softKeyReleased(int softKey) {}
	
	abstract void doCommandAction(int i);
	public abstract void terminate();

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
			}
		}
	}

	public void lock()
	{
		g.lock();
	}
	
	public void unlock()
	{
		g.unlock();
	}
	
	public void fillRect(int x, int y, int w, int h)
	{
		g.fillRect(x, y, w, h);
	}

	public void drawImage(Image img, int x, int y)
	{
		g.drawImage(img, screenX + x, screenY + y);
	}
	
	public void drawString(String msg, int x, int y)
	{
		g.drawString(msg, screenX + x, screenY + y);
	}
	
	public void drawRect(int x, int y, int w, int h)
	{
		g.drawRect(screenX + x, screenY + y, w, h);
	}
	
	public void setColor(int r, int gg, int b)
	{
		g.setColor(g.getColorOfRGB(r,gg,b));
	}
	
	public void setFont()
	{
		g.setFont(Font.getFont(Font.FACE_MONOSPACE | Font.SIZE_SMALL));
	}
}
