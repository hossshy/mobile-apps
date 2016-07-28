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
s.addElement("set image 2 set image -1 set msg ここは… set msg そうだ…霊を成仏しに… set msg ……… set msg …閉じ込められたのか… set play 0");
s.addElement("set msg 出られた…けど… set msg 何も… set msg 成仏できてない… set msg EndingNo.3 set msg ＮｅｘｔＨｉｎｔ... set msg 人形を復元しよう set title 0");
s.addElement("set msg 出られた… set msg これで… set msg 成仏出来たかな… set msg 『…ワタシハ…ココダヨ…』 set msg EndingNo.2 set msg ＮｅｘｔＨｉｎｔ... set msg テーブルを鏡で映し…魂を… set title 0");
s.addElement("set msg 『…ワタシノ…ヌイグ…ルミ…』 set msg 『…壊シタノ…』 set image 60 set msg 『…オマエ…カ…』 set msg 『壊壊壊壊シ壊壊壊壊壊壊壊シタ壊壊壊壊ナナナ』 set msg EndingNo.1 set msg ＮｅｘｔＨｉｎｔ... set msg 幽霊と話した後に綺麗なヌイグルミを… set title 0");
s.addElement("set msg 出られた… set msg これで… set msg 成仏出来たかな… set msg あれ？ポケットに手紙が… set msg 『…アリ…ガトウ…』 set msg 　 set msg おまけ set msg 幽霊に裁縫道具を… set title 0");
s.addElement("set msg 出られた… set msg 簡単な依頼だったな… set msg ん？ポケットに何か… set msg 『本当に…ありがとう…』 set msg Ｂｅｓｔ　Ｅｎｄ set title 0");
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
RoomData.addRooms(10, "-1:3:-1:-1"); // 棚の奥
v.addElement(new CharacterData(-100, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //色パネルの謎解き
v.addElement(new CharacterData(-101, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //壁板外し
v.addElement(new CharacterData(-102, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //ロッカーの謎解き
v.addElement(new CharacterData(-103, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //９ボタンの謎解き
v.addElement(new CharacterData(-104, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //９ボタンの謎解き2
v.addElement(new CharacterData(-105, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //ロッカーの鍵外し
v.addElement(new CharacterData(-106, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //細長い鍵取得
v.addElement(new CharacterData(-107, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //腹割いたフラグ
v.addElement(new CharacterData(-108, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //護符２フラグ
v.addElement(new CharacterData(-109, -1, 0, 0, 0, 0,0,0,0,null, CharacterData.STATE_HIDE, -1)); //人形の首フラグ
v.addElement(new CharacterData(1, 4, 0, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //ドア背景
v.addElement(new CharacterData(2, 12, 0, 81,42, 0,0,74,131,"set msg 早くここから出たい…", CharacterData.STATE_SHOW, -1)); //ドア
v.addElement(new CharacterData(3, -1, 0, 84,98, 0,0,14,14,"if isset item 158 set msg 鍵が合わない else isset item 156 set delitem 156 set hide 3 set hide 2 set show 4 set msg 開いた！ else set msg 開かない… end", CharacterData.STATE_SHOW, -1)); //ドアノブ
v.addElement(new CharacterData(4, 11, 0, 81,35, 0,0,74,155,"if isset show -108 isset show 10 set story 5 else isset show 10 set story 4 else isset show 8 set story 2 else isset show 9 set story 2 else set story 1 end", CharacterData.STATE_HIDE, -1)); //開いたドア
v.addElement(new CharacterData(5, 16, 0, 20,134, 0,0,60,60,"set go 7", CharacterData.STATE_SHOW, -1)); //色パネル箱
v.addElement(new CharacterData(6, 48, 0, 155,60, 0,0,83,83,"set msg なんだ…これ…", CharacterData.STATE_HIDE, -1)); //壁の文字
v.addElement(new CharacterData(7, 46, 0, 136,170, 0,0,32,25,"if isset show 8 set msg これで成仏できたかな… else isset hide -109 isset show 91 isset show 97 isset show 103 isset show 109 isset show 114 isset show 118 isset show 122 isset show 126 isset show 130 isset show 134 isset show 138 isset show 142 isset show 146 isset show 46 isset show 53 isset show 56 isset show 43 isset show 49 set show 38 set show -109 set msg 左の方で音がした…！ else isset item 170 set delitem 170 set show 8 set msg 首を付けた！ else isset item 171 set delitem 171 set show 8 set msg 首を付けた！ else set msg これに魂を宿らせたい… end", CharacterData.STATE_HIDE, -1)); //首の無い人形
v.addElement(new CharacterData(8, 46, 0, 139,154, 32,0,23,17,"if isset item 169 set delitem 169 set show 10 set msg これで… else set msg これで成仏できたかな… end", CharacterData.STATE_HIDE, -1)); //付けた人形の首
v.addElement(new CharacterData(9, 17, 0, 140,154, 0,0,22,44,"if isset item 169 set delitem 169 set show 10 set msg これで… else isset show 10 set msg 『お疲れ様』 else set msg これは… end", CharacterData.STATE_HIDE, -1)); //れくと
v.addElement(new CharacterData(10, 3, 0, 139,154, 168,24,24,24,"set msg 『お疲れ様』", CharacterData.STATE_HIDE, -1)); //貼った護符
v.addElement(new CharacterData(11, 52, 0, 81,10, 0,0,92,229,"if isset item 169 set hide 11 set msg 『ぎいぃやあああああっ！』 else set msg 『苦六六六路絵二波差未』 end", CharacterData.STATE_HIDE, -1)); //別幽霊
v.addElement(new CharacterData(12, 37, 0, 50,176, 0,0,23,27,"set item 159 set hide 12 set msg 鏡を手に入れた！", CharacterData.STATE_HIDE, -1)); //置いてある鏡
v.addElement(new CharacterData(13, 4, 1, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //絵側背景
v.addElement(new CharacterData(14, 44, 1, 149,59, 0,0,67,46,"if isset hide -108 isset show 11 isset show 90 isset show 96 isset show 102 isset show 108 isset item 165 set delitem 165 set show -108 set show 27 set msg 何か落ちた else set msg 青空の風景画だ… end", CharacterData.STATE_SHOW, -1)); //風景画
v.addElement(new CharacterData(15, 24, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_HIDE, -1)); //９ボタン入り口
v.addElement(new CharacterData(16, 23, 1, 38,52, 0,0,64,64,"set go 5", CharacterData.STATE_SHOW, -1)); //９ボタン入り口板
v.addElement(new CharacterData(17, 23, 1, 38,122, 0,0,64,64,"set hide 17 set show 18 set msg 裏返した", CharacterData.STATE_HIDE, -1)); //落ちた９ボタン入り口板
v.addElement(new CharacterData(18, 25, 1, 38,122, 0,0,64,64,"set hide 18 set show 17 set msg 裏返した", CharacterData.STATE_HIDE, -1)); //落ちた９ボタン入り口板裏
v.addElement(new CharacterData(19, 35, 1, 47,153, 22,0,22,22,"set msg なんだこれ…", CharacterData.STATE_SHOW, 18)); //絵マーク１
v.addElement(new CharacterData(20, 35, 1, 71,153, 44,0,22,22,"set msg なんだこれ…", CharacterData.STATE_SHOW, 18)); //絵マーク２
v.addElement(new CharacterData(21, 35, 1, 71,130, 22,22,22,22,"set msg なんだこれ…", CharacterData.STATE_SHOW, 18)); //絵マーク３
v.addElement(new CharacterData(22, 28, 1, 82,156, 0,0,120,48,"if isset item 159 set msg 反対側から映したい… else set msg テーブルだ end", CharacterData.STATE_SHOW, -1)); //テーブル
v.addElement(new CharacterData(23, -1, 1, 111,186, 0,0,62,18,"set go 6", CharacterData.STATE_SHOW, -1)); //テーブル下
v.addElement(new CharacterData(24, 34, 1, 102,126, 0,0,54,49,"if isset item 160 set delitem 160 set show 25 set msg 何か表示された！ else set msg モニタだ end", CharacterData.STATE_SHOW, -1)); //モニタ
v.addElement(new CharacterData(25, 34, 1, 109,136, 58,0,40,13,"set msg どういう意味だろう…", CharacterData.STATE_HIDE, -1)); //モニタ文字
v.addElement(new CharacterData(26, 3, 1, 80,198, 144,24,24,24,"set hide 26 set item 168 set msg 裁縫道具を手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきた裁縫道具
v.addElement(new CharacterData(27, 3, 1, 180,182, 168,24,24,24,"set hide 27 set item 169 set msg 護符を手に入れた！", CharacterData.STATE_HIDE, -1)); //落ちてる護符２
v.addElement(new CharacterData(28, 47, 3, 0,0, 0,0,120,120,"", CharacterData.STATE_SHOW, -1)); //幽霊映ってる鏡
v.addElement(new CharacterData(29, 4, 3, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //棚側背景
v.addElement(new CharacterData(30, 14, 3, 20,50, 0,0,85,142,"set msg 何もない", CharacterData.STATE_SHOW, -1)); //棚
v.addElement(new CharacterData(31, 37, 3, 40,110, 23,0,21,28,"set item 161 set hide 31 set msg ぬいぐるみを手に入れた！", CharacterData.STATE_SHOW, -1)); //置いてある鏡ぬいぐるみ
v.addElement(new CharacterData(32, -1, 3, 29,147, 0,0,67,37,"if isset item 159 set hide 32 set delitem 159 set show 33 set msg 鏡を置いた！ else set msg 何もない end", CharacterData.STATE_SHOW, -1)); //一番下の棚
v.addElement(new CharacterData(33, 37, 3, 50,148, 0,0,23,27,"if isset item 159 set delitem 159 set msg 鏡を置いた！ else set detailnoitem 28 set show 69 set msg 何か映ってる… end", CharacterData.STATE_HIDE, -1)); //棚に置いた鏡
v.addElement(new CharacterData(34, -1, 3, 93,144, 0,0,36,38,"set go 4", CharacterData.STATE_SHOW, -1)); //棚裏へ
v.addElement(new CharacterData(35, 27, 3, 133,90, 0,21,62,27,"set msg 何かの模様みたいだ", CharacterData.STATE_SHOW, -1)); //２進数プレート
v.addElement(new CharacterData(36, 27, 3, 148,59, 0,0,32,21,"if isset show -102 set msg もう何もない else isset show 46 isset show 53 isset show 56 isset hide 43 isset hide 49 set show 37 set show -102 set msg 何か落ちた else set msg 入力が違うみたい… end", CharacterData.STATE_SHOW, -1)); //２進数ボタン
v.addElement(new CharacterData(37, 3, 3, 151,155, 73,0,23,24,"set hide 37 set item 155 set msg シートを手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきたシート
v.addElement(new CharacterData(38, 46, 3, 30,38, 32,25,23,17,"set go 10", CharacterData.STATE_HIDE, -1)); //落ちてる人形の首
v.addElement(new CharacterData(39, 4, 2, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //ロッカー側背景
v.addElement(new CharacterData(40, 7, 2, 14,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー１背景
v.addElement(new CharacterData(41, 3, 2, 16,156, 72,24,24,24,"set hide 41 set item 165 set msg ハサミを手に入れた！", CharacterData.STATE_SHOW, -1)); //落ちてるハサミ
v.addElement(new CharacterData(42, 7, 2, 18,53, 36,13,26,127,"set show 43 set hide 42", CharacterData.STATE_SHOW, -1)); //ロッカー１閉ドア
v.addElement(new CharacterData(43, 7, 2, 41,50, 65,10,21,135,"set show 42 set hide 43", CharacterData.STATE_HIDE, -1)); //ロッカー１開ドア
v.addElement(new CharacterData(44, 7, 2, 50,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー２背景
v.addElement(new CharacterData(45, 7, 2, 54,53, 36,13,26,127,"set show 46 set hide 45", CharacterData.STATE_SHOW, -1)); //ロッカー２閉ドア
v.addElement(new CharacterData(46, 7, 2, 77,50, 65,10,21,135,"set show 45 set hide 46", CharacterData.STATE_HIDE, -1)); //ロッカー２開ドア
v.addElement(new CharacterData(47, 7, 2, 86,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー３背景
v.addElement(new CharacterData(48, 7, 2, 90,53, 36,13,26,127,"set show 49 set hide 48", CharacterData.STATE_SHOW, -1)); //ロッカー３閉ドア
v.addElement(new CharacterData(49, 7, 2, 113,50, 65,10,21,135,"set show 48 set hide 49", CharacterData.STATE_HIDE, -1)); //ロッカー３開ドア
v.addElement(new CharacterData(50, 7, 2, 122,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー４背景
v.addElement(new CharacterData(51, 30, 2, 135,114, 0,0,10,60,"set item 153 set hide 51 set msg 長い棒を手に入れた！", CharacterData.STATE_SHOW, -1)); //ロッカーの長い棒
v.addElement(new CharacterData(52, 7, 2, 126,53, 36,13,26,127,"if isset show -105 set show 53 set hide 52 else isset item 158 set delitem 158 set show -105 set msg カチリと音がした！ else set msg 開かない… end", CharacterData.STATE_SHOW, -1)); //ロッカー４閉ドア
v.addElement(new CharacterData(53, 7, 2, 149,50, 65,10,21,135,"set show 52 set hide 53", CharacterData.STATE_HIDE, -1)); //ロッカー４開ドア
v.addElement(new CharacterData(54, 7, 2, 158,40, 0,0,32,150,"", CharacterData.STATE_SHOW, -1)); //ロッカー５背景
v.addElement(new CharacterData(55, 7, 2, 162,53, 36,13,26,127,"set show 56 set hide 55", CharacterData.STATE_SHOW, -1)); //ロッカー５閉ドア
v.addElement(new CharacterData(56, 7, 2, 185,50, 65,10,21,135,"set show 55 set hide 56", CharacterData.STATE_HIDE, -1)); //ロッカー５開ドア
v.addElement(new CharacterData(57, 15, 4, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //棚の裏背景
v.addElement(new CharacterData(58, 3, 4, 220,160, 50,0,20,24,"if isset item 153 set delitem 153 set hide 58 set item 154 set msg 小びんを手に入れた else set msg 届かない… end", CharacterData.STATE_SHOW, -1)); //落ちてる小びん
v.addElement(new CharacterData(59, 35, 4, 10,15, 22,22,22,22,"set msg なんだこれ…", CharacterData.STATE_SHOW, -1)); //棚マーク１
v.addElement(new CharacterData(60, 35, 4, 10,39, 44,0,22,22,"set msg なんだこれ…", CharacterData.STATE_SHOW, -1)); //棚マーク２
v.addElement(new CharacterData(61, 35, 4, 10,63, 22,0,22,22,"set msg なんだこれ…", CharacterData.STATE_SHOW, -1)); //棚マーク３
v.addElement(new CharacterData(62, 58, 4, 1,144, 0,0,215,50,"set msg なんだこれ…", CharacterData.STATE_HIDE, -1)); //いろは
v.addElement(new CharacterData(63, 29, 6, 0,0, 0,0,240,240,"if isset item 159 set msg 反対側から映したい… end", CharacterData.STATE_SHOW, -1)); //テーブル詳細背景
v.addElement(new CharacterData(64, 59, 6, 61,6, 0,0,99,94,"set msg なんだこれ…", CharacterData.STATE_HIDE, -1)); //テーブルいろは
v.addElement(new CharacterData(65, 29, 6, 46,120, 46,120,156,31,"set go 9", CharacterData.STATE_SHOW, -1)); //テーブル奥へ
v.addElement(new CharacterData(66, 60, 6, 0,0, 0,0,240,240,"set msg 『…アアアアリガガガトトウ…』 set hide 66", CharacterData.STATE_HIDE, -1)); //近い幽霊
v.addElement(new CharacterData(67, 26, 9, 0,0, 0,0,240,240,"if isset item 159 set msg 反対側から映したい… end", CharacterData.STATE_SHOW, -1)); //テーブル詳細２背景
v.addElement(new CharacterData(68, 3, 9, 100,120, 168,24,24,24,"set hide 68 set item 169 set msg 護符を手に入れた！", CharacterData.STATE_HIDE, -1)); //落ちてる護符
v.addElement(new CharacterData(69, 56, 9, 33,90, 0,0,171,72,"if isset item 161 set hide 69 set delitem 161 set show 68 set hide 33 set show -105 set msg 『ヌイグルミ…アリガトウ…』 else isset item 168 set hide 69 set delitem 168 set hide 33 set hide 7 set hide 6 set show 9 set show 11 set show 66 set msg 『…』 else isset item 167 set story 3 else set show 62 set show 64 set msg 『…ヌイ…グル…ミ…』 end", CharacterData.STATE_HIDE, -1)); //幽霊
v.addElement(new CharacterData(70, 22, 7, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //色パネル背景
v.addElement(new CharacterData(71, 33, 7, 66,94, 0,22,110,33,"if isset show -100 set msg もう何もない else isset show 88 isset show 97 isset show 104 isset show 108 set show -100 set show 74 set msg 何か出てきた！ else set msg 入力が違うみたい… end", CharacterData.STATE_SHOW, -1)); //色パネルボタン
v.addElement(new CharacterData(72, 33, 7, 66,130, 0,55,110,33,"if isset show -100 set msg もう何もない else set msg 何か出てくるのかな？ end", CharacterData.STATE_SHOW, -1)); //色パネル出口
v.addElement(new CharacterData(73, -1, 7, 58,177, 0,0,125,14,"set go 8", CharacterData.STATE_SHOW, -1)); //色パネル箱下へ
v.addElement(new CharacterData(74, 6, 7, 49,116, 0,0,120,120,"set item 152 set hide 74 set msg スパナを手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきたスパナ
v.addElement(new CharacterData(75, 41, 8, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //色パネル箱下背景
v.addElement(new CharacterData(76, 42, 8, 53,180, 0,0,112,23,"set hide 76 set item 160 set msg リモコンを手に入れた！", CharacterData.STATE_SHOW, -1)); //落ちてるリモコン
v.addElement(new CharacterData(77, 54, 10, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //棚の奥背景
v.addElement(new CharacterData(78, 10, 10, 0,0, 0,0,240,240,"set hide 38 set hide 78 set show 12 set item 170 set msg 人形の首を手に入れた！", CharacterData.STATE_SHOW, -1)); //置いてある人形の首
v.addElement(new CharacterData(79, 18, 5, 0,0, 0,0,240,240,"", CharacterData.STATE_SHOW, -1)); //壁穴
v.addElement(new CharacterData(80, 20, 5, 78,119, 66,0,30,11,"if isset show -104 isset show 114 isset show 116 isset show 121 isset show 126 isset show 129 isset show 133 isset show 136 isset show 140 isset show 146 set msg 入力が違うみたい… else isset show 114 isset show 116 isset show 121 isset show 126 isset show 129 isset show 133 isset show 136 isset show 140 isset show 146 set show 26 set show -104 set msg 何かが落ちた！ else isset show -103 set msg 入力が違うみたい… else isset show 112 isset show 117 isset show 120 isset show 126 isset show 130 isset show 133 isset show 136 isset show 141 isset show 146 set show -103 set show 6 set show 7 set show 82 set msg 何か出てきた！ else set msg 入力が違うみたい… end", CharacterData.STATE_SHOW, -101)); //チェックボタン
v.addElement(new CharacterData(81, 20, 5, 118,119, 66,11,30,11,"set msg 穴が空いてる", CharacterData.STATE_SHOW, -101)); //鍵の口
v.addElement(new CharacterData(82, 20, 5, 124,123, 98,16,18,6,"set hide 82 set item 156 set msg 鍵を手に入れた！", CharacterData.STATE_HIDE, -1)); //出てきた鍵
v.addElement(new CharacterData(83, 19, 5, 37,9, 0,0,150,150,"if isset hide 84 set hide 83 set show 79 set hide 16 set show 15 set show 17 set show -101 set msg フタを外した！ else set msg 固いフタだ… end", CharacterData.STATE_SHOW, -1)); //壁板
v.addElement(new CharacterData(84, 21, 5, 40,12, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト１
v.addElement(new CharacterData(85, 21, 5, 164,12, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト２
v.addElement(new CharacterData(86, 21, 5, 40,136, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト３
v.addElement(new CharacterData(87, 21, 5, 164,136, 0,0,20,20,"if isset item 152 set delitem 152 set hide 84 set hide 85 set hide 86 set hide 87 set msg ボルトを外した！ else set msg 素手では外せない… end", CharacterData.STATE_SHOW, -1)); //ボルト４
gd = new GroupData();
gd.add(new CharacterData(88, 33, 7, 59,64, 0,0,22,22,"set toggle 0", CharacterData.STATE_SHOW, -1)); //色パネル１
gd.add(new CharacterData(89, 33, 7, 59,64, 22,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
gd.add(new CharacterData(90, 33, 7, 59,64, 44,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
gd.add(new CharacterData(91, 33, 7, 59,64, 66,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
gd.add(new CharacterData(92, 33, 7, 59,64, 88,0,22,22,"set toggle 0", CharacterData.STATE_HIDE, -1)); //色パネル１
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(94, 33, 7, 92,64, 0,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
gd.add(new CharacterData(95, 33, 7, 92,64, 22,0,22,22,"set toggle 1", CharacterData.STATE_SHOW, -1)); //色パネル２
gd.add(new CharacterData(96, 33, 7, 92,64, 44,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
gd.add(new CharacterData(97, 33, 7, 92,64, 66,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
gd.add(new CharacterData(98, 33, 7, 92,64, 88,0,22,22,"set toggle 1", CharacterData.STATE_HIDE, -1)); //色パネル２
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(100, 33, 7, 125,64, 0,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
gd.add(new CharacterData(101, 33, 7, 125,64, 22,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
gd.add(new CharacterData(102, 33, 7, 125,64, 44,0,22,22,"set toggle 2", CharacterData.STATE_SHOW, -1)); //色パネル３
gd.add(new CharacterData(103, 33, 7, 125,64, 66,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
gd.add(new CharacterData(104, 33, 7, 125,64, 88,0,22,22,"set toggle 2", CharacterData.STATE_HIDE, -1)); //色パネル３
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(106, 33, 7, 158,64, 0,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
gd.add(new CharacterData(107, 33, 7, 158,64, 22,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
gd.add(new CharacterData(108, 33, 7, 158,64, 44,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
gd.add(new CharacterData(109, 33, 7, 158,64, 66,0,22,22,"set toggle 3", CharacterData.STATE_SHOW, -1)); //色パネル４
gd.add(new CharacterData(110, 33, 7, 158,64, 88,0,22,22,"set toggle 3", CharacterData.STATE_HIDE, -1)); //色パネル４
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(112, 20, 5, 72,36, 0,0,22,22,"set toggle 4", CharacterData.STATE_SHOW, -101)); //壁ボタン１
gd.add(new CharacterData(113, 20, 5, 72,36, 22,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //壁ボタン１
gd.add(new CharacterData(114, 20, 5, 72,36, 44,0,22,22,"set toggle 4", CharacterData.STATE_HIDE, -101)); //壁ボタン１
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(116, 20, 5, 103,36, 0,0,22,22,"set toggle 5", CharacterData.STATE_SHOW, -101)); //壁ボタン２
gd.add(new CharacterData(117, 20, 5, 103,36, 22,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //壁ボタン２
gd.add(new CharacterData(118, 20, 5, 103,36, 44,0,22,22,"set toggle 5", CharacterData.STATE_HIDE, -101)); //壁ボタン２
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(120, 20, 5, 134,36, 0,0,22,22,"set toggle 6", CharacterData.STATE_SHOW, -101)); //壁ボタン３
gd.add(new CharacterData(121, 20, 5, 134,36, 22,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //壁ボタン３
gd.add(new CharacterData(122, 20, 5, 134,36, 44,0,22,22,"set toggle 6", CharacterData.STATE_HIDE, -101)); //壁ボタン３
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(124, 20, 5, 72,64, 0,0,22,22,"set toggle 7", CharacterData.STATE_SHOW, -101)); //壁ボタン４
gd.add(new CharacterData(125, 20, 5, 72,64, 22,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //壁ボタン４
gd.add(new CharacterData(126, 20, 5, 72,64, 44,0,22,22,"set toggle 7", CharacterData.STATE_HIDE, -101)); //壁ボタン４
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(128, 20, 5, 103,64, 0,0,22,22,"set toggle 8", CharacterData.STATE_SHOW, -101)); //壁ボタン５
gd.add(new CharacterData(129, 20, 5, 103,64, 22,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //壁ボタン５
gd.add(new CharacterData(130, 20, 5, 103,64, 44,0,22,22,"set toggle 8", CharacterData.STATE_HIDE, -101)); //壁ボタン５
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(132, 20, 5, 134,64, 0,0,22,22,"set toggle 9", CharacterData.STATE_SHOW, -101)); //壁ボタン６
gd.add(new CharacterData(133, 20, 5, 134,64, 22,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //壁ボタン６
gd.add(new CharacterData(134, 20, 5, 134,64, 44,0,22,22,"set toggle 9", CharacterData.STATE_HIDE, -101)); //壁ボタン６
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(136, 20, 5, 72,92, 0,0,22,22,"set toggle 10", CharacterData.STATE_SHOW, -101)); //壁ボタン７
gd.add(new CharacterData(137, 20, 5, 72,92, 22,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //壁ボタン７
gd.add(new CharacterData(138, 20, 5, 72,92, 44,0,22,22,"set toggle 10", CharacterData.STATE_HIDE, -101)); //壁ボタン７
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(140, 20, 5, 103,92, 0,0,22,22,"set toggle 11", CharacterData.STATE_SHOW, -101)); //壁ボタン８
gd.add(new CharacterData(141, 20, 5, 103,92, 22,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //壁ボタン８
gd.add(new CharacterData(142, 20, 5, 103,92, 44,0,22,22,"set toggle 11", CharacterData.STATE_HIDE, -101)); //壁ボタン８
g.addElement(gd);
gd.addAlltoVector(v);
gd = new GroupData();
gd.add(new CharacterData(144, 20, 5, 134,92, 0,0,22,22,"set toggle 12", CharacterData.STATE_SHOW, -101)); //壁ボタン９
gd.add(new CharacterData(145, 20, 5, 134,92, 22,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //壁ボタン９
gd.add(new CharacterData(146, 20, 5, 134,92, 44,0,22,22,"set toggle 12", CharacterData.STATE_HIDE, -101)); //壁ボタン９
g.addElement(gd);
gd.addAlltoVector(v);
v.addElement(new CharacterData(148, 1, 0, 42,25, 46,0,18,30,"set event 1", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャル左カーソル
v.addElement(new CharacterData(149, 1, 0, 60,25, 64,0,126,30,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャルアイテム一覧
v.addElement(new CharacterData(150, 1, 0, 186,25, 190,0,18,30,"set event 2", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャル右カーソル
v.addElement(new CharacterData(151, 1, 0, 60,60, 0,30,126,126,"", CharacterData.STATE_SHOW, -1, CharacterData.ATTR_SHOW_MYITEM)); //スペシャル詳細アイテム窓
v.addElement(new ItemData(152, 3, 0, 0, 24, 24, 652, "set select 152 set detail 652 set msg スパナだ…", -1)); // スパナ
v.addElement(new ItemData(652, 6, 0, 0, 120, 120, 0, "set msg 何に使おう…", -1)); // 詳細スパナ
v.addElement(new ItemData(153, 3, 24, 0, 24, 24, 653, "set select 153 set detail 653 set msg 長い棒だ…", -1)); // 長い棒
v.addElement(new ItemData(653, 32, 0, 0, 120, 120, 0, "set msg 結構長い…", -1)); // 詳細長い棒
v.addElement(new ItemData(154, 3, 48, 0, 24, 24, 654, "set select 154 set detail 654 set msg 小びんだ…", -1)); // 小びん
v.addElement(new ItemData(654, 31, 0, 0, 120, 120, 0, "set msg 中に液体が入ってる", -1)); // 詳細小びん
v.addElement(new ItemData(155, 3, 72, 0, 24, 24, 655, "set select 155 set detail 655 set msg シートだ…", -1)); // シート
v.addElement(new ItemData(655, 9, 0, 0, 120, 120, 0, "if isset item 154 set delitem 154 set delitem 155 set item 157 set msg 文字が出てきた！ else set msg 何も書かれてない… end", -1)); // 詳細シート
v.addElement(new ItemData(156, 3, 96, 0, 24, 24, 656, "set select 156 set detail 656 set msg 鍵だ…", -1)); // 鍵
v.addElement(new ItemData(656, 8, 0, 0, 120, 120, 0, "set msg これで…", -1)); // 詳細鍵
v.addElement(new ItemData(157, 3, 120, 0, 24, 24, 657, "set select 157 set detail 657 set msg 文字シートだ…", -1)); // 文字シート
v.addElement(new ItemData(657, 13, 0, 0, 120, 120, 0, "set msg どういう意味だ…", -1)); // 詳細文字シート
v.addElement(new ItemData(158, 3, 144, 0, 24, 24, 658, "set select 158 set detail 658 set msg 細長い鍵だ…", -1)); // 細長い鍵
v.addElement(new ItemData(658, 36, 0, 0, 120, 120, 0, "set msg なんの鍵だろう…", -1)); // 詳細細長い鍵
v.addElement(new ItemData(159, 3, 168, 0, 24, 24, 659, "set select 159 set detail 659 set msg 鏡だ…", -1)); // 鏡
v.addElement(new ItemData(659, 45, 0, 0, 120, 120, 0, "set msg 鏡だ…", -1)); // 詳細鏡
v.addElement(new ItemData(160, 3, 192, 0, 24, 24, 660, "set select 160 set detail 660 set msg リモコンだ…", -1)); // リモコン
v.addElement(new ItemData(660, 39, 0, 0, 120, 120, 0, "set msg 何に使おう…", -1)); // 詳細リモコン
v.addElement(new ItemData(161, 3, 216, 0, 24, 24, 661, "set select 161 set detail 661 set msg ぬいぐるみだ…", -1)); // ぬいぐるみ
v.addElement(new ItemData(661, 51, 0, 0, 120, 120, 0, "if isset item 165 set delitem 161 set show -107 set item 167 set msg 腹を割いた！ else set msg ぬいぐるみだ… end", -1)); // 詳細ぬいぐるみ
v.addElement(new ItemData(162, 3, 0, 24, 24, 24, 662, "set select 162 set detail 662 set msg 目玉だ…", -1)); // 目玉
v.addElement(new ItemData(662, 51, 0, 0, 120, 120, 0, "set msg 目玉だ…", -1)); // 詳細目玉
v.addElement(new ItemData(163, 3, 24, 24, 24, 24, 663, "set select 163 set detail 663 set msg 鏡２だ…", -1)); // 鏡２
v.addElement(new ItemData(663, 47, 0, 0, 120, 120, 0, "set msg 何か映ってる…", -1)); // 詳細鏡２
v.addElement(new ItemData(164, 3, 48, 24, 24, 24, 664, "set select 164 set detail 664 set msg 目玉の無いぬいぐるみだ…", -1)); // 目玉の無いぬいぐるみ
v.addElement(new ItemData(664, 51, 0, 0, 120, 120, 0, "if isset item 162 set delitem 162 set delitem 164 set item 161 set msg 目玉を付けた！ else isset item 165 set delitem 165 set delitem 164 set item 166 set msg 腹を割いた！ else set msg 片目が取れてる… end", -1)); // 詳細目玉の無いぬいぐるみ
v.addElement(new ItemData(165, 3, 72, 24, 24, 24, 665, "set select 165 set detail 665 set msg ハサミだ…", -1)); // ハサミ
v.addElement(new ItemData(665, 53, 0, 0, 120, 120, 0, "set msg ハサミだ…", -1)); // 詳細ハサミ
v.addElement(new ItemData(166, 3, 96, 24, 24, 24, 666, "set select 166 set detail 666 set msg 目玉の無い腹割いたぬいぐるみだ…", -1)); // 目玉の無い腹割いたぬいぐるみ
v.addElement(new ItemData(666, 51, 0, 0, 120, 120, 0, "if isset item 162 set delitem 162 set delitem 166 set item 167 set msg 目玉を付けた！ else isset hide -106 set show -106 set item 158 set msg 細長い鍵が入ってた! else set msg お腹は綿だらけだ… end", -1)); // 詳細目玉の無い腹割いたぬいぐるみ
v.addElement(new ItemData(167, 3, 120, 24, 24, 24, 667, "set select 167 set detail 667 set msg 腹割いたぬいぐるみだ…", -1)); // 腹割いたぬいぐるみ
v.addElement(new ItemData(667, 55, 0, 0, 120, 120, 0, "if isset hide -106 set show -106 set item 158 set msg 細長い鍵が入ってた! else isset item 168 set delitem 168 set delitem 167 set item 161 set msg お腹を縫った！ else set msg お腹は綿だらけだ…… end", -1)); // 詳細腹割いたぬいぐるみ
v.addElement(new ItemData(168, 3, 144, 24, 24, 24, 668, "set select 168 set detail 668 set msg 裁縫道具だ…", -1)); // 裁縫道具
v.addElement(new ItemData(668, 40, 0, 0, 120, 120, 0, "set msg 裁縫道具だ…", -1)); // 詳細裁縫道具
v.addElement(new ItemData(169, 3, 168, 24, 24, 24, 669, "set select 169 set detail 669 set msg 護符だ…", -1)); // 護符
v.addElement(new ItemData(669, 57, 0, 0, 120, 120, 0, "set msg 護符だ…", -1)); // 詳細護符
v.addElement(new ItemData(170, 3, 192, 24, 24, 24, 670, "set select 170 set detail 670 set msg 人形の首だ…", -1)); // 人形の首
v.addElement(new ItemData(670, 49, 0, 0, 120, 120, 0, "set delitem 170 set item 171 set msg …", -1)); // 詳細人形の首
v.addElement(new ItemData(171, 3, 216, 24, 24, 24, 671, "set select 171 set detail 671 set msg 笑っている人形の首だ…", -1)); // 笑っている人形の首
v.addElement(new ItemData(671, 38, 0, 0, 120, 120, 0, "set msg …", -1)); // 詳細笑っている人形の首

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
