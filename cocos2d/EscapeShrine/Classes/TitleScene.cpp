//
//  TitleScene.cpp
//  EscapeShrine
//
//  Created by Yuichiro Hoshi on 3/8/14.
//
//
#include "TitleScene.h"
#include "StrUtil.h"
#include "Global.h"
#include "ObjectSprite.h"
#include "EscapeScene.h"
#include "StoryScene.h"

Title::~Title()
{
    L("Title destructor");
}

cocos2d::Scene* Title::createScene()
{
    // 'scene' is an autorelease object
    auto scene = cocos2d::Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = Title::create();
    
    // add layer as a child to scene
    scene->addChild(layer);
    
    // return the scene
    return scene;
}

void Title::startNew()
{
	auto scene = Story::createScene();
	Global::getInstance()->storyId = "story_op";
	cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
	cocos2d::Director::getInstance()->replaceScene(transition);
}

bool Title::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);

	if ( loadLabel->isTouchPoint(pos) )
	{
		std::string data = cocos2d::UserDefault::getInstance()->getStringForKey(Global::SAVE_ID, "");
		if ( data == "" )
		{
			startNew();
		}
		else
		{
			Global::getInstance()->loadFlag = true;
			auto scene = Escape::createScene();
			cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
			cocos2d::Director::getInstance()->replaceScene(transition);
		}
	}
	else if ( newLabel->isTouchPoint(pos) )
	{
		startNew();
	}
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

	Global::getInstance()->loadFlag = false;

	auto background = ObjectSprite::createObjectSimple("title", 0, 0);
    addChild(background, 1);
    
    auto label = CommonLabel::create(Global::dic("title"));
    label->setPosition(100, Global::WINDOW_HEIGHT - 30);
	addChild(label, 1);
    
    newLabel = CommonLabel::create(Global::dic("new"));
    newLabel->setPosition(100, Global::WINDOW_HEIGHT - 200);
	addChild(newLabel, 2);

	loadLabel = CommonLabel::create(Global::dic("load"));
	loadLabel->setPosition(100, Global::WINDOW_HEIGHT - 250);
	addChild(loadLabel, 3);
    
    L("Title init");
    return true;
}
