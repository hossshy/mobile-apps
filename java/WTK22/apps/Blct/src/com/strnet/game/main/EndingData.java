/*
 * Last modified: 2010/03/23 22:52:52
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Color;
import com.strnet.game.io.StartEndReader;
import com.strnet.game.common.list.StringLightList;
import com.strnet.game.component.viewer.PreserveViewController;

public class EndingData
{
	private static StringLightList[][] contents;
	private PreserveViewController viewController;
	public static void initialize(StartEndReader reader)
	{
		StringLightList staff = reader.get(":STAFF");
		contents = new StringLightList[3][2];
		contents[0][0] = reader.get(":RACHICA_ED_0");
		contents[0][1] = reader.get(":RACHICA_ED_1");
		contents[0][1].addAll(staff);
		contents[1][0] = reader.get(":RAUL_ED_0");
		contents[1][1] = reader.get(":RAUL_ED_1");
		contents[1][1].addAll(staff);
		contents[2][0] = reader.get(":NOMAC_ED_0");
		contents[2][1] = reader.get(":NOMAC_ED_1");
		contents[2][1].addAll(staff);
	}
	
	int endingCounter = 0;
	boolean exitFlag = true;
	
	public EndingData(int endingPattern, int selectedFighter)
	{
		exitFlag = true;
		viewController = new PreserveViewController();
		viewController.setAll(contents[selectedFighter][endingPattern], 30, 240, 32, Color.BLACK);
	}

	public boolean next()
	{
		return exitFlag;
	}
	
	public void paint(GameCommonCanvas g)
	{
		exitFlag = viewController.paintAll(g);
	}
}