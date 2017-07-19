import java.util.concurrent.atomic.AtomicLong;
/**
 * Created by Jojo on 11.05.17.
 */
public class SimulationsZeit {

    private static long ankunftszeit = 0;
    private static long einsteigezeit = 0;
    private static long fahrtzeit = 0;
    private static long aussteigezeit = 0;
    private static long sleeptimeArrival = 0;
    private static long sleeptimeEntry = 0;

    private static AtomicLong simZeit = new AtomicLong(0);

    public synchronized static long getSimZeit() {
        return simZeit.get();
    }

    public synchronized static void setSimZeit(long simZeit) {
        SimulationsZeit.simZeit.set(simZeit);
    }

    public synchronized static long getAnkunftszeit() {
        return ankunftszeit;
    }

    public synchronized static void setAnkunftszeit(long ankunftszeit) {
        SimulationsZeit.ankunftszeit = ankunftszeit;
    }

    public synchronized static long getEinsteigezeit() {
        return einsteigezeit;
    }

    public synchronized static void setEinsteigezeit(long einsteigezeit) {
        SimulationsZeit.einsteigezeit = einsteigezeit;
    }

    public synchronized static long getFahrtzeit() {
        return fahrtzeit;
    }

    public synchronized static void setFahrtzeit(long fahrtzeit) {
        SimulationsZeit.fahrtzeit = fahrtzeit;
    }

    public synchronized static long getAussteigezeit() {
        return aussteigezeit;
    }

    public synchronized static void setAussteigezeit(long aussteigezeit) {
        SimulationsZeit.aussteigezeit = aussteigezeit;
    }

    public static long getSleeptimeArrival() {
        return sleeptimeArrival;
    }

    public static void setSleeptimeArrival(long sleeptimeArrival) {
        SimulationsZeit.sleeptimeArrival = sleeptimeArrival;
    }

    public static long getSleeptimeEntry() {
        return sleeptimeEntry;
    }

    public static void setSleeptimeEntry(long sleeptimeEntry) {
        SimulationsZeit.sleeptimeEntry = sleeptimeEntry;
    }
}
