public class Menu {
    private static int difficultySetting;
    private static int width= 960;
    private static int height = 540;
    private static String background= "assets/menu.png";

    public static void start() {
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0.0, width);
        StdDraw.setYscale(height, 0.0);
        /*Easy: 385:185 to 565:255
                minX minY  maxX maxY
        * Normal: 385:280 to 565:350
        * Hard: 385:375 to 565:445*/
        draw();

    }
    public static void update(){
        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();
        if (GameEngine.getDistance((385+565)/2, (185+255)/2 ,mouseX,mouseY) <=50 && StdDraw.mousePressed()) {
            difficultySetting = 1;
            System.out.println("EASY");
        } else if (GameEngine.getDistance((385+565)/2, (280+350)/2 ,mouseX,mouseY) <=50 && StdDraw.mousePressed()) {
            difficultySetting = 2;
            System.out.println("MEDIUM");
        } else if (GameEngine.getDistance((385+565)/2, (375+445)/2 ,mouseX,mouseY) <=50 && StdDraw.mousePressed()) {
            difficultySetting = 3;
            System.out.println("HARD");
        }
        while(StdDraw.mousePressed()) {
            Player.setStamina(100);

        }
    }

    public static void draw(){
            StdDraw.picture(width / 2, height / 2, background);
    }

    public static int getDifficultySetting() {
        return difficultySetting;
    }

    public static void setDifficultySetting(int difficultySetting) {
        Menu.difficultySetting = difficultySetting;
    }
}
