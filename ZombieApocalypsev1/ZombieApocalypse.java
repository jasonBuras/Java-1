// Jason Buras HW2: Processing Operation & Control Operations
// Zombie Apocalypse: A Roguelike Survival Game
// With my skills, this game may be buggier than an EA Game on release day
/*
                    _     _      
                   | |   (_)     
 _______  _ __ ___ | |__  _  ___ 
|_  / _ \| '_ ` _ \| '_ \| |/ _ \
 / / (_) | | | | | | |_) | |  __/
/___\___/|_| |_| |_|_.__/|_|\___|
                             _                      
                            | |                     
  __ _ _ __   ___   ___ __ _| |_   _ _ __  ___  ___ 
 / _` | '_ \ / _ \ / __/ _` | | | | | '_ \/ __|/ _ \
| (_| | |_) | (_) | (_| (_| | | |_| | |_) \__ \  __/
 \__,_| .__/ \___/ \___\__,_|_|\__, | .__/|___/\___|
      | |                       __/ | |             
      |_|                      |___/|_|  
      By: Jason Buras	"\uD83D\uDC7B"																			*/

import java.util.Scanner;
import java.util.Random;
public class ZombieApocalypse
{
	//ANSI colors
public static final String ANSI_RESET = "\u001B[0m"; //I Added colors!
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";
public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
public static void main(String[] args)
	{ //Setup Game Data
		String versionNumber = "Zombie Apocalypse v1.0";
		Scanner input = new Scanner(System.in);

		System.out.printf("\nWelcome to %s!\n\nHey!\n\nI want to start off by saying thank you so much for playing.\nI put a lot of time into this project.\nI've built it up, tore it down, rebuilt it, and spent hours troubleshooting.\nSo with that said, please enjoy!\n\n", versionNumber);
		System.out.print("What is your name? "); //Custom Feature Update: Player name
		String playerName = input.nextLine();
		System.out.println("\033[2J\033[1;1H");

		// START MENU
		System.out.printf("Welcome, %s!\nSelect Game Mode:\nA- Campaign\nB- Survival\nC- Surprise\nD- HELP\nType answer and press 'Enter'\n", playerName); //Custom Feature Update: Player name
		String gameSelection = input.nextLine();
		System.out.println("\033[2J\033[1;1H");

		//Conditions
		boolean gameOver = false;
		boolean youFail = false;

		//BOARD 
		int colSize = 10;
		int rowSize = 10;
		int colSize2= 15; //for future levels. Not used yet
		int rowSize2= 15;
		String floorTile = ". ";

		//Player
		int playerX = 0;
		int playerY = 0;
			//FOOD
			int playerFood = 30;
			int foodX = 6 + (int) (Math.random() * ((15 - 6) + 1)); //(int) (Math.random()*4);
			int foodY = 6 + (int) (Math.random() * ((15 - 6) + 1));//(int) (Math.random()*4);
			String foodTile = "f ";
			String playerTile = "@ ";
			boolean gotFood = false; 

		//Objective
		int levelsComplete = 0;
		int exitX = colSize -1;
		int exitY = rowSize -1;
		String exitTile = "[] EXIT";
			//KEYS
			boolean hasKey = false;
			int keyX = 6 + (int) (Math.random() * ((15 - 6) + 1)); //(int) (Math.random()*4);
			int keyY = 6 + (int) (Math.random() * ((15 - 6) + 1)); //(int) (Math.random()*4);
			String keyTile = "k ";

		//Zombie Info
		int zombieX = 5; 
		int zombieY = 5;
		String zombieTile = ("Z1");
		int zombie2X = 7; //Custom Feature 3: Second Zombie
		int zombie2Y = 7; //2X and 2Y were both 7 before this test
		String zombie2Tile = "Z2";
		int zombie3X = 10; 
		int zombie3Y = 8;
		String zombie3Tile = ("Z3");
		boolean zombie3Spawn = false;

		//Constants
		
		if (playerFood== 0){
			youFail= true;
			}
		//Game Loop         int levels = 5; //Determines how many levels in Campaign mode.

		if (gameSelection.equals("A")|| gameSelection.equals("a")){ 
            Scanner reader = new Scanner(System.in);
            System.out.print("1-Easy\n2-Medium\n3-Hard\nSelect Difficulty: ");
            int difficultySelect = reader.nextInt();
            int levels= 0;
            switch(difficultySelect){
                case 1: levels += 3; break;
                case 2: levels += 5; break;
                case 3: levels += 7; break;
                default: System.out.println("I dunno what you think you're doing, but it ain't gonna work."); break;
            }
		while (levelsComplete <= levels){ //Start game loop1 This is super sloppy. I should've done a while (levelsComplete <= (desired number of levels)) but by the time I thought of it, I was too scared to go back and change anything since the game was running smoothly.
			//Draw Game Scene
			System.out.printf("Version: %s\nw: Up\ns: Down\na: Left\nd: Right\nThen press enter\nUse Captial Letters to move 2 spaces\nPlayer Name: %s\nLevels Complete: %d/%d\n", versionNumber, playerName, levelsComplete, levels); // Custom Feature 1
			//Colored Health Update
			if (playerName.equals("God")){
				playerFood = 9001;
				hasKey = true;
			}
			if (playerName.equals("Error")){ //god mode lol
				playerFood = 404;
			}
			if (playerName.equals("Cake")){ //god mode lol
				System.out.println("The Cake Is a Lie");
			}
			if (playerName.equals("Nice")){
				System.out.println("Nice...");
				playerFood = 69;
			}
			if (playerName.equals("Pie")){
				System.out.println("yumm");
				playerFood = 314;
			}
			if (playerName.equals("Holmberg")){
				System.out.println("I gave you food now give me an A");
				playerFood = 1583;
			}
			if (playerFood > 9000){
				System.out.printf(ANSI_YELLOW_BACKGROUND + ANSI_RED +"IT'S OVER 9000!!!\n" +ANSI_RESET);
			}
			if (playerFood >= 3){	//Custom Feature 6: Player Food. Once I have multiple levels, I want to set Food to 1, and let levelComplete give playerFood++
				System.out.printf(ANSI_GREEN + "Food: %d\n" +ANSI_RESET, playerFood);
			}
			else if (playerFood ==2){
				System.out.printf(ANSI_YELLOW + "Food: %d\n" +ANSI_RESET, playerFood);
			}
			else if (playerFood ==1){
				System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_RED + "Food: %d UH OH\n" +ANSI_RESET, playerFood);
			}

			for(int y=0; y < rowSize; y++){				//number of rows
				for(int x=0; x < colSize; x++){			//number of columns
					if (x == playerX && y == playerY){	//if this grid coord is player's position
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + playerTile + ANSI_RESET);	// print player tile
					}
					else if (x == keyX && y == keyY && !hasKey){
						System.out.print(ANSI_BLACK_BACKGROUND + ANSI_YELLOW + keyTile + ANSI_RESET);
					}
					else if (x == exitX && y == exitY){	//if this grid coord is exit's position
						System.out.print(ANSI_WHITE_BACKGROUND +ANSI_BLACK + exitTile + ANSI_RESET);		//print exit tile
					}
					else if (x == zombieX && y == zombieY){	//if this grid coord is zombie's position
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_RED + zombieTile +ANSI_RESET);
					}
					else if (x == zombie2X && y == zombie2Y && levelsComplete >= 1){
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_RED + zombie2Tile + ANSI_RESET);
					}
					else if (x == zombie3X && y == zombie3Y && zombie3Spawn && levelsComplete >= 3){
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_RED + zombie3Tile + ANSI_RESET);
					}
					else{
						System.out.printf(ANSI_GREEN_BACKGROUND + ANSI_BLACK + floorTile + ANSI_RESET);		//print floor tile
					}
				}
				System.out.print("\n"); 
			}
			//Get Player Input
			String choice = input.nextLine();
			//Execute Player Action
			if ((choice.equals("w")) && (playerY >= 1)){
				System.out.println("\033[2J\033[1;1H"); //This clears the board after every move
				playerY--;
				playerFood--;
			}
			else if ((choice.equals("s")) && (playerY <= 8)){
				System.out.println("\033[2J\033[1;1H");
				playerY++;
				playerFood--;
			}
			else if ((choice.equals("d")) && (playerX <= 8)){
				System.out.println("\033[2J\033[1;1H");
				playerX++;
				playerFood--;
			}
			else if ((choice.equals("a")) && (playerX >= 1)){
				System.out.println("\033[2J\033[1;1H");
				playerX--;
				playerFood--;
			}
			else if ((choice.equals("W")) && (playerY > 1)){					//Custom Feature 4: Included capital letters for inputs
			System.out.println("\033[2J\033[1;1H");	 				
				playerY=(playerY - 2);						//Made capital letters a sprint feature (2 movement)
				playerFood = (playerFood -2);
			}
			else if ((choice.equals("S")) && (playerY < 8)){
				System.out.println("\033[2J\033[1;1H");
				playerY=(playerY + 2);
				playerFood = (playerFood -2);
			}
			else if ((choice.equals("D")) && (playerX < 8)){
				System.out.println("\033[2J\033[1;1H");
				playerX=(playerX + 2);
				playerFood = (playerFood -2);
			}
			else if ((choice.equals("A")) && (playerX > 1)){
				System.out.println("\033[2J\033[1;1H");
				playerX=(playerX - 2);
				playerFood = (playerFood -2);
			}
			//Check Win Condition
			if (playerX == exitX && playerY == exitY){
				levelsComplete++;
				playerFood = (playerFood + 30);
				playerX = 0;
				playerY = 0;
				zombie2X = 7;
				zombie2Y = 7;
				zombieX = 5;
				zombieY = 5;
				System.out.println("\033[2J\033[1;1H"); 		// Custom Feature 2: Auto Clear
				System.out.printf("Level Passed! %d/3\n", levelsComplete);
			}
			//Execute Monster Action
			int zombieChoice = (int) (Math.random()*4); //Update idea: add diagonal movement
			if (zombieChoice == 0){
				zombieX = (zombieX + 1) % colSize;
			}
			else if (zombieChoice ==1){
				zombieX = --zombieX >=0 ? zombieX : (colSize - 1);
			}
			else if (zombieChoice == 2){
				zombieY = --zombieY >= 0 ? zombieY : (rowSize - 1);
			}
			else if (zombieChoice == 3){
				zombieY = (zombieY + 1) % rowSize;
			}
			//move zombie on x-axis
			if (zombie2X > playerX){ //Custom Feature 5: Smarter Zombie (Probably introduce in Level 2)
    			zombie2X = (zombie2X - 1) % colSize;
			}
			else if (zombie2X < playerX){
   				zombie2X = (zombie2X + 1) % colSize;
			}
			//move zombie on y-axis
			if (zombie2Y < playerY){
   				zombie2Y = (zombie2Y + 1) % rowSize;
			}
			else if (zombie2Y > playerY){
    			zombie2Y = (zombie2Y - 1) % rowSize;
			}
			if (zombieX == playerX && zombieY == playerY){
			playerFood = (playerFood - 3);
			zombieX++;
			zombieY--;

			}
			if (zombie2X == playerX && zombie2Y == playerY){
			playerFood--;
			zombie2X--;
			zombie2Y++;	
			}
			int zombie3Choice = (int) (Math.random()*7); //Update idea: add diagonal movement
			if (zombie3Choice == 0){
				zombie3X = (zombie3X + 2) % colSize;
			}
			else if (zombieChoice ==1){
				zombie3X = --zombie3X >=0 ? zombieX : (colSize - 1);
			}
			else if (zombieChoice == 2){
				zombie3Y = --zombie3Y >= 0 ? zombieY : (rowSize - 1);
			}
			else if (zombieChoice == 3){
				zombie3Y = (zombie3Y + 2) % rowSize;
			}
			if (zombie3Choice == 4){
				zombie3X = (zombie3X + 2) % colSize;
			}
			else if (zombieChoice ==5){
				zombie3X = --zombie3X >=0 ? zombieX : (colSize - 1);
			}
			else if (zombieChoice == 6){
				zombie3Y = --zombie3Y >= 0 ? zombieY : (rowSize - 1);
			}
			else if (zombieChoice == 7){
				zombie3Y = (zombie3Y + 2) % rowSize;
			}
			//move zombie on x-axis
			if (zombie2X > playerX){ //Custom Feature 5: Smarter Zombie (Probably introduce in Level 2)
    			zombie2X = (zombie2X - 1) % colSize;
			}
			else if (zombie2X < playerX){
   				zombie2X = (zombie2X + 1) % colSize;
			}
			//move zombie on y-axis
			if (zombie2Y < playerY){
   				zombie2Y = (zombie2Y + 1) % rowSize;
			}
			else if (zombie2Y > playerY){
    			zombie2Y = (zombie2Y - 1) % rowSize;
			}

			if (zombie3X == playerX && zombie3Y == playerY){
			playerFood= (playerFood - 20);
			zombie3X--;
			zombie3Y++;	
			}
			if (playerFood ==0){
				youFail = true;
				System.out.println("\033[2J\033[1;1H");			//Custom Feature 2: Auto Clear
				System.out.printf(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "You lost :( \nPress the UP Arrow and then press enter\nOR Type: 'java %s' and then press enter\nThank you for playing, %s!\n" + ANSI_RESET, playerName);
			}								
		}
	 
	}//I believe this closes the (while gameSelection == 1) statement.
	else if (gameSelection.equals("B") || gameSelection.equals("b")){ //SURVIVAL
		
			while (playerFood > 0){ //Start game loop3
			//Draw Game Scene
			Random random = new Random(); 
			rowSize = 15;
			colSize = 15;
			exitX = 14;
			exitY = 14;
			
			System.out.printf("Version: %s\nw: Up\ns: Down\na: Left\nd: Right\nThen press enter\nUse Captial Letters to move 2 spaces\nPlayer Name: %s\nLevels Complete: %d\nCollect the key('k') and make it to the end.\nHas Key: %b\n", versionNumber, playerName, levelsComplete, hasKey ); // Custom Feature 1
			//Colored Health Update
			if (playerName.equals("God")){
				playerFood = 9001;
				hasKey = true;
			}
			if (playerName.equals("Error")){ //god mode lol
				playerFood = 404;
			}
			if (playerName.equals("God")){ //god mode lol
				playerFood = 9001;
			}
			if (playerName.equals("Cake")){ //god mode lol
				System.out.println("The Cake Is a Lie");
			}
			if (playerName.equals("Nice")){
				System.out.println("Nice...");
				playerFood = 69;
			}
			if (playerName.equals("Pie")){
				System.out.println("yumm");
				playerFood = 314;
			}
			if (playerName.equals("Holmberg")){
				System.out.println("I gave you food now give me an A");
				playerFood = 1583;
			}
			if (playerFood > 9000){
				System.out.printf(ANSI_YELLOW_BACKGROUND + ANSI_RED +"IT'S OVER 9000!!!\n" +ANSI_RESET);
			}
			if (playerFood >= 3){	//Custom Feature 6: Player Food. Once I have multiple levels, I want to set Food to 1, and let levelComplete give playerFood++
				System.out.printf(ANSI_GREEN + "Food: %d ('f' tiles are +15 food)\n" +ANSI_RESET, playerFood);
			}
			else if (playerFood ==2){
				System.out.printf(ANSI_YELLOW + "Food: %d ('f' tiles are +15 food)\n" +ANSI_RESET, playerFood);
			}
			else if (playerFood ==1){
				System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_RED + "Food: %d UH OH COLLECT FOOD NOW!\n" +ANSI_RESET, playerFood);
			}

			for(int y=0; y < rowSize; y++){				//number of rows
				for(int x=0; x < colSize; x++){			//number of columns
					if (x == playerX && y == playerY){	//if this grid coord is player's position
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK + playerTile + ANSI_RESET);	// print player tile
					}
					else if (x == keyX && y == keyY && !hasKey){
						System.out.print(ANSI_BLACK_BACKGROUND + ANSI_YELLOW + keyTile + ANSI_RESET);
					}
					else if (x == foodX && y == foodY && !gotFood){
						System.out.print(ANSI_BLACK_BACKGROUND + ANSI_GREEN + foodTile + ANSI_RESET);
					}
					else if (x == exitX && y == exitY){	//if this grid coord is exit's position
						System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + exitTile + ANSI_RESET);		//print exit tile
					}
					else if (x == zombieX && y == zombieY){	//if this grid coord is zombie's position
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_RED + zombieTile +ANSI_RESET);
					}
					else if (x == zombie2X && y == zombie2Y){
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_RED + zombie2Tile + ANSI_RESET);
					}
					else if (x == zombie3X && y == zombie3Y && zombie3Spawn){
						System.out.print(ANSI_GREEN_BACKGROUND + ANSI_RED + zombie3Tile + ANSI_RESET);
					}
					else{
						System.out.printf(ANSI_GREEN_BACKGROUND + ANSI_BLACK + floorTile + ANSI_RESET);		//print floor tile
					}
				}
				System.out.print("\n"); 
			}

			//Get Player Input
			String choice = input.nextLine();
			//Execute Player Action
			if ((choice.equals("w")) && (playerY >= 1)){
				System.out.println("\033[2J\033[1;1H"); //This clears the board after every move
				playerY--;
				playerFood--;
			}
			else if ((choice.equals("s")) && (playerY <= 13)){
				System.out.println("\033[2J\033[1;1H");
				playerY++;
				playerFood--;
			}
			else if ((choice.equals("d")) && (playerX <= 13)){
				System.out.println("\033[2J\033[1;1H");
				playerX++;
				playerFood--;
			}
			else if ((choice.equals("a")) && (playerX >= 1)){
				System.out.println("\033[2J\033[1;1H");
				playerX--;
				playerFood--;
			}
			else if ((choice.equals("W")) && (playerY > 1)){					//Custom Feature 4: Included capital letters for inputs
			System.out.println("\033[2J\033[1;1H");	 				
				playerY=(playerY - 2);						//Made capital letters a sprint feature (2 movement)
				playerFood = (playerFood -2);
			}
			else if ((choice.equals("S")) && (playerY < 13)){
				System.out.println("\033[2J\033[1;1H");
				playerY=(playerY + 2);
				playerFood = (playerFood -2);
			}
			else if ((choice.equals("D")) && (playerX < 13)){
				System.out.println("\033[2J\033[1;1H");
				playerX=(playerX + 2);
				playerFood = (playerFood -2);
			}
			else if ((choice.equals("A")) && (playerX > 1)){
				System.out.println("\033[2J\033[1;1H");
				playerX=(playerX - 2);
				playerFood = (playerFood -2);
			}

			if (playerX == foodX && playerY == foodY && !gotFood){
				playerFood =(playerFood + 15);
				gotFood = true;
			}
			if (playerX == keyX && playerY == keyY){
				playerFood =(playerFood + 10);
				hasKey = true;
			}
			//Check Win Condition
			if (playerX == exitX && playerY == exitY && hasKey){
				zombie3Spawn = random.nextBoolean();
				keyX = 6 + (int) (Math.random() * ((13 - 6) + 1));
				keyY = 6 + (int) (Math.random() * ((13 - 6) + 1));
				foodX = 6 + (int) (Math.random() * ((13 - 6) + 1)); //(int) (Math.random()*4);
				foodY = 6 + (int) (Math.random() * ((13 - 6) + 1));//(int) (Math.random()*4);
				levelsComplete++;
				gotFood = false;
				hasKey = false;
				playerX = 0;
				playerY = 0;		
				zombie2X = 7;
				zombie2Y = 7;
				zombieX = 5;
				zombieY = 7;
				playerFood = (playerFood + 35);
				System.out.println("\033[2J\033[1;1H"); 		// Custom Feature 2: Auto Clear
				System.out.printf("Level Passed! You have completed %d levels!\nKeep going!\n", levelsComplete);
			}
			//Execute Monster Action
			int zombieChoice = (int) (Math.random()*4); //Update idea: add diagonal movement
			if (zombieChoice == 0){
				zombieX = (zombieX + 1) % colSize;
			}
			else if (zombieChoice ==1){
				zombieX = --zombieX >=0 ? zombieX : (colSize - 1);
			}
			else if (zombieChoice == 2){
				zombieY = --zombieY >= 0 ? zombieY : (rowSize - 1);
			}
			else if (zombieChoice == 3){
				zombieY = (zombieY + 1) % rowSize;
			}
			//move zombie on x-axis
			if (zombie2X > playerX){ //Custom Feature 5: Smarter Zombie (Probably introduce in Level 2)
    			zombie2X = (zombie2X - 1);
			}
			else if (zombie2X < playerX){
   				zombie2X = (zombie2X + 1);
			}
			//move zombie on y-axis
			if (zombie2Y < playerY){
   				zombie2Y = (zombie2Y + 1);
			}
			else if (zombie2Y > playerY){
    			zombie2Y = (zombie2Y - 1);
			}								//Add third zombie for third level. Let zombie follow set patrol path.
			if (zombieX == playerX && zombieY == playerY){
			playerFood = (playerFood - 50);
			zombieX++;
			zombieY--;

			}
			if (zombie2X == playerX && zombie2Y == playerY){
				playerFood = (playerFood - 30);
				zombie2X++;
				zombie2Y++;	
			}
			int zombie3Choice = (int) (Math.random()*7); //Update idea: add diagonal movement
			if (zombie3Choice == 0){
				zombie3X = (zombie3X + 2) % colSize;
			}
			else if (zombieChoice ==1){
				zombie3X = --zombie3X >=0 ? zombieX : (colSize - 1);
			}
			else if (zombieChoice == 2){
				zombie3Y = --zombie3Y >= 0 ? zombieY : (rowSize - 1);
			}
			else if (zombieChoice == 3){
				zombie3Y = (zombie3Y + 2) % rowSize;
			}
			if (zombie3Choice == 4){
				zombie3X = (zombie3X + 2) % colSize;
			}
			else if (zombieChoice ==5){
				zombie3X = --zombie3X >=0 ? zombieX : (colSize - 1);
			}
			else if (zombieChoice == 6){
				zombie3Y = --zombie3Y >= 0 ? zombieY : (rowSize - 1);
			}
			else if (zombieChoice == 7){
				zombie3Y = (zombie3Y + 2) % rowSize;
			}
			if (zombie3X == playerX && zombie3Y == playerY){
			playerFood= (playerFood - 25);
			zombie3X--;
			zombie3Y--;	
			}
			if (playerFood <=0){
				youFail = true;
				System.out.println("\033[2J\033[1;1H");			//Custom Feature 2: Auto Clear
				System.out.printf(ANSI_RED_BACKGROUND + ANSI_BLACK + "You lost :( \nPress the UP Arrow and then press enter\nOR Type: 'java %s' and then press enter\nThank you for playing, %s!\nIf you're stuck, press CTRL+C" + ANSI_RESET, versionNumber, playerName);
			}	
		}
		}  else if (gameSelection.equals("C") || gameSelection.equals("c")){
			System.out.printf("%s, you're really cool for playing this. I really appreciate you and hope you have an incredible day!\nIf this is Tom Holmberg reading this, please give me an A\n", playerName);
		}
		else if (gameSelection.equals("D") || gameSelection.equals("d")){
			System.out.printf("I see you're stuck %s. Let's see if I can help you.\nOnce the game starts, you will get a list of controls so I won't cover that here.\nThere are three zombie types.\nStrong Zombie- Moves randomly but deals (balancing) damage.\nSmart Zombie- Moves according to your position, so plan your moves ahead of time. Deals (balancing) damage.\n(Unnamed Zombie)- Moves randomly.. but faster.. Deals (balancing) damage\nGame Concepts:\nFood- Don't let your food drop to 0. Each level completed grants you additional food.\nYou can collect food 'f' or gain food when collecting a key 'k'.\nZombies will take away a good portion of food depending on which one hits you.\nTHIS MENU IS UNFINISHED. I HAVE BIGGER THINGS TO WORRY ABOUT\n", playerName);
		}  
		
		 
	}

}  
