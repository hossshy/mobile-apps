/*
 * Last modified: 2009/02/06 11:37:37
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class Skin
{
		//new Skin(3,4,5,"test", 0),
	public static Skin[] skins = new Skin[] {
		new Skin(6,7,8,"�p�C�v", 0),
		new Skin(16,17,18,"�X�g���x���[�n�[�g", 0),
		new Skin(12,13,14,"�A�J���C�g", 0),
		new Skin(9,10,11,"���j�o�[�X",0),
		new Skin(22,23,24,"�z�E�g�M",0),
		new Skin(25,26,27,"RECT",0),
	};
	
	public static Skin getSkin(int skinId)
	{
		if ( (skinId < 0) || (skinId > (skins.length - 1)) )
		{
			skinId = 0;
		}
		
		return skins[skinId];
	}

	private int background;
	private int block;
	private int border;
	private int point;
	private String name;
	
	public Skin(int background, int block, int border, String name, int point)
	{
		this.background = background;
		this.block = block;
		this.border = border;
		this.name = name;
		this.point = point;
	}

	public int getPoint()
	{
		return point;
	}

	public String getName()
	{
		return name;
	}
	
	public int getBackground()
	{
		return background;
	}
	
	public int getBlock()
	{
		return block;
	}
	
	public int getBorder()
	{
		return border;
	}
	
	public void load(MainCanvas g) throws Exception
	{
		for ( int i = 0; i < g.MAX_IMAGE; i++ )
		{
			if ( !isUse(i) && (g.IMAGE_MENU != i) )
			{
				g.releaseImage(i);
			}
		}
		
		g.loadImage(getBackground());
		g.loadImage(getBlock());
		g.loadImage(getBorder());
	}
	
	public boolean isUse(int id)
	{
		return (background == id) ||
			(block == id) ||
			(border == id);
	}
}