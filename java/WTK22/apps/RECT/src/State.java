import java.util.Vector;

public class State
{
	public static final String RECORD_KEY = "rect";
	public static final int MIN_WALL = 1;
	public static final int MAX_WALL = 4;
	public static final int WALL_X = 0;
	public static final int WALL_Y = 36;
	public static final int WALL_WIDTH = 240;
	public static final int WALL_HEIGHT = 232;
	public static final int NEXT_WALL_AREA = 30;

	boolean roomLight = true;
	int wallState = MIN_WALL;
	int backWallState = MIN_WALL;
	boolean showMyItem;
	CharacterData my;
	CharacterData[] allow = new CharacterData[5];
	CharacterData[] item;
	CharacterData selectedItem = null;
	CharacterData detailItem = null;
	String msg = null;
	int ending = -1;
	
	private CharacterData addItem(Vector v, int x, int y, int width, int height, int imageId, int wallId, int attribute, int state)
	{
		CharacterData c = new CharacterData(x + WALL_X, y + WALL_Y, width, height, imageId, wallId, attribute, state, imageId);
		v.addElement(c);
		return c;
	}
	
	public int init(int w, int h)
	{
		CharacterData tmp = null;
		my = new CharacterData(w / 2, h / 2, 16, 16, 0);
		
		allow[0] = new CharacterData(WALL_X, WALL_Y, 26, 240, 1);
		allow[1] = new CharacterData((WALL_WIDTH + WALL_X) - 26, WALL_Y, 26, 240, 2);
		allow[2] = new CharacterData(4, 142, 16, 24, 3);
		allow[3] = new CharacterData((WALL_WIDTH + WALL_X) - 28, 142, 16, 24, 4);
		allow[4] = new CharacterData(108, 250, WALL_WIDTH, NEXT_WALL_AREA, 29);

		Vector v = new Vector();
		for ( int i = 0; i < 4; i++ )
		{
			addItem(v, 0, 0, WALL_WIDTH, WALL_HEIGHT, 5, i + 1, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		}
		
		addItem(v, 160, 100, 16, 8, 6, 1, CharacterData.ATTR_SHOW_ALL, CharacterData.STATE_SHOW);
		addItem(v, 90, 52, 63, 125, 7, 1, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 90+6, 52+73, 9, 5, -100, 1, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 90, 52, 65, 150, 57, 1, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		addItem(v, 127, 202, 18, 19, 22, 1, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 43, 86, 44, 42, 46, 1, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		
		addItem(v, 153, 22, 54, 42, 8, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 197, 12, 10, 10, 16, 2, CharacterData.ATTR_SHOW_DARKONLY, CharacterData.STATE_SHOW);
		addItem(v, 197, 12, 10, 10, -1, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 38, 76, 70, 50, 14, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 38, 76, 70, 50, 42, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		addItem(v, 84, 157, 120, 48, 18, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 100, 190, 80, 15, -2, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 92, 152, 36, 21, 19, 2, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		
		addItem(v, 50, 0, 128, 203, 9, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp = addItem(v, 50+51, 104, 10, 11, -5, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp.rootId = 9;
		tmp = addItem(v, 50+69, 104, 10, 11, -6, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp.rootId = 9;
		addItem(v, 50, 0, 144, 223, 20, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		addItem(v, 50, 0, 143, 83, 63, 3, CharacterData.ATTR_SHOW_DARKONLY, CharacterData.STATE_DISABLE);
		
		
		tmp = addItem(v, 50+51, 104, 10, 11, -5, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp.rootId = 20;
		addItem(v, 35, 0, 144, 223, 21, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		addItem(v, 35, 0, 86, 69, 62, 3, CharacterData.ATTR_SHOW_DARKONLY, CharacterData.STATE_DISABLE);
		
		tmp = addItem(v, 35+84, 104, 10, 11, -6, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp.rootId = 21;
		tmp = addItem(v, 35+40, 166, 26, 28, 58, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp.rootId = 21;
		tmp = addItem(v, 35+41, 166-22, 24, 24, 15, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		tmp.rootId = 21;
		tmp.id = -301;

		addItem(v, -1, -1, 0, 0, -200, 0, CharacterData.ATTR_SHOW_FLAG, CharacterData.STATE_SHOW);
		addItem(v, -1, -1, 0, 0, -201, 0, CharacterData.ATTR_SHOW_FLAG, CharacterData.STATE_DISABLE);
		addItem(v, -1, -1, 0, 0, -202, 0, CharacterData.ATTR_SHOW_FLAG, CharacterData.STATE_DISABLE);
		addItem(v, -1, -1, 0, 0, -203, 0, CharacterData.ATTR_SHOW_FLAG, CharacterData.STATE_DISABLE);


		
		addItem(v, 178, 170, 5, 32, -4, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 45, 170, 5, 32, -7, 3, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		
		addItem(v, 42, 110, 48, 64, 10, 4, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 42, 110, 48, 64, 11, 4, CharacterData.ATTR_SHOW_ALL, CharacterData.STATE_DISABLE);
		addItem(v, 31, 17, 80, 80, 12, 4, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 31, 17, 80, 80, 13, 4, CharacterData.ATTR_SHOW_DARKONLY, CharacterData.STATE_DISABLE);
		
		addItem(v, 0, 0, WALL_WIDTH, WALL_HEIGHT, 26, 5, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 174, 123, 19, 12, 27, 5, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 72, 124, 110, 74, 36, 5, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		
		addItem(v, 0, 0, WALL_WIDTH, WALL_HEIGHT, 34, 6, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 184, 54, 47, 46, 35, 6, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 50, 120, 96, 100, 60, 6, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		
		addItem(v, 0, 0, WALL_WIDTH, WALL_HEIGHT, 43, 7, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 209, 139, 7, 46, 44, 7, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		
		addItem(v, 0, 0, WALL_WIDTH, WALL_HEIGHT, 45, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		int count = 1;
		for ( int y = 0; y < 3; y++ )
		{
			for ( int x = 0; x < 3; x++ )
			{
				addItem(v, 58+(48*x), 116+(25*y), 39, 20, -20 - count, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
				count++;
			}
		}
		addItem(v, 59, 86, 20, 20, -31, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 96, 86, 20, 20, -32, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 135, 86, 20, 20, -33, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 172, 86, 20, 20, -34, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		tmp = addItem(v, 57, 50, 23, 32, 47, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		tmp.id = -41;
		tmp = addItem(v, 95, 50, 23, 32, 47, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		tmp.id = -42;
		tmp = addItem(v, 134, 50, 23, 32, 47, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		tmp.id = -43;
		tmp = addItem(v, 171, 50, 23, 32, 47, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
		tmp.id = -44;
		for ( int i = 1; i <= 9; i++ )
		{
			tmp = addItem(v, 57, 50, 23, 32, 47 + i, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
			tmp.id = -50 - i;
			tmp = addItem(v, 95, 50, 23, 32, 47 + i, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
			tmp.id = -60 - i;
			tmp = addItem(v, 134, 50, 23, 32, 47 + i, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
			tmp.id = -70 - i;
			tmp = addItem(v, 171, 50, 23, 32, 47 + i, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_DISABLE);
			tmp.id = -80 - i;
		}
		count = 1;
		for ( int y = 0; y < 3; y++ )
		{
			for ( int x = 0; x < 3; x++ )
			{
				addItem(v, 58+(48*x), 116+(25*y), 39, 20, -20 - count, 8, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
				count++;
			}
		}
		
		addItem(v, 0, 0, WALL_WIDTH, WALL_HEIGHT, 61, 9, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 71, 147, 103, 58, -8, 9, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		addItem(v, 38, 139, 29, 55, -9, 9, CharacterData.ATTR_SHOW_NORMAL, CharacterData.STATE_SHOW);
		
		
		// item
		addItem(v, 60, 176, 124, 28, 25, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_SHOW);

		// selected item
		int itemX = 0;
		addItem(v, 62 + (24 * itemX++), 178, 24, 24, 24, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 62 + (24 * itemX++), 178, 24, 24, 28, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 62 + (24 * itemX++), 178, 24, 24, 33, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 62 + (24 * itemX++), 178, 24, 24, 41, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 62 + (24 * itemX++), 178, 24, 24, 15, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		
		// item detail
		addItem(v, 60, 40, 120, 120, 23, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 60, 40, 120, 120, 30, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 60, 40, 120, 120, 31, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		addItem(v, 60, 40, 120, 120, 59, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		tmp = addItem(v, 60 + 45, 40 + 26, 35, 35, -3, 0, CharacterData.ATTR_SHOW_MYITEM_DETAIL, CharacterData.STATE_SHOW);
		tmp.rootId = 31;
		tmp = addItem(v, 60+50, 40+76, 23, 12, 32, 0, CharacterData.ATTR_SHOW_MYITEM_DETAIL, CharacterData.STATE_DISABLE);
		tmp.rootId = 31;
		addItem(v, 60, 40, 120, 120, 37, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		tmp = addItem(v, 60+49, 40+97, 20, 14, 40, 0, CharacterData.ATTR_SHOW_MYITEM_DETAIL, CharacterData.STATE_SHOW);
		tmp.rootId = 37;
		count = 1;
		for ( int y = 0; y < 4; y++ )
		{
			for ( int x = 0; x < 3; x++ )
			{
				tmp = addItem(v, 60+27+(22*x), 40+10+(22*y), 20, 20, 38, 0, CharacterData.ATTR_SHOW_MYITEM_DETAIL, CharacterData.STATE_SHOW);
				tmp.rootId = 37;
				tmp.id = 100 + count;
				tmp = addItem(v, 60+27+(22*x), 40+10+(22*y), 20, 20, 39, 0, CharacterData.ATTR_SHOW_MYITEM_DETAIL, CharacterData.STATE_DISABLE);
				tmp.rootId = 37;
				tmp.id = 120 + count;
				count++;
			}
		}
		addItem(v, 60, 40, 120, 120, 59, 0, CharacterData.ATTR_SHOW_MYITEM, CharacterData.STATE_DISABLE);
		
		
		
		item = new CharacterData[v.size()];
		int maxImage = 0;
		for ( int i = 0; i < v.size(); i++ )
		{
			tmp = (CharacterData) v.elementAt(i);
			if ( tmp.imageId > maxImage )
			{
				maxImage = tmp.imageId;
			}
			item[i] = tmp;
		}
		return maxImage;
	}

	private boolean event(CharacterData s)
	{
		CharacterData tmp;
		switch ( s.id )
		{
		case -100:
			if ( (selectedItem != null) && (selectedItem.id == 24) )
			{
				if ( isValidNumbers() || isTrueNumbers() )
				{
					search(57).state = CharacterData.STATE_SHOW;
					msg = "開いた…！";
				}
				else
				{
					msg = "カードは入るが開かない";
				}
			}
			else
			{
				msg = "カードが入りそう";
			}
			break;
		case -1:
			forward(6);
			break;
		case -2:
			forward(5);
			if ( search(-301).state == CharacterData.STATE_DISABLE )
			{
				search(36).state = search(42).state;
			}
			break;
		case -3:
			msg = "ボタンを押した";
			tmp = search(32);
			if ( tmp.state == CharacterData.STATE_SHOW )
			{
				tmp.state = CharacterData.STATE_DISABLE;
				search(11).state = CharacterData.STATE_DISABLE;
				search(13).state = CharacterData.STATE_DISABLE;
			}
			else
			{
				tmp.state = CharacterData.STATE_SHOW;
				search(11).state = CharacterData.STATE_SHOW;
				search(13).state = CharacterData.STATE_SHOW;
			}
			break;
		case -4:
			forward(7);
			break;
		case -5:
			if ( isValidNumbers() || isTrueNumbers() )
			{
				search(20).state = CharacterData.STATE_DISABLE;
				search(21).state = CharacterData.STATE_SHOW;
				search(63).state = CharacterData.STATE_DISABLE;
				search(62).state = CharacterData.STATE_SHOW;
			}
			else
			{
				msg = "開かない…";
			}
			break;
		case -6:
			if ( isValidNumbers() || isTrueNumbers() )
			{
				search(20).state = CharacterData.STATE_SHOW;
				search(21).state = CharacterData.STATE_DISABLE;
				search(62).state = CharacterData.STATE_DISABLE;
				search(63).state = CharacterData.STATE_SHOW;
			}
			else
			{
				msg = "開かない…";
			}
			break;
		case -7:
			forward(9);
			break;
		case -8:
			msg = "何か書いてある";
			break;
		case -9:
			msg = "汚れてて読めない";
			break;
		case -21:
		case -22:
		case -23:
		case -24:
		case -25:
		case -26:
		case -27:
		case -28:
		case -29:
			// number input
			int count = 0;
			if ( search(-41).state == CharacterData.STATE_SHOW )
			{
				count = -50;
			}
			else if ( search(-42).state == CharacterData.STATE_SHOW )
			{
				count = -60;
			}
			else if ( search(-43).state == CharacterData.STATE_SHOW )
			{
				count = -70;
			}
			else if ( search(-44).state == CharacterData.STATE_SHOW )
			{
				count = -80;
			}
			else
			{
				break;
			}
			for ( int i = 1; i <= 9; i++ )
			{
				search(count - i).state = CharacterData.STATE_DISABLE;
			}
			search(count + (s.id + 20)).state = CharacterData.STATE_SHOW;
			
			break;
		case -31:
		case -32:
		case -33:
		case -34:
			search(-41).state = CharacterData.STATE_DISABLE;
			search(-42).state = CharacterData.STATE_DISABLE;
			search(-43).state = CharacterData.STATE_DISABLE;
			search(-44).state = CharacterData.STATE_DISABLE;
			search(s.id - 10).state = CharacterData.STATE_SHOW;
			break;
		case 6:
			turnRoomLight();
			if ( search(-201).state == CharacterData.STATE_SHOW )
			{
				search(-201).state = CharacterData.STATE_DISABLE;
			}
			break;
		case 7:
			msg = "早く外に出たい…";
			break;
		case 8:
			if ( (selectedItem != null) && (selectedItem.id == 41) )
			{
				if ( checkWindow() )
				{
					search(42).state = CharacterData.STATE_SHOW;
					msg = "！";
					break;
				}
				else
				{
					msg = "反応しない";
				}
				search(42).state = CharacterData.STATE_DISABLE;
			}
			else
			{
				msg = "エアコンは動いてる";
			}
			break;
		case 9:
		case 20:
		case 21:
			msg = "タンスだ";
			break;
		case 22:
			s.state = CharacterData.STATE_DISABLE;
			search(24).state = CharacterData.STATE_SHOW;
			msg = "カードを拾った";
			break;
		case 10:
			msg = "ライトだ";
			break;
		case 11:
			msg = "ライトが光ってる";
			break;
		case 12:
			msg = "何かの染みかな…";
			break;
		case 13:
			msg = "なんだ…これ…";
			break;
		case 14:
			msg = "窓かな…外は見えない";
			break;
		case 16:
			msg = "暗くて動けない…";
			break;
		case 18:
			msg = "テーブルがある";
			break;
		case 19:
			msg = "デジタル時計だ";
			break;
			
		case 27:
			msg = "紙切れを拾った";
			s.state = CharacterData.STATE_DISABLE;
			search(28).state = CharacterData.STATE_SHOW;
			break;
		case 35:
			s.state = CharacterData.STATE_DISABLE;
			msg = "ボタンを手に入れた";
			search(33).state = CharacterData.STATE_SHOW;
			search(16).state = CharacterData.STATE_DISABLE;
			break;
		case 36:
			s.state = CharacterData.STATE_DISABLE;
			msg = "消えた…";
			break;
		case 44:
			s.state = CharacterData.STATE_DISABLE;
			search(41).state = CharacterData.STATE_SHOW;
			msg = "リモコンを拾った";
			break;
			//item
		case 15:
			setItem(s, 59, "人形の一部だ");
			break;
		case 24:
			setItem(s, 23, "白いカードだ");
			break;
			
		case 28:
			setItem(s, 30, "紙に何か書いてある");
			break;
		case 33:
			setItem(s, 31, "何かのボタンだ");
			break;
		case 41:
			setItem(s, 37, "リモコンかな？");
			break;
		case 42:
			msg = "なんだろうこれ…";
			break;
		case 46:
			forward(8);
			break;
			
			//item detail
		case 23:
			msg = "何のカードだろ…";
			break;
		case 30:
			msg = "どんな意味が…";
			break;
		case -301:
			msg = "『光と影を合わせよ』";
			/*
			s.state = CharacterData.STATE_DISABLE;
			search(15).state = CharacterData.STATE_SHOW;
			msg = "人形の首を取った";
			*/
			break;
		case 58:
			if ( search(-202).state == CharacterData.STATE_DISABLE )
			{
				search(-202).state = CharacterData.STATE_SHOW;
				search(60).state = CharacterData.STATE_SHOW;
				msg = "左の方で音がした…！";
			}
			else if ( search(-301).state == CharacterData.STATE_SHOW )
			{
				msg = "『光と影を合わせよ』";
			}
			else if ( (selectedItem != null) && (selectedItem.id == 15) )
			{
				search(-301).state = CharacterData.STATE_SHOW;
				selectedItem.state = CharacterData.STATE_DISABLE;
				selectedItem = null;
				msg = "ピッタリだ";
			}
			else
			{
				msg = "人形だ…首がない";
			}
			if ( search(-200).state == CharacterData.STATE_SHOW )
			{
				search(-201).state = CharacterData.STATE_SHOW;
			}
			break;
		case 59:
			msg = "女性の顔の人形だ";
			break;
		case 60:
			s.state = CharacterData.STATE_DISABLE;
			search(15).state = CharacterData.STATE_SHOW;
			msg = "人形の首を拾った";
			break;
		case 62:
		case 63:
			msg = "なんだ…これ…";
			break;
			// rimokon
		case 103:
			msg = "ボタンが効かない…";
			break;
		case 101:
		case 102:
		case 104:
		case 105:
		case 106:
		case 107:
		case 108:
		case 109:
		case 110:
		case 111:
		case 112:
			msg = "";
			int c = 0;
			for ( int i = 121; i <=132; i++ )
			{
				if ( search(i).state == CharacterData.STATE_SHOW )
				{
					c++;
				}
			}
			if ( c >= 3 )
			{
				msg = "これ以上押せない";
			}
			else
			{
				search(s.id+20).state = CharacterData.STATE_SHOW;
			}
			break;
		case 40:
			for ( int i = 121; i <= 132; i++ )
			{
				search(i).state = CharacterData.STATE_DISABLE;
			}
			break;
		case 57:
			if ( search(-203).state == CharacterData.STATE_SHOW )
			{
				ending = (isTrueNumbers()) ? 2:3;
			}
			else if ( isTrueNumbers() )
			{
				ending = (search(-301).state == CharacterData.STATE_SHOW) ? 0 : 1;
			}
			return true;
		default:
			msg = "";
			break;
		}
		return false;
	}
	
	public boolean isValidNumbers()
	{
		boolean ret = (search(-53).state == CharacterData.STATE_SHOW) &&
			(search(-62).state == CharacterData.STATE_SHOW) &&
			(search(-73).state == CharacterData.STATE_SHOW) &&
			(search(-89).state == CharacterData.STATE_SHOW);
		
		if ( ret )
		{
			search(-203).state = CharacterData.STATE_SHOW;
		}
		return ret;
	}
	
	public boolean isTrueNumbers()
	{
		return (search(-56).state == CharacterData.STATE_SHOW) &&
			(search(-67).state == CharacterData.STATE_SHOW) &&
			(search(-74).state == CharacterData.STATE_SHOW) &&
			(search(-81).state == CharacterData.STATE_SHOW);
	}
	
	public boolean checkWindow()
	{
		return ( search(121).state == CharacterData.STATE_SHOW &&
				 search(125).state == CharacterData.STATE_SHOW &&
				 search(127).state == CharacterData.STATE_SHOW &&
				 search(122).state == CharacterData.STATE_DISABLE &&
				 search(123).state == CharacterData.STATE_DISABLE &&
				 search(124).state == CharacterData.STATE_DISABLE &&
				 search(126).state == CharacterData.STATE_DISABLE &&
				 search(128).state == CharacterData.STATE_DISABLE &&
				 search(129).state == CharacterData.STATE_DISABLE &&
				 search(130).state == CharacterData.STATE_DISABLE &&
				 search(131).state == CharacterData.STATE_DISABLE &&
				 search(132).state == CharacterData.STATE_DISABLE );
	}
	
	public boolean play(int screenX, int screenY)
	{
		//allow
		if ( !showMyItem )
		{
			if ( isWall() )
			{
				if ( my.x < NEXT_WALL_AREA )
				{
					leftWall();
				}
				else if ( my.x > WALL_WIDTH - NEXT_WALL_AREA )
				{
					rightWall();
				}
			}
			else if ( wallState > MAX_WALL )
			{
				if ( my.y > WALL_Y + WALL_HEIGHT - NEXT_WALL_AREA )
				{
					back();
				}
			}
		}

		//event
		for ( int i = item.length - 1; i >= 0; i-- )
		{
			if ( (showMyItem == ((item[i].attribute == CharacterData.ATTR_SHOW_MYITEM)||(item[i].attribute == CharacterData.ATTR_SHOW_MYITEM_DETAIL))) &&
				 isShow(i) && my.isClick(item[i]))
			{
				return event(item[i]);
			}
		}
		return false;
	}

	public void checkCursor()
	{
		if ( my.x < WALL_X ) my.x=WALL_X;
		if ( my.x > (WALL_X + WALL_WIDTH) - my.width ) my.x= (WALL_X + WALL_WIDTH) - my.width;
		if ( my.y < WALL_Y ) my.y=WALL_Y;
		if ( my.y > (WALL_Y + WALL_HEIGHT) - my.height ) my.y=(WALL_Y + WALL_HEIGHT) - my.height;
	}
	
	public void setItem(CharacterData s, int itemId, String msg)
	{
		if ( detailItem != null )
		{
			if ( detailItem.id == itemId )
			{
				return;
			}
			detailItem.state = CharacterData.STATE_DISABLE;
		}
		CharacterData tmp = search(itemId);
		tmp.state = CharacterData.STATE_SHOW;
		this.msg = msg;
		selectedItem = s;
		detailItem = tmp;
	}
	
	public void hideItems()
	{
		if ( detailItem != null )
		{
			detailItem.state = CharacterData.STATE_DISABLE;
			detailItem = null;
		}
		showMyItem = false;
	}
	
	public CharacterData search(int id)
	{
		for ( int i = 0; i < item.length; i++ )
		{
			if ( item[i].id == id )
				return item[i];
		}
		return null;
	}

	public void forward(int wallId)
	{
		backWallState = wallState;
		wallState = wallId;
	}
	
	public void back()
	{
		wallState = backWallState;
	}
	
	public String toSaveData()
	{
		String sid = "";
		if ( selectedItem != null )
		{
			sid = String.valueOf(selectedItem.id);
		}
		String did = "";
		if ( detailItem != null )
		{
			did = String.valueOf(detailItem.id);
		}
		return my.x + ":" + my.y + ":" + roomLight + ":" + wallState + ":" + backWallState + ":" + showMyItem + ":" + sid + ":" + did;
	}

	public boolean turnShowMyItem()
	{
		showMyItem = !showMyItem;
		return showMyItem;
	}
	
	public boolean isWall()
	{
		return (wallState >= MIN_WALL) && (wallState <= MAX_WALL);
	}

	public void turnRoomLight()
	{
		roomLight = !roomLight;
	}
	
	public void rightWall()
	{
		wallState++;
		if ( wallState > MAX_WALL )
		{
			wallState = MIN_WALL;
		}
	}
	
	public void leftWall()
	{
		wallState--;
		if ( wallState < MIN_WALL )
		{
			wallState = MAX_WALL;
		}
	}
		
	public boolean isShow(int i)
	{
		CharacterData d = item[i];
		
		boolean r = ((d.rootId == -1) || (search(d.rootId).state == CharacterData.STATE_SHOW));
		if ( d.state == CharacterData.STATE_SHOW )
		{
			switch ( d.attribute )
			{
			case CharacterData.ATTR_SHOW_NORMAL:
				return roomLight && (d.wallId == wallState) && r;
			case CharacterData.ATTR_SHOW_ALL:
				return  (d.wallId == wallState) && r;
			case CharacterData.ATTR_SHOW_DARKONLY:
				return !roomLight && (d.wallId == wallState) && r;
			case CharacterData.ATTR_SHOW_MYITEM:
				return showMyItem && r;
			case CharacterData.ATTR_SHOW_MYITEM_DETAIL:
				return showMyItem && r;
			}
		}
		return false;
	}

	public void save()
	{
		StringBuffer saveData = new StringBuffer();
		saveData.append(toSaveData());
		saveData.append(",");

		for ( int i = 0; i < item.length; i++ )
		{
			saveData.append(item[i].toSaveData());
			if ( i != item.length - 1)
			{
				saveData.append(",");
			}
		}
		try
		{
			GameUtil.save(RECORD_KEY, saveData.toString());
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public boolean load()
	{
		try
		{
			if ( !GameUtil.isRecord(RECORD_KEY) )
			{
				return false;
			}
			String tmp = GameUtil.load(RECORD_KEY);
			if ( tmp == null )
			{
				return false;
			}
			
			String[] line = GameUtil.split(tmp, ',');
			String[] s = GameUtil.split(line[0], ':');
			int i = 0;
			my.x = Integer.parseInt(s[i++]);
			my.y = Integer.parseInt(s[i++]);
			roomLight = "true".equals(s[i++]);
			wallState = Integer.parseInt(s[i++]);
			backWallState = Integer.parseInt(s[i++]);
			showMyItem = "true".equals(s[i++]);
			if ( !"".equals(s[i]) )
			{
				int sid = Integer.parseInt(s[i]);
				CharacterData d = search(sid);
				selectedItem = d;
			}
			i++;
			
			if ( !"".equals(s[i]) )
			{
				int did = Integer.parseInt(s[i]);
				CharacterData d = search(did);
				detailItem = d;
			}
			i++;

			for ( i = 1; i < line.length; i++ )
			{
				s = GameUtil.split(line[i], ':');
				CharacterData d = search(Integer.parseInt(s[0]));
				d.state = Integer.parseInt(s[1]);
			}
			return true;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			msg = "ロードに失敗しました";
			return false;
		}
	}
}
