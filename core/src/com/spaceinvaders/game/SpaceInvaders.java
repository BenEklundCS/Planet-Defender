package com.spaceinvaders.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpaceInvaders extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("a-01.png");
		player = new Player(img, null);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.Draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
