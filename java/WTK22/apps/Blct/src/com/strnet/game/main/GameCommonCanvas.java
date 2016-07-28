/*
 * Last modified: 2010/03/22 19:32:16
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;
import com.strnet.game.common.GameUtil;
import com.strnet.game.component.ImageData;
import com.strnet.game.component.scene.*;
import com.strnet.game.common.list.IntLightList;
import com.strnet.game.component.ImagePatternData;

public abstract class GameCommonCanvas extends AbstractCanvas
{
	public static final int S_PLAY = 0;
	public static final int S_TITLE = 1;
	public static final int S_INIT = 2;
	public static final int S_MANUAL = 3;
	public static final int S_OPTION = 4;
	public static final int S_SCENE_MOVE = 5;
	public static final int S_STAGE_SELECT = 6;
	public static final int S_PRESS_FIRE_KEY = 7;
	public static final int S_ENDING = 8;
	public static final int S_OMAKE = 9;
	public static final int S_STORY = 10;
	public static final int S_NAME_INPUT = 11;
	public static final int S_RANKING = 12;
	public static final int S_CHARACTER_SELECT = 13;
	public static final int S_MUSIC_ROOM = 14;
	public static final int S_TUTORIAL = 15;
	public static final int S_LIBRARY = 16;

	public static final int VOLUME_LEVEL = MAX_VOLUME / 4;

	private IntLightList imageIdList = new IntLightList();
	protected boolean initialized = false;
	protected int backScene;
	protected int nextScene;
	protected int sceneMoveCounter;
	protected String msg = null;
	protected ManualManager manual = null;
	protected int firstScene;
	private int[] ignoreImageIds = null;
	private int maxImage;
	protected boolean savedVibrateEnable = true;
	
	protected boolean soundEnable = true;
	private int gcCounter = 0;
	
	public GameCommonCanvas(Main app, int firstScene, int maxImage, int[] ignoreImageIds)
	{
		super(app);
		this.firstScene = firstScene;
		this.maxImage = maxImage;
		this.ignoreImageIds = ignoreImageIds;
		backScene = S_INIT;
		nextScene = S_INIT;
		scene = S_INIT;
	}
	
	void init(String[] labels, int[] manualPages)
	{
		super.init(labels);
		initImage(maxImage);
		setFont(S_FONT_SMALL);
		if ( manualPages != null )
		{
			manual = new ManualManager(manualPages);
		}

		resetScreen();
	}
	
	public int getMaxImage()
	{
		return maxImage;
	}

	public void preLoadImage(int id)
	{
		if ( (id >= 0) && !imageIdList.contains(id) )
		{
			imageIdList.add(id);
		}
	}
	
	public boolean loadImages() throws Exception
	{
		System.out.print("Load Images: ");
		boolean changed = false;
		for ( int i = 0; i < maxImage; i++ )
		{
			if ( imageIdList.contains(i) )
			{
				System.out.print(i + " ");
				if ( !isLoadedImage(i) )
				{
					loadImage(i);
					changed = true;
				}
			}
			else
			{
				if ( isLoadedImage(i) )
				{
					releaseImage(i);
					changed = true;
				}
			}
		}
		if ( changed )
		{
			for ( int i = 0; i < imageIdList.size(); i++ )
			{
				drawImage(imageIdList.get(i), 0, 0);
			}
		}
		imageIdList.clear();
		return changed;
	}

	public int getVolumeLevel()
	{
		return volume / VOLUME_LEVEL;
	}

	public int getSEVolumeLevel()
	{
		return seVolume / VOLUME_LEVEL;
	}

	public void setVolumeLevel(int i)
	{
		setVolume(VOLUME_LEVEL * i);
	}

	public void setBGMVolumeLevel(int i)
	{
		setBGMVolume(VOLUME_LEVEL * i);
	}

	public void setSEVolumeLevel(int i)
	{
		setSEVolume(VOLUME_LEVEL * i);
	}

	public void setVolume(int volume)
	{
		setBGMVolume(volume);
		setSEVolume(volume);
	}

	public void doAction()
	{
		try
		{
			gcCounter++;
			if ( gcCounter > 100 )
			{
				System.gc();
				gcCounter = 0;
			}
		
			if ( getKeyEvent() == S_SOFT_KEY )
			{
				if ( scene != S_SCENE_MOVE )
				{
					execCommandAction(getSoftKeyNo());
				}
				keyReset();
			}
			if ( scene == S_PLAY )
			{
				execPlay();
			}
			else
			{
				switch ( scene )
				{
				case S_PRESS_FIRE_KEY:
					execPressFireKey();
					break;

				case S_TITLE:
					execTitle();
					break;
					
				case S_SCENE_MOVE:
					execSceneMove();
					break;
					
				case S_MANUAL:
					if ( manual == null )
					{
						execManual();
					}
					else
					{
						manual.execManual(this);
					}
					break;
				case S_INIT:
					execInit();
					break;
				case S_STAGE_SELECT:
					execStageSelect();
					break;
				case S_OPTION:
					execOption();
					break;
				case S_ENDING:
					execEnding();
					break;
				case S_STORY:
					execStory();
					break;
				case S_NAME_INPUT:
					execNameInput();
					break;
				case S_RANKING:
					execRanking();
					break;
				case S_OMAKE:
					execOmake();
					break;
				case S_CHARACTER_SELECT:
					execCharacterSelect();
					break;
				case S_MUSIC_ROOM:
					execMusicRoom();
					break;
				case S_TUTORIAL:
					execTutorial();
					break;
				case S_LIBRARY:
					execLibrary();
					break;
				}
			}
			
			if ( msg != null )
			{
				//setColor(255,255,255);
				setColor(255,0,0);
				drawString(msg, 0, 0);
			}
		}catch ( Exception e ) {e.printStackTrace();setColor(255,255,255);drawString(e.getMessage(),0,0);}
	}
	
	protected void execInit() throws Exception
	{
		if ( initialized )
		{
			setScene(firstScene);
		}
	}
	
	protected void doPaintInit()
	{
		drawTitle(70, 100);
	}

	protected void setScene(int next) throws Exception
	{
		setScene(next, false);
	}
	protected void setScene(int next, boolean force) throws Exception
	{
		if ( force )
		{
			scene = next;
		}
		else
		{
			this.backScene = scene;
			this.nextScene = next;
			scene = S_SCENE_MOVE;
			sceneMoveCounter = 0;
		}
		changeCommand();
		keyReset();
	}
	
	protected void execSceneMove() throws Exception
	{
		switch ( backScene )
		{
		case S_PRESS_FIRE_KEY:
			doPaintPressFireKey();
			break;
		case S_TITLE:
			doPaintTitle();
			break;
		case S_PLAY:
			doPaintPlay();

			break;
		case S_MANUAL:
			if ( manual == null )
			{
				doPaintManual();
			}
			else
			{
				manual.doPaintManual(this);
			}
			break;
		case S_INIT:
			doPaintInit();
			break;
		case S_OPTION:
			doPaintOption();
			break;
		case S_ENDING:
			doPaintEnding();
			break;
		case S_STORY:
			doPaintStory();
			break;
		case S_NAME_INPUT:
			doPaintNameInput();
			break;
		case S_RANKING:
			doPaintRanking();
			break;
		case S_OMAKE:
			doPaintOmake();
			break;
		case S_CHARACTER_SELECT:
			doPaintCharacterSelect();
			break;
		case S_MUSIC_ROOM:
			doPaintMusicRoom();
			break;
		case S_TUTORIAL:
			doPaintTutorial();
			break;
		case S_LIBRARY:
			doPaintLibrary();
			break;
		case S_STAGE_SELECT:
			doPaintStageSelect();
			break;
		}
		doPaintSceneMove();
		sceneMoveCounter++;
	}

	public void resetImages()
	{
		try
		{
			for ( int i = 0; i < getMaxImage(); i++ )
			{
				if ( GameUtil.hasNumber(i, ignoreImageIds) )
				{
					loadImage(i);
				}
				else
				{
					releaseImage(i);
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			//messageWindow.setMessage("画像読み込みに失敗しました");
		}
	}

	private SceneDrawer sd = new PordllaSceneDrawer(4);
	
	public void doPaintSceneMove() throws Exception
	{
		if ( sceneMoveCounter > sd.getEndCount() )
		{
			setScene(nextScene, true);
		}
		else if ( sceneMoveCounter == sd.getLoadCount() )
		{
			backScene = nextScene;
			sceneLoad(nextScene);
		}
		sd.paint(this, sceneMoveCounter);
	}	
	public abstract void sceneLoad(int nextScene) throws Exception;
	
	public void setSoundEnable(boolean soundEnable)
	{
		this.soundEnable = soundEnable;
		setBGMEnable(soundEnable);
		setSEEnable(soundEnable);
		resetSoundCache();
		vibrateEnable = (soundEnable) ? savedVibrateEnable : false;
	}

	public void setVibrateEnable(boolean vibrateEnable)
	{
		this.vibrateEnable = vibrateEnable;
		savedVibrateEnable = vibrateEnable;
	}
	
	public boolean isSoundEnable()
	{
		return soundEnable;
	}
	
	public int getBGMSplit(int id)
	{
		return 1;
	}
	
	public void execPressFireKey() throws Exception
	{
	}
	public void doPaintPressFireKey() throws Exception
	{
	}

	public void drawImage(ImageData d, int dx, int dy)
	{
		Rectangle r = d.getRectangle();
		drawImage(d.getImageId(), r.x, r.y, dx, dy, r.width, r.height);
	}

	public void drawImage(ImagePatternData d, int pattern, int dx, int dy)
	{
		Rectangle r = d.getRectangle(pattern);
		drawImage(d.getImageId(), r.x, r.y, dx, dy, r.width, r.height);
	}

	public abstract void execTitle() throws Exception;
	public abstract void execPlay() throws Exception;
	public abstract void doPaintTitle() throws Exception;
	//public abstract void doPaintSceneMove() throws Exception;
	public abstract void doPaintPlay() throws Exception;
	
	public abstract void changeCommand() throws Exception;



	public void execOmake() throws Exception {}
	public void execOption() throws Exception {}
	public void execManual() throws Exception {}
	public void execEnding() throws Exception {}
	public void execStory() throws Exception {}
	public void execNameInput() throws Exception {}
	public void execRanking() throws Exception {}
	public void execStageSelect() throws Exception {}
	public void execCharacterSelect() throws Exception {}
	public void execMusicRoom() throws Exception {}
	public void execTutorial() throws Exception {}
	public void execLibrary() throws Exception {}

	public void doPaintOmake() throws Exception {}
	public void doPaintStageSelect() throws Exception {}
	public void doPaintManual() throws Exception {}
	public void doPaintOption() throws Exception {}
	public void doPaintStory() throws Exception {}
	public void doPaintNameInput() throws Exception {}
	public void doPaintRanking() throws Exception {}
	public void doPaintCharacterSelect() throws Exception {}
	public void doPaintMusicRoom() throws Exception {}
	public void doPaintTutorial() throws Exception {}
	public void doPaintEnding() throws Exception {}
	public void doPaintLibrary() throws Exception {}

	public void execCommandAction(int softKeyNo) throws Exception {}
}