/*
 * Last modified: 2009/04/05 21:54:21
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
import com.strnet.game.component.NumberImage;

import java.util.Vector;

public class MainCanvas extends GameCommonCanvas
{
	public static final String RECORD_KEY = "Pordlla";
	public static final int MAX_IMAGE = 35;

	private static final int[] NEED_POINTDS = new int[]
		{0, 1000, 5000, 15000, 40000, 100000};
	
	public static final int IMAGE_SCENE_BLOCK = 15;
	public static final int IMAGE_MENU = 1;
	public static final int IMAGE_SKIN_SELECT = 28;
	public static final int IMAGE_OPTION = 19;
	public static final int IMAGE_OPTION_BACK = 31;

	public static final int[] MENU_PLAY = new int[]{0,1,3};
	public static final int[] MENU_CLEARED = new int[]{5,1,3};
	public static final int[] MENU_FAILED = new int[]{4,1,3};
	public static final int[] MENU_STAGE_SELECT = new int[]{0,3};

	public static final int BLOCK_WIDTH = 22;
	public static final int BLOCK_HEIGHT = 22;
	public static final int CLEAR_BLOCK_WIDTH = 30;
	public static final int CLEAR_BLOCK_HEIGHT = 30;
	public static final int CLEAR_BLOCK_Y = 210;


	// BGM
	public static final int BGM_TITLE = 0;
	public static final int BGM_PLAY1 = 1;
	public static final int BGM_PLAY2 = 6;
	
	// SE
	public static final int SE_SUCCESS = 2;
	public static final int SE_FAILED = 3;
	public static final int SE_CURSOR = 4;
	public static final int SE_ERASE = 5;
	public static final int SE_SELECT = 4;

	
	public static final int STATE_PLAY = 0;
	public static final int STATE_DROP = 10;
	public static final int STATE_CLEAR = 11;
	public static final int STATE_CLEARED = 12;
	public static final int STATE_FAILED = 13;
	public static final int STATE_DROP_MOVE = 14;
	public static final int STATE_CLEAR_ANIME = 15;
	public static final int STATE_DROP_BEFORE = 16;
	public static final int STATE_START = 17;
	
	public static final int SKIN_MODE_OFF = 0;
	public static final int SKIN_MODE_DOWN = 1;
	public static final int SKIN_MODE_UP = 2;
	public static final int SKIN_MODE_ON = 3;
	

	//static final int[] CELL_MOVE = new int[]{0, 7, 14};
	public static final int[] CELL_MOVE = new int[]{0, 10, 17};
	public static final int CELL_ANIME_NO = 0;
	public static final int CELL_ANIME_LEFT = 1;
	public static final int CELL_ANIME_RIGHT = 2;


	public static final int MAP_X = 43;
	public static final int MAP_Y = 10;
	public static final int CURSOR_LEFT_WIDTH = 43;
	public static final int CURSOR_TOP_WIDTH = 10;


	public static final int ERASE_COUNT = 3;
	
	// マニュアルの画像ID
	public static final int[] MANUAL_PAGES = new int[] {20,21,33};
	
	int cellAnimeCount = 0;
	int cellAnimeMode = CELL_ANIME_NO;
	int counter = 0;
	int commonTitleCounter = 0;
	int titleCounter = 0;
	int stageSelectCounter = 0;
	boolean sceneLoaded = false;
	//SceneBlock[] sceneBlocks = new SceneBlock[8];
	TitleBlock[] titleBlocks = new TitleBlock[12];

	
	MenuWindow titleMenu;
	MenuWindow playMenu;
	MenuWindow stageSelectMenu;
	MenuWindow stageClearMenu;
	MenuWindow stageFailedMenu;

	MenuWindow optionMenu;
	MenuWindow bgmVolumeMenu;
	MenuWindow seVolumeMenu;
	MenuWindow dropButtonMenu;

	StageSelectList stageSelectList;
	SkinList skinList;

	Cell[][] map = new Cell[Map.MAX_ROWS][Map.MAX_COLS];
	//int[][] undoMap = new int[Map.MAX_ROWS][Map.MAX_COLS];
	Cursor cursor = new Cursor(MAP_X - CURSOR_LEFT_WIDTH, MAP_Y - CURSOR_TOP_WIDTH, new Rectangle(0, 42, 240, 42));
	int playState = STATE_PLAY;
	Skin skin;
	//int mode;
	Result result;
	NumberImage stageNoImage = null;
	NumberImage totalScoreImage = null;
	StringBuffer allRotate = new StringBuffer();

	
	int skinSelectCounter;
	int skinSelectMode;
	int needSkinPoint = 0;
	boolean newSkinGet = false;
	EndingData ending = null;
	int backSkinId = 0;
	int allClearCount = 0;

    // 以下保存する情報
	int[] stageClearFlags = null;
	int stage = 0;
	int paintStage = 0;
	int score = 0;
	int playCount = 0;

	// Option
	int enterButton = S_KEY_FIRE; // ドロップキー
	int canUseSkinId = 1;
	int skinId = 0; // スキン。-1の時はランダム
	int randomSkinId = -1; // ランダムスキン。-1の時はランダム
	int bgmId = BGM_PLAY1; // BGM
	// BGMボリューム
	// SEボリューム
	// バイブレーション, SE, BGMはMainのフィールド

	
	public MainCanvas(Main app)
	{
		super(app, S_PRESS_FIRE_KEY, MAX_IMAGE);
		//setWaitTime(150);
	}
	
	void init()
	{
		String webLabel = Main.isDxMode() ? "" : "WEB";
		super.init(new String[]{"♪○", "♪×", webLabel, "ﾒﾆｭｰ", "", "", "ﾀｲﾄﾙ", "", "ｽｷﾝ", "音1", "音2", "ED"});
		
		initImage(MAX_IMAGE);
		try
		{
			loadImage(0);
			loadImage(1);
			loadImage(2);
			loadImage(15);
		}
		catch ( Exception e )
		{
			msg = "image load error";
		}
		setFont(S_FONT_SMALL);
		titleMenu = new MenuWindow(1, 4, false);
		playMenu = new MenuWindow(1, 3, false, true);
		stageSelectMenu = new MenuWindow(1, MENU_STAGE_SELECT.length, false, true);
		stageClearMenu = new MenuWindow(1, 3, false, true);
		stageFailedMenu = new MenuWindow(1, 3, false, true);

		optionMenu = new MenuWindow(1, 5, false);
		bgmVolumeMenu = new MenuWindow(5, 1, false);
		seVolumeMenu = new MenuWindow(5, 1, false);
		dropButtonMenu = new MenuWindow(3, 1, false);
		closeMenu();

		Map.init();

		for ( int row = 0; row < Map.MAX_ROWS; row++ )
		{
			for ( int col = 0; col < Map.MAX_COLS; col++ )
			{
				map[row][col] = new Cell();
				//undoMap[row][col] = 0;
			}
		}

		/*
		  for ( int i = 0; i < sceneBlocks.length; i++ )
		  {
		  sceneBlocks[i] = new SceneBlock(i);
		  }
		*/

		stageClearFlags = new int[Map.getMaxStage()];
		stageSelectList = new StageSelectList(0, Map.getMaxStage(), 10);
		skinList = new SkinList(NEED_POINTDS.length, NEED_POINTDS);
		load();
		
		try
		{
			loadSkin(skinId);
		}
		catch ( Exception e )
		{
			msg = "load skin failed.";
		}
		for ( int i = 0; i < titleBlocks.length; i++ )
		{
			titleBlocks[i] = new TitleBlock();
			setTitleBlock(titleBlocks[i]);
		}

		stageNoImage = new NumberImage(IMAGE_MENU, 10, 12);
		stageNoImage.setSource(4, 207);
		stageNoImage.setMaxLength(2);
		
		totalScoreImage = new NumberImage(IMAGE_MENU, 10, 12);
		totalScoreImage.setSource(4,207);
		totalScoreImage.setMaxLength(7);
		
		manual = new ManualManager(MANUAL_PAGES);
		initialized = true;
	}

	void loadSkin(int sid) throws Exception
	{
		if ( sid == -1 )
		{
			if ( canUseSkinId == 1 )
			{
				sid = 1;
			}
			else
			{
				sid = GameUtil.rand(canUseSkinId, backSkinId);
			}
		}
		backSkinId = sid;
		skin = Skin.getSkin(sid);
		cursor.setSkin(skin.getBlock());
		skin.load(this);
	}
	
	void setTitleBlock(TitleBlock tb)
	{
		int speed = GameUtil.rand(10) + 6;
		int x = GameUtil.rand(240 - BLOCK_WIDTH);
		int y = -BLOCK_HEIGHT + (speed * 2);
		int blockId = GameUtil.rand(7);
		tb.set(speed, x, y, blockId, skin.getBlock());
	}

	/*
	  void undo()
	  {
	  for ( int row = 0; row < Map.MAX_ROWS; row++ )
	  {
	  for ( int col = 0; col < Map.MAX_COLS; col++ )
	  {
	  map[row][col].reset();
	  map[row][col].id = undoMap[row][col];
	  }
	  }
	  }
	
	  void setUndo()
	  {
	  for ( int row = 0; row < Map.MAX_ROWS; row++ )
	  {
	  for ( int col = 0; col < Map.MAX_COLS; col++ )
	  {
	  undoMap[row][col] = map[row][col].id;
	  }
	  }
	  }
	*/

	void reset() throws Exception
	{
		Map.setup(stage, map);
		
		if ( allClearCount > 0 )
		{
			Map.rotatesLeft(map, allRotate.toString());
		}
		
		loadSkin(skinId);
		bgmId = getBgmId(stage);

		/*
		  if ( !randomRetry )
		  {
		  StringBuffer ro = new StringBuffer();
		  for ( int i = 0; i < Map.MAX_ROWS; i++ )
		  {
		  ro.append(GameUtil.rand(7));
		  }
		  randomRotate = ro.toString();
		  randomStage = GameUtil.rand(Map.getMaxStage());
		  randomRetry = false;
		  loadSkin(randomSkinId);
		  bgmId = getBgmId(randomStage);
		  Map.setup(randomStage, map, randomRotate);
		*/
		
		paintStage = stage;
		Map.dropCount = 0;
		cursor.reset(map);
		playState = STATE_PLAY;
		cellAnimeMode = CELL_ANIME_NO;
		cellAnimeCount = 0;
		result.reset();
		counter = 0;
		msg = null;
		skinSelectCounter = 0;
		skinSelectMode = SKIN_MODE_OFF;
		closeMenu();
		newSkinGet = false;
		changeCommand();
		loadSE(SE_CURSOR);

		startBGM(bgmId);
		gc();
	}

	public void gc()
	{
		System.gc();
	}

	public void execPressFireKey() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			scene = S_TITLE;
			startBGM(BGM_TITLE);
			loadSE(SE_SELECT);
			changeCommand();
			keyReset();
		}
		else if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}
		titleCounter++;
		if ( titleCounter > 15 )
		{
			titleCounter = 0;
		}
		doPaintPressFireKey();
	}

	void drawCommonBackground()
	{
		commonTitleCounter++;
		if ( commonTitleCounter > 0 )
		{
			commonTitleCounter = -60;
		}
		
		for ( int i = 0; i < 5; i++ )
		{
			drawImage(0, 0, 0, 0,(i*60)+commonTitleCounter  ,240,60);
		}
	}

	void drawCommonTitle()
	{
		drawCommonBackground();
		if ( scene == S_TITLE )
		{
			for ( int i = 0; i < titleBlocks.length; i++ )
			{
				if ( !titleBlocks[i].downPaint(this) )
				{
					setTitleBlock(titleBlocks[i]);
				}
			}
		}

		drawImage(2, 0, 146, 0,0  ,240,94);
		drawImage(2, 0, 115, 82,226  ,76,14);
	}
	
	public void doPaintPressFireKey()
	{
		drawCommonTitle();
		if ( titleCounter < 10 )
		{
			drawImage(2, 0,129, 65,144, 110,16);
		}
	}
	
	public void execTitle() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			switch ( titleMenu.getId() )
			{
			case 0:
				result = new Result();
				if ( allClearCount > 0 )
				{
					allRotate = new StringBuffer();
					int xx1 = GameUtil.loopIncf(allClearCount, 0, 6);
					int xx2 = GameUtil.loopDecf(allClearCount, 0, 6);
					allRotate.append(xx1);
					allRotate.append(xx1);
					allRotate.append(xx1);
					allRotate.append(xx2);
					allRotate.append(xx2);
					allRotate.append(xx2);
					allRotate.append(xx1);
					allRotate.append(xx2);
					allRotate.append(xx2);
					allRotate.append(xx1);
				}
				
				setScene(S_STAGE_SELECT);
				break;
			case 1:
				setScene(S_OPTION);
				break;
			case 2:
				setScene(S_MANUAL);
				break;
			case 3:
				terminate();
				break;
			}
			keyReset();
		}
		else if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}
		else
		{
			if ( titleMenu.move(this) )
			{
				startSE();
			}
			keyReset();
		}

		doPaintTitle();
	}
	
	public void doPaintTitle()
	{
		drawCommonTitle();
		drawImage(2, 117,0, 59, 110, 123,114);
		for ( int i = 0; i < 4; i++ )
		{
			int y = ( titleMenu.getId() == i ) ? 56 : 0;
			drawImage(2, 0,y+11*i, 62, 130 + (22 * i), 115,11);
		}
		
		if ( allClearCount > 0 )
		{
			drawImage(2, 124,123, 30, 96, 68,12);
			for ( int i = 0; i < allClearCount; i++ )
			{
				drawImage(2, 197,123, 110+(i*13),96, 12,12);
			}
		}
	}
	
	
	public void execOption() throws Exception
	{
		int ev = getKeyEvent();
		if ( (ev == S_KEY_UP) || (ev == S_KEY_DOWN) )
		{
			if ( optionMenu.move(this) )
			{
				startSE();
			}
			keyReset();
		}
		else if ( (ev == S_KEY_LEFT) || (ev == S_KEY_RIGHT) )
		{
			int option = optionMenu.getId();
			switch ( option )
			{
			case 0://BGM
				bgmVolumeMenu.move(this);
				setBGMVolume(VOLUME_LEVEL * bgmVolumeMenu.getId());
				break;
			case 1://SE
				seVolumeMenu.move(this);
				setSEVolume(VOLUME_LEVEL * seVolumeMenu.getId());
				break;
			case 2://Vib
				if ( vibrateEnable && (ev == S_KEY_RIGHT) )
				{
					vibrateEnable = false;
				}
				else if ( !vibrateEnable && (ev == S_KEY_LEFT) )
				{
					vibrateEnable = true;
				}
				break;
			case 3://DropButton
				dropButtonMenu.move(this);
				int id = dropButtonMenu.getId();
				switch ( id )
				{
				case 0:
					enterButton = S_KEY_FIRE;
					break;
				case 1:
					enterButton = S_KEY_STAR;
					break;
				case 2:
					enterButton = S_KEY_POUND;
					break;
				}
				break;
			}
			startSE();
			keyReset();
		}
		else if ( ev == S_KEY_FIRE )
		{
			if ( optionMenu.getId() == 4 )
			{
				save();
				setScene(S_TITLE);
			}
			keyReset();
		}
		else if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}

		doPaintOption();
	}
	
	public void doPaintOption() throws Exception
	{
		drawCommonBackground();
		
		drawImage(IMAGE_OPTION_BACK, 0,0, 11,44, 218,158);

		int x = 30;
		int y = 60;
		for ( int i = 0; i < 5; i++ )
		{
			int srcY = (optionMenu.getId() == i) ? 81 : 0;
			if ( i == 4 )
			{
				drawImage(IMAGE_OPTION, 0,srcY + i*16, x + 30,y + (20*i) + 30, 104,16);
			}
			else
			{
				drawImage(IMAGE_OPTION, 0,srcY + i*16, x,y + (20*i), 104,16);
			}
		}

		for ( int i = 0; i < 5; i++ )
		{
			int srcY = (bgmVolumeMenu.getId() == i) ? 81 : 0;
			drawImage(IMAGE_OPTION, 114 + (9*i),srcY, x + 106 + (12*i), y, 9,12);
		}

		for ( int i = 0; i < 5; i++ )
		{
			int srcY = (seVolumeMenu.getId() == i) ? 81 : 0;
			drawImage(IMAGE_OPTION, 114 + (9*i),srcY, x + 106 + (12*i), y + 20, 9,12);
		}
		
		if ( vibrateEnable )
		{
			drawImage(IMAGE_OPTION, 114,81 + 16, x + 106, y + 40, 18,11);
			drawImage(IMAGE_OPTION, 138,0 + 16, x + 106 + 25, y + 40, 25,11);
		}
		else
		{
			drawImage(IMAGE_OPTION, 114,0 + 16, x + 106, y + 40, 18,11);
			drawImage(IMAGE_OPTION, 138,81 + 16, x + 106 + 25, y + 40, 25,11);
		}
		
		for ( int i = 0; i < 3; i++ )
		{
			int srcY = (dropButtonMenu.getId() == i) ? 81 : 0;
			drawImage(IMAGE_OPTION, 114 + (30*i),srcY+30, x + 106 + (30*i), y + 60, 28,15);
		}
	}

	public void execEnding() throws Exception
	{
		boolean ne = ending.next();
		if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}
		else
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				if ( !ne )
				{
					setScene(S_TITLE);
				}
				keyReset();
			}
		}
		doPaintEnding();
	}
	
	public void doPaintEnding() throws Exception
	{
		//System.out.println(ending);
		ending.paint(this);
	}

	public void execStageSelect() throws Exception
	{
		if ( stageSelectMenu.isVisible() )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				switch ( stageSelectMenu.getId() )
				{
				case 0:
					stageSelectMenu.setVisible(false);
					break;
				case 1:
					closeMenu();
					skinSelectMode = SKIN_MODE_OFF;
					setScene(S_TITLE);
					break;
				}
				keyReset();
			}
			else
			{
				if ( stageSelectMenu.move(this) )
				{
					startSE();
				}

				keyReset();
			}
		}
		
		else if ( skinSelectMode == SKIN_MODE_ON )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				skinId = skinList.getId();
				skinSelectMode = SKIN_MODE_OFF;
				needSkinPoint = 0;
				keyReset();
			}
			else if ( getKeyEvent() == S_SOFT_KEY )
			{
				execCommandAction(getSoftKeyNo());
				keyReset();
			}
			else
			{
				int ret = skinList.move(this, score);
				if ( ret == 0 )
				{
				}
				else if ( ret == 1 )
				{
					startSE();
					needSkinPoint = 0;
				}
				else
				{
					needSkinPoint = ret;
				}
				keyReset();
			}
		}
		else if ( skinSelectMode == SKIN_MODE_OFF )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				stage = stageSelectList.getId();
				setScene(S_PLAY);
				keyReset();
			}
			else if ( getKeyEvent() == S_SOFT_KEY )
			{
				execCommandAction(getSoftKeyNo());
				keyReset();
			}
			else
			{
				if ( stageSelectList.move(this) )
				{
					startSE();
				}
				keyReset();
			}
		}
		
		doPaintStageSelect();
		
		if ( stageSelectMenu.isVisible() )
		{
			drawMenu(stageSelectMenu, MENU_STAGE_SELECT);
		}
	}

	
	public void doPaintStageSelect() throws Exception
	{
		drawCommonBackground();
		
		if ( skinSelectMode == SKIN_MODE_OFF )
		{
			int tmpStage = stageSelectList.getStart();
			int nid = stageSelectList.getId();
			int tmpY = 0;
			for ( int i = 0; i < 10; i++ )
			{
				//int x = (i % 2 == 0) ? 26 : 78;
				int x = 52;
				int y = (stageClearFlags[tmpStage] > 0) ? 143 : 119;
			
				if ( stageClearFlags[tmpStage] > 0 )
				{
					if ( tmpStage == nid )
					{
						y = 95;
						tmpY = i;
					}
					else
					{
						y = 143;
					}
				}
				else
				{
					if ( tmpStage == nid )
					{
						y = 167;
						tmpY = i;
					}
					else
					{
						y = 119;
					}
				}
			
				drawImage(IMAGE_MENU, 105,y, x, (i * 23) + 4, 135,20);
			
				//drawImage(IMAGE_MENU, 27,168, x+37, (i*23) + 8, 46,12);
				stageNoImage.draw(this, tmpStage, x+96, (i*23) + 8);
			
			
				tmpStage = GameUtil.loopIncf(tmpStage, 0, Map.getMaxStage());
			}
		
			drawImage(IMAGE_MENU, 204,201, 111, (tmpY*23) -7, 18,13);
			drawImage(IMAGE_MENU, 186,201, 111, (tmpY*23) + 22, 18,13);

			// allows
			stageSelectCounter++;
			if ( stageSelectCounter > 8 )
			{
				stageSelectCounter = 0;
			}
			int hAllow = stageSelectCounter / 3;
			drawImage(IMAGE_MENU, 151,201, 20 - (hAllow*2), 112, 16,24);
			drawImage(IMAGE_MENU, 167,201, 204 + (hAllow*2), 112, 16,24);
		}
		else
		{
			drawImage(IMAGE_SKIN_SELECT, 105,0, 53,33, 135,174);
			for ( int i = 0; i < NEED_POINTDS.length; i++ )
			{
				int y = ( skinList.getId() == i ) ? 89 : 0;
				drawImage(IMAGE_SKIN_SELECT, 0,y+14*i, 68, 90 + (17 * i), 105,14);
				if ( needSkinPoint > 0 )
				{
					drawImage(IMAGE_SKIN_SELECT, 0,179, 80,50, 83,12);
					totalScoreImage.draw(this, needSkinPoint, 80, 70);
				}
				else
				{
					drawImage(IMAGE_SKIN_SELECT, 0,221, 75,60, 90,12);
				}
			}
			drawImage(IMAGE_MENU, 0,228, 70, 1, 48,12);
			totalScoreImage.draw(this, score, 150, 1);
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
			if ( scene == S_OPTION )
			{
				restartBGM();
			}
			changeCommand();
			break;
		case 2:
			if ( !Main.isDxMode() )
			{
				browser(URL);
			}
			break;
		case 3:
			if ( scene == S_PLAY )
			{
				playMenu.setVisible(true);
			}
			else if ( scene == S_STAGE_SELECT )
			{
				stageSelectMenu.setVisible(true);
			}
			changeCommand();
			break;
		case 6:
			setScene(S_TITLE);
			break;
		case 8:
			skinSelectMode = (skinSelectMode == SKIN_MODE_ON) ? SKIN_MODE_OFF : SKIN_MODE_ON;
			break;
		case 9:
		case 10:
			setHiSound(!isHiSound());
			restartBGM();
			changeCommand();
			break;
		case 11:
			setScene(S_ENDING);
			break;
		}
	}

	public void changeCommand()
	{
		if ( scene == S_PRESS_FIRE_KEY )
		{
			setSoftKey((bgmEnable) ? 0 : 1, 4);
		}
		else if ( scene == S_TITLE )
		{
			if ( allClearCount > 0 )
			{
				setSoftKey(2,11);
			}
			else
			{
				setSoftKey(2,4);
			}
		}
		else if ( (scene == S_PLAY) && (playState == STATE_PLAY) )
		{
			setSoftKey(3,4);
		}
		else if ( scene == S_STAGE_SELECT )
		{
			setSoftKey(3,8);
		}
		else if ( scene == S_MANUAL )
		{
			setSoftKey(6,7);
		}
		else if ( scene == S_OPTION )
		{
			if ( "s".equals(getCarrier()) )
			{
				int r = isHiSound() ? 9 : 10;
				setSoftKey((bgmEnable) ? 0 : 1, r);
			}
			else
			{
				setSoftKey((bgmEnable) ? 0 : 1, 4);
			}
		}
		else if ( scene == S_ENDING )
		{
			setSoftKey(6,7);
		}
		else
		{
			setSoftKey(4,5);
		}
	}
	
	public void startDrop()
	{
		//setUndo();
		try
		{
			loadSE(SE_ERASE);
		}
		catch ( Exception ignore )
		{
			System.out.println("se load failed.");
		}
		counter = 0;
		playState = STATE_DROP_BEFORE;
	}


	/*
	  public void doPaintSceneMove() throws Exception
	  {
	  int frame = 8;
		
	  if ( sceneMoveCounter > (frame * 2 + 1) ) // 17
	  {
	  scene = nextScene;
	  changeCommand();
	  keyReset();
	  releaseImage(IMAGE_SCENE_BLOCK);
	  for ( int i = 0; i < sceneBlocks.length; i++ )
	  {
	  sceneBlocks[i].reset();
	  }
	  System.gc();
	  }
	  else if ( sceneMoveCounter > (frame + 1) ) //10~32
	  {
	  for ( int i = (sceneMoveCounter - (frame + 2)); i < 8; i++ )
	  {
	  drawSceneMove(i);
	  }
	  }
	  else if ( sceneMoveCounter == (frame+ 1) )//9
	  {
	  backScene = nextScene;
	  for ( int i = 0; i < 8; i++ )
	  {
	  drawSceneMove(i);
	  }

	  if ( !sceneLoaded )
	  {
	  sceneLoaded = true;
	  sceneLoad(nextScene);
	  }
	  }
	  else// 1~8
	  {
	  sceneLoaded = false;

	  for ( int i = 0; i < sceneMoveCounter; i++ )
	  {
	  drawSceneMove(i);
	  }
	  }
	  }
	*/

	public void doPaintSceneMove() throws Exception
	{
		int frame = 8;
		
		if ( sceneMoveCounter > ((frame * 2) + 3) ) // 10
		{
			scene = nextScene;
			changeCommand();
			keyReset();
			//releaseImage(IMAGE_SCENE_BLOCK);
			/*
			  for ( int i = 0; i < sceneBlocks.length; i++ )
			  {
			  sceneBlocks[i].reset();
			  }
			*/
			drawSceneMove(frame,sceneMoveCounter);
		}
		else if ( sceneMoveCounter > frame+2 ) //6-9
		{
			drawSceneMove(frame,sceneMoveCounter);
		}
		else if ( sceneMoveCounter == (frame+ 2) )//5
		{
			backScene = nextScene;
			drawSceneMove(frame,sceneMoveCounter);

			if ( !sceneLoaded )
			{
				sceneLoaded = true;
				for ( int i = 0; i < getMaxImage(); i++ )
				{
					releaseImage(i);
				}
				sceneLoad(nextScene);
			}
		}
		else//1-4
		{
			sceneLoaded = false;
			drawSceneMove(frame,sceneMoveCounter);
		}
	}

	
	void drawSceneMove(int frame, int row) throws Exception
	{
		/*
		  if ( (row == 7) )
		  {
		  drawImage(IMAGE_SCENE_BLOCK, 0,30, 0, 30 * row, 240, 30);
		  }
		  else
		  {
		  drawImage(IMAGE_SCENE_BLOCK, 0,0, 0, 30 * row, 240, 30);
		  }
		*/

		setColor(0,0,0);
		if ( row == frame+1 )
		{
			fillRect(0,0, 240,240);
		}
		else
		{
			if ( row >= (frame+2) )
			{
				row = (frame*2+3) - row;
			}
			
			if ( row > 0 )
			{
				int height = 240 / (frame*2);
				fillRect(0,0, 240,height*row);
				fillRect(0,240 - (height*row), 240,height*row);
			}
		}
	}
	
	void sceneLoad(int scene) throws Exception
	{
		switch ( scene )
		{
		case S_PLAY:
			releaseImage(0);
			reset();
			break;
		case S_STAGE_SELECT:
			gc();
			startBGM(BGM_TITLE);
			loadSE(SE_SELECT);
			break;
		case S_TITLE:
			for ( int i = 0; i < titleBlocks.length; i++ )
			{
				setTitleBlock(titleBlocks[i]);
			}
			gc();
			startBGM(BGM_TITLE);
			loadSE(SE_SELECT);
			break;
		case S_MANUAL:
			manual.load(this);
			manual.reset();
			gc();
			break;
		case S_OPTION:
			optionMenu.resetId();

			bgmVolumeMenu.setId(0, volume / VOLUME_LEVEL);
			seVolumeMenu.setId(0, seVolume / VOLUME_LEVEL);
			int tmp = 0;
			switch ( enterButton )
			{
			case S_KEY_FIRE:
				tmp = 0;
				break;
			case S_KEY_STAR:
				tmp = 1;
				break;
			case S_KEY_POUND:
				tmp = 2;
				break;
			}
			dropButtonMenu.setId(0, tmp);

			loadImage(IMAGE_OPTION_BACK);
			loadImage(IMAGE_OPTION);
			gc();
			break;
		case S_ENDING:
			loadImage(35);
			loadImage(2);
			manualStopBGM();
			gc();
			//startBGM(BGM_TITLE);
			loadSE(BGM_TITLE);
			startSE();
			ending = new EndingData(fontHeight, allClearCount, playCount);
			break;
		case S_INIT:
		case S_PRESS_FIRE_KEY:
			break;
		}
	}

	public void fireKeyReleased()
	{
		setKeyState();
	}
	
	public void cellLeft()
	{
		cellAnimeMode = CELL_ANIME_LEFT;
		execCellAnimation();
	}
	
	public void cellRight()
	{
		cellAnimeMode = CELL_ANIME_RIGHT;
		execCellAnimation();
	}
	
	public boolean execCellAnimation()
	{
		if ( cellAnimeMode == CELL_ANIME_NO )
		{
			return false;
		}

		cellAnimeCount++;
		
		if ( cellAnimeCount >= CELL_MOVE.length )
		{
			if ( cellAnimeMode == CELL_ANIME_LEFT )
			{
				Map.rotateLeft(cursor.getRow(), map);
			}
			else if ( cellAnimeMode == CELL_ANIME_RIGHT )
			{
				Map.rotateRight(cursor.getRow(), map);
			}
			cellAnimeMode = 0;
			cellAnimeCount = 0;
		}
		return true;
	}
	
	
	public void execPlay() throws Exception
	{
		if ( playMenu.isVisible() )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				switch ( playMenu.getId() )
				{
				case 0:
					playMenu.setVisible(false);
					break;
				case 1:
					closeMenu();
					setScene(S_STAGE_SELECT);
					break;
				case 2:
					setScene(S_TITLE);
					break;
				}
				keyReset();
			}
			else
			{
				playMenu.move(this);
				keyReset();
			}
		}
		else if ( stageClearMenu.isVisible() )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				switch ( stageClearMenu.getId() )
				{
				case 0:
					if ( stage < Map.getMaxStage() - 1 )
					{
						stage++;
						stageSelectList.down();
					}
					setScene(S_PLAY);
					break;
				case 1:
					closeMenu();
					setScene(S_STAGE_SELECT);
					break;
				case 2:
					setScene(S_TITLE);
					break;
				}
				keyReset();
			}
			else
			{
				stageClearMenu.move(this);
				keyReset();
			}
		}
		else if ( stageFailedMenu.isVisible() )
		{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				switch ( stageFailedMenu.getId() )
				{
				case 0:
					reset();
					break;
				case 1:
					closeMenu();
					setScene(S_STAGE_SELECT);
					break;
				case 2:
					setScene(S_TITLE);
					break;
				}

				keyReset();
			}
			else
			{
				stageFailedMenu.move(this);
				keyReset();
			}
		}


		else
		{
			execCellAnimation();
			if ( playState == STATE_PLAY )
			{
				cursor.execAnimation();
				checkKey();
			}
			else
			{
				switch ( playState )
				{
				case STATE_CLEARED:
				case STATE_FAILED:
					checkKey();
					break;
				}
			}
			
			if ( playState == STATE_PLAY )
			{
			}
			else
			{
				keyReset();
				switch ( playState )
				{
				case STATE_CLEARED:
					//changeCommand();
					break;
				
				case STATE_DROP:
					if ( Map.drop(map) )
					{
						Map.dropCount++;
						playState = STATE_DROP_MOVE;
						execPlay();
					}
					else
					{
						// 落下終了
						if ( Map.dropCount == 0 )
						{
							try
							{
								loadSE(SE_FAILED);
								manualStopBGM();
								startSE();
							}
							catch ( Exception ignore )
							{
								System.out.println("se load failed.");
							}
							savePlayCount();
							playState = STATE_FAILED;
							changeCommand();

							closeMenu();
							stageFailedMenu.setVisible(true);
						}
						else
						{
							playState = STATE_CLEAR;
						}
					}
					break;
					
				case STATE_DROP_MOVE:
					// 落下中
					boolean drop = false;
					for ( int row = 0; row < Map.MAX_ROWS; row++ )
					{
						for ( int col = 0; col < Map.MAX_COLS; col++ )
						{
							drop |= map[row][col].execAnimation();
						}
					}
					if ( !drop )
					{
						// 落下完了
						playState = STATE_DROP;
						execPlay();
					}
					
					break;
				
				case STATE_CLEAR:
					// 消える奴
					boolean clearFlag = false;
					for ( int row = 0; row < Map.MAX_ROWS; row++ )
					{
						for ( int col = 0; col < Map.MAX_COLS; col++ )
						{
							if ( !map[row][col].checked && (map[row][col].id > 0) )
							{
								int count = Map.check(map, row, col, map[row][col].id);
								if ( count >= ERASE_COUNT )
								{
									clearFlag = true;
									Map.clear(map, row, col, map[row][col].id);
								}
							}
						}
					}
					
					if ( clearFlag )
					{
						// アニメーション開始
						playState = STATE_CLEAR_ANIME;
						execPlay();
						try
						{
							startSE();
							vibrate(500);
						}
						catch ( Exception ignore )
						{
							System.out.println("se play failed.");
						}
					}
					else
					{
						// 全消しチェック
						if ( Map.isClear(map) )
						{
							if ( stageClearFlags[stage] == 0 )
							{
								stageClearFlags[stage] = 1;
								//stageClearFlags[stage] = getClearFlagCount() + 1;
								score += result.getClearScore(stage);
							}
							if ( isAllClear() )
							{
								closeMenu();

								allClearCount++;
								for ( int i = 0; i < stageClearFlags.length; i++ )
								{
									stageClearFlags[i] = 0;
								}

								savePlayCount();
								ending = new EndingData(fontHeight, allClearCount, playCount);
								setScene(S_ENDING);
								return;
							}
							
							try
							{
								loadSE(SE_SUCCESS);
								manualStopBGM();
								startSE();
							}
							catch ( Exception ignore )
							{
								System.out.println("se load failed.");
							}

							closeMenu();
							stageClearMenu.setVisible(true);

							savePlayCount();
							playState = STATE_CLEARED;
							changeCommand();
						}
						else
						{
							Map.dropCount = 0;
							for ( int row = 0; row < Map.MAX_ROWS; row++ )
							{
								for ( int col = 0; col < Map.MAX_COLS; col++ )
								{
									map[row][col].reset();
								}
							}
							playState = STATE_DROP;
							execPlay();
						}
					}
					break;
					
				case STATE_CLEAR_ANIME:
					// 消えてる最中
					boolean cleared = false;
					
					for ( int row = 0; row < Map.MAX_ROWS; row++ )
					{
						for ( int col = 0; col < Map.MAX_COLS; col++ )
						{
							cleared |= map[row][col].clearAnimation();
						}
					}
					if ( !cleared )
					{
						// 消去完了
						playState = STATE_CLEAR;
						execPlay();
					}
					break;
				
				case STATE_DROP_BEFORE:
					if ( counter > 5 )
					{
						playState = STATE_DROP;
					}
					else
					{
						counter++;
					}
					break;
				
				case STATE_PLAY:
					break;
				}
			}
		}

		long start = System.currentTimeMillis();
		doPaintPlay();

		if ( playMenu.isVisible() )
		{
			drawMenu(playMenu, MENU_PLAY);
		}
		else if ( stageClearMenu.isVisible() )
		{
			drawMenu(stageClearMenu, MENU_CLEARED);
		}
		else if ( stageFailedMenu.isVisible() )
		{
			drawMenu(stageFailedMenu, MENU_FAILED);
		}
	}
	
	void checkKey() throws Exception
	{
		int ke = getKeyEvent();
		if ( (ke == enterButton) && (playState == STATE_PLAY) )
		{
			startDrop();
			changeCommand();
		}
		else if ( ke == S_KEY_FIRE )
		{
			switch ( playState )
			{
			case STATE_CLEARED:
				if ( stage < Map.getMaxStage() - 1 )
				{
					stage++;
					stageSelectList.down();
				}
				reset();
				break;
			case STATE_FAILED:
				reset();
				break;
			}
		}
		else
		{
			switch ( ke )
			{
			case S_KEY_UP:
				if ( cursor.up(map) )
				{
					try
					{
						startSE();
					}
					catch ( Exception ignore )
					{
						System.out.println("se play failed.");
					}
				}
				break;
			case S_KEY_DOWN:
				if ( cursor.down(map) )
				{
					try
					{
						startSE();
					}
					catch ( Exception ignore )
					{
						System.out.println("se play failed.");
					}
				}
				break;
			case S_KEY_LEFT:
				cellLeft();
				break;
			case S_KEY_RIGHT:
				cellRight();
				break;
			case S_SOFT_KEY:
				execCommandAction(getSoftKeyNo());
				break;
			}
		}
		keyReset();
	}

	public void doPaintPlay()
	{
		drawImage(skin.getBackground(),0,0);
		

		// ブロックたち
		for ( int row = 0; row < Map.MAX_ROWS; row++ )
		{
			for ( int col = 0; col < Map.MAX_COLS; col++ )
			{
				if ( map[row][col].id > 0 )
				{
					boolean normal = true;
					int paintX = MAP_X + (col * BLOCK_WIDTH);
					if ( row == cursor.getRow() )
					{
						if ( cellAnimeMode == CELL_ANIME_LEFT )
						{
							if ( col == 0 )
							{
								// 端っこ
								normal = false;

								// まず自セルに残りの体を描く
								drawImage(skin.getBlock(), (map[row][col].id - 1) * BLOCK_WIDTH + CELL_MOVE[cellAnimeCount], 0, paintX, MAP_Y + (row * BLOCK_WIDTH) + (BLOCK_WIDTH - BLOCK_HEIGHT), (BLOCK_WIDTH - CELL_MOVE[cellAnimeCount]), BLOCK_HEIGHT);
								// 次に一番左のセルに残りを描く
								paintX = MAP_X + ((Map.MAX_COLS - 1) * BLOCK_WIDTH);
								drawImage(skin.getBlock(), (map[row][col].id - 1) * BLOCK_WIDTH, 0, paintX + (BLOCK_WIDTH - CELL_MOVE[cellAnimeCount]), MAP_Y + (row * BLOCK_WIDTH) + (BLOCK_WIDTH - BLOCK_HEIGHT), CELL_MOVE[cellAnimeCount], BLOCK_HEIGHT);
							}
							else
							{
								paintX -= CELL_MOVE[cellAnimeCount];
							}
						}
						else if ( cellAnimeMode == CELL_ANIME_RIGHT )
						{
							if ( col == (Map.MAX_COLS - 1) )
							{
								// 端っこ
								normal = false;

								// まず自セルに残りの体を描く
								drawImage(skin.getBlock(), (map[row][col].id - 1) * BLOCK_WIDTH, 0, paintX + CELL_MOVE[cellAnimeCount], MAP_Y + (row * BLOCK_WIDTH) + (BLOCK_WIDTH - BLOCK_HEIGHT), BLOCK_WIDTH - CELL_MOVE[cellAnimeCount], BLOCK_HEIGHT);
								// 次に一番左のセルに残りを描く
								paintX = MAP_X;
								drawImage(skin.getBlock(), (map[row][col].id - 1) * BLOCK_WIDTH + (BLOCK_WIDTH - CELL_MOVE[cellAnimeCount]), 0, paintX, MAP_Y + (row * BLOCK_WIDTH) + (BLOCK_WIDTH - BLOCK_HEIGHT), CELL_MOVE[cellAnimeCount], BLOCK_HEIGHT);
							}
							else
							{
								paintX += CELL_MOVE[cellAnimeCount];
							}
						}
					}

					if ( normal )
					{
						if ( map[row][col].isClear() )
						{
							drawImage(skin.getBlock(), CLEAR_BLOCK_WIDTH * map[row][col].getClearCount(), CLEAR_BLOCK_Y,
									  paintX - ((CLEAR_BLOCK_WIDTH - BLOCK_WIDTH) / 2),
									  MAP_Y + (row * BLOCK_WIDTH) - (CLEAR_BLOCK_HEIGHT - BLOCK_HEIGHT),
									  CLEAR_BLOCK_WIDTH, CLEAR_BLOCK_HEIGHT);
						}
						else
						{
							drawImage(skin.getBlock(), (map[row][col].id - 1) * BLOCK_WIDTH, 0, paintX, MAP_Y + (row * BLOCK_WIDTH) + (BLOCK_WIDTH - BLOCK_HEIGHT) - map[row][col].getMoveY(), BLOCK_WIDTH, BLOCK_HEIGHT);
						}
					}
				}
			}
		}
		
		if ( playState < 10 )
		{
			// 格子状の線
			drawImage(skin.getBorder(), 0, 0, MAP_X, MAP_Y, 154, 220);
		}

		if ( playState < 10 )
		{
			//カーソル
			cursor.paint(this);
		}

		if ( playState == STATE_CLEARED )
		{
			drawImage(30, 0,0, 63,36, 114,24);
		}
		else if ( playState == STATE_FAILED )
		{
			drawImage(30, 0,24, 63,36, 114,24);
		}
		
		if ( playMenu.isVisible()
			 || stageClearMenu.isVisible()
			 || stageFailedMenu.isVisible()	)
		{
			drawImage(IMAGE_MENU, 27,168, 1, 21, 46,12);
			stageNoImage.draw(this, paintStage, 16, 20+17);
			
			drawImage(IMAGE_MENU, 0,228, 70, 1, 48,12);
			totalScoreImage.draw(this, score, 150, 1);
		}


		if ( stageClearMenu.isVisible()
			 || stageFailedMenu.isVisible()	)
		{
			if ( result.getClearScore() > 0 )
			{
				drawImage(IMAGE_MENU, 0,181, 40, 180, 99,12);
				totalScoreImage.draw(this, result.getClearScore(), 130, 180);
			}
			if ( result.gotSpecialScore() > 0 )
			{
				drawImage(IMAGE_MENU, 0,194, 40, 193, 46,12);
				drawImage(IMAGE_MENU, 46,181, 86, 193, 52,12);
				totalScoreImage.draw(this, result.gotSpecialScore(), 130, 193);
			}
			if ( result.getComboScore() > 0 )
			{
				drawImage(IMAGE_MENU, 86,228, 29, 206, 109,12);
				totalScoreImage.draw(this, result.getComboScore(), 130, 206);
			}
			
			if ( isGetNewSkin() )
			{
				titleCounter++;
				if ( titleCounter > 15 )
				{
					titleCounter = 0;
				}
				if ( titleCounter < 10 )
				{
					drawImage(IMAGE_MENU, 49,194, 70,155, 98,12);
				}
			}
		}

	}

	/*
	  int getClearFlagCount()
	  {
	  int len = 0;
	  for ( int i = 0; i < stageClearFlags.length; i++ )
	  {
	  if ( stageClearFlags[i] > 0 )
	  {
	  len++;
	  }
	  }
		
	  return len;
	  }
	*/
	
	public void updateStage(int stage)
	{
		stageSelectList.setStart(stage);
	}

	void load()
	{
		try
		{
			for ( int i = 0; i < stageClearFlags.length; i++ )
			{
				stageClearFlags[i] = 0;
			}
			if ( !isRecord(RECORD_KEY) )
			{
				return;
			}

			String tmp = load(RECORD_KEY);
			if ( tmp == null )
			{
				return;
			}
			//System.out.println(tmp);
			String[] line = GameUtil.split(tmp, ',');
			String[] s;
			// System Data
			s = GameUtil.split(line[0], ':');

			int i = 0;
			try
			{
				stage = Integer.parseInt(s[i++]);
				updateStage(stage);
				skinId = Integer.parseInt(s[i++]);
				skinList.setSkinId(skinId);
				score = Integer.parseInt(s[i++]);
				playCount = Integer.parseInt(s[i++]);
				enterButton = Integer.parseInt(s[i++]);
				randomSkinId = Integer.parseInt(s[i++]);
				randomSkinId = -1;
				bgmId = Integer.parseInt(s[i++]);
				seVolume = Integer.parseInt(s[i++]);
				volume = Integer.parseInt(s[i++]);
				vibrateEnable = ("1".equals(s[i++]));
				bgmEnable = ("1".equals(s[i++]));
				seEnable = bgmEnable;
				setSoundEnable(seEnable);
				updateCanUseSkinId();
				hiSound = ("1".equals(s[i++]));
				allClearCount = Integer.parseInt(s[i++]);
			}
			catch ( Exception ignore)
			{
				ignore.printStackTrace();
			}
			
			// Stage Clear Data
			for ( i = 0; i < line[1].length(); i++ )
			{
				stageClearFlags[i] = Integer.parseInt(line[1].substring(i, i+1));

				/*
				  if ( i != 0 )
				  {
				  stageClearFlags[i] = 1;
				  }
				  else
				  {
				  stageClearFlags[i] = 0;
				  }
				*/
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public boolean isAllClear()
	{
		for ( int i = 0; i < stageClearFlags.length; i++ )
		{
			if ( stageClearFlags[i] == 0 )
			{
				return false;
			}
		}
		
		return true;
	}
	
	void save()
	{
		try
		{
			StringBuffer saveData = new StringBuffer();
			//System Data
			SaveDataCreator sdc = new SaveDataCreator(':');
			sdc.add(stage);
			sdc.add(skinId);
			sdc.add(score);
			sdc.add(playCount);
			sdc.add(enterButton);
			sdc.add(randomSkinId);
			sdc.add(bgmId);
			sdc.add(seVolume);
			sdc.add(volume);
			sdc.add(vibrateEnable);
			sdc.add(bgmEnable);
			sdc.add(hiSound);
			sdc.add(allClearCount);
			saveData.append(sdc);
			saveData.append(',');
			
			// stage clear data
			for ( int i = 0; i < stageClearFlags.length; i++ )
			{
				saveData.append(stageClearFlags[i]);
			}

			save(RECORD_KEY, saveData.toString());
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public int getBGMSplit(int id)
	{
		return 1;
	}
	
	public void savePlayCount()
	{
		playCount++;
		score += result.getSpecialScore();

		if ( score > 999999999 )
		{
			score = 999999999;
		}

		save();


		if ( canUseSkinId < NEED_POINTDS.length )
		{
			if ( score >= NEED_POINTDS[canUseSkinId] )
			{
				updateCanUseSkinId();
				newSkinGet = true;
			}
		}
	}
	
	public void updateCanUseSkinId()
	{
		canUseSkinId = 0;
		for ( int i = 0; i < NEED_POINTDS.length; i++ )
		{
			if ( score >= NEED_POINTDS[i] )
			{
				canUseSkinId++;
			}
		}
	}
	
	public boolean isGetNewSkin()
	{
		return newSkinGet;
	}

	public void closeMenu()
	{
		playMenu.setVisible(false);
		stageClearMenu.setVisible(false);
		stageFailedMenu.setVisible(false);
		stageSelectMenu.setVisible(false);
	}
	
	public void drawMenu(MenuWindow menu, int[] rows)
	{
		drawImage(IMAGE_MENU, 105,0, 53, 60, 135, 90);
		int twoLine = (rows.length == 2) ? 40 : 20;
		for ( int i = 0; i < rows.length; i++ )
		{
			int addY = (menu.getId() == i) ? 82 : 0;
			drawImage(IMAGE_MENU, 0,13*rows[i] + addY, 68, 78 + (i * twoLine), 105, 13);
		}
	}
	
	public int getBgmId(int stage)
	{
		if ( stage >= 70 )
		{
			return BGM_PLAY2;
		}
		return BGM_PLAY1;
	}
}
