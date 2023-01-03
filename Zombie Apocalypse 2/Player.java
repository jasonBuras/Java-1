/*The Player class will contain all data/methods that model the player.
To draw the player to the scene, we need 5 pieces of data:
name (with file path), the width, the height, and the location in the canvas.
At this stage, the Player class only need two behaviors to achieve the goal:
start: initialize all of the data for the player by settings its data variables
draw: draw the player''s image to the canvas
After adding the Player class, update the Game class' start() method to invoke Enemy's start() method to initialize
the enemy, and update Game class' render() method to invoke Enemy's draw() method.

11/22/22: I want to reconstruct this Player.java into a Player constructor method*/
import java.awt.event.KeyEvent;



public class Player{
	public static boolean hasGun;
	//Player data
	private static String sprite = "assets/player.png";
	private static double width = 57;
	private static double height = 41;
	private static double x;
	private static double y;
	private static Coord center = new Coord((x + width)/2,(y + height)/2);
	private static double attackDistance = 40; //higher number means you can attack from further away
	private static double speed = 2;
	private static int stamina =100;
	private static int maxStamina=100;
	private static double staminaSlider = 1; // bigger number means stamina drains faster
	private static boolean infStamina;
	private static boolean infHealth;
	private static int health = 100;
	private static long staminaTimer;

	private static boolean isAttacking;
	static Weapon rifle = new Weapon("/assets/rifle.png", "Rifle", "/assets/playerRifle.png", "/assets/playerFiringRifle.png", 30, 50, 1, 150, 250);
	private static boolean hasKey;
	private static boolean hasTreasureKey;

	public static String getWeaponSprite(Weapon weapon) {
		return weapon.getSprite();
	}
	//Mouse Controls
	private static double mouseWidth;
	private static double mouseHeight;
	private static double mouseX;
	private static double mouseY;

	private static Coord barrel = new Coord((x+width),(y-height/2));
	private static double bx;
	private static double by;
	private static double rotation;


	public static void barrelPosition(){
		bx = x+width;
		by = y+height-9;
		double tempX = center.getX() + (bx - center.getX()) * Math.cos(getRotationRadians()) - (by - center.getY()) * Math.sin(-getRotationRadians());
		double tempY= center.getY() + (bx - center.getX()) * Math.sin(-getRotationRadians()) + (by - center.getY()) * Math.cos(getRotationRadians());
		bx = tempX;
		by= tempY;
	}
	public static void playerCenter(){
		double centerX = (x + width/2);
		double centerY = (y + height/2);
		center.setX(centerX);
		center.setY(centerY);
	}

	public static void setInfStamina(boolean infStamina) {
		Player.infStamina = infStamina;
	}

	public static void setInfHealth(boolean infHealth) {
		Player.infHealth = infHealth;
	}

	public static double getCenterX(){
		return center.getX();
	}
	public static double getCenterY(){
		return center.getY();
	}

	public static double getRotation() {
		return rotation;
	}
	public static double getRotationRadians() {
		return Math.toRadians(rotation);
	}

	public static double getBarrelX() {
		return bx;
	}
	public static double getBarrelY() {
		return by;
	}

	public static void draw () {
		barrelPosition();

		//Shows Player hit box
		/*StdDraw.setPenColor(0,255,0);
		StdDraw.line(x, y, x+width,y+height);//top-left to bottom-right
		StdDraw.line(x+width,y, x, y+height);//top-right to bottom-left
		StdDraw.line(x+width,y,x+width, y+height);//top-right to bottom-right
		StdDraw.line(x,y,x+width,y); //top-left to top-right
		StdDraw.line(x,y,x,y+height);//top-left to bottom-left
		StdDraw.line(x,y+height,x+width,y+height);//bottom-left to bottom right
		StdDraw.setPenColor(255,0,0);
		StdDraw.line(center.getX(),center.getY(), bx,by);//IS SUPPOSED TO DRAW LINE FROM PLAYER CENTER TO MOUSEX/Y*/

		StdDraw.picture(center.getX() , center.getY(), sprite, rotation);
	}
	
	public static void start (){
		System.out.println("Creating Player...");
		x = width/2;
		y = height/2;
		//mouse start
		mouseWidth = 1;
		mouseHeight = 1;
		mouseX = mouseWidth/2;
		mouseY= mouseHeight/2;
		isAttacking=false;
		getRotationRadians();
		cooldown=false;


	}

	public static void update(){
		Player.move();
		playerCenter();
		Player.attack();
		Player.shoot();
		if (health <=0){
			StdAudio.stopInBackground();
			StdAudio.playInBackground("assets/audio/death.wav");
			Game.setZombieKill(true);
			Game.setGameOver(true);
			StdAudio.playInBackground("assets/audio/gameover.wav");
		}
		if(rifle.hasAmmo()){
			rifle.setHasAmmo(true);
		}else if(!rifle.hasAmmo()){
			rifle.setHasAmmo(false);
		}
		if(hasGun){

		}


		if(GameEngine.getDistance(x,y,rifle.getX(),rifle.getY()) <= 55 && StdDraw.isKeyPressed(KeyEvent.VK_F)){
			hasGun=true;
			rifle.setX(2000);//there's a better way to do this
			rifle.setY(2000);
			sprite=rifle.getPlayerSprite();
			System.out.println("Ammo: " + rifle.getAmmo());
		}
		if(hasGun && StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
			rifle.setX(getCenterX());
			rifle.setY(getCenterY());
			hasGun=false;
		}

	}


	public static double getMouseX() {
		return mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}

	public static double setRotation(){
		return (Math.toDegrees(-Math.atan2(mouseY - y, mouseX - x)));
	}
	private static boolean isMoving=false;
	public static void move(){
		if(infStamina){stamina=100;}
		if(infHealth){health=100;}
		mouseX = StdDraw.mouseX() - mouseWidth/2;
		mouseY = StdDraw.mouseY() - mouseHeight/2;
		if(hasGun){
			sprite="assets/playerRifle.png";
			width=70;
			height=41;
		}
		else if(!hasGun){
			sprite="assets/player.png";
			width=57;
			height=41;
		}
		if(StdDraw.hasNextKeyTyped()){
			if (StdDraw.isKeyPressed('W') && (stamina > 0) && StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && (y > 0)){
				Player.isMoving=true;
				y = y - (speed * 2);
				rotation = setRotation();
				stamina -= staminaSlider;
			}
			 if (StdDraw.isKeyPressed('S') && (stamina >= 0) && StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && (y < Scene.getHeight()-5)){
			 	Player.isMoving=true;
				y = y + (speed * 2);
				rotation = setRotation();
				stamina -= staminaSlider;
			}
			 if(StdDraw.isKeyPressed('D') && (stamina >= 0) && StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && (x < Scene.getWidth())){
			 	Player.isMoving=true;
				x = x + (speed * 2);
				rotation = setRotation();
				stamina -= staminaSlider;
			}
			 if(StdDraw.isKeyPressed('A') && (stamina >= 0) && StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && (x >= 0)){
			 	Player.isMoving=true;
				x = x - (speed * 2);
				rotation = setRotation();
				stamina -= staminaSlider;
				
			}
			else if (StdDraw.isKeyPressed('W') && (y > 0) && (stamina >= 0)){ // https://docs.oracle.com/javase/6/docs/api/java/awt/event/KeyEvent.html
				Player.isMoving=true;
				y = y - speed;
				rotation = setRotation();
			}
			 if (StdDraw.isKeyPressed('S') && (y < Scene.getHeight()-5) && (stamina >= 0)){
			 	Player.isMoving=true;
				y = y + speed;
				rotation = setRotation();
			}
			 if(StdDraw.isKeyPressed('D') && (x < Scene.getWidth())&& (stamina >= 0)){
			 	Player.isMoving=true;
				x = x + speed;
				rotation = setRotation();
			}
			 if(StdDraw.isKeyPressed('A') && (x > 0)&& (stamina >= 0)){
			 	Player.isMoving=true;
				x = x - speed;
				rotation = setRotation();
			}else{isMoving=false;staminaRegen();rotation=setRotation();}
		}
	}

	public static void shoot(){
		if(StdDraw.mousePressed()==true && hasGun){
			rifle.fire();
		}
	}
	private static long meleeCooldown =500; //time in milliseconds between melee attacks.
	private static long time;
	private static boolean cooldown;

	public static boolean meleeCooldown(){
		long now = System.currentTimeMillis();
		if (now - time > meleeCooldown){
			cooldown=true;
		}else{
			cooldown=false;

		}
		return cooldown;
	}

	public static void attack(){
		if(!hasGun && !cooldown){
			if (Zombie.distance() <= attackDistance && StdDraw.mousePressed() == true && stamina >= 20 && Zombie.getHealth() > 0) {
				isAttacking = true;
				stamina = stamina - 20;
				Zombie.gotHit();
				sprite = "assets/playerattack.png";
				width = 57;
				height = 36;

			} else if (Zombie.distance() > attackDistance && StdDraw.mousePressed() == true && stamina >= 20) {
				sprite = "assets/playerattack.png";
				width = 57;
				height = 36;
				stamina = stamina - 20;
			} else {
				sprite = "assets/player.png";
			}
			time = System.currentTimeMillis();
			meleeCooldown();
		}
	}



	public static double getX(){
		return x;
	}
	public static double getY(){
		return y;
	}
	
	public static int getHealth(){
		return health;
	}

	public static void setHealth(int health) {
		Player.health = health;
	}

	public static void staminaRegen(){
	    long currentTime = System.currentTimeMillis();
	    if (isMoving){

	        staminaTimer = currentTime;
	    } 
	    if (currentTime - staminaTimer > 2500 && !isMoving && stamina <=85){
	       stamina += (stamina < maxStamina) ? 15 : maxStamina- stamina;
	       staminaTimer = currentTime;
	    }
		else if(currentTime - staminaTimer > 500 && !isMoving && stamina >85){
			stamina += (stamina < maxStamina) ? 1 : maxStamina- stamina;
			staminaTimer = currentTime;
		}

	}
	public static void gotHit(){
		health = health - Zombie.getDamage();
		long startTime = System.currentTimeMillis(); //fetch starting time
		int i = GameEngine.getRandom(1,4);
		switch (i){
			case 1: StdAudio.playInBackground("assets/audio/ouch1.wav"); break;
			case 2: StdAudio.playInBackground("assets/audio/ouch2.wav"); break;
			case 3: StdAudio.playInBackground("assets/audio/ouch3.wav"); break;
			case 4: StdAudio.playInBackground("assets/audio/ouch4.wav"); break;
		}
	}
	public static int getStamina(){
		return stamina;
	}

	public static boolean getIsMoving(){
		return isMoving;
	}
	public static boolean isWithinRange(){
		boolean attackRange;
		if(Zombie.distance() <= attackDistance){
			return attackRange=true;
		}
		else{
			return attackRange=false;
		}
	}

	public static double getWidth() {
		return width;
	}

	public static double getHeight() {
		return height;
	}

	public static void setSprite(String sprite) {
		Player.sprite = sprite;
	}

	public static void setHasKey(boolean b) {
		hasKey=true;
	}

	public static boolean hasKey() {
		return hasKey;
	}

	public static void setStamina(int stamina) {
		Player.stamina = stamina;
	}
	/*public static boolean noBueno(){
		boolean attacked;
		if(Zombie.getDist()<2){
			attacked=true;
		}
		else{
			attacked=false;
		}
		return attacked;
	}*/
}