public class Hud{
	public static void draw(){
		StdDraw.text(50, 20, "Fuel: " + Player.getFuel() );
		/*StdDraw.text(60, 35, "X Velocity: " + Physics.getVelocityX());
		StdDraw.text(60, 50, "Y Velocity: " + Physics.getVelocityY());*/
	}
}