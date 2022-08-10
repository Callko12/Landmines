package landmines.handler;

import java.lang.InterruptedException;
import landmines.app.Game;


public class Handler {
    public void landminesHandler() throws InterruptedException {
        Game game = new Game();

        game.highScore = 0;
		game.prompt();
		game = new Game(game.fSize, game.speed); 
    }
}