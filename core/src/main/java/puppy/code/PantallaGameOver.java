package puppy.code;

import com.badlogic.gdx.Input;

public class PantallaGameOver extends PantallaBase {

    public PantallaGameOver(SpaceNavigation game) {
        super(game); // Llama al constructor de la clase base
    }

    @Override
    protected void actualizar(float delta) {
        // Reiniciar el juego si el usuario interactúa
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
        game.getFont().draw(game.getBatch(), "Game Over !!!", 120, 400, 400, 1, true);
        game.getFont().draw(game.getBatch(), "Pincha en cualquier lado para reiniciar ...", 100, 300);
        game.getBatch().end();
    }
}
