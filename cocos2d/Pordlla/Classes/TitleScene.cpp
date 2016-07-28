//
//  TitleScene.cpp
//
#include "TitleScene.h"
#include "StrUtil.h"
#include "Global.h"
#include "CommonLabel.h"
#include "CommonSprite.h"
#include "StageSelectScene.h"

Title::~Title()
{
    L("Title destructor");
}

cocos2d::Scene* Title::createScene()
{
    auto scene = cocos2d::Scene::create();
    auto layer = Title::create();
    scene->addChild(layer);
    return scene;
}

void Title::changeScene(cocos2d::Scene *scene)
{
	cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
	cocos2d::Director::getInstance()->replaceScene(transition);
}

bool Title::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);

	//if ( startLabel->isTouchPoint(pos) )
	//{
		//TODO
		std::string data = cocos2d::UserDefault::getInstance()->getStringForKey(Global::SAVE_ID, "");
		changeScene(StageSelect::createScene());
	//}
    return true;
}

void Title::onEnterTransitionDidFinish()
{
    Layer::onEnterTransitionDidFinish();
	L("Title::onEnterTransitionDidFinish");
    
	auto dispatcher = cocos2d::Director::getInstance()->getEventDispatcher();
    auto listener = cocos2d::EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(Title::onTouchBegan, this);
    dispatcher->addEventListenerWithSceneGraphPriority(listener, this);
}

bool Title::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }

	auto title = CommonSprite::create("title", Global::getInstance()->playArea.origin.x, Global::getInstance()->playArea.origin.y);
    addChild(title);

	startLabel = CommonLabel::create(Global::dic("start"), 100.0f, Global::getInstance()->WINDOW_HEIGHT - 250);
	addChild(startLabel);
    
    L("Title init");
    return true;
}
