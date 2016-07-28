#ifndef __ESCAPE_SCENE_H__
#define __ESCAPE_SCENE_H__

#include <array>
#include <map>
#include <vector>
#include <string>
#include "cocos2d.h"
#include "StrUtil.h"
#include "Interpreter.h"
#include "ObjectSprite.h"
#include "GroupData.h"
#include "MessageWindowLayer.h"
#include "MenuLayer.h"
#include "InventoryLayer.h"

class Escape : public cocos2d::Layer, InterpreterEvent
{
public:
	ObjectSprite* findObjectSprite(const std::string &id);
	void goNextRoom(const std::string &newRoomId);
	void fadeIn();
	void callbackActionFinished(Node* sender);
	void changeDetailImageVisible(const std::string &id, bool flag);
	void showDetailImage(const std::string &id);
	void hideDetailImage(const std::string &id);
	void save();
	void load();
	virtual ~Escape();
	
	// there's no 'id' in cpp, so we recommend returning the class instance pointer
	static cocos2d::Scene* createScene();

	// Here's a difference. Method 'init' in cocos2d-x returns bool, instead of returning 'id' in cocos2d-iphone
	virtual bool init();  
    
	bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);

	// implement the "static create()" method manually
	CREATE_FUNC(Escape);
	void executeCommand(CommandNode *node);
	bool executeIfCommand(CommandNode *node);

    virtual void onEnter();
    virtual void onEnterTransitionDidFinish();
    virtual void onExitTransitionDidStart();
    virtual void onExit();
private:
	MessageWindowLayer* msgWindow;
	InventoryLayer* inventory;
	MenuLayer *menu;
	Interpreter *interpreter;
	std::vector<ObjectSprite*>* objects;
	std::map<std::string, std::array<std::string, 4>*>* rooms;
	std::map<std::string, GroupData*> groups;
	std::string detailItemId;
	std::string roomId;
	void createGroups();
	int indexOf(std::array<std::string, 4> arr, std::string key);
};

#endif // __ESCAPE_SCENE_H__
