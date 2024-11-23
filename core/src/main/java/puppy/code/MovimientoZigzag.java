package puppy.code;

import com.badlogic.gdx.math.MathUtils;

public class MovimientoZigzag implements EstrategiaMovimiento {
    @Override
    public void mover(ObjetosEspaciales objeto) {
        float randomFactor = MathUtils.random(-1f, 1f);
        objeto.setVel_x(objeto.getVel_x() + randomFactor);
        objeto.setVel_y(objeto.getVel_y());
        objeto.actualizarPosicion();
    }
}
