package tq.apps.obg.domain;

/**
 * Created by d1jun on 2018-03-18.
 */

public class UserVO {
    private String hint_num;
    private String player_score;
    private String emblem_score;

    public UserVO(String hint_num, String player_score, String emblem_score) {
        this.hint_num = hint_num;
        this.player_score = player_score;
        this.emblem_score = emblem_score;
    }

    public String getHint_num() {
        return hint_num;
    }

    public void setHint_num(String hint_num) {
        this.hint_num = hint_num;
    }

    public String getPlayer_score() {
        return player_score;
    }

    public void setPlayer_score(String player_score) {
        this.player_score = player_score;
    }

    public String getEmblem_score() {
        return emblem_score;
    }

    public void setEmblem_score(String emblem_score) {
        this.emblem_score = emblem_score;
    }
}
