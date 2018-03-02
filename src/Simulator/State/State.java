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
	public boolean getrun(){
		return run;
	}

	public long getSTARTTIME(){
		return this.STARTTIME;
	}



}
