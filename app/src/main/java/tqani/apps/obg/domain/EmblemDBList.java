package tqani.apps.obg.domain;

import java.util.ArrayList;
import java.util.List;

import tqani.apps.obg.activity.LoadingActivity;

/**
 * Created by d1jun on 2018-02-28.
 */

public class EmblemDBList {
    public List<EmblemVO> getDBPersonList() {
        List<EmblemVO> list = new ArrayList<>();
        LoadingActivity tq = ((LoadingActivity)LoadingActivity.mContext);
        //Spain
        list.add(new EmblemVO("FC Barcelona", tq.getId("club_barca"), "spain", "FC바르셀로나"));
        list.add(new EmblemVO("Athletic Bilbao", tq.getId("club_athletic"), "spain", "아틀레틱 빌바오"));
        list.add(new EmblemVO("Atletico Madrid", tq.getId("club_atmadrid"), "spain", "아틀레티코 마드리드"));
        list.add(new EmblemVO("Real Betis", tq.getId("club_betis"), "spain", "레알 베티스"));
        list.add(new EmblemVO("Celta Vigo", tq.getId("club_celtavigo"), "spain", "셀타 비고"));
        list.add(new EmblemVO("Deportivo La Coruna", tq.getId("club_deportivo"), "spain", "데포르티보"));
        list.add(new EmblemVO("RCD Espanyol", tq.getId("club_espanol"), "spain", "RCD 에스파뇰"));
        list.add(new EmblemVO("Getafe CF", tq.getId("club_getafe"), "spain", "헤타페 CF"));
        list.add(new EmblemVO("CD Leganes", tq.getId("club_leganes"), "spain", "CD 레가네스"));
        list.add(new EmblemVO("Levante UD", tq.getId("club_levante"), "spain", "레반테 UD"));
        list.add(new EmblemVO("Malaga CF", tq.getId("club_malaga"), "spain", "말라가 CF"));
        list.add(new EmblemVO("CA Osasuna", tq.getId("club_osasuna"), "spain", "CA 오사수나"));
        list.add(new EmblemVO("Real Madrid CF", tq.getId("club_realmadrid"), "spain", "레알 마드리드 CF"));
        list.add(new EmblemVO("Real Sociedad", tq.getId("club_realsociedad"), "spain", "레알 소시에다드"));
        list.add(new EmblemVO("Valencia CF", tq.getId("club_valencia"), "spain", "발렌시아 CF"));
        list.add(new EmblemVO("Villarreal CF", tq.getId("club_villarreal"), "spain", "비야레알 CF"));

        //England
        list.add(new EmblemVO("Arsenal", tq.getId("club_arsenal"), "epl", "아스널 FC"));
        list.add(new EmblemVO("Aston Villa FC", tq.getId("club_avfc"), "epl", "아스턴 빌라 FC"));
        list.add(new EmblemVO("Blackburn Rovers FC", tq.getId("club_blackburn"), "epl", "블랙번 로버스 FC"));
        list.add(new EmblemVO("Brighton & Hove Albion FC", tq.getId("club_brighton"), "epl", "브라이턴 & 호브 앨비언 FC"));
        list.add(new EmblemVO("Burnley FC", tq.getId("club_burnley"), "epl", "번리 FC"));
        list.add(new EmblemVO("Chelsea FC", tq.getId("club_chelsea"), "epl", "첼시 FC"));
        list.add(new EmblemVO("Everton FC", tq.getId("club_everton"), "epl", "에버튼 FC"));
        list.add(new EmblemVO("Liverpool FC", tq.getId("club_liverpool"), "epl", "리버풀 FC"));
        list.add(new EmblemVO("Manchester City FC", tq.getId("club_mcity"), "epl", "맨체스터 시티 FC"));
        list.add(new EmblemVO("Manchester United FC", tq.getId("club_munited"), "epl", "맨체스터 유나이티드 FC"));
        list.add(new EmblemVO("Swansea City AFC", tq.getId("club_swansea"), "epl", "스완지 시티 FC"));
        list.add(new EmblemVO("Tottenham Hotspur FC ", tq.getId("club_totenham"), "epl", "토튼햄 핫스퍼 FC"));
        list.add(new EmblemVO("Watford FC", tq.getId("club_watford"), "epl", "왓퍼드 FC"));
        list.add(new EmblemVO("West Ham United FC", tq.getId("club_wetsham"), "epl", "웨스트햄 유나이티드 FC"));

        //리그 1, 네덜란드
        list.add(new EmblemVO("Olympique Lyonnais", tq.getId("club_lyon"), "league1", "올랭피크 리옹"));
        list.add(new EmblemVO("Olympique de Marseille", tq.getId("club_marseille"), "league1", "올랭피크 드 마르세유"));
        list.add(new EmblemVO("AS Monaco FC", tq.getId("club_monaco"), "league1", "AS 모나코 FC"));
        list.add(new EmblemVO("FC Nantes", tq.getId("club_nantes"), "league1", "FC 낭트"));
        list.add(new EmblemVO("Paris Saint-Germain FC", tq.getId("club_paris"), "league1", "파리 생제르맹 FC"));
        list.add(new EmblemVO("AFC Ajax", tq.getId("club_ajax"), "league1", "AFC 아약스"));
        list.add(new EmblemVO("AZ Alkmaar", tq.getId("club_alkmaar"), "league1", "AZ 알크마르"));
        list.add(new EmblemVO("PSV Eindhoven", tq.getId("club_psv"), "league1", "PSV 아인트호벤"));
        list.add(new EmblemVO("Olympiacos FC", tq.getId("club_olympiacos"), "league1", "올림피아코스 FC"));
        list.add(new EmblemVO("FC Shakhtar Donetsk", tq.getId("club_shakhtar"), "league1", "FC 샤흐타르 도네츠크"));

        //세리에 A, 포르투갈
        list.add(new EmblemVO("AC Milan", tq.getId("club_acmilan"), "serie", "AC 밀란"));
        list.add(new EmblemVO("Bologna FC", tq.getId("club_bologna"), "serie", "볼로냐 FC"));
        list.add(new EmblemVO("Cagliari Calcio", tq.getId("club_cagliari"), "serie", "칼리아리 칼초"));
        list.add(new EmblemVO("ACF Fiorentina", tq.getId("club_fiorentina"), "serie", "ACF 피오렌티나"));
        list.add(new EmblemVO("Genoa CFC", tq.getId("club_genoa"), "serie", "제노아 CFC"));
        list.add(new EmblemVO("FC Internazionale Milano", tq.getId("club_intermilan"), "serie", "FC 인테르나치오날레 밀라노"));
        list.add(new EmblemVO("Juventus FC", tq.getId("club_juventus"), "serie", "유벤투스 FC"));
        list.add(new EmblemVO("SS Lazio", tq.getId("club_lazio"), "serie", "SS 라치오"));
        list.add(new EmblemVO("AS Roma", tq.getId("club_roma"), "serie", "AS 로마"));
        list.add(new EmblemVO("Hellas Verona FC", tq.getId("club_verona"), "serie", "엘라스 베로나 FC"));
        list.add(new EmblemVO("SL Benfica", tq.getId("club_benfica"), "serie", "SL 벤피카"));
        list.add(new EmblemVO("FC Porto", tq.getId("club_porto"), "serie", "FC 포르투"));
        list.add(new EmblemVO("Sporting CP", tq.getId("club_sporting"), "serie", "스포르팅 CP"));

        //분데스 , 기타
        list.add(new EmblemVO("FC Bayern Munich", tq.getId("club_bayern"), "bundes", "FC 바이에른 뮌헨"));
        list.add(new EmblemVO("Werder Bremen", tq.getId("club_bremen"), "bundes", "SV 베르더 브레멘"));
        list.add(new EmblemVO("Borussia Dortmund", tq.getId("club_dortmund"), "bundes", "보루시아 도르트문트"));
        list.add(new EmblemVO("Hamburger SV", tq.getId("club_hamburg"), "bundes", "함부르크 SV"));
        list.add(new EmblemVO("Hannover 96", tq.getId("club_hannover"), "bundes", "하노버 96"));
        list.add(new EmblemVO("FC Köln", tq.getId("club_koln"), "bundes", "FC 쾰른"));
        list.add(new EmblemVO("Bayer Leverkusen", tq.getId("club_leverkusen"), "bundes", "바이어 04 레버쿠젠"));
        list.add(new EmblemVO("FC Schalke 04", tq.getId("club_schalke"), "bundes", "FC 샬케 04"));
        list.add(new EmblemVO("Apoel FC", tq.getId("club_apoel"), "bundes", "아포엘 FC"));
        list.add(new EmblemVO("FC Basel", tq.getId("club_basel"), "bundes", "FC 바젤"));
        list.add(new EmblemVO("Besiktas JK", tq.getId("club_besiktas"), "bundes", "베식타시 JK"));
        list.add(new EmblemVO("CSKA Moscow", tq.getId("club_moscu"), "bundes", "CSKA 모스크바"));

        return list;
    }
}
