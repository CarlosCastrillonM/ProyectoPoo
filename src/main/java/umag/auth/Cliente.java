package umag.auth;

public class Cliente extends Cuenta {
    
    String nombre;
    
    public Cliente(int id, String usuario, String tipo, String correo, String nombre) {
        super(id, usuario, tipo, correo);
        this.nombre = nombre;
    }
}
