package tq.apps.obg.domain;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.activity.TQActivity;

/**
 * Created by d1jun on 2018-02-26.
 */

public class TileDBList extends Activity{
    public List<TileVO> getDBTileList() {
        List<TileVO> list = new ArrayList<>();
        FrontActivity tq = ((FrontActivity) FrontActivity.mContext);
        list.add(new TileVO("cluber", tq.getId("tile_cluber")));
        list.add(new TileVO("flower", tq.getId("tile_flower")));
        list.add(new TileVO("panda", tq.getId("tile_panda")));
        list.add(new TileVO("spades", tq.getId("tile_spades")));
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
        return list;
    }
}
