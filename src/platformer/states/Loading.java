package platformer.states;

import java.awt.Color;

import graphics.Sprite;
import gui.GraphicsHelper;
import in.Input;
import platformer.Main;
import stateBased.GameState;
import stateBased.StateBasedGame;

public class Loading extends GameState {

	private int loaded, total;
	
	private int width = 800, height = 200, border = 10;
	
	private String loadingObject; 
	
	/**
	 * Monitors, if we have rendered twice in order to show the screen first and then load
	 */
	private boolean renderOne, renderTwo;

	@Override
	public void render(StateBasedGame game, GraphicsHelper gh) {

		if (renderOne)
			renderTwo = true;
		else
			renderOne = true;
		
		gh.setColor(Color.WHITE);
		gh.drawRect(game.getWidth()/2 - width/2, game.getHeight()/2 - height/2, width, height);
		
		gh.setColor(Color.RED);
		gh.fillRect(game.getWidth()/2 - width/2 + border, game.getHeight()/2 - height/2 + border, ((width - 2 * border) * loaded) / total, height - 2 * border);
		
		gh.setColor(Color.WHITE);
		gh.drawStringCenteredTop("Loading " + loadingObject + " ... ", game.getWidth()/2, game.getHeight()/2 + height/2 + 2 * border, game.getHeight()/30);
		gh.drawStringCenteredTop((loaded * 100 / total) + "%", game.getWidth()/2, game.getHeight()/2 + height/2 + 3 * border + game.getHeight()/30, game.getHeight()/50);
	}

	@Override
	public void update(StateBasedGame stateBasedGame, Input input) {

		if (!renderTwo)
			return;
		
		Sprite s = Sprite.loadNext();
		loadingObject = s.getPath();
		
		if (Sprite.leftToLoad() > 0) {
			loaded++;
		} else {
			stateBasedGame.enter(Main.gameID);
		}

	}

	@Override
	public void init(StateBasedGame game) {

		loaded = 0;
		
		total = Sprite.leftToLoad();
		
	}

}
