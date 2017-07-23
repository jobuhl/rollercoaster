import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jojo on 11.05.17.
 */
public class FutureEventList {

    private double lambda_norm = 8;
    private static Random rando = new Random();

    public static int einsteigen(){
        double zahl = 50 + (rando.nextGaussian()*(30));
        while(zahl < 0){
            zahl = 50 + (rando.nextGaussian()*(30));
        }
        double x = zahl/60*1000;
        return (int)x;
    }

    public static int aussteigen(){
        double zahl = 80 + (rando.nextGaussian()*(20));
        while(zahl < 0){
            zahl = 80 + (rando.nextGaussian()*(20));
        }
        double x = zahl/60*1000;
        return (int)x;
    }

    private ArrayList<Integer> arrivaltime = new ArrayList<>();
    private ArrayList<Integer> entrytime = new ArrayList<>();
    private ArrayList<Integer> exittime = new ArrayList<>();


    public void add(){

        arrivaltime.add((int) ((-1/lambda_norm) * Math.log(1 - Math.random())*10000));
        //arrivaltime.add((int)(Math.random() * (2000-1000) + 1000));
        entrytime.add(einsteigen());
        //entrytime.add((int)(Math.random() * (5000-3000) + 3000));
        exittime.add(aussteigen());
       // exittime.add((int)(Math.random() * (4000-2000) + 2000));

    }

    public void removeArrival(){
        arrivaltime.remove(0);
    }

    public void removeEnty(){
        entrytime.remove(0);
    }

    public void removeExit(){
        exittime.remove(0);
    }

    public ArrayList<Integer> getArrivaltime() {
        return arrivaltime;
    }

    public ArrayList<Integer> getEntrytime() {
        return entrytime;
    }

    public ArrayList<Integer> getExittime() {
        return exittime;
    }
}