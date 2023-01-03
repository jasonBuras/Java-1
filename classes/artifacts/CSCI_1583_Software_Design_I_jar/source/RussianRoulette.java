/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 * Idea: A combination of both games in which a number is picked at random.
 * User selects heads or tails to decide who goes first between the player and computer.
 * The winner of the coin toss will pick a number within the given range. If they guess wrong,
 * the other play gets a chance to guess. Whichever player guesses the number first gets a point (or wins)*/
import java.util.Scanner;

//Toolkit.getDefaultToolkit().beep(); //produces default windows sound (dun dun dun)
public class RussianRoulette {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private static boolean gameOver;
    private static double randomNumber;
    private static boolean isCorrect;
    private static double range = 20;
    private static int playerNumber;
    private static int playerScore;
    private static boolean playerTurn;
    private static int computerScore;
    private static boolean computerTurn;
    private static int targetScore = 3; //Best of 3: 2 | Best of 5: 3 | Best of 7: 4 | etc.
    private static double computerGuess;
    private static boolean isFirstGuess;
    private static boolean coinTossWin;
    private static long delayTime = 2500; //sets delay for computer to think
    public static void start(){
        gameOver=false;
        Delay thinking = new Delay(delayTime);
        Delay wait = new Delay(1500);
        Scanner numberSelect = new Scanner(System.in);
        System.out.printf("Welcome to Number Guesser!\n"+ANSI_RED+ "[Russian Roulette]" +ANSI_RESET + "\nYou will pick a number between 1 and %.0f.\nThe computer will also pick a number in that range\nThis will be a race to see who can guess the number first\nThe answer MUST BE a WHOLE number (no decimals)\nPress [ENTER] to continue", range);
        String poo=numberSelect.nextLine();
        System.out.print("\033[2J\033[1;1H");

        coinflip();
        System.out.print("\033[2J\033[1;1H");
        System.out.printf("Game Master is picking a number in between (and including) 1-%.0f...\n", range);
        randomNumber = ((Math.round(Math.random() * (range -1))+1));
        thinking.countdown();
        System.out.print("\033[2J\033[1;1H");
        System.out.printf("Game Master has picked a number.\nBest of luck!\n", range);
        //Start game
        isFirstGuess=true;
        if(coinTossWin){
            while (!gameOver) {
                System.out.printf("Score:\nPlayer: %d/%d\nComputer: %d/%d\n", playerScore, targetScore, computerScore, targetScore);
                playerGuess();
                check();
                computerGuess();
                check();
            }
        }
        if(!coinTossWin) {
            while (!gameOver) {
                System.out.printf("Score:\nPlayer: %d/%d\nComputer: %d/%d\n", playerScore, targetScore, computerScore, targetScore);
                computerGuess();
                check();
                playerGuess();
                check();
            }
        }
    }

    public static void setRange(double range) {
        RussianRoulette.range = range;
    }
    private static void playerGuess(){
        playerTurn=true;
        computerTurn=false;
        Scanner playerInput = new Scanner(System.in);
        System.out.printf("Pick a number between 1-%.0f: ",range);
        playerNumber = playerInput.nextInt();
        //Out of bounds error handling
        while(playerNumber <= 0 || playerNumber > range){
            if (playerNumber <= 0 || playerNumber > range) {
                System.out.print("\033[2J\033[1;1H");
                System.out.printf("Your number was out of range, please select a number between (and including) 1 and %.0f\n",range);
                playerNumber = playerInput.nextInt();
            }
        }//end error handling
    }
    public static double getRandomNumber(){
        return randomNumber;
    }
    public static void computerGuess(){
        Delay cpuGuess=new Delay(3000);//Random value between 1.5-3 seconds
        System.out.println("Computer is picking a number...");
        cpuGuess.countdown();
        playerTurn=false;
        computerTurn=true;
        if(isFirstGuess){
            computerGuess = ((Math.round(Math.random() * (range -1))+1));
        }
        else if(!isFirstGuess){
            if (computerGuess == randomNumber) {
                /*Tomorrow: Change algorithm to consider the range when calculating*/
            } else if (computerGuess >= randomNumber && computerGuess - randomNumber >= 5) {
                computerGuess = computerGuess - 3;
            } else if (computerGuess <= randomNumber && computerGuess - randomNumber <= 5) {
                computerGuess = computerGuess + 3;
            } else if (computerGuess >= randomNumber && computerGuess - randomNumber >= 2 && computerGuess - randomNumber < 5) {
                computerGuess -= 2;
            } else if (computerGuess <= randomNumber && computerGuess - randomNumber <= 2 && computerGuess - randomNumber > 5) {
                computerGuess += 2;
            } else if (computerGuess >= randomNumber && computerGuess - randomNumber >= 1) {
                computerGuess--;
            } else if (computerGuess <= randomNumber && computerGuess - randomNumber <= 1) {
                computerGuess++;
            } else {
                System.out.println("[ELSE TRIGGERED] Please give me a heads up if you encounter this");
            }
        }
        isFirstGuess=false;
    }

    public static void coinflip() {//Coin Flip
        Scanner headsTails = new Scanner(System.in);
        Delay wait = new Delay(5000);
        System.out.println("Select Head or Tails!\n1) Heads\n2) Tails");
        int temp = headsTails.nextInt();
        while (temp < 1 || temp > 2) {
            System.out.print("\033[2J\033[1;1H");
            System.out.println("Your number was out of range, please select either\n[1] Heads or\n[2] Tails\n");
            temp = headsTails.nextInt();
        }
        System.out.print("\033[2J\033[1;1H");
        System.out.println("The coin is in the air...");
        Delay coinFlip = new Delay((long) (Math.random() * (4000 - 2000) + 2000));
        coinFlip.countdown();
        System.out.print("\033[2J\033[1;1H");
        double headsortails = (Math.round(Math.random() * (2 - 1)) + 1);
        if (headsortails == 1 && headsortails == temp) {
            System.out.println("It was Heads");
            System.out.println("You win the coin toss and will play first!");
            coinTossWin = true;
        } else if (headsortails == 2 && headsortails == temp) {
            System.out.println("It was tails");
            System.out.println("You win the coin toss and will play first!");
            coinTossWin = true;
        }
        if (headsortails == 1 && headsortails != temp) {
            System.out.println("It was Heads");
            System.out.println("You lose the coin toss and the computer plays first!");
            coinTossWin = false;
        } else if (headsortails == 2 && headsortails != temp) {
            System.out.println("It was tails");
            System.out.println("You lose the coin toss and the computer plays first!");
            coinTossWin = false;
        }//end coin flip
        wait.countdown();
    }


    private static void check(){
        if(playerNumber==randomNumber && playerTurn){
            System.out.print("\033[2J\033[1;1H");
            System.out.printf("Player Guessed: %d\nWhich was correct!\nYou win!\n",playerNumber);
            playerScore++;
            playerTurn=false;
            newRandomNumber();
            System.out.print("\033[2J\033[1;1H");
        }
        else if(playerNumber>randomNumber && playerTurn){
            System.out.print("\033[2J\033[1;1H");
            System.out.printf("Player Guessed: %d\nWhich was incorrect.\nTOO HIGH!\n",playerNumber);
            playerTurn=false;
        }else if(playerNumber<randomNumber && playerTurn){
            System.out.print("\033[2J\033[1;1H");
            System.out.printf("Player Guessed: %d\nWhich was incorrect.\nTOO LOW!\n",playerNumber);
            playerTurn=false;
        }

        if(computerGuess==randomNumber && computerTurn){
            System.out.print("\033[2J\033[1;1H");
            System.out.printf("Computer Guessed: %.0f\nWhich was correct!\nThe Computer wins!\n",computerGuess);
            computerScore++;
            computerTurn=false;
            newRandomNumber();
        }else if(computerGuess>randomNumber && computerTurn){
            System.out.print("\033[2J\033[1;1H");
            System.out.printf("Computer Guessed: %.0f\nWhich was incorrect.\n",computerGuess);
            computerTurn=false;
        }else if(computerGuess<randomNumber && computerTurn){
            System.out.print("\033[2J\033[1;1H");
            System.out.printf("Computer Guessed: %.0f\nWhich was incorrect.\n",computerGuess);
            computerTurn=false;
        }
        if(playerScore == targetScore){
            gameOver=true;
            playerScore=0;
            computerScore=0;
            NumberGuesser.youWin();

        }
        if(computerScore == targetScore){
            gameOver=true;
            playerScore=0;
            computerScore=0;
            NumberGuesser.youLose();
        }
    }
    public static void newRandomNumber(){
        randomNumber = ((Math.round(Math.random() * (range -1))+1));
    }

    public static int getTargetScore() {
        return targetScore;
    }

    public static void setTargetScore(int targetScore) {
        RussianRoulette.targetScore = targetScore;
    }
}
