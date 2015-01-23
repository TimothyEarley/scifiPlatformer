package platformer.states.game;

import graphics.Sprite;
import gui.GraphicsHelper;
import in.Input;
import platformer.Main;
import stateBased.GameState;
import stateBased.StateBasedGame;
import util.vectors.Vector2f;

public class Game extends GameState {

	Sprite bg, mask;
	int bgw, bgh;
	Vector2f bgDir;
	Player player;
	Level level;

	@Override
	public void render(StateBasedGame game, GraphicsHelper gh) {
		renderBack(game, gh);
		level.render(gh);
		player.render(gh);
	}

	private void renderBack(StateBasedGame game, GraphicsHelper gh) {
		gh.drawImage(bg, 0, 0, game.getWidth(), game.getHeight());

		bgw += bgDir.x;
		bgDir.x += Main.rand.nextFloat() - 0.5f;
		if (bgw > 3 * game.getWidth())
			bgDir.x -= 0.5;
		else if (bgw < 2 * game.getWidth())
			bgDir.x += 0.5;
		
		bgh += bgDir.y;
		bgDir.y += Main.rand.nextFloat() - 0.5f;
		if (bgh > 3 * game.getHeight())
			bgDir.y -= 0.5;
		else if (bgh < 2 * game.getHeight())
			bgDir.y += 0.5;

		gh.drawImage(mask, 0, 0, bgw, bgh);
	}

	@Override
	public void update(StateBasedGame stateBasedGame, Input input) {
		level.update();
		if (player.update(stateBasedGame, input, level)) {
			restart();
		}
	}

	private void restart() {
		level = new Level(Main.WIDTH, Main.HEIGHT);
		player = new Player(level.getSpawn());
		
	}

	@Override
	public void init(StateBasedGame game) {
		bg = new Sprite("/game/bg.png");
		bgw = (int) (2.5 * game.getWidth());
		bgh= (int) (2.5 * game.getHeight());
		bgDir = new Vector2f(0, 0);
		mask = new Sprite("/game/mask.png");
		restart();
	}

}
