/*
 * Last modified: 2009/02/18 07:11:12
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Menu;
import com.strnet.game.main.AbstractCanvas;

public class SkinList extends Menu
{
	private int[] needPoints;

	public SkinList(int row, int[] needPoints)
	{
		super(1, row, false);
		this.needPoints = needPoints;
	}
	
	public void setSkinId(int skinId)
	{
		setId(skinId, 0);
	}
	
	public int getNeedPoint(int id)
	{
		return needPoints[id];
	}
	
	// moved 1
	// not moved 0
	// need point up 1
	public int move(AbstractCanvas g, int point)
	{
		int tmpId = getId();
		switch ( g.getKeyEvent() )
		{
		case AbstractCanvas.S_KEY_UP:
			up();
			break;
		case AbstractCanvas.S_KEY_DOWN:
			
			if ( getId() == (needPoints.length - 1))
			{
				return 0;
			}

			int next = getId() + 1;
			if ( ( next <= (needPoints.length - 1) ) &&
				 ( point >= getNeedPoint(next) ) )
			{
				down();
			}
			else
			{
				return getNeedPoint(next);
			}
			break;
		}
		return (getId() != tmpId) ? 1 : 0;
	}
}
