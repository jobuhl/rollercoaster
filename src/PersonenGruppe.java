/**
 * Created by Jojo on 28.04.17.
 */
public class PersonenGruppe {

    private int gruppengroeße;

    private double ankunftszeit;
    private double ladezeit;
    private double ausstiegszeit;

    public PersonenGruppe(int gruppengroeße) {


        this.gruppengroeße = gruppengroeße;
        this.ankunftszeit = Math.random() * (10000-5000) + 5000;
        this.ladezeit = Math.random() * (5000-2000) + 2000;
        this.ausstiegszeit = Math.random() * (4000-2000) + 2000;
    }


    public int getGruppengroeße() {
        return gruppengroeße;
    }

    public void setGruppengroeße(int gruppengroeße) {
        this.gruppengroeße = gruppengroeße;
    }

    @Override
    public String toString() {
        return "{" + gruppengroeße + '}';
    }

    public double getAnkunftszeit() {
        return ankunftszeit;
    }

    public void setAnkunftszeit(double ankunftszeit) {
        this.ankunftszeit = ankunftszeit;
    }

    public double getLadezeit() {
        return ladezeit;
    }

    public void setLadezeit(double ladezeit) {
        this.ladezeit = ladezeit;
    }

    public double getAusstiegszeit() {
        return ausstiegszeit;
    }

    public void setAusstiegszeit(double ausstiegszeit) {
        this.ausstiegszeit = ausstiegszeit;
    }
}
