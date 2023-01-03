import java.util.Scanner;

/*Game Engine for terminal based games. I am creating this to set up a foundation to make Terminal Based games easier to make
 *Author: Jason Buras (Discord: rambeaux504#3269)
 * Untested as of 11/19/2022                                                                                                */
public class GameEngine {

    /* delay():
    * The delay() method was the inspiration behind creating a Game Engine for Terminal based Java Games.
    * It started here and snowballed into the rest of the methods.
    *
    * How to Use:
    * Simply use GameEngine.delay(seconds); in your code to delay for given seconds
    * Do note that in order to set a delay by milliseconds you will need to include "L" at the
    * end of your number if you choose to just type in a number.
    * Example:
    * GameEngine.delay(2) will delay the code for 2 seconds (2000 milliseconds) (Method automatically converts)
    * GameEngine.delay(2000L) will delay the code for 2000 milliseconds (2 seconds) */
    public static void delay(int seconds){//Tested and works. look into just casting this method into delay(long)
        long mil= (long) (seconds* 1000L);
        long startTime = System.currentTimeMillis();
        while(false|| (System.currentTimeMillis() - startTime) < mil){/*do nothing*/}
    }
    public static void delay(double seconds){//Tested and works. look into just casting this method into delay(long)
        long mil= (long) (seconds*1000);
        long startTime = System.currentTimeMillis();
        while(false|| (System.currentTimeMillis() - startTime) < mil){/*do nothing*/}
    }
    public static void delay(long milli){//Tested and works.
        long startTime = System.currentTimeMillis();
        while(false|| (System.currentTimeMillis() - startTime) < milli){/*do nothing*/}
    }
    /*1: Commented delay(int/double, int/double); out due to bugs
     *   In the meantime:
     *   Use double value = GameEngine.getRandom(double, double);
     *   GameEngine.delay(value);
     *   for random timers
     *   -Pending complete removal following further testing */
    //begin removal #1
    /*public static void delay(int min, int max){
        double x = min;
        double y = max;
        long mil= (long) getRandom(x,y);
        long startTime = System.currentTimeMillis();
        while(false|| (System.currentTimeMillis() - startTime) < mil){*//*do nothing*//*}
    }
    public static void delay(double min, double max){
        long mil= (long) getRandom(min,max);
        long startTime = System.currentTimeMillis();
        while(false|| (System.currentTimeMillis() - startTime) < mil){*//*do nothing*//*}
    }*/ //end removal 1

    /*clear():
    * Anytime you want to clear the terminal, just type GameEngine.clear(); */
    public static void clear() {//Tested and works
        System.out.print("\033[2J\033[1;1H");
    }
    /*getRandom:
     * Pick a random int/double in between a given min and max
     * How to use: "int x = getRandom(4,10);"
     * x is assigned a random value inbetween 4 and 10 (inclusive)*/
    public static int getRandom(int min, int max) {//Tested and works.
        double x = min;
        double y = max;
        return (int) Math.round((Math.random() * (y - x)) + x);//Remove the Math.round() if you want decimals in your random number
    }
    public static double getRandom(double min, double max) {//Tested and works.
        double x=min;
        double y=max;
        return (Math.random() * (y - x)) + x;//Add Math.round() if you don't want decimals in your random number
    }
    //[NOT TESTED] getRandom(long,long)
    public static long getRandom(long min, long max) {//Tested and works.
        double x=min;
        double y=max;
        return (long) ((Math.random() * (y - x)) + x);
    }

    /*[NOT TESTED] Timed Message
    * User calls timedMessage(String message, int seconds) or
    *            timedMessage(String message, long milliseconds)
    * to display a String for given amount of seconds/milliseconds*/
    public static String timedMessage(String message, int seconds){//Not tested yet
        long mil = seconds*1000L;
        System.out.println(message);
        GameEngine.delay(mil);
        return "";
    }
    public static String timedMessage(String message, double seconds){//Not tested yet
        long mil = (long) (seconds*1000L);
        System.out.println(message);
        GameEngine.delay(mil);
        return message;
    }
    public static String timedMessage(String message, long mil){//Not tested yet
        System.out.println(message);
        GameEngine.delay(mil);
        return message;
    }
    /*[NOT TESTED] Countdown:
    * Countdown(String message, int number), simply counts down starting from given integer to 0 and displays a message*/
    public static String Countdown(String message, int seconds){//Not tested yet
        for(int i=seconds;i>=seconds;i--){
            System.out.print(i);
            GameEngine.delay(1);
        }
        return message;
    }
    public static String Countdown(String message, double seconds){//Not tested yet
        for(double i=seconds;i>=seconds;i--){
            System.out.print(i);
            GameEngine.delay(1);
        }
        return message;
    }


    /*EXPERIMENTAL STUFF
    This is where I will test some brainstormed ideas.
     GameEngine.exp______() "exp" denotes experimental feature*/

    /*EXPERIMENTAL FEATURE 1: Enhanced "Coin Flip"
    * This actually took a good bit of brain storming and I'm really impressed.
    * This (for now) will be a Number Guessing Game exclusive feature.
    *
    * Future Update Idea: Make the method return a boolean value so that future uses can create
    * a boolean value to trigger an event
    * Example: if(GameEngine.expCoinflip()){Player1 goes first} else if(!GameEngine.expCoinflip()){Player2 goes first*/
    private static String arrow;
    public static void expCoinflip(){
        arrow = "---";
        System.out.printf("%s "+ arrow + " Computer\n", NumberGuesser.getPlayerName());
        GameEngine.delay(1);
        GameEngine.clear();
        int count = GameEngine.getRandom(20,30);
        for(int i=0;i<count;i++){
            randomArrow();
            System.out.printf("%s "+ arrow + " Computer\n", NumberGuesser.getPlayerName());
            GameEngine.delay(500L);
            GameEngine.clear();
        }
        System.out.printf("%s "+ arrow + " Computer\n", NumberGuesser.getPlayerName());
        GameEngine.delay(1);
        if(arrow.equals("<--")){
            System.out.printf("%s goes first!\n", NumberGuesser.getPlayerName());
            RussianRoulette.setCoinTossWin(true);
        }else if(arrow.equals("-->")){
            System.out.println("Computer goes first!\n");
            RussianRoulette.setCoinTossWin(false);
        }

    }
    public static void randomArrow(){
        int rand = getRandom(10,20); //Even: _ wins Odd: _ wins
        for(int i=0;i<rand;i++){
            if(i%2==0){
                arrow="<--";
            } else{
                arrow="-->";
            }
        }
    }
}