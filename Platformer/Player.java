import java.util.ArrayList;

public class Player extends GameObject{
    private Physics physics;
    private boolean isJumping;
    private Pose currentPose;
    private int lives;


    public Player(double x, double y){
        super(x*Block.SIZE, y* Block.SIZE, Block.SIZE, Block.SIZE, "/Assets/link-down.png");
        this.physics= new Physics(4);
        this.isJumping=false;
        this.currentPose = Pose.RIGHT;
        this.lives =3;
        super.setImage(currentPose.getImage());
    }

    public void move(){
        double dx = this.getX() + physics.getVelocityX();
        double dy = this.getY() + physics.getVelocityY();
        super.move(dx,dy);
    }

    public void draw(){
        super.setImage(currentPose.getImage());
        super.draw();
    }

    public void moveLeft(){
        physics.moveLeft();
        this.currentPose = Pose.LEFT;
    }

    public void moveRight(){
        physics.moveRight();
        this.currentPose = Pose.RIGHT;
    }

    public void update(ArrayList<Block> blocks){
        physics.update(blocks,this);
        this.move();
    }
    public void lostLife(){
        this.lives -= 1;
    }

    public int getLives() {
        return lives;
    }

    public void jump(){
        if(this.isJumping==false){
            physics.jump();
            this.isJumping = true;
        }
    }

    public void isJumping(boolean isJumping){
        this.isJumping = isJumping;
    }
}
