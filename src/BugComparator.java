import java.util.Comparator;

public class BugComparator implements Comparator<Bug>{

	@Override
	public int compare(Bug o1, Bug o2) {
		// TODO Auto-generated method stub
		if (o1.getSpecies().compareTo(o2.getSpecies())==0) {
			if(o1.getName().compareTo(o2.getName())==-1) {
				return -1;//less than
			}else if(o1.getName().compareTo(o2.getName())==1) {
				return 1;//greater than
			}else {
				return 0;//equal
			}
		}
		else if (o1.getSpecies().compareTo(o2.getSpecies())==-1) {
			return -1;
		}else {
			return 1;
		}
	}

}
