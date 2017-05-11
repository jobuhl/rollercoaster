/**
 * Created by Jojo on 28.04.17.
 */
public class PersonenGruppe {

    private int groupSize;

    public PersonenGruppe(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public String toString() {
        return "{" + groupSize + '}';
    }
}
