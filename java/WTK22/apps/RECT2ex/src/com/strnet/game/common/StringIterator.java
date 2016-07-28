/*
 * Last modified: 2009/06/29 21:12:04
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class StringIterator
{
	private String[] program;
	private int seek;
	
	public StringIterator(String str)
	{
		program = GameUtil.split(str, '\n', true);
		seek = 0;
	}
	
	public boolean hasNext()
	{
		return seek < program.length;
	}

	public String next()
	{
		return program[seek++];
	}
}
