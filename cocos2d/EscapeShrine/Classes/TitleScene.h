//
//  TitleScene.h
//  EscapeShrine
//
//  Created by Yuichiro Hoshi on 3/8/14.
//
//

#ifndef EscapeShrine_TitleScene_h
#define EscapeShrine_TitleScene_h

#include "cocos2d.h"
#include "CommonLabel.h"

class Title : public cocos2d::Layer
{
public:
    ~Title();
	static cocos2d::Scene* createScene();
	virtual bool init();
	bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);
	CREATE_FUNC(Title);
    virtual void onEnterTransitionDidFinish();
	void startNew();
private:
    CommonLabel *newLabel;
    CommonLabel *loadLabel;
};


#endif
