/*
 * Last modified: 2009/06/10 13:56:18
 * author: Kazamai, Kou
 */
package com.strnet.game.interpreter;

import java.util.Vector;
import com.strnet.game.common.StringTokenizer;

/**
 * ’´ƒ~ƒjŒ¾Œê
 */
public class Interpreter
{
	public static CommandListNode parse(String code)
	{
		StringTokenizer st = new StringTokenizer(code, " \r\n\t");
		
		String tmp;
		CommandListNode list = new CommandListNode();
		while ( ((tmp = st.nextToken()) != null ) )
		{
			if ( tmp.equals("if") )
			{
				list.add(makeIf(st));
			}
			else if ( tmp.equals("set") )
			{
				list.add(makeSet(st));
			}
			else
			{
				throw new IllegalArgumentException(tmp);
			}
		}
		
		return list;
	}
	
	private static CommandNode makeSet(StringTokenizer st)
	{
		CommandNode node = new CommandNode();
		node.setCommand(st.nextToken());
		node.setArg(st.nextToken());
		return node;
	}
	
	private static IfNode makeIf(StringTokenizer st)
	{
		String tmp;
		IfNode ifNode = new IfNode();
		CommandListNode ifCommand = new CommandListNode();
		CommandListNode execCommand = new CommandListNode();
		
		while ( ((tmp = st.nextToken()) != null ) )
		{
			if ( tmp.equals("isset") )
			{
				ifCommand.add(makeSet(st));
			}
			else if ( tmp.equals("set") )
			{
				execCommand.add(makeSet(st));
			}
			else if ( tmp.equals("else") )
			{
				ifNode.setElseNode(makeIf(st));
				ifNode.setIfCommandListNode(ifCommand);
				ifNode.setExecCommandListNode(execCommand);
				return ifNode;
			}
			else if ( tmp.equals("end") )
			{
				ifNode.setIfCommandListNode(ifCommand);
				ifNode.setExecCommandListNode(execCommand);
				return ifNode;
			}
			else
			{
				throw new IllegalArgumentException(tmp);
			}
		}
		throw new IllegalArgumentException();
	}



	private InterpreterEvent event;
	
	public Interpreter(InterpreterEvent event)
	{
		this.event = event;
	}
	
	public void execEvent(CommandListNode list)
	{
		for ( int i = 0; i < list.getCommands().size(); i++ )
		{
			Node node = (Node)list.getCommands().elementAt(i);
			CommandListNode execListNode = null;
			if ( node instanceof IfNode )
			{
				execListNode = execIf((IfNode) node);
			}
			else if ( node instanceof CommandListNode )
			{
				execListNode = (CommandListNode) node;
			}
			else
			{
				CommandNode n = (CommandNode) node;
				event.executeCommand(n);
			}
		
			if ( execListNode != null )
			{
				for ( int j = 0; j < execListNode.getCommands().size(); j++ )
				{
					CommandNode n = (CommandNode) execListNode.getCommands().elementAt(j);
					event.executeCommand(n);
				}
			}
		}
	}
	
	public CommandListNode execIf(IfNode node)
	{
		if ( node == null )
		{
			return null;
		}

		boolean flag = true;
		if ( node.getIfCommandListNode() != null )
		{
			Vector v = node.getIfCommandListNode().getCommands();
			for ( int i = 0; i < v.size(); i++ )
			{
				CommandNode n = (CommandNode) v.elementAt(i);
				flag &= event.executeIfCommand(n);
			}
		}
		if ( flag )
		{
			return node.getExecCommandListNode();
		}
		else
		{
			return execIf(node.getElseNode());
		}
	}
}