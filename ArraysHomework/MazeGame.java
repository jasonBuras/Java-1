//11-8-22: Left off on page 12.
public class MazeGame {
	private static boolean gameOver;
	private static int level;
	
	public static void start() {
		gameOver=false;
		level=0;
		World.start();
		Scene.start(level);
	}
	public static void update() {
		Player.update();
		if(Player.getX()==Exit.getX() && Player.getY()==Exit.getY()){
			level++;
			if(level==World.getLength()){
				gameOver=true;
			}
			else{
				Scene.start(level);
			}
		}
	}
	public static void render() {
		Scene.draw();
		Player.draw();
		Exit.draw();
		StdDraw.show(100);
	}
	
	public static void main(String[] args) {
		start();
		while(gameOver==false) {
			update();
			render();
		}
	}
}
