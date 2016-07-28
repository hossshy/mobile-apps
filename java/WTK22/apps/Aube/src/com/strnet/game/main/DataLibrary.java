/*
 * Last modified: 2008/07/06 22:55:58
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class DataLibrary
{
	static final EnemyData[] s_enemys = new EnemyData[3];
	static final Magic[] magics = new Magic[1];
	static int[] enemy_id;
	static final int AUBE = 3;
	static final int BLACK_HALL = 8;
	static final int EARTH = 0;
	
	
	public static void init()
	{
		s_enemys[0] = new HitodamaEnemyData();
		s_enemys[0].imageId = 6;
		s_enemys[0].speed = 3;
		s_enemys[0].minusPower = 15;
		s_enemys[0].maxImageFrame = 4;
		s_enemys[0].imageSpeed = 2;
		
		s_enemys[1] = new BrainUpEnemyData();
		s_enemys[1].imageId = 7;
		s_enemys[1].speed = 5;
		s_enemys[1].minusPower = 20;
		s_enemys[1].maxImageFrame = 4;
		s_enemys[1].imageSpeed = 2;
		
		s_enemys[2] = new DieHallOnlyEnemyData();
		s_enemys[2].imageId = 2;
		s_enemys[2].speed = 4;
		s_enemys[2].minusPower = 50;
		s_enemys[2].maxImageFrame = 4;
		s_enemys[2].imageSpeed = 0;
		
		
		magics[0] = new Magic();
		magics[0].imageId = 0;
		magics[0].maxImageFrame = 7;
		magics[0].imageSpeed = 3;
	}

	public static void setup(int stage, int[][] map, EnemyData[] enemys, CharacterData aube, CharacterData my, CharacterData hall)
	{
		for ( int i = 0; i < enemys.length; i++ )
		{
			enemys[i] = null;
		}

		int[][] m = Map.getMap(stage);
		for ( int x = 0; x < m[0].length; x++ )
		{
			for ( int y = 0; y < m.length; y++ )
			{
				boolean isEnemy = false;
				for ( int j = 0; j < s_enemys.length; j++ )
				{
					if ( (s_enemys[j] != null) && (s_enemys[j].imageId == m[y][x]) )
					{
						for ( int i = 0; i < enemys.length; i++ )
						{
							if ( enemys[i] == null )
							{
								enemys[i] = s_enemys[j].deepCopy();
								enemys[i].setPosition(x, y);
								map[y][x] = EARTH;
								break;
							}
						}
						isEnemy = true;
						break;
					}
				}

				if ( !isEnemy )
				{
					if ( m[y][x] == AUBE )
					{
						aube.setPosition(x, y);
						my.setPosition(x, y);
						map[y][x] = EARTH;
					}
					else if ( m[y][x] == BLACK_HALL )
					{
						hall.setPosition(x, y);
						map[y][x] = EARTH;
					}
					else
					{
						map[y][x] = m[y][x];
					}
				}
			}
		}
	}
	
	public static Magic getMagic(int index)
	{
		return new Magic(magics[index]);
	}
}
