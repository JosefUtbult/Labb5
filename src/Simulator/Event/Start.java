package Simulator.Event;

import Simulator.Simulator;

/**
 * An event meant to generate other events upon simulation start
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class Start extends Event {
	protected Simulator simulator;

	/**
	 * Stores the simulator and runs super with start time zero
	 * @param simulator
	 * @param generateStop
	 */
	public Start(Simulator simulator, boolean generateStop){
		super(0);
		this.simulator = simulator;

		if(generateStop){
			simulator.addEvent(new Stop(simulator));
		}
		simulator.getState().setRun(true);
	}

}