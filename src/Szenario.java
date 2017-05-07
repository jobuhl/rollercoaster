import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jojo on 28.04.17.
 */

public class Szenario {

    /* Für die Random Gruppengröße Erzeugung */
    public static Random r = new Random();
    public static final int min = 1;
    public static final int max = 6;

    public static final MultiRiderSchlange multiRiderSchlange = new MultiRiderSchlange();
    public static final SingleRiderSchlange singleRiderSchlange = new SingleRiderSchlange();
    public static final Zug zug = new Zug(10, 3, 300);

    public static final Einteiler einteiler1 = new Einteiler(singleRiderSchlange, multiRiderSchlange, zug);
    public static final ArrayList<PersonenGruppe> featureEventList = new ArrayList();


    public static void main(String[] args) {


        for (int i = 0; i < 50; i++) {
            int ran = r.nextInt(max - min) + min;
            featureEventList.add(new PersonenGruppe(ran));
        }


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    for (int i = 0; i < featureEventList.size(); i++) {

                        try {
                            Thread.sleep((long)featureEventList.get(i).getAnkunftszeit());
                            multiRiderSchlange.addPersons(featureEventList.get(i));
                            System.out.println("hello");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    if (multiRiderSchlange.getWartelaenge() > 0)
                    einteiler1.fillTrain();
                }
            }
        });

        t1.start();
        t2.start();

    }
}
