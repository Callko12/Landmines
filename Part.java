import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Dimension;

public class Part {
	Random rand = new Random();
	int maxWidth, maxHeight = 0;
	int DIAMETER; 
	int x, y = 0;
	

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
        g.setColor(Color.red);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public void setSize(int d) {
		DIAMETER = d;
	}

	public void setSize(Dimension s, int d) {
		DIAMETER = d;
		maxWidth = s.width - DIAMETER;
		maxHeight = s.height - DIAMETER;
	}

	public int[] getLocation() {
		int[] arr={x,y};
		return arr;
	}

	public void setRandLocation() {
		x = (int) (rand.nextInt(maxWidth)/DIAMETER) * DIAMETER;
		y = (int) (rand.nextInt(maxHeight)/DIAMETER) * DIAMETER;
	}

}
