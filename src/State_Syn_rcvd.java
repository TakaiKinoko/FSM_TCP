import Fsm.State;

public class State_Syn_rcvd extends State {
	
	private String stateName = "SYN_RCVD";
	
	public State_Syn_rcvd(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}
}
