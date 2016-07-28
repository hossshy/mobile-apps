#include "StrUtil.h"

std::vector<std::string> StrUtil::split(const std::string &str, const std::string &delim)
{
	std::vector<std::string> ret;
	auto start = 0U;
	auto end = str.find(delim);
	while (end != std::string::npos)
    {
		ret.push_back(str.substr(start, end - start));
		start = end + delim.length();
		end = str.find(delim, start);
    }
	ret.push_back(str.substr(start, end));

	return ret;
}

std::string StrUtil::trim(const std::string &str)
{
	std::string ret = str;
	ret.erase(0, ret.find_first_not_of(" "));
	ret.erase(ret.find_last_not_of(" ") + 1);
	return ret;
}

std::map<std::string, std::string>* StrUtil::makeAssocMap(const std::string &data)
{
	auto ret = new std::map<std::string, std::string>();
	std::vector<std::string> lines = StrUtil::split(data, "\n");
	for ( std::string line : lines )
	{
		int pos = line.find("=");
		if ( pos > 0 )
		{
			std::string key = line.substr(0, pos);
			std::string val = line.substr(pos+1);
			ret->insert(std::pair<std::string, std::string>(StrUtil::trim(key), StrUtil::trim(val)));
		}
	}
	return ret;
}

StringTokenizer::StringTokenizer(const std::string &str, const std::string &delim)
{
	this->vec = StrUtil::split(str, delim);
	this->it = vec.begin();
}

std::string StringTokenizer::next()
{
	if ( it == vec.end() ) {
		return "\0";
	}

	return *it++;
}
