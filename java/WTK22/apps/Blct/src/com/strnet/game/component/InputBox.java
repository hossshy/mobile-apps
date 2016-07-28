/*
 * Last modified: 2008/07/05 21:57:03
 * @author Kazamai, Kou
 */
package com.strnet.game.component;

public class InputBox {
	
	public static final int MODE_HIRAGANA = 1;
	public static final int MODE_KATAKANA = 2;
	public static final int MODE_ALPHABET = 4;
	public static final int MODE_NUMBER = 8;
	
	private int mode;
	private StringBuffer commitValue;
	
	public InputBox()
	{
		this(MODE_HIRAGANA);
	}
	
	public InputBox(int mode)
	{
		this.mode = mode;
		commitValue = new StringBuffer();
	}
	
	public void toggle()
	{
		
	}
	
	public void input(int keyCode)
	{
	}
	
	public String getValue()
	{
	    return commitValue.toString();
	}
	
	private void commitChar()
	{
	}
}
