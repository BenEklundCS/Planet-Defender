package com.spaceinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Sprite sprite;
    private float player_scale = 1.0f;
    private float speed = 500f;
    public Player(Texture img, Color color) {
        sprite = new Sprite(img);
        sprite.setScale(player_scale);
        position = new Vector2((float) Gdx.graphics.getWidth() /2, sprite.getScaleY() * sprite.getHeight()/2);
    }

    public void Update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) position.x -= deltaTime*speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) position.x += deltaTime*speed;
        if (position.x - (sprite.getWidth() * sprite.getScaleX()/2) <= 0)
            position.x = sprite.getWidth() * sprite.getScaleX()/2;
        if (position.x + (sprite.getWidth() * sprite.getScaleX()/2) >= Gdx.graphics.getWidth())
            position.x = (Gdx.graphics.getWidth() - sprite.getWidth() * sprite.getScaleX()/2);
    }

    public void Draw(SpriteBatch batch) {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
