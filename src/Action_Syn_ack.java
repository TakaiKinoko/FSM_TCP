import Fsm.Action;
import Fsm.Event;
import Fsm.FSM;

public class Action_Syn_ack extends Action {

	public Action_Syn_ack() {}

	@Override
	public void execute(FSM arg0, Event arg1) {
		System.out.println("Event "+ arg1.getName() + " received, current state is " + arg0.currentState());
	}
}
