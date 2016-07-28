/*
 * Last modified: 2008/09/03 11:03:26
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

public class StringTokenizer
{
	private int offset = 0;
	private char[] delimiters;
	private String str;
	
	public StringTokenizer(String str, String delim)
	{
		this.str = str;
		offset = 0;
		delimiters = new char[delim.length()];
		for ( int i = 0; i < delim.length(); i++ )
		{
			delimiters[i] = delim.charAt(i);
		}
	}
	
	public String nextToken()
	{
		StringBuffer ret = new StringBuffer();
		int end;
		for ( end = offset; end < str.length(); end++ )
		{
			char c = str.charAt(end);
			if ( isDelimiter(c) )
			{
				if ( ret.length() > 0 )
				{
					break;
				}
			}
			else
			{
				ret.append(c);
			}
		}
		offset = end;
		if ( ret.length() == 0 )
		{
			return null;
		}
		else
		{
			return ret.toString();
		}
	}
	
	private boolean isDelimiter(char c)
	{
		for ( int i = 0; i < delimiters.length; i++ )
		{
			if ( c == delimiters[i] )
			{
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args)
	{
		String s = "set item 5\r\n\tif 6";
		StringTokenizer st = new StringTokenizer(s, "\r\n\t ");
		String tmp;
		while ( ((tmp = st.nextToken()) != null ) )
		{
			System.out.println("["+tmp+"]");
		}
	}
}
