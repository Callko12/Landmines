import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Part {
	int DIAMETER;
	int x, y, xa, ya = 0;
	

	public Part() {
		x = 0;
		y = 0;
		DIAMETER = 0;
	}

	public Part(int xa, int ya, int d) {
		x = xa;
		y = ya;
		DIAMETER = d;
	}


	public void paint(Graphics2D g) {
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

}
