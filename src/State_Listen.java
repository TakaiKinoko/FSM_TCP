import Fsm.State;

public class State_Listen extends State {
	
	private String stateName = "LISTEN";
	
	public State_Listen(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}

}
