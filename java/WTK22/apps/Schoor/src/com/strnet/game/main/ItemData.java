/*
 * Last modified: 2008/09/05 11:45:43
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;

public class ItemData extends CharacterData
{
	int detailId;
	public ItemData(int id, int imageId, int x, int y, int width, int height, int detailId, String code)
	{
		super(0, 0, width, height, imageId, 0, ATTR_SHOW_MYITEM, STATE_HIDE, id, new Rectangle(x,y,width,height), code);
		this.detailId = detailId;
	}
}