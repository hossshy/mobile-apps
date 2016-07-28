/*
 * Last modified: 2010/03/23 20:54:13
 * @author Kazamai, Kou
 */
package com.strnet.game.common;

import java.util.Random;
import java.util.Vector;

public class GameUtil
{
	private static Random rand = new Random();

	/*
	 * min <= now < max
	 */
	public static int loopIncf(int now, int min, int max)
	{
		now++;
		if ( now >= max )
		{
			now = min;
		}
		else if ( now < min )
		{
			now = min;
		}
		return now;
	}

	/*
	 * min <= now < max
	 */
	public static int loopDecf(int now, int min, int max)
	{
		now--;
		if ( now < min )
		{
			now = max - 1;
		}
		else if ( now >= max )
		{
			now = max;
		}
		return now;
	}

	public static boolean isHit(int x0, int y0, int x1, int y1, int radius)
	{
		return (x0-x1) * (x0-x1) + (y0-y1) * (y0-y1) < radius * radius;
	}

	public static boolean isEmpty(String str)
	{
		return (str == null) || str.trim().length() == 0;
	}

	public static String[] split(String s, char delim)
	{
		return split(s, delim, false);
	}

	public static String[] split(String s, char delim, boolean trim)
	{
		Vector v = new Vector();
		for ( int st = 0, end = 0;; )
		{
			end = s.indexOf(delim, st);
			if ( end == -1 )
			{
				String t = s.substring(st);
				if ( !trim || !isEmpty(t) )
				{
					v.addElement(t);
				}
				break;
			}
			String t = s.substring(st, end);
			if ( !trim || !isEmpty(t) )
			{
				v.addElement(t);
			}
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
	
	public static int rand(int num, int delNo)
	{
		int ret = delNo;
		for ( int i = 0; i < 10; i++ )
		{
			ret = (rand.nextInt() >>> 1) % num;
			if ( ret != delNo )
			{
				break;
			}
		}
		return ret;
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
		if ( (tmpy >= 0) && (tmpy < map.length) &&
			 (tmpx >= 0) && (tmpx < map[0].length) )
		{
			int id = map[tmpy][tmpx];
			MapChip mc = null;
			for ( int i = 0; i < chips.length; i++ )
			{
				if ( chips[i].getId() == id )
				{
					mc = chips[i];
				}
			}
			if ( mc == null )
			{
				return false;
			}
			return mc.isMove();
		}
		return false;
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

	public static boolean hasNumber(int id, int[] ids)
	{
		if ( ids == null )
		{
			return false;
		}
		for ( int i = 0; i < ids.length; i++ )
		{
			if ( id == ids[i] )
			{
				return true;
			}
		}
		return false;
	}

	public static String leftPad(int length, char pad, int str)
	{
		return leftPad(length, pad, String.valueOf(str));
	}
	
	public static String leftPad(int length, char pad, String str)
	{
		
		StringBuffer sb = new StringBuffer();
		for ( int i = (length - str.length()); i > 0; i-- )
		{
			sb.append(pad);
		}
		sb.append(str);
		return sb.toString();
	}

	public static String[] splitForLength(String str, int length)
	{
		int maxlen = str.length();
		int size = (maxlen / length) + 1;
		if ( maxlen == length )
		{
			size = 1;
		}
		String[] ret = new String[size];
		int seek = 0;
		for ( int i = 0; i < size; i++ )
		{
			if ( (seek + length) >= maxlen )
			{
				ret[i] = str.substring(seek);
				break;
			}
			else
			{
				ret[i] = str.substring(seek, seek + length);
				seek += length;
			}
		}
		return ret;
	}

	public static void sleep(int millisec)
	{
		try
		{
			Thread.sleep(millisec);
		}
		catch ( Exception ignore ) {}
	}

	public static int parseExtraInt(String str)
	{
		char c = str.charAt(0);
		int ret = -1;
		switch ( c )
		{
		case '0': ret = 0; break;
		case '1': ret = 1; break;
		case '2': ret = 2; break;
		case '3': ret = 3; break;
		case '4': ret = 4; break;
		case '5': ret = 5; break;
		case '6': ret = 6; break;
		case '7': ret = 7; break;
		case '8': ret = 8; break;
		case '9': ret = 9; break;
		case 'a': ret = 10; break;
		case 'b': ret = 11; break;
		case 'c': ret = 12; break;
		case 'd': ret = 13; break;
		case 'e': ret = 14; break;
		case 'f': ret = 15; break;
		case 'g': ret = 16; break;
		case 'h': ret = 17; break;
		case 'i': ret = 18; break;
		case 'j': ret = 19; break;
		case 'k': ret = 20; break;
		case 'l': ret = 21; break;
		case 'm': ret = 22; break;
		case 'n': ret = 23; break;
		case 'o': ret = 24; break;
		case 'p': ret = 25; break;
		case 'q': ret = 26; break;
		case 'r': ret = 27; break;
		case 's': ret = 28; break;
		case 't': ret = 29; break;
		case 'u': ret = 30; break;
		case 'v': ret = 31; break;
		case 'w': ret = 32; break;
		case 'x': ret = 33; break;
		case 'y': ret = 34; break;
		case 'z': ret = 35; break;
		case 'A': ret = 36; break;
		case 'B': ret = 37; break;
		case 'C': ret = 38; break;
		case 'D': ret = 39; break;
		case 'E': ret = 40; break;
		case 'F': ret = 41; break;
		case 'G': ret = 42; break;
		case 'H': ret = 43; break;
		case 'I': ret = 44; break;
		case 'J': ret = 45; break;
		case 'K': ret = 46; break;
		case 'L': ret = 47; break;
		case 'M': ret = 48; break;
		case 'N': ret = 49; break;
		case 'O': ret = 50; break;
		case 'P': ret = 51; break;
		case 'Q': ret = 52; break;
		case 'R': ret = 53; break;
		case 'S': ret = 54; break;
		case 'T': ret = 55; break;
		case 'U': ret = 56; break;
		case 'V': ret = 57; break;
		case 'W': ret = 58; break;
		case 'X': ret = 59; break;
		case 'Y': ret = 60; break;
		case 'Z': ret = 61; break;
		case '!': ret = 62; break;
		case '"': ret = 63; break;
		case '$': ret = 64; break;
		case '%': ret = 65; break;
		case '&': ret = 66; break;
		case '\'': ret = 67; break;
		case '(': ret = 68; break;
		case ')': ret = 69; break;
		case '*': ret = 70; break;
		case '+': ret = 71; break;
		case ',': ret = 72; break;
		case '-': ret = 73; break;
		case '.': ret = 74; break;
		case '/': ret = 75; break;
		case '@': ret = 76; break;
		case ';': ret = 77; break;
		case '<': ret = 78; break;
		case '=': ret = 79; break;
		case '>': ret = 80; break;
		case '?': ret = 81; break;
		case '{': ret = 82; break;
		case '|': ret = 83; break;
		case '}': ret = 84; break;
		case '~': ret = 85; break;
		case '_': ret = 86; break;
		case '^': ret = 87; break;
		case '[': ret = 88; break;
		case ']': ret = 89; break;
		case '`': ret = 90; break;
		}

		return ret;
	}
	
	public static String urlEncode(String in)
	{
		StringBuffer inBuf = new StringBuffer(in);
		StringBuffer outBuf = new StringBuffer();

		for ( int i = 0; i < inBuf.length(); i++ )
		{
			char temp = inBuf.charAt(i);
			if (('a'<=temp && temp <='z')
                || ('A'<=temp && temp <='Z')
                || ('0'<=temp && temp <='9')
                || temp == '.' || temp == '-' || temp == '*' || temp == '_')
			{
				outBuf.append(temp);
			}
			else if ( temp == ' ' )
			{
				outBuf.append('+');
			}
			else
			{
				byte[] bytes;
				try
				{
					bytes = new String(new char[]{temp}).getBytes("SJIS");
					for( int j = 0; j < bytes.length; j++ )
					{
						int high = (bytes[j]>>>4)&0x0F;
						int low  = (bytes[j]&0x0F);
						outBuf.append('%');
						outBuf.append(Integer.toString(high, 16).toUpperCase());
						outBuf.append(Integer.toString(low , 16).toUpperCase());
					}
				}
				catch (Exception e)
				{
				}
			}
		}
		return outBuf.toString();
    }
	
	public static String getEncodeScore(int score)
	{
		return getEncodeScore(score, ';');
	}
	public static String getEncodeScore(int score, char delimiter)
	{
		int checkSum = 0;
		String tmp = String.valueOf(score);
		for ( int i = 0; i < tmp.length(); i++ )
		{
			checkSum += Integer.parseInt(tmp.substring(i, i+1));
		}
		String hex = Integer.toHexString(score);
		String prefixHex = Integer.toHexString(hex.length());
		checkSum += hex.length();
		SaveDataCreator ret = new SaveDataCreator(delimiter);
		ret.add(prefixHex);
		ret.add(hex);
		ret.add(Integer.toOctalString(checkSum));
		StringBuffer sb = new StringBuffer();
		sb.append(ret);
		return sb.reverse().toString();
	}
	
	public static String makeTextKey(String str)
	{
		int ret = str.length();
		
		for ( int i = 0; i < str.length(); i++ )
		{
			if ( Character.isDigit(str.charAt(i)) )
			{
				ret += Character.digit(str.charAt(i), 10);
			}
		}
		return getEncodeScore(ret, '0');
	}
	
	public static boolean checkValidText(String key, String str)
	{
		return key.equals(makeTextKey(str));
	}
}
