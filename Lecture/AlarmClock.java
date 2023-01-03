public class AlarmClock {
    private Time alarm;

    public AlarmClock(){
        this.alarm=null;

    }
    public void setAlarm(Time time){
        this.alarm=time;
    }
    public void setAlarm(String timestring){
        Time time= new Time(timestring);
        this.alarm = time;
    }
    public boolean isAlarmTime(){
        Time now=Time.getCurrentTime();
        return now.compareTo(alarm) > 0;
    }

    public static void main(String[] args){
        AlarmClock clock = new AlarmClock();
        clock.setAlarm("12:15:00");
        while (clock.isAlarmTime()==false){

        }
        System.out.printf("WAKE UP");

    }
}
