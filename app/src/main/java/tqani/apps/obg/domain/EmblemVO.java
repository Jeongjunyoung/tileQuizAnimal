package tqani.apps.obg.domain;

/**
 * Created by d1jun on 2018-02-28.
 */

public class EmblemVO {
    private int _id;
    private String e_name;
    private int e_res_id;
    private String e_league;
    private String e_kr_name;
    public EmblemVO(){}

    public EmblemVO(String e_name, int e_res_id, String e_league, String e_kr_name) {
        this.e_name = e_name;
        this.e_res_id = e_res_id;
        this.e_league = e_league;
        this.e_kr_name = e_kr_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getE_res_id() {
        return e_res_id;
    }

    public void setE_res_id(int e_res_id) {
        this.e_res_id = e_res_id;
    }

    public String getE_league() {
        return e_league;
    }

    public void setE_league(String e_league) {
        this.e_league = e_league;
    }

    public String getE_kr_name() {
        return e_kr_name;
    }

    public void setE_kr_name(String e_kr_name) {
        this.e_kr_name = e_kr_name;
    }
}
