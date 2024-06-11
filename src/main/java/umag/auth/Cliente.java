package umag.auth;

import org.intellij.lang.annotations.Language;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente extends Cuenta {
    String nombre;
    
    public Cliente(int id, String usuario, String correo, String nombre) {
        super(id, usuario, "cliente", correo);
        this.nombre = nombre;
    }

    public Cliente() {
    }

    @Override
    protected void loadRowAttributes(ResultSet rs) throws SQLException {
        nombre = rs.getString("nombre");
    }

    @Override
    public @Language("postgresql") String[] getSQLInserts() {
        @Language("postgresql")
        String cliIns = """
            INSERT INTO cliente (id_cliente, nombre) VALUES (?, ?)
            ON CONFLICT DO UPDATE SET nombre = EXCLUDED.nombre""";
        return new String[]{
            cliIns
        };
    }

    @Override
    public Object[][] getInsertParams() {
        return new Object[][]{
            {getId(), nombre}
        };
    }
}
