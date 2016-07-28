/*
 * Last modified: 2009/02/24 11:45:57
 * author: Kazamai, Kou
 */
package com.strnet.game.main;
import com.strnet.game.component.NumberImage;

public class EndingData
{
	NumberImage stageNoImage = null;
	int endingCounter = 0;
	int fontHeight=0;
	int playCount;
	int allClearCount;
	int x = 30;
	int y = 50;
	boolean exitFlag = false;
	String[] msg1 = new String[]{"企画/プログラム", "グラフィック", "サウンド", "Presented by"};
	String[] msg2 = new String[]{"風舞 光", "ゆのき", "風舞 光", ""};
	//String[] song = new String[]{"ひとりで　じっくり　ポルドラ", "みんなで　わいわい　ポルドラ", "とけると　かいかん　ポルドラ", "ぜんくり　おめでと　ポルドラ"};

	public EndingData(int fontHeight, int allClearCount, int playCount)
	{
		this.fontHeight = fontHeight;
		this.allClearCount = allClearCount;
		this.playCount = playCount;
		endingCounter = 0;
		stageNoImage = new NumberImage(35, 10, 11);
		stageNoImage.setSource(0, 150);
		stageNoImage.setMaxLength(4);
	}

	public String toString()
	{
		return endingCounter +":"+fontHeight+":"+x+":"+y;
	}

	public boolean next()
	{
		if ( endingCounter < 630 )
		{
			endingCounter++;
			return true;
		}
		return false;
	}
	
	public void paint(MainCanvas g)
	{
		g.drawCommonBackground();
		int start = 50;
		int keisuu = 70;
		int viewX = 36;
		int viewMoziX = 86;
		int viewMoziY = 122;
		if ( endingCounter < start )
		{
		}
		else if ( endingCounter < start + keisuu )
		{
			int x = (endingCounter - start) / 8;
			if ( x > 6 )
			{
				x = 6;
			}
			g.drawImage(35, 0,0, viewX,48, 24 + (24*x), 24);
		}
		else if ( endingCounter < start + (keisuu*2) )
		{
			g.drawImage(35, 0,0, viewX,48, 168, 24);
		}
		else if ( endingCounter < start + (keisuu*3) )
		{
			int x = (endingCounter - (start+(keisuu *2))) / 8;
			if ( x <= 6 )
			{
				g.drawImage(35, 0,0, viewX,48, 24 + (24*(6 - x)), 24);
				g.drawImage(35, 0+(24*(6 - x)),24, viewX+(24*(6 - x)),48, 24, 24);
			}
			else
			{
				x = 6;
			}

			for ( int i = 0; i <= x; i++ )
			{
				g.drawImage(35, (6-i)*24,0, viewX+(i*24),96, 24,24);
			}
		}
		else if ( endingCounter < start + (keisuu*4) )
		{
			int x = (endingCounter - (start + (keisuu*3)));
			for ( int i = 0; i < 7; i++ )
			{
				g.drawImage(35, (6-i)*24,0, viewX+(i*24),96 - x, 24,24);
			}

			g.drawImage(2, 0,146, 0,x-keisuu, 240,94);
		}
		else if ( endingCounter < start + (keisuu*5) )
		{
			paintPordlla(g, x, viewX);

			g.drawImage(35, 0,49, viewMoziX, viewMoziY, 77,14);
			g.drawImage(35, 0,49+(4*14), viewMoziX, viewMoziY+20, 77,14);
		}
		else if ( endingCounter < start + (keisuu*6) )
		{
			paintPordlla(g, x, viewX);

			g.drawImage(35, 0,49+(1*14), viewMoziX, viewMoziY, 77,14);
			g.drawImage(35, 0,49+(4*14), viewMoziX, viewMoziY+20, 77,14);
		}
		else if ( endingCounter < start + (keisuu*7) )
		{
			paintPordlla(g, x, viewX);

			g.drawImage(35, 0,49+(2*14), viewMoziX, viewMoziY, 77,14);
			g.drawImage(35, 0,49+(5*14), viewMoziX, viewMoziY+20, 77,14);
		}
		else if ( endingCounter < start + (keisuu*8) )
		{
			paintPordlla(g, x, viewX);

			g.drawImage(35, 0,49+(3*14), viewMoziX, viewMoziY, 77,14);
			g.drawImage(35, 0,49+(4*14), viewMoziX, viewMoziY+20, 77,14);
		}
		else
		{
			paintPordlla(g, x, viewX);

			g.drawImage(35, 0,133, viewMoziX, viewMoziY, 77,14);
			g.drawImage(35, 81,48, viewMoziX - 5, viewMoziY+20, 87,29);
			
			g.drawImage(35, 84,88, 60,175, 86,18);
			stageNoImage.draw(g, allClearCount, 150, 179);

			g.drawImage(35, 84,106, 60,195, 86,18);
			stageNoImage.draw(g, playCount, 150, 199);
		}
	}
	
	public void paintPordlla(MainCanvas g, int x, int viewX)
	{
		g.drawImage(2, 0, 146, 0,0  ,240,94);
		/*
		for ( int i = 0; i < 7; i++ )
		{
			g.drawImage(35, (6-i)*24,0, viewX+(i*24),96 - x, 24,24);
		}
		*/
	}
}