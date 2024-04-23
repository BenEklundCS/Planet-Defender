package com.planetdefender.game.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {
    public boolean isLeftPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    public boolean isRightPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    public boolean isSpaceJustPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    public boolean isEscapeJustPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
    }
}
