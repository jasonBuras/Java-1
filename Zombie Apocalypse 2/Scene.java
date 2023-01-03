import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Scene{
	private static String background; 
	private static String endScreen;
	private static String intro;// 7292 ms
	private static double width;
	private static double height;
	private static String youWin;

	//Future Update: Turn Scene into an object in order to make multiple levels.


	//Commented Out temporarily
	public static void intro(){

		Scene.width= 960;
		Scene.height = 540;
		StdAudio.playInBackground("assets/audio/intro.wav");

		int rand = GameEngine.getRandom(1,3);
		switch (rand){
			case 1: Scene.intro="assets/intro.gif"; break;
			case 2: Scene.intro="assets/art1.png";break;
			case 3: Scene.intro="assets/art3.png";break;
			case 4: Scene.intro="assets/art5.png";break;
		}
		StdDraw.setCanvasSize((int) width, (int) height);
		StdDraw.setXscale(0.0, width);
		StdDraw.setYscale(height, 0.0);
		if(rand==1){
			long startTime = System.currentTimeMillis(); //fetch starting time
			while (false || (System.currentTimeMillis() - startTime) < 7292) {

				StdDraw.picture(width / 2, height / 2, intro);
			}
		}else{
			long startTime = System.currentTimeMillis(); //fetch starting time
			while (false || (System.currentTimeMillis() - startTime) < 3000) {

				StdDraw.picture(width / 2, height / 2, intro);
			}
		}
		Menu.start();

	}

	public static void start(){
		//Setup canvas data (size & scale)
		Scene.width= 960;
		Scene.height = 540;
		Scene.background= "assets/background1.png";
		StdDraw.setCanvasSize((int) width, (int) height);
		StdDraw.setXscale(0.0, width);
		StdDraw.setYscale(height, 0.0);
	}
	public static void draw(){
		StdDraw.picture(width/2, height/2, background);
	} 


	public static double getWidth(){
		return width;
	}

	public static double getHeight(){
		return height;
	}

	
	public static void playIntro(){
		StdDraw.picture(width, height, intro);
		
	}
	public static void youWin(){
		Scene.width= 960;
		Scene.height = 540;
		
		Scene.youWin="assets/youwin.gif";
		StdDraw.setCanvasSize((int) width, (int) height);
		StdDraw.setXscale(0.0, width);
		StdDraw.setYscale(height, 0.0);
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(false||(System.currentTimeMillis()-startTime)<5500){	
    		StdDraw.picture(width/2, height/2, youWin);
			StdDraw.show(100);
		}
		System.exit(0);

	}
	
	public static void gameOver(){
		Scene.width= 960;
		Scene.height = 540;
		Scene.endScreen ="assets/gameover.png";
		StdDraw.setCanvasSize((int) width, (int) height);
		StdDraw.setXscale(0.0, width);
		StdDraw.setYscale(height, 0.0);
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(false||(System.currentTimeMillis()-startTime)<5000){
			StdDraw.picture(width/2, height/2, endScreen);
			StdDraw.show(100);
		}
		System.exit(0);		
	}
}