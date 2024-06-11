package umag.repo;

import umag.auth.Admin;
import umag.auth.Cliente;
import umag.auth.Empleado;
import umag.datos.Proyecto;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Proyectos extends AbstractRepositorio<Proyecto> {
    @Override
    public String getTableName() {
        return "proyecto";
    }

    @Override
    public Class<Proyecto> getEntityClass() {
        return Proyecto.class;
    }

    public CompletableFuture<Proyecto> crearProyecto(String nombre, String descripcion, Cliente cliente, Admin admin) {
        return SQLManager.executeQuery("""
            INSERT INTO proyecto (nombre, descripcion, id_cliente, id_admin) VALUES (?, ?, ?, ?) RETURNING id_proyecto""", nombre, descripcion, cliente.getId(), admin.getId())
                .thenApply(id -> {
                    try {
                        id.next();
                        return add(new Proyecto(id.getInt(1), nombre, descripcion, cliente, new ArrayList<>()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
