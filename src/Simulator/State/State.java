package Simulator.State;

import Simulator.Simulator;

import java.util.Date;

public class State {


	private boolean run;
	private long STARTTIME;

	public State(){
		run = true;
		STARTTIME = new Date().getTime();
	}

	public long getSTARTTIME(){
		return this.STARTTIME;
	}

	public boolean getRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	/**
	 * @return Returns the current time in milliseconds in relation to the states STARTTIME.
	 */
	public long getTime(){
		return new Date().getTime() - getSTARTTIME();
	}
}
