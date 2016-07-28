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
s.addElement("set image 2 set image -1 set msg ここは… set msg そうだ…霊を成仏しに… set msg …また…… set msg …閉じ込められたのか… set play 0");
s.addElement("set msg 『…ワタシノ…ヌイグ…ルミ…』 set msg 『…壊シタノ…』 set image 60 set msg 『…オマエ…カ…』 set msg 『壊壊壊壊シ壊壊壊壊壊壊壊シタ壊壊壊壊ナナナ』 set msg Ｂａｄ　Ｅｎｄ set title 0");
s.addElement("set msg 出られた… set msg 簡単な依頼だったな… set msg あれ？ポケットに手紙が… set msg 『最後までプレイしてくれて』 set msg 『…ありがとう…』 set msg 『本当に…お疲れ様』 set msg Ｂｅｓｔ　Ｅｎｄ set title 0");
RoomData.addRooms(0, "-1:-1:3:1"); // ドア側
RoomData.addRooms(1, "-1:-1:0:2"); // 絵側
RoomData.addRooms(2, "-1:-1:1:3"); // ロッカー側
RoomData.addRooms(3, "-1:-1:2:0"); // 棚側
RoomData.addRooms(4, "-1:3:-1:-1"); // 棚の裏
RoomData.addRooms(5, "-1:1:-1:-1"); // ９ボタン詳細
RoomData.addRooms(6, "-1:1:-1:-1"); // テーブル詳細
RoomData.addRooms(7, "-1:0:-1:-1"); // 色パネル詳細
RoomData.addRooms(8, "-1:7:-1:-1"); // 色パネル箱下
RoomData.addRooms(9, "-1:6:-1:-1"); // テーブル詳細２
v.addElement(new CharacterData(-100, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //色パネルの謎解き
v.addElement(new CharacterData(-101, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //壁板外し
v.addElement(new CharacterData(-102, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //ロッカーの謎解き
v.addElement(new CharacterData(-103, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //９ボタンの謎解き
v.addElement(new CharacterData(-104, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //９ボタンの謎解き2
v.addElement(new CharacterData(-105, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //ロッカーの鍵外し
v.addElement(new CharacterData(-106, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //細長い鍵取得
v.addElement(new CharacterData(-107, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //腹割いたフラグ
v.addElement(new CharacterData(1, 4, 0, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //ドア背景
v.addElement(new CharacterData(2, 12, 0, 81,42, 0,0,74,131,"set msg 早くここから出たい…", CharacterData.STATE_SHOW, -1)); //ドア
v.addElement(new CharacterData(3, -1, 0, 84,98, 0,0,14,14,"if isset item 143 set msg 鍵が合わない else isset item 141 set delitem 141 set hide 3 set hide 2 set show 4 set msg 開いた！ else set msg 開かない… end", CharacterData.STATE_SHOW, -1)); //ドアノブ
v.addElement(new CharacterData(4, 11, 0, 81,35, 0,0,74,155,"set story 2", CharacterData.STATE_HIDE, -1)); //開いたドア
v.addElement(new CharacterData(5, 16, 0, 20,134, 0,0,60,60,"set go 7", CharacterData.STATE_SHOW, -1)); //色パネル箱
v.addElement(new CharacterData(6, 48, 0, 155,60, 0,0,83,83,"set msg なんだ…これ…", CharacterData.STATE_HIDE, -1)); //壁の文字
v.addElement(new CharacterData(7, 17, 0, 140,154, 0,0,22,44,"if isset item 154 set delitem 154 set show 8 set show 9 set msg これで… else isset show 8 set msg 『お疲れ様』 else set msg これは… end", CharacterData.STATE_SHOW, -1)); //れくと
v.addElement(new CharacterData(8, 3, 0, 140,157, 168,24,24,24,"set msg これで…", CharacterData.STATE_HIDE, -1)); //貼った護符
v.addElement(new CharacterData(9, 3, 0, 110,180, 96,0,24,24,"set hide 9 set item 141 set msg 鍵を手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきた鍵
v.addElement(new CharacterData(10, 4, 1, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //絵側背景
v.addElement(new CharacterData(11, 44, 1, 149,59, 0,0,67,46,"set msg 青空の風景画だ…", CharacterData.STATE_SHOW, -1)); //風景画
v.addElement(new CharacterData(12, 44, 1, 159,94, 67,0,8,8,"set item 143 set hide 12 set msg 細長い鍵を手に入れた！", CharacterData.STATE_SHOW, -1)); //風景画ロッカー鍵
v.addElement(new CharacterData(13, 24, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_HIDE, -1)); //９ボタン入り口
v.addElement(new CharacterData(14, 23, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_SHOW, -1)); //９ボタン入り口板
v.addElement(new CharacterData(15, 23, 1, 38,122, 0,0,64,64,"set hide 15 set show 16 set msg 裏返した", CharacterData.STATE_HIDE, -1)); //落ちた９ボタン入り口板
v.addElement(new CharacterData(16, 25, 1, 38,122, 0,0,64,64,"set hide 16 set show 15 set msg 裏返した", CharacterData.STATE_HIDE, -1)); //落ちた９ボタン入り口板裏
v.addElement(new CharacterData(17, 28, 1, 82,156, 0,0,120,48,"set msg テーブルだ", CharacterData.STATE_SHOW, -1)); //テーブル
v.addElement(new CharacterData(18, -1, 1, 111,186, 0,0,62,18,"set go 6", CharacterData.STATE_SHOW, -1)); //テーブル下
v.addElement(new CharacterData(19, 34, 1, 102,126, 0,0,54,49,"if isset item 145 set delitem 145 set show 21 set msg 何か表示された！ else set msg モニタだ end", CharacterData.STATE_SHOW, -1)); //モニタ
v.addElement(new CharacterData(20, 3, 1, 44,190, 144,24,24,24,"set hide 20 set item 153 set msg 裁縫道具を手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきた裁縫道具
v.addElement(new CharacterData(21, 34, 1, 112,137, 54,0,36,16,"set msg どういう意味だろう…", CharacterData.STATE_HIDE, -1)); //モニタ文字
v.addElement(new CharacterData(22, 47, 3, 0,0, 0,0,120,120,"", CharacterData.STATE_SHOW, -1)); //幽霊映ってる鏡
v.addElement(new CharacterData(23, 4, 3, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //棚側背景
v.addElement(new CharacterData(24, 14, 3, 20,50, 0,0,85,142,"set msg 何もない", CharacterData.STATE_SHOW, -1)); //棚
v.addElement(new CharacterData(25, 3, 3, 55,118, 168,24,24,24,"set hide 25 set item 154 set msg 護符を手に入れた！", CharacterData.STATE_SHOW, -1)); //落ちてる護符
v.addElement(new CharacterData(26, 52, 3, 25,10, 0,0,82,229,"if isset item 153 set delitem 153 set hide 26 set msg 針で目を刺したら消えた！ else set msg 『…』 end", CharacterData.STATE_SHOW, -1)); //別幽霊
v.addElement(new CharacterData(27, -1, 3, 93,144, 0,0,36,38,"set go 4", CharacterData.STATE_SHOW, -1)); //棚裏へ
v.addElement(new CharacterData(28, 27, 3, 133,90, 0,21,62,27,"set msg どんな意味が…", CharacterData.STATE_SHOW, -1)); //２進数プレート
v.addElement(new CharacterData(29, 27, 3, 148,59, 0,0,32,21,"if isset show -102 set msg もう何もない else isset show 38 isset show 44 isset show 47 isset show 35 isset hide 41 set show 31 set show -102 set msg 何か落ちた else set msg 入力が違うみたい… end", CharacterData.STATE_SHOW, -1)); //２進数ボタン
v.addElement(new CharacterData(30, 37, 3, 120,155, 23,0,21,28,"set item 149 set hide 30 set msg ぬいぐるみを手に入れた！", CharacterData.STATE_SHOW, -1)); //置いてあるぬいぐるみ
v.addElement(new CharacterData(31, 3, 3, 151,155, 73,0,23,24,"set hide 31 set item 140 set msg シートを手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきたシート
v.addElement(new CharacterData(32, 4, 2, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //ロッカー側背景
v.addElement(new CharacterData(33, 7, 2, 14,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー１背景
v.addElement(new CharacterData(34, 7, 2, 18,53, 36,13,26,127,"set show 35 set hide 34", CharacterData.STATE_SHOW, -1)); //ロッカー１閉ドア
v.addElement(new CharacterData(35, 7, 2, 41,50, 65,10,21,135,"set show 34 set hide 35", CharacterData.STATE_HIDE, -1)); //ロッカー１開ドア
v.addElement(new CharacterData(36, 7, 2, 50,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー２背景
v.addElement(new CharacterData(37, 7, 2, 54,53, 36,13,26,127,"set show 38 set hide 37", CharacterData.STATE_SHOW, -1)); //ロッカー２閉ドア
v.addElement(new CharacterData(38, 7, 2, 77,50, 65,10,21,135,"set show 37 set hide 38", CharacterData.STATE_HIDE, -1)); //ロッカー２開ドア
v.addElement(new CharacterData(39, 7, 2, 86,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー３背景
v.addElement(new CharacterData(40, 7, 2, 90,53, 36,13,26,127,"set show 41 set hide 40", CharacterData.STATE_SHOW, -1)); //ロッカー３閉ドア
v.addElement(new CharacterData(41, 7, 2, 113,50, 65,10,21,135,"set show 40 set hide 41", CharacterData.STATE_HIDE, -1)); //ロッカー３開ドア
v.addElement(new CharacterData(42, 7, 2, 122,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー４背景
v.addElement(new CharacterData(43, 7, 2, 126,53, 36,13,26,127,"if isset show -105 set show 44 set hide 43 else isset item 143 set delitem 143 set show -105 set msg カチリと音がした！ else set msg 開かない… end", CharacterData.STATE_SHOW, -1)); //ロッカー４閉ドア
v.addElement(new CharacterData(44, 7, 2, 149,50, 65,10,21,135,"set show 43 set hide 44", CharacterData.STATE_HIDE, -1)); //ロッカー４開ドア
v.addElement(new CharacterData(45, 7, 2, 158,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー５背景
v.addElement(new CharacterData(46, 7, 2, 162,53, 36,13,26,127,"set show 47 set hide 46", CharacterData.STATE_SHOW, -1)); //ロッカー５閉ドア
v.addElement(new CharacterData(47, 7, 2, 185,50, 65,10,21,135,"set show 46 set hide 47", CharacterData.STATE_HIDE, -1)); //ロッカー５開ドア
v.addElement(new CharacterData(48, 15, 4, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //棚の裏背景
v.addElement(new CharacterData(49, 58, 4, 1,144, 0,0,215,50,"set msg なんだこれ…", CharacterData.STATE_SHOW, -1)); //いろは
v.addElement(new CharacterData(50, 29, 6, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //テーブル詳細背景
v.addElement(new CharacterData(51, 59, 6, 61,6, 0,0,99,94,"set msg なんだこれ…", CharacterData.STATE_HIDE, -1)); //テーブルいろは
v.addElement(new CharacterData(52, 29, 6, 46,120, 46,120,156,31,"set go 9", CharacterData.STATE_SHOW, -1)); //テーブル奥へ
v.addElement(new CharacterData(53, 26, 9, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //テーブル詳細２背景
v.addElement(new CharacterData(54, 3, 9, 100,120, 50,0,20,24,"set hide 54 set item 139 set msg 小びんを手に入れた", CharacterData.STATE_SHOW, -1)); //落ちてる小びん
v.addElement(new CharacterData(55, 62, 9, 33,90, 0,0,171,72,"if isset item 149 set story 1 else isset item 146 set delitem 146 set hide 55 set msg 『…ヌイグルミ…アリガトウ…』 else set msg 『…ヌイ…グル…ミ…』 end", CharacterData.STATE_SHOW, -1)); //幽霊
v.addElement(new CharacterData(56, 56, 9, 97,98, 64,8,102,25,"if isset item 149 set story 1 else isset item 137 set hide 56 set show 57 set msg 目玉をくりぬいた！ else set msg 『…ヌイ…グル…ミ…』 end", CharacterData.STATE_SHOW, 55)); //幽霊の目玉
v.addElement(new CharacterData(57, 37, 9, 140,180, 45,0,10,10,"set item 147 set hide 57 set msg 目玉を手に入れた！", CharacterData.STATE_HIDE, -1)); //落ちてる目玉
v.addElement(new CharacterData(58, 22, 7, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //色パネル背景
v.addElement(new CharacterData(59, 33, 7, 66,94, 0,22,110,33,"if isset show -100 set msg もう何もない else isset show 76 isset show 80 isset show 87 isset show 92 set show -100 set show 51 set show 61 set msg 何か出てきた！ else set msg 入力が違うみたい… end", CharacterData.STATE_SHOW, -1)); //色パネルボタン
v.addElement(new CharacterData(60, 33, 7, 66,130, 0,55,110,33,"if isset show -100 set msg もう何もない else set msg 何か出てくるのかな？ end", CharacterData.STATE_SHOW, -1)); //色パネル出口
v.addElement(new CharacterData(61, 6, 7, 49,116, 0,0,120,120,"set item 137 set hide 61 set msg スパナを手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきたスパナ
v.addElement(new CharacterData(62, -1, 7, 58,177, 0,0,125,14,"set go 8", CharacterData.STATE_SHOW, -1)); //色パネル箱下へ
v.addElement(new CharacterData(63, 41, 8, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //色パネル箱下背景
v.addElement(new CharacterData(64, 42, 8, 53,180, 0,0,112,23,"set hide 64 set item 145 set msg リモコンを手に入れた！", CharacterData.STATE_SHOW, -1)); //落ちてるリモコン
v.addElement(new CharacterData(65, 18, 5, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //壁穴
v.addElement(new CharacterData(66, 20, 5, 77,119, 66,0,30,11,"if isset show -104 set msg もう何もない else isset show 99 isset show 103 isset show 107 isset show 111 isset show 115 isset show 117 isset show 123 isset show 125 isset show 129 set show 20 set show -104 set msg 何かが落ちた！ else set msg 入力が違うみたい… end", CharacterData.STATE_SHOW, -101)); //チェックボタン
v.addElement(new CharacterData(67, 20, 5, 118,119, 66,11,30,11,"set msg 穴が空いてる", CharacterData.STATE_SHOW, -101)); //鍵の口
v.addElement(new CharacterData(68, 19, 5, 37,9, 0,0,150,150,"if isset hide 69 set hide 68 set show 65 set hide 14 set show 13 set show 15 set show -101 set msg フタを外した！ else set msg 固いフタだ… end", CharacterData.STATE_SHOW, -1)); //壁板
v.addElement(new CharacterData(69, 21, 5, 39,11, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト１
v.addElement(new CharacterData(70, 21, 5, 166,11, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト２
v.addElement(new CharacterData(71, 21, 5, 39,137, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト３
v.addElement(new CharacterData(72, 21, 5, 165,137, 0,0,20,20,"if isset item 137 set hide 69 set hide 70 set hide 71 set hide 72 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト４
gd = new GroupData();
gd.add(new CharacterData(73, 33, 7, 59,64, 0,0,22,22,"set toggle 0", CharacterData.STATE_SHOW, -1)); //色パネル１
gd.add(new CharacterData(74, 33, 7, 59,64, 22,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
gd.add(new CharacterData(75, 33, 7, 59,64, 44,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
gd.add(new CharacterData(76, 33, 7, 59,64, 66,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
gd.add(new CharacterData(77, 33, 7, 59,64, 88,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(79, 33, 7, 92,64, 0,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
gd.add(new CharacterData(80, 33, 7, 92,64, 22,0,22,22,"set toggle 1", CharacterData.STATE_SHOW, -1)); //色パネル２
gd.add(new CharacterData(81, 33, 7, 92,64, 44,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
gd.add(new CharacterData(82, 33, 7, 92,64, 66,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
gd.add(new CharacterData(83, 33, 7, 92,64, 88,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(85, 33, 7, 125,64, 0,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
gd.add(new CharacterData(86, 33, 7, 125,64, 22,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
gd.add(new CharacterData(87, 33, 7, 125,64, 44,0,22,22,"set toggle 2", CharacterData.STATE_SHOW, -1)); //色パネル３
gd.add(new CharacterData(88, 33, 7, 125,64, 66,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
gd.add(new CharacterData(89, 33, 7, 125,64, 88,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(91, 33, 7, 158,64, 0,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
gd.add(new CharacterData(92, 33, 7, 158,64, 22,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
gd.add(new CharacterData(93, 33, 7, 158,64, 44,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
gd.add(new CharacterData(94, 33, 7, 158,64, 66,0,22,22,"set toggle 3", CharacterData.STATE_SHOW, -1)); //色パネル４
gd.add(new CharacterData(95, 33, 7, 158,64, 88,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(97, 20, 5, 72,34, 0,0,22,22,"set toggle 4", CharacterData.STATE_SHOW, -101)); //壁ボタン１
gd.add(new CharacterData(98, 20, 5, 72,34, 22,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //壁ボタン１
gd.add(new CharacterData(99, 20, 5, 72,34, 44,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //壁ボタン１
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(101, 20, 5, 103,34, 0,0,22,22,"set toggle 5", CharacterData.STATE_SHOW, -101)); //壁ボタン２
gd.add(new CharacterData(102, 20, 5, 103,34, 22,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //壁ボタン２
gd.add(new CharacterData(103, 20, 5, 103,34, 44,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //壁ボタン２
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(105, 20, 5, 134,34, 0,0,22,22,"set toggle 6", CharacterData.STATE_SHOW, -101)); //壁ボタン３
gd.add(new CharacterData(106, 20, 5, 134,34, 22,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //壁ボタン３
gd.add(new CharacterData(107, 20, 5, 134,34, 44,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //壁ボタン３
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(109, 20, 5, 72,64, 0,0,22,22,"set toggle 7", CharacterData.STATE_SHOW, -101)); //壁ボタン４
gd.add(new CharacterData(110, 20, 5, 72,64, 22,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //壁ボタン４
gd.add(new CharacterData(111, 20, 5, 72,64, 44,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //壁ボタン４
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(113, 20, 5, 103,64, 0,0,22,22,"set toggle 8", CharacterData.STATE_SHOW, -101)); //壁ボタン５
gd.add(new CharacterData(114, 20, 5, 103,64, 22,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //壁ボタン５
gd.add(new CharacterData(115, 20, 5, 103,64, 44,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //壁ボタン５
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(117, 20, 5, 134,64, 0,0,22,22,"set toggle 9", CharacterData.STATE_SHOW, -101)); //壁ボタン６
gd.add(new CharacterData(118, 20, 5, 134,64, 22,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //壁ボタン６
gd.add(new CharacterData(119, 20, 5, 134,64, 44,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //壁ボタン６
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(121, 20, 5, 72,94, 0,0,22,22,"set toggle 10", CharacterData.STATE_SHOW, -101)); //壁ボタン７
gd.add(new CharacterData(122, 20, 5, 72,94, 22,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //壁ボタン７
gd.add(new CharacterData(123, 20, 5, 72,94, 44,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //壁ボタン７
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(125, 20, 5, 103,94, 0,0,22,22,"set toggle 11", CharacterData.STATE_SHOW, -101)); //壁ボタン８
gd.add(new CharacterData(126, 20, 5, 103,94, 22,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //壁ボタン８
gd.add(new CharacterData(127, 20, 5, 103,94, 44,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //壁ボタン８
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(129, 20, 5, 134,94, 0,0,22,22,"set toggle 12", CharacterData.STATE_SHOW, -101)); //壁ボタン９
gd.add(new CharacterData(130, 20, 5, 134,94, 22,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //壁ボタン９
gd.add(new CharacterData(131, 20, 5, 134,94, 44,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //壁ボタン９
g.addElement(gd);
gd.addAlltoVector(v);
v.addElement(new CharacterData(133, 1, 0, 42,25, 46,0,18,30,"set event 1", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャル左カーソル
v.addElement(new CharacterData(134, 1, 0, 60,25, 64,0,126,30,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャルアイテム一覧
v.addElement(new CharacterData(135, 1, 0, 186,25, 190,0,18,30,"set event 2", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャル右カーソル
v.addElement(new CharacterData(136, 1, 0, 60,60, 0,30,126,126,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャル詳細アイテム窓
v.addElement(new ItemData(137, 3, 0, 0, 24, 24, 637, "set select 137 set detail 637 set msg スパナだ…", -1)); // スパナ
v.addElement(new ItemData(637, 6, 0, 0, 120, 120, 0, "set msg 何に使おう…", -1)); // 詳細スパナ
v.addElement(new ItemData(138, 3, 24, 0, 24, 24, 638, "set select 138 set detail 638 set msg 長い棒だ…", -1)); // 長い棒
v.addElement(new ItemData(638, 32, 0, 0, 120, 120, 0, "set msg 結構長い…", -1)); // 詳細長い棒
v.addElement(new ItemData(139, 3, 48, 0, 24, 24, 639, "set select 139 set detail 639 set msg 小びんだ…", -1)); // 小びん
v.addElement(new ItemData(639, 31, 0, 0, 120, 120, 0, "set msg 中に液体が入ってる", -1)); // 詳細小びん
v.addElement(new ItemData(140, 3, 72, 0, 24, 24, 640, "set select 140 set detail 640 set msg シートだ…", -1)); // シート
v.addElement(new ItemData(640, 9, 0, 0, 120, 120, 0, "if isset item 139 set delitem 139 set delitem 140 set item 142 set msg 文字が出てきた！ else set msg 何も書かれてない… end", -1)); // 詳細シート
v.addElement(new ItemData(141, 3, 96, 0, 24, 24, 641, "set select 141 set detail 641 set msg 鍵だ…", -1)); // 鍵
v.addElement(new ItemData(641, 8, 0, 0, 120, 120, 0, "set msg これで…", -1)); // 詳細鍵
v.addElement(new ItemData(142, 3, 120, 0, 24, 24, 642, "set select 142 set detail 642 set msg 文字シートだ…", -1)); // 文字シート
v.addElement(new ItemData(642, 13, 0, 0, 120, 120, 0, "set msg どういう意味だ…", -1)); // 詳細文字シート
v.addElement(new ItemData(143, 3, 144, 0, 24, 24, 643, "set select 143 set detail 643 set msg 細長い鍵だ…", -1)); // 細長い鍵
v.addElement(new ItemData(643, 36, 0, 0, 120, 120, 0, "set msg なんの鍵だろう…", -1)); // 詳細細長い鍵
v.addElement(new ItemData(144, 3, 168, 0, 24, 24, 644, "set select 144 set detail 644 set msg 鏡だ…", -1)); // 鏡
v.addElement(new ItemData(644, 45, 0, 0, 120, 120, 0, "set msg 鏡だ…", -1)); // 詳細鏡
v.addElement(new ItemData(145, 3, 192, 0, 24, 24, 645, "set select 145 set detail 645 set msg リモコンだ…", -1)); // リモコン
v.addElement(new ItemData(645, 39, 0, 0, 120, 120, 0, "set msg 何に使おう…", -1)); // 詳細リモコン
v.addElement(new ItemData(146, 3, 216, 0, 24, 24, 646, "set select 146 set detail 646 set msg ぬいぐるみだ…", -1)); // ぬいぐるみ
v.addElement(new ItemData(646, 51, 0, 0, 120, 120, 0, "if isset item 150 set delitem 150 set delitem 146 set show -107 set item 152 set msg 腹を割いた！ else set msg ぬいぐるみだ… end", -1)); // 詳細ぬいぐるみ
v.addElement(new ItemData(147, 3, 0, 24, 24, 24, 647, "set select 147 set detail 647 set msg 目玉だ…", -1)); // 目玉
v.addElement(new ItemData(647, 53, 0, 0, 120, 120, 0, "set msg 目玉だ…", -1)); // 詳細目玉
v.addElement(new ItemData(148, 3, 24, 24, 24, 24, 648, "set select 148 set detail 648 set msg 鏡２だ…", -1)); // 鏡２
v.addElement(new ItemData(648, 47, 0, 0, 120, 120, 0, "set msg 何か映ってる…", -1)); // 詳細鏡２
v.addElement(new ItemData(149, 3, 48, 24, 24, 24, 649, "set select 149 set detail 649 set msg 目玉の無いぬいぐるみだ…", -1)); // 目玉の無いぬいぐるみ
v.addElement(new ItemData(649, 63, 0, 0, 120, 120, 0, "if isset item 147 set delitem 147 set delitem 149 set item 146 set msg 目玉を付けた！ else set msg 片目が取れてる… end", -1)); // 詳細目玉の無いぬいぐるみ
v.addElement(new ItemData(150, 3, 72, 24, 24, 24, 650, "set select 150 set detail 650 set msg ハサミだ…", -1)); // ハサミ
v.addElement(new ItemData(650, 53, 0, 0, 120, 120, 0, "set msg ハサミだ…", -1)); // 詳細ハサミ
v.addElement(new ItemData(151, 3, 96, 24, 24, 24, 651, "set select 151 set detail 651 set msg 目玉の無い腹割いたぬいぐるみだ…", -1)); // 目玉の無い腹割いたぬいぐるみ
v.addElement(new ItemData(651, 54, 0, 0, 120, 120, 0, "if isset item 147 set delitem 147 set delitem 151 set item 152 set msg 目玉を付けた！ else isset hide -106 set show -106 set item 143 set msg 細長い鍵が入ってた! else set msg お腹は綿だらけだ… end", -1)); // 詳細目玉の無い腹割いたぬいぐるみ
v.addElement(new ItemData(152, 3, 120, 24, 24, 24, 652, "set select 152 set detail 652 set msg 腹割いたぬいぐるみだ…", -1)); // 腹割いたぬいぐるみ
v.addElement(new ItemData(652, 55, 0, 0, 120, 120, 0, "if isset hide -106 set show -106 set item 143 set msg 細長い鍵が入ってた! else isset item 153 set delitem 153 set delitem 152 set item 146 set msg お腹を縫った！ else set msg お腹は綿だらけだ…… end", -1)); // 詳細腹割いたぬいぐるみ
v.addElement(new ItemData(153, 3, 144, 24, 24, 24, 653, "set select 153 set detail 653 set msg 裁縫道具だ…", -1)); // 裁縫道具
v.addElement(new ItemData(653, 40, 0, 0, 120, 120, 0, "set msg 裁縫道具だ…", -1)); // 詳細裁縫道具
v.addElement(new ItemData(154, 3, 168, 24, 24, 24, 654, "set select 154 set detail 654 set msg 護符だ…", -1)); // 護符
v.addElement(new ItemData(654, 57, 0, 0, 120, 120, 0, "set msg 護符だ…", -1)); // 詳細護符

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
