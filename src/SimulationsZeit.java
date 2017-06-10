/**
 * Created by Jojo on 11.05.17.
 */
public class SimulationsZeit {

    private static long ankunftszeit = 0;
    private static long einsteigezeit = 0;
    private static long fahrtzeit = 0;

    private static long simZeit = 0;

    public static long getSimZeit() {
        return simZeit;
    }

    public static void setSimZeit(long simZeit) {
        SimulationsZeit.simZeit = simZeit;
    }

    public static long getAnkunftszeit() {
        return ankunftszeit;
    }

    public static void setAnkunftszeit(long ankunftszeit) {
        SimulationsZeit.ankunftszeit = ankunftszeit;
    }

    public static long getEinsteigezeit() {
        return einsteigezeit;
    }

    public static void setEinsteigezeit(long einsteigezeit) {
        SimulationsZeit.einsteigezeit = einsteigezeit;
    }

    public static long getFahrtzeit() {
        return fahrtzeit;
    }

    public static void setFahrtzeit(long fahrtzeit) {
        SimulationsZeit.fahrtzeit = fahrtzeit;
    }
}
