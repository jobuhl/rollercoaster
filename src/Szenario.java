import java.util.ArrayList;
import java.util.Arrays;
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
    public static final Zug zug = new Zug(4, 3, 300);

    public static final Einteiler einteiler1 = new Einteiler(singleRiderSchlange, multiRiderSchlange, zug);
    public static final ArrayList<PersonenGruppe> featureEventList = new ArrayList();


    public static void main(String[] args) {


        for (int i = 0; i < 2; i++) {
            int ran = r.nextInt(max - min) + min;
            //          int ran = 3;
            featureEventList.add(new PersonenGruppe(ran));
        }
//        multiRiderSchlange.addPersons(new PersonenGruppe(5));
//        multiRiderSchlange.addPersons(new PersonenGruppe(6));
//        multiRiderSchlange.addPersons(new PersonenGruppe(6));
//        multiRiderSchlange.addPersons(new PersonenGruppe(6));
//        multiRiderSchlange.addPersons(new PersonenGruppe(6));
//        multiRiderSchlange.addPersons(new PersonenGruppe(2));
//        multiRiderSchlange.addPersons(new PersonenGruppe(2));
//        multiRiderSchlange.addPersons(new PersonenGruppe(3));
//        multiRiderSchlange.addPersons(new PersonenGruppe(4));


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < featureEventList.size(); i++) {

                    if (multiRiderSchlange.getWartelaenge() <= 100) {
                        try {

                            Thread.sleep((long) featureEventList.get(i).getAnkunftszeit());
                            multiRiderSchlange.addPersons(featureEventList.get(i));
                            //          System.out.println(multiRiderSchlange.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{


                        if (featureEventList.get(i).getGruppengroeße() == 1){
                            try {
                            Thread.sleep((long) featureEventList.get(i).getAnkunftszeit());
                            singleRiderSchlange.addPersons(featureEventList.get(i));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                Thread.sleep((long) featureEventList.get(i).getAnkunftszeit());
                                multiRiderSchlange.addPersons(featureEventList.get(i));
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
