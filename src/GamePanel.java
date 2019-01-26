import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Component.*;

public class GamePanel implements ActionListener {
	JFrame jframe = new JFrame();
	JPanel jpanel = new JPanel();
	Timer timer;
	static int x = 10;
	static int y = 10;
	public static void main(String[] args) {
		//background(0);
		  strokeWeight(0);
		  pushMatrix();
		  translate(x+5, y+5);
		  rotate(rotate);
		  //Starting position of the rocket
		  g.drawRect(x, y, 10, 10);
		  popMatrix();
		  //rotate*1/(2(PI)) = degrees
		  fill(random(rand1, rand2), random(rand3, rand4), random(rand5, rand6));
		  for (int i = 0; i < points.size(); i++) {
		    ellipse(points.get(i).x, points.get(i).y, 10, 10);
		  }
		  println("5*cos: " + 5*cos(rotate) + ", 5*sin: " + 5*sin(rotate));
		  float Gincrementer = 6.5;
		  if (gravity >= Gincrementer) {
		    gravity = Gincrementer;
		  } //note to self: a 1.75:1 ratio of thrust to gravity is a nice combo
		  float incrementer = Gincrementer*1.75;
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
		    xthrust -= xthrust/65;
		  } else if (xthrust < 0) {
		    xthrust += -xthrust/65;
		  }
		  if (keyPressed == true && key == 'w' && isLanded == false) {
		    //ythrust += ythrustCoefficient;
		    //xthrust += xthrustCoefficient;
		    ythrust += (cos(rotate)*ythrustCoefficient);
		    xthrust += (sin(rotate)*xthrustCoefficient);
		    print("(" + xthrust + ", " + ythrust + ")");
		  } else {
		    //xthrustCoefficient -= 0.1;
		    if (ythrust > 0) {
		      ythrust -= 0.1;
		    }
		    //if (xthrust > 0) {
		     xthrust -= 0.1;
		     }//
		  }
		  if (keyPressed == true && key == 'a' && isLanded == false) {
		    willRotate = true;
		    rotate -= 0.05;
		  } 
		  if (keyPressed == true && key == 'd' && isLanded == false) {
		    willRotate = true;
		    rotate += 0.05;
		  }
		  if (y >= height-10) { // <- Here, I will check for collision
		    KillPlayer();
		  }
		  if (x >= width-10) {
		    KillPlayer();
		  }
		  if (x <= 0) {
		    KillPlayer();
		  }
		  if (y <= 0) {
		    KillPlayer();
		  }
		  for (int i = 0; i < points.size(); i++) {
		    if (x+5 >= points.get(i).x - 5 && x+5 <= points.get(i).x + 5 && y+5 >= points.get(i).y - 5 && y+5 <= points.get(i).y + 5) {
		      KillPlayer();
		    }
		  }
		  fill(0, 255, 0);
		  for (int i = 0; i < 1; i += (int) random(0, 5)) {
		    rect(landingPad.get(i).x, landingPad.get(i).y, 50, 10);
		    //if(x+w>lp.x && x+w<lp.x+lp.w && y+h>lp.y && y<lp.y+lp.h) {
		    //x=lp.x-w;
		    //}
		    //x+5 > landingPad.get(i).x && x-5 < landingPad.get(i).x+50 && y+5 > landingPad.get(i).y && y-5 < landingPad.get(i).y
		    if (Collision((int) x, (int) y, (int) landingPad.get(i).x, (int) landingPad.get(i).y, 10, 10, 100, 10) == true) {
		      isLanded = true;
		      willRotate = false;
		      trail = new ArrayList<Trail>();
		    } else if (x+5 > landingPad.get(i).x && x-5 < landingPad.get(i).x+50 && y+5 > landingPad.get(i).y && y-5 < landingPad.get(i).y+10) {
		      KillPlayer();
		    }
		  }

		  trail.add(new Trail((int) this.x, (int) this.y));
		  if (trail.size() > 300) {
		    trail.remove(0);
		  }
		  for (int i = 0; i < trail.size(); i++) {
		    fill(i%255, frameCount%255, 100);
		    rect(trail.get(i).x, trail.get(i).y, 10, 10);
		  }
		  if (trail.size()>100) {
		    for (int i = 0; i < trail.size()-10; i++) {
		      if (Collision((int) x, (int) y, trail.get(i).x, trail.get(i).y, 10, 10, 10, 10)) {
		        KillPlayer();
		      }
		    }
		  }
	}
	GamePanel() { /*--------------------CONSTRUCTOR--------------------*\																																																																																																																																																																																																																																																																																																																																																																																																																																																																												*/
		timer = new Timer(1000 / 60, this);
	}
	void startGame() {
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	void paintComponent(Graphics g) {
		g.drawRect(playerX, playerY, 10, 10);
	}
	void actionPerformed() {
		//repaint();
		
	}
}
