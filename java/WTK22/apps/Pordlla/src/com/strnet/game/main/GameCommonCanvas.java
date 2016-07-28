/*
 * Last modified: 2009/02/14 13:37:01
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

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

	public static final int VOLUME_LEVEL = MAX_VOLUME / 4;

	protected boolean initialized = false;
	protected int backScene;
	protected int nextScene;
	protected int sceneMoveCounter;
	protected String msg = null;
	protected ManualManager manual = null;
	private int firstScene;
	
	private int maxImage;
	
	protected boolean soundEnable = true;
	
	public GameCommonCanvas(Main app, int firstScene, int maxImage)
	{
		super(app);
		this.firstScene = firstScene;
		this.maxImage = maxImage;
		backScene = S_INIT;
		nextScene = S_INIT;
		scene = S_INIT;
	}
	
	public int getMaxImage()
	{
		return maxImage;
	}

	public void doAction()
	{
		try
		{
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
					if ( manual != null )
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
		this.backScene = scene;
		this.nextScene = next;
		scene = S_SCENE_MOVE;
		sceneMoveCounter = 0;
		changeCommand();
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
			if ( manual != null )
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
		case S_STAGE_SELECT:
			doPaintStageSelect();
			break;
		}
		
		sceneMoveCounter++;
		doPaintSceneMove();
	}
	
	public void setSoundEnable(boolean soundEnable)
	{
		this.soundEnable = soundEnable;
		setBGMEnable(soundEnable);
		setSEEnable(soundEnable);
	}
	
	public boolean isSoundEnable()
	{
		return soundEnable;
	}
	
	public abstract void execPressFireKey() throws Exception;
	public abstract void execTitle() throws Exception;
	public abstract void execPlay() throws Exception;

	public abstract void doPaintPressFireKey() throws Exception;
	public abstract void doPaintTitle() throws Exception;
	public abstract void doPaintSceneMove() throws Exception;
	public abstract void doPaintPlay() throws Exception;
	
	public abstract void changeCommand() throws Exception;



	public void execOption() throws Exception {}
	public void execEnding() throws Exception {}
	public void execStageSelect() throws Exception {}

	public void doPaintStageSelect() throws Exception {}
	public void doPaintOption() throws Exception {}
	public void doPaintEnding() throws Exception {}

	public void execCommandAction(int softKeyNo) throws Exception {}
}