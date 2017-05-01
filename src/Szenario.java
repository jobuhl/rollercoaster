import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jojo on 28.04.17.
 */


public class Szenario {

    /* Für die Random Gruppengröße Erzeugung */
    public static Random r = new Random();
    public static final int min = 1;
    public static final int max = 6;

    public static void main(String[] args) {



        PersonenGruppe[] gruppen = new PersonenGruppe[50]; // Anzahl Gruppen = 50
        for(int i = 0; i <= gruppen.length-1; i++) {
            int ran = r.nextInt(max-min) +min;
            gruppen[i] = new PersonenGruppe(ran);
        }


        MultiRiderSchlange multi = new MultiRiderSchlange();
        for(int i = 0; i <= gruppen.length-1; i++) {
            multi.addPersons(gruppen[i]);
        }

        System.out.println(multi.toString());

//        System.out.println(multi.getFirst());


        Zug zug1 = new Zug(10,3, 300); // Zug mit 10 Wagons, 3er-, 300sec Fahrtdauer
        System.out.println(zug1);
//        System.out.println(Arrays.toString(gruppen));

        System.out.println("Länge Warteschlange = " +multi.getWartelaenge());
//
//        System.out.println("Warteschlange Belegung");
//        System.out.println(multi.toString());
//
//        multi.removePersons();
//        multi.getTail();

    }
}
