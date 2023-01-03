/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 *This handles settings (obviously) and other background tasks to practice better DRY methods*/
import java.util.Scanner;
public class Settings {
    private static int guessSet;
    private static int rangeSet;
    private static int targetScoreSet;
    public static void start(){
        System.out.print("\033[2J\033[1;1H");
        Scanner selection=new Scanner(System.in);
        System.out.print("OPTIONS\n1) Set # of guesses.\n2) Change Range(Max Number Possible)\n3) Set Target Score (Russian Roulette)\nSelect a number: ");
        int select= selection.nextInt();
        System.out.print("\033[2J\033[1;1H");
        while(select<1||select>3){
            System.out.println("Selection out of bounds. Please select an option between 1-3");
            select = selection.nextInt();
        }
        if (select == 1) {
            System.out.print("How many guesses would you like?\n[NOTE]: This also affects the computer's guess count\nThis does not affect Russian Roulette ");
            guessSet = selection.nextInt();
            NormalGame.setGuesses(guessSet);
            ComputerGuesses.setComputerGuesses(guessSet);
            System.out.print("\033[2J\033[1;1H");
        } else if (select==2) {
          System.out.println("Please pick any whole number between 0 and 2,147,483,647.\n[NOTE]: This also affects the computer's range\nThis affects Russian Roulette ");
            rangeSet = selection.nextInt();
            NormalGame.setRange(rangeSet);
            ComputerGuesses.setRange(rangeSet);
            RussianRoulette.setRange(rangeSet);
            System.out.print("\033[2J\033[1;1H");
        } else if (select==3) {
            System.out.printf("Current Value: %d\nThis determines the target score in order to determine who wins\nBest of 3 would be 2\nBest of 5 would be 3\nBest of 7 would be 4 etc.\nPlease set the target score: ", RussianRoulette.getTargetScore());
            targetScoreSet=selection.nextInt();
            RussianRoulette.setTargetScore(targetScoreSet);
            System.out.print("\033[2J\033[1;1H");
        }
        System.out.print("\033[2J\033[1;1H");
        if(NormalGame.getGuesses() >= 10 || ComputerGuesses.getGuesses() >= 10){
            System.out.println("You really need that many guesses...?");
        }
        else if(NormalGame.getGuesses()==1 || ComputerGuesses.getGuesses() == 1){
            System.out.print("ONE GUESS??\nMaybe you'd like it if I made a Russian Roulette game\n");
        }
        else{
            System.out.printf("You've changed the amount of guesses to %d, goodluck!", guessSet);
        }
        NumberGuesser.mainMenu();
    }

}
