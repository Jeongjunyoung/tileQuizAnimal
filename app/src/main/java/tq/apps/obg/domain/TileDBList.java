package tq.apps.obg.domain;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.activity.LoadingActivity;
import tq.apps.obg.activity.TQActivity;

/**
 * Created by d1jun on 2018-02-26.
 */

public class TileDBList extends Activity{
    public List<TileVO> getDBTileList() {
        List<TileVO> list = new ArrayList<>();
        LoadingActivity tq = ((LoadingActivity) LoadingActivity.mContext);
        list.add(new TileVO("flower", tq.getId("tile_flower")));
        list.add(new TileVO("panda", tq.getId("tile_panda")));
        list.add(new TileVO("star", tq.getId("tile_star")));
        list.add(new TileVO("cherry", tq.getId("tile_cherry")));
        list.add(new TileVO("apple", tq.getId("tile_apple")));
        list.add(new TileVO("grape", tq.getId("tile_grape")));
        list.add(new TileVO("kiwi", tq.getId("tile_kiwi")));
        list.add(new TileVO("peach", tq.getId("tile_peach")));
        list.add(new TileVO("pig", tq.getId("tile_pig")));
        list.add(new TileVO("pineapple", tq.getId("tile_pineapple")));
        list.add(new TileVO("strawberry", tq.getId("tile_strawberry")));
        list.add(new TileVO("ladybug", tq.getId("tile_ladybug")));
        list.add(new TileVO("shirt", tq.getId("tile_shirt")));
        list.add(new TileVO("bee", tq.getId("tile_bee")));
        list.add(new TileVO("tie", tq.getId("tile_tie")));
        list.add(new TileVO("support", tq.getId("tile_support")));
        list.add(new TileVO("fish", tq.getId("tile_fish")));
        list.add(new TileVO("diamond", tq.getId("tile_diamond")));
        list.add(new TileVO("beer", tq.getId("tile_beer")));
        list.add(new TileVO("book", tq.getId("tile_book")));
        list.add(new TileVO("castle", tq.getId("tile_castle")));
        list.add(new TileVO("chris", tq.getId("tile_chris")));
        list.add(new TileVO("clock", tq.getId("tile_clock")));
        list.add(new TileVO("coffee", tq.getId("tile_coffee")));
        list.add(new TileVO("coffee_cup", tq.getId("tile_coffee_cup")));
        list.add(new TileVO("crap", tq.getId("tile_crap")));
        list.add(new TileVO("drink", tq.getId("tile_drink")));
        list.add(new TileVO("fire", tq.getId("tile_fire")));
        list.add(new TileVO("forest", tq.getId("tile_forest")));
        list.add(new TileVO("ice", tq.getId("tile_ice")));
        list.add(new TileVO("rudol", tq.getId("tile_rudol")));
        list.add(new TileVO("straw", tq.getId("tile_straw")));
        list.add(new TileVO("summer", tq.getId("tile_summer")));
        list.add(new TileVO("summer_am", tq.getId("tile_summer_am")));
        list.add(new TileVO("summer_glass", tq.getId("tile_summer_glass")));
        list.add(new TileVO("summer_sun", tq.getId("tile_summer_sun")));
        list.add(new TileVO("sungle", tq.getId("tile_sungle")));
        list.add(new TileVO("tree", tq.getId("tile_tree")));
        list.add(new TileVO("watch", tq.getId("tile_watch")));
        list.add(new TileVO("water", tq.getId("tile_water")));
        return list;
    }
}
