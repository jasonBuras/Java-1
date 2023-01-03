/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 * This is the normal base game I started with. This idea helped inspire the game mode where the computer guesses.*/
import java.util.Scanner;
public class NormalGame {
    private static String plural;
    private static int guesses = 3;
    private static boolean isCorrect;
    private static double range =10;
    private static int staticGuesses =3;


    public static void start(){
        int errorCount = 0;
        int wins= Game.getWins();
        guesses=getStaticGuesses();
        isCorrect=false;
        Scanner guessInput = new Scanner(System.in);
        System.out.printf("Welcome to Number Guesser!\n[Normal Game]\nThe computer will pick a number between 1 and %.0f.\nYou will then have to guess the number.\nYou will be given three tries. The game will tell you if your answer is too high or too low.\nThe answer will be a WHOLE number (no decimals)\n", range);
        GameEngine.clear();
        double randomNumber = ((Math.round(Math.random() * (range -1))+1));
        if(Game.getWins()>2){
            System.out.printf("You've won %d games in a row!\n", wins);
        }
        System.out.printf("Computer has picked a number.\nGuess the number (1-%.0f): ",range);
        guesses--;
        for(int i=0;i<=guesses;guesses--){
            double guess=guessInput.nextDouble();
            while(guess <= 0 || guess > range){


                if (guess <= 0 || guess > range && errorCount < 5) {
                    GameEngine.clear();
                    System.out.printf("Your number was out of range, please select a number between (and including) 1 and %.0f\n",range);
                    guess = guessInput.nextInt();
                    errorCount++;
                } else if (guess <= 0 || guess > range && errorCount >= 5 && errorCount < 10) {
                    GameEngine.clear();
                    System.out.println("Possible Numbers:");
                    for(int j=1;j<=range;j++){
                        System.out.print(j + " ");
                        if(j%10==0){
                            System.out.println();
                        }
                    }
                    System.out.printf("\nPlease for the love of god pick one of ^THESE^ numbers..\nI mean shit you have %.0f to choose from. Why is this so difficult for you? ", range);
                    guess = guessInput.nextInt();
                    GameEngine.clear();
                    errorCount++;
                } else if (guess <= 0 || guess > range && errorCount >= 5 && errorCount >= 10){
                    System.out.println("You don't deserve to play.");
                    GameEngine.delay(2);//rage-quit timer
                    GameEngine.clear();
                    System.exit(0);
                }
            }
            GameEngine.clear();
            if(guesses==1){
                plural="";
            }else{
                plural="es";
            }

            if(guess==randomNumber){
                GameEngine.clear();
                isCorrect=true;
            }
            else if(guess>randomNumber){
                GameEngine.clear();
                System.out.printf("%.0f was incorrect.TOO HIGH!\nYou have %d guess%s left. TRY AGAIN: ",guess,guesses, plural);
            }
            else if(guess<randomNumber){
                GameEngine.clear();
                System.out.printf("%.0f was incorrect.TOO LOW!\nYou have %d guess%s left. TRY AGAIN: ",guess,guesses, plural);
            }
            if(guesses>=0 && isCorrect){
                GameEngine.clear();
                System.out.println("You got it!");
                System.out.printf("The number was: %.0f\n", randomNumber);
                Game.youWin();
            }
            if(guesses==0 && !isCorrect){
                GameEngine.clear();
                System.out.println("You have run out of guesses");
                System.out.printf("The number was: %.0f\n", randomNumber);
                Game.youLose();
            }
        }
    }


    public static int getGuesses() {
        return guesses;
    }
    public static int getStaticGuesses(){
        return staticGuesses;
    }

    public static void setGuesses(int guesses) {
        NormalGame.guesses = guesses;
        staticGuesses=guesses;
    }

    public static void setRange(double range) {
        NormalGame.range = range;
    }
}
