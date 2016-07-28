//
//  CommonLabel.cpp
//

#include "CommonLabel.h"
#include "Global.h"

CommonLabel* CommonLabel::create(const std::string &msg, float x, float y)
{
    auto label = new CommonLabel();
    label->initWithString(msg, "Arial", 22);
	label->setAnchorPoint(cocos2d::Point(0,1)); // left top
    label->setVisible(true);
    label->autorelease();
	label->setPosition(x, y);
    return label;
}

void CommonLabel::setPosition(float x, float y)
{
    cocos2d::LabelTTF::setPosition(x + Global::getInstance()->paddingX,
                                   y + Global::getInstance()->paddingY);
}


bool CommonLabel::isTouchPoint(const cocos2d::Point &target)
{
	return this->getBoundingBox().containsPoint(target);
}
