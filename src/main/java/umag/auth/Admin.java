package umag.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.intellij.lang.annotations.Language;
import umag.datos.Proyecto;
import umag.repo.Repositorios;

public class Admin extends Cuenta {

    public Admin(int id, String usuario, String correo) {
        super(id, usuario, "admin", correo);
    }

    public Admin() {
    }

    @Override
    protected void loadRowAttributes(ResultSet rs) throws SQLException {

    }

    @Override
    public @Language("postgresql") String[] getSQLInserts() {
        @Language ("postgresql")
        String admIns = "INSERT INTO admin (id_admin) VALUES (?) ON CONFLICT DO NOTHING";
        return new String[]{
            admIns
        };
    }

    @Override
    public Object[][] getInsertParams() {
        return new Object[][]{
            {getId()}
        };
    }

    public List<Proyecto> getProyectos() {
        return Repositorios.PROYECTOS.getAll()
            .stream().filter(p -> p.getAdmin().getId() == getId()).toList();
    }
}
