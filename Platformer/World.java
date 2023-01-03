import java.util.Scanner;

public class World {
    private String[][][] levels;
    public World(){
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        levels = new String[count][][];

        for (int lvl=0;lvl<count;lvl++){
            int rows = input.nextInt();
            int cols = input.nextInt();
            input.nextLine();

            setLevel(lvl, rows, cols, input);
        }
    }
    private void setLevel(int lvl, int rows, int cols, Scanner input){
        levels[lvl] = new String[rows][cols];

        input.useDelimiter("");
        for (int y=0;y < rows; y++){
            for(int x=0;x<cols;x++){
                String tile = input.next();
                levels[lvl][y][x] = tile;
            }
            input.nextLine();
        }
        input.reset();
    }

    public String[][] getLevel(int level) {
        return levels[level];
    }
    public int getLength(){
        return this.levels.length;
    }
}
