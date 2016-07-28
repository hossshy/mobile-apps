/*
 * Last modified: 2010/03/28 22:43:00
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.GameUtil;
import com.strnet.game.common.SaveDataCreator;
import java.util.Vector;

public class FighterInformationManager
{
	public static final int ADD_BOSS_STAGE = 10;
	private static Vector records = new Vector();

	public static void add(FighterInformation f)
	{
		records.addElement(f);
	}
	
	public static void addInformation(int fighterId, int mode)
	{
		if ( get(fighterId, mode) == null )
		{
			FighterInformation fi = new FighterInformation(fighterId, mode);
			add(fi);
		}
	}
	
	public static FighterInformation get(int fighterId, int mode)
	{
		for ( int i = 0; i < records.size(); i++ )
		{
			FighterInformation tmp = (FighterInformation) records.elementAt(i);
			if ( (tmp.getFighterId() == fighterId) &&
				 (tmp.getMode() == mode) )
			{
				return tmp;
			}
		}
		return null;
	}
	
	public static FighterInformation getHighScore()
	{
		FighterInformation ret = null;
		for ( int i = 0; i < records.size(); i++ )
		{
			FighterInformation tmp = (FighterInformation) records.elementAt(i);
			//EASY if ( (tmp.getMode() != MainCanvas.MODE_EASY) && ((ret == null) || (tmp.getHighScore() > ret.getHighScore())) )
			if ( (tmp.getHighScore() > 0) &&
				 ((ret == null) || (tmp.getHighScore() > ret.getHighScore())) )
			{
				ret = tmp;
			}
		}
		return ret;
	}
	
	public static int size()
	{
		return records.size();
	}

	public static void load(String data)
	{
		records = new Vector();
		String[] line = GameUtil.split(data, ':');
		for ( int i = 0; i < line.length; i++ )
		{
			String[] tmp = GameUtil.split(line[i], ';');
			int seek = 0;
			int fighterId = Integer.parseInt(tmp[seek++]);
			int mode = Integer.parseInt(tmp[seek++]);
			FighterInformation info = new FighterInformation(fighterId, mode);
			info.setHighScore(Integer.parseInt(tmp[seek++]));
			info.setAvailableStage(Integer.parseInt(tmp[seek++]));
			info.setCleared("1".equals(tmp[seek++]));
			info.setNoTrance("1".equals(tmp[seek++]));
			info.setTrueBoss("1".equals(tmp[seek++]));
			info.setRank(Integer.parseInt(tmp[seek++]));
			records.addElement(info);
		}
	}
	
	public static String toSaveString()
	{
		SaveDataCreator sdc = new SaveDataCreator(':');
		for ( int i = 0; i < records.size(); i++ )
		{
			SaveDataCreator sdc2 = new SaveDataCreator(';');
			FighterInformation info = (FighterInformation) records.elementAt(i);
			sdc2.add(info.getFighterId());
			sdc2.add(info.getMode());
			sdc2.add(info.getHighScore());
			sdc2.add(info.getAvailableStage());
			sdc2.add(info.isCleared());
			sdc2.add(info.isNoTrance());
			sdc2.add(info.hasTrueBoss());
			sdc2.add(info.getRank());
			sdc.add(sdc2);
		}
		return sdc.toString();
	}
}
