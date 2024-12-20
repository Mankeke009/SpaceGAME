package puppy.code;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;

public class PantallaJuego extends PantallaBase {

    private int ronda;
    private int score;
    private int velXAsteroides;
    private int velYAsteroides;
    private int cantAsteroides;

    private Nave4 nave;
    private ArrayList<Ball2> balls;
    private ArrayList<Bullet> balas;

    private Sound explosionSound;
    private Music gameMusic;
    private String[] texturasNave = {
        "MainShip1.png",
        "MainShip2.png",
        "MainShip3.png",
        "MainShip4.png"
    };


    public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, int velXAsteroides, int velYAsteroides) {
        super(game);
        this.ronda = ronda;
        this.score = score;
        this.velXAsteroides = velXAsteroides;
        this.velYAsteroides = velYAsteroides;
        
        this.cantAsteroides = calcularCantidadAsteroides(ronda);

        nave = ObjetosEspacialesFactorySingleton.getInstance()
        .createNave(Gdx.graphics.getWidth() / 2 - 50, 30,
                obtenerTexturaNave(ronda), // Textura de la nave según la ronda
                new Texture(Gdx.files.internal("Rocket2.png")), // Textura de las balas
                Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));

        nave.setVidas(vidas);

        balls = generarAsteroides();
        balas = new ArrayList<>();

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        explosionSound.setVolume(1, 0.5f);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();
    }
    private Texture obtenerTexturaNave(int ronda) {
    int index = (ronda - 1) % texturasNave.length; // Cicla las texturas
    return new Texture(Gdx.files.internal(texturasNave[index]));
}


    private int calcularCantidadAsteroides(int ronda) {
        return 1 + (ronda - 1) * 2;
    }

        private ArrayList<Ball2> generarAsteroides() {
        ArrayList<Ball2> asteroides = new ArrayList<>();
        ObjetosEspacialesFactory factory = ObjetosEspacialesFactorySingleton.getInstance();
        Random r = new Random();

        for (int i = 0; i < cantAsteroides; i++) {
            int x = r.nextInt(Gdx.graphics.getWidth());
            int y = r.nextInt(Gdx.graphics.getHeight());
            Texture texture = new Texture(Gdx.files.internal("aGreyMedium4.png"));

            // Alternar entre tipos de asteroides para asignar estrategias distintas
            if (i % 2 == 0) {
                asteroides.add(factory.createAsteroideGigante(x, y, texture)); // Movimiento Lineal
            } else {
                asteroides.add(factory.createAsteroidePequeño(x, y, texture)); // Movimiento Zigzag
            }
        }
        
        return asteroides;
    }



    @Override
    protected void actualizar(float delta) {
        if (!nave.estaHerido()) {
            actualizarBalas();
            actualizarAsteroides();
            verificarColisionesNaveAsteroides();
        }

        if (nave.estaDestruido()) {
            manejarGameOver();
        }

        if (balls.isEmpty()) {
            iniciarSiguienteRonda();
        }
    }

    private void actualizarBalas() {
        for (int i = 0; i < balas.size(); i++) {
            Bullet b = balas.get(i);
            b.update();

            for (int j = 0; j < balls.size(); j++) {
                Ball2 asteroide = balls.get(j);
                if (b.detectarColision(asteroide)) {
                    explosionSound.play();
                    asteroide.alColisionar(b);
                    balls.remove(j);
                    j--;
                    score += 10;
                    b.alColisionar(asteroide);
                }
            }

            if (b.isDestroyed()) {
                balas.remove(i);
                i--;
            }
        }
    }

    private void actualizarAsteroides() {
        for (Ball2 ball : balls) {
            ball.update();
        }
    }

    private void verificarColisionesNaveAsteroides() {
        for (int i = 0; i < balls.size(); i++) {
            Ball2 asteroide = balls.get(i);
            if (nave.detectarColision(asteroide)) {
                nave.alColisionar(asteroide);
                balls.remove(i);
                i--;
            }
        }
    }

    private void manejarGameOver() {
        if (score > game.getHighScore()) {
            game.setHighScore(score);
        }
        game.setScreen(new PantallaGameOver(game));
        dispose();
    }

    private void iniciarSiguienteRonda() {
        game.setScreen(new PantallaJuego(game, ronda + 1, nave.getVidas(), score, velXAsteroides + 1, velYAsteroides + 1));
        dispose();
    }

    @Override
    protected void dibujar() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        dibujarEncabezado();
        for (Bullet b : balas) {
            b.draw(batch);
        }
        nave.draw(batch, this);
        for (Ball2 b : balls) {
            b.draw(batch);
        }

        batch.end();
    }

    private void dibujarEncabezado() {
        CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
        game.getFont().getData().setScale(2f);
        game.getFont().draw(game.getBatch(), str, 10, 30);
        game.getFont().draw(game.getBatch(), "Score: " + this.score, Gdx.graphics.getWidth() - 150, 30);
        game.getFont().draw(game.getBatch(), "HighScore: " + game.getHighScore(), Gdx.graphics.getWidth() / 2 - 100, 30);
    }

    @Override
    public void dispose() {
        explosionSound.dispose();
        gameMusic.dispose();
    }
    public void agregarBala(Bullet bala) {
    balas.add(bala);
    }

}
