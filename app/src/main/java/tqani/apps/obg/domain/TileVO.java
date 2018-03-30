package tqani.apps.obg.domain;

/**
 * Created by d1jun on 2018-02-25.
 */

public class TileVO {
    private int _id;
    private String tile_name;
    private int tile_res_id;
    public TileVO(){}
    public TileVO(String tile_name, int tile_res_id) {
        this.tile_name = tile_name;
        this.tile_res_id = tile_res_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTile_name() {
        return tile_name;
    }

    public void setTile_name(String tile_name) {
        this.tile_name = tile_name;
    }

    public int gettile_res_id() {
        return tile_res_id;
    }

    public void setTile_res_id(int tile_resId) {
        this.tile_res_id = tile_resId;
    }
}
