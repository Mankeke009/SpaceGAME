package puppy.code;

import com.badlogic.gdx.Input;

public class PantallaMenu extends PantallaBase {

    public PantallaMenu(SpaceNavigation game) {
        super(game); // Llama al constructor de la clase base
    }

    @Override
    protected void actualizar(float delta) {
        // Detectar entrada del usuario para comenzar el juego
        if (com.badlogic.gdx.Gdx.input.isTouched() || 
            com.badlogic.gdx.Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new PantallaJuego(game, 1, 3, 0, 1, 1));
            dispose();
        }
    }

    @Override
    protected void dibujar() {
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Bienvenido a Space Navigation!", 100, 400);
        game.getFont().draw(game.getBatch(), "Cómo jugar:", 100, 350);
        game.getFont().draw(game.getBatch(), "WASD para moverte y apuntar.", 100, 300);
        game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 200);
        game.getBatch().end();
    }
}
