public class Controller {
    private Player player;

    public Controller(Player player){
        this.player= player;
    }

    public void keyboard(){
        if ((StdDraw.isKeyPressed(38) || StdDraw.isKeyPressed(32)|| StdDraw.isKeyPressed(87))){
            player.jump();
        }
        if(StdDraw.isKeyPressed(37) || StdDraw.isKeyPressed(65)){
            player.moveLeft();
        }
        if(StdDraw.isKeyPressed(39) || StdDraw.isKeyPressed(68)){
            player.moveRight();
        }
    }

    public void update(){
        keyboard();
    }
}
