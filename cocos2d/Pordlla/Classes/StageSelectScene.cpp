#include "StageSelectScene.h"

#include "StrUtil.h"
#include "Global.h"
#include "CommonLabel.h"
#include "MainScene.h"

cocos2d::Scene* StageSelect::createScene()
{
    auto scene = cocos2d::Scene::create();
    auto layer = StageSelect::create();
    scene->addChild(layer);
    return scene;
}

void StageSelect::goNextScene()
{
	auto scene = Main::createScene();
	cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
	cocos2d::Director::getInstance()->replaceScene(transition);
}

bool StageSelect::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);
	goNextScene();
    return true;
}

void StageSelect::onEnterTransitionDidFinish()
{
    Layer::onEnterTransitionDidFinish();
	L("StageSelect::onEnterTransitionDidFinish");
    
	auto dispatcher = cocos2d::Director::getInstance()->getEventDispatcher();
    auto listener = cocos2d::EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(StageSelect::onTouchBegan, this);
    dispatcher->addEventListenerWithSceneGraphPriority(listener, this);
}


bool StageSelect::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }
	//auto background = ObjectSprite::createObjectSimple("title", 0, 0);
    //addChild(background, 1);
    
    L("StageSelect init");
    return true;
}
