package umag.repo;

import umag.datos.Aptitud;

import java.util.HashMap;
import java.util.Map;

public class Aptitudes {
    private static Map<Integer, Aptitud> aptitudMap = new HashMap<>();

    public static void addAptitud(Aptitud aptitud) {
        aptitudMap.put(aptitud.getId(), aptitud);
    }

    public static Aptitud getAptitud(int id) {
        return aptitudMap.get(id);
    }
}
