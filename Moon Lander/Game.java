/* Mars Lander Game by Jason Buras
University of New Orleans - Department of Computer Science
java Game < assets/level0.txt
*/
import java.util.Scanner;
public class Game{

	private static boolean gameOver;
	public static void main(String[] args){ //runs game
	//Start Game
		start();
	//Game loop:
		while (gameOver == false){
			//1. Update game
			update();
			//2. Render game
			render();
		}
	}

	public static void start(){
		//setup ALL game data
		Scanner input = new Scanner(System.in);
		Scene.start(input);
		Player.start(input);
		Physics.start(input);
		LandingPad.start(input);
	}
	public static void update(){
		Player.update();
	}

	public static void render(){
		Scene.draw(); //draw scene
		Player.draw();//draw player
		LandingPad.draw(); //draw landing pad
		Hud.draw(); //draw hud
		StdDraw.show(100); //show graphics
	}

	public static void setGameOver(boolean gameOver){
		Game.gameOver= gameOver;
	}

}