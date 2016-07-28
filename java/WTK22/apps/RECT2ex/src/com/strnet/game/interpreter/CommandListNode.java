/*
 * Last modified: 2009/05/20 18:27:49
 * author: Kazamai, Kou
 */
package com.strnet.game.interpreter;

import java.util.Vector;

public class CommandListNode implements Node
{
	Vector commands = new Vector();
	
	protected void add(Node c)
	{
		commands.addElement(c);
	}
	
	public Vector getCommands()
	{
		return commands;
	}
}