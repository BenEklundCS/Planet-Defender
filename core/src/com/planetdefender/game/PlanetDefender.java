package com.planetdefender.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.planetdefender.game.entities.Player;
import com.planetdefender.game.entities.Background;
import com.planetdefender.game.rendering.AlienGridManager;
import com.planetdefender.game.rendering.AlienGridRenderer;
import com.planetdefender.game.rendering.TextureManager;
import com.planetdefender.game.screens.GameOverScreen;
import com.planetdefender.game.screens.GameScreen;
import static com.planetdefender.game.Spot.*;

public class PlanetDefender extends Game {
	private SpriteBatch batch;
	private Player player;
	private GameOverScreen gameOverScreen;

	@Override
	public void create () {
		textureManager = new TextureManager();
		batch = new SpriteBatch();
		player = new Player();
		AlienGridManager gridManager = new AlienGridManager(player);
		AlienGridRenderer gridRenderer = new AlienGridRenderer(gridManager, batch);
		Background background = new Background();
		GameScreen gameScreen = new GameScreen(background, player, gridRenderer, gridManager, batch);
		gameOverScreen = new GameOverScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void dispose () {
		batch.dispose();
		textureManager.dispose();
		player.dispose();
	}

	public void gameOver() {
		int kills = player.getKills();
		gameOverScreen.setKills(kills);
		setScreen(gameOverScreen);
	}
}
