package com.planetdefender.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import static com.planetdefender.game.Spot.*;
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Planet Defender");
		config.setWindowedMode(WIN_WIDTH, WIN_HEIGHT);
		new Lwjgl3Application(new PlanetDefender(), config);
	}
}
