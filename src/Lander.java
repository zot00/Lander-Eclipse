
import java.awt.Dimension;
import javax.swing.*;
public class Lander {
	JFrame jframe;
	GamePanel gamepanel;
	final static int WIDTH = 1600;
	final static int HEIGHT = 1200;
	public static void main(String[] args) {
		Lander LI = new Lander();
		LI.setup();
	}
	Lander(){
		jframe=new JFrame();
		jframe.setVisible(true);
	}
	void setup() {
		gamepanel=new GamePanel();
		jframe.add(gamepanel);
		jframe.addKeyListener(gamepanel.getObjectManager());
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setVisible(true);
		jframe.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamepanel.startGame();
	}
}