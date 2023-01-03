public class Coord {

    private static double x;
    private static double y;
    private static double x1;
    private static double y1;
    private static final double xMin=0;
    private static final double xMax=Scene.getWidth();
    private static final double yMin=0;
    private static final double yMax=Scene.getHeight();
    private static double x2;
    private static double y2;

    public Coord(double x, double y) {
        this.x=x;
        this.y=y;
    }
    public Coord(double x, double y, double x2, double y2) {
        this.x=x;
        this.y=y;
        this.x2=x2;
        this.y2=y2;
    }


    public static double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public static double getY() {
        return y;
    }

    public static double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public static double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setY(double y) {
        this.y = y;
    }
    public static void newX(){
        int i = GameEngine.getRandom(1, 2);
        if (i == 1) {
            Zombie.setRx(Zombie.getX() + GameEngine.getRandom(50, 200));
        } else if (i == 2) {
            Zombie.setRx(Zombie.getX() - GameEngine.getRandom(50, 200));
        }
    }
    public static void newY(){
        int i = GameEngine.getRandom(1, 2);
        if (i == 1) {
            Zombie.setRy(Zombie.getY() + GameEngine.getRandom(50, 200));
        } else if (i == 2) {
            Zombie.setRy(Zombie.getY() - GameEngine.getRandom(50, 200));
        }
    }

    public static double getxMin() {
        return xMin;
    }

    public static double getxMax() {
        return xMax;
    }

    public static double getyMin() {
        return yMin;
    }

    public static double getyMax() {
        return yMax;
    }
}
