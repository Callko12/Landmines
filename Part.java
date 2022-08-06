import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.Dimension;

public class Part {
	int[] screenDimension = {0,0};
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

	public void setSize(Dimension s, int d) {
		DIAMETER = d;
		screenDimension[0] = s.width;
		screenDimension[1] = s.height;
	}

	public void setRandLocation() {
		x = (int) (rand.nextInt(screenDimension[0] - DIAMETER)/DIAMETER) * DIAMETER;
		y = (int) (rand.nextInt(screenDimension[1] - DIAMETER)/DIAMETER) * DIAMETER;
		System.out.println("frame: "  + screenDimension[0] + " " + screenDimension[1]);
		System.out.println("New block: "  + this.getLocation()[0] + " " + this.getLocation()[1]);
	}

}
