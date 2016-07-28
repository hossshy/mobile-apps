/*
 * Last modified: 2009/09/29 22:19:52
 * @author Kazamai, Kou
 */
package com.strnet.game.main;
import com.strnet.game.common.GameUtil;

public class FixRandom
{
	private static final int[] RANDOM = new int[]{8,3,5,7,2,0,6,5,1,2,8,4,3,2,9,7,5,6,3,4,9,8,0,7,2,4,9,3,2,8,5,1};
	public static int seed;
	
	public static int setSeed()
	{
		seed = GameUtil.rand(RANDOM.length);
		return seed;
	}
	
	public static int next()
	{
		seed = GameUtil.loopIncf(seed, 0, RANDOM.length);
		return RANDOM[seed];
	}
}