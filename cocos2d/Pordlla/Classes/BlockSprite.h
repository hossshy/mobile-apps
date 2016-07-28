#ifndef BLOCKSPRITE_H
#define BLOCKSPRITE_H

#include <array>
#include <string>
#include "cocos2d.h"

enum class MoveMode { STOP, DOWN, SHRINK };

class BlockSprite : public cocos2d::Sprite
{
public:
	static BlockSprite* createBlock(cocos2d::SpriteBatchNode *batch, int type, int col, int row, float supplement);
    static cocos2d::Rect makeTextureRect(int type);

    bool isTouchOrigX(float x);
    bool isTouchPoint(const cocos2d::Point &target);
	void adjustPosition();

    int getRow() { return row; }
    int getCol() { return col; }
	void reset(int type);
	float getVy() { return vy; }
    void addX(float x);
	void down(int type, float vy);
	bool execAnimation();
	bool removeAnimation();
    void resetStatus();
	void removeBlock();

	int type;
	bool checked;
	bool removed;
private:
    float supplement;
	float vy;
	MoveMode mode;
	MoveMode moveArrType;
    float origPosX;
	float bottom;
    int row;
    int col;
	int removedCount;
};

#endif
