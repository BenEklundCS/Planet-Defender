package com.planetdefender.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {
    private final SpriteBatch batch;
    private final BitmapFont font;

    private int kills;
    private final int offset = 300;

    public GameOverScreen(SpriteBatch batch) {
        this.batch = batch;
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(10); // Set the scale to 2
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "GAME OVER", (Gdx.graphics.getWidth() / 4f) - offset, Gdx.graphics.getHeight() / 2f);

        font.draw(batch, "YOU KILLED " + kills + " ALIENS!", (Gdx.graphics.getWidth() / 4f) - offset, (Gdx.graphics.getHeight() / 2f) - offset);
        batch.end();
    }

    // Implement other Screen methods (they can be left empty for now)
    @Override public void resize(int width, int height) {}
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
