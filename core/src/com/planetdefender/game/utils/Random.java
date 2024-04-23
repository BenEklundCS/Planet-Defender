package com.planetdefender.game.utils;

import com.badlogic.gdx.math.Vector2;

public class Random {
    public static Vector2 randomVector(int x, int y) {
        return new Vector2((float) Math.random() * x, (float) Math.random() * y);
    }
    public static Vector2 randomVector(int x_min, int x_max, int y_min, int y_max) {
        return new Vector2((float) Math.random() * (x_max - x_min) + x_min, (float) Math.random() * (y_max - y_min) + y_min);
    }
}
