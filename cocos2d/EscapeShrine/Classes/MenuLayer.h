#ifndef MENULAYER_H
#define MENULAYER_H

#include "cocos2d.h"
#include "CommonLabel.h"

class MenuLayer : public cocos2d::Layer
{
public:
	virtual bool init();
	CREATE_FUNC(MenuLayer);
	CommonLabel* backLabel;
	CommonLabel* saveBackLabel;
	CommonLabel* saveTitleLabel;
};


#endif
