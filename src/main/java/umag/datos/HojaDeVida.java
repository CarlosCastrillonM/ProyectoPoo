package umag.datos;

import java.util.Date;
import java.util.List;

public class HojaDeVida {
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

    public HojaDeVida(int id, String nombre, String apellido, int cedula, Date fechaNacimiento, String direccion, int telefono, String correo, String profesion, List<Aptitud> aptitudes) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.profesion = profesion;
        this.aptitudes = aptitudes;
    }

    public int getId() {
        return id;
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
}
