/*
 * Last modified: 2008/07/15 00:17:40
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.MapChip;
import com.strnet.game.common.Point;
import java.util.Vector;

public class ASter
{
	private static ASter instance = null;
	
	public static void init()
	{
		instance = new ASter();
	}
	
	public static ASter getInstance()
	{
		return instance;
	}
	
	public Node search(int[][] map, MapChip[] chips, Point start, Point end)
	{
		Vector openList = new Vector();
		Vector closeList = new Vector();
		
		openList.addElement(new Node(start));

		while ( openList.size() > 0 )
		{
			Node now = null;
			for ( int i = 0; i < openList.size(); i++ )
			{
				Node tmp = (Node) openList.elementAt(i);
				if ( (now == null) || (tmp.cost + tmp.heuristic) < (now.cost + now.heuristic) )
				{
					now = tmp;
				}
			}
			
			if ( (now.x == end.x) && (now.y == end.y) )
			{
				return now;
			}
			else
			{
				closeList.addElement(now);
				openList.removeElement(now);
				add(map, chips, end, openList, closeList, now, now.x - 1, now.y, 'L');
				add(map, chips, end, openList, closeList, now, now.x + 1, now.y, 'R');
				add(map, chips, end, openList, closeList, now, now.x, now.y - 1, 'U');
				add(map, chips, end, openList, closeList, now, now.x, now.y + 1, 'D');
			}
		}
		return null;
	}

	private void add(int[][] map, MapChip[] chips, Point end, Vector openList, Vector closeList, Node now, int tmpx, int tmpy, char allow)
	{
		if ( GameUtil.isMoveMap(map, chips, tmpx, tmpy) &&
			 !hasElement(openList, tmpx, tmpy) &&
			 !hasElement(closeList, tmpx, tmpy) )
		{
			Node n = new Node(tmpx, tmpy);
			n.cost = now.cost + 1;
			n.heuristic = (GameUtil.abs(end.x - n.x)) + (GameUtil.abs(end.y - n.y));
			n.parent = now;
			n.allow = allow;
			openList.addElement(n);
		}

	}
	
	private boolean hasElement(Vector v, int tmpx, int tmpy)
	{
		for ( int i = 0; i < v.size(); i++ )
		{
			Point tmp = (Point) v.elementAt(i);
			if ( (tmp.x == tmpx) && (tmp.y == tmpy) )
			{
				return true;
			}
		}
		return false;
	}

	public void buildAllows(StringBuffer sb, Node n)
	{
		if ( n.parent != null )
		{
			buildAllows(sb, n.parent);
		}
		sb.append(n.allow);
	}
}
