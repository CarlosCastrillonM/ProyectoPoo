package umag.repo;

import umag.auth.Admin;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class Administradores extends AbstractRepositorio<Admin> {
    @Override
    public String getTableName() {
        return "admin JOIN cuenta ON admin.id_admin = cuenta.id_cuenta";
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }

    public CompletableFuture<Admin> crearAdmin(String usuario, String correo) {
        return SQLManager.executeQuery("""
                INSERT INTO cuenta (correo, usuario, tipo) VALUES (?, ?, ?) RETURNING id_cuenta""", correo, usuario, "admin")
                .thenCompose(id -> SQLManager.executeQuery("INSERT INTO admin (id_admin) VALUES (?)", id).thenApply(v -> {
                    try {
                        id.next();
                        return id.getInt(1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .thenApply(id -> new Admin(id, usuario, correo));
    }
}
