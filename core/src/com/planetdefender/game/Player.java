package com.planetdefender.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Vector2 position_bullet;
    private Sprite sprite;
    private Sprite sprite_bullet;
    private float speed = 500f;
    private float bullet_speed = 1200.0f;
    private final float bullet_offset = -20.0f;
    private final float player_scale = 0.5f;
    private final float bullet_scale = 2.0f;
    private int kills = 0;

    public Player(Texture img) {
        sprite = new Sprite(img);
        sprite.setScale(player_scale);
        sprite_bullet = new Sprite(new Texture("blue.png"));
        sprite_bullet.setScale(bullet_scale);
        position = new Vector2((float) Gdx.graphics.getWidth()/2 - sprite.getWidth() * sprite.getScaleX()/2, sprite.getScaleY());
        position_bullet = new Vector2(0, 10000); // just start the bullet offscreen somewhere
    }

    public void Update(float deltaTime) { // pass deltaTime to any "move" methods to determine how far they should move.
        // Bullet reset (fire on SPACEBAR)
        if (bulletShouldReset()) resetBullet();
        // Player movement
        if (playerInputA()) movePlayerLeft(deltaTime); // move left on A
        if (playerInputD()) movePlayerRight(deltaTime); // move right on D
        // Player boundaries
        if (hitLeftBound()) leftBound(); // left bound
        if (hitRightBound()) rightBound();// right bound
        // Bullet movement
        moveBullet(deltaTime);
    }

    public void Draw(SpriteBatch batch) {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x - sprite.getWidth() * sprite.getScaleX()/2, position.y);
        sprite.draw(batch);
        sprite_bullet.setPosition(position_bullet.x, position_bullet.y);
        sprite_bullet.draw(batch);
    }

    private boolean hitLeftBound() {
        return position.x - (sprite.getWidth() * sprite.getScaleX()/2) <= 0;
    }

    private boolean hitRightBound() {
        return position.x + (sprite.getWidth() * sprite.getScaleX()/2) >= Gdx.graphics.getWidth();
    }

    private boolean bulletShouldReset() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && position_bullet.y >= Gdx.graphics.getHeight();
    }

    private boolean playerInputA() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    private boolean playerInputD() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    private void leftBound() {
        position.x = sprite.getWidth() * sprite.getScaleX()/2;
    }

    private void rightBound() {
        position.x = Gdx.graphics.getWidth() - sprite.getWidth() * sprite.getScaleX()/2;
    }

    private void moveBullet(float deltaTime) {
        position_bullet.y += deltaTime * bullet_speed;
    }

    private void resetBullet() {
        position_bullet.x = position.x - bullet_offset;
        position_bullet.y = position.y + sprite.getHeight() + bullet_offset * 2;
    }

    private void movePlayerLeft(float deltaTime) {
        position.x -= deltaTime*speed;
    }

    private void movePlayerRight(float deltaTime) {
        position.x += deltaTime*speed;
    }

    public void setKills() {
        this.kills++;
    }

    public int getKills() {
        return kills;
    }

    public Vector2 getPosition_bullet() {
        return position_bullet;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Sprite getSprite_bullet() {
        return sprite_bullet;
    }
}
