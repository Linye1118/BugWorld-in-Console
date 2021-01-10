import java.util.Comparator;

public class BugEnergyComparator implements Comparator<Bug>{

	@Override
	public int compare(Bug o1, Bug o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.getEnergy(), o2.getEnergy());
	}

}
