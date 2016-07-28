#ifndef __MAIN_SCENE_H__
#define __MAIN_SCENE_H__

#include <array>
#include <string>

#include "cocos2d.h"
#include "Global.h"
#include "BlockSprite.h"

enum class State { PLAY, DROP_PREPARE, DROP_START, DROP_MOVE, REMOVE_BLOCKS, REMOVE_BLOCKS_ANIME, STAGE_CLEAR, STAGE_FAILED };

class Main : public cocos2d::Layer
{
public:
	static const int ERASE_COUNT;
	static int stageNo;
    static cocos2d::Scene* createScene();

    CREATE_FUNC(Main);

	//virtual ~Main();
    virtual bool init();
	void changeDrop(float dt);
	bool isRange(int row, int col);
	bool isRemovedAll();
	int checkBlocks(int type, int row, int col);
	void removeBlocks(int type, int row, int col);
	void resetMap(std::array<std::array<int, Global::FIELD_COL_NUM>, Global::FIELD_ROW_NUM> &map);
	void shuffleMap(std::string code);
	void dropStart();
	bool drop();
	void addXPointToRow(int row, float diff);
	BlockSprite* getTouchedBlock(cocos2d::Point pos);
    BlockSprite* getTouchedCol(const cocos2d::Point &pos, int row);
	void rotateMap(int index, int row);
	bool onTouchBegan(cocos2d::Touch *touch, cocos2d::Event *event);
	void onTouchMoved(cocos2d::Touch *touch, cocos2d::Event *event);
	void onTouchEnded(cocos2d::Touch *touch, cocos2d::Event *event);
	void update(float delta);
private:
	BlockSprite *map[Global::FIELD_ROW_NUM][Global::FIELD_COL_NUM];
	BlockSprite *leftDummyMap[Global::FIELD_ROW_NUM][Global::FIELD_COL_NUM];
	BlockSprite *rightDummyMap[Global::FIELD_ROW_NUM][Global::FIELD_COL_NUM];
	cocos2d::SpriteBatchNode *blockBatch;
    BlockSprite *touchedBlock;
    BlockSprite *touchingColBlock;

	bool dropping;
	State playState;
    cocos2d::Point lastTouchPoint;
};

#endif // __MAIN_SCENE_H__
