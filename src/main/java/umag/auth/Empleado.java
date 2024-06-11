package umag.auth;

import org.intellij.lang.annotations.Language;
import umag.datos.HojaDeVida;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado extends Cuenta {
    private HojaDeVida hojaDeVida;

    public Empleado(int id, String usuario, String correo, HojaDeVida hojaDeVida) {
        super(id, usuario, "empleado", correo);
        this.hojaDeVida = hojaDeVida;
    }

    public Empleado() {
    }

    public HojaDeVida getHojaDeVida() {
        return hojaDeVida;
    }

    @Override
    protected void loadRowAttributes(ResultSet rs) throws SQLException {
        hojaDeVida = new HojaDeVida(
            rs.getInt("id_hoja_de_vida"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getInt("cedula"),
            rs.getDate("fecha_de_nacimiento"),
            rs.getString("direccion"),
            rs.getInt("telefono"),
            rs.getString("correo"),
            rs.getString("profesion")
        );
    }

    @Override
    public @Language("postgresql") String[] getSQLInserts() {
        @Language("postgresql")
        String empIns = """
            INSERT INTO empleados (id_empleado, id_hoja_de_vida) VALUES (?, ?)
            ON CONFLICT DO UPDATE SET id_hoja_de_vida = EXCLUDED.id_hoja_de_vida""";

        return new String[]{
            empIns
        };
    }

    @Override
    public Object[][] getInsertParams() {
        return new Object[][]{
            {getId(), hojaDeVida.getId()}
        };
    }
}
