#ifndef STAGESELECTSCENE_H
#define STAGESELECTSCENE_H

#include "cocos2d.h"
#include "CommonLabel.h"

class StageSelect : public cocos2d::Layer
{
public:
	static cocos2d::Scene* createScene();
	virtual bool init();  
	bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);
	CREATE_FUNC(StageSelect);
    virtual void onEnterTransitionDidFinish();
private:
    void goNextScene();
};

#endif
