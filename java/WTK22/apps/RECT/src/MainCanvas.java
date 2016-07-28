public class MainCanvas extends AbstractCanvas
{
	static final int S_TITLE = 0;
	static final int S_PLAY = 1;
	static final int S_ED1_1 = 20;
	static final int S_ED1_2 = 21;
	static final int S_ED1_3 = 22;
	static final int S_DELETE_RECORD = 10;
	static final int S_END = 11;
	static final int S_WAIT = 99;
	static final int[] DEF_CUR_SPEED = new int[]{2, 2, 3, 4, 5, 7, 9, 12};
	
	boolean record = false;
	int selectRow = 0;
	boolean play = true;
	int keyEventHold = 0;
	Message message;
	int sysScene = -1;
	
	public MainCanvas(Main app)
	{
		super(app);
	}
	
	public void setWaitMessage(String m, int s)
	{
		message = new Message(m);
		sysScene = s;
		scene = S_WAIT;
	}

	void init()
	{
		super.init();
		lock();
		setFont();
		resetScreen();
		setColor(255,255,255);
		drawString("Please wait...", 80, 120);
		unlock();
		
		state = new State();		
		try
		{
			int maxImage = state.init(getWidth(), getHeight());
			loadImage(maxImage);
		}
		catch ( Exception e )
		{
			state.msg = e.toString();
		}

		record = GameUtil.isRecord(State.RECORD_KEY);
		selectRow = (record) ? 1 : 0;
		scene = S_TITLE;
		changeCommand();
		setCommandListener(this);
	}

	public void run()
	{
		long sleepTime = 0L;

		init();
		/*
		try
		{
			highscore = Integer.parseInt(GameUtil.load(RECORD_KEY));
		}
		catch ( Exception ignore ) {}
		*/
		while ( play )
		{
			// graphics reset
			lock();
			resetScreen();
			
			switch ( scene )
			{
			case S_WAIT:
				message.paint(this);
				
				if ( !message.isNextCount() )
				{
					if ( !message.isNextLow() )
					{
						if ( keyEvent == S_KEY_FIRE )
						{
							scene = sysScene;
							keyReset();
							changeCommand();
						}
					}
					else
					{
						if ( keyEvent == S_KEY_FIRE )
						{
							message.nextLow();
						}
					}
				}
				else
				{
					message.nextCount();
				}
				break;
			case S_TITLE:

				if ( keyEvent == S_KEY_FIRE )
				{
					switch ( selectRow )
					{
					case 0:
						setWaitMessage("ココはどこだ…\n誰かに閉じ込められたのか…？\nとにかくここから脱出しなきゃ…", S_PLAY);
						break;
					case 1:
						if ( state.load() )
						{
							scene = S_PLAY;
							changeCommand();
						}
						else
						{
							setWaitMessage("ココはどこだ…\n誰かに閉じ込められたのか…？\nとにかくここから脱出しなきゃ…", S_PLAY);
						}
						break;
					}
				}
				else if ( keyEvent == S_KEY_UP )
				{
					if ( selectRow > 0 )
					{
						selectRow--;
					}
				}
				else if ( keyEvent == S_KEY_DOWN )
				{
					int max = (record) ? 1 : 0;
					if ( selectRow < max )
					{
						selectRow++;
					}
				}

				setColor(255,255,255);
				
				int y = 120;
				drawString("New Game...", 80, y);
				y += 20;
				if ( record )
				{
					drawString("Load Game...", 80, y);
				}

				setColor(255, 155, 255);
				drawString("●", 55, 120 + (20*selectRow));

				keyReset();
				break;
				
			case S_PLAY:
				setKeyState();
				
				// change cursor speed
				if ( isKeyPress() )
				{
					if ( state.my.speed < DEF_CUR_SPEED.length - 1 )
					{
						state.my.speed++;
					}
				}
				else
				{
					state.my.speed = 0;
				}
				if ( isUp() ) state.my.y -= DEF_CUR_SPEED[state.my.speed];
				if ( isDown() ) state.my.y += DEF_CUR_SPEED[state.my.speed];
				if ( isLeft() ) state.my.x -= DEF_CUR_SPEED[state.my.speed];
				if ( isRight() ) state.my.x += DEF_CUR_SPEED[state.my.speed];
				state.checkCursor();
				

				if ( keyEvent == S_KEY_FIRE )
				{
					if ( state.play(screenX, screenY) )
					{
						scene = S_DELETE_RECORD;
					}
					keyReset();
				}

				// item
				for ( int i = 0; i < state.item.length; i++ )
				{
					if (state.isShow(i) && state.item[i].imageId >= 0)
					{
						drawImage(image[state.item[i].imageId], state.item[i].x, state.item[i].y);
					}
				}

				// allow
				if ( !state.showMyItem )
				{
					if ( state.wallState >= State.MIN_WALL && state.wallState <= State.MAX_WALL )
					{
						int j = 0;
						if ( state.my.x < State.NEXT_WALL_AREA )
						{
							j = (state.roomLight) ? 0 : 2;
							drawImage(image[state.allow[j].imageId], state.allow[j].x, state.allow[j].y);
						}
						else if ( state.my.x > State.WALL_X + State.WALL_WIDTH - State.NEXT_WALL_AREA )
						{
							j = (state.roomLight) ? 1 : 3;
							drawImage(image[state.allow[j].imageId], state.allow[j].x, state.allow[j].y);
						}
					}
					else if ( state.wallState > State.MAX_WALL )
					{
						if ( state.my.y > State.WALL_Y + State.WALL_HEIGHT - State.NEXT_WALL_AREA )
						{
							drawImage(image[state.allow[4].imageId], state.allow[4].x, state.allow[4].y);
						}
					}
				}
				//cursor
				drawImage(image[state.my.imageId], state.my.x, state.my.y);
				
				
				// message area
				setColor(120, 0, 80);
				drawRect(0, 0, State.WALL_WIDTH - 46, 35);
				setColor(200, 0, 128);
				drawRect(1, 1, State.WALL_WIDTH - 48, 33);
				setColor(120, 0, 80);
				drawRect(2, 2, State.WALL_WIDTH - 50, 31);
				if ( state.msg != null ) 
				{
					setColor(255,200,255);
					drawString(state.msg, 12, 12);
				}
				
				setColor(120, 0, 80);
				drawRect(State.WALL_WIDTH - 46, 0, 46, 35);
				setColor(200, 0, 128);
				drawRect(State.WALL_WIDTH - 45, 1, 44, 33);
				setColor(120, 0, 80);
				drawRect(State.WALL_WIDTH - 44, 2, 42, 31);
				
				if ( state.selectedItem != null )
				{
					drawImage(image[state.selectedItem.imageId], 204, 6);
				}
				break;
				
			case S_DELETE_RECORD:
				try
				{
					if ( GameUtil.isRecord(State.RECORD_KEY) )
					{
						GameUtil.delete(State.RECORD_KEY);
					}
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}

				if ( state.ending == 3 )
				{
					setWaitMessage("出られた…のか…？\n妙に…体が…重い…\nあれ…視界が…\n\n　　　　　　　　　　Ending No.3\nNext Hint: タンスを調べよう", S_END);
				}
				else if ( state.ending == 2 )
				{
					setWaitMessage("出られた…のか…？\nやった！！出られたぞ！！！\n…ん？ポケットに何か…", S_ED1_1);
				}
				else if ( state.ending == 1 )
				{
					setWaitMessage("出られた…\nやった！！出られたぞ！！！\n…ん…？", S_ED1_2);
				}
				else if ( state.ending == 0 )
				{
					setWaitMessage("出られた…\nやった！！出られたぞ！！！\n…ん？ポケットに何か…", S_ED1_3);
				}
				changeCommand();
				break;
				
			case S_ED1_1:
				setWaitMessage("　　　『たすけて』　　　\n\n　　　　　　　　　　Ending No.2\nNext Hint: 最速でクリアしよう", S_END);
				break;
				
			case S_ED1_2:
				setWaitMessage("いる…\n僕の後ろにピタリと…\nあの人形が…！！！\n\n　　　　　　　　　　Ending No.1\nNext Hint: 最速で、人形", S_END);
				break;
				
			case S_ED1_3:
				setWaitMessage("　　　『ありがとう』　　　\n\n　　　　　　　　　　Ending No.0", S_END);
				break;
			case S_END:
				if ( keyEvent == S_KEY_FIRE )
				{
					init();
					keyReset();
				}
				
				break;
			}
			if ( keyEvent != -1 )
			{
				keyEventHold++;
				if ( keyEventHold == 4 )
				{
					keyReset();
				}
			}
			
			unlock();
			while ( System.currentTimeMillis() < sleepTime + 50 && System.currentTimeMillis() - sleepTime < 50);
			
			sleepTime = System.currentTimeMillis();
		}
	}
	
	public void keyReset()
	{
		keyEvent = -1;
		keyEventHold = 0;
	}

	public void terminate()
	{
		play = false;
		if ( scene == S_PLAY )
		{
			state.save();
		}
	}
	
	public void resetScreen()
	{
		setColor(0,0,0);
		fillRect(0, 0, getWidth(), getHeight());
	}
	
	void changeCommand()
	{
		removeCommands();
		if ( scene == S_PLAY )
		{
			if ( state.showMyItem )
			{
				addCommand(0, 2);
				addCommand(1, 1);
			}
			else
			{
				addCommand(0, 0);
				addCommand(1, 1);
			}
		}
		else
		{
			addCommand(0, 3);
			addCommand(1, 1);
		}
	}
	
	void doCommandAction(int i)
	{
		switch ( i )
		{
		case 0:
			state.showMyItem = true;
			break;
		case 1:
			terminate();
			try
			{
				app.notifyDestroyed();
			} catch ( Exception ignore ) {}
			break;
		case 2:
			state.hideItems();
			break;
		}
		changeCommand();
	}
}
