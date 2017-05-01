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
        //keine Singlerider Schlange
        if (zug.getStatus().equals("green") && this.status.equals("free") && multiRiderSchlange.getWartelaenge() <= 100) {
            this.setStatusTaken();
            fillBelowHundretOne();
        } else  if(zug.getStatus().equals("green") && this.status.equals("free") && multiRiderSchlange.getWartelaenge() >= 100) {
            this.setStatusTaken();
            fillOverHundert();
        } else {

            zug.setStatusRed();
            //Zug fährt zu Methode
        }

    }

    private void fillBelowHundretOne() {

        if (zug.getAktiv() <= zug.getWaggons()) {

            //Gruppe passt Exakt in einen Waggon
            if (multiRiderSchlange.getFirst().getGruppengroeße() == zug.getTakenSeats()[zug.getAktiv()]) {
                multiRiderSchlange.removePersons();
                zug.setTakenSeats(0);
                zug.setAktiv();
                this.setStatusFree();
                fillTrain();
            }

            // Gruppe passt in einen Wagon
            else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getTakenSeats()[zug.getAktiv()]) {
                multiRiderSchlange.removePersons();
                int freeSeats = zug.getTakenSeats()[zug.getAktiv()] - multiRiderSchlange.getFirst().getGruppengroeße();
                zug.setTakenSeats(freeSeats);
                this.setStatusFree();
                fillTrain();
            }

            // Gruppe passt nicht in einen Wagon
            else if (multiRiderSchlange.getFirst().getGruppengroeße() > zug.getTakenSeats()[zug.getAktiv()]) {
                //Es gib weniger freie Sitze als benötigt
                if (multiRiderSchlange.getFirst().getGruppengroeße() > zug.getRestFreeSeats()) {
                    zug.setStatusYellow();
                    fillTrain();
                }

                // es gibt genügend Sitze aber es bleibt ein einzelner übrig
                else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getRestFreeSeats()) {
                    if (zug.getTakenSeats()[zug.getAktiv()] <= 1) {
                        zug.setAktiv();
                        this.setStatusFree();
                        fillTrain();

                    } else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getRestFreeSeats()) {

                        if (multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()] %
                                zug.getAnzahl_sitze() == 0 || multiRiderSchlange.getFirst().getGruppengroeße() -
                                zug.getTakenSeats()[zug.getAktiv()] % zug.getAnzahl_sitze() == 2) {

                            zug.setTakenSeats(0);
                            multiRiderSchlange.getFirst().setGruppengroeße(multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]);
                            zug.setAktiv();
                            this.setStatusFree();
                            fillTrain();
                        } else if (multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()] %
                                zug.getAnzahl_sitze() == 1) {

                            if (multiRiderSchlange.getFirst().getGruppengroeße() % zug.getTakenSeats()[zug.getAktiv()]
                                    - 1 == 0 || multiRiderSchlange.getFirst().getGruppengroeße() % zug.getTakenSeats()[zug.getAktiv()] - 1 == 2){
                                multiRiderSchlange.getFirst().setGruppengroeße(multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]-1);
                                zug.setAktiv();
                                this.setStatusFree();
                                fillTrain();
                            }

                        }


                    }
                }

            }
        }
    }

    private void fillOverHundert() {

        if (zug.getAktiv() <= zug.getWaggons()) {

            //Gruppe passt Exakt in einen Waggon
            if (multiRiderSchlange.getFirst().getGruppengroeße() == zug.getTakenSeats()[zug.getAktiv()]) {
                multiRiderSchlange.removePersons();
                zug.setTakenSeats(0);
                zug.setAktiv();
                this.setStatusFree();
                fillTrain();
            }

            // Gruppe passt in einen Wagon
            else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getTakenSeats()[zug.getAktiv()]) {
                multiRiderSchlange.removePersons();
                int freeSeats = zug.getAktiv() - multiRiderSchlange.getFirst().getGruppengroeße();
                zug.setTakenSeats(freeSeats);
                this.setStatusFree();
                fillTrain();
            }

            // Gruppe passt nicht in einen Wagon
            else if (multiRiderSchlange.getFirst().getGruppengroeße() > zug.getTakenSeats()[zug.getAktiv()]) {
                //Es gib weniger freie Sitze als benötigt
                if (multiRiderSchlange.getFirst().getGruppengroeße() > zug.getRestFreeSeats()) {
                    zug.setStatusYellow();
                    fillTrain();
                }

                // es gibt genügend Sitze aber es bleibt ein einzelner übrig
                else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getRestFreeSeats()) {
                    if (zug.getTakenSeats()[zug.getAktiv()] <= 1) {
                        zug.setAktiv();
                        this.setStatusFree();
                        fillTrain();
                    }
                    else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getRestFreeSeats()) {


//                        if ()//Ausgelagerte Methode muss hier hin


                            multiRiderSchlange.getFirst().setGruppengroeße(multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]);
                        zug.setAktiv();
                        this.setStatusFree();
                        fillTrain();
                    }

                }
                //Nach dem die Wagons mit den Gruppen zugeilt wurden und die Warteschlangelänge über 100 ist, können wir via Singlerider die freien Sitze auffüllen.
                fillsingle();
            }
        }
    }


    private void fillsingle() {

        if (zug.getAktiv() <= zug.getWaggons() && singleRiderSchlange.getWartelaenge() > 0 && zug.getRestFreeSeats() > 0) {

            //SingleRider passt genau in aktiven Wagon
            if (singleRiderSchlange.getFirst().getGruppengroeße() == zug.getTakenSeats()[zug.getAktiv()]) {
                singleRiderSchlange.removePersons();
                zug.setTakenSeats(0);
                zug.setAktiv();
                this.setStatusFree();
                fillsingle();
            } else {
                zug.setAktiv();
                fillsingle();
            }

        } else {
            zug.setStatusYellow();
            fillTrain();
        }
    }
}



