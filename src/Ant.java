
public class Ant extends Bug{
	
	protected int energy=80;

	public Ant(String species, String name, int x, int y, int id) {
		super("Ant", '&', name, x, y, id);
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
}
