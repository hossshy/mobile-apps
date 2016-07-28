#ifndef STORYSCENE_H
#define STORYSCENE_H

#include "cocos2d.h"
#include "CommonLabel.h"

class Story : public cocos2d::Layer
{
public:
    ~Story();
    void setText(const std::string &id);

	static cocos2d::Scene* createScene();
	virtual bool init();  
	bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);
	CREATE_FUNC(Story);
    virtual void onEnterTransitionDidFinish();
    void callbackActionFinished(cocos2d::Node* sender);
private:
    CommonLabel *skipLabel;
    int tapCount;
    bool isCalledFinish;
    void goNextScene();
};

#endif







