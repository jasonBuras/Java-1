public class Hud{

	public static void draw(){
		
		if (Player.getStamina() >= 75){
			StdDraw.setPenColor(0, 255, 0); // https://introcs.cs.princeton.edu/java/15inout/colors.html
			StdDraw.text(Scene.getWidth()/2 + 100, 20, "Stamina");
			StdDraw.filledRectangle(Scene.getWidth()/2 + 100,30, Math.abs(Player.getStamina()/2), 2);
		}
		else if ((Player.getStamina() < 75) && (Player.getStamina() >= 20)){
			StdDraw.setPenColor(255, 255, 0);
			StdDraw.text(Scene.getWidth()/2 + 100, 20, "Stamina");
			StdDraw.filledRectangle(Scene.getWidth()/2 + 100,30, Math.abs(Player.getStamina()/2), 2);
		}
		else if (Player.getStamina() < 20){
			StdDraw.setPenColor(255, 0, 0);
			StdDraw.text(Scene.getWidth()/2 + 100, 20, "Stamina");
			StdDraw.filledRectangle(Scene.getWidth()/2 + 100,30, Math.abs(Player.getStamina()/2), 2);
		}
		if (Player.getHealth() >= 75){
			StdDraw.setPenColor(0, 255, 0);
			StdDraw.text(Scene.getWidth()/2 - 100, 20, "Health");
			StdDraw.filledRectangle(Scene.getWidth()/2 - 100,30, Math.abs(Player.getHealth()/2), 2);
		}
		else if ((Player.getHealth() < 75) && (Player.getHealth() >= 50)){
			StdDraw.setPenColor(255, 255, 0);
			StdDraw.text(Scene.getWidth()/2 - 100, 20, "Health");
			StdDraw.filledRectangle(Scene.getWidth()/2 - 100,30, Math.abs(Player.getHealth()/2), 2);
		}
		else if (Player.getHealth() < 50){
			StdDraw.setPenColor(255, 0, 0);
			StdDraw.text(Scene.getWidth()/2 - 100, 20, "Health");
			StdDraw.filledRectangle(Scene.getWidth()/2 - 100,30, Math.abs(Player.getHealth()/2), 2);
			StdDraw.picture(Scene.getWidth()/2,Scene.getHeight()/2,"assets/blood.png");
		}
		if(Player.hasKey()){
			StdDraw.picture(Player.getCenterX(),Player.getCenterY()+Player.getHeight(), "assets/key.png", 15, 15);
		}
		if(!Player.isWithinRange()){
			StdDraw.setPenColor(255, 0, 0);
			StdDraw.textLeft(9, 520, "[LMB] Attack (-20 Stamina)");//, Stamina("+Player.getStamina()+") too low.
		}
		else if(Player.isWithinRange() && Player.getStamina() < 20){
			StdDraw.setPenColor(0, 255, 0);
			StdDraw.textLeft(9, 520, "[LMB] Attack (-20 Stamina), Stamina("+Player.getStamina()+") too low.");
		}
		else if(Player.isWithinRange() && Player.getStamina() >= 20){
			StdDraw.setPenColor(0, 255, 0);
			StdDraw.textLeft(9, 520, "[LMB] Attack (-20 Stamina)");
		}
		StdDraw.setPenColor(255, 255, 255);
		//Debug
		/*StdDraw.text(400, 20, "PlayerX: " + Player.getX() );
		StdDraw.text(400, 40, "PlayerY: " + Player.getY() );
		StdDraw.text(600, 20, "ZombieX: " + Zombie.getZombieX() );
		StdDraw.text(600, 40, "ZombieY: " + Zombie.getZombieY() );
		//StdDraw.text(800, 20, "Levels Complete: " + Exit.getLevelsComplete() );
		StdDraw.text(800, 40, "Random X:  " + Zombie.getRx() );
		StdDraw.text(800, 20, "Random Y: " + Zombie.getRy() );*/
		//END HUD

		//Start Game Overlay
		if(GameEngine.getDistance(Player.getCenterX(),Player.getCenterY(),Player.rifle.getX(),Player.rifle.getY()) <= 50){
			StdDraw.text(Player.getCenterX(),Player.getCenterY()-Player.getHeight(), "Press [F] to pickup weapon");
		}if(Player.hasGun){
			StdDraw.text(Player.getCenterX(),Player.getY()-40, "Press [SPACE] to drop weapon");
		}if(Player.hasGun && !Player.rifle.hasAmmo()){
			StdDraw.text(Player.getX()+ 30,Player.getY()-25, "Weapon has no ammo!");
		}else if(Player.hasGun && Player.rifle.hasAmmo()){
			StdDraw.text(Player.getX()+ 30,Player.getY()-25, "Ammo: " + Player.rifle.getAmmo());
		}
	}
}