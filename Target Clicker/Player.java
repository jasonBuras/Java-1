/*
The Player class will contain all data/methods that model the player.
To draw the player to the scene, we need 5 pieces of data:
name (with file path), the width, the height, and the location in the canvas.
At this stage, the Player class only need two behaviors to achieve the goal:
start: initialize all of the data for the player by settings its data variables
draw: draw the player''s image to the canvas
After adding the Player class, update the Game class' start() method to invoke Enemy's start() method to initialize
the enemy, and update Game class' render() method to invoke Enemy's draw() method.
*/
public class Player{
	//player data
	private static String image;
	private static int width;
	private static int height;
	private static double x;
	private static double y;
	private static boolean isAttacking;

	public static void draw(){
		StdDraw.picture(x+width/2, y+height/2, image);
	}

	public static void start() {
		image = "assets/aimer.png";
		width = 32;
		height = 32;
		x = Scene.getWidth()/2;
		y= Scene.getHeight()/2;
		isAttacking=false;
	}
	public static void move(){
		x = StdDraw.mouseX() - width/2;
		y = StdDraw.mouseY() - height/2;
	}
	public static void attack(){
		if (StdDraw.mousePressed()){
			isAttacking = true;
		}
		else {
			isAttacking=false;
		}
	}
	public static void update() {
		move(); //update player logic
		attack(); //attack logic
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
	public static boolean isAttacking(){
		return isAttacking;
	}

}