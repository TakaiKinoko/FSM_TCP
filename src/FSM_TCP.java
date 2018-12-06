/**
 * Fang Han
 * NYU Fall 2018 
 * DCN 
 */

import Fsm.FSM;
import Fsm.FsmException;
import Fsm.Event;
import Fsm.Transition;
import Fsm.Action;
import java.util.Scanner;

public class FSM_TCP {
	public static void main(String[] args) throws FsmException {
		// global variables for keeping track of data packages received/sent 
		int sdata_count = 0;
		int rdata_count = 0;

		State_Closed closed = new State_Closed("CLOSED");
		State_Closing closing = new State_Closing("CLOSING");
		State_Close_wait close_wait = new State_Close_wait("CLOSE_WAIT"); 
		State_Established established = new State_Established("ESTABLISHED");
		State_Fin_wait_1 fin_wait_1 = new State_Fin_wait_1("FIN_WAIT_1");
		State_Fin_wait_2 fin_wait_2 = new State_Fin_wait_2("FIN_WAIT_2");
		State_Last_ack last_ack = new State_Last_ack("LAST_ACK");
		State_Listen listening = new State_Listen("LISTEN");
		State_Syn_sent syn_sent = new State_Syn_sent("SYN_SENT");
		State_Syn_rcvd syn_rcvd = new State_Syn_rcvd("SYN_RCVD");
		State_Time_wait time_wait = new State_Time_wait("TIME_WAIT");
		
		FSM fsm = new FSM("ActiveClient", closed);
		
		Event_Ack eventAck = new Event_Ack("ACK");
		Event_Active eventActive = new Event_Active("ACTIVE");
		Event_Close eventClose = new Event_Close("CLOSE");
		Event_Fin eventFin = new Event_Fin("FIN");
		Event_Passive eventPassive = new Event_Passive("PASSIVE");
		Event_Rdata eventRdata = new Event_Rdata("RDATA");
		Event_Sdata eventSdata = new Event_Sdata("SDATA");
		Event_Syn eventSyn = new Event_Syn("SYN");
		Event_Synack eventSynAck = new Event_Synack("SYNACK");
		Event_Timeout eventTimeOut = new Event_Timeout("TIMEOUT");
		
		InputParser parser = new InputParser();
		
		Transition closedToListen = new Transition(closed, eventPassive, listening, new Action_None());
		Transition closedToSynSent = new Transition(closed, eventActive, syn_sent, new Action_Syn());
		Transition listenToClosed = new Transition(listening, eventClose, closed, new Action_None());
		Transition listenToSynRecvd = new Transition(listening, eventSyn, syn_rcvd, new Action_Syn_ack());
		Transition synRecvdToEstablished = new Transition(syn_rcvd, eventAck,  new State_Established("ESTABLISHED"), new Action_None());
		Transition synSentToEstablished = new Transition(syn_sent, eventSynAck,  new State_Established("ESTABLISHED"), new Action_Ack());
		Transition synRecvdToFinWait1 = new Transition(syn_rcvd, eventClose, fin_wait_1, new Action_Fin());
		Transition finWait1ToFinWait2 = new Transition(fin_wait_1, eventAck, fin_wait_2, new Action_None());
		Transition finWait2ToTimeWait = new Transition(fin_wait_2, eventFin, time_wait, new Action_Ack());
		Transition finWait1ToClosing = new Transition(fin_wait_1, eventFin, closing, new Action_Ack());
		Transition closingToTimeWait = new Transition(closing, eventAck, time_wait, new Action_None());
		Transition timeWaitToClosed = new Transition(time_wait, eventTimeOut, closed, new Action_None());
		Transition synSentToClosed = new Transition(syn_sent, eventClose, closed, new Action_None());
		Transition synSentToSynRecvd = new Transition(syn_sent, eventSyn, syn_rcvd, new Action_Syn_ack());
		Transition establishedToCloseWait = new Transition(established, eventFin, close_wait, new Action_Ack());
		Transition finClosingToLastAck = new Transition(close_wait, eventClose, last_ack, new Action_Fin());
		Transition lastAckToClosed = new Transition(last_ack, eventAck, closed, new Action_None());
		Transition establishedToFinWait1 = new Transition(established, eventClose, fin_wait_1, new Action_Fin());		
		Transition establishedRdataLoop = new Transition(established, eventRdata, established, new Action_None());
		Transition establishedSdataLoop = new Transition(established, eventSdata, established, new Action_None());
		
		// populate the fsm with transitions
		fsm.addTransition(closedToListen);
		fsm.addTransition(closedToSynSent);
		fsm.addTransition(listenToClosed);
		fsm.addTransition(listenToSynRecvd);
		fsm.addTransition(synRecvdToEstablished);
		fsm.addTransition(synRecvdToFinWait1);
		fsm.addTransition(finWait1ToFinWait2);
		fsm.addTransition(finWait2ToTimeWait);
		fsm.addTransition(finWait1ToClosing);
		fsm.addTransition(closingToTimeWait);
		fsm.addTransition(timeWaitToClosed);
		fsm.addTransition(synSentToClosed);
		fsm.addTransition(synSentToSynRecvd);
		fsm.addTransition(synSentToEstablished);
		fsm.addTransition(establishedToCloseWait);
		fsm.addTransition(establishedToFinWait1);
		fsm.addTransition(finClosingToLastAck);
		fsm.addTransition(lastAckToClosed);		
		fsm.addTransition(establishedRdataLoop);
		fsm.addTransition(establishedSdataLoop);
		
		// welcoming message
		System.out.printf("\nTCP finite state machine simulation has started.\nPlease enter a valid command:\n");
		
		// Parse commands, take actions
		Scanner input = new Scanner(System.in);
		String command = input.next();

		while(command != null) {
			System.out.printf("\nTCP finite state machine currently in %s, parsing command...\n", fsm.currentState());
			String last_state = fsm.currentState().getName();
			try {			
				fsm.doEvent(parser.getEventFromString(command));
				// Reset rdata_count and sdata_count to zero as this is a new session
				if (last_state != "ESTABLISHED" && fsm.currentState().getName().equals("ESTABLISHED")) {
					rdata_count = 0;
					sdata_count = 0;
				}else if (last_state.equals("ESTABLISHED") && command.equals("RDATA")) {
					rdata_count += 1;
					System.out.printf("Data recieved: %d\n", rdata_count);
				}else if (last_state.equals("ESTABLISHED") && command.equals("SDATA")) {
					sdata_count += 1;
					System.out.printf("Data sent: %d\n", sdata_count);}
				}catch(FsmException e) {
					System.out.printf("%s\n", e.toString());
				}
				System.out.printf("\nNow accepting the next command: \n");
				command = input.next();			
				}
			input.close();
		}
	
	private static class InputParser {
		Scanner input;
		String[] commands;

		InputParser(){
			input = new Scanner(System.in);
			commands = new String[1];
		}

		public Event getEventFromString(String input) {		
			if(input.equals("ACTIVE")) {
				return new Event_Active(input);
			}
			else if (input.equals("ACK")) {
				return new Event_Ack(input);
			}
			else if (input.equals("CLOSE")) {
				return new Event_Close(input);
			}
			else if (input.equals("FIN")) {
				return new Event_Fin(input);
			}
			else if (input.equals("PASSIVE")) {
				return new Event_Passive(input);
			}
			else if (input.equals("RDATA")) {
				return new Event_Rdata(input);
			}
			else if (input.equals("SDATA")) {
				return new Event_Sdata(input);
			}
			else if (input.equals("SYN")) {
				return new Event_Syn(input);
			}
			else if (input.equals("SYNACK")) {
				return new Event_Synack(input);
			}
			else if (input.equals("TIMEOUT")) {
				return new Event_Timeout(input);
			}
			System.out.println("Invalid command.");
			return new Event_Invalid("INVALID");
		}
	}
}
