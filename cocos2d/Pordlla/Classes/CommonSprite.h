#ifndef COMMONSPRITE_H
#define COMMONSPRITE_H

#include <string>
#include "cocos2d.h"

class CommonSprite : public cocos2d::Sprite
{
public:
	bool isTouchPoint(const cocos2d::Point &target);
    void setPosition(float x, float y);
    static CommonSprite* create(const std::string &id, float x, float y);
    static CommonSprite* create(const std::string &id, cocos2d::Rect tex, float x, float y);
};

#endif
