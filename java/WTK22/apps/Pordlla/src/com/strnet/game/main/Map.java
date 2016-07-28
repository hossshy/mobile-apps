/*
 * Last modified: 2009/05/30 13:36:06
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import java.util.Vector;

public class Map
{
	static final int AREA_EASY = 0;
	static final int AREA_NORMAL = 1;
	static final int AREA_HARD = 2;
	static final int AREA_HOLY = 3;


	static final int MAX_COLS = 7;
	static final int MAX_ROWS = 10;

	static Vector maps;
	static Vector rotates;
	public static int dropCount = 0;

	public static void init()
	{
		maps = new Vector();
		rotates = new Vector();

		EasyAutoMap.init(maps, rotates);
		NormalAutoMap.init(maps, rotates);
		HardAutoMap.init(maps, rotates);
	}
	
	public static int getMaxStage()
	{
		if ( maps == null ) return 0;
		return maps.size();
	}
	
	public static int randomSetup(Cell[][] map)
	{
		StringBuffer rotate = new StringBuffer();
		for ( int i = 0; i < MAX_ROWS; i++ )
		{
			rotate.append(GameUtil.rand(7));
		}
		
		int stage = GameUtil.rand(getMaxStage());
		
		setup(stage, map, rotate.toString());
		return stage;
	}

	public static void setup(int stage, Cell[][] map)
	{
		String r = (String) rotates.elementAt(stage);
		setup(stage, map, r);
	}

	public static int[][] getMap(int stage)
	{
		return (int[][])maps.elementAt(stage);
	}
	
	public static void setup(int stage, Cell[][] map, String rotate)
	{
		int[][] copy = (int[][]) maps.elementAt(stage);
		
		for ( int row = 0; row < MAX_ROWS; row++ )
		{
			for ( int col = 0; col < MAX_COLS; col++ )
			{
				map[row][col].reset();
				map[row][col].id = copy[row][col];
			}
		}
		rotatesLeft(map,rotate);
	}
	
	public static void rotatesLeft(Cell[][] map, String rotate)
	{
		for ( int i = 0; i < rotate.length(); i++ )
		{
			int count = Integer.parseInt(rotate.charAt(i) + "");
			while ( --count >= 0 )
			{
				rotateLeft(i, map);
			}
		}
	}
	
	public static void rotateLeft(int row, Cell[][] map)
	{
		int left = map[row][0].id;
		
		for ( int col = 0; col < MAX_COLS - 1; col++ )
		{
			map[row][col].id = map[row][col+1].id;
		}
		map[row][MAX_COLS-1].id = left;
	}
	
	public static void rotateRight(int row, Cell[][] map)
	{
		int right = map[row][MAX_COLS - 1].id;
		
		for ( int col = MAX_COLS - 1; col > 0; col-- )
		{
			map[row][col].id = map[row][col-1].id;
		}
		map[row][0].id = right;
	}
	
	public static boolean drop(Cell[][] map)
	{
		boolean ret = false;
		for ( int row = MAX_ROWS - 1; row > 0; row-- )
		{
			for ( int col = 0; col < MAX_COLS; col++ )
			{
				if ( (map[row][col].id == 0) && 
					 (map[row - 1][col].id > 0) )
				{
					ret = true;
					map[row][col].id = map[row - 1][col].id;
					map[row - 1][col].id = 0;
					int mc = map[row - 1][col].getMoveCount();
					/*
					if ( (mc >= Cell.DOWN_MOVE.length - 1) && (dropCount < 2) )
					{
						mc -= 3;
					}
					*/
					map[row][col].down(mc);
				}
			}
		}
		return ret;
	}
	
	public static void print(Cell[][] map)
	{
		for ( int row = 0; row < Map.MAX_ROWS; row++ )
		{
			for ( int col = 0; col < Map.MAX_COLS; col++ )
			{
				System.out.print(map[row][col].id + ",");
			}
			System.out.println("");
		}
	}
	
	public static boolean isClear(Cell[][] map)
	{
		for ( int row = 0; row < Map.MAX_ROWS; row++ )
		{
			for ( int col = 0; col < Map.MAX_COLS; col++ )
			{
				if ( map[row][col].id > 0 )
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public static int check(Cell[][] map, int row, int col, int id)
	{
		if ( row < 0 || row >= MAX_ROWS )
		{
			return 0;
		}
		else if ( col < 0 || col >= MAX_COLS )
		{
			return 0;
		}
		
		if ( !map[row][col].checked && map[row][col].id == id )
		{
			map[row][col].checked = true;
			int ret = 1;
			if ( (row - 1) >= 0 )
			{
				ret += check(map, row - 1, col, map[row][col].id);
			}
			if ( (row + 1) < MAX_ROWS )
			{
				ret += check(map, row + 1, col, map[row][col].id);
			}
			if ( (col - 1) >= 0 )
			{
				ret += check(map, row, col - 1, map[row][col].id);
			}
			if ( (col + 1) < MAX_COLS )
			{
				ret += check(map, row, col + 1, map[row][col].id);
			}
			return ret;
		}
		return 0;
	}
	
	public static void clear(Cell[][] map, int row, int col, int id)
	{
		if ( (row < 0) || (row >= MAX_ROWS) || (col >= MAX_COLS) || (col < 0) )
		{
			return;
		}

		if ( !map[row][col].clear && map[row][col].id == id )
		{
			map[row][col].clear();
			if ( (row - 1) >= 0 )
			{
				clear(map, row - 1, col, map[row][col].id);
			}
			if ( (row + 1) < MAX_ROWS )
			{
				clear(map, row + 1, col, map[row][col].id);
			}
			if ( (col - 1) >= 0 )
			{
				clear(map, row, col - 1, map[row][col].id);
			}
			if ( (col + 1) < MAX_COLS )
			{
				clear(map, row, col + 1, map[row][col].id);
			}
		}
	}

	public static int getUpRow(Cell[][] map, int nowRow)
	{
		for ( int i = (nowRow - 1); i >= 0 ; i-- )
		{
			if ( Map.hasBlock(map, i) )
			{
				return i;
			}
		}
		return -1;
	}

	public static int getDownRow(Cell[][] map, int nowRow)
	{
		for ( int i = (nowRow + 1); i < map.length ; i++ )
		{
			if ( Map.hasBlock(map, i) )
			{
				return i;
			}
		}
		return -1;
	}
	
	public static boolean hasBlock(Cell[][] map, int row)
	{
		for ( int col = 0; col < Map.MAX_COLS; col++ )
		{
			if ( map[row][col].id > 0 )
			{
				return true;
			}
		}
		return false;
	}
}