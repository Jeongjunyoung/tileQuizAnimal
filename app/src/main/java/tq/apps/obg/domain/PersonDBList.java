package tq.apps.obg.domain;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.activity.TQActivity;

/**
 * Created by d1jun on 2018-02-26.
 */

public class PersonDBList {
    public List<PersonVO> getDBPersonList() {
        List<PersonVO> list = new ArrayList<>();
        FrontActivity tq = ((FrontActivity)FrontActivity.mContext);
        list.add(new PersonVO("Lionel Messi", tq.getId("p_messi"), "fb", "리오넬 메시"));
        list.add(new PersonVO("Philippe Coutinho", tq.getId("p_coutinho"), "fb", "필리페 쿠티뉴"));
        list.add(new PersonVO("Sergio Aguero", tq.getId("p_aguero"), "fb", "세르히오 아구에로"));
        list.add(new PersonVO("Andres Iniesta", tq.getId("p_iniesta"), "fb", "안드레스 이니에스타"));
        list.add(new PersonVO("Neymar", tq.getId("p_neymar"), "fb", "네이마르"));
        list.add(new PersonVO("Gerard Pique", tq.getId("p_pique"), "fb", "헤라르드 피케"));
        return list;
    }
}
