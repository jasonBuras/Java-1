import java.util.ArrayList;

public class Scene {
    private String image;
    private int width;
    private int height;
    private ArrayList<Enemy> monsters;
    private Player player;

    public Scene(){
        width=500;
        height=350;
        image = "assets/spacebackground.png";
        monsters = new ArrayList<Enemy>();

        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(0.0, width);
        StdDraw.setXscale(height,0.0);
    }
    public void draw(){
        StdDraw.picture(width/2, height/2, image);
        for (Enemy monster : monsters){
            monster.draw();
        }
        player.draw();
    }

    public void addMonster(){
        double x = 32 + (Math.random() * (width - 64));
        double y = -32;
        Enemy star = new Enemy(x,y);
        monsters.add(star);
    }
    public void update(){
        for (Enemy monster : monsters){
            monster.move();
        }
    }

    public Player getPlayer(){
        return this.player;
    }

    public ArrayList<Enemy> getMonsters() {
        return this.monsters;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
