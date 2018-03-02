package Simulator;

import Simulator.Event.Event;
import Simulator.Event.EventHolder;

import java.sql.Time;
import java.time.*;

public class Simulator {

	EventHolder eventHolder;

	public Simulator(){
		this.eventHolder = new EventHolder();

		this.eventHolder.add(new Event(3));
		this.eventHolder.add(new Event(1));
		this.eventHolder.add(new Event(5));
	}

	public void run(){
		while (true) {
		}
	}

}
