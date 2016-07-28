import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Displayable;

public abstract class AbstractCanvas extends GameCanvas implements Runnable, CommandListener
{
	private static final int G_POS = Graphics.LEFT|Graphics.TOP;
	static final int S_KEY_FIRE = 1;
	static final int S_KEY_UP = 2;
	static final int S_KEY_DOWN = 3;
	int screenX = 0;
	int screenY = 0;
	Image[] image = null;
	Command[] command;
	Graphics g = null;
	int keyState;
	int keyEvent = -1;
	State state;
	Main app;
	int scene;

	public AbstractCanvas(Main app)
	{
		super(false);
		this.app = app;
	}

	void init()
	{
		command = new Command[4];
		command[0] = new Command("±²ÃÑ", Command.SCREEN, 1);
		command[1] = new Command("I—¹", Command.EXIT, 2);
		command[2] = new Command("•Â", Command.SCREEN, 1);
		command[3] = new Command("", Command.SCREEN, 1);
		if ( g == null )
		{			
			g = getGraphics();
		}
		if ( getWidth() > 240 )
			screenX = (getWidth() - 240) / 2;
		if ( getHeight() > 268 )
			screenY = (getHeight() - 268) / 2;
	}
	
	void loadImage(int maxImage) throws Exception
	{
		if ( image == null )
		{
			image = new Image[maxImage + 1];
			for ( int i = 0; i <= maxImage; i++ )
			{
				image[i] = Image.createImage("/" + i + ".png");
			}
		}
	}
	
	void setKeyState()
	{
		keyState = getKeyStates();
	}

	boolean isUp()
	{
		return (UP_PRESSED & keyState) != 0;
	}
	
	boolean isDown()
	{
		return (DOWN_PRESSED & keyState) != 0;
	}
	
	boolean isLeft()
	{
		return (LEFT_PRESSED & keyState) != 0;
	}
	
	boolean isRight()
	{
		return (RIGHT_PRESSED & keyState) != 0;
	}
	
	boolean isKeyPress()
	{
		return (UP_PRESSED & keyState) != 0 ||
			(DOWN_PRESSED & keyState) != 0 ||
			(LEFT_PRESSED & keyState) != 0 ||
			(RIGHT_PRESSED & keyState) != 0;	
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
			}
		}
	}
	
	abstract void doCommandAction(int i);
	public abstract void terminate();

	public void keyPressed(int keyCode)
	{
		if (getGameAction(keyCode) == FIRE) keyEvent = S_KEY_FIRE;
		else if (getGameAction(keyCode) == UP) keyEvent = S_KEY_UP;
		else if (getGameAction(keyCode) == DOWN) keyEvent = S_KEY_DOWN;
	}


	public void lock()
	{
	}
	
	public void unlock()
	{
		flushGraphics();
	}
	
	public void fillRect(int x, int y, int w, int h)
	{
		g.fillRect(x, y, w, h);
	}

	public void drawImage(Image img, int x, int y)
	{
		g.drawImage(img, screenX + x, screenY + y, G_POS);
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
	
	public void setFont()
	{
		g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL));
	}
}
