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
	private Texture img_bullet;
	private Texture img_alien;
	private Player player;
	private Alien[] aliens;
	private int width_aliens = 10;
	private int height_aliens = 4;
	private int spacing = 100;
	private int minX_aliens = 10000;
	private int maxX_aliens = 0;
	private int direction_aliens = 1;
	private float speed_aliens = 4.0f;
	private Vector2 offset_aliens;
	private String[] waves = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
	private int wave = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("e-01.png");
		img_bullet = new Texture("blue.png");
		player = new Player(img, img_bullet);
		img_alien = new Texture("a-03.png");
		createAliens(img_alien);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();

		player.Draw(batch);

		for (int i = 0; i < aliens.length; i++) {
			if (player.sprite_bullet.getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle()) && aliens[i].alive) {
				aliens[i].alive = false;
				player.position_bullet.y += 10000;
			}
		}

		// Initialize rightmost and leftmost to invalid values, and use them to set maxX_aliens and minX_aliens
		float rightmost = 0;
		float leftmost = Gdx.graphics.getWidth();

		for (int i = 0; i < aliens.length; i++) {
			if (aliens[i].alive) {
				if (aliens[i].position.x > rightmost) {
					rightmost = aliens[i].position.x;
					maxX_aliens = i;
				}
				if (aliens[i].position.x < leftmost) {
					leftmost = aliens[i].position.x;
					minX_aliens = i;
				}
			}
		}

		// Check edge conditions before updating offset_aliens
		if (aliens[maxX_aliens].position.x + aliens[maxX_aliens].sprite.getWidth() >= Gdx.graphics.getWidth()) {
			toggleDirection();
			dropdown();
			speedup();
		}
		if (aliens[minX_aliens].position.x <= 0) {
			toggleDirection();
			dropdown();
			speedup();
		}

		// Then update offset_aliens
		offset_aliens.x = direction_aliens * speed_aliens;

		// Recreate aliens if all are dead
		if (allDead()) {
			wave++;
			if (wave < waves.length) {
				img_alien = new Texture(waves[wave]+"-03.png");
			}
			createAliens(img_alien);
		}


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	private void createAliens(Texture img_alien) {
		aliens = new Alien[width_aliens * height_aliens];
		offset_aliens = new Vector2(speed_aliens, 0);
		int i = 0;
		for (int y = 0; y < height_aliens; y++) {
			for (int x = 0; x < width_aliens; x++) {
				Vector2 position = new Vector2(x * spacing, y * spacing);
				position.x += (float) Gdx.graphics.getWidth() /2;
				position.y += Gdx.graphics.getHeight();
				position.x -= ((float) width_aliens /2) * spacing;
				position.y -= height_aliens * spacing;
				aliens[i] = new Alien(position, img_alien);
				i++;
			}
		}
	}

	private boolean checkPlayerDeath(Alien alien) {
		return alien.sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle());
	}

	private void speedup() {
		speed_aliens *= 1.05f;
	}

	private void dropdown() {
		for (Alien alien : aliens) {
			alien.position.y -= spacing; // drop down one level
		}
	}

	private void toggleDirection() {
		direction_aliens *= -1;
	}

	private boolean allDead() {
		boolean all_dead = true;
        for (Alien alien : aliens) {
            if (alien.alive) {
                alien.position.add(offset_aliens);
                // Check if the alien overlaps the player (it also must be alive as guaranteed by the previous block)
                if (checkPlayerDeath(alien)) {
                    Gdx.app.exit();
                }
                alien.Draw(batch);
                all_dead = false;
            }
        }
		return all_dead;
	}
}
