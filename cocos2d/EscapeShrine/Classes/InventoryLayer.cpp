#include "Global.h"
#include "StrUtil.h"
#include "InventoryLayer.h"

InventoryLayer::~InventoryLayer()
{
	L("InventoryLayer::destructor");
	interpreter = nullptr; // it will be deleted by EscapeScene
	items->clear();
	delete items;
	items = nullptr;
    onMenu->release();
}

bool InventoryLayer::init()
{
    if ( !cocos2d::Layer::init() )
    {
        return false;
    }

	background = ObjectSprite::createObjectSimple("item_bg_inventory", Global::WINDOW_WIDTH, 0);
    addChild(background, 1);
    
	cursor = ObjectSprite::createObjectSimple("item_cursor", 0, 0);
    cursor->setVisible(false);
    addChild(cursor, 10000);
    
	menuIcon = ObjectSprite::createObjectSimple("menu_icon", Global::WINDOW_WIDTH, Global::WINDOW_HEIGHT - 50);
    addChild(menuIcon, 20000);
	
	auto dispatcher = cocos2d::Director::getInstance()->getEventDispatcher();
    auto listener = cocos2d::EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(InventoryLayer::onTouchBegan, this);
    dispatcher->addEventListenerWithSceneGraphPriority(listener, this);

	return true;
}

ObjectSprite* InventoryLayer::findItemSprite(const std::string &id)
{
	for ( auto sp : *items )
	{
		if ( sp->getId() == id ) {
			return sp;
		}
	}
	L("no item");
	return nullptr;
}

void InventoryLayer::addItem(const std::string &id)
{
    findItemSprite(id)->setVisible(true);
	updateView();
}

void InventoryLayer::removeItem(const std::string &id)
{
    findItemSprite(id)->setVisible(false);
    selectedItemId = "";
	cursor->setVisible(false);
	updateView();
}

ObjectSprite* InventoryLayer::getTouchedItem(cocos2d::Point pos)
{
	for ( auto os : *items )
	{
		if ( os->isVisible() && os->isTouchPoint(pos) )
		{
			return os;
		}
	}
	return nullptr;
}

void InventoryLayer::updateView()
{
	int i = 0;
	for ( auto os : *items )
	{
        if ( os->isVisible() )
        {
            L(os->getId().c_str());
            if( os->getId() == selectedItemId )
            {
                //int x = os->getPosition().x - ((cursor->getTextureRect().size.width) - (os->getTextureRect().size.width) / 2);
                //int y = os->getPosition().y - ((cursor->getTextureRect().size.height) - (os->getTextureRect().size.height) / 2);
                int x = Global::WINDOW_WIDTH;
                int y = Global::WINDOW_HEIGHT - (i * (os->getTextureRect().size.height + 8));
                cursor->setPosition(x,y);
                cursor->setVisible(true);
            }
            os->setPosition(Global::WINDOW_WIDTH + 2,
                            Global::WINDOW_HEIGHT - (i * (os->getTextureRect().size.height + 8)) - 3);
            i++;
        }
	}
}

bool InventoryLayer::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);

    L("InventoryLayer::onTouchBegan");
    
    if ( background->isTouchPoint(pos) )
    {
        if ( menuIcon->isTouchPoint(pos) )
        {
            onMenu->execute();
        }
        else
        {
            for ( auto item : *items )
            {
                if ( item->isVisible() && item->isTouchPoint(pos) )
                {
                    if ( (selectedItemId == "") || (selectedItemId != item->getId()) )
                    {
                        selectedItemId = item->getId();
                        updateView();
                    }
                    else
                    {
                        interpreter->execEvent(item->node);
                    }
                }
            }
        }
        return true;
    }
    else
    {
        return false;
    }
}
