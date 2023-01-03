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
	private static String shipImage;
	private static String thrusterUpImage;
	private static String thrusterLeftImage;
	private static String thrusterRightImage;
	private static String shipLandedImage;
	private static String shipCrashedImage;

	//current ship
	private static String sprite;

	//ship dimensions
	private static int width;
	private static int height;

	//ship initial position:
	private static double x;
	private static double y;

	//fuel
	private static int fuel;

	public static void start(Scanner input){
		//Ship art assets
		shipImage = "assets/" + input.next();
		thrusterUpImage = "assets/" + input.next();
		thrusterLeftImage = "assets/" + input.next();
		thrusterRightImage = "assets/" + input.next();
		shipLandedImage = "assets/" + input.next();
		shipCrashedImage = "assets/" + input.next();

		//set current image
		sprite = shipImage;

		//read ship dimensions
		width = input.nextInt();
		height = input.nextInt();

		//read ship init position:
		x = input.nextDouble();
		y = input.nextDouble();

		//read fuel
		fuel = input.nextInt();
	}

	public static void draw(){
		StdDraw.picture(x, y, sprite);
	}

	public static void update(){
		Player.move();
		Physics.update();
		y += Physics.getVelocityY();
		x += Physics.getVelocityX();

		if ((y+height) > (Scene.getHeight()-15)){
			if (LandingPad.isTouching() == true && Physics.isSurvivableSpeed() == true){
				sprite=shipLandedImage;
			}
			else{
				sprite = shipCrashedImage;
			}
			
			Game.setGameOver(true);
		}
	}

	public static void move(){
		if (StdDraw.hasNextKeyTyped()){
			char ch = StdDraw.nextKeyTyped();
			if (ch=='w'){
				Physics.thrustUp();
				sprite=thrusterUpImage;
				fuel--;
			}
			else if (ch=='d'){
				Physics.thrustRight();
				sprite=thrusterLeftImage;
				fuel--;
			}
			else if (ch=='a'){
				Physics.thrustLeft();
				sprite=thrusterRightImage;
				fuel--;
			}
		}
		else{
			sprite= shipImage;
		}
	}

	public static double getX(){
		return x;
	}

	public static int getFuel(){
		return fuel;
	}
}

