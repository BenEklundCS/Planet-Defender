package com.planetdefender.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.planetdefender.game.Spot.*;

public class Bullet implements Entity {
    private final Vector2 position;
    private Sprite sprite;
    private final float offset = 45.0f;
    float speed;

    public Bullet(Vector2 position, float speed) {
        TextureRegion bulletTexture = textureManager.getTexture("player_bullet");
        sprite = new Sprite(bulletTexture);
        float scale = 6.0f;
        this.speed = speed;
        sprite.setScale(scale);
        this.position = new Vector2(position.x - offset, 10000); // start the bullet offscreen somewhere
    }

    public void move(float deltaTime) {
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
