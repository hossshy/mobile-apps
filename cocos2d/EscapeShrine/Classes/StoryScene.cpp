#include "StrUtil.h"
#include "Global.h"
#include "ObjectSprite.h"
#include "StoryScene.h"
#include "CommonLabel.h"
#include "EscapeScene.h"
#include "TitleScene.h"

Story::~Story()
{
    L("Story::destroy");
}

cocos2d::Scene* Story::createScene()
{
    // 'scene' is an autorelease object
    auto scene = cocos2d::Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = Story::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

void Story::setText(const std::string &id)
{
    L(id);
    L("setText");
    auto lines = StrUtil::split(Global::dic(id), "\\n");
    int i = 0;
    for ( auto line : lines )
    {
        auto label = CommonLabel::create(line);
        label->setPosition(10 + 4, Global::WINDOW_HEIGHT - 30 - (i * 50));
        label->setOpacity(0.0f);
        addChild(label, i);
        label->runAction(cocos2d::Sequence::create(cocos2d::DelayTime::create(i * 2.0f),
                                                   cocos2d::FadeIn::create(1.5f),
                                                   cocos2d::DelayTime::create(1.0f),
                                                   cocos2d::CallFuncN::create(CC_CALLBACK_1(Story::callbackActionFinished, this)),
                                                   NULL));
        i++;
    }
    tapCount = i;
}

void Story::goNextScene()
{
    if ( !isCalledFinish )
    {
        isCalledFinish = true;
        
        if ( Global::getInstance()->storyId == "story_op" )
        {
            auto scene = Escape::createScene();
            cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
            cocos2d::Director::getInstance()->replaceScene(transition);
        }
        else
        {
            auto scene = Title::createScene();
            cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
            cocos2d::Director::getInstance()->replaceScene(transition);
        }
    }
}

void Story::callbackActionFinished(cocos2d::Node* sender)
{
    tapCount--;
    if ( tapCount <= 0 )
    {
        goNextScene();
    }
}

bool Story::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);

    if ( skipLabel->isVisible() && skipLabel->isTouchPoint(pos) )
    {
        tapCount = 0;
    }
    
    if ( tapCount <= 0 )
    {
        goNextScene();
    }
    else
    {
        skipLabel->setVisible(true);
    }
    return true;
}

void Story::onEnterTransitionDidFinish()
{
    Layer::onEnterTransitionDidFinish();
	L("Story::onEnterTransitionDidFinish");
    
	auto dispatcher = cocos2d::Director::getInstance()->getEventDispatcher();
    auto listener = cocos2d::EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(Story::onTouchBegan, this);
    dispatcher->addEventListenerWithSceneGraphPriority(listener, this);
    setText(Global::getInstance()->storyId);
}


bool Story::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }
	//auto background = ObjectSprite::createObjectSimple("title", 0, 0);
    //addChild(background, 1);
    
    tapCount = 0;
    isCalledFinish = false;
    
    skipLabel = CommonLabel::create(Global::dic("skip"));
    skipLabel->setPosition(350, Global::WINDOW_HEIGHT - 10);
    skipLabel->setVisible(false);
    addChild(skipLabel, 10);
    
    L("Story init");
    return true;
}
