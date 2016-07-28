//
//  CommonLabel.h
//

#ifndef CommonLabel_h
#define CommonLabel_h

#include <string>
#include "cocos2d.h"

class CommonLabel : public cocos2d::LabelTTF
{
public:
    void setPosition(float x, float y);
	bool isTouchPoint(const cocos2d::Point &target);
    static CommonLabel* create(const std::string &msg, float x, float y);
};

#endif
