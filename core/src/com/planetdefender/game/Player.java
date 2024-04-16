package com.planetdefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Vector2 position_bullet;
    public Sprite sprite;
    public Sprite sprite_bullet;
    public float speed = 500f;
    public float bullet_speed = 1200.0f;
    private final float bullet_offset = 16.0f;
    private final float player_scale = 0.5f;
    private final float bullet_scale = 2.0f;

    public Player(Texture img, Texture img_bullet) {
        sprite = new Sprite(img);
        sprite.setScale(player_scale);
        sprite_bullet = new Sprite(img_bullet);
        sprite_bullet.setScale(bullet_scale);
        position = new Vector2((float) Gdx.graphics.getWidth()/2 - sprite.getWidth() * sprite.getScaleX()/2, sprite.getScaleY());
        position_bullet = new Vector2(0, 10000); // just start the bullet offscreen somewhere
    }

    public void Update(float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && position_bullet.y >= Gdx.graphics.getHeight()) {
            position_bullet.x = position.x - bullet_offset;
            position_bullet.y = position.y + sprite.getHeight();
        }
        // Player movement
        if (Gdx.input.isKeyPressed(Input.Keys.A)) position.x -= deltaTime*speed; // move left on A
        if (Gdx.input.isKeyPressed(Input.Keys.D)) position.x += deltaTime*speed; // move right on D
        if (position.x - (sprite.getWidth() * sprite.getScaleX()/2) <= 0) // left bound
            position.x = sprite.getWidth() * sprite.getScaleX()/2;
        if (position.x + (sprite.getWidth() * sprite.getScaleX()/2) >= Gdx.graphics.getWidth()) // right bound
            position.x = Gdx.graphics.getWidth() - sprite.getWidth() * sprite.getScaleX()/2;
        // Bullet movement
        position_bullet.y += deltaTime * bullet_speed;
    }

    public void Draw(SpriteBatch batch) {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x - sprite.getWidth() * sprite.getScaleX()/2, position.y);
        sprite.draw(batch);
        sprite_bullet.setPosition(position_bullet.x, position_bullet.y);
        sprite_bullet.draw(batch);
    }
}
