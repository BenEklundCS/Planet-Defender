package com.planetdefender.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlanetDefender extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private GridOfAliens aliens;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("e-01.png");
		Spot.player = new Player(img);
		aliens = new GridOfAliens(new Texture("a-03.png"));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();

		Spot.player.Draw(batch);

		aliens.Update(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
