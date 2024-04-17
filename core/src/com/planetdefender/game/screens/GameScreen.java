package com.planetdefender.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.planetdefender.game.GridOfAliens;
import com.planetdefender.game.PlanetDefender;
import com.planetdefender.game.entities.Player;

public class GameScreen implements Screen {
    private final Player player;
    private final GridOfAliens aliens;
    private final SpriteBatch batch;

    public GameScreen(Player player, GridOfAliens aliens, SpriteBatch batch) {
        this.player = player;
        this.aliens = aliens;
        this.batch = batch;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        player.Draw(batch);
        aliens.Update();
        batch.end();

        if (aliens.isGameOver()) {
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
