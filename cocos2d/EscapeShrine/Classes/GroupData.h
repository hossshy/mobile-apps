#ifndef GROUPDATA_H
#define GROUPDATA_H

#include <vector>
#include <string>
#include "ObjectSprite.h"

class GroupData
{
private:
	std::vector<ObjectSprite*> children;
public:
	~GroupData();
	void add(ObjectSprite* child);
	bool check(std::string answer);
};

#endif
