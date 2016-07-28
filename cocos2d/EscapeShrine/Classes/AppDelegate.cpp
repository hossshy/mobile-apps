#include "AppDelegate.h"
#include "Global.h"
#include "EscapeScene.h"
#include "TitleScene.h"

USING_NS_CC;

AppDelegate::AppDelegate() {
}

AppDelegate::~AppDelegate() 
{
}

bool AppDelegate::applicationDidFinishLaunching() {
    // initialize director
    auto director = Director::getInstance();
    auto eglView = EGLView::getInstance();

    director->setOpenGLView(eglView);
	
    // turn on display FPS
    director->setDisplayStats(true);

    // set FPS. the default value is 1.0/60 if you don't call this
    director->setAnimationInterval(1.0 / 30);
    
    
    // load dictionary
    cocos2d::LanguageType currentLanguageType = cocos2d::Application::getInstance()->getCurrentLanguage();
	std::string filename;
	if ( currentLanguageType == cocos2d::LanguageType::JAPANESE ) {
		filename = "dic.ja";
	} else {
		filename = "dic.en";
	}
    Global::init(filename);
    
    int DESIGN_WIDTH = 480;
    int DESIGN_HEIGHT = 320;
    
    auto screenSize = EGLView::getInstance()->getFrameSize();
    auto designSize = Size(DESIGN_WIDTH, DESIGN_HEIGHT);
    Size winSize = director->getWinSize();
    if (winSize.width > winSize.height * 1.5)
    {
        designSize.width = DESIGN_HEIGHT * winSize.width / winSize.height;
    }
    else
    {
        designSize.height = DESIGN_WIDTH * winSize.height / winSize.width;
    }
    Global::getInstance()->designWidth = designSize.width;
    Global::getInstance()->designHeight = designSize.height;
    Global::getInstance()->paddingX = (designSize.width - DESIGN_WIDTH) / 2;
    Global::getInstance()->paddingY = (designSize.height - DESIGN_HEIGHT) / 2;
    cocos2d::log("W_SIZE %f:%f %f:%f %f:%f", winSize.width, winSize.height, designSize.width, designSize.height, Global::getInstance()->paddingX, Global::getInstance()->paddingY);
    eglView->setDesignResolutionSize(designSize.width, designSize.height, ResolutionPolicy::SHOW_ALL );
    //director->setContentScaleFactor(1.0f);
    // create a scene. it's an autorelease object

    
    
    auto scene = Title::createScene();

    // run
    director->runWithScene(scene);

    return true;
}

// This function will be called when the app is inactive. When comes a phone call,it's be invoked too
void AppDelegate::applicationDidEnterBackground() {
    Director::getInstance()->stopAnimation();

    // if you use SimpleAudioEngine, it must be pause
    // SimpleAudioEngine::sharedEngine()->pauseBackgroundMusic();
}

// this function will be called when the app is active again
void AppDelegate::applicationWillEnterForeground() {
    Director::getInstance()->startAnimation();

    // if you use SimpleAudioEngine, it must resume here
    // SimpleAudioEngine::sharedEngine()->resumeBackgroundMusic();
}
