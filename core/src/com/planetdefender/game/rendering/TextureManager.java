package com.planetdefender.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private final Map<String, TextureRegion> textures;

    public TextureManager() {
        textures = new HashMap<>();
        loadTextures();
    }

    private void loadTextures() {
        loadTexture("player", "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png", 31, 64, 16, 16);
        loadTexture("player_bullet", "SpaceShooterAssets/SpaceShooterAssetPack_Projectiles.png", 9, 9, 4, 7);
        loadTexture("blue_alien", "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png", 56, 1, 7, 6);
        loadTexture("red_alien", "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png", 73, 1, 7, 6);
        loadTexture("pink_alien", "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png", 40, 9, 7, 6);
        loadTexture("gray_alien", "SpaceShooterAssets/SpaceShooterAssetPack_Ships.png", 32, 9, 7, 6);
    }

    public void loadTexture(String name, String path) {
        Texture img = new Texture(Gdx.files.internal(path));
        TextureRegion region = new TextureRegion(img);
        textures.put(name, region);
    }

    public void loadTexture(String name, String path, int x, int y, int width, int height) {
        Texture img = new Texture(Gdx.files.internal(path));
        TextureRegion region = new TextureRegion(img, x, y, width, height);
        textures.put(name, region);
    }

    public TextureRegion getTexture(String name) {
        return textures.get(name);
    }

    public void dispose() {
        for (TextureRegion region : textures.values()) {
            region.getTexture().dispose();
        }
        textures.clear();
    }
}
