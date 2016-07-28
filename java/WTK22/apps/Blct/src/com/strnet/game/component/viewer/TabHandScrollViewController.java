/*
 * Last modified: 2010/03/23 23:26:28
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.main.GameCommonCanvas;
import com.strnet.game.common.list.StringLightList;
import java.util.Vector;

public class TabHandScrollViewController extends HandScrollViewController
{
	private HandScrollViewController[] viewControllers = null;
	private String[] titles = null;
	private int index;
	public void setAll(StringLightList msgs, int x, int startY, int addY, Color color)
	{
		Vector v = new Vector();
		Vector titleV = new Vector();
		HandScrollViewController vc = null;
		StringLightList list = null;

		for ( int i = 0; i < msgs.size(); i++ )
		{
			String msg = msgs.get(i);
			if ( (msg.length() > 0) && msg.charAt(0) == '=' )
			{
				if ( vc == null )
				{
					vc = new HandScrollViewController();
					list = new StringLightList();
					titleV.addElement(msg.substring(1));
				}
				else
				{
					vc.setAll(list, x, startY, addY, color);
					v.addElement(vc);

					vc = new HandScrollViewController();
					list = new StringLightList();
					titleV.addElement(msg.substring(1));
				}
			}
			else
			{
				list.add(msg);
			}
		}
		// last
		vc.setAll(list, x, startY, addY, color);
		v.addElement(vc);
		
		viewControllers = new HandScrollViewController[v.size()];
		titles = new String[titleV.size()];
		for ( int i = 0; i < viewControllers.length; i++ )
		{
			titles[i] = (String) titleV.elementAt(i);
			viewControllers[i] = (HandScrollViewController) v.elementAt(i);
		}
		
		v = null;
		titleV = null;
		index = 0;
	}

	public void move(GameCommonCanvas g)
	{
		viewControllers[index].move(g);
		if ( g.getKeyEvent() == g.S_KEY_LEFT && (index > 0) )
		{
			index--;
		}
		else if ( g.getKeyEvent() == g.S_KEY_RIGHT && (index < (viewControllers.length - 1)) )
		{
			index++;
		}
	}
	
	public boolean paintAll(GameCommonCanvas g)
	{
		g.setColor(Color.GREEN);
		g.drawString(titles[index], 60, 20);
		viewControllers[index].paintAll(g);

		g.setColor(Color.LIGHT_GRAY);
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		sb.append(index + 1);
		sb.append('/');
		sb.append(viewControllers.length);
		sb.append(']');
		g.drawString(sb.toString(), 180, 20);
		

		if ( index > 0 )
		{
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("Å©", 20, 20);
		}
		if ( index < (viewControllers.length - 1) )
		{
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("Å®", 210, 20);
		}

		return true;
	}
}