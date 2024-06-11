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

    public CompletableFuture<Oferta> crearOferta(String nombre, String descripcion) {
        return SQLManager.executeQuery("""
                INSERT INTO oferta (nombre, descripcion) VALUES (?, ?) RETURNING id_oferta""", nombre, descripcion)
                .thenApply(id -> {
                    try {
                        id.next();
                        return new Oferta(id.getInt(1), nombre, descripcion);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
