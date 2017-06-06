import java.util.ArrayList;

/**
 * Created by Jojo on 22.05.17.
 */
public class Statistik {

    private ArrayList<PersonenGruppe> persGroupStat = new ArrayList<>();

    public void addStat(PersonenGruppe personenGruppe){
        persGroupStat.add(personenGruppe);
    }

    public ArrayList<PersonenGruppe> getPersGroupStat() {
        return persGroupStat;
    }
}
