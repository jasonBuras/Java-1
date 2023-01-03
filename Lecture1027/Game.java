public class Game{
	static Enemy[] enemies = new Enemy[3];

	public static void main(String[] args){
		start();
		show();
	}
	public static void start(){
		enemies[0] = new Enemy("goblin", 3,4);
		enemies[1] = new Enemy("drago", 7,7);
		enemies[2] = new Enemy("ork",5,1);
	}

	public static void show(){
		for(Enemy monster : enemies){
			System.out.println(monster);
		}
	}

	/*
	public static void start(){
		Player.start("Jason", 3, 4);//name and start position
		Enemy.start("Bitch", 1,2); 
	}
	public static void show(){
		String playerText = Player.toText();
		String enemyText= Enemy.toText();
		System.out.println(playerText);
		System.out.println(enemyText);
	}*/
}