/**
 * Created by Jojo on 28.04.17.
 */
public class PersonenGruppe {

    private int groupSize;

    private long arrivaltime;
    private long staytime;

    public PersonenGruppe(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public long getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime() {
        this.arrivaltime = System.currentTimeMillis();
    }

    public long getStaytime() {
        return staytime;
    }

    public long setStaytime() {
        return this.staytime = System.currentTimeMillis() - this.arrivaltime;
    }

    @Override
    public String toString() {
        return "{" + groupSize + '}';
    }
}
