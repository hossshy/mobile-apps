#include "Interpreter.h"
#include "StrUtil.h"

InterpreterNode::~InterpreterNode()
{
	L("InterpreterNode::destructor");
}

CommandNode::~CommandNode()
{
	L("CommandNode::destructor");
}

IfNode::~IfNode()
{
	L("IfNode::destructor");
	if ( ifCommand != nullptr )
	{
		for ( InterpreterNode* n : *ifCommand )
		{
			delete n;
		}
		delete ifCommand;
		ifCommand = nullptr;
	}

	if ( execCommand != nullptr )
	{
		for ( InterpreterNode* n : *execCommand )
		{
			delete n;
		}
		execCommand->clear();
		delete execCommand;
		execCommand = nullptr;
	}
	
	if ( elseNode != nullptr )
	{
		delete elseNode;
		elseNode = nullptr;
	}
}

Interpreter::~Interpreter()
{
	L("Interpreter::destructor");
	//event = nullptr;
}

CommandListNode* Interpreter::parse(std::string code)
{
	StringTokenizer st(code, " ");
	CommandListNode *ret = new CommandListNode();
	std::string tmp;
	while ( (tmp = st.next()) != "\0" ) {
		if ( tmp == "if" ) {
			IfNode* ifN = Interpreter::makeIf(st);
			ret->push_back(ifN);
		} else if ( tmp == "set") {
			CommandNode *n = new CommandNode(st.next(), st.next());
			ret->push_back(n);
		}
	}
	return ret;
}

void Interpreter::execEvent(CommandListNode *list)
{
	for ( InterpreterNode* n : *list )
	{
		if ( n->isIfNode() )
		{
			IfNode *ifNode = static_cast<IfNode*>(n);
			CommandListNode *execListNode = execIf(ifNode);
			if ( execListNode != nullptr )
			{
				for (unsigned int j = 0; j < execListNode->size(); j++) {
					CommandNode* cn = static_cast<CommandNode*>(execListNode->at(j));
					event->executeCommand(cn);
				}
			}
		}
		else
		{
			CommandNode *node = static_cast<CommandNode*>(n);
			event->executeCommand(node);
		}
	}
}


CommandListNode* Interpreter::execIf(IfNode *node)
{
	if ( node == nullptr ) {
		return nullptr;
	}
	bool flag = true;
	CommandListNode *cln = node->ifCommand;
	for (unsigned int i = 0; i < cln->size(); i++)
	{
		CommandNode* comm = static_cast<CommandNode*>(cln->at(i));
		flag &= event->executeIfCommand(comm);
	}
	if ( flag )
	{
		return node->execCommand;
	}
	else
	{
		return execIf(node->elseNode);
	}
}

IfNode* Interpreter::makeIf(StringTokenizer &st) {
	CommandListNode *ifCommand = new CommandListNode();
	CommandListNode *execCommand = new CommandListNode();
	std::string tmp;
	while ( (tmp = st.next()) != "\0" ) {
		if ( tmp == "isset" ) {
			CommandNode *cn = new CommandNode(st.next(), st.next());
			ifCommand->push_back(cn);
		} else if ( tmp == "set" ) {
			CommandNode *cn = new CommandNode(st.next(), st.next());
			execCommand->push_back(cn);
		} else if ( tmp == "else" ) {
			IfNode *ifNode = new IfNode();
			ifNode->elseNode = makeIf(st);
			ifNode->ifCommand = ifCommand;
			ifNode->execCommand = execCommand;
			return ifNode;
		} else if ( tmp == "end" ) {
			IfNode *ifNode = new IfNode();
			ifNode->ifCommand = ifCommand;
			ifNode->execCommand = execCommand;
			return ifNode;
		}
	}
	L("error");
    return nullptr;
}
