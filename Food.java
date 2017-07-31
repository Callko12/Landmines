import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;

public class Food {
	int DIAMETER;
	int x = 0;
	int y = 0;
	private Game game;
	Random rand = new Random();

	public Food(Game game) {
		this.game = game;
	}

	void setSize(int d) {
		DIAMETER = d;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public void setFood() {
		x = (int) (rand.nextInt(game.getWidth() - DIAMETER)/DIAMETER) * DIAMETER;
		y = (int) (rand.nextInt(game.getHeight() - DIAMETER)/DIAMETER) *DIAMETER;

		for (int i = 0; i < game.mines.count-1; i++) {
			if ((x == game.mines.blocks.get(i).x) && (y == game.mines.blocks.get(i).y)) {
				setFood();
			}
		}
	}

}
