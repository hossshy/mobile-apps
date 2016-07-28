/*
 * Last modified: 2008/11/09 18:25:22
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.component.MessageWindow;

public class StoryData
{
	CommandListNode listNode = null;
	int imageId = -1;
	MessageWindow storyMessageWindow;

	
	public StoryData(String code, MessageWindow storyMessageWindow) throws Exception
	{
		listNode = Interpreter.parse(code, false);
		this.storyMessageWindow = storyMessageWindow;
		storyMessageWindow.clear();
	}
	
	public void next(MainCanvas g) throws Exception
	{
		CommandNode node = (CommandNode) listNode.commands.elementAt(0);
		listNode.commands.removeElementAt(0);
			
		if ( "image".equals(node.command) )
		{
			//if ( imageId >= 0 )
			//{
			//g.releaseImage(imageId);
			//}
			imageId = Integer.parseInt(node.arg);
			//if ( imageId >= 0 )
			//{
			//g.loadImage(imageId);
			//}
			storyMessageWindow.clear();
		}
		else if ( "msg".equals(node.command) )
		{
			storyMessageWindow.setMessage(node.arg);
		}
		else if ( "omake".equals(node.command) )
		{
			g.doPlayInitOmake();
		}
		else if ( "play".equals(node.command) )
		{
			int roomId = Integer.parseInt(node.arg);
			
			if ( roomId >= 0 )
			{
				g.goNextRoom(roomId);
			}
			g.setScene(MainCanvas.S_PLAY);
		}
		else if ( "title".equals(node.command) )
		{
			g.setScene(MainCanvas.S_TITLE);
		}
		else if ( "ending".equals(node.command) )
		{
			g.doClear();
		}
		else
		{
			throw new IllegalArgumentException(node.command);
		}
	}
	
	public void paint(AbstractCanvas g)
	{
		if ( imageId >= 0 )
		{
			g.drawImage(imageId, 0, 0);
		}
		storyMessageWindow.paint(g);
	}
}