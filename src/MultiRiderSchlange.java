import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jojo on 01.05.17.
 */
public class MultiRiderSchlange {

    private List<PersonenGruppe> warteschlange = Collections.synchronizedList(new ArrayList<>());

    private int wartelaenge = 0;

    public List<PersonenGruppe> getWarteschlange() {
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

    public boolean isEmpty(){

        boolean check = false;

        if (this.warteschlange.size() == 0){
            check = true;
        }

        return check;
    }

    @Override
    public String toString() {
        return "Warteschlange{" +
                "warteschlangen=" + warteschlange +
                '}';
    }


}
