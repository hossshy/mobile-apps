/*
 * Last modified: 2009/01/22 15:28:45
 * @author Kazamai, Kou
 */
package com.strnet.game.main;


public class SaveDataCreator
{
	private StringBuffer sb;
	private char delimiter;
	
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
		return sb.toString();
	}
}