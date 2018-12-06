import Fsm.State;

public class State_Time_wait extends State {
	
	private String stateName = "TIME_WAIT";
	
	public State_Time_wait(String name) {
		super(name);
	}

	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}
}
