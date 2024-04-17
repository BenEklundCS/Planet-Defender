package com.planetdefender.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameOverScreen implements Screen {
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Here you can add code to display a game over message
    }

    // Implement other Screen methods (they can be left empty for now)
    @Override public void resize(int width, int height) {}
    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {}
}
