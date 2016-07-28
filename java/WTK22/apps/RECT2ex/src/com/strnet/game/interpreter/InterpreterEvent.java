/*
 * Last modified: 2009/06/12 16:17:52
 * author: Kazamai, Kou
 */
package com.strnet.game.interpreter;

public interface InterpreterEvent
{
	public void executeCommand(CommandNode node);
	public boolean executeIfCommand(CommandNode node);
}