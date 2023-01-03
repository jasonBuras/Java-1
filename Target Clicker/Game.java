/* Target Clicker by Jason Buras
University of New Orleans - Department of Computer Science
CSCI 1583 Software Design I 
Methods & Classes
Part II: DRY Principles. Don't repeat yourself and K.I.S.D.A.
The Game class will contain all data/methods for managing the game's rules.
Game class should contain the main algorithm that runs the game, where the
following actions occur: Initially start the game then run the game loop,
The game loop typically performs two tasks:
update game state then render game to display.
*/
public class Game{

	private static boolean gameOver=false;
	private static int score = 0;

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

	public static void render(){
		//draw scene
		Scene.draw();
		Enemy.draw();
		Player.draw();
		StdDraw.text(64, 32, "Score: " + score);
		StdDraw.show(100); //show graphics
		//draw enemy
		//draw player
	}

	public static void update(){
		//check for input
		//update player
		Player.update(); //update player
		//update enemy
		Enemy.update();
	}

	public static void start(){
		//setup ALL game data
		Scene.start();	//scene data
		Enemy.start();	//enemy data
		Player.start(); //player data
	}

	public static void addScore(){
		score++;
	}

}