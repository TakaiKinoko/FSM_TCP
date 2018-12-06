import Fsm.State;

public class State_Close_wait extends State {

	private String stateName = "CLOSE_WAIT";
	
	public State_Close_wait(String name) {
		super(name);
	}

	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}
}
