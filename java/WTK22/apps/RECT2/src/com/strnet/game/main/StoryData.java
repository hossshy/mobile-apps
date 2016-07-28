/*
 * Last modified: 2010/05/24 22:53:22
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.component.MessageWindow;
import com.strnet.game.interpreter.CommandListNode;
import com.strnet.game.interpreter.CommandNode;
import com.strnet.game.interpreter.Interpreter;

public class StoryData
{
	CommandListNode listNode = null;
	int imageId = -1;
	MessageWindow storyMessageWindow;

	
	public StoryData(String code, MessageWindow storyMessageWindow) throws Exception
	{
		listNode = Interpreter.parse(code);
		this.storyMessageWindow = storyMessageWindow;
		storyMessageWindow.clear();
	}
	
	public void next(MainCanvas g) throws Exception
	{
		CommandNode node = (CommandNode) listNode.getCommands().elementAt(0);
		listNode.getCommands().removeElementAt(0);
			
		if ( "image".equals(node.getCommand()) )
		{
			imageId = Integer.parseInt(node.getArg());
			storyMessageWindow.clear();
		}
		else if ( "msg".equals(node.getCommand()) )
		{
			storyMessageWindow.setMessage(node.getArg());
		}
		else if ( "play".equals(node.getCommand()) )
		{
			int roomId = Integer.parseInt(node.getArg());
			
			if ( roomId >= 0 )
			{
				g.goNextRoom(roomId);
			}
			g.setScene(MainCanvas.S_PLAY);
		}
		else if ( "title".equals(node.getCommand()) )
		{
			g.setScene(MainCanvas.S_TITLE);
		}
		else if ( "ending".equals(node.getCommand()) )
		{
			g.doClear();
		}
		else
		{
			throw new IllegalArgumentException(node.getCommand());
		}
	}
	
	public void paint(AbstractCanvas g)
	{
		if ( imageId >= 0 )
		{
			g.drawImage(imageId, 0, 0);
		}
		else
		{
			g.resetScreen();
		}
		storyMessageWindow.paint(g);
	}
}