import java.util.ArrayList;
import java.util.Arrays;
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

    public static final MultiRiderSchlange multiRiderSchlange = new MultiRiderSchlange();
    public static final SingleRiderSchlange singleRiderSchlange = new SingleRiderSchlange();
    public static final ArrayList<PersonenGruppe> personList = new ArrayList();
    public static final FeatureEventList featureEventList = new FeatureEventList();

    public static final Zug zug = new Zug(4, 3, 300);
    public static final SimulationsZeit sim1 = new SimulationsZeit();

    public static final Einteiler einteiler1 = new Einteiler(singleRiderSchlange, multiRiderSchlange, zug, sim1,featureEventList);

    public static GUI getGui() {
        return gui;
    }

    public static void main(String[] args) {

        gui = new GUI();
        gui.setVisible(true);

        personList.add(new PersonenGruppe(5));
        personList.add(new PersonenGruppe(1));
        personList.add(new PersonenGruppe(3));
        personList.add(new PersonenGruppe(2));
        personList.add(new PersonenGruppe(1));
        personList.add(new PersonenGruppe(6));
        personList.add(new PersonenGruppe(4));
        personList.add(new PersonenGruppe(5));
        personList.add(new PersonenGruppe(3));
        personList.add(new PersonenGruppe(1));


        for (int i = 0; i < 10; i++) {
            //          int ran = 3; // FOR SAME VALUE
/*            int ran = r.nextInt(max - min) + min;
            personList.add(new PersonenGruppe(ran));*/
            featureEventList.add();

            gui.getSecond().addColumn(new String[]{Integer.toString(personList.get(i).getGroupSize()),
                                                   Integer.toString(featureEventList.getArrivaltime().get(i)),
                                                   Integer.toString(featureEventList.getEntrytime().get(i)),
                                                   Integer.toString(featureEventList.getExittime().get(i))
            });

        }

        Thread t1 = new Thread(new Runnable() {
            @Override


            public void run() {
                for (int i = 0; i < personList.size(); i++) {
                    if (multiRiderSchlange.getWartelaenge() <= 100) {

                        try {
                            if (i == 0) {
                                gui.getFirst().addColumn(new String[]{Integer.toString(
                                        featureEventList.getArrivaltime().get(0)),
                                        "-",
                                        "-",
                                        "-",
                                        zug.getStatus(),
                                        Integer.toString(zug.getAktiv()),
                                        Integer.toString(zug.getTakenSeats()[zug.getAktiv()]),
                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                                        Long.toString(sim1.getSimZeit())});

                                //System.out.println(featureEventList.getArrivaltime().get(0));
                                SimulationsZeit.setSimZeit(featureEventList.getArrivaltime().get(0));
                                Thread.sleep((featureEventList.getArrivaltime().get(0)));
                                featureEventList.removeArrival();
                                multiRiderSchlange.addPersons(personList.get(i));
                                //          System.out.println(multiRiderSchlange.toString());

                            }else {

                                int aktivWagon = 0;

                                if (zug.getAktiv() > zug.getWaggons()-1){
                                    aktivWagon = zug.getWaggons()-1;
                                }else{
                                    aktivWagon = zug.getAktiv();
                                }


                                gui.getFirst().addColumn(new String[]{
                                        Integer.toString(featureEventList.getArrivaltime().get(0)+ (int)sim1.getSimZeit()),
                                        "-",//Integer.toString(featureEventList.getEntrytime().get(0)),
                                        "-",
                                        "-",
                                        zug.getStatus(),
                                        Integer.toString(zug.getAktiv()),
                                        Integer.toString(zug.getTakenSeats()[aktivWagon]),
                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                                        Long.toString(sim1.getSimZeit())});

                                Thread.sleep((featureEventList.getArrivaltime().get(0)));
                                SimulationsZeit.setSimZeit(sim1.getSimZeit()+featureEventList.getArrivaltime().get(0));
                                featureEventList.removeArrival();
                                multiRiderSchlange.addPersons(personList.get(i));

                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }else{
                        if (personList.get(i).getGroupSize() == 1){
                            try {
                                Thread.sleep((featureEventList.getArrivaltime().get(0)));
                                gui.getFirst().addColumn(new String []{Integer.toString(featureEventList.getArrivaltime().get(0)),
                                        "-",
                                        "-",
                                        "-",
                                        zug.getStatus(),
                                        Integer.toString(zug.getAktiv()),
                                        Integer.toString(zug.getTakenSeats()[zug.getAktiv()]),
                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                                        Long.toString(sim1.getSimZeit())});
                                featureEventList.removeArrival();
                            singleRiderSchlange.addPersons(personList.get(i));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                Thread.sleep((featureEventList.getArrivaltime().get(0)));
                                gui.getFirst().addColumn(new String []{Integer.toString(featureEventList.getArrivaltime().get(0)),
                                        "-",
                                        "-",
                                        "-",
                                        zug.getStatus(),
                                        Integer.toString(zug.getAktiv()),
                                        Integer.toString(zug.getTakenSeats()[zug.getAktiv()]),
                                        Integer.toString(multiRiderSchlange.getWartelaenge()),
                                        Integer.toString(singleRiderSchlange.getWartelaenge()),
                                        Long.toString(sim1.getSimZeit())});
                                featureEventList.removeArrival();
                                multiRiderSchlange.addPersons(personList.get(i));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                System.out.println("Durchgang fertig");
                System.out.println("Simulationszeit = " + sim1.getSimZeit());
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
