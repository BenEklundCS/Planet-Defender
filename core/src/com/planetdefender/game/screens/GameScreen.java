package com.planetdefender.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.planetdefender.game.entities.Background;
import com.planetdefender.game.rendering.AlienGridManager;
import com.planetdefender.game.rendering.AlienGridRenderer;
import com.planetdefender.game.PlanetDefender;
import com.planetdefender.game.entities.Player;
import com.badlogic.gdx.audio.Music;

public class GameScreen implements Screen {
    private final Player player;
    private final AlienGridRenderer renderer;
    private final AlienGridManager manager;
    private final SpriteBatch batch;
    private final Background background;
    private final Music music;

    public GameScreen(Background background, Player player, AlienGridRenderer renderer, AlienGridManager manager, SpriteBatch batch) {
        this.background = background;
        this.player = player;
        this.renderer = renderer;
        this.manager = manager;
        this.batch = batch;
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/main.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        background.Draw(batch);
        player.Draw(batch);
        manager.Update();
        renderer.renderAliens();
        batch.end();

        if (renderer.isGameOver()) {
            music.stop();
            ((PlanetDefender) Gdx.app.getApplicationListener()).gameOver();
        }
    }

    // Implement other Screen methods (they can be left empty for now)
    @Override public void resize(int width, int height) {}
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {}
}
