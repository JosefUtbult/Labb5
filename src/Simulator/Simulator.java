package Simulator;

import Simulator.Event.Event;
import Simulator.Event.EventHolder;
import Simulator.State.State;


import java.util.Date;

public class Simulator {

	private EventHolder eventHolder;
	private State state;

	public Simulator(State state){
		this.eventHolder = new EventHolder();
		this.state = state;

	}

	public void run(){
		while (this.state.getrun()) {


			try {
				while (getTime() < this.eventHolder.getEvent().getEventTime()){
				}
			} catch (Error e){
				break;
			}

			Event temp = this.eventHolder.getEvent();
			this.eventHolder.removeFirst();

			temp.run();
		}
	}

	public void addEvent(Event event){
		this.eventHolder.add(event);

	}

	public long getTime(){
		return new Date().getTime() - state.getSTARTTIME();
	}

}
