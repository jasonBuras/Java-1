public class Player {
    private String image;
    private int width;
    private int height;
    private double x;
    private double y;
    private int speed;

    public Player(double x, double y){
        this.x = x;
        this.y = y;
        this.width = 32;
        this.height = 32;
        this.image = "assets/spaceman.png";
        this.speed = 10;

    }

    public void move(double x, double y){
        this.y=y;
        this.x=x;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isTouchingX(Enemy gameObject){
        int hitzone=24;
        return this.x<=gameObject.getX() + hitzone && gameObject.getX() <= this.x + hitzone;
    }

    public boolean isTouchingY(Enemy gameObject){
        int hitzone = 24;
        return this.y<= gameObject.getY()+hitzone && gameObject.getY() <= this.y + hitzone;
    }

    public boolean isTouching(Enemy gameObject){
        return this.isTouchingX(gameObject) && this.isTouchingY(gameObject);
    }



    public int getSpeed() {
        return this.speed;
    }

    public void draw(){
        StdDraw.picture(x, y, image, width, height);
    }
}
