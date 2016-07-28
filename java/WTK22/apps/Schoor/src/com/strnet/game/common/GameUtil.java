/*
 * Last modified: 2008/09/04 20:33:01
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

import java.util.Random;
import java.util.Vector;

public class GameUtil
{
	private static Random rand = new Random();
	
	public static int loopIncf(int now, int min, int max)
	{
		now++;
		if ( now > max )
		{
			now = min;
		}
		else if ( now < min )
		{
			now = min;
		}
		return now;
	}

	public static int loopDecf(int now, int min, int max)
	{
		now--;
		if ( now < min )
		{
			now = max;
		}
		else if ( now > max )
		{
			now = max;
		}
		return now;
	}

	public static boolean isHit(int x0, int y0, int x1, int y1, int radius)
	{
		return (x0-x1) * (x0-x1) + (y0-y1) * (y0-y1) < radius * radius;
	}

	public static String[] split(String s, char delim)
	{
		Vector v = new Vector();
		for ( int st = 0, end = 0;; )
		{
			end = s.indexOf(delim, st);
			if ( end == -1 )
			{
				v.addElement(s.substring(st));
				break;
			}
			v.addElement(s.substring(st, end));
			st = (end + 1);
		}

		String[] ret = null;
		if ( v.size() > 0 )
		{
			ret = new String[v.size()];
			for ( int i = 0; i < v.size(); i++ )
			{
				ret[i] = (String) v.elementAt(i);
			}
		}
		return ret;
	}
	
	public static int rand(int num)
	{
		return (rand.nextInt() >>> 1) % num;
	}
	
	public static String formatMoney(int money)
	{
		String ch = String.valueOf(money);
		StringBuffer ret = new StringBuffer();
		int x = 0;
		for ( int i = ch.length() - 1; i >= 0; i-- )
		{
			ret.append(ch.charAt(i));
			x++;
			if ( (x == 3) && (i > 0) )
			{
				ret.append(',');
				x = 0;
			}
		}
		
		return ret.reverse().toString();
	}
	
	public static void main(String[] args)throws Exception
	{
		int money = Integer.parseInt(args[0]);
		System.out.println(GameUtil.formatMoney(money));
		long start = System.currentTimeMillis();
		
		for ( int i = 0;i<100000;i++)
			GameUtil.formatMoney(money);
		
		long end = System.currentTimeMillis();
		System.out.println(end  - start);
		
	}
	
	public static boolean isMoveMap(int[][] map, MapChip[] chips, int tmpx, int tmpy)
	{
		return (tmpy >= 0) && (tmpy < map[0].length) &&
			(tmpx >= 0) && (tmpx < map.length) &&
			chips[map[tmpy][tmpx]].isMove();
	}
	
	public static boolean isHitChip(Point p, Point[] ps)
	{
		for ( int i = 0; i < ps.length; i++ )
		{
			if ( ps[i] != null )
			{
				if ( equalPoint(p, ps[i]) )
				{
					return true;
				}
			}
		}
		return false;
	}
	public static boolean equalPoint(Point p1, Point p2)
	{
		return (p1.x == p2.x) && (p1.y == p2.y);
	}

	public static int abs(int i)
	{
		return (i < 0) ? (i * -1) : i;
	}
}
