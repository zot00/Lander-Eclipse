public class Gravity {
	static boolean IsLanded = false;
	static double gravity = 0.1;
	static double gravityCoefficient = 0.1;
	static double Gincrementer = 6.5;
	static double velocity = 0; //the speed of acceleration
	static double velocityCoefficient = 0.2; // ? \\
	static double airResistance = 0;
	
	public static void main(String[] args) {
		if (gravity >= Gincrementer) {
			gravity = Gincrementer;
		} // note to self: a 1.75:1 ratio of thrust to gravity is a nice combo
		double incrementer = Gincrementer*1.75;
		  if (Thrust.ythrust >= incrementer) {
		    Thrust.ythrust = incrementer;
		  }
	}
}
