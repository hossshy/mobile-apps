/*
 * Last modified: 2009/09/24 22:00:17
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class BurstData extends CharacterData
{
	private boolean wind = false;

	public void copy(BurstData parent)
	{
		super.copy(parent);
		this.wind = parent.wind;
	}
	
	public void onWind()
	{
		this.wind = true;
	}
	
	public void move(MainCanvas g)
	{
		if ( alive )
		{
			counter++;

			if ( wind )
			{
				fixY += 2024;
			}
			
			if ( counter >= image.size() )
			{
				destroy();
			}
			else if ( counter > 0 )
			{
				roleImagePattern();
			}
		}
	}
}
