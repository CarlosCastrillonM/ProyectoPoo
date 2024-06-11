package umag.datos;

import umag.model.Guardable;
import umag.repo.SQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Oferta implements Guardable {
    private int id;
    private String nombre;
    private String descripcion;
//    private List<Aptitud> aptitudes;

    public Oferta(int id, String nombre, String descripcion/*, List<Aptitud> aptitudes*/) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
//        this.aptitudes = aptitudes;
    }

    public Oferta() {
    }

    public int getId() {
        return id;
    }

    @Override
    public void loadRow(ResultSet rs) throws SQLException {
        id = rs.getInt("id_oferta");
        nombre = rs.getString("nombre");
        descripcion = rs.getString("descripcion");
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

//    public List<Aptitud> getAptitudes() {
//        return aptitudes;
//    }

    @Override
    public void save() {
        SQLManager.executeUpdate("""
            UPDATE oferta SET
                nombre = ?,
                descripcion = ?
            WHERE id_oferta = ?
            """, nombre, descripcion, id);
    }
}
