public class Weapon {
    private static String image;
    private static String name;
    private static int ammo;
    private static double damage;
    private final String playerSprite;
    private final String playerFiring;
    private static int fireRate;
    private static boolean hasAmmo;
    private static double x;
    private static double y;
    private static double barrelX;
    private static double barrelY;
    private static double angle;
    private static long time;


    public Weapon(String image, String name, String playerSprite,String playerFiring, int ammo, double damage, int fireRate, double x, double y){
        this.image = image;
        this. name = name;
        this.playerSprite = playerSprite;
        this.playerFiring = playerFiring;
        this.ammo = ammo;
        this.damage = damage;
        this.fireRate = fireRate;
        this.x = x;
        this.y = y;
        this.hasAmmo=false;
    }
//                StdDraw.line(Player.getPlayerX()+Player.getWidth(),Player.getPlayerY()+Player.getHeight(),Player.getMouseX(),Player.getMouseY());StdDraw.show(10);

    public static void fire(){
        /*barrelX = (Player.getX()) + (Player.getWidth());
        barrelY = (Player.getY()) - (Player.getHeight()/2);
        angle = Player.getRotation() * (Math.PI/180);
        double tempX = (barrelX - Player.getX()) * Math.cos(angle) - (barrelY - Player.getY()) * Math.sin(angle) + Player.getX();
        double tempY = (barrelX - Player.getX()) * Math.sin(angle) + (barrelY - Player.getY()) * Math.cos(angle) + Player.getY();*/
        StdDraw.setPenColor(StdDraw.YELLOW);

        if(Weapon.hasAmmo && GameEngine.getDistance(Player.getMouseX(), Player.getMouseY(), Zombie.getX(), Zombie.getY()) <=50 && StdDraw.mousePressed()){

            if(GameEngine.getDistance(Player.getX(), Player.getY(), Zombie.getX(), Zombie.getY()) >= 500){//Long Range
                Zombie.gotShot(damage/GameEngine.getRandom(2,3));

            }else if(GameEngine.getDistance(Player.getX(), Player.getY(), Zombie.getX(), Zombie.getY()) >= 300 && GameEngine.getDistance(Player.getX(), Player.getY(), Zombie.getX(), Zombie.getY()) < 500){//mid Range
                Zombie.gotShot(damage/GameEngine.getRandom(1,2));
            }
            else if(GameEngine.getDistance(Player.getX(), Player.getY(), Zombie.getX(), Zombie.getY()) < 300){//short Range
                Zombie.gotShot(damage/GameEngine.getRandom(1,1.1));
            }
            ammo--;
            time = System.currentTimeMillis();
            soundHeard();
            gunshotX=Player.getCenterX();
            gunshotY=Player.getCenterY();
            StdDraw.line(Player.getBarrelX(),Player.getBarrelY(), Player.getMouseX(),Player.getMouseY());StdDraw.show(10);
            Player.setSprite("assets/playerShootingRifle.gif");
            StdAudio.playInBackground("assets/audio/gunshot.wav");


        }else if(Weapon.hasAmmo && GameEngine.getDistance(Player.getMouseX(), Player.getMouseY(), Zombie.getX(), Zombie.getY()) >50 && StdDraw.mousePressed()){
            ammo--;
            time = System.currentTimeMillis();
            soundHeard();
            gunshotX=Player.getCenterX();
            gunshotY=Player.getCenterY();
            StdDraw.line(Player.getBarrelX(),Player.getBarrelY(), Player.getMouseX(),Player.getMouseY());StdDraw.show(10);
            Player.setSprite("assets/playerShootingRifle.gif");
            StdAudio.playInBackground("assets/audio/gunshot.wav");

        }else{System.out.println("Out of ammo");}


    }


    public static void start() {
        cooldown = false;
        System.out.println("Loading weapons...");
    }
    private static boolean cooldown;
    private static double gunshotX;
    private static double gunshotY;
    public static boolean soundHeard(){
        long now = System.currentTimeMillis();
        if (now - time > 10000){
            cooldown=false;
        }else{
            cooldown=true;

        }
        return cooldown;
    }

    public static double getGunshotX() {
        return gunshotX;
    }

    public static double getGunshotY() {
        return gunshotY;
    }

    public static String getSprite(){
        return image;
    }
    public static void draw(){
        StdDraw.picture(getX(), getY(), getSprite());
    }

    public static void setHasAmmo(boolean hasAmmo) {
        Weapon.hasAmmo = hasAmmo;
    }

    public static String getName() {
        return name;
    }

    public static int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public boolean hasAmmo() {
        boolean temp = false;
        if(this.ammo > 0){
            temp=true;
        }else if(this.ammo<0) {
            temp = false;
        }
        return temp;
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

    public String getPlayerSprite() {
        return playerSprite;
    }
}
