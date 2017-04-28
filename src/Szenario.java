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


        Zug zug1 = new Zug(10,3, 300); // Zug mit 10 Wagons, 3er-Sitz, 300sec Fahrtdauer
        Warteschlange warteschlange = new Warteschlange();
        PersonenGruppe[] gruppen = new PersonenGruppe[50]; // Anzahl Gruppen = 50


        for(int i = 0; i <= gruppen.length-1; i++) {
            int ran = r.nextInt(max-min) +min;
            gruppen[i] = new PersonenGruppe(ran);

        }

        for(int i = 0; i <= gruppen.length-1; i++) {
            warteschlange.addPersons(gruppen[i]);
        }



        System.out.println(zug1);
        System.out.println(Arrays.toString(gruppen));
        System.out.println("##############");
        System.out.println("Länge Warteschlange = " +warteschlange.getWartelaenge());

        System.out.println("#################");
        System.out.println("Warteschlange Belegung");
        System.out.println(warteschlange.toString());

        for(int i = 0; i <= 3; i++) {
            warteschlange.removePersons(gruppen[i]);
        }

        System.out.println("Länge Warteschlange = " +warteschlange.getWartelaenge());

        System.out.println("#################");
        System.out.println("Warteschlange Belegung");
        System.out.println(warteschlange.toString());

    }
}
