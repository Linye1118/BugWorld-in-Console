import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BugWorld {

	public int id;
	public ArrayList<Bug> bugs= new ArrayList<Bug>();
	public ArrayList<Plant> plants=new ArrayList<Plant>();
	public ArrayList<Obstacle> obstacles=new ArrayList<Obstacle>();
	public ArrayList<Object> objects=new ArrayList<Object>();
	
	private static Scanner scan = new Scanner(System.in);
	
	protected int worldHeight=10;
	protected int worldWidth=32;
	private int[][] position = new int[worldHeight][worldWidth]; //2D array to store positions
	protected char stepPrint ='.';
	
	private List<Bug> sortBugs= new ArrayList<>();//to implement Comparators
	
	//constructors for main
	
	public BugWorld() {
		//BugWorld Part I
		loadDefaultItems();
//		bugInitialize();
		drawWorld();
//		bugsMoveAround(); 
		
		//BugWorld Part II
		smellFood();
		updateWorld();
//		switchMenu();
		
		//create Generic and use Comparators
		listBugs();
//		pairAndLists();	
	}
	
//	private void switchMenu(int mn) {
//		// TODO Auto-generated method stub
//		switch(mn) {
//		case 1: this.drawWorld();
//		case 2: this.bugInitialize();
//		case 3: this.bugsMoveAround();
//		case 4: this.smellFood();
//	}
//	}
	
	public void bugInitialize(Bug b) {
		if (b.getX() < 0 || b.getX() >= worldWidth || b.getY() < 0 || b.getY() >= worldHeight) {
			return;
		}
		bugs.add(b);
	}
	
	public void listBugs() {
		System.out.println("Choose List bugs by: 1.Energy, 2.Species & Name...");
		int choice = scan.nextInt();
		switch(choice) {
		case 1: 
			BugEnergyComparator bugComparator = new BugEnergyComparator();
			Collections.sort(sortBugs, bugComparator);
			System.out.println("Sort by Energy: ");
			for (Bug b:sortBugs) {
				System.out.println(b.toString());
			}
			break;
		case 2: 
			BugComparator bugSNComparator = new BugComparator();
			Collections.sort(sortBugs,bugSNComparator);
			System.out.println("Sort by Species & Name: ");
			for (Bug b:sortBugs) {
				System.out.println(b.toString());
			}
			break;
		}
	}
	
	public void pairAndLists() {
		//give value to Pair list bugNameEnergy
		List<Pair> bugNameEnergy = new ArrayList<>(); //to check how to use class PairValue
		
		for (Bug b: bugs) {
			String name = b.name;
			int energy = b.getEnergy();
			Pair p = new Pair(name,energy);
			bugNameEnergy.add(p);
		}
		System.out.println(bugNameEnergy.toString());
		
		List<Pair> bugNameEnergy2 = new ArrayList<>();
		for (Bug b: bugs) {
			String name = b.name;
			int energy = b.getEnergy();	
			List<String> N = new ArrayList<String>();
			N.add(name);
			List<Integer> E = new ArrayList<Integer>();
			E.add(energy);
			Pair P = new Pair(N, E);
			bugNameEnergy2.add(P);
		}
		System.out.println(bugNameEnergy2.toString());
	}	
	
	/*checkPosition function adjust the x, y according to the world rules
	 * later on pass the adjusted x, y to startX startY for next move.
	 */
	public void checkPosition(int Id) {
		/* Rules:
		 * 1.Bugs cannot move outside world;(done)
		 * 2.Bugs can sit on position of plants(show plants) or on each other bugs, but cannot on obstacles;(?)
		 * 3.Bugs cannot move across obstacles; (done)
		*/
		//System.out.println("Go into check Position"); //test
//		int[][] position = new int[worldHeight][worldWidth];
		//hit obstacle and change directions
		
		for (Bug m : bugs) {
			if (m.getId()==Id) {
				int xMove = m.xMove;
				int yMove = m.yMove;
				String dir=m.dir;
				if (dir.equals("N")) {//go north
					//stop bug in front of obstacle
					for (Obstacle o: obstacles) {
						if(o.getY()==yMove && o.getX()==xMove) {
							System.out.println(m.getSymbol()+" Bug meet with an Ø when go N!" + xMove + " " + yMove);
							yMove=o.getY()+1;
							System.out.println("adjust back to Y: " + yMove);
						}	
					}
					if(yMove<=0) {//go north
						//if bug move across the boundary pull them backwards inside world
						yMove=1;
						System.out.println(m.getSymbol()+" Hit wall and pull back to Y: " + yMove);
					}
				
				}else if(dir.equals("S")){//go south
					for (Obstacle o: obstacles) {
						if(o.getY()==yMove && o.getX()==xMove) {
							System.out.println(m.getSymbol()+" Bug meet with an Ø when go S!"+ xMove + " " + yMove);
							yMove=o.getY()-1;
							System.out.println("adjust back to Y: " + yMove);
						}
					}
					if (yMove>=worldHeight-1) {
						yMove=worldHeight-2;
						System.out.println(m.getSymbol()+" Hit wall and pull back to Y: " + yMove);
					}

				}else if(dir.equals("E")){//go east
					for (Obstacle o: obstacles) {
						if(o.getX()==xMove && o.getY()==yMove) {
							System.out.println(m.getSymbol()+" Bug meet with an Ø when go E!"+ xMove + " " + yMove);
							xMove=o.getX()-1;
							System.out.println("adjust back to X:" + xMove);
						}
					}
					if(xMove>=worldWidth-1) {
						xMove=worldWidth-2;
						System.out.println(m.getSymbol()+" Hit wall and pull back to X: " + xMove);
					}

				}else if(dir.equals("W")){//go west
					for (Obstacle o: obstacles) {
						if(o.getX()==xMove && o.getY()==yMove) {
							System.out.println(m.getSymbol()+" Bug meet with an Ø when go W!"+ xMove + " " + yMove);
							xMove=o.getX()+1;
							System.out.println("adjust back to X:" + xMove);
						}
					}
					if(xMove<=0) {
						xMove=1;
						System.out.println(m.getSymbol()+ " Hit wall and pull back to X: " + xMove);
					}
				}//end of check rules			
			
			//record the end x y to Bug xStart yStart for next time use
			m.setxStart(xMove);
			m.setyStart(yMove);
			m.steps++;
			m.setEnergy(m.getEnergy()-1);
			
			//replace id track with stepPrints
			for (int i=0; i<worldHeight; i++) {
				for (int j=0; j<worldWidth; j++) {
					if (position[i][j]==m.getId()) {
						position[i][j]=999;
					}
				}
			}

			//give the new position to Array for draw
			position[yMove][xMove]=m.getId();
			//give the value to object list
			Object c = new Object(m.getId(), xMove, yMove);
			objects.add(c);
			}
		}
	}
	
	public void drawWorld() {
		int h = worldHeight; int w = worldWidth;// height 10 width 32
		for (int i =0; i<h; i++) {
			for (int j =0; j<w; j++) {
				if (j==0) {
					System.out.print("|"); //world left side	
				}else if (j==w-1) {
					System.out.println("|");//world right side
				}else if (i==0 || i==h-1) {
					System.out.print("-");//world top and bottom
				}else {
					boolean objectFlag=false;
					for (Bug k : bugs) {
						if(k.getX()==j && k.getY()==i) {
							System.out.print(k.getSymbol());
							objectFlag=true;
							break; 
						}
					}
					for (Plant p : plants) {
						if(p.getX()==j && p.getY()==i) {
							System.out.print(p.getSize());
							objectFlag=true;
							break;
						}
					}
					for (Obstacle o : obstacles) {
						if(o.getX()==j && o.getY()==i) {
							System.out.print(o.getSymbol());
							objectFlag=true;
							break;
						}
					}
					if (objectFlag==false) {
					System.out.print(" ");
					}
				}				
			}
		}
	}
	/*show default set as
	|------------------------------|
	|                              |
	|           %           Ø      |
	|                    5         |
	|      2    Ø                  |
	|         @                    |
	|        Ø                     |
	|                 9      &     |
	|                              |
	|------------------------------|
	*/
	
	public void bugsMoveAround() {	
		//random moves from [1 to 10]
		int randomMoves=(int)(Math.random()*(10))+1;
		for(int i=0; i<randomMoves; i++) {
			System.out.println("RandoMove "+randomMoves+ " times " + (i+1));
			for (Bug b: bugs) {
				if(b.steps==0) {
					b.move(b.getX(),b.getY());
					this.checkPosition(b.getId());
					System.out.println(b.printBugMove());
				}else {
					b.move(b.xStart, b.yStart);
					this.checkPosition(b.getId());
					System.out.println(b.printBugMove());
				}
			}
			
			this.updateWorld();
		}
		
	}
	
	public void updateWorld() {
		//draw new world
		int H = worldHeight; int W = worldWidth;// height 10 width 32
		for (int i =0; i<H; i++) {
			for (int j =0; j<W; j++) {
				if (j==0) {
					System.out.print("|"); //world left side	
				}else if (j==W-1) {
					System.out.println("|");//world right side
				}else if (i==0 || i==H-1) {
					System.out.print("-");//world top and bottom
				}else if (position[i][j]==999) {
					System.out.print('.');//print step prints
				}else {
					boolean objectFlag=false;
					for (Obstacle o : obstacles) {
						if(o.getX()==j && o.getY()==i) {
							System.out.print(o.getSymbol());
							objectFlag=true;
						}
					}
					for (Plant p : plants) {
						if(p.getX()==j && p.getY()==i) {
							System.out.print(p.getSize());
							objectFlag=true;
						}
					}
					for (Bug b : bugs) {
						if(position[i][j]==b.getId() && objectFlag==false) {	
							System.out.print(b.getSymbol());
							objectFlag=true;
						}
					}
					if (objectFlag==false) {
					System.out.print(" ");
					}
				}
			}
		}
	}
	
	//findTarget() is to find nearest target for bugs to move
	public int findTarget(ArrayList<Integer> targets) {
		int minDis=0;
		int targetID=0;
		for (Bug t : bugs) {
			for (int i=0; i<targets.size();i++) {
				for (Object o: objects) {
					if (o.getId()==targets.get(i)) {
						int xDis=o.x-t.xStart;
						int yDis=o.y-t.yStart;
						if (minDis==0) {
							minDis=xDis+yDis; //give mini Distance the 1st value
							targetID=o.getId();
						}else if(minDis!=0 && minDis>(xDis+yDis)) {
							minDis=xDis+yDis; 
							targetID=o.getId();
						}	
					}
				}		
			}
		}
		//System.out.println("Test findTarget return: "+targetID);
		return targetID;
	}
	public String senseDirection(int targetID) {
		//move bug towards the target
		String dir=""; 
		for (Bug b: bugs) {
			for (Object p: objects) {
				if (p.getId()==targetID) {
					int xDis=Math.abs(p.x-b.xStart);
					int yDis=Math.abs(p.y-b.yStart);
					//move towards x first then y
					if (xDis==0 && p.y<b.yStart) {
						dir="N";
					}else if(xDis==0 && p.y>b.yStart) {
						dir="S";
					}else if(yDis==0 && p.x<b.xStart) {
						dir="W";
					}else if(yDis==0 && p.x>b.xStart) {
						dir="E";
						
					}else if(xDis>yDis && p.x<b.xStart && p.y<b.yStart) {
						dir="WN";
					}else if(xDis<yDis && p.x<b.xStart && p.y<b.yStart) {
						dir="NW";
					}else if(xDis>yDis && p.x<b.xStart && p.y>b.yStart) {
						dir="WS";
					}else if(xDis<yDis && p.x<b.xStart && p.y>b.yStart) {
						dir="SW";
					}else if(xDis>yDis && p.x>b.xStart && p.y<b.yStart) {
						dir="EN";
					}else if(xDis<yDis && p.x>b.xStart && p.y<b.yStart) {
						dir="NE";
					}else if(xDis>yDis && p.x>b.xStart && p.y>b.yStart) {
						dir="ES";
					}else if(xDis<yDis && p.x>b.xStart && p.y>b.yStart) {
						dir="SE";	
					}
				}
			}
		}
		//System.out.println("Test senseDir return dir: "+dir);
		return dir;
	}
	
	public String findAPass(String targetDir) {
		/* find a Pass () Tasks:
		 * 1. Avoid obstacles
		 * 2. Check boundaries
		 * 3. If possible not go through old pass
		 * 4. find the shortest road
		 * 5. Bugs cannot go on obstacle, but can be on plants or other bugs
		 */
		String passDir=""; 
		//System.out.println("findApass targetDir: "+targetDir);//test
		String subDir=targetDir.substring(0,1);
		
		for (Bug m: bugs) {
			int xE=m.xStart+1; int xW=m.xStart-1;
			int yN=m.yStart-1; int yS=m.yStart+1;
			int pass = 4;
			
			boolean N=true; boolean W=true;
			boolean E=true; boolean S=true;
					
			for (Obstacle o: obstacles) { //check boundary and obstacles
				if(o.y==yN && o.x==m.xStart ||yN==0) {pass--;N=false;}
				if(o.x==xE && o.y==m.yStart ||xE==worldWidth-1) {pass--;E=false;}
				if(o.y==yN && o.x==m.xStart ||yS==worldHeight-1) {pass--;S=false;}
				if(o.x==xW && o.y==m.yStart ||xW==0) {pass--;W=false;}
			}		
			//do not let the bug go back to the old road
			if(m.steps>0) {
				if(m.dir=="N") {S=false;}
				else if (m.dir=="S") {N=false; pass--;}
				else if (m.dir=="E") {W=false; pass--;}
				else if (m.dir=="W") {S=false; pass--;}
			}		
					
			if(pass<=0 && m.steps==0) {
				System.out.println(m.getSymbol()+" No way to go..."); //should not happen...
			}		
			else if(pass==0 && m.steps>0) {//change the direction if stucked
				if(m.dir=="N") {passDir="S";}
				else if (m.dir=="S") {passDir="N";}
				else if (m.dir=="E") {passDir="W";}
				else if (m.dir=="W") {passDir="E";}
			}
			else if(pass==1) {
				if (N==true) {passDir="N";}
				else if(E==true) {passDir="E";}	
				else if(W==true) {passDir="W";}	
				else {passDir="S";}
			}
			else if(pass<=4 && pass>1) {
				if (subDir=="N" && N==true) {passDir="N";}
				else if(subDir=="S" && S==true) {passDir="S";}
				else if(subDir=="E" && E==true) {passDir="E";}
				else if(subDir=="W" && W==true) {passDir="W";}
				
				if(pass<=3) {
					if (subDir=="N" && N==false) {
						if (targetDir=="NW") {passDir="W";}
						if (targetDir=="NE") {passDir="E";}
						if (targetDir=="N") {passDir="E";}	
						if (pass==2) {
							if (E==false) {passDir="W";}
							else if(W==false) {passDir="E";}	
							}
						}
					}
					else if(subDir=="S" && S==false) {
						if(targetDir=="SW") {passDir="W";}
						if(targetDir=="SE") {passDir="E";}
						if(targetDir=="S") {passDir="E";}
						if(pass==2) {
							if(E==false) {passDir="W";}
							else if(W==false) {passDir="E";}
						}
					}
					else if(subDir=="E" && E==false) {
						if(targetDir=="EN") {passDir="N";}
						if(targetDir=="ES") {passDir="S";}
						if(targetDir=="E") {passDir="N";}
						if(pass==2) {
							if(N==false) {passDir="S";}
							else if(S==false) {passDir="N";}
						}
					}
					else if(subDir=="W" && W==false) {
						if(targetDir=="WN") {passDir="N";}
						if(targetDir=="WS") {passDir="W";}
						if(targetDir=="W") {passDir="N";}
						if(pass==2) {
							if(N==false) {passDir="S";}	
							else if(S==false) {passDir="N";}
						}
					}
				}
			}			

		return passDir;
	}
	
	public void smellFood() {
	/* Rules:
	 * 1.Butterfly love Daisy, Ant eat all kinds of plants, beetle eat ant;
	*/	
	int targetID=0;
	String passDir="";
	ArrayList<Integer> targets=new ArrayList<Integer>();
	
	//butterfly smell daisy
	for (Bug b : bugs) {
		//butterfly smell Daisy
		if (b.getSpecies().equals("Butterfly")) {
			for (Plant t: plants) {
				if (t.getSpecies().equals("Daisy")) {
					targets.add(t.getId());
				}
			}
			
			targetID = this.findTarget(targets);
			String targetDir = this.senseDirection(targetID);
			
			passDir = this.findAPass(targetDir);
			System.out.println("from smell food Butterfly: " + passDir);
			
			b.moveForFood(passDir);
			
			//give the new position to Array for draw
			position[b.yStart][b.xStart]=b.getId();
			
			System.out.println("Butterfly position[b.yStart][b.xStart]" + b.getxStart() + " " + b.getyStart());//test
			
			//give the value to object list
			Object c = new Object(b.getId(), b.xStart, b.yStart);
			objects.add(c);
			
			//eat food
			for (Plant p:plants) {
				if (b.xStart==p.getX() && b.yStart==p.getY()) {
					System.out.println("Butterfly find the Dasiy.");
					p.setSize(p.getSize()-1);//reduce plant size
					b.setEnergy(b.getEnergy()+1);//bug increase energy
				}	
			}
		}//butterfly loop end
		
		//Ant smell all plants
		if(b.getSpecies().equals("Ant")) {
			for (Object t: objects) {
				if (t.id>200 && t.id<300) { //plants id range
					targets.add(t.getId());
				}
			}
			targetID = this.findTarget(targets);
			String dir = this.senseDirection(targetID);
			passDir = this.findAPass(dir);
			System.out.println("from smell food Ant: " + passDir);
			
			b.moveForFood(passDir);
			
			//pass values
			position[b.yStart][b.xStart]=b.getId();
			
			System.out.println("Ant position[b.yStart][b.xStart]" + b.yStart + " " + b.xStart);//Test
			
			//give the value to object list
			Object c = new Object(b.getId(), b.xStart, b.yStart);
			objects.add(c);
					
			//eat food
			for (Plant p: plants) {
				if(p.getId()==targetID) {
					if (b.xStart==p.getX() && b.yStart==p.getY()) {
					System.out.println("Ant find the plant.");
					p.setSize(p.getSize()-1);//reduce plant size
					b.setEnergy(b.getEnergy()+1);//bug increase energy
					}
				}	
			}
		}//ant loop end
		
		//Beetle smell Ants and choose the nearest
		if(b.getSpecies().equals("Beetle")) {
			for (Bug a: bugs) {
				if(a.getSpecies().equals("Ant")) {
					targets.add(a.getId());
				}
			}
			targetID = this.findTarget(targets);
			String dir = this.senseDirection(targetID);
			passDir = this.findAPass(dir);
			System.out.println("from smell food Beetle: " + passDir);
			
			b.moveForFood(passDir);
			//pass values
			position[b.yStart][b.xStart]=b.getId();
			System.out.println("Beetle position[b.yStart][b.xStart]" + b.yStart + " " + b.xStart);//Test
			
			//give the value to object list
			Object c = new Object(b.getId(), b.xStart, b.yStart);
			
			objects.add(c);
			
			//eat food
			for(Bug a: bugs) {
				if(a.getId()==targetID) {
					if (b.xStart==a.getX() && b.yStart==a.getY()) {
						System.out.println("Beetle find the Ant.");
						a.setEnergy(a.getEnergy()-1);//reduce Ant energy
						b.setEnergy(b.getEnergy()+1);//beetle increase energy
					}
				}	
			}
		}//Beetle loop end
		
	}//all bugs loop end
	
	//this.updateWorld();
	}
	
	/*
	public void eatFood(int targetID) {
		for(Bug a: bugs) {
			if(a.getId()==targetID) {
				if (b.xStart==a.getX() && b.yStart==a.getY()) {
					System.out.println("Beetle find the Ant.");
					a.setEnergy(a.getEnergy()-1);//reduce Ant energy
					b.setEnergy(b.getEnergy()+1);//beetle increase energy
				}
			}	
		}
	}
	*/
	public void loadDefaultItems() {
		//default items for test
			Bug bug1 = new Bug ("Beetle", "LadyBug", '@', 10, 5, 10, 101);
			Bug bug2 = new Bug ("Butterfly", "LunaMoth", '%', 12, 2, 5, 102);
			Bug bug3 = new Bug ("Ant", "FireAnt", '&', 25, 7, 8, 103);
			bugs.add(bug1);bugs.add(bug2);bugs.add(bug3);
			
			sortBugs.add(bug1);sortBugs.add(bug2);sortBugs.add(bug3);
			
			//Plant species, x(1-31), y(1-9), size(0-9), id(2xx)
			Plant plant1 = new Plant("TreeFern", 18, 7, 9,201);
			Plant plant2 = new Plant("Daisy", 7, 4, 2, 202);
			Plant plant3 = new Plant("Sedge", 21, 3, 5, 203);
			plants.add(plant1);plants.add(plant2);plants.add(plant3);
			
			//Obstacle symbol(Ø), x(1-31), y(1-9), id(3xx)
			Obstacle ob1=new Obstacle('Ø', 9, 6, 301);
			Obstacle ob2=new Obstacle('Ø', 12, 4, 302);
			Obstacle ob3=new Obstacle('Ø', 24, 2, 303);
			obstacles.add(ob1);obstacles.add(ob2);obstacles.add(ob3);
			
			//give the info to object list
			Object b1=new Object(101, 10, 5); //id, x, y
			Object b2=new Object(102, 12, 2);
			Object b3=new Object(103, 25, 7);
			objects.add(b1);objects.add(b2);objects.add(b3);
			Object p1=new Object(201, 18, 7);
			Object p2=new Object(202, 7, 4);
			Object p3=new Object(203, 21, 3);
			objects.add(p1);objects.add(p2);objects.add(p3);
			Object o1=new Object(301, 9, 6);
			Object o2=new Object(302, 12, 4);
			Object o3=new Object(303, 24, 2);
			objects.add(o1);objects.add(o2);objects.add(o3);		
	}
	}
