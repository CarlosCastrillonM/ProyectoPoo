package umag.repo;

import umag.datos.Oferta;

import java.util.concurrent.CompletableFuture;

public class Ofertas extends AbstractRepositorio<Oferta> {
    @Override
    public String getTableName() {
        return "oferta";
    }

    @Override
    public Class<Oferta> getEntityClass() {
        return Oferta.class;
    }

    public CompletableFuture<Oferta> crearOferta(String nombre, String descripcion, String jornada, String experiencia, String lugarDeTrabajo, double salario) {
        return SQLManager.executeQuery("""
                INSERT INTO oferta (nombre, descripcion, jornada, experiencia, lugar_de_trabajo, salario)
                VALUES (?, ?, ?, ?, ?, ?) RETURNING id_oferta""", nombre, descripcion, jornada, experiencia, lugarDeTrabajo, salario)
                .thenApply(id -> {
                    try {
                        id.next();
                        return new Oferta(id.getInt(1), nombre, descripcion, jornada, experiencia, lugarDeTrabajo, salario);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
