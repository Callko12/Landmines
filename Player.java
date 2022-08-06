import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Player {
	private Game game;
	public int DIAMETER;
	int x, xdir, y, ydir = 0;

	public Player(Game game) {
		this.game = game;
        	// Currently, arbitrary coordinates for where Player starts on screen
        	x = 80;
        	y = 80;
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

	public int[] getLocation() {
		int[] arr={x,y};
		return arr;
	}
    
    public void kp(String e) {
        if (e == "l") {
            xdir = -DIAMETER;
            ydir = 0;
        }
        if (e == "r") {
            xdir = DIAMETER;
            ydir = 0;
        }
        if (e == "u") {
            xdir = 0;
            ydir = -DIAMETER;
        }
        if (e == "d") {
            xdir = 0;
            ydir = DIAMETER;
        }
    }

	int move() throws InterruptedException {
		if ((x + xdir < 0) // Left
		|| (x + xdir > game.getWidth() - DIAMETER) // Right
		|| (y + ydir < 0) // Up
		|| (y + ydir > game.getHeight() - DIAMETER)) // Down 
		{
			return -1;
		}
 
		x = x + xdir;
		y = y + ydir;

		return 0;
	}

}
