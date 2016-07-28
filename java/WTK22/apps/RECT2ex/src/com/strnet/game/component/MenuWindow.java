/*
 * Last modified: 2009/02/02 22:22:07
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Menu;
import com.strnet.game.main.AbstractCanvas;

public class MenuWindow extends Menu
{
	private boolean visible;
	private boolean reset;

	public boolean isReset()
	{
		return reset;
	}

	public void setReset(boolean reset)
	{
		this.reset = reset;
	}

	
	public MenuWindow(int maxCol, int maxRow, boolean endless)
	{
		this(maxCol,maxRow,endless, false);
	}
	
	public MenuWindow(int maxCol, int maxRow, boolean endless, boolean reset)
	{
		super(maxCol, maxRow, endless);
		visible = true;
		this.reset = reset;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
		if ( reset && !visible )
		{
			resetId();
		}
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public boolean move(AbstractCanvas g)
	{
		int tmpId = getId();
		switch ( g.getKeyEvent() )
		{
		case AbstractCanvas.S_KEY_UP:
			up();
			break;
		case AbstractCanvas.S_KEY_DOWN:
			down();
			break;
		case AbstractCanvas.S_KEY_LEFT:
			left();
			break;
		case AbstractCanvas.S_KEY_RIGHT:
			right();
			break;
		}
		
		return getId() != tmpId;
	}
}
