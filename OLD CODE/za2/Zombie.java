public class Zombie{
	//zombie data --> accessible by all zombie methods, scoped for as long as Game class runs.
	private static String image;
	private static String zombieDead="assets/zombiedead.png";
	private static int width;
	private static int height;
	private static double x;
	private static double y;
	private static double dist;
	private static double zombieSpeed=1;
	private static double randomSpeed = Math.random() * 3;
	private static int hitPower = 20;
	private static int health = 100;
	private static double rotation;
	private static long moveTimer;
	private static boolean isDead;
	private static String name = GameEngine.randomName();

	public Zombie(String image, int width, int height, double x, double y, int hitPower, int health) {

		this.image=image;
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.hitPower=hitPower;
		this.health=health;
	}

	//draw zombie
	public static void draw(){
		if (health>=75) {
			StdDraw.setPenColor(0, 255, 0);
		}else if(health<75 && health >=25){StdDraw.setPenColor(255, 255, 0);}
		else if(health<25){StdDraw.setPenColor(255,0,0);}
		StdDraw.text(x, y-height, name + " " + health);
		StdDraw.picture(x+width/2,y+height/2, image, rotation);
	}

	public static void start(){

		//Look into better move methods
		x = Math.floor(GameEngine.getRandom(550,850));
		y = Math.floor(GameEngine.getRandom(125,400));
	}
	public static void dead(){
		image=zombieDead;
		width=63;
		height=34;
		hitPower=0;
	}

	public static double distance(){
		 dist = Math.sqrt(Math.pow(x - Player.getX(),2)+Math.pow(y-Player.getY(),2));
			return Math.round(dist/zombieSpeed)*zombieSpeed;
	}

	public static void move(){ //Revisiting later
		if(health>0){
			isDead=false;
			image = "assets/zombie.png";
			width=57;
			height=46;
			zombieSpeed=1;
		}
		else if(health <=0){
			isDead=true;

		}



		if (distance() >= 250 && !Weapon.soundHeard()) {
				moveRandomly();
		}
		if (distance() < 250) {
				zombieSpeed = 2;
				if (x >= Player.getX()) {
					//rotation=270;
					rotation = setRotation();
					x = x - zombieSpeed;
				}
				if (y < Player.getY()) {
					//rotation=0;
					rotation = setRotation();
					y = y + zombieSpeed;
				}
				if (y >= Player.getY()) {
					//rotation=180;
					rotation = setRotation();
					y = y - zombieSpeed;
				}
				if (x < Player.getX()) {
					//rotation=90;
					rotation = setRotation();
					x = x + zombieSpeed;
				}
		} else if (distance() <= 20 && distance() > 2) {
				zombieSpeed = 1.75;

				if (x > Player.getX()) {
					//rotation=90;
					rotation = setRotation();
					x = x - zombieSpeed;
				}
				if (y < Player.getY()) {
					//rotation=180;
					rotation = setRotation();
					y = y + zombieSpeed;
				}
				if (y >= Player.getY()) {
					//rotation=0;
					rotation = setRotation();
					y = y - zombieSpeed;
				}
				if (x <= Player.getX()) {
					//rotation=270;
					rotation = setRotation();
					x = x + zombieSpeed;
				}
			}else if(Weapon.soundHeard()){
			trackGunshot();
			}

			if (distance() <= 2) {
				zombieSpeed = 1.25;
				Player.gotHit();
			}

		}

		public static void trackGunshot(){
			zombieSpeed = 3;
			if (x > Weapon.getGunshotX()) {
				//rotation=90;
				rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
				x = x - zombieSpeed;
			}
			if (y < Weapon.getGunshotY()) {
				//rotation=180;
				rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
				y = y + zombieSpeed;
			}
			if (y >= Weapon.getGunshotY()) {
				//rotation=0;
				rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
				y = y - zombieSpeed;
			}
			if (x <= Weapon.getGunshotX()) {
				//rotation=270;
				rotation = setRotation(Weapon.getGunshotX(), Weapon.getGunshotY());
				x = x + zombieSpeed;
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

	public static void update(){
		/*long now = System.currentTimeMillis();
		if(now-time > 1000){*/
		if(isDead==false){
			move();
		}
		else if(isDead==true){
			dead();
		}
		//}

	}

	public static double getRx() {
		return rx;
	}

	public static double getRy() {
		return ry;
	}

	//Testing Move Methods for smoother movement
	private static double randomMovement; //max number of coordinates possible to move

	private static Coord randCoord= new Coord(GameEngine.getRandom(700,900),GameEngine.getRandom(200,350));
	private static double rx = randCoord.getX();
	private static double ry = randCoord.getY();
	public static void randomX(){//in theory, this should work
		//In practice, it did kind of but let's make X/Y points an object.
		//Also, will make Zombie an object
		int i = GameEngine.getRandom(1, 2);
		if (i == 1) {
			rx = x + GameEngine.getRandom(100, 300);
		} else if (i == 2) {
			rx = x - GameEngine.getRandom(100, 300);
		}
	}
	public static void randomY(){//in theory, this should work
		int i = GameEngine.getRandom(1, 2);
		if (i == 1) {
			ry = y + GameEngine.getRandom(100, 300);
		} else if (i == 2) {
			ry = y - GameEngine.getRandom(100, 300);
		}
	}
	public static void generateRandomX(){
		int i = GameEngine.getRandom(1, 2);
		if (i == 1) {
			rx = x + GameEngine.getRandom(50, 200);
		} else if (i == 2) {
			rx = x - GameEngine.getRandom(50, 200);
		}
	}
	public static void generateRandomY(){
		int i = GameEngine.getRandom(1, 2);
		if (i == 1) {
			ry = y + GameEngine.getRandom(50, 100);
		} else if (i == 2) {
			ry = y - GameEngine.getRandom(50, 100);
		}
	}
	public static void moveRandomly() {
		zombieSpeed = GameEngine.getRandom(1.0, 2.0);
		double rot = Math.toDegrees(-Math.atan2(ry - y, rx - x));
		if (x >= rx - 1) {
			//rotation=270;
			rotation = rot;
			x = x - zombieSpeed;
		}
		if (y < ry + 1) {
			//rotation=0;
			rotation = rot;
			y = y + zombieSpeed;
		}
		if (y >= ry - 1) {
			//rotation=180;
			rotation = rot;
			y = y - zombieSpeed;
		}
		if (x < rx + 1) {
			//rotation=90;
			rotation = rot;
			x = x + zombieSpeed;
		} if(x-rx <= 2 && y-ry <= 2){
			randCoord.newX();
			randCoord.newY();
		}
	}
	public static int getHealth(){
		return health;
	}
	public static double getZombieX(){
		return x;
	}
	public static double getZombieY(){
		return y;
	}
	public static int getDamage(){
		return hitPower;
	}
	public static double getDist(){
		return dist;
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

	//This is some real nerdy shit but really fucking cool hope it works
	//Idea: Use ZombieX/Y and PlayerX/Y to calculate the angle and use that calculation to set Zombie.rotation ;)
	public static double setRotation(){
		return (Math.toDegrees(-Math.atan2(Player.getY() - y, Player.getX() - x)));
	}
	public static double setRotation(double tempX, double tempY){
		return (Math.toDegrees(-Math.atan2(tempY - y, tempX - x)));
	}

	public static double getRotation() {
		return rotation;
	}
}