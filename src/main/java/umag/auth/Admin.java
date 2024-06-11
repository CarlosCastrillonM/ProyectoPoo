package umag.auth;

import java.util.ArrayList;
import java.util.List;
import umag.datos.Proyecto;

public class Admin extends Cuenta {
    
    ArrayList<Proyecto> proyectos;
    
    public Admin(int id, String usuario, String tipo, String correo) {
        super(id, usuario, tipo, correo);
        proyectos = new ArrayList<>();
    }
}
