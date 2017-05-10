import java.util.Arrays;

/**
 * sCreated by Jojo on 01.05.17.
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

        if (multiRiderSchlange.isEmpty() == false && !(zug.getStatus().equals("red"))) {

            if (zug.getAktiv() <= zug.getWaggons()-1) { //aktiv kann bis auf 10 steigen und wenn takenSeats[10] dann Exception....

                if (zug.getStatus().equals("green") && this.status.equals("free")) {
                   // System.out.println(multiRiderSchlange);
                     
                    fillFirstRun();
                }

                if (zug.getStatus().equals("yellow") && this.status.equals("free") && (multiRiderSchlange.getWartelaenge() >= 100 || !(singleRiderSchlange.isEmpty()))
                        && zug.isFreeSeats() == true && singleRiderSchlange.isEmpty() == false) {
                     
                    System.out.println("STARTE SINLERIDER");
                    fillsingle();
                }

            }else {

                if(singleRiderSchlange.getWartelaenge()>0) {
                    fillsingle();
                } else {
                    System.out.println("ZUG VOLL");
                    zugfahrt();

                }

            }
        } else {

            if (zug.getStatus().equals("red") == true){
                System.out.println("<<<<<<<<<<");
                System.out.println("test me if you can");
                System.out.println("<<<<<<<<<<");
                zugfahrt();
            }else{
                int freeSeats = zug.getRestFreeSeats();
                int seats = zug.getAnzahl_sitze() * zug.getWaggons();
                if (multiRiderSchlange.isEmpty() == true && freeSeats != seats){
                    try {
                        Thread.sleep(6000);
                        if (multiRiderSchlange.isEmpty() == true && freeSeats != seats){
                         zugfahrt();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(multiRiderSchlange.getWartelaenge());

                }

            }
        }
    }

    private void ausgabe() {
        System.out.println("Freie Sitze Gesamt: " + zug.getRestFreeSeats());
        System.out.println(Arrays.toString(zug.getTakenSeats()));
        System.out.println("Wartelänge Multi: " +multiRiderSchlange.getWartelaenge());
        System.out.println("Wartelänge Single: " +singleRiderSchlange.getWartelaenge());
        System.out.println("------------");

    }

    private void zugfahrt() {

                try {
                    System.out.println("Zug gleich wieder da");
                    Thread.sleep(3000);
                    System.out.println("Zug angekommen...");
                    zug.setAktivToZero();
                    zug.wagonsleeren(); // neue Methode um Wagons-Array mit 3er zu befüllen
                    zug.setStatusGreen();
                    setStatusFree();

                    try {
                        Thread.sleep(4000);
                        fillTrain();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }



    private void fillFirstRun() {

        if (zug.getAktiv() <= zug.getWaggons()-1) {

            //Gruppe passt Exakt in einen Waggon
            if (zug.getRestFreeSeats() >= multiRiderSchlange.getFirst().getGruppengroeße() && multiRiderSchlange.getFirst().getGruppengroeße() == zug.getTakenSeats()[zug.getAktiv()]) {
                groupFitInExakt();
            }

            // Gruppe passt in einen Wagon
            else if (zug.getRestFreeSeats() >= multiRiderSchlange.getFirst().getGruppengroeße() && multiRiderSchlange.getFirst().getGruppengroeße() < zug.getTakenSeats()[zug.getAktiv()]) {
                groupFitInLess();
            }

            // Gruppe passt nicht in einen Wagon
            else if (multiRiderSchlange.getFirst().getGruppengroeße() > zug.getTakenSeats()[zug.getAktiv()]) { // hier kann es zu gettakenseats[10] zum ArrayBound-Exception kommen...
                //Es gib weniger freie Sitze als benötigt
                if (multiRiderSchlange.getFirst().getGruppengroeße() > zug.getRestFreeSeats()) {
                    System.out.println(zug.getRestFreeSeats());
                    trainReady();
                }

                // es gibt genügend Sitze aber es bleibt ein einzelner übrig
                else if (multiRiderSchlange.getFirst().getGruppengroeße() <= zug.getRestFreeSeats()) {
                    if (zug.getTakenSeats()[zug.getAktiv()] <= 1) {
                        newDeploy();

                    }
                    else {

                        if ((multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]) %
                                zug.getAnzahl_sitze() == 0 || (multiRiderSchlange.getFirst().getGruppengroeße() -
                                zug.getTakenSeats()[zug.getAktiv()]) % zug.getAnzahl_sitze() == 2) {
                            restGroupDeploy();

                        } else if ((multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]) %
                                zug.getAnzahl_sitze() == 1) {

                            if (multiRiderSchlange.getFirst().getGruppengroeße() <= 3){
                                newDeploy();
                            }

                            if (multiRiderSchlange.getFirst().getGruppengroeße() % (zug.getTakenSeats()[zug.getAktiv()]
                                    - 1) == 0 || multiRiderSchlange.getFirst().getGruppengroeße() % (zug.getTakenSeats()[zug.getAktiv()] - 1) == 2) {
                                groupFitafterDeploy();
                            } else {
                                System.out.println("Hier könnte der Fehler sein 1");
                            }

                        }


                    }
                }

            }
        } else {
            System.out.println("Hier könnte der Fehler sein 2");
        }
    }

    //Gruppe passt exakt in den Wagen
    private void groupFitInExakt() {

        multiRiderSchlange.removePersons();
        zug.setTakenSeats(0);
        this.setStatusFree();
        ausgabe();
        zug.setAktiv();
        try {
            Thread.sleep(3000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void groupFitInLess() {
        int freeSeats = zug.getTakenSeats()[zug.getAktiv()] - multiRiderSchlange.getFirst().getGruppengroeße();
        zug.setTakenSeats(freeSeats);
        ausgabe();
        this.setStatusFree();
        multiRiderSchlange.removePersons();

        try {
            Thread.sleep(3000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void trainReady() {
        if (multiRiderSchlange.getWartelaenge() > 100 && !(singleRiderSchlange.isEmpty()) == true){
            zug.setStatusYellow();
        }else{
            zug.setStatusRed();
        }

        this.setStatusFree();
        zug.setAktivToZero();
        ausgabe();

        try {
            Thread.sleep(3000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void newDeploy() {
        zug.setFreeSeats(true);
        zug.setAktiv();
        this.setStatusFree();
        try {
            Thread.sleep(3000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void restGroupDeploy() {
        int setValue = multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()];
        multiRiderSchlange.setWartelaenge(multiRiderSchlange.getWartelaenge()-zug.getTakenSeats()[zug.getAktiv()]);
        multiRiderSchlange.getFirst().setGruppengroeße(setValue);
        zug.setTakenSeats(0);
        ausgabe();
        zug.setAktiv();
        this.setStatusFree();
        try {
            Thread.sleep(3000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void groupFitafterDeploy() {

        int val = multiRiderSchlange.getFirst().getGruppengroeße() - (zug.getTakenSeats()[zug.getAktiv()] - 1);
        multiRiderSchlange.setWartelaenge(multiRiderSchlange.getWartelaenge()-val);
        zug.setTakenSeats(zug.getTakenSeats()[zug.getAktiv()] - val);
        multiRiderSchlange.getFirst().setGruppengroeße(val);
        ausgabe();
        zug.setFreeSeats(true);
        zug.setAktiv();
        this.setStatusFree();
        try {
            Thread.sleep(3000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fillsingle() {

        if (zug.getAktiv() <= (zug.getWaggons()-1) && singleRiderSchlange.getWartelaenge() > 0) {

            //SingleRider passt genau in aktiven Wagon
            if (zug.getTakenSeats()[zug.getAktiv()] > 0) {
;
                zug.setTakenSeats(zug.getTakenSeats()[zug.getAktiv()]-singleRiderSchlange.getFirst().getGruppengroeße());
                singleRiderSchlange.removePersons();
                this.setStatusFree();
                ausgabe();
                try {
                    Thread.sleep(3000);
                    fillsingle();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                zug.setAktiv();
                this.setStatusFree();
                fillsingle();
            }

        } else {
            zug.setStatusRed();
            try {
                Thread.sleep(3000);
                fillTrain();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



