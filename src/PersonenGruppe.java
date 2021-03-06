/**
 * Created by Jojo on 28.04.17.
 */
public class PersonenGruppe {

    private int groupSize;
    private long groupArrivalTime;
    private long groupWaitingTime;

    public PersonenGruppe(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public long getGroupArrivalTime() {
        return groupArrivalTime;
    }

    public long getGroupWaitingTime() {
        return groupWaitingTime;
    }

    public void setGroupArrivalTime(){
        this.groupArrivalTime = System.currentTimeMillis();
    }

    public void setGroupWaitingTime() {

        this.groupWaitingTime = System.currentTimeMillis()-this.groupArrivalTime;
        //System.out.println(this.groupWaitingTime);
    }



    @Override
    public String toString() {
        return "{" + groupSize + '}';
    }
}
