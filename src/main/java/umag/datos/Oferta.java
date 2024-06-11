package umag.datos;

import umag.model.Guardable;
import umag.repo.SQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Oferta implements Guardable {
    private int id;
    private String nombre;
    private String descripcion;
    private String jornada;
    private String experiencia;
    private String lugarDeTrabajo;
    private double salario;
//    private List<Aptitud> aptitudes;

    public Oferta(int id, String nombre, String descripcion, String jornada, String experiencia, String lugarDeTrabajo, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.jornada = jornada;
        this.experiencia = experiencia;
        this.lugarDeTrabajo = lugarDeTrabajo;
        this.salario = salario;
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
        jornada = rs.getString("jornada");
        experiencia = rs.getString("experiencia");
        lugarDeTrabajo = rs.getString("lugar_de_trabajo");
        salario = rs.getDouble("salario");
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getJornada() {
        return jornada;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public double getSalario() {
        return salario;
    }

    //    public List<Aptitud> getAptitudes() {
//        return aptitudes;
//    }

    @Override
    public void save() {
        SQLManager.executeUpdate("""
            UPDATE oferta SET
                nombre = ?,
                descripcion = ?,
                jornada = ?,
                experiencia = ?,
                lugar_de_trabajo = ?,
                salario = ?
            WHERE id_oferta = ?
            """, nombre, descripcion, id);
    }
}
