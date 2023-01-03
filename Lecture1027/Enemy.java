public class Enemy{
	 String name;
	 int x;
	 int y;

	//constructor
	public Enemy(String name, int x, int y){
		this.x = x;
		this.y = y;
		this.name = name;
	}

	/*public static void start(String name, int x, int y){
		Enemy.name = name;
		Enemy.x= x;
		Enemy.y= y;
	}*/

	public String toText(){
		return String.format("[Enemy] %s, @ (%d, %d)", this.name, this.x, this.y);
	} 
}
