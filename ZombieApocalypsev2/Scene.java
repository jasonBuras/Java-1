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
public class Scene{
	//Scene Data--> accessible only within this class since labeled private.
	private static String image;
	private static int width = 960;
	private static int height = 540;

	//Draws Scene
	public static void draw() {
		StdDraw.picture(width/2, height/2, image);
	}

	public static void start() {
	//Setup canvas data (size & scale)
	StdDraw.setCanvasSize(width, height); 	//set Canvas size of image size
	StdDraw.setXscale(0.0, width);			//set X=0 from right to left
	StdDraw.setYscale(height, 0.0);			//set Y=0 from top to bottom
	image = "assets/background.png";		//set scene image path
	}
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
}