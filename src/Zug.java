/**
 * Created by Jojo on 28.04.17.
 */

import java.util.Arrays;

public class Zug {

    //Rahmen für Wagons mit Sitzplatzanzahl
    private int waggons;
    private int anzahl_sitze;

    //Aktiver Wagon
    private int aktiv = 0;

    //Sitzplätze des Zugs
    private int[] wagon;

    //fahrt dauer des Zuges
    private int fahrt_dauer; //in sekunden

    //Warteschlange für den Zug
    private MultiRiderSchlange warteschlange;

    private String status = "free";



    public Zug(int waggons, int anzahl_sitze, int fahrt_dauer) {
        this.waggons = waggons;
        this.anzahl_sitze = anzahl_sitze;
        this.fahrt_dauer = fahrt_dauer;
        this.wagon = new int[waggons];

        for (int i = 0; i < wagon.length; i++) {
            wagon[i] = anzahl_sitze;
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

    public int[] getWagon() {
        return wagon;
    }

    public void setWagon(int[] wagon) {
        this.wagon = wagon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusGreen() {
        this.status = "Green";
    }

    public void setStatusYellow(){
        this.status = "Yellow";
    }

    public void setStatusRed(){
        this.status = "Red";
    }


    @Override
    public String toString() {
        return "Zug{" +
                "waggons=" + waggons +
                ", anzahl_sitze=" + anzahl_sitze +
                ", wagon=" + Arrays.toString(wagon) +
                ", fahrt_dauer=" + fahrt_dauer +
                ", status=" + status +
                '}';
    }
}