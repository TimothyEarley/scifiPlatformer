package platformer.states.game;

import graphics.Sprite;
import gui.GraphicsHelper;
import platformer.Main;
import util.vectors.Vector2i;

public class Platform {
	
	private static Sprite platform = new Sprite("/game/platform.png");
	private static int maxWidth = 300;
	public static Vector2i padding = new Vector2i(maxWidth, maxWidth / 4);
	
	private Sprite sprite;
	private Vector2i pos;
	private int width, height;
	
	public Platform(Vector2i pos) {
		this.pos = pos;
		width = Main.rand.nextInt(maxWidth/2) + maxWidth/2;
		sprite = new Sprite(platform.getScaled(width / (float) platform.getWidth()));
		height = sprite.getHeight();
	}

	public void render(GraphicsHelper gh) {
		gh.drawImage(sprite, pos);
	}

	public Vector2i getPos() {
		return pos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
