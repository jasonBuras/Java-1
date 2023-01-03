import java.util.ArrayList;
public class Zombie { //.....in hindsight I should've done a Zombie class, and then did extensions for each zombie type to keep it simple
    private static double speed;
    private static String deadZombie;
    private static String name;//will eventually become just Zombie following testing
    private static String image;
    private static int width;
    private static int height;
    private static double x;
    private static double y;
    private static boolean isDead;
    private static int hitPower;
    private static int health;
    private static double rotation;
    private static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

    public Zombie() {
        Zombie.name=GameEngine.randomName();
        Zombie.image="assets/zombie.png";
        Zombie.deadZombie="assets/zombiedead.png";
        Zombie.width=57;
        Zombie.height=46;
        Zombie.x = Math.floor(GameEngine.getRandom(550,850));
        Zombie.y = Math.floor(GameEngine.getRandom(125,400));
        Zombie.speed=2;
        Zombie.hitPower=25;
        Zombie.health=100;
        Zombie.rotation=0;
        Zombie.isDead=false;
        Zombie.randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
        Zombie.rx = randCoord.getX();
        Zombie.ry = randCoord.getY();
        Zombie.secondLife=false;
    }
    public Zombie(String name) {
        this.name=name;
        this.image="assets/zombie.png";
        this.deadZombie="assets/zombiedead.png";
        this.width=57;
        this.height=46;
        this.x = Math.floor(GameEngine.getRandom(550,850));
        this.y = Math.floor(GameEngine.getRandom(125,400));
        this.speed=2;
        this.hitPower=25;
        this.health=100;
        this.rotation=0;
        this.isDead=false;
        this.randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
        this.rx = randCoord.getX();
        this.ry = randCoord.getY();
        this.secondLife=false;
    }
    public Zombie(double x, double y) {
        this.name=GameEngine.randomName();
        this.image="assets/zombie.png";
        this.deadZombie="assets/zombiedead.png";
        this.width=57;
        this.height=46;
        this.x = x;
        this.y = y;
        this.hitPower=25;
        this.health=100;
        this.rotation=0;
        this.isDead=false;
        this.randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
        this.rx = randCoord.getX();
        this.ry = randCoord.getY();
        this.secondLife=false;
    }

    public Zombie(double speed, int hitPower, int health) {
        this.name=GameEngine.randomName();
        this.image="assets/zombie.png";
        this.deadZombie="assets/zombiedead.png";
        this.width=57;
        this.height=46;
        this.x = Math.floor(GameEngine.getRandom(550,850));
        this.y = Math.floor(GameEngine.getRandom(125,400));
        this.hitPower=hitPower;
        this.health=health;
        this.rotation=0;
        this.isDead=false;
        this.speed=speed;
        this.randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
        this.rx = randCoord.getX();
        this.ry = randCoord.getY();
        this.secondLife=false;
    }


    public Zombie(double x, double y, double speed, int hitPower, int health) {
        this.name=GameEngine.randomName();
        this.image="assets/zombie.png";
        this.deadZombie="assets/zombiedead.png";
        this.width=57;
        this.height=46;
        this.x=x;
        this.y=y;
        this.hitPower=hitPower;
        this.health=health;
        this.rotation=0;
        this.isDead=false;
        this.speed=speed;
        this.randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
        this.rx = randCoord.getX();
        this.ry = randCoord.getY();
        this.secondLife=false;

    }

    public Zombie(String image, String deadZombie, int width, int height, double x, double y, double speed, int hitPower, int health) {
        this.name=GameEngine.randomName();
        this.image=image;
        this.deadZombie=deadZombie;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        this.hitPower=hitPower;
        this.health=health;
        this.rotation=0;
        this.isDead=false;
        this.randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
        this.rx = randCoord.getX();
        this.ry = randCoord.getY();
        this.secondLife=false;

    }
    public Zombie(String name, String deadZombie, String image, int width, int height, double x, double y, double speed, int hitPower, int health) {
        this.name=name;
        this.deadZombie=deadZombie;
        this.image=image;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.hitPower=hitPower;
        this.health=health;
        this.rotation=0;
        this.isDead=false;
        this.secondLife=false;

    }
    public static void start(){
        System.out.println("Creating Zombies...");

    }
    public static void draw(){

        StdDraw.picture(Zombie.x+Zombie.width/2,Zombie.y+Zombie.height/2, Zombie.image, Zombie.rotation);
        if (Zombie.health>=75) {
            StdDraw.setPenColor(0, 255, 0);
        }else if(Zombie.health<75 && Zombie.health >=25){StdDraw.setPenColor(255, 255, 0);}
        else if(Zombie.health<25){StdDraw.setPenColor(255,0,0);}
        StdDraw.text(Zombie.x, Zombie.y-Zombie.height, Zombie.name + " " + Zombie.health);

        if (Zombie.health <= 0){
            Zombie.image= Zombie.deadZombie;
            Zombie.isDead=true;
        }
    }
    public static void update(){
        if(Zombie.health>0){
            Zombie.isDead=false;
        }else{Zombie.isDead=true;}
		/*long now = System.currentTimeMillis();
		if(now-time > 1000){*/
        if(Zombie.isDead==false){
            Zombie.move();
        }
        else if(Zombie.isDead==true){
            dead();
        }


    }
    private static boolean secondLife;
    public static void dead(){
        Zombie.image=Zombie.deadZombie;
        int rand = GameEngine.getRandom(1,10);
        if(rand==5 && !Zombie.secondLife){
            Zombie.health+=15;
            Zombie.secondLife=true;
        }else{Zombie.secondLife=false; Zombie.health=0;}
        if(Zombie.health>0 && Zombie.secondLife){
            Zombie.hitPower=10;
            Zombie.speed=.5;
            System.out.println("This is a feature not a bug. Zombie has a 10% chance to come back to life... again.. again.");
        }
        else{
            Zombie.hitPower = 0;
            Zombie.secondLife = true;
            Zombie.x = Zombie.getX();
            Zombie.y = Zombie.getY();
        }
    }
    public static double distance(){
        return Math.sqrt(Math.pow(x - Player.getX(),2)+Math.pow(y-Player.getY(),2));

    }
    public static void move() {
        if(!isDead){
            if (distance() >= 250 && !Weapon.soundHeard()) {
                moveRandomly();
            }
            if (distance() < 250) {
                speed = 2;
                if (x >= Player.getX()) {
                    //rotation=270;
                    rotation = setRotation();
                    x = x - speed;
                }
                if (y < Player.getY()) {
                    //rotation=0;
                    rotation = setRotation();
                    y = y + speed;
                }
                if (y >= Player.getY()) {
                    //rotation=180;
                    rotation = setRotation();
                    y = y - speed;
                }
                if (x < Player.getX()) {
                    //rotation=90;
                    rotation = setRotation();
                    x = x + speed;
                }
            } else if (distance() <= 20 && distance() > 2) {
                speed = 1.75;

                if (x > Player.getX()) {
                    //rotation=90;
                    rotation = setRotation();
                    x = x - speed;
                }
                if (y < Player.getY()) {
                    //rotation=180;
                    rotation = setRotation();
                    y = y + speed;
                }
                if (y >= Player.getY()) {
                    //rotation=0;
                    rotation = setRotation();
                    y = y - speed;
                }
                if (x <= Player.getX()) {
                    //rotation=270;
                    rotation = setRotation();
                    x = x + speed;
                }
            } else if (Weapon.soundHeard()) {
                trackGunshot();
            }

            if (distance() <= 2) {
                speed = 1.25;
                Player.gotHit();
            }
        }
    }
    public static void trackGunshot(){
        speed = 2;
        if (x > Weapon.getGunshotX()) {
            //rotation=90;
            rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
            x = x - speed;
        }
        if (y < Weapon.getGunshotY()) {
            //rotation=180;
            rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
            y = y + speed;
        }
        if (y >= Weapon.getGunshotY()) {
            //rotation=0;
            rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
            y = y - speed;
        }
        if (x <= Weapon.getGunshotX()) {
            //rotation=270;
            rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
            x = x + speed;
        }
    }
    public static void gotHit(){
        health =health- GameEngine.getRandom(20,80);
        if(health<0){
            health=0;
        }
    }
    public static void gotShot(double damage){
        health -= damage;
        if(health<0){
            health=0;
        }
    }

    private static Coord randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
    private static double rx = randCoord.getX();
    private static double ry = randCoord.getY();
    public static void moveRandomly() {
        speed = GameEngine.getRandom(1.0, 1.5);
        double rot = Math.toDegrees(-Math.atan2(ry - y, rx - x));
        if (x >= rx - 1) {
            //rotation=270;
            rotation = rot;
            x = x - speed;
        }
        if (y < ry + 1) {
            //rotation=0;
            rotation = rot;
            y = y + speed;
        }
        if (y >= ry - 1) {
            //rotation=180;
            rotation = rot;
            y = y - speed;
        }
        if (x < rx + 1) {
            //rotation=90;
            rotation = rot;
            x = x + speed;
        } if(x-rx <= 2 && y-ry <= 2){
            randCoord.newX();
            randCoord.newY();
        }
    }
    public static double setRotation(){
        return (Math.toDegrees(-Math.atan2(Player.getY() - y, Player.getX() - x)));
    }
    public static double setRotation(double tempX, double tempY){
        return (Math.toDegrees(-Math.atan2(tempY - y, tempX - x)));
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

    public void setY(double y) {
        this.y = y;
    }

    public static int getHealth() {
        return health;
    }
    public static int getDamage(){
        return hitPower;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public static double getRx() {
        return rx;
    }

    public static double getRy() {
        return ry;
    }
    public static void setRx(double rx) {
        while(rx>=Coord.getxMax()){rx--;}
        while(rx<=0){rx++;}
        Zombie.rx = rx;
    }

    public static void setRy(double ry) {
        while(ry>=Scene.getHeight()){ry--;}
        while(ry<=0){ry++;}
        Zombie.ry = ry;
    }
}
