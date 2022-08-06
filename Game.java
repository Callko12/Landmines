import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.Dimension;


//@SuppressWarnings("serial")
public class Game extends JPanel { // implements ActionListener{

	Player player;
	Food food;
	Enemy enemy;
	JFrame frame;
	JMenuBar menuBar;
	JMenuItem diff, size, score;
    JLabel lScore, hScore;
	static int speed, speed2; // speed2 is to reset the speed
	static int fSize; // frame size
	static int sSize; // mine (part) size
    
    static int levelScore, highScore;

	//ifstream Scores;

	public static void main(String[] args) throws InterruptedException {

		/*Scores.open("Scores.txt");
		if (Scores.fail()) {
			System.out.println("Unable to read Scores file");
			System.exit(0);
		}*/
        highScore = 0;
		prompt();
		Game game = new Game(fSize, speed); 
	}


	public Game(int fS, int spd) throws InterruptedException  {

		player = new Player();
		food = new Food();
		enemy = new Enemy();
		speed = spd;

        // Creates the main frame and sets properties
		frame = new JFrame("Landmines");

        // Creates and sets the score panel at the bottom of the screen
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
			lScore = new JLabel("Score: " + (food.count-1));
			lScore.setHorizontalAlignment(SwingConstants.LEFT);
        bottom.add(lScore);
            hScore = new JLabel("High Score: " + highScore);
            hScore.setHorizontalAlignment(SwingConstants.RIGHT);
        bottom.add(hScore);
        bottom.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add("South", bottom);

        // Creates the option bar to be used for changing settings and seeing high scores
/*		menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		menuBar.add(options);

		ButtonGroup group = new ButtonGroup();

		JMenu submenu = new JMenu("Change Size");
		JRadioButtonMenuItem JRbmi;
		JRbmi = new JRadioButtonMenuItem("Easy");
		JRbmi.setSelected(true);
		group.add(JRbmi);
		submenu.add(JRbmi);
		JRbmi = new JRadioButtonMenuItem("Medium");
		group.add(JRbmi);
		submenu.add(JRbmi);
		JRbmi = new JRadioButtonMenuItem("Hard");
		group.add(JRbmi);
		submenu.add(JRbmi);

		options.add(submenu);

		size = new JMenuItem("Size");
	//	size.addActionListener(this);
		options.add(size);

		score  = new JMenuItem("High Score");
	//	score.addActionListener(this);
		options.add(score);

		frame.setJMenuBar(menuBar);
*/
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

            // Refers to "kepPressed" method on Player class
			@Override
			public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) player.kp("l");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.kp("r");
                if (e.getKeyCode() == KeyEvent.VK_UP) player.kp("u");
                if (e.getKeyCode() == KeyEvent.VK_DOWN) player.kp("d");
			}
		});
        
        frame.setSize(fS, fS+ bottom.getHeight());
        frame.add(this);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		
		setSize(this.getSize(), sSize);

		food.newFood();
		while(true) {
			move();
			repaint();
			Thread.sleep(speed);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        food.paint(g2d);
		player.paint(g2d);
		enemy.paint(g2d);

		/*g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(player.count-1), 10, 30);*/
	}

	/*public void actionPerformed(ActionEvent e) {
		Object b = e.getSource();

		if (b == size) {
			
		}
		if (b == score) {
			
		}		
		
	}*/

	public void setSize(Dimension d, int s) {
		player.setSize(d, s);
		food.setSize(d, s);
		enemy.setSize(d, s);
	}

    static void prompt() {
        Object[] poss = {"easy", "medium", "hard"};
        String s = (String)JOptionPane.showInputDialog(null, "Choose Level", "Choose Level", JOptionPane.PLAIN_MESSAGE, null, poss, "easy");
        
        if (s == "easy") {
            fSize = 200;
            sSize = 10;
            speed = 175;
        }
        else if (s == "medium") {
            fSize = 200;
            sSize = 20;
            speed = 175;
        }
        else if (s == "hard") {
            fSize = 200;
            sSize = 25;
            speed = 175;
        }
        else System.exit(-1);
        speed2 = speed;
    }
    
	// Do I need gameOver() and playAgain() both?
	// Why do I use getWidth() and getHeight()? Where are they used?
	public void gameOver() throws InterruptedException  {
        levelScore = food.count;
        if (levelScore > highScore) highScore = levelScore;
		playAgain(this.getWidth(), this.getHeight(), "Game Over");
	}
    
    public void playAgain(int rr, int cc, String WoL) throws InterruptedException  {
        
        int result = JOptionPane.showConfirmDialog(null, "Replay?",
                                                   WoL, JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            this.frame.dispose();
            Game game = new Game(fSize, speed2);
        }
        
        if (result == JOptionPane.CANCEL_OPTION) {
            this.frame.dispose();
            prompt();
            Game game = new Game(fSize, speed2);
        }
        
        else System.exit(0);
    }

	/* Component Logistics */

	private void move() throws InterruptedException {
		int pMove = player.move();
		int eMove = enemy.move(player.getLocation());
		int[] fCheck = food.checkCollision(player.getBounds(), enemy.getBounds(), getFrameSize());

		if ((fCheck[0] == -1) || (pMove == -1)) 
			this.gameOver();
		if (fCheck[1] == -1) 
			enemy.reset();

		lScore.setText("Score: " + fCheck[2]);
	} 

	public int[] getFrameSize() {
		int[] ret = {this.getWidth(), this.getHeight()};
		return ret;
	}

}

