/*
 * auto generate by com.strnet.escape-maker
 */
package com.strnet.game.main;

import java.util.Vector;

public class Data
{
	private static String[] stories = null;
	private static GroupData[] groups = null;

	public static String getStoryCode(int id)
	{
		return stories[id];
	}

	public static GroupData getGroupData(int id)
	{
		return groups[id];
	}
	
	public static CharacterData[] make()
	{
		Vector v = new Vector();
		Vector s = new Vector();
		Vector g = new Vector();
		GroupData gd = null;
s.addElement("set image 2 set image -1 set msg �����́c set msg �������c��𐬕����Ɂc set msg �c�܂��c�c set msg �c�����߂�ꂽ�̂��c set play 0");
s.addElement("set msg �w�c���^�V�m�c�k�C�O�c���~�c�x set msg �w�c��V�^�m�c�x set image 60 set msg �w�c�I�}�G�c�J�c�x set msg �w�����V��������V�^�����i�i�i�x set msg �a�����@�d���� set title 0");
s.addElement("set msg �o��ꂽ�c set msg �ȒP�Ȉ˗��������ȁc set msg ����H�|�P�b�g�Ɏ莆���c set msg �w�Ō�܂Ńv���C���Ă���āx set msg �w�c���肪�Ƃ��c�x set msg �w�{���Ɂc�����l�x set msg �a�������@�d���� set title 0");
RoomData.addRooms(0, "-1:-1:3:1"); // �h�A��
RoomData.addRooms(1, "-1:-1:0:2"); // �G��
RoomData.addRooms(2, "-1:-1:1:3"); // ���b�J�[��
RoomData.addRooms(3, "-1:-1:2:0"); // �I��
RoomData.addRooms(4, "-1:3:-1:-1"); // �I�̗�
RoomData.addRooms(5, "-1:1:-1:-1"); // �X�{�^���ڍ�
RoomData.addRooms(6, "-1:1:-1:-1"); // �e�[�u���ڍ�
RoomData.addRooms(7, "-1:0:-1:-1"); // �F�p�l���ڍ�
RoomData.addRooms(8, "-1:7:-1:-1"); // �F�p�l������
RoomData.addRooms(9, "-1:6:-1:-1"); // �e�[�u���ڍׂQ
v.addElement(new CharacterData(-100, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�F�p�l���̓����
v.addElement(new CharacterData(-101, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�ǔO��
v.addElement(new CharacterData(-102, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //���b�J�[�̓����
v.addElement(new CharacterData(-103, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�X�{�^���̓����
v.addElement(new CharacterData(-104, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�X�{�^���̓����2
v.addElement(new CharacterData(-105, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //���b�J�[�̌��O��
v.addElement(new CharacterData(-106, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�ג������擾
v.addElement(new CharacterData(-107, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //���������t���O
v.addElement(new CharacterData(1, 4, 0, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�h�A�w�i
v.addElement(new CharacterData(2, 12, 0, 81,42, 0,0,74,131,"set msg ������������o�����c", CharacterData.STATE_SHOW, -1)); //�h�A
v.addElement(new CharacterData(3, -1, 0, 84,98, 0,0,14,14,"if isset item 143 set msg ��������Ȃ� else isset item 141 set delitem 141 set hide 3 set hide 2 set show 4 set msg �J�����I else set msg �J���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�h�A�m�u
v.addElement(new CharacterData(4, 11, 0, 81,35, 0,0,74,155,"set story 2", CharacterData.STATE_HIDE, -1)); //�J�����h�A
v.addElement(new CharacterData(5, 16, 0, 20,134, 0,0,60,60,"set go 7", CharacterData.STATE_SHOW, -1)); //�F�p�l����
v.addElement(new CharacterData(6, 48, 0, 155,60, 0,0,83,83,"set msg �Ȃ񂾁c����c", CharacterData.STATE_HIDE, -1)); //�ǂ̕���
v.addElement(new CharacterData(7, 17, 0, 140,154, 0,0,22,44,"if isset item 154 set delitem 154 set show 8 set show 9 set msg ����Łc else isset show 8 set msg �w�����l�x else set msg ����́c end", CharacterData.STATE_SHOW, -1)); //�ꂭ��
v.addElement(new CharacterData(8, 3, 0, 140,157, 168,24,24,24,"set msg ����Łc", CharacterData.STATE_HIDE, -1)); //�\�����아
v.addElement(new CharacterData(9, 3, 0, 110,180, 96,0,24,24,"set hide 9 set item 141 set msg ������ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă�����
v.addElement(new CharacterData(10, 4, 1, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�G���w�i
v.addElement(new CharacterData(11, 44, 1, 149,59, 0,0,67,46,"set msg ��̕��i�悾�c", CharacterData.STATE_SHOW, -1)); //���i��
v.addElement(new CharacterData(12, 44, 1, 159,94, 67,0,8,8,"set item 143 set hide 12 set msg �ג���������ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //���i�惍�b�J�[��
v.addElement(new CharacterData(13, 24, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_HIDE, -1)); //�X�{�^�������
v.addElement(new CharacterData(14, 23, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_SHOW, -1)); //�X�{�^���������
v.addElement(new CharacterData(15, 23, 1, 38,122, 0,0,64,64,"set hide 15 set show 16 set msg ���Ԃ���", CharacterData.STATE_HIDE, -1)); //�������X�{�^���������
v.addElement(new CharacterData(16, 25, 1, 38,122, 0,0,64,64,"set hide 16 set show 15 set msg ���Ԃ���", CharacterData.STATE_HIDE, -1)); //�������X�{�^���������
v.addElement(new CharacterData(17, 28, 1, 82,156, 0,0,120,48,"set msg �e�[�u����", CharacterData.STATE_SHOW, -1)); //�e�[�u��
v.addElement(new CharacterData(18, -1, 1, 111,186, 0,0,62,18,"set go 6", CharacterData.STATE_SHOW, -1)); //�e�[�u����
v.addElement(new CharacterData(19, 34, 1, 102,126, 0,0,54,49,"if isset item 145 set delitem 145 set show 21 set msg �����\�����ꂽ�I else set msg ���j�^�� end", CharacterData.STATE_SHOW, -1)); //���j�^
v.addElement(new CharacterData(20, 3, 1, 44,190, 144,24,24,24,"set hide 20 set item 153 set msg �ٖD�������ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă����ٖD����
v.addElement(new CharacterData(21, 34, 1, 112,137, 54,0,36,16,"set msg �ǂ������Ӗ����낤�c", CharacterData.STATE_HIDE, -1)); //���j�^����
v.addElement(new CharacterData(22, 47, 3, 0,0, 0,0,120,120,"", CharacterData.STATE_SHOW, -1)); //�H��f���Ă鋾
v.addElement(new CharacterData(23, 4, 3, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�I���w�i
v.addElement(new CharacterData(24, 14, 3, 20,50, 0,0,85,142,"set msg �����Ȃ�", CharacterData.STATE_SHOW, -1)); //�I
v.addElement(new CharacterData(25, 3, 3, 55,118, 168,24,24,24,"set hide 25 set item 154 set msg �아����ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�����Ă�아
v.addElement(new CharacterData(26, 52, 3, 25,10, 0,0,82,229,"if isset item 153 set delitem 153 set hide 26 set msg �j�Ŗڂ��h������������I else set msg �w�c�x end", CharacterData.STATE_SHOW, -1)); //�ʗH��
v.addElement(new CharacterData(27, -1, 3, 93,144, 0,0,36,38,"set go 4", CharacterData.STATE_SHOW, -1)); //�I����
v.addElement(new CharacterData(28, 27, 3, 133,90, 0,21,62,27,"set msg �ǂ�ȈӖ����c", CharacterData.STATE_SHOW, -1)); //�Q�i���v���[�g
v.addElement(new CharacterData(29, 27, 3, 148,59, 0,0,32,21,"if isset show -102 set msg ���������Ȃ� else isset show 38 isset show 44 isset show 47 isset show 35 isset hide 41 set show 31 set show -102 set msg ���������� else set msg ���͂��Ⴄ�݂����c end", CharacterData.STATE_SHOW, -1)); //�Q�i���{�^��
v.addElement(new CharacterData(30, 37, 3, 120,155, 23,0,21,28,"set item 149 set hide 30 set msg �ʂ�����݂���ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�u���Ă���ʂ������
v.addElement(new CharacterData(31, 3, 3, 151,155, 73,0,23,24,"set hide 31 set item 140 set msg �V�[�g����ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă����V�[�g
v.addElement(new CharacterData(32, 4, 2, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //���b�J�[���w�i
v.addElement(new CharacterData(33, 7, 2, 14,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�P�w�i
v.addElement(new CharacterData(34, 7, 2, 18,53, 36,13,26,127,"set show 35 set hide 34", CharacterData.STATE_SHOW, -1)); //���b�J�[�P�h�A
v.addElement(new CharacterData(35, 7, 2, 41,50, 65,10,21,135,"set show 34 set hide 35", CharacterData.STATE_HIDE, -1)); //���b�J�[�P�J�h�A
v.addElement(new CharacterData(36, 7, 2, 50,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�Q�w�i
v.addElement(new CharacterData(37, 7, 2, 54,53, 36,13,26,127,"set show 38 set hide 37", CharacterData.STATE_SHOW, -1)); //���b�J�[�Q�h�A
v.addElement(new CharacterData(38, 7, 2, 77,50, 65,10,21,135,"set show 37 set hide 38", CharacterData.STATE_HIDE, -1)); //���b�J�[�Q�J�h�A
v.addElement(new CharacterData(39, 7, 2, 86,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�R�w�i
v.addElement(new CharacterData(40, 7, 2, 90,53, 36,13,26,127,"set show 41 set hide 40", CharacterData.STATE_SHOW, -1)); //���b�J�[�R�h�A
v.addElement(new CharacterData(41, 7, 2, 113,50, 65,10,21,135,"set show 40 set hide 41", CharacterData.STATE_HIDE, -1)); //���b�J�[�R�J�h�A
v.addElement(new CharacterData(42, 7, 2, 122,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�S�w�i
v.addElement(new CharacterData(43, 7, 2, 126,53, 36,13,26,127,"if isset show -105 set show 44 set hide 43 else isset item 143 set delitem 143 set show -105 set msg �J�`���Ɖ��������I else set msg �J���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //���b�J�[�S�h�A
v.addElement(new CharacterData(44, 7, 2, 149,50, 65,10,21,135,"set show 43 set hide 44", CharacterData.STATE_HIDE, -1)); //���b�J�[�S�J�h�A
v.addElement(new CharacterData(45, 7, 2, 158,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�T�w�i
v.addElement(new CharacterData(46, 7, 2, 162,53, 36,13,26,127,"set show 47 set hide 46", CharacterData.STATE_SHOW, -1)); //���b�J�[�T�h�A
v.addElement(new CharacterData(47, 7, 2, 185,50, 65,10,21,135,"set show 46 set hide 47", CharacterData.STATE_HIDE, -1)); //���b�J�[�T�J�h�A
v.addElement(new CharacterData(48, 15, 4, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�I�̗��w�i
v.addElement(new CharacterData(49, 58, 4, 1,144, 0,0,215,50,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, -1)); //�����
v.addElement(new CharacterData(50, 29, 6, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�e�[�u���ڍהw�i
v.addElement(new CharacterData(51, 59, 6, 61,6, 0,0,99,94,"set msg �Ȃ񂾂���c", CharacterData.STATE_HIDE, -1)); //�e�[�u�������
v.addElement(new CharacterData(52, 29, 6, 46,120, 46,120,156,31,"set go 9", CharacterData.STATE_SHOW, -1)); //�e�[�u������
v.addElement(new CharacterData(53, 26, 9, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�e�[�u���ڍׂQ�w�i
v.addElement(new CharacterData(54, 3, 9, 100,120, 50,0,20,24,"set hide 54 set item 139 set msg ���т����ɓ��ꂽ", CharacterData.STATE_SHOW, -1)); //�����Ă鏬�т�
v.addElement(new CharacterData(55, 62, 9, 33,90, 0,0,171,72,"if isset item 149 set story 1 else isset item 146 set delitem 146 set hide 55 set msg �w�c�k�C�O���~�c�A���K�g�E�c�x else set msg �w�c�k�C�c�O���c�~�c�x end", CharacterData.STATE_SHOW, -1)); //�H��
v.addElement(new CharacterData(56, 56, 9, 97,98, 64,8,102,25,"if isset item 149 set story 1 else isset item 137 set hide 56 set show 57 set msg �ڋʂ�����ʂ����I else set msg �w�c�k�C�c�O���c�~�c�x end", CharacterData.STATE_SHOW, 55)); //�H��̖ڋ�
v.addElement(new CharacterData(57, 37, 9, 140,180, 45,0,10,10,"set item 147 set hide 57 set msg �ڋʂ���ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�����Ă�ڋ�
v.addElement(new CharacterData(58, 22, 7, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�F�p�l���w�i
v.addElement(new CharacterData(59, 33, 7, 66,94, 0,22,110,33,"if isset show -100 set msg ���������Ȃ� else isset show 76 isset show 80 isset show 87 isset show 92 set show -100 set show 51 set show 61 set msg �����o�Ă����I else set msg ���͂��Ⴄ�݂����c end", CharacterData.STATE_SHOW, -1)); //�F�p�l���{�^��
v.addElement(new CharacterData(60, 33, 7, 66,130, 0,55,110,33,"if isset show -100 set msg ���������Ȃ� else set msg �����o�Ă���̂��ȁH end", CharacterData.STATE_SHOW, -1)); //�F�p�l���o��
v.addElement(new CharacterData(61, 6, 7, 49,116, 0,0,120,120,"set item 137 set hide 61 set msg �X�p�i����ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă����X�p�i
v.addElement(new CharacterData(62, -1, 7, 58,177, 0,0,125,14,"set go 8", CharacterData.STATE_SHOW, -1)); //�F�p�l��������
v.addElement(new CharacterData(63, 41, 8, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�F�p�l�������w�i
v.addElement(new CharacterData(64, 42, 8, 53,180, 0,0,112,23,"set hide 64 set item 145 set msg �����R������ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�����Ă郊���R��
v.addElement(new CharacterData(65, 18, 5, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�ǌ�
v.addElement(new CharacterData(66, 20, 5, 77,119, 66,0,30,11,"if isset show -104 set msg ���������Ȃ� else isset show 99 isset show 103 isset show 107 isset show 111 isset show 115 isset show 117 isset show 123 isset show 125 isset show 129 set show 20 set show -104 set msg �������������I else set msg ���͂��Ⴄ�݂����c end", CharacterData.STATE_SHOW, -101)); //�`�F�b�N�{�^��
v.addElement(new CharacterData(67, 20, 5, 118,119, 66,11,30,11,"set msg �����󂢂Ă�", CharacterData.STATE_SHOW, -101)); //���̌�
v.addElement(new CharacterData(68, 19, 5, 37,9, 0,0,150,150,"if isset hide 69 set hide 68 set show 65 set hide 14 set show 13 set show 15 set show -101 set msg �t�^���O�����I else set msg �ł��t�^���c end", CharacterData.STATE_SHOW, -1)); //�ǔ�
v.addElement(new CharacterData(69, 21, 5, 39,11, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�P
v.addElement(new CharacterData(70, 21, 5, 166,11, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�Q
v.addElement(new CharacterData(71, 21, 5, 39,137, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�R
v.addElement(new CharacterData(72, 21, 5, 165,137, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�S
gd = new GroupData();
gd.add(new CharacterData(73, 33, 7, 59,64, 0,0,22,22,"set toggle 0", CharacterData.STATE_SHOW, -1)); //�F�p�l���P
gd.add(new CharacterData(74, 33, 7, 59,64, 22,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
gd.add(new CharacterData(75, 33, 7, 59,64, 44,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
gd.add(new CharacterData(76, 33, 7, 59,64, 66,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
gd.add(new CharacterData(77, 33, 7, 59,64, 88,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(79, 33, 7, 92,64, 0,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
gd.add(new CharacterData(80, 33, 7, 92,64, 22,0,22,22,"set toggle 1", CharacterData.STATE_SHOW, -1)); //�F�p�l���Q
gd.add(new CharacterData(81, 33, 7, 92,64, 44,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
gd.add(new CharacterData(82, 33, 7, 92,64, 66,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
gd.add(new CharacterData(83, 33, 7, 92,64, 88,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(85, 33, 7, 125,64, 0,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
gd.add(new CharacterData(86, 33, 7, 125,64, 22,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
gd.add(new CharacterData(87, 33, 7, 125,64, 44,0,22,22,"set toggle 2", CharacterData.STATE_SHOW, -1)); //�F�p�l���R
gd.add(new CharacterData(88, 33, 7, 125,64, 66,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
gd.add(new CharacterData(89, 33, 7, 125,64, 88,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(91, 33, 7, 158,64, 0,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
gd.add(new CharacterData(92, 33, 7, 158,64, 22,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
gd.add(new CharacterData(93, 33, 7, 158,64, 44,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
gd.add(new CharacterData(94, 33, 7, 158,64, 66,0,22,22,"set toggle 3", CharacterData.STATE_SHOW, -1)); //�F�p�l���S
gd.add(new CharacterData(95, 33, 7, 158,64, 88,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(97, 20, 5, 72,34, 0,0,22,22,"set toggle 4", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���P
gd.add(new CharacterData(98, 20, 5, 72,34, 22,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���P
gd.add(new CharacterData(99, 20, 5, 72,34, 44,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���P
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(101, 20, 5, 103,34, 0,0,22,22,"set toggle 5", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���Q
gd.add(new CharacterData(102, 20, 5, 103,34, 22,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���Q
gd.add(new CharacterData(103, 20, 5, 103,34, 44,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���Q
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(105, 20, 5, 134,34, 0,0,22,22,"set toggle 6", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���R
gd.add(new CharacterData(106, 20, 5, 134,34, 22,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���R
gd.add(new CharacterData(107, 20, 5, 134,34, 44,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���R
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(109, 20, 5, 72,64, 0,0,22,22,"set toggle 7", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���S
gd.add(new CharacterData(110, 20, 5, 72,64, 22,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���S
gd.add(new CharacterData(111, 20, 5, 72,64, 44,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���S
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(113, 20, 5, 103,64, 0,0,22,22,"set toggle 8", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���T
gd.add(new CharacterData(114, 20, 5, 103,64, 22,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���T
gd.add(new CharacterData(115, 20, 5, 103,64, 44,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���T
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(117, 20, 5, 134,64, 0,0,22,22,"set toggle 9", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���U
gd.add(new CharacterData(118, 20, 5, 134,64, 22,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���U
gd.add(new CharacterData(119, 20, 5, 134,64, 44,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���U
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(121, 20, 5, 72,94, 0,0,22,22,"set toggle 10", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���V
gd.add(new CharacterData(122, 20, 5, 72,94, 22,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���V
gd.add(new CharacterData(123, 20, 5, 72,94, 44,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���V
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(125, 20, 5, 103,94, 0,0,22,22,"set toggle 11", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���W
gd.add(new CharacterData(126, 20, 5, 103,94, 22,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���W
gd.add(new CharacterData(127, 20, 5, 103,94, 44,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���W
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(129, 20, 5, 134,94, 0,0,22,22,"set toggle 12", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���X
gd.add(new CharacterData(130, 20, 5, 134,94, 22,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���X
gd.add(new CharacterData(131, 20, 5, 134,94, 44,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���X
g.addElement(gd);
gd.addAlltoVector(v);
v.addElement(new CharacterData(133, 1, 0, 42,25, 46,0,18,30,"set event 1", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�������J�[�\��
v.addElement(new CharacterData(134, 1, 0, 60,25, 64,0,126,30,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�����A�C�e���ꗗ
v.addElement(new CharacterData(135, 1, 0, 186,25, 190,0,18,30,"set event 2", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�����E�J�[�\��
v.addElement(new CharacterData(136, 1, 0, 60,60, 0,30,126,126,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�����ڍ׃A�C�e����
v.addElement(new ItemData(137, 3, 0, 0, 24, 24, 637, "set select 137 set detail 637 set msg �X�p�i���c", -1)); // �X�p�i
v.addElement(new ItemData(637, 6, 0, 0, 120, 120, 0, "set msg ���Ɏg�����c", -1)); // �ڍ׃X�p�i
v.addElement(new ItemData(138, 3, 24, 0, 24, 24, 638, "set select 138 set detail 638 set msg �����_���c", -1)); // �����_
v.addElement(new ItemData(638, 32, 0, 0, 120, 120, 0, "set msg ���\�����c", -1)); // �ڍג����_
v.addElement(new ItemData(139, 3, 48, 0, 24, 24, 639, "set select 139 set detail 639 set msg ���т񂾁c", -1)); // ���т�
v.addElement(new ItemData(639, 31, 0, 0, 120, 120, 0, "set msg ���ɉt�̂������Ă�", -1)); // �ڍ׏��т�
v.addElement(new ItemData(140, 3, 72, 0, 24, 24, 640, "set select 140 set detail 640 set msg �V�[�g���c", -1)); // �V�[�g
v.addElement(new ItemData(640, 9, 0, 0, 120, 120, 0, "if isset item 139 set delitem 139 set delitem 140 set item 142 set msg �������o�Ă����I else set msg ����������ĂȂ��c end", -1)); // �ڍ׃V�[�g
v.addElement(new ItemData(141, 3, 96, 0, 24, 24, 641, "set select 141 set detail 641 set msg �����c", -1)); // ��
v.addElement(new ItemData(641, 8, 0, 0, 120, 120, 0, "set msg ����Łc", -1)); // �ڍ׌�
v.addElement(new ItemData(142, 3, 120, 0, 24, 24, 642, "set select 142 set detail 642 set msg �����V�[�g���c", -1)); // �����V�[�g
v.addElement(new ItemData(642, 13, 0, 0, 120, 120, 0, "set msg �ǂ������Ӗ����c", -1)); // �ڍו����V�[�g
v.addElement(new ItemData(143, 3, 144, 0, 24, 24, 643, "set select 143 set detail 643 set msg �ג��������c", -1)); // �ג�����
v.addElement(new ItemData(643, 36, 0, 0, 120, 120, 0, "set msg �Ȃ�̌����낤�c", -1)); // �ڍ׍ג�����
v.addElement(new ItemData(144, 3, 168, 0, 24, 24, 644, "set select 144 set detail 644 set msg �����c", -1)); // ��
v.addElement(new ItemData(644, 45, 0, 0, 120, 120, 0, "set msg �����c", -1)); // �ڍ׋�
v.addElement(new ItemData(145, 3, 192, 0, 24, 24, 645, "set select 145 set detail 645 set msg �����R�����c", -1)); // �����R��
v.addElement(new ItemData(645, 39, 0, 0, 120, 120, 0, "set msg ���Ɏg�����c", -1)); // �ڍ׃����R��
v.addElement(new ItemData(146, 3, 216, 0, 24, 24, 646, "set select 146 set detail 646 set msg �ʂ�����݂��c", -1)); // �ʂ������
v.addElement(new ItemData(646, 51, 0, 0, 120, 120, 0, "if isset item 150 set delitem 150 set delitem 146 set show -107 set item 152 set msg �����������I else set msg �ʂ�����݂��c end", -1)); // �ڍׂʂ������
v.addElement(new ItemData(147, 3, 0, 24, 24, 24, 647, "set select 147 set detail 647 set msg �ڋʂ��c", -1)); // �ڋ�
v.addElement(new ItemData(647, 53, 0, 0, 120, 120, 0, "set msg �ڋʂ��c", -1)); // �ڍזڋ�
v.addElement(new ItemData(148, 3, 24, 24, 24, 24, 648, "set select 148 set detail 648 set msg ���Q���c", -1)); // ���Q
v.addElement(new ItemData(648, 47, 0, 0, 120, 120, 0, "set msg �����f���Ă�c", -1)); // �ڍ׋��Q
v.addElement(new ItemData(149, 3, 48, 24, 24, 24, 649, "set select 149 set detail 649 set msg �ڋʂ̖����ʂ�����݂��c", -1)); // �ڋʂ̖����ʂ������
v.addElement(new ItemData(649, 63, 0, 0, 120, 120, 0, "if isset item 147 set delitem 147 set delitem 149 set item 146 set msg �ڋʂ�t�����I else set msg �Жڂ����Ă�c end", -1)); // �ڍזڋʂ̖����ʂ������
v.addElement(new ItemData(150, 3, 72, 24, 24, 24, 650, "set select 150 set detail 650 set msg �n�T�~���c", -1)); // �n�T�~
v.addElement(new ItemData(650, 53, 0, 0, 120, 120, 0, "set msg �n�T�~���c", -1)); // �ڍ׃n�T�~
v.addElement(new ItemData(151, 3, 96, 24, 24, 24, 651, "set select 151 set detail 651 set msg �ڋʂ̖������������ʂ�����݂��c", -1)); // �ڋʂ̖������������ʂ������
v.addElement(new ItemData(651, 54, 0, 0, 120, 120, 0, "if isset item 147 set delitem 147 set delitem 151 set item 152 set msg �ڋʂ�t�����I else isset hide -106 set show -106 set item 143 set msg �ג������������Ă�! else set msg �����͖Ȃ��炯���c end", -1)); // �ڍזڋʂ̖������������ʂ������
v.addElement(new ItemData(152, 3, 120, 24, 24, 24, 652, "set select 152 set detail 652 set msg ���������ʂ�����݂��c", -1)); // ���������ʂ������
v.addElement(new ItemData(652, 55, 0, 0, 120, 120, 0, "if isset hide -106 set show -106 set item 143 set msg �ג������������Ă�! else isset item 153 set delitem 153 set delitem 152 set item 146 set msg ������D�����I else set msg �����͖Ȃ��炯���c�c end", -1)); // �ڍו��������ʂ������
v.addElement(new ItemData(153, 3, 144, 24, 24, 24, 653, "set select 153 set detail 653 set msg �ٖD����c", -1)); // �ٖD����
v.addElement(new ItemData(653, 40, 0, 0, 120, 120, 0, "set msg �ٖD����c", -1)); // �ڍ׍ٖD����
v.addElement(new ItemData(154, 3, 168, 24, 24, 24, 654, "set select 154 set detail 654 set msg �아���c", -1)); // �아
v.addElement(new ItemData(654, 57, 0, 0, 120, 120, 0, "set msg �아���c", -1)); // �ڍ׌아

		CharacterData[] ret = new CharacterData[v.size()];
		for ( int i = 0; i < v.size(); i++ )
		{
			ret[i] = (CharacterData) v.elementAt(i);
		}

		stories = new String[s.size()];
		for ( int i = 0; i < s.size(); i++ )
		{
			stories[i] = (String) s.elementAt(i);
		}
		groups = new GroupData[g.size()];
		for ( int i = 0; i < g.size(); i++ )
		{
			groups[i] = (GroupData) g.elementAt(i);
		}

		return ret;
	}
}
