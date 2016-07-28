/*
 * Last modified: 2008/07/05 21:57:13
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.common.Menu;
import com.strnet.game.main.AbstractCanvas;

public class MenuWindow
{
	private Menu menu;
	private boolean visible;
	
	public MenuWindow(int maxCol, int maxRow, boolean endless)
	{
		menu = new Menu(maxCol, maxRow, endless);
	}
	
	public int getId()
	{
		return menu.getId();
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
			menu.up();
			break;
		case AbstractCanvas.S_KEY_DOWN:
			menu.down();
			break;
		case AbstractCanvas.S_KEY_LEFT:
			menu.left();
			break;
		case AbstractCanvas.S_KEY_RIGHT:
			menu.right();
			break;
		}
	}
}
