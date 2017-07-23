import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jojo on 28.04.17.
 */

public class Rollercoaster {

    // laufzeitbeschärnkung
    private static long x = 20000;


    private static GUI gui;
    private static Thread t1;
    private static Thread t2;
    private static Thread t3;
    private static Thread t4;

    /* Für die Random Gruppengröße Erzeugung */
    public static Random r = new Random();
    public static final int min = 1;
    public static final int max_single = 1;
    public static final int max = 6;
    public static final int min_multi = 2;
    public static double time = 0;


    static final Statistik stat = new Statistik();

    public static final MultiRiderSchlange multiRiderSchlange = new MultiRiderSchlange();
    public static final SingleRiderSchlange singleRiderSchlange = new SingleRiderSchlange();
    public static final ArrayList<PersonenGruppe> personList = new ArrayList();
    public static final FutureEventList FUTURE_EVENT_LIST = new FutureEventList();

    public static final Zug zug = new Zug(4, 3, 300);
    public static final int kapazitaet = zug.getWaggons()*zug.getAnzahl_sitze();
    public static final SimulationsZeit sim1 = new SimulationsZeit();

    public static final Einteiler einteiler1 = new Einteiler(singleRiderSchlange, multiRiderSchlange, zug, sim1, FUTURE_EVENT_LIST, 100);

    public static GUI getGui() {
        return gui;
    }

    public static void main(String[] args) {

        gui = new GUI();

        gui.setVisible(true);

        for (int i = 0; i < 80; i++) {
                     // int ran = 3; // FOR SAME VALUE
            int ran = r.nextInt(max - min) + min;
            personList.add(new PersonenGruppe(ran));
            FUTURE_EVENT_LIST.add();

            gui.getSecond().addColumn(new String[]{Integer.toString(personList.get(i).getGroupSize()),
                    Integer.toString(FUTURE_EVENT_LIST.getArrivaltime().get(i)),
                    Integer.toString(FUTURE_EVENT_LIST.getEntrytime().get(i)),
                    Integer.toString(FUTURE_EVENT_LIST.getExittime().get(i))
            });

        }

        t1 = new Thread(new Runnable() {

            private void output() {
                int aktivWagon = 0;

                if (zug.getAktiv() > zug.getWaggons() - 1) {
                    aktivWagon = zug.getWaggons() - 1;
                } else {
                    aktivWagon = zug.getAktiv();

                }

                gui.getFirst().addColumn(new String[]{
                        Long.toString(sim1.getAnkunftszeit()),
                        Long.toString(sim1.getEinsteigezeit()),
                        Long.toString(sim1.getFahrtzeit()),
                        Long.toString(sim1.getAussteigezeit()),
                        zug.getStatus(),
                        Integer.toString(zug.getAktiv()),
                        Integer.toString(zug.getTakenSeats()[aktivWagon]),
                        Integer.toString(multiRiderSchlange.getWartelaenge()),
                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                        Long.toString(sim1.getSimZeit())});


            }

            //Ausgabe für erstes Element
            private void output1(int i) throws InterruptedException {

                sim1.setAnkunftszeit(sim1.getAnkunftszeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));
                sim1.setEinsteigezeit(sim1.getAnkunftszeit() + FUTURE_EVENT_LIST.getEntrytime().get(0));
                sim1.setSleeptimeArrival(FUTURE_EVENT_LIST.getArrivaltime().get(0));
                sim1.setSleeptimeEntry(FUTURE_EVENT_LIST.getEntrytime().get(0) + FUTURE_EVENT_LIST.getArrivaltime().get(0));

                output();


                FUTURE_EVENT_LIST.removeArrival();
                FUTURE_EVENT_LIST.removeEnty();
                personList.get(i).setGroupArrivalTime();
                multiRiderSchlange.addPersons(personList.get(i));

                t1.sleep(sim1.getSleeptimeArrival()); // syncro
            }

            //Ausgabe für i++ Elemente <100
            private void output2(int i) throws InterruptedException {


                sim1.setSimZeit(sim1.getAnkunftszeit());
                sim1.setAnkunftszeit(sim1.getSimZeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));
                sim1.setSleeptimeArrival(FUTURE_EVENT_LIST.getArrivaltime().get(0));

                output();

                sim1.setSimZeit(sim1.getAnkunftszeit());
                FUTURE_EVENT_LIST.removeArrival();
                personList.get(i).setGroupArrivalTime();
                multiRiderSchlange.addPersons(personList.get(i));
                Thread.sleep(sim1.getSleeptimeArrival()); // syncro
            }

            //Ausgabe für i++ Elemente >100
            private void output3(int i) throws InterruptedException {

                sim1.setSimZeit(sim1.getAnkunftszeit());
                sim1.setAnkunftszeit(sim1.getSimZeit() + FUTURE_EVENT_LIST.getArrivaltime().get(0));
                sim1.setSleeptimeArrival(FUTURE_EVENT_LIST.getArrivaltime().get(0));

                output();
                FUTURE_EVENT_LIST.removeArrival();
                personList.get(i).setGroupArrivalTime();
                singleRiderSchlange.addPersons(personList.get(i));
                Thread.sleep((FUTURE_EVENT_LIST.getArrivaltime().get(0)));
            }

            @Override
            public void run() {

                for (int i = 0; i < personList.size(); i++) {


                    try {
                        //unter hundert
                        if (multiRiderSchlange.getWartelaenge() <= 100) {
                            if (i == 0) {
                                output1(i);

                            } else {
                                output2(i);
                            }

                            // über 100
                        } else {
                            if (personList.get(i).getGroupSize() == 1) {
                                output3(i);

                            } else {
                                output2(i);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                sim1.setAnkunftszeit(0);

            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    einteiler1.fillTrain();
                }
            }
        });

        t3 = new Thread(new Runnable() {
            private int counter = 0;

            private double avg = 0;
            @Override
            public void run() {
                while (true){

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if (counter < stat.getPersGroupStat().size()) {

                        avg += ((double)stat.getPersGroupStat().get(counter).getGroupWaitingTime());
                        time = avg/(double)(stat.getPersGroupStat().size());

                        counter++;



                    }

                    //System.out.println(time);



                }
            }
        });


        t4 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    //System.out.println(sim1.getSimZeit());
                    if (sim1.getSimZeit() >= (long) x) {

                        System.out.println("FERTIG");
                        gui.getFirst().addColumn(new String[]{
                                Long.toString(0),
                                Long.toString(0),
                                Long.toString(0),
                                Long.toString(0),
                                "-",
                                Integer.toString(0),
                                Integer.toString(0),
                                Integer.toString(0),
                                Integer.toString(0),
                                Long.toString(x)});


                        double auslastungsqoute = (double)zug.getFahrgaeste()/(double)(kapazitaet*zug.getZugfahrt());
                        gui.getThird().addColumn(new String[]{
                                Integer.toString(zug.getFahrgaeste()),
                                Integer.toString(zug.getZugfahrt()),
                                Integer.toString(kapazitaet*zug.getZugfahrt()),
                                Double.toString(auslastungsqoute),
                                Double.toString(Math.round((time/1000)*100)/100)
                        });

                        t1.stop();
                        t2.stop();
                        t3.stop();
                        t4.stop();

                    }

                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();





    }
}
