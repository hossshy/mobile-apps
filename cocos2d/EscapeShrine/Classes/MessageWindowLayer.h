#ifndef MESSAGEWINDOWLAYER_H
#define MESSAGEWINDOWLAYER_H

#include <string>
#include "cocos2d.h"
#include "CommonLabel.h"

class MessageWindowLayer : public cocos2d::Layer
{
public:
	virtual bool init();
	void setText(const std::string& text);
	CREATE_FUNC(MessageWindowLayer);
private:
	cocos2d::Sprite* background;
	CommonLabel* label;
};

#endif
