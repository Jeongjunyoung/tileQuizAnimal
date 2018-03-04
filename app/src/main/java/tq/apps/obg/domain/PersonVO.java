package tq.apps.obg.domain;

/**
 * Created by d1jun on 2018-02-26.
 */

public class PersonVO {
    private int _id;
    private String p_name;
    private int p_res_id;
    private String p_job;
    private String p_kr_name;
    public PersonVO(){}
    public PersonVO(String p_name, int p_res_id, String p_job, String p_kr_name) {
        this.p_name = p_name;
        this.p_res_id = p_res_id;
        this.p_job = p_job;
        this.p_kr_name = p_kr_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_res_id() {
        return p_res_id;
    }

    public void setP_res_id(int p_res_id) {
        this.p_res_id = p_res_id;
    }

    public String getP_job() {
        return p_job;
    }

    public void setP_job(String p_job) {
        this.p_job = p_job;
    }

    public String getP_kr_name() {
        return p_kr_name;
    }

    public void setP_kr_name(String p_kr_name) {
        this.p_kr_name = p_kr_name;
    }
}
