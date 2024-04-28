package com.planetdefender.game.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.planetdefender.game.entities.Alien;
import com.planetdefender.game.utils.Random;

import static com.planetdefender.game.Spot.*;

public class AlienGridRenderer {
    private final AlienGridManager alienGridManager;
    private final SpriteBatch batch;
    private int round = 0;
    private boolean toggle = false;

    public AlienGridRenderer(AlienGridManager alienGridManager, SpriteBatch batch) {
        this.alienGridManager = alienGridManager;
        this.batch = batch;
    }

    public void renderAliens() {
        boolean all_dead = true;
        for (Alien alien : alienGridManager.getAliens()) {
            if (alien.alive) {
                alien.getPosition().add(alienGridManager.getOffsetAliens());
                alien.Draw(batch);
                all_dead = false;
                alienGridManager.checkPlayerDeath(alien);
            }
        }
        if (all_dead) {
            round++;
            if (round == 4) {
                round = 0;
            }
            alienGridManager.resetSpeed();
            renderForRound();
            Vector2 dimensions = Random.randomVector(4, 6, 2, 4);
            alienGridManager.setDimensions(dimensions);
            alienGridManager.createAliens();
        }
    }

    public boolean isGameOver() {
        return alienGridManager.game_over;
    }

    private void renderForRound() {
        switch (round) {
            case 0:
                alienGridManager.setAlienTexture(textureManager.getTexture("blue_alien"));
                break;
            case 1:
                alienGridManager.setAlienTexture(textureManager.getTexture("red_alien"));
                break;
            case 2:
                alienGridManager.setAlienTexture(textureManager.getTexture("pink_alien"));
                break;
            case 3:
                alienGridManager.setAlienTexture(textureManager.getTexture("gray_alien"));
                break;
        }
    }


}
