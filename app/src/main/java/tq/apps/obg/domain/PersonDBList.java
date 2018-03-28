package tq.apps.obg.domain;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.activity.LoadingActivity;
import tq.apps.obg.activity.TQActivity;

/**
 * Created by d1jun on 2018-02-26.
 */

public class PersonDBList {
    public List<PersonVO> getDBPersonList() {
        List<PersonVO> list = new ArrayList<>();
        LoadingActivity tq = ((LoadingActivity)LoadingActivity.mContext);
        list.add(new PersonVO("Lionel Messi", tq.getId("p_messi"), "fb"));
        list.add(new PersonVO("Philippe Coutinho", tq.getId("p_coutinho"), "fb"));
        list.add(new PersonVO("Sergio Aguero", tq.getId("p_aguero"), "fb"));
        list.add(new PersonVO("Andres Iniesta", tq.getId("p_iniesta"), "fb"));
        list.add(new PersonVO("Neymar", tq.getId("p_neymar"), "fb"));
        list.add(new PersonVO("Gerard Pique", tq.getId("p_pique"), "fb"));
        list.add(new PersonVO("Cesar Azpilicueta", tq.getId("p_azpilicueta"), "fb"));
        list.add(new PersonVO("Andreas Christensen", tq.getId("p_christensen"), "fb"));
        list.add(new PersonVO("N`Golo Kante", tq.getId("p_kante"), "fb"));
        list.add(new PersonVO("Cesc Fabregas", tq.getId("p_fabregas"), "fb"));
        list.add(new PersonVO("Mesut Ozil", tq.getId("p_ozil"), "fb"));
        list.add(new PersonVO("Alexis Sanchez", tq.getId("p_sanchez"), "fb"));
        list.add(new PersonVO("David De Gea", tq.getId("p_de_gea"), "fb"));
        list.add(new PersonVO("Juan Mata", tq.getId("p_mata"), "fb"));
        list.add(new PersonVO("Kevin De Bruyne", tq.getId("p_bruyne"), "fb"));
        list.add(new PersonVO("Thiago", tq.getId("p_thiago"), "fb"));
        list.add(new PersonVO("Isco", tq.getId("p_isco"), "fb"));
        list.add(new PersonVO("Julian Draxler", tq.getId("p_draxler"), "fb"));
        list.add(new PersonVO("Jan Vertonghen", tq.getId("p_vertonghen"), "fb"));
        list.add(new PersonVO("Gareth Bale", tq.getId("p_bale"), "fb"));
        list.add(new PersonVO("Dani Carvajal", tq.getId("p_carvajal"), "fb"));
        list.add(new PersonVO("Toni Kroos", tq.getId("p_kroos"), "fb"));
        list.add(new PersonVO("Keylor Navas", tq.getId("p_navas"), "fb"));
        list.add(new PersonVO("Son", tq.getId("p_son"), "fb"));
        list.add(new PersonVO("Thiago Silva", tq.getId("p_t_silva"), "fb"));
        list.add(new PersonVO("Manuel Neuer", tq.getId("p_neuer"), "fb"));
        list.add(new PersonVO("Mats Hummels", tq.getId("p_hummels"), "fb"));
        list.add(new PersonVO("Jerome Boateng", tq.getId("p_boateng"), "fb"));
        list.add(new PersonVO("Marco Reus", tq.getId("p_reus"), "fb"));
        list.add(new PersonVO("Samuel Umtiti", tq.getId("p_umtiti"), "fb"));
        list.add(new PersonVO("Antoine Griezmann", tq.getId("p_griezmann"), "fb"));
        list.add(new PersonVO("Arturo Vidal", tq.getId("p_vidal"), "fb"));
        list.add(new PersonVO("Luis Suarez", tq.getId("p_suarez"), "fb"));
        list.add(new PersonVO("Marc-Andre ter Stegen", tq.getId("p_stegen"), "fb"));
        list.add(new PersonVO("Kylian Mbappe", tq.getId("p_mbappe"), "fb"));
        list.add(new PersonVO("David Alaba", tq.getId("p_alaba"), "fb"));
        list.add(new PersonVO("Jordi Alba", tq.getId("p_alba"), "fb"));
        list.add(new PersonVO("Carles Alena", tq.getId("p_alena"), "fb"));
        list.add(new PersonVO("Marcos Alonso", tq.getId("p_alonso"), "fb"));
        list.add(new PersonVO("Andrea Barzagli", tq.getId("p_barzagli"), "fb"));
        list.add(new PersonVO("Medhi Benatia", tq.getId("p_benatia"), "fb"));
        list.add(new PersonVO("Karim Benzema", tq.getId("p_benzema"), "fb"));
        list.add(new PersonVO("Leonardo Bonucci", tq.getId("p_bonucci"), "fb"));
        list.add(new PersonVO("Gianluigi Buffon", tq.getId("p_buffon"), "fb"));
        list.add(new PersonVO("Sergio Busquets", tq.getId("p_busquets"), "fb"));
        list.add(new PersonVO("Gary Cahill", tq.getId("p_cahill"), "fb"));
        list.add(new PersonVO("Michael Carrick", tq.getId("p_carrick"), "fb"));
        list.add(new PersonVO("Petr Cech", tq.getId("p_cech"), "fb"));
        list.add(new PersonVO("Giorgio Chiellini", tq.getId("p_chiellini"), "fb"));
        list.add(new PersonVO("Angel Correa", tq.getId("p_correa"), "fb"));
        list.add(new PersonVO("Douglas Costa", tq.getId("p_costa"), "fb"));
        list.add(new PersonVO("Cristiano Ronaldo", tq.getId("p_cronaldo"), "fb"));
        list.add(new PersonVO("Danilo Luiz", tq.getId("p_danilo"), "fb"));
        list.add(new PersonVO("Denis Suarez", tq.getId("p_denis"), "fb"));
        list.add(new PersonVO("Paulo Dybala", tq.getId("p_dybala"), "fb"));
        list.add(new PersonVO("Fernandinho", tq.getId("p_fernandinho"), "fb"));
        list.add(new PersonVO("Filipe Luis", tq.getId("p_luiz"), "fb"));
        list.add(new PersonVO("Eden Hazard", tq.getId("p_hazard"), "fb"));
        list.add(new PersonVO("Gonzalo Higuain", tq.getId("p_higuain"), "fb"));
        list.add(new PersonVO("Gabriel Jesus", tq.getId("p_jesus"), "fb"));
        list.add(new PersonVO("Sami Khedira", tq.getId("p_khedira"), "fb"));
        list.add(new PersonVO("Joshua Kimmich", tq.getId("p_kimmich"), "fb"));
        list.add(new PersonVO("Vincent Kompany", tq.getId("p_kompany"), "fb"));
        list.add(new PersonVO("Laurent Koscielny", tq.getId("p_koscielny"), "fb"));
        list.add(new PersonVO("Philipp Lahm", tq.getId("p_lahm"), "fb"));
        list.add(new PersonVO("Erik Lamela", tq.getId("p_lamela"), "fb"));
        list.add(new PersonVO("Stephan Lichtsteiner", tq.getId("p_lichtsteiner"), "fb"));
        list.add(new PersonVO("David Luiz", tq.getId("p_luiz"), "fb"));
        list.add(new PersonVO("Marcelo", tq.getId("p_marcelo"), "fb"));
        list.add(new PersonVO("Claudio Marchisio", tq.getId("p_marchisio"), "fb"));
        list.add(new PersonVO("Marquinhos", tq.getId("p_marquinhos"), "fb"));
        list.add(new PersonVO("Javier Mascherano", tq.getId("p_mascherano"), "fb"));
        list.add(new PersonVO("Jeremy Mathieu", tq.getId("p_mathieu"), "fb"));
        list.add(new PersonVO("Per Mertesacker", tq.getId("p_mertesacker"), "fb"));
        list.add(new PersonVO("James Milner", tq.getId("p_milner"), "fb"));
        list.add(new PersonVO("Luka Modric", tq.getId("p_modric"), "fb"));
        list.add(new PersonVO("Martin Montoya", tq.getId("p_montoya"), "fb"));
        list.add(new PersonVO("Alvaro Morata", tq.getId("p_morata"), "fb"));
        list.add(new PersonVO("Thiago Motta", tq.getId("p_mota"), "fb"));
        list.add(new PersonVO("Paco Alcacer", tq.getId("p_paco"), "fb"));
        list.add(new PersonVO("Javier Pastore", tq.getId("p_pastore"), "fb"));
        list.add(new PersonVO("Paulinho", tq.getId("p_paulinho"), "fb"));
        list.add(new PersonVO("Pedro Rodriguez", tq.getId("p_pedro"), "fb"));
        list.add(new PersonVO("Miralem Pjanic", tq.getId("p_pjanic"), "fb"));
        list.add(new PersonVO("Rafinha", tq.getId("p_rafinha"), "fb"));
        list.add(new PersonVO("Ivan Rakitic", tq.getId("p_rakitic"), "fb"));
        list.add(new PersonVO("Sergio Ramos", tq.getId("p_ramos"), "fb"));
        list.add(new PersonVO("Marcus Rashford", tq.getId("p_rashford"), "fb"));
        list.add(new PersonVO("Franck Ribery", tq.getId("p_ribery"), "fb"));
        list.add(new PersonVO("Arjen Robben", tq.getId("p_robben"), "fb"));
        list.add(new PersonVO("Marcos Rojo", tq.getId("p_rojo"), "fb"));
        list.add(new PersonVO("Sergio Romero", tq.getId("p_romero"), "fb"));
        list.add(new PersonVO("Wayne Rooney", tq.getId("p_rooney"), "fb"));
        list.add(new PersonVO("Sergi Roberto", tq.getId("p_sergi"), "fb"));
        list.add(new PersonVO("David Silva", tq.getId("p_silva"), "fb"));
        list.add(new PersonVO("Sneijder", tq.getId("p_sneijder"), "fb"));
        list.add(new PersonVO("Carlos Tevez", tq.getId("p_tevez"), "fb"));
        list.add(new PersonVO("Arda Turan", tq.getId("p_turan"), "fb"));
        list.add(new PersonVO("Thomas Vermaelen", tq.getId("p_vermaelen"), "fb"));
        list.add(new PersonVO("Marco Verratti", tq.getId("p_verratti"), "fb"));

        return list;
    }
}
