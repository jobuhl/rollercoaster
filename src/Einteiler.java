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

            if (zug.getAktiv() <= zug.getWaggons()) { //aktiv kann bis auf 10 steigen und wenn takenSeats[10] dann Exception....

                if (zug.getStatus().equals("green") && this.status.equals("free")) {
                    System.out.println(multiRiderSchlange);
                    this.setStatusTaken();
                    fillFirstRun();
                }

                if (zug.getStatus().equals("yellow") && this.status.equals("free") && multiRiderSchlange.getWartelaenge() >= 100
                        && zug.isFreeSeats() == true && singleRiderSchlange.isEmpty() == false) {
                    this.setStatusTaken();
                    fillsingle();
                }

                if (zug.getStatus().equals("red")) {
                    zugfahrt();
                }

            }else {
                System.out.println("gute frage...");

            }
        }
    }

    private void zugfahrt() {
        Thread fahrt = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("Zug gleich wieder da");
                    Thread.sleep(3000);
                    System.out.println("Zug angekommen...");
                    zug.setAktivToZero();
                    zug.wagonsleeren(); // neue Methode um Wagons-Array mit 3er zu befüllen
                    zug.setStatusGreen();
                    setStatusFree();
                    fillTrain();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        fahrt.start();

    }

    private void fillFirstRun() {

        if (zug.getAktiv() <= zug.getWaggons()) {

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
                else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getRestFreeSeats()) {
                    if (zug.getTakenSeats()[zug.getAktiv()] <= 1) {
                        newDeploy();

                    } else if (multiRiderSchlange.getFirst().getGruppengroeße() < zug.getRestFreeSeats()) {

                        if ((multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]) %
                                zug.getAnzahl_sitze() == 0 || (multiRiderSchlange.getFirst().getGruppengroeße() -
                                zug.getTakenSeats()[zug.getAktiv()]) % zug.getAnzahl_sitze() == 2) {
                            restGroupDeploy();

                        } else if ((multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]) %
                                zug.getAnzahl_sitze() == 1) {

                            if (multiRiderSchlange.getFirst().getGruppengroeße() <= 3){
                                newDeploy();
                            }

                            else if (multiRiderSchlange.getFirst().getGruppengroeße() % (zug.getTakenSeats()[zug.getAktiv()]
                                    - 1) == 0 || multiRiderSchlange.getFirst().getGruppengroeße() % (zug.getTakenSeats()[zug.getAktiv()] - 1) == 2) {
                                groupFitafterDeploy();
                            }

                        }


                    }
                }

            }
        }
    }

    //Gruppe passt exakt in den Wagen
    private void groupFitInExakt() {

        multiRiderSchlange.removePersons();
        zug.setTakenSeats(0);
        this.setStatusFree();
        System.out.println("Freie Sitze Gesamt: " + zug.getRestFreeSeats());
        System.out.println(Arrays.toString(zug.getTakenSeats()));
        System.out.println("------------");
        zug.setAktiv();
        try {
            Thread.sleep(2000);
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void groupFitInLess() {
        int freeSeats = zug.getTakenSeats()[zug.getAktiv()] - multiRiderSchlange.getFirst().getGruppengroeße();
        System.out.println("Free: " + freeSeats);
        zug.setTakenSeats(freeSeats);
        System.out.println("Freie Sitze Gesamt: " + zug.getRestFreeSeats());
        System.out.println(Arrays.toString(zug.getTakenSeats()));
        System.out.println("------------");
        this.setStatusFree();
        multiRiderSchlange.removePersons();

        System.out.println(zug.getRestFreeSeats());
        System.out.println("------------");
        fillTrain();
//        try {
//            Thread.sleep(3000);
//            fillTrain();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private void trainReady() {
        if (multiRiderSchlange.getWartelaenge() >= 100){
            zug.setStatusYellow();
        }else{
            zug.setStatusRed();
        }

        this.setStatusFree();
        zug.setAktivToZero();
        System.out.println(zug.getAktiv());
        System.out.println(this.getStatus());
        System.out.println(zug.getStatus());
        System.out.println(multiRiderSchlange.getWartelaenge());
        fillTrain();
    }

    private void newDeploy() {
        zug.setFreeSeats(true);
        zug.setAktiv();
        this.setStatusFree();
        fillTrain();
    }

    private void restGroupDeploy() {
        multiRiderSchlange.getFirst().setGruppengroeße(multiRiderSchlange.getFirst().getGruppengroeße() - zug.getTakenSeats()[zug.getAktiv()]);
        zug.setTakenSeats(0);
        System.out.println("Freie Sitze Gesamt: " + zug.getRestFreeSeats());
        System.out.println(Arrays.toString(zug.getTakenSeats()));
        System.out.println("------------");
        zug.setAktiv();
        this.setStatusFree();
        fillTrain();
    }

    private void groupFitafterDeploy() {

        int val = multiRiderSchlange.getFirst().getGruppengroeße() - (zug.getTakenSeats()[zug.getAktiv()] - 1);
        zug.setTakenSeats(zug.getTakenSeats()[zug.getAktiv()] - val);
        multiRiderSchlange.getFirst().setGruppengroeße(val);
        System.out.println("Freie Sitze Gesamt: " + zug.getRestFreeSeats());
        System.out.println(Arrays.toString(zug.getTakenSeats()));
        System.out.println("------------");
        zug.setFreeSeats(true);
        zug.setAktiv();
        this.setStatusFree();
        fillTrain();
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
            zug.setStatusRed();
            fillTrain();
        }
    }
}



