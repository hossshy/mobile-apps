/*
 * Last modified: 2008/07/05 21:57:26
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

import com.strnet.game.main.AbstractCanvas;

public interface Painter
{
	public void paint(AbstractCanvas g, int x, int y, int width, int height);
	public int getRevise();
}
