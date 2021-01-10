
public class Plant {
	public String species;
	public int x, y;
	public int size;
	public int id;
	
	
	public Plant(String species, int x, int y, int size, int id) {
		super();
		this.species = species;
		this.x = x;
		this.y = y;
		this.size = size;
		this.id = id;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
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


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
}
