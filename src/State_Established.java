import Fsm.State;

public class State_Established extends State {

	private String stateName = "ESTABLISHED";
	
	public State_Established(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}

}
