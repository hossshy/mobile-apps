/*
 * Last modified: 2009/06/19 22:46:32
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil
{
	public static void close(InputStream in)
	{
		if ( in != null )
		{
			try
			{
				in.close();
			}
			catch ( Exception ignore ) {}
		}
	}

	public static void close(OutputStream out)
	{
		if ( out != null )
		{
			try
			{
				out.close();
			}
			catch ( Exception ignore ) {}
		}
	}
}
