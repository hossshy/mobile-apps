/*
 * Last modified: 2010/03/23 19:32:27
 * author: Kazamai, Kou
 */
package com.strnet.game.component.viewer;

import com.strnet.game.common.Color;
import com.strnet.game.main.GameCommonCanvas;
import com.strnet.game.common.list.StringLightList;

public class PreserveViewController extends CommonViewController
{
	protected static final Color subColor = new Color(4, 4, 4);

	public boolean paintAll(GameCommonCanvas g)
	{
		boolean painted = false;
		for ( int i = 0; i < sv.length; i++ )
		{
			if ( sv[i] != null )
			{
				int ret = sv[i].paint(g);
				if ( ret < 10 )
				{
					sv[i] = null;
				}
				else
				{
					if ( ret > 0 )
					{
						sv[i].y--;
					}
					if ( ret < 230 && ret > 150 )
					{
						sv[i].color.add(subColor);
					}
					else if ( ret < 110 )
					{
						sv[i].color.sub(subColor);
					}
					painted = true;
				}
			}
		}
		return painted;
	}
}