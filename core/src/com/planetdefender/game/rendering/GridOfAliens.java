package com.planetdefender.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.planetdefender.game.entities.Alien;
import com.planetdefender.game.entities.Player;
import com.planetdefender.game.events.BoundaryChecker;

import static com.planetdefender.game.Spot.textureManager;

public class GridOfAliens {
    private Alien[] aliens;
    private final int spacing = 100;
    private int minX_aliens = 10000;
    private int maxX_aliens = 0;
    private int direction_aliens = 1;
    private float speed_aliens = 4.0f;
    private Vector2 offset_aliens;
    private final String[] waves = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private int wave = 0;
    private TextureRegion alienTexture;
    private final SpriteBatch batch;
    private final Player player;
    private boolean game_over = false;

    public GridOfAliens(SpriteBatch batch, Player player) {
        int alien_x = 56, alien_y = 1; // 63, 7
        int alien_width = 7, alien_height = 6;
        String alien_path = "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png";
        textureManager.loadTexture("blue_alien", alien_path, alien_x, alien_y, alien_width, alien_height);
        TextureRegion alienTexture = textureManager.getTexture("blue_alien");
        this.alienTexture = alienTexture;
        this.batch = batch;
        this.player = player;
        createAliens(alienTexture);
    }

    public void Update() {

        checkBulletCollisions();

        // Initialize rightmost and leftmost to invalid values, and use them to set maxX_aliens and minX_aliens
        setAlienDimensions();
        BoundaryChecker boundaryChecker = new BoundaryChecker(0, Gdx.graphics.getWidth());
        // Check edge conditions before updating offset_aliens
        int alien_offset = 40;
        if (boundaryChecker.isOutOfLeftBound(aliens[minX_aliens].getPosition(), aliens[minX_aliens].getSprite().getWidth() + alien_offset)) {
            toggleDirection();
            dropdown();
            speedup();
        }

        if (boundaryChecker.isOutOfRightBound(aliens[maxX_aliens].getPosition(), aliens[maxX_aliens].getSprite().getWidth() + alien_offset)) {
            toggleDirection();
            dropdown();
            speedup();
        }

        // Then update offset_aliens
        offset_aliens.x = direction_aliens * speed_aliens;

        // Recreate aliens if all are dead

        renderAliens();
    }

    private void createAliens(TextureRegion img_alien) {
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
                alien.getPosition().add(offset_aliens);
                // Check if the alien overlaps the player (it also must be alive as guaranteed by the previous block)
                checkPlayerDeath(alien);
                alien.Draw(batch);
                all_dead = false;
            }
        }
        if (all_dead) {
            //changeImage();
            createAliens(alienTexture);
        }
    }

    private void speedup() {
        speed_aliens *= 1.05f;
    }

    private void dropdown() {
        for (Alien alien : aliens) {
            alien.getPosition().y -= spacing; // drop down one level
        }
    }

    private void toggleDirection() {
        direction_aliens *= -1;
    }

    private void checkPlayerDeath(Alien alien) {
        boolean contact = alien.getSprite().getBoundingRectangle().overlaps(player.getSprite().getBoundingRectangle());
        boolean position = alien.getPosition().y < 0; // 0 bottom of the screen
        if (contact || position) {
            game_over = true;
        }
    }

    private void checkBulletCollisions() {
        for (Alien alien : aliens) {
            if (player.getBullet().getCollide(alien.getSprite()) && alien.alive) {
                alien.alive = false;
                player.setKills();
                player.getBullet().getPosition().y += 10000;
            }
        }
    }

    private boolean hitRightEdge() {
        return aliens[maxX_aliens].getPosition().x + aliens[maxX_aliens].getSprite().getWidth() >= Gdx.graphics.getWidth();
    }

    private boolean hitLeftEdge() {
        return aliens[minX_aliens].getPosition().x <= 0;
    }

    /*
    private void changeImage() {
        wave++;
        if (wave < waves.length) {
            alienTexture = new TextureRegion(waves[wave]+"-03.png");
        }
    }
    */

    private void setAlienDimensions() {
        float rightmost = 0;
        float leftmost = Gdx.graphics.getWidth();

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i].alive) {
                if (aliens[i].getPosition().x > rightmost) {
                    rightmost = aliens[i].getPosition().x;
                    maxX_aliens = i;
                }
                if (aliens[i].getPosition().x < leftmost) {
                    leftmost = aliens[i].getPosition().x;
                    minX_aliens = i;
                }
            }
        }
    }

    public boolean isGameOver() {
        return game_over;
    }
}
