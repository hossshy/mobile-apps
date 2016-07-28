/*
 * Last modified: 2009/06/13 01:47:05
 * author: Kazamai, Kou
 */
package com.strnet.game.main;

import com.strnet.game.common.Rectangle;

import java.util.Vector;

public class GroupData
{
	private Vector child = new Vector();

	public void add(CharacterData cd)
	{
		child.addElement(cd);
	}
	
	public void addAlltoVector(Vector v)
	{
		for (int i = 0; i < child.size(); i++) {
			v.addElement(child.elementAt(i));
		}
	}
	
	public void toggle()
	{
		boolean show = false;
		for (int i = 0; i < child.size(); i++ ) {
			CharacterData cd = (CharacterData) child.elementAt(i);
			if ( show ) {
				show = false;
				cd.state = CharacterData.STATE_SHOW;
				break;
			}
			else if ( cd.state == CharacterData.STATE_SHOW ) {
				show = true;
				cd.state = CharacterData.STATE_HIDE;
			}
		}

		if ( show ) {
			CharacterData cd = (CharacterData) child.elementAt(0);
			cd.state = CharacterData.STATE_SHOW;
		}
	}
}