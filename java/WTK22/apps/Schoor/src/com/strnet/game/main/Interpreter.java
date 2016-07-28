/*
 * Last modified: 2008/09/03 11:43:34
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.StringTokenizer;

public class Interpreter
{
	public static CommandListNode parse(String code)
	{
		return parse(code, true);
	}
	
	public static CommandListNode parse(String code, boolean ifCmd)
	{
		StringTokenizer st = new StringTokenizer(code, " \r\n\t");
		
		String tmp;
		CommandListNode list = new CommandListNode();
		while ( ((tmp = st.nextToken()) != null ) )
		{
			if ( ifCmd && tmp.equals("if") )
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
		node.command = st.nextToken();
		node.arg = st.nextToken();
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
				ifNode.elseNode = makeIf(st);
				ifNode.ifCommand = ifCommand;
				ifNode.execCommand = execCommand;
				return ifNode;
			}
			else if ( tmp.equals("end") )
			{
				ifNode.ifCommand = ifCommand;
				ifNode.execCommand = execCommand;
				return ifNode;
			}
			else
			{
				throw new IllegalArgumentException(tmp);
			}
		}
		throw new IllegalArgumentException();
	}
}