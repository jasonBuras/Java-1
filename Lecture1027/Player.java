public class Player{
	static String name;
	static int x;
	static int y;

	public static void start(String name, int x, int y){
		Player.name = name;
		Player.x= x;
		Player.y= y;
	}

	public static String toText(){
		return String.format("[Player] %s, @ (%d, %d)", Player.name, Player.x, Player.y);
	} 
}