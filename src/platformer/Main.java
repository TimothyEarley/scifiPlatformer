package platformer;

import graphics.Sprite;

import java.awt.Font;
import java.util.Random;

import platformer.states.Loading;
import platformer.states.game.Game;
import platformer.states.menu.Help;
import platformer.states.menu.Menu;
import stateBased.GameState;
import stateBased.StateBasedGame;

public class Main extends StateBasedGame {
	
	public static Random rand = new Random();

	
	private static final String TITLE = "Sci-fi Platformer";
	
	public static final int WIDTH = 1600, HEIGHT = 900;
	
	public static final int loadingID = 0, gameID = 1, menuID = 2, helpID = 3;

	private static Main main;
	private static Loading loading;
	private static Game game;
	private static Menu menu;
	private static Help help;

	public static void main(String[] args) {

		loading = new Loading();

		game = new Game();

		menu = new Menu();

		help = new Help();

		GameState[] gs = new GameState[] { loading, game, menu, help };
		
		main = new Main(TITLE, gs, loadingID, WIDTH, HEIGHT);
		
		main.start();

	}

	public Main(String title, GameState[] gs, int start, int width, int height) {
		super(title, gs, start, width, height);
		
		setFont("Sans", Font.PLAIN, getHeight()/10);
		
		Sprite.deferred = true;
		initAll();
	}

}
