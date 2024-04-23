package com.planetdefender.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.planetdefender.game.Spot.*;

public class Background implements Entity {
    private Sprite sprite;
    private final Vector2 position;

    public Background() {
        String background_path = "Space Sprites/Space_1.png";
        textureManager.loadTexture("background", background_path);
        TextureRegion backgroundTexture = textureManager.getTexture("background");
        sprite = new Sprite(backgroundTexture);
        sprite.setScale(2);
        position = new Vector2(200, 0);
        sprite.setPosition(position.x, position.y);
    }
    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void Update(float deltaTime) {

    }

    @Override
    public void setPosition(Vector2 position) {
        this.position.x = position.x;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void Draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }
}
