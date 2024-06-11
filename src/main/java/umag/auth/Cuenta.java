package umag.auth;

public class Cuenta {
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

    public int getId() {
        return id;
    }
}
