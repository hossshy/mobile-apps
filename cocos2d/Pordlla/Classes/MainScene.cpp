#include <memory>
#include "MainScene.h"
#include "StrUtil.h"
#include "BlockSprite.h"
#include "StageReader.h"

int Main::stageNo = 99;
const int Main::ERASE_COUNT = 3;

cocos2d::Scene* Main::createScene()
{
    auto scene = cocos2d::Scene::create();
    auto layer = Main::create();
    scene->addChild(layer);
    return scene;
}

/* maybe it is unnecessary...?
Main::~Main()
{
	L("Main::destructor");

	for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
	{
		delete [] map[row];
		delete [] leftDummyMap[row];
		delete [] rightDummyMap[row];
	}
	delete [] map;
	delete [] leftDummyMap;
	delete [] rightDummyMap;
}
*/
bool Main::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }

	auto dispatcher = cocos2d::Director::getInstance()->getEventDispatcher();
    auto listener = cocos2d::EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(Main::onTouchBegan, this);
    listener->onTouchMoved = CC_CALLBACK_2(Main::onTouchMoved, this);
    listener->onTouchEnded = CC_CALLBACK_2(Main::onTouchEnded, this);
    listener->onTouchCancelled = CC_CALLBACK_2(Main::onTouchEnded, this);
    dispatcher->addEventListenerWithSceneGraphPriority(listener, this);
    
    auto bg = cocos2d::Sprite::create("bg.png");
	bg->setAnchorPoint(cocos2d::Point(0,1)); // left top
    bg->setPosition(0,Global::getInstance()->WINDOW_HEIGHT);
    addChild(bg);
    
    blockBatch = cocos2d::SpriteBatchNode::create("blocks.png");
	blockBatch->getTexture()->setAliasTexParameters();
	//blockBatch->autorelease();
	addChild(blockBatch);
    
    auto fbg = cocos2d::Sprite::create("front_bg.png");
	fbg->setAnchorPoint(cocos2d::Point(0,1)); // left top
    fbg->setPosition(0,Global::getInstance()->WINDOW_HEIGHT);
    addChild(fbg);

	stageNo = 99; // TODO
	std::unique_ptr<StageReader> sr(new StageReader(stageNo));
	
	resetMap(sr->getMapInfo());
    for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
	{
		rotateMap(sr->getRotateInfo()[row], (Global::FIELD_ROW_NUM - 1) - row);
	}

	scheduleUpdate();

	dropping = false;
	playState = State::PLAY;
    touchedBlock = nullptr;
	
    return true;
}

bool Main::isRange(int row, int col)
{
	return (0 <= row) && (row < Global::FIELD_ROW_NUM) &&
		(0 <= col) && (col < Global::FIELD_COL_NUM);
}

void Main::removeBlocks(int type, int row, int col)
{
	// range check
	if ( !isRange(row, col) )
	{
		return;
	}

	if ( !map[row][col]->removed && map[row][col]->type == type )
	{
		map[row][col]->removeBlock();
		if ( (row - 1) >= 0 )
		{
			removeBlocks(map[row][col]->type, row - 1, col);
		}
		if ( (row + 1) < Global::FIELD_ROW_NUM )
		{
			removeBlocks(map[row][col]->type, row + 1, col);
		}
		if ( (col - 1) >= 0 )
		{
			removeBlocks(map[row][col]->type, row, col - 1);
		}
		if ( (col + 1) < Global::FIELD_COL_NUM )
		{
			removeBlocks(map[row][col]->type, row, col + 1);
		}
	}
}



bool Main::isRemovedAll()
{
	for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
	{
		for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
		{
			if ( map[row][col]->type > 0 )
			{
				return false;
			}
		}
	}
	return true;
}

int Main::checkBlocks(int type, int row, int col)
{
	// range check
	if ( !isRange(row, col) )
	{
		return 0;
	}
		
	if ( !map[row][col]->checked && map[row][col]->type == type )
	{
		map[row][col]->checked = true;
		int ret = 1;
		if ( (row - 1) >= 0 )
		{
			ret += checkBlocks(map[row][col]->type, row - 1, col);
		}
		if ( (row + 1) < Global::FIELD_ROW_NUM )
		{
			ret += checkBlocks(map[row][col]->type, row + 1, col);
		}
		if ( (col - 1) >= 0 )
		{
			ret += checkBlocks(map[row][col]->type, row, col - 1);
		}
		if ( (col + 1) < Global::FIELD_COL_NUM )
		{
			ret += checkBlocks(map[row][col]->type, row, col + 1);
		}
		return ret;
	}
	return 0;
}

/*
 * fall down events.
 */
void Main::update(float delta)
{
	if ( playState == State::DROP_START )
	{
		if ( drop() )
		{
			dropping = true;
			playState = State::DROP_MOVE;
		}
		else
		{
			if ( dropping )
			{
				playState = State::REMOVE_BLOCKS;
			}
			else
			{
				// end to drop
				playState = State::STAGE_FAILED;
                L("fail");
			}
		}
    }
    else if ( playState == State::DROP_MOVE )
    {
		bool dropped = false;
		for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
		{
			for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
			{
				dropped |= map[row][col]->execAnimation();
			}
		}
		if ( !dropped )
		{
			playState = State::DROP_START;
		}
	}
	else if ( playState == State::REMOVE_BLOCKS )
	{
		bool removing = false;
		for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
		{
			for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
			{
				if ( (map[row][col]->type > 0) && !map[row][col]->checked )
				{
					int count = checkBlocks(map[row][col]->type, row, col);
					if ( count >= ERASE_COUNT )
					{
						removing = true;
						removeBlocks(map[row][col]->type, row, col);
					}
				}
			}
		}

		if ( removing )
		{
			playState = State::REMOVE_BLOCKS_ANIME;
		}
		else
		{
			if ( isRemovedAll() )
			{
				playState = State::STAGE_CLEAR;
                L("clear");
				// TODO check all clear
				// TODO check ending
				// TODO reset?
			}
            else
            {
                for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
                {
                    for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
                    {
                        map[row][col]->resetStatus();
                    }
                }
				dropStart();
            }
		}
	}
	else if ( playState == State::REMOVE_BLOCKS_ANIME )
	{
		bool removed = false;
		for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
		{
			for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
			{
				removed |= map[row][col]->removeAnimation();
			}
		}
		if ( !removed )
		{
			playState = State::REMOVE_BLOCKS;
		}
	}
}

void Main::dropStart()
{
	dropping = false;
	playState = State::DROP_MOVE;
}

bool Main::drop()
{
	bool ret = false;
	for ( int row = (Global::FIELD_ROW_NUM - 1); row > 0; row-- )
	{
		for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
		{
			if ( (map[row][col]->type == 0) && (map[row - 1][col]->type > 0) )
			{
				ret = true;
				map[row][col]->down(map[row-1][col]->type, map[row-1][col]->getVy());
				map[row-1][col]->reset(0); // empty
			}
		}
	}
    return ret;
}

BlockSprite* Main::getTouchedBlock(cocos2d::Point pos)
{
    cocos2d::log("getTouchedBlock %f:%f", pos.x, pos.y);
	for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
	{
		for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
		{
			if ( map[row][col]->isTouchPoint(pos) )
			{
				return map[row][col];
			}
		}
	}
	return nullptr;
}

BlockSprite* Main::getTouchedCol(const cocos2d::Point &pos, int row)
{
    for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
    {
        if ( map[row][col]->isTouchOrigX(pos.x) )
        {
            return map[row][col];
        }
    }
	return nullptr;
}

/*
 * move left or right (rotate)
 */
bool Main::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);
    
    if ( (touchedBlock == nullptr) && (playState == State::PLAY) )
    {
        touchedBlock = getTouchedBlock(pos);
        touchingColBlock = touchedBlock;
        if ( touchedBlock != nullptr )
        {
            lastTouchPoint = pos;
        }
        else
        {
            cocos2d::log("no playarea");
            playState = State::DROP_PREPARE;
            scheduleOnce(schedule_selector(Main::changeDrop), 0.5f); //TODO
        }
    }
	else if ( playState == State::STAGE_FAILED )
	{
		auto scene = Main::createScene();
		cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
		cocos2d::Director::getInstance()->replaceScene(transition);
	}
	else if ( playState == State::STAGE_CLEAR )
	{
		stageNo++;
		if ( stageNo >= 100 )
		{
			stageNo = 0;
		}
		auto scene = Main::createScene();
		cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
		cocos2d::Director::getInstance()->replaceScene(transition);
	}

	return true;
}

void Main::changeDrop(float dt)
{
	playState = State::DROP_START;
}

void Main::addXPointToRow(int row, float diff)
{
	for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
	{
		map[row][col]->addX(diff);
		leftDummyMap[row][col]->addX(diff);
		rightDummyMap[row][col]->addX(diff);
	}
}
 
void Main::onTouchMoved(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);
    if ( touchedBlock != nullptr )
    {
        auto tmp = getTouchedCol(pos, touchedBlock->getRow());
        if ( tmp != nullptr )
        {
            touchingColBlock = tmp;
            int target = touchedBlock->getRow();
            float diff = pos.x - lastTouchPoint.x;
            // mention about left side and right side of copied map.
            addXPointToRow(target, diff);
            lastTouchPoint = pos;
        }
    }
}

void Main::rotateMap(int index, int row)
{
	if ( index < 0 )
	{
		index = Global::FIELD_COL_NUM + index;
	}

	int colTypes[Global::FIELD_COL_NUM];
	for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
	{
		colTypes[index] = map[row][col]->type;
		index++;
		if ( index >= Global::FIELD_COL_NUM )
		{
			index = 0;
		}
	}
				
	for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
	{
		map[row][col]->reset(colTypes[col]);
		leftDummyMap[row][col]->reset(colTypes[col]);
		rightDummyMap[row][col]->reset(colTypes[col]);
	}
}

void Main::onTouchEnded(cocos2d::Touch* touch, cocos2d::Event *event)
{
    if ( (touchedBlock != nullptr) && (touchingColBlock != nullptr) )
    {
		int shiftCounter = touchingColBlock->getCol() - touchedBlock->getCol();
        cocos2d::log("sc %d", shiftCounter);
		if ( shiftCounter == 0 )
		{
			for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
			{
				map[touchedBlock->getRow()][col]->adjustPosition();
				leftDummyMap[touchedBlock->getRow()][col]->adjustPosition();
				rightDummyMap[touchedBlock->getRow()][col]->adjustPosition();
			}
		}
		else
		{
			rotateMap(shiftCounter, touchedBlock->getRow());
		}
        cocos2d::log("Main::onTouchEnded %d %d", touchingColBlock->getCol(), touchedBlock->getCol());
        touchedBlock = nullptr;
    }
}

void Main::resetMap(std::array<std::array<int, Global::FIELD_COL_NUM>, Global::FIELD_ROW_NUM> &data)
{
    blockBatch->removeAllChildren();
	for ( int row = 0; row < Global::FIELD_ROW_NUM; row++ )
	{
		for ( int col = 0; col < Global::FIELD_COL_NUM; col++ )
		{
			map[row][col] = BlockSprite::createBlock(blockBatch, data[row][col], col, row, 0.0f);
			leftDummyMap[row][col] = BlockSprite::createBlock(blockBatch, data[row][col], col, row, Global::getInstance()->playArea.size.width * -1);
			rightDummyMap[row][col] = BlockSprite::createBlock(blockBatch, data[row][col], col, row, Global::getInstance()->playArea.size.width);
		}
	}
}
