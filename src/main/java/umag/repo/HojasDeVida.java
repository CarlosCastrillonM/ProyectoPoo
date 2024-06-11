package umag.repo;

import umag.datos.HojaDeVida;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class HojasDeVida extends AbstractRepositorio<HojaDeVida>{
    @Override
    public String getTableName() {
        return "hoja_de_vida";
    }

    @Override
    public Class<HojaDeVida> getEntityClass() {
        return HojaDeVida.class;
    }

    public CompletableFuture<HojaDeVida> crearHojaDeVida(String nombre, String apellido, int cedula, Date fechaNacimiento, String correo, int telefono, String direccion, String profesion) {
        return SQLManager.executeQuery("""
                INSERT INTO hoja_de_vida (nombre, apellido, cedula, fecha_de_nacimiento, correo, direccion, telefono, profesion) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_hoja_de_vida""", nombre, apellido, cedula, fechaNacimiento, correo, direccion, telefono, profesion)
                .thenApply(id -> {
                    try {
                        id.next();
                        return add(new HojaDeVida(id.getInt(1), nombre, apellido, cedula,
                            fechaNacimiento, direccion, telefono, correo, profesion));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
