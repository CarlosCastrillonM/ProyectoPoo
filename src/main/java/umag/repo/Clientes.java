package umag.repo;

import umag.auth.Cliente;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class Clientes extends AbstractRepositorio<Cliente> {
    @Override
    public String getTableName() {
        return "cliente JOIN cuenta ON cliente.id_cliente = cuenta.id_cuenta";
    }

    @Override
    public Class<Cliente> getEntityClass() {
        return Cliente.class;
    }

    public CompletableFuture<Cliente> crearCliente(String usuario, String correo, String nombre) {
        return SQLManager.executeQuery("""
                INSERT INTO cuenta (correo, usuario, tipo) VALUES (?, ?, ?) RETURNING id_cuenta""", correo, usuario, "cliente")
                .thenCompose(rs -> {
                    int id;
                    try {
                        rs.next();
                        id = rs.getInt(1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return SQLManager.executeUpdate("INSERT INTO cliente (id_cliente, nombre) VALUES (?, ?)", id, nombre)
                        .thenApply(v -> id);
                })
                .thenApply(id -> add(new Cliente(id, usuario, correo, nombre)));
    }
}
