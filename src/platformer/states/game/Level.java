package platformer.states.game;

import java.awt.Color;

import platformer.Main;
import gui.GraphicsHelper;
import util.vectors.Vector2i;

public class Level {

	
	int width, height, topPadding;
	float deltaTime, time;
	Vector2i spawnpoint;

	Platform current, next;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;

		current = addPlatform();
		next = addPlatform();
		spawnpoint = new Vector2i(current.getWidth()/2, -Platform.padding.x).add(current.getPos());

		deltaTime = 100;
		time = deltaTime;
		
		topPadding = (int) (height/2.5f);
	}

	public Vector2i getSpawn() {
		return spawnpoint;
	}

	public void update() {
		time--;
		if (time <= 0) {
			deltaTime-=0.5;
			time = deltaTime;
			current = next;
			next = addPlatform();
			}
	}

	private Platform addPlatform() {
		Vector2i pos = new Vector2i(0, 0);
		if (current != null) {
			Vector2i cPos = current.getPos();
			do {
				pos = new Vector2i(Main.rand.nextInt(width - 2 * Platform.padding.x) + Platform.padding.x, Main.rand.nextInt(height - topPadding - Platform.padding.y) + topPadding);
			} while (Math.abs(pos.x - cPos.x) < Platform.padding.x || Math.abs(pos.y - cPos.y) < Platform.padding.y);
		} else {
			pos = new Vector2i(Main.rand.nextInt(width - 2 * Platform.padding.x) + Platform.padding.x, Main.rand.nextInt(height - topPadding - Platform.padding.y) + topPadding);
		}
		return new Platform(pos);
	}

	public void render(GraphicsHelper gh) {
		float alpha = time / (float) deltaTime;
		gh.setAlpha(alpha);
		current.render(gh);
		gh.setAlpha(1 - alpha);
		next.render(gh);
		gh.setAlpha(1);
		
		gh.setColor(Color.WHITE);
		gh.drawStringRight(time + " (" + deltaTime + ")", 10, 200, 20);
	}

	public Platform[] getPlatforms() {
		return new Platform[] {current, next};
	}

}
