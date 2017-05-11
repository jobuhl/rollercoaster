import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jojo on 28.04.17.
 */

public class Szenario {

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

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            //          int ran = 3; // FOR SAME VALUE
            int ran = r.nextInt(max - min) + min;
            personList.add(new PersonenGruppe(ran));
            featureEventList.add();
        }



        Thread t1 = new Thread(new Runnable() {
            @Override


            public void run() {
                for (int i = 0; i < personList.size(); i++) {
                    if (multiRiderSchlange.getWartelaenge() <= 100) {
                        try {
                            Thread.sleep((featureEventList.getArrivaltime().get(0)));
                            featureEventList.removeArrival();
                            multiRiderSchlange.addPersons(personList.get(i));
                            //          System.out.println(multiRiderSchlange.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        if (personList.get(i).getGroupSize() == 1){
                            try {
                                Thread.sleep((featureEventList.getArrivaltime().get(0)));
                                featureEventList.removeArrival();
                            singleRiderSchlange.addPersons(personList.get(i));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                Thread.sleep((featureEventList.getArrivaltime().get(0)));
                                featureEventList.removeArrival();
                                multiRiderSchlange.addPersons(personList.get(i));
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
