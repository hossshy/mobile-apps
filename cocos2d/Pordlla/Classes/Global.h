#ifndef GLOBAL_H
#define GLOBAL_H

#include <map>
#include <string>
#include "cocos2d.h"

class Global
{
public:
    float WINDOW_WIDTH;
    float WINDOW_HEIGHT;
	int BLOCK_WIDTH;
    cocos2d::Rect playArea;
	constexpr static const char *SAVE_ID = "save";

	static const int FIELD_COL_NUM = 7;
	static const int FIELD_ROW_NUM = 10;

    static Global* getInstance();
    static void init(const std::string& dic);
    static std::string dic(const std::string& key);

    float designWidth;
    float designHeight;
    float paddingX;
    float paddingY;
private:
    Global(const std::string& dic);
    std::map<std::string, std::string> *dictionary;
    static Global *_instance;
};
#endif
