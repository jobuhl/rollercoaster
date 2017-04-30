import java.util.ArrayList;

/**
 * Created by Jojo on 28.04.17.
 */
public class Warteschlange {

    private ArrayList<PersonenGruppe> singleschlange = new ArrayList<>();
    private ArrayList<PersonenGruppe> warteschlange = new ArrayList<>();
    private int wartelaenge=0;
    private int singlelaenge=0;
    private final int maxlaenge = 100;



    public void addPersons (PersonenGruppe gruppen) {
        warteschlange.add(gruppen);
        wartelaenge += gruppen.getGruppengroeße();
    }

    // Gruppe erst loeschen wenn alle Personen verteilt wurden
    public void removePersons () {
        wartelaenge -= warteschlange.get(0).getGruppengroeße();
        warteschlange.remove(0);
    }

    public void addSingle(PersonenGruppe personenGruppe){
        singleschlange.add(personenGruppe);
        singlelaenge += personenGruppe.getGruppengroeße();
    }

    public void removeSingle(){
        singlelaenge -= singleschlange.get(0).getGruppengroeße();
        singleschlange.remove(0);
    }


    public int getWartelaenge() {
        return wartelaenge;
    }

    public int getSinglelaenge() {
        return singlelaenge;
    }

    @Override
    public String toString() {
        return "Warteschlange{" +
                "warteschlangen=" + warteschlange +
                '}';
    }

    public PersonenGruppe getFirst(){

       PersonenGruppe first = this.warteschlange.get(0);
        return first;
    }

    public PersonenGruppe getTail(){

        PersonenGruppe tail = this.warteschlange.get(warteschlange.size()-1);

        System.out.println(tail);

        return tail;

    }



}
