#include "Global.h"
#include "ObjectSprite.h"

static const int WINDOW_HEIGHT = 320;

ObjectSprite::~ObjectSprite()
{
	L("ObjectSprite::destructor");
    L(id.c_str());
    
    for ( InterpreterNode* n : *node )
    {
        delete n;
    }
    node->clear();
	delete node;
	node = nullptr;
}

ObjectSprite* ObjectSprite::createObject(std::string id, const std::string &imageId, int frame, int x, int y, int w, int h, const std::string &roomId, const std::string &code, bool viewable, int maxFrame)
{
	y = WINDOW_HEIGHT - y;
	std::string file = cocos2d::StringUtils::format("%s.png", imageId.c_str());
	
	ObjectSprite *sprite = new ObjectSprite();
	sprite->frame = frame;
    if ( (w < 0) || (h < 0) )
    {
        sprite->initWithFile(file);
    }
    else
    {
        cocos2d::Rect src((w * frame), 0, w, h);
        sprite->initWithFile(file, src);
    }
	sprite->autorelease();
	sprite->setPosition(x, y);
	sprite->id = id;
	sprite->roomId = roomId;
	sprite->viewable = viewable;
	sprite->maxFrame = maxFrame;
	sprite->setVisible(false);
	sprite->setAnchorPoint(cocos2d::Point(0,1)); // left top

	//sprite->getTexture()->setAliasTexParameters();

	sprite->node = Interpreter::parse(code);
	//cocos2d::log("hoge %s %s %d %d %d %d", sprite->id.c_str(), file.c_str(), x, y, w, h);
	return sprite;
}

void ObjectSprite::setPosition(float x, float y)
{
    cocos2d::Sprite::setPosition(x + Global::getInstance()->paddingX,
                                 y + Global::getInstance()->paddingY);
}

ObjectSprite* ObjectSprite::createObjectSimple(const std::string &id, int x, int y)
{
    auto ret = ObjectSprite::createObject(id, id, 0, x, y, -1, -1, "", "", true, 0);
    ret->setVisible(true);
    return ret;
}

void ObjectSprite::rotateFrame()
{
	frame++;
	if ( frame >= maxFrame )
	{
		frame = 0;
	}
    updateFrame();
}
void ObjectSprite::updateFrame()
{
	cocos2d::Rect now = getTextureRect();

	cocos2d::Rect src((now.size.width * frame), 0, now.size.width, now.size.height);
	setTextureRect(src);
}

bool ObjectSprite::isTouchPoint(const cocos2d::Point &target)
{
	return this->getBoundingBox().containsPoint(target);
	/*
	  cocos2d::Point point = this->getPosition();
	  int w = this->getContentSize().width;
	  int h = this->getContentSize().height;
	  int x = point.x - (w / 2);
	  int y = point.y - (h / 2);

	  return (x < target.x) && (target.x < (x + w)) &&
	  (y < target.y) && (target.y < (y + h));
	*/
}
