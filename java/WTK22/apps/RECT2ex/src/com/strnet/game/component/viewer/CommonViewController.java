/*
 * Last modified: 2010/03/24 00:36:06
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.common.list.StringLightList;
import com.strnet.game.main.GameCommonCanvas;

public abstract class CommonViewController
{
	protected StateObject[] sv;
	public void move(GameCommonCanvas g) {}
	
	public void setAll(StringLightList msgs, int x, int startY, int addY, Color color)
	{
		sv = new StateObject[msgs.size()];
		for ( int i = 0; i < msgs.size(); i++ )
		{
			String msg = msgs.get(i);
			if ( (msg.length() > 0) && msg.charAt(0) == '@' )
			{
				sv[i] = new StateImage();
				sv[i].set(msg, 0, 0, color);
			}
			else if ( (msg.length() > 0) && msg.charAt(0) == '`' )
			{
				sv[i] = new StateTitleImage();
				sv[i].set(msg, 70, startY + (i * addY), color);
			}
			else
			{
				sv[i] = new StateString();
				sv[i].set(msg, x, startY + (i * addY), color);
			}
		}
	}
	public abstract boolean paintAll(GameCommonCanvas g);
}