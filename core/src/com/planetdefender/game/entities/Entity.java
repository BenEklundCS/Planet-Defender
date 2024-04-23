package com.planetdefender.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
    void Draw(SpriteBatch batch);
    Vector2 getPosition();
    Sprite getSprite();
    void Update(float deltaTime);
    void setPosition(Vector2 position);
    void setSprite(Sprite sprite);
}
