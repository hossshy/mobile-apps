#ifndef GLOBAL_H
#define GLOBAL_H

#include <map>
#include <string>

class Global
{
public:
    constexpr static const float WINDOW_WIDTH = 430.0f;
    constexpr static const float WINDOW_HEIGHT = 320.0f;
	constexpr static const char *SAVE_ID = "save";
    static Global* getInstance();
    static void init(const std::string& dic);
    static std::string dic(const std::string& key);
    std::string storyId;
    float designWidth;
    float designHeight;
    float paddingX;
    float paddingY;
	bool loadFlag;
private:
    Global(const std::string& dic);
    std::map<std::string, std::string> *dictionary;
    static Global *_instance;
};
#endif
