package com.planetdefender.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlanetDefender extends Game {
	private SpriteBatch batch;
	private Texture img;
	private Player player;
	private GridOfAliens aliens;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("e-01.png");
		player = new Player(img);
		aliens = new GridOfAliens(new Texture("a-03.png"), batch, player);
		gameScreen = new GameScreen(player, aliens, batch);
		gameOverScreen = new GameOverScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void gameOver() {
		setScreen(gameOverScreen);
	}
}
