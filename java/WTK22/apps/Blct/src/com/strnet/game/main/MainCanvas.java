/*
 * Last modified: 2010/04/04 13:56:27
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.Point;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Rectangle;
import com.strnet.game.common.SaveDataCreator;
import com.strnet.game.common.StringIterator;
import com.strnet.game.common.list.StringLightList;

import com.strnet.game.io.StartEndReader;

import com.strnet.game.component.ImageData;
import com.strnet.game.component.ImagePatternData;
import com.strnet.game.component.MenuManager;
import com.strnet.game.component.MenuWindow;
import com.strnet.game.component.CenterMessageBox;
import com.strnet.game.component.MessageBox;
import com.strnet.game.component.SelectBox;
import com.strnet.game.component.StrongString;
import com.strnet.game.component.viewer.CommonViewController;
import com.strnet.game.component.viewer.HandScrollViewController;
import com.strnet.game.component.viewer.PreserveViewController;
import com.strnet.game.component.viewer.TabHandScrollViewController;

import java.util.Vector;

public class MainCanvas extends GameCommonCanvas
{
	private static final String RECORD_KEY = "Blct";
	static final int MAX_IMAGE = 30;
	static final String[] TITLE_MENU = new String[]{"アーケード", "ステージセレクト", "ライブラリ", "オプション", "ネットランキング",  "終了"};
	static final String[] MODE_LABEL = new String[]{"Normal", "Hard", "Chaos"};
	static final int[] REQUIRE_PLAY_IMAGES = new int[] {1, 3};
	//static final int[] EXTEND_SCORE_1 = new int[] {2000000, 10000000, 30000000, 50000000};
	static final int[] EXTEND_SCORE_1 = new int[] {10000000, 30000000, 50000000};
	//		"残機、ボムが多いモードです。",
	private static final String[] MODE_EXPLANATION = new String[]
	{
		"初級者にオススメのモードです",
		"腕に自信のある方向けです",
		"頑張ってください"
	};

	private static final String[] RANK_LABEL = new String[]
	{
		"10級",
		"9級",
		"8級",
		"7級",
		"6級",
		"5級",
		"4級",
		"3級",
		"2級",
		"1級",
		"初段", //10
		"二段",
		"三段",
		"四段",
		"五段",
		"六段",
		"七段",
		"八段",
		"九段",
		"十段", //19
		"軍曹(十一)",
		"曹長(十ニ)",
		"准尉(十三)",
		"少尉(十四)",
		"中尉(十五)",
		"中佐(十六)",
		"大佐(十七)",
		"大将(十八)",//25
		"元帥(十九)",
		"大元帥(二十)",
		"覇者(二十一)",
		"覇王(二十ニ)",
		"皇帝(二十三)",
		"仙人(二十四)",
		"神(MAX)",
	};

	MenuManager titleMenu;
	MenuManager modeMenu;
	
	MenuManager libraryMenu;
	MenuManager musicMenu;
	int titleCounter = 0;
	
	public static final int PLAY_STATE_DEFAULT = 0;
	public static final int PLAY_STATE_EVENT_MOVE = 1;
	public static final int PLAY_STATE_EVENT_DIALOG = 2;
	public static final int PLAY_STATE_STATUS = 3;
	public static final int PLAY_STATE_BATTLE = 4;
	public static final int PLAY_STATE_GAMEOVER = 5;
	public static final int PLAY_STATE_STAGE_CLEAR = 6;

	public static final int PLAY_SCREEN_WIDTH = 160;
	public static final int PLAY_SCREEN_HEIGHT = 240;
	public static final int PLAY_SCREEN_X = (240 - PLAY_SCREEN_WIDTH) / 2;
	public static final int PLAY_SCREEN_Y = (240 - PLAY_SCREEN_HEIGHT) / 2;
	public static final Rectangle PLAY_SCREEN_RECT = new Rectangle(PLAY_SCREEN_X, PLAY_SCREEN_Y, PLAY_SCREEN_WIDTH, PLAY_SCREEN_HEIGHT);
	

	public static final int BGM_TITLE = 10;
	public static final int BGM_BOMB = 10;
	public static final int BGM_WARNING = 2;
	public static final int BGM_SCORE_ITEM_MANY = 4;
	public static final int BGM_STAGE_CLEAR = 6;
	public static final int BGM_ENDING = 3;
	public static final Color SCORE_COLOR = Color.WHITE;
	public static final Color SCORE_COUNTER_COLOR = new Color(221,211,153);

	public static final int MODE_UNDEFINED = -1;
	/*EASY
	public static final int MODE_EASY = 0;
	public static final int MODE_ORIGINAL = 1;
	public static final int MODE_HARD = 2;
	public static final int MODE_HELL = 3;
	*/
	public static final int MODE_ORIGINAL = 0;
	public static final int MODE_HARD = 1;
	public static final int MODE_HELL = 2;
	public static final int S_KEY_NONE = -999;
	private static final String[] NAME_CHARACTERS = new String[] {
		"ＡＢＣＤＥＦＧＨＩＪ",
		"ＫＬＭＮＯＰＱＲＳＴ",
		"ＵＶＷＸＹＺ＋‐＊／",
		"０１２３４５６７８９"};
	private static final String[] RANKING_MENU = new String[]{"閲覧", "送信", "ネーム変更"};

	private static final String RANKING_URL = "http://m.strnet.com/u/sra.php";
	private static final String VIEW_RANKING_URL = "http://m.strnet.com/u/stg_ranking.php?a=my&GUID=ON";

	static final int maxStage = 5;
	static final int TRUE_BOSS_STAGE = 6;

	MenuWindow nameCharMenu;
	MenuWindow rankingMenu;
	StartEndReader textContentsReader;
	private static MainCanvas instance = null;
	
	int playState = PLAY_STATE_DEFAULT;
	int nextPlayState = PLAY_STATE_DEFAULT;
	//long programExecTime;
	//long paintExecTime;

	StringLightList systemMsgs;
	MyData my;
	private int myCounter;
	Score score;
	StageManager stageManager;
	boolean needHyperGauge = false;
	int needHyperGaugeBackup = 0;
	boolean needAllScoreItemFlag = false;
	boolean needAllScoreItemForceFlag = false;
	boolean needPaintLeftField = false;
	boolean needPaintScoreCounter = false;
	MenuWindow bgmVolumeMenu;
	MenuWindow seVolumeMenu;
	MenuWindow changeSpeedKeyMenu;
	MenuWindow bombKeyMenu;
	MenuWindow shotSwitchKeyMenu;
	MenuWindow optionMenu;
	int[] availableStages;
	int stageNo;
	int scoreItemSize;
	boolean pause;
	boolean numberMove;
	int changeSpeedKey;
	int bombKey;
	int shotSwitchKey;
	int mode;
	//boolean autoBomb;
	String playerName;
	String warningMsg = null;
	StrongString strongStr;
	StrongString strongNext;
	EndingData ending;
	boolean exitOmake = false;
	
	int highScore = 0;

	int maxFighter = 2;
	int selectedFighter = 0;
	boolean resetPlay;
	MyData[] fighters = new MyData[3];
	FighterInformation rankingFighter;
	boolean extend;
	boolean goTitle = false;
	BackgroundData commonBg;
	boolean fighterSelected;
	boolean arcard;
	int stageIndex;
	
	private boolean canMusicMode;
	private boolean canLaser2MyData;
	private boolean canChaosMode;
	private boolean tutorial;
	private boolean playSoftKey;
	private boolean needSave = false;
	int getBombCounter = 0;
	int get1UPCounter = 0;
	int endingPattern = 0;
	int selectedLibraryId = -1;
	CommonViewController viewController;

	/*EASY
	private boolean canEasyMode;
	public void onCanEasyMode()
	{
		if ( arcard )
		{
			canEasyMode = true;
		}
	}
	*/
	public MainCanvas(Main app)
	{
		super(app, S_STORY, MAX_IMAGE,REQUIRE_PLAY_IMAGES);
		setWaitTime(50);
		MainCanvas.instance = this;
	}
	
	public static MainCanvas getInstance()
	{
		return instance;
	}


	void init()
	{
		String VALIDATE_KEY = "6302ad10473075e1045308ca104030e22204040f05204510c0403";
		
		FixRandom.setSeed();
		setBGMVolumeLevel(4);
		setSEVolumeLevel(4);
		changeSpeedKey = S_KEY_FIRE;
		bombKey = S_KEY_STAR;
		shotSwitchKey = S_KEY_NONE;
		//autoBomb = true;
		numberMove = false;
		canMusicMode = false;
		canLaser2MyData = false;
		//EASY canEasyMode = false;
		tutorial = true;
		playSoftKey = true;
		playerName = "";
		String webLabel = Main.isDxMode() ? "" : "WEB";
		strongStr = new StrongString(new Color(180,255,180), Color.BLACK);
		strongNext = new StrongString(new Color(255,180,180), Color.BLACK);
		super.init(new String[]{
				"♪○", "♪×", webLabel, "ﾀｲﾄﾙ", "ﾒﾆｭｰ",
				"終了", "再開", "ﾘﾄﾗｲ", "ﾀｲﾄﾙ", "戻る",
				"決定", "Yes", "No", "戻る","", "音１",
				"音２", "中止", "ﾀｲﾄﾙ", "SKIP"}, null);
		setFont(S_FONT_SMALL);
		
		commonBg = new BackgroundData(240,240);
		commonBg.setImageId(23);
		commonBg.setSpeed(1);
		
		systemMsgs = new StringLightList();
		titleMenu = new MenuManager(TITLE_MENU, true);
		//EASY modeMenu = new MenuManager(new String[]{"イージー", "スタンダード", "ハード", "カオス"}, true);
		modeMenu = new MenuManager(MODE_LABEL, true);

		musicMenu = new MenuManager(new String[]
			{"PROLOGUE",
			 "STAGE 1",
			 "STAGE 2",
			 "1&2 BOSS",
			 "STAGE 3",
			 "STAGE 4",
			 "3&4 BOSS",
			 "STAGE 5",
			 "5 BOSS",
			 "TRUE BOSS",
			 "EPILOGUE"}, true);
		libraryMenu = new MenuManager(new String[]
			{
				"マニュアル",
				"プロローグ",
				"レイリック・ラチカ",
				"ゲイリー・ラウル",
				"ビレーゼ・ノマック",
				"Tranceed",
				"Nerverity",
				"サウンドトラック"
			}, true);
		
		CharacterData.setScreen(PLAY_SCREEN_X, PLAY_SCREEN_Y, PLAY_SCREEN_WIDTH, PLAY_SCREEN_HEIGHT);
		ObjectPool.init();

		fighters[0] = new LaserMyData();
		fighters[1] = new NormalMyData();
		fighters[2] = new Laser2MyData();
		
		score = new Score();

		StageManager.init(PLAY_SCREEN_WIDTH, PLAY_SCREEN_HEIGHT);
		stageManager = StageManager.getInstance();

		

		stageManager.setHPGauge(new HPGauge(PLAY_SCREEN_X + 3, PLAY_SCREEN_Y + 15,
											new Rectangle(0,166,154,13),
											new Rectangle(0,179,142,7), 1));


		optionMenu = new MenuWindow(1, 11, true);
		bgmVolumeMenu = new MenuWindow(5, 1, false);
		seVolumeMenu = new MenuWindow(5, 1, false);
		changeSpeedKeyMenu = new MenuWindow(6, 1, true);
		bombKeyMenu = new MenuWindow(6, 1, true);
		shotSwitchKeyMenu = new MenuWindow(6, 1, true);
		nameCharMenu = new MenuWindow(10, 4, true);
		rankingMenu = new MenuWindow(1, 3, false);
		
		Dictionary.init();

		try
		{
			// text valid check
			StringBuffer longkey = new StringBuffer();
			for ( int i = 1; i <= 6; i++ )
			{
				String key = GameUtil.makeTextKey(readTextFile(i + ".dat"));
				longkey.append(key);
			}
			if ( !validateText(longkey.toString(), VALIDATE_KEY) )
			{
				return;
			}
			
		
			String textContents = readTextFile("e.dat");
			textContentsReader = new StartEndReader(textContents, false);
			EndingData.initialize(textContentsReader);
		}
		catch ( Exception e )
		{
			error(e, "ロードに失敗");
		}

		load();

		if ( FighterInformationManager.size() == 0 )
		{
			for ( int i = 0; i <= 2; i++ )
			{
				//EASY FighterInformationManager.add(new FighterInformation(i, MODE_EASY));
				FighterInformationManager.add(new FighterInformation(i, MODE_ORIGINAL));
				FighterInformationManager.add(new FighterInformation(i, MODE_HARD));
				FighterInformationManager.add(new FighterInformation(i, MODE_HELL));
			}
		}
		
		if ( !tutorial )
		{
			firstScene = S_PRESS_FIRE_KEY;
		}
		/*
		firstScene = S_ENDING;
		endingPattern = 1;
		selectedFighter = 2;
		*/
		/*
		try
		{
			loadBGM(BGM_TITLE);
			loadPlaySE();
		} catch ( Exception ignore ) { ignore.printStackTrace(); }
		*/
		initialized = true;
	}

	private void loadPlaySE()
	{
		try
		{
			loadSE(BGM_SCORE_ITEM_MANY);
			//loadSecondSE(BGM_BOMB);
		}
		catch ( Exception ignore ) {ignore.printStackTrace();}
	}
	
	void setKeyMenu(MenuWindow menu, int key)
	{
		switch ( key )
		{
		case S_KEY_NONE:
			menu.setId(0,0);
			break;
		case S_KEY_FIRE:
			menu.setId(0,1);
			break;
		case S_KEY_STAR:
			menu.setId(0,2);
			break;
		case S_KEY_0:
			menu.setId(0,3);
			break;
		case S_KEY_POUND:
			menu.setId(0,4);
			break;
		case S_KEY_5:
			menu.setId(0,5);
			break;
		}
	}

	private static final String[] KEY_LABELS = new String[]{"無効", "決定", " ＊ ", " ０ ", " ＃ ", " ５ "};
	private static final int[] KEY_LABEL_ID = new int[]{S_KEY_NONE, S_KEY_FIRE, S_KEY_STAR, S_KEY_0, S_KEY_POUND, S_KEY_5};
	String getKeyMenuLabel(MenuWindow menu)
	{
		return KEY_LABELS[menu.getId()];
	}

	int getKey(MenuWindow menu)
	{
		return KEY_LABEL_ID[menu.getId()];
	}
	
	int checkDuplicateKeySetting(MenuWindow target, MenuWindow other1, MenuWindow other2)
	{
		if ( target.getId() > 0 )
		{
			if ( (target.getId() == other1.getId()) ||
				 (target.getId() == other2.getId()) )
			{
				return target.getId();
			}
		}
		if ( other1.getId() > 0 )
		{
			if ( other1.getId() == other2.getId() )
			{
				return other1.getId();
			}
		}
		return 0;
	}

	public void resetBomb()
	{
		//EASY my.setBomb((mode == MODE_EASY) ? 3 : 2);
		my.setBomb(2);
	}
	
	void retry()
	{
		goTitle = false;
		my.resetSpeed();
		//EASY my.setRemainder((mode == MODE_EASY) ? 3 : 2);
		my.setRemainder(2);
		my.resetScoreCounter();
		my.setHyperGauge(((stageNo == 1) ? 100 : 0));
		score.reset();
		stageManager.reset();
		stageManager.resetRank();
		resetBomb();
		my.resetNoTrance();
		my.resetNoMiss();
		extend = true;
		resetPlay = true;
	}

	void nextStage()
	{
		if ( stageNo < maxStage )
		{
			stageNo++;
		}
	}
	
	void reset()
	{
		my.resetSpeed();
		pause = false;
		stageManager.setStage(stageNo, mode);

		ObjectPool.destroyAll(ObjectPool.myBullets);
		ObjectPool.destroyAll(ObjectPool.enemies);
		ObjectPool.destroyAll(ObjectPool.bullets);
		ObjectPool.destroyAll(ObjectPool.bursts);
		ObjectPool.destroyAll(ObjectPool.bombItemData);

		myCounter = 0;
		needAllScoreItemFlag = false;
		needAllScoreItemForceFlag = false;
		needPaintLeftField();
		
		my.preLoad();
		my.reset();
		my.destroy();

		Dictionary.preLoadImages();
		stageManager.reset();
	}
	
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	
	public int getMode()
	{
		return mode;
	}

	public void execTitle() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			switch ( titleMenu.getId() )
			{
			case 0:
				arcard = true;
				setMode(MODE_UNDEFINED);
				setScene(S_CHARACTER_SELECT);
				break;
			case 1:
				arcard = false;
				setMode(MODE_UNDEFINED);
				fighterSelected = false;
				setScene(S_STAGE_SELECT);
				break;
			case 2:
				setScene(S_LIBRARY);
				break;
			case 3:
				setScene(S_OPTION);
				break;
			case 4:
				setScene(S_RANKING);
				break;
			case 5:
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
			//drawString("＞ ", x - wideFontWidth - 2, y);
			// カーソル
			drawImage(0, 80,23, x - 16,y, 12,12);
		}
		else
		{
			//setColor(255,249,201);
			setColor(Color.WHITE);
		}
		drawString(label, x, y);
	}

	public void drawCommonBackground()
	{
		resetScreen();
		commonBg.execute();
		commonBg.paint(this, 0,0);
	}

	public void doPaintTitle()
	{
		drawCommonBackground();
		int x = 60;
		int y = 110;
		for ( int i = 0; i < titleMenu.getLabelLength(); i++ )
		{
			if ( !titleMenu.isHide(i) )
			{
				drawSelectedMenu(i, x, y, titleMenu);
				y += 14;
			}
		}
		setColor(Color.LIGHT_GRAY);
		
		drawString("Copyright (C) 2010 STR", 50, 205);
		drawString(" http://m.strnet.com/", 50, 220);
		
		// タイトル
		drawImage(0, 0,165, 11,20, 218,74);
	}
	
	public void stageClear() throws Exception
	{
		stopBGM();
		//startBGM(BGM_STAGE_CLEAR);
		setEqualSEVolume();
		startSE(BGM_STAGE_CLEAR);
		if ( arcard )
		{
			if ( my.isNoMiss() )
			{
				addScore(1000000);
			}
		
			addScore(my.getScoreCounter() * 100);
			
			addScoreAll();
			checkExtend();
			setPlayState(PLAY_STATE_STAGE_CLEAR);
			FighterInformation fi = getSelectedInfo();
			if ( (stageNo + 1) <= maxStage )
			{
				fi.setAvailableStage(stageNo + 1);
			}
			else if ( (stageNo == maxStage) && !resetPlay )
			{
				fi.setTrueBoss(true);
				if ( !canMusicMode )
				{
					canMusicMode = true;
					systemMsgs.add("サウンドトラックが解禁しました");
				}
			}
			else if ( stageNo == TRUE_BOSS_STAGE )
			{
				if ( !resetPlay )
				{
					fi.setCleared(true);
					fi.setNoTrance(my.isNoTrance());
				}
				if ( !canLaser2MyData )
				{
					canLaser2MyData = true;
					systemMsgs.add("新機体が解禁しました");
				}
			}

			if ( (stageNo == maxStage) && (mode == MODE_HARD) && !canChaosMode )
			{
				canChaosMode = true;
				systemMsgs.add("Chaosモードが解禁しました");
			}
		}
		else
		{
			addScoreAll();
			setPlayState(PLAY_STATE_STAGE_CLEAR);
		}
		updateHighScore(false);
	}

	protected void setScene(int next) throws Exception
	{
		if ( (next == S_TITLE) && (systemMsgs.size() > 0) && !exitOmake )
		{
			setScene(S_OMAKE, false);
		}
		else
		{
			setScene(next, false);
		}
	}

	public void execOmake() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			exitOmake = true;
			setScene(S_TITLE);
		}
		doPaintOmake();
	}
	
	public void doPaintOmake() throws Exception
	{
		drawCommonBackground();

		for ( int i = 0; i < systemMsgs.size(); i++ )
		{
			String s = systemMsgs.get(i);
			drawBoldString(s, 20, 50 + (i * 18));
		}
	}

	private void drawBoldString(String str, int x, int y)
	{
		setColor(255,180,180);
		drawString(str, x+1, y);
		setColor(Color.WHITE);
		drawString(str, x, y);
	}

	private void drawBoldString2(String str, int x, int y)
	{
		setColor(255,180,180);
		drawString(str, x+1, y);
		setColor(Color.LIGHT_GRAY);
		drawString(str, x, y);
	}

	public void sceneLoad(int scene) throws Exception
	{
		try
		{
			restoreSEVolume();
			stopSE();
		}catch ( Exception ignore ) {}
		if ( needSave )
		{
			save();
			needSave = false;
		}
		switch ( scene )
		{
		case S_PLAY:
			try
			{
				reset();
				loadImages();
			}
			catch ( Exception e )
			{
				error(e, "画像の読み込みに失敗");
			}
			try
			{
				loadBGM(stageManager.getStageBGM());
				loadPlaySE();
			}
			catch ( Exception e )
			{
				error(e, "音楽の読み込みに失敗");
			}
			
			playState = nextPlayState;

			break;
		case S_PRESS_FIRE_KEY:
		case S_TITLE:
			stageNo = 1;
			commonBg.reset();
			if ( exitOmake )
			{
				systemMsgs.clear();
			}
			exitOmake = false;
			// 隠し要素
			modeMenu.setShow(2, canChaosMode);
			/*EASY
			modeMenu.setShow(3, canChaosMode);
			modeMenu.setShow(0, canEasyMode);
			*/
			maxFighter = (canLaser2MyData ? 3 : 2);

			if ( scene == S_TITLE )
			{
				if ( nowBGM != BGM_TITLE )
				{
					startBGM(BGM_TITLE);
				}
			}
			break;
		case S_LIBRARY:
			commonBg.reset();
			libraryMenu.setShow(4, canLaser2MyData);
			libraryMenu.setShow(7, canMusicMode);
			libraryMenu.resetId();
			selectedLibraryId = -1;
			if ( nowBGM != BGM_TITLE )
			{
				startBGM(BGM_TITLE);
			}
			break;
		case S_MUSIC_ROOM:
			commonBg.reset();
			musicMenu.resetId();
			break;
		case S_MANUAL:
			commonBg.reset();
			viewController = new TabHandScrollViewController();
			viewController.setAll(textContentsReader.get(":MANUAL"), 30, 40, 15, Color.WHITE);
			break;
		case S_OMAKE:
			commonBg.reset();
			exitOmake = false;
			break;
		case S_STAGE_SELECT:
			try
			{
				if ( nowBGM != BGM_TITLE )
				{
					startBGM(BGM_TITLE);
				}
			} catch ( Exception ignore ) {}
			break;
		case S_OPTION:
			optionMenu.resetId();
			bgmVolumeMenu.setId(0, getVolumeLevel());
			seVolumeMenu.setId(0, getSEVolumeLevel());
			setKeyMenu(changeSpeedKeyMenu, changeSpeedKey);
			setKeyMenu(bombKeyMenu, bombKey);
			setKeyMenu(shotSwitchKeyMenu, shotSwitchKey);
			warningMsg = null;
			break;
		case S_RANKING:
			rankingMenu.resetId();
			warningMsg = null;
			rankingFighter = FighterInformationManager.getHighScore();
			break;
		case S_NAME_INPUT:
			nameCharMenu.resetId();
			break;
		case S_ENDING:
			commonBg.reset();
			ending = new EndingData(endingPattern, selectedFighter);
			startBGM(BGM_ENDING);
			break;
		case S_STORY:
			commonBg.reset();
			viewController = new PreserveViewController();
			viewController.setAll(textContentsReader.get(":PROLOGUE"), 30, 240, 28, Color.BLACK);
			break;
		case S_TUTORIAL:
			commonBg.reset();
			String key = "";
			switch ( selectedFighter )
			{
			case 0:
				key = ":PROFILE_RACHICA";
				break;
			case 1:
				key = ":PROFILE_RAUL";
				break;
			case 2:
				key = ":PROFILE_NOMAC";
				break;
			}
			StringLightList list = textContentsReader.get(key);
			StringLightList tmp = new StringLightList();
			for ( int i = 8; i < list.size(); i++ )
			{
				tmp.add(list.get(i));
			}
			viewController = new PreserveViewController();
			viewController.setAll(tmp, 30, 240, 26, Color.BLACK);
			break;
		case S_INIT:
			break;
		}
	}
	
	public void execRanking() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			switch ( rankingMenu.getId() )
			{
			case 0:
				browser(VIEW_RANKING_URL);
				break;
			case 1:
				if ( playerName.length() <= 0 )
				{
					warningMsg = "名前を入力してください";
				}
				else if ( rankingFighter == null )
				{
					warningMsg = "プレイ記録がありません";
				}
				else
				{
					SaveDataCreator sdc = new SaveDataCreator(':');
					sdc.add(playerName);
					sdc.add(getPlatform());
					sdc.add(GameUtil.getEncodeScore(rankingFighter.getHighScore()));
					sdc.add(rankingFighter.getMode());
					sdc.add(rankingFighter.getFighterId());

					String value = sdc.toString();
					doGetBrowser(RANKING_URL, value);
				}
				break;
			case 2:
				setScene(S_NAME_INPUT);
				break;
			}
			keyReset();
		}
		else
		{
			rankingMenu.move(this);
			keyReset();
		}
		doPaintRanking();
	}

	public void doPaintRanking() throws Exception
	{
		drawCommonBackground();
		//タイトル
		drawImage(0, 0,110, 57,11, 125,17);

		drawBoldString("BEST SCORE", 50, 55);
		
		setColor(Color.WHITE);
		if ( rankingFighter != null )
		{
			drawImage(0, 183,(13*rankingFighter.getMode()), 120, 55, 55,13);

			drawImage(fighters[rankingFighter.getFighterId()].getImagePatternData(), 0, 50, 70);

			drawString(getRankLabel(rankingFighter.getRank()), 80, 85);
			drawString("HI-SCORE: " + rankingFighter.getHighScore(), 80, 70);
		}

		setColor(Color.WHITE);
		drawString("NAME: " + playerName, 80, 100);

		if ( warningMsg != null )
		{
			setColor(Color.RED);
			drawString(warningMsg, 50, 120);
		}
		
		int x = 70;
		int y = 140;
		for ( int i = 0; i < RANKING_MENU.length; i++ )
		{
			drawSelectedMenu(i, x, y, RANKING_MENU, rankingMenu);
			y += 15;
		}

		setColor(Color.LIGHT_GRAY);
		drawString("※通信に失敗した場合は", 50, 195);
		drawString("　しばらく時間をおいて", 50, 209);
		drawString("　再度アクセスください", 50, 223);
	}
	
	private void execMode()
	{
		switch ( getKeyEvent() )
		{
		case S_KEY_FIRE:
			setMode(modeMenu.getId());
			keyReset();
			break;
		case S_KEY_UP:
			modeMenu.move(this);
			keyReset();
			break;
		case S_KEY_DOWN:
			modeMenu.move(this);
			keyReset();
			break;
		}
	}

	public void execCharacterSelect() throws Exception
	{
		// アーケードモード
		if ( mode == MODE_UNDEFINED )
		{
			execMode();
		}
		else
		{
			switch ( getKeyEvent() )
			{
			case S_KEY_FIRE:
				my = fighters[selectedFighter];
				FighterInformation fi = getSelectedInfo();
				highScore = fi.getHighScore();
				retry();
				reset();
				resetPlay = false;
				
				setScene((tutorial) ? S_TUTORIAL : S_PLAY);
				//setScene(S_PLAY);
				keyReset();
				break;
			case S_KEY_LEFT:
				selectedFighter = GameUtil.loopDecf(selectedFighter, 0, maxFighter);
				keyReset();
				break;
			case S_KEY_RIGHT:
				selectedFighter = GameUtil.loopIncf(selectedFighter, 0, maxFighter);
				keyReset();
				break;
			}
		}
		doPaintCharacterSelect();
	}

	private int keyToKeyId(int key)
	{
		int keyId = 0;
		for ( int i = 0; i < KEY_LABEL_ID.length; i++ )
		{
			if ( KEY_LABEL_ID[i] == key )
			{
				keyId = i;
				break;
			}
		}
		return keyId;
	}

	private boolean drawCustomizeKey(int x, int y, String name, int key)
	{
		int keyId = keyToKeyId(key);
		if ( keyId > 0 )
		{
			StringBuffer sb = new StringBuffer();
			sb.append(name);
			sb.append(" [");
			sb.append(KEY_LABELS[keyId]);
			sb.append("]");
			drawString(sb.toString(), x, y);
			return true;
		}
		return false;
	}
	
	public void doPaintCharacterSelect() throws Exception
	{
		drawCommonBackground();
		if ( mode == MODE_UNDEFINED )
		{
			//タイトル
			drawImage(0, 0,65, 75,11, 90,13);

			for ( int i = 0; i < modeMenu.getLabelLength(); i++ )
			{

				if ( !modeMenu.isHide(i) )
				{
					int y = ( modeMenu.getId() == i ) ? 0 : 39;
					drawImage(0, 185,y+(13*i), 91, 75 + (30 * i), 55,13);
				}
			}
			// カーソル
			drawImage(0, 80,23, 91 - 16,75+(30*modeMenu.getId()), 12,12);

			// 説明
			setColor(Color.LIGHT_GRAY);
			drawString(MODE_EXPLANATION[modeMenu.getId()], 30, 182);
			if ( arcard )
			{
				drawString("1UP SCORE: " + GameUtil.formatMoney(EXTEND_SCORE_1[modeMenu.getId()]), 30, 197);
				/*EASY
				if ( modeMenu.getId() == MODE_EASY )
				{
					drawString("※ランキングには反映されません", 24, 212);
				}
				*/
			}
		}
		else
		{
			// タイトル
			drawImage(0, 0,0, 69,11, 101,17);

			// ハイスコア
			setColor(Color.WHITE);
			FighterInformation fi = getSelectedInfo();
			highScore = fi.getHighScore();

			StringBuffer sb = new StringBuffer(26);
			sb.append("HI-");
			sb.append(GameUtil.leftPad(9, ' ', highScore));
			drawString(sb.toString(), 84, 30);
			drawString(getRankLabel(fi.getRank()), 84, 45);
			
			// 勲章
			if ( fi.isCleared() )
			{
				//drawImage(0, 48, 46, 36,43, 16,16);
				drawImage(0, 98, 44, 36,40, 13,19);
			}
			if ( fi.isNoTrance() )
			{
				//drawImage(0, 64, 46, 54,43, 16,16);
				drawImage(0, 114, 44, 54,40, 13,19);
			}

			int wid = (maxFighter == 2) ? 70 : 50;
			for ( int i = 0; i < maxFighter; i++ )
			{
				int hosei = (i != 0) ? 2 : 0;
				drawImage(fighters[i].getImagePatternData(), 0,
						  wid + (i * wid) + hosei, 70);
			}
			drawImage(0, 0,22, (wid - 7) + (selectedFighter * wid),65, 42,42);

			setColor(255,180,180);
			drawString("搭乗者:" + fighters[selectedFighter].getDescription(), 40, 110);
			
			setColor(Color.LIGHT_GRAY);
			int x = 50;
			int y = 130;
			if ( drawCustomizeKey(x, y, "速度変更キー", changeSpeedKey) )
			{
				y += 14;
			}
			if ( drawCustomizeKey(x, y, "トランスキー", bombKey) )
			{
				y += 14;
			}
			drawCustomizeKey(x, y, "ショットオンオフキー", shotSwitchKey);

			setColor(Color.WHITE);
			y = 175;
			drawString("Code Name  :", 40, y);
			drawString(fighters[selectedFighter].getName(), 112, y);
			y+= 15;
			drawString("Speed      :", 40, y);
			for ( int i = 0; i < fighters[selectedFighter].getSlowSpeed(); i++ )
			{
				drawImage(0, 52,22, 113 + (i * 8),y ,6,12);
			}
			y+= 15;
			drawString("Fast Weapon:", 40, y);
			drawString(fighters[selectedFighter].getFastWeaponName(), 112, y);
			y+= 15;
			drawString("Slow Weapon:", 40, y);
			drawString(fighters[selectedFighter].getSlowWeaponName(), 112, y);
		
		}
	}

	public void execStageSelect() throws Exception
	{
		if ( mode == MODE_UNDEFINED )
		{
			execMode();
		}
		else if ( !fighterSelected )
		{
			switch ( getKeyEvent() )
			{
			case S_KEY_FIRE:
				my = fighters[selectedFighter];
				fighterSelected = true;
				stageIndex = 0;
				makeAvailableStages();
				keyReset();
				break;
			case S_KEY_LEFT:
				selectedFighter = GameUtil.loopDecf(selectedFighter, 0, maxFighter);
				keyReset();
				break;
			case S_KEY_RIGHT:
				selectedFighter = GameUtil.loopIncf(selectedFighter, 0, maxFighter);
				keyReset();
				break;
			}
		}
		else
		{
			switch ( getKeyEvent() )
			{
			case S_KEY_FIRE:
				stageNo = availableStages[stageIndex];
				retry();
				reset();
				
				setScene(S_PLAY);
				keyReset();
				break;
			case S_KEY_DOWN:
				if ( stageIndex < (availableStages.length - 1) )
				{
					 stageIndex++;
				}
				else
				{
					stageIndex = 0;
				}
				keyReset();
				break;
			case S_KEY_UP:
				if ( stageIndex > 0 )
				{
					stageIndex--;
				}
				else
				{
					stageIndex = availableStages.length - 1;
				}
				keyReset();
			}
		}
		doPaintStageSelect();
	}
	
	public void doPaintStageSelect() throws Exception
	{
		if ( fighterSelected )
		{
			drawCommonBackground();
			// タイトル
			drawImage(0, 0,80, 75,11, 89,16);
			int x = 50;
			int y = 60;
			int row = 0;
			setColor(Color.WHITE);
			for ( int i = 0; i < availableStages.length; i++ )
			{
				StringBuffer sb = new StringBuffer();
				if ( availableStages[i] < FighterInformationManager.ADD_BOSS_STAGE )
				{
					sb.append("STAGE ");
					sb.append(availableStages[i]);
				}
				else
				{
					sb.append(' ');
					sb.append(availableStages[i] - FighterInformationManager.ADD_BOSS_STAGE);
					sb.append(" BOSS ONLY");
				}

				if ( row == stageIndex )
				{
					setColor(Color.GREEN);
					// カーソル
					drawImage(0, 80,23, x - 16,y, 12,12);
				}
				else
				{
					setColor(Color.WHITE);
				}
				drawString(sb.toString(), x, y);
				y += 15;
				row++;
			}
		}
		else
		{
			doPaintCharacterSelect();
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
					goTitle = false;
					setScene(S_TITLE);
				}
				keyReset();
			}
		}
		doPaintEnding();
	}
	
	public void doPaintEnding() throws Exception
	{
		drawCommonBackground();
		ending.paint(this);
		if ( !ending.next() )
		{
			goTitle = false;
			setScene(S_TITLE);
		}
	}

	public void execManual() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			setScene(S_LIBRARY);
			keyReset();
		}
		else
		{
			viewController.move(this);
			keyReset();
		}
		doPaintManual();
	}
	
	public void doPaintManual() throws Exception
	{
		drawCommonBackground();
		viewController.paintAll(this);
	}
	
	public void execLibrary() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			if ( selectedLibraryId >= 0 )
			{
				selectedLibraryId = -1;
			}
			else
			{
				selectedLibraryId = libraryMenu.getId();
				switch ( libraryMenu.getId() )
				{
				case 0:
					//setScrollStory(":MANUAL");
					setScene(S_MANUAL);
					selectedLibraryId = -1;
					break;
				case 1:
					setScrollStory(":PROLOGUE");
					break;
				case 2:
					setScrollStory(":PROFILE_RACHICA");
					break;
				case 3:
					setScrollStory(":PROFILE_RAUL");
					break;
				case 4:
					setScrollStory(":PROFILE_NOMAC");
					break;
				case 5:
					setScrollStory(":REPORT_TRANCEED");
					break;
				case 6:
					setScrollStory(":REPORT_NERVERITY");
					break;
				case 7:
					setScene(S_MUSIC_ROOM);
					selectedLibraryId = -1;
					break;
				}
			}
			keyReset();
		}
		else if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}
		else if ( selectedLibraryId == -1 )
		{
			libraryMenu.move(this);
			keyReset();
		}
		else if ( selectedLibraryId >= 1 && selectedLibraryId < 7 )
		{
			viewController.move(this);
			keyReset();
		}
		doPaintLibrary();
	}

	private void setScrollStory(String key)
	{
		viewController = new HandScrollViewController();
		viewController.setAll(textContentsReader.get(key), 30, 40, 22, Color.WHITE);
	}

	public void doPaintLibrary() throws Exception
	{
		drawCommonBackground();
		// タイトル
		drawImage(0, 0,130, 93,11, 54,17);
		
		switch ( selectedLibraryId )
		{
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			viewController.paintAll(this);
			break;
		default:
			int x = 60;
			int y = 40;
			drawSelectedMenu(0, x, y, libraryMenu);
			y += 15;
			y += 15;
			drawSelectedMenu(1, x, y, libraryMenu);
			y += 15;
			y += 15;
			
			drawSelectedMenu(2, x, y, libraryMenu);
			y += 15;
			drawSelectedMenu(3, x, y, libraryMenu);
			y += 15;
			if ( libraryMenu.isHide(4) )
			{
				setColor(Color.WHITE);
				drawString("？？？？？？？？？", x, y);
			}
			else
			{
				drawSelectedMenu(4, x, y, libraryMenu);
			}
			y += 15;
			y += 10;

			drawSelectedMenu(5, x, y, libraryMenu);
			y += 15;
			drawSelectedMenu(6, x, y, libraryMenu);
			y += 15;
			y += 10;

			if ( libraryMenu.isHide(7) )
			{
				setColor(Color.WHITE);
				drawString("？？？？？？？？", x, y);
			}
			else
			{
				drawSelectedMenu(7, x, y, libraryMenu);
			}
			y+= 15;
			if ( "au".equals(getCarrier()) )
			{
				try
				{
					long cur = Long.parseLong(System.getProperty("com.kddi.vm.netuse.current"));
					long tot = Long.parseLong(System.getProperty("com.kddi.vm.netuse.max"));
					cur = tot - cur;
					cur /= 1024;
					setColor(Color.LIGHT_GRAY);
					drawString("au本日残り通信量 " + cur + "KB", x, y);
				}catch(Exception ignore) {}
			}
			break;
		}
	}

	public void execStory() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			setScene(S_PRESS_FIRE_KEY);
		}
		doPaintStory();
	}

	public void doPaintStory() throws Exception
	{
		drawCommonBackground();
		if ( !viewController.paintAll(this) )
		{
			setScene(S_PRESS_FIRE_KEY);
		}
	}
	
	private void doPaintTextContents(StringLightList list)
	{
		int x = 30;
		int y = 30;
		setColor(Color.WHITE);
		for ( int i = 0; i < list.size(); i++ )
		{
			drawString(list.get(i), x, y);
			y += 15;
		}
	}

	public void execMusicRoom() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			int[] midiIds = new int[]
				{BGM_TITLE,
				 1,5,0,7,8,12,9,11,13,
				BGM_ENDING};
			startBGM(midiIds[musicMenu.getId()]);
			keyReset();
		}
		else if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}
		else
		{
			musicMenu.move(this);
			keyReset();
		}

		doPaintMusicRoom();
	}

	public void doPaintMusicRoom() throws Exception
	{
		drawCommonBackground();

		// タイトル
		drawImage(0, 0,149, 78,11, 84,13);

		int x = 60;
		int y = 50;
		for ( int i = 0; i < musicMenu.getLabelLength(); i++ )
		{
			if ( !musicMenu.isHide(i) )
			{
				drawSelectedMenu(i, x, y, musicMenu);
				y += 15;
			}
		}
	}

	public void execNameInput() throws Exception
	{
		if ( nameCharMenu.move(this) )
		{
			keyReset();
		}
		else if ( getKeyEvent() == S_KEY_FIRE )
		{
			if ( playerName.length() < 8 )
			{
				playerName += NAME_CHARACTERS[nameCharMenu.getRow()].substring(nameCharMenu.getCol(), nameCharMenu.getCol() + 1);
			}
			keyReset();
		}
		doPaintNameInput();
	}
	
	public void doPaintNameInput() throws Exception
	{
		drawCommonBackground();
		setColor(Color.DARK_GRAY);
		drawString("NAME: " + playerName, 61, 31);
		setColor(Color.WHITE);
		drawString("NAME: " + playerName, 60, 30);

		setColor(Color.GRAY);
		drawString("名前を入力してください", 60, 60);
		drawString("(8文字まで入力可)", 60, 73);
		
		int x = 60;
		int y = 103;
		for ( int i = 0; i < NAME_CHARACTERS.length; i++ )
		{
			setColor(Color.DARK_GRAY);
			drawString(NAME_CHARACTERS[i], x + 1, y + 1);
			setColor(Color.WHITE);
			drawString(NAME_CHARACTERS[i], x, y);
			y += 24;
		}
		y = 103;
		setColor(Color.GREEN);
		drawRect(x + nameCharMenu.getCol() * 12 - 1, y + nameCharMenu.getRow() * 24 - 1, 14, 14);
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
				setBGMVolumeLevel(bgmVolumeMenu.getId());
				break;
			case 1://SE
				seVolumeMenu.move(this);
				setSEVolumeLevel(seVolumeMenu.getId());
				break;
			case 2://Vib
				if ( savedVibrateEnable && (ev == S_KEY_RIGHT) )
				{
					setVibrateEnable(false);
				}
				else if ( !savedVibrateEnable && (ev == S_KEY_LEFT) )
				{
					setVibrateEnable(true);
					vibrate(100);
				}
				break;
			case 3://数字キー
				if ( numberMove && (ev == S_KEY_RIGHT) )
				{
					numberMove = false;
				}
				else if ( !numberMove && (ev == S_KEY_LEFT) )
				{
					numberMove = true;
				}
				break;
				/*
			case 3:// auto bomb
				if ( autoBomb && (ev == S_KEY_RIGHT) )
				{
					autoBomb = false;
				}
				else if ( !autoBomb && (ev == S_KEY_LEFT) )
				{
					autoBomb = true;
				}
				break;
				*/
			case 4:// 背景描画
				if ( stageManager.canPaintBackground() && (ev == S_KEY_RIGHT) )
				{
					stageManager.setCanPaintBackground(false);
				}
				else if ( !stageManager.canPaintBackground() && (ev == S_KEY_LEFT) )
				{
					stageManager.setCanPaintBackground(true);
				}
				break;
			case 5: // オープニング
				if ( tutorial && (ev == S_KEY_RIGHT) )
				{
					tutorial = false;
				}
				else if ( !tutorial && (ev == S_KEY_LEFT) )
				{
					tutorial = true;
				}
				break;
			case 6:// プレイ中ソフトキー
				if ( playSoftKey && (ev == S_KEY_RIGHT) )
				{
					playSoftKey = false;
				}
				else if ( !playSoftKey && (ev == S_KEY_LEFT) )
				{
					playSoftKey = true;
				}
				break;
			case 7:// 速度変更キー
				changeSpeedKeyMenu.move(this);
				changeSpeedKey = getKey(changeSpeedKeyMenu);
				break;
			case 8:// ボムキー
				bombKeyMenu.move(this);
				bombKey = getKey(bombKeyMenu);
				break;
			case 9:// ショットスイッチキー
				shotSwitchKeyMenu.move(this);
				shotSwitchKey = getKey(shotSwitchKeyMenu);
				break;
			}
			keyReset();
		}
		else if ( ev == S_KEY_FIRE )
		{
			if ( optionMenu.getId() == (optionMenu.getMaxRow() - 1) )
			{
				int dup = checkDuplicateKeySetting(changeSpeedKeyMenu, bombKeyMenu, shotSwitchKeyMenu);
				if ( dup > 0 )
				{
					warningMsg = KEY_LABELS[dup] + "キーが重複しています";
				}
				else
				{
					needSave = true;
					setScene(S_TITLE);
				}
			}
			keyReset();
		}

		doPaintOption();
	}
	
	public void execPressFireKey() throws Exception
	{
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			scene = S_TITLE;
			startBGM(BGM_TITLE);
			changeCommand();
			keyReset();
		}
		else if ( getKeyEvent() == S_SOFT_KEY )
		{
			execCommandAction(getSoftKeyNo());
			keyReset();
		}
		doPaintPressFireKey();
	}

	public void doPaintPressFireKey() throws Exception
	{
		drawCommonBackground();

		// タイトル
		drawImage(0, 0,165, 11,20, 218,74);
		titleCounter++;
		if ( titleCounter > 20 )
		{
			titleCounter = 0;
		}
		if ( titleCounter < 15 )
		{
			drawBoldString("PRESS START", 85, 140);
		}

		setColor(Color.LIGHT_GRAY);
		drawString(" http://m.strnet.com/", 50, 220);
		drawString("Copyright (C) 2010 STR", 50, 205);
	}

	private String generateOnOffLabel(String title, boolean flag)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(title);
		sb.append(" [");
		sb.append((flag) ? "有効" : "無効");
		sb.append("]");
		return sb.toString();
	}
	
	public void doPaintOption() throws Exception
	{
		int x = 35;
		int y = 55;
		int index = 0;
		int span = 15;
		drawCommonBackground();

		// タイトル
		drawImage(0, 102,0, 94,11, 51,16);
		
		if ( warningMsg == null )
		{
			setColor(255,180,180);
			drawString("左右で値を変更してください", 35, 30);
		}
		else
		{
			setColor(Color.RED);
			drawString(warningMsg, 30, 30);
		}

		setColor(Color.WHITE);
		StringBuffer sb = new StringBuffer();
		sb.append("BGM音量 ");
		
		for ( int i = 0; i < bgmVolumeMenu.getId(); i++ )
		{
			sb.append("> ");
		}
		drawSelectedMenu(index++, x, y, optionMenu, sb.toString());
		y+=span;

		sb = new StringBuffer();
		sb.append("SE音量  ");
		
		for ( int i = 0; i < seVolumeMenu.getId(); i++ )
		{
			sb.append("> ");
		}
		drawSelectedMenu(index++, x, y, optionMenu, sb.toString());
		y+=span;
		
		drawSelectedMenu(index++, x, y, optionMenu,
							   generateOnOffLabel("バイブレーション　　", savedVibrateEnable));
		y+=span;

		drawSelectedMenu(index++, x, y, optionMenu,
							   generateOnOffLabel("数字キー移動　　　　", numberMove));
		y+=span;
		/*
		drawSelectedMenu(index++, x, y, optionMenu,
							   generateOnOffLabel("オートボム", autoBomb));
		y+=span;
		*/
		drawSelectedMenu(index++, x, y, optionMenu,
							   generateOnOffLabel("背景描画　　　　　　", stageManager.canPaintBackground()));
		y+=span;
		
		drawSelectedMenu(index++, x, y, optionMenu,
							   generateOnOffLabel("プロローグ表示　　　", tutorial));
		y+=span;
		
		drawSelectedMenu(index++, x, y, optionMenu,
							   generateOnOffLabel("プレイ中ソフトキー　", playSoftKey));
		y+=span;
		y+=(span / 2);
		
		sb = new StringBuffer();
		sb.append("速度変更キー　　　　 [");
		sb.append(getKeyMenuLabel(changeSpeedKeyMenu));
		sb.append("]");
		drawSelectedMenu(index++, x, y, optionMenu, sb.toString());
		y+=span;
		
		sb = new StringBuffer();
		sb.append("トランスキー　　　　 [");
		sb.append(getKeyMenuLabel(bombKeyMenu));
		sb.append("]");
		drawSelectedMenu(index++, x, y, optionMenu, sb.toString());
		y+=span;
		
		sb = new StringBuffer();
		sb.append("ショットオンオフキー [");
		sb.append(getKeyMenuLabel(shotSwitchKeyMenu));
		sb.append("]");
		drawSelectedMenu(index++, x, y, optionMenu, sb.toString());
		y+=span;

		y+=(span / 2);

		drawSelectedMenu(index++, x, y, optionMenu, "設定");
	}

	public void execTutorial() throws Exception
	{
		/*
		if ( getKeyEvent() == S_KEY_FIRE )
		{
			if ( !prologueExit )
			{
				setScene(S_PLAY);
			}
			keyReset();
		}
		*/
		doPaintTutorial();
	}

	public void doPaintTutorial() throws Exception
	{
		drawCommonBackground();
		if ( !viewController.paintAll(this) )
		{
			setScene(S_PLAY);
		}
	}

	public void execPlay() throws Exception
	{
		if ( pause )
		{
			strongStr.paint(this, "PAUSE", 105, 110);
			return;
		}
		else if ( playState == PLAY_STATE_DEFAULT )
		{
			//programExecTime = System.currentTimeMillis();
			my.updateInvincible();
			stageManager.execute();
			ObjectPool.moveAll(ObjectPool.enemies, this);
			int nowBullets = ObjectPool.moveBulletAll(ObjectPool.bullets, this);
			ObjectPool.moveAll(ObjectPool.bursts, this);
			if ( my.isAlive() )
			{
				my.move(this);
			}
			ObjectPool.moveAll(ObjectPool.bombItemData, this);
			ObjectPool.moveAll(ObjectPool.myBullets, this);
			if ( my.isAlive() )
			{
				my.shotCheck();
				if ( getKeyEvent() != S_SOFT_KEY )
				{
					keyReset();
				}
			}
			else if ( my.getRemainder() >= 0 )
			{
				myCounter++;
				
				if ( myCounter > 24 )
				{
					my.onAlive();
					myCounter = 0;
				}
				else if ( myCounter > 12 )
				{
					my.up();
				}
				else if ( myCounter == 12 )
				{
					keyReset();
					my.appear();
					ObjectPool.destroyAllBullets(ObjectPool.bullets);
				}
			}

			if ( needAllScoreItemFlag )
			{
				scoreItemSize = ObjectPool.scoreItemAll(ObjectPool.bullets, this, needAllScoreItemForceFlag);
				needAllScoreItemFlag = false;
				needAllScoreItemForceFlag = false;
			}

			score.update();
			checkExtend();
			
			doPaintPlay();
			/*
			programExecTime = System.currentTimeMillis() - programExecTime;
			setColor(Color.WHITE);
			drawString("Program " + programExecTime,40,40);
			*/
		}
		else
		{
			switch ( playState )
			{
			case PLAY_STATE_GAMEOVER:
				doPaintPlay();
				strongNext.paint(this, "GAME OVER", 93, 110);
				break;

			case PLAY_STATE_STAGE_CLEAR:
				if ( arcard )
				{
					if ( getKeyEvent() == S_KEY_FIRE )
					{
						if ( stageNo < maxStage )
						{
							nextStage();
							setPlayStateAndUpdateScene(PLAY_STATE_DEFAULT);
						}
						else if ( (stageNo == maxStage) )
						{
							if ( resetPlay )
							{
								// エンディング1
								endingPattern = 0;
								setScene(S_ENDING);
							}
							else
							{
								// 真ボス
								stageNo = TRUE_BOSS_STAGE;
								setPlayStateAndUpdateScene(PLAY_STATE_DEFAULT);
							}
						}
						else if ( stageNo == TRUE_BOSS_STAGE )
						{
							// エンディング2
							endingPattern = 1;
							setScene(S_ENDING);
						}
						else
						{
							setScene(S_TITLE);
						}
						keyReset();
					}

					stageManager.doBackground();
					doPaintPlay();

					int x = 50;
					strongStr.paint(this, "STAGE CLEAR!", x + 31, 70);

					if ( my.isNoMiss() )
					{
						strongStr.paint(this, "No Miss Bonus: 1000000", x, 100);
					}
					strongStr.paint(this, "Item Bonus: " + my.getScoreCounter() + " x 100", x, 120);

					if ( stageNo < maxStage )
					{
						strongNext.paint(this, "Press Enter Key", x + 20, 150);
					}
				}
				else
				{
					if ( getKeyEvent() == S_KEY_FIRE )
					{
						setScene(S_STAGE_SELECT);
						keyReset();
					}
					stageManager.doBackground();
					doPaintPlay();
					int x = 50;
					strongStr.paint(this, "STAGE CLEAR!", x + 31, 70);
					strongNext.paint(this, "Press Enter Key", x + 20, 150);
				}

				break;
			}
		}
	}
	
	public void updateHighScore(boolean isGameover)
	{
		int sc = score.getScore();
		if ( sc > highScore )
		{
			highScore = sc;
		}

		if ( arcard )
		{
			FighterInformation fi = getSelectedInfo();
			fi.setHighScore(highScore);
			fi.setRank(stageManager.getRank());

			if ( isGameover )
			{
				save();
			}
			else
			{
				needSave = true;
			}
		}
	}
	
	public void needAllScoreItem(boolean force)
	{
		needAllScoreItemFlag = true;
		needAllScoreItemForceFlag |= force;
	}

	public void needPaintHyperGauge()
	{
		needHyperGauge = true;
	}
	
	public void needPaintLeftField()
	{
		needPaintLeftField = true;
		needPaintScoreCounter();
		needPaintHyperGauge();
		needHyperGaugeBackup = 0;
	}
	
	public void needPaintScoreCounter()
	{
		needPaintScoreCounter = true;
	}
	
	public void startScoreItemSE(int mergeScore)
	{
		startSE(BGM_SCORE_ITEM_MANY);
	}
	
	public void startSE(int id)
	{
		try
		{
			super.startSE(id);
		}
		catch ( Exception e ) {}
	}
	
	public void startSecondSE()
	{/*
		try
		{
			super.startSecondSE(BGM_BOMB);
		}
		catch ( Exception e ) {}
	 */
	}
	
/*		
	public void doPaintPlay() throws Exception
	{
//		paintExecTime = System.currentTimeMillis();

		doPaintPlayField();
		paintExecTime = System.currentTimeMillis() - paintExecTime;

		setColor(Color.WHITE);
		drawString("Program " + programExecTime + "ms. Paint " + paintExecTime + "ms",40,40);
		drawString("Bullet " + bulletMoveTime,40,60);
		//drawString(Runtime.getRuntime().freeMemory() +"/" + Runtime.getRuntime().totalMemory(), 80, 60);
	}
		*/
	
	private void checkExtend()
	{
		// エクステンド
		int sc = score.getScore();
		if ( sc > highScore )
		{
			highScore = sc;
		}

		if ( extend )
		{
			if ( sc >= EXTEND_SCORE_1[mode] )
			{
				my.addRemainder();
				extend = false;
				needPaintLeftField();
			}
		}
	}

	//public void doPaintPlayField()
	public void doPaintPlay() throws Exception
	{
		stageManager.paintBackground(this);
		ObjectPool.paintAll(ObjectPool.bombItemData, this);
		ObjectPool.paintEnemyAll(ObjectPool.enemies, this);
		ObjectPool.paintAll(ObjectPool.myBullets, this);
		ObjectPool.paintAll(ObjectPool.bursts, this);

		if ( my.isAlive() || myCounter >= 12 )
		{
			my.paint(this);
		}
		ObjectPool.paintBulletAll(ObjectPool.bullets, this);

		// 画面上部
		paintPlayUpField();
		
		if ( scene != S_PLAY )
		{
			paintPlayRightField();
		}

		if ( needPaintLeftField )
		{
			// 画面左
			paintPlayLeftField();
			needPaintLeftField = false;
		}
		
		if ( needPaintScoreCounter )
		{
			paintScoreCounter();
			needPaintScoreCounter = false;
		}

		if ( needHyperGauge )
		{
			paintHyperGauge();
			needHyperGauge = false;
		}

		if ( scene != S_PLAY || playState != PLAY_STATE_DEFAULT )
		{
			needPaintLeftField();
		}
	}

	private void paintPlayUpField()
	{
		// Trance Gauge
		/*
		if ( my.isAlive() && my.isHyper() )
		{
			int dx = FixedPoint.toInt(my.getFixX());
			int dy = FixedPoint.toInt(my.getFixY());
			Rectangle back = enableDrawArea;
			enableDrawArea = PLAY_SCREEN_RECT;
			drawImage(1, 47,150, dx, dy - 6, my.getHyperGauge() / 10, 4);
			enableDrawArea = back;
		}
		*/

		if ( get1UPCounter > 0 )
		{
			drawImage(1, 195,71, 107, 100+get1UPCounter, 25, 14);
			//strongNext.paint(this, "1UP!", 108, 100 + get1UPCounter);
			get1UPCounter--;
		}
		else if ( getBombCounter > 0 )
		{
			drawImage(1, 195,87, 103, 100+getBombCounter, 30, 12);
			//strongNext.paint(this, "Bomb", 108, 100 + getBombCounter);
			getBombCounter--;
		}

		if ( (my.getFixY() >= 9472) || !my.isAlive() )
		{
			// boss hp
			stageManager.paintForeground(this);
		
			StringBuffer sb = new StringBuffer(26);
			sb.append(GameUtil.leftPad(9, ' ', score.getScore()));
			sb.append("  HI-");
			sb.append(GameUtil.leftPad(9, ' ', highScore));
			setColor(Color.BLACK);
			drawString(sb.toString(), 51, 2);
			setColor(SCORE_COLOR);
			drawString(sb.toString(), 50, 1);
		}
	}

	int hyperKeisuu = MyData.MAX_HYPER_GAUGE / 60;
	public void paintScoreCounter()
	{
		setColor(Color.BLACK);
		fillRect(1, PLAY_SCREEN_Y + 30, 35, 14);
		setColor(SCORE_COUNTER_COLOR);
		drawString(GameUtil.leftPad(5, ' ',my.getScoreCounter()), 3, PLAY_SCREEN_Y + 30);
	}
	
	public void paintHyperGauge()
	{
		int gaugeLen = my.getHyperGauge() / hyperKeisuu;
		if ( gaugeLen > 60 )
		{
			gaugeLen = 60;
		}
		int sa = (60 - gaugeLen);
		//		System.out.println(gaugeLen +":"+sa);
		if ( my.isHyper() )
		{
			drawImage(3, 112,152, 0,152, 39,73);
			drawImage(3, 157,169 + sa, 0,159 + sa, 35,gaugeLen);
			needHyperGaugeBackup = 0;
		}
		else if ( (gaugeLen > 0) && needHyperGaugeBackup < gaugeLen )
		{
			drawImage(3, 157,169 + sa, 0,159 + sa, 35,gaugeLen);
			needHyperGaugeBackup = gaugeLen;
			if ( my.canHyper() )
			{
				drawImage(1, 103,107, 5,184, 14,9);
			}
		}
	}
	
	int[] zanki = new int[]{175,191,207};
	public void paintPlayLeftField()
	{
		drawImage(3, 112,0,0,0,40,240);
		
		for ( int i = 0; i < my.getRemainder(); i++ )
		{
			drawImage(1, zanki[selectedFighter],110, 1,110 - (i * 20), 16,16);
		}
		
		for ( int i = 0; i < my.getBomb(); i++ )
		{
			//drawImage(1, 184,1, 20,108 - (i * 22), 16,21);
			drawImage(1, 185,3, 22,110 - (i * 22), 14,17);
		}
	}
	
	public void paintPlayRightField()
	{
		drawImage(3, 152,0,PLAY_SCREEN_X + PLAY_SCREEN_WIDTH,0,40,160);
		drawImage(3, 152,66,PLAY_SCREEN_X + PLAY_SCREEN_WIDTH,160,40,80);

		
		
		int x = PLAY_SCREEN_X + PLAY_SCREEN_WIDTH + 2;
		int y = 142;

		drawBoldString2("Mode", x + 6, 30);
		int modeX = x;
		if ( mode == MODE_HARD )
		{
			modeX += 6;
		}
		else if ( mode == MODE_HELL )
		{
			modeX += 3;
		}
		drawBoldString(MODE_LABEL[mode], modeX, 45);
		
		int keyId;
		keyId = keyToKeyId(changeSpeedKey);
		if ( keyId > 0 )
		{
			drawBoldString2("Speed", x + 3, y);
			y+= 14;
			drawBoldString("[" + KEY_LABELS[keyId] + "]", x, y);
			y += 19;
		}
		keyId = keyToKeyId(bombKey);
		if ( keyId > 0 )
		{
			drawBoldString2("Trance", x, y);
			y+= 14;
			drawBoldString("[" + KEY_LABELS[keyId] + "]", x, y);
			y += 19;
		}
		keyId = keyToKeyId(shotSwitchKey);
		if ( keyId > 0 )
		{
			drawBoldString2("On/Off", x, y);
			y+= 14;
			drawBoldString("[" + KEY_LABELS[keyId] + "]", x, y);
			y += 19;
		}
	}

	public void addScore(int s)
	{
		score.add(s);
	}
	
	public void addScoreAll()
	{
		score.updateAll();
	}
	
	public void execCommandAction(int softKeyNo) throws Exception
	{
		switch ( softKeyNo )
		{
		case 0: // ♪○
			setSoundEnable(false);
			changeCommand();
			break;
		case 1: // ♪×
			setSoundEnable(true);
			if ( scene != S_PRESS_FIRE_KEY )
			{
				startBGM(BGM_TITLE);
			}
			changeCommand();
			break;
		case 2: // webLabel
			if ( !Main.isDxMode() )
			{
				browser(URL);
			}
			break;
		case 8: // ﾀｲﾄﾙ
			goTitle = true;
			changeCommand();
			break;
		case 12: //No
			goTitle = false;
			changeCommand();
			break;
		case 3: // ﾀｲﾄﾙ
		case 11: // Yes
		case 18: // ﾀｲﾄﾙ
			goTitle = false;
			setScene(S_TITLE);
			break;
		case 4: // ﾒﾆｭｰ
			//setWaitTime(50);
			if ( !my.isLock() )
			{
				pause = true;
				if ( playState == PLAY_STATE_DEFAULT )
				{
					try
					{
						pauseBGM();
					}
					catch ( Exception ignore ) {}
				}
				changeCommand();
			}
			break;
		case 5: // 終了
			terminate();
			break;
		case 6: // 再開
			pause = false;
			if ( playState == PLAY_STATE_DEFAULT )
			{
				try
				{
					restartBGM();
				}
				catch ( Exception ignore ) {}
			}
			changeCommand();
			break;
		case 7:// ﾘﾄﾗｲ
			retry();
			setPlayStateAndUpdateScene(PLAY_STATE_DEFAULT);
			break;
		case 9: // 戻る
			if ( playerName.length() > 0 )
			{
				playerName = playerName.substring(0, playerName.length() - 1);
			}
			break;
		case 10: // 決定
			if ( playerName.length() > 0 )
			{
				needSave = true;
				setScene(S_RANKING);
			}
			break;
		case 13: // 戻る
			if ( scene == S_MUSIC_ROOM || scene == S_MANUAL )
			{
				setScene(S_LIBRARY);
			}
			else if ( scene == S_LIBRARY )
			{
				if ( selectedLibraryId == -1 )
				{
					goTitle = false;
					setScene(S_TITLE);
				}
				else
				{
					selectedLibraryId = -1;
				}
			}
			else
			{
				// stage select back
				if ( mode == MODE_UNDEFINED )
				{
					goTitle = false;
					setScene(S_TITLE);
				}
				else if ( !fighterSelected )
				{
					setMode(MODE_UNDEFINED);
				}
				else
				{
					fighterSelected = false;
				}
			}
			break;
		case 15: //音1
		case 16: //音2
			setHiSound(!isHiSound());
			stopBGM();
			startBGM(BGM_TITLE);
			//restartBGM();
			changeCommand();
			break;
		case 17: // 中止
			setScene(S_STAGE_SELECT);
			break;
		case 19: // ｽｷｯﾌﾟ
			setScene(S_PLAY);
			break;
		}
		keyReset();
	}

	public void changeCommand()
	{
		switch ( scene )
		{
		case S_PRESS_FIRE_KEY:
		case S_TITLE:
			setSoftKey((bgmEnable) ? 0 : 1, 2);
			break;
		case S_RANKING:
		case S_ENDING:
			setSoftKey(3, -1);
			break;
		case S_OMAKE:
			setSoftKey(14, -1);
			break;
		case S_OPTION:
			if ( "s".equals(getCarrier()) )
			{
				setSoftKey((bgmEnable) ? 0 : 1, (isHiSound()) ? 15 : 16);
			}
			else
			{
				setSoftKey((bgmEnable) ? 0 : 1, -1);
			}
			break;
		case S_TUTORIAL:
			setSoftKey(19, -1);
			break;
		case S_NAME_INPUT:
			setSoftKey(9, 10);
			break;
		case S_MANUAL:
		case S_MUSIC_ROOM:
		case S_CHARACTER_SELECT:
		case S_STAGE_SELECT:
		case S_LIBRARY:
			setSoftKey(13, 18);
			break;
		case S_PLAY:
			switch ( playState )
			{
			case PLAY_STATE_STAGE_CLEAR:
				setSoftKey(14, -1);
				break;
			case PLAY_STATE_GAMEOVER:
				if ( goTitle )
				{
					setSoftKey(11,12);
				}
				else
				{
					setSoftKey(7, 8);
				}
				break;
			default:
				if ( playSoftKey )
				{
					if ( pause )
					{
						if ( goTitle )
						{
							setSoftKey(11,12);
						}
						else
						{
							if ( arcard )
							{
								setSoftKey(6, 8);
							}
							else
							{
								setSoftKey(6, 17);
							}
						}
					}
					else
					{
						setSoftKey(4, -1);
					}
				}
				else
				{
					setSoftKey(14, -1);
				}
				break;
			}
		}
	}

	public void setPlayState(int playState) throws Exception
	{
		if ( (playState == PLAY_STATE_GAMEOVER) && arcard && stageNo == TRUE_BOSS_STAGE )
		{
			// エンディング1
			endingPattern = 0;
			setScene(S_ENDING);
		}
		else
		{
			this.playState = playState;
		}
		changeCommand();
	}

	public void setPlayStateAndUpdateScene(int playState) throws Exception
	{
		this.nextPlayState = playState;
		setScene(S_PLAY);
	}	

	public void load()
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
			System.out.println("load:"+tmp);
			
			int i = 0;
			String[] line = GameUtil.split(tmp, ',');
			String[] s;
			// System Data
			s = GameUtil.split(line[0], ':');
			try
			{
				setBGMVolumeLevel(Integer.parseInt(s[i++]));
				setSEVolumeLevel(Integer.parseInt(s[i++]));
				savedVibrateEnable = ("1".equals(s[i++]));
				setSoundEnable("1".equals(s[i++]));
				numberMove = ("1".equals(s[i++]));
				changeSpeedKey = (Integer.parseInt(s[i++]));
				bombKey = (Integer.parseInt(s[i++]));
				shotSwitchKey = (Integer.parseInt(s[i++]));
				//autoBomb = ("1".equals(s[i++]));
				stageManager.setCanPaintBackground("1".equals(s[i++]));
				playerName = s[i++];
				setHiSound("1".equals(s[i++]));
				canMusicMode = ("1".equals(s[i++]));
				canLaser2MyData = ("1".equals(s[i++]));
				canChaosMode = ("1".equals(s[i++]));
				//EASY canEasyMode = ("1".equals(s[i++]));
				tutorial = ("1".equals(s[i++]));
				playSoftKey = ("1".equals(s[i++]));
				FighterInformationManager.load(line[1]);
			}
			catch ( Exception ignore)
			{
				ignore.printStackTrace();
			}
		}
		catch ( Exception e )
		{
			error(e, "ロードに失敗しました");
		}
	}

	public void save()
	{
		try
		{
			StringBuffer saveData = new StringBuffer();
			//System Data
			SaveDataCreator sdc = new SaveDataCreator(':');
			sdc.add(getVolumeLevel());
			sdc.add(getSEVolumeLevel());
			sdc.add(savedVibrateEnable);
			sdc.add(bgmEnable);
			sdc.add(numberMove);
			sdc.add(changeSpeedKey);
			sdc.add(bombKey);
			sdc.add(shotSwitchKey);
			//sdc.add(autoBomb);
			sdc.add(stageManager.canPaintBackground());
			sdc.add(playerName);
			sdc.add(isHiSound());
			sdc.add(canMusicMode);
			sdc.add(canLaser2MyData);
			sdc.add(canChaosMode);
			//EASY sdc.add(canEasyMode);
			sdc.add(tutorial);
			sdc.add(playSoftKey);
			saveData.append(sdc);
			saveData.append(',');
			
			saveData.append(FighterInformationManager.toSaveString());
			saveData.append(',');
			System.out.println("save:"+saveData.toString());
			save(RECORD_KEY, saveData.toString());
		}
		catch ( Exception e )
		{
			error(e, "セーブに失敗しました");
		}
	}

	public int getBGMSplit(int id)
	{
		switch ( id )
		{
		case 0:
		case 1:
		case 3:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 11:
		case 12:
		case 13:
			return 2;
		default:
			return 1;
		}
	}

	public boolean isUp()
	{
		if ( numberMove )
		{
			return keyState[0] || keyState[5] || keyState[6] || keyState[7];
		}
		else
		{
			return keyState[0];
		}
	}
	
	public boolean isDown()
	{
		if ( numberMove )
		{
			return keyState[1] || keyState[11] || keyState[12] || keyState[13];
		}
		else
		{
			return keyState[1];
		}
	}
	
	public boolean isLeft()
	{
		if ( numberMove )
		{
			return keyState[2] || keyState[5] || keyState[8] || keyState[11];
		}
		else
		{
			return keyState[2];
		}
	}
	
	public boolean isRight()
	{
		if ( numberMove )
		{
			return keyState[3] || keyState[7] || keyState[10] || keyState[13];
		}
		else
		{
			return keyState[3];
		}
	}
	
	public FighterInformation getSelectedInfo()
	{
		return FighterInformationManager.get(selectedFighter, mode);
	}

	private void makeAvailableStages()
	{
		FighterInformation fi = getSelectedInfo();
		availableStages = new int[fi.getAvailableStage() * 2 + ((fi.hasTrueBoss()) ? 1 : 0)];
		int seek = 0;
		for ( int i = 0; i < fi.getAvailableStage(); i++ )
		{
			availableStages[seek++] = i + 1;
			availableStages[seek++] = i + 1 + FighterInformationManager.ADD_BOSS_STAGE;
		}
		if ( fi.hasTrueBoss() )
		{
			availableStages[availableStages.length - 1] = TRUE_BOSS_STAGE;
		}

		//all
		//availableStages = new int[]{1,11,2,12,3,13,4,14,5,15,6};
	}

	private String getRankLabel(int rank)
	{
		if ( rank == 0 )
		{
			return "";
		}
		int r = rank / 5;
		if ( r >= RANK_LABEL.length )
		{
			r = RANK_LABEL.length - 1;
		}
		
		return "Lv." + rank + " (" + RANK_LABEL[r] + ")";
	}
}
