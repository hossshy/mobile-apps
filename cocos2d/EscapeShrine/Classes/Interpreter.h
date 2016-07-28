#ifndef INTERPRETER_H
#define INTERPRETER_H

#include <vector>
#include "StrUtil.h"

class InterpreterNode
{
public:
	virtual ~InterpreterNode();
	virtual bool isIfNode() = 0;
};

typedef std::vector<InterpreterNode*> CommandListNode;


class CommandNode : public InterpreterNode
{
public:
	virtual ~CommandNode();
	std::string command;
	std::string arg;
	bool isIfNode() { return false; }
	CommandNode(std::string command, std::string arg) : command(command), arg(arg) {}
};

class IfNode : public InterpreterNode
{
public:
	CommandListNode *ifCommand;
	CommandListNode *execCommand;
	IfNode *elseNode;
	bool isIfNode() { return true; }
	std::string getType() { return "IfNode"; }
	virtual ~IfNode();
};

class InterpreterEvent
{
public:
	virtual void executeCommand(CommandNode *node) = 0;
	virtual bool executeIfCommand(CommandNode *node) = 0;
};

class Interpreter
{
public:
	Interpreter(InterpreterEvent *evt) : event(evt) {};
 	static CommandListNode* parse(std::string code);
	void execEvent(CommandListNode *list);
	CommandListNode* execIf(IfNode *node);
	virtual ~Interpreter();
private:
	InterpreterEvent *event;
 	static IfNode* makeIf(StringTokenizer &st);
};

#endif
