import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jojo on 28.04.17.
 */

public class Rollercoaster {

    private static GUI gui;

    /* Für die Random Gruppengröße Erzeugung */
    public static Random r = new Random();
    public static final int min = 1;
    public static final int max_single = 1;
    public static final int max = 6;
    public static final int min_multi = 2;

    static final Statistik stat = new Statistik();

    public static final MultiRiderSchlange multiRiderSchlange = new MultiRiderSchlange();
    public static final SingleRiderSchlange singleRiderSchlange = new SingleRiderSchlange();
    public static final ArrayList<PersonenGruppe> personList = new ArrayList();
    public static final FutureEventList FUTURE_EVENT_LIST = new FutureEventList();

    public static final Zug zug = new Zug(4, 3, 300);
    public static final SimulationsZeit sim1 = new SimulationsZeit();

    public static final Einteiler einteiler1 = new Einteiler(singleRiderSchlange, multiRiderSchlange, zug, sim1, FUTURE_EVENT_LIST);

    public static GUI getGui() {
        return gui;
    }

    public static void main(String[] args) {

        gui = new GUI();
        gui.setVisible(true);

        for (int i = 0; i < 10; i++) {
            //          int ran = 3; // FOR SAME VALUE
            int ran = r.nextInt(max - min) + min;
            personList.add(new PersonenGruppe(ran));
            FUTURE_EVENT_LIST.add();

            gui.getSecond().addColumn(new String[]{Integer.toString(personList.get(i).getGroupSize()),
                    Integer.toString(FUTURE_EVENT_LIST.getArrivaltime().get(i)),
                    Integer.toString(FUTURE_EVENT_LIST.getEntrytime().get(i)),
                    Integer.toString(FUTURE_EVENT_LIST.getExittime().get(i))
            });

        }

        Thread t1 = new Thread(new Runnable() {


            private void output1(int i) throws InterruptedException {
                SimulationsZeit.setAnkunftszeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));
                SimulationsZeit.setEinsteigezeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getEntrytime().get(0));

                Thread.sleep((FUTURE_EVENT_LIST.getArrivaltime().get(0)));

                gui.getFirst().addColumn(new String[]{
                        Long.toString(SimulationsZeit.getAnkunftszeit()),
                        Long.toString(SimulationsZeit.getEinsteigezeit()),
                        "-",
                        "-",
                        zug.getStatus(),
                        Integer.toString(zug.getAktiv()),
                        Integer.toString(zug.getTakenSeats()[zug.getAktiv()]),
                        Integer.toString(multiRiderSchlange.getWartelaenge()),
                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                        Long.toString(sim1.getSimZeit())});

                SimulationsZeit.setSimZeit(FUTURE_EVENT_LIST.getArrivaltime().get(0));
                FUTURE_EVENT_LIST.removeArrival();
                personList.get(i).setGroupArrivalTime();
                multiRiderSchlange.addPersons(personList.get(i));

            }

            private void output2(int i) throws InterruptedException {
                int aktivWagon = 0;

                if (zug.getAktiv() > zug.getWaggons() - 1) {
                    aktivWagon = zug.getWaggons() - 1;
                } else {
                    aktivWagon = zug.getAktiv();

                }

                if (SimulationsZeit.getAnkunftszeit() <= SimulationsZeit.getEinsteigezeit()) {
                    SimulationsZeit.setAnkunftszeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));

                } else {
                    SimulationsZeit.setEinsteigezeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getEntrytime().get(0));
                }

                Thread.sleep((FUTURE_EVENT_LIST.getArrivaltime().get(0)));
                gui.getFirst().addColumn(new String[]{
                        Long.toString(SimulationsZeit.getAnkunftszeit()),
                        Long.toString(SimulationsZeit.getEinsteigezeit()),
                        "-",
                        "-",
                        zug.getStatus(),
                        "-",
                        "-",
                        "-",
                        "-",
//                                        Integer.toString(zug.getAktiv()),
//                                        Integer.toString(zug.getTakenSeats()[aktivWagon]),
//                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
//                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                        Long.toString(sim1.getSimZeit())});


                // SimulationsZeit.setSimZeit(sim1.getSimZeit()+ FUTURE_EVENT_LIST.getArrivaltime().get(0));

                if (SimulationsZeit.getAnkunftszeit() <= SimulationsZeit.getEinsteigezeit()) {
                    SimulationsZeit.setSimZeit(SimulationsZeit.getAnkunftszeit());

                } else {
                    SimulationsZeit.setSimZeit(SimulationsZeit.getEinsteigezeit());
                }

                FUTURE_EVENT_LIST.removeArrival();
                personList.get(i).setGroupArrivalTime();
                multiRiderSchlange.addPersons(personList.get(i));
            }

            private void output3(int i) throws InterruptedException {
                if (SimulationsZeit.getAnkunftszeit() <= SimulationsZeit.getEinsteigezeit()) {
                    SimulationsZeit.setAnkunftszeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));

                } else {
                    SimulationsZeit.setEinsteigezeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getEntrytime().get(0));
                }
                Thread.sleep((FUTURE_EVENT_LIST.getArrivaltime().get(0)));
                gui.getFirst().addColumn(new String[]{
                        Integer.toString(FUTURE_EVENT_LIST.getArrivaltime().get(0)),
                        "-",
                        "-",
                        "-",
                        zug.getStatus(),
                        "-",
                        "-",
                        "-",
                        "-",
//                                        Integer.toString(zug.getAktiv()),
//                                        Integer.toString(zug.getTakenSeats()[zug.getAktiv()]),
//                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
//                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                        Long.toString(sim1.getSimZeit())});


                FUTURE_EVENT_LIST.removeArrival();
                personList.get(i).setGroupArrivalTime();
                singleRiderSchlange.addPersons(personList.get(i));
            }

            private void output4(int i) throws InterruptedException {
                if (SimulationsZeit.getAnkunftszeit() <= SimulationsZeit.getEinsteigezeit()) {
                    SimulationsZeit.setAnkunftszeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));

                } else {
                    SimulationsZeit.setEinsteigezeit(SimulationsZeit.getAnkunftszeit() + FUTURE_EVENT_LIST.getEntrytime().get(0));
                }
                Thread.sleep((FUTURE_EVENT_LIST.getArrivaltime().get(0)));
                gui.getFirst().addColumn(new String[]{
                        Long.toString(SimulationsZeit.getAnkunftszeit()),
                        Long.toString(SimulationsZeit.getEinsteigezeit()),
                        "-",
                        "-",
                        zug.getStatus(),
                        "-",
                        "-",
                        "-",
                        "-",
//                                        Integer.toString(zug.getAktiv()),
//                                        Integer.toString(zug.getTakenSeats()[zug.getAktiv()]),
//                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
//                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                        Long.toString(sim1.getSimZeit())});
                FUTURE_EVENT_LIST.removeArrival();
                personList.get(i).setGroupArrivalTime();
                multiRiderSchlange.addPersons(personList.get(i));
            }

            @Override


            public void run() {
                for (int i = 0; i < personList.size(); i++) {
                    if (multiRiderSchlange.getWartelaenge() <= 100) {
                        if (i == 0) {

                            try {
                                output1(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        } else {

                            try {
                                output2(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    } else {
                        if (personList.get(i).getGroupSize() == 1) {

                            try {
                                output3(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{

                                try {
                                    output4(i);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    einteiler1.fillTrain();
                }
            }
        });
        t1.start();
        t2.start();


    }
}
