package com.planetdefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.planetdefender.game.entities.Alien;
import com.planetdefender.game.entities.Player;

public class GridOfAliens {
    private Alien[] aliens;
    private final int spacing = 100;
    private int minX_aliens = 10000;
    private int maxX_aliens = 0;
    private int direction_aliens = 1;
    private float speed_aliens = 15.0f;
    private Vector2 offset_aliens;
    private final String[] waves = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private int wave = 0;
    private Texture img_alien;
    private SpriteBatch batch;
    private Player player;
    private boolean game_over = false;

    public GridOfAliens(Texture img_alien, SpriteBatch batch, Player player) {
        this.img_alien = img_alien;
        this.batch = batch;
        this.player = player;
        createAliens(img_alien);
    }

    public void Update() {

        checkBulletCollisions();

        // Initialize rightmost and leftmost to invalid values, and use them to set maxX_aliens and minX_aliens
        setAlienDimensions();

        // Check edge conditions before updating offset_aliens
        if (hitLeftEdge() || hitRightEdge()) {
            toggleDirection();
            dropdown();
            speedup();
        }

        // Then update offset_aliens
        offset_aliens.x = direction_aliens * speed_aliens;

        // Recreate aliens if all are dead

        renderAliens();
    }

    private void createAliens(Texture img_alien) {
        int height_aliens = 4;
        int width_aliens = 10;
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

    private void renderAliens() {
        boolean all_dead = true;
        for (Alien alien : aliens) {
            if (alien.alive) {
                alien.position.add(offset_aliens);
                // Check if the alien overlaps the player (it also must be alive as guaranteed by the previous block)
                checkPlayerDeath(alien);
                alien.Draw(batch);
                all_dead = false;
            }
        }
        if (all_dead) {
            changeImage();
            createAliens(img_alien);
        }
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

    private void checkPlayerDeath(Alien alien) {
        boolean contact = alien.sprite.getBoundingRectangle().overlaps(player.getSprite().getBoundingRectangle());
        boolean position = alien.position.y < 0; // 0 bottom of the screen
        if (contact || position) {
            game_over = true;
        }
    }

    private void checkBulletCollisions() {
        for (Alien alien : aliens) {
            if (player.getSprite_bullet().getBoundingRectangle().overlaps(alien.sprite.getBoundingRectangle()) && alien.alive) {
                alien.alive = false;
                player.setKills();
                player.getPosition_bullet().y += 10000;
            }
        }
    }

    private boolean hitRightEdge() {
        return aliens[maxX_aliens].position.x + aliens[maxX_aliens].sprite.getWidth() >= Gdx.graphics.getWidth();
    }

    private boolean hitLeftEdge() {
        return aliens[minX_aliens].position.x <= 0;
    }

    private void changeImage() {
        wave++;
        if (wave < waves.length) {
            img_alien = new Texture(waves[wave]+"-03.png");
        }
    }

    private void setAlienDimensions() {
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
    }

    public boolean isGameOver() {
        return game_over;
    }
}