/*
 * Last modified: 2008/09/05 17:17:31
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import java.util.Hashtable;

public class RoomData
{
	private static Hashtable rooms = new Hashtable();

	private int[] roomAllow;
	
	static void addRooms(int roomId, String code)
	{
		rooms.put(new Integer(roomId), new RoomData(code));
	}

	public static int get(int roomId, int i)
	{
		RoomData rd = (RoomData) rooms.get(new Integer(roomId));
		return rd.roomAllow[i];
	}
	
	public RoomData(String code)
	{
		String[] tmp = GameUtil.split(code, ':');
		roomAllow = new int[tmp.length];
		for ( int i = 0; i < roomAllow.length; i++ )
		{
			roomAllow[i] = Integer.parseInt(tmp[i]);
		}
	}
}