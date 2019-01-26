

import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Font titleFont;
	// GameObject GO = new GameObject(10, 10, 100, 100);
	ObjectManager OM = new ObjectManager();

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		// GO.update();
		repaint();
		updateGameState();
	}

	GamePanel() { /*--------------------CONSTRUCTOR--------------------*\                                         																																																																				*/
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 36);
	}

	void startGame() {
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		drawGameState(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
		}
	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		OM.update();
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setFont(titleFont);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Lander.WIDTH, Lander.HEIGHT);
		g.setColor(Color.YELLOW);
		g.drawString("VEGETARIAN V. PIGS", 10, 100);
		g.drawString("Control character with arrows", 0, 200);
		g.drawString("Shoot with space", 0, 250);
		OM.score = 0;
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Lander.WIDTH, Lander.HEIGHT);
		g.setColor(Color.RED);
		g.drawString(OM.getScore() + "", 10, 10);
		OM.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.fillRect(0, 0, Lander.WIDTH, Lander.HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 125, 100);
		g.drawString("You killed " + OM.getScore() + " Enemies", 50, 150);
		g.drawString("Press ENTER to restart", 50, 500);
	}
}