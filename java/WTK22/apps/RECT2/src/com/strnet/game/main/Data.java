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
s.addElement("set image 2 set image -1 set msg �����́c set msg �������c��𐬕����Ɂc set msg �c�c�c set msg �c�����߂�ꂽ�̂��c set play 0");
s.addElement("set msg �o��ꂽ�c���ǁc set msg �����c set msg �����ł��ĂȂ��c set msg EndingNo.3 set msg �m�������g������... set msg �l�`�𕜌����悤 set title 0");
s.addElement("set msg �o��ꂽ�c set msg ����Łc set msg �����o�������ȁc set msg �w�c���^�V�n�c�R�R�_���c�x set msg EndingNo.2 set msg �m�������g������... set msg �e�[�u�������ŉf���c�����c set title 0");
s.addElement("set msg �w�c���^�V�m�c�k�C�O�c���~�c�x set msg �w�c��V�^�m�c�x set image 60 set msg �w�c�I�}�G�c�J�c�x set msg �w�����V��������V�^�����i�i�i�x set msg EndingNo.1 set msg �m�������g������... set msg �H��Ƙb��������Y��ȃk�C�O���~���c set title 0");
s.addElement("set msg �o��ꂽ�c set msg ����Łc set msg �����o�������ȁc set msg ����H�|�P�b�g�Ɏ莆���c set msg �w�c�A���c�K�g�E�c�x set msg �@ set msg ���܂� set msg �H��ɍٖD������c set title 0");
s.addElement("set msg �o��ꂽ�c set msg �ȒP�Ȉ˗��������ȁc set msg ��H�|�P�b�g�ɉ����c set msg �w�{���Ɂc���肪�Ƃ��c�x set msg �a�������@�d���� set title 0");
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
RoomData.addRooms(10, "-1:3:-1:-1"); // �I�̉�
v.addElement(new CharacterData(-100, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�F�p�l���̓����
v.addElement(new CharacterData(-101, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�ǔO��
v.addElement(new CharacterData(-102, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //���b�J�[�̓����
v.addElement(new CharacterData(-103, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�X�{�^���̓����
v.addElement(new CharacterData(-104, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�X�{�^���̓����2
v.addElement(new CharacterData(-105, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //���b�J�[�̌��O��
v.addElement(new CharacterData(-106, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�ג������擾
v.addElement(new CharacterData(-107, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //���������t���O
v.addElement(new CharacterData(-108, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�아�Q�t���O
v.addElement(new CharacterData(-109, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //�l�`�̎�t���O
v.addElement(new CharacterData(1, 4, 0, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�h�A�w�i
v.addElement(new CharacterData(2, 12, 0, 81,42, 0,0,74,131,"set msg ������������o�����c", CharacterData.STATE_SHOW, -1)); //�h�A
v.addElement(new CharacterData(3, -1, 0, 84,98, 0,0,14,14,"if isset item 158 set msg ��������Ȃ� else isset item 156 set delitem 156 set hide 3 set hide 2 set show 4 set msg �J�����I else set msg �J���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�h�A�m�u
v.addElement(new CharacterData(4, 11, 0, 81,35, 0,0,74,155,"if isset show -108 isset show 10 set story 5 else isset show 10 set story 4 else isset show 8 set story 2 else isset show 9 set story 2 else set story 1 end", CharacterData.STATE_HIDE, -1)); //�J�����h�A
v.addElement(new CharacterData(5, 16, 0, 20,134, 0,0,60,60,"set go 7", CharacterData.STATE_SHOW, -1)); //�F�p�l����
v.addElement(new CharacterData(6, 48, 0, 155,60, 0,0,83,83,"set msg �Ȃ񂾁c����c", CharacterData.STATE_HIDE, -1)); //�ǂ̕���
v.addElement(new CharacterData(7, 46, 0, 136,170, 0,0,32,25,"if isset show 8 set msg ����Ő����ł������ȁc else isset hide -109 isset show 91 isset show 97 isset show 103 isset show 109 isset show 114 isset show 118 isset show 122 isset show 126 isset show 130 isset show 134 isset show 138 isset show 142 isset show 146 isset show 46 isset show 53 isset show 56 isset show 43 isset show 49 set show 38 set show -109 set msg ���̕��ŉ��������c�I else isset item 170 set delitem 170 set show 8 set msg ���t�����I else isset item 171 set delitem 171 set show 8 set msg ���t�����I else set msg ����ɍ����h�点�����c end", CharacterData.STATE_HIDE, -1)); //��̖����l�`
v.addElement(new CharacterData(8, 46, 0, 139,154, 32,0,23,17,"if isset item 169 set delitem 169 set show 10 set msg ����Łc else set msg ����Ő����ł������ȁc end", CharacterData.STATE_HIDE, -1)); //�t�����l�`�̎�
v.addElement(new CharacterData(9, 17, 0, 140,154, 0,0,22,44,"if isset item 169 set delitem 169 set show 10 set msg ����Łc else isset show 10 set msg �w�����l�x else set msg ����́c end", CharacterData.STATE_HIDE, -1)); //�ꂭ��
v.addElement(new CharacterData(10, 3, 0, 139,154, 168,24,24,24,"set msg �w�����l�x", CharacterData.STATE_HIDE, -1)); //�\�����아
v.addElement(new CharacterData(11, 52, 0, 81,10, 0,0,92,229,"if isset item 169 set hide 11 set msg �w�������₠�����������I�x else set msg �w��Z�Z�Z�H�G��g�����x end", CharacterData.STATE_HIDE, -1)); //�ʗH��
v.addElement(new CharacterData(12, 37, 0, 50,176, 0,0,23,27,"set item 159 set hide 12 set msg ������ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�u���Ă��鋾
v.addElement(new CharacterData(13, 4, 1, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�G���w�i
v.addElement(new CharacterData(14, 44, 1, 149,59, 0,0,67,46,"if isset hide -108 isset show 11 isset show 90 isset show 96 isset show 102 isset show 108 isset item 165 set delitem 165 set show -108 set show 27 set msg ���������� else set msg ��̕��i�悾�c end", CharacterData.STATE_SHOW, -1)); //���i��
v.addElement(new CharacterData(15, 24, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_HIDE, -1)); //�X�{�^�������
v.addElement(new CharacterData(16, 23, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_SHOW, -1)); //�X�{�^���������
v.addElement(new CharacterData(17, 23, 1, 38,122, 0,0,64,64,"set hide 17 set show 18 set msg ���Ԃ���", CharacterData.STATE_HIDE, -1)); //�������X�{�^���������
v.addElement(new CharacterData(18, 25, 1, 38,122, 0,0,64,64,"set hide 18 set show 17 set msg ���Ԃ���", CharacterData.STATE_HIDE, -1)); //�������X�{�^���������
v.addElement(new CharacterData(19, 35, 1, 47,153, 22,0,22,22,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, 18)); //�G�}�[�N�P
v.addElement(new CharacterData(20, 35, 1, 71,153, 44,0,22,22,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, 18)); //�G�}�[�N�Q
v.addElement(new CharacterData(21, 35, 1, 71,130, 22,22,22,22,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, 18)); //�G�}�[�N�R
v.addElement(new CharacterData(22, 28, 1, 82,156, 0,0,120,48,"if isset item 159 set msg ���Α�����f�������c else set msg �e�[�u���� end", CharacterData.STATE_SHOW, -1)); //�e�[�u��
v.addElement(new CharacterData(23, -1, 1, 111,186, 0,0,62,18,"set go 6", CharacterData.STATE_SHOW, -1)); //�e�[�u����
v.addElement(new CharacterData(24, 34, 1, 102,126, 0,0,54,49,"if isset item 160 set delitem 160 set show 25 set msg �����\�����ꂽ�I else set msg ���j�^�� end", CharacterData.STATE_SHOW, -1)); //���j�^
v.addElement(new CharacterData(25, 34, 1, 109,136, 58,0,40,13,"set msg �ǂ������Ӗ����낤�c", CharacterData.STATE_HIDE, -1)); //���j�^����
v.addElement(new CharacterData(26, 3, 1, 80,198, 144,24,24,24,"set hide 26 set item 168 set msg �ٖD�������ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă����ٖD����
v.addElement(new CharacterData(27, 3, 1, 180,182, 168,24,24,24,"set hide 27 set item 169 set msg �아����ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�����Ă�아�Q
v.addElement(new CharacterData(28, 47, 3, 0,0, 0,0,120,120,"", CharacterData.STATE_SHOW, -1)); //�H��f���Ă鋾
v.addElement(new CharacterData(29, 4, 3, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�I���w�i
v.addElement(new CharacterData(30, 14, 3, 20,50, 0,0,85,142,"set msg �����Ȃ�", CharacterData.STATE_SHOW, -1)); //�I
v.addElement(new CharacterData(31, 37, 3, 40,110, 23,0,21,28,"set item 161 set hide 31 set msg �ʂ�����݂���ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�u���Ă��鋾�ʂ������
v.addElement(new CharacterData(32, -1, 3, 29,147, 0,0,67,37,"if isset item 159 set hide 32 set delitem 159 set show 33 set msg ����u�����I else set msg �����Ȃ� end", CharacterData.STATE_SHOW, -1)); //��ԉ��̒I
v.addElement(new CharacterData(33, 37, 3, 50,148, 0,0,23,27,"if isset item 159 set delitem 159 set msg ����u�����I else set detailnoitem 28 set show 69 set msg �����f���Ă�c end", CharacterData.STATE_HIDE, -1)); //�I�ɒu������
v.addElement(new CharacterData(34, -1, 3, 93,144, 0,0,36,38,"set go 4", CharacterData.STATE_SHOW, -1)); //�I����
v.addElement(new CharacterData(35, 27, 3, 133,90, 0,21,62,27,"set msg �����̖͗l�݂�����", CharacterData.STATE_SHOW, -1)); //�Q�i���v���[�g
v.addElement(new CharacterData(36, 27, 3, 148,59, 0,0,32,21,"if isset show -102 set msg ���������Ȃ� else isset show 46 isset show 53 isset show 56 isset hide 43 isset hide 49 set show 37 set show -102 set msg ���������� else set msg ���͂��Ⴄ�݂����c end", CharacterData.STATE_SHOW, -1)); //�Q�i���{�^��
v.addElement(new CharacterData(37, 3, 3, 151,155, 73,0,23,24,"set hide 37 set item 155 set msg �V�[�g����ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă����V�[�g
v.addElement(new CharacterData(38, 46, 3, 30,38, 32,25,23,17,"set go 10", CharacterData.STATE_HIDE, -1)); //�����Ă�l�`�̎�
v.addElement(new CharacterData(39, 4, 2, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //���b�J�[���w�i
v.addElement(new CharacterData(40, 7, 2, 14,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�P�w�i
v.addElement(new CharacterData(41, 3, 2, 16,156, 72,24,24,24,"set hide 41 set item 165 set msg �n�T�~����ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�����Ă�n�T�~
v.addElement(new CharacterData(42, 7, 2, 18,53, 36,13,26,127,"set show 43 set hide 42", CharacterData.STATE_SHOW, -1)); //���b�J�[�P�h�A
v.addElement(new CharacterData(43, 7, 2, 41,50, 65,10,21,135,"set show 42 set hide 43", CharacterData.STATE_HIDE, -1)); //���b�J�[�P�J�h�A
v.addElement(new CharacterData(44, 7, 2, 50,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�Q�w�i
v.addElement(new CharacterData(45, 7, 2, 54,53, 36,13,26,127,"set show 46 set hide 45", CharacterData.STATE_SHOW, -1)); //���b�J�[�Q�h�A
v.addElement(new CharacterData(46, 7, 2, 77,50, 65,10,21,135,"set show 45 set hide 46", CharacterData.STATE_HIDE, -1)); //���b�J�[�Q�J�h�A
v.addElement(new CharacterData(47, 7, 2, 86,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�R�w�i
v.addElement(new CharacterData(48, 7, 2, 90,53, 36,13,26,127,"set show 49 set hide 48", CharacterData.STATE_SHOW, -1)); //���b�J�[�R�h�A
v.addElement(new CharacterData(49, 7, 2, 113,50, 65,10,21,135,"set show 48 set hide 49", CharacterData.STATE_HIDE, -1)); //���b�J�[�R�J�h�A
v.addElement(new CharacterData(50, 7, 2, 122,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�S�w�i
v.addElement(new CharacterData(51, 30, 2, 135,114, 0,0,10,60,"set item 153 set hide 51 set msg �����_����ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //���b�J�[�̒����_
v.addElement(new CharacterData(52, 7, 2, 126,53, 36,13,26,127,"if isset show -105 set show 53 set hide 52 else isset item 158 set delitem 158 set show -105 set msg �J�`���Ɖ��������I else set msg �J���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //���b�J�[�S�h�A
v.addElement(new CharacterData(53, 7, 2, 149,50, 65,10,21,135,"set show 52 set hide 53", CharacterData.STATE_HIDE, -1)); //���b�J�[�S�J�h�A
v.addElement(new CharacterData(54, 7, 2, 158,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //���b�J�[�T�w�i
v.addElement(new CharacterData(55, 7, 2, 162,53, 36,13,26,127,"set show 56 set hide 55", CharacterData.STATE_SHOW, -1)); //���b�J�[�T�h�A
v.addElement(new CharacterData(56, 7, 2, 185,50, 65,10,21,135,"set show 55 set hide 56", CharacterData.STATE_HIDE, -1)); //���b�J�[�T�J�h�A
v.addElement(new CharacterData(57, 15, 4, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�I�̗��w�i
v.addElement(new CharacterData(58, 3, 4, 220,160, 50,0,20,24,"if isset item 153 set delitem 153 set hide 58 set item 154 set msg ���т����ɓ��ꂽ else set msg �͂��Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�����Ă鏬�т�
v.addElement(new CharacterData(59, 35, 4, 10,15, 22,22,22,22,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, -1)); //�I�}�[�N�P
v.addElement(new CharacterData(60, 35, 4, 10,39, 44,0,22,22,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, -1)); //�I�}�[�N�Q
v.addElement(new CharacterData(61, 35, 4, 10,63, 22,0,22,22,"set msg �Ȃ񂾂���c", CharacterData.STATE_SHOW, -1)); //�I�}�[�N�R
v.addElement(new CharacterData(62, 58, 4, 1,144, 0,0,215,50,"set msg �Ȃ񂾂���c", CharacterData.STATE_HIDE, -1)); //�����
v.addElement(new CharacterData(63, 29, 6, 0,0, 0,0,240,240,"if isset item 159 set msg ���Α�����f�������c end", CharacterData.STATE_SHOW, -1)); //�e�[�u���ڍהw�i
v.addElement(new CharacterData(64, 59, 6, 61,6, 0,0,99,94,"set msg �Ȃ񂾂���c", CharacterData.STATE_HIDE, -1)); //�e�[�u�������
v.addElement(new CharacterData(65, 29, 6, 46,120, 46,120,156,31,"set go 9", CharacterData.STATE_SHOW, -1)); //�e�[�u������
v.addElement(new CharacterData(66, 60, 6, 0,0, 0,0,240,240,"set msg �w�c�A�A�A�A���K�K�K�g�g�E�c�x set hide 66", CharacterData.STATE_HIDE, -1)); //�߂��H��
v.addElement(new CharacterData(67, 26, 9, 0,0, 0,0,240,240,"if isset item 159 set msg ���Α�����f�������c end", CharacterData.STATE_SHOW, -1)); //�e�[�u���ڍׂQ�w�i
v.addElement(new CharacterData(68, 3, 9, 100,120, 168,24,24,24,"set hide 68 set item 169 set msg �아����ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�����Ă�아
v.addElement(new CharacterData(69, 56, 9, 33,90, 0,0,171,72,"if isset item 161 set hide 69 set delitem 161 set show 68 set hide 33 set show -105 set msg �w�k�C�O���~�c�A���K�g�E�c�x else isset item 168 set hide 69 set delitem 168 set hide 33 set hide 7 set hide 6 set show 9 set show 11 set show 66 set msg �w�c�x else isset item 167 set story 3 else set show 62 set show 64 set msg �w�c�k�C�c�O���c�~�c�x end", CharacterData.STATE_HIDE, -1)); //�H��
v.addElement(new CharacterData(70, 22, 7, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�F�p�l���w�i
v.addElement(new CharacterData(71, 33, 7, 66,94, 0,22,110,33,"if isset show -100 set msg ���������Ȃ� else isset show 88 isset show 97 isset show 104 isset show 108 set show -100 set show 74 set msg �����o�Ă����I else set msg ���͂��Ⴄ�݂����c end", CharacterData.STATE_SHOW, -1)); //�F�p�l���{�^��
v.addElement(new CharacterData(72, 33, 7, 66,130, 0,55,110,33,"if isset show -100 set msg ���������Ȃ� else set msg �����o�Ă���̂��ȁH end", CharacterData.STATE_SHOW, -1)); //�F�p�l���o��
v.addElement(new CharacterData(73, -1, 7, 58,177, 0,0,125,14,"set go 8", CharacterData.STATE_SHOW, -1)); //�F�p�l��������
v.addElement(new CharacterData(74, 6, 7, 49,116, 0,0,120,120,"set item 152 set hide 74 set msg �X�p�i����ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă����X�p�i
v.addElement(new CharacterData(75, 41, 8, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�F�p�l�������w�i
v.addElement(new CharacterData(76, 42, 8, 53,180, 0,0,112,23,"set hide 76 set item 160 set msg �����R������ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�����Ă郊���R��
v.addElement(new CharacterData(77, 54, 10, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�I�̉��w�i
v.addElement(new CharacterData(78, 10, 10, 0,0, 0,0,240,240,"set hide 38 set hide 78 set show 12 set item 170 set msg �l�`�̎����ɓ��ꂽ�I", CharacterData.STATE_SHOW, -1)); //�u���Ă���l�`�̎�
v.addElement(new CharacterData(79, 18, 5, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //�ǌ�
v.addElement(new CharacterData(80, 20, 5, 78,119, 66,0,30,11,"if isset show -104 isset show 114 isset show 116 isset show 121 isset show 126 isset show 129 isset show 133 isset show 136 isset show 140 isset show 146 set msg ���͂��Ⴄ�݂����c else isset show 114 isset show 116 isset show 121 isset show 126 isset show 129 isset show 133 isset show 136 isset show 140 isset show 146 set show 26 set show -104 set msg �������������I else isset show -103 set msg ���͂��Ⴄ�݂����c else isset show 112 isset show 117 isset show 120 isset show 126 isset show 130 isset show 133 isset show 136 isset show 141 isset show 146 set show -103 set show 6 set show 7 set show 82 set msg �����o�Ă����I else set msg ���͂��Ⴄ�݂����c end", CharacterData.STATE_SHOW, -101)); //�`�F�b�N�{�^��
v.addElement(new CharacterData(81, 20, 5, 118,119, 66,11,30,11,"set msg �����󂢂Ă�", CharacterData.STATE_SHOW, -101)); //���̌�
v.addElement(new CharacterData(82, 20, 5, 124,123, 98,16,18,6,"set hide 82 set item 156 set msg ������ɓ��ꂽ�I", CharacterData.STATE_HIDE, -1)); //�o�Ă�����
v.addElement(new CharacterData(83, 19, 5, 37,9, 0,0,150,150,"if isset hide 84 set hide 83 set show 79 set hide 16 set show 15 set show 17 set show -101 set msg �t�^���O�����I else set msg �ł��t�^���c end", CharacterData.STATE_SHOW, -1)); //�ǔ�
v.addElement(new CharacterData(84, 21, 5, 40,12, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�P
v.addElement(new CharacterData(85, 21, 5, 164,12, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�Q
v.addElement(new CharacterData(86, 21, 5, 40,136, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�R
v.addElement(new CharacterData(87, 21, 5, 164,136, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg �{���g���O�����I else set msg �f��ł͊O���Ȃ��c end", CharacterData.STATE_SHOW, -1)); //�{���g�S
gd = new GroupData();
gd.add(new CharacterData(88, 33, 7, 59,64, 0,0,22,22,"set toggle 0", CharacterData.STATE_SHOW, -1)); //�F�p�l���P
gd.add(new CharacterData(89, 33, 7, 59,64, 22,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
gd.add(new CharacterData(90, 33, 7, 59,64, 44,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
gd.add(new CharacterData(91, 33, 7, 59,64, 66,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
gd.add(new CharacterData(92, 33, 7, 59,64, 88,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //�F�p�l���P
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(94, 33, 7, 92,64, 0,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
gd.add(new CharacterData(95, 33, 7, 92,64, 22,0,22,22,"set toggle 1", CharacterData.STATE_SHOW, -1)); //�F�p�l���Q
gd.add(new CharacterData(96, 33, 7, 92,64, 44,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
gd.add(new CharacterData(97, 33, 7, 92,64, 66,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
gd.add(new CharacterData(98, 33, 7, 92,64, 88,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //�F�p�l���Q
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(100, 33, 7, 125,64, 0,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
gd.add(new CharacterData(101, 33, 7, 125,64, 22,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
gd.add(new CharacterData(102, 33, 7, 125,64, 44,0,22,22,"set toggle 2", CharacterData.STATE_SHOW, -1)); //�F�p�l���R
gd.add(new CharacterData(103, 33, 7, 125,64, 66,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
gd.add(new CharacterData(104, 33, 7, 125,64, 88,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //�F�p�l���R
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(106, 33, 7, 158,64, 0,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
gd.add(new CharacterData(107, 33, 7, 158,64, 22,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
gd.add(new CharacterData(108, 33, 7, 158,64, 44,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
gd.add(new CharacterData(109, 33, 7, 158,64, 66,0,22,22,"set toggle 3", CharacterData.STATE_SHOW, -1)); //�F�p�l���S
gd.add(new CharacterData(110, 33, 7, 158,64, 88,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //�F�p�l���S
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(112, 20, 5, 72,36, 0,0,22,22,"set toggle 4", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���P
gd.add(new CharacterData(113, 20, 5, 72,36, 22,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���P
gd.add(new CharacterData(114, 20, 5, 72,36, 44,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���P
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(116, 20, 5, 103,36, 0,0,22,22,"set toggle 5", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���Q
gd.add(new CharacterData(117, 20, 5, 103,36, 22,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���Q
gd.add(new CharacterData(118, 20, 5, 103,36, 44,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���Q
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(120, 20, 5, 134,36, 0,0,22,22,"set toggle 6", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���R
gd.add(new CharacterData(121, 20, 5, 134,36, 22,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���R
gd.add(new CharacterData(122, 20, 5, 134,36, 44,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���R
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(124, 20, 5, 72,64, 0,0,22,22,"set toggle 7", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���S
gd.add(new CharacterData(125, 20, 5, 72,64, 22,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���S
gd.add(new CharacterData(126, 20, 5, 72,64, 44,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���S
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(128, 20, 5, 103,64, 0,0,22,22,"set toggle 8", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���T
gd.add(new CharacterData(129, 20, 5, 103,64, 22,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���T
gd.add(new CharacterData(130, 20, 5, 103,64, 44,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���T
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(132, 20, 5, 134,64, 0,0,22,22,"set toggle 9", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���U
gd.add(new CharacterData(133, 20, 5, 134,64, 22,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���U
gd.add(new CharacterData(134, 20, 5, 134,64, 44,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���U
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(136, 20, 5, 72,92, 0,0,22,22,"set toggle 10", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���V
gd.add(new CharacterData(137, 20, 5, 72,92, 22,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���V
gd.add(new CharacterData(138, 20, 5, 72,92, 44,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���V
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(140, 20, 5, 103,92, 0,0,22,22,"set toggle 11", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���W
gd.add(new CharacterData(141, 20, 5, 103,92, 22,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���W
gd.add(new CharacterData(142, 20, 5, 103,92, 44,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���W
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(144, 20, 5, 134,92, 0,0,22,22,"set toggle 12", CharacterData.STATE_SHOW, -101)); //�ǃ{�^���X
gd.add(new CharacterData(145, 20, 5, 134,92, 22,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���X
gd.add(new CharacterData(146, 20, 5, 134,92, 44,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //�ǃ{�^���X
g.addElement(gd);
gd.addAlltoVector(v);
v.addElement(new CharacterData(148, 1, 0, 42,25, 46,0,18,30,"set event 1", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�������J�[�\��
v.addElement(new CharacterData(149, 1, 0, 60,25, 64,0,126,30,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�����A�C�e���ꗗ
v.addElement(new CharacterData(150, 1, 0, 186,25, 190,0,18,30,"set event 2", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�����E�J�[�\��
v.addElement(new CharacterData(151, 1, 0, 60,60, 0,30,126,126,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //�X�y�V�����ڍ׃A�C�e����
v.addElement(new ItemData(152, 3, 0, 0, 24, 24, 652, "set select 152 set detail 652 set msg �X�p�i���c", -1)); // �X�p�i
v.addElement(new ItemData(652, 6, 0, 0, 120, 120, 0, "set msg ���Ɏg�����c", -1)); // �ڍ׃X�p�i
v.addElement(new ItemData(153, 3, 24, 0, 24, 24, 653, "set select 153 set detail 653 set msg �����_���c", -1)); // �����_
v.addElement(new ItemData(653, 32, 0, 0, 120, 120, 0, "set msg ���\�����c", -1)); // �ڍג����_
v.addElement(new ItemData(154, 3, 48, 0, 24, 24, 654, "set select 154 set detail 654 set msg ���т񂾁c", -1)); // ���т�
v.addElement(new ItemData(654, 31, 0, 0, 120, 120, 0, "set msg ���ɉt�̂������Ă�", -1)); // �ڍ׏��т�
v.addElement(new ItemData(155, 3, 72, 0, 24, 24, 655, "set select 155 set detail 655 set msg �V�[�g���c", -1)); // �V�[�g
v.addElement(new ItemData(655, 9, 0, 0, 120, 120, 0, "if isset item 154 set delitem 154 set delitem 155 set item 157 set msg �������o�Ă����I else set msg ����������ĂȂ��c end", -1)); // �ڍ׃V�[�g
v.addElement(new ItemData(156, 3, 96, 0, 24, 24, 656, "set select 156 set detail 656 set msg �����c", -1)); // ��
v.addElement(new ItemData(656, 8, 0, 0, 120, 120, 0, "set msg ����Łc", -1)); // �ڍ׌�
v.addElement(new ItemData(157, 3, 120, 0, 24, 24, 657, "set select 157 set detail 657 set msg �����V�[�g���c", -1)); // �����V�[�g
v.addElement(new ItemData(657, 13, 0, 0, 120, 120, 0, "set msg �ǂ������Ӗ����c", -1)); // �ڍו����V�[�g
v.addElement(new ItemData(158, 3, 144, 0, 24, 24, 658, "set select 158 set detail 658 set msg �ג��������c", -1)); // �ג�����
v.addElement(new ItemData(658, 36, 0, 0, 120, 120, 0, "set msg �Ȃ�̌����낤�c", -1)); // �ڍ׍ג�����
v.addElement(new ItemData(159, 3, 168, 0, 24, 24, 659, "set select 159 set detail 659 set msg �����c", -1)); // ��
v.addElement(new ItemData(659, 45, 0, 0, 120, 120, 0, "set msg �����c", -1)); // �ڍ׋�
v.addElement(new ItemData(160, 3, 192, 0, 24, 24, 660, "set select 160 set detail 660 set msg �����R�����c", -1)); // �����R��
v.addElement(new ItemData(660, 39, 0, 0, 120, 120, 0, "set msg ���Ɏg�����c", -1)); // �ڍ׃����R��
v.addElement(new ItemData(161, 3, 216, 0, 24, 24, 661, "set select 161 set detail 661 set msg �ʂ�����݂��c", -1)); // �ʂ������
v.addElement(new ItemData(661, 51, 0, 0, 120, 120, 0, "if isset item 165 set delitem 161 set show -107 set item 167 set msg �����������I else set msg �ʂ�����݂��c end", -1)); // �ڍׂʂ������
v.addElement(new ItemData(162, 3, 0, 24, 24, 24, 662, "set select 162 set detail 662 set msg �ڋʂ��c", -1)); // �ڋ�
v.addElement(new ItemData(662, 51, 0, 0, 120, 120, 0, "set msg �ڋʂ��c", -1)); // �ڍזڋ�
v.addElement(new ItemData(163, 3, 24, 24, 24, 24, 663, "set select 163 set detail 663 set msg ���Q���c", -1)); // ���Q
v.addElement(new ItemData(663, 47, 0, 0, 120, 120, 0, "set msg �����f���Ă�c", -1)); // �ڍ׋��Q
v.addElement(new ItemData(164, 3, 48, 24, 24, 24, 664, "set select 164 set detail 664 set msg �ڋʂ̖����ʂ�����݂��c", -1)); // �ڋʂ̖����ʂ������
v.addElement(new ItemData(664, 51, 0, 0, 120, 120, 0, "if isset item 162 set delitem 162 set delitem 164 set item 161 set msg �ڋʂ�t�����I else isset item 165 set delitem 165 set delitem 164 set item 166 set msg �����������I else set msg �Жڂ����Ă�c end", -1)); // �ڍזڋʂ̖����ʂ������
v.addElement(new ItemData(165, 3, 72, 24, 24, 24, 665, "set select 165 set detail 665 set msg �n�T�~���c", -1)); // �n�T�~
v.addElement(new ItemData(665, 53, 0, 0, 120, 120, 0, "set msg �n�T�~���c", -1)); // �ڍ׃n�T�~
v.addElement(new ItemData(166, 3, 96, 24, 24, 24, 666, "set select 166 set detail 666 set msg �ڋʂ̖������������ʂ�����݂��c", -1)); // �ڋʂ̖������������ʂ������
v.addElement(new ItemData(666, 51, 0, 0, 120, 120, 0, "if isset item 162 set delitem 162 set delitem 166 set item 167 set msg �ڋʂ�t�����I else isset hide -106 set show -106 set item 158 set msg �ג������������Ă�! else set msg �����͖Ȃ��炯���c end", -1)); // �ڍזڋʂ̖������������ʂ������
v.addElement(new ItemData(167, 3, 120, 24, 24, 24, 667, "set select 167 set detail 667 set msg ���������ʂ�����݂��c", -1)); // ���������ʂ������
v.addElement(new ItemData(667, 55, 0, 0, 120, 120, 0, "if isset hide -106 set show -106 set item 158 set msg �ג������������Ă�! else isset item 168 set delitem 168 set delitem 167 set item 161 set msg ������D�����I else set msg �����͖Ȃ��炯���c�c end", -1)); // �ڍו��������ʂ������
v.addElement(new ItemData(168, 3, 144, 24, 24, 24, 668, "set select 168 set detail 668 set msg �ٖD����c", -1)); // �ٖD����
v.addElement(new ItemData(668, 40, 0, 0, 120, 120, 0, "set msg �ٖD����c", -1)); // �ڍ׍ٖD����
v.addElement(new ItemData(169, 3, 168, 24, 24, 24, 669, "set select 169 set detail 669 set msg �아���c", -1)); // �아
v.addElement(new ItemData(669, 57, 0, 0, 120, 120, 0, "set msg �아���c", -1)); // �ڍ׌아
v.addElement(new ItemData(170, 3, 192, 24, 24, 24, 670, "set select 170 set detail 670 set msg �l�`�̎񂾁c", -1)); // �l�`�̎�
v.addElement(new ItemData(670, 49, 0, 0, 120, 120, 0, "set delitem 170 set item 171 set msg �c", -1)); // �ڍאl�`�̎�
v.addElement(new ItemData(171, 3, 216, 24, 24, 24, 671, "set select 171 set detail 671 set msg �΂��Ă���l�`�̎񂾁c", -1)); // �΂��Ă���l�`�̎�
v.addElement(new ItemData(671, 38, 0, 0, 120, 120, 0, "set msg �c", -1)); // �ڍ׏΂��Ă���l�`�̎�

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
