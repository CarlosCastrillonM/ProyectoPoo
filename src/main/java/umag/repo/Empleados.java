package umag.repo;

import umag.auth.Empleado;
import umag.datos.HojaDeVida;

import java.util.concurrent.CompletableFuture;

public class Empleados extends AbstractRepositorio<Empleado> {
    @Override
    public String getTableName() {
        return "empleados " +
               "JOIN cuenta ON empleados.id_empleado = cuenta.id_cuenta " +
               "JOIN hoja_de_vida ON empleados.id_hoja_de_vida = hoja_de_vida.id_hoja_de_vida";
    }

    @Override
    public Class<Empleado> getEntityClass() {
        return Empleado.class;
    }

    public CompletableFuture<Empleado> crearEmpleado(String usuario, HojaDeVida hdv) {
        // ingresar el empleado en la tabla de cuenta y de empleado, pero asumir que la hoja de vida
        // ya fue ingresada en la tabla de hoja de vida previamente
        String correo = hdv.getCorreo();
        return SQLManager.executeQuery("""
                INSERT INTO cuenta (correo, usuario, tipo) VALUES (?, ?, ?) RETURNING id_cuenta""", correo, usuario, "empleado")
                .thenCompose(id -> SQLManager.executeQuery("INSERT INTO empleados (id_empleado, id_hoja_de_vida) VALUES (?, ?)", id, hdv.getId()).thenApply(v -> {
                    try {
                        id.next();
                        return id.getInt(1);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }))
                .thenApply(id -> new Empleado(id, usuario, correo, hdv));
    }
}
