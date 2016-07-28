#include <string>

#include "Global.h"
#include "EscapeScene.h"
#include "TitleScene.h"
#include "StoryScene.h"
#include "InventoryLayer.h"
#include "CommonLabel.h"
#include "resource.h"

USING_NS_CC;

int Escape::indexOf(std::array<std::string, 4> arr, std::string key)
{
	int i = 0;
	for ( auto val : arr ) {
		if ( val == key ) {
			return i;
		}
		i++;
	}
	return -1;
}

void Escape::changeDetailImageVisible(const std::string &id, bool flag)
{
	findObjectSprite(DETAIL_IMG_BASE)->setVisible(flag);
	findObjectSprite(id)->setVisible(flag);
}

void Escape::showDetailImage(const std::string &id)
{
	if ( detailItemId != "" )
    {
		hideDetailImage(detailItemId);
	}
	changeDetailImageVisible(id, true);
	detailItemId = id;
}

void Escape::hideDetailImage(const std::string &id)
{
	if ( detailItemId != "" )
    {
        changeDetailImageVisible(id, false);
        detailItemId = "";
    }
}

void Escape::executeCommand(CommandNode *node)
{
	std::string cmd = node->command;
	if ( "msg" == cmd )
	{
		msgWindow->setText(Global::dic(node->arg));
		msgWindow->setVisible(true);
	}
	else if ( "arrow" == cmd )
	{
		auto arrow = rooms->find(roomId);
		int index = indexOf(DIRECTION, node->arg);
		std::string val = arrow->second->at(index);
		L("ROOM ");
		L(val.c_str());
		goNextRoom(val);
	}
	else if ( "item" == cmd )
	{
		hideDetailImage(detailItemId);
		inventory->addItem(node->arg);
	}
	else if ( "delitem" == cmd )
	{
		inventory->removeItem(node->arg);
	}
	else if ( "show" == cmd )
	{
		auto tmp = findObjectSprite(node->arg);
		tmp->viewable = true;
		if ( roomId == tmp->roomId )
		{
			tmp->setVisible(true);
		}
	}
	else if ( "hide" == cmd )
	{
		auto tmp = findObjectSprite(node->arg);
		tmp->viewable = false;
		tmp->setVisible(false);
	}
	else if ( "go" == cmd )
	{
		goNextRoom(node->arg);
	}
	else if ( "detail" == cmd )
	{
		showDetailImage(node->arg);
	}
	else if ( "story" == cmd )
	{
        auto scene = Story::createScene();
        Global::getInstance()->storyId = node->arg;
        cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
        cocos2d::Director::getInstance()->replaceScene(transition);
	}
	else if ( "rotate" == cmd )
	{
		findObjectSprite(node->arg)->rotateFrame();
	}
}

bool Escape::executeIfCommand(CommandNode *node)
{
	std::string cmd = node->command;
	if ( "item" == cmd )
	{
		return inventory->getSelectedItemId() == node->arg;
	}
	else if ( "show" == cmd )
	{
		return findObjectSprite(node->arg)->viewable == true;
	}
	else if ( "hide" == cmd )
	{
		return findObjectSprite(node->arg)->viewable == false;
	}
	else if ( "number" == cmd )
	{
		auto args = StrUtil::split(node->arg, ":");
		auto g = groups.find(args[0]);
		return g->second->check(args[1]);
	}
	return false;
}

ObjectSprite* Escape::findObjectSprite(const std::string &id)
{
	for ( auto sp : *objects )
	{
		if ( sp->getId() == id ) {
			return sp;
		}
	}
	L(id.c_str());
	L("no object");
	return nullptr;
}

void Escape::createGroups()
{
	GroupData* gd = new GroupData();
	gd->add(findObjectSprite("number1"));
	gd->add(findObjectSprite("number2"));
	gd->add(findObjectSprite("number3"));
	gd->add(findObjectSprite("number4"));
	groups.insert(std::pair<std::string, GroupData*>("trick_box", gd));

	gd = new GroupData();
	gd->add(findObjectSprite("lamp1"));
	gd->add(findObjectSprite("lamp2"));
	gd->add(findObjectSprite("lamp3"));
	gd->add(findObjectSprite("lamp4"));
	groups.insert(std::pair<std::string, GroupData*>("lamps", gd));

	gd = new GroupData();
	gd->add(findObjectSprite("number2_1"));
	gd->add(findObjectSprite("number2_2"));
	gd->add(findObjectSprite("number2_3"));
	gd->add(findObjectSprite("number2_4"));
	groups.insert(std::pair<std::string, GroupData*>("trick_box2", gd));

	gd = new GroupData();
	gd->add(findObjectSprite("symbol1"));
	gd->add(findObjectSprite("symbol2"));
	gd->add(findObjectSprite("symbol3"));
	groups.insert(std::pair<std::string, GroupData*>("symbols", gd));
}



void Escape::goNextRoom(const std::string &newRoomId)
{
	for ( auto sp : *objects )
	{
		if ( sp->roomId == "" )
		{
			// nothing to do
		}
		else if ( (sp->roomId == roomId) && sp->isVisible() )
		{
			// hide previous room side.
			sp->setVisible(false);
		}
		else if ( (sp->roomId == newRoomId) && sp->viewable )
		{
			// show next room side.
			sp->setVisible(true);
		}
	}

	// set system data
	auto arrow = rooms->find(newRoomId);
	for ( int i = 0; i < DIRECTION.size(); i++ ) {
		std::string dir = cocos2d::StringUtils::format("arrow_%s", DIRECTION[i].c_str());
		ObjectSprite* os = findObjectSprite(dir);
		os->setVisible(arrow->second->at(i) != "null");
	}

	roomId = newRoomId;
}


Scene* Escape::createScene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = Escape::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

bool Escape::onTouchBegan(cocos2d::Touch* touch, cocos2d::Event *event)
{
	cocos2d::Point pos = this->convertTouchToNodeSpace(touch);

	if ( menu->isVisible() )
	{
		if ( menu->backLabel->isTouchPoint(pos) )
		{
			menu->setVisible(false);
		}
		else if ( menu->saveBackLabel->isTouchPoint(pos) )
		{
			save();
			menu->setVisible(false);
		}
		else if ( menu->saveTitleLabel->isTouchPoint(pos) )
		{
			save();
			auto scene = Title::createScene();
			cocos2d::TransitionFade* transition = cocos2d::TransitionFade::create(1.5f, scene);
			cocos2d::Director::getInstance()->replaceScene(transition);
		}
		return true;
	}
	
	bool flag = true;
	if ( msgWindow->isVisible() )
	{
		msgWindow->setVisible(false);
	}
								
	if ( flag ) { // go if you don't touch other objects
		std::vector<ObjectSprite*>::reverse_iterator ite = objects->rbegin();
		while( ite != objects->rend() )
		{
			if ( (*ite)->isVisible() && (*ite)->isTouchPoint(pos) )
			{
				if ( detailItemId == "" )
				{
					interpreter->execEvent((*ite)->node);
				}
				else
				{
					if ( (*ite)->getId() == detailItemId )
					{
						interpreter->execEvent((*ite)->node);
					}
					else
					{
						hideDetailImage(detailItemId);
					}
				}
				break;
			}
			ite++;
		}
	}
		
	log("hoge %f:%f", pos.x, pos.y);
	return false;
}

bool Escape::init()
{
    if ( !Layer::init() )
    {
        return false;
    }
    
	//auto base = ObjectSprite::createObjectSimple("base", Global::getInstance()->paddingX * -1, Global::getInstance()->paddingY * -1);
    //addChild(base);

	interpreter = new Interpreter(this);
	objects = createObjects();
	rooms = createRoom();
	createGroups();
	roomId = "room_door";
    

    
	inventory = InventoryLayer::create();
    inventory->interpreter = this->interpreter;
    inventory->setVisible(true);
	this->addChild(inventory, 90000);
    
    inventory->items = createItems();
	for ( auto sp : *inventory->items )
	{
        inventory->addChild(sp, 10);
        sp->setVisible(false);
    }

    
	msgWindow = MessageWindowLayer::create();
	msgWindow->setVisible(false);
	this->addChild(msgWindow, 100000);

	menu = MenuLayer::create();
	menu->setVisible(false);
	this->addChild(menu, 500000);
    
    inventory->onMenu = CallFunc::create([this](){
        this->menu->setVisible(true);
    });
    inventory->onMenu->retain();

    
	int counter = 0;
	for ( auto sp : *objects )
	{
		this->addChild(sp, counter++);
	}

	if ( Global::getInstance()->loadFlag )
	{
		load();
	}
	
    goNextRoom(roomId);

    L("Escape init");
    return true;
}

void Escape::onEnter()
{
    Layer::onEnter();
	L("onEnter");
}

void Escape::onEnterTransitionDidFinish()
{
    Layer::onEnterTransitionDidFinish();
	L("Escape::onEnterTransitionDidFinish");
    
	auto dispatcher = Director::getInstance()->getEventDispatcher();
    auto listener = EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(Escape::onTouchBegan, this);
    dispatcher->addEventListenerWithSceneGraphPriority(listener, this);
}

void Escape::onExitTransitionDidStart()
{
    Layer::onExitTransitionDidStart();
	L("onExitTransitionDidStart");
}

void Escape::onExit()
{
    Layer::onExit();
	L("onExit");
}

static const std::string DELIMITER = "\t";
static const std::string DELIMITER2 = ":";
static const std::string DELIMITER3 = ".";

void Escape::save()
{
	std::string data;

	data = roomId;
	data += DELIMITER;
	int i = 0;
	for ( auto obj : *objects )
	{
		data += obj->getId();
        data += DELIMITER3;
        data += ((obj->viewable) ? "1" : "0");
        data += DELIMITER3;
		std::stringstream ss;
		ss << obj->frame;
        data += ss.str();
		if ( i < (objects->size() - 1) )
		{
			data += DELIMITER2;
		}
		i++;
	}
	data += DELIMITER;
	i = 0;
	for ( auto obj : *(inventory->items) )
	{
		if ( obj->isVisible() )
		{
			data += obj->getId();
            data += DELIMITER2;
		}
		i++;
	}
	L(data.c_str());
    UserDefault::getInstance()->setStringForKey(Global::SAVE_ID, data.c_str());
}

void Escape::load()
{
    std::string data = UserDefault::getInstance()->getStringForKey(Global::SAVE_ID, "");
	L(data.c_str());
	if ( data != "" )
	{
		auto base = new StringTokenizer(data, DELIMITER);
		roomId = base->next();
		auto statusList = StrUtil::split(base->next(), DELIMITER2);
		for ( auto st : statusList )
		{
			auto status = new StringTokenizer(st, DELIMITER3);
			auto tmp = findObjectSprite(status->next());
			tmp->viewable = ("1" == status->next());
            tmp->frame = atoi(status->next().c_str());
            tmp->updateFrame();
		}
		auto items = StrUtil::split(base->next(), DELIMITER2);
		for ( auto id : items )
		{
            if ( id != "" )
            {
                inventory->addItem(id);
            }
		}
	}
}

Escape::~Escape()
{
	L("Escape::destructor");
	
	delete interpreter;
	interpreter = nullptr;
    /*
	for ( auto obj : *objects )
	{
		delete obj;
     }
     */
	objects->clear();
	delete objects;
	objects = nullptr;
    
     for ( auto obj : *rooms )
	{
		delete obj.second;
	}
    
	rooms->clear();
	delete rooms;
	rooms = nullptr;
    
	for ( auto obj : groups )
	{
		delete obj.second;
	}
    
     groups.clear();
}
