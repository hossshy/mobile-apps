#ifndef OBJECTSPRITE_H
#define OBJECTSPRITE_H

#include <string>
#include "cocos2d.h"
#include "Interpreter.h"

class ObjectSprite : public cocos2d::Sprite
{
public:
    virtual ~ObjectSprite();
    std::string& getId() { return id; }
	std::string roomId;
	bool viewable;
	int frame;
	int maxFrame;
	CommandListNode *node;
	
	void rotateFrame();
    void updateFrame();
	bool isTouchPoint(const cocos2d::Point &target);
    void setPosition(float x, float y);
	
	static ObjectSprite* createObject(std::string id, const std::string &imageId, int frame, int x, int y, int w, int h, const std::string &roomId, const std::string &code, bool viewable, int maxFrame);
    static ObjectSprite* createObjectSimple(const std::string &id, int x, int y);
private:
	std::string id;
};

#endif
