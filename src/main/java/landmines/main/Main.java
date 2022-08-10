package landmines.main;

import java.awt.*;
import landmines.app.Game;

public class Main {

    private static int highScore = 0;

	public static void main(String[] args) throws InterruptedException {

		Game game = new Game();

        game.highScore = highScore;
		game.prompt();
		new Game(game.fSize, game.speed); 
	}
}