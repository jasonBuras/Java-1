/*
The Enemy class will contain all data/methods that model the enemy.
To draw an enemy to the scene, we need 5 pieces of data:
name (with file path), the width, the height, and the location in the canvas.
At this stage, the Enemy class only need two behaviors to achieve the goal:
start: initialize all of the data of the enemy by settings its data variables
draw: draw the enemy's image to the canvas
In order to spawn the Enemy within the confines of the Scene, we must
able to ask the Scene for its width size and its height size. So we must add
two methods in Scene:
getWidth: returns the Scene's width size
getHeight: returns the Scene's height size
*/
public class Enemy {
	//Enemy data --> accesible by all enemy methods, scoped for as long as Game class runs
	private static String image;
	private static int width;
	private static int height;
	private static double x;
	private static double y;
	private static long time;

	//draw enemy
	public static void draw(){
		StdDraw.picture(x+width/2, y+height/2, image);
	}

	public static void start() {
		image = "assets/target.png";
		width = 32;
		height = 32;
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
			Enemy.move();
		}
		if (Enemy.isTouching() && Player.isAttacking()){
			Enemy.move();
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
		return Player.getLeft() <= Enemy.getRight() && Enemy.getLeft() <= Player.getRight();
		
	}
	public static boolean isTouchingY() {
		return Player.getTop() <= Enemy.getBottom() && Enemy.getTop() <= Player.getBottom();
	}
	public static boolean isTouching() {
		return isTouchingX() && isTouchingY();
	}
}