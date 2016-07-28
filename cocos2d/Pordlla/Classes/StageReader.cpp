#include "StageReader.h"

#include <memory>
#include "cocos2d.h"
#include "StrUtil.h"

StageReader::StageReader(int stage)
{
	//auto file = cocos2d::StringUtils::format("./dat/%03d_dat.txt", stage);
	auto file = cocos2d::StringUtils::format("%03d_dat.txt", stage);
	L(file.c_str());
	auto str = cocos2d::FileUtils::getInstance()->getStringFromFile(file);
    
	std::unique_ptr<StringTokenizer> st(new StringTokenizer(str, "\n"));
	//auto st = new StringTokenizer(str, "\n");

	auto rotates = st->next();
	assert(rotates.size() == Global::FIELD_ROW_NUM);

	for ( int i = 0; i < rotates.size(); i++ )
	{
		this->rotateInfo[i] = rotates[i] - '0';
	}

	for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
	{
		std::unique_ptr<StringTokenizer> st2(new StringTokenizer(st->next(), ", "));
		for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
		{
			this->mapInfo[row][col] = st2->next()[0] - '0';
		}
	}
}
