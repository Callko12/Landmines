import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class Part {
	static int screenSize;
	Random rand = new Random();
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

	public int[] getLocation() {
		int[] arr={x,y};
		return arr;
	}

	public void setSize(int d) {
		DIAMETER = d;
	}

	public void setRandLocation(int w, int h) {
		x = (int) (rand.nextInt(w - DIAMETER)/DIAMETER) * DIAMETER;
		y = (int) (rand.nextInt(h- DIAMETER)/DIAMETER) * DIAMETER;
		System.out.println("frame: "  + w + " " + h);
		System.out.println("New block: "  + this.getLocation()[0] + " " + this.getLocation()[1]);
	}

}
