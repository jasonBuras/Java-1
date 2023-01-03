/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 *Purpose: for fun
 *Description: A number between 1 and (User defined) is picked at random. The user is then prompted to
 *pick a number. If the number matches the number picked at random, the player wins.*/
import java.util.Scanner;

public class NumberGuesser{
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
    public static void main(String[] args){
        mainMenu();
    }
    public static void mainMenu() {

        System.out.print("\033[2J\033[1;1H");
        Scanner modePicker = new Scanner(System.in);
        System.out.printf(BLACK_UNDERLINED + ANSI_WHITE_BACKGROUND + "Number Guessing Game" + ANSI_RESET + "\n\nMAIN MENU:\n" + GREEN_UNDERLINED + "1) Normal Game" + ANSI_RESET+ "\n[Computer picks a number and you have to guess]\n\n" + BLUE_UNDERLINED + "2) Let The Computer Guess!" + ANSI_RESET + "\n[This mode allows the user to pick a number and force the computer to guess]\n\n" + RED_UNDERLINED + "3) Russian Roulette" + ANSI_RESET + "\n[This mode uses a coinflip to decide which player goes first.\nIf a player guesses the number, they receive a point.\nFirst one to %d wins!]\n\n4) Settings\n[Allows the user to change gameplay settings]\n\n"+ ANSI_RED_BACKGROUND + ANSI_BLACK +"5) IMMEDIATELY TERMINATE THE PROGRAM" +ANSI_RESET+ "\n\nPick a number to select an option: ", RussianRoulette.getTargetScore());
        int gameMode = modePicker.nextInt();
        System.out.print("\033[2J\033[1;1H");
        while(gameMode<1||gameMode>5){
            System.out.printf(BLACK_UNDERLINED + ANSI_BLUE_BACKGROUND + "Number Guessing Game" + ANSI_RESET + "\n\nMAIN MENU:\n" + GREEN_UNDERLINED + "1) Normal Game" + ANSI_RESET+ "\n[Computer picks a number and you have to guess]\n\n" + BLUE_UNDERLINED + "2) Let The Computer Guess!" + ANSI_RESET + "\n[This mode allows the user to pick a number and force the computer to guess]\n\n" + RED_UNDERLINED + "3) Russian Roulette (Work in Progress)" + ANSI_RESET + "\n[This mode uses a coinflip to decide which player goes first.\nIf a player guesses the number, they receive a point.\nFirst one to %d wins!]\n\n4) Settings\n[Allows the user to change gameplay settings]\n\n"+ ANSI_RED_BACKGROUND + ANSI_BLACK +"5) IMMEDIATELY TERMINATE THE PROGRAM" +ANSI_RESET+ "\n\nPick a number to select an option: ", RussianRoulette.getTargetScore());
            System.out.print("Selection out of bounds. Please select an option between 1-5: ");
            gameMode = modePicker.nextInt();
            System.out.print("\033[2J\033[1;1H");
        }
        switch (gameMode) {
            case 1: isNormalGame=true; isComputerGuessing=false; isRussianRoulette=false; NormalGame.start(); break;
            case 2: isNormalGame=false; isComputerGuessing=true; isRussianRoulette=false; ComputerGuesses.start(); break;
            case 3: isNormalGame=false; isComputerGuessing=false; isRussianRoulette=true; RussianRoulette.start(); break;
            case 4: Settings.start(); break;
            case 5: System.out.print("\033[2J\033[1;1H"); System.exit(0);
        }
    }
    public static void postGameScreen(){
        Delay wait = new Delay(2500);
        Delay colorChange = new Delay(200);
        if(youWin){
            wins++;
            wait.countdown();
            for(int i=0;i<5;i++) {
                System.out.println(ANSI_RED + "You Won!" + ANSI_RESET);
                colorChange.countdown();
                System.out.print("\033[2J\033[1;1H");
                System.out.println(ANSI_YELLOW + "You Won!" + ANSI_RESET);
                colorChange.countdown();
                System.out.print("\033[2J\033[1;1H");
                System.out.println(ANSI_GREEN + "You Won!" + ANSI_RESET);
                colorChange.countdown();
                System.out.print("\033[2J\033[1;1H");
                System.out.println(ANSI_BLUE + "You Won!" + ANSI_RESET);
                colorChange.countdown();
                System.out.print("\033[2J\033[1;1H");
                System.out.println(ANSI_PURPLE + "You Won!" + ANSI_RESET);
                colorChange.countdown();
                System.out.print("\033[2J\033[1;1H");
            }
        }
        else if(!youWin){
            wins=0;
            System.out.println(ANSI_RED + "You Lost!" + ANSI_RESET);
        }
        Scanner playAgainSelect = new Scanner(System.in);
        System.out.print("Would you like to play again?\n1) Yes (Returns to Main Menu)\n2) No (Terminates the Program)\n3) Play Again (Replays same game mode)\nSelect Number: ");
        int playAgain = playAgainSelect.nextInt();
        System.out.print("\033[2J\033[1;1H");
        if (playAgain == 1) {
            mainMenu();
        }
        else if (playAgain == 2) {
            System.out.println("Thanks for playing!");

            wait.countdown();
            System.out.print("\033[2J\033[1;1H");
            System.exit(0);
        }
        else if(playAgain==3 && isNormalGame){
            NormalGame.start();
        }
        else if(playAgain==3 && isComputerGuessing){
            ComputerGuesses.start();
        }
        else if(playAgain==3 && isRussianRoulette){
            RussianRoulette.start();
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

}
