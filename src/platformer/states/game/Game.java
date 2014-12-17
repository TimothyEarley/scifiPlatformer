package platformer.states.game;

import graphics.Sprite;
import gui.GraphicsHelper;
import in.Input;
import stateBased.GameState;
import stateBased.StateBasedGame;

public class Game extends GameState {
	
	Sprite bg;

	@Override
	public void render(StateBasedGame game, GraphicsHelper gh) {
		gh.drawImage(bg, 0, 0, game.getWidth(), game.getHeight());
	}

	@Override
	public void update(StateBasedGame stateBasedGame, Input input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(StateBasedGame game) {
		bg = new Sprite("/game/bg.png");
	}

}
