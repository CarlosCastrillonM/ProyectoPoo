package umag.datos;

import umag.auth.Admin;
import umag.auth.Cliente;
import umag.auth.Empleado;
import umag.model.Guardable;
import umag.repo.Repositorios;
import umag.repo.SQLManager;
import umag.util.DeferredVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Proyecto implements Guardable {
    private int id;
    private String nombre;
    private String descripcion;
    private DeferredVariable<Cliente> cliente;
    private DeferredVariable<Admin> admin;
    private ArrayList<Empleado> empleados;

    private int id_cliente;
    private int id_admin;

    public Proyecto(int id, String nombre, String descripcion, Cliente cliente, Admin admin, ArrayList<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cliente = new DeferredVariable<>(() -> cliente);
        this.admin = new DeferredVariable<>(() -> admin);
        this.empleados = empleados;
    }

    public Proyecto() {
    }

    public int getId() {
        return id;
    }

    @Override
    public void loadRow(ResultSet rs) throws SQLException {
        id = rs.getInt("id_proyecto");
        nombre = rs.getString("nombre");
        descripcion = rs.getString("descripcion");
        id_cliente = rs.getInt("id_cliente");
        id_admin = rs.getInt("id_admin");
        cliente = new DeferredVariable<>(() -> Repositorios.CLIENTES.get(id_cliente));
        admin = new DeferredVariable<>(() -> Repositorios.ADMINISTRADORES.get(id_admin));
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Cliente getCliente() {
        return cliente.get();
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public void save() {
        SQLManager.executeUpdate("""
            UPDATE proyecto SET
                nombre = ?,
                descripcion = ?,
                id_cliente = ?
            WHERE id_proyecto = ?
            """, nombre, descripcion, cliente.get().getId(), id);
    }

    public Admin getAdmin() {
        return admin.get();
    }
}
