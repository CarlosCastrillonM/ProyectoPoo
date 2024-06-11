import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import umag.auth.Admin;
import umag.auth.Cliente;
import umag.repo.Repositorios;

public class TestLoad {
    int initialId = 0;
    @Test
    public void testLoadAdmins() {
        System.out.println("TestLoad.testLoadAdmins");
        Repositorios.ADMINISTRADORES.getAll().forEach(System.out::println);
    }

    @Test
    public void testLoadClientes() {
        System.out.println("TestLoad.testLoadClientes");
        Repositorios.CLIENTES.getAll().forEach(System.out::println);
    }

    @Test
    public void testLoadProyectos() {
        System.out.println("TestLoad.testLoadProyectos");
        Repositorios.PROYECTOS.getAll().forEach(System.out::println);
    }

    @Test
    public void testLoadHojasDeVida() {
        System.out.println("TestLoad.testLoadHojasDeVida");
        Repositorios.HOJAS_VIDA.getAll().forEach(System.out::println);
    }

    @Test
    public void testLoadEmpleados() {
        System.out.println("TestLoad.testLoadEmpleados");
        Repositorios.EMPLEADOS.getAll().forEach(System.out::println);
    }

    @Test
    public void testLoadOfertas() {
        System.out.println("TestLoad.testLoadOfertas");
        Repositorios.OFERTAS.getAll().forEach(System.out::println);
    }
}
