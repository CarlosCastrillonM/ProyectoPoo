package umag.datos;

public class Aptitud {
    private int id;
    private String nombre;

    public Aptitud(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
