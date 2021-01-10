//a Generic class use for pair
public class Pair<Left, Right> {
	private Left v1; //could be name
	private Right v2; //could be energy
	
	
	public Pair(Left v1, Right v2) {
		super();
		this.v1 = v1;
		this.v2 = v2;
	}

	public String toString() {
		return v1+ " " + v2; 
	}
	
	public Left getV1() {
		return v1;
	}


	public void setV1(Left v1) {
		this.v1 = v1;
	}


	public Right getV2() {
		return v2;
	}


	public void setV2(Right v2) {
		this.v2 = v2;
	}


	
}
