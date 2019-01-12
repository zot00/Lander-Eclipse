import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class GamePanel extends JPanel implements KeyListener {
	JFrame jframe;
	GamePanel gamepanel;
	final static int WIDTH = 500;
	final static int HEIGHT = 800;
	public static void main(String[] args) {
		GamePanel GP = new GamePanel();
		GP.setup();
	}
	GamePanel() {
		jframe=new JFrame();
		jframe.setVisible(true);
	}
	void setup() {
		gamepanel=new GamePanel();
		jframe.add(gamepanel);
		jframe.addKeyListener(gamepanel);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamepanel.startGame();
	}
	private void gameSwitch() {
		
	}
	private void startGame() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.equals("enter")) {
			gameSwitch();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
