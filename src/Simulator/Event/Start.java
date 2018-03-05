package Simulator.Event;

import Simulator.Simulator;

public class Start extends Event {
	protected Simulator simulator;

	public Start(Simulator simulator){
		super(0);
		this.simulator = simulator;

		simulator.addEvent(new Stop(simulator));
		simulator.getState().setRun(true);
	}

}