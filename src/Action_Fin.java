import Fsm.Action;
import Fsm.Event;
import Fsm.FSM;

public class Action_Fin extends Action {

	public Action_Fin() {}

	@Override
	public void execute(FSM arg0, Event arg1) {
		System.out.println("Fin");
	  	System.out.println("Event "+ arg1.getName() +" received, current State is "+ arg0.currentState());
	}
}
