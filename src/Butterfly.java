
public class Butterfly extends Bug{
		
	protected int energy=50;
	
	
	public Butterfly(String species, String name, int x, int y, int id) {
		super("Butterfly", '%', name, x, y, id);
		// TODO Auto-generated constructor stub
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
}
