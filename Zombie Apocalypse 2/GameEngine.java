import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import javax.sound.sampled.*;

/*Game Engine for terminal based games. I am creating this to set up a foundation to make Terminal Based games easier to make
 *Author: Jason Buras (Discord: rambeaux504#3269)
 * Towards the bottom I've included a comment to indicate what lines to take out if using in a game other than Number Guessing Game*/
public class GameEngine {
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
    public static double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt((Math.pow(x2-x1,2))+(Math.pow(y2-y1, 2)));
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
    public static void randomMessage(){
        int message = GameEngine.getRandom(1,33);//make sure max=highest case #
        switch (message) {
            case 1:
                System.out.println("I hope you have fun with this");
                GameEngine.delay(3);
                break;
            case 2:
                System.out.println("I just wanna make my momma proud");
                GameEngine.delay(3);
                break;
            case 3:
                System.out.println("You're the reason there are instructions on shampoo bottles.");
                GameEngine.delay(3);
                break;
            case 4:
                System.out.println("I hope your day is as pleasant as you");
                GameEngine.delay(3);
                break;
            case 5:
                System.out.println("There are 10 kinds of people in the world: those who understand binary, and 9 others.");
                GameEngine.delay(3);
                break;
            case 6:
                System.out.println("There are 10 kinds of people in the world: those who understand binary, and those who don't.");
                GameEngine.delay(3);
                break;
            case 7:
                System.out.println("There are three kinds of people in the world: those who can count and those who can't.");
                GameEngine.delay(3);
                break;
            case 8:
                System.out.println("What do you call dudes who love math?");
                GameEngine.delay(3);
                System.out.println("Algebros");
                GameEngine.delay(2);
                break;
            case 9:
                System.out.println("99 little errors in the code. 99 errors in the code, 1 error fixed... compile again, 100 fuckin' errors in the code.");
                GameEngine.delay(3);
                break;
            case 10:
                System.out.println("How did the programmer die in the shower?");
                GameEngine.delay(3);
                System.out.println("He read the shampoo bottle instructions: Lather. Rinse. Repeat.");
                GameEngine.delay(3);
                break;
            case 11:
                System.out.println("All programmers are playwrights, and all computers are lousy actors.");
                GameEngine.delay(3);
                break;
            case 12:
                System.out.println("Debugging: Removing the needles from the haystack.");
                GameEngine.delay(3);
                break;
            case 13:
                System.out.println("A programmer puts two glasses on his bedside table before going to sleep.");
                GameEngine.delay(2);
                System.out.println("A full one, in case he gets thirsty,");
                GameEngine.delay(2);
                System.out.println("and an empty one, in case he doesn't.");
                GameEngine.delay(2);
                break;
            case 14:
                System.out.println("You can catch flies with honey, but you can catch more honeys being fly");
                GameEngine.delay(3);
                break;
            case 15:
                System.out.println("Oh shit, did you do your Wordle today?");
                GameEngine.delay(3);
                break;
            case 16:
                System.out.println("My wife told me to stop impersonating a flamingo. I had to put my foot down.");
                GameEngine.delay(3);
                break;
            case 17:
                System.out.println("I was wondering why the frisbee kept getting bigger and bigger, but then it hit me.");
                GameEngine.delay(3);
                break;
            case 18:
                System.out.println("When life gives you melons, you might be dyslexic.");
                GameEngine.delay(3);
                break;
            case 19:
                System.out.println("Don't you hate it when someone answers their own questions? I do.");
                GameEngine.delay(3);
                break;
            case 20:
                System.out.println("It takes a lot of balls to golf the way I do.");
                GameEngine.delay(3);
                break;
            case 21:
                System.out.println("I know they say that money talks, but all mine says is 'Goodbye.'");
                GameEngine.delay(3);
                break;
            case 22:
                System.out.println("I can't believe I got fired from the calendar factory. All I did was take a day off.");
                GameEngine.delay(3);
                break;
            case 23:
                System.out.println("Most people are shocked when they find out how bad I am as an electrician.");
                GameEngine.delay(3);
                break;
            case 24:
                System.out.println("Never trust atoms; they make up everything.");
                GameEngine.delay(3);
                break;
            case 25:
                System.out.println("I was addicted to the hokey pokey, but then I turned myself around.");
                GameEngine.delay(3);
                break;
            case 26:
                System.out.println("Russian dolls are so full of themselves.");
                GameEngine.delay(3);
                break;
            case 27:
                System.out.println("Light travels faster than sound, which is the reason that some people appear bright before you hear them speak.");
                GameEngine.delay(3);
                break;
            case 28:
                System.out.println("A termite walks into the bar and asks, 'Is the bar tender here?'");
                GameEngine.delay(3);
                break;
            case 29:
                System.out.println("People who use selfie sticks really need to have a good, long look at themselves.");
                GameEngine.delay(3);
                break;
            case 30:
                System.out.println("Always borrow money from a pessimist. They'll never expect it back.");
                GameEngine.delay(3);
                break;
            case 31:
                System.out.println("I don't suffer from insanity, I enjoy every minute of it.");
                GameEngine.delay(3);
                break;
            case 32:
                System.out.println("Adam & Eve were the first ones to ignore the Apple terms and conditions.");
                GameEngine.delay(3);
                break;
            case 33:
                System.out.println("Real G's move in silence like lasagna");
                GameEngine.delay(3);
                break;
            case 34:
                System.out.println("I 100% copy/pasted this logo");
                GameEngine.delay(3);
                break;
            case 35:
                GameEngine.delay(3);
                for (int i = 0; i < 3; i++) {
                    System.out.println(ANSI_RED + "8=====D" + ANSI_RESET);
                    GameEngine.delay(250L);
                    GameEngine.clear();
                    System.out.println(ANSI_YELLOW + "	8=====D" + ANSI_RESET);
                    GameEngine.delay(250L);
                    GameEngine.clear();
                    System.out.println(ANSI_GREEN + "		8=====D" + ANSI_RESET);
                    GameEngine.delay(250L);
                    GameEngine.clear();
                    System.out.println(ANSI_BLUE + "			8=====D" + ANSI_RESET);
                    GameEngine.delay(250L);
                    GameEngine.clear();
                    System.out.println(ANSI_PURPLE + "					8=====D" + ANSI_RESET);
                    GameEngine.delay(250L);
                    GameEngine.clear();
                }
                GameEngine.delay(1);
                System.out.println("you had 1/35 chance of seeing that.. I'm sorry");
                break;
        }
    }
    public static void randomInsult() {
        int message = GameEngine.getRandom(1, 10);//make sure max=highest case #
        switch (message) {
            case 1:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(1.5);
                GameEngine.clear();
                System.out.println("Computer: gg ez");
                GameEngine.delay(3);
                break;
            case 2:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(5);
                GameEngine.clear();
                System.out.println("Computer: did you even have your monitor on?");
                GameEngine.delay(3);
                break;
            case 3:
                Scanner input = new Scanner(System.in);
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(5);
                GameEngine.clear();
                System.out.println("Computer: Hey what band did that song that goes: \"I'M RAAADIO ACTIVE\"?\nPlayer: ");
                String answer = "Player: " + input.nextLine();
                if(answer.equals("Imagine Dragons") || answer.equals("imagine dragons")) {
                    System.out.println("Computer is typing...");
                    GameEngine.delay(3);
                    GameEngine.clear();
                    System.out.println("Computer: Hey what band did that song that goes: \"I'M RAAADIO ACTIVE\"?");
                    System.out.println(answer);
                    System.out.println("Computer: Imagine Dragon deez nutz on your head");
                    GameEngine.delay(3);
                }else System.out.println("Ahh.. nevermind");GameEngine.delay(2);
                break;
            case 4:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(5);
                GameEngine.clear();
                System.out.println("Computer: I hope your day is as pleasant as you are bad at this game");
                GameEngine.delay(2);
                break;
            case 5:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(1);
                GameEngine.clear();
                System.out.println("Computer: gg");
                GameEngine.delay(2);
                break;
            case 6:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(1);
                GameEngine.clear();
                System.out.println("Computer: noob");
                GameEngine.delay(2);
                break;
            case 7:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(3);
                GameEngine.clear();
                System.out.println("Computer: I may have to show you my algorithm because your guesses sucked");
                GameEngine.delay(3);
                break;
            case 8:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(3);
                GameEngine.clear();
                System.out.println("Computer: If I had a nickel for every how dumb you are, I'd be millionaire");
                GameEngine.delay(2);
                break;
            case 9:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(1);
                GameEngine.clear();
                System.out.println("Computer: ez");
                GameEngine.delay(2);
                break;
            case 10:
                GameEngine.clear();
                System.out.println("Computer is typing...");
                GameEngine.delay(1.5);
                GameEngine.clear();
                System.out.println("Computer: lol ez");
                GameEngine.delay(2);
                break;
        }
    }

    public static String randomName(){
        String[] names = {"James","Robert","John","Michael","David","William","Richard","Joseph","Thomas",
                "Charles","Chris","Daniel","Matthew","Anthony","Mark","Donald","Paul","Andrew","Joshua",
                "George", "Timothy", "Ronald", "Edward", "Jason", "Jeffrey", "Ryan", "Jacob", "Gary", "Nicholas", "Eric",
                "Mary","Patricia","Jennifer","Linda","Elizabeth","Barbara","Susan","Jessica","Sarah",
                "Karen","Lisa","Nancy","Betty","Margaret","Sandra","Ashley","Kimberly","Emily","Michelle",
                /*take this line out to get rid of the easter egg names*/"Secret Agent Randy Beans", "Cleo Dookieslide", "Earl Turlet", "Gucci Membrane", "Holden Afart", "Rickyticky Bobbywobbin",
                "Amanda", "Melissa","Deborah","Pamela","Emma","Nicole","Helen","Samantha","Christine","Debra"};
        String name = names[getRandom(0, names.length)];
        return name;
    }


    /*[NOT TESTED] Countdown:
     * Countdown(String message, int number), simply counts down starting from given integer to 0 and displays a message*/
    public static void countdown(String message, int seconds){//Not tested yet
        for(int i=seconds;i>0;i--){
            System.out.println(message + i);
            GameEngine.delay(1);
        }
    }
    public static String Countdown(String message, int seconds){//Not tested yet
        for(int i=seconds;i>0;i--){
            System.out.println(i);
            GameEngine.delay(1);
            clear();
        }
        return message;
    }
    public static void Cheats(){
        Scanner input = new Scanner(System.in);
        System.out.println("Cheater!");
        System.out.print("Enter Cheat: ");
        String cheatCode = input.nextLine();
        if(cheatCode.equals("Run forest!")){
            Player.setInfStamina(true);
        }else if(cheatCode.equals("Health")){
            Player.setInfHealth(true);
        }else {System.out.println("[UNRECOGNIZED CHEAT]");delay(1);System.out.println("You will have to do Ctrl+C again."); countdown("This menu will close in: ",3);}

    }

}