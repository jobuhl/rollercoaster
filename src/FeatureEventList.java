import java.util.ArrayList;

/**
 * Created by Jojo on 11.05.17.
 */
public class FeatureEventList {

    private ArrayList<Integer> arrivaltime = new ArrayList<>();
    private ArrayList<Integer> entrytime = new ArrayList<>();
    private ArrayList<Integer> exittime = new ArrayList<>();

    public void add(){

        arrivaltime.add((int)(Math.random() * (2000-1000) + 1000));
        entrytime.add((int)Math.random() * (5000-3000) + 3000);
        exittime.add((int)Math.random() * (4000-2000) + 2000);

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