#ifndef RESOURCE_H
#define RESOURCE_H


#include <array>
#include <map>
#include <string>
#include <vector>

const std::array<std::string, 4> DIRECTION = {"up", "down", "left", "right"};

const std::string DETAIL_IMG_BASE = "detail_img_base";

const std::string room_door = "room_door";
const std::string room_scroll = "room_scroll";
const std::string room_circle = "room_circle";
const std::string room_wood = "room_wood";
const std::string room_shelf = "room_shelf";
const std::string room_shelf2 = "room_shelf2";
const std::string room_net = "room_net";
const std::string room_trick_box = "room_trick_box";
const std::string room_trick_box2 = "room_trick_box2";
const std::string room_mirror = "room_mirror";
const std::string room_mirror2 = "room_mirror2";
const std::string room_drawer = "room_drawer";
const std::string room_scroll_detail = "room_scroll_detail";
const std::string room_circle_detail = "room_circle_detail";
const std::string room_circle_detail2 = "room_circle_detail2";

const std::string room_north = "room_north";
const std::string room_scroll_north = "room_scroll_north";
const std::string room_west = "room_west";
const std::string room_east = "room_east";
const std::string room_south = "room_south";
const std::string room_board = "room_board";

std::vector<ObjectSprite*>* createItems()
{
	auto items = new std::vector<ObjectSprite*>();
    items->push_back(ObjectSprite::createObject("item_hammer_head", "item_icons", 0, 0,0,44,44, "", "set detail detail_hammer_head set msg msg_item_hammer_head", true, -1));
	items->push_back(ObjectSprite::createObject("item_wood_stick", "item_icons", 1, 0,0,44,44, "", "set detail detail_wood_stick set msg msg_item_wood_stick", true, -1));
	items->push_back(ObjectSprite::createObject("item_wood_hammer", "item_icons", 2, 0,0,44,44, "", "set detail detail_wood_hammer set msg msg_item_wood_hammer", true, -1));
	items->push_back(ObjectSprite::createObject("item_nail", "item_icons", 3, 0,0,44,44, "", "set detail detail_nail set msg msg_item_nail", true, -1));
	items->push_back(ObjectSprite::createObject("item_doll_hand", "item_icons", 4, 0,0,44,44, "", "set detail detail_doll_hand set msg msg_item_doll_hand", true, -1));
	items->push_back(ObjectSprite::createObject("item_straw_doll", "item_icons", 5, 0,0,44,44, "", "set detail detail_straw_doll set msg msg_item_straw_doll", true, -1));
	items->push_back(ObjectSprite::createObject("item_defective_doll", "item_icons", 6, 0,0,44,44, "", "set detail detail_defective_doll set msg msg_item_defective_doll", true, -1));
	items->push_back(ObjectSprite::createObject("item_oharai_stick", "item_icons", 7, 0,0,44,44, "", "set detail detail_oharai_stick set msg msg_item_oharai_stick", true, -1));
	items->push_back(ObjectSprite::createObject("item_key", "item_icons", 8, 0,0,44,44, "", "set detail detail_key set msg msg_item_key", true, -1));
	items->push_back(ObjectSprite::createObject("item_key2", "item_icons", 8, 0,0,44,44, "", "set detail detail_key2 set msg msg_item_key", true, -1));
	items->push_back(ObjectSprite::createObject("item_rect_doll", "item_icons", 9, 0,0,44,44, "", "set detail detail_rect_doll set msg msg_item_rect_doll", true, -1));
	items->push_back(ObjectSprite::createObject("item_doll", "item_icons", 10, 0,0,44,44, "", "set detail detail_doll set msg msg_item_doll", true, -1));
	items->push_back(ObjectSprite::createObject("item_ohuda", "item_icons", 11, 0,0,44,44, "", "set detail detail_ohuda set msg msg_item_ohuda", true, -1));
	items->push_back(ObjectSprite::createObject("item_cursor", "item_cursor", 10, 0,0,50,50, "", "", false, -1));
	items->push_back(ObjectSprite::createObject("item_bg_inventory", "item_bg_inventory", 10, 0,0,50,320, "", "", false, -1));
	items->push_back(ObjectSprite::createObject("item_bg_msgwin", "item_bg_msgwin", 10, 0,0,0,0, "", "", false, -1));
	items->push_back(ObjectSprite::createObject("item_bg_slidein", "item_bg_slidein", 10, 0,0,0,0, "", "", false, -1));
    return items;
}

std::vector<ObjectSprite*>* createObjects()
{
	auto objects = new std::vector<ObjectSprite*>();
	objects->push_back(ObjectSprite::createObject("bg_door", "bg", 0, 0,0,430,320, room_door, "", true, -1));
	objects->push_back(ObjectSprite::createObject("door", "door", 0, 150,80,146,173, room_door, "if isset item item_key2 set delitem item_key2 set show opened_door set msg msg_door_opened else if isset item item_key set delitem item_key set show opened_door set msg msg_door_opened else set msg msg_door_closed end", true, -1));
	objects->push_back(ObjectSprite::createObject("opened_door", "opened_door", 0, 150,80,146,173, room_door, "if isset show rect_doll set story story_good else set story story_bad end", false, -1));
	objects->push_back(ObjectSprite::createObject("spider_net1", "spider_net", 0, 8,69,65,50, room_door, "", true, -1));
	objects->push_back(ObjectSprite::createObject("spider_net2", "spider_net", 0, 367,69,65,50, room_door, "", true, -1));
	objects->push_back(ObjectSprite::createObject("rect_doll", "rect_doll", 0, 297,226,22,44, room_door, "set msg msg_rect_doll_congrats", false, -1));
	objects->push_back(ObjectSprite::createObject("dropped_key2", "dropped_key", 0, 297,275,24,16, room_door, "set hide dropped_key2 set item item_key2 set msg msg_item_key", false, -1));
	objects->push_back(ObjectSprite::createObject("ghost2", "ghost2", 0, 0,0,430,320, room_door, "set hide ghost2 set msg msg_ghost2_vanished", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_scroll", "bg2", 0, 0,0,430,320, room_scroll, "", true, -1));
	objects->push_back(ObjectSprite::createObject("shelf", "shelf", 0, 240,162,65,107, room_scroll, "", true, -1));
	objects->push_back(ObjectSprite::createObject("dropped_straw_doll", "dropped_straw_doll", 0, 255,239,30,20, room_scroll, "set hide dropped_straw_doll set item item_straw_doll set msg msg_item_straw_doll", true, -1));
	objects->push_back(ObjectSprite::createObject("set_closed_trick_box", "set_closed_trick_box", 0, 250,178,46,35, room_scroll, "set go room_trick_box", true, -1));
	objects->push_back(ObjectSprite::createObject("set_opened_trick_box", "set_opened_trick_box", 0, 250,178,46,39, room_scroll, "set go room_trick_box", false, -1));
	objects->push_back(ObjectSprite::createObject("shelf_door_closed", "shelf_door_closed", 0, 247,218,51,43, room_scroll, "if isset show shelf_hole_with_stick set hide shelf_door_closed set show shelf_door_opened set msg msg_door_opened else set msg msg_door_closed end", true, -1));
	objects->push_back(ObjectSprite::createObject("shelf_door_opened", "shelf_door_opened", 0, 283,219,15,51, room_scroll, "", false, -1));
	objects->push_back(ObjectSprite::createObject("shelf_door_closed2", "shelf_door_closed", 0, 247,171,51,43, room_scroll, "set hide shelf_door_closed2 set show shelf_door_opened2", true, -1));
	objects->push_back(ObjectSprite::createObject("defective_doll", "dropped_defective_doll", 0, 257,132,29,38, room_scroll, "set hide defective_doll set item item_defective_doll set msg msg_item_doll", false, -1));
	objects->push_back(ObjectSprite::createObject("shelf_door_opened2", "shelf_door_opened", 0, 283,173,15,51, room_scroll, "", false, -1));
	objects->push_back(ObjectSprite::createObject("go_shelf", "transparent", 0, 302,222,44,44, room_scroll, "set go room_shelf", true, -1));
	objects->push_back(ObjectSprite::createObject("go_shelf2", "transparent", 0, 198,222,44,44, room_scroll, "set go room_shelf2", true, -1));
	objects->push_back(ObjectSprite::createObject("scroll", "scroll", 0, 86,124,40,113, room_scroll, "set go room_scroll_detail", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_scroll_detail", "bg_mirror", 0, 0,0,430,320, room_scroll_detail, "", true, -1));
	objects->push_back(ObjectSprite::createObject("scroll_detail", "scroll_detail", 0, 0,0,430,320, room_scroll_detail, "set msg msg_scroll_detail", true, -1));
	objects->push_back(ObjectSprite::createObject("b_hint_scroll", "b_hint_scroll", 0, 151,0,130,320, room_scroll_detail, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_circle", "bg", 0, 0,0,430,320, room_circle, "", true, -1));
	objects->push_back(ObjectSprite::createObject("circle", "circle", 0, 148,253,135,55, room_circle, "set go room_circle_detail", true, -1));
	objects->push_back(ObjectSprite::createObject("lamp1", "lamp", 0, 90,148,30,44, room_circle, "set rotate lamp1", true, 2));
	objects->push_back(ObjectSprite::createObject("lamp2", "lamp", 0, 160,148,30,44, room_circle, "set rotate lamp2", true, 2));
	objects->push_back(ObjectSprite::createObject("lamp3", "lamp", 0, 230,148,30,44, room_circle, "set rotate lamp3", true, 2));
	objects->push_back(ObjectSprite::createObject("lamp4", "lamp", 0, 300,148,30,44, room_circle, "set rotate lamp4", true, 2));
	objects->push_back(ObjectSprite::createObject("spider_net3", "spider_net", 0, 8,69,65,50, room_circle, "set go room_net", true, -1));
	objects->push_back(ObjectSprite::createObject("dropped_nail", "dropped_nail", 0, 25,282,53,34, room_circle, "set hide dropped_nail set item item_nail set msg msg_item_nail", false, -1));
	objects->push_back(ObjectSprite::createObject("ghost", "ghost", 0, 25,3,230,320, room_circle, "if isset item item_oharai_stick set show mirror_opened set show mirror_opened_small set delitem item_oharai_stick set hide ghost set msg msg_scream else set hide ghost set hide laid_doll set show broken_doll set msg msg_notice end", false, -1));
	objects->push_back(ObjectSprite::createObject("dropped_key", "dropped_key", 0, 314,283,24,16, room_circle, "set hide dropped_key set item item_key set msg msg_item_key", false, -1));
	objects->push_back(ObjectSprite::createObject("spider_net4", "spider_net", 0, 367,69,65,50, room_circle, "", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_wood", "bg2", 0, 0,0,430,320, room_wood, "", true, -1));
	objects->push_back(ObjectSprite::createObject("wood_fragment", "wood_fragment", 0, 121,124,70,133, room_wood, "if isset item item_straw_doll set delitem item_straw_doll set show hanged_straw_doll set msg msg_set_straw_doll_on_wood else set msg msg_wood_fragment end", true, -1));
	objects->push_back(ObjectSprite::createObject("doll_hand", "dropped_hand", 0, 75,272,30,35, room_wood, "set hide doll_hand set item item_doll_hand set msg msg_item_doll_hand", true, -1));
	objects->push_back(ObjectSprite::createObject("hanged_straw_doll", "hanged_straw_doll", 0, 142,165,26,33, room_wood, "if isset item item_nail set delitem item_nail set hide embed_hammer_head set show dropped_hammer_head set hide hanged_straw_doll set show straw_doll_with_nail set msg msg_set_nail_on_hanged_straw_doll else set msg msg_item_straw_doll end", false, -1));
	objects->push_back(ObjectSprite::createObject("straw_doll_with_nail", "straw_doll_with_nail", 0, 142,165,26,33, room_wood, "if isset item item_wood_hammer set hide straw_doll_with_nail set delitem item_wood_hammer set show defective_doll set show straw_doll_with_blood set msg msg_shocked else set msg msg_item_straw_doll end", false, -1));
	objects->push_back(ObjectSprite::createObject("straw_doll_with_blood", "straw_doll_with_blood", 0, 142,165,26,33, room_wood, "set msg msg_shocked", false, -1));
	objects->push_back(ObjectSprite::createObject("mirror", "mirror", 0, 261,146,60,60, room_wood, "set go room_mirror", true, -1));
	objects->push_back(ObjectSprite::createObject("mirror_opened_small", "mirror_opened_small", 0, 261,146,60,60, room_wood, "set go room_mirror", false, -1));
	objects->push_back(ObjectSprite::createObject("laid_drawer", "laid_drawer", 0, 262,235,62,50, room_wood, "set go room_drawer", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_mirror", "bg_mirror", 0, 0,0,430,320, room_mirror, "", true, -1));
	objects->push_back(ObjectSprite::createObject("mirror_detil", "mirror_detail", 0, 95,28,240,240, room_mirror, "set msg msg_mirror", true, -1));
	objects->push_back(ObjectSprite::createObject("mirror_opened", "mirror_opened", 0, 95,28,240,240, room_mirror, "set go room_north", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_north", "bg4", 0, 0,0,430,320, room_north, "", true, -1));
	objects->push_back(ObjectSprite::createObject("scroll_north", "scroll", 0, 86,124,40,113, room_north, "set go room_scroll_north", true, -1));
	objects->push_back(ObjectSprite::createObject("table", "table", 0, 115,200,210,100, room_north, "set go room_scroll_north", true, -1));
    
	objects->push_back(ObjectSprite::createObject("set_closed_trick_box2", "set_closed_trick_box", 0, 193,190,46,35, room_north, "set go room_trick_box2", true, -1));
	objects->push_back(ObjectSprite::createObject("set_opened_trick_box2", "set_opened_trick_box", 0, 193,190,46,39, room_north, "set go room_trick_box2", false, -1));
	objects->push_back(ObjectSprite::createObject("t_hint_1", "t_hint_1", 0, 179,126,80,60, room_north, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_scroll_detail", "bg_mirror", 0, 0,0,430,320, room_scroll_north, "", true, -1));
	objects->push_back(ObjectSprite::createObject("scroll_detail_north", "scroll_detail", 0, 0,0,430,320, room_scroll_north, "set msg msg_scroll_detail", true, -1));
	objects->push_back(ObjectSprite::createObject("t_hint_scroll", "t_hint_scroll", 0, 161,32,100,231, room_scroll_north, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_trick_box2", "bg_mirror", 0, 0,0,430,320, room_trick_box2, "", true, -1));
	objects->push_back(ObjectSprite::createObject("based_trick_box2", "based_trick_box", 0, 30,40,356,257, room_trick_box2, "", true, -1));
	objects->push_back(ObjectSprite::createObject("dropped_ohuda", "dropped_ohuda", 0, 145,249,140,22, room_trick_box2, "set hide dropped_ohuda set item item_ohuda set msg msg_item_ohuda", true, -1));
	objects->push_back(ObjectSprite::createObject("closed_trick_box2", "closed_trick_box", 0, 52,62,314,219, room_trick_box2, "if isset number trick_box2:3096 set hide closed_trick_box2 set hide number2_1 set hide number2_2 set hide number2_3 set hide number2_4 set hide set_closed_trick_box2 set show set_opened_trick_box2 set show mirror_opened2 set hide ghost3 set show mirror_opened2_small set show opened_trick_box2 set msg msg_door_opened else set msg msg_door_closed end", true, -1));
	objects->push_back(ObjectSprite::createObject("number2_1", "number", 0, 108,180,44,52, room_trick_box2, "set rotate number2_1", true, 10));
	objects->push_back(ObjectSprite::createObject("number2_2", "number", 1, 160,180,44,52, room_trick_box2, "set rotate number2_2", true, 10));
	objects->push_back(ObjectSprite::createObject("number2_3", "number", 2, 214,180,44,52, room_trick_box2, "set rotate number2_3", true, 10));
	objects->push_back(ObjectSprite::createObject("number2_4", "number", 3, 268,180,44,52, room_trick_box2, "set rotate number2_4", true, 10));
	objects->push_back(ObjectSprite::createObject("opened_trick_box2", "opened_trick_box", 0, 14,272,388,63, room_trick_box2, "", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_west", "bg3", 0, 0,0,430,320, room_west, "", true, -1));
	objects->push_back(ObjectSprite::createObject("circle2", "circle", 0, 148,253,135,55, room_west, "set go room_circle_detail2", true, -1));
	objects->push_back(ObjectSprite::createObject("t_hint_2", "t_hint_2", 0, 179,126,80,60, room_west, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_circle_detail2", "bg_circle_detail2", 0, 0,0,430,320, room_circle_detail2, "set msg msg_circle", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_south", "bg4", 0, 0,0,430,320, room_south, "", true, -1));
	objects->push_back(ObjectSprite::createObject("mirror2", "mirror", 0, 110,146,60,60, room_south, "set go room_mirror2", true, -1));
	objects->push_back(ObjectSprite::createObject("mirror_opened2_small", "mirror_opened", 0, 95,113,109,133, room_south, "set go room_mirror2", false, -1));
	objects->push_back(ObjectSprite::createObject("t_hint_3", "t_hint_3", 0, 179,126,80,60, room_south, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("board", "board", 0, 280,167,40,40, room_south, "set go room_board", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_symbol", "bg_mirror", 0, 0,0,430,320, room_board, "", true, -1));
	objects->push_back(ObjectSprite::createObject("board_base", "board_base", 0, 60,0,320,320, room_board, "if isset number symbols:132 set show t_hint_0 set msg msg_board_base else set msg msg_nothing_happened end", true, -1));
	objects->push_back(ObjectSprite::createObject("symbol1", "symbols", 3, 80,166,60,60, room_board, "set rotate symbol1", true, 5));
	objects->push_back(ObjectSprite::createObject("symbol2", "symbols", 3, 188,166,60,60, room_board, "set rotate symbol2", true, 5));
	objects->push_back(ObjectSprite::createObject("symbol3", "symbols", 3, 298,166,60,60, room_board, "set rotate symbol3", true, 5));
	objects->push_back(ObjectSprite::createObject("t_hint_0", "t_hint_0", 0, 170,78,100,60, room_board, "set msg msg_unknown_hint", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_mirror2", "bg_mirror", 0, 0,0,430,320, room_mirror2, "", true, -1));
    
	objects->push_back(ObjectSprite::createObject("mirror_detil2", "mirror_detail", 0, 95,28,240,240, room_mirror2, "set msg msg_cant_go_back", true, -1));
	objects->push_back(ObjectSprite::createObject("ghost3", "ghost3", 0, 95,28,240,240, room_mirror2, "set msg msg_shocked", true, -1));
    
	objects->push_back(ObjectSprite::createObject("mirror_opened2", "mirror_opened", 0, 170,76,109,133, room_mirror2, "set go room_scroll", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_east", "bg3", 0, 0,0,430,320, room_east, "", true, -1));
	objects->push_back(ObjectSprite::createObject("t_hint_4", "t_hint_4", 0, 179,126,80,60, room_east, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("jizo", "jizo", 0, 67,119,90,180, room_east, "set msg msg_jizo", true, -1));
	objects->push_back(ObjectSprite::createObject("symbol_mark", "symbol_mark", 0, 81,179,50,26, room_east, "set msg msg_symbol_mark", true, -1));
	objects->push_back(ObjectSprite::createObject("bg_drawer", "bg_drawer", 0, 0,0,430,320, room_drawer, "set msg msg_bg_drawer", true, -1));
	objects->push_back(ObjectSprite::createObject("closed_drawer", "closed_drawer", 0, 104,165,220,75, room_drawer, "if isset number lamps:1011 set hide closed_drawer set show opened_drawer set show dropped_wood_stick set msg msg_door_opened else set msg msg_door_closed end", true, -1));
	objects->push_back(ObjectSprite::createObject("opened_drawer", "opened_drawer", 0, 0,163,432,156, room_drawer, "", false, -1));
	objects->push_back(ObjectSprite::createObject("dropped_wood_stick", "dropped_wood_stick", 0, 98,160,220,100, room_drawer, "set hide dropped_wood_stick set item item_wood_stick set msg msg_item_wood_stick", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_trick_box", "bg_trick_box", 0, 0,0,430,320, room_trick_box, "", true, -1));
	objects->push_back(ObjectSprite::createObject("based_trick_box", "based_trick_box", 0, 30,40,356,257, room_trick_box, "", true, -1));
	objects->push_back(ObjectSprite::createObject("dropped_oharai_stick", "dropped_oharai_stick", 0, 52,150,295,112, room_trick_box, "set hide dropped_oharai_stick set item item_oharai_stick set msg msg_item_oharai_stick", true, -1));
	objects->push_back(ObjectSprite::createObject("closed_trick_box", "closed_trick_box", 0, 52,62,314,219, room_trick_box, "if isset number trick_box:3107 set hide closed_trick_box set hide number1 set hide number2 set hide number3 set hide number4 set hide set_closed_trick_box set show set_opened_trick_box set show opened_trick_box set msg msg_door_opened else set msg msg_door_closed end", true, -1));
	objects->push_back(ObjectSprite::createObject("number1", "number", 0, 108,180,44,52, room_trick_box, "set rotate number1", true, 10));
	objects->push_back(ObjectSprite::createObject("number2", "number", 1, 160,180,44,52, room_trick_box, "set rotate number2", true, 10));
	objects->push_back(ObjectSprite::createObject("number3", "number", 2, 214,180,44,52, room_trick_box, "set rotate number3", true, 10));
	objects->push_back(ObjectSprite::createObject("number4", "number", 3, 268,180,44,52, room_trick_box, "set rotate number4", true, 10));
	objects->push_back(ObjectSprite::createObject("opened_trick_box", "opened_trick_box", 0, 14,254,388,63, room_trick_box, "", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_shelf2", "bg_shelf2", 0, 0,0,430,320, room_shelf2, "", true, -1));
	objects->push_back(ObjectSprite::createObject("hint0", "hint0", 0, 109,196,80,50, room_shelf2, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("shelf_hole2", "shelf_hole2", 0, 177,56,180,100, room_shelf2, "", true, -1));
	objects->push_back(ObjectSprite::createObject("embed_hammer_head", "embed_hammer_head", 0, 195,65,150,70, room_shelf2, "set msg msg_embed_hammer_head", true, -1));
	objects->push_back(ObjectSprite::createObject("dropped_hammer_head", "dropped_hammer_head", 0, 197,202,150,70, room_shelf2, "set hide dropped_hammer_head set item item_hammer_head set msg msg_item_hammer_head", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_shelf", "bg_shelf", 0, 0,0,430,320, room_shelf, "", true, -1));
	objects->push_back(ObjectSprite::createObject("hint11", "hint11", 0, 229,196,80,50, room_shelf, "set msg msg_unknown_hint", true, -1));
	objects->push_back(ObjectSprite::createObject("shelf_hole", "shelf_hole", 0, 277,125,50,100, room_shelf, "if isset item item_wood_stick set delitem item_wood_stick set hide shelf_hole set show shelf_hole_with_stick set msg msg_put_wood_stick_in_shelf_hole else set msg msg_shelf_hole end", true, -1));
	objects->push_back(ObjectSprite::createObject("shelf_hole_with_stick", "shelf_hole_with_stick", 0, 277,125,50,100, room_shelf, "set item item_wood_stick set hide shelf_hole_with_stick set show shelf_hole set msg msg_pull_wood_stick_off_hole", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_circle_detail", "bg_circle_detail", 0, 0,0,430,320, room_circle_detail, "if isset item item_doll set delitem item_doll set show ghost set show laid_doll set msg msg_put_doll_on_circle else set msg msg_circle end", true, -1));
	objects->push_back(ObjectSprite::createObject("laid_doll", "lying_doll", 0, 140,71,155,175, room_circle_detail, "if isset item item_ohuda set delitem item_ohuda set show affixed_ohuda set show rect_doll set show dropped_key2 set msg msg_put_ohuda_on_laid_doll else set msg msg_item_doll end", false, -1));
	objects->push_back(ObjectSprite::createObject("affixed_ohuda", "affixed_ohuda", 0, 202,93,30,62, room_circle_detail, "set msg msg_affixed_ohuda", false, -1));
	objects->push_back(ObjectSprite::createObject("broken_doll", "broken_doll", 0, 18,11,400,300, room_circle_detail, "if isset item item_oharai_stick set show dropped_key set delitem item_oharai_stick set show ghost2 set msg msg_broken_doll else set msg msg_shocked end", false, -1));
	objects->push_back(ObjectSprite::createObject("bg_net", "bg_net", 0, 0,0,430,320, room_net, "", true, -1));
	objects->push_back(ObjectSprite::createObject("net_zoom", "net_zoom", 0, 15,108,325,215, room_net, "if isset item item_wood_stick set hide spider_net3 set hide net_zoom set show dropped_nail set msg msg_something_fell else set msg msg_cant_get_something_on_net end", true, -1));
	objects->push_back(ObjectSprite::createObject("detail_img_base", "detail_img_base", 0, 115,60,200,200, "", "", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_hammer_head", "detail_hammer_head", 0, 115,60,200,200, "", "if isset item item_wood_stick set delitem item_hammer_head set delitem item_wood_stick set item item_wood_hammer set msg msg_became_hammer else set msg msg_detail_hammer_head end", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_wood_stick", "detail_wood_stick", 0, 115,60,200,200, "", "set msg msg_item_wood_stick", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_wood_hammer", "detail_wood_hammer", 0, 115,60,200,200, "", "set msg msg_item_wood_hammer", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_nail", "detail_nail", 0, 115,60,200,200, "", "set msg msg_item_nail", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_doll_hand", "detail_doll_hand", 0, 115,60,200,200, "", "set msg msg_terrific_doll", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_straw_doll", "detail_straw_doll", 0, 115,60,200,200, "", "if isset item item_nail set msg msg_cannot_sting_nail else set msg msg_item_straw_doll end", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_defective_doll", "detail_defective_doll", 0, 115,60,200,200, "", "if isset item item_doll_hand set delitem item_doll_hand set delitem item_defective_doll set item item_doll set msg msg_put_hand_on_defective_doll else set msg msg_detail_defective_doll end", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_oharai_stick", "detail_oharai_stick", 0, 115,60,200,200, "", "set msg msg_item_oharai_stick", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_key", "detail_key", 0, 115,60,200,200, "", "set msg msg_item_key", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_key2", "detail_key", 0, 115,60,200,200, "", "set msg msg_item_key", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_rect_doll", "detail_rect_doll", 0, 115,60,200,200, "", "set msg msg_item_doll", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_doll", "detail_doll", 0, 115,60,200,200, "", "set msg msg_terrific_doll", false, -1));
	objects->push_back(ObjectSprite::createObject("detail_ohuda", "detail_ohuda", 0, 115,60,200,200, "", "set msg msg_item_ohuda", false, -1));
	objects->push_back(ObjectSprite::createObject("arrow_up", "Cursor1", 0, 185,6,60,30, "", "set arrow up", true, -1));
	objects->push_back(ObjectSprite::createObject("arrow_down", "Cursor1", 1, 185,287,60,30, "", "set arrow down", true, -1));
	objects->push_back(ObjectSprite::createObject("arrow_left", "Cursor2", 0, 2,127,30,60, "", "set arrow left", true, -1));
	objects->push_back(ObjectSprite::createObject("arrow_right", "Cursor2", 1, 388,127,30,60, "", "set arrow right", true, -1));
	objects->push_back(ObjectSprite::createObject("title", "title", 0, 0,0,480,320, "", "", false, -1));
	return objects;
}

std::map<std::string, std::array<std::string, 4>*>* createRoom()
{
	auto room = new std::map<std::string, std::array<std::string, 4>*>();
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_door", new std::array<std::string, 4>{{"null","null","room_scroll","room_wood"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_scroll", new std::array<std::string, 4>{{"null","null","room_circle","room_door"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_circle", new std::array<std::string, 4>{{"null","null","room_wood","room_scroll"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_wood", new std::array<std::string, 4>{{"null","null","room_door","room_circle"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_shelf", new std::array<std::string, 4>{{"null","room_scroll","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_shelf2", new std::array<std::string, 4>{{"null","room_scroll","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_trick_box", new std::array<std::string, 4>{{"null","room_scroll","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_mirror", new std::array<std::string, 4>{{"null","room_wood","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_drawer", new std::array<std::string, 4>{{"null","room_wood","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_scroll_detail", new std::array<std::string, 4>{{"null","room_scroll","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_circle_detail", new std::array<std::string, 4>{{"null","room_circle","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_net", new std::array<std::string, 4>{{"null","room_circle","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_north", new std::array<std::string, 4>{{"null","null","room_west","room_east"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_trick_box2", new std::array<std::string, 4>{{"null","room_north","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_scroll_north", new std::array<std::string, 4>{{"null","room_north","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_west", new std::array<std::string, 4>{{"null","null","room_south","room_north"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_circle_detail2", new std::array<std::string, 4>{{"null","room_west","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_south", new std::array<std::string, 4>{{"null","null","room_east","room_west"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_mirror2", new std::array<std::string, 4>{{"null","room_south","null","null"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_east", new std::array<std::string, 4>{{"null","null","room_north","room_south"}}));
	room->insert(std::pair<std::string, std::array<std::string, 4>*>("room_board", new std::array<std::string, 4>{{"null","room_south","null","null"}}));
	return room;
}

#endif
