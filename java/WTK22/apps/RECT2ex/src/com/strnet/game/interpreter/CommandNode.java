/*
 * Last modified: 2009/06/10 13:53:47
 * author: Kazamai, Kou
 */
package com.strnet.game.interpreter;

public class CommandNode implements Node
{
	private String command;
	private String arg;
	
	protected void setCommand(String command)
	{
		this.command = command;
	}
	
	public String getCommand()
	{
		return command;
	}

	protected void setArg(String arg)
	{
		this.arg = arg;
	}
	
	public String getArg()
	{
		return arg;
	}
	
	public String toString()
	{
		return command + " : " + arg;
	}
}