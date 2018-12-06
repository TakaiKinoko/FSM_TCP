import Fsm.State;

public class State_Closed extends State {

	private String stateName = "CLOSED"; 

	public State_Closed(String name) {		// constructor
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}
}
