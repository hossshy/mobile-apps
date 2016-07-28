//
//  CommonLabel.h
//  EscapeShrine
//
//  Created by Yuichiro Hoshi on 3/9/14.
//
//

#ifndef EscapeShrine_CommonLabel_h
#define EscapeShrine_CommonLabel_h

#include <string>
#include "cocos2d.h"

class CommonLabel : public cocos2d::LabelTTF
{
public:
    void setPosition(float x, float y);
	bool isTouchPoint(const cocos2d::Point &target);
    static CommonLabel* create(const std::string &msg);
};

#endif
