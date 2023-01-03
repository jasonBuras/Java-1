import java.util.Scanner;

public class Physics{
	private static double playerX;
	private static double playerY;
	private static double move;

	public static void start(Scanner input){
		playerX= 0.0;
		playerY= 0.0;
		gravity= input.nextDouble();
		maxSurvivableVelocity = input.nextDouble();
		move = input.nextDouble();
	}

	public static void update(){
		playerY -= gravity;
	}

	public static double getplayerX(){
		return playerX;
	}

	public static double getplayerY(){
		return playerY;
	}

	public static void moveUp(){
		playerY -= move;
	}

	public static void moveRight(){
		playerX += move;
	}

	public static void moveLeft(){
		playerX -= move;
	}


}