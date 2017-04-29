/**
 * Created by Jojo on 28.04.17.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Zug {
    private int waggons;
    private int anzahl_sitze;
    private int[] sitz;
    private int fahrt_dauer; //in sekunden
    private Warteschlange warteschlange;

//
    public Zug(int waggons, int anzahl_sitze, int fahrt_dauer, Warteschlange warteschlange) {
        this.waggons = waggons;
        this.anzahl_sitze = anzahl_sitze;
        this.fahrt_dauer = fahrt_dauer;
        this.sitz = new int[waggons];
        this.warteschlange = warteschlange;

        for (int i = 0; i < sitz.length ; i++) {
            sitz[i] = anzahl_sitze;

        }
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

    public void fillTrain(){

    }

    @Override
    public String toString() {
        return "Zug{" +
                "waggons=" + waggons +
                ", anzahl_sitze=" + anzahl_sitze +
                ", sitz=" + Arrays.toString(sitz) +
                ", fahrt_dauer=" + fahrt_dauer +
                '}';
    }
}
