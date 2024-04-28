package com.planetdefender.game.entities;
import static com.planetdefender.game.Spot.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alien implements Entity {
    private final Vector2 position;
    private Sprite sprite;
    public boolean alive = true;
    private List<Bullet> bullets = new ArrayList<>();
    public Alien(Vector2 position, TextureRegion alienTexture) {
        this.position = position;
        sprite = new Sprite(alienTexture);
        sprite.scale(10f);

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
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
