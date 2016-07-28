/*
 * Last modified: 2010/05/17 01:31:34
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;
import com.strnet.game.common.Rectangle;
import com.strnet.game.interpreter.CommandListNode;
import com.strnet.game.interpreter.Interpreter;

public class CharacterData extends Rectangle
{
	static final int ATTR_SHOW_NORMAL = 10;
	static final int ATTR_SHOW_ALL = 11;
	static final int ATTR_SHOW_DARKONLY = 12;
	static final int ATTR_SHOW_MYITEM = 13;
	static final int ATTR_SHOW_MYITEM_DETAIL = 14;
	static final int ATTR_SHOW_FLAG = 15;
	static final int ATTR_SHOW_ALLOW = 16;
	
	static final int STATE_SHOW = 1;
	static final int STATE_HIDE = 2;

	Rectangle imgSrc;
	Point imgDest = null;
	int state;
	int attribute;
	int speed = 0;
	int id;
	int imageId;
	int roomId;
	int rootId = -1;
	CommandListNode listNode = null;

	// CursorData AllowData
	public CharacterData(int x, int y, int width, int height, int imageId, Rectangle imgSrc)
	{
		this(x, y, width, height, imageId, 0, ATTR_SHOW_NORMAL, STATE_SHOW, -1, imgSrc, null, -1);
	}
	
	// normal item
	public CharacterData(int id, int imageId, int roomId, int x, int y, int sx, int sy, int width, int height, String code, int show, int rootId)
	{
		this(x, y, width, height, imageId, roomId, ATTR_SHOW_NORMAL, show, id, new Rectangle(sx,sy,width,height), code, rootId);
	}

	// item window
	public CharacterData(int id, int imageId, int roomId, int x, int y, int sx, int sy, int width, int height, String code, int show, int rootId, int attribute)
	{
		this(x, y, width, height, imageId, roomId, attribute, show, id, new Rectangle(sx,sy,width,height), code, rootId);
	}

	// ItemData
	public CharacterData(int x, int y, int width, int height, int imageId, int roomId, int attribute, int state, int id, Rectangle imgSrc, String code, int rootId)
	{
		super(x, y, width, height);
		this.imgSrc = imgSrc;
		this.imageId = imageId;
		this.roomId = roomId;
		this.attribute = attribute;
		this.state = state;
		this.id = id;
		this.imgSrc = imgSrc;
		this.rootId = rootId;
		
		if ( (code != null) && (code.length() > 0) )
		{
			setListNode(Interpreter.parse(code));
		}
	}
	
	public CharacterData(CharacterData cd)
	{
		this(cd.x,cd.y,cd.width,cd.height,cd.imageId,cd.roomId,cd.attribute,cd.state,cd.id, cd.imgSrc, null, cd.rootId);
		setListNode(cd.listNode);
	}
	
	public int getDestX()
	{
		return (imgDest == null) ? x : imgDest.x;
	}
	
	public int getDestY()
	{
		return (imgDest == null) ? y : imgDest.y;
	}

	public void setListNode(CommandListNode listNode)
	{
		this.listNode = listNode;
	}

	public String toString()
	{
		return id + "." + state;
	}
}
