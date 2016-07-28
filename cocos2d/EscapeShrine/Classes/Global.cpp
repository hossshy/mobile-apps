//
//  Global.cpp
//  EscapeShrine
//
//  Created by Yuichiro Hoshi on 3/8/14.
//
//

#include "cocos2d.h"
#include "Global.h"
#include "StrUtil.h"

Global* Global::_instance = nullptr;

Global::Global(const std::string& dic)
{
    dictionary = StrUtil::makeAssocMap(cocos2d::FileUtils::getInstance()->getStringFromFile(dic));
}

Global* Global::getInstance()
{
    return Global::_instance;
}

void Global::init(const std::string& dic)
{
    Global::_instance = new Global(dic);
}

std::string Global::dic(const std::string& key)
{
    assert(Global::_instance != nullptr);
    return Global::_instance->dictionary->find(key)->second;
}