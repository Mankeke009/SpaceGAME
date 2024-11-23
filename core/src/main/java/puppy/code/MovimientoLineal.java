package puppy.code;

public class MovimientoLineal implements EstrategiaMovimiento {
    @Override
    public void mover(ObjetosEspaciales objeto) {
        objeto.setVel_x(objeto.getVel_x());
        objeto.setVel_y(objeto.getVel_y());
        objeto.actualizarPosicion();
    }
}

