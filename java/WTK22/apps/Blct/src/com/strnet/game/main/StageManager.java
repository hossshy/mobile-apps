/*
 * Last modified: 2010/03/16 12:05:30
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.common.GameUtil;
import com.strnet.game.common.Rectangle;
import com.strnet.game.io.StartEndReader;
import com.strnet.game.common.list.StringLightList;
import com.strnet.game.component.ImagePatternData;

public class StageManager
{
	private static final int STAGE_RANK[] = new int[]{1,1,2,3,3,2};

	private static StageManager instance;
	private int counter;
	private BackgroundData background;
	private int backupImageId = -1;
	private int backupSpeed = -1;
	private HPGauge hpGauge = null;
	private EnemyData enemy = null;
	private int gameover = -1;
	private int stageClear = -1;
	private int stageBGM;
	private int bossBGM;
	private boolean noStart;
	
	private ScheduleData[] schedule;
	private int scheduleSeek;
	private boolean lastEnemy;
	private int nowStageNo = -1;
	private boolean bossMode = false;
	private int mode;
	private int rank;

	public static void init(int w, int h)
	{
		instance = new StageManager(w, h);
	}

	public static StageManager getInstance()
	{
		return instance;
	}

	public StageManager(int w, int h)
	{
		background = new BackgroundData(w, h);
		rank = 0;
		reset();
	}
	
	public void setStage(int stageNo, int mode)
	{
		try
		{
			if ( stageNo >= FighterInformationManager.ADD_BOSS_STAGE )
			{
				stageNo -= FighterInformationManager.ADD_BOSS_STAGE;
				bossMode = true;
			}
			else
			{
				bossMode = false;
			}
			this.nowStageNo = stageNo;
			
			String str = MainCanvas.getInstance().readTextFile(stageNo + ".dat");

			StartEndReader reader = new StartEndReader(str);
			StringLightList list = null;

			list = reader.get(":BG_IMAGE");
			backupImageId = Integer.parseInt(list.get(0));
			setBackground(backupImageId);
			if ( canPaintBackground() )
			{
				MainCanvas.getInstance().preLoadImage(backupImageId);
			}
			list = reader.get(":BG_SPEED");
			backupSpeed = Integer.parseInt(list.get(0));
			setBackgroundSpeed(backupSpeed);
		
			list = reader.get(":STAGE_BGM");
			stageBGM = Integer.parseInt(list.get(0));
			list = reader.get(":BOSS_BGM");
			bossBGM = Integer.parseInt(list.get(0));
			
			list = reader.get(":ENEMY");
			schedule = new ScheduleData[list.size()];
			for ( int i = 0; i < schedule.length; i++ )
			{
				schedule[i] = new ScheduleData(list.get(i));
				if ( schedule[i].isBgImage() )
				{
					if ( canPaintBackground() )
					{
						MainCanvas.getInstance().preLoadImage(schedule[i].getId());
					}
				}
			}
			scheduleSeek = 0;

			if ( mode == MainCanvas.MODE_HELL )
			{
				list = reader.get(":HELL_ENEMY_DIC");
			}
			else if ( mode == MainCanvas.MODE_HARD )
			{
				list = reader.get(":HARD_ENEMY_DIC");
			}
			else
			{
				list = reader.get(":ENEMY_DIC");
			}
			
			Dictionary.setEnemies(list);
			this.mode = mode;
			reset();
		}
		catch ( Exception e )
		{
			MainCanvas.getInstance().error(e, "ƒ[ƒh‚ÉŽ¸”s");
		}
	}
	
	public void reset()
	{
		counter = 0;
		gameover = -1;
		stageClear = -1;
		lastEnemy = false;
		scheduleSeek = 0;
		lastEnemy = false;
		noStart = true;
		setBackground(backupImageId);
		setBackgroundSpeed(backupSpeed);
		if ( hpGauge != null )
		{
			hpGauge.reset();
		}
		if ( background != null )
		{
			background.reset();
		}
			
		if ( bossMode )
		{
			if ( nowStageNo == 1 )
			{
				background.setSpeed(4);
			}
			
			for ( ; scheduleSeek < schedule.length; scheduleSeek++ )
			{
				if ( schedule[scheduleSeek].isWarning() )
				{
					counter = schedule[scheduleSeek].getScheduleCount() - 80;
					break;
				}
			}
		}
	}

	public void resetRank()
	{
		rank = 0;
	}

	public int getRank()
	{
		return rank + (MainCanvas.getInstance().score.getScore() / 5000000);
	}

	public void setHPGauge(HPGauge hpGauge)
	{
		this.hpGauge = hpGauge;
	}

	public void setBackground(int imageId)
	{
		background.setImageId(imageId);
	}

	public void setBackgroundSpeed(int speed)
	{
		background.setSpeed(speed);
	}
	
	public int getStageBGM()
	{
		return stageBGM;
	}

	private void addRank()
	{
		rank += (STAGE_RANK[nowStageNo - 1] * (mode+1));
	}

	public void execute() throws Exception
	{
		MainCanvas g = MainCanvas.getInstance();
		if ( noStart )
		{
			noStart = false;
			g.startBGM(stageBGM);
		}

		if ( gameover >= 0 )
		{
			gameover++;
			if ( gameover > 20 )
			{
				g.setPlayState(MainCanvas.PLAY_STATE_GAMEOVER);
				
				g.stopBGM();
				g.addScoreAll();
				//g.onCanEasyMode();
				g.updateHighScore(true);
			}
		}
		else if ( stageClear >= 0 )
		{
			stageClear++;
			if ( stageClear > 100 )
			{
				g.stageClear();
			}
		}
		else if ( lastEnemy && hpGauge.isDie() )
		{
			addRank();
			stageClear = 0;
			g.my.offHyper();
			g.my.lock();
		}
		else
		{
			EnemyData enemy;
			counter++;
			
			for ( ; scheduleSeek < schedule.length; scheduleSeek++ )
			{
				if ( schedule[scheduleSeek].getScheduleCount() == counter )
				{
					//System.out.println(counter);
					
					if ( schedule[scheduleSeek].isWarning() )
					{
						addRank();
						BurstData bd = ObjectPool.getBurst(3);
						if ( bd != null )
						{
							bd.setPosition(schedule[scheduleSeek].getX(null),
										   schedule[scheduleSeek].getY(null));
						}
						if ( MainCanvas.getInstance().stageNo !=  MainCanvas.TRUE_BOSS_STAGE )
						{
							try
							{
								MainCanvas.getInstance().stopBGM();
							}catch ( Exception e ) {}
							try
							{
								g.startBGM(MainCanvas.BGM_WARNING);
							}catch ( Exception e ) {}
						}
					}
					else if ( schedule[scheduleSeek].isBgSpeed() )
					{
						setBackgroundSpeed(schedule[scheduleSeek].getId());
					}
					else if ( schedule[scheduleSeek].isBgImage() )
					{
						background.setNextImageId(schedule[scheduleSeek].getId());
					}
					else
					{
						enemy = ObjectPool.getEnemy(schedule[scheduleSeek].getId());
						if ( enemy != null )
						{
							enemy.setPosition(schedule[scheduleSeek].getX(enemy),
											  schedule[scheduleSeek].getY(enemy));
							
							if ( schedule[scheduleSeek].isBoss() && (hpGauge != null) )
							{
								hpGauge.setEnemy(enemy);
								if ( MainCanvas.getInstance().stageNo !=  MainCanvas.TRUE_BOSS_STAGE )
								{
									g.startBGM(bossBGM);
								}
								lastEnemy = true;
							}
							else if ( schedule[scheduleSeek].isBomb() )
							{
								enemy.haveBomb();
							}
						}
					}
				}
				else
				{
					break;
				}
			}
		}
		background.execute();
	}
	
	public void doBackground()
	{
		background.execute();
	}
	
	public int getBackgroundSpeed()
	{
		return background.getSpeed();
	}
	
	public void gameover(MainCanvas g)
	{
		gameover = 0;
	}

	public void paintBackground(MainCanvas g)
	{
		background.paint(g, MainCanvas.PLAY_SCREEN_X, MainCanvas.PLAY_SCREEN_Y);
	}
	
	public boolean canPaintBackground()
	{
		return background.canPaint();
	}
	
	public void setCanPaintBackground(boolean canPaint)
	{
		background.setCanPaint(canPaint);
	}

	public void paintForeground(MainCanvas g)
	{
		/*
		  if ( warningImage.isAlive() )
		  {
		  warningImage.move(g);
		  warningImage.paint(g);
		  }
		*/
		hpGauge.paint(g);
	}
}
