/*
 * Last modified: 2008/11/07 22:24:02
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;

import java.util.Vector;

public class Data
{
	// 最低限 1アクション＋ set go 2 
	private static final String[] storyCode = new String[] {
		"set image 7 set msg 『……大丈夫……』 set msg 『……あなたなら…きっと…』 set image -1 set msg ……… set msg ………… set msg …………… set msg ……ここ……… set msg ………どこ……？ set play 4",
		"set msg わっ！！ set image -1 set msg 仮面が…！ set msg …外れない……っ！ set play 4",
		"set msg やった… set msg ………出られた… set msg ……………………… set msg ………あっ… set msg ……思い出した…… set image 20 set msg 『…なら…この薬を飲みなさい…』 set msg 『…まだ実験中のモノだけど…』 set image -1 set msg ……そうだ…… set msg …あの女の人に… set msg ……… set msg …… set msg 赤ゐ糸-前編-　終 set title 1",
		"set image 22 set msg ……これで…… set msg ………これで…… set msg ……今度こそ……っ！ set title 1"
	};
	
	public static String getStoryCode(int id)
	{
		return storyCode[id];
	}
	
	public static void makeRooms()
	{
		RoomData.addRooms(0, "4:-1:1:2");//教室前
		RoomData.addRooms(1, "-1:-1:3:0");//教室窓
		RoomData.addRooms(2, "-1:-1:0:3");//教室廊下
		RoomData.addRooms(3, "-1:-1:2:1");//教室後ろ
		RoomData.addRooms(4, "-1:0:-1:-1");//天井
		RoomData.addRooms(5, "-1:0:-1:-1");//教卓裏
		RoomData.addRooms(6, "-1:3:-1:-1");//後ろ棚左
		RoomData.addRooms(7, "-1:3:-1:-1");//後ろ棚右
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
		// 教卓とか黒板とか
		v.addElement(new CharacterData(-1, 2, 0, 0, 0,  0,0,240,240,null));
		//v.addElement(new CharacterData(-1, 7, 0, 63, 46, 143,0,97,140,null));
		//v.addElement(new CharacterData(-1, 7, 0, 166, 147, 58, 0, 68, 39,null));
		//v.addElement(new CharacterData(-1, 7, 0, 112, 44, 11,57,72,25,null));

		v.addElement(new CharacterData(2,3, 0, 3, 87, 0,0,192,71,"if isset item 1101 set msg 特に書くことはない else isset item 1100 set msg 消すものがない else set msg 黒板だ… end"));
		v.addElement(new CharacterData(9,13, 0, 3, 87, 49,171,191,69,"if isset item 1100 set hide 9 set show 10 set msg 何か出てきた… else set msg なんだこれ… end", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(10,3, 0, 3, 87, 0,150,192,69,"set msg いろんな色…", CharacterData.STATE_HIDE));

		v.addElement(new CharacterData(3,3, 0, 200, 57, 75,91,29,26,"if isset show 8 set item 1108 set msg 時計を取った set hide 3 else set msg 時計だ end"));
		v.addElement(new CharacterData(4,3, 0, 31, 145, 71,73,14,9,"set hide 4 set item 1101 set msg チョークを拾った"));

		//v.addElement(new CharacterData(5,3, 0, 162, 144, 85,77,14,8,"set hide 5 set show 451 set item 1100 set msg 黒板消しを拾った"));
		v.addElement(new CharacterData(5,3, 0, 162, 144, 85,77,14,8,"set hide 5 set item 1100 set msg 黒板消しを拾った"));

		v.addElement(new CharacterData(6,3, 0, 71, 157, 107,73,44,61,"set go 5"));
		v.addElement(new CharacterData(7,3, 0, 198, 97, 157,74,35,53,"set msg 時間割かな"));
		v.addElement(new CharacterData(-101,-1, 0, 180, 140, 0,0,47,63,"if isset item 1102 set show 8 set delitem 1102 set msg 椅子を置いた end"));
		//v.addElement(new CharacterData(8,14, 0, 188, 140, 0,0,47,63,"if isset show 8 set hide 8 set item 1102 set msg 椅子を取った end", CharacterData.STATE_HIDE));
		//cd = new CharacterData(8,14, 0, 180, 140, 0,0,47,63,"if isset show 3 set msg 時計に手が届きそうだ else set msg もう重くて持ちたくない end");
		v.addElement(new CharacterData(8,14, 0, 188, 140, 0,0,47,63,"if isset show 3 set msg 時計に手が届きそう else set msg もう持ちたくない end", CharacterData.STATE_HIDE));


		// 窓側
		v.addElement(new CharacterData(-1, 2, 1, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-1, 12, 1, 13, 64,  3,4,116,80,null)); //格子左
		v.addElement(new CharacterData(-1, 12, 1, 14, 68,  122,8,116,72,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg 夕焼けが眩しい… end")); //窓左
		v.addElement(new CharacterData(-1, 12, 1, 134, 64,  3,4,106,80,null)); //格子右
		v.addElement(new CharacterData(-1, 12, 1, 135, 68,  122,8,105,72,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg 夕焼けが眩しい… end"));//窓右


		v.addElement(new CharacterData(201, 12, 1, 14, 68,  0,120,118,74,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg なに…これ… end", CharacterData.STATE_HIDE)); //窓左 手
		v.addElement(new CharacterData(202, 12, 1, 135, 68,  120,120,105,72,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg なに…これ… end", CharacterData.STATE_HIDE));//窓右 手


		v.addElement(new CharacterData(-1, 12, 1, 13, 144,  4,98,227,17,null));//棒
		
		v.addElement(new CharacterData(-1, 12, 1, 67, 80,  141,84,4,6,"set msg 動かない")); // とって
		v.addElement(new CharacterData(-1, 12, 1, 67, 120,  141,84,4,6,"set msg 動かない")); // とって
		v.addElement(new CharacterData(-1, 12, 1, 188, 80,  141,84,4,6,"set msg 動かない")); // とって
		v.addElement(new CharacterData(-1, 12, 1, 188, 120,  141,84,4,6,"set msg 動かない")); // とって

		



		// 廊下側
		v.addElement(new CharacterData(-1, 2, 2, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(-1, 13, 2, 12, 91,  67,5,103,95,null));//ドア縁
		v.addElement(new CharacterData(-301, 2, 2, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));//flag
		v.addElement(new CharacterData(301, 13, 2, 60, 94,  5,8,55,91,"if isset show -301 set show 309 set show 310 set show 311 else set msg 開かない… end"));//ドア

		v.addElement(new CharacterData(302, 13, 2, 71, 109,  183,12,33,24,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg 早くここから出たい… end"));//ドア窓
		v.addElement(new CharacterData(303, 13, 2, 140, 138,  7,112,100,17,null));//棒みたいなの
		v.addElement(new CharacterData(304, 13, 2, 12, 55,  130,110,104,24,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg 特に異常はない… end"));//上窓1
		v.addElement(new CharacterData(305, 13, 2, 140, 55,  130,110,100,24,"if isset item 1102 set msg 割れない… else isset item 1104 set msg 割れない… else set msg 特に異常はない… end"));//上窓2
		
		v.addElement(new CharacterData(306, 13, 2, 61, 94+50,  6,58,12,12,"if isset item 1107 set delitem 1107 set show -301 set hide 306 set msg カチッと音がした！ else set msg 開かない… end"));//ドア鍵

		v.addElement(new CharacterData(309, 13, 2, 40, 94,  5,8,55,91,null, CharacterData.STATE_HIDE));//あいたドア
		v.addElement(new CharacterData(310, 13, 2, 51, 109,  183,12,33,24,null, CharacterData.STATE_HIDE));//ドア窓
		v.addElement(new CharacterData(311, 13, 2, 40+55, 94,  0,141,20,91,"set story 2", CharacterData.STATE_HIDE));//あいたドア隙間
		

		// 教室後ろ側
		v.addElement(new CharacterData(-1, 2, 3, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(401, 8, 3, 10, 130,  0,0,90,71,"set go 6"));// 棚左
		v.addElement(new CharacterData(402, 8, 3, 100, 130,  90,0,90,71,"set go 7"));// 棚右
		v.addElement(new CharacterData(404, 3, 3, 195, 170, 201,0,21,35, "set detailnoitem 2301 set msg ゴミ箱は空っぽ…"));
		
		v.addElement(new CharacterData(403,14, 3, 48, 152, 0,0,47,63,"set hide 403 set item 1102 set msg 椅子を取った"));




		// 天井
		v.addElement(new CharacterData(-1, 10, 4, 0, 0,  0,0,240,240,null));
		//v.addElement(new CharacterData(451, 10, 4, 0, 0,  0,0,240,240,"set msg なんだろうあれ…", CharacterData.STATE_HIDE));//来訪
		//v.addElement(new CharacterData(452, 20, 4, 0, 0,  0,0,240,240,"set hide 451 set hide 452 set show 201 set show 202 set show 9 set additemonly 1112 set story 1", CharacterData.STATE_HIDE));//お面
		v.addElement(new CharacterData(452, 4, 4, 42, 24,  72,46,168,194,"set hide 452 set show 501 set show -501 set show 201 set show 202 set show 9 set additemonly 1112 set story 1", CharacterData.STATE_HIDE));//お面


		
		// 教卓裏
		v.addElement(new CharacterData(-1, 11, 5, 0, 0,  0,0,240,240,null));
		v.addElement(new CharacterData(501, 14, 5, 92, 50,  161,100,56,37,"set hide 501 set item 1106 set msg 箱を取った", CharacterData.STATE_HIDE));// 箱
		v.addElement(new CharacterData(-501, 1, 5, 0, 0,  0,0,0,0,null, CharacterData.STATE_HIDE));// flag
		
		v.addElement(new CharacterData(502, 14, 5, 109, 121,  123,146,57,56,"set msg …はら…あたま…？"));
		
		
		
		
		// 後ろ棚左
		v.addElement(new CharacterData(-1, 16, 6, 0, 0,  0,0,240,240,"set msg 特に何もない"));
		v.addElement(new CharacterData(601, 14, 6, 100, 74,  26,63,23,13, "set hide 601 set item 1104 set msg トンカチを拾った"));
		
		
		
		// 後ろ棚右
		v.addElement(new CharacterData(-1, 17, 7, 0, 0,  0,0,240,240,null));


		v.addElement(new CharacterData(701, 14, 7, 36, 73,  191,69,19,16,"set msg 人形だ…"));
		v.addElement(new CharacterData(702, 14, 7, 111, 73,  191,69,19,16,"set msg 人形だ…"));
		v.addElement(new CharacterData(703, 14, 7, 183, 73,  191,69,19,16,"set msg 人形だ…"));
		v.addElement(new CharacterData(704, 14, 7, 36, 123,  191,69,19,16,"set msg 人形だ…"));
		v.addElement(new CharacterData(705, 14, 7, 111, 123,  191,69,19,16,"set msg 不気味…"));
		v.addElement(new CharacterData(706, 14, 7, 183, 123,  191,69,19,16,"set msg 不気味…"));
		v.addElement(new CharacterData(707, 14, 7, 36, 173,  191,69,19,16,"set msg 不気味…"));
		v.addElement(new CharacterData(708, 14, 7, 111, 173,  191,69,19,16,"set msg 不気味…"));
		v.addElement(new CharacterData(709, 14, 7, 183, 173,  191,69,19,16,"set msg 不気味…"));



		v.addElement(new CharacterData(711, 14, 7, 48, 77,  159,72,18,14,"set show 721 set hide 711 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(712, 14, 7, 123, 77,  159,72,18,14,"set show 722 set hide 712 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(713, 14, 7, 196, 77,  159,72,18,14,"set show 723 set hide 713 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(714, 14, 7, 48, 127,  159,72,18,14,"set show 724 set hide 714 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(715, 14, 7, 123, 127,  159,72,18,14,"set show 725 set hide 715 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(716, 14, 7, 196, 127,  159,72,18,14,"set show 726 set hide 716 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(717, 14, 7, 48, 177,  159,72,18,14,"set show 727 set hide 717 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(718, 14, 7, 123, 177,  159,72,18,14,"set show 728 set hide 718 set event 30", CharacterData.STATE_HIDE));
		v.addElement(new CharacterData(719, 14, 7, 196, 177,  159,72,18,14,"set show 729 set hide 719 set event 30", CharacterData.STATE_HIDE));



		v.addElement(new CharacterData(721, 14, 7, 36, 60,  191,56,19,13,"set show 711 set hide 721 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(722, 14, 7, 111, 60,  191,56,19,13,"set show 712 set hide 722 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(723, 14, 7, 183, 60,  191,56,19,13,"set show 713 set hide 723 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(724, 14, 7, 36, 110,  191,56,19,13,"set show 714 set hide 724 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(725, 14, 7, 111, 110,  191,56,19,13,"set show 715 set hide 725 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(726, 14, 7, 183, 110,  191,56,19,13,"set show 716 set hide 726 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(727, 14, 7, 36, 160,  191,56,19,13,"set show 717 set hide 727 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(728, 14, 7, 111, 160,  191,56,19,13,"set show 718 set hide 728 set event 30", CharacterData.STATE_SHOW));
		v.addElement(new CharacterData(729, 14, 7, 183, 160,  191,56,19,13,"set show 719 set hide 729 set event 30", CharacterData.STATE_SHOW));


		
		
		
		
		// アイテム
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

		v.addElement(new ItemData(1100, 4, 0, 0,24, 24, 2200,"set select 1100 set detail 2200 set msg 黒板消しだ…"));
		v.addElement(new ItemData(1101, 4, 24, 0,24, 24, 2201,"set select 1101 set detail 2201 set msg チョークだ…"));
		v.addElement(new ItemData(1102, 4, 48, 0,24, 24, 2202,"set select 1102 set detail 2202 set msg 椅子だ…"));
		//v.addElement(new ItemData(1103, 4, 72, 0,24, 24, 2203,"set select 1103 set detail 2203 set msg カギだ"));
		v.addElement(new ItemData(1104, 4, 96, 0,24, 24, 2204,"set select 1104 set detail 2204 set msg トンカチだ…"));
		v.addElement(new ItemData(1105, 4, 120, 0,24, 24, 2205,"set select 1105 set detail 2205 set msg 電池だ…"));
		v.addElement(new ItemData(1106, 4, 144, 0,24, 24, 2216,"set select 1106 set detail 2216 set msg からくり箱だ…"));
		v.addElement(new ItemData(1107, 4, 168, 0,24, 24, 2207,"set select 1107 set detail 2207 set msg カギだ…"));
		v.addElement(new ItemData(1108, 4, 192, 0,24, 24, 2208,"set select 1108 set detail 2208 set msg 時計だ…"));
		v.addElement(new ItemData(1112, 4, 216, 0,24, 24, 2212,"set select 1112 set detail 2212 set msg いつの間に…？"));
		v.addElement(new ItemData(1113, 4, 0, 24,24, 24, 2213,"set select 1113 set detail 2213 set msg フィルムだ…"));
		

		// アイテム詳細
		v.addElement(new ItemData(2200, 5, 0, 0,120, 120, 0,"set msg 変わった所はない"));
		v.addElement(new ItemData(2201, 5, 120, 0,120, 120, 0,"if isset item 1104 set delitem 1101 set additemonly 1113 set detailnoitem 2211 set msg フィルムが出てきた！ else set msg 変わった所はない end"));
		v.addElement(new ItemData(2202, 6, 0, 0,120, 120, 0,"set msg 重い…"));
		//v.addElement(new ItemData(2203, 6, 120, 0,120, 120, 0,"set msg なんのカギだろう"));
		v.addElement(new ItemData(2204, 5, 0, 120,120, 120, 0,"set msg 変わった所はない"));
		v.addElement(new ItemData(2205, 5, 120, 120,120, 120, 0,"set msg 変わった所はない"));
		v.addElement(new ItemData(2207, 6, 120, 120,120, 120, 0,"set msg なんのカギだろう"));
		v.addElement(new ItemData(2208, 15, 0, 120,120, 120, 0,"set detail 2209"));
		ItemData tokei = new ItemData(2297, -1, 0, 0, 60, 60, 0,"if isset item 1104 set msg 何故か壊れない else set msg 針がない… end");
		tokei.rootId = 2208;
		tokei.x = 63+30;
		tokei.y = 63+30;
		tokei.state = ItemData.STATE_SHOW;
		v.addElement(tokei);



		v.addElement(new ItemData(2209, 15, 120, 0,120, 120, 0,"set detail 2208"));
		ItemData tokei2 = new ItemData(2298, -1, 0, 0, 60, 60, 0,"if isset item 1104 set msg 何故か壊れない else set msg 時計の裏側… end");
		tokei2.rootId = 2209;
		tokei2.x = 63+30;
		tokei2.y = 63+30;
		tokei2.state = ItemData.STATE_SHOW;
		v.addElement(tokei2);


		v.addElement(new ItemData(2211, 15, 0, 0,120, 120, 0,null));
		v.addElement(new ItemData(2212, 15, 120, 120,120, 120, 0,"set msg …なんだろう…"));
		v.addElement(new ItemData(2213, 19, 120, 120,120, 120, 0,"set msg …白緑赤紫…"));
		//ItemData denti = new ItemData(2214, 14, 0, 100, 22, 12, 0,"set item 1105 set detail 2205 set hide 2214 set msg 電池を取った");
		ItemData denti = new ItemData(2299, 14, 0, 100, 22, 12, 0,"set item 1105 set hide 2299 set msg 電池を取った");
		denti.rootId = 2209;
		denti.x = 63+49;
		denti.y = 63+65;
		denti.state = ItemData.STATE_SHOW;
		v.addElement(denti);



		v.addElement(new ItemData(2216, 19, 0, 0,120, 120, 0,"if isset item 1104 set msg 壊れない else set msg どうしたら開くかな end"));//真正面
		ItemData itemTmp = new ItemData(2217, -1, 5, 5, 109, 33, 0,"set detail 2218"); // 裏へ
		itemTmp.rootId = 2216;
		itemTmp.x = 68;
		itemTmp.y = 68;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);

		for ( int i = 0; i < 4; i++ )
		{
			//数字
			for ( int j = 0; j < 10; j++ )
			{
				// 2240~2279
				itemTmp = new ItemData(2240 + (i * 10) + j, 14, 140+(j*10), 227, 10, 13, 0,null);
				itemTmp.rootId = 2216;
				itemTmp.x = 63+31+(i*16);
				itemTmp.y = 63+61;
				itemTmp.state = ItemData.STATE_HIDE;
				v.addElement(itemTmp);
			}
			
			//ボタン 2230~2233
			itemTmp = new ItemData(2230 + i, -1, 31, 77, 10, 11, 0,"if isset show 2222 set event " + (50 + i) + " else set msg 動かない end");
			itemTmp.rootId = 2216;
			itemTmp.x = 63+31+(i*16);
			itemTmp.y = 63+77;
			itemTmp.state = ItemData.STATE_SHOW;
			v.addElement(itemTmp);
		}
		itemTmp = new ItemData(2219, -1, 43, 42, 34, 16, 0,"set event 48"); // 取っ手
		itemTmp.rootId = 2216;
		itemTmp.x = 63+43;
		itemTmp.y = 63+42;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);

		v.addElement(new ItemData(2218, 19, 120, 0,120, 120, 0,"if isset item 1104 set msg 壊れない else set msg 箱の裏側だ… end"));//底
		itemTmp = new ItemData(2220, -1, 144, 72, 24, 14, 0,"if isset item 1105 set delitem 1105 set show 2222 set event 49 set msg 電池を入れた else set msg 電池が入りそう end"); // 電池入れ
		itemTmp.rootId = 2218;
		itemTmp.x = 63+24;
		itemTmp.y = 63+72;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);
		itemTmp = new ItemData(2221, -1, 5, 5, 109, 33, 0,"set detail 2216"); //表へ
		itemTmp.rootId = 2218;
		itemTmp.x = 68;
		itemTmp.y = 68;
		itemTmp.state = ItemData.STATE_SHOW;
		v.addElement(itemTmp);
		itemTmp = new ItemData(2222, 14, 0, 100, 22, 12, 0,"set msg 電池が入ってる…"); //電池
		itemTmp.rootId = 2218;
		itemTmp.x = 63+24;
		itemTmp.y = 63+72;
		itemTmp.state = ItemData.STATE_HIDE;
		v.addElement(itemTmp);
		v.addElement(new ItemData(2290, 19, 0, 120,120, 120, 0,null));//開いた




		v.addElement(new ItemData(2301, 14, 0, 120,120, 120, 0,null)); // ゴミ箱上から



		CharacterData[] ret = new CharacterData[v.size()];
		for ( int i = 0; i < v.size(); i++ )
		{
			ret[i] = (CharacterData) v.elementAt(i);
		}
		return ret;
	}
}