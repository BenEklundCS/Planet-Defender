package com.planetdefender.game.rendering;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.planetdefender.game.Spot.textureManager;

public class RenderUtils {
    public static TextureRegion playerTexture() {
        int ship_width = 16, ship_height = 16;
        int ship_x = 31, ship_y = 64;
        String ship_path = "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png";
        textureManager.loadTexture("player", ship_path, ship_x, ship_y, ship_width, ship_height);
        return textureManager.getTexture("player");
    }
    public static TextureRegion bulletTexture() {
        int bullet_width = 4, bullet_height = 7;
        int bullet_x = 9, bullet_y = 9;
        String bullet_path = "SpaceShooterAssets/SpaceShooterAssetPack_Projectiles.png";
        textureManager.loadTexture("player_bullet", bullet_path, bullet_x, bullet_y, bullet_width, bullet_height);
        return textureManager.getTexture("player_bullet");
    }
    public static TextureRegion redAliens() {
        return getAlienTexture(73, 1);
    }

    public static TextureRegion blueAliens() {
        return getAlienTexture(56, 1);
    }

    public static TextureRegion pinkAliens() {
        return getAlienTexture(40, 9);
    }

    public static TextureRegion grayAliens() {
        return getAlienTexture(32, 9);
    }

    private static TextureRegion getAlienTexture(int alien_x, int alien_y) {
        int alien_width = 7, alien_height = 6;
        String alien_path = "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png";
        textureManager.loadTexture("blue_alien", alien_path, alien_x, alien_y, alien_width, alien_height);
        return textureManager.getTexture("blue_alien");
    }
}
