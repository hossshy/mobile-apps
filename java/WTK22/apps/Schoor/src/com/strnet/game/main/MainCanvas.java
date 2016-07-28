/*
 * Last modified: 2008/12/15 20:15:09
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.Counter;
import com.strnet.game.common.SpriteData;
import com.strnet.game.common.Point;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.BevelRectPainter;
import com.strnet.game.component.CheapPainter;
import com.strnet.game.component.MenuWindow;
import com.strnet.game.component.MessageWindow;

import java.util.Vector;

public class MainCanvas extends AbstractCanvas
{
	private static final String URL = "http://m.strnet.com/";
	private static final String RECORD_KEY = "Schoor";

	static final int S_INIT = 0;
	static final int S_TITLE = 1;
	static final int S_PLAY = 2;
	static final int S_MANUAL = 3;
	static final int S_STORY = 4;
	static final int S_SCENE_MOVE = 5;
	
	static final int MAX_IMAGE = 22;
	//static final int[] DEF_CUR_SPEED = new int[]{3, 5, 7, 9, 11, 13};
	static final int[] DEF_CUR_SPEED = new int[]{2, 2, 3, 4, 5, 7, 9, 12};
	static final AllowData[] ALLOWS_DATA = new AllowData[4];

	int commandNum = -1;

	int detailItemX, detailItemY;
	MenuWindow titleMenu;
	int titleCounter = 0;
	MenuWindow playMenu;

	MessageWindow messageWindow;
	MessageWindow storyMessageWindow;

	CharacterData[] items;

	CharacterData cursor;
	CharacterData detailItemWindow;
	CharacterData useItemWindow;
	CharacterData itemWindow;
	CharacterData itemLeftCursor;
	CharacterData itemRightCursor;
	ItemBox itemBox;

	ItemData useItem = null;
	ItemData selectedItem = null;
	CharacterData detailItem = null;
	CharacterData detailNoItem = null;
	int roomId = 0;
	int ending = -1;
	boolean showMyItem;
	boolean initialized = false;
	int initCounter = 255;
	boolean syncFlag = false;
	
	StoryData storyData;
	int backScene;
	int nextScene;
	int sceneMoveCounter;
	//Color playMenuCursor = new Color(255,0,0);
	
	String msg = null;
	
	public MainCanvas(Main app)
	{
		super(app);
	}
	
	void init()
	{
		super.init(new String[]{"ﾒﾆｭｰ", "ｱｲﾃﾑ",  "閉", "持つ", "", "Web", "ﾀｲﾄﾙ", "戻る"});
		removeCommands();
		initImage(MAX_IMAGE);
		scene = S_INIT;
		setFont(S_FONT_MEDIUM);

		messageWindow = new MessageWindow(new Rectangle(30, 180, 180, 80), wideFontWidth, fontHeight, wordSpace);
		BevelRectPainter painter = new BevelRectPainter();
		painter.setNormalColor(new Color(50, 50, 50));
		painter.setBorderWidth(1);
		messageWindow.setBackground(painter);

		storyMessageWindow = new MessageWindow(new Rectangle(30, 160, 180, 80), wideFontWidth, fontHeight, wordSpace);
		CheapPainter cPainter= new CheapPainter();
		storyMessageWindow.setBackground(cPainter);
		
		titleMenu = new MenuWindow(1, 4, false);
		playMenu = new MenuWindow(1, 3, false);
		playMenu.setVisible(false);
		
		itemLeftCursor = new CharacterData(42, 25, 18, 30, 1, new Rectangle(46,0,18,30));
		itemWindow = new CharacterData(60, 25, 126, 30, 1, new Rectangle(64,0,126,30));
		itemRightCursor = new CharacterData(186, 25, 18, 30, 1, new Rectangle(190,0,18,30));
		useItemWindow = new CharacterData(204, 6, 30, 30, 1, new Rectangle(16,0,30,30));

		detailItemWindow = new CharacterData(60, 60, 120, 120, 1, new Rectangle(0,30,126,126));
		detailItemX = detailItemWindow.x + 3;
		detailItemY = detailItemWindow.y + 3;

		int area = 20;
		ALLOWS_DATA[AllowData.UP] = new AllowData(1, 0,0,240,area, new Rectangle(56,156,24,16));
		ALLOWS_DATA[AllowData.UP].imgDest = new Point(108, 4);
		ALLOWS_DATA[AllowData.DOWN] = new AllowData(1, 0,240 - area,240,area, new Rectangle(32,156,24,16));
		ALLOWS_DATA[AllowData.DOWN].imgDest = new Point(108, 240 - area);
		ALLOWS_DATA[AllowData.LEFT] = new AllowData(1, 0,0,area,240, new Rectangle(0,156,16,24));
		ALLOWS_DATA[AllowData.LEFT].imgDest = new Point(4, 108);
		ALLOWS_DATA[AllowData.RIGHT] = new AllowData(1, 240 - area,0,area,240, new Rectangle(16,156,16,24));
		ALLOWS_DATA[AllowData.RIGHT].imgDest = new Point(240 - area, 108);


		for ( int i = 0; i <= MAX_IMAGE; i++ )
		{
			try
			{
				switch ( i )
				{
				case 7:
				case 9:
				case 20:
				case 22:
					continue;
				}
				
				loadImage(i);
				if ( "s".equals(getCarrer()) )
				{
					releaseImage(i);
				}
				loadedCounter++;
			}
			catch ( Exception ignore )
			{
				//msg = ignore +"";
				//break;
			}
		}

		if ( isRecord(RECORD_KEY) )
		{
			titleMenu.down();
		}
		//setScene(S_TITLE);
		System.out.println(loadedCounter);
		
		initialized = true;
	}
	
	void reset()
	{
		try
		{
			releaseImage(9);
		}
		catch ( Exception e ) {e.printStackTrace();}
		
		playMenu.setVisible(false);
		playMenu.resetId();
		cursor = new CharacterData(120, 120, 16, 16, 1, new Rectangle(0,0,16,16));
		itemBox = new ItemBox();
		items = Data.make();
		clearWindow();
		useItem = null;
		selectedItem = null;
		detailItem = null;
		detailNoItem = null;
		showMyItem = false;
		goNextRoom(0);
	}

	void doAction()
	{
		try
		{
			execCommandAction();
			switch ( scene )
			{
			case S_PLAY:
				
				if ( playMenu.isVisible() )
				{
					if ( getKeyEvent() == S_KEY_FIRE )
					{
						saveData();
						switch ( playMenu.getId() )
						{
						case 0:
							clearWindow();
							playMenu.setVisible(false);
							changeCommand();
							break;
						case 1:
							terminate();
							break;
						case 2:
							setScene(S_TITLE);
							break;
						}
					}
					else
					{
						playMenu.move(this);
					}
					keyReset();
				}
				else
				{
					checkKey();
				}
				doPaint();
				if ( playMenu.isVisible() )
				{
					drawImage(1, 0, 30, 60, 60, 126, 126);
					//setColor(playMenuCursor);
					//drawRect(40+31, 40+15+(46*playMenu.getId()), 81,20);
					for ( int i = 0; i < 3; i++ )
					{
						drawImage(1, 130, 94+(18*i), 60+26, 70+(45*i), 76, 17);
					}
					drawImage(1, 130, 94+55+(18*playMenu.getId()), 60+26, 70+(45*playMenu.getId()), 76, 17);
				}
				break;

			case S_TITLE:
				execTitle();
				break;
				
			case S_STORY:
				execStory();
				break;
				
			case S_SCENE_MOVE:
				switch ( backScene )
				{
				case S_TITLE:
					doPaintTitle();
					break;
				case S_STORY:
					doPaintStory();
					break;
				case S_MANUAL:
					doPaintManual();
					break;
				case S_PLAY:
					doPaint();
					break;
				}
				
				sceneMoveCounter++;
				int frame = 5;

				setColor(0,0,0);

				if ( sceneMoveCounter >= (frame*2 - 1) )
				{
					scene = nextScene;
					changeCommand();
				}
				else if ( sceneMoveCounter >= frame ) // 5~8
				{
					backScene = nextScene;
					for ( int i = 0; i < (240 / frame); i++ )
					{
						fillRect(0, i * frame + (sceneMoveCounter - frame), 240, (frame*2) - sceneMoveCounter);
					}
				}
				else// 1~4
				{
					for ( int i = 0; i < (240 / frame); i++ )
					{
						fillRect(0, i * frame, 240, sceneMoveCounter);
					}
				}

				
				break;
				
			case S_MANUAL:
				execManual();
				break;

			case S_INIT:
				if ( initialized )
				{
					//setWaitTime(50);
					setScene(S_TITLE);
				}
				/*
				  int c = (initCounter > 0) ? initCounter : 0;
				  setColor(c,c,c);
				  fillAll();
				*/
				drawTitle(70, 100);
				/*
				  setColor(255,255,255);
				  drawString("Please wait...", 10, 10);
				*/
				setColor(255,255,0);
				fillRect(10, 230, loadedCounter * 5, 1);
				break;
			}
			
			if ( scene != S_PLAY && scene != S_STORY )
			{
				keyReset();
			}

			if ( msg != null )
			{
				setColor(255,255,255);
				drawString(msg, 0, 0);
			}
			System.gc();
		}catch ( Exception e ) {e.printStackTrace();setColor(255,255,255);drawString(e.getMessage(),0,0);}
	}

	void execTitle() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			switch ( titleMenu.getId() )
			{
			case 0:
				reset();
				setStoryData(Data.getStoryCode(0));
				storyData.next(this);
				break;
			case 1:
				reset();
				if ( isRecord(RECORD_KEY) )
				{
					loadData();
					setScene(S_PLAY);
				}
				else
				{
					setStoryData(Data.getStoryCode(0));
					storyData.next(this);
				}
				break;
			case 2:
				loadImage(9);
				setScene(S_MANUAL);
				break;
			case 3:
				terminate();
				break;
			}
		}
		else
		{
			titleMenu.move(this);
		}
		doPaintTitle();
	}
	
	void doPaintTitle()
	{
		drawImage(0, 0, 70);
		
		int i = 0;
		titleCounter++;
		if ( titleCounter < 8 )
		{
			i = (titleCounter / 2);
		}
		else if ( titleCounter < 13 )
		{
			i = 6 - (titleCounter / 2);
		}
		else if ( titleCounter > 45 )
		{
			titleCounter = 0;
		}
		drawImage(21,0,i * 52, 27,26,180,52);
		
		drawImage(18,0,0, 114, 115, 92,108);
		drawImage(18, 93,(27*titleMenu.getId()), 114, 115 + (27*titleMenu.getId()),92,27);
	}

	void execManual()
	{
		doPaintManual();
	}

	void doPaintManual()
	{
		drawImage(9, 0, 0);
	}


	void checkKey()
	{
		if ( isUp() || isDown() || isLeft() || isRight() )
		{
			if ( cursor.speed < DEF_CUR_SPEED.length - 1 )
			{
				cursor.speed++;
			}
			
			clearWindow();
		}
		else
		{
			cursor.speed = 0;
		}
		if ( isUp() ) cursor.y -= DEF_CUR_SPEED[cursor.speed];
		if ( isDown() ) cursor.y += DEF_CUR_SPEED[cursor.speed];
		if ( isLeft() ) cursor.x -= DEF_CUR_SPEED[cursor.speed];
		if ( isRight() ) cursor.x += DEF_CUR_SPEED[cursor.speed];

		if ( cursor.x < 0 ) cursor.x=0;
		else if ( cursor.x > 240 - cursor.width ) cursor.x= 240 - cursor.width;
		if ( cursor.y < 0 ) cursor.y=0;
		else if ( cursor.y > 240 - cursor.height ) cursor.y=240 - cursor.height;

		if ( getKeyEvent() == S_KEY_FIRE )
		{
			if ( clearWindow() )
			{
				keyReset();
				return;
			}

			boolean evt = true;
			//allow
			if ( !showMyItem )
			{
				for ( int i = 0; i < ALLOWS_DATA.length; i++ )
				{
					if ( (ALLOWS_DATA[i] != null) && (ALLOWS_DATA[i].state == AllowData.STATE_SHOW) )
					{
						if ( cursor.isHitLeftTop(ALLOWS_DATA[i]) )
						{
							goNextRoom(ALLOWS_DATA[i].nextRoomId);
							evt = false;
							break;
						}
					}
				}
			}
			
			if ( evt )
			{
				//event
				for ( int i = items.length - 1; i >= 0; i-- )
				{
					if ( (showMyItem == ((items[i].attribute == CharacterData.ATTR_SHOW_MYITEM)||(items[i].attribute == CharacterData.ATTR_SHOW_MYITEM_DETAIL))) &&
						 isShow(i) && cursor.isHitLeftTop(items[i]))
					{
						event(items[i]);
						break;
					}
				}
			}
		}
		keyReset();
	}
	
	void doPaint()
	{
		for ( int i = 0; i < items.length; i++ )
		{
			//if (isShow(i) && items[i].imageId >= 0 && items[i].attribute != CharacterData.ATTR_SHOW_MYITEM)
			if (isShow(i) && items[i].imageId >= 0 )
			{
				drawChara(items[i]);
			}
		}
		

		if ( !showMyItem )
		{
			for ( int i = 0; i < ALLOWS_DATA.length; i++ )
			{
				if ( ALLOWS_DATA[i].state == AllowData.STATE_SHOW )
				{
					if ( cursor.isHitLeftTop(ALLOWS_DATA[i]) )
					{
						drawChara(ALLOWS_DATA[i]);
						break;
					}
				}
			}

			if ( detailNoItem != null )
			{
				drawChara(detailItemWindow);
				drawChara(detailNoItem);
			}
		}

		drawChara(useItemWindow);
		drawChara(useItem, useItemWindow.x + 3, useItemWindow.y + 3);
		drawChara(cursor);
		messageWindow.paint(this);

		//msg = "FPS: "+showFps;
	}

	private void drawChara(CharacterData cd)
	{
		if ( cd != null )
		{
			drawImage(cd.imageId, cd.imgSrc.x, cd.imgSrc.y, cd.getDestX(), cd.getDestY(), cd.imgSrc.width, cd.imgSrc.height);
		}
	}
	
	private void drawChara(CharacterData cd, int x, int y)
	{
		if ( cd != null )
		{
			drawImage(cd.imageId, cd.imgSrc.x, cd.imgSrc.y, x, y, cd.imgSrc.width, cd.imgSrc.height);
		}
	}
	
	void execCommandAction()
	{
		boolean exec = false;
		switch ( commandNum )
		{
		case 0:
			//terminate();
			clearWindow();
			playMenu.setVisible(true);
			changeCommand();
			exec = true;
			break;
		case 1:
			clearWindow();
			showMyItem = true;
			itemBox.updateViewItems();
			changeCommand();
			exec = true;
			break;
		case 2:
			clearWindow();
			hideItems();
			changeCommand();
			exec = true;
			break;
		case 3:
			if ( selectedItem != null )
			{
				useItem = selectedItem;
			}
			exec = true;
			break;
		case 5:
			browser(URL);
			exec = true;
			break;
		case 6:
			setScene(S_TITLE);
			changeCommand();
			exec = true;
			break;
		case 7:
			clearWindow();
			playMenu.setVisible(false);
			changeCommand();
			exec = true;
			break;
		}
		if ( exec )
		{
			commandNum = -1;
			keyReset();
		}
	}

	void doCommandAction(int i)
	{
		commandNum = i;
	}

	void changeCommand()
	{
		if ( scene == S_PLAY )
		{
			if ( showMyItem )
			{
				setSoftKey(2, 3);
			}
			else if ( playMenu.isVisible() )
			{
				setSoftKey(7, -1);
			}
			else
			{
				setSoftKey(0, 1);
			}
		}
		else if ( scene == S_MANUAL )
		{
			setSoftKey(6, -1);
		}
		else if ( scene == S_TITLE )
		{
			setSoftKey(5, -1);
		}
		else
		{
			setSoftKey(4, -1);
		}
	}

	public void fireKeyReleased()
	{
	}

	public void goNextRoom(int roomId)
	{
		this.roomId = roomId;

		if ( "s".equals(getCarrer()) )
		{
			for ( int i = 0; i <= MAX_IMAGE; i++ )
			{
				try
				{
					switch ( i )
					{
					case 1:
					case 2:
					case 3:
					case 14:
						continue;
					}
					releaseImage(i);
				}
				catch ( Exception ignore )
				{
					//msg = ignore +"";
					//break;
				}
			}
		}
		
		for ( int i = 0; i < ALLOWS_DATA.length; i++ )
		{
			int next = RoomData.get(roomId, i);
			if ( next == -1 )
			{
				ALLOWS_DATA[i].state = AllowData.STATE_HIDE;
			}
			else
			{
				ALLOWS_DATA[i].state = AllowData.STATE_SHOW;
				ALLOWS_DATA[i].nextRoomId = next;
			}
		}
	}
	
	public void hideItems()
	{
		if ( detailItem != null )
		{
			detailItem.state = CharacterData.STATE_HIDE;
			detailItem = null;
		}
		showMyItem = false;
	}

	public boolean isShow(int i)
	{
		CharacterData d = items[i];
		
		boolean r = ((d.rootId == -1) || (search(d.rootId).state == CharacterData.STATE_SHOW));
		if ( d.state == CharacterData.STATE_SHOW )
		{
			switch ( d.attribute )
			{
			case CharacterData.ATTR_SHOW_NORMAL:
				return (d.roomId == roomId) && r;
			case CharacterData.ATTR_SHOW_ALL:
				return  (d.roomId == roomId) && r;
			case CharacterData.ATTR_SHOW_MYITEM:
				return showMyItem && r;
			case CharacterData.ATTR_SHOW_MYITEM_DETAIL:
				return showMyItem && r;
			}
		}
		return false;
	}

	public boolean turnShowMyItem()
	{
		showMyItem = !showMyItem;
		return showMyItem;
	}

	public CharacterData search(String id)
	{
		return search(Integer.parseInt(id));
	}

	public CharacterData search(int id)
	{
		for ( int i = 0; i < items.length; i++ )
		{
			if ( items[i].id == id )
				return items[i];
		}
		return null;
	}
	
	boolean clearWindow()
	{
		boolean ret = messageWindow.clear();
		if ( detailNoItem != null )
		{
			detailNoItem.state = ItemData.STATE_HIDE;
			detailNoItem= null;
		}
		return ret;
	}

	public void event(CharacterData cd)
	{
		if ( cd.listNode != null )
		{
			execEvent(cd.listNode);
		}
	}
	
	public void execEvent(CommandListNode list)
	{
		for ( int i = 0; i < list.commands.size(); i++ )
		{
			Node node = (Node)list.commands.elementAt(i);
			CommandListNode execNode = null;
			if ( node instanceof IfNode )
			{
				execNode = execIf((IfNode) node);
			}
			else if ( node instanceof CommandListNode )
			{
				execNode = (CommandListNode) node;
			}
			else
			{
				CommandNode n = (CommandNode) node;
				set(n);
			}
		
			if ( execNode != null )
			{
				for ( int j = 0; j < execNode.commands.size(); j++ )
				{
					CommandNode n = (CommandNode) execNode.commands.elementAt(j);
					set(n);
				}
			}
		}
	}
	
	public CommandListNode execIf(IfNode node)
	{
		if ( node == null )
		{
			return null;
		}

		boolean flag = true;
		if ( node.ifCommand != null )
		{
			Vector v = node.ifCommand.commands;
			for ( int i = 0; i < v.size(); i++ )
			{
				CommandNode n = (CommandNode) v.elementAt(i);
				flag &= check(n);
			}
		}
		if ( flag )
		{
			return node.execCommand;
		}
		else
		{
			return execIf(node.elseNode);
		}
	}
	
	void setDetailNoItem(int id)
	{
		if ( detailNoItem != null )
		{
			detailNoItem.state = ItemData.STATE_HIDE;
		}
		if ( detailItem != null )
		{
			detailItem.state = ItemData.STATE_HIDE;
			detailItem = null;
		}

		detailNoItem = search(id);
		detailNoItem.x = detailItemWindow.x + 3;
		detailNoItem.y = detailItemWindow.y + 3;
		detailNoItem.state = ItemData.STATE_SHOW;
	}

	ItemData addItem(String id)
	{
		ItemData d = (ItemData)search(id);
		itemBox.add(d);
		return d;
	}
	
	void removeItem(String id)
	{
		ItemData d = (ItemData)search(id);
		d.state = ItemData.STATE_HIDE;
		itemBox.remove(d);
		if ( d == useItem )
		{
			useItem = null;
		}
		selectedItem = null;
		itemBox.updateViewItems();
	}
	
	public void set(CommandNode node)
	{
		if ( "item".equals(node.command) )
		{
			ItemData d = addItem(node.arg);
			if ( d.detailId >= 0 )
			{
				setDetailNoItem(d.detailId);
			}
			itemBox.updateViewItems();
		}
		else if ( "detailnoitem".equals(node.command) )
		{
			setDetailNoItem(Integer.parseInt(node.arg));
		}
		else if ( "delitem".equals(node.command) )
		{
			removeItem(node.arg);
		}
		else if ( "msg".equals(node.command) )
		{
			messageWindow.setMessage(node.arg);
		}
		else if ( "show".equals(node.command) )
		{
			search(node.arg).state = CharacterData.STATE_SHOW;
		}
		else if ( "hide".equals(node.command) )
		{
			search(node.arg).state = CharacterData.STATE_HIDE;
		}
		else if ( "go".equals(node.command) )
		{
			goNextRoom(Integer.parseInt(node.arg));
		}
		else if ( "story".equals(node.command) )
		{
			int id = Integer.parseInt(node.arg);
			setStoryData(Data.getStoryCode(id));
		}
		else if ( "ending".equals(node.command) )
		{
			ending = Integer.parseInt(node.arg);
		}
		else if ( "detail".equals(node.command) )
		{
			if ( detailItem != null )
			{
				detailItem.state = ItemData.STATE_HIDE;
			}
			detailItem = search(node.arg);
			detailItem.x = detailItemX;
			detailItem.y = detailItemY;
			detailItem.state = ItemData.STATE_SHOW;
		}
		else if ( "select".equals(node.command) )
		{
			selectedItem = (ItemData)search(node.arg);
		}
		else if ( "event".equals(node.command) )
		{
			int id = Integer.parseInt(node.arg);
			if ( id == 1 )
			{
				itemBox.left();
			}
			else if ( id == 2 )
			{
				itemBox.right();
			}
			else if ( id == 30 )
			{
				if ( search(-501).state == CharacterData.STATE_HIDE )
				{
					boolean flag = true;
					flag &= search(721).state == CharacterData.STATE_SHOW;
					flag &= search(722).state == CharacterData.STATE_HIDE;
					flag &= search(723).state == CharacterData.STATE_HIDE;
					flag &= search(724).state == CharacterData.STATE_SHOW;
					flag &= search(725).state == CharacterData.STATE_HIDE;
					flag &= search(726).state == CharacterData.STATE_SHOW;
					flag &= search(727).state == CharacterData.STATE_SHOW;
					flag &= search(728).state == CharacterData.STATE_HIDE;
					flag &= search(729).state == CharacterData.STATE_SHOW;
					if ( flag )
					{
						search(452).state = CharacterData.STATE_SHOW;//天井仮面
						//search(501).state = CharacterData.STATE_SHOW;//箱
						//search(-501).state = CharacterData.STATE_SHOW;//箱フラグ
						
						messageWindow.setMessage("何か物音が聞こえた");
					}
				}
			}
			else if ( id == 48 ) // 取って
			{
				if ( isAllShow(new int[]{2243,2252,2263,2279}) || isAllShow(new int[]{2246,2257,2264,2271}) )
				{
					messageWindow.setMessage("RECTかよ");
				}
				else if ( isAllShow(new int[]{2244,2257,2261,2278}) )
				{
					addItem("1107");
					itemBox.updateViewItems();
					detailItem.state = ItemData.STATE_HIDE;
					setDetailNoItem(2290);
					removeItem("1106");
					messageWindow.setMessage("鍵が出てきた！");
				}
				else if ( isAllShow(new int[]{2249,2250,2265,2274}) )
				{
					boolean flag = true;
					flag &= search(721).state == CharacterData.STATE_HIDE;
					flag &= search(722).state == CharacterData.STATE_HIDE;
					flag &= search(723).state == CharacterData.STATE_HIDE;
					flag &= search(724).state == CharacterData.STATE_HIDE;
					flag &= search(725).state == CharacterData.STATE_SHOW;
					flag &= search(726).state == CharacterData.STATE_HIDE;
					flag &= search(727).state == CharacterData.STATE_HIDE;
					flag &= search(728).state == CharacterData.STATE_HIDE;
					flag &= search(729).state == CharacterData.STATE_HIDE;
					if ( flag )
					{
						setStoryData(Data.getStoryCode(3));
					}
					else
					{
						messageWindow.setMessage("開かない…");
					}
				}
				else
				{
					messageWindow.setMessage("開かない…");
				}
			}
			else if ( id == 49 ) // 箱裏電池
			{
				for ( int i = 0 ; i < 4; i++ )
				{
					search(2240 + (i*10)).state = ItemData.STATE_SHOW;
				}
			}
			else if ( id >= 50 && id <= 53 ) // 数字ボタン
			{
				int first = 2240 + ((3 - (53 - id)) * 10);
				int now = 0;
				for ( int i = first; i < first+10; i++ )
				{
					if ( search(i).state == ItemData.STATE_SHOW )
					{
						now = i;
					}
					search(i).state = ItemData.STATE_HIDE;
				}
				int next = GameUtil.loopIncf(now, first, first+9);
				
				search(next).state = ItemData.STATE_SHOW;
			}
		}
		else if ( "additemonly".equals(node.command) )
		{
			ItemData d = addItem(node.arg);
			itemBox.updateViewItems();
		}
		else
		{
			throw new IllegalArgumentException(node.command);
		}
	}
	
	private boolean isAllShow(int[] ids)
	{
		for ( int i = 0; i < ids.length; i++ )
		{
			if ( search(ids[i]).state == CharacterData.STATE_HIDE )
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean check(CommandNode node)
	{
		if ( "item".equals(node.command) )
		{
			return ((useItem != null) &&
					(useItem.id == Integer.parseInt(node.arg)));
		}
		else if ( "show".equals(node.command) )
		{
			return search(node.arg).state == CharacterData.STATE_SHOW;
		}
		else if ( "hide".equals(node.command) )
		{
			return search(node.arg).state == CharacterData.STATE_HIDE;
		}
		else
		{
			throw new IllegalArgumentException(node.command);
		}
	}
	
	public void setStoryData(String code)
	{
		try
		{
			storyData = new StoryData(code, storyMessageWindow);
			setScene(S_STORY);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public void execStory() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			storyData.next(this);
			keyReset();
		}
		doPaintStory();
	}
	
	public void doPaintStory()
	{
		storyData.paint(this);
	}
	
	void setScene(int next)
	{
		this.backScene = scene;
		this.nextScene = next;
		scene = S_SCENE_MOVE;
		sceneMoveCounter = 0;
		changeCommand();
	}
	
	void loadData()
	{
		try
		{
			if ( !isRecord(RECORD_KEY) )
			{
				return;
			}

			String tmp = load(RECORD_KEY);
			if ( tmp == null )
			{
				return;
			}
			String[] line = GameUtil.split(tmp, ',');
			String[] s;
			int lineCount = 0;
			s = GameUtil.split(line[lineCount++], ':');
			cursor.x = Integer.parseInt(s[0]);
			cursor.y = Integer.parseInt(s[1]);
			if ( !"".equals(s[2]) )
			{
				useItem = (ItemData) search(s[2]);
			}
			goNextRoom(Integer.parseInt(s[3]));

			s = GameUtil.split(line[lineCount++], ':');
			for ( int i = 0; i < s.length; i++ )
			{
				String[] itemState = GameUtil.split(s[i], '.');
				CharacterData d = search(itemState[0]);
				d.state = Integer.parseInt(itemState[1]);
			}
			

			s = GameUtil.split(line[lineCount++], ':');
			itemBox.viewStart = Integer.parseInt(s[0]);
			for ( int i = 1; i < s.length; i++ )
			{
				ItemData d = (ItemData) search(s[i]);
				itemBox.add(d);
			}
			itemBox.updateViewItems();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			messageWindow.setMessage("ロードに失敗しました");
		}
	}
	
	void saveData()
	{
		StringBuffer saveData = new StringBuffer();
		saveData.append(this);
		saveData.append(',');
		for ( int i = 0; i < items.length; i++ )
		{
			saveData.append(items[i]);
			if ( i < items.length - 1 )
			{
				saveData.append(":");
			}
		}
		
		saveData.append(',');
		saveData.append(itemBox);
		
		try
		{
			save(RECORD_KEY, saveData.toString());
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			messageWindow.setMessage("セーブに失敗しました");
		}
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(cursor.x);
		sb.append(':');
		sb.append(cursor.y);
		sb.append(':');
		if ( useItem != null )
		{
			sb.append(useItem.id);
		}
		sb.append(':');
		sb.append(roomId);
		return sb.toString();
	}
}
