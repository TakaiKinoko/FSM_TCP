import Fsm.State;

public class State_Syn_sent extends State {
	
	private String stateName = "SYN_SENT";
			
	public State_Syn_sent(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}
}
