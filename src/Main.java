import java.util.Scanner;

public class Main {
	private static Scanner scan = new Scanner(System.in);
	//constructors for main
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.newBugs();
		
    	BugWorld w = new BugWorld();
    	//Main.scan.close();
    	//for animation
//    	for (int i = 0; i < 20; i++) {
//			w.drawWorld();
//			w.updateWorld();
//			try {
//				Thread.sleep(250);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public Bug newBugs() {
		//ask user input attributes and add bugs 
		System.out.println("Input species: ");
		String species = scan.nextLine();
		System.out.println("Input Symbol: ");
		char symbol = scan.nextLine().charAt(0);
		System.out.println("Input Name: ");
		String name = scan.nextLine();
		System.out.println("Input X position: ");
		int x = scan.nextInt();
		System.out.println("Input Y position: ");
		int y = scan.nextInt();
		System.out.println("Input energy: ");
		int energy = scan.nextInt();
			
		if (x>=32||y>=10) {
			System.out.println("Be carefull about your selection,");
			System.out.println("Bugs outside world bundary(X:32, Y:10) will be killed,");
			System.out.println("Or they could meet with their predators...");
			System.out.println("Input X position: ");
			x = scan.nextInt();
			System.out.println("Input Y position: ");
			y = scan.nextInt();	
			scan.nextLine();//discard the following line after int scan
			}
			
		Bug b = new Bug (species, symbol, name, x, y, energy);
		return b;
	}

	public int menu() {
		System.out.println("Welcome to BugWorld! Please selected...");
		System.out.println("1.Show bug World");
		System.out.println("2.Add your bugs");
		System.out.println("3.Move bugs arround");
		System.out.println("4.Bug smell food");
		int mn = scan.nextInt();
		scan.nextLine();//discard the following line after int scan
		
		return mn;
	}
	
	
}

