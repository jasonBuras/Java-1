/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 *This handles settings (obviously) and other background tasks to practice better DRY methods*/
import java.util.Scanner;
public class Settings {
    private static int guessSet;
    private static int rangeSet;
    private static int targetScoreSet;
    private static double delaySet;
    private static String playerName;
    public static void start(){
        GameEngine.clear();
        Scanner selection=new Scanner(System.in);
        System.out.print("OPTIONS\n\nSelect 0 to change player name\n\n1) Set # of guesses.\n2) Change Range(Max Number Possible)\n3) Set Target Score (Russian Roulette)\n4) Set Delay (how long it takes for the computer to \"think\")\nSelect a number: ");
        int select= selection.nextInt();
        GameEngine.clear();
        while(select<0||select>4){
            System.out.println("Selection out of bounds. Please select an option between 0-4");
            select = selection.nextInt();
        }
        if(select==0){
            Scanner newName = new Scanner(System.in);
            String temp = Game.getPlayerName();
            System.out.println("What would you like to change your name to? ");
            playerName = newName.next();
            Game.setPlayerName(playerName);
            System.out.printf("You have changed your name from %s to %s", temp, playerName);

        }else if (select == 1) { //Set # of Guesses
            System.out.print("How many guesses would you like?\n[NOTE]: This also affects the computer's guess count\nThis does not affect Russian Roulette ");
            guessSet = selection.nextInt();
            NormalGame.setGuesses(guessSet);
            ComputerGuesses.setComputerGuesses(guessSet);
            GameEngine.clear();
                if(NormalGame.getGuesses() >= 10 || ComputerGuesses.getGuesses() >= 10){
                    System.out.println("You really need that many guesses...?");
                } else if(NormalGame.getGuesses()==1 || ComputerGuesses.getGuesses() == 1){
                    System.out.print("ONE GUESS??\nMaybe you'd like it if I made a Russian Roulette game\n");
                } else{
                    System.out.printf("You've changed the amount of guesses to %d, goodluck!\n", guessSet);
                }
        } else if (select==2) { //Change Range
          System.out.println("Please pick any whole number between 0 and 2,147,483,647.\n[NOTE]: This also affects the computer's range\nThis affects Russian Roulette ");
            rangeSet = selection.nextInt();
            NormalGame.setRange(rangeSet);
            ComputerGuesses.setRange(rangeSet);
            RussianRoulette.setRange(rangeSet);
            GameEngine.clear();
        } else if (select==3) { //Set Target Score
            System.out.printf("Current Value: %d\nThis determines the target score in order to determine who wins\nBest of 3 would be 2\nBest of 5 would be 3\nBest of 7 would be 4 etc.\nPlease set the target score: ", RussianRoulette.getTargetScore());
            targetScoreSet=selection.nextInt();
            RussianRoulette.setTargetScore(targetScoreSet);
            GameEngine.clear();
        } else if (select==4){ //Set Delay
            System.out.print("This will change the delay in seconds (1 sec=1000ms) for the computer/game master to pick a number\n(untested) ");
            delaySet = selection.nextDouble();
            RussianRoulette.setGameMasterDelay(delaySet);
            RussianRoulette.setCpuDelay(delaySet);
            ComputerGuesses.setDelayTime(delaySet);
            double milli = delaySet*1000;
            System.out.printf("You have set the delay to %.02f seconds, which is %.0f milliseconds", delaySet, milli);
        }
        GameEngine.delay(3);
        GameEngine.clear();
        Game.mainMenu();
    }

}
