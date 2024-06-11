package umag.datos;

import umag.auth.Cliente;
import umag.auth.Empleado;
import java.util.ArrayList;


public class Proyecto {
    private int id;
    private String nombre;
    private String descripcion;
    private Cliente cliente;
    private ArrayList<Empleado> empleados;

    public Proyecto(int id, String nombre, String descripcion, Cliente cliente, ArrayList<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.empleados = empleados;
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

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
