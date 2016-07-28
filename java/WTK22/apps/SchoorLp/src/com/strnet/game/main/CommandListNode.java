/*
 * Last modified: 2008/09/03 11:41:06
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import java.util.Vector;

public class CommandListNode implements Node
{
	Vector commands = new Vector();
	
	public void add(Node c)
	{
		commands.addElement(c);
	}
}