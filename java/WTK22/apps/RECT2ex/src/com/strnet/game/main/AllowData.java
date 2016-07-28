/*
 * Last modified: 2009/06/10 00:29:48
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;

public class AllowData extends CharacterData
{
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	int nextRoomId = -1;

	public AllowData(AllowData ad)
	{
		super(ad);
		this.nextRoomId = ad.nextRoomId;
	}

	public AllowData(int imageId, int x, int y, int width, int height, Rectangle imgSrc)
	{
		super(x, y, width, height, imageId, 0, ATTR_SHOW_ALLOW, STATE_HIDE, -1, imgSrc, null, -1);
	}
}