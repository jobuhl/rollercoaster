import java.util.ArrayList;

/**
 * Created by Jojo on 28.04.17.
 */
public class Warteschlange {

    private ArrayList<PersonenGruppe> warteschlangen = new ArrayList<>();
    private int wartelaenge;
    private final int maxlaenge = 100;



    public void addPersons (PersonenGruppe gruppen) {
        warteschlangen.add(gruppen);
        wartelaenge += gruppen.getGruppengröße();
    }

    // Gruppe erst löschen wenn alle Personen verteilt wurden
    public void removePersons (PersonenGruppe gruppen) {
        warteschlangen.remove(gruppen);
        wartelaenge -= gruppen.getGruppengröße();
    }

    public int getWartelaenge() {
        return wartelaenge;
    }

    @Override
    public String toString() {
        return "Warteschlange{" +
                "warteschlangen=" + warteschlangen +
                '}';
    }



}
