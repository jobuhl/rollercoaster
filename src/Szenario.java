import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jojo on 28.04.17.
 */


public class Szenario {

    public static void main(String[] args) {
        Random r = new Random();
        int min = 1;
        int max = 6;

        Zug zug1 = new Zug(6,3, 300);
        Warteschlange warteschlange = new Warteschlange();

        PersonenGruppe[] gruppen = new PersonenGruppe[50];

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


    }
}
