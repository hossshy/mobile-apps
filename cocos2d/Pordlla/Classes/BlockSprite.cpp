#include "Global.h"
#include "BlockSprite.h"

cocos2d::Rect BlockSprite::makeTextureRect(int type)
{
    int tp = type - 1;
    if ( tp < 0 )
    {
        tp = 0;
    }
    return cocos2d::Rect((tp * Global::getInstance()->BLOCK_WIDTH), 0, Global::getInstance()->BLOCK_WIDTH, Global::getInstance()->BLOCK_WIDTH);
}

BlockSprite* BlockSprite::createBlock(cocos2d::SpriteBatchNode *batch, int type, int col, int row, float supplement)
{
	BlockSprite *sprite = new BlockSprite();
    sprite->type = type;
	sprite->checked = false;
	sprite->removed = false;
    sprite->supplement = supplement;
    sprite->initWithTexture(batch->getTexture());
    sprite->setTextureRect(makeTextureRect(type));
    sprite->setAnchorPoint(cocos2d::Point(0,1)); // left top
    sprite->row = row;
    sprite->col = col;
	sprite->bottom = Global::getInstance()->WINDOW_HEIGHT - (row * Global::getInstance()->BLOCK_WIDTH) - Global::getInstance()->playArea.origin.y;
	batch->addChild(sprite);
    sprite->autorelease();
    sprite->adjustPosition();
    sprite->setVisible(type > 0);
    sprite->vy = 0.01;
    sprite->origPosX = sprite->getPositionX();
	return sprite;
}

void BlockSprite::removeBlock()
{
	removed = true;
    setPositionX(getPositionX() + (Global::getInstance()->BLOCK_WIDTH / 2));
    setPositionY(getPositionY() - (Global::getInstance()->BLOCK_WIDTH / 2));
    setAnchorPoint(cocos2d::Point(0.5f,0.5f)); // center
}

bool BlockSprite::isTouchOrigX(float x)
{
	return (origPosX < x) && (x < (origPosX + Global::getInstance()->BLOCK_WIDTH));
}

bool BlockSprite::isTouchPoint(const cocos2d::Point &target)
{
	return this->getBoundingBox().containsPoint(target);
}

void BlockSprite::adjustPosition()
{
	setPosition(col * Global::getInstance()->BLOCK_WIDTH + Global::getInstance()->playArea.origin.x + supplement,
                bottom);
}

void BlockSprite::down(int type, float vy)
{
	mode = MoveMode::DOWN;
    setVisible(true);
    setTextureRect(makeTextureRect(type));
	this->type = type;
	this->vy = vy;
	
	setPosition(col * Global::getInstance()->BLOCK_WIDTH + Global::getInstance()->playArea.origin.x,
				bottom + Global::getInstance()->BLOCK_WIDTH); // move up for animation
}

void BlockSprite::reset(int type)
{
	setAnchorPoint(cocos2d::Point(0,1)); // left top
	mode = MoveMode::STOP;
    setTextureRect(makeTextureRect(type));
	this->type = type;
    setVisible(type > 0);
    adjustPosition();
	setScale(1.0f);
}

void BlockSprite::addX(float x)
{
    setPositionX(getPositionX() + x);
}

bool BlockSprite::removeAnimation()
{
	if ( !removed )
	{
		return false;
	}
	
	float scale = getScale();
	if ( mode == MoveMode::SHRINK )
	{
		scale -= 0.2f;
		if ( scale <= 0.0f )
		{
			reset(0);
			return false;
		}
	}
	else
	{
		scale += 0.2f;
		if ( scale >= 1.7f )
		{
			mode = MoveMode::SHRINK;
		}
	}

	setScale(scale);
	return true;
}

bool BlockSprite::execAnimation()
{
	if ( mode == MoveMode::STOP )
	{
		return false;
	}

	if ( vy < Global::getInstance()->BLOCK_WIDTH )
	{
		vy += vy;
	}

	auto pos = getPosition();
    //cocos2d::log("vy %f", vy);
    setPosition(pos.x, pos.y - vy);
	if ( (pos.y - vy) <= bottom )
	{
		setPosition(pos.x, bottom);
		mode = MoveMode::STOP;
		return false;
	}
	return true;
}

void BlockSprite::resetStatus()
{
	checked = false;
	removed = false;
    mode = MoveMode::STOP;
    vy=1.0f;
}
