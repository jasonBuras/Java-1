import java.util.Scanner;
/*
I would like to make this into a GUI once we get into that part of class.
-Have the program run as a GUI and have the GUI print out a .txt (or something) file for easy copy/paste.
-Make some sort of auto-setter
*/

public class AutoGetterSetter{
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

	public static void main(String[]args){
		System.out.print("Hello and welcome to Auto Getter and Setter v1.2\nBy: Jason Buras\n\nPurpose: To quickly create Getter/Setter methods, and because I was bored.\nExample:\nInput: 1, Health, 1\nOutput: public static int getHealth(){\n//your code here\n}\n\nIf any bugs/suggestions, hit me up on Discord: rambeaux504#3269\n\n");
		Scanner input=new Scanner(System.in);
		int counter=1;
		System.out.print("How Many Methods would you like to create?\nEnter Number: ");
		int cases= input.nextInt();
		input.nextLine();
		System.out.print("\033[2J\033[1;1H");
	
		for(int i=0;i<cases;i++){
			
			System.out.println("Method: "+ counter + "/" +cases);
			String insideBrackets="";
			Boolean isGetter=false;
			Boolean isSetter=false;
			boolean isBoth=false;
			System.out.println(ANSI_RED + "public static " +ANSI_RESET +ANSI_YELLOW_BACKGROUND+ANSI_BLACK+ "[Data Type]" +ANSI_RESET+ " get/setVariableName()");
			System.out.print("Data Type?\n1) int\n2) long\n3) double\n4) float\n5) String\n6) Boolean\n7) Other (Uses _ as a placeholder)\nSelect Number: ");
			int temp= input.nextInt();
			String dataType ="";
				switch(temp){
				case 1: dataType = "int"; System.out.println(dataType); break;
				case 2: dataType = "long"; System.out.println(dataType); break;
				case 3: dataType = "double"; System.out.println(dataType); break;
				case 4: dataType = "float"; System.out.println(dataType); break;
				case 5: dataType = "String"; System.out.println(dataType); break;
				case 6: dataType = "Boolean"; System.out.println(dataType); break;
				case 7: dataType = "_"; System.out.println(dataType); break;
				case 69: dataType = "Niiice"; System.out.println("Grow up..."); break;
				default: System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Unrecognized Command. Please Pick a number between 1-7"+ANSI_RESET); dataType = "ERROR"; break;
				}
			input.nextLine();
			System.out.print("\033[2J\033[1;1H");
			System.out.println("Method: "+ counter + "/" +cases);
			System.out.println(ANSI_RED + "public static " +ANSI_RESET +ANSI_GREEN +dataType+ ANSI_RESET+ " get/set"+ANSI_YELLOW_BACKGROUND+ANSI_BLACK+"[VariableName]"+ANSI_RESET+"()");
			System.out.print("Variable Name (First letter capitalization recommended): ");
			String variableName = input.nextLine();
			System.out.print("\033[2J\033[1;1H");
			System.out.println("Method: "+ counter + "/" +cases);
			System.out.println(ANSI_RED + "public static " +ANSI_RESET +ANSI_GREEN +dataType+ ANSI_RESET+" "+ ANSI_YELLOW_BACKGROUND+ANSI_BLACK+"[get/set]"+ANSI_RESET+ANSI_GREEN +variableName+ ANSI_RESET+ "()");
			System.out.print("Getter or Setter?\n1) Getter\n2) Setter\n3) Both\nSelect Number: ");
			int temp2 = input.nextInt();
			String getSetName ="";
			switch(temp2){
				case 1: getSetName = "get"; isGetter=true; 
					break;
				case 2: getSetName = "set"; isSetter=true; 
					break;
				case 3: isBoth=true;
				default: System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"Unrecognized Command. Please Choose 1 or 2"+ANSI_RESET); getSetName = "ERROR"; 
					break;
			}System.out.print("\033[2J\033[1;1H");
			if(isGetter && !isSetter){
				insideBrackets="return " + variableName.toLowerCase() +";"; //Need to come up with a better method for this since it could be a variable like amountOfArrows
				System.out.printf(ANSI_GREEN_BACKGROUND+ANSI_BLACK+"\nOUTPUT:\npublic static %s %s%s(){\n%s\n}\n" +ANSI_RESET, dataType, getSetName, variableName, insideBrackets);
				
			}
			else if(isSetter && !isGetter){
				insideBrackets="this."+variableName.toLowerCase()+ " = " + "new"+variableName;
				System.out.printf(ANSI_GREEN_BACKGROUND+ANSI_BLACK+"\nOUTPUT:\npublic static %s%s(%s new%s){\n%s\n}\n" +ANSI_RESET, getSetName, variableName, dataType, variableName, insideBrackets);
				
			}
			else if(isBoth){
				getSetName="get";
				insideBrackets="return " + variableName.toLowerCase() +";"; //Need to come up with a better method for this since it could be a variable like amountOfArrows
				System.out.printf(ANSI_GREEN_BACKGROUND+ANSI_BLACK+"\nGETTER OUTPUT:\npublic static %s %s%s(){\n%s\n}\n" +ANSI_RESET, dataType, getSetName, variableName, insideBrackets);
				getSetName="set";
				insideBrackets="this."+variableName.toLowerCase()+ " = " + "new"+variableName;
				System.out.printf(ANSI_GREEN_BACKGROUND+ANSI_BLACK+"\nSETTER OUTPUT:\npublic static %s%s(%s new%s){\n%s\n}\n" +ANSI_RESET, getSetName, variableName, dataType, variableName, insideBrackets);
				
			}
			else if(!isGetter && !isSetter){
				insideBrackets="//your code here";
				System.out.printf(ANSI_GREEN_BACKGROUND+ANSI_BLACK+"\nOUTPUT:\npublic static %s %s%s(){\n%s\n}\n" +ANSI_RESET, dataType, getSetName, variableName, insideBrackets);
				
			} 
			counter++;
			System.out.println(ANSI_RED_BACKGROUND+ANSI_BLACK+"COPY/PASTE NOW because once you start the next method, the terminal will clear"+ANSI_RESET);			
		}
		System.out.println("Thank you for using!");
		
	}

}