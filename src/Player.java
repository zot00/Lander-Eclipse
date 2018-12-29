public class Player {
	public static double y;
	public static double x;
	static double incrementer = Gravity.Gincrementer * 1.75;

	public static void main(String[] args) {

		if (Thrust.ythrust >= incrementer) {
			Thrust.ythrust = incrementer;
		}
		if (Gravity.isLanded == false) {
			Gravity.gravity += Gravity.gravityCoefficient;
			y -= Gravity.gravity;
			y += Thrust.ythrust;
			x -= Thrust.xthrust;
		}
	}
}