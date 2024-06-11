package umag.auth;

import umag.datos.HojaDeVida;

public class Empleado extends Cuenta {
    private HojaDeVida hojaDeVida;

    public Empleado(int id, String usuario, String tipo, String correo, HojaDeVida hojaDeVida) {
        super(id, usuario, tipo, correo);
        this.hojaDeVida = hojaDeVida;
    }

    public HojaDeVida getHojaDeVida() {
        return hojaDeVida;
    }
}
