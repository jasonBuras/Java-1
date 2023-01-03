//[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
import java.util.Scanner;
public class ComputerGuesses {
    private static String plural;
    private static int computerGuesses = 3;
    private static boolean isCorrect;
    private static double range = 10;
    private static int staticGuesses =3;
    private static int answer;
    private static double computerGuess;
    private static double delayTime = 2.5; //sets delay for computer to think


    public static void start(){
        int errorCount = 0;
        int wins= Game.getWins();
        computerGuesses =getStaticGuesses();
        isCorrect=false;
        Scanner numberSelect = new Scanner(System.in);
        System.out.printf("Welcome to Number Guesser!\n[Computer Guesses]\nYou will pick a number between 1 and %.0f.\nThe computer will then have to guess the number.\nIt will be given three tries.\nThe answer MUST BE a WHOLE number (no decimals)\nSelect a number: ", range);
        answer = numberSelect.nextInt();
        while(answer <= 0 || answer > range){

            if (answer <= 0 || answer > range && errorCount < 5) {
                System.out.print("\033[2J\033[1;1H");
                System.out.printf("Your number was out of range, please select a number between (and including) 1 and %.0f\n",range);
                answer = numberSelect.nextInt();
                errorCount++;
            } else if (answer <= 0 || answer > range && errorCount >= 5 && errorCount < 10) {
                System.out.print("\033[2J\033[1;1H");
                System.out.println("Possible Numbers:");
                for(int i=1;i<=range;i++){
                    System.out.print(i + " ");
                    if(i%10==0){
                        System.out.println();
                    }
                }
                System.out.printf("\nPlease for the love of god pick one of ^THESE^ numbers..\nI mean shit you have %.0f to choose from. Why is this so difficult for you? ", range);
                answer = numberSelect.nextInt();
                System.out.print("\033[2J\033[1;1H");
                errorCount++;
            } else if (answer <= 0 || answer > range && errorCount >= 5 && errorCount >= 10){
                System.out.println("You don't deserve to play.");
                GameEngine.delay(2.5);
                System.out.print("\033[2J\033[1;1H");
                System.exit(0);
            }
        }
        System.out.print("\033[2J\033[1;1H");
        if(errorCount>1){
            System.out.printf("It took you %d attempts to pick a number..",errorCount);
        }
        if(Game.getWins()>2){
            System.out.printf("You've won %d games in a row!\n", wins);
        }
        System.out.print("COMPUTER IS THINKING...\n");
        GameEngine.delay(delayTime);
        computerGuess = ((Math.round(Math.random() * (range -1))+1));

        computerGuesses--;
        for(int i = 0;i<=computerGuesses;computerGuesses--){
            System.out.printf("You picked: %d\nCOMPUTER HAS PICKED A NUMBER: %.0f\n", answer, computerGuess);
            if(computerGuesses ==1){
                plural="";
            }else{
                plural="es";
            }
            if(computerGuess==answer){
                isCorrect=true;
            }
            else if(computerGuess>answer){
                System.out.printf("%.0f was incorrect.TOO HIGH!\nThe computer has %d guess%s left.\n",computerGuess, computerGuesses, plural);
                GameEngine.delay(delayTime);
                guessAlgorithm();
            }
            else if(computerGuess<answer){
                System.out.printf("%.0f was incorrect.TOO LOW!\nThe computer has %d guess%s left.\n",computerGuess, computerGuesses, plural);
                GameEngine.delay(delayTime);
                guessAlgorithm();
            }
            if(computerGuesses >=0 && isCorrect){
                System.out.println("The computer got it!");
                System.out.printf("The computer guessed: %.0f\n", computerGuess);
                GameEngine.delay(2.5);
                Game.youLose();
            }
            if(computerGuesses ==0 && !isCorrect){
                System.out.println("The computer has run out of guesses");
                System.out.printf("Your number was: %d\n", answer);
                GameEngine.delay(2.5);
                Game.youWin();
            }
            GameEngine.delay(2.5);
            System.out.print("\033[2J\033[1;1H");


        }
    }

    private static void guessAlgorithm() {

        if(computerGuess==answer){
            isCorrect=true;
        }
        else if(computerGuess >= answer && computerGuess-answer >= 5){
            computerGuess = computerGuess - 3;
        }
        else if(computerGuess <= answer && computerGuess-answer <= 5 ){
            computerGuess = computerGuess + 3;
        }
        else if(computerGuess >= answer && computerGuess-answer >= 2 && computerGuess-answer < 5){
            computerGuess -= 2;
        }
        else if(computerGuess <= answer && computerGuess-answer <= 2 && computerGuess-answer > 5){
            computerGuess += 2;
        }
        else if(computerGuess >= answer && computerGuess-answer >=1 && computerGuesses != staticGuesses-1){
            computerGuess--;
        }
        else if(computerGuess <= answer && computerGuess-answer <=1 && computerGuesses != staticGuesses-1){
            computerGuess++;
        }
        else{
            System.out.println("[ELSE TRIGGERED] Please give me a heads up if you encounter this");
        }
    }


    public static int getGuesses() {
        return computerGuesses;
    }
    public static int getStaticGuesses(){
        return staticGuesses;
    }

    public static void setComputerGuesses(int computerGuesses) {
        ComputerGuesses.computerGuesses = computerGuesses;
    }

    public static void setRange(double range) {
        ComputerGuesses.range = range;
    }

    public static void setDelayTime(double delayTime) {
        ComputerGuesses.delayTime = delayTime;
    }
}
