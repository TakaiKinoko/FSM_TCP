import Fsm.State;

public class State_Fin_wait_1 extends State {

	private String stateName = "FIN_WAIT_1";
	
	public State_Fin_wait_1(String name) {
		super(name);
	}
	
	public String getName() {
		return this.stateName;
	}
	
	public String toString() {
		return new String("State (" + this.stateName + ")");
	}
}
