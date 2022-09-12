import java.util.Scanner;

public class mainApp {
	private static Scanner readNum = new Scanner(System.in); // Scanner for reading numbers
	private static Scanner readLine = new Scanner(System.in); // Scanner for reading lines
	private static ST st = new ST(); // Search Tree
	
	public static void main(String[] args){
		int choose = 0; // choice of user
		do{
			printMenu(); // print menu
			System.out.printf("Choose: "); 
			choose = readNum.nextInt(); // get choice
			
			System.out.println();
			
			switch (choose){
				case 1: searchSuspect(); break; 
				case 2: insertSuspect(); break;
				case 3: removeSuspect(); break;
				case 4: loadFile(); break;
				case 5: getMeanSavings(); break;
				case 6: printTopSuspects(); break;
				case 7: st.printTreeByAFM(System.out); break; // Call print suspects by AFM from ST
			}
		}while(choose!=0);
	}
	
	// Print menu
	private static void printMenu(){
		System.out.println("Choose what to do:" + "\n" +
						   "0. Exit" + "\n" +
						   "1. Search Suspect" + "\n" +
						   "2. Insert Suspect" + "\n" + 
						   "3. Remove Suspect" + "\n" +
						   "4. Load file" + "\n" +
						   "5. Get Mean Savings of Suspects" + "\n" +
						   "6. Print Top Suspects" + "\n" +
						   "7. Print Suspects by AFM");	
	}
	
	// Search suspect
	private static void searchSuspect(){
		int choose = 0;
		
		// Get choice
		do{
			System.out.println("0. Cancel" + "\n" +
							   "1. Search Suspect by AFM" + "\n" +
							   "2. Search Suspect by Last Name");
			System.out.printf("Choose: ");
			
			choose = readNum.nextInt();	 // save choice		
			
			System.out.println();
		}while(choose!=1 && choose!=2 && choose!=0);
		
		if(choose == 1){ // Search suspect by AFM
			System.out.printf("Give AFM: ");
			
			int afm = readNum.nextInt(); // get afm
			
			System.out.println();
			
			Suspect s = st.searchByAFM(afm); // save suspect from search to s
			if(s!=null)	System.out.println(st.searchByAFM(afm)); // if suspect found print infos
			else System.out.println("Suspect not found!" + "\n"); // else print error
		}
		else if(choose == 2){ // Search suspect by last name
			System.out.printf("Give Last Name: ");
			String last_name = readLine.nextLine(); // get last name
			
			st.searchByLastname(last_name).print(); // print list
		}else return;
	}
	
	// Insert suspect in tree
	private static void insertSuspect(){
		int choose = 0;
		do{
			System.out.println("0. Cancel" + "\n" +
							   "1. Insert Suspect at Leaf" + "\n" +
							   "2. Insert Suspect at Root");
			System.out.printf("Choose: ");
			
			choose = readNum.nextInt();	// get choice
			
			System.out.println();
		}while(choose!=1 && choose!=2 && choose!=0);
		
		if(choose == 0) return; // if cancel return
		System.out.printf("Give First Name: ");
		String first_name = readLine.nextLine(); // save first name
		
		System.out.println();
		
		System.out.printf("Give Last Name: "); 
		String last_name = readLine.nextLine(); // save last name
		
		System.out.println();
		
		System.out.printf("Give AFM: ");
		int AFM = readNum.nextInt(); // save AFM
		
		System.out.println();
		
		System.out.printf("Give Savings: ");
		double savings = readNum.nextDouble(); // save savings
		
		System.out.println();
		
		System.out.printf("Give Taxed Income: ");
		double taxed_income = readNum.nextDouble(); // save taxed income
		
		System.out.println();
		
		Suspect suspect = new Suspect(AFM, first_name, last_name, savings, taxed_income); // create suspect
		
		if(choose == 1){ 
			st.insert(suspect); // Insert suspect normally
		}
		else if(choose == 2){
			st.insert_at_root(suspect); // Insert suspect at root
		}
		
		System.out.println("Insert completed!"); 
		System.out.println();
	}
	
	// Remove suspect from tree
	private static void removeSuspect(){
		System.out.printf("Give AFM: ");
		int AFM = readNum.nextInt(); // save AFM
		
		System.out.println();
		st.remove(AFM); // remove suspect with given AFM
		System.out.println();
	}
	
	// Load suspects from file
	private static void loadFile(){
		System.out.printf("Give path of file: ");
		String path = readLine.nextLine(); // get path of file
		
		System.out.println();
		
		st.load(path); // load suspects from file
	}
	
	// Get mean savings of suspects
	private static void getMeanSavings(){
		System.out.println("Mean Savings: " + st.getMeanSavings()); // print mean savings of suspects
		System.out.println();
	}
	
	// Print top suspects of tree
	private static void printTopSuspects(){
		System.out.printf("Number of Top Suspects: "); 
		
		int k = readNum.nextInt(); // save number of top suspects we want
		st.printTopSuspects(System.out, k); // print top suspects of tree
	}
}