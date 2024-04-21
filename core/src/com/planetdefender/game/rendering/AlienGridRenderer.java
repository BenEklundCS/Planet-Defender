package com.planetdefender.game.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.planetdefender.game.entities.Alien;

public class AlienGridRenderer {
    private final AlienGridManager alienGridManager;
    private final SpriteBatch batch;
    private int round = 0;

    public AlienGridRenderer(AlienGridManager alienGridManager, SpriteBatch batch) {
        this.alienGridManager = alienGridManager;
        this.batch = batch;
    }

    public void renderAliens() {
        boolean all_dead = true;
        for (Alien alien : alienGridManager.getAliens()) {
            if (alien.alive) {
                alien.getPosition().add(alienGridManager.getOffsetAliens());
                alienGridManager.checkPlayerDeath(alien);
                alien.Draw(batch);
                all_dead = false;
            }
        }
        if (all_dead) {
            round++;
            if (round == 4) {
                round = 0;
            }
            alienGridManager.resetSpeed();
            renderForRound();
            alienGridManager.createAliens(alienGridManager.getAlienTexture());
        }
    }

    public boolean isGameOver() {
        return alienGridManager.game_over;
    }

    private void renderForRound() {
        switch (round) {
            case 0:
                alienGridManager.setAlienTexture(RenderUtils.blueAliens());
                break;
            case 1:
                alienGridManager.setAlienTexture(RenderUtils.redAliens());
                break;
            case 2:
                alienGridManager.setAlienTexture(RenderUtils.pinkAliens());
                break;
            case 3:
                alienGridManager.setAlienTexture(RenderUtils.grayAliens());
                break;
        }
    }
}
