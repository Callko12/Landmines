package landmines.app;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.*;

public class Food extends Part{
	Random rand = new Random();
    int count = 0;
    int startSize = 5;
    Vector<Part> blocks = new Vector<Part>(5);

	public Food() {
	}

    @Override   
	public void paint(Graphics2D g) {
        super.paint(g);
        for (Part block: blocks) {
            block.paint(g);
        }
		g.setColor(Color.green);
		g.fillRect(x, y, DIAMETER, DIAMETER);
	}
    
    
	public void newFood() {
		setRandLocation();

        // If coordinates already exist in block array, reset
        for (Part block: blocks) {
			if ((x == block.x) && (y == block.y)) {
				newFood();
                break;
			}
		}
	}

    public void newFood(int c) {
        while (c > 0) {
            newFood();
            Part temp = new Part(x, y, DIAMETER);
            blocks.add(temp);
            c --;
        }
    }

    public int[] checkCollision(Rectangle pBlock, Rectangle eBlock) {
        int[] resultingActions = {0,0,count};

        for (Part block: blocks) {
            if (pBlock.intersects(block.getBounds())) { // if player collides with solid blocks
                resultingActions[0] = -1;
            }
            if (eBlock.intersects(block.getBounds())) { // if enemy collides with solid blocks
                resultingActions[1] = -1;
            }
        }

        if (eBlock.intersects(pBlock.getBounds())) { // if enemy collides with player
            resultingActions[1] = -2;
        }

        if (pBlock.intersects(getBounds())) { // if player collides with highlighted block
            Part temp = new Part(x, y, DIAMETER);
            blocks.add(temp);
            /* if (game.speed >= 100) {
                game.speed -= 15;
                }*/
            count += 1;
            resultingActions[2] = count;
            newFood();
        }

        return resultingActions;
    }
}