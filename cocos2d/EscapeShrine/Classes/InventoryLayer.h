#ifndef INVENTORYLAYER_H
#define INVENTORYLAYER_H

#include <vector>
#include <string>
#include "cocos2d.h"
#include "Interpreter.h"
#include "ObjectSprite.h"

class InventoryLayer : public cocos2d::Layer
{
public:
	virtual ~InventoryLayer();
	virtual bool init();
	void addItem(const std::string &id);
    ObjectSprite* findItemSprite(const std::string &id);
	void removeItem(const std::string &id);
	ObjectSprite* getTouchedItem(cocos2d::Point pos);
	void updateView();
	std::string getSelectedItemId() { return selectedItemId; };
	bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);
	CREATE_FUNC(InventoryLayer);
    
	std::vector<ObjectSprite*> *items;
	Interpreter *interpreter;
    cocos2d::CallFunc *onMenu;

private:
	ObjectSprite *background;
	ObjectSprite *cursor;
    ObjectSprite *menuIcon;
	std::string selectedItemId;
};


#endif
