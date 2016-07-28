#include "StrUtil.h"
#include "GroupData.h"

GroupData::~GroupData()
{
	L("GroupData::destructor");
    // child will be removed by escapeScene.
}

void GroupData::add(ObjectSprite* child)
{
	children.push_back(child);
}

bool GroupData::check(std::string answer)
{
	assert(answer.size() == children.size());
	for ( size_t i = 0; i < answer.size(); i++ )
	{
		char c = answer.at(i);
		int val = c - '0';
		if ( val != children[i]->frame ) {
			return false;
		}
	}
	return true;
}
