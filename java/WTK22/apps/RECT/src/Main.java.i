import com.nttdocomo.ui.IApplication;
import com.nttdocomo.ui.Display;


public class Main extends IApplication
{
	MainCanvas c = null;
	public void start()
	{
		c = new MainCanvas(this);
		Display.setCurrent(c);
		b.run();
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
