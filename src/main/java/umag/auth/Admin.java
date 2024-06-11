package umag.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.intellij.lang.annotations.Language;
import umag.datos.Proyecto;

public class Admin extends Cuenta {

    // La lista se popula con base a los proyectos que se están cargando
    ArrayList<Proyecto> proyectos;
    
    public Admin(int id, String usuario, String correo) {
        super(id, usuario, "admin", correo);
        proyectos = new ArrayList<>();
    }

    @Override
    protected void loadRowAttributes(ResultSet rs) throws SQLException {

    }

    @Override
    public String @Language("postgresql") [] getSQLInserts() {
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
}
