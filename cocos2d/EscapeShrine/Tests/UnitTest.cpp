#include <iostream>
#include <array>
#include <map>
#include <string>
#include <vector>
#include "../Classes/Interpreter.h"
#include "../Classes/StrUtil.h"

std::map<std::string, std::array<std::string, 4>*>* createRoom()
{
	auto room = new std::map<std::string, std::array<std::string, 4>*>();
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_door", new std::array<std::string, 4>{{"a","b","c","d"}}));
	return room;
}
/*
	std::map<std::string, std::array<std::string, 4>> createRoom()
{
	std::map<std::string, std::array<std::string, 4>> room;
		room.insert(std::pair<std::string, std::array<std::string, 4>>("room_door", {{"a","b","c","d"}}));
	return room;
}
*/

class UnitTest : public InterpreterEvent
{
public:
	void executeCommand(CommandNode *node);
	bool executeIfCommand(CommandNode *node);
};



void UnitTest::executeCommand(CommandNode *node)
{
	L(node->command);
}

bool UnitTest::executeIfCommand(CommandNode *node)
{
	L(node->command);
	return false;
}

int main()
{
	Interpreter itpr(new UnitTest());
	CommandListNode *nlist = Interpreter::parse("if isset show hoge set show msghoge else set msg piyo end");
	L("test start");
	itpr.execEvent(nlist);
	for ( auto a : *nlist )
	{
		delete a;
	}
	nlist->clear();
	delete nlist;
	nlist = nullptr;
	if ( nlist != NULL ) delete nlist;

	L("start hogepiyo");
	CommandNode *nod = new CommandNode("hoge", "piyo");
	delete nod;

	auto room = createRoom();
	//auto itr = room.find("room_door");
	//auto pair = room->begin();
	//L(pair->second->at(0));
	auto itr = room->find("room_door");
	L(itr->second->at(2));

	L(StrUtil::trim("  hoge    "));

	auto data = "a = b\ntesttest\n\nabc=def ef\nhoge=piyo\n";
	auto amap = StrUtil::makeAssocMap(data);
	L(amap->find("a")->second);
	L(amap->find("abc")->second);
	L(amap->find("hoge")->second);

	std::string hoge;
	L(hoge);
	if ( hoge == "" )
	{
		L("empty");
	}
	hoge += "piyo";
	hoge += ":";
	hoge += "test";
	L(hoge);

	std::string st = "9423175742";
	L(st[2]);
	for ( char ch : st )
	{
		L(atoi(&ch));
	}

	return 0;
}
