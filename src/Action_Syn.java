import Fsm.Action;
import Fsm.Event;
import Fsm.FSM;

public class Action_Syn extends Action {
	
	//private String syn;
	
	public Action_Syn() {}
	
	@Override
	public void execute(FSM arg0, Event arg1) {
		System.out.println("Event "+ arg1.getName() +" received, current state is "+ arg0.currentState());
	}

}
