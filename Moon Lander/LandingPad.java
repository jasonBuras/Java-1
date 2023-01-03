import java.util.Scanner;

public class LandingPad{
	private static int x;
	private static int width;

	public static void start(Scanner input){
		//Read the landing pad X-position and width/2
		LandingPad.x=input.nextInt();
		LandingPad.width=input.nextInt();
	}

	public static void draw(){
		//draw landing pad
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(x, Scene.getHeight()-20, width, 5);
	}

	public static boolean isTouching(){
		boolean isTouching;
		isTouching = Math.abs(Player.getX() - LandingPad.x) < LandingPad.width;
		return isTouching;
	}
}