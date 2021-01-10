
public class Beetle extends Bug{
	
	protected int energy=100;
	
	
	public Beetle(String species, String name, int x, int y, int id) {
		super("Beetle", '@', name, x, y, id);
	}
	
	public String getName() {
		return name;
	}

	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}

}
