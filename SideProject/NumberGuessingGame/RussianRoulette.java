/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 * Idea: A combination of both games in which a number is picked at random.
 * User selects heads or tails to decide who goes first between the player and computer.
 * The winner of the coin toss will pick a number within the given range. If they guess wrong,
 * the other play gets a chance to guess. Whichever player guesses the number first gets a point (or wins)*/
import java.util.Scanner;
import java.util.ArrayList;

//Toolkit.getDefaultToolkit().beep(); //produces default windows sound (dun dun dun)
public class RussianRoulette {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_GREEN = "\u001B[31m";

    private static boolean gameOver;
    private static double randomNumber;
    private static boolean isCorrect;
    private static int range = 100;

    private static int playerScore;
    private static boolean playerTurn;

    private static boolean tooHigh;
    private static String highLow;
    private static String computerName;
    private static int computerScore;
    private static boolean computerTurn;
    private static int targetScore = 3; //Best of 3: 2 | Best of 5: 3 | Best of 7: 4 | etc.


    private static boolean coinTossWin;
    private static double gameMasterDelay = 3.0; //sets delay for game master to "think"

    private static String playerName= "";
    private static double median;
    private static int logLength;

    private static boolean wantsSPdata;
    private static Player computer = new Player();
    public static void start(){
        int bananaNut = 0;
        isMultiplayer=false;
        gameOver=false;
        Scanner numberSelect = new Scanner(System.in);
        while (bananaNut <= 0 || bananaNut > 2){
            System.out.printf(ANSI_RED + "[Russian Roulette]" + ANSI_RESET + "\n\nSingle Player or Multiplayer?\n1) Single Player (vs CPU)\n2) Multiplayer (You and another player share a keyboard\n", range);
            bananaNut = numberSelect.nextInt();
        }
        if(bananaNut==2){
            int x = 0;
            GameEngine.clear();
            isMultiplayer=true;
            while(x<=0||x>2) {
                System.out.printf("Would you like to import your player data from single player?\nName: [%s]\n1) Yes\n2) No\n", Game.getPlayerName());
                x = numberSelect.nextInt();
                GameEngine.clear();
            }
            if (x == 1) {
                System.out.println("Importing...");
                wantsSPdata = true;
                GameEngine.delay(1);
                GameEngine.clear();
            } else if (x == 2) {
                System.out.println("Proceeding...");
                GameEngine.delay(1);
            }
            multiplayer();
            GameEngine.clear();

        }
        computer.setName(GameEngine.randomName());
        numberSelect.nextLine();
        GameEngine.clear();
        System.out.printf(ANSI_RED+ "[Russian Roulette]" +ANSI_RESET + "\nYou will pick a number between 1 and %d.\nThe computer will also pick a number in that range\nThis will be a race to see who can guess the number first\nThe answer MUST BE a WHOLE number (no decimals)\nPress [ENTER] to continue", range);
        String lol =numberSelect.nextLine();
        System.out.println(lol);
        GameEngine.clear();

        //coinflip();
        GameEngine.expCoinflip();
        GameEngine.delay(3);
        GameEngine.clear();
        newRandomNumber();

        //Start game
        playerName = playervsCPU.getName();
        isFirstGuess=true;
        if(coinTossWin){
            while (!gameOver) {
                System.out.printf("Score:\n%s: %d/%d\nComputer: %d/%d\n", playervsCPU.getName(), playervsCPU.getScore(), targetScore, computerScore, targetScore);
                playerGuess();
                check();
                computerGuess();
                check();
            }
        }
        if(!coinTossWin) {
            while (!gameOver) {
                System.out.printf("Score:\n%s: %d/%d\nComputer: %d/%d\n",playervsCPU.getName(), playervsCPU.getScore(), targetScore, computerScore, targetScore);
                computerGuess();
                check();
                playerGuess();
                check();
            }
        }
    }
    private static Player playervsCPU = Game.transfer();
    public static Player transfer(){
        return new Player(playervsCPU.getName(), playervsCPU.getScore(), playervsCPU.getStreak(), playervsCPU.getWins(), playervsCPU.isOnFire());
    }

    public static void setRange(int range) {
        RussianRoulette.range = range;
    }

    private static int playerNumber;
    private static int lastGuess;
    private static void playerGuess(){
        playerTurn=true;
        computerTurn=false;
        Scanner playerInput = new Scanner(System.in);
        System.out.printf("Pick a number between 1-%d,\nor 0 to check previous guesses: ",range);
        playerNumber = playerInput.nextInt();
        //Out of bounds error handling
        while(playerNumber <= 0 || playerNumber > range){
            if (playerNumber < 0 || playerNumber > range) {
                GameEngine.clear();
                System.out.printf("Your number was out of range,\nplease select a number between (and including) 1 and %d\n",range);
                playerNumber = playerInput.nextInt();

            }
            else if (playerNumber==0){
                if(playerNumber==0) {
                    GameEngine.clear();
                    printGuessLog();
                    System.out.printf("Pick a number between 1-%d: ", range);
                    playerNumber = playerInput.nextInt();
                }
            }
        }//end error handling

        lastGuess=playerNumber; logGuess();
    }
    public static double getRandomNumber(){
        return randomNumber;
    }
    private static boolean cpuTooHigh;
    private static int computerGuess;
    private static boolean isFirstGuess;
    private static double cpuDelay = 3.0;
    private static int CPUmin=1;
    private static int CPUmax= range;

    public static void computerGuess(){
        System.out.println("Computer is picking a number...");
        GameEngine.delay(cpuDelay);
        playerTurn=false;
        computerTurn=true;
        if(isFirstGuess && lastGuess==0){//For first guess, adjust the randomness to keep it around the midway point in the range for realism
            computerGuess = range/2;
        }else if(isFirstGuess && lastGuess==50){
            double temp = GameEngine.getRandom(1,2);
            if(temp==1){
                computerGuess =(range/2) +1;
            }else if(temp==2){
                computerGuess = (range/2) -1;
            }
        }
        else if(!isFirstGuess){
            if(cpuTooHigh){
                CPUmax=lastCPUguess;
            }else if(!cpuTooHigh){
                CPUmin=lastCPUguess;
            }

            computerGuess=(CPUmax+CPUmin)/2;
        }

        if(computerGuess==lastGuess && !cpuTooHigh){
            computerGuess++;
        }else if(computerGuess==lastGuess&& cpuTooHigh){
            computerGuess--;
        }
        if (computerGuess > randomNumber) {
            cpuTooHigh=true;
        }else if(computerGuess<randomNumber){
            cpuTooHigh=false;
        }while(computerGuess>range){computerGuess--;}while(computerGuess<1){computerGuess++;}
        isFirstGuess=false;
        lastCPUguess = computerGuess;
    }
    private static int lastCPUguess;

    public static void setCoinTossWin(boolean coinTossWin) {
        RussianRoulette.coinTossWin = coinTossWin;
    }

    private static Player player1 = new Player("");
    private static Player player2 = new Player("");
    private static String player1Name = player1.getName();
    private static String player2Name = player2.getName();
    private static boolean p1Turn;
    private static boolean p2Turn;
    private static boolean isMultiplayer;
    private static boolean player1First = false;
    private static boolean player2First = false;
    private static Scanner player1Input = new Scanner(System.in);
    private static Scanner player2Input = new Scanner(System.in);
    public static void multiplayer() {
        isMultiplayer=true;

        if(wantsSPdata && !Game.changeBoth()){
            player1 = Game.transfer();
            System.out.println("Player Names");
            System.out.println("Player 1: " + player1.getName());
            System.out.print("Player 2: ");
            String p2 = player2Input.nextLine();
            player2.setName(p2);
        }else{
            System.out.println("Player Names");
            System.out.print("Player 1: ");
            String p1 = player1Input.nextLine();
            player1.setName(p1);
            System.out.print("Player 2: ");
            String p2 = player2Input.nextLine();
            player2.setName(p2);
        }

        GameEngine.clear();
        gameOver = false;
        p1Turn = false;
        p2Turn = false;
        player1First = false;
        player2First = false;
        player1.setScore(0);
        player2.setScore(0);

        multiplayerRandomNumber();
        int temp = GameEngine.enhCoinflip(player1, player2);
        if (temp == 1) {
            player1First = true;
            player2First = false;
        } else if(temp==2){
            player1First = false;
            player2First = true;
        }temp=0;GameEngine.delay(3);multiplayerStart();
    }

    public static String getPlayer1Name() {
        return player1Name;
    }

    public static String getPlayer2Name() {
        return player2Name;
    }

    public static void setPlayer1Name(String player1Name) {
        player1.setName(player1Name);
        player1.setStreak(0);
        player1.setWins(0);
    }

    public static void setPlayer2Name(String player2Name) {
        player1.setName(player2Name);
        player2.setStreak(0);
        player2.setWins(0);
    }

    public static void multiplayerStart(){
        roundOver=false;
        int p1guess = 0;
        int p2guess = 0;
        player1.setGuess(0);
        player2.setGuess(0);

        while (!gameOver || player1.getScore() < 4 || player2.getScore() < 4) {
            if (player1First) {//Player 1 Goes first
                System.out.printf("Scoreboard\n%s: %d/4\n%s: %d/4\n",player1.getName(), player1.getScore(),player2.getName(), player2.getScore());//Display Scoreboard
                p1Turn = true;//start player 1 turn
                p2Turn = false;
                System.out.printf("%s, please choose a number between 1 and %d (0 to see previous guesses): ", player1.getName(), range);
                p1guess = player1Input.nextInt();
                //Out of bounds error handling
                while (p1guess <= 0 || p1guess > range) {
                    if (p1guess < 0 || p1guess > range) {
                        GameEngine.clear();
                        System.out.printf("Your number was out of range,\nplease select a number between (and including) 1 and %d\n", range);
                        p1guess = player1Input.nextInt();

                    } else if (p1guess == 0) {
                        GameEngine.clear();
                        printGuessLog();
                        System.out.printf("Pick a number between 1-%d: ", range);
                        p1guess = player1Input.nextInt();
                    }
                }
                GameEngine.clear();//end error handling
                player1.setGuess(p1guess);
                multiplayerLog();
                multiplayerCheck();//end player1 turn

                System.out.printf("Scoreboard\n%s: %d/4\n%s: %d/4\n",player1.getName(), player1.getScore(),player2.getName(), player2.getScore());//Display Scoreboard
                p1Turn = false;//start player2 turn
                p2Turn = true;
                System.out.printf("%s, please choose a number between 1 and %d (0 to see previous guesses): ", player2.getName(), range);
                p2guess = player2Input.nextInt();
                while (p2guess <= 0 || p2guess > range) {
                    if (p2guess < 0 || p2guess > range) {
                        GameEngine.clear();
                        System.out.printf("Your number was out of range,\nplease select a number between (and including) 1 and %d\n", range);
                        p2guess = player2Input.nextInt();

                    } else if (p2guess == 0) {
                        GameEngine.clear();
                        printGuessLog();
                        System.out.printf("Pick a number between 1-%d: ", range);
                        p2guess = player2Input.nextInt();
                    }
                }
                GameEngine.clear();//end error handling
                player2.setGuess(p2guess);
                multiplayerLog();
                multiplayerCheck();//end player2 turn


            } else if (player2First) {//Player 2 Goes First
                System.out.printf("Scoreboard\n%s: %d/4\n%s: %d/4\n",player1.getName(), player1.getScore(),player2.getName(), player2.getScore());//Display Scoreboard
                p1Turn = false;//start player2 turn
                p2Turn = true;
                System.out.printf("%s, please choose a number between 1 and %d (0 to see previous guesses): ", player2.getName(), range);
                p2guess = player2Input.nextInt();
                while (p2guess <= 0 || p2guess > range) {
                    if (p2guess < 0 || p2guess > range) {
                        GameEngine.clear();
                        System.out.printf("Your number was out of range,\nplease select a number between (and including) 1 and %d\n", range);
                        p2guess = player2Input.nextInt();

                    } else if (p2guess == 0) {
                        GameEngine.clear();
                        printGuessLog();
                        System.out.printf("Pick a number between 1-%d: ", range);
                        p2guess = player2Input.nextInt();
                    }
                }
                GameEngine.clear();//end error handling
                player2.setGuess(p2guess);
                multiplayerLog();
                multiplayerCheck();//end player2 turn

                System.out.printf("Scoreboard\n%s: %d/4\n%s: %d/4\n",player1.getName(), player1.getScore(),player2.getName(), player2.getScore());//Display Scoreboard
                p1Turn = true;//start player 1 turn
                p2Turn = false;
                System.out.printf("%s, please choose a number between 1 and %d (0 to see previous guesses): ", player1.getName(), range);
                p1guess = player1Input.nextInt();
                //Out of bounds error handling
                while (p1guess <= 0 || p1guess > range) {
                    if (p1guess < 0 || p1guess > range) {
                        GameEngine.clear();
                        System.out.printf("Your number was out of range,\nplease select a number between (and including) 1 and %d\n", range);
                        p1guess = player1Input.nextInt();

                    } else if (p1guess == 0) {
                        GameEngine.clear();
                        printGuessLog();
                        System.out.printf("Pick a number between 1-%d: ", range);
                        p1guess = player1Input.nextInt();
                    }
                }
                GameEngine.clear();//end error handling
                player1.setGuess(p1guess);
                multiplayerLog();
                multiplayerCheck();//end player1 turn
            }
        }
        if(player1.getScore() == 4){
            String.format(player1.getName() + " has taken down " + player2.getName() + " in " + (player1.getScore() + player2.getScore()) + " games, \n" +player1.getScore()+"-"+player2.getScore());//untested
            winLog();
            GameEngine.delay(3);

        }if(player2.getScore()==4){
            String.format(player2.getName() + " has taken down " + player1.getName() + " in " + (player1.getScore() + player2.getScore()) + " games, \n" +player2.getScore()+"-"+player1.getScore());//untested
            winLog();
            GameEngine.delay(3);
        }
    }
    private static ArrayList<String> multiplayerLog = new ArrayList<>();
    private static void multiplayerLog() {
        String temp= "";
        String logger = "";
        if(p1Turn){
            if(player1.getGuess() > randomNumber){
                highLow = "high";
            }
            else if(player1.getGuess() < randomNumber){
                highLow="low";
            }
            temp = Integer.toString(player1.getGuess());
            logger = player1.getOriginalName() + " Guessed: " + temp + ", which was " + highLow;
            multiplayerLog.add(logger);
        }else if(p2Turn){
            if(player2.getGuess() > randomNumber){
                highLow = "high";
            }
            else if(player2.getGuess() < randomNumber){
                highLow="low";
            }
            temp = Integer.toString(player2.getGuess());
            logger = player2.getOriginalName() + " Guessed: " + temp + ", which was " + highLow;
            multiplayerLog.add(logger);
        }if(player1.getGuess()==randomNumber||player2.getGuess()==randomNumber){
            multiplayerLog.clear();
        }
    }

    private static ArrayList<String> winLog = new ArrayList<>();
    private static void winLog(){
        String logger="";
        if(player1.getScore()==4){
            logger= "[" + player1.getWins() + "]" + player1.getOriginalName() + " beat " + "[" + player2.getWins() + "]" + player2.getOriginalName();
        }else if(player2.getScore()==4){
            logger= "[" + player2.getWins() + "]" + player2.getOriginalName() + " beat " + "[" + player1.getWins() + "]" + player1.getOriginalName();
        }
        winLog.add(logger);
    }
    public static void printWinLog(){
        System.out.println("Previous Games:");
        System.out.println("-----------------------------");
        for (String log : winLog) {//look into this
            if (winLog.size() == 0) {
                System.out.println("No Previous Games Found");
                System.out.println("-----------------------------");
            } else {
                System.out.println(log);
                System.out.println("-----------------------------");
            }
        }
    }


    private static int temp;
    private static int counter1;
    private static int counter2;
    private static boolean onFire1;
    private static boolean onFire2;

    public static void multiplayerRandomNumber(){

        player1.setGuess(0);
        player2.setGuess(0);
        if(player1.getStreak() >1){
            System.out.println("Hey no pressure but.. " + player1.getName() + " has won " + player1.getStreak() + " games in a row!");
            GameEngine.delay(2);
            onFire1 = true;
            counter1++;
        }else if(player2.getStreak() >1){
            System.out.println("Hey no pressure but.. " + player2.getName() + " has won " + player2.getStreak() + " games in a row!");
            GameEngine.delay(2);
            onFire2 = true;
            counter2++;
        }
        if(onFire1){
            temp++;
            if(temp==counter1){}
            else if(temp>counter1){
                System.out.println("oof... " + player2.getName() + " has snapped " + player1.getName() +"'s streak");
                counter1=0;
                temp=0;
                onFire1=false;
                GameEngine.delay(2);
            }
        }
        if(onFire2){
            temp++;
            if(temp==counter2){}
            else if(temp>counter2){
                System.out.println("oof... " + player1.getName() + " has snapped " + player2.getName() +"'s streak");
                counter2=0;
                temp=0;
                onFire2=false;
                GameEngine.delay(2);
            }
        }
        int compliment = GameEngine.getRandom(1,20);
        if(compliment==1){System.out.println(player1.getName() + " is cool.");}else if(compliment==2){System.out.println(player2.getName() + " is cool.");}
        System.out.printf("Game Master is picking a number in between (and including) 1-%d...\n", range);
        randomNumber = GameEngine.getRandom(1,range);
        GameEngine.delay(gameMasterDelay);
        GameEngine.clear();
        System.out.printf("Game Master has picked a number.\nBest of luck!\n", range);
        GameEngine.delay(2);
    }
    private static int p1Streak;
    private static int p2Streak;

    public static void multiplayerCheck(){
        GameEngine.clear();
        if(player1.getGuess()==randomNumber && p1Turn){
            System.out.println(player1.getName() + " has won and will go first next round!");
            GameEngine.delay(3);
            int score1 = player1.getScore() + 1;
            player1.setScore(score1);
            player1First=true;
            player2First=false;
            multiplayerLog.clear();
            roundOver = true;
            p1Streak++;
            p2Streak=0;
            player1.setStreak(p1Streak);
            player2.setStreak(0);
            player1.onFire();
        }
        if(player2.getGuess()==randomNumber && p2Turn){
            System.out.println(player2.getName() + " has won and will go first next round!");
            GameEngine.delay(3);
            int score2 = player2.getScore() + 1;
            player2.setScore(score2);
            player1First = false;
            player2First = true;
            multiplayerLog.clear();
            roundOver = true;
            p1Streak=0;
            p2Streak++;
            player1.setStreak(0);
            player2.setStreak(p2Streak);
            player2.onFire();
        }

        if(!roundOver){
            if (player1.getGuess() > randomNumber && p1Turn) {
                System.out.println(player1.getGuess() + " was TOO HIGH");
                p1Turn = false;
            } else if (player1.getGuess() < randomNumber && p1Turn) {
                System.out.println(player1.getGuess() + " was TOO LOW");
                p1Turn = false;
            }
            if (player2.getGuess() > randomNumber && p2Turn) {
                System.out.println(player2.getGuess() + " was TOO HIGH");
                p2Turn = false;
            } else if (player2.getGuess() < randomNumber && p2Turn) {
                System.out.println(player2.getGuess() + " was TOO LOW");
                p2Turn = false;
            }
        }
        if (player1.getScore() == 4 || player2.getScore() == 4) {
            Game.multiplayerPostgameScreen();
            gameOver = true;
        }else if(player1.getGuess() == randomNumber || player2.getGuess()== randomNumber){multiplayerRandomNumber();}
        if(roundOver){multiplayerStart();}
        roundOver=false;
    }

    private static boolean roundOver;
    private static int playerWins;
    private static void check(){
        roundOver =false;
        if(playerNumber==randomNumber && playerTurn){
            GameEngine.clear();
            System.out.printf("%s Guessed: %d\nWhich was correct!\nYou win!\n",playerName, playerNumber);
            playerScore++;
            roundOver =true;
            CPUmin=1;
            CPUmax= range;
            logGuess();
            playerTurn=false;
            playervsCPU.setScore(playerScore);
            playerWins++;
            playervsCPU.setWins(playerWins);
            newRandomNumber();

            GameEngine.clear();
        } else if(playerNumber>randomNumber && playerTurn){
            GameEngine.clear();
            System.out.printf("%s Guessed: %d\nWhich was incorrect.\n"+ ANSI_RED + "TOO HIGH"+ ANSI_RESET + "\n" ,playerName, playerNumber);
            playerTurn=false;
        }else if(playerNumber<randomNumber && playerTurn){
            GameEngine.clear();
            System.out.printf("%s Guessed: %d\nWhich was incorrect.\n"+ ANSI_RED + "TOO LOW"+ ANSI_RESET + "\n" ,playerName, playerNumber);
            playerTurn=false;
        }

        if(computerGuess==randomNumber && computerTurn){
            GameEngine.clear();
            System.out.printf("Computer Guessed: %d\nWhich was "+ ANSI_GREEN + "CORRECT!"+ ANSI_RESET + "\nThe Computer wins!\n",computerGuess);
            computerScore++;
            roundOver =true;
            logGuess();
            computerTurn=false;
            newRandomNumber();
        }else if(computerGuess>randomNumber && computerTurn){
            GameEngine.clear();
            System.out.printf("Computer Guessed: %d, which was "+ ANSI_RED + "incorrect"+ ANSI_RESET + "\n" ,computerGuess);
            computerTurn=false;
        }else if(computerGuess<randomNumber && computerTurn){
            GameEngine.clear();
            System.out.printf("Computer Guessed: %d, which was "+ ANSI_RED + "incorrect"+ ANSI_RESET + "\n" ,computerGuess);
            computerTurn=false;
        }
        if(playerScore == targetScore){
            gameOver=true;
            playerScore=0;
            computerScore=0;
            Game.youWin();

        }
        if(computerScore == targetScore){
            gameOver=true;
            playerScore=0;
            computerScore=0;
            Game.youLose();
        }
        if(roundOver){
            isFirstGuess=true;
            CPUmin=1;
            CPUmax=range;
            roundOver=false;
        }
    }
    private static ArrayList<String> guessLog = new ArrayList<>(); //ArrayList object
    public static void logGuess(){
        if(lastGuess > randomNumber){
            highLow = "high";
        }
        else if(lastGuess < randomNumber){
            highLow="low";
        }
        String temp = Integer.toString(lastGuess);
        String logger = temp + " which was " + highLow;
        guessLog.add(logger);

        if(roundOver || computerGuess==randomNumber || playerNumber == randomNumber){
            guessLog.clear();
        }

    }
    public static void printGuessLog(){
        if (isMultiplayer) {
            System.out.println("Previous Guesses this round:");
            System.out.println("-----------------------------");
            for (String log : multiplayerLog) {//look into this
                if (multiplayerLog.size() == 0) {
                    System.out.println("You have not made any guesses this round");
                    System.out.println("-----------------------------");
                } else {
                    System.out.println(log);
                    System.out.println("-----------------------------");
                }
            }
        }else {
            System.out.println("Previous Guesses this round:");
            System.out.println("-----------------------------");
            for (String log : guessLog) {//look into this
                if (guessLog.size() <= 0) {
                    System.out.println("You have not made any guesses this round");
                    System.out.println("-----------------------------");
                } else {
                    System.out.println(log);
                    System.out.println("-----------------------------");
                }
            }
        }
    }

    public static void newRandomNumber(){
        lastGuess=0;
        playerNumber=0;
        computerGuess=0;
        System.out.printf("Game Master is picking a number in between (and including) 1-%d...\n", range);
        randomNumber = GameEngine.getRandom(1,range);
        GameEngine.delay(gameMasterDelay);
        GameEngine.clear();
        System.out.printf("Game Master has picked a number.\nBest of luck!\n");
    }

    public static int getTargetScore() {
        return targetScore;
    }

    public static void setTargetScore(int targetScore) {
        RussianRoulette.targetScore = targetScore;
    }

    public static void setGameMasterDelay(double gameMasterDelay) {
        RussianRoulette.gameMasterDelay = gameMasterDelay;
    }

    public static void setCpuDelay(double cpuDelay) {
        RussianRoulette.cpuDelay = cpuDelay;
    }

    public static int getRange() {
        return range;
    }
}
