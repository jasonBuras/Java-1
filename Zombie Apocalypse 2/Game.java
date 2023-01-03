//Contains all methods/data that manages the game rules
/*The Game class will contain all data/methods for managing the game's rules.
Game class should contain the main algorithm that runs the game, where the
following actions occur: Initially start the game then run the game loop,
The game loop typically performs two tasks:
update game state then render game to display.*/
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.ArrayList;


public class Game{
	private static String playerName;
	private static int difficultySetting;
	private static boolean gameOver=false;
	private static long time;
	private static int seconds;
	private static boolean zombieKill=false;
	private static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		start(); 	//Start game
		int difficultySetting = Menu.getDifficultySetting();
		if(difficultySetting==1){
			System.out.println("Zombie");
			Zombie z1= new Zombie();
			zombies.add(z1);
		}else if(difficultySetting==2){
			Zombie z2= new Zombie();
			zombies.add(z2);
			Zombie z3= new Zombie();
			zombies.add(z3);
			Zombie z4= new Zombie();
			zombies.add(z4);
		}else if(difficultySetting==3){
			Zombie z5= new Zombie();
			zombies.add(z5);
			Zombie z6= new Zombie();
			zombies.add(z6);
			Zombie z7= new Zombie();
			zombies.add(z7);
			Zombie z8= new Zombie();
			zombies.add(z8);
			Zombie z9= new Zombie();
			zombies.add(z9);
			Zombie z10= new Zombie();
			zombies.add(z10);
		}

		while (gameOver==false){
				update();	//1. Update Game
				render();	//2. Render Game
		}
		
	    if (gameOver ==true){
			gameOver();	
		}
	}
	private static final Font font = new Font(Font.DIALOG, Font.PLAIN, 32);
	private static final Font font2 = new Font(Font.MONOSPACED, Font.BOLD, 12);

	public static void start() {
		System.out.println("[INITIALIZING GAME]");
			gameOver=false;
			Player.start();
			StdDraw.setFont(font2);
			Scene.intro(); //Skipping intro for now
			Menu.start();
			while(Menu.getDifficultySetting()<=0||Menu.getDifficultySetting()>4){
				Menu.update();
			}
			StdDraw.setFont(font);
			Scene.start();
			for (Zombie ignored : zombies) {
				Zombie.start();
			}
			Weapon.start();
			Exit.start();
			Key key = new Key();
			Key treasureKey = new Key("assets/treasurekey.png");
			Key.start();

	}
	public static void render(){
		Scene.draw(); 		//draw scene
		for (Zombie zombie : zombies){
			Zombie.draw();
		}
		Player.draw();
		Weapon.draw();
		Key.draw();
		Hud.draw();
		Exit.draw();
		StdDraw.show(100); 	//show graphics
		
	}

	public static void update(){
		//check for input
		Player.update();//update player
		Zombie.update(); //update zombie
		Key.update();
		if(Exit.isTouching()==true){
			gameWin();

		}
		Zombie.move();
		while(StdDraw.isKeyPressed(KeyEvent.VK_CONTROL)&& StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && StdDraw.isKeyPressed(KeyEvent.VK_C)) {
			StdDraw.setPenColor(255, 0, 0);
			StdDraw.setFont(font);
			StdDraw.text(Scene.getWidth() / 2, Scene.getHeight() / 2, "PAUSED");
			StdDraw.show();
			GameEngine.Cheats();
		}
		StdDraw.setFont(font2);
		/*if (Exit.isTouching()==true){
			Scene.youWin();
		}
		else if(Game.zombieKill==true){
			Game.gameOver=true;
		}*/
	}

	public static double getTime(){
		return time;
	}
	public static int seconds(){//i forgot why I made this
		return seconds++;
	}

	public static void setZombieKill(boolean zombieKill){
		zombieKill=true;
	}
	public static void setGameOver(boolean gameOver){
		if(zombieKill==true){
			Game.gameOver=true;
		} 
		else{
			Game.gameOver=true;
		}
		
	}

	public static void gameOver(){
		Scene.gameOver(); 		//draw scene
		StdDraw.show(100); 	//show graphics
	}
	public static void gameWin(){
		Scene.youWin(); 		//draw scene
		StdDraw.show(100); 	//show graphics
		
		
	}

	public static String getPlayerName() {
		return playerName;
	}
}