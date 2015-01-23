package platformer.states.game;

import java.awt.Color;

import com.sun.glass.events.KeyEvent;

import graphics.Sprite;
import gui.GraphicsHelper;
import in.Input;
import stateBased.StateBasedGame;
import util.vectors.Vector2f;
import util.vectors.Vector2i;

public class Player {

	private static float gravity = 1f, power = 25f, sliding = 3f, friction = 0.5f;

	String dirString;
	Vector2f pos, dir;
	Sprite sprite;
	private boolean onPlatform;
	private int time;

	public Player(Vector2i spawn) {
		pos = new Vector2f(spawn);
		dir = new Vector2f(0, 0);
		sprite = new Sprite("/game/dude.png");
	}

	public boolean update(StateBasedGame stateBasedGame, Input input, Level level) {
		time++;
		onPlatform = !move(level, dir);

		if (!onPlatform) {
			dir.y += gravity;
			if (input.keyPressed[KeyEvent.VK_D]) {
				dir.x += sliding/2;
			}
			if (input.keyPressed[KeyEvent.VK_A]) {
				dir.x -= sliding/2;
			}
		} else {
			dir.y = 0;
			dir.x *= friction;
			if (input.keyPressed[KeyEvent.VK_W]) {
				dir.y = -power;
			}
			if (input.keyPressed[KeyEvent.VK_D]) {
				dir.x += sliding;
			}
			if (input.keyPressed[KeyEvent.VK_A]) {
				dir.x -= sliding;
			}
		}

		if (time % 10 == 0) {
			dirString = dir.toString();
		}
		
		return (pos.y > level.height);

	}

	private boolean move(Level level, Vector2f moveDir) {
		float length = moveDir.length();
		if (length >= 10) {
			move(level, moveDir.copy().mult(0.5f));
			return move(level, moveDir.copy().mult(0.5f));
		}
		if (moveDir.x != 0 && moveDir.y != 0) {
			move(level, new Vector2f(moveDir.x, 0));
			return move(level, new Vector2f(0, moveDir.y));
		}
		Vector2f newPos = pos.copy().add(moveDir);
		for (Platform p : level.getPlatforms()) {
			if (
					newPos.x < p.getPos().x + p.getWidth() &&
					newPos.x + sprite.getWidth() > p.getPos().x &&
					newPos.y < p.getPos().y + p.getHeight() &&
					sprite.getHeight() + newPos.y > p.getPos().y) {
				// collision detected!
				return false;
			}
		}
		pos = newPos;
		return true;
	}

	public void render(GraphicsHelper gh) {
		gh.setColor(Color.RED);
		// gh.drawRect(x, y, w, h);
		gh.drawImage(sprite, pos.toVector2i());

		gh.setColor(Color.WHITE);
		gh.drawStringRight(dirString, 10, 100, 20);
		gh.drawStringRight(((onPlatform) ? "Landed" : "Flying"), 10, 150, 20);
		
//		gh.drawRect((int) pos.x, (int) pos.y, sprite.getWidth(), sprite.getHeight());
	}

}
