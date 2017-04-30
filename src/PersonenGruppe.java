/**
 * Created by Jojo on 28.04.17.
 */
public class PersonenGruppe {

    private int gruppengroeße;

    public PersonenGruppe(int gruppengroeße) {
        this.gruppengroeße = gruppengroeße;
    }

    public int getGruppengroeße() {
        return gruppengroeße;
    }

    public void setGruppengroeße(int gruppengroeße) {
        this.gruppengroeße = gruppengroeße;
    }

    @Override
    public String toString() {
        return "{"+gruppengroeße+'}';
    }
}
