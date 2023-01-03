/*[Jason Buras][Nov 2022][Discord: rambeaux504#3269]
 *Will look into enhancing this to where instead of having to make a new
 *Delay with a specified time, I can just do Delay.countdown(long)*/
public class Delay {
    long milsec;
    long startTime;
    public Delay(long time) {
        this.milsec=time;
    }
    public void countdown(){
        this.startTime = System.currentTimeMillis();
        while (false||(System.currentTimeMillis() - startTime) < milsec) {/*do nothing*/}
    }
}
