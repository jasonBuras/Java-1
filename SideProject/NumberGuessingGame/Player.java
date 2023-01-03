import java.util.ArrayList;

public class Player {
    private int wins;
    private int score;
    private String name;
    private String originalName;
    private int guess;
    private boolean onFire;
    private int streak;

    public Player() {
        this.name="";
        this.score=0;
        this.guess=0;
        this.streak=0;
        this.wins=0;
        this.onFire=false;
        this.originalName=name;
    }
    public Player(String name) {
        this.name=name;
        this.score=0;
        this.guess=0;
        this.streak=0;
        this.wins=0;
        this.onFire=false;
        this.originalName=name;
    }

    //For transferring Player data
    public Player(String name, int score, int streak, int wins, boolean onFire) {
        this.name=name;
        this.score= score;
        this.guess=0;
        this.streak= streak;
        this.wins= wins;
        this.onFire= onFire;
        this.originalName=name;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }



    public boolean isHighLow() {//return true if high
        return guess > RussianRoulette.getRandomNumber();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int guess(int guess){
        return guess;
    }
    public int getStreak() {
        return this.streak;
    }
    public void setStreak(int check) {
        this.streak = check;
    }
    public void onFire(){
        if(this.isOnFire()){
            this.name = "["+this.getStreak()+"]" + this.originalName;
        }else {
            this.name = this.originalName;
        }
    }

    public boolean isOnFire() {
        boolean enFuego = false;
        if(this.getStreak() >= 2){
            enFuego = true;
        }else{enFuego=false;}
        return enFuego;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public int getWins() {
        return this.wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
