/*
 * Last modified: 2008/09/03 01:37:31
 * author: Hoshi, Yuichiro
 */
package com.strnet.game.main;

public class IfNode implements Node
{
	CommandListNode ifCommand = null;
	CommandListNode execCommand;
	IfNode elseNode = null;
}