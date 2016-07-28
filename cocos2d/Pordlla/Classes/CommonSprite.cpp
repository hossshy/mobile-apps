#include "CommonSprite.h"
#include "Global.h"


CommonSprite* CommonSprite::create(const std::string &id, float x, float y)
{
	return create(id, cocos2d::Rect(0,0,-1,-1), x, y);
}

CommonSprite* CommonSprite::create(const std::string &id, cocos2d::Rect tex, float x, float y)
{
	y = Global::getInstance()->WINDOW_HEIGHT - y;
	std::string file = cocos2d::StringUtils::format("%s.png", id.c_str());
	CommonSprite *sprite = new CommonSprite();

    if ( (tex.size.width < 0) || (tex.size.height < 0) )
    {
        sprite->initWithFile(file);
    }
    else
    {
        sprite->initWithFile(file, tex);
    }

	sprite->autorelease();
	sprite->setPosition(x, y);
	sprite->setVisible(true);
	sprite->setAnchorPoint(cocos2d::Point(0,1)); // left top
	//sprite->getTexture()->setAliasTexParameters();
	return sprite;
}

void CommonSprite::setPosition(float x, float y)
{
    cocos2d::Sprite::setPosition(x + Global::getInstance()->paddingX,
                                 y + Global::getInstance()->paddingY);
}

bool CommonSprite::isTouchPoint(const cocos2d::Point &target)
{
	return this->getBoundingBox().containsPoint(target);
}
