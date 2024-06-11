import org.junit.jupiter.api.Test;
import umag.datos.HojaDeVida;
import umag.repo.Repositorios;

import java.time.Instant;
import java.util.Date;

public class SaveTest {
    @Test
    public void saveAdmins() {
        Repositorios.ADMINISTRADORES.crearAdmin("admin1", "admin@admin.admn");
        Repositorios.ADMINISTRADORES.crearAdmin("camilo1", "camilo@cam.lo.bj").join();
    }

    @Test
    public void saveOfertas() {
        Repositorios.OFERTAS.crearOferta("Oferta 1", "Descripcion 1").join();
        Repositorios.OFERTAS.crearOferta("Oferta 2", "Descripcion 2").join();
    }

    @Test
    public void saveClientes() {
        Repositorios.CLIENTES.crearCliente("Cliente 5", "c5@a.com", "ajshd asjd").join();
        Repositorios.CLIENTES.crearCliente("Cliente 6", "c6@a.com", "efea jaklshd").join();
    }

    @Test
    public void saveEmpleados() {
        Repositorios.EMPLEADOS.crearEmpleado("Empleado 1",
            new HojaDeVida(1, "Empleado", "1", 123, Date.from(Instant.now()), "dir", 123, "akjlsdha@asdjklas.com",
                "S"));
    }

    @Test
    public void saveProyectos() {
        Repositorios.PROYECTOS.crearProyecto("Proyecto 1", "Descripcion 1", Repositorios.CLIENTES.getAll().stream().findFirst().orElse(null), Repositorios.ADMINISTRADORES.getAll().stream().findFirst().orElse(null)).join();
        Repositorios.PROYECTOS.crearProyecto("Proyecto 2", "Descripcion 2", Repositorios.CLIENTES.getAll().stream().findFirst().orElse(null), Repositorios.ADMINISTRADORES.getAll().stream().findFirst().orElse(null)).join();
    }

    @Test
    public void saveHojasDeVida() {
        Repositorios.HOJAS_VIDA
            .crearHojaDeVida("Hoja 1", "De Vida", 123, Date.from(Instant.now()), "aaskldj@asljkd.com", 123, "dir",
                "Profesion").join();
    }

}
