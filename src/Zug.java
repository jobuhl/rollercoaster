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
    private Warteschlange warteschlange;

    public Zug(int waggons, int anzahl_sitze, int fahrt_dauer, Warteschlange warteschlange) {
        this.waggons = waggons;
        this.anzahl_sitze = anzahl_sitze;
        this.fahrt_dauer = fahrt_dauer;
        this.wagon = new int[waggons];
        this.warteschlange = warteschlange;

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


    public void fillTrain() {

        //keine Singlerider Schlange
        if (warteschlange.getWartelaenge() <= 100) {


            if (warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] == 0 || warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] == 2) {

                fillTrainWithoutRest(warteschlange.getFirst());

            }
            if (warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] != 0 || warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] != 2) {

                fillTrainWithoutRestMod(warteschlange.getFirst());
            }
            // Singlerider Schlange
        } else if (warteschlange.getWartelaenge() > 100) {
            fillTrainWithRest(warteschlange.getFirst());

        }


    }

    //methode die bis zu 100 Menschen in der Warteschlange den Zugfüllt bei mod 0 und 2
    public void fillTrainWithoutRest(PersonenGruppe personenGruppe) {


        if (aktiv < waggons) {

            //Die Gruppe passt in einen Wagon
            if (personenGruppe.getGruppengroeße() <= wagon[aktiv]) {

                wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
                warteschlange.removePersons();
                fillTrain();


            } else if (personenGruppe.getGruppengroeße() > wagon[aktiv]) {

                if (wagon[aktiv] < 2) {
                    aktiv++;
                    fillTrainWithoutRest(personenGruppe);
                } else {
                    int differnez = personenGruppe.getGruppengroeße() - wagon[aktiv];
                    personenGruppe.setGruppengroeße(differnez);
                    wagon[aktiv] = 0;
                    aktiv++;
                    fillTrainWithoutRest(personenGruppe);
                }
            }

        } else if (aktiv == waggons) {
            if (personenGruppe.getGruppengroeße() <= wagon[aktiv]) {
                wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
                warteschlange.removePersons();
            } else {

                //Zug fährt zu

            }
        }
    }

    //methode die bis zu 100 Menschen in der Warteschlange den Zugfüllt bei mod = 1 (SingleRider oder Gruppenaufspaltung)
    public void fillTrainWithoutRestMod(PersonenGruppe personenGruppe) {

        if (personenGruppe.getGruppengroeße() % wagon[aktiv] == 0 && personenGruppe.getGruppengroeße() > wagon[aktiv] && aktiv != waggons ) {
            personenGruppe.setGruppengroeße(personenGruppe.getGruppengroeße() - aktiv);
            wagon[aktiv] = 0;
            aktiv++;
            fillTrain();

        }

        else if(personenGruppe.getGruppengroeße() % wagon[aktiv] == 1 && personenGruppe.getGruppengroeße() <= wagon[aktiv]) {
            wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
            warteschlange.removePersons();

        }else if(personenGruppe.getGruppengroeße() % wagon[aktiv] == 1 && personenGruppe.getGruppengroeße() >= wagon[aktiv] && aktiv != waggons){

            if (wagon[aktiv] == 1){
                aktiv++;
            }else if (wagon[aktiv] > 2){
                if (personenGruppe.getGruppengroeße()-2 % anzahl_sitze != 1){
                wagon[aktiv] = wagon[aktiv]-2;
                aktiv ++;
                }
            }

        }
    }

    public void fillTrainWithRest(PersonenGruppe personenGruppe) {

    }


    @Override
    public String toString() {
        return "Zug{" +
                "waggons=" + waggons +
                ", anzahl_sitze=" + anzahl_sitze +
                ", wagon=" + Arrays.toString(wagon) +
                ", fahrt_dauer=" + fahrt_dauer +
                '}';
    }
}