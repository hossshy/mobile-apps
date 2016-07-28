/*
 * Last modified: 2010/08/09 23:57:41
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.SaveDataCreator;
import com.strnet.game.common.Point;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.component.BevelRectPainter;
import com.strnet.game.component.CheapPainter;
import com.strnet.game.component.MenuManager;
import com.strnet.game.component.MenuWindow;
import com.strnet.game.component.MessageWindow;
import com.strnet.game.component.NumberImage;
import com.strnet.game.component.StrongString;
import com.strnet.game.interpreter.CommandNode;
import com.strnet.game.interpreter.Interpreter;
import com.strnet.game.interpreter.InterpreterEvent;

import java.util.Vector;

public class MainCanvas extends GameCommonCanvas implements InterpreterEvent
{
	public static final String RECORD_KEY = "RECT2ex";
	public static final int MAX_IMAGE = 65;

	static final int TITLE_IMAGE = 0;
	public static final int[] MANUAL_PAGES = new int[] {2};
	static final AllowData[] ALLOWS_DATA = new AllowData[4];
	static final String[] TITLE_MENU = new String[]
		{"始めから", "続きから", "マニュアル", "オプション", "終了"};
		//		{"New Game", "Load Game", "Manual", "Option", "Exit"};
	static final String[] PLAY_MENU = new String[]
		{"保存して戻る", "保存して終了", "保存してタイトル"};
	static final int[] REQUIRE_PLAY_IMAGES = new int[] {1, 4};

	MenuWindow titleMenu;
	MenuWindow optionMenu;
	MenuWindow bgmVolumeMenu;

	MessageWindow messageWindow;
	MessageWindow storyMessageWindow;

	CharacterData[] items;

	CursorData cursor;
	CharacterData detailItemWindow;
	CharacterData useItemWindow;
	ItemBox itemBox;

	ItemData useItem = null;
	ItemData selectedItem = null;
	CharacterData detailItem = null;
	CharacterData detailNoItem = null;
	boolean showMyItem;
	
	StoryData storyData;
	EndingData ending;
	MenuWindow playMenu;

	int roomId = 0;
	boolean clearFlag = false;
	Interpreter program;
    boolean opYuurei = false;

	public MainCanvas(Main app)
	{
		super(app, S_TITLE, MAX_IMAGE,REQUIRE_PLAY_IMAGES);
	}
	
	void init()
	{
		String webLabel = Main.isDxMode() ? "" : "WEB";
		super.init(new String[]{"♪○", "♪×", webLabel, "ﾀｲﾄﾙ", "ﾒﾆｭｰ","ｱｲﾃﾑ", "閉", "持つ", ""}, MANUAL_PAGES);
		setFont(S_FONT_SMALL);
		program = new Interpreter(this);

		titleMenu = new MenuWindow(1, 5, false);
		setSEVolumeLevel(4);



		messageWindow = new MessageWindow(new Rectangle(30, 180, 180, 80), wideFontWidth, fontHeight, wordSpace);
		BevelRectPainter painter = new BevelRectPainter();
		painter.setNormalColor(new Color(50, 50, 50));
		painter.setBorderWidth(1);
		messageWindow.setBackground(painter);

		storyMessageWindow = new MessageWindow(new Rectangle(30, 160, 180, 80), wideFontWidth, fontHeight, wordSpace);
		CheapPainter cPainter= new CheapPainter();
		storyMessageWindow.setBackground(cPainter);
		
		playMenu = new MenuWindow(1, 3, false);
		playMenu.setVisible(false);
		optionMenu = new MenuWindow(1, 2, true);
		bgmVolumeMenu = new MenuWindow(5, 1, false);
		
		useItemWindow = new CharacterData(204, 6, 30, 30, 1, new Rectangle(16,0,30,30));

		detailItemWindow = new CharacterData(60, 60, 120, 120, 1, new Rectangle(0,30,126,126));


		// 上下左右矢印
		int area = 20;
		ALLOWS_DATA[AllowData.UP] = new AllowData(1, 0,0,240,area, new Rectangle(56,156,24,16));
		ALLOWS_DATA[AllowData.UP].imgDest = new Point(108, 4);
		ALLOWS_DATA[AllowData.DOWN] = new AllowData(1, 0,240 - area,240,area, new Rectangle(32,156,24,16));
		ALLOWS_DATA[AllowData.DOWN].imgDest = new Point(108, 240 - area);
		ALLOWS_DATA[AllowData.LEFT] = new AllowData(1, 0,0,area,240, new Rectangle(0,156,16,24));
		ALLOWS_DATA[AllowData.LEFT].imgDest = new Point(4, 108);
		ALLOWS_DATA[AllowData.RIGHT] = new AllowData(1, 240 - area,0,area,240, new Rectangle(16,156,16,24));
		ALLOWS_DATA[AllowData.RIGHT].imgDest = new Point(240 - area, 108);



		if ( isRecord(RECORD_KEY) )
		{
			loadSystem();
			titleMenu.down();
            opYuurei = true;
		}

		initialized = true;
	}

	
	void reset()
	{
		playMenu.setVisible(false);
		playMenu.resetId();
		cursor = new CursorData(120, 120, 16, 16, 1, new Rectangle(0,0,16,16));
		itemBox = new ItemBox();
		items = Data.make();
		clearWindow();
		useItem = null;
		selectedItem = null;
		detailItem = null;
		detailNoItem = null;
		showMyItem = false;

		ending = new EndingData(fontHeight);
		itemBox.updateViewItems();
		goNextRoom(0);
		resetImages();
	}

/////////// execXXX
/*
  public void execTitle() throws Exception
  {
  reset();
  setStoryData(Data.getStoryCode(0));
  storyData.next(this);
  }
*/

	public void execTitle() throws Exception
	{

		if ( getKeyEvent() == S_KEY_FIRE )
		{
			try
			{
				startSE(0);
			}
			catch ( Exception e ) {}
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
					load();
					setScene(S_PLAY);
				}
				else
				{
					setStoryData(Data.getStoryCode(0));
					storyData.next(this);
				}
				break;
			case 2:
				setScene(S_MANUAL);
				break;
			case 3:
				setScene(S_OPTION);
				break;
			case 4:
				terminate();
				break;
			}
			keyReset();
		}
		else
		{
			titleMenu.move(this);
			keyReset();
		}

		doPaintTitle();
	}

	public void doPaintPressFireKeyDetail()
	{
		drawImage(0,0,0);
	}

	public void execOption() throws Exception
	{
		int ev = getKeyEvent();
		if ( (ev == S_KEY_UP) || (ev == S_KEY_DOWN) )
		{
			optionMenu.move(this);
			keyReset();
		}
		else if ( (ev == S_KEY_LEFT) || (ev == S_KEY_RIGHT) )
		{
			int option = optionMenu.getId();
			switch ( option )
			{
			case 0://BGM
				bgmVolumeMenu.move(this);
				setSEVolumeLevel(bgmVolumeMenu.getId());
				break;
			}
			keyReset();
		}
		else if ( ev == S_KEY_FIRE )
		{
			if ( optionMenu.getId() == (optionMenu.getMaxRow() - 1) )
			{
				setScene(S_TITLE);
			}
			keyReset();
		}

		doPaintOption();
	}

	public void doPaintOption() throws Exception
	{
		resetScreen();
		int x = 35;
		int y = 55;
		int index = 0;
		int span = 15;
		setColor(Color.WHITE);
		StringBuffer sb = new StringBuffer();
		sb.append("効果音音量 ");
		
		for ( int i = 0; i < bgmVolumeMenu.getId(); i++ )
		{
			sb.append("> ");
		}
		drawSelectedMenu(index++, x, y, optionMenu, sb.toString());
		y+=span;

		drawSelectedMenu(index++, x, y, optionMenu, "設定");
	}

	private void drawSelectedMenu(int index, int x, int y, String[] labels, MenuWindow menu)
	{
		drawSelectedMenu(index, x, y, menu, labels[index]);
	}

	private void drawSelectedMenu(int index, int x, int y, MenuManager menu)
	{
		drawSelectedMenu(index, x, y, menu, menu.getLabel(index));
	}

	private void drawSelectedMenu(int index, int x, int y, MenuWindow menu, String label)
	{
		if ( menu.getId() == index )
		{
			setColor(0,255,0);
			drawString("*", x - wideFontWidth - 2, y);
			// カーソル
			//drawImage(0, 80,23, x - 16,y, 12,12);
		}
		else
		{
			//setColor(255,249,201);
			setColor(Color.WHITE);
		}
		drawString(label, x, y);
	}

	public void doPaintTitle()
	{
		drawImage(0,0,0);
        
        if ( opYuurei ) {
            drawImage(52, 0,0, 140,10, 92, 229);
        }
		int x = 90;
		int y = 100;
		for ( int i = 0; i < TITLE_MENU.length; i++ )
		{
			drawTitleMenu(i, x, y);
			y += 18;
		}
		setColor(Color.RED);
		
		titleString.paint(this, "Copyright (C) 2010 STR", 50, 205);
		titleString.paint(this, " http://m.strnet.com/", 50, 220);
	}
	
	private StrongString titleString = new StrongString(new Color(255,50,50), Color.BLACK);
	private StrongString unselectString = new StrongString(new Color(110,60,60), Color.BLACK);
	
	private void drawTitleMenu(int index, int x, int y)
	{
		if ( titleMenu.getId() == index )
		{
			setColor(0,255,0);
			//titleString.paint(this, "*", x - fontWidth - 2, y);
			drawImage(37, 45,0, x - 16, y + 1, 10, 10);
			titleString.paint(this, TITLE_MENU[index], x, y);
		}
		else
		{
			//setColor(255,255,255);
			setColor(255,0,0);
			unselectString.paint(this, TITLE_MENU[index], x, y);
		}
	}


	public void execPlay() throws Exception
	{
		if ( playMenu.isVisible() )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				save();
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
		doPaintPlay();

	}

	public void doPaintPlay()
	{
		for ( int i = 0; i < items.length; i++ )
		{
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

		if ( playMenu.isVisible() )
		{
			drawImage(1, 0, 30, 60, 60, 126, 126);
			int x = 78;
			int y = 92;
			for ( int i = 0; i < PLAY_MENU.length; i++ )
			{
				drawPlayMenu(i, x, y);
				y += 20;
			}
		}
	}


	private void drawPlayMenu(int index, int x, int y)
	{
		if ( playMenu.getId() == index )
		{
			setColor(0,155,0);
			drawString("*", x - fontWidth - 2, y);
		}
		else
		{
			setColor(0,0,0);
		}
		drawString(PLAY_MENU[index], x, y);
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
	
	public void doPaintStory() throws Exception
	{
		storyData.paint(this);
	}

	public void execEnding() throws Exception
	{
		if ( !ending.next() )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				setScene(S_TITLE);
			}
		}
		doPaintEnding();
	}
	
	public void doPaintEnding()
	{
		ending.paint(this);
	}

	
	public void doClear() throws Exception
	{
		reset();
		clearFlag = true;
		save();
		setScene(MainCanvas.S_ENDING);
	}
	
	public void sceneLoad(int scene) throws Exception
	{
		switch ( scene )
		{
		case S_PLAY:
			break;
		case S_TITLE:
			break;
		case S_MANUAL:
			manual.load(this);
			break;
		case S_OPTION:
			optionMenu.resetId();
			bgmVolumeMenu.setId(0, getSEVolumeLevel());
			break;
		case S_INIT:
			break;
		}
	}

	void checkKey()
	{
		if ( cursor.move(this) )
		{
			clearWindow();
		}
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

	private void drawChara(CharacterData cd)
	{
		if ( cd != null )
		{
			//System.out.println(cd);
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

	public void execCommandAction(int softKeyNo) throws Exception
	{
		switch ( softKeyNo )
		{
		case 0:
			setSoundEnable(false);
			changeCommand();
			break;
		case 1:
			setSoundEnable(true);
			changeCommand();
			break;
		case 2:
			if ( !Main.isDxMode() )
			{
				browser(URL);
			}
			break;
		case 3:
			setScene(S_TITLE);
			break;

		case 4:
			clearWindow();
			playMenu.setVisible(true);
			changeCommand();
			break;
		case 5:
			clearWindow();
			showMyItem = true;
			itemBox.updateViewItems();
			changeCommand();
			break;
		case 6:
			clearWindow();
			hideItems();
			playMenu.setVisible(false);
			changeCommand();
			break;
		case 7:
			if ( selectedItem != null )
			{
				useItem = selectedItem;
			}
			break;
		}
	}

	public void changeCommand()
	{
		if ( scene == S_PLAY )
		{
			if ( showMyItem )
			{
				setSoftKey(6, 7);
			}
			else if ( playMenu.isVisible() )
			{
				setSoftKey(6, -1);
			}
			else
			{
				setSoftKey(4, 5);
			}
		}
		else if ( scene == S_MANUAL )
		{
			setSoftKey(3, -1);
		}
		else if ( scene == S_TITLE )
		{
			setSoftKey((soundEnable) ? 0 : 1, 2);
		}
		else
		{
			setSoftKey(8, -1);
		}
	}

	public void goNextRoom(int roomId)
	{
		this.roomId = roomId;
		
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
		hideDetailItem();
		showMyItem = false;
	}

	private void hideDetailNoItem()
	{
		if ( detailNoItem != null )
		{
			detailNoItem.state = ItemData.STATE_HIDE;
			detailNoItem= null;
		}
	}
	
	private void hideDetailItem()
	{
		if ( detailItem != null )
		{
			detailItem.state = CharacterData.STATE_HIDE;
			detailItem = null;
		}
	}

	public boolean isShow(int i)
	{
		CharacterData d = items[i];
		
		boolean r = ((d.rootId == -1) || (search(d.rootId).state == CharacterData.STATE_SHOW));
		//System.out.println(d.rootId + ":"+ (search(d.rootId) == null ? -1 : search(d.rootId).state) + ":"+ d);
		
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
		hideDetailNoItem();
		return ret;
	}

	public void event(CharacterData cd)
	{
		if ( cd.listNode != null )
		{
			program.execEvent(cd.listNode);
		}
	}
	
	void setDetailNoItem(int id)
	{
		hideDetailNoItem();
		hideDetailItem();

		detailNoItem = search(id);
		detailNoItem.x = detailItemWindow.x + 3;
		detailNoItem.y = detailItemWindow.y + 3;
		detailNoItem.state = ItemData.STATE_SHOW;
	}

	ItemData addItem(String id)
	{
		ItemData d = (ItemData)search(id);
		itemBox.add(d);
		try
		{
			startSE(1);
		}
		catch ( Exception e ) {}
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
	
	public void executeCommand(CommandNode node)
	{
		//System.out.println(node);
		
		if ( "item".equals(node.getCommand()) )
		{
			ItemData d = addItem(node.getArg());
			if ( d.detailId >= 0 )
			{
				setDetailNoItem(d.detailId);
			}
			itemBox.updateViewItems();
		}
		else if ( "detailnoitem".equals(node.getCommand()) )
		{
			setDetailNoItem(Integer.parseInt(node.getArg()));
		}
		else if ( "delitem".equals(node.getCommand()) )
		{
			removeItem(node.getArg());
		}
		else if ( "msg".equals(node.getCommand()) )
		{
			messageWindow.setMessage(node.getArg());
		}
		else if ( "show".equals(node.getCommand()) )
		{
			search(node.getArg()).state = CharacterData.STATE_SHOW;
		}
		else if ( "hide".equals(node.getCommand()) )
		{
			search(node.getArg()).state = CharacterData.STATE_HIDE;
		}
		else if ( "go".equals(node.getCommand()) )
		{
			resetImages();
			goNextRoom(Integer.parseInt(node.getArg()));
		}
		else if ( "story".equals(node.getCommand()) )
		{
			int id = Integer.parseInt(node.getArg());
			setStoryData(Data.getStoryCode(id));
		}
		else if ( "detail".equals(node.getCommand()) )
		{
			hideDetailItem();
			detailItem = search(node.getArg());
			detailItem.x = detailItemWindow.x + 3;
			detailItem.y = detailItemWindow.y + 3;
			detailItem.state = ItemData.STATE_SHOW;
		}
		else if ( "select".equals(node.getCommand()) )
		{
			selectedItem = (ItemData)search(node.getArg());
		}
		else if ( "event".equals(node.getCommand()) )
		{
			int id = Integer.parseInt(node.getArg());
			if ( id == 1 )
			{
				itemBox.left();
			}
			else if ( id == 2 )
			{
				itemBox.right();
			}
		}
		else if ( "additemonly".equals(node.getCommand()) )
		{
			ItemData d = addItem(node.getArg());
			itemBox.updateViewItems();
		}
		else if ( "vibrate".equals(node.getCommand()) ) {
			vibrate(Integer.parseInt(node.getArg()));
		}
		else if ( "se".equals(node.getCommand()) ) {
			try {

				startSE(Integer.parseInt(node.getArg()));

			} catch (Exception ignore) {
			}
		}
		else if ( "toggle".equals(node.getCommand()) ) {
			GroupData gd = Data.getGroupData(Integer.parseInt(node.getArg()));
			gd.toggle();
		}
		else
		{
			throw new IllegalArgumentException(node.getCommand());
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
	
	public boolean executeIfCommand(CommandNode node)
	{
		if ( "item".equals(node.getCommand()) )
		{
			return ((useItem != null) &&
					(useItem.id == Integer.parseInt(node.getArg())));
		}
		else if ( "show".equals(node.getCommand()) )
		{
			return search(node.getArg()).state == CharacterData.STATE_SHOW;
		}
		else if ( "hide".equals(node.getCommand()) )
		{
			return search(node.getArg()).state == CharacterData.STATE_HIDE;
		}
		else
		{
			throw new IllegalArgumentException(node.getCommand());
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





//////////load & save /////////////
	void loadSystem()
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
			// System Data
			s = GameUtil.split(line[0], ':');
			int lineCount = 0;
			s = GameUtil.split(line[lineCount++], ':');
			setSEVolumeLevel(Integer.parseInt(s[5]));
			setSoundEnable("1".equals(s[6]));
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			messageWindow.setMessage("ロードに失敗しました");
		}
	}
			
	void load()
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
			// System Data
			s = GameUtil.split(line[0], ':');
			int lineCount = 0;
			s = GameUtil.split(line[lineCount++], ':');
			cursor.x = Integer.parseInt(s[0]);
			cursor.y = Integer.parseInt(s[1]);
			if ( !"".equals(s[2]) )
			{
				useItem = (ItemData) search(s[2]);
			}
			goNextRoom(Integer.parseInt(s[3]));
			clearFlag = "1".equals(s[4]);
			//setSEVolumeLevel(Integer.parseInt(s[5]));
			//setSoundEnable("1".equals(s[6]));
			
			
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
	
	void save()
	{
		try
		{
			StringBuffer saveData = new StringBuffer();

			SaveDataCreator sdc = new SaveDataCreator();
			sdc.add(cursor.x);
			sdc.add(cursor.y);
			sdc.add((useItem == null) ? "" : String.valueOf(useItem.id));
			sdc.add(roomId);
			sdc.add(clearFlag);
			sdc.add(getSEVolumeLevel());
			sdc.add(soundEnable);
			saveData.append(sdc);
			saveData.append(',');

			sdc = new SaveDataCreator();
			for ( int i = 0; i < items.length; i++ )
			{
				sdc.add(items[i]);
			}
			saveData.append(sdc);
			
			saveData.append(',');
			saveData.append(itemBox);
			
			save(RECORD_KEY, saveData.toString());
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			messageWindow.setMessage("セーブに失敗しました");
		}
	}
}
