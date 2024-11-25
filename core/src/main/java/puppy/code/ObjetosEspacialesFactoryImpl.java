package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ObjetosEspacialesFactoryImpl implements ObjetosEspacialesFactory {
    
    @Override
    public Ball2 createAsteroide(int x, int y, Texture texture) {
        return new Ball2(x, y, 20, 2, 2, texture); // Tamaño y velocidad genérica
    }

    @Override
    public Bullet createBala(float x, float y, float angle, Texture texture) {
        return new Bullet(x, y, angle, texture);
    }

    @Override
    public Nave4 createNave(int x, int y, Texture textureNave, Texture textureBala, Sound soundChoque, Sound soundBala) {
        return new Nave4(x, y, textureNave, soundChoque, textureBala, soundBala);
    }

    // Implementación de tipos específicos
    @Override
    public Ball2 createAsteroideGigante(int x, int y, Texture texture) {
        Ball2 asteroideGigante = new Ball2(x, y, 50, 1, 1, texture); // Más grande, más lento
        asteroideGigante.setEstrategiaMovimiento(new MovimientoLineal());
        return asteroideGigante;
    }

    @Override
    public Ball2 createAsteroidePequeño(int x, int y, Texture texture) {
        Ball2 asteroidePequeño = new Ball2(x, y, 10, 4, 4, texture); // Más pequeño, más rápido
        asteroidePequeño.setEstrategiaMovimiento(new MovimientoZigzag());
        return asteroidePequeño;
    }

    @Override
    public Nave4 createNave1(int x, int y, Texture textureNave, Texture textureBala, Sound soundChoque, Sound soundBala) {
        Nave4 naveLigera = new Nave4(x, y, textureNave, soundChoque, textureBala, soundBala);
        naveLigera.setVel_x(4); // Más rápida
        naveLigera.setVel_y(4);
        naveLigera.setEstrategiaMovimiento(new MovimientoLineal());
        return naveLigera;
    }

    @Override
    public Nave4 createNave2(int x, int y, Texture textureNave, Texture textureBala, Sound soundChoque, Sound soundBala) {
        Nave4 naveBlindada = new Nave4(x, y, textureNave, soundChoque, textureBala, soundBala);
        naveBlindada.setVidas(5); // Más resistente
        naveBlindada.setEstrategiaMovimiento(new MovimientoZigzag());
        return naveBlindada;
    }
}

