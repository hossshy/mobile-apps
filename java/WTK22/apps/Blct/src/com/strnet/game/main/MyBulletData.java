/*
 * Last modified: 2009/12/14 20:15:28
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;

public class MyBulletData extends CharacterData
{
	protected int vx;
	protected int vy;
	protected int damage;
	protected boolean laser = false;
	
	public void onLaser()
	{
		this.laser = true;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public int getDamage()
	{
		return damage;
	}

	public void copy(MyBulletData parent)
	{
		super.copy(parent);
		this.damage = parent.damage;
		this.laser = parent.laser;
	}
	
	public void setFixPosition(int fixX, int fixY)
	{
		this.fixX = fixX - FixedPoint.toFixedInt(image.getRectangle(0).width / 2);
		this.fixY = fixY - FixedPoint.toFixedInt(image.getRectangle(0).height / 2);
	}

	public void setAngle(int fixAngle)
	{
		vx = FixedPoint.multiply(fixSpeed, FixedPoint.cos(fixAngle));
		vy = FixedPoint.multiply(fixSpeed, FixedPoint.sin(fixAngle));
	}
	
	public void move(MainCanvas g)
	{
		if ( laser )
		{
			fixX = g.my.getCenterFixX() - FixedPoint.toFixedInt(image.getRectangle(0).width / 2);
		}
		else
		{
			fixX += vx;
		}
		fixY += vy;
		checkScreenRange();
		counter++;
		if ( laser || (counter % 2 == 0) )
		{
			roleImagePattern();
		}
	}
}
