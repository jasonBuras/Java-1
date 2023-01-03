//Exit
public class Exit{
	private static double x;
	private static double y;
	private static double width;
	private static double height;
	private static boolean isTouching;

	
	public static void start(){
		System.out.println("Creating keys...");
		Exit.width=10;
		Exit.height=50;
		Exit.x= Scene.getWidth();
		Exit.y= Scene.getHeight()/2;
		int x = GameEngine.getRandom(1,100);
		if(x==50){
			GameEngine.delay(1);
			System.out.println("Oops... dropped the keys.");
			GameEngine.delay(2);
			System.out.println("my bad.");
			GameEngine.delay(1);
		}
	}
	public static void draw(){
		//draw landing pad
		if(!Player.hasKey()){
			StdDraw.setPenColor(StdDraw.RED);
		}else if(Player.hasKey()){
			StdDraw.setPenColor(StdDraw.GREEN);
		}
		StdDraw.filledRectangle(x, y, width, height);
	}
	public static double distance(){
		 return Math.sqrt((Math.pow(x - Player.getCenterX(),2))+(Math.pow(y-Player.getCenterY(),2)));

	}
	public static boolean isTouching(){
		if(distance() <=20 && Player.hasKey()){

			StdAudio.stopInBackground();
			StdAudio.playInBackground("assets/audio/powerup.wav");
			isTouching=true;
			System.out.print("Now that you have beat the game...\nThe cheat codes are:" +
					"\n\"Run forest!\"- gives infinite stamina\n" +
					  "\"Health\"- 	   gives infinite health (Couldn't think of something creative.\n");

		} else if (distance() <= 20 && !Player.hasKey() && !cooldown) {
			time = System.currentTimeMillis();
			cooldown();
			StdDraw.setPenColor(255,0,0);
			StdDraw.text(Scene.getWidth()/2, Scene.getHeight()/2, "You need the key!");
			StdDraw.show(2000);

		}
		return isTouching;
	}

	private static long time;
	private static boolean cooldown;
	public static boolean cooldown(){
		long now = System.currentTimeMillis();
		if (now - time > 3000){
			cooldown=false;
		}else{
			cooldown=true;

		}
		return cooldown;
	}

}