package umag.auth;

import org.intellij.lang.annotations.Language;
import umag.model.Guardable;
import umag.repo.SQLManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Cuenta implements Guardable {
    private int id;
    private String usuario;
    private String tipo;
    private String correo;

    public Cuenta(int id, String usuario, String tipo, String correo) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.correo = correo;
    }

    public Cuenta() {
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void loadRow(ResultSet rs) throws SQLException {
        id = rs.getInt("id_cuenta");
        usuario = rs.getString("usuario");
        tipo = rs.getString("tipo");
        correo = rs.getString("correo");
        loadRowAttributes(rs);
    }

    protected abstract void loadRowAttributes(ResultSet rs) throws SQLException;

    @Override
    public void save() throws IllegalStateException {
        SQLManager.executeUpdate("""
            UPDATE cuenta SET
                usuario = ?,
                tipo = ?,
                correo = ?
            WHERE id_cuenta = ?
            """, getUsuario(), getTipo(), getCorreo(), id);
        Object[][] insertParams = getInsertParams();
        String[] sqlInserts = getSQLInserts();

        for (int i = 0; i < sqlInserts.length; i++) {
            @Language("postgresql") String sqlInsert = sqlInserts[i];
            if (sqlInsert == null) {
                throw new IllegalStateException("Los valores dados por el getInsertParams() de la clase %s no pueden contener nulos".formatted(this.getClass().getName()));
            }

            if (insertParams.length <= i) {
                throw new IllegalStateException("Faltan parámetros para el insert: %d < %d".formatted(insertParams.length, i));
            }

            Object[] params = insertParams[i];
            SQLManager.executeUpdate(sqlInsert, params);
        }
    }

    /**
     * Diseñado para las clases que heredan de esta que puedan guardar sus
     * datos en una tabla aparte siguiendo un modelo jerárquico
     *
     * @return los insert de la consulta para ser ejecutado al mismo tiempo
     * que el insert de la cuenta genérica en la tabla "cuenta"
     */
    public abstract @Language("postgresql") String[] getSQLInserts();

    /**
     * Diseñado para las clases que heredan de esta que puedan guardar sus
     * datos en una tabla aparte siguiendo un modelo jerárquico
     *
     * @return los parámetros necesarios para los insert de la consulta
     * para ser ejecutado al mismo tiempo que el insert de la cuenta genérica
     * en la tabla "cuenta"
     */
    public abstract Object[][] getInsertParams();
}
