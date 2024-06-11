package umag.datos;

import umag.model.Guardable;
import umag.repo.SQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class HojaDeVida implements Guardable {
    private int id;
    private String nombre;
    String apellido;
    int cedula;
    private Date fechaNacimiento;
    private String direccion;
    private int telefono;
    private String correo;
    private String profesion;
    private List<Aptitud> aptitudes;

    public HojaDeVida(int id, String nombre, String apellido, int cedula, Date fechaNacimiento, String direccion, int telefono, String correo, String profesion/*, List<Aptitud> aptitudes*/) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.profesion = profesion;
//        this.aptitudes = aptitudes;
    }

    public HojaDeVida() {
    }

    public int getId() {
        return id;
    }

    @Override
    public void loadRow(ResultSet rs) throws SQLException {
        id = rs.getInt("hoja_de_vida.id_hoja_de_vida");
        nombre = rs.getString("hoja_de_vida.nombre");
        apellido = rs.getString("hoja_de_vida.apellido");
        cedula = rs.getInt("hoja_de_vida.cedula");
        fechaNacimiento = rs.getDate("hoja_de_vida.fecha_de_nacimiento");
        direccion = rs.getString("hoja_de_vida.direccion");
        telefono = rs.getInt("hoja_de_vida.telefono");
        correo = rs.getString("hoja_de_vida.correo");
        profesion = rs.getString("hoja_de_vida.profesion");
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public int getCedula() {
        return cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getProfesion() {
        return profesion;
    }

    public List<Aptitud> getAptitudes() {
        return aptitudes;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public void save() {
        SQLManager.executeUpdate("""
            UPDATE hoja_de_vida SET
                nombre = ?,
                apellido = ?,
                cedula = ?,
                fecha_de_nacimiento = ?,
                direccion = ?,
                telefono = ?,
                correo = ?,
                profesion = ?
            WHERE id_hoja_de_vida = ?
            """, nombre, apellido, cedula, fechaNacimiento, direccion, telefono, correo, profesion, id);
    }
}
