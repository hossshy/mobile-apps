/*
 * Last modified: 2009/05/21 01:01:23
 * @author Kazamai, Kou
 */
package com.strnet.game.common;


public class SaveDataCreator
{
	private static final char DEFAULT_COLON_DELIMITER = ':';
	private StringBuffer sb;
	private char delimiter;
	
	public SaveDataCreator()
	{
		this(DEFAULT_COLON_DELIMITER);
	}

	public SaveDataCreator(char delimiter)
	{
		sb = new StringBuffer();
		this.delimiter = delimiter;
	}
	
	public void addDelimiter()
	{
		sb.append(delimiter);
	}
	
	public void add(String s)
	{
		sb.append(s);
		addDelimiter();
	}
	
	public void add(Object o)
	{
		sb.append(o);
		addDelimiter();
	}
	
	public void add(int s)
	{
		sb.append(s);
		addDelimiter();
	}
	
	public void add(boolean s)
	{
		sb.append(s ? 1 : 0);
		addDelimiter();
	}
	
	public String toString()
	{
		// ÅŒã‚ÌƒfƒŠƒ~ƒ^‚ðÁ‚·
		if ( sb.length() > 0 )
		{
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
}