package com.planetdefender.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlanetDefender extends Game {
	private SpriteBatch batch;
	private Texture img;
	private GridOfAliens aliens;
	private Player player;
	private GameOverScreen gameOverScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("e-01.png");
		player = new Player(img);
		aliens = new GridOfAliens(new Texture("a-03.png"), batch, player);
		gameOverScreen = new GameOverScreen();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();

		player.Draw(batch);

		aliens.Update();

		if (aliens.isGameOver()) {
			gameOver();
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void gameOver() {
		setScreen(gameOverScreen);
		Gdx.app.exit();
	}
}
