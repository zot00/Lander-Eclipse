
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements KeyListener {
	long enemyTimer = 0;
	int enemySpawnTime = 500;
	public int score = 0;
	float x = 240; // x position of the player
	float y = 80; // y position of the player
	float ythrust = 0; // the thrust in the y direction
	float ythrustCoefficient = 0.25f; // ? \\
	float xthrust = 0; // the thrust in the x direction
	float xthrustCoefficient = 0.25f; // ? \\
	float gravity = 0.1f; // gravity affecting the player's robot
	float gravityCoefficient = 0.1f; // ? \\
	float velocity = 0; // the speed of acceleration
	float velocityCoefficient = 0.2f; // ? \\
	float rotate = 0; // placeholder of how much the robot rotates
	float degrees; // the amount of degrees to rotate
	float airResistance = 0; // Resistance for the robot in the x direction

	Random gen = new Random();
	int randY = (int) gen.nextInt(Lander.HEIGHT - 10); // controls the y values of the obstacles & landing pad
	int randX = (int) gen.nextInt(Lander.WIDTH - 10); // controls the x values of the obstacles & landing pad
	int rand1 = (int) gen.nextInt(255); // partially controls the colors of the obstacles
	int rand2 = (int) gen.nextInt(255); // partially controls the colors of the obstacles
	int rand3 = (int) gen.nextInt(255); // partially controls the colors of the obstacles
	int rand4 = (int) gen.nextInt(255); // partially controls the colors of the obstacles
	int rand5 = (int) gen.nextInt(255); // partially controls the colors of the obstacles
	int rand6 = (int) gen.nextInt(255); // partially controls the colors of the obstacles
	int trailCount = 0; // amount of objects in the trail
	int frameCount = 0; // amount of frames past in the game

	boolean willRotate = false; // determines if the player will rotate
	boolean isLanded = false; // determines if the player is landed

	ArrayList<Point> points = new ArrayList<Point>(); // controls how many obstacles there are and their positions
	ArrayList<LandingPad> landingPad = new ArrayList<LandingPad>(); // controls the position of the landing pad
	ArrayList<Trail> trail = new ArrayList<Trail>();

	ObjectManager() {
		// size(480, 270);
		for (int i = 0; i <= gen.nextInt(50000); i++) {
			randY = (int) gen.nextInt(Lander.HEIGHT - 10);
			randX = (int) gen.nextInt(Lander.WIDTH - 10);
			points.add(new Point(randX, randY));
		}
		for (int i = 0; i <= 1; i += (int) gen.nextInt(5)) {
			randY = (int) gen.nextInt(Lander.HEIGHT - 10);
			randX = (int) gen.nextInt(Lander.WIDTH - 50);
			landingPad.add(new LandingPad(randX, randY));
		}
	}

	public void update() {

	}

	public void draw(Graphics g) {
		frameCount += 1;
		g.setColor(Color.black);
		g.fillRect(0, 0, Lander.WIDTH, Lander.HEIGHT);
		if (frameCount % 2 == 0) {
			trailCount += 1;
		}
		for (int i = 0; i < trailCount; i++) {
			g.setColor(new Color(i % 255, frameCount % 255, 100));
		}
//		  pushMatrix();
//		  translate(x+5, y+5);
//		  rotate(rotate);
		// Starting position of the rocket
		g.fillRect(-Lander.WIDTH / 2, -5, 10, 10);
//		  popMatrix();
		// rotate*1/(2(PI)) = degrees
		g.setColor(new Color(gen.nextInt(Math.abs(rand2 - rand1)) + rand1, gen.nextInt(Math.abs(rand4 - rand3)) + rand3, gen.nextInt(Math.abs(rand6 - rand5) + rand5)));
		for (int i = 0; i < points.size(); i++) {
			g.fillOval(points.get(i).x, points.get(i).y, 10, 10);
		}
		System.out.println("5*cos: " + 5 * Math.cos(rotate) + ", 5*sin: " + 5 * Math.sin(rotate));
		float Gincrementer = 6.5f;
		if (gravity >= Gincrementer) {
			gravity = Gincrementer;
		} // note to self: a 1.75:1 ratio of thrust to gravity is a nice combo
		float incrementer = Gincrementer * 1.75f;
		if (ythrust >= incrementer) {
			ythrust = incrementer;
		}
		if (isLanded == false) {
			gravity += gravityCoefficient;
			y += gravity;
			y -= ythrust;
			x += xthrust;
		}
		if (xthrust > 0) {
			xthrust -= xthrust / 65;
		} else if (xthrust < 0) {
			xthrust += -xthrust / 65;
		}

		if (y >= Lander.HEIGHT - 10) { // <- Here, I will check for collision
			KillPlayer();
		}
		if (x >= Lander.WIDTH - 10) {
			KillPlayer();
		}
		if (x <= 0) {
			KillPlayer();
		}
		if (y <= 0) {
			KillPlayer();
		}
		for (int i = 0; i < points.size(); i++) {
			if (x + 5 >= points.get(i).x - 5 && x + 5 <= points.get(i).x + 5 && y + 5 >= points.get(i).y - 5
					&& y + 5 <= points.get(i).y + 5) {
				KillPlayer();
			}
		}

		g.setColor(new Color(0, 255, 0));
		for (int i = 0; i < 1; i += (int) gen.nextInt(5)) {
			g.fillRect(landingPad.get(i).x, landingPad.get(i).y, 50, 10);
			// if(x+w>lp.x && x+w<lp.x+lp.w && y+h>lp.y && y<lp.y+lp.h) {
			// x=lp.x-w;
			// }
			// x+5 > landingPad.get(i).x && x-5 < landingPad.get(i).x+50 && y+5 >
			// landingPad.get(i).y && y-5 < landingPad.get(i).y
			if (Collision((int) x, (int) y, (int) landingPad.get(i).x, (int) landingPad.get(i).y, 10, 10, 100,
					10) == true) {
				isLanded = true;
				willRotate = false;
				trail = new ArrayList<Trail>();
			} else if (x + 5 > landingPad.get(i).x && x - 5 < landingPad.get(i).x + 50 && y + 5 > landingPad.get(i).y
					&& y - 5 < landingPad.get(i).y + 10) {
				KillPlayer();
			}
		}

		trail.add(new Trail((int) this.x, (int) this.y));
		if (trail.size() > 300) {
			trail.remove(0);
		}
		for (int i = 0; i < trail.size(); i++) {
			g.setColor(new Color(i % 255, frameCount % 255, 100));
			g.fillRect(trail.get(i).x, trail.get(i).y, 10, 10);
		}
		if (trail.size() > 100) {
			for (int i = 0; i < trail.size() - 10; i++) {
				if (Collision((int) x, (int) y, trail.get(i).x, trail.get(i).y, 10, 10, 10, 10)) {
					KillPlayer();
				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	void KillPlayer() {
		// gravity = 0;
		ythrust = 0;
		xthrust = 0;
		ythrustCoefficient = 0;
		xthrustCoefficient = 0;
		gravityCoefficient = 0;//
		y = 20;
		x = Lander.WIDTH / 2;
		trail = new ArrayList<Trail>();
	}

	boolean Collision(int x1, int y1, int x2, int y2, int sx1, int sy1, int sx2, int sy2) {
		// x1 - The x position of object 1
		// y1 - the y position of object 1
		// x2 - the x position of object 2
		// y2 - the y position of object 2
		// sx1 - the width of object 1
		// sy1 - the height of object 1
		// sx2 - the width of object 2
		// sy2 - the height of object 2
		if (x1 + sx1 / 2 > x2 && x - sx1 / 2 < x2 + sx2 / 2 && y1 + sy1 / 2 > y2 && y - sy1 / 2 < y2 + sy2 / 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'w' && isLanded == false) {
			// ythrust += ythrustCoefficient;
			// xthrust += xthrustCoefficient;
			ythrust += (Math.cos(rotate) * ythrustCoefficient);
			xthrust += (Math.sin(rotate) * xthrustCoefficient);
			System.out.println("Thrusts: (" + xthrust + ", " + ythrust + ")");
		} else {
			// xthrustCoefficient -= 0.1;
			if (ythrust > 0) {
				ythrust -= 0.1;
			}
			// if (xthrust > 0) {
			xthrust -= 0.1;
			// }//
		}
		if (e.getKeyChar() == 'a' && isLanded == false) {
			willRotate = true;
			rotate -= 0.05;
		}
		if (e.getKeyChar() == 'd' && isLanded == false) {
			willRotate = true;
			rotate += 0.05;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}