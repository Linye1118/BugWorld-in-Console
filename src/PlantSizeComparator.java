import java.util.Comparator;

public class PlantSizeComparator implements Comparator<Plant>{

	@Override
	public int compare(Plant o1, Plant o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.getSize(), o2.getSize());
	}

}
