import java.util.Random;

/**
 * Created by ozgurPC on 23.07.2017.
 */
public class testklasse {

    private static Random r = new Random();

    public static int bedienZeit(){
        double zahl = 50 + (r.nextGaussian()*(30));
        while(zahl < 0){
            zahl = 50 + (r.nextGaussian()*(30));
        }
        double x = zahl/60*1000;
        return (int)x;
    }

    public static void main(String[] args) {

        double lambda_norm =8;


/*        for (int i = 0; i < 20; i++) {
            double x = ((-1/lambda_norm) * Math.log(1 - Math.random())*10000);

            System.out.println(x);

        }*/

        for (int i = 0; i < 20; i++) {
            System.out.println(bedienZeit());
        }



    }

}
