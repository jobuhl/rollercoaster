/**
 * Created by Jojo on 01.05.17.
 */
public class Einteiler {

    private String status = "free";
    private SingleRiderSchlange singleRiderSchlange;
    private MultiRiderSchlange multiRiderSchlange;
    private Zug zug;

    public Einteiler(SingleRiderSchlange singleRiderSchlange, MultiRiderSchlange multiRiderSchlange, Zug zug) {
        this.singleRiderSchlange = singleRiderSchlange;
        this.multiRiderSchlange = multiRiderSchlange;
        this.zug = zug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusFree() {
        this.status = "free";
    }

    public void setStatusTaken() {
        this.status = "taken";
    }


    public void fillTrain() {

        //keine Singlerider Schlange
        if (zug.getStatus().equals("green") && this.status.equals("free") && multiRiderSchlange.getWartelaenge() <= 100) {

            this.setStatusTaken();

            if (multiRiderSchlange.getFirst().getGruppengroeße() % zug.getWagon()[0] == 0) {

            }


        }
    }
}
//            if (multiRiderSchlange.getFirst().getGruppengroeße() % zug.wagon[aktiv] == 0 || warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] == 2) {
//
//                fillTrainWithoutRest(warteschlange.getFirst());
//
//            }
//
//            if (warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] != 0 || warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] != 2) {
//
//                fillTrainWithoutRestMod(warteschlange.getFirst());
//            }
//            // Singlerider Schlange
//        } else if (warteschlange.getWartelaenge() > 100) {
//            if (warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] == 0 || warteschlange.getFirst().getGruppengroeße() % wagon[aktiv] == 2) {
//
//                fillTrainWithRest(warteschlange.getFirst());
//
//            }
//
//        }
//
//
//    }
//
//    //methode die bis zu 100 Menschen in der Warteschlange den Zugfüllt bei mod 0 und 2
//    public void fillTrainWithoutRest(PersonenGruppe personenGruppe) {
//
//
//        if (aktiv < waggons) {
//
//            //Die Gruppe passt in einen Wagon
//            if (personenGruppe.getGruppengroeße() <= wagon[aktiv]) {
//
//                wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
//                warteschlange.removePersons();
//                fillTrain();
//
//
//            } else if (personenGruppe.getGruppengroeße() > wagon[aktiv]) {
//
//                if (wagon[aktiv] < 2) {
//                    aktiv++;
//                    fillTrainWithoutRest(personenGruppe);
//                } else {
//                    int differnez = personenGruppe.getGruppengroeße() - wagon[aktiv];
//                    personenGruppe.setGruppengroeße(differnez);
//                    wagon[aktiv] = 0;
//                    aktiv++;
//                    fillTrainWithoutRest(personenGruppe);
//                }
//            }
//
//        } else if (aktiv == waggons) {
//            if (personenGruppe.getGruppengroeße() <= wagon[aktiv]) {
//                wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
//                warteschlange.removePersons();
//            } else {
//
//                //Zug fährt zu
//
//            }
//        }
//    }
//
//    //methode die bis zu 100 Menschen in der Warteschlange den Zugfüllt bei mod = 1 (SingleRiderSchlange oder Gruppenaufspaltung)
//    public void fillTrainWithoutRestMod(PersonenGruppe personenGruppe) {
//
//        if (personenGruppe.getGruppengroeße() % wagon[aktiv] == 0 && personenGruppe.getGruppengroeße() > wagon[aktiv] && aktiv != waggons ) {
//            personenGruppe.setGruppengroeße(personenGruppe.getGruppengroeße() - aktiv);
//            wagon[aktiv] = 0;
//            aktiv++;
//            fillTrain();
//
//        }
//
//        else if(personenGruppe.getGruppengroeße() % wagon[aktiv] == 1 && personenGruppe.getGruppengroeße() <= wagon[aktiv]) {
//            wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
//            warteschlange.removePersons();
//
//        }else if(personenGruppe.getGruppengroeße() % wagon[aktiv] == 1 && personenGruppe.getGruppengroeße() >= wagon[aktiv] && aktiv != waggons){
//
//            if (wagon[aktiv] == 1){
//                aktiv++;
//            }else if (wagon[aktiv] > 2){
//                if (personenGruppe.getGruppengroeße()-2 % anzahl_sitze != 1){
//                    wagon[aktiv] = wagon[aktiv]-2;
//                    aktiv ++;
//                }
//            }
//
//        }
//    }

//    public void fillTrainWithRest(PersonenGruppe personenGruppe) {
//
//        if (warteschlange.getWartelaenge() > 0 ){
//
//            if (wagon[aktiv] <= 1){
//                aktiv++;
//            }else {
//
//                if (personenGruppe.getGruppengroeße() <= wagon[aktiv]) {
//                    wagon[aktiv] = wagon[aktiv] - personenGruppe.getGruppengroeße();
//                    aktiv++;
//                    warteschlange.removePersons();
//                    fillTrain();
//                }
//
//                if (personenGruppe.getGruppengroeße() > wagon[aktiv]) {
//
//
//                }
//            }
//
//        }
//
//
//    }

