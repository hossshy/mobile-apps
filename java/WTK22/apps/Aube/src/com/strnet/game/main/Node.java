/*
 * Last modified: 2008/07/05 21:58:51
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Point;

public class Node extends Point
{
	Node parent = null;
	int cost = 0;
	int heuristic = 0;
	char allow;
	
	public Node()
	{
	}
	
	public Node(Point p)
	{
		this(p.x, p.y);
	}
	
	public Node(int x, int y)
	{
		super(x, y);
	}
}
