package com.planetdefender.game.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class BoundaryChecker {
    private final float leftBound;
    private final float rightBound;

    public BoundaryChecker(float leftBound, float rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public boolean isOutOfLeftBound(Vector2 position, float width) {
        return position.x - width <= leftBound;
    }

    public boolean isOutOfRightBound(Vector2 position, float width) {
        return position.x + width >= rightBound;
    }

    public boolean isOutOfTopBound(Vector2 position, float height) {
        return position.y + height >= Gdx.graphics.getHeight();
    }

    public boolean isOutOfBottomBound(Vector2 position, float height) {
        return position.y <= 0;
    }
}
