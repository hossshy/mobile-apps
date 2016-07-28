/*
 * Last modified: 2009/05/20 22:36:11
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import java.util.Hashtable;

/**
 * �����ɑ΂��鎟�̍s�����\���N���X
 */
public class RoomData
{
	/** ������� */
	private static Hashtable rooms = new Hashtable();

	/** ���̕����̖��̐�̕����ԍ� */
	private int[] roomAllow;

	/**
	 * ������ǉ����܂��B
	 * code�T���v���F-1:-1:3:0
	 * @param roomId �ǉ����镔���ԍ�
	 * @param code �㉺���E�ɑ΂��鎟�̕����ԍ��Q�B�s���悪�����ꍇ��-1
	 */
	static void addRooms(int roomId, String code)
	{
		rooms.put(new Integer(roomId), new RoomData(code));
	}
	
	/**
	 * �w�肵�������E���̕���ID���擾���܂��B
	 * @param roomId ���݂̕����ԍ�
	 * @param ���i�㉺���E�R�[�h�j
	 * @return ���̕���ID
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