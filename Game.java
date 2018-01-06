import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;


//@SuppressWarnings("serial")
public class Game extends JPanel { // implements ActionListener{

	Player player;
	Food food;
	JFrame frame;
	JMenuBar menuBar;
	JMenuItem diff, size, score;
	JLabel hScore;
	static int speed, speed2; // speed2 is to reset the speed
	static int fSize; // frame size
	static int sSize; // mine (part) size

	//ifstream Scores;

	public static void main(String[] args) throws InterruptedException {

		/*Scores.open("Scores.txt");
		if (Scores.fail()) {
			System.out.println("Unable to read Scores file");
			System.exit(0);
		}*/
		prompt();
		Game game = new Game(fSize, speed); 
	}


	public Game(int fS, int spd) throws InterruptedException  {

		player = new Player(this);
		food = new Food(this);
		speed = spd;

		frame = new JFrame("Landmines");
		frame.getContentPane().add(this);
		frame.setSize(fS, fS);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
			hScore = new JLabel("Score: " + (player.count-1));
			hScore.setHorizontalAlignment(SwingConstants.CENTER);
		bottom.add(hScore);
		frame.add("South", bottom);

		menuBar = new JMenuBar();
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

		/*options.add(submenu);

		size = new JMenuItem("Size");
		size.addActionListener(this);
		options.add(size);

		score  = new JMenuItem("High Score");
		score.addActionListener(this);
		options.add(score);

		frame.setJMenuBar(menuBar);*/

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
                                player.keyTyped(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				player.keyPressed(e);
			}
		});
		setFocusable(true);

		setSize(sSize);
		food.setFood();

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
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 					RenderingHints.VALUE_ANTIALIAS_ON);
		player.paint(g2d);
		food.paint(g2d);

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

	public void setSize(int s) {
		player.setSize(s);
		food.setSize(s);
	}

	private void move() throws InterruptedException {
		player.move();
	}

	// Do I need gameOver() and playAgain() both?
	// Why do I use getWidth() and getHeight()? Where are they used?
	public void gameOver() throws InterruptedException  {
		playAgain(this.getWidth(), this.getHeight(), "Game Over");
	}

}
