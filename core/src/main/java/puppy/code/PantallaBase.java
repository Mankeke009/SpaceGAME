package puppy.code;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class PantallaBase implements Screen {

    protected SpaceNavigation game; // Referencia al juego principal
    protected OrthographicCamera camera;

    public PantallaBase(SpaceNavigation game) {
        this.game = SpaceNavigation.getInstance(); // Singleton
        this.camera = new OrthographicCamera();
        configurarCamara(); // Paso común para todas las pantallas
    }

    // Método Template: define el flujo general
    @Override
    public final void render(float delta) {
        limpiarPantalla();
        actualizar(delta);
        dibujar();
    }
    

    // Método concreto para limpiar la pantalla
    protected void limpiarPantalla() {
        com.badlogic.gdx.utils.ScreenUtils.clear(0, 0, 0.2f, 1);
    }

    // Método abstracto que las subclases deben implementar
    protected abstract void actualizar(float delta);

    // Método abstracto para dibujar contenido específico
    protected abstract void dibujar();

    // Configuración común de la cámara
    protected void configurarCamara() {
        camera.setToOrtho(false, 1200, 800); // Tamaño estándar
    }

    @Override
    public void show() {
    // Implementación vacía por defecto
    }

    @Override
    public void resize(int width, int height) {
    // Implementación vacía por defecto
    }

    @Override
    public void pause() {
    // Implementación vacía por defecto
    }

    @Override
    public void resume() {
    // Implementación vacía por defecto
    }

    @Override
    public void hide() {
    // Implementación vacía por defecto
    }

    @Override
    public void dispose() {
    // Implementación vacía por defecto
    }

}
