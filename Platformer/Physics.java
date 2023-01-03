import java.util.ArrayList;

public class Physics {
    private int speed;
    private double gravity;
    private double terminal;
    private double velocityX;
    private double velocityY;
    private double friction;

    public Physics(int speed){
        this.speed=speed;
        this.gravity=0.3;
        this.terminal=8;
        this.velocityX=0.0;
        this.velocityY=0.0;
        this.friction=0.8;
    }

    public void jump(){
        velocityY= -speed*2;
    }

    public void update(ArrayList<Block> blocks, Player player){
        applyGravity();
        applyFriction();
        checkCollisions(blocks,player);
    }

    public void moveLeft(){
        if(velocityX > -speed){
            velocityX--;
        }
    }

    public void moveRight(){
        if(velocityX < speed){
            velocityX++;
        }
    }

    public void applyFriction(){
        velocityX *= friction;
    }

    public void checkCollisions(ArrayList<Block> blocks, Player player){
        for (Block block : blocks){
            if (block.isTouching(player)){
                checkCollisionFloor(block,player);
                checkCollisionCeiling(block,player);
                checkCollisionRight(block, player);
                checkCollisionLeft(block, player);
            }
        }
    }

    public void checkCollisionFloor(Block block, Player player){
        if (player.getY() < block.getY() && velocityY > 0){
            if(block.isTouchingX(player, 0.5)){
                this.velocityY=0;
                player.isJumping(false);
            }
        }
    }

    public void checkCollisionCeiling(Block block, Player player){
        if (player.getY() > block.getY() && velocityY < 0){
            this.velocityY *= -0.5;
        }
    }

    public void checkCollisionRight(Block block, Player player){
        if(player.getX() < block.getX() && velocityX > 0){
            if(block.isTouchingY(player,0.5)){
                this.velocityX *=-1;
            }
        }
    }

    public void checkCollisionLeft(Block block, Player player){
        if (player.getX() > block.getX() && velocityX < 0){
            if (block.isTouchingY(player,0.5)){
                this.velocityX *= -1;
            }
        }
    }

    public void applyGravity(){
        if(velocityY < terminal){
            velocityY += gravity;
        }
    }



    public double getVelocityX() {
        return this.velocityX;
    }

    public double getVelocityY() {
        return this.velocityY;
    }
}
