import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Enemy extends Part{
	int xdir, ydir = 0;

	public Enemy() {
		// Currently, arbitrary coordinates for where Enemy starts on screen
		x = 0;
		y = 0;
	}
    
    public void paint(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(x, y, DIAMETER, DIAMETER);
    }

	int move(int[] target) throws InterruptedException {

		int targetX = target[0];
        int targetY = target[1];
		
		if (x - targetX < 0) { // If player to right of enemy
			x = x + DIAMETER/2;
		} else x = x - DIAMETER/2;

		if (y - targetY > 0) { // If player to below enemy
			y = y - DIAMETER/2;
		} else y = y + DIAMETER/2;

		x = x + xdir;
		y = y + ydir;

		return 0;
	}

	public void reset() {
		x = 0;
		y = 0;
	}

}
