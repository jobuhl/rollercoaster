import java.util.Arrays;

/**
 * sCreated by Jojo on 01.05.17.
 */
public class Einteiler {

    private SingleRiderSchlange singleRiderSchlange;
    private MultiRiderSchlange multiRiderSchlange;
    private Zug zug;
    private SimulationsZeit simulationsZeit;
    private FutureEventList futureEventList;


    public Einteiler(SingleRiderSchlange singleRiderSchlange, MultiRiderSchlange multiRiderSchlange,
                     Zug zug, SimulationsZeit simulationsZeit, FutureEventList futurEventList) {
        this.singleRiderSchlange = singleRiderSchlange;
        this.multiRiderSchlange = multiRiderSchlange;
        this.zug = zug;
        this.simulationsZeit = simulationsZeit;
        this.futureEventList = futurEventList;

    }


    public void fillTrain() {

        if (multiRiderSchlange.isEmpty() == false && !(zug.getStatus().equals("red"))) {

            if (zug.getAktiv() <= zug.getWaggons()-1) { //aktiv kann bis auf 10 steigen und wenn takenSeats[10] dann Exception....

                if (zug.getStatus().equals("green")) {


                    fillFirstRun();
                }

                if (zug.getStatus().equals("yellow") && (multiRiderSchlange.getWartelaenge() >= 100 || !(singleRiderSchlange.isEmpty()))
                        && zug.isFreeSeats() == true && singleRiderSchlange.isEmpty() == false) {
                     

                    fillsingle();
                }

            }else {

                if(singleRiderSchlange.getWartelaenge()>0) {
                    fillsingle();
                } else {
                    zug.setStatusRed();
                    zugfahrt();

                }

            }
        } else {

            if (zug.getStatus().equals("red") == true){
                zugfahrt();
            }else{
                int freeSeats = zug.getRestFreeSeats();
                int seats = zug.getAnzahl_sitze() * zug.getWaggons();
                if (multiRiderSchlange.isEmpty() == true && freeSeats != seats){
                    try {
                        Thread.sleep(6000);
                      simulationsZeit.setSimZeit( simulationsZeit.getSimZeit() + 6000);
                        if (multiRiderSchlange.isEmpty() == true){
                            zug.setStatusRed();


                         zugfahrt();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{

                }

            }
        }
    }

    private void fillFirstRun() {

        if (zug.getAktiv() <= zug.getWaggons()-1) {

            //Gruppe passt Exakt in einen Waggon
            if (zug.getRestFreeSeats() >= multiRiderSchlange.getFirst().getGroupSize() && multiRiderSchlange.getFirst().getGroupSize() == zug.getTakenSeats()[zug.getAktiv()]) {
                groupFitInExakt();
            }

            // Gruppe passt in einen Wagon
            else if (zug.getRestFreeSeats() >= multiRiderSchlange.getFirst().getGroupSize() && multiRiderSchlange.getFirst().getGroupSize() < zug.getTakenSeats()[zug.getAktiv()]) {
                groupFitInLess();
            }

            // Gruppe passt nicht in einen Wagon
            else if (multiRiderSchlange.getFirst().getGroupSize() > zug.getTakenSeats()[zug.getAktiv()]) { // hier kann es zu gettakenseats[10] zum ArrayBound-Exception kommen...
                //Es gib weniger freie Sitze als benötigt
                if (multiRiderSchlange.getFirst().getGroupSize() > zug.getRestFreeSeats()) {
                    trainReady();
                }

                // es gibt genügend Sitze aber es bleibt ein einzelner übrig
                else if (multiRiderSchlange.getFirst().getGroupSize() <= zug.getRestFreeSeats()) {
                    if (zug.getTakenSeats()[zug.getAktiv()] <= 1) {
                        newDeploy();

                    }
                    else {

                        if ((multiRiderSchlange.getFirst().getGroupSize() - zug.getTakenSeats()[zug.getAktiv()]) %
                                zug.getAnzahl_sitze() == 0 || (multiRiderSchlange.getFirst().getGroupSize() -
                                zug.getTakenSeats()[zug.getAktiv()]) % zug.getAnzahl_sitze() == 2) {
                            restGroupDeploy();

                        } else if ((multiRiderSchlange.getFirst().getGroupSize() - zug.getTakenSeats()[zug.getAktiv()]) %
                                zug.getAnzahl_sitze() == 1) {

                            if (multiRiderSchlange.getFirst().getGroupSize() <= 3){
                                newDeploy();
                            }

                            if (multiRiderSchlange.getFirst().getGroupSize() % (zug.getTakenSeats()[zug.getAktiv()]
                                    - 1) == 0 || multiRiderSchlange.getFirst().getGroupSize() % (zug.getTakenSeats()[zug.getAktiv()] - 1) == 2) {
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

    private void groupFitInExakt() {

        multiRiderSchlange.getFirst().setGroupWaitingTime();
        Rollercoaster.stat.addStat(multiRiderSchlange.getFirst());

        multiRiderSchlange.removePersons();
        zug.setTakenSeats(0);


        zug.setAktiv();
        sleeping();

    }

    private void groupFitInLess() {
        int freeSeats = zug.getTakenSeats()[zug.getAktiv()] - multiRiderSchlange.getFirst().getGroupSize();
        zug.setTakenSeats(freeSeats);

        multiRiderSchlange.getFirst().setGroupWaitingTime();
        Rollercoaster.stat.addStat(multiRiderSchlange.getFirst());

        multiRiderSchlange.removePersons();
        sleeping();
    }

    private void trainReady() {
        if (multiRiderSchlange.getWartelaenge() > 100 && !(singleRiderSchlange.isEmpty()) == true){
            zug.setStatusYellow();
        }else{
            zug.setStatusRed();
        }


        zug.setAktivToZero();

    }

    private void newDeploy() {
        zug.setFreeSeats(true);
        zug.setAktiv();
    }

    private void restGroupDeploy() {
        int setValue = multiRiderSchlange.getFirst().getGroupSize() - zug.getTakenSeats()[zug.getAktiv()];
        multiRiderSchlange.setWartelaenge(multiRiderSchlange.getWartelaenge()-zug.getTakenSeats()[zug.getAktiv()]);
        multiRiderSchlange.getFirst().setGroupSize(setValue);
        zug.setTakenSeats(0);

        zug.setAktiv();
        //keine Zeit, da Gruppe noch nicht vollständig dirn ist, wird unter fitin oder fitinless berechnet
    }

    private void groupFitafterDeploy() {

        int val = multiRiderSchlange.getFirst().getGroupSize() - (zug.getTakenSeats()[zug.getAktiv()] - 1);
        multiRiderSchlange.setWartelaenge(multiRiderSchlange.getWartelaenge()-val);
        zug.setTakenSeats(zug.getTakenSeats()[zug.getAktiv()] - val);
        multiRiderSchlange.getFirst().setGroupSize(val);
        zug.setFreeSeats(true);
        zug.setAktiv();
    }

    private void fillsingle() {

        if (zug.getAktiv() <= (zug.getWaggons()-1) && singleRiderSchlange.getWartelaenge() > 0) {

            //SingleRider passt genau in aktiven Wagon
            if (zug.getTakenSeats()[zug.getAktiv()] > 0) {
;
                zug.setTakenSeats(zug.getTakenSeats()[zug.getAktiv()]-singleRiderSchlange.getFirst().getGroupSize());
                multiRiderSchlange.getFirst().setGroupWaitingTime();
                Rollercoaster.stat.addStat(singleRiderSchlange.getFirst());
                singleRiderSchlange.removePersons();
                sleeping();
            } else {
                zug.setAktiv();
                fillsingle();
            }

        } else {
            zug.setStatusRed();
        }
    }


    private void output(){


            int aktivWagon = 0;
            if (zug.getAktiv() > zug.getWaggons()-1){
                aktivWagon = zug.getWaggons()-1;
            }else{
                aktivWagon = zug.getAktiv();
            }

        Rollercoaster.getGui().getFirst().addColumn(new String []{
                Long.toString(SimulationsZeit.getAnkunftszeit()),
                Long.toString(SimulationsZeit.getEinsteigezeit()),
                Long.toString(simulationsZeit.getFahrtzeit()),
                "-",
                zug.getStatus(),
                Integer.toString(zug.getAktiv()),
                Integer.toString(zug.getTakenSeats()[aktivWagon]),
                Integer.toString(multiRiderSchlange.getWartelaenge()),
                Integer.toString(singleRiderSchlange.getWartelaenge()),
                Long.toString(simulationsZeit.getSimZeit())});
    }

    private void zugfahrt() {

        zug.setStatusRed();
        try {

            int aktivWagon = 0;
            if (zug.getAktiv() > zug.getWaggons()-1){
                aktivWagon = zug.getWaggons()-1;
            }else{
                aktivWagon = zug.getAktiv();
            }




            Thread.sleep(5000);
            simulationsZeit.setEinsteigezeit(simulationsZeit.getEinsteigezeit()+5000);
            simulationsZeit.setFahrtzeit(simulationsZeit.getSimZeit() + 5000);


            output();
            zug.setAktivToZero();
            zug.wagonsleeren(); // neue Methode um Wagons-Array mit 3er zu befüllen

            Thread.sleep(futureEventList.getExittime().get(0));
            simulationsZeit.setSimZeit(simulationsZeit.getFahrtzeit());
            Rollercoaster.getGui().getFirst().addColumn(new String []{
                    Long.toString(SimulationsZeit.getAnkunftszeit()),
                    Long.toString(SimulationsZeit.getEinsteigezeit()),
                    Long.toString(simulationsZeit.getFahrtzeit()),
                    Long.toString(simulationsZeit.getSimZeit() + futureEventList.getExittime().get(0)),
                    zug.getStatus(),
                    Integer.toString(zug.getAktiv()),
                    Integer.toString(zug.getTakenSeats()[aktivWagon]),
                    Integer.toString(multiRiderSchlange.getWartelaenge()),
                    Integer.toString(singleRiderSchlange.getWartelaenge()),
                    Long.toString(simulationsZeit.getSimZeit())});


            simulationsZeit.setEinsteigezeit(simulationsZeit.getEinsteigezeit()+futureEventList.getExittime().get(0));
            simulationsZeit.setSimZeit( simulationsZeit.getSimZeit() + futureEventList.getExittime().get(0));
            futureEventList.removeExit();
            zug.setStatusGreen();

            output();



        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zug.setStatusGreen();

    }



    private void sleeping(){
        try {

            int aktivWagon = 0;
            if (zug.getAktiv() > zug.getWaggons()-1){
                aktivWagon = zug.getWaggons()-1;
            }else{
                aktivWagon = zug.getAktiv();
            }

            System.out.println(futureEventList.getEntrytime().get(0));
            Thread.sleep(futureEventList.getEntrytime().get(0));

            output();

            simulationsZeit.setSimZeit(SimulationsZeit.getEinsteigezeit());
            simulationsZeit.setEinsteigezeit(simulationsZeit.getEinsteigezeit()+futureEventList.getEntrytime().get(0));

            futureEventList.removeEnty();
            fillTrain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



