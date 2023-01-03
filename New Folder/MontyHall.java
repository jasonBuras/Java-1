public class MontyHall {
    public static void main(String [] args) {
        //data for MontyHall simulation
        int correctDoor;
        int openDoor;
        int guess1;
        int guess2;
        int guess1Counter=0;
        int guess2Counter=0;
        int totalCounter=0;
        
        for(int i=0; i<10_000_000; i++) {
            //modeling the monty hall game show
            correctDoor = (int) (Math.random() * 3);
            guess1 = (int) (Math.random() * 3);
                
            //open a door that i;snt correct & it's not the guess
            do {
                openDoor = (int) (Math.random() * 3);
            } while (openDoor == correctDoor || openDoor == guess1);
                
            do {
                guess2 = (int) (Math.random() * 3);
            } while (guess2 == guess1 || guess2 == openDoor);
                
            totalCounter++;

            if (correctDoor == guess1) {
                guess1Counter++;
            }
            if (correctDoor == guess2) {
                guess2Counter++;
            }
        }//end for loop
        System.out.printf("2nd guess wins: %d/%d=%.2f%%\n",   
                           guess2Counter, 
                           totalCounter, 
                           ((1.0 * guess2Counter)/totalCounter)*100);
        System.out.printf("1st guess wins: %d/%d=%.2f%%\n",
                           guess1Counter, 
                           totalCounter, 
                           ((1.0 * guess1Counter)/totalCounter)*100);
    }   
}