import java.util.ArrayList;

/**
 * Created by Jojo on 01.05.17.
 */
public class SingleRiderSchlange {

    private ArrayList<PersonenGruppe> warteschlange = new ArrayList<>();
    private int wartelaenge = 0;



    public ArrayList<PersonenGruppe> getWarteschlange() {
        return warteschlange;
    }

    public void setWarteschlange(ArrayList<PersonenGruppe> warteschlange) {
        this.warteschlange = warteschlange;
    }

    public int getWartelaenge() {
        return wartelaenge;
    }

    public void setWartelaenge(int wartelaenge) {
        this.wartelaenge = wartelaenge;
    }

    public void addPersons(PersonenGruppe gruppen) {
        warteschlange.add(gruppen);
        wartelaenge += gruppen.getGruppengroeße();
    }

    public void removePersons () {
        wartelaenge -= warteschlange.get(0).getGruppengroeße();
        warteschlange.remove(0);
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

    @Override
    public String toString() {
        return "Warteschlange{" +
                "warteschlangen=" + warteschlange +
                '}';
    }


}