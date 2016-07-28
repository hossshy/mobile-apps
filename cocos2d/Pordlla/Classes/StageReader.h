#ifndef STAGEREADER_H
#define STAGEREADER_H

#include <array>
#include "Global.h"

class StageReader
{
public:
	StageReader(int stage);
	std::array<std::array<int, Global::FIELD_COL_NUM>, Global::FIELD_ROW_NUM>& getMapInfo() { return mapInfo; }
	std::array<int, Global::FIELD_ROW_NUM>& getRotateInfo() { return rotateInfo; }
private:
	std::array<int, Global::FIELD_ROW_NUM> rotateInfo;
	std::array<std::array<int, Global::FIELD_COL_NUM>, Global::FIELD_ROW_NUM> mapInfo;
};

#endif
