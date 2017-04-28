/**
 * Created by Jojo on 28.04.17.
 */
public class PersonenGruppe {

    private int Gruppengröße;

    public PersonenGruppe(int gruppengröße) {
        Gruppengröße = gruppengröße;
    }

    public int getGruppengröße() {
        return Gruppengröße;
    }

    public void setGruppengröße(int gruppengröße) {
        Gruppengröße = gruppengröße;
    }

    @Override
    public String toString() {
        return "PersonenGruppe{" +
                "Gruppengröße=" + Gruppengröße +
                '}';
    }
}
