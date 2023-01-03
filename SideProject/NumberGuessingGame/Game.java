/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 *Purpose: for fun
 *Description: A number between 1 and (User defined) is picked at random. The user is then prompted to
 *pick a number. If the number matches the number picked at random, the player wins.*/
import java.util.Scanner;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
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
    private static boolean youWin;
    private static boolean isNormalGame;
    private static boolean isComputerGuessing;
    private static boolean isRussianRoulette;
    private static int wins;
    private static String playerName;
    private static Player singlePlayer = new Player("");


    public static void main(String[] args){
        intro();
        GameEngine.delay(1);
        GameEngine.randomMessage();
        Scanner throwaway = new Scanner(System.in);
        System.out.print("Press [ENTER] to start the game...");
        String ta=throwaway.nextLine();
        playerName();
        mainMenu();
    }
    public static void playerName(){
        GameEngine.clear();
        Scanner player = new Scanner(System.in);
        System.out.println("What is your name?");
        System.out.print("Hi, my name is ");
        playerName = player.nextLine();
        if(playerName.equals("What?")||playerName.equals("what?")||playerName.equals("What")||playerName.equals("what")){
            GameEngine.clear();
            System.out.println("What is your name?");
            System.out.print("My name is ");
            playerName = player.nextLine();
            if(playerName.equals("Who?")||playerName.equals("who?")||playerName.equals("Who")||playerName.equals("who")){
                GameEngine.clear();
                System.out.println("My name is");
                GameEngine.delay(1);
                System.out.println("chka-chka,");
                GameEngine.delay(1);
                System.out.println("Slim Shady");
                playerName = "Marshall Bruce Mathers III";
                GameEngine.delay(3);
            }

        } singlePlayer.setName(playerName);

    }

    public static String getPlayerName() {
        return playerName;
    }

    public static Player transfer(){
        return new Player(singlePlayer.getName(), singlePlayer.getScore(), singlePlayer.getStreak(), singlePlayer.getWins(), singlePlayer.isOnFire());
    }

    public static void setPlayerName(String playerName) {
        Game.playerName = playerName;
    }

    public static void mainMenu() {

        GameEngine.clear();
        Scanner modePicker = new Scanner(System.in);
        System.out.printf(BLACK_UNDERLINED + ANSI_WHITE_BACKGROUND + "Number Guessing Game" + ANSI_RESET +"\nWelcome, %s\n\nMAIN MENU:\n" + GREEN_UNDERLINED + "1) Normal Game" + ANSI_RESET+ "\n[Computer picks a number and you have to guess]\n\n" + BLUE_UNDERLINED + "2) Let The Computer Guess!" + ANSI_RESET + "\n[This mode allows the user to pick a number and force the computer to guess]\n\n" + RED_UNDERLINED + "3) Russian Roulette" + ANSI_RESET + "\n[This mode uses a coinflip to decide which player goes first.\nIf a player guesses the number, they receive a point.\nFirst one to %d wins!]\n\n4) Settings\n[Allows the user to change gameplay settings]\n\n"+ ANSI_RED_BACKGROUND + ANSI_BLACK +"5) IMMEDIATELY TERMINATE THE PROGRAM" +ANSI_RESET+ "\n\nPick a number to select an option: ", playerName, RussianRoulette.getTargetScore());
        int gameMode = modePicker.nextInt();
        GameEngine.clear();
        while(gameMode<1||gameMode>5){
            System.out.printf(BLACK_UNDERLINED + ANSI_WHITE_BACKGROUND + "Number Guessing Game" + ANSI_RESET +"\nWelcome, %s\n\nMAIN MENU:\n" + GREEN_UNDERLINED + "1) Normal Game" + ANSI_RESET+ "\n[Computer picks a number and you have to guess]\n\n" + BLUE_UNDERLINED + "2) Let The Computer Guess!" + ANSI_RESET + "\n[This mode allows the user to pick a number and force the computer to guess]\n\n" + RED_UNDERLINED + "3) Russian Roulette" + ANSI_RESET + "\n[This mode uses a coinflip to decide which player goes first.\nIf a player guesses the number, they receive a point.\nFirst one to %d wins!]\n\n4) Settings\n[Allows the user to change gameplay settings]\n\n"+ ANSI_RED_BACKGROUND + ANSI_BLACK +"5) IMMEDIATELY TERMINATE THE PROGRAM" +ANSI_RESET+ "\n\nPick a number to select an option: ", playerName, RussianRoulette.getTargetScore());
            System.out.print("Selection out of bounds. Please select an option between 1-5: ");
            gameMode = modePicker.nextInt();
            GameEngine.clear();
        }
        switch (gameMode) {
            case 1: isNormalGame=true; isComputerGuessing=false; isRussianRoulette=false; NormalGame.start(); break;
            case 2: isNormalGame=false; isComputerGuessing=true; isRussianRoulette=false; ComputerGuesses.start(); break;
            case 3: isNormalGame=false; isComputerGuessing=false; isRussianRoulette=true; RussianRoulette.start(); break;
            case 4: Settings.start(); break;
            case 5: GameEngine.clear(); System.exit(0);
        }
    }
    public static void postGameScreen(){
        if(youWin){
            wins++;
            GameEngine.delay(2.5);
            for(int i=0;i<3;i++) {
                System.out.println(ANSI_RED + "You Won!" + ANSI_RESET);
                GameEngine.delay(250L);
                GameEngine.clear();
                System.out.println(ANSI_YELLOW + "You Won!" + ANSI_RESET);
                GameEngine.delay(250L);
                GameEngine.clear();
                System.out.println(ANSI_GREEN + "You Won!" + ANSI_RESET);
                GameEngine.delay(250L);
                GameEngine.clear();
                System.out.println(ANSI_BLUE + "You Won!" + ANSI_RESET);
                GameEngine.delay(250L);
                GameEngine.clear();
                System.out.println(ANSI_PURPLE + "You Won!" + ANSI_RESET);
                GameEngine.delay(250L);
                GameEngine.clear();
            }
        }
        else if(!youWin){
            wins=0;
            System.out.println(ANSI_RED + "You Lost!" + ANSI_RESET);
            GameEngine.delay(3);
            GameEngine.randomInsult();
        }
        Scanner playAgainSelect = new Scanner(System.in);
        System.out.print("What would you like to do?\n1) Return to the Main Menu\n2) Play Again (Restarts Currently Selected Game-mode)\n3) Terminate the program\nSelect Number: ");
        int playAgain = playAgainSelect.nextInt();
        GameEngine.clear();
        if (playAgain == 1) {
            mainMenu();
        }
        if(playAgain==2 && isNormalGame){
            NormalGame.start();
        }else if(playAgain==2 && isRussianRoulette){
            RussianRoulette.start();
        }else if(playAgain==2&&isComputerGuessing){
            ComputerGuesses.start();
        }
        if (playAgain == 3) {
            System.out.println("Thanks for playing!");

            GameEngine.delay(2.5);
            GameEngine.clear();
            System.exit(0);
        }
    }

    private static boolean changeBoth;

    public static boolean changeBoth() {
        return changeBoth;
    }

    public static void multiplayerPostgameScreen(){
        changeBoth=false;
        GameEngine.randomMessage();
        System.out.println();
        Scanner playAgainSelect = new Scanner(System.in);
        Scanner playerNameChange = new Scanner(System.in);
        System.out.print("What would you like to do?\n1) Return to the Main Menu\n2) Play Again (Restarts Currently Selected Game-mode)\n3) Check Previous Game Results\n4) Terminate the program\nSelect Number: ");
        int playAgain = playAgainSelect.nextInt();
        GameEngine.clear();
        if (playAgain == 1) {
            mainMenu();
        }
        if(playAgain==2){
            System.out.printf("NOTE: Changing player name also resets the player's wins and winstreak.\nSelect Player you wish to" +ANSI_RED + "CHANGE:"+ ANSI_RESET +"\n1) %s\n2) %s\n3) Both\n4) Neither\n", RussianRoulette.getPlayer1Name(), RussianRoulette.getPlayer2Name());
            int select=playAgainSelect.nextInt();
            switch(select){
                case 1: System.out.println("Change " + RussianRoulette.getPlayer1Name()+"'s name:"); String name1=playerNameChange.nextLine();RussianRoulette.setPlayer1Name(name1);RussianRoulette.multiplayerStart();break;
                case 2: System.out.println("Change " + RussianRoulette.getPlayer2Name()+"'s name:"); String name2=playerNameChange.nextLine();RussianRoulette.setPlayer2Name(name2);RussianRoulette.multiplayerStart();break;
                case 3: RussianRoulette.multiplayer(); changeBoth=true; break;
                case 4: RussianRoulette.multiplayerStart();break;
            }
        }
        if(playAgain==3){
            RussianRoulette.printWinLog();
            String temp = playAgainSelect.nextLine();
            System.out.println("Press [ENTER] to continue");
            multiplayerPostgameScreen();
        }
        if (playAgain == 4) {
            System.out.println("Thanks for playing!");

            GameEngine.delay(2.5);
            GameEngine.clear();
            System.exit(0);
        }
    }



    public static void youWin() {
        youWin=true;
        postGameScreen();
    }
    public static void youLose() {
        youWin=false;
        wins=0;
        postGameScreen();
    }

    public static int getWins() {
        return wins;
    }
    public static void intro(){
        GameEngine.randomIntro();
    }

}
