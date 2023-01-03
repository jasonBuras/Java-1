public class Block extends GameObject {
    public static final int SIZE = 32;

    public Block(double x, double y){
        super(x*SIZE, y*SIZE, SIZE, SIZE, "/Assets/tile-brick.png");
    }

    public Block(double x, double y, String image){
        super(x*SIZE,y*SIZE, SIZE, SIZE, image);
    }
    public boolean isTouchingX(GameObject gameObject, double ratio){
        double overlap = this.getWidth() * ratio;
        return (Math.abs(this.getX() - gameObject.getX()) < overlap);
    }

    public boolean isTouchingY(GameObject gameObject, double ratio){
        double overlap = this.getHeight() * ratio;
        return (Math.abs(this.getY() - gameObject.getY()) < overlap);
    }

    public boolean isTouching(GameObject gameObject){
        return isTouchingY(gameObject,1.0) && isTouchingX(gameObject,0.75);
    }
}
