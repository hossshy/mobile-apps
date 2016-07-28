/*
 * Last modified: 2010/03/25 13:00:09
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.ImagePatternData;

public abstract class CharacterData
{
	// スクリーンリアル座標
	private static int screenX;
	private static int screenY;
	private static int screenWidth;
	private static int screenHeight;
	private static int screenEndX;
	private static int screenEndY;
	
	// スクリーン固定小数座標
	protected static int screenFixMinX;
	protected static int screenFixMinY;
	protected static int screenFixMaxX;
	protected static int screenFixMaxY;

	// キャラクターの位置・大きさ（固定小数）
	protected int fixX = 0;
	protected int fixY = 0;
	protected int fixWidth = 0;
	protected int fixHeight = 0;
	protected int halfFixWidth = 0;
	protected int halfFixHeight = 0;
	
	protected int fixSpeed;
	protected ImagePatternData image;
	protected boolean alive = false;

	protected int aliveScreenFixMinX;
	protected int aliveScreenFixMinY;
	protected Rectangle collision;

	protected int counter;
	protected int imagePattern;

	public void copy(CharacterData parent)
	{
		this.image = parent.image;
		this.collision = parent.collision;
		this.fixWidth = parent.fixWidth;
		this.fixHeight = parent.fixHeight;
		this.halfFixWidth = parent.halfFixWidth;
		this.halfFixHeight = parent.halfFixHeight;
		this.aliveScreenFixMinX = parent.aliveScreenFixMinX;
		this.aliveScreenFixMinY = parent.aliveScreenFixMinY;
		this.imagePattern = parent.imagePattern;
		this.fixSpeed = parent.fixSpeed;
		counter = 0;
	}

	public void setCollision(int collWidth, int collHeight)
	{
		int sx = FixedPoint.toFixedInt((image.getRectangle(0).width - collWidth) / 2);
		int sy = FixedPoint.toFixedInt((image.getRectangle(0).height - collHeight) / 2);
		this.collision = new Rectangle(sx, sy, FixedPoint.toFixedInt(collWidth), FixedPoint.toFixedInt(collHeight));
	}
	
	public boolean isHit(CharacterData other)
	{
		return (fixX + collision.x + collision.width >= other.fixX + other.collision.x) &&
			(fixX + collision.x <= (other.fixX + other.collision.x + other.collision.width)) &&
			(fixY + collision.y + collision.height >= other.fixY + other.collision.y) &&
			(fixY + collision.y <= (other.fixY + other.collision.y + other.collision.height));
	}

	public boolean isHitImage(CharacterData other)
	{
		return (fixX + fixWidth >= other.fixX + other.collision.x) &&
			(fixX <= (other.fixX + other.collision.x + other.collision.width)) &&
			(fixY + fixHeight >= other.fixY + other.collision.y) &&
			(fixY <= (other.fixY + other.collision.y + other.collision.height));
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void onAlive()
	{
		alive = true;
	}

	public void destroy()
	{
		alive = false;
	}

	public static void setScreen(int x, int y, int width, int height)
	{
		screenX = x;
		screenY = y;
		screenWidth = width;
		screenHeight = height;
		screenEndX = width + x;
		screenEndY = height + y;

		screenFixMinX = FixedPoint.toFixedInt(x);
		screenFixMinY = FixedPoint.toFixedInt(y);
		screenFixMaxX = FixedPoint.toFixedInt(x + width);
		screenFixMaxY = FixedPoint.toFixedInt(y + height);
	}

	public void setFixPosition(int fixX, int fixY)
	{
		this.fixX = fixX;
		this.fixY = fixY;
	}
	
	public void setPosition(int x, int y)
	{
		this.fixX = FixedPoint.toFixedInt(x);
		this.fixY = FixedPoint.toFixedInt(y);
	}

	public void setImage(ImagePatternData image)
	{
		this.image = image;
		fixWidth = FixedPoint.toFixedInt(image.getRectangle(0).width);
		fixHeight = FixedPoint.toFixedInt(image.getRectangle(0).height);
		halfFixWidth = FixedPoint.toFixedInt(image.getRectangle(0).width / 2);
		halfFixHeight = FixedPoint.toFixedInt(image.getRectangle(0).height / 2);
		
		aliveScreenFixMinX = screenFixMinX - fixWidth;
		aliveScreenFixMinY = screenFixMinY - fixHeight;
		
		preLoad();
	}
	
	public void preLoad()
	{
		MainCanvas.getInstance().preLoadImage(image.getImageId());
	}

	public void setSpeed(int speed)
	{
		fixSpeed = FixedPoint.toFixedInt(speed);
	}
	
	public void setFixSpeed(int fixSpeed)
	{
		this.fixSpeed = fixSpeed;
	}
	
	public int getFixSpeed()
	{
		return fixSpeed;
	}

	public int getFixX()
	{
		return fixX;
	}

	public int getFixY()
	{
		return fixY;
	}

	public int getCenterFixX()
	{
		return fixX + halfFixWidth;
	}

	public int getCenterFixY()
	{
		return fixY + halfFixHeight;
	}

	public void checkScreenRange()
	{
		if ( (fixX < aliveScreenFixMinX) ||
			 (fixX > screenFixMaxX) ||
			 (fixY < aliveScreenFixMinY) ||
			 (fixY > screenFixMaxY) )
		{
			alive = false;
		}
	}

	public void roleImagePattern()
	{
		roleImagePattern(image.size());
	}

	public void roleImagePattern(int max)
	{
		imagePattern = GameUtil.loopIncf(imagePattern, 0, max);
	}

	public void setLastImagePattern()
	{
		imagePattern = image.size() - 1;
	}


	public ImagePatternData getImagePatternData()
	{
		return image;
	}

	public Rectangle getImageRect()
	{
		return image.getRectangle(imagePattern);
	}

	public int getImageId()
	{
		return image.getImageId();
	}
	
	public abstract void move(MainCanvas g);

	public void paint(MainCanvas g)
	{
		int dx = FixedPoint.toInt(fixX);
		int dy = FixedPoint.toInt(fixY);
		Rectangle r = getImageRect();
		int sx = r.x;
		int sy = r.y;
		int w = r.width;
		int h = r.height;
		
		if ( (dx > screenEndX) || ((dx + w) < screenX) ||
			 (dy > screenEndY) || ((dy + h) < screenY) )
		{
			return;
		}

		if ( (dx + w) > screenEndX )
		{
			w = screenEndX - dx;
		}
		else if ( dx < screenX )
		{
			w += (dx - screenX);
			sx -= (dx - screenX);
			dx = screenX;
		}
		
		if ( (dy + h) > screenEndY )
		{
			h = screenEndY - dy;
		}
		else if ( dy < screenY )
		{
			h += (dy - screenY);
			sy -= (dy - screenY);
			dy = screenY;
		}
		
		g.drawImage(image.getImageId(), sx, sy, dx, dy, w, h);

		//debug
		/*
		g.setColor(Color.BLUE);
		if ( collision != null )
		{
			g.fillRect(FixedPoint.toInt(fixX + collision.x),
					   FixedPoint.toInt(fixY + collision.y),
					   FixedPoint.toInt(collision.width),
					   FixedPoint.toInt(collision.height));
		}
		*/
	}
}
