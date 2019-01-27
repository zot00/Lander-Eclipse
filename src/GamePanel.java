
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
		timer = new Timer(1000 / 100, this);
		titleFont = new Font("Arial", Font.PLAIN, 36);
	}

	void startGame() {
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		drawGameState(g);
	}

	public void updateGameState() {
		OM.update();
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Lander.WIDTH, Lander.HEIGHT);
		g.setColor(Color.RED);
		g.drawString(OM.getScore() + "", 10, 10);
		OM.draw(g);
	}

	public void drawEndState(Graphics g) {
	}
	public ObjectManager getObjectManager () {
		return OM;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}