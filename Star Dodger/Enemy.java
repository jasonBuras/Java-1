public class Enemy {
    private String image;
    private int width;
    private int height;
    private double x;
    private double y;
    private int speed;

    public Enemy(double x, double y){
        this.x=x;
        this.y=y;
        this.width = 32;
        this.height = 32;
        this.image="assets/asteroid.png";
        this.speed = (int) (3 + Math.random() * 10);
    }

    public void draw(){
        StdDraw.picture(x, y, image, width, height);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void move(){
        this.y += this.speed;
    }
}
