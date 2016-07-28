#include "MenuLayer.h"

#include "Global.h"
#include "ObjectSprite.h"

bool MenuLayer::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }

	int x = 115;
	int y = 60;

	auto background = ObjectSprite::createObjectSimple("detail_img_base", x, y);
    addChild(background);

	backLabel = CommonLabel::create(Global::dic("back"));
    backLabel->setPosition(x + 10, y + 160);
	addChild(backLabel);

	saveBackLabel = CommonLabel::create(Global::dic("save_back"));
    saveBackLabel->setPosition(x + 10, y + 110);
	addChild(saveBackLabel);

	saveTitleLabel = CommonLabel::create(Global::dic("save_title"));
    saveTitleLabel->setPosition(x + 10, y + 60);
	addChild(saveTitleLabel);

	return true;
}
