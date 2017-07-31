import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Mines {
	public int DIAMETER;
	private Game game;
	int x, xdir, y, ydir = 0;
	int count = 1;
	Vector<Part> blocks = new Vector<Part>(5);

	public Mines(Game game) {
		this.game = game;
	}

	void setSize(int d) {
		DIAMETER = d;
	}

	void move() throws InterruptedException {
		if (x + xdir < 0) // Left
			game.gameOver();
		if (x + xdir > game.getWidth()) // Right
			game.gameOver();
		if (y + ydir < 0) // Up
			game.gameOver();
		if (y + ydir > game.getHeight() - DIAMETER) // Down
			game.gameOver();
		if (collision()) {
			game.gameOver();
		}

		if (eatFood()) {

			Part temp = new Part(game.food.x, game.food.y, DIAMETER);
			blocks.add(temp);

			/*if (game.speed >= 100) {
				game.speed -= 15;
			}*/
			count += 1;
			game.hScore.setText("Score: " + (count-1));
			game.food.setFood();
		}
 
			x = x + xdir;
			y = y + ydir;
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			xdir = -DIAMETER;
			ydir = 0; 
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xdir = DIAMETER;
			ydir = 0; 
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			xdir = 0;
			ydir = -DIAMETER;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			xdir = 0;
			ydir = DIAMETER;
		}
	}

	public boolean eatFood() {
		return game.food.getBounds().intersects(getBounds());
	}

	public boolean collision() {
		for (int i = 0; i < count-1; i++) {
			if (blocks.get(i).getBounds().intersects(getBounds())) {
				return true;
			}
		}
		return false;
	}

	public void paint(Graphics2D g) {
		for (int i = 0; i < count-1; i++) {
			blocks.get(i).paint(g);
		}
		g.fillRect(x, y, DIAMETER, DIAMETER); 
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

}
