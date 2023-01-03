/*The Scene class will contain all data/methods that model the game scene.
In this game, the scene will appear as a static background image.
Therefore, the Scene class requires three pieces of data:
1. name (with filepath) of the image file (datatype: text)
2. width in pixels of the image file (datatype: int)
3. height in pixels of the image file (datatype: int)
At this stage, the Scene class only needs two behaviors:
start: initialize all of the data of the scene by settings its data variables
draw: draw the scene's image to the canvas
*/
import java.util.Scanner;
public class Scene{
	//Scene Data--> accessible only within this class since labeled private.
	private static String image;
	private static int width;
	private static int height;

	public static void start(Scanner input) {
	//Setup canvas data (size & scale)
	Scene.width= input.nextInt();
	Scene.height = input.nextInt();
	Scene.image = "assets/" + input.next();

	StdDraw.setCanvasSize(width, height);
	StdDraw.setXscale(0.0, width);
	StdDraw.setYscale(height, 0.0);
	}
	//Draws Scene
	public static void draw() {
		//Draw Scene to canvas (origin at screen center)
		StdDraw.picture(width/2, height/2, image);
	}

	public static int getHeight(){
		return height;
	}
	

}