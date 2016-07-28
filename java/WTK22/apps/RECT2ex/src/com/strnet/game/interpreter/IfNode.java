/*
 * Last modified: 2009/05/20 18:23:35
 * author: Kazamai, Kou
 */
package com.strnet.game.interpreter;

public class IfNode implements Node
{
	private CommandListNode ifCommand = null;
	private CommandListNode execCommand;
	private IfNode elseNode = null;

	protected void setIfCommandListNode(CommandListNode ifCommand)
	{
		this.ifCommand = ifCommand;
	}
	
	public CommandListNode getIfCommandListNode()
	{
		return ifCommand;
	}

	protected void setExecCommandListNode(CommandListNode execCommand)
	{
		this.execCommand = execCommand;
	}

	public CommandListNode getExecCommandListNode()
	{
		return execCommand;
	}
	
	protected void setElseNode(IfNode elseNode)
	{
		this.elseNode = elseNode;
	}

	public IfNode getElseNode()
	{
		return elseNode;
	}
}