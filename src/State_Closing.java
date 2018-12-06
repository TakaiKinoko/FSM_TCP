import Fsm.State;

public class State_Closing extends State {

	private String stateName = "CLOSING";
	
	public State_Closing(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}

}
