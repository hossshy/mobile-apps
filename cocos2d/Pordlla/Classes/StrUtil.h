#ifndef STR_UTIL_H
#define STR_UTIL_H

#include <array>
#include <map>
#include <string>
#include <vector>

#ifdef __COCOS2D_H__
#define L(msg) cocos2d::log("STR %s", msg)
#else
#include <iostream>
#define L(msg) std::cout << msg << std::endl;
#endif

inline std::string dic(std::map<std::string, std::string> *dictionary, const std::string &key)
{
	L(key.c_str());
	return dictionary->find(key)->second;
}

class StrUtil
{
public:
	static std::vector<std::string> split(const std::string &str, const std::string &delim);
	static std::string trim(const std::string &str);
	static std::map<std::string, std::string>* makeAssocMap(const std::string &data);
};

class StringTokenizer
{
public:
	StringTokenizer(const std::string &str, const std::string &delim);
	std::string next();
private:
	std::vector<std::string> vec;
	std::vector<std::string>::iterator it;
};

#endif
