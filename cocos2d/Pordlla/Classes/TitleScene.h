//
//  TitleScene.h
//

#ifndef TitleScene_h
#define TitleScene_h

#include "cocos2d.h"
#include "CommonLabel.h"

class Title : public cocos2d::Layer
{
public:
    ~Title();
	static cocos2d::Scene* createScene();
	virtual bool init();
	bool onTouchBegan(cocos2d::Touch *touch, cocos2d::Event *event);
	CREATE_FUNC(Title);
    virtual void onEnterTransitionDidFinish();
	void changeScene(cocos2d::Scene *scene);
private:
    CommonLabel *startLabel;
};


#endif
