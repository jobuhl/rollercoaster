/**
 * Created by Jojo on 28.04.17.
 */
public class Zug {

    private int waggons;
    private int anzahl_sitze;
    private int[] sitz = new int[this.waggons];
    private int fahrt_dauer; //in sekunden

    public Zug(int waggons, int anzahl_sitze, int fahrt_dauer) {
        this.waggons = waggons;
        this.anzahl_sitze = anzahl_sitze;
        this.fahrt_dauer = fahrt_dauer;
    }

    public int getWaggons() {
        return waggons;
    }

    public void setWaggons(int waggons) {
        this.waggons = waggons;
    }

    public int getAnzahl_sitze() {
        return anzahl_sitze;
    }

    public void setAnzahl_sitze(int anzahl_sitze) {
        this.anzahl_sitze = anzahl_sitze;
    }

    public int[] getSitz() {
        return sitz;
    }

    public void setSitz(int[] sitz) {
        this.sitz = sitz;
    }
}
