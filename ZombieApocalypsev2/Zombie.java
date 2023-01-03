/*
The Zombie class will contain all data/methods that model the Zombie.
To draw an Zombie to the scene, we need 5 pieces of data:
name (with file path), the width, the height, and the location in the canvas.
At this stage, the Zombie class only need two behaviors to achieve the goal:
start: initialize all of the data of the Zombie by settings its data variables
draw: draw the Zombie's image to the canvas
In order to spawn the Zombie within the confines of the Scene, we must
able to ask the Scene for its width size and its height size. So we must add
two methods in Scene:
getWidth: returns the Scene's width size
getHeight: returns the Scene's height size
*/
public class Zombie {
	//Zombie data --> accesible by all Zombie methods, scoped for as long as Game class runs
	private static String image;
	private static int width;
	private static int height;
	private static double x;
	private static double y;
	private static long time;

	//draw Zombie
	public static void draw(){
		StdDraw.picture(x+width/2, y+height/2, image);
	}

	public static void start() {
		image = "assets/zombie.png";
		width = 16;
		height = 16;
		x= Math.random() * Scene.getWidth() - width;
		y= Math.random() * Scene.getHeight() - height;
		time = System.currentTimeMillis();
	}

	public static void move() {
		x = Math.random() * Scene.getWidth() - width;
		y = Math.random() * Scene.getHeight() - height;
		time = System.currentTimeMillis();
	}
	public static void update() {
		long now = System.currentTimeMillis();
		if (now - time > 1000){
			Zombie.move();
		}
		if (Zombie.isTouching() && Player.isAttacking()){
			Zombie.move();
			Game.addScore();
		}
	}
	public static double getLeft(){
		return x;
	}
	public static double getTop() {
		return y;
	}
	public static double getBottom(){
		return y + height;
	}
	public static double getRight() {
		return x + width;
	}
	public static boolean isTouchingX(){
		return Player.getLeft() <= Zombie.getRight() && Zombie.getLeft() <= Player.getRight();
		
	}
	public static boolean isTouchingY() {
		return Player.getTop() <= Zombie.getBottom() && Zombie.getTop() <= Player.getBottom();
	}
	public static boolean isTouching() {
		return isTouchingX() && isTouchingY();
	}
}