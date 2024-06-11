package umag.datos;

import java.util.List;

public class Oferta {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Aptitud> aptitudes;

    public Oferta(int id, String nombre, String descripcion, List<Aptitud> aptitudes) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.aptitudes = aptitudes;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Aptitud> getAptitudes() {
        return aptitudes;
    }
}
