import Fsm.State;

public class State_Last_ack extends State {

	private String stateName = "LAST_ACK";
	
	public State_Last_ack(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}

}
