package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public interface ObjetosEspacialesFactory {
    // Métodos genéricos para crear objetos espaciales
    Ball2 createAsteroide(int x, int y, Texture texture);
    Bullet createBala(float x, float y, float angle, Texture texture);
    Nave4 createNave(int x, int y, Texture textureNave, Texture textureBala, Sound soundChoque, Sound soundBala);

    // Métodos específicos para diferentes tipos
    Ball2 createAsteroideGigante(int x, int y, Texture texture);
    Ball2 createAsteroidePequeño(int x, int y, Texture texture);
    Nave4 createNave1(int x, int y, Texture textureNave, Texture textureBala, Sound soundChoque, Sound soundBala);
    Nave4 createNave2(int x, int y, Texture textureNave, Texture textureBala, Sound soundChoque, Sound soundBala);
}
