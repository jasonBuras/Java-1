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
	private static int lives = 3;

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
		Zombie.draw();
		Player.draw(); 
		StdDraw.text(64, 508, "Lives: " + lives); //int width = 960 int height = 540;
		StdDraw.show(100); //show graphics
		//draw Zombie
		//draw player
	}

	public static void update(){
		//check for input
		//update player
		Player.update(); //update player
		//update Zombie
		Zombie.update();
	}

	public static void start(){
		//setup ALL game data
		Scene.start();	//scene data
		Zombie.start();	//Zombie data
		Player.start(); //player data
	}

	public static void addScore(){
		lives++;
	}

}