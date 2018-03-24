package Simulator.State;

import Simulator.Event.Event;
import Simulator.Simulator;

import java.util.Observable;

/**
 * A class that holds the status of the simulation.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class State extends Observable {

	private boolean run;	//Describes if the simulator runs
	protected StateViewer stateViewer;	//An object that observes the state, writing every update to the screen
	protected double time;	//Current time in the simulation

	/**
	 * Generates state
	 */
	public State(){
		run = true;
		time = 0;
		
	}

	/**
	 * Notifies the observers of an event.
	 * @param event
	 */
	public void update(Event event){
		this.setChanged();
		this.notifyObservers(event);
	}

	/**
	 * @return Returns run
	 */
	public boolean getRun() {
		return run;
	}

	/**
	 * @param run Sets run
	 */
	public void setRun(boolean run) {
		this.run = run;
	}


	/**
	 * @return Returns the current time in the simulation.
	 */
	public double getTime(){
		return time;
	}

	/**
	 * @param time sets the time in the simulation.
	 */
	public void setTime(double time){
		this.time = time;
	}
}
