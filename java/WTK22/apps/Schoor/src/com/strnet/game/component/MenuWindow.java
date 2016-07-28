/*
 * Last modified: 2008/10/06 23:50:47
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Menu;
import com.strnet.game.main.AbstractCanvas;

public class MenuWindow extends Menu
{
	private boolean visible;
	
	public MenuWindow(int maxCol, int maxRow, boolean endless)
	{
		super(maxCol, maxRow, endless);
		visible = true;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void move(AbstractCanvas g)
	{
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
	}
}
