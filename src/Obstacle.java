
public class Obstacle {
	protected char symbol='Ø';
	public int x, y;
	public int id;
	
	public Obstacle(char symbol, int x, int y, int id) {
		super();
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public char getSymbol() {
		return symbol;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
