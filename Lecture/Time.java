public class Time{
	private int hour;
	private int minute;
	private int second;

	public Time(int hour, int minute, int second){
		this.setTime(hour,minute,second);
	}

	public Time(int hour, int minute){
		this(hour, minute, 0);
	}

	public Time(int hour){
		this(hour, 0, 0);
	}

	public Time(){
		this(0,0,0);
	}

    public static Time getCurrentTime() {
		String timeString=String.format("%1$TH:");
    }

    public void setTime(int hour, int minute, int second){
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
	}

	private void setHour(int hour){
		if (hour<0||hour>23){
			throw new IllegalArgumentException("hour must be between 0-23");
		}
		this.hour=hour;
	}

	private void setMinute(int minute){
		if (minute < 0 || minute > 59){
			throw new IllegalArgumentException("minute must be between 0-59");
		}
		this.minute=minute;
	}

	private void setSecond(int second){
		if (second < 0 || second > 59){
			throw new IllegalArgumentException("second must be between 0-59");
		}
		this.minute=minute;
	}

	public String toMeridiem(){
		return String.format("%d:%02d:%02d %s",
				(hour % 12 ==0) ? 12 : hour % 12,
				minute,
				second,
				(hour < 12) ? "AM" : "PM");
	}

	public String toString(){
		return String.format("%d:%02d:%02d",hour, minute, second);
	}

	public boolean equals(Object obj){
		if(obj instanceof Time ){
			Time other = (Time) obj;
			isEquals=true;
			isEquals &= this.getHour() == other.getHour();
			isEquals &= this.getMinute() == other.getMinute();
			isEquals &= this.getSecond() == other.getSecond();
		}
		return isEquals;
	}

	public int compareTo (Time other){
		int result=0;
		String thisTimeStr= String.format("%d%02d%02d",this.getHour(),this.getMinute(),this.getSecond());
		String otherTimeStr= String.format("%d%02d%02d",other.getHour(),other.getMinute(),other.getSecond());
		int thisTimeInt= Integer.parseInt(thisTimeStr);
		int otherTimeInt= Integer.parseInt(otherTimeStr);
		if(thisTimeInt < otherTimeInt){
			result=-1;
		}
		else if(thisTimeInt==otherTimeInt){
			result=0;
		}
		else if(thisTimeInt > otherTimeInt){
			result=1;
		}

		return result;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}
}