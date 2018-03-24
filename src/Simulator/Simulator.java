package Simulator;

import Simulator.Event.Event;
import Simulator.Event.EventHolder;
import Simulator.Event.Stop;
import Simulator.State.State;



/**
 * A class to hold a simulation. It has the possibility to add an event that is to be executed at a specific time and
 * appends this to a queue of events that is placed in order of timing and is later executed at its correlated time.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class Simulator {

	private EventHolder eventHolder;
	private State state;

	public Simulator(State state){
		this.eventHolder = new EventHolder();
		this.state = state;

	}

	/**
	 * @return the state object
	 */
	public State getState(){
		return this.state;
	}

	/**
	 * Runs the simulation. Takes the next event, waits until it is to be executed, and then runs the function "run"
	 */
	public void run(){
		while (this.state.getRun()) {

			Event temp;
			try {
				temp = this.eventHolder.getEvent();
			}
			catch (IndexOutOfBoundsException e){
				break;
			}

			if(temp.getEventTime() != Stop.MAXVAL){
				state.setTime(temp.getEventTime());
			}

			this.eventHolder.removeFirst();

			this.state.update(temp);
			temp.run();
		}
	}

	/**
	 * Forwards an event to the eventHolder.
	 * @param event
	 */
	public void addEvent(Event event){
		this.eventHolder.add(event);

	}

}
