/*
 * Last modified: 2010/03/16 23:42:14
 * @author Kazamai, Kou
 */
package com.strnet.game.io;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.list.StringLightList;
import java.util.Hashtable;

public class StartEndReader
{
	private Hashtable values;

	public StringLightList get(String key)
	{
		return (StringLightList) values.get(key);
	}

	public StartEndReader(String str) throws Exception
	{
		this(str, true);
	}

	public StartEndReader(String str, boolean ignore) throws Exception
	{
		String[] lines = GameUtil.split(str, '\n');
		
		values = new Hashtable();
		StringLightList list = new StringLightList();
		String key = null;
		for ( int i = 0; i < lines.length; i++ )
		{
			String tmp = lines[i].trim();
			if ( tmp.length() <= 0 )
			{
				if ( !ignore && key != null )
				{
					tmp = " ";
				}
				else
				{
					continue;
				}
			}
			
			if ( tmp.charAt(0) == ':' )
			{
				if ( key == null )
				{
					key = tmp;
				}
				else
				{
					if ( key.equals(tmp) )
					{
						values.put(key, list);
						key = null;
						list = new StringLightList();
					}
					else
					{
						throw new Exception("invalid data." + tmp + "," + key);
					}
				}
			}
			else if ( tmp.charAt(0) == '#' )
			{
				continue;
			}
			else
			{
				list.add(tmp);
			}
		}
	}
}