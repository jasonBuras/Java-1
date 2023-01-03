public class Key {
    private static double x;
    private static double y;
    private static int width;
    private static int height;
    private static String image;
    public Key() {
        this.x=GameEngine.getRandom(Scene.getWidth()/2,Scene.getWidth()-30);
        this.y=GameEngine.getRandom(30, Scene.getHeight()-30);
        this.width=30;
        this.height=30;
        this.image="assets/key.png";
    }
    public Key(String image) {
        this.x=GameEngine.getRandom(Scene.getWidth()/2,Scene.getWidth()-30);
        this.y=GameEngine.getRandom(30, Scene.getHeight()-30);
        this.width=30;
        this.height=30;
        this.image=image;
    }

    public Key(double x, double y) {
        this.x=x;
        this.y=y;
        this.width=30;
        this.height=30;
        this.image="assets/key.png";
    }
    public Key(double x, double y, int width, int height, String image) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.image=image;
    }

    public static void start() {
        System.out.println("Making Keys");

    }

    public static void draw() {
        StdDraw.picture(Key.x+Key.width/2,Key.y+Key.height/2,Key.image);
    }

    public static void update() {
        if(GameEngine.getDistance(Player.getCenterX(), Player.getCenterY(), Key.x, Key.y) <= 40){
            Key.x= 1000;
            Key.y= 1000;
            Player.setHasKey(true);
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
