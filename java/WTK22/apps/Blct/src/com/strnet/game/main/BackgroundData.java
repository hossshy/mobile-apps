/*
 * Last modified: 2009/12/27 15:03:13
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.GameUtil;

public class BackgroundData
{
	private int nextImageId;
	private int secondImageId;
	private int imageId;
	private int counter;
	private int speed;
	private int width;
	private int height;
	private boolean canPaint = true;

	public BackgroundData(int width, int height)
	{
		this.width = width;
		this.height = height;
		speed = 0;
		imageId = -1;
		nextImageId = -1;
		canPaint = true;
	}
	
	public void setCanPaint(boolean canPaint)
	{
		this.canPaint = canPaint;
	}
	
	public boolean canPaint()
	{
		return canPaint;
	}
	
	public void reset()
	{
		counter = 0;
		secondImageId = imageId;
		nextImageId = imageId;
	}
	
	public void setImageId(int imageId)
	{
		this.imageId = imageId;
		secondImageId = imageId;
	}

	public void setNextImageId(int nextImageId)
	{
		this.nextImageId = nextImageId;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void execute()
	{
		counter += speed;
		if ( counter > height )
		{
			counter -= height;
			imageId = secondImageId;
			if ( nextImageId >= 0 )
			{
				secondImageId = nextImageId;
				nextImageId = -1;
			}
		}
	}

	public void paint(MainCanvas g, int x, int y)
	{
		if ( canPaint )
		{
			g.drawImage(secondImageId, 0,0, x, y + (-height + counter), width, height);
			g.drawImage(imageId, 0,0, x, y + counter, width, height);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(MainCanvas.PLAY_SCREEN_X, MainCanvas.PLAY_SCREEN_Y, MainCanvas.PLAY_SCREEN_WIDTH, MainCanvas.PLAY_SCREEN_HEIGHT);
		}
	}
}
