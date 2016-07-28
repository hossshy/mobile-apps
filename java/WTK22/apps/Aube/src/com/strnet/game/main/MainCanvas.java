/*
 * Last modified: 2009/04/05 01:05:45
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Counter;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;
import com.strnet.game.component.MapImage;
import com.strnet.game.component.MenuWindow;
import com.strnet.game.component.NumberImage;

public class MainCanvas extends AbstractCanvas
{
	private static final String URL = "http://m.strnet.com/";
	private static final String RECORD_KEY = "Aube";

	static final int S_INIT = 0;
	static final int S_TITLE = 1;
	static final int S_STAGE_SELECT = 2;
	static final int S_READY = 3;
	static final int S_PLAY = 4;
	static final int S_CLEAR = 5;
	static final int S_GAMEOVER = 6;
	static final int S_MANUAL = 7;
	static final int S_END_1 = 8;
	static final int S_END_2 = 9;
	static final int S_END_3 = 10;
	static final int S_HUSUMA = 11;
	static final int S_STORY = 12;
	public static final int CHIP_WIDTH = 24;
	public static final int MAX_POWER = 50;
	static final int TILE_LENGTH = 9;
	static final int TILE_HOSEI = CHIP_WIDTH / 2;
	static final int MAX_IMAGE = 30;
	static final int MAX_MANUAL = 5;


	MenuWindow playMenu = new MenuWindow(1, 4, false);

	MenuWindow titleMenu = new MenuWindow(1, 5, false);

	
	MapImage[] mapImage = new MapImage[MAX_IMAGE];
	MapChip[] mapChips = new MapChip[16];
	NumberImage numberImage = null;

	int[][] map = null;
	EnemyData[] enemys = null;
	CharacterData aube = null;
	MyData cursor;
	CharacterData hall;
	int stage = 0;
	int maxStory = 1;


	Magic[] magics = new Magic[5];

	int magicId = 0;
	int manualPage = 0;
	int storyPage = 0;
	int waitCounter = 0;

	String msg = null;

	int[] clearFlagStore = null;

	public MainCanvas(Main app)
	{
		super(app);
		setWaitTime(80);
	}
	
	void init()
	{
		super.init(new String[]{"", "ôƒáO", "ê∞í◊", "ç’ó‹"});
		removeCommands();
		addCommand(0, 0);
		scene = S_INIT;
		setFont(S_FONT_MEDIUM);

		DataLibrary.init();
		Map.init();
		ASter.init();

		clearFlagStore = new int[Map.maps.length];
		load();

		numberImage = new NumberImage(13, 8, 13);
		numberImage.setMaxLength(2);
		
		cursor = new MyData();
		cursor.imageId = 4;
		cursor.maxImageFrame = 4;
		cursor.imageSpeed = 0;
		cursor.speed = 13;

		hall = new ItemData();
		hall.imageId = DataLibrary.BLACK_HALL;
		hall.maxImageFrame = 7;
		hall.imageSpeed = 5;

		aube = new ItemData();
		aube.imageId = DataLibrary.AUBE;
		aube.maxImageFrame = 4;
		aube.imageSpeed = 5;
		enemys = new EnemyData[20];
		map = new int[TILE_LENGTH][TILE_LENGTH];

		try
		{
			loadImage(MAX_IMAGE);
		}
		catch ( Exception e )
		{
		}
		/*
		for ( int m = 0; m < MAX_MANUAL; m++ )
		{
			try {
				destroyOneImage(26+m);
			}catch ( Exception e ){}
		}
*/
		
		mapChips[0] = new MapChip(0, true);
		mapChips[1] = new MapChip(1, false);
		mapChips[2] = new MapChip(2, true);
		mapChips[3] = new MapChip(3, true);
		mapChips[4] = new MapChip(4, true);
		mapChips[5] = new MapChip(5, false);
		mapChips[6] = new MapChip(6, true);
		mapChips[7] = new MapChip(7, true);
		mapChips[8] = new MapChip(8, true);
		mapChips[9] = new MapChip(0, false);//magic

		for ( int i = 0; i < 10;i++ )
			mapImage[i] = new MapImage(i, 10, CHIP_WIDTH, CHIP_WIDTH);

		mapImage[10] = new MapImage(10, 4, 38, 106);
		mapImage[11] = new MapImage(11, 1, 56, 16);
		mapImage[12] = new MapImage(12, 1, 50, 10);
		mapImage[18] = new MapImage(18, 2, 108, 216);
		mapImage[19] = new MapImage(19, 5, 13, 13);
		mapImage[20] = new MapImage(20, 1, 146, 123);
		mapImage[21] = new MapImage(21, 1, 70, 16);
		loadedCounter++;

		addCommand(0, 3);
		scene = S_TITLE;
	}

	void reset()
	{
		playMenu.setVisible(false);
		System.gc();

		cursor.setPosition(4, 4);
		cursor.power = MAX_POWER;
		cursor.hosuu = 0;
		cursor.movingDir = 'D';
		cursor.moved = false;

		DataLibrary.setup(stage, map, enemys, aube, cursor, hall);

		for ( int i = 0; i < magics.length; i++ )
		{
			magics[i] = null;
		}
		for ( int i = 0; i < enemys.length; i++ )
		{
			if ( enemys[i] != null )
			{
				enemys[i].searchRoad(map, mapChips, aube);
			}
		}
		waitCounter = 0;
		scene = S_READY;
		Counter.count = 0;
		cursor.setState(MyData.STATE_NORMAL);
		loadedCounter++;
	}

	void doAction()
	{
		try
		{
			switch ( scene )
			{
			case S_READY:
			case S_PLAY:
			case S_GAMEOVER:
			case S_CLEAR:
				if ( playMenu.isVisible() )
				{
					playMenu.move(this);
					if ( getKeyEvent() == S_KEY_FIRE )
					{
						playMenu.setVisible(false);
						
						switch ( playMenu.getId() )
						{
						case 0:
							break;
						case 1:
							reset();
							break;
						case 2:
							removeCommands();
							addCommand(0, 3);
							scene = S_TITLE;
							break;
						case 3:
							terminate();
							break;
						}
					}
					keyReset();

					mapImage[20].drawCenter(this, 0);
					drawImage(3, 0, 0, 70, 67 + 27*playMenu.getId(), 24, 24);
					drawImage(3, 0, 0, 148, 67 + 27*playMenu.getId(), 24, 24);
				}
				Counter.count++;
			}


			switch ( scene )
			{
			case S_PLAY:

				if ( !playMenu.isVisible() )
				{
					checkKey();
					doPaint();
				}
				
				break;
				
			case S_TITLE:

				if ( getKeyEvent() == S_KEY_FIRE )
				{
					switch ( titleMenu.getId() )
					{
					case 0:
						reset();
						scene = S_HUSUMA;
						removeCommands();
						addCommand(0, 1);
						break;
					case 1:
						/*
						for ( int m = 0; m < MAX_MANUAL; m++ )
						{
							loadOneImage(26+m);
						}
						*/
						manualPage = 0;
						scene = S_MANUAL;
						removeCommands();
						addCommand(0, 2);
						break;
					case 2:
						storyPage = 0;
						scene = S_STORY;
						removeCommands();
						addCommand(0, 2);
						break;
					case 3:
						terminate();
						break;
					case 4:
						browser(URL);
						break;
					}
				}
				else if ( getKeyEvent() == S_KEY_RIGHT )
				{
					if ( stage < getMaxStage() )
					{
						stage++;
					}
					else
					{
						stage = 0;
					}
				}
				else if ( getKeyEvent() == S_KEY_LEFT )
				{
					if ( stage > 0 )
					{
						stage--;
					}
					else
					{
						stage = getMaxStage();
					}
				}
				else
				{
					titleMenu.move(this);
				}
				keyReset();
				

				drawImage(9, 0, 0);
				
				int mX = 110, mY=95;
				if ( clearFlagStore[stage] == 1 )
				{
					mapImage[19].draw(this, 1, mX, mY);
				}
				else if ( clearFlagStore[stage] == 2 )
				{
					mapImage[19].draw(this, 0, mX, mY);
				}

				mapImage[19].draw(this, 2, mX - 17, mY);
				mapImage[19].draw(this, 3, mX + 32, mY);

				numberImage.draw(this, (stage + 1), mX + 13, mY);
				mX = 83;
				mY = 112;
				drawImage(3, 0, 0, mX, mY + 23*titleMenu.getId(), 24, 24);
				drawImage(3, 0, 0, mX+62, mY + 23*titleMenu.getId(), 24, 24);

				
				break;
			case S_MANUAL:
				
				if ( (getKeyEvent() == S_KEY_FIRE) || (getKeyEvent() == S_KEY_RIGHT) )
				{
					if ( manualPage < MAX_MANUAL - 1 )
						manualPage++;
				}
				else if ( getKeyEvent() == S_KEY_LEFT )
				{
					if ( manualPage > 0 )
						manualPage--;
				}
				keyReset();
				drawImage(26 + manualPage, 0, 0);
				break;

			case S_STORY:
				
				if ( (getKeyEvent() == S_KEY_FIRE) || (getKeyEvent() == S_KEY_RIGHT) )
				{
					if ( storyPage < maxStory - 1 )
						storyPage++;
				}
				else if ( getKeyEvent() == S_KEY_LEFT )
				{
					if ( storyPage > 0 )
						storyPage--;
				}
				keyReset();
				drawImage(22 + storyPage, 0, 0);
				break;

			case S_HUSUMA:
				doPaint();
				waitCounter++;

				mapImage[18].draw(this, 0, TILE_HOSEI - waitCounter * 10, TILE_HOSEI);
				mapImage[18].draw(this, 1, 108 + TILE_HOSEI + waitCounter * 10, TILE_HOSEI);

				if ( waitCounter > 14 )
				{
					waitCounter = 0;
					scene = S_READY;
				}
				keyReset();

				break;

			case S_READY:

				if ( !playMenu.isVisible() )
				{
					doPaint();
					//TODO SSno tame
					//waitCounter++;

					int y = (waitCounter * 18) - 105;
					if ( y >= 60 ) y = 60;
					mapImage[10].draw(this, 0, 240 / 2 - 38 / 2, y);

					if ( waitCounter > 25 )
					{
						scene = S_PLAY;
					}
					keyReset();
				}
				
				break;

			case S_GAMEOVER:
				
				if ( !playMenu.isVisible() )
				{
					int y = (waitCounter * 18) - 105;
					if ( y >= 60 )
					{
						if ( getKeyEvent() == S_KEY_FIRE )
						{
							reset();
						}
					}

					doPaint();
					if ( y >= 60 )
					{
						y = 60;
					}
					else
					{
						waitCounter++;
					}
					mapImage[10].draw(this, 2, 240 / 2 - 38 / 2, y);
					keyReset();
				}				
				break;
				
			case S_CLEAR:
				if ( !playMenu.isVisible() )
				{
					int y = (waitCounter * 18) - 105;
					if ( y >= 60 )
					{
						if ( getKeyEvent() == S_KEY_FIRE )
						{
							if ( getClearFlagCount(2) >= 50 && maxStory < 4)
							{
								scene = S_END_3;
								maxStory = 4;
								removeCommands();
								addCommand(0, 2);
							}
							else if ( isClear(50) && maxStory < 3 )
							{
								scene = S_END_2;
								maxStory = 3;
								removeCommands();
								addCommand(0, 2);
							}
							else if ( isClear(40) && maxStory < 2 )
							{
								scene = S_END_1;
								maxStory = 2;
								removeCommands();
								addCommand(0, 2);
							}
							else if ( stage < getMaxStage() )
							{
								stage++;
								reset();
								scene = S_HUSUMA;
							}
							else
							{
								stage = 0;
								reset();
								scene = S_HUSUMA;
							}
						}
					}

					if ( scene == S_CLEAR )
					{
						doPaint();
						if ( y >= 60 )
						{
							y = 60;
						}
						else
						{
							waitCounter++;
						}
						mapImage[10].draw(this, 3, 240 / 2 - 38 / 2, y);
					}
					keyReset();
				}
				break;
			case S_INIT:
				setColor(255,255,255);
				drawString("Please wait...", 10, 10);
				setColor(255,255,0);
				fillRect(10, 230, loadedCounter * 7, 1);
				break;
			case S_END_1:
			case S_END_2:
			case S_END_3:

				int i = 0;
				if ( scene == S_END_1 ) i = 1;
				else if ( scene == S_END_2 ) i = 2;
				else if ( scene == S_END_3 ) i = 3;
				drawImage(22 + i, 0, 0);
				keyReset();
				break;
			}
		}catch ( Exception e ) {e.printStackTrace();setColor(255,255,255);drawString(e.getMessage(),0,0);}
	}
	
	int getMaxStage()
	{
		if ( isClear(40) )
		{
			return 50 - 1;
		}
		else
		{
			return 40 - 1;
		}
	}

	void checkKey()
	{try{
			if ( getKeyEvent() == S_KEY_FIRE )
			{
				if ( !cursor.moved )
				{
					if ( map[cursor.y][cursor.x] == 0 )
					{
						Magic magic = DataLibrary.getMagic(magicId);
						if ( magic.action(cursor, map, mapChips, enemys, aube, hall) )
						{
							for ( int i = 0; i < magics.length; i++ )
							{
								if ( magics[i] == null )
								{
									magics[i] = magic;
									break;
								}
							}
					
							for ( int i = 0; i < enemys.length; i++ )
							{
								if ( enemys[i] != null )
								{
									enemys[i].actionMagicd();
								}
							}
						}
					}
				}
			}
			else
			{
				cursor.move(map, mapChips, this);
			}

			for ( int i = 0; i < magics.length; i++ )
			{
				if ( magics[i] != null )
				{
					if ( !magics[i].doAction(map) )
					{
						magics[i] = null;
					}
				}
			}
		
			keyReset();

			if ( cursor.power < MAX_POWER )
			{
				cursor.power++;
			}
			if ( cursor.frame > 0 )
			{
				cursor.frame--;
			}


			boolean clearFlag = true;
			for ( int i = 0; i < enemys.length; i++ )
			{
				if ( enemys[i] != null )
				{
					if ( enemys[i].state == EnemyData.STATE_WAIT )
					{
						if ( enemys[i].counter++ > 10 )
						{
							enemys[i].state = EnemyData.STATE_BRAIN;
						}
					}
					if ( enemys[i].state == EnemyData.STATE_BRAIN )
					{
						enemys[i].searchRoad(map, mapChips, aube);
					}
					if ( enemys[i] != null )
					{
						enemys[i].move(map, mapChips);
					}

					if ( GameUtil.equalPoint(enemys[i], aube) )
					{
						waitCounter = 0;
						scene = S_GAMEOVER;
						cursor.setState(MyData.STATE_GAMEOVER);
						break;
					}
					else if ( GameUtil.equalPoint(enemys[i], hall) )
					{
						enemys[i].die(true);
					}
					else if ( GameUtil.equalPoint(enemys[i], cursor) )
					{
						if ( cursor.frame == 0 )
						{
							cursor.power -= enemys[i].minusPower;
							cursor.frame = 10;
						}
					}
				
					clearFlag &= (enemys[i].state == EnemyData.STATE_DIE);
				}
			}
		
			if ( (scene != S_GAMEOVER) && clearFlag )
			{
				waitCounter = 0;
				scene = S_CLEAR;
				cursor.setState(MyData.STATE_CLEAR);
				if ( clearFlagStore[stage] == 0 )
				{
					clearFlagStore[stage] = (isHallClear()) ? 2 : 1;
					save();
				}
				else if ( clearFlagStore[stage] == 1 )
				{
					if ( isHallClear() )
					{
						clearFlagStore[stage] = 2;
						save();
					}
				}


				if ( (getClearFlagCount(2) >= 50 && maxStory < 4) ||
					 ( isClear(50) && maxStory < 3 ) ||
					 ( isClear(40) && maxStory < 2 ) )
				{
					removeCommands();
				}
			}
		}catch(Exception e ){e.printStackTrace();}
	}
	
	boolean isHallClear()
	{
		for ( int i = 0; i < enemys.length; i++ )
		{
			if ( (enemys[i] != null) && !enemys[i].isHall )
			{
				return false;
			}
		}
		return true;
	}
	
	void doPaint()
	{
		for ( int x = 0; x < TILE_LENGTH; x++ )
		{
			for ( int y = 0; y < TILE_LENGTH; y++ )
			{
				int imgArr = 0;
				int imgId = 0;
				// draw magic zimen
				if ( map[y][x] == 5 )
				{
					imgId = (stage > 39) ? 1 : 0;
				}
				else if ( map[y][x] == 0 )
				{
					imgId = (stage > 39) ? 1 : 0;
				}
				else if ( map[y][x] == 1 )
				{
					imgId = (stage > 39) ? 3 : 2;
				}
				else
				{
					imgArr = map[y][x];
				}
				mapImage[imgArr].draw(this, imgId, x * CHIP_WIDTH + TILE_HOSEI, y * CHIP_WIDTH + TILE_HOSEI);
			}
		}
		/*			
					if ( node != null )
					{
					drawNode(node);
					}
		*/
		
		drawChara(aube);
		//drawChara(hall);
		Point p = hall.getPosition();
		int pattern = hall.getImagePattern();
		if ( pattern != -1 )
			mapImage[0].draw(this, 20 + pattern, p.x + TILE_HOSEI, p.y + TILE_HOSEI);

		for ( int i = 0; i < magics.length; i++ )
		{
			if ( magics[i] != null )
			{
				drawChara(magics[i]);
			}
		}

		for ( int i = 0; i < enemys.length; i++ )
		{
			if ( enemys[i] != null )
			{
				drawChara(enemys[i]);
			}
		}
		drawChara(cursor);


		//setColor(255,255,255);
		//drawString("Stage " + (stage + 1), 140,0);


		drawImage(11, 0, 0, 155, 5, 70, 16);
		numberImage.draw(this, (stage + 1), 205, 7);


		drawImage(11, 0, 16, 15, 5, 55, 15);
		if ( cursor.power > 0 && cursor.power <= MAX_POWER )
		{
			drawImage(11, 0, 32, 18, 5, cursor.power, 16);
		}

		if ( waitCounter > 25 && waitCounter < 35 && scene == S_PLAY )
		{
			waitCounter++;
			int y = 60 + ((waitCounter - 27) * 24);
			if ( waitCounter < 27 ) y = 60;
			mapImage[10].draw(this, 1, 240 / 2 - 38 / 2, y);
		}

		//msg = Runtime.getRuntime().freeMemory() + " / " + Runtime.getRuntime().totalMemory();
		if ( msg != null )
		{
			setColor(255,255,255);
			drawString(msg, 0, 0);
		}
	}
	
	private void drawChara(CharacterData cd)
	{
		Point p = cd.getPosition();
		int pattern = cd.getImagePattern();
		if ( pattern != -1 )
			mapImage[cd.imageId].draw(this, pattern, p.x + TILE_HOSEI, p.y + TILE_HOSEI);
	}

	/*
	  void drawNode(Node n)
	  {
	  if ( n.parent != null )
	  {
	  drawNode(n.parent);
	  }
	  mapImage[0].draw(this, 2, n.x, n.y);
	  }
	*/
	
	void doCommandAction(int i)
	{
		switch ( i )
		{
		case 1:
			switch ( scene )
			{
			case S_READY:
			case S_PLAY:
			case S_GAMEOVER:
			case S_CLEAR:
				playMenu.setVisible(!playMenu.isVisible());
				break;
			}
			break;
		case 2:
			/*
			for ( int m = 0; m < MAX_MANUAL; m++ )
			{
				try {
					destroyOneImage(26+m);
				}catch ( Exception e ){}
			}
			*/
			scene = S_TITLE;
			removeCommands();
			addCommand(0, 3);
			break;
		case 3:
			browser("http://appget.com/rd/i/c/appli/hou/?/dxi/af/ad?uid=NULLGWDOCOMO&id=cto-houtogi&rd=/dxi/collaba_appli/houtogien.htm");
			break;
		}
	}

	public void fireKeyReleased()
	{
		cursor.canMove = 0;
	}

	boolean isClear(int stageSize)
	{
		for ( int i = 0; i < stageSize; i++ )
		{
			if ( clearFlagStore[i] == 0 )
			{
				return false;
			}
		}
		return true;
	}

	int getClearFlagCount(int mode)
	{
		int len = 0;
		for ( int i = 0; i < clearFlagStore.length; i++ )
		{
			if ( clearFlagStore[i] == mode )
			{
				len++;
			}
		}
		
		return len;
	}

	void load()
	{
		for ( int i = 0; i < clearFlagStore.length; i++ )
		{
			clearFlagStore[i] = 0;
		}

		if ( !isRecord(RECORD_KEY) )
		{
			return;
		}
		
		try
		{
			String tmp = load(RECORD_KEY);
			if ( tmp == null )
			{
				return;
			}
			
			String[] cell = GameUtil.split(tmp, ',');
			stage = Integer.parseInt(cell[0]);
			for ( int i = 0; i < cell.length - 1; i++ )
			{
				clearFlagStore[i] = Integer.parseInt(cell[i+1]);
			}

			/*
			for ( int i = 0; i < 50; i++ )
			{
				clearFlagStore[i] = 1;
			}
			for ( int i = 1; i < 50; i++ )
			{
				clearFlagStore[i] = 2;
			}
			*/

			if ( getClearFlagCount(2) >= 50 )
			{
				maxStory = 4;
			}
			else if ( isClear(50) )
			{
				maxStory = 3;
			}
			else if ( isClear(40) )
			{
				maxStory = 2;
			}
		}
		catch ( Exception e )
		{
			msg = "load failed.";
			e.printStackTrace();
		}
	}

	void save()
	{
		StringBuffer saveData = new StringBuffer();

		int st = stage;
		if ( stage < getMaxStage() )
		{
			st++;
		}

		saveData.append(st);
		for ( int i = 0; i < clearFlagStore.length; i++ )
		{
			saveData.append(',');
			saveData.append(clearFlagStore[i]);
		}

		/*
		  if ( saveData.charAt(saveData.length() - 1) == ',' )
		  {
		  saveData.deleteCharAt(saveData.length() - 1);
		  }
		*/

		try
		{
			save(RECORD_KEY, saveData.toString());
		}
		catch ( Exception e )
		{
			msg = "save failed.";
			e.printStackTrace();
		}
	}
}
