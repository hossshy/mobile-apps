#include "ObjectSprite.h"
#include "MessageWindowLayer.h"

bool MessageWindowLayer::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }
    
	background = ObjectSprite::createObjectSimple("bg_msg_window", 10, 220);
    addChild(background, 1);
    
    label = CommonLabel::create("");
    label->setPosition(10 + 4, 100 - 4);
	addChild(label, 1);

    return true;
}

void MessageWindowLayer::setText(const std::string& text)
{
	label->setString(text);
}
