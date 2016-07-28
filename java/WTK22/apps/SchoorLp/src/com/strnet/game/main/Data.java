/*
 * Last modified: 2008/11/19 02:08:38
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;

import java.util.Vector;

public class Data
{
	// �Œ�� 1�A�N�V�����{ set go 2 
	// 14 normalend  17 bestend
	private static final String[] storyCode = new String[] {
		"set image 20 set msg �w�c�Ȃ�c���̖�����݂Ȃ����c�x set msg �w�c�܂��������̃��m�����ǁc�x set image -1 set msg �c�c�c set msg �c�c�c�c set msg �c�c���c��c set msg �c�c�܂��c�c�����c�H set play 0",//0
		"set msg �c set msg �c�L�����c set image 7 set msg �w���Ȃ��̗��l�́c�����c�c�x set msg �w�c�c�����A��������@���c�c�x set image -1 set msg �c set msg �c�c set msg �c�������c set msg �c���l�̍����c set msg �c�c���߂����߂Ɂc set play 6",//1
		"set image 7 set msg �w��������Ȃ����c�x set msg �w�c�c�c�c�x set msg �w�c�_���ˁc�x set msg �w�c���̎q�c�ӎ����߂�Ȃ��c�x set msg �w�c�����q�����Ƃ��c�x set msg �w�c�ł��Ȃ������̂ˁc�x set image -1 set msg �c set msg �����c set msg �ł��Ȃ������c set msg �a�`�c�d�m�c set title 1",//2
		"set image 7 set msg �w��������Ȃ����c�x set msg �w�c�c���c�c�x set image -1 set msg �w�c���̎q�c�ӎ����߂�����c�x set image 14 set msg �w�c�c���c�c�x set msg �w�c�c�c�肪�c�Ɓc�c�x set msg �w�c�c�c�c�c�x set image -1 set msg �w�c�c�c�c�x set image 7 set msg �w�c����ς�c�����������̂ˁc�x set msg �w�c����߂��Ȃ�āc�x set msg �w�c���߂�Ȃ����c�x set msg �w�c��]���������Ă��܂��āc�x set image -1 set msg �������� set msg ������ set msg �I����� set msg �m�n�q�l�`�k�d�m�c set msg �@ set msg �c set msg �c�c set msg �w�c����ł��c�܂��c�x set msg �w�c��]���̂ĂȂ��̂Ȃ�c�x set image 11 set msg �c�c�c set msg �c�c set msg �c set title 1",//3
		"set image 7 set msg �w��������Ȃ����c�I�x set msg �w�c�c���c�c�x set image -1 set msg �w�c���̎q�c�ӎ����߂�����c�x set image 14 set msg �w�c�c���c�c�x set msg �w�c�c�c���肪�Ƃ��c�c�x set msg �w�c�c�c�c�c�x set image -1 set msg �w�c�c�c�c�x set image 17 set msg �w�c�c�������܂��x set ending 1", //4
		"set image 20 set msg �w�c������x�c�x set msg �w�c���̖�����݂Ȃ����c�x set msg �w�c�ق�c�c�x set image 7 set msg �w�c��������c�x set msg �w�c�������݂Ȃ����c�x set image -1 set msg �c�c�������c�c set msg �c���̏��̐l�Ɂc set msg �c������c�c set msg �c�c�c�c set omake 1",//5
		"set image 7 set msg �w��������Ȃ����c�x set msg �w�c�������Łc�x set msg �w�c�����f�[�^���[��������c�x set msg �w�c���肪�Ƃ��I�x set image -1 set msg �������� set msg �S�Ă��I����� set msg �Ԃ�@���S�I���I set title 1"//6
	};
	
	public static String getStoryCode(int id)
	{
		return storyCode[id];
	}
	
	public static void makeRooms()
	{
		RoomData.addRooms(0, "-1:-1:1:2");//�����O��
		RoomData.addRooms(1, "-1:-1:3:0");//�����I
		RoomData.addRooms(2, "-1:-1:0:3");//�����h�A
		RoomData.addRooms(3, "-1:-1:2:1");//������냍�b�J�[

		RoomData.addRooms(4, "-1:0:-1:-1");//��
		RoomData.addRooms(5, "-1:3:-1:-1");//���b�J�[�|����
		RoomData.addRooms(6, "-1:-1:8:7");//�L��
		RoomData.addRooms(7, "-1:-1:6:-1");//�L���ʍs�~��
		RoomData.addRooms(8, "-1:-1:-1:6");//�L���{�f����
		RoomData.addRooms(9, "-1:8:-1:-1");//�f����
	}
	
	public static CharacterData[] makeOmake()
	{
		Vector v = new Vector();
		//v.addElement(new CharacterData(1, 2, 0, 0, 0,  0,0,240,240,"set msg �Ă���"));
		v.addElement(new CharacterData(2, 3,0,25,5, 96, 216,24, 24, "set msg �[�w�ӎ��Ɖ�����q�������z��Ԃł�"));
		v.addElement(new CharacterData(3, 3,0,75,5, 120, 216,24, 24, "set msg �g�p�҂̈ӎv�ő��l�̍��𑀂��V��ł�"));
		v.addElement(new CharacterData(4, 3,0,125,5, 144, 216,24, 24, "set msg ����������̂�������悤�ɂȂ���̂ł�"));
		v.addElement(new CharacterData(5, 3,0,175,5, 168, 216,24, 24, "set msg �Ώێ҂̍������߂����̂ł�"));

		v.addElement(new CharacterData(6, 3,0,25,50, 168, 216,24, 24, "set msg ����ɏZ�ޖS�҂ł�"));
		v.addElement(new CharacterData(7, 3,0,75,50, 144, 216,24, 24, "set msg �����q������̂ł�"));
		v.addElement(new CharacterData(8, 3,0,125,50, 120, 216,24, 24, "set msg �ق��Ƃ��̋ʂ������ɂ��Ă��܂���"));
		v.addElement(new CharacterData(9, 3,0,175,50, 96, 216,24, 24, "set msg �H���ǂ��������ʂ�����܂�"));

		v.addElement(new CharacterData(26, 3,0,25,95, 120, 216,24, 24, "set msg �s���̕a�������͂��̗��l�ł�"));
		v.addElement(new CharacterData(27, 3,0,75,95, 96, 216,24, 24, "set msg �����Ē����Ă��肪�Ƃ��������܂�"));
		v.addElement(new CharacterData(28, 3,0,125,95, 168, 216,24, 24, "set msg ����̍�i�ɂ����҉�����"));
		v.addElement(new CharacterData(29, 3,0,175,95, 144, 216,24, 24, "set msg �^���̐Ԃ����Ƃ������ƂŁc"));

		v.addElement(new CharacterData(101,3, 0, 22, 30, 198,0,30,5,"set msg �w�E�o�����x"));
		v.addElement(new CharacterData(102,3, 0, 72, 30, 198,0,30,5,"set msg �w��̖�x"));
		v.addElement(new CharacterData(103,3, 0, 122, 30, 198,0,30,5,"set msg �w���ʁx"));
		v.addElement(new CharacterData(104,3, 0, 172, 30, 198,0,30,5,"set msg �w�l�`�x"));

		v.addElement(new CharacterData(105,3, 0, 22, 75, 198,0,30,5,"set msg �w�H��x"));
		v.addElement(new CharacterData(106,3, 0, 72, 75, 198,0,30,5,"set msg �w�Ԃ����x"));
		v.addElement(new CharacterData(107,3, 0, 122, 75, 198,0,30,5,"set msg �w���n���������x"));
		v.addElement(new CharacterData(108,3, 0, 172, 75, 198,0,30,5,"set msg �w�아�x"));

		v.addElement(new CharacterData(105,3, 0, 22, 120, 198,0,30,5,"set msg �w�ӎ��s���ɂȂ��Ă��q�x"));
		v.addElement(new CharacterData(106,3, 0, 72, 120, 198,0,30,5,"set msg �w���Ǐ��������́H�x"));
		v.addElement(new CharacterData(107,3, 0, 122, 120, 198,0,30,5,"set msg �w�������͂Ȃ�Ȃ́H�x"));
		v.addElement(new CharacterData(108,3, 0, 172, 120, 198,0,30,5,"set msg �w���ǃ^�C�g���̈Ӗ��́x"));


		v.addElement(new CharacterData(10, 3, 0, 22,150, 145,46,22,20, "set show 201 set msg ���܂��ł悯��΁c", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(109,3, 0, 17, 180, 198,0,30,5,"if isset hide 301 set show 10 set msg �w�����ƒE�o�������I�x end"));


		v.addElement(new CharacterData(201, 15, 9, 153, 135,  80,105,80,105,"set hide 10 set show 301 set msg �Z�[�u�ł��Ȃ��̂Œ��ӁI", CharacterData.STATE_HIDE));
		
		v.addElement(new CharacterData(301, 3, 0, 100, 137,  145,46,22,43,"set story 5", CharacterData.STATE_HIDE));//RECT�l�`


		CharacterData[] ret = new CharacterData[v.size()];
		for ( int i = 0; i < v.size(); i++ )
		{
			ret[i] = (CharacterData) v.elementAt(i);
		}
		return ret;
	}	

	public static CharacterData[] makePlayOmake()
	{
		makeRooms();

		Vector v = new Vector();
		CharacterData cd;
		// ����
		v.addElement(new CharacterData(-1, 2, 0, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(1,3, 0, 62, 160, 0,92,64,46,"set go 4"));
		//v.addElement(new CharacterData(2,3, 0, 147, 104, 66,0,66,46,"set msg ���̊G��"));
		v.addElement(new CharacterData(-2,-1, 0, 147, 104, 66,0,66,46,"set msg �����c�������悤�ȁc"));
		v.addElement(new CharacterData(3,3, 0, 165, 154, 198,0,30,5,"set msg �Q�O�D�P�T�D�P�W�D�X"));
		



		// �I��
		v.addElement(new CharacterData(-1, 2, 1, 0, 0,  0,0,240,240,null));

		//v.addElement(new CharacterData(101,3, 1, 32, 104, 0,0,66,46,"set msg �Y��ȊG�c"));
		v.addElement(new CharacterData(-101,-1, 1, 32, 104, 0,0,66,46,"set msg �����c�������悤�ȁc"));
		v.addElement(new CharacterData(102,3, 1, 50, 154, 198,0,30,5,"set msg �Q�P�D�P�X�D�W�D�X"));

		v.addElement(new CharacterData(101, 3, 1, 182, 188, 94, 141,9, 9, "set hide 101 set item 1106 set msg ���F�̋ʂ������"));
		v.addElement(new CharacterData(103, 3, 1, 128, 96,  175,48,65,106,null));//�I
		v.addElement(new CharacterData(110, 3, 1, 123, 96,  175,48,65,106,null, CharacterData.STATE_HIDE));//�I
		//v.addElement(new CharacterData(104, 3, 1, 128+6, 96+17,  198,27,16,21,"set msg ������Ȗ{�c"));//�{
		//v.addElement(new CharacterData(105, 3, 1, 128+41, 96+28,  228,0,4,10,"set msg �`���Q�~�P"));//�G�̋
		//v.addElement(new CharacterData(106, 3, 1, 128+47, 96+25,  232,0,8,13,"set msg ���M���Ă��c"));//�M����
		//v.addElement(new CharacterData(108, 3, 1, 128+34, 96+44,  220,13,18,22,"set hide 108 set item 1108 set msg �����������"));//����
		//v.addElement(new CharacterData(109, 3, 1, 128+6, 96+71,  145,89,28,24,"set msg �_���{�[�����c"));//�_���{�[��

		v.addElement(new CharacterData(116, 3, 1, 128+7, 96+44,  198,5,22,22,"if isset show 103 set hide 103 set show 110 set msg �I�H else set hide 110 set show 103 set msg �I�H end"));//�L�����o�X
		//v.addElement(new CharacterData(117, 3, 1, 128+30, 96+44,  198,5,22,22,"set hide 117 set show 116", CharacterData.STATE_HIDE));//�L�����o�X
		//v.addElement(new CharacterData(121, 3, 1, 128+36, 96+84,  214,35,20,11,"set msg �ʕ����c�H�ׂ��Ȃ��c"));//�ʕ��T���v��
		//v.addElement(new CharacterData(122, 3, 1, 128+36, 96+71,  214,35,20,11,"set hide 122 set show 121", CharacterData.STATE_HIDE));//�ʕ��T���v��





		// �h�A��
		v.addElement(new CharacterData(-1, 2, 2, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(-1, 13, 2, 12, 91,  67,5,103,95,null));//�h�A��
		v.addElement(new CharacterData(-201, 2, 2, 0, 0,  0,0,0,0,null));//flag
		//v.addElement(new CharacterData(201, 13, 2, 60, 94,  5,8,55,91,"if isset show -201 set show 209 set show 210 set show 211 else set msg �J���Ȃ��c end"));//�h�A
		//v.addElement(new CharacterData(202, 13, 2, 71, 109,  183,12,33,24,"set msg ������������o�����c"));//�h�A��
		//v.addElement(new CharacterData(206, 13, 2, 61, 94+50,  6,58,12,12,"if isset item 1102 set delitem 1102 set show -201 set hide 206 set msg �J�`�b�Ɖ��������I else set msg �J���Ȃ��c end"));//�h�A��
		v.addElement(new CharacterData(209, 13, 2, 40, 94,  5,8,55,91,null));//�������h�A
		v.addElement(new CharacterData(210, 13, 2, 51, 109,  183,12,33,24,null));//�h�A��
		v.addElement(new CharacterData(-211, -1, 2, 0,0,  0,0,0,0,null));
		v.addElement(new CharacterData(211, 13, 2, 40+55, 94,  0,141,20,91,"set go 6"));//�������h�A���� TODO

		//v.addElement(new CharacterData(212,3, 2, 147, 104, 0,46,66,46,"set msg ���i�悩�c"));
		v.addElement(new CharacterData(-212,-1, 2, 147, 104, 0,46,66,46,"set msg �����c�������悤�ȁc"));
		v.addElement(new CharacterData(213,3, 2, 165, 154, 198,0,30,5,"set msg �Q�O�D�P�T�D�P�W�D�P"));
		
		v.addElement(new CharacterData(214, 13, 2, 12, 55,  130,110,104,24,"set msg ���Ɉُ�͂Ȃ��c"));//�㑋1
		v.addElement(new CharacterData(215, 13, 2, 140, 55,  130,110,100,24,"set msg ���Ɉُ�͂Ȃ��c"));//�㑋2



		// ������둤���b�J�[
		v.addElement(new CharacterData(-1, 2, 3, 0, 0,  0,0,240,240,null));

		//v.addElement(new CharacterData(301,3, 3, 147, 104, 132,0,66,46,"set msg �N�₩�c"));
		v.addElement(new CharacterData(-301,-1, 3, 147, 104, 132,0,66,46,"set msg �����c�������悤�ȁc"));
		v.addElement(new CharacterData(302,3, 3, 165, 154, 198,0,30,5,"set msg �P�X�D�P�D�P�W�D�Q�P"));

		v.addElement(new CharacterData(303, 3, 3, 30, 110,  66,46,32,89,null));//���b�J�[
		v.addElement(new CharacterData(304, 3, 3, 62, 110,  66,46,32,89,null));//���b�J�[

		

		v.addElement(new CharacterData(336, 3, 3, 62+10, 110+28, 107,141,9, 9, "set show -306 set hide 336 set item 1107 set msg ���F�̋ʂ������"));
		v.addElement(new CharacterData(306, 3, 3, 62+10, 110+28, 69,141,9, 9, "set hide 306 set hide -306 set item 1104 set msg �F�̋ʂ������", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(305, 3, 3, 62+4, 110+37,  145,46,22,43,"if isset show -402 set msg �w�����l�x else set msg �w�c���c�c�Ɓc�c�c�x end"));//RECT�l�`
		v.addElement(new CharacterData(-306, -1, 3, 0, 0, 0,0,0,0, null, CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(326, 3, 3, 30+7, 110+69,  119,123,17,12,"set hide 326 set item 1101 set msg ���������"));//��

		v.addElement(new CharacterData(307, 3, 3, 30+6, 110+7,  0,139,19,59,"set go 5"));//�|����


		//v.addElement(new CharacterData(-308, 2, 3, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag
		//v.addElement(new CharacterData(308, 3, 3, 166, 196, 229,154,11,6,"set hide 308 set item 1102 set msg �����E����", CharacterData.STATE_HIDE));//������




		v.addElement(new CharacterData(395, 3, 3, 30+4, 110+6,  119,46,26,77,"set show 397 set hide 395"));//���b�J�[��
		v.addElement(new CharacterData(-396, -1, 3, 0,0,  0,0,0,0,null,CharacterData.STATE_HIDE));//���b�J�[�t���O
		v.addElement(new CharacterData(396, 3, 3, 62+4, 110+6,  119,46,26,77,"if isset show -396 set show 398 set hide 396 else isset item 1102 set msg ��������Ȃ��c else isset item 1100 set delitem 1100 set show -396 set msg �J�`�b�Ɖ��������I else set msg �����|�����Ă�c end"));//���b�J�[��

		v.addElement(new CharacterData(397, 3, 3, 30+29, 110+3,  98,46,21,83,"set show 395 set hide 397", CharacterData.STATE_HIDE));//���b�J�[�J��
		v.addElement(new CharacterData(398, 3, 3, 62+29, 110+3,  98,46,21,83,"set show 396 set hide 398 if isset show -306 set hide -306 set show 306 set msg �������������c end", CharacterData.STATE_HIDE));//���b�J�[�J��
		

		// ��
		//v.addElement(new CharacterData(-1, 16, 4, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(-1, 4, 4, 0, 0,  0,176,240,24,null));
		for ( int i = 0; i < 8; i++ )
		{
			v.addElement(new CharacterData(-1, 4, 4, 0, 24+(i*24),  0,197,240,24,null));
		}
		v.addElement(new CharacterData(-1, 4, 4, 0, 216,  0,216,240,24,null));

		//x 10, 30, 88, 108
		makeHall(v, 400, 107, 38);
		makeHall(v, 401, 142, 46);

		makeHall(v, 402, 168, 72);
		makeHall(v, 403, 179, 104);
		makeHall(v, 404, 168, 139);

		makeHall(v, 405, 142, 165);
		makeHall(v, 406, 107, 178);
		makeHall(v, 407, 73, 165);

		makeHall(v, 408, 46, 139);
		makeHall(v, 409, 38, 104);
		makeHall(v, 410, 46, 72);

		makeHall(v, 411, 73, 46);
		
		v.addElement(new CharacterData(-401, -1, 4, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag
		v.addElement(new CharacterData(-402, -1, 4, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag

		//v.addElement(new CharacterData(499, 3, 4, 108, 108, 216,192,24,24, "set hide 499 set item 1110 set msg �아�������", CharacterData.STATE_HIDE));
				
		// ���b�J�[�|����
		v.addElement(new CharacterData(500, 10, 5, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-1, 10, 5, 80, 24,  79,181,78,59,null));
		v.addElement(new CharacterData(-1, 10, 5, 80, 74,  79,181,78,59,null));
		v.addElement(new CharacterData(-1, 10, 5, 80, 124,  79,181,78,59,null));
		v.addElement(new CharacterData(-1, 10, 5, 80, 154,  79,181,78,59,null));
		v.addElement(new CharacterData(-1, 10, 5, 80, 204,  79,181,78,36,null));
		v.addElement(new CharacterData(599, 16, 5, 72, 11,  135,0,99,226,"if isset item 1110 set hide 599 set msg �w�������₠�����������I�x else set msg �o�d�s end"));




		// �L��
		v.addElement(new CharacterData(-1, 2, 6, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-1, 4, 6, 0, 0,  0,0,168,45,null));
		v.addElement(new CharacterData(-1, 4, 6, 168, 0,  0,0,72,45,null));
		v.addElement(new CharacterData(-1, 4, 6, 0, 186,  0,45,183,54,null));
		v.addElement(new CharacterData(-1, 4, 6, 183, 186,  0,45,57,54,null));
		v.addElement(new CharacterData(-1, 4, 6, 120, 36,  194,0,46,163,null));//��

		v.addElement(new CharacterData(-1, 12, 6, 0, 64,  3,4,116,80,null)); //�i�q��
		v.addElement(new CharacterData(-1, 12, 6, 1, 68,  122,8,116,72,"if isset item 1108 set msg ����Ȃ��c else isset item 1104 set msg ����Ȃ��c else set msg �Â��c end")); //����
		v.addElement(new CharacterData(-1, 12, 6, 54, 80,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���
		v.addElement(new CharacterData(-1, 12, 6, 54, 120,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���

		v.addElement(new CharacterData(-1, 12, 6, 170, 64,  3,4,70,80,null)); //�i�q�E
		v.addElement(new CharacterData(-1, 12, 6, 171, 68,  122,8,69,72,"if isset item 1108 set msg ����Ȃ��c else isset item 1104 set msg ����Ȃ��c else set msg �Â��c end"));//���E
		v.addElement(new CharacterData(-1, 12, 6, 223, 80,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���
		v.addElement(new CharacterData(-1, 12, 6, 223, 120,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���

		v.addElement(new CharacterData(-601, -1, 6, 60,98,  3,149,52,43,"if isset item 1108 set hide -601 set show 601 set msg ���ꂽ�c�I else set msg ������������o�����c end")); // ���ꂽ��
		v.addElement(new CharacterData(601, 12, 6, 60,98,  3,149,52,43,"set story 6", CharacterData.STATE_HIDE)); // ���ꂽ��




		// �L���ʍs�~��
		v.addElement(new CharacterData(-1, 2, 7, 0, 0,  0,0,240,240,"set msg �s���~�܂肾�c"));
		v.addElement(new CharacterData(-1, 4, 7, 0, 0,  0,0,168,45,null));
		v.addElement(new CharacterData(-1, 4, 7, 168, 0,  0,0,72,45,null));
		v.addElement(new CharacterData(-1, 4, 7, 0, 186,  0,45,183,54,null));
		v.addElement(new CharacterData(-1, 4, 7, 183, 186,  0,45,57,54,null));
		v.addElement(new CharacterData(-1, 4, 7, 210, 36,  194,0,30,163,null));//��
		v.addElement(new CharacterData(701, 3, 7, 100, 120,  210,154,6,11,"set hide 701 set item 1100 set msg �����E����"));
		v.addElement(new CharacterData(702, 16, 7, 72, 11,  135,0,99,226,"if isset item 1110 set hide 702 set msg �w�������₠�����������I�x else set msg �`���Q�~�P end"));

		v.addElement(new CharacterData(108, 3, 7, 120, 198,  220,13,18,22,"set hide 108 set item 1108 set msg �����������", CharacterData.STATE_HIDE));//����

		
		

		// �L��+�f����
		v.addElement(new CharacterData(-1, 8, 8, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-801, -1, 8, 0, 109,  0,0,12,118,"set go 1"));
		v.addElement(new CharacterData(-802, -1, 8, 15, 126,  0,0,34,68,"set go 9"));
		//v.addElement(new CharacterData(803, 16, 8, 70, 14,  135,0,99,226,"if isset item 1110 set hide 299 set hide 803 set delitem 1110 set show -2212 set msg �H�삪�ǂ����֍s�����c else set hide 803 set msg �������c end"));



		// �f����
		v.addElement(new CharacterData(-1, 11, 9, 0, 0,  0,0,240,240,"set msg �f�����c"));
		//v.addElement(new CharacterData(900, 15, 9, 42, 81,  0,0,80,105,"set msg ���������Ă���c"));
		//v.addElement(new CharacterData(901, 15, 9, 131, 40,  80,0,80,105,"set msg �������c"));
		v.addElement(new CharacterData(902, 15, 9, 131, 40,  160,0,80,105,"set msg ���������Ă���c"));
		v.addElement(new CharacterData(903, 15, 9, 42, 81,  0,105,80,105,"set msg ���������Ă���c"));
		v.addElement(new CharacterData(703, 3, 9, 163, 45, 82,141,9, 9, "set hide 703 set item 1105 set msg ��F�̋ʂ������"));

		// �A�C�e��
		cd = new CharacterData(-1, 1, 0, 42, 25, 46,0,18,30, "set event 1");
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);
		cd = new CharacterData(-1, 1, 0, 60, 25, 64,0,126,30, "");
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);
		cd = new CharacterData(-1, 1, 0, 186, 25, 190,0,18,30, "set event 2");
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);
		
		cd = new CharacterData(-1, 1, 0, 60, 60, 0,30,126,126, null);
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);

		int itemImg = 3;
		int itemY = 216;
		v.addElement(new ItemData(1100, itemImg, 0, itemY,24, 24, 2200,"set select 1100 set detail 2200 set msg �J�M���c"));
		v.addElement(new ItemData(1101, itemImg, 24, itemY,24, 24, 2216,"set select 1101 set detail 2216 set msg ���炭�蔠���c"));
		v.addElement(new ItemData(1102, itemImg, 48, itemY,24, 24, 2202,"set select 1102 set detail 2202 set msg �J�M���c"));
		v.addElement(new ItemData(1103, itemImg, 72, itemY,24, 24, 2203,"set select 1103 set detail 2203 set msg �Ԃ������c"));
		v.addElement(new ItemData(1104, itemImg, 96, itemY,24, 24, 2204,"set select 1104 set detail 2204 set msg �F�̋ʂ��c"));
		v.addElement(new ItemData(1105, itemImg, 120, itemY,24, 24, 2205,"set select 1105 set detail 2205 set msg ��F�̋ʂ��c"));
		v.addElement(new ItemData(1106, itemImg, 144, itemY,24, 24, 2206,"set select 1106 set detail 2206 set msg ���F�̋ʂ��c"));
		v.addElement(new ItemData(1107, itemImg, 168, itemY,24, 24, 2207,"set select 1107 set detail 2207 set msg ���F�̋ʂ��c"));
		v.addElement(new ItemData(1108, itemImg, 192, itemY,24, 24, 2208,"set select 1108 set detail 2208 set msg �������c"));
		v.addElement(new ItemData(1109, itemImg, 216, itemY,24, 24, 2209,"set select 1109 set detail 2209 set msg ���v���c"));
		v.addElement(new ItemData(1110, itemImg, 216, itemY-24,24, 24, 2210,"set select 1110 set detail 2210 set msg �아���c"));
		v.addElement(new ItemData(1111, itemImg, 192, itemY-24,24, 24, 2211,"set select 1111 set detail 2211 set msg �l�`���c"));
		

		// �A�C�e���ڍ�
		v.addElement(new ItemData(2200, 6, 120, 0,120, 120, 0,"set msg �Ȃ�̃J�M���낤"));
		v.addElement(new ItemData(2202, 6, 120, 120,120, 120, 0,"set msg �Ȃ�̃J�M���낤"));
		v.addElement(new ItemData(2203, 6, 0, 0,120, 120, 0,"set msg �Ԃ����c"));

		v.addElement(new ItemData(2204, 5, 120, 0,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2205, 5, 0, 0,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2206, 5, 0, 120,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2207, 5, 120, 120,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2208, 19, 120, 120,120, 120, 0,"set msg �������c"));
		v.addElement(new ItemData(2209, 6, 0, 120,120, 120, 0,"set msg �j���Ȃ��c"));
		v.addElement(new ItemData(2210, 19, 120, 0,120, 120, 0,"set msg �����͂�������c"));
		v.addElement(new ItemData(2211, 16, 0, 0,120, 120, 0,"if isset show -2211 set msg �����������������c else isset item 1103 set delitem 1103 set show -2211 set msg ���w�Ɏ��������� else set msg �l�`���c end"));
		v.addElement(new CharacterData(-2211, -1, 0, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag

		v.addElement(new CharacterData(-2212, -1, 0, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag


		v.addElement(new ItemData(2216, 19, 0, 0,120, 120, 0,"set msg �ǂ�������J������"));//�^����
		/*
		ItemData itemTmp = new ItemData(2217, -1, 5, 5, 109, 33, 0,"set detail 2218"); // ����
		itemTmp.rootId = 2216;
		itemTmp.x = 68;
		itemTmp.y = 68;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);
		*/
		ItemData itemTmp;
		for ( int i = 0; i < 3; i++ )
		{
			//����
			for ( int j = 0; j < 10; j++ )
			{
				// 2240~2269
				itemTmp = new ItemData(2240 + (i * 10) + j, 13, 140+(j*10), 227, 10, 13, 0,null);
				itemTmp.rootId = 2216;
				itemTmp.x = 63+31+(i*24);
				itemTmp.y = 63+61;
				itemTmp.state = (j == 0) ? ItemData.STATE_SHOW : ItemData.STATE_HIDE;
				v.addElement(itemTmp);
			}
			
			//�{�^�� 2230~2232
			itemTmp = new ItemData(2230 + i, -1, 31, 77, 10, 11, 0,"set event " + (50 + i));
			itemTmp.rootId = 2216;
			itemTmp.x = 63+31+(i*24);
			itemTmp.y = 63+77;
			itemTmp.state = ItemData.STATE_SHOW;
			v.addElement(itemTmp);
		}
		itemTmp = new ItemData(2219, -1, 43, 42, 34, 16, 0,"set event 48"); // �����
		itemTmp.rootId = 2216;
		itemTmp.x = 63+43;
		itemTmp.y = 63+42;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);

		v.addElement(new ItemData(2218, 19, 120, 0,120, 120, 0,"set msg ���̗������c"));//��
		itemTmp = new ItemData(2221, -1, 5, 5, 109, 33, 0,"set detail 2216"); //�\��
		itemTmp.rootId = 2218;
		itemTmp.x = 68;
		itemTmp.y = 68;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);
		v.addElement(new ItemData(2290, 19, 0, 120,120, 120, 0,null));//�J����
		itemTmp = new ItemData(2291, 19, 120, 0,120, 120, 0,null); // �J�����Ƃ��̃A�C�e��
		itemTmp.rootId = 2290;
		itemTmp.x = 63;
		itemTmp.y = 42;
		itemTmp.state = ItemData.STATE_SHOW;
		
		v.addElement(itemTmp);




		v.addElement(new ItemData(2301, 14, 0, 120,120, 120, 0,null)); // �S�~���ォ��



		CharacterData[] ret = new CharacterData[v.size()];
		for ( int i = 0; i < v.size(); i++ )
		{
			ret[i] = (CharacterData) v.elementAt(i);
		}
		return ret;
	}

	public static CharacterData[] make()
	{
		makeRooms();
		
		Vector v = new Vector();
		//id, imageId,roomId, x, y, sx, sy,width,height, code
		//id,imageId, x, y, width,height, detailId,code
		//max 21 ? < mozi

		//hibi imageId = 7
		// 0,0,57,43
		// 58, 0, 68, 39
		// 11,57,72,25
		// 107,58,35,20
		// 13,98,22,19
		// 60,97,25,14
		// 118,97,32,20
		// 20,132,40,53
		// 143, 0, 97, 140


		CharacterData cd;
		// ����
		v.addElement(new CharacterData(-1, 2, 0, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(1,3, 0, 62, 160, 0,92,64,46,"set go 4"));
		v.addElement(new CharacterData(2,3, 0, 147, 104, 66,0,66,46,"if isset item 1106 set msg �����F�c else set msg �t�̊G���c end"));
		v.addElement(new CharacterData(3,3, 0, 165, 154, 198,0,30,5,"set msg �Q�~�P�@�V�~�P�@�V�~�R"));
		//v.addElement(new CharacterData(4, 3, 0, 155, 215, 69,141,9, 9, "set hide 4 set item 1104 set msg �F�̋ʂ������"));



		// �I��
		v.addElement(new CharacterData(-1, 2, 1, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(101,3, 1, 32, 104, 0,0,66,46,"if isset item 1104 set msg �����F�c else set msg �Ă̊G���c end"));
		v.addElement(new CharacterData(102,3, 1, 50, 154, 198,0,30,5,"set msg �Q�~�P�@�W�~�Q�@�S�~�P"));

		v.addElement(new CharacterData(103, 3, 1, 128, 96,  175,48,65,106,null));//�I
		v.addElement(new CharacterData(104, 3, 1, 128+6, 96+17,  198,27,16,21,"set msg ������Ȗ{�c"));//�{
		v.addElement(new CharacterData(105, 3, 1, 128+41, 96+28,  228,0,4,10,"set msg �Q�~�P���`"));//�G�̋
		v.addElement(new CharacterData(106, 3, 1, 128+47, 96+25,  232,0,8,13,"set msg ���M���Ă��c"));//�M����
		v.addElement(new CharacterData(107, 3, 1, 128+13, 96+56, 94,141,9, 9, "set hide 107 set item 1106 set msg ���F�̋ʂ������"));
		v.addElement(new CharacterData(108, 3, 1, 128+34, 96+44,  220,13,18,22,"set hide 108 set item 1108 set msg �����������"));//����
		v.addElement(new CharacterData(109, 3, 1, 128+6, 96+71,  145,89,28,24,"set msg �_���{�[�����c"));//�_���{�[��

		v.addElement(new CharacterData(110, 3, 1, 128+39, 96+85, 82,141,9, 9, "set hide 122 set show 121 set hide 110 set item 1105 set msg ��F�̋ʂ������"));
		v.addElement(new CharacterData(116, 3, 1, 128+6, 96+44,  198,5,22,22,"if isset hide 108 set hide 116 set show 117 else set msg �L�����o�X���c end"));//�L�����o�X
		v.addElement(new CharacterData(117, 3, 1, 128+30, 96+44,  198,5,22,22,"set hide 117 set show 116", CharacterData.STATE_HIDE));//�L�����o�X
		v.addElement(new CharacterData(121, 3, 1, 128+36, 96+84,  214,35,20,11,"if isset show 110 set hide 121 set show 122 else set msg �ʕ����c�H�ׂ��Ȃ��c end"));//�ʕ��T���v��
		v.addElement(new CharacterData(122, 3, 1, 128+36, 96+71,  214,35,20,11,"set hide 122 set show 121", CharacterData.STATE_HIDE));//�ʕ��T���v��




		



		// �h�A��
		v.addElement(new CharacterData(-1, 2, 2, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(-1, 13, 2, 12, 91,  67,5,103,95,null));//�h�A��
		v.addElement(new CharacterData(-201, 2, 2, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag
		v.addElement(new CharacterData(201, 13, 2, 60, 94,  5,8,55,91,"if isset show -201 set show 209 set show 210 set show 211 else set msg �J���Ȃ��c end"));//�h�A
		v.addElement(new CharacterData(202, 13, 2, 71, 109,  183,12,33,24,"set msg ������������o�����c"));//�h�A��
		v.addElement(new CharacterData(206, 13, 2, 61, 94+50,  6,58,12,12,"if isset item 1102 set delitem 1102 set show -201 set hide 206 set msg �J�`�b�Ɖ��������I else set msg �J���Ȃ��c end"));//�h�A��
		v.addElement(new CharacterData(209, 13, 2, 40, 94,  5,8,55,91,null, CharacterData.STATE_HIDE));//�������h�A
		v.addElement(new CharacterData(210, 13, 2, 51, 109,  183,12,33,24,null, CharacterData.STATE_HIDE));//�h�A��
		v.addElement(new CharacterData(-211, -1, 2, 0,0,  0,0,0,0,null, CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(211, 13, 2, 40+55, 94,  0,141,20,91,"if isset hide -211 set show -211 set story 1 else isset show -2212 set go 6 else set show 803 set go 6 end", CharacterData.STATE_HIDE));//�������h�A���� TODO

		v.addElement(new CharacterData(212,3, 2, 147, 104, 0,46,66,46,"if isset item 1107 set msg �����F�c else set msg �~�̊G���c end"));
		v.addElement(new CharacterData(213,3, 2, 165, 154, 198,0,30,5,"set msg �R�~�R�@�R�~�Q�@�Q�~�Q"));
		
		v.addElement(new CharacterData(214, 13, 2, 12, 55,  130,110,104,24,"set msg ���Ɉُ�͂Ȃ��c"));//�㑋1
		v.addElement(new CharacterData(215, 13, 2, 140, 55,  130,110,100,24,"set msg ���Ɉُ�͂Ȃ��c"));//�㑋2

		v.addElement(new CharacterData(299, 16, 2, 70, 14,  135,0,99,226,"if isset item 1110 set hide 299 set hide 803 set delitem 1110 set show -2212 set msg �H�삪�ǂ����֍s�����c else set hide 299 set msg �������c end", CharacterData.STATE_HIDE));



		// ������둤���b�J�[
		v.addElement(new CharacterData(-1, 2, 3, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(301,3, 3, 147, 104, 132,0,66,46,"if isset item 1105 set msg �����F�c else set msg �H�̊G���c end"));
		v.addElement(new CharacterData(302,3, 3, 165, 154, 198,0,30,5,"set msg �U�~�Q�@�U�~�R�@�W�~�R"));

		v.addElement(new CharacterData(303, 3, 3, 30, 110,  66,46,32,89,null));//���b�J�[
		v.addElement(new CharacterData(304, 3, 3, 62, 110,  66,46,32,89,null));//���b�J�[

		

		v.addElement(new CharacterData(305, 3, 3, 62+4, 110+37,  145,46,22,43,"if isset hide -2212 set show 299 end set hide 305 set item 1111 set msg �l�`�������"));//RECT�l�`
		v.addElement(new CharacterData(-306, -1, 3, 0, 0, 0,0,0,0, null, CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(306, 3, 3, 30+9, 110+70, 69,141,9, 9, "set hide 306 set hide -306 set item 1104 set msg �F�̋ʂ������", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(326, 3, 3, 30+7, 110+69,  119,123,17,12,"set show -306 set hide 326 set item 1101 set msg ���������"));//��

		v.addElement(new CharacterData(307, 3, 3, 30+6, 110+7,  0,139,19,59,"set go 5"));//�|����


		v.addElement(new CharacterData(-308, 2, 3, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag
		v.addElement(new CharacterData(308, 3, 3, 166, 196, 229,154,11,6,"set hide 308 set item 1102 set msg �����E����", CharacterData.STATE_HIDE));//������




		v.addElement(new CharacterData(395, 3, 3, 30+4, 110+6,  119,46,26,77,"set show 397 set hide 395"));//���b�J�[��
		v.addElement(new CharacterData(-396, -1, 3, 0,0,  0,0,0,0,null,CharacterData.STATE_HIDE));//���b�J�[�t���O
		v.addElement(new CharacterData(396, 3, 3, 62+4, 110+6,  119,46,26,77,"if isset show -396 set show 398 set hide 396 else isset item 1102 set msg ��������Ȃ��c else isset item 1100 set delitem 1100 set show -396 set msg �J�`�b�Ɖ��������I else set msg �����|�����Ă�c end"));//���b�J�[��

		v.addElement(new CharacterData(397, 3, 3, 30+29, 110+3,  98,46,21,83,"set show 395 set hide 397 if isset show -306 set hide -306 set show 306 set msg �������������c end", CharacterData.STATE_HIDE));//���b�J�[�J��
		v.addElement(new CharacterData(398, 3, 3, 62+29, 110+3,  98,46,21,83,"set show 396 set hide 398", CharacterData.STATE_HIDE));//���b�J�[�J��
		

		// ��
		//v.addElement(new CharacterData(-1, 16, 4, 0, 0,  0,0,240,240,null));

		v.addElement(new CharacterData(355, 4, 4, 0, 0,  0,176,240,24,"if isset item 1109 set msg �����P�Q�c end"));
		for ( int i = 0; i < 8; i++ )
		{
			v.addElement(new CharacterData(355, 4, 4, 0, 24+(i*24),  0,197,240,24,"if isset item 1109 set msg �����P�Q�c end"));
		}
		v.addElement(new CharacterData(355, 4, 4, 0, 216,  0,216,240,24,"if isset item 1109 set msg �����P�Q�c end"));

		//x 10, 30, 88, 108
		makeHall(v, 400, 107, 38);
		makeHall(v, 401, 142, 46);

		makeHall(v, 402, 168, 72);
		makeHall(v, 403, 179, 104);
		makeHall(v, 404, 168, 139);

		makeHall(v, 405, 142, 165);
		makeHall(v, 406, 107, 178);
		makeHall(v, 407, 73, 165);

		makeHall(v, 408, 46, 139);
		makeHall(v, 409, 38, 104);
		makeHall(v, 410, 46, 72);

		makeHall(v, 411, 73, 46);
		
		v.addElement(new CharacterData(-401, -1, 4, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag
		v.addElement(new CharacterData(-402, -1, 4, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag

		v.addElement(new CharacterData(499, 3, 4, 108, 108, 216,192,24,24, "set hide 499 set item 1110 set msg �아�������", CharacterData.STATE_HIDE));
				
		// ���b�J�[�|����
		v.addElement(new CharacterData(500, 10, 5, 0, 0,  0,0,240,240,"set msg �Ȃ񂾂낤�c"));



		// �L��
		v.addElement(new CharacterData(-1, 2, 6, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-1, 4, 6, 0, 0,  0,0,168,45,null));
		v.addElement(new CharacterData(-1, 4, 6, 168, 0,  0,0,72,45,null));
		v.addElement(new CharacterData(-1, 4, 6, 0, 186,  0,45,183,54,null));
		v.addElement(new CharacterData(-1, 4, 6, 183, 186,  0,45,57,54,null));
		v.addElement(new CharacterData(-1, 4, 6, 120, 36,  194,0,46,163,null));//��

		v.addElement(new CharacterData(-1, 12, 6, 0, 64,  3,4,116,80,null)); //�i�q��
		v.addElement(new CharacterData(-1, 12, 6, 1, 68,  122,8,116,72,"if isset item 1108 set msg ����Ȃ��c else isset item 1104 set msg ����Ȃ��c else set msg �Â��c end")); //����
		v.addElement(new CharacterData(-1, 12, 6, 54, 80,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���
		v.addElement(new CharacterData(-1, 12, 6, 54, 120,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���

		v.addElement(new CharacterData(-1, 12, 6, 170, 64,  3,4,70,80,null)); //�i�q�E
		v.addElement(new CharacterData(-1, 12, 6, 171, 68,  122,8,69,72,"if isset item 1108 set msg ����Ȃ��c else isset item 1104 set msg ����Ȃ��c else set msg �Â��c end"));//���E
		v.addElement(new CharacterData(-1, 12, 6, 223, 80,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���
		v.addElement(new CharacterData(-1, 12, 6, 223, 120,  141,84,4,6,"set msg �����Ȃ�")); // �Ƃ���

		v.addElement(new CharacterData(-601, -1, 6, 60,98,  3,149,52,43,"if isset item 1108 set hide -601 set show 601 set msg ���ꂽ�c�I else set msg �c end")); // ���ꂽ��
		v.addElement(new CharacterData(601, 12, 6, 60,98,  3,149,52,43,"set event 99", CharacterData.STATE_HIDE)); // ���ꂽ��




		// �L���ʍs�~��
		v.addElement(new CharacterData(-1, 2, 7, 0, 0,  0,0,240,240,"set msg �s���~�܂肾�c"));
		v.addElement(new CharacterData(-1, 4, 7, 0, 0,  0,0,168,45,null));
		v.addElement(new CharacterData(-1, 4, 7, 168, 0,  0,0,72,45,null));
		v.addElement(new CharacterData(-1, 4, 7, 0, 186,  0,45,183,54,null));
		v.addElement(new CharacterData(-1, 4, 7, 183, 186,  0,45,57,54,null));
		v.addElement(new CharacterData(-1, 4, 7, 210, 36,  194,0,30,163,null));//��

		v.addElement(new CharacterData(701, 3, 7, 195, 208,  218,154,11,6,"set hide 701 set item 1100 set msg �����E����"));
		
		

		// �L��+�f����
		v.addElement(new CharacterData(-1, 8, 8, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-801, -1, 8, 0, 109,  0,0,12,118,"set go 1"));
		v.addElement(new CharacterData(-802, -1, 8, 15, 126,  0,0,34,68,"set go 9"));
		v.addElement(new CharacterData(803, 16, 8, 70, 14,  135,0,99,226,"if isset item 1110 set hide 299 set hide 803 set delitem 1110 set show -2212 set msg �H�삪�ǂ����֍s�����c else set hide 803 set msg �������c end"));



		// �f����
		v.addElement(new CharacterData(-1, 11, 9, 0, 0,  0,0,240,240,"set msg �f�����c"));
		v.addElement(new CharacterData(900, 15, 9, 42, 81,  0,0,80,105,"set msg ���������Ă���c"));
		v.addElement(new CharacterData(901, 15, 9, 131, 40,  80,0,80,105,"set msg �������c"));
		v.addElement(new CharacterData(902, 15, 9, 131, 40,  160,0,80,105,"set msg ���������Ă���c", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(903, 15, 9, 42, 81,  0,105,80,105,"set msg ���������Ă���c", CharacterData.STATE_HIDE));

		// �A�C�e��
		cd = new CharacterData(-1, 1, 0, 42, 25, 46,0,18,30, "set event 1");
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);
		cd = new CharacterData(-1, 1, 0, 60, 25, 64,0,126,30, "");
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);
		cd = new CharacterData(-1, 1, 0, 186, 25, 190,0,18,30, "set event 2");
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);
		
		cd = new CharacterData(-1, 1, 0, 60, 60, 0,30,126,126, null);
		cd.attribute = cd.ATTR_SHOW_MYITEM;
		cd.state = cd.STATE_SHOW;
		v.addElement(cd);

		int itemImg = 3;
		int itemY = 216;
		v.addElement(new ItemData(1100, itemImg, 0, itemY,24, 24, 2200,"set select 1100 set detail 2200 set msg �J�M���c"));
		v.addElement(new ItemData(1101, itemImg, 24, itemY,24, 24, 2216,"set select 1101 set detail 2216 set msg ���炭�蔠���c"));
		v.addElement(new ItemData(1102, itemImg, 48, itemY,24, 24, 2202,"set select 1102 set detail 2202 set msg �J�M���c"));
		v.addElement(new ItemData(1103, itemImg, 72, itemY,24, 24, 2203,"set select 1103 set detail 2203 set msg �Ԃ������c"));
		v.addElement(new ItemData(1104, itemImg, 96, itemY,24, 24, 2204,"set select 1104 set detail 2204 set msg �F�̋ʂ��c"));
		v.addElement(new ItemData(1105, itemImg, 120, itemY,24, 24, 2205,"set select 1105 set detail 2205 set msg ��F�̋ʂ��c"));
		v.addElement(new ItemData(1106, itemImg, 144, itemY,24, 24, 2206,"set select 1106 set detail 2206 set msg ���F�̋ʂ��c"));
		v.addElement(new ItemData(1107, itemImg, 168, itemY,24, 24, 2207,"set select 1107 set detail 2207 set msg ���F�̋ʂ��c"));
		v.addElement(new ItemData(1108, itemImg, 192, itemY,24, 24, 2208,"set select 1108 set detail 2208 set msg �������c"));
		v.addElement(new ItemData(1109, itemImg, 216, itemY,24, 24, 2209,"set select 1109 set detail 2209 set msg ���v���c"));
		v.addElement(new ItemData(1110, itemImg, 216, itemY-24,24, 24, 2210,"set select 1110 set detail 2210 set msg �아���c"));
		v.addElement(new ItemData(1111, itemImg, 192, itemY-24,24, 24, 2211,"set select 1111 set detail 2211 set msg �l�`���c"));
		

		// �A�C�e���ڍ�
		v.addElement(new ItemData(2200, 6, 120, 0,120, 120, 0,"set msg �Ȃ�̃J�M���낤"));
		v.addElement(new ItemData(2202, 6, 120, 120,120, 120, 0,"set msg �Ȃ�̃J�M���낤"));
		v.addElement(new ItemData(2203, 6, 0, 0,120, 120, 0,"set msg �Ԃ����c"));

		v.addElement(new ItemData(2204, 5, 120, 0,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2205, 5, 0, 0,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2206, 5, 0, 120,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2207, 5, 120, 120,120, 120, 0,"set msg �Y��c"));
		v.addElement(new ItemData(2208, 19, 120, 120,120, 120, 0,"set msg �������c"));
		v.addElement(new ItemData(2209, 6, 0, 120,120, 120, 0,"set msg �j���Ȃ��c"));
		v.addElement(new ItemData(2210, 19, 120, 0,120, 120, 0,"set msg �����͂�������c"));
		v.addElement(new ItemData(2211, 16, 0, 0,120, 120, 0,"if isset show -2211 set msg �����������������c else isset item 1103 set delitem 1103 set show -2211 set msg ���w�Ɏ��������� else set msg �l�`���c end"));
		v.addElement(new CharacterData(-2211, -1, 0, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag

		v.addElement(new CharacterData(-2212, -1, 0, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag


		v.addElement(new ItemData(2216, 19, 0, 0,120, 120, 0,"set msg �ǂ�������J������"));//�^����
		/*
		ItemData itemTmp = new ItemData(2217, -1, 5, 5, 109, 33, 0,"set detail 2218"); // ����
		itemTmp.rootId = 2216;
		itemTmp.x = 68;
		itemTmp.y = 68;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);
		*/
		ItemData itemTmp;
		for ( int i = 0; i < 3; i++ )
		{
			//����
			for ( int j = 0; j < 10; j++ )
			{
				// 2240~2269
				itemTmp = new ItemData(2240 + (i * 10) + j, 13, 140+(j*10), 227, 10, 13, 0,null);
				itemTmp.rootId = 2216;
				itemTmp.x = 63+31+(i*24);
				itemTmp.y = 63+61;
				itemTmp.state = (j == 0) ? ItemData.STATE_SHOW : ItemData.STATE_HIDE;
				v.addElement(itemTmp);
			}
			
			//�{�^�� 2230~2232
			itemTmp = new ItemData(2230 + i, -1, 31, 77, 10, 11, 0,"set event " + (50 + i));
			itemTmp.rootId = 2216;
			itemTmp.x = 63+31+(i*24);
			itemTmp.y = 63+77;
			itemTmp.state = ItemData.STATE_SHOW;
			v.addElement(itemTmp);
		}
		itemTmp = new ItemData(2219, -1, 43, 42, 34, 16, 0,"set event 48"); // �����
		itemTmp.rootId = 2216;
		itemTmp.x = 63+43;
		itemTmp.y = 63+42;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);

		v.addElement(new ItemData(2218, 19, 120, 0,120, 120, 0,"set msg ���̗������c"));//��
		itemTmp = new ItemData(2221, -1, 5, 5, 109, 33, 0,"set detail 2216"); //�\��
		itemTmp.rootId = 2218;
		itemTmp.x = 68;
		itemTmp.y = 68;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);
		v.addElement(new ItemData(2290, 19, 0, 120,120, 120, 0,null));//�J����
		itemTmp = new ItemData(2291, 5, 120, 120,120, 120, 0,null); // �J�����Ƃ��̃A�C�e��
		itemTmp.rootId = 2290;
		itemTmp.x = 63;
		itemTmp.y = 38;
		itemTmp.state = ItemData.STATE_SHOW;
		
		v.addElement(itemTmp);




		v.addElement(new ItemData(2301, 14, 0, 120,120, 120, 0,null)); // �S�~���ォ��



		CharacterData[] ret = new CharacterData[v.size()];
		for ( int i = 0; i < v.size(); i++ )
		{
			ret[i] = (CharacterData) v.elementAt(i);
		}
		return ret;
	}
	
	private static void makeHall(Vector v, int id, int x, int y)
	{
		v.addElement(new CharacterData(id, 3, 4, x, y,  149,113,24,24,"if isset item 1109 set msg �����P�Q�c else isset item 1104 set delitem 1104 set show " + (id+20) + " set event 20 else isset item 1105 set delitem 1105 set show " + (id+40) + " set event 20 else isset item 1106 set delitem 1106 set show " + (id+60) + " set event 20 else isset item 1107 set delitem 1107 set show " + (id+80) + " set event 20 else set msg �����󂢂Ă�c end"));//��
		int itemY = 216;
		CharacterData cd;
		cd = new CharacterData(id+20, 3, 4, x, y,  96,itemY,24,24,"set hide " + (id+20) + " set additemonly 1104",CharacterData.STATE_HIDE);
		//if ( cd.id == 431 ) cd.state = CharacterData.STATE_SHOW;
		v.addElement(cd);//��
		cd = new CharacterData(id+40, 3, 4, x, y,  120,itemY,24,24,"set hide " + (id+40) + " set additemonly 1105",CharacterData.STATE_HIDE);
		//if ( cd.id == 440 ) cd.state = CharacterData.STATE_SHOW;
		v.addElement(cd);//���
		cd = new CharacterData(id+60, 3, 4, x, y,  144,itemY,24,24,"set hide " + (id+60) + " set additemonly 1106",CharacterData.STATE_HIDE);
		//if ( cd.id == 461 ) cd.state = CharacterData.STATE_SHOW;
		v.addElement(cd);//����
	v.addElement(new CharacterData(id+80, 3, 4, x, y,  168,itemY,24,24,"set hide " + (id+80) + " set additemonly 1107",CharacterData.STATE_HIDE));//����
	}
}