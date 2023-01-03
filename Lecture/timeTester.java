public class TimeTester{
	public static void main(String[] args){
		testToString();
		testHour();
		testMinute();
		testSecond();
		testToMeridiem();
		testConstructors();
	}


	public static void testToString(){
		String actual, expected;
		Time t1 = new Time();
		actual = t1.toString();
		expected= "0:00:00";
		boolean test = expected.equals(actual);
		System.out.printf("[toString] Test1: %b\n", test);
	}
	public static void testHour(){
		String actual, expected;
		boolean test;
		Time t1 = new Time();
		t1.setTime(12,0,0);
		
		actual = t1.toString();
		expected = "12:00:00";
		test = expected.equals(actual);
		System.out.printf("[testHour] Test1: %b\n", test);
		try{
			t1.setTime(-1,0,0);
		}
		catch(IllegalArgumentException e){
			actual = e.getMessage();
		}
		
		expected = "hour must be between 0-23";
		test = expected.equals(actual);
		//System.out.printf("[testHour] Test2: %b\n"), test;
		try{
			t1.setTime(24,0,0);
		}
		catch(IllegalArgumentException e){
			actual=e.getMessage();
		}
		
		expected="hour must be between 0-23";
		test = expected.equals(actual);
		System.out.printf("[testHour] Test3: %b\n", test);
	}

	public static void testMinute(){
		String actual, expected;
		boolean test;
		Time t1 = new Time();
		t1.setTime(0,30,0);
		
		actual = t1.toString();
		expected = "0:30:00";
		test = expected.equals(actual);
		System.out.printf("[testMinute] Test1: %b\n", test);
		try{
			t1.setTime(0,-30,0);
		}
		catch(IllegalArgumentException e){
			actual = e.getMessage();
		}
		
		expected="minute must be between 0-59";
		test = expected.equals(actual);
		System.out.printf("[testMinute] Test1: %b\n", test);
		try{
			t1.setTime(0,60,0);
		}
		catch(IllegalArgumentException e){
			actual = e.getMessage();
		}
		
		expected="minute must be between 0-59";
		test = expected.equals(actual);
		System.out.printf("[testHour] Test3: %b\n", test);
	}
	}

	public static void testSecond(){
		
	}

	public static void testToMeridiem(){
		
	}

	public static void testConstructors(){
		String actual,expected;
		boolean test;
		Time t1 = new Time();
		actual = t1.toString();
		expected="0:00:00";
		test = expected.equals(actual);
		System.out.printf("[Constructor] new Test(): %b\n", test);
		Time t2= new Time(13);
		actual = t2.toString();
		expected="13:00:00";
		test = expected.equals(actual);
		System.out.printf("[Constructor] new Test(3,33): %b\n", test);

	}
