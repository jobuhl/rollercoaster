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
    private int[] takenSeats;

    private boolean freeSeats = false;

    //fahrt dauer des Zuges
    private int fahrt_dauer; //in sekunden

    //Warteschlange für den Zug
    private MultiRiderSchlange warteschlange;

    private String status = "green";



    public Zug(int waggons, int anzahl_sitze, int fahrt_dauer) {
        this.waggons = waggons;
        this.anzahl_sitze = anzahl_sitze;
        this.fahrt_dauer = fahrt_dauer;
        this.takenSeats = new int[waggons];

        for (int i = 0; i < takenSeats.length; i++) {
            takenSeats[i] = anzahl_sitze;
        }
    }

    public int getWaggons() {
        return waggons;
    }

    public void wagonsleeren() {
        for (int i = 0; i < takenSeats.length; i++) {
            takenSeats[i] = anzahl_sitze;
        }

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

    public int[] getTakenSeats() {
        return takenSeats;
    }

    public void setTakenSeats(int value){
        takenSeats[aktiv] = value;
    }

    public int getAktiv() {
            return aktiv;
    }

    public void setAktiv() {
        if(aktiv < 9) {
            aktiv++;
        } else {

        }
    }

    public void setAktivToZero() {
        aktiv = 0;
    }

    public String getStatus() {
        return status;
    }

    //Auflade bereit
    public void setStatusGreen() {
        this.status = "green";
    }

    //Abfahrbereit
    public void setStatusYellow(){
        this.status = "yellow";
    }

    //fährt oder (eventuell) beschädigt
    public void setStatusRed(){
        this.status = "red";
    }

    public int getRestFreeSeats(){

        int counter = 0;
        for (int i = aktiv; i < takenSeats.length; i++) {
            counter = counter + takenSeats[i];
        }
        return counter;
    }

    public boolean isFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(boolean freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Override
    public String toString() {
        return "Zug{" +
                "waggons=" + waggons +
                ", anzahl_sitze=" + anzahl_sitze +
                ", takenSeats=" + Arrays.toString(takenSeats) +
                ", fahrt_dauer=" + fahrt_dauer +
                ", status=" + status +
                '}';
    }
}