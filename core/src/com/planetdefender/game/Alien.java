package com.planetdefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
public class Alien {
    // Don't care if these are changed elsewhere, the aliens are individual and their state is public
    public Vector2 position;
    public Vector2 initial_position;
    public Sprite sprite;
    public boolean alive = true;
    public Alien(Vector2 position, Texture img) {
        this.position = position;
        initial_position = position;
        sprite = new Sprite(img);
        sprite.scale(0.002f);

    }
    public void Draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
