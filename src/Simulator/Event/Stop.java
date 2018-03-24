package Simulator.Event;

import Simulator.Simulator;
import Simulator.State.State;

/**
 * An event to stop a simulation. Sets the run variable in state to false upon execution.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class Stop extends Event {

	private Simulator simulator;
	public final static double MAXVAL = 999000;

	/**
	 * Stops the simulation at the max val time
	 */
	public Stop(){
		super(MAXVAL);

	}

	/**
	 * Stops the simulation at the max val time
	 * @param simulator
	 */
	public Stop(Simulator simulator) {
		//super(10000);
		super(MAXVAL);
		this.simulator = simulator;
	}

	/**
	 * Sets the run in state to false
	 */
	@Override
	public void run(){
		this.simulator.getState().setRun(false);
	}

	/**
	 * Overrides the standard to string and returns the event name and ID
	 * @return
	 */
	@Override
	public String toString(){
		return "Terminating			-";
	}
}
