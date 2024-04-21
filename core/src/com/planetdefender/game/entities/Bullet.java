package com.planetdefender.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.planetdefender.game.Spot.textureManager;

public class Bullet implements Entity {
    private final Vector2 position;
    private Sprite sprite;
    private final float offset = 45.0f;

    public Bullet(Vector2 position) {
        int bullet_width = 4, bullet_height = 7;
        int bullet_x = 9, bullet_y = 9;
        String bullet_path = "SpaceShooterAssets/SpaceShooterAssetPack_Projectiles.png";
        textureManager.loadTexture("player_bullet", bullet_path, bullet_x, bullet_y, bullet_width, bullet_height);
        TextureRegion bulletTexture = textureManager.getTexture("player_bullet");
        sprite = new Sprite(bulletTexture);
        float scale = 6.0f;
        sprite.setScale(scale);
        this.position = new Vector2(position.x - offset, 10000); // start the bullet offscreen somewhere
    }

    public void move(float deltaTime) {
        float speed = 2500.0f;
        position.y += deltaTime * speed;
    }

    public void reset(Vector2 playerPosition) {
        position.x = playerPosition.x - offset;
        position.y = playerPosition.y + sprite.getHeight() + offset * 2;
    }
    @Override
    public void Draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
    @Override
    public Vector2 getPosition() {
        return position;
    }
    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean getCollide(Sprite sprite) {
        return this.sprite.getBoundingRectangle().overlaps(sprite.getBoundingRectangle());
    }
}
