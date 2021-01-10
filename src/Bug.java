

public class Bug implements Comparable<Bug>{
	private String species;
	protected String name;
	private char symbol;
	protected int x;
	protected int y;
	private int energy;
	private int id;
	
	public int xMove;
	public int yMove;
	public int xStart;
	public int yStart;
	
	public int steps;
	public String dir;
	
	public Bug(String name, int x, int y, int id) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public Bug(String species, String name, char symbol, int x, int y, int energy, int id) {
		super();
		this.species = species;
		this.name = name;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.id = id;
	}

	public Bug(String species, char symbol, String name, int x, int y, int id) {
		// TODO Auto-generated constructor stub
		this.species = species;
		this.name = name;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.id = id;
	}

	@Override
	public int compareTo(Bug o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getEnergy(), o.getEnergy());
	}

	
	public String toString() {
		return "Bug species: " + this.species + " Name: " + this.name + "\n" + "Initial energy: " + energy;
	}
	
	public String toText() {
		return "Bug "+ this.id +" Symbol: "+symbol+"\nInitial Position: "+ this.x +", "+ this.y;
	}

	public void printBug() {
		System.out.println(this.toString());
		System.out.println(this.toText());
	}
	public String printBugMove() {
		return this.getSymbol()+ " moved X: "+ this.xStart + " Y: "+this.yStart;
	}
	
	
	public void move(int xStart, int yStart) {
		xMove=xStart; yMove=yStart;//initialize values	
		int d = (int)(Math.random()*(4))+1;	
		if (d==1) {
			dir="N";
			yMove=yStart-1;
		}else if (d==2) {
			dir="S";
			yMove=yStart+1;
		}else if (d==3) {
			dir="E";
			xMove=xStart+1;
		}else {
			dir="W";
			xMove=xStart-1;
		}
	}
	
	public void moveForFood(String dir) {
		if(steps==0) {
			xStart=this.x;
			yStart=this.y;
		}else {
			if (dir.equals("N")) {
				yMove=yStart-1;
			}else if(dir.equals("S")){
				yMove=yStart+1;
			}else if(dir.equals("E")){
				xMove=xStart+1;
			}else if(dir.equals("W")){
				xMove=xStart-1;
			}
		}
		System.out.println("from Bug: "+dir);
		System.out.println("from Bug: "+xMove+" "+yMove);
		this.dir=dir;
		this.xStart=xMove;
		this.yStart=yMove;
		this.steps++;
		this.energy--;	
		System.out.println("from Bug: "+xStart+" "+yStart);
		
	}
	
	public int getxStart() {
		return xStart;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getxMove() {
		return xMove;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	
	
}
