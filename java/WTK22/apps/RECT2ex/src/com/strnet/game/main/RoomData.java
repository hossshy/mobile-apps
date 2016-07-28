/*
 * Last modified: 2009/05/20 22:36:11
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import java.util.Hashtable;

/**
 * 部屋に対する次の行き先を表すクラス
 */
public class RoomData
{
	/** 部屋情報 */
	private static Hashtable rooms = new Hashtable();

	/** この部屋の矢印の先の部屋番号 */
	private int[] roomAllow;

	/**
	 * 部屋を追加します。
	 * codeサンプル：-1:-1:3:0
	 * @param roomId 追加する部屋番号
	 * @param code 上下左右に対する次の部屋番号群。行き先が無い場合は-1
	 */
	static void addRooms(int roomId, String code)
	{
		rooms.put(new Integer(roomId), new RoomData(code));
	}
	
	/**
	 * 指定した部屋・矢印の部屋IDを取得します。
	 * @param roomId 現在の部屋番号
	 * @param 矢印（上下左右コード）
	 * @return 次の部屋ID
	 */
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