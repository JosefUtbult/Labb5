package Simulator.Event;

import Simulator.Simulator;
import Simulator.State.State;

public class Stop extends Event {

	private Simulator simulator;

	public Stop(Simulator simulator) {
		super(Integer.MAX_VALUE);
		this.simulator = simulator;
	}

	@Override
	public void run(){
		this.simulator.getState().setRun(false);
	}
}
